/* Cking Inc. (C) 2012. All rights reserved.
 *
 * HistoryDisease.java
 * classes : com.cking.phss.bean.HistoryDisease
 * @author Wation Haliyoo
 * V 1.0.0
 * Create at 2012-10-5 上午12:39:42
 */
package com.cking.phss.bean;

import com.cking.phss.dto.IDto;
import com.cking.phss.xml.util.XmlTag;

/**
 * com.cking.phss.bean.HistoryDisease
 * @author Wation Haliyoo <br/>
 * create at 2012-10-5 上午12:39:42
 */

public class HistoryDisease implements IDto {
    // 既往史序号
    @XmlTag(name = "DisSn")
    public String disSn = "0";

    // 必填。既往史类别代码（单选）。值为代码：1：疾病；2：手术；3：外伤；4：输血
    @XmlTag(name = "HDType")
    public int hDType = 0;

    // 必填。既往史类别文字值
    @XmlTag(name = "HDTypeName")
    public String hDTypeName = "";

    // 必填。值：ICD10代码名称，ID：代码或ID
    @XmlTag(name = "ICD10")
    public BeanID iCD10 = null;

    // 疾病名称
    @XmlTag(name = "Disease")
    public String disease = "";

    // 必填。确诊日期、手术日期、外伤日期、输血日期，格式：yyyy-mm-dd
    @XmlTag(name = "DiagnoseDate")
    public String diagnoseDate = "";

    // 必填。发病日期，格式：yyyy-mm-dd
    @XmlTag(name = "HappenDate")
    public String happenDate = "";

    // 必填。原因
    @XmlTag(name = "HDReason")
    public String hDReason = "";

    // 结果代码（单选）。值为代码：1：治愈；2：好转；3：未愈；4：死亡；5：其他
    @XmlTag(name = "ResultCD")
    public int resultCD = 0;

    // 结果文字值
    @XmlTag(name = "ResultName")
    public String resultName = "";


    // 治疗描述
    @XmlTag(name = "CureDes")
    public String cureDes = "";

    // 诊治医院名称
    @XmlTag(name = "CureHos")
    public String cureHos = "";

    public String getDisSn() {
        return disSn;
    }

    public void setDisSn(String disSn) {
        this.disSn = disSn;
    }

    public int gethDType() {
        return hDType;
    }

    public void sethDType(int hDType) {
        this.hDType = hDType;
    }

    public String gethDTypeName() {
        return hDTypeName;
    }

    public void sethDTypeName(String hDTypeName) {
        this.hDTypeName = hDTypeName;
    }

    public BeanID getiCD10() {
        return iCD10;
    }

    public void setiCD10(BeanID iCD10) {
        this.iCD10 = iCD10;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getDiagnoseDate() {
        return diagnoseDate;
    }

    public void setDiagnoseDate(String diagnoseDate) {
        this.diagnoseDate = diagnoseDate;
    }

    public String getHappenDate() {
        return happenDate;
    }

    public void setHappenDate(String happenDate) {
        this.happenDate = happenDate;
    }

    public String gethDReason() {
        return hDReason;
    }

    public void sethDReason(String hDReason) {
        this.hDReason = hDReason;
    }

    public int getResultCD() {
        return resultCD;
    }

    public void setResultCD(int resultCD) {
        this.resultCD = resultCD;
    }

    public String getResultName() {
        return resultName;
    }

    public void setResultName(String resultName) {
        this.resultName = resultName;
    }

    public String getCureDes() {
        return cureDes;
    }

    public void setCureDes(String cureDes) {
        this.cureDes = cureDes;
    }

    public String getCureHos() {
        return cureHos;
    }

    public void setCureHos(String cureHos) {
        this.cureHos = cureHos;
    }
}
