/* Cking Inc. (C) 2012. All rights reserved.
 *
 * IdcReader.java
 * classes : com.cking.phss.util.IdcReader
 * @author Administrator
 * V 1.0.0
 * Create at 2012-9-16 下午6:56:43
 */
package com.cking.phss.util;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.ivsign.android.IDCReader.IDCReaderSDK;

/**
 * com.cking.phss.util.IdcReader
 * @author Administrator <br/>
 * create at 2012-9-16 下午6:56:43
 */
public class IdcReader {
    @SuppressWarnings("unused")
    private static final String TAG = "IdcReader";
    
    // Member object for the chat services
    private IDCReaderSDK mChatService = null;

    /**
     * 侦听读取数据
     */
    public static interface OnReadMessageListener {
        /**
         * 读取姓名
         */
        public void onReadPeopleName(String peopleName);
        /**
         * 读取性别
         */
        public void onReadPeopleSex(String peopleSex);
        /**
         * 读取民族
         */
        public void onReadPeopleNation(String peopleNation);
        /**
         * 读取出生
         */
        public void onReadPeopleBirthday(Date peopleBirthday);
        /**
         * 读取住址
         */
        public void onReadPeopleAddress(String peopleAddress);
        /**
         * 读取卡号
         */
        public void onReadPeopleIdCode(String peopleIdCode);
        /**
         * 读取发证机关
         */
        public void onReadDepartment(String department);
        /**
         * 读取有效开始日期
         */
        public void onReadStartDate(Date startDate);
        /**
         * 读取有效截止日期
         */
        public void onReadStopDate(Date stopDate);
        /**
         * 读取头像
         */
        public void onReadProfile(Bitmap profile);
    }
    
    private OnReadMessageListener mOnReadMessageListener = null;

    /**
     * 设置
     *
     * @param listener.
     */
    public void setOnReadMessageListener(OnReadMessageListener listener) {
        mOnReadMessageListener = listener;
    }

    /**
     * 侦听错误信息
     */
    public static interface OnErrorListener {
        /**
         * 错误事件
         */
        public void onError(int code, String errorDesc);
    }
    
    private OnErrorListener mOnErrorListener = null;

    /**
     * 设置
     *
     * @param listener.
     */
    public void setOnErrorListener(OnErrorListener listener) {
        mOnErrorListener = listener;
    }
    
    public int run() {
        int ret = 0;
        
        /**
         * 初始化
         */
        if (mChatService == null) {
            mChatService = new IDCReaderSDK(MyApplication.getInstance(), null, "/mnt/sdcard/wltlib");
        }
        
        /**
         *  连接设备
         */
        if (mChatService.getState() != IDCReaderSDK.STATE_CONNECTED) {
            ret = mChatService.CVR_InitComm("CVR-100B", 5000);

            if (IDCReaderSDK.CVR_RETCODE_SUCCESS == ret) {
                // 成功
            } else if (IDCReaderSDK.CVR_RETCODE_TIMEOUT == ret) {
                // 超时
                doError(ret, "连接超时.");
                return ret;
            } else{
                // 连接失败 
                doError(ret, "连接失败.");
                return ret;
            }
        } else {
            // 已连接
        }
        
        /**
         * 身份验证
         */
        if (mChatService.getState() == IDCReaderSDK.STATE_CONNECTED) {
            ret = mChatService.CVR_Authenticate(3000);
            if (IDCReaderSDK.CVR_RETCODE_SUCCESS == ret) {
                // 验证成功
            } else if (IDCReaderSDK.CVR_RETCODE_TIMEOUT == ret) {
                // 操作超时
                doError(ret, "操作超时.");   
                return ret;
            } else {
                // 没有读到SAM信息
                doError(ret, "没有读到SAM信息."); 
                return ret;
            }
        } else {
            // 设备未连接
            doError(ret, "设备未连接 .");   
            return ret;
        }
        
        /**
         * 读取身份证信息
         */
        if (mChatService.getState() == IDCReaderSDK.STATE_CONNECTED) {
            String readMessage = "";
            ret = mChatService.CVR_Read_Content(1, 3000);
            if (IDCReaderSDK.CVR_RETCODE_SUCCESS == ret) {
                byte[] dataBuf = mChatService.CVR_GetInfo();
                try {
                    String TestStr = new String(dataBuf, "UTF16-LE");
                    readMessage = new String(TestStr.getBytes("UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            } else if (IDCReaderSDK.CVR_RETCODE_TIMEOUT == ret) {
                // 操作超时
                doError(ret, "操作超时.");   
                return ret;
            } else if (IDCReaderSDK.CVR_RETCODE_LICERROR == ret) {
                // 授权问题 
                doError(ret, "授权问题 .");   
                return ret;
            } else {
                // 没有读到人员信息
                doError(ret, "没有读到人员信息 .");   
                return ret;
            }

            if (readMessage.length() > 100) {
                if (mOnReadMessageListener != null) {
                    SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
                    mOnReadMessageListener.onReadPeopleName(mChatService.GetPeopleName());
                    mOnReadMessageListener.onReadPeopleSex(mChatService.GetPeopleSex());
                    mOnReadMessageListener.onReadPeopleNation(mChatService.GetPeopleNation());
                    mOnReadMessageListener.onReadPeopleAddress(mChatService.GetPeopleAddress());
                    mOnReadMessageListener.onReadPeopleIdCode(mChatService.GetPeopleIDCode());
                    mOnReadMessageListener.onReadDepartment(mChatService.GetDepartment());
                    try {
                        mOnReadMessageListener.onReadPeopleBirthday(format.parse(mChatService.GetPeopleBirthday()));
                        mOnReadMessageListener.onReadStartDate(format.parse(mChatService.GetStartDate()));
                        mOnReadMessageListener.onReadStopDate(format.parse(mChatService.GetEndDate()));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    mOnReadMessageListener.onReadProfile(BitmapFactory
                            .decodeFile("/sdcard/wltlib/zp.bmp"));
                }
            } else {
                // 没有数据
                doError(ret, "没有读到人员信息 .");   
                return ret;
            }

        } else {
            // 设备未连接 
            doError(ret, "设备未连接 .");   
            return ret;
        }
        
        /**
         * 断开连接
         */
        if (mChatService.getState() == IDCReaderSDK.STATE_CONNECTED) {
            mChatService.CVR_CloseComm();
        }
        
        return ret;
    }
    
    private void doError(int code, String errorStr) {

        if (mOnErrorListener != null) {
            mOnErrorListener.onError(code, errorStr);
        }

        if (mChatService.getState() == IDCReaderSDK.STATE_CONNECTED) {
            mChatService.CVR_CloseComm();
        }
    }
}
