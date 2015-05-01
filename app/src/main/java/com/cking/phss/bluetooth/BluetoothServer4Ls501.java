/* Cking Inc. (C) 2012. All rights reserved.
 *
 * JbxxBodyView.java
 * classes : com.cking.phss.view.JbxxBodyView
 * @author Administrator
 * V 1.0.0
 * Create at 2012-9-16 上午11:25:10
 */
package com.cking.phss.bluetooth;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Context;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

/**
 * 蓝牙服务端，可测血压和人体成分
 * com.cking.phss.view.JbxxBodyView
 * @author Administrator <br/>
 * create at 2012-9-16 上午11:25:10
 */
public class BluetoothServer4Ls501 extends BluetoothServer {
    @SuppressWarnings("unused")
    private static final String TAG = "BluetoothServer4Ls501";
    private static final boolean D = true;

    private Toast mToast = null;
    
   private Context mContext = null;

    private final static String recvPairOkAck = "$PAIROK\r\n";
    private final static String recvTimeRequire = "$REQTIME\r\n";
    private final static String timeResponseFormatter = "+TIME:%s+\r\n";
    private final static String dataResponseFormatter = "+DATACK+\r\n";
    int ywValue = 0;
    int xwValue = 0;
    int twValue = 0;
    /**
     * 侦听接收数据
     */
    public static interface OnReceiveListener {
        /**
         * 接收腰围
         */
        public void onReceiveYwData(float fValue, String unit);

        /**
         * 接收胸围
         */
        public void onReceiveXwData(float fValue, String unit);

        /**
         * 接收臀围
         */
        public void onReceiveTwData(float fValue, String unit);

        /**
         * 一次接收多条数据 当前采用此方式，单个传输方式未使用
         * 
         * @param fywValue
         * @param ywUnit
         * @param fxwValue
         * @param xwUnit
         * @param ftwValue
         * @param twUnit
         */
        public void onReceiveAllData(float fywValue, String ywUnit, float fxwValue, String xwUnit,
                float ftwValue, String twUnit);

        /**
         * 配对成功
         */
        public void onPaired();

        /**
         * 测试错误
         */
        public void onTestError();
    }

    private OnReceiveListener mOnReceiveListener = null;

    /**
     * 设置
     * 
     * @param listener
     *            .
     */
    public void setOnReceiveListener(OnReceiveListener listener) {
        mOnReceiveListener = listener;
    }

    /**
     * @param context
     */
    public BluetoothServer4Ls501(Context context) {
        super(context);
        mContext = context;

        // TODO 测试代码
        // parseValidData("80;N;N;2013-07-09 12:12:12;LS501;112233445566".getBytes());
        // parseValidData(":80;N;N;2013-07-09 12:12:12;LS501;112233445566".getBytes());
        // parseValidData(";80;N;N;2013-07-09 12:12:12;LS501;112233445566".getBytes());
        // parseValidData("<80;N;N;2013-07-09 12:12:12;LS501;112233445566".getBytes());
        // parseValidData("=80;N;N;2013-07-09 12:12:12;LS501;112233445566".getBytes());
        // parseValidData(">80;N;N;2013-07-09 12:12:12;LS501;112233445566".getBytes());
        // parseValidData("?80;N;N;2013-07-09 12:12:12;LS501;112233445566".getBytes());
        // parseValidData("N;:80;N;2013-07-09 12:12:12;LS501;112233445566".getBytes());
        // parseValidData("N;;80;N;2013-07-09 12:12:12;LS501;112233445566".getBytes());
        // parseValidData("N;N;;80;2013-07-09 12:12:12;LS501;112233445566".getBytes());
        // parseValidData("N;N;:80;2013-07-09 12:12:12;LS501;112233445566".getBytes());
    }

    private static byte[] lastData = null;

    /**
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

        String readMessage = new String(tmpData);
        Log.i(TAG, "parseData " + readMessage);

        // 多余的过滤
        lastData = null;
        byte cmdType = getCmdType(tmpData);
        Log.i(TAG, "cmdType " + cmdType);
        switch (cmdType) {
            case (byte) 0x00: // 配对成功
                if (mOnReceiveListener != null) {
                    mOnReceiveListener.onPaired();
                }
                break;
            case (byte) 0x01: // 请求时间
                // 发送时间
                sendTimeResponse();
                break;
            case (byte) 0x02: // 回复数据
                parseValidData(tmpData); // 继续解析
                // 发送响应
                sendDataResponse();

                // 会调处理
                processCallback();
                break;
            case (byte) 0x0f: // 回复数据，数据不全
                lastData = tmpData;
                break;
        }
    }

    Timer closeTimer = null;

    /**
     * 
     */
    private void processCallback() {
        // 启动3秒监听器，3秒内无数据则关闭
        // 3秒内有数据则重启定时器
        if (closeTimer != null) {
            closeTimer.cancel();
            closeTimer = null;
        }
        closeTimer = new Timer();
        closeTimer.schedule(new TimerTask() {

            @Override
            public void run() {
                ((Activity) mContext).runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        if (ywValue == 0 && xwValue == 0 && twValue == 0) {
                            if (mOnReceiveListener != null) {
                                mOnReceiveListener.onTestError();
                            }
                        } else {
                            if (mOnReceiveListener != null) {
                                mOnReceiveListener.onReceiveAllData(ywValue * 1.0f / 10, "cm",
                                        xwValue * 1.0f / 10, "cm", twValue * 1.0f / 10, "cm");
                            }
                        }
                    }

                });
            }
        }, 3000);
    }
	
    /**
     * 解析有效数据 由于乐心的腰围尺存在一个这样的BUG，当值大于1000时，返回数据出错，这里对此问题进行补救： </br>
     * 
     * 10XX显示 :(3A)XX </br>
     * 
     * 11XX显示 ;(3B)XX </br>
     * 
     * 12XX显示 <(3C)XX </br>
     * 
     * 13XX显示 =(3D)XX </br>
     * 
     * 14XX显示 >(3E)XX </br>
     * 
     * 15XX显示 ?(3F)XX </br>
     * 
     * 处理方式：
     * 
     * 1. 11XX以外的情况，正常处理外，把值进行转换即可；
     * 
     * 2. 11XX的情况则如下方式处理：
     * 
     * 先找两个N，因为N为无效，剩下的就是有效
     * 
     * ;05;N;N - 1105;N;N
     * 
     * N;;05;N - N;1105;N
     * 
     * N;N;;05 - N;N;1105
     * 
     * 判断11XX的情况则是split后判断数组的数目：其他为6组，11XX为7组
     * 
     * @param tmpData
     */
    private void parseValidData(byte[] tmpData) {
        String validData = new String(tmpData);
        Log.i(TAG, "valid data:" + validData);
        String[] items = validData.split(";");
        if (items.length == 7) { // 11XX情况
            if (!items[0].equals("N") && !items[1].equals("N")) { // ;05;N;N
                int value = parseValue(";" + items[1]);
                ywValue = value;
                // float yw = Float.parseFloat(items[0]);
                // if (mOnReceiveListener != null) {
                // mOnReceiveListener.onReceiveYwData(yw / 10, "cm");
                // }
            }
            if (!items[1].equals("N") && !items[2].equals("N")) { // N;;05;N
                int value = parseValue(";" + items[2]);
                xwValue = value;
                // float xw = Float.parseFloat(items[2]);
                // if (mOnReceiveListener != null) {
                // mOnReceiveListener.onReceiveXwData(xw / 10, "cm");
                // }
            }
            if (!items[2].equals("N") && !items[3].equals("N")) { // N;N;;05
                int value = parseValue(";" + items[3]);
                twValue = value;
                // float tw = Float.parseFloat(items[2]);
                // if (mOnReceiveListener != null) {
                // mOnReceiveListener.onReceiveTwData(tw / 10, "cm");
                // }
            }
        } else if (items.length == 6) { // 非11XX情况
            if (!items[0].equals("N")) {
                int value = parseValue(items[0]);
                ywValue = value;
                // float yw = Float.parseFloat(items[0]);
                // if (mOnReceiveListener != null) {
                // mOnReceiveListener.onReceiveYwData(yw / 10, "cm");
                // }
            }
            if (!items[1].equals("N")) {
                int value = parseValue(items[1]);
                xwValue = value;
                // float xw = Float.parseFloat(items[1]);
                // if (mOnReceiveListener != null) {
                // mOnReceiveListener.onReceiveXwData(xw / 10, "cm");
                // }
            }
            if (!items[2].equals("N")) {
                int value = parseValue(items[2]);
                twValue = value;
                // float tw = Float.parseFloat(items[2]);
                // if (mOnReceiveListener != null) {
                // mOnReceiveListener.onReceiveTwData(tw / 10, "cm");
                // }
            }
        }
    }

    /**
     * @param string
     * @return
     */
    private int parseValue(String value) {
        // 浮点数解析成功则小于10，不成功则大于等于10，特殊处理
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            // 处理第一个特殊字符后其他字符继续解析
            int ret = 0;
            ret = value.charAt(0) - 0x30;

            return ret * 100 + Integer.parseInt(value.substring(1));
        }
    }

    /**
     * 发送数据响应
     */
    private void sendDataResponse() {
        Log.i(TAG, "sendDataResponse ...");
        sendMessage(dataResponseFormatter);
    }

    /**
     * 发送时间响应
     */
    private void sendTimeResponse() {
        Log.i(TAG, "sendTimeResponse ...");
        Date now = new Date();
        String nowStr = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(now);
        String sendData = String.format(timeResponseFormatter, nowStr);
        sendMessage(sendData);
    }

    /**
     * @param tmpData
     * @return
     */
    private byte getCmdType(byte[] tmpData) {
        String dataStr = new String(tmpData);
        int index = dataStr.indexOf("\r\n");
        if (index > 0) {
            if (dataStr.equals(recvPairOkAck)) {
                return 0x0;
            } else if (dataStr.equals(recvTimeRequire)) {
                return 0x1;
            } else {
                return 0x2;
            }
        } else {
            return 0xf;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.cking.phss.bluetooth.BluetoothServer#getBluetoothName()
     */
    @Override
    public String getBluetoothName() {
        return "TEST";
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.cking.phss.bluetooth.BluetoothClient#getDeviceListDialogTitle()
     */
    @Override
    protected String getDeviceListDialogTitle() {
        return "请选择LS501腰围尺设备进行蓝牙配对";
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.cking.phss.bluetooth.BluetoothServer#getId()
     */
    @Override
    protected String getId() {
        return "LS501";
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
        String readMessage = new String(b);
        if (D)
            Log.i(TAG, "READ size: " + msg.arg1 + ", " + readMessage);
        parseData(b);
        super.onRead(msg);
    }

    @Override
    protected void onDisconnect(Message msg) {
        super.onDisconnect(msg);
        Log.i(TAG, "onDisconnect");
    }

    @Override
    protected void onWrite(Message msg) {
        super.onWrite(msg);
    }

    @Override
    protected void onConnected(Message msg) {
        super.onConnected(msg);
        Log.i(TAG, "onConnected");
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.cking.phss.bluetooth.BluetoothServer#getMaxChannelCount()
     */
    @Override
    public int getMaxChannelCount() {
        return 1;
    }
}
