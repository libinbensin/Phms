/* Cking Inc. (C) 2012. All rights reserved.
 *
 * TagUtil.java
 * classes : com.cking.phss.xml.util.TagUtil
 * @author Administrator
 * V 1.0.0
 * Create at 2012-9-23 上午12:26:39
 */
package com.cking.phss.xml.util;

import java.io.IOException;
import java.util.List;

import org.xmlpull.v1.XmlSerializer;

import com.cking.phss.xml.bean.ITag;

/**
 * com.cking.phss.xml.util.TagUtil
 * @author Administrator <br/>
 * create at 2012-9-23 上午12:26:39
 */
public class TagUtil {
    private static final String TAG = "TagUtil";

    /**
     * @param ser
     * @param value
     * @param tag
     * @throws IOException 
     * @throws IllegalStateException 
     * @throws IllegalArgumentException 
     */
    public static void writeTag(XmlSerializer ser, String value, String tag) throws IllegalArgumentException, IllegalStateException, IOException {
        if (value != null) {
            ser.startTag("", tag);
            ser.text(value + "");
            ser.endTag("", tag);
        }
    }

    public static void writeTag(XmlSerializer ser, String attr, String attrTag, String value,
            String tag) throws IllegalArgumentException, IllegalStateException, IOException {
        ser.startTag("", tag);
        if (attr != null) {
            ser.attribute("", attrTag, attr);
        }
        if (value != null) {
            ser.text(value + "");
        }
        ser.endTag("", tag);
    }

    public static void writeTag(XmlSerializer ser, String[] attrs, String[] attrsTag, String value,
            String tag) throws IllegalArgumentException, IllegalStateException, IOException {
        ser.startTag("", tag);
        for (int i = 0; i < attrs.length; i++) {
            if (attrs[i] != null) {
                ser.attribute("", attrsTag[i], attrs[i]);
            }
        }
        
        if (value != null) {
            ser.text(value + "");
        }
        ser.endTag("", tag);
    }
//    
//    public static void autoNewTag(List<ITag> tags, String tag, String[] refTags, ITag item) {
//
//        for (int i=0; i<refTags.length; i++) {
//            if (tag.equals(refTags[i])) {
//                //item = new (item.getClass())ITag();
//                tags.add(item);
//            }
//        }
//    }
//
//    /**
//     * @param tags
//     * @param tag2
//     * @param tagList
//     * @param objList
//     */
    public static void autoNewTag(List<ITag> tags, String tag, String[] tagList, ITag[] objList, ITag[] relList) {
        for (int i=0; i<tagList.length; i++) {
            if (tag.equals(tagList[i])) {
                objList[i] = relList[i];
                tags.add(objList[i]);
            }
        }
    }
}
