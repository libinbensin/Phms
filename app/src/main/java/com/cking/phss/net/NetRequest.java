package com.cking.phss.net;

import android.util.Log;
import android.util.Xml;

import com.cking.phss.file.FileLog;
import com.cking.phss.global.Global;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class NetRequest {
	private static String TAG = "NetRequest";
	private static int REQUEST_TIMEOUT = 60*1000;
	private static int SO_TIMEOUT = 60*1000;
	
	public static String getXmlByPost(String url, String xmlStr) {
		HttpPost httpPost = new HttpPost();
		httpPost = new HttpPost(url);
		List<BasicNameValuePair> paraList = new ArrayList<BasicNameValuePair>();
		
		Log.i(TAG, "send data has been save to file.");
		FileLog.i("\r\n-------------------[SEND DATA]----------------------------\r\n", "\r\n" + xmlStr);
        Log.e("请求字符串",xmlStr);
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
			}else{
				//TODO 异常处理
				Log.e(TAG,"服务器响应："+statusCode);
			}
			result = getInterfaceResult(result);

	        Log.i(TAG, "recv data has been save to file.");
	        FileLog.i("\r\n-------------------[RECV DATA]----------------------------\r\n", "\r\n" + result);
            Log.e("响应字符串",result);
			return result;

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	private static String getInterfaceResult(String result) throws XmlPullParserException, IOException {
		//接口返回值格式如下，string部分是接口协议的xml文件，需剥壳
		//<?xml version="1.0" encoding="utf-8"?>
		//<string xmlns="http://tempuri.org/">string</string>
		
		final String ROOT_TAG = "string";
		
		XmlPullParser parser = Xml.newPullParser();
		byte[] bytes = result.getBytes();
		ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
		parser.setInput(bais, "utf-8");
		int eventType = parser.getEventType();
		while (eventType != XmlPullParser.END_DOCUMENT) {

			if (parser.getEventType() == XmlPullParser.START_TAG
					&& parser.getName().equals(ROOT_TAG)) {
				return parser.nextText();
			}
			eventType = parser.next();

		}
		
		return null;
	}

	public static String getXmlByPost(String xmlStr) {
        String url = Global.webserviceUrl;
		return getXmlByPost(url, xmlStr);
	}

	public static String getXmlByGet(String url) {
		//TODO 按需要实现此方法
		return null;
	}

}
