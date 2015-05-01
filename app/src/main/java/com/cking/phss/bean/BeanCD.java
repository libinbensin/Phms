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
public class BeanCD implements IBean, Cloneable {
    @XmlAttribute(name="CD")
    private String cD="";
    @XmlTag(name = "TagValue",hasSubTag=false)
    private String tagValue="";
    
    public BeanCD() {
        // TODO Auto-generated constructor stub
    }
    public BeanCD(String cD, String tagValue) {
        this.cD = cD;
        this.tagValue = tagValue;
    }

    public BeanCD(int cD, String tagValue) {
        this.cD = cD + "";
        this.tagValue = tagValue;
    }
    public String getcD() {
        return cD;
    }
    public void setcD(String cD) {
        this.cD = cD;
    }
    public String getTagValue() {
        return tagValue;
    }
    public void setTagValue(String tagValue) {
        this.tagValue = tagValue;
    }

    public boolean sameValue(BeanCD cd) {
        if ((cD == null && cd.cD == null) || (cD != null && cD.equals(cd.cD))) {
            if ((tagValue == null && cd.tagValue == null)
                    && (tagValue != null && tagValue.equals(cd.tagValue))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        BeanCD cloneBeanCD = (BeanCD) super.clone();
        if (cD != null) {
            cloneBeanCD.cD = new String(cD);
        }
        if (tagValue != null) {
            cloneBeanCD.tagValue = new String(tagValue);
        }
        return super.clone();
    }
}
