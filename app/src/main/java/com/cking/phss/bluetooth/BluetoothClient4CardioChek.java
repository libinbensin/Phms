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

/**
 * This is the main Activity that displays the current chat session.
 */
public class BluetoothClient4CardioChek extends BluetoothClient {
    /**
     * @param context
     */
    public BluetoothClient4CardioChek(Context context) {
        super(context);
    }

    // Debugging
    private static final String TAG = "BluetoothClient4CardioChek";
    private static final boolean D = true;

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
         * 接收低血糖数据
         */
        public void onReceiveGlucoseData(float fValue, String unit);
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

    private class ResultClass {
        public float value = 0;
        public String unit = "";
    }

    private static byte[] lastData = null;

    /**
     * @param b
     */
    protected void parseData(byte[] data) {
        final String[] tagList = new String[] {
                "CHOL    :", "HDL CHOL:", "TRIG    :", "CALC LDL:", "GLUCOSE :"
        };

        byte[] tmpData = null;
        if (lastData != null) {
            tmpData = new byte[lastData.length + data.length];
            System.arraycopy(lastData, 0, tmpData, 0, lastData.length);
            System.arraycopy(data, 0, tmpData, lastData.length, data.length);
        } else {
            tmpData = new byte[data.length];
            System.arraycopy(data, 0, tmpData, 0, data.length);
        }

        String readMessage = new String(tmpData);
        Log.i(TAG, "parseData " + readMessage);

        String strData = new String(tmpData);
        strData = findCompleteData(strData);
        if (strData == null) {
            return;
        }
        Log.i(TAG, "validData " + strData);
        // 多余的过滤
        lastData = null;
        String[] strItems = strData.split("\n");
        for (String strItem : strItems) {
            Log.i(TAG, "strItem " + strItem);
            int index = containsEach(strItem, tagList); //判断是否存在标记的头部
            if (index >= 0) {
                ResultClass result = parseResult(strItem); // 解析数值
                switch (index) {
                    case 0: { // 胆固醇
                        if (mOnReceiveListener != null) {
                            mOnReceiveListener.onReceiveCholData(result.value, result.unit);
                        }
                        break;
                    }
                    case 1: { // 高密度脂蛋白
                        if (mOnReceiveListener != null) {
                            mOnReceiveListener.onReceiveHdlData(result.value, result.unit);
                        }
                        break;
                    }
                    case 2: { // 甘油三酯
                        if (mOnReceiveListener != null) {
                            mOnReceiveListener.onReceiveTgData(result.value, result.unit);
                        }
                        break;
                    }
                    case 3: { // 低密度脂蛋白
                        if (mOnReceiveListener != null) {
                            mOnReceiveListener.onReceiveLdlData(result.value, result.unit);
                        }
                        break;
                    }
                    case 4: { // 血糖
                        if (mOnReceiveListener != null) {
                            mOnReceiveListener.onReceiveGlucoseData(result.value, result.unit);
                        }
                        break;
                    }
                }
            }
        }
    }

    /**
     * @param strData
     * @return
     */
    private String findCompleteData(String strData) {
        String keyStr = "A60,";
        if (strData.contains(keyStr)) {
            int index = strData.indexOf(keyStr);
            return strData.substring(index);
        }
        return null;
    }

    /**
     * A60,000360,0,4,1,1,N,"CALC LDL:  1.96 mmol/L"
     * 
     * @param strItem
     * @return
     */
    private ResultClass parseResult(String strItem) {
        ResultClass result = new ResultClass();
        // 第1步找双引号中间的字符串
        int start = strItem.indexOf("\"") + 1;
        int end = strItem.lastIndexOf("\"");
        String validStr = strItem.substring(start, end);
        // 第2步找到：号
        String[] items = validStr.split(":");
        // 第3步过滤空格
        String needParsedStr = items[1].trim();
        // 第4步以空格分开
        // 对于有小于大于号的，过滤小于大于号
        // 所以直接去最后两组数据即可
        String[] sps = needParsedStr.split(" ");
        // 第5步对数字进行处理
        result.value = Float.parseFloat(sps[sps.length - 2]);
        result.unit = sps[sps.length - 1];
        return result;
    }

    /**
     * @param strItem
     * @param tagList
     * @return
     */
    private int containsEach(String strItem, String[] tagList) {
        for (int i = 0; i < tagList.length; i++) {
            String tag = tagList[i];
            if (strItem.contains(tag)) {
                return i;
            }
        }
        return -1;
    }

    /* (non-Javadoc)
     * @see com.cking.phss.bluetooth.BluetoothClient#getDeviceListDialogTitle()
     */
    @Override
    protected String getDeviceListDialogTitle() {
        return "请选择CardioChek设备进行蓝牙配对";
    }

    /* (non-Javadoc)
     * @see com.cking.phss.bluetooth.BluetoothClient#getId()
     */
    @Override
    protected String getId() {
        return "CardioChek";
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
        String readMessage = new String(b);//CommonUtil.bytes2HexString(b);
        if(D) Log.i(TAG, "READ size: " + msg.arg1 + ", " + readMessage);
        parseData(b);
        super.onRead(msg);
    }
}
