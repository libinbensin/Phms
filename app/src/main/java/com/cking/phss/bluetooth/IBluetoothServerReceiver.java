/* Cking Inc. (C) 2012. All rights reserved.
 *
 * IBluetoothServerReceiver.java
 * classes : com.cking.phss.bluetooth.IBluetoothServerReceiver
 * @author Wation Haliyoo
 * V 1.0.0
 * Create at 2012-9-25 上午10:22:55
 */
package com.cking.phss.bluetooth;

/**
 * com.cking.phss.bluetooth.IBluetoothServerReceiver
 * @author Wation Haliyoo <br/>
 * create at 2012-9-25 上午10:22:55
 */
public interface IBluetoothServerReceiver {
    public class BcaData {
        //      49 44 2C 30 31 0D 0A - ID,01
        public int id;
        //      53 54 2C 2B 30 37 37 2E 34 35 6B 67 0D 0A - ST,+077.45kg - 体重
        public String weight;
        //      52 54 2C 30 35 30 37 2E 32 4F 0D 0A - RT,0507.2O - 阻抗
        public String impedance;
        //      46 54 2C 32 37 2E 33 25 0D 0A - FT,27.3% - 肥胖度
        public float fatDegree;
        //      48 54 2C 2B 31 37 34 30 6D 6D 0D 0A - HT,+1740mm - 高度
        public String height;
        //      42 54 2C 30 31 36 33 30 6B 63 0D 0A - BT,01630kc - 基础代谢
        public String basalMetabolism;
        //      4D 54 2C 30 35 33 2E 34 6B 67 0D 0A - MT,053.4kg - 肌肉质量
        public String muscleMass;
        //      57 54 2C 30 34 31 2E 31 6B 67 0D 0A - WT,041.1kg - 体内水的质量
        public String bodyWaterMass;
    }
    
    /**
     * 接收PBT Serires数据
     */
    public void onReceivePbtSeriresData(int systolic, int diastolic, int pulseRate);
    /**
     * 接收PBT-C Devices数据
     */
    public void onReceivePbtcDevicesData(BcaData bcaData);
    /**
     * 接收PBT-C Devices配置信息
     */
    public void onReceivePbtcDevicesConfig(int id);

}
