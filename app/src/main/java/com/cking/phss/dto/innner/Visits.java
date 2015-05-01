package com.cking.phss.dto.innner;

import com.cking.phss.dto.IDto;
import com.cking.phss.xml.util.XmlAttribute;

public class Visits implements IDto {
	//@XmlAttribute(name = "!--必填。此条随访记录是否只读。1：是；0：否 -->
	@XmlAttribute(name = "ReadOnly")
	public String readOnly= "";
	//@XmlAttribute(name = "!--必填。 用户ID，输入人员-->
	@XmlAttribute(name = "UserID")
	public String userID= "";
	//@XmlAttribute(name = "!--必填。 用户名称-->
	@XmlAttribute(name = "UserName")
	public String userName= "";
	//@XmlAttribute(name = "!--必填。个人档案号-->
	@XmlAttribute(name = "ResidentID")
	public String residentID= "";
	//@XmlAttribute(name = "!--必填。随访序号 -->
	@XmlAttribute(name = "FlupID")
	public String flupID= "";
	//@XmlAttribute(name = "!--必填。 随访日期：yyyy-mm-dd-->
	@XmlAttribute(name = "FlupDate")
	public String flupDate= "";
	//@XmlAttribute(name = "!--随访医生 -->
	@XmlAttribute(name = "FlupDoctorName")
	public String flupDoctorName= "";
	//@XmlAttribute(name = "!--下次随访日期，格式：yyyy-mm-dd -->
	@XmlAttribute(name = "NextFlupDate")
	public String nextFlupDate= "";
}  






			
