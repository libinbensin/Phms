package com.cking.phss.dto;

import java.util.List;

import com.cking.phss.global.Global;
import com.cking.phss.xml.util.XmlAttribute;
import com.cking.phss.xml.util.XmlTag;

/**
 * 慢病随访明细列表.xml
 * 
 * @author Administrator
 */
public class MbsfmxlbJ011 implements IDto {
	/**
	 * Request
	 */
	@XmlTag(name = "Request")
	public Request request = null;

	static public class Request implements IDto {
		@XmlAttribute(name = "OrgCode")
		public String orgCode = Global.orgCode;

		@XmlAttribute(name = "OperType")
		public String operType = "J011";

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
        
        //查询类别 1应随访 2已随访 3未随访-->
        @XmlTag(name = "SearchType")
        public int searchType = 1;
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

		//-人数 -
        @XmlTag(name = "Count")
        public String count = "";

        @XmlTag(name="Person",isListWithoutGroupTag=true)
        public List<Person> persons = null;
	}

	public static class Person implements IDto {
	    //档案编号
	    @XmlTag(name = "ResidentID")
        public String residentID = "";
	    
	    //姓名
	    @XmlTag(name = "Name")
        public String name = "";
	    
	   //性别 
        @XmlTag(name = "Sex")
        public String sex = "";
        
        //年龄
        @XmlTag(name = "Age")
        public String age = "";
        
        //联系电话
        @XmlTag(name = "LinkPhone")
        public String linkPhone = "";
        
        //身份证
        @XmlTag(name = "Idcard")
        public String idcard = "";
        
        //住址 -
        @XmlTag(name = "Address")
        public String address = "";
        
      //职业
        @XmlTag(name = "Occupation")
        public String occupation = "";
        
      //工作单位
        @XmlTag(name = "Company")
        public String company = "";
        
       //疾病名称
        @XmlTag(name = "DisType")
        public String disType = "";
        
        //报卡日期 
        @XmlTag(name = "RptDate")
        public String rptDate = "";
        //审核日期 
        @XmlTag(name = "ApprovedDate")
        public String approvedDate = "";
        //审核状态
        @XmlTag(name = "ApprovedFlag")
        public String approvedFlag = "";
        //报卡状态
        @XmlTag(name = "RptStatus")
        public String rptStatus = "";
	}
}
