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

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.cking.application.MyApplication;
import com.cking.phss.widget.DeviceListDialog;
import com.cking.phss.widget.DeviceListDialog.OnDialogResultListener;

/**
 * This is the main Activity that displays the current chat session.
 */
public abstract class BluetoothClient {
    // Debugging
    private static final String TAG = "BluetoothClient";
    private static final boolean D = true;

    // Local Bluetooth adapter
    protected BluetoothAdapter mBluetoothAdapter = null;
    // Member object for the chat services
    protected BluetoothClientService mChatService = null;

    private Context mContext = null;
    
    // 获取配对对话框标题
    protected abstract String getDeviceListDialogTitle();

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
     */
    public void setOnDisconnectListener(OnDisconnectListener listener) {
        mOnDisconnectListener = listener;
    }

    public BluetoothClient(Context context) {
        mContext = context;
        if(D) Log.e(TAG, "+++ ON CREATE +++");

        // Get local Bluetooth adapter
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        // If the adapter is null, then Bluetooth is not supported
        if (mBluetoothAdapter == null) {
            return;
        }

        // If BT is not on, request that it be enabled.
        // setupChat() will then be called during onActivityResult
        if (!mBluetoothAdapter.isEnabled()) {
            // Toast.makeText(mContext, "蓝牙设备未开启，请先到设置中开启蓝牙。",
            // Toast.LENGTH_LONG).show();
            mBluetoothAdapter.enable();
        }

        if (mChatService == null) {
            mChatService = new BluetoothClientService(mContext, mHandler);
        }
    }
    
    public void run(OnDialogResultListener listener) {

        String deviceAddr = null;
        try {

           deviceAddr = getDeviceAddr(); // qs 00:19:5D:24:18:05

        } catch (Exception e) {
            Log.e(TAG,e.toString());
        }
        /// 获取已配对列表
        if (deviceAddr == null) {
            IntentFilter filter = new IntentFilter(DeviceListDialog.ACTION_PATCHED_BT);
            mContext.registerReceiver(mReceiver, filter);
            DeviceListDialog dialog = new DeviceListDialog(mContext, getDeviceListDialogTitle());
            dialog.show();
            dialog.setOnDialogResultListener(listener);
        } else {
            connectDevice(deviceAddr);
            if (listener != null) {
                listener.onConfirm();
            }
        }
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
        if (mChatService != null) {
            mChatService.stop();
            mChatService.connect(device, true);
        }

    }

    public void stop() {
        if (mChatService != null) {
            mChatService.stop();
            mChatService = null;
        }
//        if (mReceiver != null) {
//            mContext.unregisterReceiver(mReceiver);
//            mReceiver = null;
//        }
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#finalize()
     */
    @Override
    protected void finalize() throws Throwable {
        stop();
        super.finalize();
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
        // Check that we're actually connected before trying anything
        if (mChatService.getState() != BluetoothClientService.STATE_CONNECTED) {
            return;
        }

        // Check that there's actually something to send
        if (message.length() > 0) {
            // Get the message bytes and tell the BluetoothClientService to write
            byte[] send = message.getBytes();
            mChatService.write(send);
        }
    }

    /**
     * Sends a message.
     * @param message  A string of text to send.
     */
    protected void sendMessage(byte[] message) {
        // Check that we're actually connected before trying anything
        if (mChatService.getState() != BluetoothClientService.STATE_CONNECTED) {
            return;
        }

        // Check that there's actually something to send
        if (message.length > 0) {
            // Get the message bytes and tell the BluetoothClientService to write
            byte[] send = message;
            mChatService.write(send);
        }
    }
    // The Handler that gets information back from the BluetoothClientService
    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
            case BluetoothClientService.MESSAGE_STATE_CHANGE:
                if(D) Log.i(TAG, "MESSAGE_STATE_CHANGE: " + msg.arg1);
                switch (msg.arg1) {
                case BluetoothClientService.STATE_CONNECTED:
                    onConnected(msg);
                    break;
                case BluetoothClientService.STATE_CONNECTING:
                    onConnecting(msg);
//                    mTitle.setText(R.string.title_connecting);
                    break;
                case BluetoothClientService.STATE_LISTEN:
                    onListen(msg);
                    break;
                case BluetoothClientService.STATE_NONE:
//                    mTitle.setText(R.string.title_not_connected);
                    break;
                }
                break;
            case BluetoothClientService.MESSAGE_WRITE:
                onWrite(msg);
                break;
            case BluetoothClientService.MESSAGE_READ:
                onRead(msg);
                break;
            case BluetoothClientService.MESSAGE_DEVICE_NAME:
                // save the connected device's name
                onDeviceName(msg);
                break;
            case BluetoothClientService.MESSAGE_DISCONNECT:
                onDisconnect(msg);
                break;
            case BluetoothClientService.MESSAGE_TOAST:
                break;
            }
        }
    };

    private void connectDevice(String address) {
        // Get the BLuetoothDevice object
        BluetoothDevice device = mBluetoothAdapter.getRemoteDevice(address);
        // Attempt to connect to the device
        if (mChatService != null) {
            mChatService.connect(device, true);
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
     */
    protected String getDeviceAddr() throws Exception {
         return MyApplication.getInstance().globalSp.getString(getId(), null);
     }

    /**
     * 将输入的数据存入本地文件
     */
     protected boolean writeDeviceAddr(String data) throws Exception {
        MyApplication.getInstance().globalSp.edit().putString(getId(), data).commit();
         return true;
     }
}
