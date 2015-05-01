package com.cking.phss.net; 
 
import java.io.IOException;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.AndroidHttpTransport;
import org.xmlpull.v1.XmlPullParserException;

import android.os.Environment;
import android.util.Log;

import com.cking.phss.global.Global;

public class SoapHelper {
    @SuppressWarnings("unused")
    private static final String TAG = "SoapHelper";
	
    private static final String exterNalPath=Environment.getExternalStorageDirectory().getPath();
	private static final String METHOD_NAME_GetStaff = "GetStaff";
    private static final String METHOD_NAME_SaveResults = "SaveResults";
	private static final String NAMESPACE = "http://tempuri.org/";    
    public static String webService(String method, String name, String value) throws Exception {
        final String SOAP_ACTION = NAMESPACE + method;
        
        SoapObject request = new SoapObject(NAMESPACE, method);

        request.addProperty(name, value);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);

        AndroidHttpTransport androidHttpTransport = new AndroidHttpTransport(Global.uploadKstjUrl);
        
        androidHttpTransport.call(SOAP_ACTION, envelope);

        // SoapPrimitive result = (SoapPrimitive)envelope.getResponse();
//        SoapObject result = (SoapObject) envelope.getResponse();
//        Log.v(TAG, "the soap result is >>>>>" + result.toString());
        SoapObject result = (SoapObject) envelope.bodyIn;
        //SoapObject detail = (SoapObject) result.getProperty(method + "Result");
        int count=result.getPropertyCount();
        if(count>0){
            Object obj= result.getProperty(0);
            if(obj instanceof SoapPrimitive){
                SoapPrimitive soapPrimitive=(SoapPrimitive)obj;
                String string=soapPrimitive.toString();
                Log.i(TAG, string);
                return string;
            }
        }
        return result.toString();
    }
    
    //获取快速体检 腰围和血糖数据的接口
    public static String webServiceGetKstj(String method, String name, String value) throws Exception {
        final String SOAP_ACTION = NAMESPACE + method;
        SoapObject request = new SoapObject(NAMESPACE, method);
        Log.i(TAG, value);
        request.addProperty(name, value);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);
        AndroidHttpTransport androidHttpTransport = new AndroidHttpTransport(Global.downloadKstjUrl);
        androidHttpTransport.call(SOAP_ACTION, envelope);
        SoapObject result = (SoapObject) envelope.bodyIn;
        int count=result.getPropertyCount();
        if(count>0){
            SoapPrimitive soapPrimitive=(SoapPrimitive) result.getProperty(0);
            String string=soapPrimitive.toString();
           return string;
        }
        return null;
    }
    
    public static String getStaff(String strCode) throws Exception {
        final String SOAP_ACTION = NAMESPACE + METHOD_NAME_GetStaff;

        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME_GetStaff);

        request.addProperty("code", strCode);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);

        AndroidHttpTransport androidHttpTransport = new AndroidHttpTransport(Global.uploadKstjUrl);
        
        androidHttpTransport.call(SOAP_ACTION, envelope);

        // SoapPrimitive result = (SoapPrimitive)envelope.getResponse();
        SoapObject result = (SoapObject) envelope.getResponse();
        Log.v(TAG, "the soap result is >>>>>" + result.toString());

        return result.getProperty("GetStaffResult").toString();
    }

    public static String saveResults(String strData) throws Exception {
        final String SOAP_ACTION = NAMESPACE + METHOD_NAME_SaveResults;

        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME_SaveResults);

        request.addProperty("data", strData);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);

        AndroidHttpTransport androidHttpTransport = new AndroidHttpTransport(Global.uploadKstjUrl);
        
        androidHttpTransport.call(SOAP_ACTION, envelope);

        // SoapPrimitive result = (SoapPrimitive)envelope.getResponse();
        SoapObject result = (SoapObject) envelope.getResponse();
        Log.v(TAG, "the soap result is >>>>>" + result.toString());

        return result.getProperty("SaveResultsResult").toString();
    }
    
    
    // 获得版本号
    public static String excuteGetVersionInfo(String orgCode, String type) {
        final String SOAP_ACTION = NAMESPACE + "UpdateFile";
        SoapObject request = new SoapObject(NAMESPACE, "UpdateFile");
        request.addProperty("orgCode", orgCode);
        request.addProperty("fileType", type);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);
        AndroidHttpTransport androidHttpTransport = new AndroidHttpTransport(
                Global.versionServiceUrl);
        try {
            androidHttpTransport.call(SOAP_ACTION, envelope);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        SoapObject result = null;
        try {
            result = (SoapObject) envelope.bodyIn;
        } catch (ClassCastException e) {
            return null;
        }
        return result.getProperty("UpdateFileResult").toString();
    }
}

