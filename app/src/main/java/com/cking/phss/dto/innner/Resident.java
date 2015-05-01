/* Xinhuaxing Inc. (C) 2014. All rights reserved.
 *
 * Resident.java
 * classes : com.cking.phss.dto.innner.Resident
 * @author Administrator
 * V 1.0.0
 * Create at 2014-7-24 上午11:11:09
 */
package com.cking.phss.dto.innner;

import java.util.List;

import com.cking.phss.bean.BeanCD;
import com.cking.phss.bean.BeanID;
import com.cking.phss.dto.IDto;
import com.cking.phss.util.DeviceUseFactory;
import com.cking.phss.xml.util.XmlTag;

/**
 * com.cking.phss.dto.innner.Resident
 * @author Administrator <br/>
 * create at 2014-7-24 上午11:11:09
 */

public class Resident implements IDto {
    // 必填。此条记录是否只读。1：是；0：否
    @XmlTag(name = "ReadOnly")
    public int ReadOnly = 0;

    // 必填。用户ID
    @XmlTag(name = "UserID")
    public String userID = "";

    // 必填。用户名称
    @XmlTag(name = "User")
    public String userName = "";

    // 必填。值：与户主关系名称
    @XmlTag(name = "Relation")
    public BeanID relation = null;

    // 卡号 , 身份证刷卡为身份证号码
    @XmlTag(name = "CardID")
    public String cardID = "";

    // 必填。家庭档案号
    @XmlTag(name = "FamilyID")
    public String familyID = "";

    // 必填。个人档案号
    @XmlTag(name = "ResidentID")
    public String residentID = "";

    // 必填。居民姓名
    @XmlTag(name = "ResidentName")
    public String residentName = "";

    // 必填。性别代码（单选）。值为代码：0）未知；1）男；2）女；9）未说明性别
    @XmlTag(name = "SexCD")
    public int sexCD = 0;

    // 必填。性别代码
    @XmlTag(name = "SexName")
    public String sexName = "";
    // 必填。出生日期。格式：yyyy-mm-dd
    @XmlTag(name = "BirthDay")
    public String birthDay = "";

    // 证件类型ID, CD：代码
    @XmlTag(name = "Credentials")
    public BeanCD credentials = null;

    // 必填。身份证号
    @XmlTag(name = "PaperNum")
    public String paperNum = "";

    // 工作单位
    @XmlTag(name = "WorkUnit")
    public String workUnit = "";

    // 参加工作入日期
    @XmlTag(name = "WorkDate")
    public String workDate = "";

    // 本人电话（联系方式）
    @XmlTag(name = "SelfPhone")
    public String selfPhone = "";

    // 手机号
    @XmlTag(name = "MobilePhone")
    public String mobilePhone = "";

    // 必填。ID：民族ID或代码，值：民族名称
    @XmlTag(name = "FolkCD")
    public BeanID folkCD = null;

    // 必填。文化程度代码（单选），值为代码：10、研究生；20,大学本科；30,大学专科和专科学校；40,中专；50,技工学校；60,高中；70,初中；80,小学；90,文盲或半文盲；97,其他
    @XmlTag(name = "EducationCD")
    public int EducationCD = 0;

    // --必填。文化程度-
    @XmlTag(name = "EducationName")
    public String educationName = "";

    // 必填。值：行业名称或职业类别；ID：行业或职业类别代码
    @XmlTag(name = "VocationCD")
    public BeanID vocationCD = null;

    // 必填。婚姻状况代码（单选），值为代码：10,未婚；20,已婚；22,再婚；23,复婚；30,丧偶；40,离婚；90,未说明的婚姻状况
    @XmlTag(name = "MarriageCD")
    public int marriageCD = 0;

    // --必填。婚姻状况, 文字值--
    @XmlTag(name = "MarriageName")
    public String marriageName = "";

    // 必填。住址类型代码（单选），值为代码：1、本县区；2：本市区其他县区；3：本省其他城市；4：外省；5：港澳台；6：外籍
    @XmlTag(name = "AddressTypeCD")
    public int AddressTypeCD = 0;

    // --必填。住址类型,文字值--
    @XmlTag(name = "AddressTypeName")
    public String addressTypeName = "";

    // 常住地址户籍标志，常住地址是否户籍地址
    @XmlTag(name = "RegisterAddressFlag")
    public String registerAddressFlag = "";

    // 必填。现住址省，值：省名，ID：省ID或代码
    @XmlTag(name = "NowCountry")
    public BeanID nowCountry = null;

    // 必填。现住址省，值：省名，ID：省ID或代码
    @XmlTag(name = "NowProvince")
    public BeanID nowProvince = null;

    // 必填。现住址市，值：市名，ID：市ID或代码
    @XmlTag(name = "NowCity")
    public BeanID nowCity = null;

    // 必填。现住址区县，值：区县名，ID：区县ID或代码
    @XmlTag(name = "NowDistrict")
    public BeanID nowDistrict = null;

    // 必填。现住址街道或镇，值：街道名，ID：街道ID或代码
    @XmlTag(name = "NowStreet")
    public BeanID nowStreet = null;

    // 必填。现住址居委、社区、村 值：居委名称 ID：居委ID或代码
    @XmlTag(name = "NowZone")
    public BeanID nowZone = null;

    // 现住址路，值：路；ID：路ID或代码
    @XmlTag(name = "NowRoad")
    public BeanID nowRoad = null;

    // 现住址弄
    @XmlTag(name = "NowN")
    public String nowN = "";

    // 现住址号
    @XmlTag(name = "NowH")
    public String nowH = "";

    // 现住址室
    @XmlTag(name = "NowS")
    public String nowS = "";

    // 其他地址信息，非以上规则的地址时使用
    @XmlTag(name = "NowOther")
    public String nowOther = "";

    // 现住址：详细地址
    @XmlTag(name = "NowDetail")
    public String nowDetail = "";

    // 户籍地址-邮政编码，CD：代码
    @XmlTag(name = "RegPostCode")
    public String regPostCode = "";

    // 户籍地址-国家，CD：代码
    @XmlTag(name = "RegCountry")
    public BeanCD regCountry = null;

    // 必填。户籍地址省，值：省名，ID：省ID或代码
    @XmlTag(name = "RegProvince")
    public BeanID regProvince = null;

    // 必填。户籍地址市，值：市名，ID：市ID或代码
    @XmlTag(name = "RegCity")
    public BeanID regCity = null;

    // 必填。户籍地址区县，值：区县名，ID：区县ID或代码
    @XmlTag(name = "RegDistrict")
    public BeanID regDistrict = null;

    // 必填。户籍地址街道或镇，值：街道名，ID：街道ID或代码
    @XmlTag(name = "RegStreet")
    public BeanID regStreet = null;

    // 户籍地址居委、社区、村 值：居委名称 ID：居委ID或代码
    @XmlTag(name = "RegZone")
    public BeanID regZone = null;

    // 户籍地址：详细地址
    @XmlTag(name = "RegDetail")
    public String regDetail = "";

    // 必填。个人档案中身高或近期身高，新增管理卡时用。单位：cm，整型
    @XmlTag(name = "Height")
    public String height = "";

    // 必填。个人档案中体重或近期体重，新增管理卡时用。单位：kg，浮点
    @XmlTag(name = "Weight")
    public String weight = "";

    // 医疗费用支付方式,CD：代码
    @XmlTag(name = "MedicalFeePay")
    public BeanCD medicalFeePay = null;

    // 过敏史标志, CD:ID或代码; 1、有；2、无
    @XmlTag(name = "AllergyFlag")
    public BeanCD allergyFlag = null;

    // 手术史标志, CD:ID或代码; 1、有；2、无
    @XmlTag(name = "OperationFlag")
    public BeanCD operationFlag = null;

    // 外伤史标志, CD:ID或代码; 1、有；2、无 --
    @XmlTag(name = "TraumaFlag")
    public BeanCD traumaFlag = null;

    // 输血史标志, CD:ID或代码; 1、有；2、无
    @XmlTag(name = "BloodTransfusionFlag")
    public BeanCD bloodTransfusionFlag = null;

    // 必填。值：责任医生，ID：医生代码或ID
    @XmlTag(name = "DutyDoctor")
    public BeanID dutyDoctor = null;

    // 必填。个人档案状态代码（单选）。值为状态代码：0.正常；1.注销；2.死亡；3.迁出(区域\外地)；4.挂起；5.临时
    @XmlTag(name = "FileStatusCD")
    public int fileStatusCD = 0;

    // 必填。个人档案状态,文字值
    @XmlTag(name = "FileStatusName")
    public String fileStatusName = "";

    // 数据来源, 有多条记录，因此会有多个DeviceUse节点
    @XmlTag(name = "DeviceUse", isListWithoutGroupTag = true)
    public List<DeviceUse> deviceUses = DeviceUseFactory.getDtoDeviceUses(getClass());
}
