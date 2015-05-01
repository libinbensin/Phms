package com.cking.phss.dto;

import com.cking.phss.xml.util.XmlAttribute;
import com.cking.phss.xml.util.XmlTag;

/**
 * 获取三围数据
 * @author taowencong
 * 
 */
public class GetYwData implements IDto {
    /**
     * Request
     */
    @XmlTag(name = "Request")
    public Request request = null;

    static public class Request implements IDto {

        @XmlAttribute(name = "Type")
        public String type = "1";
        
        @XmlTag(name = "bridgeId")//必填。数据基站ID
        public String bridgeId = "";

        @XmlTag(name = "deviceSn")//必填。设备编号
        public String deviceSn = "";

        @XmlTag(name = "pfId")//必填。平台编号。用于分辨是随访平台和健康小屋 0表示随访平台；1表示健康小屋
        public String pfId="";
    }

    /**
     * Response
     */
    @XmlTag(name = "Response")
    public Response response = null;

    static public class Response implements IDto {
        @XmlAttribute(name = "ErrMsg")
        public String errMsg = "";
        
        @XmlTag(name = "Chest", hasSubTag = true)//胸围
        public Chest chest = null;
        
        public static class Chest implements IDto{
            //id编号
            @XmlTag(name="id")
            public String id="";
            
            //数据基站id
            @XmlTag(name="bridgeId")
            public String bridgeId="";
            
            //胸围 
            @XmlTag(name="circumferenceType")
            public String circumferenceType="";
            
            //胸围尺值
            @XmlTag(name="circumferenceValue")
            public String circumferenceValue="";
            
            //设备编号
            @XmlTag(name="deviceSn")
            public String deviceSn="";
            
            //测试日期
            @XmlTag(name="measurementDate")
            public String measurementDate="";
            
            //平台ID
            @XmlTag(name="pfId")
            public String pfId="";
        }
        
        
        @XmlTag(name = "Waist", hasSubTag = true)//胸围
        public Waist waist = null;
        
        public static class Waist implements IDto{
            //id编号
            @XmlTag(name="id")
            public String id="";
            
            //数据基站id
            @XmlTag(name="bridgeId")
            public String bridgeId="";
            
            //胸围 
            @XmlTag(name="circumferenceType")
            public String circumferenceType="";
            
            //胸围尺值
            @XmlTag(name="circumferenceValue")
            public String circumferenceValue="";
            
            //设备编号
            @XmlTag(name="deviceSn")
            public String deviceSn="";
            
            //测试日期
            @XmlTag(name="measurementDate")
            public String measurementDate="";
            
            //平台ID
            @XmlTag(name="pfId")
            public String pfId="";
        }
        
        
        @XmlTag(name = "Hip", hasSubTag = true)//胸围
        public Hip hip = null;
        
        public static class Hip implements IDto{
            //id编号
            @XmlTag(name="id")
            public String id="";
            
            //数据基站id
            @XmlTag(name="bridgeId")
            public String bridgeId="";
            
            //胸围 
            @XmlTag(name="circumferenceType")
            public String circumferenceType="";
            
            //胸围尺值
            @XmlTag(name="circumferenceValue")
            public String circumferenceValue="";
            
            //设备编号
            @XmlTag(name="deviceSn")
            public String deviceSn="";
            
            //测试日期
            @XmlTag(name="measurementDate")
            public String measurementDate="";
            
            //平台ID
            @XmlTag(name="pfId")
            public String pfId="";
        }
    }
}
