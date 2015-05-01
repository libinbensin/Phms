/* Cking Inc. (C) 2012. All rights reserved.
 *
 * Nationality.java
 * classes : com.cking.phss.xml.bean.Nationality
 * @author Administrator
 * V 1.0.0
 * Create at 2012-9-22 下午11:59:22
 */
package com.cking.phss.xml.bean;

import java.io.IOException;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import com.cking.phss.xml.util.TagUtil;

/**
 * com.cking.phss.xml.bean.Nationality
 * @author Administrator <br/>
 * create at 2012-9-22 下午11:59:22
 */
public class Nationality implements ITag {
    public static final String TAG = "Nationality";
    
    public String mNationality = null;

    public void writeTag(XmlSerializer ser) throws IllegalArgumentException, IllegalStateException,
            IOException {
        TagUtil.writeTag(ser, mNationality, TAG);
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
                    mNationality = parser.nextText();
                    return true;
                }
                break;
            case XmlPullParser.END_TAG:
                break;

        }
        return false;
    }
}
