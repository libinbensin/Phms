package com.cking.phss.http;

public interface IServerRev {
    public static final int HTTP_SUCCESS = 200;// 联网成功
    public static final int HTTP_FAIL = 404;// 联网失败
    public static final int HTTP_TIMEOUT = 500;// 联网超时
    public static final int HTTP_TIMEOUTTIME = 6000;// 联网超时时间
    public static final int HTTP_TIMESOOUT = 20000;// 服务器超时时间设置
    public static final int HTTP_POST = 0;// post请求方式
    public static final int HTTP_GET = 1;// get请求方式
    public static final int GETINPUTSTREAMDATA = 1;// 联网以流的方式返回
    public static final int GETSTRINGDATA = 2;// 联网以字符串的方式返回
    public static final int GETBYTEDATA = 3;// 联网以二进制数组的方式返回
    public static final int GETAMFINPUTSTREAMDATA = 4;// 联网以二进制数组的方式返回
    public static final int NETWORKDISABLE = 1;// 网络断开消息提醒
    public static final int IMAGEDOWNLOADFIAL = 2;// 图片下载失败
    public static final int CONNECTFAILED = 3;// 网络断开消息提醒

    // 回传数据
    void onRecv(int requestMark, Object obj,boolean bUpdate);
}
