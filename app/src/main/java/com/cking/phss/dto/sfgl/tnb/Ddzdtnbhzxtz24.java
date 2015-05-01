package com.cking.phss.dto.sfgl.tnb;

import com.cking.phss.dto.IDto;
import com.cking.phss.dto.innner.BloodSugar;
import com.cking.phss.dto.innner.BloodSugarPBG;
import com.cking.phss.dto.innner.XuetangStandard;
import com.cking.phss.global.Global;
import com.cking.phss.xml.util.XmlAttribute;
import com.cking.phss.xml.util.XmlTag;

/**
 * 24 得到指定糖尿病患者血糖值.xml
 * 
 * @author Administrator
 * 
 */
public class Ddzdtnbhzxtz24 implements IDto {
	/**
	 * Request
	 */
	@XmlTag(name = "Request")
	public Request request = null;

	static public class Request implements IDto {
		@XmlAttribute(name = "OrgCode")
		public String orgCode = Global.orgCode;

		@XmlAttribute(name = "OperType")
		public String operType = "24";   
		
		//必填。个人档案号
		@XmlTag(name = "ResidentID")
		public String residentID= "";
		
		//必填。查询开始时间段，格式：yyyy-mm-dd
		@XmlTag(name = "SD")
		public String SD= "";
		
		//必填。查询结束时间段，格式：yyyy-mm-dd
		@XmlTag(name = "ED")
		public String ED= ""; 
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

		//-血糖标准值 
		@XmlTag(name = "Standard")
		public XuetangStandard standard = null; 
		
		//可以返回有多条记录（空腹血糖），每条记录一个BloodSugar节点,按BSDate升序
		@XmlTag(name = "BloodSugar")
        public BloodSugar bloodSugar = null;
		
		//可以返回有多条记录（餐后2小时血糖），每条记录一个BloodSugarPBG节点,按PBGDate升序
		@XmlTag(name = "BloodSugarPBG")
		public BloodSugarPBG bloodSugarPBG = null; 
	}
}
