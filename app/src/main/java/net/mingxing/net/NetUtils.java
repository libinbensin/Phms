package net.mingxing.net;

import android.util.Log;
import android.util.Xml;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import net.mingxing.global.Global;
import net.mingxing.log.FileLog;
import net.mingxing.protocol.IProtocol;
import net.mingxing.utils.XmlSeriaUtil;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.xmlpull.v1.XmlPullParser;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MingXing on 2015/4/21 0021.
 * 功能:
 * 1, bean <==> xmlString
 */
public class NetUtils {

    private static String URL = Global.mHospitalTag.getWebserviceurl();

    public static final String REQUESTPARAMS = "XmlString";

    //    request state code;
    public static int REQUEST_STATE;
    public static int REQUEST_SUCCESS = 0;
    public static int REQUEST_ERROR = -1;

    //    response string message
    public static String RESPONSE_MSG;


    private static int REQUEST_TIMEOUT = 4 * 1000;
    private static int SO_TIMEOUT = 4 * 1000;


    private NetUtils() {
    }

    public static NetUtils netInstance() {
        NetUtils m = new NetUtils();
        return m;
    }


    public interface onRequestResult {

        /**
         * 网络请求成功
         *
         * @param bean
         */
        void onSuccess(IProtocol bean);

        /**
         * 网络请求成功
         *
         * @param beans
         */
        void onSuccessForTimes(List<IProtocol> beans);

        /**
         * 网络请求失败
         *
         * @param errMsg 错误信息
         */
        void onFail(String errMsg);
    }


    /**
     * 发送post请求
     */
    public void requestByPost(final IProtocol requestBean, final onRequestResult callBack) {

        if (requestBean != null) {
            RequestParams params = new RequestParams(); // 默认编码UTF-8
            String reqXML = XmlSeriaUtil.beanToXml(requestBean);
            Log.e("请求字符串", reqXML);
            FileLog.i("\r\n-------------------[SEND DATA]----------------------------\r\n", "\r\n" + reqXML);
            params.addBodyParameter(REQUESTPARAMS, reqXML);
            HttpUtils http = new HttpUtils();
            http.send(HttpRequest.HttpMethod.POST,
                    URL,
                    params,
                    new RequestCallBack<String>() {

                        @Override
                        public void onStart() {
                        }

                        @Override
                        public void onLoading(long total, long current, boolean isUploading) {
                        }

                        @Override
                        public void onSuccess(ResponseInfo<String> responseInfo) {
                            String resXML = getInterfaceResult(responseInfo.result);
                            Log.e("响应字符串", resXML);
                            FileLog.i("\r\n-------------------[RECV DATA]----------------------------\r\n", "\r\n" + resXML);
                            IProtocol iProtocol = XmlSeriaUtil.xmlToBean(resXML, requestBean.getClass());
                            callBack.onSuccess(iProtocol);
                        }

                        @Override
                        public void onFailure(HttpException error, String msg) {
                            callBack.onFail(msg);
                        }
                    });
        }
    }


    /**
     * 发送多次请求,调用
     *
     * @param beans    协议实体
     * @param callBack 回调函数
     * @return
     */
    public void getXmlByPost(List<IProtocol> beans, onRequestResult callBack) {

        List<IProtocol> protocols = new ArrayList<>();

        for (IProtocol bean : beans) {

            HttpPost httpPost = new HttpPost(URL);

            List<BasicNameValuePair> paraList = new ArrayList<>();
            String xmlStr = XmlSeriaUtil.beanToXml(bean);
            Log.e("请求字符串", xmlStr);
            FileLog.i("\r\n-------------------[SEND DATA]----------------------------\r\n", "\r\n" + xmlStr);
            paraList.add(new BasicNameValuePair("XmlString", xmlStr));
            try {
                httpPost.setEntity(new UrlEncodedFormEntity(paraList, HTTP.UTF_8));

                String result = "";

                //设置超时
                BasicHttpParams httpParams = new BasicHttpParams();
                HttpConnectionParams.setConnectionTimeout(httpParams, REQUEST_TIMEOUT);
                HttpConnectionParams.setSoTimeout(httpParams, SO_TIMEOUT);
                DefaultHttpClient defaultHttpClient = new DefaultHttpClient(httpParams);

                //发送请求
                HttpResponse response = defaultHttpClient.execute(httpPost);
                int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode == 200) {
                    HttpEntity entity = response.getEntity();

                    result = EntityUtils.toString(entity, HTTP.UTF_8);
                    result = getInterfaceResult(result);
                    Log.e("响应字符串", result);
                    FileLog.i("\r\n-------------------[RECV DATA]----------------------------\r\n", "\r\n" + result);
                    protocols.add(XmlSeriaUtil.xmlToBean(result, bean.getClass()));
                }
            } catch (Exception e) {
                callBack.onFail(e.getMessage());
            }
        }
        callBack.onSuccessForTimes(protocols);
    }


    /**
     * 接口返回值格式如下，string部分是接口协议的xml文件，需剥壳
     *
     * @param result
     * @return
     */
    private String getInterfaceResult(String result) {
        //接口返回值格式如下，string部分是接口协议的xml文件，需剥壳
        //<?xml version="1.0" encoding="utf-8"?>
        //<string xmlns="http://tempuri.org/">string</string>
        final String ROOT_TAG = "string";

        XmlPullParser parser = Xml.newPullParser();
        byte[] bytes = result.getBytes();
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        try {
            parser.setInput(bais, "utf-8");
            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {

                if (parser.getEventType() == XmlPullParser.START_TAG
                        && parser.getName().equals(ROOT_TAG)) {
                    return parser.nextText();
                }
                eventType = parser.next();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
