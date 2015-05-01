package com.cking.phss.http;

import java.io.Serializable;
import java.util.HashMap;

import android.content.Context;

public class HttpItemBean implements Serializable {

    /**
     * 联网实体类
     */
    private static final long serialVersionUID = 1L;
    private int requestId;// 请求id
    private String url;// url连接
    private HashMap<String, Object> postData;// post数据
    private IServerRev mIsISocketServer;// 回调借口
    private int dataType;// 以什么数据类型返回ISocketServer.GETINPUTSTREAMDATA =
                         // 1;联网以流的方式返回 ，ISocketServer.GETSTRINGDATA =
                         // 2;联网以字符串的方式返回 ，ISocketServer.GETBYTEDATA=
                         // 3;联网以二进制数组的方式返回
    private int requestType;// 数据请求类型
                            // ISocketServer.HTTP_POST=0和ISocketServer.HTTP_GET=1
    private Context context;
    private Thread thread;
    private boolean isRun;

    public boolean isRun() {
        return isRun;
    }

    public void setRun(boolean isRun) {
        this.isRun = isRun;
    }

    public int getRequestType() {
        return requestType;
    }

    public void setRequestType(int requestType) {
        this.requestType = requestType;
    }

    public int getDataType() {
        return dataType;
    }

    public void setDataType(int dataType) {
        this.dataType = dataType;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public HashMap<String, Object> getPostData() {
        return postData;
    }

    public void setPostData(HashMap<String, Object> postData) {
        this.postData = postData;
    }

    public IServerRev getmIsISocketServer() {
        return mIsISocketServer;
    }

    public void setmIsISocketServer(IServerRev mIsISocketServer) {
        this.mIsISocketServer = mIsISocketServer;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public Thread getThread() {
        return thread;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }

}
