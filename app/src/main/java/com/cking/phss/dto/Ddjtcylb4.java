package com.cking.phss.dto;

import java.util.List;

import com.cking.phss.bean.BeanID;
import com.cking.phss.dto.innner.Resident;
import com.cking.phss.global.Global;
import com.cking.phss.xml.util.XmlAttribute;
import com.cking.phss.xml.util.XmlTag;

/**
 * 4得到家庭成员列表.xml
 * 
 * @author Administrator
 * 
 */
public class Ddjtcylb4 implements IDto {
	@XmlTag(name = "Request")
	public Request request =null;

	static public class Request implements IDto {
		@XmlAttribute(name = "OrgCode")
		public String OrgCode = Global.orgCode;
		@XmlAttribute(name = "OperType")
		public String operType = "4";

		// 必填。值：当前登录用户工号
		@XmlTag(name = "EmployeeNo")
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

		//    <!-- 可以返回有多个居民，每个居民一个Resident节点，按ResidentName升序-->
		@XmlTag(name = "Resident",isListWithoutGroupTag=true)
		public List<Resident> residents = null;
	}
}
