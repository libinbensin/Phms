/* Cking Inc. (C) 2012. All rights reserved.
 *
 * Body.java
 * classes : com.cking.phss.xml.bean.Body
 * @author Administrator
 * V 1.0.0
 * Create at 2012-9-22 下午11:12:42
 */
package com.cking.phss.xml.bean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

/**
 * com.cking.phss.xml.bean.Body
 * @author Administrator <br/>
 * create at 2012-9-22 下午11:12:42
 */
public class Body implements ITag {
    public static final String TAG = "Body";
    
    // 请求
    public Request mRequest = null;
    
    // 响应
    public Response mResponse = null;

    public void writeTag(XmlSerializer ser) throws IllegalArgumentException,
            IllegalStateException, IOException {
        ser.startTag("", TAG);
        
        // 请求
        if (mRequest != null)
            mRequest.writeTag(ser);

        // 响应
        if (mResponse != null)
            mResponse.writeTag(ser);

        ser.endTag("", TAG);
    }

    private List<ITag> tags = new ArrayList<ITag>();
    /* (non-Javadoc)
     * @see com.cking.phss.xml.bean.ITag#read(org.xmlpull.v1.XmlPullParser, int)
     */
    @Override
    public boolean read(XmlPullParser parser, int eventType) throws XmlPullParserException, IOException {
        String tag = parser.getName();
        switch (eventType) {
            case XmlPullParser.START_TAG:
                if (tag.equals(Request.TAG)) {
                    mRequest = new Request();
                    tags.add(mRequest);
                }
                if (tag.equals(Response.TAG)) {
                    mResponse = new Response();
                    tags.add(mResponse);
                }
                
                for (ITag itag : tags) {
                    boolean ret = itag.read(parser, eventType);
                    if (ret) {
                        return ret;
                    }
                }
                break;
            case XmlPullParser.END_TAG:
                if (tag.equals(TAG)) {
                    tags.clear();
                }
                break;

        }
        return false;
    }
}
