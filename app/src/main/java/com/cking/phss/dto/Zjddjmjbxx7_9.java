package com.cking.phss.dto;

import java.util.List;

import com.cking.phss.bean.BeanID;
import com.cking.phss.dto.innner.Resident;
import com.cking.phss.global.Global;
import com.cking.phss.xml.util.XmlAttribute;
import com.cking.phss.xml.util.XmlTag;

/**
 * 79 直接得到居民基本信息.xml
 * 
 * @author Administrator
 */
public class Zjddjmjbxx7_9 implements IDto {
	/**
	 * Request
	 */
	@XmlTag(name = "Request")
	public Request request =null;

	static public class Request implements IDto {
		@XmlAttribute(name = "OrgCode")
		public String orgCode = Global.orgCode;

		@XmlAttribute(name = "OperType")
		public String operType = "79";

		// 返回记录数，假如为0或空则说明返回所有符合条件的记录
		@XmlTag(name = "ReturnRecord")
		public int returnRecord = 0;

		// 必填。值：当前登录用户工号，ID：相应代码或ID
		@XmlTag(name = "EmployeeNo")
        public BeanID employeeNo = null;

		// 个人档案号
		@XmlTag(name = "ResidentID")
		public String residentID = "";

		// 社区（村）或者居委会，值：居委名称；ID：居委代码
		@XmlTag(name = "Zone")
        public BeanID zone = null;

		// 路名，查询时用like
		@XmlTag(name = "Road")
		public String road = "";

		// 居民姓名，查询时用like
		@XmlTag(name = "ResidentName")
		public String residentName = "";

		// 户主姓名，查询时用like
		@XmlTag(name = "Householder")
		public String Householder = "";

		// 身份证号
		@XmlTag(name = "PaperNum")
		public String PaperNum = "";

		// 卡号
		@XmlTag(name = "CardID")
		public String CardID = "";

		// 责任医生姓名，查询时用like
		@XmlTag(name = "DutyDocotor")
		public String DutyDocotor = "";
	}

	/**
	 * Response
	 */
	@XmlTag(name = "Response")
	public Response response = null;

	static public class Response implements IDto {
		@XmlAttribute(name = "ErrMsg")
		public String errMsg = "";

		@XmlTag(name = "Resident",isListWithoutGroupTag=true)
		public List<Resident> resident = null;
	}
}
