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
public interface IBluetoothClientReceiver {
    public class DeviceInfo {
        //Manufacturer id   4       设备id和device id对应传感器终端id。
        public int mManufacturerId;
        //device id 12  
        public byte[] mDeviceId = new byte[12];
        //manufacture date  8       出厂日期，前两个字节表示年，第三个表示月，第四个表示日，后面4个字节表示当天的出货批次流水号
        public short mYear; 
        public byte mMonth; 
        public byte mDay; 
        public int mSeq;
        //Version number    4       版本号，前两位表示主本版号，后两位表示子版本号
        public short mMainVersion; 
        public short mSubVersion;
        //Sensor Mode   1       传感器模式：
        //0：传感器无需不断实时传输数据。
        //1：传感器传输的数据需要不断实时在前端主动显示。
        //此模式选择用于网关对两类数据的区别处理。
        public byte mSensorMode;
        //Auth mode 1       鉴权模式：
        //0：空验证，传感器终端对网关的连接请求不进行验证
        //1：password验证，传感器终端对网关的连接请求进行密码验证
        public byte mAuthMode;
        //bundle mode   1       绑定模式：
        //0：无绑定，传感器终端允许任何网关进行连接
        //1：绑定：传感器终端仅允许绑定id的网关进行连接
        public byte mBundleMode;
        //Bundle id 16  
        public byte[] mBundleId = new byte[16];    
        //ack mode  1       ACK模式：
        //0：传感器终端与网关通信不进行ACK
        //1：传感器终端与网关通信必须进行ACK
        public byte mAckMode;
        //compress mode 1       压缩模式：
        //0：不压缩
        //1：压缩模式1
        //2：压缩模式2
        //3：压缩模式3
        public byte mCompressMode;
        //encryption mode   1       加密模式：
        //0：不加密
        //1： DES加密
        public byte mEncryptionMode;
    }
    public void onRecvConnectAck(boolean bSuccess);

    public void onRecvDisconnectAck(boolean bSuccess);
    
    public void onRecvDeviceInfoAck(DeviceInfo deviceInfo);

    public void onRecvDataBGLU(String strValue);
    public void onRecvDataCHOL(String strValue);
    public void onRecvDataTG(String strValue);
    public void onRecvDataHDL(String strValue);
    public void onRecvDataBKET(String strValue);
    public void onRecvDataNIT(String strValue);
    public void onRecvDataLEU(String strValue);
    public void onRecvDataURO(String strValue);
    public void onRecvDataPRO(String strValue);
    public void onRecvDataBLD(String strValue);
    public void onRecvDataUKET(String strValue);
    public void onRecvDataBIL(String strValue);
    public void onRecvDataUGLU(String strValue);
    public void onRecvDataVC(String strValue);
    public void onRecvDataPH(String strValue);
    public void onRecvDataSG(String strValue);
    public void onRecvDataTSN(String strValue);

}
