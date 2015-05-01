/* Xinhuaxing Inc. (C) 2014. All rights reserved.
 *
 * BluetoothUtil.java
 * classes : com.cking.phss.util.BluetoothUtil
 * @author Administrator
 * V 1.0.0
 * Create at 2014-7-8 下午4:38:46
 */
package com.cking.phss.util;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;

import java.util.Set;

/**
 * com.cking.phss.util.BluetoothUtil
 * @author Administrator <br/>
 * create at 2014-7-8 下午4:38:46
 */
public class BluetoothUtil {
    private static final String TAG = "BluetoothUtil";

    public static boolean isPared(String profile) {
        // Get the local Bluetooth adapter
        BluetoothAdapter mBtAdapter = BluetoothAdapter.getDefaultAdapter();

        // Get a set of currently paired devices
        Set<BluetoothDevice> pairedDevices = mBtAdapter.getBondedDevices();

        // If there are paired devices, add each one to the ArrayAdapter
        if (pairedDevices.size() > 0) {
            for (BluetoothDevice device : pairedDevices) {
                String deviceName = device.getName().trim();
//              Log.e("设备名称: ", deviceName + "设备地址: " + device.getAddress());

              if (deviceName.equalsIgnoreCase(profile)) {
                  return true;
              }
            }
        }

        return false;
    }
}
