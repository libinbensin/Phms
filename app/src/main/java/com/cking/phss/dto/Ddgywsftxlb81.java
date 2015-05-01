package com.cking.phss.dto;

import java.util.List;

import com.cking.phss.bean.BeanID;
import com.cking.phss.dto.innner.WarnInfo;
import com.cking.phss.global.Global;
import com.cking.phss.xml.util.XmlAttribute;
import com.cking.phss.xml.util.XmlTag;

/**
 * 81 得到各业务随访提醒列表.xml
 * 
 * @author Administrator
 */
public class Ddgywsftxlb81 implements IDto {
	/**
	 * Request
	 */
	@XmlTag(name = "Request")
	public Request request = null;

	static public class Request implements IDto {
		@XmlAttribute(name = "OrgCode")
		public String orgCode = Global.orgCode;

		@XmlAttribute(name = "OperType")
		public String operType = "81";

		// 返回记录数，假如为0或空则说明返回所有符合条件的记录-
		@XmlTag(name = "ReturnRecord")
		public int returnRecord = 0;

		// 必填。值：当前登录用户工号，ID：相应代码或ID
		@XmlTag(name = "EmployeeNo")
        public BeanID employeeNo = null;

		// 社区（村）或者居委会，值：居委名称；ID：居委代码
		@XmlTag(name = "Zone")
        public BeanID zone = null;

		// 值：责任医生，ID：医生代码或ID
		@XmlTag(name = "DutyDoctor")
        public BeanID dutyDoctor = null;

		// 必填。要提醒哪个业务模块。ID：业务模块代码，值：业务模块名称。代码为固定代码：0.所有模块；1.高血压；2.糖尿病；3.慢阻肺；4.心脑血管；5.肿瘤病
		@XmlTag(name = "WarnBusi")
        public BeanID warnBusi = null;

		// 必填。随访提醒条件。ID：随访条件代码，值：随访条件名称。代码为固定代码：1.至今未访；2.应访日期段；3.至今未访和应访日期段；4.首次随访
		@XmlTag(name = "WarnCondi" )
        public BeanID warnCondi = null;

		// 假如随访提醒条件为：应访日期段，则值：应访开始日期，格式：yyyy-mm-dd
		@XmlTag(name = "WarnSD")
		public String warnSD = "";

		// 假如随访提醒条件为：应访日期段，则值：应访结束日期，格式：yyyy-mm-dd
		@XmlTag(name = "WarnED")
		public String warnED = "";

		// 必填。排序条件，检索出的记录按哪个关键字排序。ID：排序条件代码，值：排序条件名称。代码为固定代码：1.按ResidentName升序；2.按Address升序；3.按DutyDoctor升序；4.按OughtVisitDate升序
		@XmlTag(name = "SortCondi")
        public BeanID sortCondi = null;
	}

	/**
	 * Response
	 * 
	 * @author Administrator
	 */
	@XmlTag(name = "Response")
	public Response response = null;
	static public class Response implements IDto {
		@XmlAttribute(name = "ErrMsg")
		public String errMsg = "";

		// 返回记录数
		@XmlTag(name = "ReturnNum")
		public int returnNum = 0;

		/**
		 * 可以返回有多个患者随访提醒信息，每个信息一个WarnInfo节点,按ResidentName升序
		 */
		@XmlTag(name = "WarnInfo" ,isListWithoutGroupTag = true)
		public List<WarnInfo> warnInfo = null;
	}
}
