package com.cking.phss.dto;
 
import android.provider.ContactsContract.CommonDataKinds.Relation;

import com.cking.phss.bean.BeanCD;
import com.cking.phss.bean.BeanID;
import com.cking.phss.global.Global;
import com.cking.phss.xml.util.XmlAttribute;
import com.cking.phss.xml.util.XmlTag;

/**
 * 保存居民基本信息接口
 * 
 * @author Administrator
 */
public class Bcjmjbxx8 implements IDto {
	@XmlTag(name = "Request")
	public Request request = null;

	@XmlTag(name = "Response")
	public Response response = null;

	static public class Request implements IDto {
		@XmlAttribute(name = "OrgCode")
		public String orgCode = Global.orgCode;

		@XmlAttribute(name = "OperType")
		public String operType = "8";

		// 存盘类型，1：新增存盘 2：编辑存盘
		@XmlTag(name = "Type")
		public int type = 0;

		// 操作用户ID
		@XmlTag(name = "UserID")
		public String userID = "";

		// 卡号 , 身份证刷卡为身份证号码
		@XmlTag(name = "CardID")
		public String cardID = "";

		// 家庭档案号
		@XmlTag(name = "FamilyID")
		public String familyID = "";

		// 个人档案号
		@XmlTag(name = "ResidentID")
		public String residentID = "";

		// 居民姓名
		@XmlTag(name = "ResidentName")
		public String residentName = "三";

		// 性别
		@XmlTag(name = "SexCD")
		public int sexCD = 0;

		//必填。性别代码
		@XmlTag(name = "SexName")
		public String sexName = "";

		// 出生日期
		@XmlTag(name = "BirthDay")
		public String birthDay = "";

		//  证件类型ID, CD：代码
		@XmlTag(name = "Credentials")
        public BeanCD credentials = null;

		// 身份证号
		@XmlTag(name = "PaperNum")
		public String paperNum = "";

		// 常住地址户籍标志，常住地址是否户籍地址
		@XmlTag(name = "RegisterAddressFlag")
		public String registerAddressFlag = "";

		// 居住状况代码 1/永久 2/常久 3/临时 4/流动 5/其他
		@XmlTag(name = "ResideStatusCD")
		public String resideStatusCD = "";

		//<!--居住状况, 文字值-->
		@XmlTag(name = "ResideStatusName")
		public String resideStatusName = "";

		// 户口性质
		@XmlTag(name = "RegTypeCD")
		public int regTypeCD = 0;

		//<!--户口性质,文字值-->
		@XmlTag(name = "RegTypeName")
		public String regTypeName = "";

		// 户籍地址
		@XmlTag(name = "RegAddress")
		public String regAddress = "";

		@XmlTag(name = "WorkUnit")
		// 工作单位
		public String workUnit = "";

		// 联系方式（本人电话）
		@XmlTag(name = "SelfPhone")
		public String selfPhone = "";

		// <!-- 手机号 -->
		@XmlTag(name = "MobilePhone")
		public String mobilePhone = "";

		// 联系人姓名
		@XmlTag(name = "RelaName")
		public String relaName = "";

		// 联系人电话
		@XmlTag(name = "RelaPhone")
		public String relaPhone = "";

		// 常驻类型代码（单选）:1户籍 2非户籍
		@XmlTag(name = "ResideCD")
		public int resideCD = 0;

		//<!--常住类型代码,文字值-->
		@XmlTag(name = "ResideName")
		public String resideName = "";

		// 民族
		@XmlTag(name = "FolkCD")
        public BeanID folkCD = null;

		// 血型代码：1、A型 2、B型 3、O型 4、AB型 5、AB型的RH阴性6、AB型的RH阳性
		@XmlTag(name = "BloodCD")
		public int bloodCD = -1;

		//血型,文字值
		@XmlTag(name = "BloodName")
		public String bloodName = "";

		//<!-- Rh血型，CD：代码；1、Rh阴性；2、Rh阳性；3、不详 -->
		@XmlTag(name = "BloodRh")
        public BeanCD bloodRh = null;

		// 文化程度文化程度代码（单选），值为代码：10、研究生；20,大学本科；30,大学专科和专科学校；40,中专；50,技工学校；60,高中；70,初中；80,小学；90,文盲或半文盲；97,其他
		@XmlTag(name = "EducationCD")
		public int educationCD = 0;

		// --必填。文化程度-
		@XmlTag(name = "EducationName")
		public String educationName = "";

		// 职业
		@XmlTag(name = "VocationCD")
        public BeanID vocationCD = null;

		// 婚姻状况值为代码：10,未婚；20,已婚；22,再婚；23,复婚；30,丧偶；40,离婚；90,未说明的婚姻状况
		@XmlTag(name = "MarriageCD")
		public int marriageCD = 10;

		// --必填。婚姻状况, 文字值--
		@XmlTag(name = "MarriageName")
		public String marriageName = "";

		// 保险类别值为代码：01,医保；02,商业医疗保险；03,大病统筹；04,新型农村合作医疗；05,城镇居民基本医疗保险；06,公费医疗；99,其他
		@XmlTag(name = "InsuranceCD")
		public String insuranceCD = "";

		//保险类别, 文字值
		@XmlTag(name = "InsuranceName")
		public String insuranceName = "";

		// 保险号
		@XmlTag(name = "InsuranceNum")
		public String insuranceNum = "";

		// 救助类别代码（单选），值为代码：1：低保对象；2：五保对象；3：特困残疾人
		@XmlTag(name = "AidCD")
		public int aidCD = 0;

		//救助类别, 文字值
		@XmlTag(name = "AidName")
		public String aidName = "";

		// 医疗费用支付方式,CD：代码
		@XmlTag(name = "MedicalFeePay")
        public BeanCD medicalFeePay = null;

		// 国籍
		@XmlTag(name = "NationalityCD")
        public BeanID nationalityCD = null;

		// 与户主关系
		@XmlTag(name = "Relation")
		public Relation relation = null;

		// 邮政编码
		@XmlTag(name = "ZIP")
		public String zip = "";

		// 电子邮件
		@XmlTag(name = "Email")
		public String email = "";

		// 纸质文档号
		@XmlTag(name = "ManualNm")
		public String manuaINm = "";

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

		// 责任医生
		@XmlTag(name = "DutyDoctor")
        public BeanID dutyDoctor = null;

		// 管理机构
		@XmlTag(name = "ManageOrg")
        public BeanID manageOrg = null;

		// 服务站点
		@XmlTag(name = "Station")
        public BeanID station = null;

		// 建档日期
		@XmlTag(name = "BuildDate")
		public String buildDate = "";

		// 建档人员
		@XmlTag(name = "Builder")
        public BeanID builder = null;

		// 建档单位
		@XmlTag(name = "BuildOrg")
        public BeanID buildOrg = null;
		
		// 身高
		@XmlTag(name = "Height")
		public String height = "0";

		// 体重
		@XmlTag(name = "Weight")
		public String weight ="0";

		// 体质指数体质指数，界面中自动计算。公式=体重（kg）/身高的平方（m2）
		@XmlTag(name = "BMI")
		public String bMI = "0";

		// 胸围
		@XmlTag(name = "Bust")
		public String bust = "0";

		// 臀围
		@XmlTag(name = "HIP")
		public String hIP = "0";

		// 腰围
		@XmlTag(name = "Waist")
		public String waist = "0";

		// 个人档案状态代码
		@XmlTag(name = "FileStatusCD")
		public int fileStatusCD = 0;
		// 省
        @XmlTag(name = "NowProvince")
        public BeanID nowProvince = new BeanID();

        // 城市
        @XmlTag(name = "NowCity")
        public BeanID nowCity = new BeanID();

        //
        @XmlTag(name = "NowDistrict")
        public BeanID nowDistrict = new BeanID();

        //
        @XmlTag(name = "NowStreet")
        public BeanID nowStreet = new BeanID();

        //
        @XmlTag(name = "NowZone")
        public BeanID nowZone = new BeanID();

        //
        @XmlTag(name = "NowRoad")
        public BeanID nowRoad = new BeanID();

        @XmlTag(name = "NowN")
        public String nowN = "";
        @XmlTag(name = "NowH")
        public String nowH = "";
        @XmlTag(name = "NowS")
        public String nowS = "";
        
        @XmlTag(name = "RegDetail")
        public String regDetail = "";
        
        @XmlTag(name = "NowDetail")
        public String nowDetail = "";
	}

	/**
	 * Response
	 * 
	 * @author Administrator
	 */
	static public class Response implements IDto {
		@XmlAttribute(name = "ErrMsg")
		public String errMsg = "";

		@XmlTag(name = "FamilyID")
		public String familyID = "";

		@XmlTag(name = "ResidentID")
		public String residentID = "";
	}

	public void init() {
		/**
//		 * Request
//		 */
//		this.request = new Request();
//		this.request.orgCode = "8";
//		this.request.type = 1;
//		this.request.userID = 8;
//		this.request.familyID = 1008;
//		this.request.residentID = "1008";
//		this.request.residentName = "安哥";
//		this.request.sexCD = 0;
//		this.request.birthDay = "1988-10-11";
//		this.request.paperNum = "452626198811102857";
//		this.request.resideStatusCD = 0;
//		this.request.regTypeCD = 0;
//		this.request.regAddress = "深圳市";
//		this.request.workUnit = "盈烨科技";
//		this.request.selfPhone = "467467";
//		this.request.relaName = "峰哥";
//		this.request.relaPhone = "075589897564";
//		this.request.resideCD = 1;
//		this.request.folkCD.folkID = 8;
//		this.request.bloodCD = 1;
//		this.request.educationCD = 10;
//		this.request.vocationCD.vocationID = 1008;
//		this.request.marriageCD = 10;
//		this.request.insuranceCD = "";
//		this.request.insuranceNum = 0;
//		this.request.aidCD = 1;
//		this.request.nationalityCD.nationalityID = 1008;
//		this.request.relation.zipID = 1268;
//		this.request.zip = "538671";
//		this.request.email = "xxx@163.com";
//		this.request.manuaINm = "1006";
//		this.request.dutyDoctor.douyDoctorID = 1238;
//		this.request.manageOrg.manageOrgID = 1248;
//		this.request.station.stationID = 8;
//		this.request.buildDate = "2012-02-11";
//		this.request.builder.builderID = 1248;
//		this.request.buildOrg.buildOrgID = 1208;
//		this.request.height = 171;
//		this.request.weight = "61";
//		this.request.bMI = "";
//		this.request.bust = 78;
//		this.request.hIP = 24;
//		this.request.waist = 34;
//		this.request.fileStatusCD = 3;
//		/**
//		 * Response
//		 */
//		this.response = new Response();
//		this.response.errMsg = "8";
//		this.response.familyID = 8;
//		this.response.residentID = 8;
	}
}
