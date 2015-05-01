package com.cking.phss.dto.sfgl; 

import java.util.List;

import com.cking.phss.dto.IDto;
import com.cking.phss.dto.innner.Card;
import com.cking.phss.global.Global;
import com.cking.phss.xml.util.XmlAttribute;
import com.cking.phss.xml.util.XmlTag;

/**
 * HVC01 得到个人报卡列表.xml
 * @author Administrator 
 */
public class DdgrbklbHvc01 implements IDto {
	/**
	 * Request
	 */
	@XmlTag(name = "Request")
	public Request request = null; 
	static public class Request implements IDto {
		@XmlAttribute(name = "OrgCode")
		public String orgCode = Global.orgCode;

		@XmlAttribute(name = "OperType")
        public String operType = "HVC01";

        // 必填。 操作用户ID或代码
        @XmlTag(name = "UserID")
        public String UserID = "";

        // 个人档案号。如果是新增则无个人档案号（新增时此ID由程序后台生成
        @XmlTag(name = "ResidentID")
        public String ResidentID = "";
	} 
	/**
	 * Response
	 */
	@XmlTag(name = "Response")
	public Response response = null;
	static public class Response implements IDto {
		@XmlAttribute(name = "ErrMsg")
		public String errMsg = "";

        // <!--报卡信息，可返回多条信息，因此可能有多个Card节点 -->
        @XmlTag(name = "Card", isListWithoutGroupTag = true)
        public List<Card> cards = null;
	}

}
