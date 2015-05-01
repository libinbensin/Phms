package com.cking.phss.dto.sfgl.gxy;

import java.util.List;

import com.cking.phss.bean.BeanID;
import com.cking.phss.dto.IDto;
import com.cking.phss.global.Global;
import com.cking.phss.xml.util.XmlAttribute;
import com.cking.phss.xml.util.XmlTag;

/**
 * 14 得高血压患者随访列表.xml
 * 
 * @author Administrator
 */
public class Dgxyhzsflb14 implements IDto {
	/**
	 * Request
	 */
	@XmlTag(name = "Request")
	public Request request = null;

	static public class Request implements IDto {
		@XmlAttribute(name = "OrgCode")
		public String orgCode = Global.orgCode;

		@XmlAttribute(name = "OperType")
		public String operType = "14";

		// 必填。值：当前登录用户工号，ID：相应代码或ID
		@XmlTag(name = "EmployeeNo")
		public BeanID employeeNo = null;

		// 必填。个人档案号
		@XmlTag(name = "ResidentID")
		public String residentID = "";

		// 必填。查询随访开始时间段，格式：yyyy-mm-dd
		@XmlTag(name = "VisitSD")
		public String visitSD = "";

		// 必填。查询随访结束时间段，格式：yyyy-mm-dd
		@XmlTag(name = "VisitED")
		public String visitED = "";
	}

	/**
	 * Response
	 */
	@XmlTag(name = "Response")
	public Response response = null;

	static public class Response implements IDto {
		@XmlAttribute(name = "ErrMsg")
		public String errMsg = ""; 

		@XmlTag(name = "Visits",isListWithoutGroupTag=true)
		public List<Visits> visitsList = null;

		public static  class Visits implements IDto {
		 // 必填。此条随访记录是否只读。1：是；0：否
            @XmlTag(name = "ReadOnly")
            public int readOnly = 0;
            
            // 必填。 用户ID，输入人员
            @XmlTag(name = "UserID")
            public String userID = "";

            // 必填。 用户名称
            @XmlTag(name = "User")
            public String user = "";
            
            // 必填。个人档案号
            @XmlTag(name = "ResidentID")
            public String residentID = "";
		
			// 必填。随访序号
			@XmlTag(name = "VisitID")
			public String visitID = "";

			// 必填。 随访日期：yyyy-mm-dd
			@XmlTag(name = "VisitDate")
			public String visitDate = "";

			// 随访医生
			@XmlTag(name = "DoctorName")
			public String doctorName = "";

			// 血压，格式：收缩压/舒张压
			@XmlTag(name = "HBP")
			public String hBP = "";

			// 下次随访日期，格式：yyyy-mm-dd
			@XmlTag(name = "NextVisitDate")
			public String nextVisitDate = "";
		}
	}
}
