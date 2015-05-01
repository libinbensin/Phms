package com.cking.phss.dto;

import java.util.List;

import com.cking.phss.bean.BeanCD;
import com.cking.phss.bean.BeanID;
import com.cking.phss.bean.FamilyMainProblems;
import com.cking.phss.bean.FamilyMember;
import com.cking.phss.dto.innner.DeviceUse;
import com.cking.phss.global.Global;
import com.cking.phss.util.DeviceUseFactory;
import com.cking.phss.xml.util.XmlAttribute;
import com.cking.phss.xml.util.XmlTag;

public class BcjmjbxxhjtxxJ003 implements IDto {
    @XmlTag(name = "Request")
    public Request request = null;

    @XmlTag(name = "Response")
    public Response response = null;

    static public class Request implements IDto {
        @XmlAttribute(name = "OrgCode")
        public String orgCode = Global.orgCode;

        @XmlAttribute(name = "OperType")
        public String operType = "J003";

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
        public String residentName = "";

        // 性别
        @XmlTag(name = "SexCD")
        public int sexCD = 0;

        // 必填。性别代码
        @XmlTag(name = "SexName")
        public String sexName = "";

        @XmlTag(name = "BirthDay")
        public String birthDay = "";

        // 证件类型ID, CD：代码
        @XmlTag(name = "Credentials")
        public BeanCD credentials = new BeanCD();

        // 身份证号
        @XmlTag(name = "PaperNum")
        public String paperNum = "";

        // 地址类别// 值为代码：1、本县区；2：本市区其他县区；3：本省其他城市；4：外省；5：港澳台；6：外籍
        @XmlTag(name = "AddressType")
        public int addressType = 1;

        // --必填。住址类型,文字值--
        @XmlTag(name = "AddressTypeName")
        public String addressTypeName = "";

        // 常住地址户籍标志，常住地址是否户籍地址
        @XmlTag(name = "RegisterAddressFlag")
        public String registerAddressFlag = "";

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

        // 路ID或代码
        @XmlTag(name = "NowRoadCD")
        public String nowRoadCD = "";

        // 值：路名
        @XmlTag(name = "NowRoad")
        public String nowRoad = "";

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

        // 户籍地址-邮政编码，CD：代码
        @XmlTag(name = "RegPostCode")
        public String regPostCode = "";

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

        // 路 CD:ID或代码
        @XmlTag(name = "RegRoad")
        public BeanID regRoad = new BeanID();

        // <!-- 户籍地址-弄-->
        @XmlTag(name = "RegN")
        public String regN = "";

        // <!-- 户籍地址-号-->
        @XmlTag(name = "RegH")
        public String regH = "";

        // <!-- 户籍地址-室-->
        @XmlTag(name = "RegS")
        public String regS = "";

        // <!-- 户籍地址-其他地址信息，非以上规则的地址时使用,同下面户籍地址-详细 -->
        @XmlTag(name = "RegOther")
        public String regOther = "";

        // 户籍地址：详细地址
        // @XmlTag(name = "RegDetail")
        // public BeanID regDetail = null;

        @XmlTag(name = "RegDetail")
        public String regDetail = "";

        // 居住状况代码 1/永久 2/常久 3/临时 4/流动 5/其他
        @XmlTag(name = "ResideStatusCD")
        public String resideStatusCD = "";

        // <!--居住状况, 文字值-->
        @XmlTag(name = "ResideStatusName")
        public String resideStatusName = "";

        // 户口性质
        @XmlTag(name = "RegTypeCD")
        public int regTypeCD = 0;

        // <!--户口性质,文字值-->
        @XmlTag(name = "RegTypeName")
        public String regTypeName = "";

        // 户籍地址
        @XmlTag(name = "RegAddress")
        public String regAddress = "";

        // 工作单位
        @XmlTag(name = "WorkUnit")
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

        // 常住类型代码（单选），值为代码：1户籍；2非户籍
        @XmlTag(name = "ResideCD")
        public int resideCD = 0;

        // <!--常住类型代码,文字值-->
        @XmlTag(name = "ResideName")
        public String resideName = "";

        // 必填。ID：民族ID或代码，值：民族名称
        @XmlTag(name = "FolkCD")
        public BeanID folkCD = null;

        // 血型代码（单选），值为代码：1、A型；2、B型；3、O型；4、AB型；5、AB型的RH阴性；6：AB型的RH阳性； 7、不详
        @XmlTag(name = "BloodCD")
        public int bloodCD = -1;

        // 血型,文字值
        @XmlTag(name = "BloodName")
        public String bloodName = "";

        // <!-- Rh血型，CD：代码；1、Rh阴性；2、Rh阳性；3、不详 -->
        @XmlTag(name = "BloodRh")
        public BeanCD bloodRh = null;

        // 文化程度文化程度代码（单选），值为代码：10、研究生；20,大学本科；30,大学专科和专科学校；40,中专；50,技工学校；60,高中；70,初中；80,小学；90,文盲或半文盲；97,其他
        @XmlTag(name = "EducationCD")
        public int educationCD = 0;

        // --必填。文化程度-
        @XmlTag(name = "EducationName")
        public String educationName = "";

        // 必填。值：行业名称或职业类别；ID：行业或职业类别代码
        @XmlTag(name = "VocationCD")
        public BeanID vocationCD = null;

        // 婚姻状况值为代码：10,未婚；20,已婚；22,再婚；23,复婚；30,丧偶；40,离婚；90,未说明的婚姻状况
        @XmlTag(name = "MarriageCD")
        public int marriageCD = 10;

        // --必填。婚姻状况, 文字值--
        @XmlTag(name = "MarriageName")
        public String marriageName = "";

        // 保险类别代码（单选），值为代码：01,医保；02,商业医疗保险；03,大病统筹；04,新型农村合作医疗；05,城镇居民基本医疗保险；06,公费医疗；99,其他
        @XmlTag(name = "InsuranceCD")
        public String insuranceCD = "";

        // 保险类别, 文字值
        @XmlTag(name = "InsuranceName")
        public String insuranceName = "";

        // 保险号
        @XmlTag(name = "InsuranceNum")
        public String insuranceNum = "";

        // 是否签约 0:否；1:
        @XmlTag(name = "SignContract")
        public BeanCD signContract = null;

        // 签约时间
        @XmlTag(name = "SignDate")
        public String signDate = "";

        // 救助类别代码（单选），值为代码：1：低保对象；2：五保对象；3：特困残疾人
        @XmlTag(name = "AidCD")
        public int aidCD = 0;

        // 救助类别, 文字值
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
        public String zip = "";

        // 电子邮件
        @XmlTag(name = "Email")
        public String email = "";

        // QQ
        @XmlTag(name = "QQ")
        public String qQ = "";

        // MSN
        @XmlTag(name = "MSN")
        public String mSN = "";

        // 微信号
        @XmlTag(name = "Wechat")
        public String wechat = "";

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

        // 体重
        @XmlTag(name = "Weight")
        public String weight = "0";

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

        // 必填。个人档案状态,文字值
        @XmlTag(name = "FileStatusName")
        public String fileStatusName = "";

        // <!---->
        // <!--家庭部分-->
        // <!---->
        // <!--家庭纸质档案号 -->

        // <!--家庭纸质档案号 -->
        @XmlTag(name = "FamilyPaperArchives")
        public String familyPaperArchives = "";

        // <!-- 房东姓名 -->
        @XmlTag(name = "LandlordName")
        public String landlordName = "";

        // <!-- 房东电话 -->
        @XmlTag(name = "LandlordPhone")
        public String landlordPhone = "";

        // <!--家庭电话 -->
        @XmlTag(name = "FamilyTelephone")
        public String familyTelephone = "";

        // 必填。家庭类型代码，值为代码：1、单亲家庭；2：主干家庭；3：单人家庭；4：核心家庭；5：联合家庭；6：其他
        @XmlTag(name = "FamilyTypeCD")
        public String familyTypeCD = "";

        // 必填。家庭类型, 文字值
        @XmlTag(name = "FamilyTypeName")
        public String familyTypeName = "";

        // <!--居住类型, CD：1.户籍 2.非户籍 3.流动），其中默认1.户籍。 -->
        @XmlTag(name = "DwellType")
        public BeanCD dwellType = null;

        // <!--住房性质, CD包括（1自有、2租房），默认自有 -->
        @XmlTag(name = "HousingProperty")
        public BeanCD housingProperty = null;

        // <!-- 经济状况, CD：1 好 2 一般 3差 -->
        @XmlTag(name = "Economics")
        public BeanCD economics = null;

        // <!-- 家庭总收入 -->
        @XmlTag(name = "GrossIncome")
        public String GrossIncome = "";

        // <!-- 家庭总支出 -->
        @XmlTag(name = "GrossCharge")
        public String GrossCharge = "";

        // 家庭年人均收入代码（单选）。值为代码：1：＜1000元；2：1000～1999元；3：2000～3999元；4：4000～7999元；5：8000元以上
        @XmlTag(name = "IncomeCD")
        public int incomeCD = 0;

        // <!-- 家庭年人均收入,文字值 -->
        @XmlTag(name = "IncomeName")
        public String incomeName = "";

        // <!-- 保健合同 0:无；1:有 -->
        @XmlTag(name = "HealthCareContractFlag")
        public String healthCareContractFlag = "";

        // 必填。户属性代码（单选）。值为代码：1：五保户；2：贫困户；3：特困户；4：烈军属；5：一般农户；6：城镇居民
        @XmlTag(name = "HouseHoldCD")
        public String houseHoldCD = "";

        // <!--必填。户属性, 文字值-->
        @XmlTag(name = "HouseHoldName")
        public String houseHoldName = "";

        // 居住面积
        @XmlTag(name = "Area")
        public String area = "";

        // 人均居住面积
        @XmlTag(name = "AvgArea")
        public String avgArea = "";

        // <!-- 总人口 -->
        @XmlTag(name = "Population")
        public String population = "";

        // <!-- 现住人口 -->
        @XmlTag(name = "PopulationNow")
        public String populationNow = "";

        // <!-- 住房间数 -->
        @XmlTag(name = "HousingRooms")
        public String housingRooms = "";

        // 必填。房屋类型代码（单选）。值为代码：1：茅屋；2：木屋；3：砖瓦平房；4：砖瓦楼房；5：土屋；6：其他
        @XmlTag(name = "FloorTypeCD")
        public int floorTypeCD = 0;

        // <!--必填。房屋类型, 文字值-->
        @XmlTag(name = "FloorTypeName")
        public String floorTypeName = "";

        // 必填。厨房使用方式代码（单选）。值为代码：1：无；2：独用；3：合用
        @XmlTag(name = "KitchenUseCD")
        public int kitchenUseCD = 0;

        // <!--必填。厨房使用方式,文字值-->
        @XmlTag(name = "KitchenUseName")
        public String kitchenUseName = "";

        // <!--必填。住房采光；CD：1好、2一般、3差-->
        @XmlTag(name = "HousingLighting")
        public BeanCD housingLighting = null;

        // <!--必填。住房通风；CD：1好、2一般、3差-->
        @XmlTag(name = "HousingVentilation")
        public BeanCD housingVentilation = null;

        // <!--必填。住房保暖；CD：1好、2一般、3差-->
        @XmlTag(name = "HousingWarm")
        public BeanCD housingWarm = null;

        // <!--必填。空气湿度；CD：1好、2一般、3差-->
        @XmlTag(name = "AirHumidity")
        public BeanCD airHumidity = null;

        // <!--必填。卫生；CD：1好、2一般、3差-->
        @XmlTag(name = "HealthStatus")
        public BeanCD healthStatus = null;

        // <!--必填。水质状况；CD：1好、2一般、3差-->
        @XmlTag(name = "WaterStatus")
        public BeanCD waterStatus = null;

        // <!--必填。污水处理；CD包括（1无处理、2下水道、3深水坑）-->
        @XmlTag(name = "SewageTreatment")
        public BeanCD sewageTreatment = null;

        // <!--必填。文体设备；CD包括（1电视机、2收录机、3收音机、4卫生报刊、5其他报刊杂志、6体育锻炼用品、7网络宽带、8电脑、9其他）-->
        @XmlTag(name = "StylisticDevices")
        public BeanCD stylisticDevices = null;

        // <!--必填。排烟；CD：1好、2一般、3差-->
        @XmlTag(name = "SmokeRemoval")
        public BeanCD smokeRemoval = null;

        // 必填。厨房排风设施代码（单选），值为代码：1无 2油烟机 3换气扇 4烟囱
        @XmlTag(name = "KitchenFanCD")
        public int kitchenFanCD = 0;

        // <!--必填。厨房排风设施,文字值-->
        @XmlTag(name = "KitchenFanName")
        public String kitchenFanName = "";

        // 必填。饮水代码（单选），值为代码：1自来水 2经净化过滤的水 3井水 4河湖水 5塘水 6纯水或桶装水 7其他
        @XmlTag(name = "WaterCD")
        public int waterCD = 0;

        // <!--必填。饮水,文字值-->
        @XmlTag(name = "WaterName")
        public String waterName = "";

        // 必填。燃料类型代码（单选），值为代码：1液化气 2煤 3天然气 4沼气 5柴火 6其他
        @XmlTag(name = "FuelCD")
        public int fuelCD = 0;

        // <!--必填。燃料类型,文字值-->
        @XmlTag(name = "FuelName")
        public String fuelName = "";

        // 必填。卫生厕所代码（单选），值为代码：1：双瓮漏斗式；2：三联沼气池式；3：粪坑分集式；4：完整下水道式；5：水冲式；6：双坑交替式；7：三格式粪池
        @XmlTag(name = "SanToiletCD")
        public int sanToiletCD = 0;

        // <!--必填。卫生厕所,文字值-->
        @XmlTag(name = "SanToiletName")
        public String sanToiletName = "";

        // 必填。非卫生厕所代码（单选），值为代码：1：无； 2一格或二格粪池式 3马桶 4露天粪坑 5简易棚厕
        @XmlTag(name = "NotSanToiletCD")
        public int notSanToiletCD = 0;

        // <!--必填。非卫生厕,文字值-->
        @XmlTag(name = "NotSanToiletName")
        public String notSanToiletName = "";

        // 禽畜栏代码（单选），值为代码：1单设 2室内 3室外
        @XmlTag(name = "AnimalPlaceCD")
        public int animalPlaceCD = 0;

        // <!--禽畜栏,文字值-->
        @XmlTag(name = "AnimalPlaceName")
        public String animalPlaceName = "";

        // 垃圾处理代码（单选），值为代码：1：垃圾箱；2：自行处理；3：其他
        @XmlTag(name = "GarbageDealCD")
        public int garbageDealCD = 0;

        // <!--垃圾处理, 文字值-->
        @XmlTag(name = "GarbageDealName")
        public String garbageDealName = "";

        // 家用电器代码（多选），多个代码用英文|分隔，值为代码：1： 黑白电视；2：彩色电视；3：冰箱；4：空调；5：洗衣机；6：电脑
        @XmlTag(name = "ApplianceCD")
        public String applianceCD = "";

        // <!--家用电器代码,文字值-->
        @XmlTag(name = "ApplianceName")
        public String applianceName = "";

        // 交通工具（多选），多个代码用英文|分隔，值为代码：1：自行车；2：助动车；3：摩托车；4：汽车。
        @XmlTag(name = "Transport")
        public String transport = "";

        // <!--交通工具,文字值-->
        @XmlTag(name = "TransportName")
        public String transportName = "";

        // <!-- 家庭成员 -->
        @XmlTag(name = "FamilyMember")
        public FamilyMember familyMember = null;

        // <!-- 家庭主要问题 -->
        @XmlTag(name = "FamilyMainProblems")
        public FamilyMainProblems familyMainProblems = null;

        // 数据来源, 有多条记录，因此会有多个DeviceUse节点
        @XmlTag(name = "DeviceUse", isListWithoutGroupTag = true)
        public List<DeviceUse> deviceUses = DeviceUseFactory.getDtoDeviceUses(getClass());
    }

    /**
     * Response 如果有重复居民，则直接在Response节点ErrMsg属性反应其重复原因。同时无FamilyID、ResidentID节点
     * 如果保存成功则返回家庭档案号、个人档案号
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

        // ///家庭部分1：保存成功。2：有重复家庭 -
        @XmlTag(name = "ReturnType")
        public int returnType = 0;

        // 假如 ReturnType=1，则说明是保存成功后此家庭户主；ReturnType=2，则说明是重复家庭的户主
        @XmlTag(name = "Householder")
        public String householder = "";
    }

}
