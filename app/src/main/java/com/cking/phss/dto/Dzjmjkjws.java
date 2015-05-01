package com.cking.phss.dto;

import java.util.List;

import com.cking.phss.bean.BeanID;
import com.cking.phss.dto.innner.DeviceUse;
import com.cking.phss.global.Global;
import com.cking.phss.util.DeviceUseFactory;
import com.cking.phss.xml.util.XmlAttribute;
import com.cking.phss.xml.util.XmlTag;
/**
 * 调整居民健康既往史
 * @author taowencong
 *
 */
public class Dzjmjkjws implements IDto{
    /**
     * Request
     */
    @XmlTag(name = "Request")
    public Request request = null;

    static public class Request implements IDto {
        @XmlAttribute(name = "OrgCode")
        public String orgCode = Global.orgCode;

        @XmlAttribute(name = "OperType")
        public String operType = "J008";
        
        @XmlTag(name = "UserID")
        public String userID = "";
        
        @XmlTag(name = "ResidentID")
        public String residentID = "";
        
        
        @XmlTag(name = "HDType")
        public int hDType = 2;

		//必填。既往史类别文字值
		@XmlTag(name = "HDTypeName")
		public String hDTypeName = "";
               
        @XmlTag(name = "DisSn")
        public String disSn = "";
        
        @XmlTag(name = "DisOperType")
        public int disOperType = 1;
        
		// 必填。值：ICD10代码名称，ID：代码或ID
		@XmlTag(name = "ICD10")
		public BeanID iCD10 = null;

		//public class ICD10 implements IDto {
		//	@XmlAttribute(name = "ID")
		//	public String iCD10ID = "";
		//	@XmlTag(name = "TagValue", hasSubTag = false)
		//	public String tagValue = "";	
			
		//	public ICD10(String id, String tagValue) {
		//		this.iCD10ID = id;
		//		this.tagValue = tagValue;
		//	}
		//}
        
        @XmlTag(name = "Disease")
        public String disease ="";
        
        @XmlTag(name = "DiagnoseDate")
        public String diagnoseDate ="";
        
        @XmlTag(name = "HappenDate")
        public String happenDate ="";
        
		//必填。原因
		@XmlTag(name = "HDReason")
		public String hDReason = "";

        @XmlTag(name = "ResultCD")
        public int resultCD =0;
        
		//结果文字值
		@XmlTag(name = "ResultName")
		public String resultName = "";


		// 治疗描述
		@XmlTag(name = "CureDes")
		public String cureDes = "";

		// 诊治医院名称
		@XmlTag(name = "CureHos")
		public String cureHos = "";  
		
		//数据来源, 有多条记录，因此会有多个DeviceUse节点
		@XmlTag(name = "DeviceUse",isListWithoutGroupTag=true)
		public List<DeviceUse> deviceUses = DeviceUseFactory.getDtoDeviceUses(getClass());
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

        @XmlTag(name = "ResidentID")
        public String residentID = "";
        
        @XmlTag(name = "DisSn")
        public String disSn = "";
    }	
}
