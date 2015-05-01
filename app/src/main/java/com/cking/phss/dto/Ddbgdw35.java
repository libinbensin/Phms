package com.cking.phss.dto;

import java.util.ArrayList;

import com.cking.phss.bean.BeanID;
import com.cking.phss.global.Global;
import com.cking.phss.xml.util.XmlAttribute;
import com.cking.phss.xml.util.XmlTag;

/**
 * 35 得到报告（卡）单位.xml
 * 
 * @author Administrator
 * 
 */
public class Ddbgdw35 implements IDto {
	/**
	 * Request
	 */
	@XmlTag(name = "Request")
	public Request request = null;

	static public class Request implements IDto {
		@XmlAttribute(name = "OrgCode")
		public String orgCode = Global.orgCode;

		@XmlAttribute(name = "OperType")
		public String operType = "35";
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

		// 成功：返回多个ReportUnit节点记录，按ReportUnit升序
		// 值：报告单位名称，ID：相应单位ID或代码
		@XmlTag(name = "ReportUnit", isListWithoutGroupTag = true)
		public ArrayList<BeanID> reportUnitList = null;
	}
}
