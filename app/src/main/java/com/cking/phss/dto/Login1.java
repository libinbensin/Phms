package com.cking.phss.dto;

import java.util.List;

import com.cking.phss.bean.BeanCD;
import com.cking.phss.bean.BeanID;
import com.cking.phss.global.Global;
import com.cking.phss.xml.util.XmlAttribute;
import com.cking.phss.xml.util.XmlTag;


public class Login1 implements IDto{
	
	@XmlTag(name = "Request")
	public Request request = null;
	
	@XmlTag(name = "Response")
	public Response response = null;
	
	public static class Request implements IDto{
		@XmlAttribute(name = "OrgCode")
		public String orgCode = Global.orgCode;
		@XmlAttribute(name = "OperType")
		public String operType = "1";
		
		
		@XmlTag(name = "UserN")
		public String userN = "";
		@XmlTag(name = "PassWord")
		public String password="";
	}
	
	public static class Response implements IDto{
		@XmlAttribute(name = "ErrMsg")
		public String errMsg = "";
		
		@XmlTag(name = "UserID")
		public String userID = "";
		
		@XmlTag(name = "PassWord")
		public String passWord = "";
		
		@XmlTag(name = "DoctorID")
		public String doctorID = "";
		
		@XmlTag(name = "DoctorName")
		public String doctorName = "";
		
		@XmlTag(name = "EmployeeNo")
        public BeanID employeeNo = null;
		
		@XmlTag(name = "Position")
        public BeanCD position = null;
		
		@XmlTag(name = "HealthService")
        public BeanCD healthService = null;
		
		@XmlTag(name = "Station")
        public BeanCD station = null;
		
		@XmlTag(name = "Country")
        public BeanCD country = null;
		
		@XmlTag(name = "Province")
        public BeanID province = null;
		
		@XmlTag(name = "City")
        public BeanID city = null;
		
		@XmlTag(name = "District")
        public BeanID district = null;
		
		@XmlTag(name = "Street")
        public BeanID street = null;
		
		@XmlTag(name = "Community")
        public BeanID community = null;
		
		@XmlTag(name = "Road")
        public BeanID road = null;
		
		@XmlTag(name = "Lane")
		public String lane = "";	
		
		@XmlTag(name = "Group")
		public String group = "";	
		
		@XmlTag(name = "Room")
		public String room = "";	
		
		@XmlTag(name = "Other")
		public String other = "";			
		
		@XmlTag(name = "CommunityJurisdiction")
        public BeanCD communityJurisdiction = null;
		
		@XmlTag(name = "Zones")
        public List<BeanID> Zones = null;
	}
}
