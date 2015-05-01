package com.cking.phss.dto;

import java.util.ArrayList;

import com.cking.phss.bean.BeanID;
import com.cking.phss.global.Global;
import com.cking.phss.xml.util.XmlAttribute;
import com.cking.phss.xml.util.XmlTag;

/**
 * 40 得到行业或职业类别代码.xml
 * 
 * @author Administrator
 * 
 */
public class Ddhyhzylbdm40 implements IDto {
	/**
	 * Request
	 */
	@XmlTag(name = "Request")
	public Request request =null;

	static public class Request implements IDto {
		@XmlAttribute(name = "OrgCode")
		public String orgCode = Global.orgCode;

		@XmlAttribute(name = "OperType")
		public String operType = "40";  

		//行业名称，在查询时用like查询
		@XmlTag(name = "Vocation")
		public String vocation= "";
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

		//成功：有多条记录，按Vocation升序。值：行业名称，ID：ID或代码
		@XmlTag(name = "Vocation", isListWithoutGroupTag = true)
        public ArrayList<BeanID> vocations = null;
	}
}
