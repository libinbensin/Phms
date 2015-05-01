package com.cking.phss.dto;
import java.util.List;

import com.cking.phss.dto.innner.DeviceUse;
import com.cking.phss.global.Global;
import com.cking.phss.util.DeviceUseFactory;
import com.cking.phss.xml.util.XmlAttribute;
import com.cking.phss.xml.util.XmlTag;
/**
 * 得到身份证照片
 * @author taowencong
 *
 */
public class DdsfzzpJ001 implements IDto{
    /**
     * Request
     */
    @XmlTag(name = "Request")
    public Request request = null;
    
    static public class Request implements IDto {
        @XmlAttribute(name = "OrgCode")
        public String orgCode = Global.orgCode;

        @XmlAttribute(name = "OperType")
        public String operType = "J001";
        
        // 个人档案号
        @XmlTag(name = "ResidentID")
        public String residentID = "";
    }
    
    /**
     * Response
     */
    @XmlTag(name = "Response")
    public Response response = null;
    
    static public class Response implements IDto {
        @XmlAttribute(name = "ErrMsg")
        public String errMsg = "";
        // 个人档案号
        @XmlTag(name = "ResidentID")
        public String residentID = "";
        
        @XmlTag(name = "Photo")
        public String photo="";
        
		@XmlTag(name = "DeviceUse",isListWithoutGroupTag=true)
		public List<DeviceUse> deviceUses = DeviceUseFactory.getDtoDeviceUses(getClass());
    }
}
