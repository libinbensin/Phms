package com.cking.phss.dto;

import com.cking.phss.xml.util.XmlAttribute;
import com.cking.phss.xml.util.XmlTag;

/**
 * 人体成分传输协议01
 * @author taowencong
 * 
 */
public class Rtcfcsxi01 implements IDto {
    /**
     * Request
     */
    @XmlTag(name = "Request")
    public Request request = null;

    static public class Request implements IDto {

        @XmlAttribute(name = "Type")
        public String type = "3";

        @XmlTag(name = "bridgeId")
        // 必填。数据基站ID
        public String bridgeId = "";

        @XmlTag(name = "deviceSn")
        // 必填。设备编号
        public String deviceSn = "";

        @XmlTag(name = "PlatFormID")
        // 必填。平台编号。用于分辨是随访平台和健康小屋 0表示随访平台；1表示健康小屋
        public String platFormID = "";
    }

    /**
     * Response
     */
    @XmlTag(name = "Response")
    public Response response = null;

    static public class Response implements IDto {

        @XmlTag(name = "scale", hasSubTag = true)
        // 胸围
        public Scale scale = null;

        public static class Scale implements IDto {
            // id编号
            @XmlTag(name = "id")
            public String id = "";

            // 平台id
            @XmlTag(name = "platformId")
            public String platformId = "";

            // 数据基站id
            @XmlTag(name = "bridgeId")
            public String bridgeId = "";

            // 设备编号
            @XmlTag(name = "deviceSn")
            public String deviceSn = "";

            // 测试日期
            @XmlTag(name = "measurementDate")
            public String measurementDate = "";

            // 电阻50k
            @XmlTag(name = "resistance50K")
            public String resistance50K = "";

            // 电阻5k
            @XmlTag(name = "resistance5K")
            public String resistance5K = "";
            // 体重 -
            @XmlTag(name = "weight")
            public String weight = "";
        }
    }
}
