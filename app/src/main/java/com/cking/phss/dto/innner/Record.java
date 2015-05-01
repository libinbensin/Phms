/* Xinhuaxing Inc. (C) 2014. All rights reserved.
 *
 * Record.java
 * classes : com.cking.phss.dto.innner.Record
 * @author Administrator
 * V 1.0.0
 * Create at 2014-7-4 下午3:13:08
 */
package com.cking.phss.dto.innner;

import com.cking.phss.bean.BeanCD;
import com.cking.phss.dto.IDto;
import com.cking.phss.xml.util.XmlTag;

/**
 * com.cking.phss.dto.innner.Record
 * @author Administrator <br/>
 * create at 2014-7-4 下午3:13:08
 */
public class Record implements IDto {
    // <!-- 必填。序号 -->
    @XmlTag(name = "Order")
    public String Order = "";
    // <!--必填。个人档案号-->
    @XmlTag(name = "ResidentID")
    public String ResidentID = "";
    // <!-- 必填。居民姓名 -->
    @XmlTag(name = "ResidentName")
    public String ResidentName = "";
    // <!-- 证件号码(一般为身份证) -->
    @XmlTag(name = "CredentialsNo")
    public String CredentialsNo = "";
    // <!--必填。性别，CD:代码（单选）。0）未知；1）男；2）女；9）未说明性别 -->
    @XmlTag(name = "Sex")
    public BeanCD Sex = null;
    // <!--必填。出生日期。格式：yyyy-mm-dd -->
    @XmlTag(name = "BirthDay")
    public String BirthDay = "";
    // <!--必填。类型，0.个人信息、1.家庭信息、2.快速体检、3.体质辨识、4.心理评估、5.老年评估、6.普通体检、7.高血压随访、8.糖尿病随访管理卡、9.脑卒中随访管理卡、10.精神病随访管理卡、11.孕产访视管理卡、12儿童访视管理卡、13.老年随访管理卡、14.残疾随访管理卡、15.高血压随访、16.糖尿病随访、17.脑卒中随访、18.精神病随访、19.孕产访视、20.儿童访视、21.老年随访、22.残疾随访-->
    @XmlTag(name = "ProjectType")
    public BeanCD ProjectType = null;
    // <!--必填。项目唯一编号，如：个人信息为个人档案号，家庭信息为家庭档案号，体检为体检登记编号，随访为随访编号，评估为评估序号-->
    @XmlTag(name = "SerialNumber")
    public String SerialNumber = "";
    // <!--必填。管理机构。CD：机构代码 -->
    @XmlTag(name = "Organization")
    public BeanCD Organization = null;
    // <!--必填。检查时间或创建修改时间。格式：yyyy-mm-dd -->
    @XmlTag(name = "CheckDate")
    public String CheckDate = "";
    // <!--必填。责任医生-->
    @XmlTag(name = "Doctor")
    public String Doctor = "";
    // <Doctor></Doctor>
    // <!--必填。完成状态 0.未完成，1.已完成-->
    @XmlTag(name = "Finish")
    public BeanCD Finish = null;
    // 下载状态，本地使用
    @XmlTag(name = "Download")
    public BeanCD Download = null;

    @XmlTag(name = "ResidentUUID")
    public String ResidentUUID = "";
    @XmlTag(name = "ProjectUUID")
    public String ProjectUUID = "";
    @XmlTag(name = "ClassName")
    public String ClassName = "";
}
