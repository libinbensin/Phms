package com.cking.phss.dto;

import java.util.ArrayList;

import com.cking.phss.bean.BeanID;
import com.cking.phss.global.Global;
import com.cking.phss.xml.util.XmlAttribute;
import com.cking.phss.xml.util.XmlTag;

/**
 * 32 根据街道得到相应居委或社区.xml
 * 
 * @author Administrator
 * 
 */
public class Gjjdddxyjwhsq32 implements IDto {
	/**
	 * Request
	 */
	@XmlTag(name = "Request")
	public Request request = null;

	static public class Request implements IDto {
		@XmlAttribute(name = "OrgCode")
		public String orgCode = Global.orgCode;

		@XmlAttribute(name = "OperType")
		public String operType = "32";

		// 必填。值：街道名，ID：街道ID或代码
		@XmlTag(name = "Street")
        public BeanID street = null;
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

		//成功：有多条Zone节点记录。按Zone升序 
		//值：居委名称，ID：居委名称ID或代码
		@XmlTag(name = "Zone", isListWithoutGroupTag = true)
        public ArrayList<BeanID> zones = null;
	}
}
