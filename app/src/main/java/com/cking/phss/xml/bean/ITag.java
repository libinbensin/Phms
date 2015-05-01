/* Cking Inc. (C) 2012. All rights reserved.
 *
 * ITag.java
 * classes : com.cking.phss.xml.bean.ITag
 * @author Administrator
 * V 1.0.0
 * Create at 2012-9-23 上午1:25:14
 */
package com.cking.phss.xml.bean;

import java.io.IOException;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

/**
 * com.cking.phss.xml.bean.ITag
 * @author Administrator <br/>
 * create at 2012-9-23 上午1:25:14
 */
public interface ITag {
    public void writeTag(XmlSerializer ser) throws IllegalArgumentException, IllegalStateException,
    IOException;
    
    public boolean read(XmlPullParser parser, int eventType) throws XmlPullParserException, IOException;
}
