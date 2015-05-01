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

import java.io.UnsupportedEncodingException;

import android.content.Context;
import android.os.Message;
import android.util.Log;

/**
 * This is the main Activity that displays the current chat session.
 */
public class BluetoothClient4SpRmtiii extends BluetoothClient {
    /**
     * @param context
     */
    public BluetoothClient4SpRmtiii(Context context) {
        super(context);
    }

    // Debugging
    private static final String TAG = "BluetoothClient4SpRmtiii";
    private static final boolean D = true;
    
    private Context mContext = null;

    private String mPrintText = null;
    /**
     * 
     */
    protected void printCallback() {
        if (mPrintText != null) {
            try {
                byte[] data = mPrintText.getBytes("gbk");
                sendMessage(data);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            mPrintText = null;
        }
        stop();
    }

    public void print(String text) {
        if (D)
            Log.i(TAG, "print:\r\n" + text);
        mPrintText = text;
        run(null);
    }
    
    /* (non-Javadoc)
     * @see com.cking.phss.bluetooth.BluetoothClient#getDeviceListDialogTitle()
     */
    @Override
    protected String getDeviceListDialogTitle() {
        return "请选择打印设备进行蓝牙配对";
    }

    /* (non-Javadoc)
     * @see com.cking.phss.bluetooth.BluetoothClient#getId()
     */
    @Override
    protected String getId() {
        return "SP-RMTIII BTA";
    }

    @Override
    protected void onConnected(Message msg) {
        printCallback();
        super.onConnected(msg);
    }
}
