/* Xinhuaxing Inc. (C) 2014. All rights reserved.
 *
 * CD.java
 * classes : com.cking.phss.bean.CD
 * @author Administrator
 * V 1.0.0
 * Create at 2014-6-27 下午2:29:23
 */
package com.cking.phss.bean;

import com.cking.phss.xml.util.XmlAttribute;
import com.cking.phss.xml.util.XmlTag;

/**
 * com.cking.phss.bean.CD
 * @author Administrator <br/>
 * create at 2014-6-27 下午2:29:23
 */
public class BeanID implements IBean, Cloneable {
    @XmlAttribute(name="ID")
    private String iD = "";
    @XmlTag(name = "TagValue",hasSubTag=false)
    private String tagValue="";
    
    public BeanID() {
        // TODO Auto-generated constructor stub
    }
    public BeanID(int iD, String tagValue) {
        this.iD = iD + "";
        this.tagValue = tagValue;
    }

    public BeanID(String iD, String tagValue) {
        this.iD = iD;
        this.tagValue = tagValue;
    }

    public String getiD() {
        return iD;
    }

    public void setiD(String iD) {
        this.iD = iD;
    }
    public String getTagValue() {
        return tagValue;
    }
    public void setTagValue(String tagValue) {
        this.tagValue = tagValue;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        BeanID cloneBeanID = (BeanID) super.clone();
        if (iD != null) {
            cloneBeanID.iD = new String(iD);
        }
        if (tagValue != null) {
            cloneBeanID.tagValue = new String(tagValue);
        }
        return super.clone();
    }
}
