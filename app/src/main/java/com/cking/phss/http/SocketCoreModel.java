package com.cking.phss.http;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.net.ssl.HttpsURLConnection;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.util.Log;

import com.cking.phss.impl.InteractiveCenterImpl;

/**
 * 联网配置
 * 
 * @author
 * 
 */
public class SocketCoreModel implements Runnable {
    private static final String TAG = "SocketCoreModel";
    private boolean isRunning = false;// 是否在请求数据
    private Thread mThread = null;// 联网线程
    private Context mContext;
    private String url;// 请求的url
    private ConnectivityManager mConnectivityManager;// 联网管理类
    private boolean netGood;// 网络是否可用
    private int requestType;// 联网请求类型post or get
    private int requestId; // 联网唯一标示
    private HashMap<String, Object> postData;// post 数据
    private int dataType;// 获得数据的方式
    private IServerRev mISocketServer;// 回调借口
    private String mBackData;// 联网返回的字符
    private Handler mHandler;// 联网的消息提示
    private HttpItemBean bean = null;
    private boolean isCancal = false;;

    /**
     * 联网入口构造函数
     * 
     * @param mContext
     * @param url
     * @param requestId
     * @param requestType
     * @param postData
     * @param dataType
     * @param mISocketServer
     */
    public SocketCoreModel(Context mContext, String url, int requestId, int requestType,
            HashMap<String, Object> postData, int dataType, IServerRev mISocketServer) {
        bean = new HttpItemBean();
        this.mContext = mContext;
        bean.setUrl(url);
        bean.setPostData(postData);
        bean.setRequestId(requestId);
        bean.setDataType(dataType);
        bean.setmIsISocketServer(mISocketServer);
        bean.setContext(mContext);
        bean.setRun(true);
        this.mHandler = HttpHandler.getInstance();// 获得消息实例
        if (!checkNetWorkInfo()) {// 网络检测
            mHandler.obtainMessage(IServerRev.NETWORKDISABLE).sendToTarget();
        }
        startThread();// 启动线程
    }

    public void run() {
        try {
            this.socket(bean);
            HttpRequestList.httpList.remove(bean);
        } catch (Exception e) {
            Log.i(TAG, e.toString());
        }
    }

    /**
     * 检测网络是否可用和代理判断
     * 
     * @return
     */
    private boolean checkNetWorkInfo() {
        netGood = false;
        mConnectivityManager = (ConnectivityManager) mContext
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = mConnectivityManager.getActiveNetworkInfo();
        if (info != null) {
            this.netGood = info.isAvailable();
        }
        return netGood;
    }

    private void socket(HttpItemBean itemBean) {
        if (!isRunning)
            return;
        if (isCancal)
            return;
        this.url = itemBean.getUrl();
        if (url == null)
            return;

        this.dataType = itemBean.getDataType();
        this.postData = itemBean.getPostData();
        this.mISocketServer = itemBean.getmIsISocketServer();
        this.requestId = itemBean.getRequestId();
        this.requestType = itemBean.getRequestType();
        switch (dataType) {
            case IServerRev.GETSTRINGDATA:
                mBackData = getStringformNet(url);// 获得字符串
                if ("isCancel".endsWith(mBackData)) {
                    return;
                }
                if (mBackData != null && !mBackData.trim().equals("null") && bean.isRun()) {
                    InteractiveCenterImpl.getInstance().onRecv(mISocketServer,requestId, IServerRev.HTTP_SUCCESS, mBackData);// 联网成功回调
                } else if (bean.isRun()) {
                    InteractiveCenterImpl.getInstance().onRecv(mISocketServer,requestId, IServerRev.HTTP_FAIL, mBackData);// 联网失败回调
                }
                mBackData = null;
                break;
        }
    }

    private String getStringformNet(String url) {
        Object mHttpRequest = null;
        if (requestType == IServerRev.HTTP_POST) {
            mHttpRequest = new HttpPost(url);
        } else {
            mHttpRequest = new HttpGet(url);
        }
        BasicHttpParams localBasicHttpParams = new BasicHttpParams();
        HttpConnectionParams.setStaleCheckingEnabled(localBasicHttpParams, true);
        HttpConnectionParams
                .setConnectionTimeout(localBasicHttpParams, IServerRev.HTTP_TIMEOUTTIME);// 连接超时设置
        HttpConnectionParams.setSoTimeout(localBasicHttpParams, IServerRev.HTTP_TIMESOOUT);// 服务器超时设置
        HttpConnectionParams.setSocketBufferSize(localBasicHttpParams, 16384);
        SchemeRegistry localSchemeRegistry = new SchemeRegistry();
        PlainSocketFactory localPlainSocketFactory = PlainSocketFactory.getSocketFactory();
        Scheme localScheme1 = new Scheme("http", localPlainSocketFactory, 80);
        localSchemeRegistry.register(localScheme1);
        SSLSocketFactory localSSLSocketFactory = SSLSocketFactory.getSocketFactory();
        X509HostnameVerifier localX509HostnameVerifier1 = SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER;
        X509HostnameVerifier localX509HostnameVerifier2 = (X509HostnameVerifier) localX509HostnameVerifier1;
        localSSLSocketFactory.setHostnameVerifier(localX509HostnameVerifier2);
        HttpsURLConnection.setDefaultHostnameVerifier(localX509HostnameVerifier1);
        Scheme localScheme2 = new Scheme("https", localSSLSocketFactory, 443);
        localSchemeRegistry.register(localScheme2);
        ThreadSafeClientConnManager localThreadSafeClientConnManager = new ThreadSafeClientConnManager(
                localBasicHttpParams, localSchemeRegistry);
        DefaultHttpClient mDefaultHttpClient = new DefaultHttpClient(
                localThreadSafeClientConnManager, localBasicHttpParams);
        ((HttpRequestBase) mHttpRequest)
                .setHeader(
                        "Accept",
                        "image/gif, image/jpeg, image/pjpeg, image/pjpeg, application/x-shockwave-flash, application/xaml+xml, application/vnd.ms-xpsdocument, application/x-ms-xbap, application/x-ms-application, application/vnd.ms-excel, application/vnd.ms-powerpoint, application/msword, */*");

        if (requestType == IServerRev.HTTP_POST && this.postData != null && postData.size() > 0) {// post数据
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            Set<String> keyValue = this.postData.keySet();

            for (String iter : keyValue) {
                nameValuePairs
                        .add(new BasicNameValuePair(iter, this.postData.get(iter).toString()));
            }
            try {
                ((HttpPost) mHttpRequest).setEntity(new UrlEncodedFormEntity(nameValuePairs,
                        HTTP.UTF_8));
            } catch (UnsupportedEncodingException e) {

                e.printStackTrace();
                return null;
            }
        }
        while (true) {
            String strformat;
            try {
                HttpResponse mHttpResponse = mDefaultHttpClient
                        .execute((HttpUriRequest) mHttpRequest);
                int stateCode = mHttpResponse.getStatusLine().getStatusCode();
                if (isCancal)
                    return "isCancel";
                if (stateCode == 200) {
                    HttpEntity mHttpEntity = mHttpResponse.getEntity();
                    strformat = EntityUtils.toString(mHttpEntity, "utf-8");
                    return strformat;
                } else {
                    break;
                }
            } catch (SocketTimeoutException e) {
                strformat = null;
            } catch (SocketException e) {
                strformat = null;
                break;
            } catch (ClientProtocolException e) {// 报出异常则回收资源
                strformat = null;
                break;
            } catch (IOException e) {
                strformat = null;
                break;
            } finally {
            }
        }

        return null;
    }

    public void startThread() {
        isRunning = true;
        if (mThread != null) {
            mThread = null;
        }
        mThread = new Thread(this);
        bean.setThread(mThread);
        HttpRequestList.httpList.add(bean);
        mThread.start();
    }

    /**
     * 取消联网
     * 
     * @param c
     * @param requestId
     *            ,如果要取消该Activity所有的联网则传0
     */
    public void cancelSocket(Context c, int requestId) {
        if (HttpRequestList.httpList != null && HttpRequestList.httpList.size() > 0) {
            HttpItemBean iBean = HttpRequestList.httpList.get(0);
            // for(int
            // i=0,n=HttpRequestList.httpList.size();i<n;i++){//取消正在联网的线程
            if (bean == iBean) {
                bean.setRun(false);
                isCancal = true;
                bean.getThread().interrupt();
                if (bean.getThread().isAlive()) {
                    try {
                        bean.setThread(null);
                    } catch (Exception e) {
                    }
                }
                HttpRequestList.httpList.remove(0);
                return;
            }
        }
        if (HttpRequestList.mList != null && HttpRequestList.mList.size() > 0) {
            for (int i = 0, n = HttpRequestList.mList.size(); i < n; i++) {// 取消等待在联网的线程
                HttpItemBean bean = HttpRequestList.mList.get(i);
                switch (requestId) {
                    case 0:
                        if (bean.getContext() == c) {
                            HttpRequestList.mList.remove(i);
                        }
                        break;
                    default:
                        if (bean.getRequestId() == requestId /*
                                                              * &&
                                                              * bean.getContext
                                                              * () == c
                                                              */) {
                            HttpRequestList.mList.remove(i);
                            return;
                        }
                        break;
                }

            }
        }
    }

    /**
     * 取消联网
     * 
     * @param c
     */
    public void cancelSocket(Context c) {

    }
}
