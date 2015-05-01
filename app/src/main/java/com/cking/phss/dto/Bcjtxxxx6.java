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
 * 6保存家庭详细信息.xml
 * 
 * @author Administrator
 * 
 */
public class Bcjtxxxx6 implements IDto {
	@XmlTag(name = "Request")
	public Request request = null;

	static public class Request implements IDto {
		@XmlAttribute(name = "OrgCode")
		public String OrgCode = Global.orgCode;
		@XmlAttribute(name = "OperType")
		public String operType = "6";

		// 存盘类型，1：新增存盘 2：编辑存盘
		@XmlTag(name = "Type")
		public int type = 0;

		// 用户ID-
		@XmlTag(name = "UserID")
		public String userID = "";

		// 家庭档案号。如果是新增，则无家庭档案号（新增时此ID由程序后台生成）
		@XmlTag(name = "FamilyID")
		public String familyID = "";

		@XmlTag(name = "Country")
        public BeanCD country = null;
		
		@XmlTag(name = "Province")
        public BeanID province = null;
		
		@XmlTag(name = "City")
        public BeanID city = null;
		
		@XmlTag(name = "District")
        public BeanID district = null;

		// 必填。街道或镇，值：街道名，ID：街道ID或代码
		@XmlTag(name = "Street")
        public BeanID street = null;

		// 必填。值：居委会名称、社区、村 ID：居委会ID
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

		@XmlTag(name = "Other")
		public String other = "";

		// 必填。家庭类型代码，值为代码：1、单亲家庭；2：主干家庭；3：单人家庭；4：核心家庭；5：联合家庭；6：其他
		@XmlTag(name = "FamilyTypeCD")
		public String familyTypeCD = "";

		//必填。家庭类型, 文字值 
		@XmlTag(name = "FamilyTypeName")
		public String familyTypeName = "";


		// 家庭年人均收入代码（单选）。值为代码：1：＜1000元；2：1000～1999元；3：2000～3999元；4：4000～7999元；5：8000元以上
		@XmlTag(name = "IncomeCD")
		public int incomeCD = 0;

		//<!-- 家庭年人均收入,文字值 -->
		@XmlTag(name = "IncomeName")
		public String incomeName = "";


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
		public int notSanToiletCD = 0;

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

		//数据来源, 有多条记录，因此会有多个DeviceUse节点
		@XmlTag(name = "DeviceUse",isListWithoutGroupTag=true)
		public List<DeviceUse> deviceUses = DeviceUseFactory.getDtoDeviceUses(getClass());

	}

	@XmlTag(name = "Response")
	public Response response = null;

	static public class Response implements IDto {
		@XmlAttribute(name = "ErrMsg")
		public String errMsg = "";

		// 返回类型。1：保存成功。2：有重复家庭
		@XmlTag(name = "ReturnType")
		public int returnType = 0;
		// 家庭档案号。假如 ReturnType=1，则说明是保存成功后此家庭ID；ReturnType=2，则说明是重复家庭的ID
		@XmlTag(name = "FamilyID")
		public String familyID = "";

		// 户主姓名。假如 ReturnType=1，则说明是保存成功后此家庭户主；ReturnType=2，则说明是重复家庭的户主
		@XmlTag(name = "Householder")
		public String householder = ""; 
	}
}
