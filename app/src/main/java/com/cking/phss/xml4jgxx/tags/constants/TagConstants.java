/* Cking Inc. (C) 2013. All rights reserved.
 *
 * TagConstants.java
 * classes : net.xinhuaxing.eshow.util.xml.tags.constants.TagConstants
 * @author wation
 * V 1.0.0
 * Create at 2013-10-22 涓婂崍11:19:49
 */
package com.cking.phss.xml4jgxx.tags.constants;

/**
 * net.xinhuaxing.eshow.util.xml.tags.constants.TagConstants
 * @author wation <br/>
 * create at 2013-10-22 上午11:19:49
 */
public class TagConstants {
    private static final String TAG = "TagConstants";

    public static final String XML_NAMESPACE = "";

    public static final String XML_ATTR_ID = "id";
    public static final String XML_ATTR_NAME = "name";
    public static final String XML_ATTR_TYPE = "type";
    public static final String XML_ATTR_BRAND = "brand";
    public static final String XML_ATTR_MODEL = "model";
    public static final String XML_ATTR_SERIALNO = "serialno";
    public static final String XML_ATTR_WEBSERVICEURL = "webserviceurl";
    public static final String XML_ATTR_UPLOADKSTJURL = "uploadkstjurl";
    public static final String XML_ATTR_DOWNLOADKSTJURL = "downloadkstjurl";
    public static final String XML_ATTR_VERSIONSERVICEURL = "versionserviceurl";
    public static final String XML_ATTR_DATAVERSIONURL = "dataversionurl";
    public static final String XML_ATTR_PRINTHEADER = "printheader";
    public static final String XML_ATTR_PRINTFOOTER = "printfooter";
    public static final String XML_ATTR_BRIDGEID = "bridgeid";
    public static final String XML_ATTR_STATUS = "status";

    public static final String XML_VALUE_NAME_PBDN = "平板电脑";
    public static final String XML_VALUE_NAME_SFZYDQ = "身份证阅读器";
    public static final String XML_VALUE_NAME_SPDKQ = "射频读卡器";
    public static final String XML_VALUE_NAME_XYJ = "血压计";
    public static final String XML_VALUE_NAME_XTY = "血糖仪";
    public static final String XML_VALUE_NAME_XZY = "血脂仪";
    public static final String XML_VALUE_NAME_DZYWC = "电子腰围尺";
    public static final String XML_VALUE_NAME_XYY = "血氧仪";
    public static final String XML_VALUE_NAME_RTCFY = "人体成分仪";
    public static final String XML_VALUE_NAME_XDTJ = "心电图机";
    public static final String XML_VALUE_NAME_RMDYJ = "热敏打印机";
    public static final String XML_VALUE_NAME_TWJ = "体温计";

//    <?xml version="1.0" encoding="utf-8"?>
//    <!-- 请先德完成此文档内容，并填写每个测试项目可选的设备 -->
//    <!-- serialno:机构代码，name:机构名称 -->
//    <ConfigTag
//        serialno="2354489"
//        name="上海先德医疗设备有限公司">
//        <!-- id:序号，name:仪器名称， type:仪器类型，brand:品牌，model:型号，serialno:编号 -->
//        <DeviceInfoTag
//            id="1"
//            name="平板电脑"
//            type="硬件平台"
//            brand="三星"
//            model="P739"
//            serialno="55245751">
//        </DeviceInfoTag>
//        <DeviceInfoTag
//            id="2"
//            name="身份证阅读器"
//            type="身份识别"
//            brand="华视电子"
//            model="CVR-100B"
//            serialno="12349599112">
//        </DeviceInfoTag>
//        <DeviceInfoTag
//            id="3"
//            name="射频读卡器"
//            type="身份识别"
//            brand="HANDE"
//            model="发卡器"
//            serialno="4201122">
//        </DeviceInfoTag>
//        <DeviceInfoTag
//            id="4"
//            name="血压计"
//            type="血压测量"
//            brand="AND"
//            model="767" 
//            serialno="5441111223">
//        </DeviceInfoTag>
//        <DeviceInfoTag
//            id="5"
//            name="血压计"
//            type="血压测量"
//            brand="microlife"
//            model="BP-3M"   
//            serialno="5441111223">
//        </DeviceInfoTag>
//            <DeviceInfoTag
//            id="6"
//            name="血压计"
//            type="血压测量"
//            brand="LifeSense"
//            model="LS"  
//            serialno="544111121">
//        </DeviceInfoTag>
//        <DeviceInfoTag
//            id="7"
//            name="血糖仪"
//            type="血糖检测"
//            brand="百捷"
//            model="PGD-1"
//            serialno="21DSFD">
//        </DeviceInfoTag>    
//        <DeviceInfoTag
//            id="9"
//            name="血糖仪"
//            type="血糖检测"
//            brand="卡迪克"
//            model="Cardiocheck"
//            serialno="51232124">
//        </DeviceInfoTag>
//        <DeviceInfoTag
//            id="8"
//            name="血糖仪"
//            type="血糖检测"
//            brand="其他"
//            model="标准"
//            serialno="21DSFD">
//        </DeviceInfoTag>    
//        <DeviceInfoTag
//            id="9"
//            name="血脂仪"
//            type="血脂检测"
//            brand="卡迪克"
//            model="Cardiocheck"
//            serialno="51232124">
//        </DeviceInfoTag>
//        <DeviceInfoTag
//            id="10"
//            name="血脂仪"
//            type="血脂检测"
//            brand="健康在线"
//            model="BU-34"
//            serialno="21DSFD">
//        </DeviceInfoTag>
//            <DeviceInfoTag
//            id="7"
//            name="血脂仪"
//            type="血脂检测"
//            brand="百捷"
//            model="PGD-1"
//            serialno="21DSFD">
//        </DeviceInfoTag>
//        <DeviceInfoTag
//            id="11"
//            name="电子腰围尺"
//            type="三围测量"
//            brand="乐心"
//            model="LS501"
//            serialno="23123144">
//        </DeviceInfoTag>
//        <DeviceInfoTag
//            id="12"
//            name="血氧仪"
//            type="血氧检测"
//            brand="Beirui"
//            model="标准"  
//            serialno="77612">
//        </DeviceInfoTag>
//        <DeviceInfoTag
//            id="13"
//            name="血氧仪"
//            type="血氧检测"
//            brand="KangShang"
//            model="标准"  
//            serialno="77612">
//        </DeviceInfoTag>
//        <DeviceInfoTag
//            id="14"
//            name="血氧仪"
//            type="血氧检测"
//            brand="其他"
//            model="标准"  
//            serialno="77612">
//        </DeviceInfoTag>
//        <DeviceInfoTag
//            id="15"
//            name="人体成分仪"
//            type="人体成分分析"
//            brand="乐心"
//            model="标准"
//            serialno="88281329">
//        </DeviceInfoTag>
//        <DeviceInfoTag
//            id="16"
//            name="心电图机"
//            type="心电分析"
//            brand="理邦"
//            model="SE-1010"
//            serialno="11233">
//        </DeviceInfoTag>
//        <DeviceInfoTag
//            id="17"
//            name="热敏打印机"
//            type="热敏打印"
//            brand="SPRT"
//            model="SP-RMTIII BTA"
//            serialno="241521">
//        </DeviceInfoTag>
//        <DeviceInfoTag
//            id="18"
//            name="热敏打印机"
//            type="热敏打印"
//            brand="QS"
//            model="标准"
//            serialno="241521">
//        </DeviceInfoTag>
//            <DeviceInfoTag
//            id="19"
//            name="热敏打印机"
//            type="热敏打印"
//            brand="其他"
//            model="标准"
//            serialno="241521">
//        </DeviceInfoTag>
//    </ConfigTag>
//    public static final String XML_VALUE_ID_1 = "1";
//    public static final String XML_VALUE_ID_2 = "2";
//    public static final String XML_VALUE_ID_3 = "3";
//    public static final String XML_VALUE_ID_4 = "4";
//    public static final String XML_VALUE_ID_5 = "5";
//    public static final String XML_VALUE_ID_6 = "6";
//    public static final String XML_VALUE_ID_7 = "7";
//    public static final String XML_VALUE_ID_8 = "8";
//    public static final String XML_VALUE_ID_9 = "9";
//    public static final String XML_VALUE_ID_10 = "10";
//    public static final String XML_VALUE_ID_11 = "11";
//    public static final String XML_VALUE_ID_12 = "12";
//    public static final String XML_VALUE_ID_13 = "13";
//    public static final String XML_VALUE_ID_14 = "14";
//    public static final String XML_VALUE_ID_15 = "15";
//    public static final String XML_VALUE_ID_16 = "16";
//    public static final String XML_VALUE_ID_17 = "17";
//    public static final String XML_VALUE_ID_18 = "18";
//    public static final String XML_VALUE_ID_19 = "19";
    public static final int XML_VALUE_ID_SX_P739 = 1;
    public static final int XML_VALUE_ID_HSDZ_CVR100B = 2;
    public static final int XML_VALUE_ID_HANDE_FKQ = 3;
    public static final int XML_VALUE_ID_AND_767 = 4;
    public static final int XML_VALUE_ID_MICROLIFE_BP3M = 5;
    public static final int XML_VALUE_ID_LIFESENSE_LS = 6;
    public static final int XML_VALUE_ID_BJ_PGD1 = 7;
    public static final int XML_VALUE_ID_QT_BZ = 8;
    public static final int XML_VALUE_ID_KDK_CARDIOCHEK = 9;
    public static final int XML_VALUE_ID_JKZX_BU34 = 10;
    public static final int XML_VALUE_ID_LX_LS501 = 11;
    public static final int XML_VALUE_ID_BEIRUI_BZ = 12;
    public static final int XML_VALUE_ID_KANGSHENG_BZ = 13;
    public static final int XML_VALUE_ID_QT_BZ2 = 14;
    public static final int XML_VALUE_ID_LX_BZ = 15;
    public static final int XML_VALUE_ID_LB_SE1010 = 16;
    public static final int XML_VALUE_ID_SPRT_SPRMTIIIBTA = 17;
    public static final int XML_VALUE_ID_QS_BZ = 18;
    public static final int XML_VALUE_ID_QT_BZ3 = 19;
}
