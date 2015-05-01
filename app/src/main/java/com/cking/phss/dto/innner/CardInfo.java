package com.cking.phss.dto.innner;

import com.cking.phss.bean.BeanCD;
import com.cking.phss.dto.IDto;
import com.cking.phss.xml.util.XmlAttribute;
import com.cking.phss.xml.util.XmlTag;

public class CardInfo implements IDto {

	@XmlAttribute(name="ID")
	public int cardInfoID = 0;
	@XmlTag(name="TagValue",hasSubTag=false)
	public String tagValue = "";

	//必填。个人档案号
	@XmlTag(name = "ResidentID")
	public String residentID= "";

	//必填。家庭档案号
	@XmlTag(name = "FamilyID")
	public String familyID= "";

	//必填。 居民姓名
	@XmlTag(name = "ResidentName")
	public String residentName= "";

	//必填。性别代码（单选）。值为代码：0）未知；1）男；2）女；9）未说明性别
	@XmlTag(name = "SexCD")
	public int sexCD= 0;

	//<!--必填。性别代码（单选）。值为代码：0）未知；1）男；2）女；9）未说明性别 -->
	@XmlAttribute(name = "Sex")
	public BeanCD sex = null;

	//必填。出生日期。格式：yyyy-mm-dd
	@XmlTag(name = "BirthDay")
	public String birthDay= ""; 

	//居民本人联系电话
	@XmlTag(name = "SelfPhone")
	public String selfPhone= "";

	//<!-- 本人电话（宅电） -->
	@XmlAttribute(name = "Telephone")
	public String telephone= "";
	//<!-- 手机号 -->
	@XmlAttribute(name = "MobilePhone")
	public String mobilePhone= "";

	//必填。 家庭地址
	@XmlTag(name = "Address")
	public String address= ""; 

	//必填。管理级别代码（单选），值为代码：1、1级；2、2级别；3、3级别；4、无
	@XmlTag(name="ManageLevelCD")
	public int manageLevelCD= 0; 

	//必填。个人档案中身高或近期身高，新增管理卡时用。单位：cm，身高
	@XmlTag(name = "Height")
	public int height= 0;

	//必填。个人档案中体重或近期体重，新增管理卡时用。单位：kg，浮点 
	@XmlTag(name = "Weight")
	public String weight= "";

	//<!--必填。值：责任医生，ID：医生代码或ID -->
	@XmlAttribute(name = "DutyDoctor")
	public BeanCD dutyDoctor = null;
	//<!--随访医生  ID：医生代码或ID-->
	@XmlAttribute(name = "FlupDocotor")
	public BeanCD flupDocotor = null;

	//必填。糖尿病类型诊断代码（单选），值为代码：1、I型糖尿病；2：II型糖尿病；3：妊娠糖尿病；4：其他特殊类型
	@XmlTag(name = "DiagnoseCD")
	public int diagnoseCD= 0;
}  







			
