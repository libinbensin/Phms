package com.cking.phss.dto;

import java.util.List;

import com.cking.phss.bean.BeanID;
import com.cking.phss.dto.innner.Family;
import com.cking.phss.dto.innner.ReturnNum;
import com.cking.phss.global.Global;
import com.cking.phss.xml.util.XmlAttribute;
import com.cking.phss.xml.util.XmlTag;

public class Ddjtjbxx3 implements IDto {
	@XmlTag(name = "Request")
	public Request request = null;

	static public class Request implements IDto {
		@XmlAttribute(name = "OrgCode")
		public String orgCode = Global.orgCode;

		@XmlAttribute(name = "OperType")
		public String operType = "3";

		// 返回记录数，假如为0或空则说明返回所有符合条件的记录
		@XmlTag(name = "ReturnRecord")
		public int returnRecord = 0;

		// 当前登录用户号
		@XmlTag(name = "EmployeeNo")
        public BeanID employeeNo = null;

		// 社区（村）或者居委会
		@XmlTag(name = "Zone")
        public BeanID zone = null;

		// 路名，查询时用like
		@XmlTag(name = "Road")
		public String Road = "";

		// 居民姓名，即成员姓名，查询时用like-
		@XmlTag(name = "ResidentName")
		public String residentName = "";

		// 户主姓名
		@XmlTag(name = "Householder")
		public String householder = "";

		// 居民（成员）责任医生姓名，查询时用like
		@XmlTag(name = "DutyDocotor")
		public String dutyDocotor = "";

		// 居民（成员）身份证号
		@XmlTag(name = "PaperNum")
		public String paperNum = "";

		// 居民（成员）身份证号
		@XmlTag(name = "ResidentID")
		public String residentID = "";

		// 居民（成员）建档人员
		@XmlTag(name = "Builder")
        public BeanID builder = null;

		// 居民（成员）个人档案状态代码。如果未填则说明是所有。值为状态代码：0.正常；1.注销；2.死亡；3.迁出(区域\外地)；4.挂起；5.临时
		@XmlTag(name = "FileStatusCD")
		public String fileStatusCD = "";

		// 居民本人电话，查询时用like
		@XmlTag(name = "SelfPhone")
		public String SelfPhone = "";
	}

	@XmlTag(name = "Response")
	public Response Response = null;

	static public class Response implements IDto {
		@XmlAttribute(name = "")
		public String errMsg = "";

		//<!--返回记录数 -->
		@XmlTag(name = "ReturnNum")
		public ReturnNum returnNum = null;

		//<!-- 可以返回有多个家庭，每个家庭一个Family节点，按Group、Address升序-->
		@XmlTag(name = "Family",isListWithoutGroupTag=true)
		public List<Family> families = null;
	}
}
