/* Cking Inc. (C) 2012. All rights reserved.
 *
 * UserName.java
 * classes : com.cking.phss.xml.bean.UserName
 * @author Administrator
 * V 1.0.0
 * Create at 2012-9-23 上午12:15:02
 */
package com.cking.phss.xml.bean;

import java.io.IOException;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import com.cking.phss.xml.util.TagUtil;

/**
 * com.cking.phss.xml.bean.UserName
 * @author Administrator <br/>
 * create at 2012-9-23 上午12:15:02
 */
public class UserName implements ITag {
    public static final String TAG = "UserName";
    
    public String mUserName = null;

    public void writeTag(XmlSerializer ser) throws IllegalArgumentException, IllegalStateException,
            IOException {
        TagUtil.writeTag(ser, mUserName, TAG);
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
                    mUserName = parser.nextText();
                    return true;
                }
                break;
            case XmlPullParser.END_TAG:
                break;

        }
        return false;
    }
}
