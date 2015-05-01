/* Cking Inc. (C) 2012. All rights reserved.
 *
 * JbxxBodyView.java
 * classes : com.cking.phss.view.JbxxBodyView
 * @author Administrator
 * V 1.0.0
 * Create at 2012-9-16 上午11:25:10
 */
package com.cking.phss.bluetooth;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.cking.phss.R;
import com.cking.phss.bluetooth.IBluetoothServerReceiver.BcaData;
import com.cking.phss.file.FileLog;
import com.cking.phss.util.CommonUtil;
import com.cking.phss.util.MyApplication;
import com.cking.phss.util.TispToastFactory;

/**
 * 蓝牙服务端，可测血压和人体成分
 * com.cking.phss.view.JbxxBodyView
 * @author Administrator <br/>
 * create at 2012-9-16 上午11:25:10
 */
public class BluetoothServer4And {
    @SuppressWarnings("unused")
    private static final String TAG = "BluetoothServer";
    private static final boolean D = true;
    
    private Context mContext = null;

    private Toast mToast = null;
    
    // Name of the connected device
    private String mConnectedDeviceName = null;
    // Local Bluetooth adapter
    private BluetoothAdapter mBluetoothAdapter = null;
    // Member object for the bluetooth services
    private BluetoothServerService4And mBluetoothService = null;
    // Intent request codes
    private static final int REQUEST_ENABLE_BT = 1;

    // Input packet storage and processing
    ByteArrayOutputStream mPacket = new ByteArrayOutputStream(256);
    byte[] mFullRxData = new byte[256];
    int mRxDataLen = 0;
    boolean mPduValid = true;
    
    private int mHeight = 0;
    private boolean mIsMale = false;
    private int mAge = 0;
    private int mId = 0;
    
    private int mPort = -1;

    /**
     * 侦听接收数据
     */
    public static interface OnReceiveListener {
        /**
         * 接收PBT Serires数据
         */
        public void onReceivePbtSeriresData(int systolic, int diastolic, int pulseRate);
        /**
         * 接收PBT-C Devices数据
         */
        public void onReceivePbtcDevicesData(BcaData bcaData);
        /**
         * 接收PBT-C Devices配置信息
         */
        public void onReceivePbtcDevicesConfig(int id);
    }
    
    private OnReceiveListener mOnReceiveListener = null;

    /**
     * 设置
     *
     * @param listener.
     */
    public void setOnReceiveListener(OnReceiveListener listener) {
        mOnReceiveListener = listener;
    }

    /**
     * @param context
     */
    public BluetoothServer4And(Context context) {
        init(context);
    }

    /**
     * @param context
     */
    private void init(Context context) {
        mContext = context;
        mToast = TispToastFactory.getToast(context, "");

        // 先到存储中读取该设备对应的端口
        if (mPort == -1) {
            mPort = MyApplication.getInstance().globalSp.getInt("HARDWARE_AND_UA767BT", -1);
        }
        
        Log.i(TAG, "ser port: " + mPort);

        // Get local Bluetooth adapter
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        // If BT is not on, request that it be enabled.
        // setupChat() will then be called during onActivityResult
        if (mBluetoothAdapter!=null && !mBluetoothAdapter.isEnabled()) {
            mToast.setText("找不到蓝牙适配器.");
            mToast.show();
        // Otherwise, setup the chat session
        } else {
            if (mBluetoothService == null) {
                // Initialize the BluetoothServerService4And to perform
                // bluetooth connections
                mBluetoothService = new BluetoothServerService4And(context, mHandler, mPort,
                        "PWAccessP", 18);
            }
        }
        
        //09-17 11:02:38.329: I/ANDtestManager(30876): UA-767PBT sn: 5111124008  Reading: 801D4C4152
        //updateView("801D4C4152");09-22 15:29:40.260: D/BluetoothServer(3499): parsePbtData8020423C4C
//        //parsePbtData("8020423C4C");
//        String str = new String("50574341504902005b000000970100dc070915003628dc070916080c16ed4a801f090055432d3431313531323034303030313700005042542d430000000000f0002353542c2b3037382e30356b670d0a52542c303438362e314f0d0a46542c32352e34250d0a48542c2b313734306d6d0d0a42542c30313638336b630d0a4d542c3035352e326b670d0a57542c3034322e356b670d0a49442c30310d0a");
//        byte[] data = CommonUtil.hexString2Bytes(str);
//        processInput(data.length, data);
        //        String str = new String("53542c2b3037382e30356b670d0a52542c303438362e314f0d0a46542c32352e34250d0a48542c2b313734306d6d0d0a42542c30313638336b630d0a4d542c3035352e326b670d0a57542c3034322e356b670d0a49442c30310d0a");
//        //02005b000000970100dc070915003628dc070916080c16ed4a801f090055432d3431313531323034303030313700005042542d430000000000f0002353542c2b3037382e30356b670d0a52542c303438362e314f0d0a46542c32352e34250d0a48542c2b313734306d6d0d0a42542c30313638336b630d0a4d542c3035352e326b670d0a57542c3034322e356b670d0a49442c30310d0a
//
//        String str2 = new String(CommonUtil.hexString2Bytes(str));
//        parsePbtcData(str2);
    }
    
    public void setPbtcConfig(int height, boolean isMale, int age) {
        mHeight = height;
        mIsMale = isMale;
        mAge = age;
    }

    public void close() {
        Log.i(TAG, "close");
        FileLog.i(TAG, "close");
        // Stop the Bluetooth chat services
        if (mBluetoothService != null)
            mBluetoothService.stop();
    }

    public void open() {
        Log.i(TAG, "open");
        FileLog.i(TAG, "open");
        // Performing this check in onResume() covers the case in which BT was
        // not enabled during onStart(), so we were paused to enable it...
        // onResume() will be called when ACTION_REQUEST_ENABLE activity returns.
        if (mBluetoothService != null) {
            // Only if the state is STATE_NONE, do we know that we haven't started already
            if (mBluetoothService.getState() == BluetoothServerService4And.STATE_NONE) {
              // Start the Bluetooth chat services
              mBluetoothService.start();
            }
        }
    }

    /**
     * Sends a message.
     * @param message  A string of text to send.
     */
    private void sendMessage(String message) {
        if (mBluetoothService == null) return;
        Log.i(TAG, "sendMessage,msg:" + message);
        FileLog.i(TAG, "sendMessage,msg:" + message);
        // Check that we're actually connected before trying anything
        if (mBluetoothService.getState() != BluetoothServerService4And.STATE_CONNECTED) {
            mToast.setText(R.string.not_connected);
            mToast.show();
            return;
        }

        // Check that there's actually something to send
        if (message.length() > 0) {
            // Get the message bytes and tell the BluetoothServerService4And to
            // write
            byte[] send = message.getBytes();
            mBluetoothService.write(send);
        }
    }

    // The Handler that gets information back from the
    // BluetoothServerService4And
    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case BluetoothServerService4And.MESSAGE_STATE_CHANGE:
                if(D) Log.i(TAG, "MESSAGE_STATE_CHANGE: " + msg.arg1);
                FileLog.i(TAG, "MESSAGE_STATE_CHANGE: " + msg.arg1);
                switch (msg.arg1) {
                        case BluetoothServerService4And.STATE_CONNECTED:
                    if(D) Log.i(TAG, mConnectedDeviceName);
                    FileLog.i(TAG, mConnectedDeviceName);
                    mPduValid = true; 
                    break;
                        case BluetoothServerService4And.STATE_CONNECTING:
                    if(D) Log.i(TAG, mContext.getResources().getString(R.string.title_connecting));
                    FileLog.i(TAG, mContext.getResources().getString(R.string.title_connecting));
                    break;
                        case BluetoothServerService4And.STATE_LISTEN:
                        case BluetoothServerService4And.STATE_NONE:
                    if(D) Log.i(TAG, mContext.getResources().getString(R.string.title_not_connected));
                    FileLog.i(TAG, mContext.getResources().getString(R.string.title_not_connected));
                    break;
                }
                break;
                case BluetoothServerService4And.MESSAGE_WRITE:
                byte[] writeBuf = (byte[]) msg.obj;
                // construct a string from the buffer
                String writeMessage = new String(writeBuf);
                break;
                case BluetoothServerService4And.MESSAGE_READ:
                if(D) Log.i(TAG, "MESSAGE_READ:" + mPduValid);
                FileLog.i(TAG, "MESSAGE_READ:" + mPduValid);
                if (mPduValid)
                    processInput(msg.arg1, (byte[]) msg.obj);
                break;
                case BluetoothServerService4And.MESSAGE_DEVICE_NAME:
                // save the connected device's name
                    if (msg.getData().containsKey(BluetoothServerService4And.DEVICE_NAME)) {
                        mConnectedDeviceName = msg.getData().getString(
                                BluetoothServerService4And.DEVICE_NAME);
                }
                
                    if (msg.getData().containsKey(BluetoothServerService4And.DEVICE_PORT)) {
                        mPort = msg.getData().getInt(BluetoothServerService4And.DEVICE_PORT);

                        MyApplication.getInstance().globalSp.edit().putInt("HARDWARE_AND_UA767BT", mPort).commit();
                    
                    Log.i(TAG, "save port: " + mPort);
                }
//                mToast.setText("Connected to " + mConnectedDeviceName);
//                mToast.show();
                break;
                case BluetoothServerService4And.MESSAGE_TOAST:
//                mToast.setText(msg.getData().getString(TOAST));
//                mToast.show();
                break;
            }
        }
    };
    // Process bytes arriving from the device
    private void processInput(int len, byte[] rxpacket) {
        if(D) Log.i(TAG, "processInput:" + len + "bytes," + (rxpacket[0] + (rxpacket[1] << 8)));
        Log.d(TAG, "< "+CommonUtil.bytes2HexString(rxpacket, len));
        FileLog.d(TAG, "< "+CommonUtil.bytes2HexString(rxpacket, len));
        
        int i = 0;
        while (i < len) {
            /// 数据包
            if ((rxpacket[i] + (rxpacket[i + 1] << 8)) == 2 || mRxDataLen > 0) {
                ///追加数据
                System.arraycopy(rxpacket, i, mFullRxData, mRxDataLen, len - i);
                mRxDataLen += len - i;
                Log.d(TAG, "recv len:" + mRxDataLen);
                FileLog.d(TAG, "recv len:" + mRxDataLen);
                int realSize = mFullRxData[2] + (mFullRxData[3] << 8) + (mFullRxData[4] << 16) + (mFullRxData[5] << 24);
                if ((realSize+60) == mRxDataLen) {
                    processInputData(mRxDataLen, mFullRxData);
                    mRxDataLen = 0;
                } 
                i += len - i;
            } else if (pwcapiCheck(rxpacket, i, len - i)) {
                i += 6;
            } else if (pwrqpiCheck(rxpacket, i, len - i)) {
                /// 控制包
                mId = (rxpacket[7] - '0') * 10 + (rxpacket[8] - '0');
                
                /// 回调
                if (mOnReceiveListener != null) {
                    /// 重置参数
                    mHeight = 0;
                    mIsMale = false;
                    mAge = 0;
                    mOnReceiveListener.onReceivePbtcDevicesConfig(mId);
                }
                
                /// 假设高度和年龄不为0
                if (mHeight != 0 && mAge != 0) {
                    /// 发送配置数据
                    String str = assemblePbtcConfig(mId, mHeight, mIsMale, mAge);
                    if(D) Log.i(TAG, "Send:" + str);
                    FileLog.i(TAG, "Send:" + str);
                    sendMessage(str);
                }

                i += 9;
            }
        }
    }
    
    /**
     * @param rxpacket
     * @param i
     * @param len
     * @return
     */
    private boolean pwrqpiCheck(byte[] rxpacket, int i, int len) {
        byte[] tmp = new byte[6];
        System.arraycopy(rxpacket, i, tmp, 0, 6);
        String str = new String(tmp);
        if (str.equals("PWRQPI")) {
            return true;
        }
        return false;
    }

    /**
     * @param rxpacket
     * @param i
     * @param len
     * @return
     */
    private boolean pwcapiCheck(byte[] rxpacket, int i, int len) {
        
        byte[] tmp = new byte[6];
        System.arraycopy(rxpacket, i, tmp, 0, 6);
        String str = new String(tmp);
        if (str.equals("PWCAPI")) {
            return true;
        }
        return false;
    }

    /**
     * 组包
     * @param mId2
     * @param mHeight2
     * @param mIsMale2
     * @param mAge2
     * @return
     */
    private String assemblePbtcConfig(int mId, int mHeight, boolean mIsMale, int mAge) {
        //Example 1; ID 01, Male 1,Age 33, Height 1750 mm, GA 9798, Tare 09.5kg, 00 00 00 00 00 00 00 00 00
        //Hex; 3031313333313735306D6D3937393830392E356B67000000000000000000
        int isMale = mIsMale? 1: 0;
        DecimalFormat format = new DecimalFormat("00");
        String str = format.format(mId) + isMale + mAge + mHeight + "6D6D3937393830392E356B67000000000000000000";
        return str;
    }

    // Process bytes arriving from the device
    private void processInputData(int len, byte[] rxpacket) {
        mPacket.write(rxpacket, 0, len);
        Log.d(TAG, "< "+CommonUtil.bytes2HexString(rxpacket, len));
        FileLog.d(TAG, "< "+CommonUtil.bytes2HexString(rxpacket, len));
        
        boolean ok = true;
        boolean done = false;
        int pktLen = 0;
        
        ByteArrayInputStream pdu = new ByteArrayInputStream(mPacket.toByteArray());
        
        // Check the packet type...
        int pktType = pdu.read() + (pdu.read() << 8);
        
        // 数据包
        if (pktType != 2) {
            Log.e(TAG, "< Incorrect packet type");
            FileLog.e(TAG, "< Incorrect packet type");
            ok = false;
        }
        
        // Check the packet length...
        if (ok) {
            pktLen = pdu.read() + (pdu.read() << 8) + (pdu.read() << 16) + (pdu.read() << 24);
            
            if ((pktLen+60) == mPacket.size()) {
                done = true;
            }
            else if ((pktLen+60) < mPacket.size()) {
                Log.d(TAG, "< Packet too big...pktLen="+pktLen);
                FileLog.d(TAG, "< Packet too big...pktLen="+pktLen);
                ok = false;
            }
            else {
                Log.d(TAG, "< Incomplete...");
                FileLog.d(TAG, "< Incomplete...");
            }
        }
        
        // Respond if we can...
        if (ok) {
            if (done) {
                Log.d(TAG, "< type="+pktType);
                Log.d(TAG, "< length="+pktLen);
                FileLog.d(TAG, "< type="+pktType);
                FileLog.d(TAG, "< length="+pktLen);
                
                byte reading[] = new byte[128];
                String dev = "";
                
                int devType = pdu.read() + (pdu.read() << 8);
                pdu.skip(1); // Skip the flag for now
                pdu.skip(7); // Skip the timestamp for now
                pdu.skip(7); // Skip the timestamp for now
                pdu.skip(6); // Skip the bdaddr for now
                pdu.skip(6); // Skip the bdaddr for now

                byte sn[] = new byte[16];
                pdu.read(sn,0,12); // Serial number

                pdu.skip(10); // Skip reserved
                pdu.skip(1); // Skip the battery status for now
                pdu.skip(1); // Skip reserved
                pdu.skip(1); // Skip firmware rev for now

                String strReading = null;
                
                if (devType == 767) { // BP
                    dev="UA-767PBT";
                    pdu.read(reading,0,10);                 
                    strReading = new String(reading,0,10);

                    /// 解析PBT数据
                    parsePbtData(strReading);
                } if (devType == 407) { //PBT-C
                    dev="UC-411PBT-C";
                    pdu.read(reading,0,91);                 
                    strReading = new String(reading,0,91);
                    /// 解析PBT-C数据
                    parsePbtcData(strReading);
                }
                else {
                    dev="UC-321PBT";
                    pdu.read(reading,0,14);                 
                    strReading = new String(reading,0,14);
                }

                String strSN = new String(sn,0,11);

                Log.i(TAG, dev+" sn: "+strSN+" Reading: "+strReading);
                
                mPacket.reset();
            }
        }
        else {
            mPduValid = false;
            mPacket.reset();
            Log.i(TAG, "Invalid PDU!!!");
            FileLog.i(TAG, "Invalid PDU!!!");
            sendMessage("PWA0");
        }
        
        try {
            pdu.close();
        } catch (IOException e) {
            Log.e(TAG, "Problem closing input stream.");
            FileLog.e(TAG, "Problem closing input stream.");
        }
    }

    /**
     * 解析
     * @param strReading
     */
    private void parsePbtData(String strReading) {
        Log.d(TAG, "parsePbtData " + strReading);
        FileLog.d(TAG, "parsePbtData " + strReading);
        String head = strReading.substring(0, 2);
        // 判断数据是否有效，血压仪
        if (head.equals("80")) {
            byte[] hexBytes = CommonUtil.hexString2Bytes(strReading);
            /// 回调
            if (mOnReceiveListener != null) {
                mOnReceiveListener.onReceivePbtSeriresData(hexBytes[1] + hexBytes[2], hexBytes[2],
                        hexBytes[3]);
            }
        }

        sendMessage("PWA1");
    }

    /**
     * 解析
     * @param strReading
     */
    private void parsePbtcData(String strReading) {
//        53 54 2C 2B 30 37 37 2E 34 35 6B 67 0D 0A - ST,+077.45kg - 体重
//        52 54 2C 30 35 30 37 2E 32 4F 0D 0A - RT,0507.2O - 阻抗
//        46 54 2C 32 37 2E 33 25 0D 0A - FT,27.3% - 肥胖度
//        48 54 2C 2B 31 37 34 30 6D 6D 0D 0A - HT,+1740mm - 高度
//        42 54 2C 30 31 36 33 30 6B 63 0D 0A - BT,01630kc - 基础代谢
//        4D 54 2C 30 35 33 2E 34 6B 67 0D 0A - MT,053.4kg - 肌肉质量
//        57 54 2C 30 34 31 2E 31 6B 67 0D 0A - WT,041.1kg - 体内水的质量
//        49 44 2C 30 31 0D 0A - ID,01
        float weight = 0.0f;
        String weightUnit = null;
        int height = 0;
        String heightUnit = null;
        Log.d(TAG, "parsePbtcData " + strReading);
        FileLog.d(TAG, "parsePbtcData " + strReading);
        BcaData bcaData = new BcaData();

        byte[] data = strReading.getBytes();
        int i = 0;
        while (i < data.length) {
            switch (data[i]) {
                case 0x53: /// 体重
                    if (data[i+1] == 0x54) {

                        byte[] tmp = new byte[8];
                        System.arraycopy(data, i + 4, tmp, 0, 8);
                        bcaData.weight = new String(tmp);
                        
                        tmp = new byte[6];
                        System.arraycopy(data, i + 4, tmp, 0, 6);
                        String weightStr = new String(tmp);
                        weight = Float.parseFloat(weightStr);
                        tmp = new byte[2];
                        System.arraycopy(data, i + 10, tmp, 0, 2);
                        weightUnit = new String(tmp);
                    }
                    i += 14;
                    break;
                
                case 0x52: /// 阻抗
                    if (data[i+1] == 0x54) {

                        byte[] tmp = new byte[7];
                        System.arraycopy(data, i + 3, tmp, 0, 7);
                        bcaData.impedance = new String(tmp);
                    }
                    i += 12;
                    break;
                    
                case 0x46: /// 肥胖度
                    if (data[i+1] == 0x54) {

                        byte[] tmp = new byte[4];
                        System.arraycopy(data, i + 3, tmp, 0, 4);
                        bcaData.fatDegree = Float.parseFloat(new String(tmp));
                    }
                    i += 10;
                    break;
                    
                case 0x48: /// 高度
                    if (data[i+1] == 0x54) {
                        byte[] tmp = new byte[6];
                        System.arraycopy(data, i + 4, tmp, 0, 6);
                        bcaData.height = new String(tmp);
                        
                        tmp = new byte[4];
                        System.arraycopy(data, i + 4, tmp, 0, 4);
                        String heightStr = new String(tmp);
                        height = Integer.parseInt(heightStr);
                        tmp = new byte[2];
                        System.arraycopy(data, i + 8, tmp, 0, 2);
                        heightUnit = new String(tmp);
                    }
                    i += 12;
                    break;
                    
                case 0x42: /// 基础代谢
                    if (data[i+1] == 0x54) {

                        byte[] tmp = new byte[7];
                        System.arraycopy(data, i + 3, tmp, 0, 7);
                        bcaData.basalMetabolism = new String(tmp);
                    }
                    i += 12;
                    break;
                    
                case 0x4D: /// 肌肉质量
                    if (data[i+1] == 0x54) {

                        byte[] tmp = new byte[7];
                        System.arraycopy(data, i + 3, tmp, 0, 7);
                        bcaData.muscleMass = new String(tmp);
                    }
                    i += 12;
                    break;
                    
                case 0x57: /// 体内水的质量
                    if (data[i+1] == 0x54) {

                        byte[] tmp = new byte[7];
                        System.arraycopy(data, i + 3, tmp, 0, 7);
                        bcaData.bodyWaterMass = new String(tmp);
                    }
                    i += 12;
                    break;
                    
                case 0x49: /// 编号
                    if (data[i+1] == 0x44) {

                        byte[] tmp = new byte[2];
                        System.arraycopy(data, i + 3, tmp, 0, 2);
                        bcaData.id = Integer.parseInt(new String(tmp));
                    }
                    i += 7;
                    break;
            }
        }
        
        if (heightUnit == null || weightUnit == null) {
            Log.e(TAG, "Parse ERROR!");
            FileLog.e(TAG, "Parse ERROR!");
            return;
        }

        /// 回调
        if (mOnReceiveListener != null) {
            mOnReceiveListener.onReceivePbtcDevicesData(bcaData);
        }
        
        // 等待250ms后发送
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    Thread.sleep(250);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                
                sendMessage("PWA1");
            }
            
        }).start();
    }
}
