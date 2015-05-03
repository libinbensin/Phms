/* Xinhuaxing Inc. (C) 2014. All rights reserved.
 *
 * DeviceUseFactory.java
 * classes : com.cking.phss.util.DeviceUseFactory
 * @author Wation.Haliyoo
 * V 1.0.0
 * Create at 2014-9-16 下午1:54:02
 */
package com.cking.phss.util;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import com.cking.application.MyApplication;
import com.cking.phss.bean.BeanCD;
import com.cking.phss.dto.BcjmjbxxhjtxxJ003;
import com.cking.phss.dto.Bcjmjkxx8_2;
import com.cking.phss.dto.Bcjmxwxg8_1;
import com.cking.phss.dto.BcsfzzpJ002;
import com.cking.phss.dto.BcxlhetzJ004;
import com.cking.phss.dto.Dzjmjkgms;
import com.cking.phss.dto.Dzjmjkjws;
import com.cking.phss.dto.JmjkxxbcJ007;
import com.cking.phss.dto.daxx.BcqyqkHrs01;
import com.cking.phss.dto.innner.DeviceUse;
import com.cking.phss.dto.sfgl.gxy.Bcgxysfjl16;
import com.cking.phss.dto.sfgl.lnsf.BclnrglkHfe01;
import com.cking.phss.dto.sfgl.tnb.Bctnbsfjl23;
import com.cking.phss.xml4jgxx.tags.DeviceInfoTag;
import com.cking.phss.xml4jgxx.tags.constants.TagConstants;

/**
 * com.cking.phss.util.DeviceUseFactory
 * @author Wation.Haliyoo <br/>
 * create at 2014-9-16 下午1:54:02
 */
public class DeviceUseFactory {
    private static final String TAG = "DeviceUseFactory";

    public static List<DeviceUse> getDtoDeviceUses(Class clazz) {
        if (clazz.equals(BcjmjbxxhjtxxJ003.Request.class)
                || clazz.equals(BcsfzzpJ002.Request.class)
                || clazz.equals(JmjkxxbcJ007.Request.class)
                || clazz.equals(Dzjmjkjws.Request.class) || clazz.equals(Dzjmjkgms.Request.class)
                || clazz.equals(Bcjmxwxg8_1.Request.class)
                || clazz.equals(Bcjmxwxg8_1.Request.class)
                || clazz.equals(Bcjmjkxx8_2.Request.class)
                || clazz.equals(BcqyqkHrs01.Request.class)) { // 0 1 10
            return assembleDeviceUsesValue(new int[] { 0, 1, 10 });
        } else if (clazz.equals(BcxlhetzJ004.Request.class)) { // 0 1 10
            return assembleDeviceUsesValue(new int[] { 0, 1, 10 });
        } else if (clazz.equals(BclnrglkHfe01.Request.class)) { // 0 1 10
            return assembleDeviceUsesValue(new int[] { 0, 1, 10 });
        } else if (clazz.equals(Bcgxysfjl16.Request.class)) { // 0 1 10 2
            return assembleDeviceUsesValue(new int[] { 0, 1, 10, 2 });
        } else if (clazz.equals(Bctnbsfjl23.Request.class)) { // 0 1 10 2 3
            return assembleDeviceUsesValue(new int[] { 0, 1, 10, 2, 3 });
        }

        return null;
    }

    public static String getKstjDeviceUses(String driverId) {
        if (driverId.equals("01")) { // 0 1 10 99
            return assembleDeviceUsesXml(new int[] { 0, 1, 10, 99 });
        } else if (driverId.equals("02")) { // 0 1 10 2
            return assembleDeviceUsesXml(new int[] { 0, 1, 10, 2 });
        } else if (driverId.equals("03")) { // 0 1 10 3
            return assembleDeviceUsesXml(new int[] { 0, 1, 10, 3 });
        } else if (driverId.equals("08")) { // 0 1 10 6
            return assembleDeviceUsesXml(new int[] { 0, 1, 10, 6 });
        } else if (driverId.equals("10")) { // 0 1 10 8
            return assembleDeviceUsesXml(new int[] { 0, 1, 10, 8 });
        } else if (driverId.equals("12")) { // 0 1 10 99
            return assembleDeviceUsesXml(new int[] { 0, 1, 10, 99 });
        } else if (driverId.equals("16")) { // 0 1 10 4
            return assembleDeviceUsesXml(new int[] { 0, 1, 10, 4 });
        } else if (driverId.equals("17")) { // 0 1 10 4
            return assembleDeviceUsesXml(new int[] { 0, 1, 10, 4 });
        } else if (driverId.equals("18")) { // 0 1 10 5
            return assembleDeviceUsesXml(new int[] { 0, 1, 10, 5 });
        } else if (driverId.equals("19")) { // 0 1 10 99
            return assembleDeviceUsesXml(new int[] { 0, 1, 10, 99 });
        } else if (driverId.equals("20")) { // 0 1 10 9
            return assembleDeviceUsesXml(new int[] { 0, 1, 10, 9 });
        } else if (driverId.equals("21")) { // 0 1 10 9
            return assembleDeviceUsesXml(new int[] { 0, 1, 10, 9 });
        } else if (driverId.equals("24")) { // 0 1 10 9
            return assembleDeviceUsesXml(new int[] { 0, 1, 10, 9 });
        } else if (driverId.equals("25")) { // 0 1 10 7
            return assembleDeviceUsesXml(new int[] { 0, 1, 10, 7 });
        } else if (driverId.equals("26")) { // 0 1 10 9
            return assembleDeviceUsesXml(new int[] { 0, 1, 10, 9 });
        }
        return null;
    }

    /**
     * @param is
     * @return
     */
    private static List<DeviceUse> assembleDeviceUsesValue(int[] is) {
        Context mContext = MyApplication.getInstance();
        DeviceInfoTag[] deviceInfoTags = new DeviceInfoTag[] {
                // 所使用的软件
                null,
                // 使用软件的设备
                JgxxConfigFactory.findDeviceInfoTagByName(mContext,
                        TagConstants.XML_VALUE_NAME_PBDN),
                // 高血压检测设备
                JgxxConfigFactory
                        .findDeviceInfoTagByName(mContext, TagConstants.XML_VALUE_NAME_XYJ),
                // 糖尿病检测设备
                JgxxConfigFactory
                        .findDeviceInfoTagByName(mContext, TagConstants.XML_VALUE_NAME_XTY),
                // 血脂检测设备
                JgxxConfigFactory
                        .findDeviceInfoTagByName(mContext, TagConstants.XML_VALUE_NAME_XZY),
                // 腰围尺设备
                JgxxConfigFactory.findDeviceInfoTagByName(mContext,
                        TagConstants.XML_VALUE_NAME_DZYWC),
                // 人体成分
                JgxxConfigFactory.findDeviceInfoTagByName(mContext,
                        TagConstants.XML_VALUE_NAME_RTCFY),
                // 血氧检测设备
                JgxxConfigFactory
                        .findDeviceInfoTagByName(mContext, TagConstants.XML_VALUE_NAME_XYY),
                // 心电检测设备
                JgxxConfigFactory.findDeviceInfoTagByName(mContext,
                        TagConstants.XML_VALUE_NAME_XDTJ),
                // 体液检测设备
                null,
                // 身份识别设备
                JgxxConfigFactory.findDeviceInfoTagByName(mContext,
                        TagConstants.XML_VALUE_NAME_SFZYDQ),
                // 其他
                null };

        String[][] datas = new String[12][11];
        datas[0] = new String[] { "0", "所使用的软件", "", "PHMS", "", "", "",
                CommonUtil.getVersion(MyApplication.getInstance()), "", "1", "随访包" };
        datas[9] = new String[] { "9", "体液检测设备", "", "", "", "", "", "", "", "1", "随访包" };
        datas[11] = new String[] { "99", "其他", "", "", "", "", "", "", "", "1", "随访包" };
        for (int i = 0; i < 12; i++) {
            if (deviceInfoTags[i] != null) {
                datas[i] = new String[] { "1", "使用软件的设备", deviceInfoTags[i].attrBean.getId(),
                        deviceInfoTags[i].attrBean.getName(), "",
                        deviceInfoTags[i].attrBean.getBrand(), "",
                        deviceInfoTags[i].attrBean.getModel(),
                        deviceInfoTags[i].attrBean.getSerialNo(), "1", "随访包" };
            }
        }
        List<DeviceUse> dus = new ArrayList<DeviceUse>();
        for (int n : is) {
            DeviceUse du = new DeviceUse();
            for (int i = 0; i < 12; i++) {
                if (datas[i][0] != null && datas[i][0].equals(n + "")) {
                    int j = 0;
                    // @XmlAttribute(name = "!-- 数据来源类型
                    du.dataType = new BeanCD(datas[i][j++], datas[i][j++]);
                    du.device = new BeanCD(datas[i][j++], datas[i][j++]);
                    // @XmlAttribute(name = "!--所使用的设备的品牌或厂商-->
                    du.deviceBrand = new BeanCD(datas[i][j++], datas[i][j++]);
                    // @XmlAttribute(name = "!--所使用的设备的型号-->
                    du.deviceType = new BeanCD(datas[i][j++], datas[i][j++]);
                    // @XmlAttribute(name = "!-- 所使用的设备序列号（如：padA0006788） -->
                    du.deviceSn = datas[i][j++];
                    // @XmlAttribute(name = "!--
                    // 所属先德系列，CD：ID或代码；1.随访包,2.随访箱,3.健康小屋 -->
                    du.handeType = new BeanCD(datas[i][j++], datas[i][j++]);
                    dus.add(du);
                    break;
                }
            }
        }
        return dus;
    }

    /**
     * @param is
     * @return
     */
    private static String assembleDeviceUsesXml(int[] is) {
        Context mContext = MyApplication.getInstance();
        DeviceInfoTag[] deviceInfoTags = new DeviceInfoTag[] {
                // 所使用的软件
                null,
                // 使用软件的设备
                JgxxConfigFactory.findDeviceInfoTagByName(mContext,
                        TagConstants.XML_VALUE_NAME_PBDN),
                // 高血压检测设备
                JgxxConfigFactory
                        .findDeviceInfoTagByName(mContext, TagConstants.XML_VALUE_NAME_XYJ),
                // 糖尿病检测设备
                JgxxConfigFactory
                        .findDeviceInfoTagByName(mContext, TagConstants.XML_VALUE_NAME_XTY),
                // 血脂检测设备
                JgxxConfigFactory
                        .findDeviceInfoTagByName(mContext, TagConstants.XML_VALUE_NAME_XZY),
                // 腰围尺设备
                JgxxConfigFactory.findDeviceInfoTagByName(mContext,
                        TagConstants.XML_VALUE_NAME_DZYWC),
                // 人体成分
                JgxxConfigFactory.findDeviceInfoTagByName(mContext,
                        TagConstants.XML_VALUE_NAME_RTCFY),
                // 血氧检测设备
                JgxxConfigFactory
                        .findDeviceInfoTagByName(mContext, TagConstants.XML_VALUE_NAME_XYY),
                // 心电检测设备
                JgxxConfigFactory.findDeviceInfoTagByName(mContext,
                        TagConstants.XML_VALUE_NAME_XDTJ),
                // 体液检测设备
                null,
                // 身份识别设备
                JgxxConfigFactory.findDeviceInfoTagByName(mContext,
                        TagConstants.XML_VALUE_NAME_SFZYDQ),
                // 其他
                null };
        String format = "<DeviceUse>" + "<DataType CD='%s'>%s</DataType>"
                + "<Device CD='%s'>%s</Device>" + "<DeviceBrand CD='%s'>%s</DeviceBrand>"
                + "<DeviceType CD='%s'>%s</DeviceType>" + "<DeviceSn>%s</DeviceSn>"
                + "<HandeType CD='%s'>%s</HandeType>" + "</DeviceUse>";

        String[][] datas = new String[12][11];
        datas[0] = new String[] { "0", "所使用的软件", "", "PHMS", "", "", "",
                CommonUtil.getVersion(MyApplication.getInstance()), "", "1", "随访包" };
        datas[9] = new String[] { "9", "体液检测设备", "", "", "", "", "", "", "", "1", "随访包" };
        datas[11] = new String[] { "99", "其他", "", "", "", "", "", "", "", "1", "随访包" };
        for (int i = 0; i < 12; i++) {
            if (deviceInfoTags[i] != null) {
                datas[i] = new String[] { "1", "使用软件的设备", deviceInfoTags[i].attrBean.getId(),
                        deviceInfoTags[i].attrBean.getName(), "",
                        deviceInfoTags[i].attrBean.getBrand(), "",
                        deviceInfoTags[i].attrBean.getModel(),
                        deviceInfoTags[i].attrBean.getSerialNo(), "1", "随访包" };
            }
        }
        String t = "";
        for (int n : is) {
            for (int i = 0; i < 12; i++) {
                if (datas[i][0] != null && datas[i][0].equals(n + "")) {
                    int j = 0;
                    t += String.format(format, datas[i][j++], datas[i][j++], datas[i][j++],
                            datas[i][j++], datas[i][j++], datas[i][j++], datas[i][j++],
                            datas[i][j++], datas[i][j++], datas[i][j++], datas[i][j++]);
                    break;
                }
            }
        }
        return t;
    }
}
