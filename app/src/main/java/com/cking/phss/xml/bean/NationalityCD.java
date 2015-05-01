/* Cking Inc. (C) 2012. All rights reserved.
 *
 * NationalityCD.java
 * classes : com.cking.phss.xml.bean.NationalityCD
 * @author Administrator
 * V 1.0.0
 * Create at 2012-9-23 上午12:38:29
 */
package com.cking.phss.xml.bean;

import java.io.IOException;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import com.cking.phss.xml.util.TagUtil;

/**
 * com.cking.phss.xml.bean.NationalityCD
 * @author Administrator <br/>
 * create at 2012-9-23 上午12:38:29
 */
public class NationalityCD implements ITag {
    public static final String TAG = "NationalityCD";

    // <!--成功：有多条记录，按NationalityCD升序。值：国家名称，ID：ID或代码 -->
    public String mNationalityCD = null;
    public String mID = null;

    public void writeTag(XmlSerializer ser) throws IllegalArgumentException, IllegalStateException,
            IOException {
        TagUtil.writeTag(ser, mID, "ID", mNationalityCD, TAG);
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
                    mNationalityCD = parser.nextText();
                    return true;
                }
                break;
            case XmlPullParser.END_TAG:
                break;

        }
        return false;
    }
}
