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
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

/**
 * com.cking.phss.xml.bean.Zone
 * @author Administrator <br/>
 * create at 2012-9-22 下午10:53:17
 */
public class Zones implements ITag {
    public static final String TAG = "Zones";

    // <!--此用户可以管辖哪些社区（村）或者居委会 -->
    // <!--社区（村）或者居委会，值：名称；ID：相应代码 -->
    public List<Zone> mZones = null;

    public void writeTag(XmlSerializer ser) throws IllegalArgumentException, IllegalStateException,
            IOException {
        ser.startTag("", TAG);
        if (mZones != null) {
            for (Zone zone : mZones) {
                if (zone != null)
                    zone.writeTag(ser);
            }
        }
        ser.endTag("", TAG);
    }

    /* (non-Javadoc)
     * @see com.cking.phss.xml.bean.ITag#read(java.lang.String, java.lang.String)
     */
    @Override
    public boolean read(XmlPullParser parser, int eventType) throws XmlPullParserException, IOException {
        Zone zone = null;

        String tag = parser.getName();
        switch (eventType) {
            case XmlPullParser.START_TAG:
                if (tag.equals(TAG)) {
                    mZones = new ArrayList<Zone>();
                    return true;
                }

                if (tag.equals(Zone.TAG)) {
                    zone = new Zone();
                    boolean ret = zone.read(parser, eventType);
                    if (ret) {
                        if (mZones != null) mZones.add(zone);
                        return ret;
                    }
                }
                break;
            case XmlPullParser.END_TAG:
//                if (tag.equals(Zone.TAG) && zone != null) {
//                    mZones.add(zone);
//                }
                break;

        }
        return false;
    }
}
