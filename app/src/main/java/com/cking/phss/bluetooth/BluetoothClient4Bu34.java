/*
 * Copyright (C) 2009 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.cking.phss.bluetooth;

import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.os.Message;
import android.util.Log;

import com.cking.phss.util.CommonUtil;

/**
 * This is the main Activity that displays the current chat session.
 */
public class BluetoothClient4Bu34 extends BluetoothClient {
    /**
     * @param context
     */
    public BluetoothClient4Bu34(Context context) {
        super(context);
    }

    // Debugging
    private static final String TAG = "BluetoothClient4Bu34";
    private static final boolean D = true;

    private Timer mDelayTimer = new Timer();

    /**
     * 侦听接收数据
     */
    public static interface OnReceiveListener {
        /**
         * 接收胆固醇数据
         */
        public void onReceiveCholData(float fValue, String unit);
        /**
         * 接收甘油三酯数据
         */
        public void onReceiveTgData(float fValue, String unit);
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

    enum SEND_PACKET_TYPE {
        UNKNOWN_PACKET,
        CONNECT_PACKET,
        DEVICE_INFO_PACKET,
        DATA_PACKET,
        DISCONNECT_PACKET
    }
    
    private SEND_PACKET_TYPE mSendPacketType = SEND_PACKET_TYPE.UNKNOWN_PACKET;
    class DelayTimerTask extends TimerTask {

        /* (non-Javadoc)
         * @see java.util.TimerTask#run()
         */
        @Override
        public void run() {

            if (mSendPacketType == SEND_PACKET_TYPE.CONNECT_PACKET) {
                // 发送鉴权
                sendConnectReqPacket();
            } else if (mSendPacketType == SEND_PACKET_TYPE.DEVICE_INFO_PACKET) {
                // 发送获取设备信息
                sendDeviceInfoReqPacket();
            } else if (mSendPacketType == SEND_PACKET_TYPE.DATA_PACKET) {
                // 发送获取数据
                sendDataReqPacket();
            } else if (mSendPacketType == SEND_PACKET_TYPE.DISCONNECT_PACKET) {
                // 发送断开连接
                sendDisconnectReqPacket();
            } else if (mSendPacketType == SEND_PACKET_TYPE.UNKNOWN_PACKET) {
                // 断开连接
                // 屏蔽原因：数据还没发出去就断开会造成异常，使得上层认为通讯失败。
                //mChatService.stop();
            }
        }
    }

    public byte[] assembleConnectReqPacket() {
        byte[] b = new byte[0x2c];
        mProtocolHandler.assembleConnectReqPacket(b, 0x2c);

        return b;
    }

    public byte[] assembleDisconnectReqPacket() {
        byte[] b = new byte[0x2c];
        mProtocolHandler.assembleDisconnectReqPacket(b, 0x2c);

        return b;
    }
    
    public byte[] assembleGetDeviceInfoPacket() {
        byte[] b = new byte[0x0c];
        mProtocolHandler.assembleGetDeviceInfoReqPacket(b, 0x0c);

        return b;
    }

    public byte[] assembleGetDataPacket() {
        byte[] b = new byte[0x0c];
        mProtocolHandler.assembleGetSensorDataReqPacket(b, 0x0c);

        return b;
    }

    public void sendConnectReqPacket() {
        byte[] b = assembleConnectReqPacket();
        if(D) Log.i(TAG, "sendConnectReqPacket:" + CommonUtil.bytes2HexString(b));
        sendMessage(b);
    }
    
    public void sendDeviceInfoReqPacket() {
        byte[] b = assembleGetDeviceInfoPacket();
        if(D) Log.i(TAG, "sendDeviceInfoReqPacket:" + CommonUtil.bytes2HexString(b));
        sendMessage(b);
    }

    public void sendDisconnectReqPacket() {
        byte[] b = assembleDisconnectReqPacket();
        if(D) Log.i(TAG, "sendDisconnectReqPacket:" + CommonUtil.bytes2HexString(b));
        sendMessage(b);
    }
    public void sendDataReqPacket() {
        byte[] b = assembleGetDataPacket();
        if(D) Log.i(TAG, "sendDataReqPacket:" + CommonUtil.bytes2HexString(b));
        sendMessage(b);
    }

    /**
     * @param readBuf
     * @param length
     */
    protected void readDatafoAck(byte[] readBuf, int length) {
        byte[] b = new byte[length];
        System.arraycopy(readBuf, 0, b, 0, length);
        if(D) Log.i(TAG, "readDatafoAck:" + CommonUtil.bytes2HexString(b));
        sendDisconnectReqPacket();
    }

    /**
     * @param readBuf
     * @param length
     */
    protected void readDeviceInfoAck(byte[] readBuf, int length) {
        byte[] b = new byte[length];
        System.arraycopy(readBuf, 0, b, 0, length);
        if(D) Log.i(TAG, "readDeviceInfoAck:" + CommonUtil.bytes2HexString(b));
        sendDataReqPacket();
    }

    /**
     * @param readBuf
     * @param length
     */
    protected void readConnectAck(byte[] readBuf, int length) {
        byte[] b = new byte[length];
        System.arraycopy(readBuf, 0, b, 0, length);
        if(D) Log.i(TAG, "readConnectAck:" + CommonUtil.bytes2HexString(b));
        sendDeviceInfoReqPacket();
    }

    /**
     * @param readBuf
     * @param length
     */
    protected void readDisconnectAck(byte[] readBuf, int length) {
        byte[] b = new byte[length];
        System.arraycopy(readBuf, 0, b, 0, length);
        if(D) Log.i(TAG, "readDisconnectAck:" + CommonUtil.bytes2HexString(b));
//        if (mChatService != null) {
//            mChatService.stop();
//            mChatService = null;
//        }
    }

    BluetoothProtocolHandler mProtocolHandler = new BluetoothProtocolHandler() {

        @Override
        public void onRecvConnectAck(boolean bSuccess) {
            super.onRecvConnectAck(bSuccess);
            if(D) Log.i(TAG, "onRecvConnectAck:");
            //sendDeviceInfoReqPacket();
            //mSendPacketType = SEND_PACKET_TYPE.DEVICE_INFO_PACKET;
            mSendPacketType = SEND_PACKET_TYPE.DATA_PACKET;
            mDelayTimer.schedule(new DelayTimerTask(), 800); 
        }

        @Override
        public void onRecvDisconnectAck(boolean bSuccess) {
            super.onRecvDisconnectAck(bSuccess);
            if(D) Log.i(TAG, "onRecvDisconnectAck:");
            //mChatService.stop();
            mSendPacketType = SEND_PACKET_TYPE.UNKNOWN_PACKET;
            mDelayTimer.schedule(new DelayTimerTask(), 800); 
        }

        @Override
        public void onRecvDeviceInfoAck(DeviceInfo deviceInfo) {
            super.onRecvDeviceInfoAck(deviceInfo);
            if(D) Log.i(TAG, "onRecvDeviceInfoAck:");
            //sendDataReqPacket();
            mSendPacketType = SEND_PACKET_TYPE.DATA_PACKET;
            mDelayTimer.schedule(new DelayTimerTask(), 800); 
        }

        @Override
        public void onRecvDataCHOL(String strValue) {
            super.onRecvDataCHOL(strValue);
            if(D) Log.i(TAG, "onRecvDataCHOL:");
            //sendDisconnectReqPacket();
            mSendPacketType = SEND_PACKET_TYPE.DISCONNECT_PACKET;
            mDelayTimer.schedule(new DelayTimerTask(), 800); 
            
            if (mOnReceiveListener != null) {
                float fValue = 0.0f;
                String unit = null;
                if (strValue.contains("mmol/L")) {
                    unit = "mmol/L";
                    strValue = strValue.replace(unit, "");
                    fValue = Float.parseFloat(strValue);
                }
                
                mOnReceiveListener.onReceiveCholData(fValue, unit);
            }
        }

        @Override
        public void onRecvDataTG(String strValue) {
            super.onRecvDataTG(strValue);
            if(D) Log.i(TAG, "onRecvDataTG:");
            //sendDisconnectReqPacket();
            mSendPacketType = SEND_PACKET_TYPE.DISCONNECT_PACKET;
            mDelayTimer.schedule(new DelayTimerTask(), 800); 

            if (mOnReceiveListener != null) {
                float fValue = 0.0f;
                String unit = null;
                if (strValue.contains("mmol/L")) {
                    unit = "mmol/L";
                    strValue = strValue.replace(unit, "");
                    fValue = Float.parseFloat(strValue);
                }
                
                mOnReceiveListener.onReceiveTgData(fValue, unit);
            }
        }
        
    };
    
    /* (non-Javadoc)
     * @see com.cking.phss.bluetooth.BluetoothClient#getDeviceListDialogTitle()
     */
    @Override
    protected String getDeviceListDialogTitle() {
        return "请选择BU-34血压设备进行蓝牙配对";
    }

    /* (non-Javadoc)
     * @see com.cking.phss.bluetooth.BluetoothClient#getId()
     */
    @Override
    protected String getId() {
        return "BU-34(I)";
    }

    @Override
    protected void onRead(Message msg) {
        byte[] readBuf = (byte[]) msg.obj;
        if (msg.arg1 <= 0) {
            return;
        }
        // construct a string from the valid bytes in the buffer
        byte[] b = new byte[msg.arg1];
        System.arraycopy(readBuf, 0, b, 0, msg.arg1);
        String readMessage = CommonUtil.bytes2HexString(b);
        if(D) Log.i(TAG, "READ size: " + msg.arg1 + ", " + readMessage);
        mProtocolHandler.processInput(readBuf, msg.arg1);
        super.onRead(msg);
    }

    @Override
    protected void onWrite(Message msg) {
        byte[] writeBuf = (byte[]) msg.obj;
        // construct a string from the buffer
        String writeMessage = CommonUtil.bytes2HexString(writeBuf);
        //mConversationArrayAdapter.add("Me:  " + writeMessage);
        super.onWrite(msg);
    }

    @Override
    protected void onConnected(Message msg) {
        mSendPacketType = SEND_PACKET_TYPE.CONNECT_PACKET;
        mDelayTimer.schedule(new DelayTimerTask(), 800); 
        super.onConnected(msg);
    }
}
