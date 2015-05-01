package com.cking.phss.dto.sfgl.cjsf;

import java.util.List;

import com.cking.phss.bean.BeanCD;
import com.cking.phss.dto.IDto;
import com.cking.phss.dto.innner.DeviceUse;
import com.cking.phss.global.Global;
import com.cking.phss.util.DeviceUseFactory;
import com.cking.phss.xml.util.XmlAttribute;
import com.cking.phss.xml.util.XmlTag;


/**
 * HFDA02 得到残疾人管理卡详细信息.xml
 * 
 * @author Administrator
 */
public class DdcjrglkxxxxHfda02 implements IDto {
	/**
	 * Request
	 */
	@XmlTag(name = "Request")
	public Request request = null;

	static public class Request implements IDto {
		@XmlAttribute(name = "OrgCode")
		public String orgCode = Global.orgCode;

		@XmlAttribute(name = "OperType")
		public String operType = "HFDA02";

		//<!--必填。值：当前登录用户ID -->
        @XmlTag(name = "UserID")
		public String userID = "";

		//<!--必填。个人档案号-->
        @XmlTag(name = "ResidentID")
		public String residentID = "";
	}

	/**
	 * Response
	 * 
	 * @author Administrator
	 */
	@XmlTag(name = "Response")
	public Response response = null;

	static public class Response implements IDto {
		@XmlAttribute(name = "ErrMsg")
		public String errMsg = "";

		//<!--必填。此条记录是否只读。1：是；0：否 -->
        @XmlTag(name = "ReadOnly")
		public String readOnly = "";
		//<!-- 必填。用户ID-->
        @XmlTag(name = "UserID")
		public String userID = "";
		//<!-- 必填。用户名称-->
        @XmlTag(name = "UserName")
		public String userName = "";

		//<!--个人档案号。-->
        @XmlTag(name = "ResidentID")
		public String residentID = "";
		//<!-- 必填。居民姓名 -->
        @XmlTag(name = "ResidentName")
		public String residentName = "";
		//<!-- 证件类型ID-->
        @XmlTag(name = "Credentials")
		public BeanCD credentials = null;
		//<!-- 证件号 -->
        @XmlTag(name = "CredentialsNo")
		public String credentialsNo = "";
		//<!-- 性别, CD：代码 ,值为, CD：代码 ：0）未知；1）男；2）女；9）未说明性别-->
        @XmlTag(name = "Sex")
		public BeanCD sex = null;
		//<!-- 出生日期。格式：yyyy-mm-dd -->
        @XmlTag(name = "BirthDay")
		public String birthDay = "";
		//<!-- 文化程度, CD：代码 ，值为, CD：代码 ：10、研究生；20,大学本科；30,大学专科和专科学校；40,中专；50,技工学校；60,高中；70,初中；80,小学；90,文盲或半文盲；97,其他-->
        @XmlTag(name = "Education")
		public BeanCD education = null;
		//<!--必填。值：行业名称；CD：行业代码 -->
        @XmlTag(name = "Industry")
		public BeanCD industry = null;
		//<!--值：具体工种名称，ID：具体工种代码 -->
        @XmlTag(name = "WorkType")
		public BeanCD workType = null;
		//<!-- 婚姻状况, CD：代码 ,值为, CD：代码 ：10,未婚；20,已婚；22,再婚；23,复婚；30,丧偶；40,离婚；90,未说明的婚姻状况-->
        @XmlTag(name = "MaritalStatus")
		public BeanCD maritalStatus = null;
		//<!-- 本人电话（宅电） -->
        @XmlTag(name = "Telephone")
		public String telephone = "";
		//<!-- 手机号 -->
        @XmlTag(name = "MobilePhone")
		public String mobilePhone = "";
		//<!-- 民族, CD：代码 -->
        @XmlTag(name = "Ethnicity")
		public BeanCD ethnicity = null;
		//<!-- 国籍, CD：代码 -->
        @XmlTag(name = "Nationality")
		public BeanCD nationality = null;
		//<!--必填。住址类型代码（单选），CD:值为代码：1、本县区；2：本市区其他县区；3：本省其他城市；4：外省；5：港澳台；6：外籍 -->
        @XmlTag(name = "AddressType")
		public BeanCD addressType = null;
		//<!-- 国家, CD：代码 -->
        @XmlTag(name = "Country")
		public BeanCD country = null;
		//<!-- 省/直辖市, CD：代码 -->
        @XmlTag(name = "Province")
		public BeanCD province = null;
		//<!-- 市/地区/州, CD：代码 -->
        @XmlTag(name = "City")
		public BeanCD city = null;
		//<!-- 区/县, CD：代码 -->
        @XmlTag(name = "District")
		public BeanCD district = null;
		//<!-- 乡/镇/街道办事处, CD：代码 -->
        @XmlTag(name = "Street")
		public BeanCD street = null;
		//<!-- 居委/社区, CD：代码 -->
        @XmlTag(name = "Community")
		public BeanCD community = null;
		//<!-- 村/路/街, CD：代码 -->
        @XmlTag(name = "Road")
		public BeanCD road = null;
		//<!-- 弄 -->
        @XmlTag(name = "Lane")
		public String lane = "";
		//<!-- 号/组 -->
        @XmlTag(name = "Group")
		public String Group = "";
		//<!-- 室/门牌号码 -->
        @XmlTag(name = "Room")
		public String room = "";
		//<!-- 其他地址信息 -->
        @XmlTag(name = "Other")
		public String other = "";
		//<!-- 户籍地址 -->
        @XmlTag(name = "RegisterAddress")
		public String registerAddress = "";
		//<!-- 工作单位 -->
        @XmlTag(name = "WorkUnit")
		public String workUnit = "";
		//<!-- 医疗费用支付方式, CD：代码 -->
        @XmlTag(name = "MedicalFeePay")
		public BeanCD medicalFeePay = null;
		//<!-- 随访方式, CD：代码 ；1.门诊 2.家庭 3.电话 4.集体-->
        @XmlTag(name = "FlupWay")
		public BeanCD flupWay = null;
		//<!-- 残疾情况 - 残疾证号 -->
        @XmlTag(name = "DeformityCard")
		public String deformityCard = "";
		//<!-- 残疾情况 - 主要残疾, CD：代码 ；1.视力残疾，2.听力残疾，3.言语残疾，4.肢体残疾，5.智力残疾，6.精神残疾-->
        @XmlTag(name = "MainDisability")
		public BeanCD mainDisability = null;
		//<!-- 残疾情况 - 多重残疾标志，1.是，2.否 -->
        @XmlTag(name = "MultiDisabilityFlag")
		public String multiDisabilityFlag = "";
		//<!-- 残疾情况 - 多重残疾, CD：代码 （多选）；多条用英文逗号隔开；1.视力残疾，2.听力残疾，3.言语残疾，4.肢体残疾，5.智力残疾，6.精神残疾-->
        @XmlTag(name = "MultiDisability")
		public BeanCD multiDisability = null;
		//<!-- 残疾情况 - 残疾程度, CD：代码 ；1.一级，2.二级，3.三级，4.四级，99.未评定-->
        @XmlTag(name = "DisabilityLevel")
		public BeanCD disabilityLevel = null;
		//<!-- 残疾情况 - 致残原因, CD：代码 -->
        @XmlTag(name = "DisabilityReason")
		public BeanCD disabilityReason = null;
		//<!-- 残疾情况 - 致残时间 -->
        @XmlTag(name = "DisabilityDate")
		public String disabilityDate = "";
		//<!-- 报卡日期 -->
        @XmlTag(name = "ReportDate")
		public String reportDate = "";
		//<!--必填。责任医生，值：医生姓名，ID：医生ID或代码 。 -->
        @XmlTag(name = "DutyDoctor")
		public BeanCD dutyDoctor = null;
		//<!--必填。报卡医生，值：医生姓名，ID：医生ID或代码 。 -->
        @XmlTag(name = "ReportDoctor")
		public BeanCD reportDoctor = null;
		//<!--必填。报卡单位。值：单位名称；ID：单位ID或代码 -->
        @XmlTag(name = "ReportUnit")
		public BeanCD reportUnit = null;
		//<!-- 数据来源, 有多条记录，因此会有多个DeviceUse节点-->
		@XmlTag(name = "DeviceUse", isListWithoutGroupTag=true)
		public List<DeviceUse> deviceUses = DeviceUseFactory.getDtoDeviceUses(getClass());
	}

	public void init() {
		// /**
		// * Request
		// */
		// this.request = new Request();
		// this.request.orgCode = "16";
		// this.request.operType = "16";
		// this.request.type = 1;
		// this.request.userID = 16;
		// this.request.residentID = "16";
		// this.request.flupID = "16";
		// this.request.visitDate = "2012-10-03";
		// this.request.doctorID = "1016";
		// this.request.doctorName = "安哥";
		// this.request.sffscd = 1016;
		// this.request.xcsf = "2012-10-12";
		// this.request.ZZCD = "1";
		// this.request.ZZQT = "无异常";
		// this.request.SBP = 0;
		// this.request.DBP = 12;
		// this.request.BCTZ = "61.5";
		// this.request.BCSG = "171";
		// this.request.TZZS = "体质正常";
		// this.request.BCXL = 68;
		// this.request.QTTZ = "其他特征正常";
		// this.request.XCTZ = "60";
		// this.request.XCXL = 75;
		// this.request.BCXYL = 03;
		// this.request.XCXY = 04;
		// this.request.BCYJ = 03;
		// this.request.XCYJ = 04;
		// this.request.YDZC = 11;
		// this.request.XCYDZC = 19;
		// this.request.XCYDCD = 30;
		// this.request.BCSYL = 1;
		// this.request.XCSYL = 2;
		// this.request.XLTZCD = 1;
		// this.request.ZYXWCD = 1;
		// this.request.FZJC = "1";
		// this.request.FYYCXCD = 2;
		// this.request.BLFY = 0;
		// this.request.FYQK = "没有不良情况";
		// this.request.SFFLCD = 1;
		// this.request.ZZYY = "该医院设备不全";
		// this.request.ZZKB = "什么什么设备";
		// this.request.BZ = "无备注";
		// /**
		// * Response
		// */
		// this.response = new Response();
		// this.response.errMsg = "16";
		// this.response.residentID = "1016";
		// this.response.visitID = 1106;
	}
}
