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

/**
 * com.cking.phss.xml.bean.Province
 * @author Administrator <br/>
 * create at 2012-9-22 下午10:56:53
 */
public class District implements ITag {
    public static final String TAG = "District";
    
    //<!--必填。此社区卫生服务中心所在区县，值：区县名，ID：区县ID或代码 -->
    public String mDistrict = null;
    public String mID = null;

    public void writeTag(XmlSerializer ser) throws IllegalArgumentException, IllegalStateException,
            IOException {
        ser.startTag("", TAG);
        if (mID != null) {
            ser.attribute("", "ID", mID);
        }
        if (mDistrict != null) {
            ser.text(mDistrict + "");
        }
        ser.endTag("", TAG);
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
                    mDistrict = parser.nextText();
                    return true;
                }
                break;
            case XmlPullParser.END_TAG:
                break;

        }
        return false;
    }
}
