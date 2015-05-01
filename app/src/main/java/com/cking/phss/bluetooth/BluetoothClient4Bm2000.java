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

import com.cking.phss.bean.BloodOxygen;
import com.cking.phss.util.CommonUtil;

/**
 * This is the main Activity that displays the current chat session.
 */
public class BluetoothClient4Bm2000 extends BluetoothClient {
    // Debugging
    private static final String TAG = "BluetoothClient4Bm2000";
    private static final boolean D = true;
    private static final int PACKAGE_SIZE = 5;

    /**
     * 侦听接收数据
     */
    public static interface OnReceiveListener {
        /**
         * 接收血氧数据
         */
        public void onReceiveBloodOxygenData(BloodOxygen data);
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
    public BluetoothClient4Bm2000(Context context) {
        super(context);
    }

    private final static byte BIT_0 = 0x01;
    private final static byte BIT_1 = 0x02;
    private final static byte BIT_2 = 0x04;
    private final static byte BIT_3 = 0x08;
    private final static byte BIT_4 = 0x10;
    private final static byte BIT_5 = 0x20;
    private final static byte BIT_6 = 0x40;
    private final static byte BIT_7 = (byte) 0x80;
    /**
     * @param b
     */
    protected void parseData(byte[] data) {
        byte BIT_HEAD_4 = (BIT_0 | BIT_1 | BIT_2 | BIT_3);
        byte BIT_HEAD_7 = (BIT_0 | BIT_1 | BIT_2 | BIT_3 | BIT_4 | BIT_5 | BIT_6);
        BloodOxygen bo = new BloodOxygen();

        try {
            // byte 1
            bo.setSignal((int) (data[0] % BIT_HEAD_4)); // 第一个字节的0~3位 信号强度
            bo.setSearchTimeLong((data[0] & BIT_4) == BIT_4 ? 1 : 0); // 第一个字节第4位 搜索时间
            bo.setSpo2Low((data[0] & BIT_5) == BIT_5 ? 1 : 0); // 第一个字节第5位Spo2饱和度
            
            // byte2
            bo.setPulseWave((int) (data[1] % BIT_HEAD_7)); // 第二个字节0~6，体积描记图
            
            // byte3
            bo.setPi((int) (data[2] & BIT_HEAD_4)); // 第三个字节0~3，柱状图
            bo.setProberError((data[2] & BIT_4) == BIT_4 ? 1 : 0); // 第三个字节第4位，探头错误
            bo.setSearchPulse((data[2] & BIT_5) == BIT_5 ? 1 : 0); // 第三个字节第5位， 搜索脉搏
            
            //byte4
            bo.setRealPulseRate(((data[2] & BIT_6) << 1) | data[3]); // 第四个字节，脉率
            
            //byte5
            bo.setSpo2((int) (data[4] & BIT_HEAD_7)); // 第五个字节0~6，Spo2
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        
        if (mOnReceiveListener != null) {
            mOnReceiveListener.onReceiveBloodOxygenData(bo);
        }
    }

    /* (non-Javadoc)
     * @see com.cking.phss.bluetooth.BluetoothClient#getDeviceListDialogTitle()
     */
    @Override
    protected String getDeviceListDialogTitle() {
        return "请选择BM2000血糖设备进行蓝牙配对";
    }

    /* (non-Javadoc)
     * @see com.cking.phss.bluetooth.BluetoothClient#getId()
     */
    @Override
    protected String getId() {
        return "BM2000";
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
        int count = b.length / PACKAGE_SIZE; // 获取数据包个数
        for (int i = 0; i < count; i++) {
            byte[] data = new byte[PACKAGE_SIZE];
            System.arraycopy(b, i * PACKAGE_SIZE, data, 0, PACKAGE_SIZE);
            parseData(data);
        }
        super.onRead(msg);
    }
}
