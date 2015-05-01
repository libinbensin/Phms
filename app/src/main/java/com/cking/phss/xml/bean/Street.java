/* Cking Inc. (C) 2012. All rights reserved.
 *
 * Province.java
 * classes : com.cking.phss.xml.bean.Province
 * @author Administrator
 * V 1.0.0
 * Create at 2012-9-22 下午10:56:53
 */
package com.cking.phss.xml.bean;

import java.io.IOException;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import com.cking.phss.xml.util.TagUtil;

/**
 * com.cking.phss.xml.bean.Province
 * @author Administrator <br/>
 * create at 2012-9-22 下午10:56:53
 */
public class Street implements ITag {
    public static final String TAG = "Street";
    
    //<!--必填。此社区卫生服务中心所在街道或镇，值：街道名，ID：街道ID或代码 -->
    public String mStreet = null;
    public String mID = null;

    public void writeTag(XmlSerializer ser) throws IllegalArgumentException, IllegalStateException,
            IOException {
        TagUtil.writeTag(ser, mID, "ID", mStreet, TAG);
    }

    /* (non-Javadoc)
     * @see com.cking.phss.xml.bean.ITag#read(java.lang.String, java.lang.String)
     */
    @Override
    public boolean read(XmlPullParser parser, int eventType) throws XmlPullParserException, IOException {

        String tag = parser.getName();
        switch (eventType) {
            case XmlPullParser.START_TAG:
                if (tag.equals(TAG)) {
                    mID = parser.getAttributeValue(0);
                    mStreet = parser.nextText();
                    return true;
                }
                break;
            case XmlPullParser.END_TAG:
                break;

        }
        return false;
    }
}
