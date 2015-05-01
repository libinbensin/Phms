package com.cking.phss.dto;

import com.cking.phss.xml.util.XmlAttribute;
import com.cking.phss.xml.util.XmlTag;

/**
 * 人体成分传输协议01
 * 
 * @author taowencong
 * 
 */
public class Rtcfcsxi02 implements IDto {
    /**
     * Request
     */
    @XmlTag(name = "Request")
    public Request request = null;

    static public class Request implements IDto {

        @XmlAttribute(name = "Type")
        public String type = "4";
        
        @XmlTag(name = "PlatFormID") // 必填。平台编号
        public String platFormID = "";

        @XmlTag(name = "Sex")
        // 必填，性别 男、女
        public String sex = "";

        @XmlTag(name = "Weight")
        // 必填，体重 KG
        public String weight = "";

        @XmlTag(name = "Height")
        // 必填，身高 单位：M
        public String height = "";

        @XmlTag(name = "Waistline")
        // 必填，腰围 单位：CM
        public String waistline = "";

        @XmlTag(name = "Age")
        // 必填，年龄 单位：岁
        public String age = "";

        @XmlTag(name = "IMP")
        // 必填，电阻 单位：欧姆
        public String iMP = "";
    }

    /**
     * Response
     */
    @XmlTag(name = "Response")
    public Response response = null;

    static public class Response implements IDto {
        // 体脂肪率 保留一位小数 单位:%
        @XmlTag(name = "Fat")
        public String fat = "";

        // 内脏脂肪 保留一位小数
        @XmlTag(name = "VisceralFat")
        public String visceralFat = "";

        // 身体指数计算公式
        @XmlTag(name = "BMI")
        public String bMI = "";

        // 基础代谢 保留一位小数
        @XmlTag(name = "BMR")
        public String bMR = "";

        // 相对基础代谢 保留一位小数
        @XmlTag(name = "RBMR")
        public String rBMR = "";

        // 身体水分含量 保留一位小数 单位:%
        @XmlTag(name = "TBW")
        public String tBW = "";

        // 肌肉含量 保留一位小数
        @XmlTag(name = "Mus")
        public String mus = "";

        // 骨含量 保留一位小数 单位:%
        @XmlTag(name = "Bone")
        public String bone = "";

        // 身体类型-->
        @XmlTag(name = "Ctype")
        public String ctype = "";

        // 健康建议-
        @XmlTag(name = "Cname")
        public String cname = "";
    }
}
