package com.cking.phss.dto;
 
import com.cking.phss.global.Global;
import com.cking.phss.xml.util.XmlAttribute;
import com.cking.phss.xml.util.XmlTag;

/**
 * 80 得到本街道各业务管理人数.xml
 * @author Administrator 
 */
public class Ddbjdgywglrs80 implements IDto {
	/**
	 * Request
	 */
	@XmlTag(name = "Request")
	public Request request = null;

	static public class Request implements IDto {
		@XmlAttribute(name = "OrgCode")
		public String orgCode = Global.orgCode;

		@XmlAttribute(name = "OperType")
		public String operType = "80";   
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

		//健康档案人数
		@XmlTag(name = "JKDA")
		public int JKDA= 0;
		
		//高血压患者人数
		@XmlTag(name = "GXY")
		public int GXY= 0;
		
		//糖尿病患者人数
		@XmlTag(name = "TNB")
		public int TNB= 0;
		
		//慢阻肺患者人数
		@XmlTag(name = "MZF")
		public int MZF= 0;
		
		//心脑血管患者人数
		@XmlTag(name = "XNXG")
		public int XNXG= 0;
		
		//肿瘤病患者人数
		@XmlTag(name = "ZLB")
		public int ZLB= 0; 
	}
}
