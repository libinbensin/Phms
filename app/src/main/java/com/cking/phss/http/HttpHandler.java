package com.cking.phss.http;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.cking.phss.R;
import com.cking.application.MyApplication;

/**
 * 联网检测相关提示，消息类
 * 
 * @author
 * 
 */
public class HttpHandler extends Handler {
    private static HttpHandler instance;

    public static HttpHandler getInstance() {
        if (instance == null) {
            instance = new HttpHandler();
        }
        return instance;
    }

    /**
     * 提示网络不可获取
     */
    @Override
    public void handleMessage(Message msg) {
        Context mContext = MyApplication.getInstance().getApplicationContext();
        switch (msg.what) {
            case IServerRev.NETWORKDISABLE:// 网络连接失败
                Toast.makeText(mContext,
                        mContext.getResources().getString(R.string.networkdisable),
                        Toast.LENGTH_SHORT).show();
                break;
            case IServerRev.IMAGEDOWNLOADFIAL:// 图片下载失败
                Toast.makeText(mContext, mContext.getResources().getString(R.string.imagefail),
                        Toast.LENGTH_SHORT).show();
                break;
            case IServerRev.CONNECTFAILED:// 连接服务器失败
                Toast.makeText(mContext, mContext.getResources().getString(R.string.connectfail),
                        Toast.LENGTH_SHORT).show();
        }
        super.handleMessage(msg);
    }

}
