/* Cking Inc. (C) 2012. All rights reserved.
 *
 * UserN.java
 * classes : com.cking.phss.xml.bean.UserN
 * @author Administrator
 * V 1.0.0
 * Create at 2012-9-22 下午11:59:07
 */
package com.cking.phss.xml.bean;

import java.io.IOException;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import com.cking.phss.xml.util.TagUtil;

/**
 * com.cking.phss.xml.bean.UserN
 * @author Administrator <br/>
 * create at 2012-9-22 下午11:59:07
 */
public class UserN implements ITag {
    public static final String TAG = "UserN";

    public String mUserN = null;

    public void writeTag(XmlSerializer ser) throws IllegalArgumentException, IllegalStateException,
            IOException {
        TagUtil.writeTag(ser, mUserN, TAG);
    }

    /* (non-Javadoc)
     * @see com.cking.phss.xml.bean.ITag#read(org.xml.sax.Attributes, java.lang.String)
     */
    @Override
    public boolean read(XmlPullParser parser, int eventType) throws XmlPullParserException, IOException {

        String tag = parser.getName();
        switch (eventType) {
            case XmlPullParser.START_TAG:
                if (tag.equals(TAG)) {
                    mUserN = parser.nextText();
                    return true;
                }
                break;
            case XmlPullParser.END_TAG:
                break;

        }
        return false;
    }
}
