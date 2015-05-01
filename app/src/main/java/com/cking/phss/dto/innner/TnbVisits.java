/* Xinhuaxing Inc. (C) 2014. All rights reserved.
 *
 * TnbVisits.java
 * classes : com.cking.phss.dto.innner.TnbVisits
 * @author Administrator
 * V 1.0.0
 * Create at 2014-7-24 上午11:50:00
 */
package com.cking.phss.dto.innner;

import com.cking.phss.bean.BeanCD;
import com.cking.phss.dto.IDto;
import com.cking.phss.xml.util.XmlTag;

/**
 * com.cking.phss.dto.innner.TnbVisits
 * @author Administrator <br/>
 * create at 2014-7-24 上午11:50:00
 */

public class TnbVisits implements IDto {
    // 必填。此条记录是否只读。1：是；0：否
    @XmlTag(name = "ReadOnly")
    public int readOnly = 0;

    // 必填。用户ID，输入人员
    @XmlTag(name = "UserID")
    public String userID = "";

    // 必填。用户名称
    @XmlTag(name = "User")
    public String userName = "";

    // 必填。个人档案号
    @XmlTag(name = "ResidentID")
    public String residentID = "";

    // 必填。随访序号
    @XmlTag(name = "VisitID")
    public String visitID = "";

    // 必填。随访日期：yyyy-mm-dd
    @XmlTag(name = "VisitDate")
    public String visitDate = "";

    // 必填。随访者
    @XmlTag(name = "DoctorName")
    public String doctorName = "";

    // <!-- 必填。血糖类型， CD：ID或代码，1.空腹血糖，2.餐后两小时血糖，3.随机血糖, 4.其他-->
    @XmlTag(name = "GlucoseType")
    public BeanCD glucoseType = null;

    // 必填。空腹血糖。单位：mmol/L。浮点
    @XmlTag(name = "FPG")
    public String fPG = "";

    // <!-- 必填。餐后两小时血糖，单位：mmol/L，浮点-->
    @XmlTag(name = "PPGTwoH")
    public String pPGTwoH = "";

    // <!-- 必填。其他血糖类型的血糖值，单位：mmol/L，浮点-->
    @XmlTag(name = "GlucoseOther")
    public String glucoseOther = "";

    // 下次随访日期，格式：yyyy-mm-dd
    @XmlTag(name = "NextVisitDate")
    public String nextVisitDate = "";
}
