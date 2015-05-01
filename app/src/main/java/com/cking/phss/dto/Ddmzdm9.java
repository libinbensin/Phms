package com.cking.phss.dto;

import java.util.ArrayList;

import com.cking.phss.bean.BeanID;
import com.cking.phss.global.Global;
import com.cking.phss.xml.util.XmlAttribute;
import com.cking.phss.xml.util.XmlTag;

/**
 * 9得到民族代码.xml
 * @author Administrator
 *
 */
public class Ddmzdm9 implements IDto { 
/**
	 * Request
	 */
	@XmlTag(name = "Request")
	public Request request = null;

	static public class Request implements IDto {
		@XmlAttribute(name = "OrgCode")
		public String orgCode = Global.orgCode;

		@XmlAttribute(name = "OperType")
		public String operType = "9";
		
		//必填。民族查询条件，在查询时用民族名称进行like查询
		@XmlTag(name = "Folk")
		public String folk= "";
} 
/**
	 * Response
	 * @author Administrator 
	 */
	@XmlTag(name="Response")
	public Response response = null;
	static public class Response implements IDto {
		@XmlAttribute(name = "ErrMsg")
		public String errMsg = ""; 
		
		//成功：有多条记录，按FolkCD升序。值：民族名称，ID：ID或代码

		@XmlTag(name = "FolkCD", isListWithoutGroupTag = true)

        public ArrayList<BeanID> folkCDs = null;
	}
	
	/**
	 * 测试序列化和反序列化填充的测试数据、只供测试
	 */
	public void init() { 
		/**
//		 * request
//		 */
//		this.request = new Request();
//		this.request.orgCode = "19";
//		this.request.operType = "19"; 
//		this.request.folk = "1";
//		/**
//		 * response
//		 */
//		this.response = new Response();
//		this.response.folkCD.folkID = 1; 
//		/**
//		 * request
//		 */
//		this.request = new Request();
//		this.request.orgCode = "19";
//		this.request.operType = "19"; 
//		this.request.folk = "1";
//		/**
//		 * response
//		 */
//		this.response = new Response();
//		this.response.folkCD.folkID = 1; 
	}
}
