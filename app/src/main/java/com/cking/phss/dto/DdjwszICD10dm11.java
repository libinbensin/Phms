package com.cking.phss.dto;

import java.util.ArrayList;

import com.cking.phss.bean.BeanID;
import com.cking.phss.global.Global;
import com.cking.phss.xml.util.XmlAttribute;
import com.cking.phss.xml.util.XmlTag;

/**
 * 11 得到既往史中ICD10代码.xml
 * @author Administrator
 *
 */
public class DdjwszICD10dm11 implements IDto { 
/**
	 * Request
	 */
	@XmlTag(name = "Request")
	public Request request = null;

	static public class Request implements IDto {
		@XmlAttribute(name = "OrgCode")
		public String orgCode = Global.orgCode;

		@XmlAttribute(name = "OperType")
		public String operType = "11";
		
		//必填。疾病查询条件，在查询时用疾病名称进行like查询
		@XmlTag(name = "ICD")
		public String ICD= "";
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
		
		//成功：有多条记录，按NationalityCD升序。值：国家名称，ID：ID或代码
		@XmlTag(name = "ICDCD", isListWithoutGroupTag = true)
        public ArrayList<BeanID> icdcds = null;
	}
}
