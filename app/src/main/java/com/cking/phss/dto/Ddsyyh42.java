package com.cking.phss.dto;

import java.util.ArrayList;

import com.cking.phss.dto.innner.UserInfo;
import com.cking.phss.global.Global;
import com.cking.phss.xml.util.XmlAttribute;
import com.cking.phss.xml.util.XmlTag;

/**
 * 42 得到所有用户.xml
 * 
 * @author Administrator
 * 
 */
public class Ddsyyh42 implements IDto {
	/**
	 * Request
	 */
	@XmlTag(name = "Request")
	public Request request = null;

	static public class Request implements IDto {
		@XmlAttribute(name = "OrgCode")
		public String orgCode = Global.orgCode;

		@XmlAttribute(name = "OperType")
		public String operType = "42";  
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

		//成功：返回多个UserInfo节点记录，按UserName升序
		//值：用户姓名，UserID：相应用户ID或代码，UserName：用户登录名
		@XmlTag(name = "UserInfo", isListWithoutGroupTag = true)
		public ArrayList<UserInfo> userInfos = null;
	}
}
