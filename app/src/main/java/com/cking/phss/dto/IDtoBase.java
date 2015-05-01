package com.cking.phss.dto;

import com.cking.phss.global.Global;
import com.cking.phss.xml.util.XmlAttribute;
import com.cking.phss.xml.util.XmlTag;

/**
 * 79 直接得到居民基本信息.xml
 * 
 * @author Administrator
 */
public class IDtoBase implements IDto {
	/**
	 * Request
	 */
	@XmlTag(name = "Request")
	public Request request =null;

	static public class Request implements IDto {
		@XmlAttribute(name = "OrgCode")
		public String orgCode = Global.orgCode;

		@XmlAttribute(name = "OperType")
        public String operType = "0";
	}

	/**
	 * Response
	 */
	@XmlTag(name = "Response")
	public Response response = null;

	static public class Response implements IDto {
		@XmlAttribute(name = "ErrMsg")
		public String errMsg = "";
	}
}
