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

import java.util.Arrays;
import java.util.Timer;

import android.content.Context;
import android.os.Message;
import android.util.Log;

import com.cking.phss.util.CommonUtil;

/**
 * This is the main Activity that displays the current chat session.
 */
public class BluetoothClient4BeneCheck extends BluetoothClient {
    // Debugging
    private static final String TAG = "BluetoothClient4BeneCheck";
    private static final boolean D = true;

    boolean isConnectionClosed = true;
    int recvEchoCount = 0;
    int sendHeartbeatCount = 0;

    private final static byte[] headData = new byte[] { 0x24, 0x50, 0x43, 0x4c };
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

        /**
         * 接收高密度脂蛋白数据
         */
        public void onReceiveHdlData(float fValue, String unit);

        /**
         * 接收低密度脂蛋白数据
         */
        public void onReceiveLdlData(float fValue, String unit);

        /**
         * 接收血糖数据
         */
        public void onReceiveGlucoseData(float fValue, String unit);

        /**
         * 测试错误
         */
        public void onTestError();
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
    public BluetoothClient4BeneCheck(Context context) {
        super(context);
        recvEchoCount = 0;
    }

    private static byte[] lastData = null;
    
    /**
     * Function :GLUC 0x41 ; UA 0x51 ; CHOL 0x61 ; HDL 0x71 ; TG 0xB1 ; M_CHOL
     * 0xd1 ; M_HDL 0xd2 ; M_TG 0xd3 $PCL Function 0x00 0x00 0x01 0x09 0x00 0x00
     * 0x00 Y M D h m vv cs
     * 
     * @param b
     */
    protected void parseData(byte[] data) {
        byte[] tmpData = null;
        if (lastData != null) {
            tmpData = new byte[lastData.length + data.length];
            System.arraycopy(lastData, 0, tmpData, 0, lastData.length);
            System.arraycopy(data, 0, tmpData, lastData.length, data.length);
        } else {
            tmpData = new byte[data.length];
            System.arraycopy(data, 0, tmpData, 0, data.length);
        }

        String readMessage = CommonUtil.bytes2HexString(tmpData);
        Log.i(TAG, "parseData " + readMessage);

        byte[] validData = getValidData(tmpData);
        if (validData != null) {
            // 多余的过滤
            lastData = null;
            switch (validData[0]) {
                case (byte) 0xd0: // 错误信息
                    if (mOnReceiveListener != null) {
                        mOnReceiveListener.onTestError();
                    }
                    break;
                case (byte) 0x01: // Echo
                    recvEchoCount++;
                    parseData(validData); // 继续解析
                    break;
                case (byte) 0x41: // GLUC
                    float fValue = getValueFromData(validData) / 18;
                    if (mOnReceiveListener != null) {
                        mOnReceiveListener.onReceiveGlucoseData(fValue, "mmol/L");
                    }
                    break;
            }
        } else {
            lastData = tmpData;
        }
    }

    /**
     * @param validData
     * @return
     */
    private float getValueFromData(byte[] validData) {
        short v = (short) (validData[13] + (validData[14] << 8));
        return (float) v;
    }

    /**
     * 获取有效数据，除掉头部和尾部
     * 
     * @param data
     * @return
     */
    private byte[] getValidData(byte[] data) {
        // String strHead = new String(data, 0, 4);
        // if (strHead.equals("$PCL")) {
        // return ;
        // }
        for (int i = 0; i < data.length - 4; i++) {
            if (data[i] == 0x24 && data[i + 1] == 0x50 && data[i + 2] == 0x43
                    && data[i + 3] == 0x4c) {
                byte[] retData = Arrays.copyOfRange(data, i + 4, data.length);
                return retData;
            }
        }
        return null;
    }

    /* (non-Javadoc)
     * @see com.cking.phss.bluetooth.BluetoothClient#getDeviceListDialogTitle()
     */
    @Override
    protected String getDeviceListDialogTitle() {
        return "请选择BeneCheck血糖设备进行蓝牙配对";
    }

    /* (non-Javadoc)
     * @see com.cking.phss.bluetooth.BluetoothClient#getId()
     */
    @Override
    protected String getId() {
        return "BeneCheck";
    }

    @Override
    protected void onRead(Message msg) {
        Log.i(TAG, "onRead");
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

    @Override
    protected void onDisconnect(Message msg) {
        super.onDisconnect(msg);
        Log.i(TAG, "onDisconnect");
        isConnectionClosed = true;
        recvEchoCount = 0;
    }

    @Override
    protected void onWrite(Message msg) {
        super.onWrite(msg);
    }

    @Override
    protected void onConnected(Message msg) {
        super.onConnected(msg);
        Log.i(TAG, "onConnected");
        // isConnectionClosed = false;
        // sendHeartbeatCount = 0;
        // // 周期性发送心跳
        // sendHeartbeatTimer = new Timer();
        // sendHeartbeatTimer.schedule(new TimerTask() {
        //
        // @Override
        // public void run() {
        // if (isConnectionClosed) {
        // sendHeartbeatTimer.cancel();
        // return;
        // }
        //
        // // 如果心跳发送次数大于20次且没有收到相应则断开重连
        // if (recvEchoCount == 0 && sendHeartbeatCount > 20) {
        // sendHeartbeatTimer.cancel();
        // reconnect();
        // return;
        // }
        //
        // sendHeartbeat();
        // }
        // }, 500, 500);
    }

    /**
     * 
     */
    protected void sendHeartbeat() {
        byte[] data = new byte[] { 0x24, 0x50, 0x43, 0x4c, 0x01, 0x00, 0x00, 0x00, 0x00, 0x00,
                (byte) 0xe0 };

        sendMessage(data);
    }

    Timer sendHeartbeatTimer;
}
