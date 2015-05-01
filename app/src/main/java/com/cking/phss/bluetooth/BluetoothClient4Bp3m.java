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

import android.content.Context;
import android.os.Message;
import android.util.Log;

import com.cking.phss.util.CommonUtil;

/**
 * This is the main Activity that displays the current chat session.
 */
public class BluetoothClient4Bp3m extends BluetoothClient {
    /**
     * @param context
     */
    public BluetoothClient4Bp3m(Context context) {
        super(context);
    }

    // Debugging
    private static final String TAG = "BluetoothClient4Bp3m";
    private static final boolean D = true;

    /**
     * 侦听接收数据
     */
    public static interface OnReceiveListener {
        /**
         * 接收血压数据
         */
        public void onReceiveXyData(int systolic, int diastolic, int pulseRate);
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
     * @param b
     */
    protected void parseData(byte[] data) {
        if (data.length < 8) {
            return;
        }
        // 发送清除记忆的命令
        sendMessage(new byte[]{0x4D, (byte) 0xFF, 0x02, 0x03, 0x51});
        // 只取收缩压，舒张压，心率这三个有用的数据
        if (mOnReceiveListener != null) {
            int systolic = data[data.length - 8] & 0xff;
            int diastolic = data[data.length - 7] & 0xff;
            int pulseRate = data[data.length - 6] & 0xff;
            mOnReceiveListener.onReceiveXyData(systolic, diastolic, pulseRate);
        }
    }

    /* (non-Javadoc)
     * @see com.cking.phss.bluetooth.BluetoothClient#getDeviceListDialogTitle()
     */
    @Override
    protected String getDeviceListDialogTitle() {
        return "请选择BP3M血压设备进行蓝牙配对";
    }

    /* (non-Javadoc)
     * @see com.cking.phss.bluetooth.BluetoothClient#getId()
     */
    @Override
    protected String getId() {
        return "BP3M";
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
        parseData(b);
        super.onRead(msg);
    }
}
