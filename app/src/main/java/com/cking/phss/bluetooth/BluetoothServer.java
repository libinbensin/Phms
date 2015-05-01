/* Cking Inc. (C) 2012. All rights reserved.
 *
 * JbxxBodyView.java
 * classes : com.cking.phss.view.JbxxBodyView
 * @author Administrator
 * V 1.0.0
 * Create at 2012-9-16 上午11:25:10
 */
package com.cking.phss.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.cking.phss.R;
import com.cking.phss.file.FileLog;
import com.cking.phss.util.MyApplication;
import com.cking.phss.util.TispToastFactory;
import com.cking.phss.widget.DeviceListDialog;
import com.cking.phss.widget.DeviceListDialog.OnDialogResultListener;

/**
 * 蓝牙服务端，可测血压和人体成分
 * com.cking.phss.view.JbxxBodyView
 * @author Administrator <br/>
 * create at 2012-9-16 上午11:25:10
 */
public abstract class BluetoothServer {
    @SuppressWarnings("unused")
    private static final String TAG = "BluetoothServer2";
    private static final boolean D = true;

    private Toast mToast = null;
    
    // Name of the connected device
    private String mConnectedDeviceName = null;
    // Local Bluetooth adapter
    private BluetoothAdapter mBluetoothAdapter = null;
    // Member object for the bluetooth services
    private BluetoothServerService mBluetoothService = null;
    // Intent request codes
    private static final int REQUEST_ENABLE_BT = 1;

    private int mPort = -1;
   private Context mContext = null;
    private boolean isConnected = false;
    
    // 获取配对对话框标题
    protected abstract String getDeviceListDialogTitle();

    // 蓝牙名称
    public abstract String getBluetoothName();

    // 最大侦听通道数量
    public abstract int getMaxChannelCount();

    /**
     * 侦听断开连接
     */
    public static interface OnDisconnectListener {
        /**
         * Disconnect
         */
        public void onDisconnect(boolean isSuccess);
    }
    
    private OnDisconnectListener mOnDisconnectListener = null;

    /**
     * 设置
     *
     * @param listener.
     */
    public void setOnDisconnectListener(OnDisconnectListener onDisconnectListener) {
        mOnDisconnectListener = onDisconnectListener;
    }

    /**
     * @param context
     */
    public BluetoothServer(Context context) {
        init(context);
    }

    public boolean isConnected() {
        return isConnected;
    }

    /**
     * @param context
     */
    private void init(Context context) {
        mContext = context;
        mToast = TispToastFactory.getToast(context, "");

        // 先到存储中读取该设备对应的端口
        if (mPort == -1) {
            mPort = MyApplication.getInstance().globalSp.getInt(getId(), -1);
        }
        
        Log.i(TAG, "ser port: " + mPort);

        // Get local Bluetooth adapter
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        // If BT is not on, request that it be enabled.
        // setupChat() will then be called during onActivityResult
        if (mBluetoothAdapter!=null && !mBluetoothAdapter.isEnabled()) {
            // Toast.makeText(mContext, "蓝牙设备未开启，请先到设置中开启蓝牙。",
            // Toast.LENGTH_LONG).show();
            mBluetoothAdapter.enable();
        // Otherwise, setup the chat session
        }
        mBluetoothService = new BluetoothServerService(context, mHandler, mPort,
                getBluetoothName(), getMaxChannelCount());
    }
    
    public void close() {
        Log.i(TAG, "close");
        FileLog.i(TAG, "close");
        // Stop the Bluetooth chat services
        if (mBluetoothService != null) {
            mBluetoothService.stop();
            mBluetoothService = null;
        }
    }

    public void open() {
        Log.i(TAG, "open");
        FileLog.i(TAG, "open");
        // Performing this check in onResume() covers the case in which BT was
        // not enabled during onStart(), so we were paused to enable it...
        // onResume() will be called when ACTION_REQUEST_ENABLE activity returns.
        if (mBluetoothService != null) {
            // Only if the state is STATE_NONE, do we know that we haven't started already
            if (mBluetoothService.getState() == BluetoothServerService.STATE_NONE) {
              // Start the Bluetooth chat services
              mBluetoothService.start();
            }
        }
    }

    public void run(OnDialogResultListener listener) {
        open();
        if (listener != null) {
            listener.onConfirm();
        }
        // String deviceAddr = null;
        // try {
        // deviceAddr = getDeviceAddr();
        // } catch (Exception e) {
        // Log.e(TAG, e.toString());
        // }
        // // / 获取已配对列表
        // if (deviceAddr == null) {
        // IntentFilter filter = new
        // IntentFilter(DeviceListDialog.ACTION_PATCHED_BT);
        // mContext.registerReceiver(mReceiver, filter);
        // DeviceListDialog dialog = new DeviceListDialog(mContext,
        // getDeviceListDialogTitle());
        // dialog.show();
        // dialog.setOnDialogResultListener(listener);
        // } else {
        // connectDevice(deviceAddr);
        // if (listener != null) {
        // listener.onConfirm();
        // }
        // }
    }
	
    public void reconnect() {
        String deviceAddr = null;
        try {
            deviceAddr = getDeviceAddr();
        } catch (Exception e) {
            Log.e(TAG, e.toString());
            return;
        }
        // Get the BLuetoothDevice object
        BluetoothDevice device = mBluetoothAdapter.getRemoteDevice(deviceAddr);
        // Attempt to connect to the device
        if (mBluetoothService != null) {
            mBluetoothService.stop();
            mBluetoothService.connect(device);
        }

    }

    public void stop() {
        if (mBluetoothService != null) {
            mBluetoothService.stop();
            mBluetoothService = null;
        }
        try {
            finalize();
        } catch (Throwable e) {
            e.printStackTrace();
        }
//        if (mReceiver != null) {
//            mContext.unregisterReceiver(mReceiver);
//            mReceiver = null;
//        }
    }
    
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
    
            // When discovery finds a device
            if (DeviceListDialog.ACTION_PATCHED_BT.equals(action)) {
                // Get the BluetoothDevice object from the Intent
                String addr = intent.getStringExtra(DeviceListDialog.EXTRA_DEVICE_ADDRESS);
                // If it's already paired, skip it, because it's been listed already
                if (addr != null) {
                    // Attempt to connect to the device
                    connectDevice(addr);
                    try {
                        writeDeviceAddr(addr);
                    } catch (Exception e) {
                        Log.e(TAG,e.toString());
                    }
                    mContext.unregisterReceiver(mReceiver);
                }
            // When discovery is finished, change the Activity title
            }
        }
    };

    /**
     * Sends a message.
     * @param message  A string of text to send.
     */
    protected void sendMessage(String message) {
        if (mBluetoothService == null) return;
        // Log.i(TAG, "mBluetoothService:" + mBluetoothService + "this:" +
        // BluetoothServer.this);
        Log.i(TAG, "sendMessage,msg:" + message);
        FileLog.i(TAG, "sendMessage,msg:" + message);
        // Check that we're actually connected before trying anything
        if (mBluetoothService.getState() != BluetoothServerService.STATE_CONNECTED) {
            mToast.setText(R.string.not_connected);
            mToast.show();
            return;
        }

        // Check that there's actually something to send
        if (message.length() > 0) {
            // Get the message bytes and tell the BluetoothServerService to write
            byte[] send = message.getBytes();
            mBluetoothService.write(send);
        }
    }

    /**
     * Sends a message.
     * 
     * @param message
     *            A string of text to send.
     */
    protected void sendMessage(byte[] message) {
        // Check that we're actually connected before trying anything
        if (mBluetoothService.getState() != BluetoothServerService.STATE_CONNECTED) {
            return;
        }

        // Check that there's actually something to send
        if (message.length > 0) {
            // Get the message bytes and tell the BluetoothServerService to
            // write
            byte[] send = message;
            mBluetoothService.write(send);
        }
    }

    // The Handler that gets information back from the BluetoothServerService
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            // Log.i(TAG, "mBluetoothService:" + mBluetoothService + "this:" +
            // BluetoothServer.this);
            switch (msg.what) {
                case BluetoothServerService.MESSAGE_STATE_CHANGE:
                    if (D)
                        Log.i(TAG, "MESSAGE_STATE_CHANGE: " + msg.arg1);
                    switch (msg.arg1) {
                        case BluetoothServerService.STATE_CONNECTED:
                            isConnected = true;
                            onConnected(msg);
                            break;
                        case BluetoothServerService.STATE_CONNECTING:
                            onConnecting(msg);
                            // mTitle.setText(R.string.title_connecting);
                            break;
                        case BluetoothServerService.STATE_LISTEN:
                            onListen(msg);
                            break;
                        case BluetoothServerService.STATE_NONE:
                            // mTitle.setText(R.string.title_not_connected);
                            break;
                    }
                    break;
                case BluetoothServerService.MESSAGE_WRITE:
                    onWrite(msg);
                    break;
                case BluetoothServerService.MESSAGE_READ:
                    onRead(msg);
                    break;
                case BluetoothServerService.MESSAGE_DEVICE_NAME:
                    // save the connected device's name
                    onDeviceName(msg);
                    break;
                case BluetoothServerService.MESSAGE_DISCONNECT:
                    onDisconnect(msg);
                    break;
                case BluetoothServerService.MESSAGE_TOAST:
                    break;
            }
        }
    };

    private void connectDevice(String address) {
        // Get the BLuetoothDevice object
        BluetoothDevice device = mBluetoothAdapter.getRemoteDevice(address);
        // Attempt to connect to the device
        if (mBluetoothService != null) {
            mBluetoothService.connect(device);
        }
    }

    /**
     * @param msg
     */
    protected void onDisconnect(Message msg) {
        if (mOnDisconnectListener != null) {
            mOnDisconnectListener.onDisconnect(false);
        }
    }

    /**
     * @param msg
     */
    protected void onDeviceName(Message msg) {
    }

    /**
     * @param msg
     */
    protected void onRead(Message msg) {
    }

    /**
     * @param msg
     */
    protected void onWrite(Message msg) {
    }

    /**
     * @param msg
     */
    protected void onListen(Message msg) {
    }

    /**
     * @param msg
     */
    protected void onConnecting(Message msg) {
    }

    /**
     * @param msg
     */
    protected void onConnected(Message msg) {

    }

    /**
     * @return
     */
    protected abstract String getId();

    /**
     * 获取配对蓝牙地址
     * 
     * 
     * @param filepath
     *            文件路径
     * @return byte[]
     */
    protected String getDeviceAddr() throws Exception {
        return MyApplication.getInstance().globalSp.getString(getId(), null);
    }

    /**
     * 将输入的数据存入本地文件
     * 
     * 
     * @param filepath
     *            文件路径
     * @param data
     *            存入文件的数据
     * @param iscontinue
     *            true,追加在文件最后，false替换当前内容
     * @return byte[]
     */
    protected boolean writeDeviceAddr(String data) throws Exception {
        MyApplication.getInstance().globalSp.edit().putString(getId(), data).commit();
        return true;
    }
}
