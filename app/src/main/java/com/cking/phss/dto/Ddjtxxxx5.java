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

/**
 * 5得到家庭详细信息.xml
 * 
 * @author Administrator
 * 
 */
public class Ddjtxxxx5 implements IDto {
	@XmlTag(name = "Request")
	public Request request = null;

	static public class Request implements IDto {
		@XmlAttribute(name = "OrgCode")
		public String OrgCode = Global.orgCode;
		@XmlAttribute(name = "OperType")
		// 必填。值：当前登录用户工号
		public String operType = "5";
		
		@XmlTag(name="EmployeeNo")
        public BeanID employeeNo = null;

		// 必填。家庭档案号
		@XmlTag(name = "FamilyID")
		public String familyID = "";
	}

	@XmlTag(name = "Response")
	public Response response = null;

	static public class Response implements IDto {
		@XmlAttribute(name = "ErrMsg")
		public String errMsg = "";

		// 必填。此条记录是否只读。1：是；0：否
		@XmlTag(name = "ReadOnly")
		public int readOnly = 0;

		// 用户ID-
		@XmlTag(name = "UserID")
		public String userID = "";

		// 用户名称
		@XmlTag(name = "User")
		public String userName = "";

		// 必填。家庭档案号
		@XmlTag(name = "FamilyID")
		public String familyID = "";

	    //<!--家庭纸质档案号 -->
		@XmlTag(name = "FamilyPaperArchives")
		public String familyPaperArchives = "";

		//<!-- 房东姓名 -->
		@XmlTag(name = "LandlordName")
		public String landlordName = "";

		//<!-- 房东电话 -->
		@XmlTag(name = "LandlordPhone")
		public String landlordPhone = "";
		
		//<!--家庭电话 -->
		@XmlTag(name = "FamilyTelephone")
		public String familyTelephone = "";

		//<!-- 必填。国家，CD：代码 -->
		@XmlTag(name = "Country")
        public BeanCD country = null;

		//<!--必填。省 -->
		@XmlTag(name = "Province")
        public BeanID province = null;

		//<!--必填。市 -->
		@XmlTag(name = "City")
        public BeanID city = null;

		//<!--必填。区县 -->
		@XmlTag(name = "District")
        public BeanID district = null;

		// 必填。街道或镇，值：街道名，ID：街道ID或代码
		@XmlTag(name = "Street")
        public BeanID street = null;

		// 居委会名称、社区、村 ID：居委会ID
		@XmlTag(name = "Zone")
        public BeanID zone = null;

		// 路名
		@XmlTag(name = "Road")
        public BeanID road = null;

		// 弄
		@XmlTag(name = "N")
		public String n = "";

		// 号
		@XmlTag(name = "H")
		public String h = "";

		// 室，间
		@XmlTag(name = "S")
		public String s = "";

		//其他地址，非以上规则地址时使用
		@XmlTag(name = "Other")
		public String other = "";

		// 必填。家庭类型代码，值为代码：1、单亲家庭；2：主干家庭；3：单人家庭；4：核心家庭；5：联合家庭；6：其他
		@XmlTag(name = "FamilyTypeCD")
		public String familyTypeCD = "";

		//必填。家庭类型, 文字值 
		@XmlTag(name = "FamilyTypeName")
		public String familyTypeName = "";

		//<!--居住类型, CD：1.户籍 2.非户籍 3.流动），其中默认1.户籍。 -->
		@XmlTag(name = "DwellType")
        public BeanCD dwellType = null;

		//<!--住房性质, CD包括（1自有、2租房），默认自有 -->
		@XmlTag(name = "HousingProperty")
        public BeanCD housingProperty = null;

		//<!-- 经济状况, CD：1 好  2 一般  3差  -->
		@XmlTag(name = "Economics")
		public BeanCD economics = null;
		
		//<!-- 家庭总收入  -->
		@XmlTag(name = "GrossIncome")
		public String GrossIncome = "";

		//<!-- 家庭总支出  -->
		@XmlTag(name = "GrossCharge")
		public String GrossCharge = "";

		// 家庭年人均收入代码（单选）。值为代码：1：＜1000元；2：1000～1999元；3：2000～3999元；4：4000～7999元；5：8000元以上
		@XmlTag(name = "IncomeCD")
		public int incomeCD = 0;

	    //<!-- 家庭年人均收入,文字值 -->
		@XmlTag(name = "IncomeName")
		public String incomeName = "";

		//<!-- 保健合同 0:无；1:有 -->
		@XmlTag(name = "HealthCareContractFlag")
		public String healthCareContractFlag = "";

		// 必填。户属性代码（单选）。值为代码：1：五保户；2：贫困户；3：特困户；4：烈军属；5：一般农户；6：城镇居民
		@XmlTag(name = "HouseHoldCD")
		public String houseHoldCD = "";

		//<!--必填。户属性, 文字值-->
		@XmlTag(name = "HouseHoldName")
		public String houseHoldName = "";
		
		// 居住面积
		@XmlTag(name = "Area")
		public String area = "";

		// 人均居住面积
		@XmlTag(name = "AvgArea")
		public String avgArea = "";

		//<!-- 总人口 -->
		@XmlTag(name = "Population")
		public String population = "";

		//<!-- 现住人口 -->
		@XmlTag(name = "PopulationNow")
		public String populationNow = "";

		//<!-- 住房间数 -->
		@XmlTag(name = "HousingRooms")
		public String housingRooms = "";

		// 必填。房屋类型代码（单选）。值为代码：1：茅屋；2：木屋；3：砖瓦平房；4：砖瓦楼房；5：土屋；6：其他
		@XmlTag(name = "FloorTypeCD")
		public int floorTypeCD = 0;

	    //<!--必填。房屋类型, 文字值-->
		@XmlTag(name = "FloorTypeName")
		public String floorTypeName = "";

		// 必填。厨房使用方式代码（单选）。值为代码：1：无；2：独用；3：合用
		@XmlTag(name = "KitchenUseCD")
		public int kitchenUseCD = 0;

	    //<!--必填。厨房使用方式,文字值-->
		@XmlTag(name = "KitchenUseName")
		public String kitchenUseName = "";

		//<!--必填。住房采光；CD：1好、2一般、3差-->
		@XmlTag(name = "HousingLighting")
		public BeanCD housingLighting = null;

		//<!--必填。住房通风；CD：1好、2一般、3差-->
		@XmlTag(name = "HousingVentilation")
		public BeanCD housingVentilation = null;

		//<!--必填。住房保暖；CD：1好、2一般、3差-->
		@XmlTag(name = "HousingWarm")
		public BeanCD housingWarm = null;

		//<!--必填。空气湿度；CD：1好、2一般、3差-->
		@XmlTag(name = "AirHumidity")
		public BeanCD airHumidity = null;

		//<!--必填。卫生；CD：1好、2一般、3差-->
		@XmlTag(name = "HealthStatus")
		public BeanCD healthStatus = null;

		//<!--必填。水质状况；CD：1好、2一般、3差-->
		@XmlTag(name = "WaterStatus")
		public BeanCD waterStatus = null;

		//<!--必填。污水处理；CD包括（1无处理、2下水道、3深水坑）-->
		@XmlTag(name = "SewageTreatment")
		public BeanCD sewageTreatment = null;

		//<!--必填。文体设备；CD包括（1电视机、2收录机、3收音机、4卫生报刊、5其他报刊杂志、6体育锻炼用品、7网络宽带、8电脑、9其他）-->
		@XmlTag(name = "StylisticDevices")
		public BeanCD stylisticDevices = null;

		//<!--必填。排烟；CD：1好、2一般、3差-->
		@XmlTag(name = "SmokeRemoval")
		public BeanCD smokeRemoval = null;

		// 必填。厨房排风设施代码（单选），值为代码：1无 2油烟机 3换气扇 4烟囱
		@XmlTag(name = "KitchenFanCD")
		public int kitchenFanCD = 0;

	    //<!--必填。厨房排风设施,文字值-->
		@XmlTag(name = "KitchenFanName")
		public String kitchenFanName = "";

		// 必填。饮水代码（单选），值为代码：1自来水 2经净化过滤的水 3井水 4河湖水 5塘水 6纯水或桶装水 7其他
		@XmlTag(name = "WaterCD")
		public int waterCD = 0;

	    //<!--必填。饮水,文字值-->
		@XmlTag(name = "WaterName")
		public String waterName = "";

		// 必填。燃料类型代码（单选），值为代码：1液化气 2煤 3天然气 4沼气 5柴火 6其他
		@XmlTag(name = "FuelCD")
		public int fuelCD = 0;

	    //<!--必填。燃料类型,文字值-->
		@XmlTag(name = "FuelName")
		public String fuelName = "";

		// 必填。卫生厕所代码（单选），值为代码：1：双瓮漏斗式；2：三联沼气池式；3：粪坑分集式；4：完整下水道式；5：水冲式；6：双坑交替式；7：三格式粪池
		@XmlTag(name = "SanToiletCD")
		public int sanToiletCD = 0;

		//<!--必填。卫生厕所,文字值-->
		@XmlTag(name = "SanToiletName")
		public String sanToiletName = "";

		// 必填。非卫生厕所代码（单选），值为代码：1：无； 2一格或二格粪池式 3马桶 4露天粪坑 5简易棚厕
		@XmlTag(name = "NotSanToiletCD")
		public int NotSanToiletCD = 0;

		//<!--必填。非卫生厕,文字值-->
		@XmlTag(name = "NotSanToiletName")
		public String notSanToiletName = "";

		// 禽畜栏代码（单选），值为代码：1单设 2室内 3室外
		@XmlTag(name = "AnimalPlaceCD")
		public int animalPlaceCD = 0;

		//<!--禽畜栏,文字值-->
		@XmlTag(name = "AnimalPlaceName")
		public String animalPlaceName = "";

		// 垃圾处理代码（单选），值为代码：1：垃圾箱；2：自行处理；3：其他
		@XmlTag(name = "GarbageDealCD")
		public int garbageDealCD = 0;

		//<!--垃圾处理, 文字值-->
		@XmlTag(name = "GarbageDealName")
		public String garbageDealName = "";

		// 家用电器代码（多选），多个代码用英文|分隔，值为代码：1： 黑白电视；2：彩色电视；3：冰箱；4：空调；5：洗衣机；6：电脑
		@XmlTag(name = "ApplianceCD")
		public String applianceCD = "";

		//<!--家用电器代码,文字值-->
		@XmlTag(name = "ApplianceName")
		public String applianceName = "";

		// 交通工具（多选），多个代码用英文|分隔，值为代码：1：自行车；2：助动车；3：摩托车；4：汽车。
		@XmlTag(name = "Transport")
		public String transport = "";

		//<!--交通工具,文字值-->
		@XmlTag(name = "TransportName")
		public String transportName = "";

		// <!-- 家庭成员 -->
        @XmlTag(name = "FamilyMember")
        public FamilyMember familyMember = null;

		//<!-- 家庭主要问题 -->
		@XmlTag(name = "FamilyMainProblems")
		public FamilyMainProblems familyMainProblems = null;

		//数据来源, 有多条记录，因此会有多个DeviceUse节点
		@XmlTag(name = "DeviceUse",isListWithoutGroupTag=true)
		public List<DeviceUse> deviceUses = DeviceUseFactory.getDtoDeviceUses(getClass());
	}
}
