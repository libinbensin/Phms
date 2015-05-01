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

import net.xinhuaxing.util.ToolsUtil;
import android.content.Context;
import android.os.Message;
import android.util.Log;

import com.cking.phss.util.CommonUtil;

/**
 * This is the main Activity that displays the current chat session.
 */
public class BluetoothClient4Hande extends BluetoothClient {
    /**
     * @param context
     */
    public BluetoothClient4Hande(Context context) {
        super(context);
    }

    // Debugging
    private static final String TAG = "BluetoothClient4Hande";
    private static final boolean D = true;

    /**
     * 侦听连接
     */
    public static interface OnConnectedListener {
        /**
         * 接收卡数据
         */
        public void onConnected(boolean isSuccess);
    }
    
    private OnConnectedListener mOnConnectedListener = null;

    /**
     * 设置
     *
     * @param listener.
     */
    public void setOnConnectedListener(OnConnectedListener listener) {
        mOnConnectedListener = listener;
    }

    /**
     * 侦听接收数据
     */
    public static interface OnReceiveListener {
        /**
         * 接收卡数据
         */
        public void onReceiveIdcard(int type, String snr);
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

    private static byte[] lastData = null;
    private final static int ST_FOUND_INIT = 0;
    private final static int ST_FOUND_HEAD = 1;
    private final static int ST_FOUND_LENGTH = 2;
    /**
     * @param b
     */
    protected void parseData(byte[] data) {
        /**
         * 
         * 上传数据格式：
         * 第一部分：
         *  数据头     0x02
         * 第二部分
         *  数据长度    0x09 
         * 第三部分
         *  卡片类型    0x20(二代证)
         * 第四部分
         *  数据      snr[0~3]  4个字节
         * 第五部分    
         *  异或校验    BCC (除数据头尾外的其它数据的异或运算) 1个字节
         * 第六部分
         * 数据结尾    0x03
         * 
         * e.g. 0x02,0x09,0x29,0x24,0x17,0x60,0x3D,0x4E,0x03
         */
        byte[] tmpData = null;
        if (lastData != null) {
            tmpData = new byte[lastData.length + data.length];
            System.arraycopy(lastData, 0, tmpData, 0, lastData.length);
            System.arraycopy(data, 0, tmpData, lastData.length, data.length);
        } else {
            tmpData = new byte[data.length];
            System.arraycopy(data, 0, tmpData, 0, data.length);
        }
        int i = 0;
        int status = ST_FOUND_INIT;
        int length = 0;
        String snr = null;
        while (i < tmpData.length) {
            if (status == ST_FOUND_INIT && tmpData[i] == 0x02) { // 读头部 
                i++;
                status = ST_FOUND_HEAD;
            } else if (status == ST_FOUND_HEAD) { // 读长度
                length = tmpData[i];
                i++;
                status = ST_FOUND_LENGTH;
                
                // 获取内容
                if (length <= tmpData.length) {
                    int type = tmpData[i++];
                    snr = new String(tmpData, i, length - 5);
                    snr = ToolsUtil.bytesToHexString(snr.getBytes());
                    // 多余的过滤
                    lastData = null;
                    // 回调
                    if (mOnReceiveListener != null) {
                        mOnReceiveListener.onReceiveIdcard(type, snr);
                    }
                    return;
                } else {
                    break;
                }
            } else {
                i++;
            }
        }
        lastData = tmpData;
//        try {
//            while (data[i++] != 0x02) {
//                if (i >= data.length) {
//                    Log.e(TAG, "无效数据");
//                    return;
//                }
//            }
//            int length = data[i++];
//            int type = data[i++];
//            String snr = new String(data, i, length - 5);
//            snr = ToolsUtil.bytesToHexString(snr.getBytes());
//            
//            if (mOnReceiveListener != null) {
//                mOnReceiveListener.onReceiveIdcard(type, snr);
//            }
//        } catch (ArrayIndexOutOfBoundsException e) {
//            e.printStackTrace();
//        }
    }

    /* (non-Javadoc)
     * @see com.cking.phss.bluetooth.BluetoothClient#getDeviceListDialogTitle()
     */
    @Override
    protected String getDeviceListDialogTitle() {
        return "请选择HANDE射频读卡设备进行蓝牙配对";
    }

    /* (non-Javadoc)
     * @see com.cking.phss.bluetooth.BluetoothClient#getId()
     */
    @Override
    protected String getId() {
        return "HANDE";
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

    @Override
    protected void onConnected(Message msg) {
        if (mOnConnectedListener != null) {
            mOnConnectedListener.onConnected(true);
        }
        super.onConnected(msg);
    }
}
