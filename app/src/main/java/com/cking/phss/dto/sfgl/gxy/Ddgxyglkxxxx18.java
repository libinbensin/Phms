package com.cking.phss.dto.sfgl.gxy;

import java.util.List;

import com.cking.phss.bean.BeanCD;
import com.cking.phss.bean.BeanID;
import com.cking.phss.dto.IDto;
import com.cking.phss.dto.innner.DeviceUse;
import com.cking.phss.dto.innner.MedicineUse;
import com.cking.phss.global.Global;
import com.cking.phss.util.DeviceUseFactory;
import com.cking.phss.xml.util.XmlAttribute;
import com.cking.phss.xml.util.XmlTag;

/**
 * 18 得到高血压管理卡详细信息.xml
 * 
 * @author Administrator
 */
public class Ddgxyglkxxxx18 implements IDto {
	/**
	 * Request
	 */
	@XmlTag(name = "Request")
	public Request request = null;

	static public class Request implements IDto {
		@XmlAttribute(name = "OrgCode")
		public String orgCode = Global.orgCode;

		@XmlAttribute(name = "OperType")
		public String operType = "18";

		// 当前登录用户工号
        @XmlTag(name = "EmployeeNo")
        public BeanID employeeNo;
		
		// 必填。个人档案号
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

		// 必填。此条记录是否只读。1：是；0：否
		@XmlTag(name = "ReadOnly")
		public String readOnly = "";

		// 必填。用户ID
		@XmlTag(name = "UserID")
		public String userID = "";

		// 必填。用户名称
		@XmlTag(name = "User")
		public String userName = "";

		// 必填，个人档案号
		@XmlTag(name = "ResidentID")
		public String residentID = "";

		// 必填。居民姓名
		@XmlTag(name = "ResidentName")
		public String residentName = "";

		// 必填。性别代码（单选）。值为代码：0）未知；1）男；2）女；9）未说明性别
		@XmlTag(name = "SexCD")
		public String sexCD = "1";

		//必填。性别代码
		@XmlTag(name = "SexName")
		public String sexName = "";

		// 必填。出生日期。格式：yyyy-mm-dd
		@XmlTag(name = "BirthDay")
		public String birthDay = "";

		//  证件类型ID, CD：代码
		@XmlTag(name = "Credentials")
        public BeanCD credentials = null;

		// 必填。身份证号
		@XmlTag(name = "PaperNum")
		public String paperNum = "";

		// 工作单位
		@XmlTag(name = "WorkUnit")
		public String workUnit = "";

		// 必填。本人电话
		@XmlTag(name = "SelfPhone")
		public String selfPhone = "";

		// <!-- 手机号 -->
		@XmlTag(name = "MobilePhone")
		public String mobilePhone = "";

		// 必填。ID：民族ID或代码，值：民族名称
		@XmlTag(name = "FolkCD")
		public BeanID folkCD = null;

		// 必填。文化程度代码（单选），值为代码：10、研究生；20,大学本科；30,大学专科和专科学校；40,中专；50,技工学校；60,高中；70,初中；80,小学；90,文盲或半文盲；97,其他
		@XmlTag(name = "EducationCD")
		public String educationCD = "";

		// --必填。文化程度-
		@XmlTag(name = "EducationName")
		public String educationName = "";

		// 必填。值：行业名称；ID：行业代码
		@XmlTag(name = "VocationCD")
		public BeanID vocationCD = null;

		// 值：具体工种名称，ID：具体工种代码
		@XmlTag(name = "WorkTypeCD")
		public BeanID workTypeCD = null;

		// 必填。婚姻状况代码（单选），值为代码：10,未婚；20,已婚；22,再婚；23,复婚；30,丧偶；40,离婚；90,未说明的婚姻状况
		@XmlTag(name = "MarriageCD")
		public String marriageCD = "";

		// --必填。婚姻状况, 文字值--
		@XmlTag(name = "MarriageName")
		public String marriageName = "";

		// 医疗费用支付方式代码（单选），值为代码：01,医保；02,商业医疗保险；03,大病统筹；04,新型农村合作医疗；05,城镇居民基本医疗保险；06,公费医疗；99,其他
		@XmlTag(name = "MedicalCD")
		public String medicalCD = "";

		//<!--医疗费用支付方式,文字值-->
		public String medicalName = "";

		// 必填。住址类型代码（单选），值为代码：1、本县区；2：本市区其他县区；3：本省其他城市；4：外省；5：港澳台；6：外籍 -
		@XmlTag(name = "AddressTypeCD")
		public String addressTypeCD = "1";

		// --必填。住址类型,文字值--
		@XmlTag(name = "AddressTypeName")
		public String addressTypeName = "";

		// 必填。现住址省，值：省名，ID：省ID或代码
		@XmlTag(name = "NowCountry")
        public BeanCD nowCountry = new BeanCD();

		// 必填。现住址省，值：省名，ID：省ID或代码
		@XmlTag(name = "NowProvince")
        public BeanID nowProvince = new BeanID();

		// 必填。现住址市，值：市名，ID：市ID或代码
		@XmlTag(name = "NowCity")
        public BeanID nowCity = new BeanID();

		// 必填。现住址区县，值：区县名，ID：区县ID或代码
		@XmlTag(name = "NowDistrict")
        public BeanID nowDistrict = new BeanID();

		// 必填。现住址街道或镇，值：街道名，ID：街道ID或代码
		@XmlTag(name = "NowStreet")
        public BeanID nowStreet = new BeanID();

		// 必填。现住址居委、社区、村 值：居委名称 ID：居委ID或代码
		@XmlTag(name = "NowZone")
        public BeanID nowZone = new BeanID();

		// 现住址路，值：路；ID：路ID或代码
		@XmlTag(name = "NowRoad")
        public BeanID nowRoad = new BeanID();

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

		// 户籍地址-国家，CD：代码
		@XmlTag(name = "RegCountry")
        public BeanCD regCountry = new BeanCD();

		// 必填。户籍地址省，值：省名，ID：省ID或代码
		@XmlTag(name = "RegProvince")
        public BeanID regProvince = new BeanID();

		// 必填。户籍地址市，值：市名，ID：市ID或代码
		@XmlTag(name = "RegCity")
        public BeanID regCity = new BeanID();


		// 必填。户籍地址区县，值：区县名，ID：区县ID或代码
		@XmlTag(name = "RegDistrict")
        public BeanID regDistrict = new BeanID();

		// 必填。户籍地址街道或镇，值：街道名，ID：街道ID或代码
		@XmlTag(name = "RegStreet")
        public BeanID regStreet = new BeanID();

		// 户籍地址居委、社区、村 值：居委名称 ID：居委ID或代码
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

		// 户籍地址：详细地址
		@XmlTag(name = "RegDetail")
		public String regDetail = "";

		// 必填。最高诊断单位代码（单选），值为代码：1、省级医院；2：市级医院；3：县(区)级医院；4：乡级医院；5：其他；6：不详
		@XmlTag(name = "DiagnoseUnitCD")
		public String diagnoseUnitCD = "";

		//<!--必填。最高诊断单位, 文字值-->
		@XmlTag(name = "DiagnoseUnitName")
		public String diagnoseUnitName = "";

		// 必填。首次诊断日期，格式：yyyy-mm-dd
		@XmlTag(name = "FirstDate")
		public String firstDate = "";

		@XmlTag(name = "DutyDoctor")
		private BeanID dutyDoctor = null; // 责任医生

		// 必填。报卡医生，值：医生姓名，ID：医生ID或代码 。
		@XmlTag(name = "ReportDoctor")
		public BeanID reportDoctor = null;

		// 必填。报卡日期，格式：yyyy-mm-dd
		@XmlTag(name = "ReportDate")
		public String reportDate = "";

		// 必填。报卡单位。值：单位名称；ID：单位ID或代码
		@XmlTag(name = "ReportUnit")
		public BeanID reportUnit = null;

		// 必填。诊断收缩压，单位：mmHg，整型
		@XmlTag(name = "DSBP")
		public String dSBP = "";

		// 必填。诊断舒张压，单位：mmHg，整型
		@XmlTag(name = "DDBP")
		public String dDBP = "";

		// 界面中根据诊断收缩压、舒张压自动计算,1级：(收缩压≥140 and 收缩压≤159) or (舒张压≥90 and
		// 舒张压≤99)；2级：(收缩压≥160 and 收缩压≤179) or (舒张压≥100 and 舒张压≤109)；3级：收缩压≥180
		// or 舒张压≥110
		@XmlTag(name = "HBPLevelCD")
		public String hBPLevelCD = "";

		//<!--必填。血压级别, 文字值-->
		@XmlTag(name = "HBPLevelName")
		public String hBPLevelName = "";

		// 高血压类型代码（单选）。值为代码：1：原发性；2：继发性；3：不详
		@XmlTag(name = "HBPTypeCD")
		public String hBPTypeCD = "";

		//<!--高血压类型,文字值-->
		@XmlTag(name = "HBPTypeName")
		public String hBPTypeName = "";

		// 个人史
		@XmlTag(name = "Person")
		public String person = "";

		// 家族史代码（多选），多个代码之间用英文|分隔，值为代码：1：高血压；2：糖尿病；3：冠心病；4：脑卒中；5：以上都无。
		@XmlTag(name = "FamilyCD")
		public String familyCD = "";

		//<!--家族史-->
		@XmlTag(name = "FamilyName")
		public String familyName = "";

		// 过敏史代码（单选），值为代码：1：有；2：无
		@XmlTag(name = "HyperCD")
		public String hyperCD = "";

		// 如果有过敏史，则此处是填详细情况
		@XmlTag(name = "HyperDetail")
		public String hyperDetail = "";

		// 身高，单位：cm，整型
		@XmlTag(name = "Height")
		public String height = "";

		// 体重，单位：kg，浮点型
		@XmlTag(name = "Weight")
		public String weight = "";

		// 脉搏，单位：次/分，整型
		@XmlTag(name = "Pulse")
		public String pulse ="";

		// 心率，单位： 次/分，整型
		@XmlTag(name = "HR")
		public String hR = "";

		// 最近一次检查收缩压，单位：mmHg，整型
		@XmlTag(name = "LSBP")
		public String lSBP = "";

		// 最近一次检查舒张压，单位：mmHg，整型
		@XmlTag(name = "LDBP")
		public String lDBP = "";

		// 血糖，单位：mmol/L，浮点
		@XmlTag(name = "GLU")
		public String gLU = "";

		// 甘油三酯，单位：mmol/L，浮点
		@XmlTag(name = "TG")
		public String tG = "";

		// 总胆固醇，单位：mmol/L，浮点
		@XmlTag(name = "CHO")
		public String cHO = "";

		// 尿微量白蛋白，单位：mg/24小时，浮点
		@XmlTag(name = "mAlb")
		public String mAlb = "";

		// 高/低密度脂蛋白胆固醇，单位：mmol/L，浮点
		@XmlTag(name = "DLC")
		public String dLC = "";

		// 视网膜病变代码（单选），值为代码：1：有；2：无-
		@XmlTag(name = "RetinaCD")
		public String retinaCD = "";

		// 心电图检查结果
		@XmlTag(name = "ECG")
		public String eCG = "";

		// 其他检查
		@XmlTag(name = "OtherCheck")
		public String otherCheck = "";

		// 近期药物治疗情况代码（单选），值为代码：1：使用；2：未使用
		@XmlTag(name = "MedicineUseCD")
		public String medicineUseCD = "";

		// 限盐饮食代码（单选），值为代码：1：有；2：无
		@XmlTag(name = "FoodCD")
		public String foodCD = "";

		// 体力活动代码（单选），值为代码：1：有；2：无
		@XmlTag(name = "SportCD")
		public String sportCD = "";

		// 戒烟代码（单选），值为代码：1：有；2：无
		@XmlTag(name = "NoSmokeCD")
		public String noSmokeCD = "";

		// 限酒代码（单选），值为代码：1：有；2：无
		@XmlTag(name = "LimitDrinkCD")
		public String limitDrinkCD ="";

		// 备注
		@XmlTag(name = "Memo")
		public String memo = "";

		/**
		 * 有多条用药情况记录，因此会有多个MedicineUse节点
		 */
        @XmlTag(name = "MedicineUse", isListWithoutGroupTag = true)
		public List<MedicineUse> medicineUseList = null;

		/*
		 * <!--此节点只有新增保存时有。高血压危险分层因素（多选），多个代码之间用英文|分隔，值为代码：
		 * 1、男性＞55岁或女性＞65岁；2、吸烟；3：缺乏体力活动；4：超重或肥胖；5：早发心血管疾病家族史；6：血脂异常
		 * 7、左心室肥厚(心电图、超声心动图或X线)；8、颈动脉内膜增厚，斑块；9：肾功能受损；10：糖尿病
		 * 11、缺血性卒中；12：脑出血；13：短暂性脑缺血发作(TIA)；
		 * 14、心肌梗死；15：心绞痛；16：充血心力衰竭；17：冠状动脉血运重建
		 * 18：高血压性肾病；19：肾功能衰竭；20：夹层动脉瘤；21：症状性动脉疾病；22：出血或渗出；23：视乳头水肿-->
		 */
		@XmlTag(name = "DangerCause")
		public String dangerCause = "";

		//<!--高血压危险分层因素-->
		@XmlTag(name = "DangerCauseName")
		public String dangerCauseName = "";

		//数据来源, 有多条记录，因此会有多个DeviceUse节点
		@XmlTag(name = "DeviceUse",isListWithoutGroupTag=true)
		public List<DeviceUse> deviceUses = DeviceUseFactory.getDtoDeviceUses(getClass());
	}
}
