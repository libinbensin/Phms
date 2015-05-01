package com.cking.phss.dto.innner;

import com.cking.phss.bean.BeanCD;
import com.cking.phss.dto.IDto;
import com.cking.phss.xml.util.XmlTag;

public class Card implements IDto {
    // <!--必填。 报卡类型。CD：类型代码 1.高血压，2.糖尿病，3.脑卒中，4.精神病，5.老年人，6.残疾人，7.围产，8.儿童
    // -->
    @XmlTag(name = "CardType")
    public BeanCD CardType = null;
    // <!--必填。个人档案号。-->
    @XmlTag(name = "ResidentID")
    public String ResidentID = "";
    // <!-- 姓名 -->
    @XmlTag(name = "ResidentName")
    public String ResidentName = "";
    // <!--必填。 报卡日期 -->
    @XmlTag(name = "ReportDate")
    public String ReportDate = "";
    // <!--必填。 报卡医生。 CD：医生ID -->
    @XmlTag(name = "ReportDoctor")
    public BeanCD ReportDoctor = null;
}  







			
