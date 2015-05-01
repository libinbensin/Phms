package com.cking.phss.dto;

import java.util.ArrayList;

import android.provider.ContactsContract.CommonDataKinds.Relation;

import com.cking.phss.global.Global;
import com.cking.phss.xml.util.XmlAttribute;
import com.cking.phss.xml.util.XmlTag;

/**
 * 46 得到与户主关系代码.xml
 * @author Administrator 
 */
public class Ddyhzgxdm46 implements IDto {
	/**
	 * Request
	 */
	@XmlTag(name = "Request")
	public Request request = null;

	static public class Request implements IDto {
		@XmlAttribute(name = "OrgCode")
		public String orgCode = Global.orgCode;

		@XmlAttribute(name = "OperType")
		public String operType = "46";  

		//与户主关系名称，在查询时用like查询
		@XmlTag(name = "Relation")
		public String relation= ""; 
 
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

		//成功：有多条记录，按Relation升序。值：与户主关系名称，ID：ID或代码
		@XmlTag(name = "Relation", isListWithoutGroupTag = true)
		public ArrayList<Relation> relations = null;
	}
}
