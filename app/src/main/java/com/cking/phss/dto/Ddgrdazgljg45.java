package com.cking.phss.dto;

import java.util.ArrayList;

import com.cking.phss.bean.BeanID;
import com.cking.phss.global.Global;
import com.cking.phss.xml.util.XmlAttribute;
import com.cking.phss.xml.util.XmlTag;

/**
 * 45 得到个人档案中管理机构、服务站点、建档单位代码.xml
 * @author Administrator 
 */
public class Ddgrdazgljg45 implements IDto {
	/**
	 * Request
	 */
	@XmlTag(name = "Request")
	public Request request = null;

	static public class Request implements IDto {
		@XmlAttribute(name = "OrgCode")
		public String orgCode = Global.orgCode;

		@XmlAttribute(name = "OperType")
		public String operType = "45";  

		//必填。查询类型：1：管理机构；2：服务站点；3：建档单位
		@XmlTag(name = "Type")
		public String type= ""; 

		//单位名称，在查询时用like查询
		@XmlTag(name = "UnitName")
		public String unitName= "";
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

		//成功：有多条记录，按UnitName升序。值：单位名称，ID：ID或代码
		@XmlTag(name = "UnitName", isListWithoutGroupTag = true)
        public ArrayList<BeanID> unitNames = null;
	}
}
