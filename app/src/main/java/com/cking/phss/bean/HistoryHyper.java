/* Cking Inc. (C) 2012. All rights reserved.
 *
 * HistoryHyper.java
 * classes : com.cking.phss.bean.HistoryHyper
 * @author Wation Haliyoo
 * V 1.0.0
 * Create at 2012-10-5 上午12:40:25
 */
package com.cking.phss.bean;

import com.cking.phss.xml.util.XmlTag;

/**
 * com.cking.phss.bean.HistoryHyper
 * @author Wation Haliyoo <br/>
 * create at 2012-10-5 上午12:40:25
 */

public class HistoryHyper implements IBean{// 过敏史
    @XmlTag(name="HyperTypeCD")
    public String hyperTypeCD = "";// 过敏类型代码（单选）。值为代码：1：非药品过敏；2：药品过敏
    
    @XmlTag(name = "HyperSn")
    public String hyperSn = "";

    // 必填 疾病序号 新增的时候序号为0
    @XmlTag(name = "DisSn")
    public String disSn = "0";
    
    @XmlTag(name="HyperSource")
    public BeanID hyperSource;// 过敏源名称，ID：相应代码或ID
    
    @XmlTag(name="HappenDate")
    public String happenDate = "";// 发病日期，格式：yyyy-mm-dd
    
    @XmlTag(name="HyperReason")
    public String hyperReason = "";// 过敏原因
    
    @XmlTag(name="CureDes")
    public String cureDes = "";// 治疗描述

    public String getHyperSn() {
        return hyperSn;
    }

    public void setHyperSn(String hyperSn) {
        this.hyperSn = hyperSn;
    }

    public String getDisSn() {
        return disSn;
    }

    public void setDisSn(String disSn) {
        this.disSn = disSn;
    }
    
    public String getHyperTypeCD() {
        return hyperTypeCD;
    }
    public void setHyperTypeCD(String hyperTypeCD) {
        this.hyperTypeCD = hyperTypeCD;
    }
    public BeanID getHyperSource() {
        return hyperSource;
    }
    public void setHyperSource(BeanID hyperSource) {
        this.hyperSource = hyperSource;
    }
    public String getHappenDate() {
        return happenDate;
    }
    public void setHappenDate(String happenDate) {
        this.happenDate = happenDate;
    }
    public String getHyperReason() {
        return hyperReason;
    }
    public void setHyperReason(String hyperReason) {
        this.hyperReason = hyperReason;
    }
    public String getCureDes() {
        return cureDes;
    }
    public void setCureDes(String cureDes) {
        this.cureDes = cureDes;
    }
    
}