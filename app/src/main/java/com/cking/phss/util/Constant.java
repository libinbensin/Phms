/* Cking Inc. (C) 2012. All rights reserved.
 *
 * Constant.java
 * classes : com.cking.phss.util.Constant
 * @author Wation Haliyoo
 * V 1.0.0
 * Create at 2012-9-23 下午03:25:01
 */
package com.cking.phss.util;


/**
 * com.cking.phss.util.Constant
 * 
 * @author Wation Haliyoo <br/>
 *         create at 2012-9-23 下午03:25:01
 */
public class Constant {
    private static final String TAG = "Constant";

    private static String CONFIG_CONTENT = "";// 普通配置文件的内容
    private static int theTimeGapTzpsAndXlcs = -1;// 心理测试和体质判识上传的间隔时间，从文件中读取
    private static String bridgeId = "";
    private static String deviceSn = "";
    private static String pfId = "";
    private static String ischoice="";//是否选择社区
    private static String hospname1="";//医院名称1
    private static String hospname2="";//医院名称2
    private static String rtcfPlatFormID="";//人体成分平台编号
    private static String rtcfBridgeId="";//人体成分数据基站ID
    private static String rtcfDeviceSn="";// 人体成分设备编号

    public static final String exterNalPath = "/mnt/sdcard";
    public static final String MODULE_XML_PATH = exterNalPath + "/wltlib/xml/module/";
    public static final String RES_YYZD_PATH = exterNalPath + "/phms/res/yyzd/";
    public static final String RES_JKJY_PATH = exterNalPath + "/phms/res/jkjy/";
    public static final String LOG_PATH = exterNalPath + "/phms/log/";
    public static final String PROFILE_PATH = exterNalPath + "/phms/profile/";
    public static final String ADDR_PATH = exterNalPath + "/phms/addr/";
    public static String WEBURL_PATH=exterNalPath + "/phms/url/WebServiceUrl.txt";
    /**
     * 网络请求ID，必须和XML中一致
     */
    public static final int HTTP_ID_LOGIN = 1;
}
