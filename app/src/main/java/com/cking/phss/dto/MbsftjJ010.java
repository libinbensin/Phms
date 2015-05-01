package com.cking.phss.dto;

import com.cking.phss.global.Global;
import com.cking.phss.xml.util.XmlAttribute;
import com.cking.phss.xml.util.XmlTag;

/**
 * 慢病随访统计.xml
 * 
 * @author Administrator
 */
public class MbsftjJ010 implements IDto {
	/**
	 * Request
	 */
	@XmlTag(name = "Request")
	public Request request = null;

	static public class Request implements IDto {
		@XmlAttribute(name = "OrgCode")
		public String orgCode = Global.orgCode;

		@XmlAttribute(name = "OperType")
		public String operType = "J010";

		// 必填。 操作用户ID或代码
		@XmlTag(name = "UserID")
		public String userID = "";

	   /*必填。疾病类型。值为代码：0101 高血压报卡
	    0102    糖尿病
	    0103    心脑血管
	    0104    肿瘤
	    0105    精神病
	    0106    慢性阻塞性肺疾病
	    0107    高血脂
	    0201    高危 
	    若存在多条则用|分隔，比如高血压和糖尿病则为：0101|0102-->*/
        @XmlTag(name = "DisType")
        public String disType = "";
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

		// 必填。 操作用户ID或代码
        @XmlTag(name = "UserID")
        public String userID = "";
        
		// 应随访数量
		@XmlTag(name = "Should")
		public String should = "";

		// 已随访数量 
		@XmlTag(name = "Done")
		public String done = "";

		// 未随访数量
		@XmlTag(name = "Notdo")
		public String notdo = "";
	}

}
