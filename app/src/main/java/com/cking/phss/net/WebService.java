/* Cking Inc. (C) 2012. All rights reserved.
 *
 * WebService.java
 * classes : com.cking.phss.net.WebService
 * @author Wation Haliyoo
 * V 1.0.0
 * Create at 2012-10-17 下午12:06:39
 */
package com.cking.phss.net;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.cking.phss.dto.IDto;
import com.cking.phss.xml.util.XmlSerializerUtil;

/**
 * com.cking.phss.net.WebService
 * 
 * @author Wation Haliyoo <br/>
 *         create at 2012-10-17 下午12:06:39
 */
public class WebService {
    private static final String TAG = "WebService";
    private String[] mMethodNames = { "Checkin", "GetStaff", "SaveResults","SearchCheck","SearchResultsByCheckinId","SearchResults" };

    private static WebService mWebServiceFactory = new WebService();

    public static WebService getInstance() {
        return mWebServiceFactory;
    }

    private boolean existMethdName(String webServiceName) {
        for (String str : mMethodNames) {
            if (str.equals(webServiceName)) {
                return true;
            }
        }

        return false;
    }

    public void excute(final String webServiceName, final String name, final String value,
            final ISoapRecv iSoapRecv) {

        final Handler handler = new Handler(Looper.getMainLooper());
        new Thread() {
            @Override
            public void run() {
                String result = null;
                boolean resultState = false;
                if (existMethdName(webServiceName)) {
                    try {
                        result = SoapHelper.webService(webServiceName, name, value);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (result == null) {
                    resultState = false;
                } else {
                    resultState = true;
                }
                final boolean isSucc = resultState;
                final String resultStr = result;
                Runnable r = new Runnable() {

                    @Override
                    public void run() {

                        iSoapRecv.onRecvData(resultStr, isSucc);
                    }
                };
                handler.post(r);
            }
        }.start();
    }

    // 执行获取快速体检 腰围和血糖的数据
    public void excuteGetKstj(final String webServiceName, final String name, final IDto bean,
            final ISoapRecv iSoapRecv) {
        final Handler handler = new Handler(Looper.getMainLooper());
        new Thread() {
            @Override
            public void run() {
                String result = null;
                boolean resultState = false;
                try {
                    result = SoapHelper.webServiceGetKstj(webServiceName, name, XmlSerializerUtil
                            .getInstance().beanToXml(bean));
                    // String
                    // xmlString2="<?xml version=\"1.0\" encoding=\"utf-8\" ?><Body><Request Type=\"1\"><bridgeId>645dd7001db7</bridgeId><deviceSn>0911078500525731</deviceSn><pfId>1</pfId></Request></Body>";
                    // result = SoapHelper.webServiceGetKstj(webServiceName,
                    // name, xmlString2);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (result == null) {
                    resultState = false;
                } else {
                    resultState = true;
                }
                final boolean isSucc = resultState;
                final String resultStr = result;
                Log.i(TAG, resultStr);
                Runnable r = new Runnable() {
                    @Override
                    public void run() {
                        iSoapRecv.onRecvData(resultStr, isSucc);
                    }
                };
                handler.post(r);
            }
        }.start();
    }

    // 去掉一个字符串前面的<?xml version="1.0" encoding="utf-8"?><Body>
    // 和后面的</Body>
    public static String removeXmlString(String orgStr) {
        try {
            orgStr = orgStr.replace("<?xml version=\"1.0\" encoding=\"utf-8\"?>", "")
                    .replace("<Body>", "").replace("</Body>", "");
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return orgStr;
    }
    
    // 字符串前面的加上<?xml version="1.0" encoding="utf-8"?><Body>
    // 和最后面加上</Body>
    public static String addXmlString(String orgStr) {
        try {
            orgStr = "<?xml version=\"1.0\" encoding=\"utf-8\"?><Body>"+orgStr+"</Body>";
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return orgStr;
    }
}
