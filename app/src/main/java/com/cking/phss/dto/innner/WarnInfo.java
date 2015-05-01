/* Xinhuaxing Inc. (C) 2014. All rights reserved.
 *
 * WarnInfo.java
 * classes : com.cking.phss.dto.innner.WarnInfo
 * @author Administrator
 * V 1.0.0
 * Create at 2014-7-24 上午11:23:45
 */
package com.cking.phss.dto.innner;

import com.cking.phss.bean.BeanID;
import com.cking.phss.dto.IDto;
import com.cking.phss.xml.util.XmlTag;

/**
 * com.cking.phss.dto.innner.WarnInfo
 * @author Administrator <br/>
 * create at 2014-7-24 上午11:23:45
 */

public class WarnInfo implements IDto {
    // 必填。这条记录是哪个业务模块的提醒（单选）。ID：业务模块代码，值：业务模块名称。代码为固定代码：1.高血压；2.糖尿病；3.慢阻肺；4.心脑血管；5.肿瘤病
    @XmlTag(name = "Business")
    public BeanID business = null;

    // 必填。个人档案号
    @XmlTag(name = "ResidentID")
    public String residentID = "";

    // 必填。家庭档案号
    @XmlTag(name = "FamilyID")
    public String familyID = "";

    // 必填。 居民姓名
    @XmlTag(name = "ResidentName")
    public String residentName = "";

    // 必填。性别（单选）。ID：性别代码， 值：性别名称。代码为固定代码：0.未知；1.男；2.女；9.未说明性别
    @XmlTag(name = "SexCD")
    public BeanID sexCD = null;

    // 必填。出生日期。格式：yyyy-mm-dd
    @XmlTag(name = "BirthDay")
    public String birthDay = "";

    // 居民本人联系电话
    @XmlTag(name = "SelfPhone")
    public String selfPhone = "";

    // -必填。 家庭地址
    @XmlTag(name = "Address")
    public String address = "";

    // 必填。值：责任医生，ID：医生代码或ID
    @XmlTag(name = "DutyDoctor")
    public BeanID dutyDoctor = null;

    // 必填。应访视日期。格式：yyyy-mm-dd
    @XmlTag(name = "OughtVisitDate")
    public String oughtVisitDate = "";

    // 必填。个人档案中身高或近期身高，新增管理卡时用。单位：cm，整型
    @XmlTag(name = "Height")
    public String height = "";

    // 必填。个人档案中体重或近期体重，新增管理卡时用。单位：kg，浮点
    @XmlTag(name = "Weight")
    public String weight = "";
}
