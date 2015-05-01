package com.cking.phss.dto;

import java.util.ArrayList;

import com.cking.phss.bean.BeanID;
import com.cking.phss.global.Global;
import com.cking.phss.xml.util.XmlAttribute;
import com.cking.phss.xml.util.XmlTag;

/**
 * 31 根据区县得到相应街道.xml
 * @author Administrator
 *
 */
public class Gjqxddxyjd31 implements IDto { 
    /**
	 * Request
	 */
	@XmlTag(name = "Request")
	public Request request = null;

	static public class Request implements IDto {
		@XmlAttribute(name = "OrgCode")
		public String orgCode = Global.orgCode;

		@XmlAttribute(name = "OperType")
		public String operType = "31";
		
		//必填。值：区县名称，ID：区县ID或代码
		@XmlTag(name = "District")
        public BeanID district = null;
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
		
		//成功：有多条Street节点记录。按Street升序 
		//值：街道名，ID：街道ID或代码
		@XmlTag(name = "Street", isListWithoutGroupTag = true)
        public ArrayList<BeanID> streets = null;
	}
}
