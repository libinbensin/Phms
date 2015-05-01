package com.cking.phss.dto;

import java.util.List;

import com.cking.phss.global.Global;
import com.cking.phss.xml.util.XmlAttribute;
import com.cking.phss.xml.util.XmlTag;

/**
 * J015 查询健康评估列表 和J014 查询心理+中医列表 一模一样的格式，所以公用同一个类，在用的时候，只需要包
 * operType改一下即可
 * 
 * @author Administrator
 */
public class CxlihzylbJ014AndCxjkpgJ015 implements IDto {
	/**
	 * Request
	 */
	@XmlTag(name = "Request")
	public Request request = null;

	public static  class Request implements IDto {
		@XmlAttribute(name = "OrgCode")
		public String orgCode = Global.orgCode;

		@XmlAttribute(name = "OperType")
		public String operType = "J014";

		// 必填。个人档案号
		@XmlTag(name = "ResidentID")
		public String residentID = "";

		//<!--身份证号-->
		@XmlTag(name = "Idcard")
		public String idcard = "";


		// 必填。查询随访开始时间段，格式：yyyy-mm-dd
		@XmlTag(name = "Sdate")
		public String sdate  = "";

		// 必填。查询随访结束时间段，格式：yyyy-mm-dd
		@XmlTag(name = "Edate")
		public String edate  = "";
	}

	/**
	 * Response
	 */
	@XmlTag(name = "Response")
	public Response response = null;

	static public class Response implements IDto {
		@XmlAttribute(name = "ErrMsg")
		public String errMsg = ""; 

		@XmlTag(name = "EvalList",isListWithoutGroupTag=true)
		public List<EvalList> evalList = null;

		public static  class EvalList implements IDto {
            
		    // 必填。个人档案号
            @XmlTag(name = "ResidentID")
            public String residentID = "";
            
            // 姓名
            @XmlTag(name = "ResidentName")
            public String residentName  = "";

            // 性别
            @XmlTag(name = "Sex")
            public String sex = "";
            
		
			// 身份证号
			@XmlTag(name = "Idcard")
			public String idcard  = "";

			// 评估序号
			@XmlTag(name = "EvalSn")
			public String evalSn = "";

			// 评估类型
			@XmlTag(name = "EvalType")
			public String evalType  = "";

			// 评估机构
			@XmlTag(name = "OrgName")
			public String orgName  = "";

			// 检查时间
			@XmlTag(name = "CheckDate")
			public String checkDate  = "";
		}
	}
}
