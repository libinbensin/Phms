package com.cking.phss.dto;

import java.util.ArrayList;

import com.cking.phss.bean.BeanID;
import com.cking.phss.global.Global;
import com.cking.phss.xml.util.XmlAttribute;
import com.cking.phss.xml.util.XmlTag;

/**
 * 29 根据省得到相应地级市.xml
 * @author Administrator
 *
 */
public class Gjsddxydjs29 implements IDto { 
    /**
	 * Request
	 */
	@XmlTag(name = "Request")
	public Request request = null;

	static public class Request implements IDto {
		@XmlAttribute(name = "OrgCode")
		public String orgCode = Global.orgCode;

		@XmlAttribute(name = "OperType")
		public String operType = "29";
		
		//必填。值：省名称，ID：省ID或代码
		@XmlTag(name = "Province")
        public BeanID province = null;
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
		
		//成功：有多条City节点记录。按City升序
		//值：地级市名称，ID：地级市ID或代码
		@XmlTag(name = "City", isListWithoutGroupTag = true)
        public ArrayList<BeanID> citys = null;
	}
}
