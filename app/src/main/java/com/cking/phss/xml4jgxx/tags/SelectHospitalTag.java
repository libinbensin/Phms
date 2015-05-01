/* Xinhuaxing Inc. (C) 2013. All rights reserved.
 *
 * ApkTag.java
 * classes : net.xinhuaxing.eshow.util.xml.tags.ApkTag
 * @author wation
 * V 1.0.0
 * Create at 2013-10-19 涓嬪崍1:22:54
 */
package com.cking.phss.xml4jgxx.tags;


/**
 * net.xinhuaxing.eshow.util.xml.tags.ApkTag
 * @author wation <br/>
 * create at 2013-10-19 1:22:54
 */
public class SelectHospitalTag extends BaseTag {
    private static final String TAG = "SelectHospitalTag";
    public static final String XML_TAG = "SelectHospitalTag";

    /* (non-Javadoc)
     * @see net.xinhuaxing.eshow.util.xml.tags.IntentTag#getXmlTag()
     */
    @Override
    public String getXmlTag() {
        return XML_TAG;
    }

    /* (non-Javadoc)
     * @see net.xinhuaxing.eshow.util.xml.tags.BaseTag#getValidAttr()
     */
    @Override
    protected String[] getValidAttr() {
        return new String[] {
        };
    }
}
