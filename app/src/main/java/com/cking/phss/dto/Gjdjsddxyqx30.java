package com.cking.phss.dto;

import java.util.ArrayList;

import com.cking.phss.bean.BeanID;
import com.cking.phss.global.Global;
import com.cking.phss.xml.util.XmlAttribute;
import com.cking.phss.xml.util.XmlTag;

/**
 * 30 根据地级市得到相应区县.xml
 * @author Administrator
 *
 */
public class Gjdjsddxyqx30 implements IDto { 
    /**
	 * Request
	 */
	@XmlTag(name = "Request")
	public Request request = null;

	static public class Request implements IDto {
		@XmlAttribute(name = "OrgCode")
		public String orgCode = Global.orgCode;

		@XmlAttribute(name = "OperType")
		public String operType = "30";
		
		//必填。值：地级市名称，ID：地级市ID或代码
		@XmlTag(name = "City")
        public BeanID city = null;
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
		
		//成功：有多条District节点记录。按District升序
		//值：区县名称，ID：区县ID或代码
		@XmlTag(name = "District", isListWithoutGroupTag = true)
        public ArrayList<BeanID> districts = null;
	}
}
