package com.cking.phss.dto;

import java.util.ArrayList;

import com.cking.phss.bean.BeanID;
import com.cking.phss.global.Global;
import com.cking.phss.xml.util.XmlAttribute;
import com.cking.phss.xml.util.XmlTag;

/**
 * 33 根据居委或社区得到相应的路.xml
 * 
 * @author Administrator
 * 
 */
public class Gjjwhsqddxydl33 implements IDto {
	/**
	 * Request
	 */
	@XmlTag(name = "Request")
	public Request request =null;

	static public class Request implements IDto {
		@XmlAttribute(name = "OrgCode")
		public String orgCode = Global.orgCode;

		@XmlAttribute(name = "OperType")
		public String operType = "33";

		// 必填。值：居委或社区名称，ID：相应ID或代码
		@XmlTag(name = "Zone")
        public BeanID zone = null;
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

		//成功：有多条Road节点记录。按Road升序 
		//值：路名称，ID：相应名称ID或代码-
		@XmlTag(name = "Road", isListWithoutGroupTag = true)
        public ArrayList<BeanID> roads = null;
	}
}
