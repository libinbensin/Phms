package com.cking.phss.update;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Environment;
import android.util.Log;
import android.util.Xml;

import com.cking.phss.global.Global;
import com.cking.phss.net.SoapHelper;

/**
 * check whether need update versions
 * 
 * @author taowencong
 *  we need four values for version check 
 *  the fisrt value is last check time 
 *  the second value is last check version  code
 *  the third value is the last check and download file name 
 *  the fourth value is the last check and update status
 * 
 */
public class VersionChecked {
    
    public static final int CHECK_NO_UPDATE=1;
    public static final int CHECK_TO_INSTALL=2;
    public static final int CHECK_TO_UPDATE=3;
    private static final String TAG = "VersionChecked";
    
    private Context mContext;
    private String currentTime;
    private SimpleDateFormat format;
    public VersionInfo versionInfo;
    private int localVersion;
    public VersionChecked(Context context) {
        mContext = context;
        format = new SimpleDateFormat("yyyy-MM-dd");
        currentTime = format.format(new Date());
    }

    /**
     * start check
     * 
     * @return 
     */
    public int check() {
        localVersion = getLocalVersion();//get the current app version
        if(UpdateInfo.getInstance().getLastCheckVersion()<=localVersion){
            if (UpdateInfo.getInstance().getLastCheckTime().equals(currentTime)) {// if has checked today,so we Don't need checked again
                return CHECK_NO_UPDATE;
            }else{
                return checkVersionInLocalAndServer();
            }
        }
        // need to check why  the last update faied
        //check the last checked status
        int lastCheckStatus=UpdateInfo.getInstance().getLastCheckStatus();
        switch (lastCheckStatus) {
            case 0:
            case 1:// although status report succeed,but the version is also lower the last check version
            case 2:
                String lastCheckFile=UpdateInfo.getInstance().getLastCheckFile();
                if(!lastCheckFile.equals("")){
                    File file=new File(Environment.getExternalStorageDirectory().getAbsoluteFile()+"/phms/"+lastCheckFile);
                    if(file.exists()){
                        return CHECK_TO_INSTALL;
                    }
                 }
                 if(versionInfo==null)
                     versionInfo = getServiceVersion(Global.orgCode, "APK");
                 return CHECK_TO_UPDATE;
            case 3:
                if(versionInfo==null)
                    versionInfo = getServiceVersion(Global.orgCode, "APK");
                return CHECK_TO_UPDATE;
        }
        return CHECK_NO_UPDATE;
    }

    private int checkVersionInLocalAndServer(){
        versionInfo = getServiceVersion(Global.orgCode, "APK");
        if (versionInfo == null || versionInfo.versionCode == -1) {
            return CHECK_NO_UPDATE;
        }
        UpdateInfo.getInstance().setLastCheckTime(currentTime);
        //check the last file exists or not .if exists then delete
        String lastCheckFile=UpdateInfo.getInstance().getLastCheckFile();
        Log.i(TAG, "lastCheckFile"+lastCheckFile);
        if(!lastCheckFile.equals("")){
            UpdateInfo.getInstance().setLastCheckFile("");
            UpdateInfo.getInstance().setLastCheckStatus(0);
            File file=new File(Environment.getExternalStorageDirectory().getAbsoluteFile()+"/phms/"+lastCheckFile);
            if(file.exists()){
                file.delete();
            }
        }
        Log.i(TAG, "localVersion="+localVersion);
        Log.i(TAG, "versionInfo.versionCode="+versionInfo.versionCode);
        if (localVersion < versionInfo.versionCode) { // the local version is
                                                      // lower than server
                                                      // version
            //set the newest version code and return to update
            UpdateInfo.getInstance().setLastCheckVersion(versionInfo.versionCode);
            return CHECK_TO_UPDATE;
        }
        return CHECK_NO_UPDATE;
    }
    
    /**
     * get the local current app version code
     */
    public int getLocalVersion() {
        PackageInfo pi = null;
        int localVersion = 0;
        try {
            pi = mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), 0);
            localVersion = pi.versionCode;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return localVersion;
    }

    /**
     * get the server current app version code
     */
    public static VersionInfo getServiceVersion(String orgCode, String type) {
        String infoResult = SoapHelper.excuteGetVersionInfo(orgCode, type);
        if(infoResult==null)
            return null;
        infoResult = "<?xml version=\"1.0\" encoding=\"utf-8\" ?>"+infoResult;
        try {
            return parseInfoResult(infoResult);
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return null;
    }

    private static VersionInfo parseInfoResult(String infoResult) throws XmlPullParserException, IOException {
        VersionInfo versionInfo = new VersionInfo();
        if (infoResult != null && !infoResult.equals("")) {
            // 构建解析器
            XmlPullParser parser = Xml.newPullParser();
            // 获得xml文件的输入流,获得文档对象
            ByteArrayInputStream in = new ByteArrayInputStream(infoResult.getBytes());
            parser.setInput(in, "UTF-8");
            // 开始解析
            int eventType = parser.getEventType();// 产生第一个事件
            while (eventType != XmlPullParser.END_DOCUMENT) {// 循环条件 文档结束
                switch (eventType) {// 判断事件的类型
                    case XmlPullParser.START_DOCUMENT: // 开始文档
                        break;
                    case XmlPullParser.START_TAG: // 开始元素节点
                        String name1 = parser.getName();// 获 取解析器当前指向元素节点的名称
                        if ("FileInfo".equals(name1)) {// 如果指向person节点
                        } else if ("url".equals(name1)) {
                            versionInfo.url = parser.nextText();
                        } else if ("versionCode".equals(name1)) {
                            versionInfo.versionCode = new Integer(parser.nextText());// 取得当前节点
                                                                                     // 下一个的文本节点的值
                        } else if ("versionName".equals(name1)) {
                            versionInfo.versionName = parser.nextText();
                        } else if ("fileName".equals(name1)) {
                            versionInfo.fileName = parser.nextText();
                        } else if ("dateInfo".equals(name1)) {
                            versionInfo.dateInfo = parser.nextText();
                        }
                        break;
                    case XmlPullParser.END_TAG:// 结束元素节点
                        break;
                }
                eventType = parser.next();// 解析器向下移动一个单位
            }
        }
        
        return versionInfo;
    }
}
