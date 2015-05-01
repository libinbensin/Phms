package com.cking.phss.dto.sfgl.gxy; 

import java.util.List;

import com.cking.phss.bean.BeanID;
import com.cking.phss.dto.IDto;
import com.cking.phss.dto.innner.CardInfo;
import com.cking.phss.global.Global;
import com.cking.phss.xml.util.XmlAttribute;
import com.cking.phss.xml.util.XmlTag;

/**
 * 13 得到高血压患者列表.xml
 * @author Administrator 
 */
public class Ddgxyhzlb13 implements IDto {
	/**
	 * Request
	 */
	@XmlTag(name = "Request")
	public Request request = null; 
	static public class Request implements IDto {
		@XmlAttribute(name = "OrgCode")
		public String orgCode = Global.orgCode;

		@XmlAttribute(name = "OperType")
		public String operType = "13";
		
		//返回记录数，假如为0或空则说明返回所有符合条件的记录
		@XmlTag(name="ReturnRecord")
		public int returnRecord= 0; 
		
		//必填。值：当前登录用户工号，ID：相应代码或ID
		@XmlTag(name="EmployeeNo")
        public BeanID employeeNo = null;
		
		//社区（村）或者居委会，值：居委名称；ID：居委代码 
		@XmlTag(name="Zone")
        public BeanID zone = null;
		
		//责任医生姓名，查询时用like
		@XmlTag(name="DutyDocotor")
		public String dutyDocotor= ""; 
		
		//患者姓名，查询时用 like
		@XmlTag(name="ResidentName")
		public String residentName= ""; 
		
		//必填。值：随访提醒的条件 0：无；1：至今未访；2：应访日期段
		@XmlTag(name="WarnCondi")
		public int warnCondi= 0; 
		
		//假如随访提醒条件为：应访日期段，则值：应访开始日期，格式：yyyy-mm-dd
		@XmlTag(name="WarnSD")
		public String warnSD= ""; 
		
		//假如随访提醒条件为：应访日期段，则值：应访结束日期，格式：yyyy-mm-dd
		@XmlTag(name="WarnED")
		public String warnED= ""; 
		
		//身份证号
		@XmlTag(name="PaperNum")
		public String paperNum= ""; 
		
		//个人档案号
		@XmlTag(name="ResidentID")
		public String residentID= ""; 
		
		//报卡医生，值：医生姓名，ID：医生ID或代码
		@XmlTag(name="ReportDoctor")
        public BeanID reportDoctor = null;
	} 
	/**
	 * Response
	 */
	@XmlTag(name = "Response")
	public Response response = null;
	static public class Response implements IDto {
		@XmlAttribute(name = "ErrMsg")
		public String errMsg = "";

		@XmlTag(name = "FamilyID")
		public String familyID = "";

		@XmlTag(name = "ResidentID")
		public String residentID = "";
		
		//返回记录数 
		@XmlTag(name="ReturnNum")
		public String returnNum= "";  
		
		/**
		 * 可以返回有多个患者的管理卡，每张管理卡一个CardInfo节点,按ResidentName升序
		 */
		@XmlTag(name = "CardInfo", isListWithoutGroupTag=true)
		public List<CardInfo> cardInfo;		
	}

}
