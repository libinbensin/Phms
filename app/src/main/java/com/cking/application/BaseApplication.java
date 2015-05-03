package com.cking.application;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.cking.phss.util.Constants;

import net.xinhuaxing.util.ResourcesFactory;

import java.util.UUID;

/**
 * Created by MingXing on 2015/5/3.
 */
public class BaseApplication extends Application {

    private static final String TAG = "BaseApplication";
    private static BaseApplication instance = null;

    private Context mContext = null;

    public void onCreate() {
        super.onCreate();
        Log.i("BaseApplication", "init application");
        init();
    }

    private void init() {
        this.mContext = this;
        instance = this;

        initPrepare();

        ResourcesFactory.readFile(this.mContext, getResourcesFile(), getResourcesFile2());
    }

    public void initPrepare() {
    }

    public String getWebServiceUrl() {
        return Constants.WEBSERVICE_URL;
    }

    public String getAppRootName() {
        return Constants.APP_ROOT_NAME;
    }

    public String getTemplateXmlDir() {
        return Constants.APP_TEMPLATE_DIR;
    }

    public String getAppCacheDir() {
        return Constants.APP_CACHE_DIR;
    }

    public String getLogPath() {
        return Constants.LOG_PATH;
    }

    public String getDatabasePath() {
        return Constants.DATABASE_FILE_PATH;
    }

    public int getDatabaseVersion() {
        return Constants.DB_VERSION;
    }

    public String getResidentUuid() {
        return UUID.randomUUID().toString();
    }

    public String getResourcesFile() {
        return Constants.APP_XML_DIR + "参数列表.xml";
    }

    public String getResourcesFile2() {
        return Constants.APP_XML_DIR + "widgets.xml";
    }

    public static BaseApplication getInstance() {
        return instance;
    }
}