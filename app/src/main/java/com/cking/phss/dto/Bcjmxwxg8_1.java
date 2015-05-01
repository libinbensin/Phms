package com.cking.phss.dto;

import java.util.List;

import com.cking.phss.bean.BeanCD;
import com.cking.phss.dto.innner.DeviceUse;
import com.cking.phss.global.Global;
import com.cking.phss.util.DeviceUseFactory;
import com.cking.phss.xml.util.XmlAttribute;
import com.cking.phss.xml.util.XmlTag;

/**
 * 8.1 保存居民行为习惯.xml
 * @author Administrator 
 */
public class Bcjmxwxg8_1 implements IDto{
	/**
	 * Request
	 */
	@XmlTag(name = "Request")
	public Request request = null; 
	static public class Request implements IDto {
		@XmlAttribute(name = "OrgCode")
		public String orgCode = Global.orgCode;

		@XmlAttribute(name = "OperType")
		public String operType = "8.1"; 
		
		// 操作用户ID
		@XmlTag(name = "UserID")
        public String userID = "";

		// 家庭档案号
		@XmlTag(name = "FamilyID")
		public String familyID ="";

		// 个人档案号
		@XmlTag(name = "ResidentID")
		public String residentID = "";
		
		//是否吸烟代码（单选）。值为代码：1：从不吸；2：过去吸，现在不吸；3：是的，但不是每天吸；4：是的，每天吸
		@XmlTag(name = "SmokeCD")
		public int smokeCD= 0;

		// 是否吸烟, 文字值
		@XmlTag(name = "SmokeName")
		public String smokeName = "";

		// 是否戒烟，值为代码：1：已戒烟；2：未戒烟
		@XmlTag(name = "QuitSmokingFlag")
        public BeanCD quitSmokingFlag = null;
		
		//开始吸烟时间，单位：岁，整型
		@XmlTag(name = "SmokeAge")
		public int smokeAge= 0;
		
		//戒烟时间，单位：岁，整型
		@XmlTag(name = "NoSmokeAge")
		public int noSmokeAge= 0;
		
		//日平均吸烟量。单位：支，整型
		@XmlTag(name = "SmokeDay")
		public int smokeDay= 0;
		
		//以往日平均吸烟量。单位：支，整型
		@XmlTag(name = "SmokeDayPast")
		public int smokeDayPast = 0;
		
		//是否饮酒代码（单选）。值为代码：1：从不喝；2：每天；3：少于1天/月；4：1～3天/月；5：1～2天/周；6：3～4天/周；7：5～6天/周 
		@XmlTag(name = "DrinkCD")
		public int drinkCD= 0;

		// 是否饮酒,文字值
		@XmlTag(name = "DrinkName")
		public String drinkName = "";
		
		//常饮酒类代码（单选）。值为代码：1：白酒（≥42度）；2：白酒（＜42度）；3：啤酒；4：黄酒、糯米酒；5：葡萄酒；6：其他
		@XmlTag(name = "DrinkTypeCD")
		public int drinkTypeCD= 0;

		// 是否饮酒,文字值
		@XmlTag(name = "DrinkTypeName")
		public String drinkTypeName = "";
		
		//饮酒量每次几两。单位：两/次，整型
		@XmlTag(name = "DrinkAmount")
		public int drinkAmount= 0;
		
		//是否戒酒代码（单选）。值为代码：1：已戒酒；2：未戒酒
		@XmlTag(name = "NoDrinkCD")
		public int noDrinkCD= 0;

		// 是否戒酒,文字值
		@XmlTag(name = "NoDrinkName")
		public String noDrinkName = "";
		
		//戒酒时间。单位：岁，整型
		@XmlTag(name = "NoDrinkAge")
		public int noDrinkAge= 0;
		
		//以往饮酒量每月几次。单位：次/月，整型
		@XmlTag(name = "PastDrinkNum")
		public int pastDrinkNum= 0;
		
		//以往饮酒量每次几两。单位：两/次，整型
		@XmlTag(name = "PastDrinkAmount")
		public int pastDrinkAmount= 0;
		
		//以往常饮酒类代码（单选）。值为代码：1：白酒（≥42度）；2：白酒（＜42度）；3：啤酒；4：黄酒、糯米酒；5：葡萄酒；6：其他
		@XmlTag(name = "PastDrinkTypeCD")
		public int pastDrinkTypeCD= 0;

		// 以往常饮酒类,文字值，选其他时可为其他的具体内容
		@XmlTag(name = "PastDrinkTypeName")
		public String pastDrinkTypeName = "";
		
		//饮食习惯（多选），多个代码之间用英文|分隔，值为代码：1：荤食为主；2：素食为主；3：嗜盐；4：嗜油；5：嗜糖；6：荤素均衡
		@XmlTag(name = "FoodCD")
		public String foodCD= "";

		// 饮食习惯,文字值
		@XmlTag(name = "FoodName")
		public String foodName = "";
		
		//每日刷牙频率代码（单选），值为代码：1：1次；2：2次；3：2次以上；4：不刷牙
		@XmlTag(name = "BrushTeethCD")
		public int brushTeethCD= 0;
		// 每日刷牙频率,文字值
		@XmlTag(name = "BrushTeethName")
		public String brushTeethName = "";
		
		//体育锻炼频率代码（单选），值为代码：1：少于1天/月；2：1～2天/周；3：1～3天/月；4：3～4天/周；5：5～6天/周；6：每天
		@XmlTag(name = "SportRateCD")
		public int sportRateCD= 0;

		// 体育锻炼频率,文字值
		@XmlTag(name = "SportRateName")
		public String sportRateName = "";
		
		//锻炼方式代码。值为代码：1：登山；2：跑步；3：快步走；4：其他 
		@XmlTag(name = "SportTypeCD")
		public int sportTypeCD= 0;

		// 锻炼方式,文字值hande-DATA，选其他时可为其他的具体内容
		@XmlTag(name = "SportTypeName")
		public String sportTypeName = "";
		
		//其他锻炼方式 
		@XmlTag(name = "SportTypeElse")
		public String sportTypeElse= "";
		
		//每次锻炼时间代码（单选）。值为代码：1：＜20分钟/天；2：20～40分钟/天；3：40分钟以上/天 
		@XmlTag(name = "SportTime")
		public int sportTime = 0;

		// 每次锻炼时间, 文字值
		@XmlTag(name = "SportTimeName")
		public String sportTimeName = "";
		
		//主要负性生活事件代码（多选），多个代码之间用英文|分隔，值为代码：1：目前独居；2：一年之内住院治疗；3：子女分家生活；4：失去亲人；5：丧偶（两年之内）；6：其他
		@XmlTag(name = "PrimaryEventCD")
		public String primaryEventCD= ""; 

		//主要负性生活事件,文字值, 选其他时可为其他的具体内容
		@XmlTag(name = "PrimaryEventName")
		public String primaryEventName = "";

		//数据来源, 有多条记录，因此会有多个DeviceUse节点
		@XmlTag(name = "DeviceUse",isListWithoutGroupTag=true)
		public List<DeviceUse> deviceUses = DeviceUseFactory.getDtoDeviceUses(getClass());
	}
	/**
	 * Response
	 */
	@XmlTag(name = "Response")
	public Response response = null;
	static public class Response implements IDto {
		@XmlAttribute(name = "ErrMsg")
		public String errMsg = "";

		@XmlTag(name = "FamilyID")
		public String familyID = "";

		@XmlTag(name = "ResidentID")
		public String residentID = "";
	}
	public void init(){
		/**
		 * Request
		 */
		this.request = null;
		this.request.orgCode = "8.1";
		this.request.operType = "8.1";
        this.request.userID = "1080";
		this.request.familyID = "1081";
		this.request.residentID = "1081";
		this.request.smokeCD = 1;
		this.request.smokeAge = 18;
		this.request.noSmokeAge = 15;
		this.request.smokeDay = 5;
		this.request.smokeDayPast =1;
		this.request.drinkTypeCD = 1;
		this.request.drinkAmount = 10; 
		this.request.noDrinkCD = 1;
		this.request.noDrinkAge = 23;
		this.request.pastDrinkNum = 12;
		this.request.pastDrinkAmount = 22;
		this.request.pastDrinkTypeCD = 11;
		this.request.foodCD = "1";
		this.request.brushTeethCD = 1;
		this.request.sportRateCD = 1;
		this.request.sportTypeCD = 1;
		this.request.sportTypeElse ="登山";
		this.request.sportTime = 1;
		this.request.primaryEventCD = "2";
		/**
		 * Response
		 */
		this.response = new Response();
		this.response.errMsg = "8.1";
		this.response.familyID = "8.1";
		this.response.residentID = "81";
	}
}
