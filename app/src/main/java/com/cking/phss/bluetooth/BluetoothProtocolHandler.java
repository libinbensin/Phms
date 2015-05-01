/* Cking Inc. (C) 2012. All rights reserved.
 *
 * BluetoothProtocolHandler.java
 * classes : com.cking.phss.bluetooth.BluetoothProtocolHandler
 * @author Wation Haliyoo
 * V 1.0.0
 * Create at 2012-9-26 下午03:46:13
 */
package com.cking.phss.bluetooth;

/**
 * com.cking.phss.bluetooth.BluetoothProtocolHandler
 * @author Wation Haliyoo <br/>
 * create at 2012-9-26 下午03:46:13
 */
public class BluetoothProtocolHandler implements IBluetoothClientReceiver, IBluetoothServerReceiver {
    private static final String TAG = "BluetoothProtocolHandler";

    /**
     * 要实现C回调JAVA，千万不要加static
     */
    protected native int processInput(byte[] rxPacket, int len);
    protected native int assembleConnectReqPacket(byte[] txPacket, int size);
    protected native int assembleDisconnectReqPacket(byte[] txPacket, int size);
    protected native int assembleGetDeviceInfoReqPacket(byte[] txPacket, int size);
    protected native int assembleGetSensorDataReqPacket(byte[] txPacket, int size);

    /** Load jni .so on initialization */
    static {
         System.loadLibrary("ProtocolHandler");
    }
    
//    private IBluetoothServerReceiver mServerReceiver = null;
//    private IBluetoothClientReceiver mClientReceiver = null;
//    
//    public void setServerReceiver(IBluetoothServerReceiver receiver) {
//        mServerReceiver = receiver;
//    }
//    public void setClientReceiver(IBluetoothClientReceiver receiver) {
//        mClientReceiver = receiver;
//    }

    /* (non-Javadoc)
     * @see com.cking.phss.bluetooth.IBluetoothServerReceiver#onReceivePbtSeriresData(int, int, int)
     */
    @Override
    public void onReceivePbtSeriresData(int systolic, int diastolic, int pulseRate) {
    }
    /* (non-Javadoc)
     * @see com.cking.phss.bluetooth.IBluetoothServerReceiver#onReceivePbtcDevicesData(com.cking.phss.bluetooth.IBluetoothServerReceiver.BcaData)
     */
    @Override
    public void onReceivePbtcDevicesData(BcaData bcaData) {
    }
    /* (non-Javadoc)
     * @see com.cking.phss.bluetooth.IBluetoothServerReceiver#onReceivePbtcDevicesConfig(int)
     */
    @Override
    public void onReceivePbtcDevicesConfig(int id) {
    }
    /* (non-Javadoc)
     * @see com.cking.phss.bluetooth.IBluetoothClientReceiver#onRecvConnectAck(boolean)
     */
    @Override
    public void onRecvConnectAck(boolean bSuccess) {
    }
    /* (non-Javadoc)
     * @see com.cking.phss.bluetooth.IBluetoothClientReceiver#onRecvDisconnectAck(boolean)
     */
    @Override
    public void onRecvDisconnectAck(boolean bSuccess) {
    }
    /* (non-Javadoc)
     * @see com.cking.phss.bluetooth.IBluetoothClientReceiver#onRecvDeviceInfoAck(com.cking.phss.bluetooth.IBluetoothClientReceiver.DeviceInfo)
     */
    @Override
    public void onRecvDeviceInfoAck(DeviceInfo deviceInfo) {
    }
    /* (non-Javadoc)
     * @see com.cking.phss.bluetooth.IBluetoothClientReceiver#onRecvDataBGLU(java.lang.String)
     */
    @Override
    public void onRecvDataBGLU(String strValue) {
    }
    /* (non-Javadoc)
     * @see com.cking.phss.bluetooth.IBluetoothClientReceiver#onRecvDataCHOL(java.lang.String)
     */
    @Override
    public void onRecvDataCHOL(String strValue) {
    }
    /* (non-Javadoc)
     * @see com.cking.phss.bluetooth.IBluetoothClientReceiver#onRecvDataTG(java.lang.String)
     */
    @Override
    public void onRecvDataTG(String strValue) {
    }
    /* (non-Javadoc)
     * @see com.cking.phss.bluetooth.IBluetoothClientReceiver#onRecvDataHDL(java.lang.String)
     */
    @Override
    public void onRecvDataHDL(String strValue) {
    }
    /* (non-Javadoc)
     * @see com.cking.phss.bluetooth.IBluetoothClientReceiver#onRecvDataBKET(java.lang.String)
     */
    @Override
    public void onRecvDataBKET(String strValue) {
    }
    /* (non-Javadoc)
     * @see com.cking.phss.bluetooth.IBluetoothClientReceiver#onRecvDataNIT(java.lang.String)
     */
    @Override
    public void onRecvDataNIT(String strValue) {
    }
    /* (non-Javadoc)
     * @see com.cking.phss.bluetooth.IBluetoothClientReceiver#onRecvDataLEU(java.lang.String)
     */
    @Override
    public void onRecvDataLEU(String strValue) {
    }
    /* (non-Javadoc)
     * @see com.cking.phss.bluetooth.IBluetoothClientReceiver#onRecvDataURO(java.lang.String)
     */
    @Override
    public void onRecvDataURO(String strValue) {
    }
    /* (non-Javadoc)
     * @see com.cking.phss.bluetooth.IBluetoothClientReceiver#onRecvDataPRO(java.lang.String)
     */
    @Override
    public void onRecvDataPRO(String strValue) {
    }
    /* (non-Javadoc)
     * @see com.cking.phss.bluetooth.IBluetoothClientReceiver#onRecvDataBLD(java.lang.String)
     */
    @Override
    public void onRecvDataBLD(String strValue) {
    }
    /* (non-Javadoc)
     * @see com.cking.phss.bluetooth.IBluetoothClientReceiver#onRecvDataUKET(java.lang.String)
     */
    @Override
    public void onRecvDataUKET(String strValue) {
    }
    /* (non-Javadoc)
     * @see com.cking.phss.bluetooth.IBluetoothClientReceiver#onRecvDataBIL(java.lang.String)
     */
    @Override
    public void onRecvDataBIL(String strValue) {
    }
    /* (non-Javadoc)
     * @see com.cking.phss.bluetooth.IBluetoothClientReceiver#onRecvDataUGLU(java.lang.String)
     */
    @Override
    public void onRecvDataUGLU(String strValue) {
    }
    /* (non-Javadoc)
     * @see com.cking.phss.bluetooth.IBluetoothClientReceiver#onRecvDataVC(java.lang.String)
     */
    @Override
    public void onRecvDataVC(String strValue) {
    }
    /* (non-Javadoc)
     * @see com.cking.phss.bluetooth.IBluetoothClientReceiver#onRecvDataPH(java.lang.String)
     */
    @Override
    public void onRecvDataPH(String strValue) {
    }
    /* (non-Javadoc)
     * @see com.cking.phss.bluetooth.IBluetoothClientReceiver#onRecvDataSG(java.lang.String)
     */
    @Override
    public void onRecvDataSG(String strValue) {
    }
    /* (non-Javadoc)
     * @see com.cking.phss.bluetooth.IBluetoothClientReceiver#onRecvDataTSN(java.lang.String)
     */
    @Override
    public void onRecvDataTSN(String strValue) {
    }

}
