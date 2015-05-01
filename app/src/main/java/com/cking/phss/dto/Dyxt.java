package com.cking.phss.dto;

import com.cking.phss.dto.innner.Glucose;
import com.cking.phss.xml.util.XmlAttribute;
import com.cking.phss.xml.util.XmlTag;
/**
 * 调用血糖
 * @author taowencong
 *
 */
public class Dyxt implements IDto{
    /**
     * Request
     */
    @XmlTag(name = "Request")
    public Request request = null;
    
    static public class Request implements IDto {
        // type
        @XmlAttribute(name = "Type")
        public String type = "2";
        

        // <!--必填。数据基站ID-->
        @XmlTag(name = "bridgeId")
        public String bridgeId = "";

        // <!--必填。设备编号-->
        @XmlTag(name = "deviceSn")
        public String deviceSn = "";

        // <!--必填。血糖类型 0:随机血糖；1：空腹血糖; 2:餐后2小时-->
        @XmlTag(name = "glucoseType")
        public String glucoseType = "";

        // <!--必填。平台编号。用于分辨是随访平台和健康小屋 0表示随访平台；1表示健康小屋-->
        @XmlTag(name = "pfId")
        public String pfId = "";
    }
    
    /**
     * Response
     */
    @XmlTag(name = "Response")
    public Response response = null;
    
    static public class Response implements IDto {
        @XmlAttribute(name = "ErrMsg")
        public String errMsg = "";
        // <glucose>
        @XmlTag(name = "glucose")
        public Glucose glucose = null;
    }
}
