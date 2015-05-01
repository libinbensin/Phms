package com.cking.phss.http;

import java.util.HashMap;

import android.content.Context;

import com.cking.phss.util.MyApplication;

public class SocketModelImpl {
    private static Context context = MyApplication.getInstance().getApplicationContext();

    /**
     * get方式请求数据，以String方式返回数据
     * 
     * @param url
     * @param requestId
     * @param server
     * @return
     */
    public static SocketCoreModel socketGetByString(String url, int requestId, IServerRev server) {
        SocketCoreModel mSocketCoreModel = new SocketCoreModel(context, url, requestId,
                IServerRev.HTTP_GET, null, IServerRev.GETSTRINGDATA, server);
        return mSocketCoreModel;
    }

    /**
     * post方式请求数据，以String方式返回数据
     * 
     * @param url
     * @param postData
     * @param requestId
     * @param server
     * @return
     */
    public static SocketCoreModel socketPostByString(String url, HashMap<String, Object> postData,
            int requestId, IServerRev server) {
        SocketCoreModel mSocketCoreModel = new SocketCoreModel(context, url, requestId,
                IServerRev.HTTP_POST, postData, IServerRev.GETSTRINGDATA, server);
        return mSocketCoreModel;
    }
}
