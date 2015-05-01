package com.cking.phss.dto.sfgl.jsb;

import java.util.List;

import com.cking.phss.bean.BeanCD;
import com.cking.phss.bean.BeanID;
import com.cking.phss.dto.IDto;
import com.cking.phss.dto.innner.DeviceUse;
import com.cking.phss.global.Global;
import com.cking.phss.util.DeviceUseFactory;
import com.cking.phss.xml.util.XmlAttribute;
import com.cking.phss.xml.util.XmlTag;

/**
 * HFM02 得到精神病管理卡详细信息.xml
 * 
 * @author Administrator
 */
public class DdjsbglkxxxxHfm02 implements IDto {
	/**
	 * Request
	 */
	@XmlTag(name = "Request")
	public Request request = null;

	static public class Request implements IDto {
		@XmlAttribute(name = "OrgCode")
		public String orgCode = Global.orgCode;

		@XmlAttribute(name = "OperType")
		public String operType = "HFM02";

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
		//<!-- 证件类型, CD：代码 -->
        @XmlTag(name = "Credentials")
		public BeanCD credentials = null;
		//<!-- 证件号码 -->
        @XmlTag(name = "CredentialsNo")
		public String credentialsNo = "";
		//<!-- 生日 -->
        @XmlTag(name = "BirthDay")
		public String birthDay = "";
		//<!-- 性别, CD：代码 -->
        @XmlTag(name = "Sex")
		public BeanCD sex = null;
		//<!-- 名族, CD：代码 -->
        @XmlTag(name = "Ethnicity")
		public BeanCD ethnicity = null;
		//<!--必填。居住地址-国家，CD：代码 -->
        @XmlTag(name = "NowCountry")
        public BeanCD nowCountry = new BeanCD();
		//<!--必填。居住地址-省 -->
        @XmlTag(name = "NowProvince")
        public BeanID nowProvince = new BeanID();
		//<!--必填。居住地址-市 -->
        @XmlTag(name = "NowCity")
        public BeanID nowCity = new BeanID();
		//<!--必填。居住地址-区县 -->
        @XmlTag(name = "NowDistrict")
        public BeanID nowDistrict = new BeanID();
		//<!--必填。居住地址-街道 -->
        @XmlTag(name = "nowStreet")
        public BeanID nowStreet = new BeanID();
		//<!--必填。居住地址-社区/村 -->
        @XmlTag(name = "NowCommunity")
        public BeanID nowCommunity = new BeanID();
		//<!--路 -->
        @XmlTag(name = "NowRoad")
        public BeanID nowRoad = new BeanID();
		//<!-- 弄-->
        @XmlTag(name = "NowLane")
		public String nowLane = "";
		//<!-- 号-->
        @XmlTag(name = "NowGroup")
		public String nowGroup = "";
		//<!-- 室-->
        @XmlTag(name = "NowRoom")
		public String nowRoom = "";
		//<!-- 其他地址信息，非以上规则的地址时使用 -->
        @XmlTag(name = "NowOther")
		public String nowOther = "";
		//<!--必填。户籍地址-国家，CD：代码 -->
        @XmlTag(name = "RegCountry")
        public BeanCD regCountry = new BeanCD();
		//<!--必填。户籍地址-省 -->
        @XmlTag(name = "RegProvince")
        public BeanID regProvince = new BeanID();
		//<!--必填。户籍地址-市 -->
        @XmlTag(name = "RegCity")
        public BeanID regCity = new BeanID();
		//<!--必填。户籍地址-区县 -->
        @XmlTag(name = "RegDistrict")
        public BeanID regDistrict = new BeanID();
		//<!--必填。户籍地址-街道 -->
        @XmlTag(name = "RegStreet")
        public BeanID regStreet = new BeanID();
		//<!--必填。户籍地址-社区/村 -->
        @XmlTag(name = "RegCommunity")
        public BeanID regCommunity = new BeanID();
		//<!--路 CD:ID或代码 -->
        @XmlTag(name = "RegRoad")
        public BeanID regRoad = new BeanID();
		//<!-- 户籍地址-弄-->
        @XmlTag(name = "RegLane")
		public String regLane = "";
		//<!-- 户籍地址-号-->
        @XmlTag(name = "RegGroup")
		public String regGroup = "";
		//<!-- 户籍地址-室-->
        @XmlTag(name = "RegRoom")
		public String regRoom = "";
		//<!-- 户籍地址-其他地址信息，非以上规则的地址时使用 -->
        @XmlTag(name = "RegOther")
		public String regOther = "";
		//<!-- 本人电话（宅电） -->
        @XmlTag(name = "Telephone")
		public String telephone = "";
		//<!-- 手机号 -->
        @XmlTag(name = "MobilePhone")
		public String mobilePhone = "";
		//<!-- 教育程度, CD：代码 -->
        @XmlTag(name = "Education")
		public BeanCD education = null;
		//<!-- 婚姻状况, CD：代码 -->
        @XmlTag(name = "MaritalStatus")
		public BeanCD maritalStatus = null;
		//<!-- 行业, CD：代码 -->
        @XmlTag(name = "Industry")
		public BeanCD industry = null;
		//<!-- 具体工种, CD：代码 -->
        @XmlTag(name = "WorkType")
		public BeanCD workType = null;
		//<!-- 工作单位 -->
        @XmlTag(name = "WorkUnit")
		public String workUnit = "";
		//<!-- 监护人姓名 -->
        @XmlTag(name = "GuardianName")
		public String guardianName = "";
		//<!-- 与患者关系, CD：代码 -->
        @XmlTag(name = "RelationToPatient")
		public BeanCD relationToPatient = null;
		//<!-- 监护人地址 -->
        @XmlTag(name = "GuardianAddress")
		public String guardianAddress = "";
		//<!-- 监护人电话 -->
        @XmlTag(name = "GuardianPhone")
		public String guardianPhone = "";
		//<!-- 居委会名称 -->
        @XmlTag(name = "CommitteeName")
		public String committeeName = "";
		//<!-- 居委会联系人 -->
        @XmlTag(name = "CommitteeContacts")
		public String committeeContacts = "";
		//<!-- 居委会电话 -->
        @XmlTag(name = "CommitteePhone")
		public String committeePhone = "";
		//<!-- 知情同意情况, CD：代码 ；1 ：同意参加管理；2：不同意参加管理-->
        @XmlTag(name = "Informed")
		public BeanCD informed = null;
		//<!-- 签字 -->
        @XmlTag(name = "Signature")
		public String signature = "";
		//<!-- 签字日期 -->
        @XmlTag(name = "SignatureDate")
		public String signatureDate = "";
		//<!-- 初次发病时间 -->
        @XmlTag(name = "OnsetDate")
		public String onsetDate = "";
		//<!-- 初次发病年龄 -->
        @XmlTag(name = "OnsetAge")
		public String onsetAge = "";
		//<!-- 既往主要症状, CD：代码 ；1.幻觉、2.交流困难、3.猜疑、4.喜怒无常、5.行为怪异、6.兴奋话多、7.伤人毁物、8.悲观厌世、9.无故外走、10.自语自笑、11.孤僻懒散、99.其他-->
        @XmlTag(name = "Symptoms")
		public BeanCD symptoms = null;
		//<!-- 门诊治疗, CD：代码 ；1.未治，2.间断门诊治疗，3.连续门诊治疗-->
        @XmlTag(name = "OutpatientTreatment")
		public BeanCD outpatientTreatment = null;
		//<!-- 首次抗精精神病药物治疗时间 -->
        @XmlTag(name = "FirstTreatDate")
		public String firstTreatDate = "";
		//<!-- 曾住精神病专科医院/综合医院专科次数 -->
        @XmlTag(name = "HospitalTimes")
		public String hospitalTimes = "";
		//<!-- 目前诊断情况（诊断） -->
        @XmlTag(name = "Diagnosis")
        public BeanCD diagnosis = null;
		//<!-- 确诊医院 -->
        @XmlTag(name = "DiagnosisHospital")
		public String diagnosisHospital = "";
		//<!-- 确诊时间 -->
        @XmlTag(name = "DiagnosisDate")
		public String diagnosisDate = "";
		//<!-- 最后治疗效果, CD：代码 ；1.痊愈、2.好转、3.无变化、4.加重-->
        @XmlTag(name = "LastEffect")
		public BeanCD lastEffect = null;
		//<!-- 有无对家庭社会的影响, CD：代码 ；1.无，2.有-->
        @XmlTag(name = "Influence")
		public BeanCD influence = null;
		//<!-- 对家庭社会的影响 - 轻度滋事次数 -->
        @XmlTag(name = "MildTrouble")
		public String mildTrouble = "";
		//<!-- 对家庭社会的影响 - 肇事次数 -->
        @XmlTag(name = "Accident")
		public String accident = "";
		//<!-- 对家庭社会的影响 - 肇祸次数 -->
        @XmlTag(name = "Trouble")
		public String trouble = "";
		//<!-- 对家庭社会的影响 - 自伤次数 -->
        @XmlTag(name = "SelfWounding")
		public String selfWounding = "";
		//<!-- 对家庭社会的影响 - 自杀未遂次数 -->
        @XmlTag(name = "AttemptedSuicide")
		public String attemptedSuicide = "";
		//<!-- 对家庭社会的影响 - 其他 -->
        @XmlTag(name = "InfluenceOther")
		public String influenceOther = "";
		//<!-- 关锁情况, CD：代码 ；1.无关锁、2.关锁、3.关锁已解除-->
        @XmlTag(name = "LockUp")
		public BeanCD lockUp = null;
		//<!-- 经济情况, CD：代码 ；1.贫困、2.非贫困、3.不详-->
        @XmlTag(name = "Economic")
		public BeanCD economic = null;
		//<!-- 专科医生的意见 -->
        @XmlTag(name = "DoctorsAdvice")
		public String doctorsAdvice = "";
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
