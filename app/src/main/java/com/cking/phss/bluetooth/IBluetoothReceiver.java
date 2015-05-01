/* Cking Inc. (C) 2012. All rights reserved.
 *
 * IBluetoothReceiver.java
 * classes : com.cking.phss.bluetooth.IBluetoothReceiver
 * @author Wation Haliyoo
 * V 1.0.0
 * Create at 2012-9-25 上午10:22:55
 */
package com.cking.phss.bluetooth;

/**
 * com.cking.phss.bluetooth.IBluetoothReceiver
 * @author Wation Haliyoo <br/>
 * create at 2012-9-25 上午10:22:55
 */
public interface IBluetoothReceiver {
    public void onRecvConnectAck(boolean bSuccess);

    public void onRecvDisconnectAck(boolean bSuccess);

    public void onRecvDataBGLU(float fValue, String strUnit);
    public void onRecvDataCHOL(float fValue, String strUnit);
    public void onRecvDataTG(float fValue, String strUnit);
    public void onRecvDataHDL(float fValue, String strUnit);
    public void onRecvDataBKET(float fValue, String strUnit);
    public void onRecvDataNIT(float fValue, String strUnit);
    public void onRecvDataLEU(float fValue, String strUnit);
    public void onRecvDataURO(float fValue, String strUnit);
    public void onRecvDataPRO(float fValue, String strUnit);
    public void onRecvDataBLD(float fValue, String strUnit);
    public void onRecvDataUKET(float fValue, String strUnit);
    public void onRecvDataBIL(float fValue, String strUnit);
    public void onRecvDataUGLU(float fValue, String strUnit);
    public void onRecvDataVC(float fValue, String strUnit);
    public void onRecvDataPH(float fValue, String strUnit);
    public void onRecvDataSG(float fValue, String strUnit);
    public void onRecvDataTSN(float fValue, String strUnit);

}
