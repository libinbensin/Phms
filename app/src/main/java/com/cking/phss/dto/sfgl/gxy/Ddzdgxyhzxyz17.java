package com.cking.phss.dto.sfgl.gxy;

import com.cking.phss.dto.IDto;
import com.cking.phss.dto.innner.HBP;
import com.cking.phss.dto.innner.XueyaStandard;
import com.cking.phss.global.Global;
import com.cking.phss.xml.util.XmlAttribute;
import com.cking.phss.xml.util.XmlTag;

/**
 * 17 得到指定高血压患者血压值.xml
 * 
 * @author Administrator
 */
public class Ddzdgxyhzxyz17 implements IDto {
	/**
	 * Request
	 */
	@XmlTag(name = "Request")
	public Request request = null;

	static public class Request implements IDto {
		@XmlAttribute(name = "OrgCode")
		public String orgCode = Global.orgCode;

		@XmlAttribute(name = "OperType")
		public String operType = "17";

		// 必填。个人档案号
		@XmlTag(name = "ResidentID")
		public String residentID = "";

		// 必填。查询开始时间段，格式：yyyy-mm-dd
		@XmlTag(name = "SD")
		public String SD = "";

		// 必填。查询结束时间段，格式：yyyy-mm-dd
		@XmlTag(name = "ED")
		public String ED = "";
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

		/**
		 * 血压标准值，以及预警值
		 */
		@XmlTag(name = "Standard")
		public XueyaStandard standard = null;

		/**
		 * 可以返回有多条记录，每条记录一个HBP节点,按HBPDate升序
		 */
		@XmlTag(name = "HBP")
		public HBP hbp = null;
	}
}
