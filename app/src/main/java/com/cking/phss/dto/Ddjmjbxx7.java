package com.cking.phss.dto;

import java.util.List;

import com.cking.phss.bean.BeanCD;
import com.cking.phss.bean.BeanID;
import com.cking.phss.dto.innner.DeviceUse;
import com.cking.phss.global.Global;
import com.cking.phss.util.DeviceUseFactory;
import com.cking.phss.xml.util.XmlAttribute;
import com.cking.phss.xml.util.XmlTag;

/**
 * 7 得到居民基本信息.xml
 * 
 * @author Administrator
 * 
 */
public class Ddjmjbxx7 implements IDto {
	@XmlTag(name = "Request")
	public Request request = null;

	static public class Request implements IDto {
		@XmlAttribute(name = "OrgCode")
		public String OrgCode = Global.orgCode;
		@XmlAttribute(name = "OperType")
		public String operType = "7";
		// 必填。值：当前登录用户工号，ID：相应代码或ID
		@XmlTag(name = "EmployeeNo")
        public BeanID employeeNo = null;

		// 必填。个人档案号
		@XmlTag(name = "ResidentID")
		public String residentID = "";

		// 必填，家庭档案号
		@XmlTag(name = "FamilyID")
		public String familyID = "";
		
        // 必填，0正常1注销2死亡3迁出4挂起5临时
        @XmlTag(name = "Status")
        public String status = "";
	}

	@XmlTag(name = "Response")
	public Response response = null;

	static public class Response implements IDto {
		@XmlAttribute(name = "ErrMsg")
		public String errMsg = "";

		// 必填。此条记录是否只读。1：是；0：否
		@XmlTag(name = "ReadOnly")
		public int ReadOnly = 0;

		// 必填。用户ID
		@XmlTag(name = "UserID")
		public String userID = "";

		// 必填。用户名称
		@XmlTag(name = "User")
		public String userName = "";

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

		// 居住状况代码（单选）。值为代码：1：永久；2：常久；3：临时；4：流动；5：其他
		@XmlTag(name = "ResideStatusCD")
		public String resideStatusCD = "";

		//居住状况, 文字值
		@XmlTag(name = "ResideStatusName")
		public String resideStatusName = "";

		// 户口性质代码（单选）。 值为代码：1：农业户口；2：非农业户口；3：农转非
		@XmlTag(name = "RegTypeCD")
		public int regTypeCD = 0;

		//户口性质,文字值
		@XmlTag(name = "RegTypeName")
		public String regTypeName = "";

		// 户籍地址-邮政编码，CD：代码
		@XmlTag(name = "RegPostCode")
		public String regPostCode = "";

		// 户籍地址
		@XmlTag(name = "RegAddress")
		public String regAddress = "";

		// 常住地址户籍标志，常住地址是否户籍地址
		@XmlTag(name = "RegisterAddressFlag")
		public String registerAddressFlag = "";

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

		// 联系人姓名
		@XmlTag(name = "RelaName")
		public String relaName = "";

		// 联系人电话
		@XmlTag(name = "RelaPhone")
		public String RelaPhone = "";

		// 常住类型代码（单选），值为代码：1户籍；2非户籍
		@XmlTag(name = "ResideCD")
		public int resideCD = 0;
		
		//常住类型代码,文字值
		@XmlTag(name = "ResideName")
		public String resideName = "";		

		// 必填。ID：民族ID或代码，值：民族名称
		@XmlTag(name = "FolkCD")
        public BeanID folkCD = null;

		// 血型代码（单选），值为代码：1、A型；2、B型；3、O型；4、AB型；5、AB型的RH阴性；6：AB型的RH阳性； 7、不详
		@XmlTag(name = "BloodCD")
		public int bloodCD = -1;
		
		//血型,文字值
		@XmlTag(name = "BloodName")
		public String bloodName = "";
		
		//<!-- Rh血型，CD：代码；1、Rh阴性；2、Rh阳性；3、不详 -->
		@XmlTag(name = "BloodRh")
        public BeanCD bloodRh = null;

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
		
		// 保险类别代码（单选），值为代码：01,医保；02,商业医疗保险；03,大病统筹；04,新型农村合作医疗；05,城镇居民基本医疗保险；06,公费医疗；99,其他
		@XmlTag(name = "InsuranceCD")
		public String insuranceCD = "";

		//保险类别, 文字值
		@XmlTag(name = "InsuranceName")
		public String insuranceName = "";

		// 保险号
		@XmlTag(name = "InsuranceNum")
		public String insuranceNum = "";

		//是否签约 0:否；1:
		@XmlTag(name = "SignContract")
        public BeanCD signContract = null;
		//签约时间
		@XmlTag(name = "SignDate")
		public String signDate = "";
		
		// 救助类别代码（单选），值为代码：1：低保对象；2：五保对象；3：特困残疾人
		@XmlTag(name = "AidCD")
		public int aidCD = 0;

		//救助类别, 文字值
		@XmlTag(name = "AidName")
		public String aidName = "";

		// 医疗费用支付方式,CD：代码
		@XmlTag(name = "MedicalFeePay")
        public BeanCD medicalFeePay = null;
		
		// 必填。ID：国籍ID或代码，值：国籍名称
		@XmlTag(name = "NationalityCD")
        public BeanID nationalityCD = null;

		// 必填。值：与户主关系名称，ID：相应代码或ID
		@XmlTag(name = "Relation")
        public BeanID relation = null;

		// -邮政编码
		@XmlTag(name = "ZIP")
		public String ZIP = "";

		// 电子邮件
		@XmlTag(name = "Email")
		public String email = "";

		//QQ
		@XmlTag(name = "QQ")
		public String qQ = "";

		//MSN
		@XmlTag(name = "MSN")
		public String mSN = "";

		//微信号
		@XmlTag(name = "Wechat")
		public String wechat = "";


		// 纸质档案号
		@XmlTag(name = "ManualNm")
        public String manualNm = "";

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

		// 必填。值：管理机构，ID：相应代码或ID
		@XmlTag(name = "ManageOrg")
        public BeanID manageOrg = null;

		// 必填。值：服务站点，ID：相应代码或ID
		@XmlTag(name = "Station")
        public BeanID station = null;

		// 必填。建档日期，格式：yyyy-mm-dd
		@XmlTag(name = "BuildDate")
		public String buildDate = "";

		// 必填。值：建档人员。ID：相应代码或ID
		@XmlTag(name = "Builder")
        public BeanID builder = null;

		// 必填。值：建档单位，ID：相应代码或ID
		@XmlTag(name = "BuildOrg")
        public BeanID buildOrg = null;

		// 必填。身高。单位：cm，整型
		@XmlTag(name = "Height")
		public String height = "0";

		// 必填。体重。单位：kg，浮点
		@XmlTag(name = "Weight")
		public String weight = "0";

		// 必填。体质指数，界面中自动计算。公式=体重（kg）/身高的平方（m2）。浮点
		@XmlTag(name = "BMI")
		public String BMI = "";

		// 胸围。单位：cm，整型
		@XmlTag(name = "Bust")
		public String Bust = "0";

		// 臀围。 单位：cm，整型
		@XmlTag(name = "HIP")
		public String HIP = "0";

		// 腰围。单位：cm，整型
		@XmlTag(name = "Waist")
		public String waist = "0";

		// 必填。个人档案状态代码（单选）。值为状态代码：0.正常；1.注销；2.死亡；3.迁出(区域\外地)；4.挂起；5.临时
		@XmlTag(name = "FileStatusCD")
		public int fileStatusCD = 0;

		// 必填。个人档案状态,文字值
		@XmlTag(name = "FileStatusName")
		public String fileStatusName = "";

		//数据来源, 有多条记录，因此会有多个DeviceUse节点
		@XmlTag(name = "DeviceUse",isListWithoutGroupTag=true)
		public List<DeviceUse> deviceUses = DeviceUseFactory.getDtoDeviceUses(getClass());
	}
}
