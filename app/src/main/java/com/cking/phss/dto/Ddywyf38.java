package com.cking.phss.dto;

import java.util.ArrayList;

import com.cking.phss.bean.BeanID;
import com.cking.phss.global.Global;
import com.cking.phss.xml.util.XmlAttribute;
import com.cking.phss.xml.util.XmlTag;

/**
 * 38 得到药物用法.xml
 * 
 * @author Administrator
 * 
 */
public class Ddywyf38 implements IDto {
	/**
	 * Request
	 */
	@XmlTag(name = "Request")
	public Request request = null;

	static public class Request implements IDto {
		@XmlAttribute(name = "OrgCode")
		public String orgCode = Global.orgCode;

		@XmlAttribute(name = "OperType")
		public String operType = "38";  

		//药物用法名称，在查询时用like查询 
		@XmlTag(name = "Usage")
		public String usage= "";
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

		//成功：有多条记录，按Usage升序。值：用法名称，ID：ID或代码 
		@XmlTag(name = "Usage", isListWithoutGroupTag = true)
        public ArrayList<BeanID> usages = null;
	}
}
