package com.cking.phss.dto;

import java.util.ArrayList;

import com.cking.phss.bean.BeanID;
import com.cking.phss.global.Global;
import com.cking.phss.xml.util.XmlAttribute;
import com.cking.phss.xml.util.XmlTag;

/**
 * 28 得到全国所有省直辖市.xml
 * @author Administrator
 *
 */
public class Ddqgsyszxs28 implements IDto { 
/**
	 * Request
	 */
	@XmlTag(name = "Request")
	public Request request = null;

	static public class Request implements IDto {
		@XmlAttribute(name = "OrgCode")
		public String orgCode = Global.orgCode;

		@XmlAttribute(name = "OperType")
		public String operType = "28"; 
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
		
		//值：省名称，ID：省ID或代码
		@XmlTag(name = "Province", isListWithoutGroupTag = true)
        public ArrayList<BeanID> provinces = null;
	}
}
