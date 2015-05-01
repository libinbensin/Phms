package com.cking.phss.dto;
 
import java.util.ArrayList;

import com.cking.phss.bean.BeanID;
import com.cking.phss.global.Global;
import com.cking.phss.xml.util.XmlAttribute;
import com.cking.phss.xml.util.XmlTag;

/**
 * 34 得到本院中所有医生.xml
 * 
 * @author Administrator
 * 
 */
public class Ddbyzsyys34 implements IDto {
	/**
	 * Request
	 */
	@XmlTag(name = "Request")
	public Request request = null;

	static public class Request implements IDto {
		@XmlAttribute(name = "OrgCode")
		public String orgCode = Global.orgCode;

		@XmlAttribute(name = "OperType")
		public String operType = "34"; 
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

		//成功：返回多个Doctor节点记录，按Doctor升序
		//值：医生姓名，ID：相应医生ID或代码
		@XmlTag(name = "Doctor", isListWithoutGroupTag = true)
		public ArrayList<BeanID> doctorList = null;

	}
}
