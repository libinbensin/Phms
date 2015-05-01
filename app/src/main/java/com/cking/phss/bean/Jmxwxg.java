package com.cking.phss.bean;

import com.cking.phss.xml.util.XmlTag;

/**
 * 居民行为习惯 
 * @author AUS 
 */
public class Jmxwxg implements IBean { 
	@XmlTag(name = "SmokeCD")
	private int smokeCD=1;// 是否吸烟代码（单选）。值为代码：1：是的，每天吸 ；2：从不吸 ；3：过去吸，现在不吸；4：是的，但不是每天吸
	
	@XmlTag(name = "SmokeAge")
	private int smokeAge=0;// 开始吸烟时间，单位：岁，整型
	
	@XmlTag(name = "NoSmokeAge")
	private int noSmokeAge=0;// 戒烟时间，单位：岁，整型
	
	@XmlTag(name = "SmokeDay")
	private int smokeDay=0;// 日平均吸烟量。单位：支，整型
	
	@XmlTag(name = "SmokeDayPast")
	private int smokeDayPast=0;// 以往日平均吸烟量。单位：支，整型
	
	@XmlTag(name = "DrinkCD")
	private int drinkCD=1;// 是否饮酒代码（单选）。值为代码：1：从不喝；  2：每天；    3：5～6天/周     4：3～4天/周；   5：1～2天/周；  6：1～3天/月；  9：少于1天/月；
	
	@XmlTag(name = "DrinkTypeCD")
	private int drinkTypeCD=1;// 常饮酒类代码（单选）。值为代码：1：白酒（≥42度）；2：白酒（＜42度）；3：啤酒；4：黄酒、糯米酒；5：葡萄酒；9：其他
	
	@XmlTag(name = "DrinkAmount")
	private int drinkAmount=0;// 饮酒量每次几两。单位：两/次，整型
	
	@XmlTag(name = "NoDrinkCD")
	private int noDrinkCD=1;// 是否戒酒代码（单选）。值为代码：1：未戒酒  2：已戒酒；
	
	@XmlTag(name = "NoDrinkAge")
	private int noDrinkAge=0;// 戒酒时间。单位：岁，整型
	
	@XmlTag(name = "PastDrinkNum")
	private int pastDrinkNum=0;// 以往饮酒量每月几次。单位：次/月，整型
	
	@XmlTag(name = "PastDrinkAmount")
	private int pastDrinkAmount=0;// 以往饮酒量每次几两。单位：两/次，整型
	
	@XmlTag(name = "PastDrinkTypeCD")
	private int pastDrinkTypeCD=1;// 以往常饮酒类代码（单选）。值为代码：1：白酒（≥42度）；2：白酒（＜42度）；3：啤酒；4：黄酒、糯米酒；5：葡萄酒；9：其他
	
	@XmlTag(name = "FoodCD")
	private String foodCD="1";// 饮食习惯（多选），多个代码之间用英文|分隔，值为代码1：荤素均衡       2：荤食为主；   3：素食为主；4：嗜盐；5：嗜油；6：嗜糖
	
	@XmlTag(name = "BrushTeethCD")
	private int brushTeethCD=1;// 每日刷牙频率代码（单选），值为代码：1：不刷牙  2：1次；3：2次；4：2次以上；
	
	@XmlTag(name = "SportRateCD")
	private int sportRateCD=1;// -
							// 体育锻炼频率代码（单选），值为代码：1：每天； 2：5～6天/周；     3：3～4天/周；      4：少于1天/月；   5：1～3天/月；6：1～2天/周；
	
	@XmlTag(name = "SportTypeCD")
	private int sportTypeCD=1;// 锻炼方式代码。值为代码：1：快步走；  2：登山；3：跑步；4：其他
	
	@XmlTag(name = "SportTypeElse")
	private String sportTypeElse="";// 其他锻炼方式
	
	@XmlTag(name = "SportTime")
	private int sportTime=1;// 每次锻炼时间代码（单选）。值为代码：1：＜20分钟/天；2：20～40分钟/天；3：40分钟以上/天
	
	@XmlTag(name = "PrimaryEvent")
	private String primaryEvent="";// 负性生活事件主要负性生活事件代码（多选），多个代码之间用英文|分隔，值为代码：1：目前独居；2：一年之内住院治疗；3：子女分家生活；4：失去亲人；5：丧偶（两年之内）；6：其他

    @XmlTag(name = "PrimaryEventName")
    private String primaryEventName = "";// 负性生活事件主要负性生活事件代码（多选），多个代码之间用英文|分隔，值为代码：1：目前独居；2：一年之内住院治疗；3：子女分家生活；4：失去亲人；5：丧偶（两年之内）；6：其他

	public int getSmokeCD() {
		return smokeCD;
	}

	public void setSmokeCD(int smokeCD) {
		this.smokeCD = smokeCD;
	}

	public int getSmokeAge() {
		return smokeAge;
	}

	public void setSmokeAge(int smokeAge) {
		this.smokeAge = smokeAge;
	}

	public int getNoSmokeAge() {
		return noSmokeAge;
	}

	public void setNoSmokeAge(int noSmokeAge) {
		this.noSmokeAge = noSmokeAge;
	}
	

	public int getSmokeDay() {
		return smokeDay;
	}

	public void setSmokeDay(int smokeDay) {
		this.smokeDay = smokeDay;
	}

	public int getSmokeDayPast() {
		return smokeDayPast;
	}

	public void setSmokeDayPast(int smokeDayPast) {
		this.smokeDayPast = smokeDayPast;
	}

	public int getDrinkCD() {
		return drinkCD;
	}

	public void setDrinkCD(int drinkCD) {
		this.drinkCD = drinkCD;
	}

	public int getDrinkTypeCD() {
		return drinkTypeCD;
	}

	public void setDrinkTypeCD(int drinkTypeCD) {
		this.drinkTypeCD = drinkTypeCD;
	}

	public int getDrinkAmount() {
		return drinkAmount;
	}

	public void setDrinkAmount(int drinkAmount) {
		this.drinkAmount = drinkAmount;
	}

	public int getNoDrinkCD() {
		return noDrinkCD;
	}

	public void setNoDrinkCD(int noDrinkCD) {
		this.noDrinkCD = noDrinkCD;
	}

	public int getNoDrinkAge() {
		return noDrinkAge;
	}

	public void setNoDrinkAge(int noDrinkAge) {
		this.noDrinkAge = noDrinkAge;
	}

	public int getPastDrinkNum() {
		return pastDrinkNum;
	}

	public void setPastDrinkNum(int pastDrinkNum) {
		this.pastDrinkNum = pastDrinkNum;
	}

	public int getPastDrinkAmount() {
		return pastDrinkAmount;
	}

	public void setPastDrinkAmount(int pastDrinkAmount) {
		this.pastDrinkAmount = pastDrinkAmount;
	}

	public int getPastDrinkTypeCD() {
		return pastDrinkTypeCD;
	}

	public void setPastDrinkTypeCD(int pastDrinkTypeCD) {
		this.pastDrinkTypeCD = pastDrinkTypeCD;
	}

	public String getFoodCD() {
		return foodCD;
	}

	public void setFoodCD(String foodCD) {
		this.foodCD = foodCD;
	}

	public int getBrushTeethCD() {
		return brushTeethCD;
	}

    public String getPrimaryEventName() {
        return primaryEventName;
    }

    public void setPrimaryEventName(String primaryEventName) {
        this.primaryEventName = primaryEventName;
    }

    public void setBrushTeethCD(int brushTeethCD) {
		this.brushTeethCD = brushTeethCD;
	}

	public int getSportRateCD() {
		return sportRateCD;
	}

	public void setSportRateCD(int sportRateCD) {
		this.sportRateCD = sportRateCD;
	}

	public int getSportTypeCD() {
		return sportTypeCD;
	}

	public void setSportTypeCD(int sportTypeCD) {
		this.sportTypeCD = sportTypeCD;
	}

	public String getSportTypeElse() {
		return sportTypeElse;
	}

	public void setSportTypeElse(String sportTypeElse) {
		this.sportTypeElse = sportTypeElse;
	}

	public int getSportTime() {
		return sportTime;
	}

	public void setSportTime(int sportTime) {
		this.sportTime = sportTime;
	}

	public String getPrimaryEvent() {
		return primaryEvent;
	}

	public void setPrimaryEvent(String primaryEvent) {
		this.primaryEvent = primaryEvent;
	} 
}
