package com.cking.phss.dto;

import java.util.ArrayList;

import com.cking.phss.bean.BeanID;
import com.cking.phss.global.Global;
import com.cking.phss.xml.util.XmlAttribute;
import com.cking.phss.xml.util.XmlTag;

/**
 * 12 得到过敏源代码.xml
 * @author Administrator
 *
 */
public class Ddgmydm12 implements IDto { 
/**
	 * Request
	 */
	@XmlTag(name = "Request")
	public Request request = null;

	static public class Request implements IDto {
		@XmlAttribute(name = "OrgCode")
		public String orgCode = Global.orgCode;

		@XmlAttribute(name = "OperType")
		public String operType = "12";
		
		//必填。查询类型：1：非药品过敏；2：药品过敏
		@XmlTag(name = "Type")
		public int type= 2;
} 
/**
	 * Response
	 * @author Administrator 
	 */
	@XmlTag(name = "Response")
	public Response response = null;
	static public class Response implements IDto {
		@XmlAttribute(name = "ErrMsg")
		public String errMsg = ""; 
		
		//成功：有多条记录，按HyperSource升序。值：过敏源名称，ID：ID或代码
		@XmlTag(name = "HyperSource", isListWithoutGroupTag = true)
        public ArrayList<BeanID> hyperSources = null;
	}
}
