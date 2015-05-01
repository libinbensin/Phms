/* Xinhuaxing Inc. (C) 2013. All rights reserved.
 *
 * AppConfigFactory.java
 * classes : net.xinhuaxing.launcher.util.AppConfigFactory
 * @author Wation Haliyoo
 * V 1.0.0
 * Create at 2013-6-3 涓嬪崍03:35:02
 */
package com.cking.phss.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import net.xinhuaxing.eshow.constants.Constants;
import android.content.Context;
import android.util.Log;

import com.cking.phss.bean.IBean;

/**
 * net.xinhuaxing.launcher.util.AppConfigFactory
 * 
 * @author Wation Haliyoo <br/>
 *         create at 2013-6-3 03:35:02
 */
public class AppConfigFactory {
    private static final String TAG = "AppConfigFactory";

    public static class AppConfig implements IBean {
        private static final String TAG = "AppConfig";

        String mksz = null;
        String dalusz = null;
        String tjsrxz = null;

        public String getMksz() {
            return mksz;
        }

        public void setMksz(String mksz) {
            this.mksz = mksz;
        }

        public String getDalusz() {
            return dalusz;
        }

        public void setDalusz(String dalusz) {
            this.dalusz = dalusz;
        }

        public String getTjsrxz() {
            return tjsrxz;
        }

        public void setTjsrxz(String tjsrxz) {
            this.tjsrxz = tjsrxz;
        }
    }
    
    public static boolean readAppConfig(Context context, AppConfig appConfig) throws IOException {
        Properties properties = new Properties();
        String file = Constants.PHSS_XML_DIR + "app.properties";
        
        Log.i(TAG, "entry AppConfigFactory.readAppConfig...");

        File fil = new File(file);
        if (!fil.exists()) {
            writeDefaultAppConfig(context);
        }

        FileInputStream s = new FileInputStream(file);
        properties.load(s);

        if (properties.containsKey("模块设置")) {
            String ret = properties.getProperty("模块设置");
            appConfig.setMksz(ret);
        }
        if (properties.containsKey("档案录入设置")) {
            String ret = properties.getProperty("档案录入设置");
            appConfig.setDalusz(ret);
        }
        if (properties.containsKey("体检输入限制")) {
            String ret = properties.getProperty("体检输入限制");
            appConfig.setTjsrxz(ret);
        }
        return true;
    }
    
    private static boolean writeDefaultAppConfig(Context context) throws IOException {
        Properties properties = new Properties();
        String file = Constants.PHSS_XML_DIR + "app.properties";

        File pat = new File(new File(file).getParent());
        if (!pat.exists()) {
            if (!pat.mkdirs()) {
            	Log.e(TAG, "CANT Create Dir:" + pat);
            	return false;
            }
        }
        File fil = new File(file);
        if (!fil.exists()) {
            fil.createNewFile();
        }
        if (!fil.canWrite()) {
            return false;
        }

        properties.put("模块设置", "");
        properties.put("档案录入设置", "身份证号可编辑,按身份证号搜索档案,姓名可编辑,按姓名搜索档案,卡号可编辑,按卡号搜索档案");
        properties.put("体检输入限制", "");
        try {
            FileOutputStream s = new FileOutputStream(file, false);
            properties.store(s, null);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static boolean writeAppConfig(Context context, AppConfig appConfig)
            throws IOException {
        Properties properties = new Properties();
        String file = Constants.PHSS_XML_DIR + "app.properties";

        File pat = new File(new File(file).getParent());
        if (!pat.exists())
            pat.mkdirs();
        File fil = new File(file);
        if (!fil.exists())
            fil.createNewFile();
        if (!fil.canWrite()) {
            return false;
        }

        properties.put("模块设置", appConfig.getMksz());
        properties.put("档案录入设置", appConfig.getDalusz());
        properties.put("体检输入限制", appConfig.getTjsrxz());
        
        try {
            FileOutputStream s = new FileOutputStream(file, false);
            properties.store(s, null);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
