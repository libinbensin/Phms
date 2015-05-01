package com.cking.phss.bean;

import com.cking.phss.xml.util.XmlTag;

/**
 * 居民基本信息
 * 
 * @author Administrator
 */

public class Jmjbxx implements IBean {
	@XmlTag(name = "ResidentID")
	private String residentID=""; // 个人档案号
	
	@XmlTag(name = "ResidentName")
	private String residentName=""; // 居民姓名
	
	@XmlTag(name = "SexCD")
	private int sexCD=0; // 性别代码  0 未知；1）男；2）女；9）未说明性别 -->
	
	@XmlTag(name = "BirthDay")
	private String birthDay=""; // 出生日期，格式yyyy-mm-dd
	
	@XmlTag(name ="Relation")
    private BeanID relation;// 与户主的关系
	
	
	@XmlTag(name = "PaperNum")
	private String paperNum=""; // 身份证号码
	
	// 卡号 , 身份证刷卡为身份证号码
	@XmlTag(name = "CardID")
	public String cardID = "";
	
	@XmlTag(name = "ResideStatusCD")
	private String resideStatusCD=""; // 居住状况代码  1/永久 2/常久 3/临时 4/流动 5/其他
	
	@XmlTag(name = "RegTypeCD")
	private int regTypeCD=-1; // 用户性质，1.农业户口 2.非农业户口 3.农转非
	
	
	@XmlTag(name = "RegAddress")
	private String regAddress=""; // 户籍地址
	
	
	@XmlTag(name = "WorkUnit")
	private String workUnit=""; // 工作单位
	
	
	@XmlTag(name = "SelfPhone")
	private String selfPhone=""; // 联系方式（本人电话）
	
	
	@XmlTag(name = "RelaName")
	private String relaName=""; // 联系人姓名
	
	@XmlTag(name = "RelaPhone")
	private String relaPhone=""; // 联系人电话
	
	
	@XmlTag(name = "ResideCD")
	private int resideCD=1; // 常驻类型代码（单选）:1户籍 2非户籍
	
	
	@XmlTag(name = "FolkCD")
    private BeanID flokCD;// 选择民族
	
	@XmlTag(name="BloodCD")
	private int bloodCD=-1; // 血型代码1、A型；2、B型；3、O型；4、AB型；5、AB型的RH阴性；6：AB型的RH阳性； 7、不详

	@XmlTag(name = "EducationCD")
	private int educationCD=-1; // 文化程度文化程度代码（单选），值为代码：10、研究生；20,大学本科；30,大学专科和专科学校；40,中专；50,技工学校；60,高中；70,初中；80,小学；90,文盲或半文盲；97,其他

	@XmlTag(name = "VocationCD")
    private BeanID vocationCD = new BeanID(4000, "不详");// 设置居民职业：不详
	
	// 值：具体工种名称，ID：具体工种代码
	@XmlTag(name = "WorkTypeCD")
	public BeanID workTypeCD = new BeanID();
	
	@XmlTag(name = "MarriageCD")
	private int marriageCD=-1; // 婚姻状况值为代码：10,未婚；20,已婚；22,再婚；23,复婚；30,丧偶；40,离婚；90,未说明的婚姻状况
	
	// 医疗费用支付方式代码（单选），值为代码：01,医保；02,商业医疗保险；03,大病统筹；04,新型农村合作医疗；05,城镇居民基本医疗保险；06,公费医疗；99,其他
	@XmlTag(name = "MedicalCD")
	public String medicalCD = "";
	
	@XmlTag(name = "InsuranceCD")
	private String insuranceCD=""; // 保险类别值为代码：01,医保；02,商业医疗保险；03,大病统筹；04,新型农村合作医疗；05,城镇居民基本医疗保险；06,公费医疗；99,其他
	
	@XmlTag(name = "InsuranceNum")
	private String insuranceNum="";// 保险号
	
	@XmlTag(name = "AidCD")
	private int aidCD=5; // 救助类别代码（单选），值为代码：1：低保对象；2：五保对象；3：特困残疾人 4、其他
	
	@XmlTag(name = "NationalityCD")
    private BeanID nationalityCD;
	
	@XmlTag(name = "Zip")
	private String zip=""; // 邮政编码：默认值为“315040”
	
	@XmlTag(name = "Email")
	private String email=""; // 电子邮件
	
	@XmlTag(name = "ManuaINm")
	private String manuaINm=""; // 纸质文档号

	@XmlTag(name = "DutyDoctor")
    private BeanID dutyDoctor; // 责任医生
	
	@XmlTag(name = "ManageOrg")
    private BeanID manageOrg; // 管理机构
	
	@XmlTag(name = "Station")
    private BeanID station; // 服务站点
	
	@XmlTag(name = "BuildDate")
	private String buildDate=""; // 建档日期
	
	@XmlTag(name = "Builder")
    private BeanID builder; // 建档人员
	
	@XmlTag(name = "BuildOrg")
    private BeanID buildOrg; // 建档单位

	@XmlTag(name = "FileStatusCD")
	private int fileStatusCD=0; // 个人档案状态代码值为状态代码：0.正常；1.注销；2.死亡；3.迁出(区域\外地)；4.挂起；5.临时
	
	@XmlTag(name = "AddressTypeCD")
	private int addressTypeCD=1;// 值为代码：1、本县区；2：本市区其他县区；3：本省其他城市；4：外省；5：港澳台；6：外籍

	
	
	
	@XmlTag(name = "NowProvince")
    private BeanID nowProvince; // 现居住的省
	
	@XmlTag(name = "NowCity")
    private BeanID nowCity; // 现居住的市
	
	@XmlTag(name = "NowDistrict")
    private BeanID nowDistrict; // 现居住的区
	
	@XmlTag(name = "NowStreet")
    private BeanID nowStreet; // 现居住的街道
	
	@XmlTag(name = "NowZone")
    private BeanID nowZone; // 现居住的社区
	
	@XmlTag(name = "NowRoad")
    private BeanID nowRoad; // 现居住的路
	
	@XmlTag(name = "NowN")
	private String nowN=""; // 现居住的弄
	
	@XmlTag(name = "NowH")
	private String nowH=""; // 现居住的号
	
	@XmlTag(name = "NowS")
	private String nowS=""; // 现居住的室

    @XmlTag(name = "NowOther")
    private String nowOther = ""; // 现居住的其他

	@XmlTag(name="NowDetail")
	private String nowDetail="";// 现居住地址
	
	
	@XmlTag(name = "RegProvince")
    private BeanID regProvince; // 现居住的省
	
	@XmlTag(name = "RegCity")
    private BeanID regCity; // 现居住的市
	
	@XmlTag(name = "RegDistrict")
    private BeanID regDistrict; // 现居住的区
	
	@XmlTag(name = "RegStreet")
    private BeanID regStreet; // 现居住的街道
	
	@XmlTag(name = "RegZone")
    private BeanID regZone; // 现居住的社区
	
	@XmlTag(name="RegDetail")
	private String regDetail="";// 现居住地址
	
    // <!--必填。户籍地址-国家，CD：代码 -->
    @XmlTag(name = "RegCountry")
    public BeanCD RegCountry = null;
    // <!--必填。户籍地址-省 -->
    @XmlTag(name = "RegProvince")
    public BeanID RegProvince = null;
    // <!--必填。户籍地址-市 -->
    @XmlTag(name = "RegCity")
    public BeanID RegCity = null;
    // <!--必填。户籍地址-区县 -->
    @XmlTag(name = "RegDistrict")
    public BeanID RegDistrict = null;
    // <!--必填。户籍地址-街道 -->
    @XmlTag(name = "RegStreet")
    public BeanID RegStreet = null;
    // <!--必填。户籍地址-社区/村 -->
    @XmlTag(name = "RegCommunity")
    public BeanID RegCommunity = null;
    // <!--路 CD:ID或代码 -->
    @XmlTag(name = "RegRoad")
    public BeanID RegRoad = null;
    // <!-- 户籍地址-弄-->
    @XmlTag(name = "RegLane")
    public String RegLane = null;
    // <!-- 户籍地址-号-->
    @XmlTag(name = "RegGroup")
    public String RegGroup = null;
    // <!-- 户籍地址-室-->
    @XmlTag(name = "RegRoom")
    public String RegRoom = null;
	
	@XmlTag(name="Weight")
	public String weight = "0"; 
	@XmlTag(name="Height")
	public String height = "0"; 
	@XmlTag(name="Bust")
	public String bust = "0"; 
	@XmlTag(name="BMI")
	public String bmi = "0";
	@XmlTag(name="Waist")
	public String waist = "0";
	@XmlTag(name="HTP")
	public String hIP="0";
	
	@XmlTag(name="RH")
	public int rh=-1;//默认为不详
	
	@XmlTag(name="Credentials")
	public int credentials=0;
	
	
	/**
	 * getter和setter
	 */
	public String getCardID() {
		return cardID;
	}

	public void setCardID(String cardID) {
		this.cardID = cardID;
	}
	
	public String getResidentID() {
		return residentID;
	}


	public void setResidentID(String residentID) {
		this.residentID = residentID;
	}


	public String getResidentName() {
		return residentName;
	}


	public void setResidentName(String residentName) {
		this.residentName = residentName;
	}


	public int getSexCD() {
		return sexCD;
	}


	public void setSexCD(int sexCD) {
		this.sexCD = sexCD;
	}


	public String getBirthDay() {
		return birthDay;
	}


	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}


    public BeanID getRelation() {
		return relation;
	}


    public void setRelation(BeanID relation) {
		this.relation = relation;
	}


	public String getPaperNum() {
		return paperNum;
	}


	public void setPaperNum(String paperNum) {
		this.paperNum = paperNum;
	}


	public String getResideStatusCD() {
		return resideStatusCD;
	}


	public void setResideStatusCD(String resideStatusCD) {
		this.resideStatusCD = resideStatusCD;
	}


	public int getRegTypeCD() {
		return regTypeCD;
	}


	public void setRegTypeCD(int regTypeCD) {
		this.regTypeCD = regTypeCD;
	}


	public String getRegAddress() {
		return regAddress;
	}


	public void setRegAddress(String regAddress) {
		this.regAddress = regAddress;
	}


	public String getWorkUnit() {
		return workUnit;
	}


	public void setWorkUnit(String workUnit) {
		this.workUnit = workUnit;
	}


	public String getSelfPhone() {
		return selfPhone;
	}


	public void setSelfPhone(String selfPhone) {
		this.selfPhone = selfPhone;
	}


	public String getRelaName() {
		return relaName;
	}


	public void setRelaName(String relaName) {
		this.relaName = relaName;
	}


	public String getRelaPhone() {
		return relaPhone;
	}


	public void setRelaPhone(String relaPhone) {
		this.relaPhone = relaPhone;
	}


	public int getResideCD() {
		return resideCD;
	}


	public void setResideCD(int resideCD) {
		this.resideCD = resideCD;
	}


    public BeanID getFlokCD() {
		return flokCD;
	}


    public void setFlokCD(BeanID flokCD) {
		this.flokCD = flokCD;
	}


	public int getBloodCD() {
		return bloodCD;
	}


	public void setBloodCD(int bloodCD) {
		this.bloodCD = bloodCD;
	}


	public int getEducationCD() {
		return educationCD;
	}


	public void setEducationCD(int educationCD) {
		this.educationCD = educationCD;
	}


    public BeanID getVocationCD() {
		return vocationCD;
	}

    public void setVocationCD(BeanID vocationCD) {
		this.vocationCD = vocationCD;
	}
	
	public BeanID getWorkTypeCD() {
		return workTypeCD;
	}

	public void setWorkTypeCD(BeanID workTypeCD) {
		this.workTypeCD = workTypeCD;
	}

	public int getMarriageCD() {
		return marriageCD;
	}


	public void setMarriageCD(int marriageCD) {
		this.marriageCD = marriageCD;
	}
	
	public String getMedicalCD() {
		return medicalCD;
	}


	public void setMedicalCD(String medicalCD) {
		this.medicalCD = medicalCD;
	}
	

	public String getInsuranceCD() {
		return insuranceCD;
	}


	public void setInsuranceCD(String insuranceCD) {
		this.insuranceCD = insuranceCD;
	}


	public String getInsuranceNum() {
		return insuranceNum;
	}


	public void setInsuranceNum(String insuranceNum) {
		this.insuranceNum = insuranceNum;
	}


	public int getAidCD() {
		return aidCD;
	}


	public void setAidCD(int aidCD) {
		this.aidCD = aidCD;
	}


    public BeanID getNationalityCD() {
		return nationalityCD;
	}


    public void setNationalityCD(BeanID nationalityCD) {
		this.nationalityCD = nationalityCD;
	}


	public String getZip() {
		return zip;
	}


	public void setZip(String zip) {
		this.zip = zip;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getManuaINm() {
		return manuaINm;
	}


	public void setManuaINm(String manuaINm) {
		this.manuaINm = manuaINm;
	}


    public BeanID getDutyDoctor() {
		return dutyDoctor;
	}


    public void setDutyDoctor(BeanID dutyDoctor) {
		this.dutyDoctor = dutyDoctor;
	}


    public BeanID getManageOrg() {
		return manageOrg;
	}


    public void setManageOrg(BeanID manageOrg) {
		this.manageOrg = manageOrg;
	}


    public BeanID getStation() {
		return station;
	}


    public void setStation(BeanID station) {
		this.station = station;
	}


	public String getBuildDate() {
		return buildDate;
	}


	public void setBuildDate(String buildDate) {
		this.buildDate = buildDate;
	}


    public BeanID getBuilder() {
		return builder;
	}


    public void setBuilder(BeanID builder) {
		this.builder = builder;
	}


    public BeanID getBuildOrg() {
		return buildOrg;
	}


    public void setBuildOrg(BeanID buildOrg) {
		this.buildOrg = buildOrg;
	}


	public int getFileStatusCD() {
		return fileStatusCD;
	}


	public void setFileStatusCD(int fileStatusCD) {
		this.fileStatusCD = fileStatusCD;
	}


	public int getAddressTypeCD() {
		return addressTypeCD;
	}


	public void setAddressTypeCD(int addressTypeCD) {
		this.addressTypeCD = addressTypeCD;
	}


    public BeanID getNowProvince() {
		return nowProvince;
	}


    public void setNowProvince(BeanID nowProvince) {
		this.nowProvince = nowProvince;
	}


    public BeanID getNowCity() {
		return nowCity;
	}


    public void setNowCity(BeanID nowCity) {
		this.nowCity = nowCity;
	}


    public BeanID getNowDistrict() {
		return nowDistrict;
	}


    public void setNowDistrict(BeanID nowDistrict) {
		this.nowDistrict = nowDistrict;
	}


    public BeanID getNowStreet() {
		return nowStreet;
	}


    public void setNowStreet(BeanID nowStreet) {
		this.nowStreet = nowStreet;
	}


    public BeanID getNowZone() {
		return nowZone;
	}


    public void setNowZone(BeanID nowZone) {
		this.nowZone = nowZone;
	}


    public BeanID getNowRoad() {
		return nowRoad;
	}


    public void setNowRoad(BeanID nowRoad) {
		this.nowRoad = nowRoad;
	}


	public String getNowN() {
		return nowN;
	}


	public void setNowN(String nowN) {
		this.nowN = nowN;
	}


	public String getNowH() {
		return nowH;
	}


	public void setNowH(String nowH) {
		this.nowH = nowH;
	}


	public String getNowS() {
		return nowS;
	}


	public void setNowS(String nowS) {
		this.nowS = nowS;
	}


    public String getNowOther() {
        return nowOther;
    }

    public void setNowOther(String nowOther) {
        this.nowOther = nowOther;
    }

	public String getNowDetail() {
		return nowDetail;
	}


	public void setNowDetail(String nowDetail) {
		this.nowDetail = nowDetail;
	}


    public BeanID getRegProvince() {
		return regProvince;
	}


    public void setRegProvince(BeanID regProvince) {
		this.regProvince = regProvince;
	}


    public BeanID getRegCity() {
		return regCity;
	}


    public void setRegCity(BeanID regCity) {
		this.regCity = regCity;
	}


    public BeanID getRegDistrict() {
		return regDistrict;
	}


    public void setRegDistrict(BeanID regDistrict) {
		this.regDistrict = regDistrict;
	}


    public BeanID getRegStreet() {
		return regStreet;
	}


    public void setRegStreet(BeanID regStreet) {
		this.regStreet = regStreet;
	}


    public BeanID getRegZone() {
		return regZone;
	}


    public void setRegZone(BeanID regZone) {
		this.regZone = regZone;
	}


	public String getRegDetail() {
		return regDetail;
	}


	public void setRegDetail(String regDetail) {
		this.regDetail = regDetail;
	}


	public String getWeight() {
		return weight;
	}


	public void setWeight(String weight) {
		this.weight = weight;
	}


	public String getHeight() {
		return height;
	}


	public void setHeight(String height) {
		this.height = height;
	}


	public String getBust() {
		return bust;
	}


	public void setBust(String bust) {
		this.bust = bust;
	}


	public String getBmi() {
		return bmi;
	}


	public void setBmi(String bmi) {
		this.bmi = bmi;
	}


	public String getWaist() {
		return waist;
	}


	public void setWaist(String waist) {
		this.waist = waist;
	}


	public String gethIP() {
		return hIP;
	}


	public void sethIP(String hIP) {
		this.hIP = hIP;
	}


    public int getRh() {
        return rh;
    }


    public void setRh(int rh) {
        this.rh = rh;
    }
    
    public int getCredentials() {
        return credentials;
    }


    public void setCredentials(int credentials) {
        this.credentials = credentials;
    }    
}
