/* Cking Inc. (C) 2012. All rights reserved.
 *
 * Zone.java
 * classes : com.cking.phss.xml.bean.Zone
 * @author Administrator
 * V 1.0.0
 * Create at 2012-9-22 下午10:53:17
 */
package com.cking.phss.xml.bean;

import java.io.IOException;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import com.cking.phss.xml.util.TagUtil;

/**
 * com.cking.phss.xml.bean.Zone
 * @author Administrator <br/>
 * create at 2012-9-22 下午10:53:17
 */
public class Zone implements ITag {
    public static final String TAG = "Zone";

    // <!--此用户可以管辖哪些社区（村）或者居委会 -->
    // <!--社区（村）或者居委会，值：名称；ID：相应代码 -->
    public String mZone = null;
    public String mID = null;

    public void writeTag(XmlSerializer ser) throws IllegalArgumentException, IllegalStateException,
            IOException {
        TagUtil.writeTag(ser, mID, "ID", mZone, TAG);
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
                    mZone = parser.nextText();
                    return true;
                }
                break;
            case XmlPullParser.END_TAG:
                break;

        }
        return false;
    }
}
