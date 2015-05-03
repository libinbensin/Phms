package com.cking.phss.util;

import android.os.Environment;

import java.io.File;

/**
 * Created by MingXing on 2015/5/3.
 */
public class Constants {
    private static final String TAG = "Constants";
    public static String APP_ROOT_NAME = "app";

    public static final String XHX_STORAGE_DIR = Environment.getExternalStorageDirectory() + File.separator;
    public static String APP_ROOT_DIR = XHX_STORAGE_DIR + APP_ROOT_NAME + File.separator;
    public static String APP_XML_DIR = APP_ROOT_DIR + "xml" + File.separator;
    public static String APP_RES_DIR = APP_ROOT_DIR + "res" + File.separator;
    public static String APP_RECORD_DIR = APP_ROOT_DIR + "record" + File.separator;
    public static String APP_TEMPLATE_DIR = APP_ROOT_DIR + "template" + File.separator;
    public static String APP_CACHE_DIR = APP_ROOT_DIR + "cache" + File.separator;

    public static String LOG_PATH = XHX_STORAGE_DIR + "xhxlog" + File.separator;
    public static final String DEFAULT_HTTP_URL = "http://xinhuaxing.net";
    public static String WEBSERVICE_URL = "http://xinhuaxing.net";

    public static final String DATABASE_FILE_PATH = APP_RECORD_DIR + "app.db";

    public static int DB_VERSION = 1;
}
