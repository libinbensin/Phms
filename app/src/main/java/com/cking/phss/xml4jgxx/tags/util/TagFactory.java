/* Cking Inc. (C) 2013. All rights reserved.
 *
 * TagFactory.java
 * classes : net.xinhuaxing.eshow.util.xml.tags.util.TagFactory
 * @author wation
 * V 1.0.0
 * Create at 2013-10-22 涓婂崍10:37:59
 */
package com.cking.phss.xml4jgxx.tags.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import net.xinhuaxing.interfaces.ITag;

import org.xmlpull.v1.XmlSerializer;

import android.util.Xml;

import com.cking.phss.xml4jgxx.tags.ConfigTag;
import com.cking.phss.xml4jgxx.tags.DeviceInfoTag;
import com.cking.phss.xml4jgxx.tags.HospitalTag;
import com.cking.phss.xml4jgxx.tags.ListDeviceInfoTag;
import com.cking.phss.xml4jgxx.tags.SelectHospitalTag;

/**
 * net.xinhuaxing.eshow.util.xml.tags.util.TagFactory
 * @author wation <br/>
 * create at 2013-10-22 上午10:37:59
 */
public class TagFactory {
    private static final String TAG = "TagFactory";

    /**
     * 根据标签名获取标签实例
     * @param attr
     * @return
     */
    public static ITag getNewInstanceOfTag(String tag) {
        ITag tagClazz = null;
        String className = convertTagToClassName(tag);
        try {
            tagClazz = (ITag) Class.forName(className).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tagClazz;
    }

    public static String print(ITag rootTag) throws IllegalArgumentException, IllegalStateException, IOException {
        XmlSerializer ser = Xml.newSerializer();
        ByteArrayOutputStream packet = new ByteArrayOutputStream(512);
        ser.setOutput(packet, "UTF-8");
        ser.startDocument("UTF-8", true);

        // 写Xml，包括所有子标签
        rootTag.write(ser);
        
        ser.endDocument();
        
        return packet.toString();
    }
    
    private static String convertTagToClassName(String tag) {
        if (tag.equals(ConfigTag.XML_TAG)) {
            return ConfigTag.class.getName();
        } else if (tag.equals(DeviceInfoTag.XML_TAG)) {
            return DeviceInfoTag.class.getName();
        } else if (tag.equals(HospitalTag.XML_TAG)) {
            return HospitalTag.class.getName();
        } else if (tag.equals(SelectHospitalTag.XML_TAG)) {
            return SelectHospitalTag.class.getName();
        } else if (tag.equals(ListDeviceInfoTag.XML_TAG)) {
            return ListDeviceInfoTag.class.getName();
        }
        
        return null;
    }
    
    public static String convertClassNameToTag(String className) {
        // 如果类名中包含包名，则去掉包名
        if (className.contains(getTagClassPackage())) {
            className = className.substring(getTagClassPackage().length() + 1);
        }
        // 把标签名首字母转换成大写即变成类名
        String tag = className.replace(className.substring(0, 1), className.substring(0, 1).toLowerCase());
        tag = tag.substring(0, tag.length() - "Tag".length());
//        Log.i(TAG, "tag:" + tag + ", className:" + className);
        
        return tag;
    }
    
    private static String getTagClassPackage() {
        String clazzPath = TagFactory.class.getName();
        // Tag路径是当前包的上一级
        String[] packageItem = clazzPath.split("[.]");
        String tagPath = packageItem[0];
        for (int i = 1; i < packageItem.length - 2; i++) {
            tagPath += "." + packageItem[i];
        }
        return tagPath;
    }
}
