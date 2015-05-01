package com.cking.phss.bean;

import java.util.List;

import com.cking.phss.xml.util.XmlTag;

/**
 * 居民家庭信息
 * 
 * @author AUS
 * 
 */
public class Jmjtxx implements IBean {

    // <!--家庭纸质档案号 -->
    @XmlTag(name = "FamilyPaperArchives")
    private String familyPaperArchives="";
    //<!-- 房东姓名 -->
    @XmlTag(name = "LandlordName")
    private String landlordName="";
    //<!-- 房东电话 -->
    @XmlTag(name = "LandlordPhone")
    private String landlordPhone="";
    //<!--家庭电话 -->
    @XmlTag(name = "FamilyTelephone")
    private String familyTelephone="";
    
	@XmlTag(name = "FamilyID")
	private String familyID=""; // 家庭档案号姓名，查询时用like
	
	@XmlTag(name = "Householder")
	private String householder="";// 户主姓名

	@XmlTag(name = "Address")
	private String address=""; // 家庭地址，具体所在的省份什么的区找个人信息里面的NowCIty等等地址
	
	@XmlTag(name = "Team")
	private String team=""; // 团队
	
	@XmlTag(name = "HomePhone")
	private String homePhone=""; // 家庭电话
	
	@XmlTag(name = "Resident")
	private List<Jmjbxx> resident;// 家庭成员列表

	@XmlTag(name = "FamilyTypeCD")
	private String familyTypeCD="05"; // 家庭类型代码，值为代码：1、单亲家庭；2：主干家庭；3：单人家庭；4：核心家庭；5：联合家庭；6：其他

    // <!--居住类型, CD：1.户籍 2.非户籍 3.流动，其中默认1.户籍。 -->
    @XmlTag(name = "DwellType")
    private BeanCD dwellType = null;
    // <!--住房性质, CD包括（1自有、2租房），默认自有 -->
    @XmlTag(name = "HousingProperty")
    private BeanCD housingProperty = null;
    // <!-- 经济状况, CD：1 好 2 一般 3差 -->
    @XmlTag(name = "Economics")
    private BeanCD economics = null;
    // <!-- 家庭总收入 -->
    @XmlTag(name = "GrossIncome")
    private String grossIncome = "";
    // <!-- 家庭总支出 -->
    @XmlTag(name = "GrossCharge")
    private String grossCharge = "";
    
	@XmlTag(name = "IncomeCD")
	private int incomeCD=5; // 家庭年人均收入代码（单选）。值为代码：1：＜1000元；2：1000～1999元；3：2000～3999元；4：4000～7999元；5：8000元以上

    // <!-- 保健合同 0:无；1:有 -->
    @XmlTag(name = "HealthCareContractFlag")
    private String gealthCareContractFlag = "0";
   
	@XmlTag(name = "HouseHoldCD")
	private String houseHoldCD="0"; // 户属性代码（单选）。值为代码：1：五保户；2：贫困户；3：特困户；4：烈军属；5：一般农户；6：城镇居民

	@XmlTag(name = "Area")
	private String area="0"; // 居住面积，浮点
	
	@XmlTag(name = "AvgArea")
	private String avgArea="0"; // 平均居住面积

    // <!-- 总人口 -->
    @XmlTag(name = "Population")
    private int population = 0;
    // <!-- 现住人口 -->
    @XmlTag(name = "PopulationNow")
    private int populationNow = 0;
    // <!-- 住房间数 -->
    @XmlTag(name = "HousingRooms")
    private int housingRooms = 0;
    
	@XmlTag(name = "FloorTypeCD")
	private int floorTypeCD=6; // 房屋类型代码（单选）。值为代码：1：茅屋；2：木屋；3：砖瓦平房；4：砖瓦楼房；5：土屋；6：钢筋混凝土;9：其他

    // <!--必填。住房采光；CD：1好、2一般、3差-->
    @XmlTag(name = "HousingLighting")
    private BeanCD housingLighting = null;
    // <!--必填。住房通风；CD：1好、2一般、3差-->
    @XmlTag(name = "HousingVentilation")
    private BeanCD housingVentilation = null;
    // <!--必填。住房保暖；CD：1好、2一般、3差-->
    @XmlTag(name = "HousingWarm")
    private BeanCD housingWarm = null;
    // <!--必填。空气湿度；CD：1好、2一般、3差-->
    @XmlTag(name = "AirHumidity")
    private BeanCD airHumidity = null;
    // <!--必填。卫生；CD：1好、2一般、3差-->
    @XmlTag(name = "HealthStatus")
    private BeanCD healthStatus = null;
    // <!--必填。水质状况；CD：1好、2一般、3差-->
    @XmlTag(name = "WaterStatus")
    private BeanCD waterStatus = null;
    // <!--必填。污水处理；CD包括（1无处理、2下水道、3深水坑）-->
    @XmlTag(name = "SewageTreatment")
    private BeanCD sewageTreatment = null;
    // <!--必填。文体设备；CD包括（1电视机、2收录机、3收音机、4卫生报刊、5其他报刊杂志、6体育锻炼用品、7网络宽带、8电脑、9其他）-->
    @XmlTag(name = "StylisticDevices")
    public BeanCD stylisticDevices = new BeanCD();

    // <!--必填。排烟；CD：1好、2一般、3差-->
    @XmlTag(name = "SmokeRemoval")
    private BeanCD smokeRemoval = null;
   
	@XmlTag(name = "KitchenUseCD")
	private int kitchenUseCD=-1; // 厨房使用方式代码（单选）。值为代码：1：无；2：独用；3：合用
	
	@XmlTag(name = "KitchenFanCD")
	private int kitchenFanCD=-1; // 厨房排风设施代码（单选），值为代码：1无 2油烟机 3换气扇 4烟囱
	
	@XmlTag(name = "WaterCD")
	private int waterCD=-1; // 饮水代码（单选），值为代码：1自来水 2经净化过滤的水 3井水 4河湖水 5塘水 6纯水或桶装水
							// 7其他
	@XmlTag(name = "FuelCD")
	private int fuelCD=-1; // 燃料类型代码（单选），值为代码：1液化气 2煤 3天然气 4沼气 5柴火 6其他
	
	@XmlTag(name = "SanToiletCD")
	private int sanToiletCD=-1; // 卫生厕所代码（单选），值为代码：1：双瓮漏斗式；2：三联沼气池式；3：粪坑分集式；4：完整下水道式；5：水冲式；6：双坑交替式；7：三格式粪池
	
	@XmlTag(name = "NotSanToiletCD")
	private int notSanToiletCD=-1; // 非卫生厕所代码（单选），值为代码：1：无； 2一格或二格粪池式 3马桶 4露天粪坑
								// 5简易棚厕
	@XmlTag(name = "AnimalPlaceCD")
	private int animalPlaceCD=-1; // 禽畜栏代码（单选），值为代码：1单设 2室内 3室外
	
	@XmlTag(name = "GarbageDealCD")
	private int garbageDealCD=-1; // 垃圾处理代码（单选），值为代码：1：垃圾箱；2：自行处理；3：其他
	
	@XmlTag(name = "ApplianceCD")
	private String applianceCD=""; // 家用电器代码（多选），多个代码用英文|分隔，值为代码：1：
								// 黑白电视；2：彩色电视；3：冰箱；4：空调；5：洗衣机；6：电脑
	
	@XmlTag(name = "Transport")
	private String transport=""; // 交通工具（多选），多个代码用英文|分隔，值为代码：1：自行车；2：助动车；3：摩托车；4：汽车。

    // <!-- 家庭成员 -->
    @XmlTag(name = "FamilyMember")
    public FamilyMember familyMember = null;

    //<!-- 家庭主要问题 -->
    @XmlTag(name = "FamilyMainProblems")
    public FamilyMainProblems familyMainProblems = null;
    
	public String getFamilyID() {
		return familyID;
	}

	public void setFamilyID(String familyID) {
		this.familyID = familyID;
	}

	public String getHouseholder() {
		return householder;
	}

	public void setHouseholder(String householder) {
		this.householder = householder;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public String getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	public List<Jmjbxx> getResident() {
		return resident;
	}

	public void setResident(List<Jmjbxx> resident) {
		this.resident = resident;
	}

	public String getFamilyTypeCD() {
		return familyTypeCD;
	}

	public void setFamilyTypeCD(String familyTypeCD) {
		this.familyTypeCD = familyTypeCD;
	}

	public int getIncomeCD() {
		return incomeCD;
	}

	public void setIncomeCD(int incomeCD) {
		this.incomeCD = incomeCD;
	}

	public String getHouseHoldCD() {
		return houseHoldCD;
	}

	public void setHouseHoldCD(String houseHoldCD) {
		this.houseHoldCD = houseHoldCD;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getAvgArea() {
		return avgArea;
	}

	public void setAvgArea(String avgArea) {
		this.avgArea = avgArea;
	}

	public int getFloorTypeCD() {
		return floorTypeCD;
	}

	public void setFloorTypeCD(int floorTypeCD) {
		this.floorTypeCD = floorTypeCD;
	}

	public int getKitchenUseCD() {
		return kitchenUseCD;
	}

	public void setKitchenUseCD(int kitchenUseCD) {
		this.kitchenUseCD = kitchenUseCD;
	}

	public int getKitchenFanCD() {
		return kitchenFanCD;
	}

	public void setKitchenFanCD(int kitchenFanCD) {
		this.kitchenFanCD = kitchenFanCD;
	}

	public FamilyMember getFamilyMember() {
        return familyMember;
    }

    public void setFamilyMember(FamilyMember familyMember) {
        this.familyMember = familyMember;
    }

    public FamilyMainProblems getFamilyMainProblems() {
        return familyMainProblems;
    }

    public void setFamilyMainProblems(FamilyMainProblems familyMainProblems) {
        this.familyMainProblems = familyMainProblems;
    }

    public int getWaterCD() {
		return waterCD;
	}

	public void setWaterCD(int waterCD) {
		this.waterCD = waterCD;
	}

	public int getFuelCD() {
		return fuelCD;
	}

	public void setFuelCD(int fuelCD) {
		this.fuelCD = fuelCD;
	}

	public int getSanToiletCD() {
		return sanToiletCD;
	}

	public void setSanToiletCD(int sanToiletCD) {
		this.sanToiletCD = sanToiletCD;
	}

	public int getNotSanToiletCD() {
		return notSanToiletCD;
	}

	public void setNotSanToiletCD(int notSanToiletCD) {
		this.notSanToiletCD = notSanToiletCD;
	}

	public int getAnimalPlaceCD() {
		return animalPlaceCD;
	}

	public void setAnimalPlaceCD(int animalPlaceCD) {
		this.animalPlaceCD = animalPlaceCD;
	}

	public int getGarbageDealCD() {
		return garbageDealCD;
	}

	public void setGarbageDealCD(int garbageDealCD) {
		this.garbageDealCD = garbageDealCD;
	}

	public String getApplianceCD() {
		return applianceCD;
	}

	public void setApplianceCD(String applianceCD) {
		this.applianceCD = applianceCD;
	}

	public String getTransport() {
		return transport;
	}

	public void setTransport(String transport) {
		this.transport = transport;
	}

    public String getFamilyPaperArchives() {
        return familyPaperArchives;
    }

    public void setFamilyPaperArchives(String familyPaperArchives) {
        this.familyPaperArchives = familyPaperArchives;
    }

    public String getLandlordName() {
        return landlordName;
    }

    public void setLandlordName(String landlordName) {
        this.landlordName = landlordName;
    }

    public String getLandlordPhone() {
        return landlordPhone;
    }

    public void setLandlordPhone(String landlordPhone) {
        this.landlordPhone = landlordPhone;
    }

    public String getFamilyTelephone() {
        return familyTelephone;
    }

    public void setFamilyTelephone(String familyTelephone) {
        this.familyTelephone = familyTelephone;
    }

    public BeanCD getDwellType() {
        return dwellType;
    }

    public void setDwellType(BeanCD dwellType) {
        this.dwellType = dwellType;
    }

    public BeanCD getHousingProperty() {
        return housingProperty;
    }

    public void setHousingProperty(BeanCD housingProperty) {
        this.housingProperty = housingProperty;
    }

    public BeanCD getEconomics() {
        return economics;
    }

    public void setEconomics(BeanCD economics) {
        this.economics = economics;
    }

    public String getGrossIncome() {
        return grossIncome;
    }

    public void setGrossIncome(String grossIncome) {
        this.grossIncome = grossIncome;
    }

    public String getGrossCharge() {
        return grossCharge;
    }

    public void setGrossCharge(String grossCharge) {
        this.grossCharge = grossCharge;
    }

    public String getGealthCareContractFlag() {
        return gealthCareContractFlag;
    }

    public void setGealthCareContractFlag(String gealthCareContractFlag) {
        this.gealthCareContractFlag = gealthCareContractFlag;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public int getPopulationNow() {
        return populationNow;
    }

    public void setPopulationNow(int populationNow) {
        this.populationNow = populationNow;
    }

    public int getHousingRooms() {
        return housingRooms;
    }

    public void setHousingRooms(int housingRooms) {
        this.housingRooms = housingRooms;
    }

    public BeanCD getHousingLighting() {
        return housingLighting;
    }

    public void setHousingLighting(BeanCD housingLighting) {
        this.housingLighting = housingLighting;
    }

    public BeanCD getHousingVentilation() {
        return housingVentilation;
    }

    public void setHousingVentilation(BeanCD housingVentilation) {
        this.housingVentilation = housingVentilation;
    }

    public BeanCD getHousingWarm() {
        return housingWarm;
    }

    public void setHousingWarm(BeanCD housingWarm) {
        this.housingWarm = housingWarm;
    }

    public BeanCD getAirHumidity() {
        return airHumidity;
    }

    public void setAirHumidity(BeanCD airHumidity) {
        this.airHumidity = airHumidity;
    }

    public BeanCD getHealthStatus() {
        return healthStatus;
    }

    public void setHealthStatus(BeanCD healthStatus) {
        this.healthStatus = healthStatus;
    }

    public BeanCD getWaterStatus() {
        return waterStatus;
    }

    public void setWaterStatus(BeanCD waterStatus) {
        this.waterStatus = waterStatus;
    }

    public BeanCD getSewageTreatment() {
        return sewageTreatment;
    }

    public void setSewageTreatment(BeanCD sewageTreatment) {
        this.sewageTreatment = sewageTreatment;
    }

    public BeanCD getStylisticDevices() {
        return stylisticDevices;
    }

    public void setStylisticDevices(BeanCD stylisticDevices) {
        this.stylisticDevices.setcD(stylisticDevices.getcD());
        this.stylisticDevices.setTagValue(stylisticDevices.getTagValue());
    }

    public BeanCD getSmokeRemoval() {
        return smokeRemoval;
    }

    public void setSmokeRemoval(BeanCD smokeRemoval) {
        this.smokeRemoval = smokeRemoval;
    }
}
