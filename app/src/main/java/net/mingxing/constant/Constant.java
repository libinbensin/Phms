package net.mingxing.constant;

import net.mingxing.utils.SDCardUtil;

import java.io.File;

/**
 * Created by MingXing on 2015/5/1.
 * 配置常量
 */
public class Constant {

    /**
     * SD卡路径
     */
    public static final String SDCARD_PATH = SDCardUtil.SDCARD_PATH_STRING;

    /**
     * 配置路径
     */
    public static final String PHMS_PATH = SDCARD_PATH + "/mingxing";
    public static final String LOGO_PATH = PHMS_PATH + "/image/logo.png";
    public static final String VERSION_PATH = PHMS_PATH + "/image/banquan.png";
    public static final String APPCONFIG_PATH = PHMS_PATH + "/xml/appconfig.xml";
    public static final String VALUES_PATH = PHMS_PATH + "/xml/values.xml";
    public static final String LOG_PATH = PHMS_PATH + "/log/";

    /**
     * database cofig
     */
    public static final String DB_DIR = PHMS_PATH + "/record";
    public static final String ADDRESS_DB_NANME = "address.db";
    public static final String ADDRESS_DB_PATH = DB_DIR + File.separator + ADDRESS_DB_NANME;

    public static final String PHMS_DB_NANME = "phms.db";
    public static final String PHMS_DB_PATH = PHMS_PATH + File.separator + PHMS_DB_NANME;

}
