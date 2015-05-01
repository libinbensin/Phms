package com.cking.phss.dto.sfgl.tnb;

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
 *26 保存糖尿病管理卡.xml 
 * @author Administrator 
 */
public class Bctnbglk26 implements IDto {
	/**
	 * Request
	 */
	@XmlTag(name = "Request")
	public Request request = null;

	static public class Request implements IDto {
		@XmlAttribute(name = "OrgCode")
		public String orgCode = Global.orgCode;

		@XmlAttribute(name = "OperType")
		public String operType = "26";  
		
		//必填。存盘类型，1：新增存盘 2：编辑存盘
		@XmlTag(name = "Type")
		public int type= 0;
		
		//必填。 操作用户ID或代码
		@XmlTag(name = "UserID")
		public String userID= "";
		
		//个人档案号。如果是新增则无个人档案号（新增时此ID由程序后台生成）
		@XmlTag(name = "ResidentID")
		public String residentID= "";
		
		//必填。居民姓名
		@XmlTag(name = "ResidentName")
		public String residentName= "";
		
		//必填。性别代码（单选）。值为代码：0）未知；1）男；2）女；9）未说明性别
		@XmlTag(name = "SexCD")
		public String sexCD= "0";

		//必填。性别代码
		@XmlTag(name = "SexName")
		public String sexName = "";
		
		//必填。出生日期。格式：yyyy-mm-dd
		@XmlTag(name = "BirthDay")
		public String birthDay= "";

		//  证件类型ID, CD：代码
		@XmlTag(name = "Credentials")
        public BeanCD credentials = null;

		//必填。身份证号
		@XmlTag(name = "PaperNum")
		public String paperNum= ""; 
		
		//工作单位
		@XmlTag(name = "WorkUnit")
		public String workUnit= "";
		
		//必填。本人电话
		@XmlTag(name = "SelfPhone")
		public String selfPhone= "";
		
		// <!-- 手机号 -->
		@XmlTag(name = "MobilePhone")
		public String mobilePhone = "";
		
		//必填。ID：民族ID或代码，值：民族名称
		@XmlTag(name = "FolkCD")
		public BeanID folkCD = null; 

		//必填。文化程度代码（单选），值为代码：10、研究生；20,大学本科；30,大学专科和专科学校；40,中专；50,技工学校；60,高中；70,初中；80,小学；90,文盲或半文盲；97,其他 
		@XmlTag(name = "EducationCD")
		public String educationCD= "";
		
		// --必填。文化程度-
		@XmlTag(name = "EducationName")
		public String educationName = "";
		
		//必填。值：行业名称；ID：行业代码
		@XmlTag(name = "VocationCD")
		public BeanID vocationCD = null; 
		
		//值：具体工种名称，ID：具体工种代码
		@XmlTag(name = "WorkTypeCD")
		public BeanID workTypeCD = null; 
		
		//必填。婚姻状况代码（单选），值为代码：10,未婚；20,已婚；22,再婚；23,复婚；30,丧偶；40,离婚；90,未说明的婚姻状况
		@XmlTag(name = "MarriageCD")
		public String marriageCD= "";
		
		// --必填。婚姻状况, 文字值--
		@XmlTag(name = "MarriageName")
		public String marriageName = "";
		
		//-医疗费用支付方式代码（单选），值为代码：01,医保；02,商业医疗保险；03,大病统筹；04,新型农村合作医疗；05,城镇居民基本医疗保险；06,公费医疗；99,其他 
		@XmlTag(name = "MedicalCD")
		public String medicalCD= "";

		//<!--医疗费用支付方式,文字值-->
		public String medicalName = "";
		
		//必填。住址类型代码（单选），值为代码：1、本县区；2：本市区其他县区；3：本省其他城市；4：外省；5：港澳台；6：外籍
		@XmlTag(name = "AddressTypeCD")
		public String addressTypeCD= ""; 
		
		// --必填。住址类型,文字值--
		@XmlTag(name = "AddressTypeName")
		public String addressTypeName = "";
		
		// 必填。现住址省，值：省名，ID：省ID或代码
		@XmlTag(name = "NowCountry")
        public BeanCD nowCountry = new BeanCD();

		@XmlTag(name = "NowProvince")
        public BeanID nowProvince = new BeanID();

		//必填。现住址市，值：市名，ID：市ID或代码 
		@XmlTag(name = "NowCity")
        public BeanID nowCity = new BeanID();
		
		//必填。现住址区县，值：区县名，ID：区县ID或代码 
		@XmlTag(name = "NowDistrict")
        public BeanID nowDistrict = new BeanID();
		
		//必填。现住址街道或镇，值：街道名，ID：街道ID或代码
		@XmlTag(name = "NowStreet")
        public BeanID nowStreet = new BeanID();
		
		//必填。现住址居委、社区、村 值：居委名称 ID：居委ID或代码
		@XmlTag(name = "NowZone")
        public BeanID nowZone = new BeanID();
		
		//现住址路，值：路；ID：路ID或代码
		@XmlTag(name = "NowRoad")
        public BeanID nowRoad = new BeanID();
		
		//现住址弄
		@XmlTag(name = "NowN")
		public String nowN= "";
		
		//现住址号
		@XmlTag(name = "NowH")
		public String nowH= "";
		
		// 现住址室
		@XmlTag(name = "NowS")
		public String nowS= "";
		
		// 其他地址信息，非以上规则的地址时使用
		@XmlTag(name = "NowOther")
		public String nowOther = "";
		
		//现住址：详细地址
		@XmlTag(name = "NowDetail")
		public String nowDetail= "";
		
		// 户籍地址-国家，CD：代码
		@XmlTag(name = "RegCountry")
        public BeanCD regCountry = new BeanCD();

		//必填。户籍地址省，值：省名，ID：省ID或代码
		@XmlTag(name = "RegProvince")
        public BeanID regProvince = new BeanID();
		
		//必填。户籍地址市，值：市名，ID：市ID或代码 
		@XmlTag(name = "RegCity")
        public BeanID regCity = new BeanID();
		
		//必填。户籍地址区县，值：区县名，ID：区县ID或代码
		@XmlTag(name = "RegDistrict")
        public BeanID regDistrict = new BeanID();
		
		//必填。户籍地址街道或镇，值：街道名，ID：街道ID或代码
		@XmlTag(name = "RegStreet")
        public BeanID regStreet = new BeanID();
		
		//户籍地址居委、社区、村 值：居委名称 ID：居委ID或代码
		@XmlTag(name = "RegZone")
        public BeanID regZone = new BeanID();
		
		//路 CD:ID或代码
		@XmlTag(name = "RegRoad")
        public BeanID regRoad = new BeanID();

		//<!-- 户籍地址-弄-->
		@XmlTag(name = "RegN")
		public String regN = "";

		//<!-- 户籍地址-号-->
		@XmlTag(name = "RegH")
		public String regH = "";

		//<!-- 户籍地址-室-->
		@XmlTag(name = "RegS")
		public String regS = "";

		//<!-- 户籍地址-其他地址信息，非以上规则的地址时使用,同下面户籍地址-详细 -->
		@XmlTag(name = "RegOther")
		public String regOther = "";
		
		//户籍地址：详细地址
		@XmlTag(name = "RegDetail")
		public String regDetail= "";
		
		//必填。值：糖尿病ICD名称，ID：代码或编码
		@XmlTag(name = "ICD")
		public BeanID icd = null; 
		
		//必填。糖尿病类型诊断代码（单选），值为代码：1、I型糖尿病；2：II型糖尿病；3：妊娠糖尿病；4：其他特殊类型 
		@XmlTag(name = "DiagnoseCD")
		public String diagnoseCD= "";

		//<!--必填。糖尿病类型,文字值-->
		@XmlTag(name = "DiagnoseName")
		public String diagnoseName= "";

		//必填。并发症（多选），多个代码之间用英文|分隔，值为代码：0：无；1：肾脏病变；2：神经病变；3：血管病变；4：视网膜病变；5：皮肤感染
		@XmlTag(name = "SyndromeCD")
		public String syndromeCD= "";

		//<!--必填。并发症, 文字值-->
		@XmlTag(name = "SyndromeName")
		public String syndromeName= "";
	
		//必填。体重，单位：kg，整型
		@XmlTag(name = "Weight")
		public String weight= "";
		
		//必填。身高，单位：cm，整型
		@XmlTag(name = "Height")
		public String height= "";
		
		//体质指数，界面中自动计算。公式=体重（kg）/身高的平方（m2）。浮点
		@XmlTag(name = "BMI")
		public String bmi= "";
		
		//危险因素代码（多选），多个代码之间用英文|分隔，值为代码：1：肥胖；2：高血压；3：高血脂；4：巨大而分娩史
		@XmlTag(name = "DangerCD")
		public String dangerCD= ""; 

		//<!--危险因素,文字值-->
		@XmlTag(name = "DangerName")
		public String dangerName= ""; 

		//父母兄弟姐妹人数。整型
		@XmlTag(name = "FamilysNum")
		public String familysNum= "";

        // <!-- 是否是研究对象,0:否，1:是；值：数字 -->
        @XmlTag(name = "StudyObjectFlag")
        public int studyObjectFlag = 0;

        // <!-- 病存期 -->
        @XmlTag(name = "DiseasePeriod")
        public String diseasePeriod = "";
		
		//家族中有糖尿病史的（多选），多个代码之间用英文|分隔，值为代码：0：无；1：父；2、母；3：兄弟；4：姐妹 
		@XmlTag(name = "HistoryDMCD")
		public String historyDMCD= "";

		//<!--家族中有糖尿病史的, 文字值-->
		@XmlTag(name = "HistoryDMName")
		public String historyDMName= "";
		
		//必填。最高诊断单位代码（单选），值为代码：1、省级医院；2：市级医院；3：县(区)级医院；4：乡级医院；5：其他；6：不详
		@XmlTag(name = "DiagnoseUnitCD")
		public String diagnoseUnitCD= "";
		
		//<!--必填。最高诊断单位, 文字值-->
		@XmlTag(name = "DiagnoseUnitName")
		public String diagnoseUnitName = "";
		
		//必填。首次诊断日期，格式：yyyy-mm-dd
		@XmlTag(name = "FirstDate")
		public String firstDate= "";
		
		@XmlTag(name = "DutyDoctor")
        public BeanID dutyDoctor = null; // 责任医生
		
		//必填。报卡医生，值：医生姓名，ID：医生ID或代码 。
		@XmlTag(name = "ReportDoctor")
		public BeanID reportDoctor =null; 
		
		//必填。报卡日期，格式：yyyy-mm-dd
		@XmlTag(name = "ReportDate")
		public String reportDate= "";
		
		//必填。报卡单位。值：单位名称；ID：单位ID或代码
		@XmlTag(name = "ReportUnit")
		public BeanID reportUnit = null; 
		
		//死亡日期，格式：yyyy-mm-dd
		@XmlTag(name = "DeathDate")
		public String deathDate= "";
		
		//死亡原因代码（单选），值为代码：1、糖尿病；2：非糖尿病
		@XmlTag(name = "DeathReasonCD")
		public String deathReasonCD= "1";

		//<!-- 死亡原因, 文字值-->
		@XmlTag(name = "DeathReasonName")
		public String deathReasonName= "";
		
		//值：死亡ICD10名称，ID：代码 
		@XmlTag(name = "DeathICD10")
		public BeanID deathICD10 = new BeanID(); 
		
		//死亡ICD名称
		@XmlTag(name = "DeathICDName")
		public String deathICDName= "";
		
		//必填。临床表现代码（多选），多个代码之间用英文|分隔，值为代码：1、多饮、多尿；2：多食/常有饥饿感；3：乏力；4：体重下降；5：视力下降；6：肢体麻木；7：下肢浮肿；8：肢端溃疡；9：皮肤及外阴瘙痒；10：其他；11：无临床症状
		@XmlTag(name = "ClinicalCD")
		public String clinicalCD= "";

		//<!-- 必填。临床表现代码, 文字值，选择其他时，可为其他的具体内容-->
		@XmlTag(name = "ClinicalName")
		public String clinicalName= "";
		
		//其他临床表现
		@XmlTag(name = "ClinicalOther")
		public String clinicalOther= "";
		
		//必填。空腹血糖，单位：mmol/L，浮点
		@XmlTag(name = "FPG")
		public String fpg= "";
		
		//随机血糖，单位：mmol/L，浮点 
		@XmlTag(name = "RandGLU")
		public String randGLU= "";
		
		//OGTT试验，2小时血浆葡萄糖水平，单位：mmol/L，浮点 
		@XmlTag(name = "OGTT")
		public String ogtt= "";
		
		//总胆固醇，单位：mmol/L，浮点
		@XmlTag(name = "CHO")
		public String cho= "";
		
		//高密度脂蛋白胆固醇，单位：mmol/L，浮点 
		@XmlTag(name = "HDLC")
		public String hdlc= "";
		
		//低密度脂蛋白胆固醇，单位：mmol/L，浮点
		@XmlTag(name = "LDLC")
		public String ldlc= "";
		
		//甘油三酯，单位：mmol/L，浮点 
		@XmlTag(name = "TG")
		public String tg= "";
		
		//尿微量白蛋白，单位：mg/min，浮点
		@XmlTag(name = "mAlb")
		public String mAlb= "";
		
		//糖化血红蛋白，单位：mg/min，浮点 
		@XmlTag(name = "HbAlc")
		public String hbAlc= "";
		
		//备注
		@XmlTag(name = "Memo")
		public String memo= ""; 

		//数据来源, 有多条记录，因此会有多个DeviceUse节点
		@XmlTag(name = "DeviceUse",isListWithoutGroupTag=true)
		public List<DeviceUse> deviceUses = DeviceUseFactory.getDtoDeviceUses(getClass());
	}

	/**
	 * Response
	 * 
	 * @author Administrator
	 */
	@XmlTag(name="Response")
	public Response response = null;
	static public class Response implements IDto {
		@XmlAttribute(name = "ErrMsg")
		public String errMsg = "";
 
		//必填。个人档案号
		@XmlTag(name = "ResidentID")
		public String residentID= "";
		
		//必填。家庭档案号
		@XmlTag(name = "FamilyID")
		public String familyID= "";
		
		// 必填。居民姓名 
		@XmlTag(name = "ResidentName")
		public String residentName= "";
		
		//必填。性别代码（单选）。值为代码：0）未知；1）男；2）女；9）未说明性别
		@XmlTag(name = "SexCD")
		public String sexCD= "";
		
		//必填。出生日期。格式：yyyy-mm-dd
		@XmlTag(name = "BirthDay")
		public String birthDay= "";
		
		//居民本人联系电话
		@XmlTag(name = "SelfPhone")
		public String selfPhone= "";
		
		//必填。家庭地址
		@XmlTag(name = "Address")
		public String address= "";
		
		//必填。个人档案中身高或近期身高，新增管理卡时用。单位：cm，整型
		@XmlTag(name = "Height")
		public String height= "";
		
		//必填。个人档案中体重或近期体重，新增管理卡时用。单位：kg，浮点
		@XmlTag(name = "Weight")
		public String weight= ""; 
	}
}
