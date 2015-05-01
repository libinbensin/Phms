package com.cking.phss.dto;

import java.util.ArrayList;

import com.cking.phss.dto.innner.Medicine;
import com.cking.phss.global.Global;
import com.cking.phss.xml.util.XmlAttribute;
import com.cking.phss.xml.util.XmlTag;

/**
 * 36 得到治疗药物.xml
 * 
 * @author Administrator
 * 
 */
public class Ddzlyw36 implements IDto {
	/**
	 * Request
	 */
	@XmlTag(name = "Request")
	public Request request = null;

	static public class Request implements IDto {
		@XmlAttribute(name = "OrgCode")
		public String orgCode = Global.orgCode;

		@XmlAttribute(name = "OperType")
		public String operType = "36";  

		//用like模糊查询根据InputCode
		@XmlTag(name = "InputCode")
		public String inputCode= "";
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

		//成功：返回多个Medicine节点记录，按Medicine升序 
		@XmlTag(name = "Medicine", isListWithoutGroupTag = true)
		public ArrayList<Medicine> medicines = null;
	}
}
