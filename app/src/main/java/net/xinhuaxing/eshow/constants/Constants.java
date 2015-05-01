/* Xinhuaxing Inc. (C) 2012. All rights reserved.
 *
 * Constants.java
 * classes : net.xinhuaxing.eshow.constants.Constants
 * @author wation
 * V 1.0.0
 * Create at 2012-12-18 涓嬪崍04:51:18
 */
package net.xinhuaxing.eshow.constants;

import java.io.File;

import android.os.Environment;

/**
 * 全局常量类
 * net.xinhuaxing.eshow.constants.Constants
 * @author wation <br/>
 * create at 2013-10-22 下午7:51:01
 */
public class Constants {
    private static final String TAG = "Constants";

    /**
     * eShow:xinhuaxing_storage根目录，挂载在/mnt/目录下
     * eShowMaker:本地存储根目录，通过System.getenv("APPDATA")获取
     */
    public static final String XHX_STORAGE_DIR = Environment.getExternalStorageDirectory() + File.separator;
    public static final String ESHOW_ROOT_DIR = XHX_STORAGE_DIR + "eshow" + File.separator;
    public static final String ESHOW_GLOBAL_PATH = ESHOW_ROOT_DIR + "global" + File.separator;
    public static final String ESHOW_LOCAL_PATH = ESHOW_ROOT_DIR + "local" + File.separator;
    public static final String ESHOW_GLOBALAPK_PATH = ESHOW_ROOT_DIR + "global-apk" + File.separator;
    public static final String ESHOW_GLOBAL_SYS_CONFIG_FILE = ESHOW_GLOBAL_PATH + "sysConfig.xml";
    public static final String ESHOW_GLOBAL_RES_DIR = ESHOW_GLOBAL_PATH + "res" + File.separator;
    public static final String ESHOW_GLOBAL_DATA_DIR = ESHOW_GLOBAL_PATH + "data" + File.separator;
    public static final String ESHOW_GLOBAL_APK_DIR = ESHOW_GLOBAL_DATA_DIR + "apk" + File.separator;
    public static final String ESHOW_GLOBAL_APKT_DIR = ESHOW_GLOBAL_DATA_DIR + "apkt" + File.separator;
    public static final String ESHOW_GLOBAL_FILE_DIR = ESHOW_GLOBAL_DATA_DIR + "file" + File.separator;
    public static final String ESHOW_GLOBAL_VENDOR_DIR = ESHOW_GLOBAL_PATH + "vendor" + File.separator;
    public static final String ESHOW_GLOBAL_CUSTOMER_DIR = ESHOW_GLOBAL_APK_DIR + "customer" + File.separator;
    public static final String ESHOW_GLOBALAPK_DATA_DIR = ESHOW_GLOBALAPK_PATH + "data" + File.separator;
    public static final String ESHOW_GLOBALAPK_APK_DIR = ESHOW_GLOBALAPK_DATA_DIR + "apk" + File.separator;
    public static final String ESHOW_GLOBALAPK_CUSTOMER_DIR = ESHOW_GLOBALAPK_APK_DIR + "customer" + File.separator;
    public static final String ESHOW_GLOBALAPK_VIDEO_DIR = ESHOW_GLOBALAPK_CUSTOMER_DIR + "video" + File.separator;
    public static final String ESHOW_GLOBAL_APKT_CUSTOMER_DIR = ESHOW_GLOBAL_APKT_DIR + "customer" + File.separator;
	public static final String ESHOW_LOCAL_DATA_DIR = ESHOW_LOCAL_PATH + "data" + File.separator;
    public static final String ESHOW_LOCAL_APK_DIR = ESHOW_LOCAL_DATA_DIR + "apk" + File.separator;
    public static final String ESHOW_LOCAL_APKT_DIR = ESHOW_LOCAL_DATA_DIR + "apkt" + File.separator;
    public static final String ESHOW_LOCAL_FILE_DIR = ESHOW_LOCAL_DATA_DIR + "file" + File.separator;
    public static final String ESHOW_LOCAL_VENDOR_DIR = ESHOW_LOCAL_PATH + "vendor" + File.separator;
    public static final String ESHOW_LOCAL_CUSTOMER_DIR = ESHOW_LOCAL_APK_DIR + "customer" + File.separator;
    public static final String ESHOW_LOCAL_APK_TOOLS_DIR = ESHOW_LOCAL_APK_DIR + "tools" + File.separator;
    public static final String ESHOW_LOCAL_APKT_CUSTOMER_DIR = ESHOW_LOCAL_APKT_DIR + "customer" + File.separator;
    public static final String ESHOW_GLOBAL_ANIMATION_DIR = ESHOW_GLOBAL_PATH + "animation" + File.separator;
    public static final String ESHOW_LOCAL_ANIMATION_DIR = ESHOW_LOCAL_PATH + "animation" + File.separator;
    public static final String ESHOW_LOCAL_RES_DIR = ESHOW_LOCAL_PATH + "res" + File.separator;
    public static final String ESHOW_LOCAL_VIDEO_DIR = ESHOW_LOCAL_CUSTOMER_DIR + "video" + File.separator;
    public static final String ESHOW_LOCAL_BOOK_DIR = ESHOW_LOCAL_PATH + "book" + File.separator;
    public static final String ESHOW_TMP_DIR = ESHOW_ROOT_DIR + "tmp" + File.separator;
    public static final String PHSS_ROOT_DIR = XHX_STORAGE_DIR + "phms" + File.separator;
    public static final String PHSS_XML_DIR = PHSS_ROOT_DIR + "xml" + File.separator;
    public static final String PHSS_RES_DIR = PHSS_ROOT_DIR + "res" + File.separator;
    public static final String PHSS_RECORD_DIR = PHSS_ROOT_DIR + "record" + File.separator;
    public static final String PHSS_USERS_DIR = PHSS_ROOT_DIR + "users" + File.separator;
    public static final String EDAN_PATIENT_DIR = XHX_STORAGE_DIR + "fVirtue" + File.separator + "patient" + File.separator;
    public static final String DZQY_XY_DIR = PHSS_RES_DIR + "daxx" + File.separator + "dzqy" + File.separator; // 电子签约协议

    public static String APP_ROOT_NAME = "phms";

    /**
     * Android:xinhuaxing_storage根目录，挂载在/mnt/目录下
     * Windows:本地存储根目录，通过System.getenv("APPDATA")获取
     */
    public static String APP_ROOT_DIR = XHX_STORAGE_DIR + APP_ROOT_NAME + File.separator;
    public static String APP_XML_DIR = APP_ROOT_DIR + "xml" + File.separator;
    public static String APP_RES_DIR = APP_ROOT_DIR + "res" + File.separator;
    public static String APP_RECORD_DIR = APP_ROOT_DIR + "record" + File.separator;
    public static String APP_TEMPLATE_DIR = APP_ROOT_DIR + "template" + File.separator;
    /**
     * 数据库参数
     */
    public static final String DATABASE_FILE_PATH = APP_RECORD_DIR + "app.db";
    // / 数据库版本
    public static int DB_VERSION = 1;

    /**
     * 机器型号
     */
    public static final String RK3066_MODEL = "rk30sdk"; // RK3066
    public static final String A10_MODEL = "a10sdk"; // A10
    

    /*************************** 3.通用配置信息部分 end ************************************/

    /**
     * 广播
     */
    public static final String SILENT_INSTALL_SERVICE_ACTION = "net.xinhuaxing.action.SILENT_INSTALL"; // 开启静默安装服务action

    public static final String AUTO_CLEAR_SERVICE_ACTION = "net.xinhuaxing.action.AUTO_CLEAR"; // 开启自动清除服务action

    public static final String AUTO_SET_ANIMATION_SERVICE_ACTION = "net.xinhuaxing.action.AUTO_SET_ANIMATION"; // 开启自动设置动画服务action
    public static final String LAUNCH_APK_ACTION = "net.xinhuaxing.action.LAUNCH_APK"; // 启动APK的广播
    /**
     * 应用文件状态
     */
    public static int INSTALLED = 1; // 表示已经安装，且跟现在这个apk文件是一个版本
    public static int UNINSTALLED = 0; // 表示未安装
    public static int INSTALLED_UPDATE = 2; // 表示已经安装，版本比现在这个版本要低，可以点击按钮更新
    
    /**
     * Shell脚本路径
     */
    public static final String LOG_PATH = XHX_STORAGE_DIR + "xhxlog" + File.separator;

    public static final String DEFAULT_HTTP_URL = "http://xinhuaxing.net";
    
    public static final int FTPS_SERVER_PORT = 58466;
    public static final int FTP_SERVER_PORT = 58465;
    public static final String START_FTP_SERVER_ACTION = "net.xinhuaxing.action.START_FTP_SERVER";
    public static final String START_FTP_CLIENT_ACTION = "net.xinhuaxing.action.START_FTP_CLIENT";
    public static final String WIFI_LOCK_ACTION = "net.xinhuaxing.action.WIFI_LOCK";
//    public static final String FTP_USER = "ftp_eshow_user";
//    public static final String FTP_PASSWORD = "ftp_eshow_password";
    public static final String FTP_USER = "android";
    public static final String FTP_PASSWORD = "eshowgogo888";
    public static final String FTP_ROOT_DIR = "";
    public static final String FTP_GLOBAL_PATH = FTP_ROOT_DIR + "global" + File.separator;
    public static final String FTP_LOCAL_PATH = FTP_ROOT_DIR + "local" + File.separator;
    public static final String FTP_GLOBALAPK_PATH = FTP_ROOT_DIR + "global-apk" + File.separator;
    public static final String FTP_GLOBAL_SYS_CONFIG_FILE = FTP_GLOBAL_PATH + "sysConfig.xml";
    public static final String FTP_GLOBAL_RES_DIR = FTP_GLOBAL_PATH + "res" + File.separator;
    public static final String FTP_GLOBAL_DATA_DIR = FTP_GLOBAL_PATH + "data" + File.separator;
    public static final String FTP_GLOBAL_APK_DIR = FTP_GLOBAL_DATA_DIR + "apk" + File.separator;
    public static final String FTP_GLOBAL_APKT_DIR = FTP_GLOBAL_DATA_DIR + "apkt" + File.separator;
    public static final String FTP_GLOBAL_FILE_DIR = FTP_GLOBAL_DATA_DIR + "file" + File.separator;
    public static final String FTP_GLOBAL_VENDOR_DIR = FTP_GLOBAL_PATH + "vendor" + File.separator;
    public static final String FTP_GLOBAL_CUSTOMER_DIR = FTP_GLOBAL_APK_DIR + "customer" + File.separator;
    public static final String FTP_GLOBALAPK_DATA_DIR = FTP_GLOBALAPK_PATH + "data" + File.separator;
    public static final String FTP_GLOBALAPK_APK_DIR = FTP_GLOBALAPK_DATA_DIR + "apk" + File.separator;
    public static final String FTP_GLOBALAPK_CUSTOMER_DIR = FTP_GLOBALAPK_APK_DIR + "customer" + File.separator;
    public static final String FTP_GLOBALAPK_VIDEO_DIR = FTP_GLOBALAPK_CUSTOMER_DIR + "video" + File.separator;
    public static final String FTP_GLOBAL_APKT_CUSTOMER_DIR = FTP_GLOBAL_APKT_DIR + "customer" + File.separator;
	public static final String FTP_LOCAL_DATA_DIR = FTP_LOCAL_PATH + "data" + File.separator;
    public static final String FTP_LOCAL_APK_DIR = FTP_LOCAL_DATA_DIR + "apk" + File.separator;
    public static final String FTP_LOCAL_APKT_DIR = FTP_LOCAL_DATA_DIR + "apkt" + File.separator;
    public static final String FTP_LOCAL_FILE_DIR = FTP_LOCAL_DATA_DIR + "file" + File.separator;
    public static final String FTP_LOCAL_VENDOR_DIR = FTP_LOCAL_PATH + "vendor" + File.separator;
    public static final String FTP_LOCAL_CUSTOMER_DIR = FTP_LOCAL_APK_DIR + "customer" + File.separator;
    public static final String FTP_LOCAL_APK_TOOLS_DIR = FTP_LOCAL_APK_DIR + "tools" + File.separator;
    public static final String FTP_LOCAL_APKT_CUSTOMER_DIR = FTP_LOCAL_APKT_DIR + "customer" + File.separator;
    public static final String FTP_GLOBAL_ANIMATION_DIR = FTP_GLOBAL_PATH + "animation" + File.separator;
    public static final String FTP_LOCAL_ANIMATION_DIR = FTP_LOCAL_PATH + "animation" + File.separator;
    public static final String FTP_LOCAL_RES_DIR = FTP_LOCAL_PATH + "res" + File.separator;
    public static final String FTP_LOCAL_VIDEO_DIR = FTP_LOCAL_CUSTOMER_DIR + "video" + File.separator;
    public static final String FTP_LOCAL_BOOK_DIR = FTP_LOCAL_PATH + "book" + File.separator;

    public static final String ESHOW_WIFI_AP_ACCOUNT = "Xinhuaxing-eShow";
    public static final String ESHOW_WIFI_AP_PASSWORD = "66668888";
    
    public static final int SAVE_XML_INTERVAL = 300000;
    
    // 易秀资源
    public static final int ESHOW_RES_TYPE_UNKNOWN = 0;
    public static final int ESHOW_RES_TYPE_BOOK = 1 << 1;
    public static final int ESHOW_RES_TYPE_FILE = 1 << 2;
    public static final int ESHOW_RES_TYPE_IMAGE = 1 << 3;

    public static final int BOOK_FTP_SERVER_PORT = 58466;
    public static final String BOOK_FTP_USER = "eshow";
    public static final String BOOK_FTP_PASSWORD = "eshowbook58466";
    public static final String BOOK_FTP_ROOT_PATH = "";
    /**
     * 文件类型
     */
    public static final int FILE_TYPE_DOC = 0;
    public static final int FILE_TYPE_AVI = 1;
    public static final int FILE_TYPE_MP4 = 2;
    public static final int FILE_TYPE_PDF = 3;
    public static final int FILE_TYPE_PPT = 4;
    public static final int FILE_TYPE_XLS = 5;
    public static final int FILE_TYPE_MP3 = 6;
    public static final int FILE_TYPE_JPG = 7;
    public static final int FILE_TYPE_PNG = 8;

    public static final String ESHOW_EXTENSION_BOOK = "*.apk";
    public static final String ESHOW_EXTENSION_FILE = "*.mp4;*.avi;*.pdf;*.doc;*.xls;*.ppt;*.mp3;*.jpg;*.png";
    public static final String ESHOW_EXTENSION_IMAGE = "*.png;*.jpg";
    public static final String ESHOW_EXTENSION_TMP = "*.tmp";
    public static final String ESHOW_EXTENSION_ZIP = "*.zip";

    public static final String APK_PACKAGE_PREFIX = "net.xinhuaxing.app";

    public static final String ESHOWFILE_PREFIX = "eshowfile://";
    public static final String ESHOWLOCAL_PREFIX = "eshowlocal://";
    public static final String HTTP_PREFIX = "http://";
    public static final String TAG_PREFIX = "tag://";

    public static final String RESTRICTIVE_ESHOW_SYSTEM = "ESHOW_SYSTEM";
    public static final String RESTRICTIVE_TOOL_PART = "TOOL_PART";
    public static final String RESTRICTIVE_APP_PART = "APP_PART";
    public static final String RESTRICTIVE_SYSTEM_TOOL = "SYSTEM_TOOL";
    public static final String RESTRICTIVE_USER_FILE = "USER_FILE";

    // 替换对话框返回值
    public static final int REPLACE_NO = 0;
    public static final int REPLACE_NO_ALWAYS = 1;
    public static final int REPLACE_YES = 2;
    public static final int REPLACE_YES_ALWAYS = 3;

    public static final int INSTALL_SUCCESSED = 1;
    public static final int INSTALL_FAILED = 0;

    public static final boolean DEBUG = false;
    public static final String SYN_TYPE_HALF_HOUR = "half_hour";
    public static final String SYN_TYPE_TWO_HOURS = "two_hours";
    public static final String SYN_TYPE_MIDNIGHT = "midnight";
    public static final String SYN_TYPE_CHARGE_ON = "charge_on";
    public static final String SYN_TYPE_DEFAULT = SYN_TYPE_HALF_HOUR;
    
    public static final int MACHINE_STATUS_SLAVE = 0;
    public static final int MACHINE_STATUS_MASTER = 1;

    public static final String DEFAULT_IME_ID = "com.sohu.inputmethod.sogou/.SogouIME";
    
    public static final String MASTER_SLAVE_EXCHANGE_CMD = "88823318806123";
    
    public static final int PAGE_JBXX = 1;
    public static final int PAGE_JKTJ = 2;
    public static final int PAGE_SFGL = 3;
    public static final int PAGE_YCJH = 4;
    public static final int PAGE_JKJY = 5;
    public static final int PAGE_YYZD = 6;
    public static final int PAGE_SJGL = 7;
    public static final int PAGE_XTSZ = 8;

    public static final int JBXX_BODY_GRXX = 1;
    public static final int JBXX_BODY_BXLB = 2;
    public static final int JBXX_BODY_QYQK = 3;
    public static final int JBXX_BODY_BLS = 4;
    public static final int JBXX_BODY_GMS = 5;
    public static final int JBXX_BODY_JZS = 6;
    public static final int JBXX_BODY_JWS = 7;
    public static final int JBXX_BODY_YCBS = 8;
    public static final int JBXX_BODY_CJQK = 9;
    public static final int JBXX_BODY_JTXX = 10;
    public static final int JBXX_BODY_JTCY = 11;
    public static final int JBXX_BODY_JTWT = 12;
    public static final int JBXX_BODY_SHHJ = 13;

    public static final String BT_NAME_SPRT_SPRMTIIIBTA = "TIII Bluetooth Printer";
    public static final String BT_NAME_QS_BZ = "Qsprinter";  // Qsprinter
    public static final String BT_NAME_BMV2_BZ = "BMV2";  // BMV2
}
