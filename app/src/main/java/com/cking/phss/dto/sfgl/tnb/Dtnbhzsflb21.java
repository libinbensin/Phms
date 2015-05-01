package com.cking.phss.dto.sfgl.tnb;

import java.util.List;

import com.cking.phss.bean.BeanID;
import com.cking.phss.dto.IDto;
import com.cking.phss.dto.innner.TnbVisits;
import com.cking.phss.global.Global;
import com.cking.phss.xml.util.XmlAttribute;
import com.cking.phss.xml.util.XmlTag;

/**
 * 21 得糖尿病患者随访列表.xml
 * @author Administrator 
 */
public class Dtnbhzsflb21 implements IDto {
	/**
	 * Request
	 */
	@XmlTag(name = "Request")
	public Request request = null;

	static public class Request implements IDto {
		@XmlAttribute(name = "OrgCode")
		public String orgCode = Global.orgCode;

		@XmlAttribute(name = "OperType")
		public String operType = "21";    
		
		//必填。值：当前登录用户工号，ID：相应代码或ID
		@XmlTag(name = "EmployeeNo")
		public BeanID employeeNo = null; 
		
		//必填。个人档案号
		@XmlTag(name = "ResidentID")
		public String residentID= "";
		
		//必填。查询随访开始时间段，格式：yyyy-mm-dd
		@XmlTag(name = "VisitSD")
		public String visitSD= "";
		
		//必填。查询随访结束时间段，格式：yyyy-mm-dd
		@XmlTag(name = "VisitED")
		public String visitED= ""; 
	} 
	/**
	 * Response
	 * 
	 * @author Administrator
	 */
	@XmlTag(name = "Response")
	public Response response =null;
	static public class Response implements IDto {
		@XmlAttribute(name = "ErrMsg")
		public String errMsg = "";  
		/**
		 * 可以返回有多条随访记录，每条随访记录一个Visits节点，按VisitDate升序
		 */ 
		@XmlTag(name = "Visits",isListWithoutGroupTag=true)
		public List<TnbVisits> visitsList = null; 
	}
}
