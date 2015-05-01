package com.cking.phss.dto.sjcx;

import java.util.List;

import com.cking.phss.bean.BeanCD;
import com.cking.phss.dto.IDto;
import com.cking.phss.dto.innner.DeviceUse;
import com.cking.phss.global.Global;
import com.cking.phss.util.DeviceUseFactory;
import com.cking.phss.xml.util.XmlAttribute;
import com.cking.phss.xml.util.XmlTag;

/**
 * 查询心理+中医明细J012
 * 
 * 
 */
public class CxxlzymxJ012 implements IDto {
    /**
     * Request
     */
    @XmlTag(name = "Request")
    public Request request = null;

    static public class Request implements IDto {
        @XmlAttribute(name = "OrgCode")
        public String orgCode = Global.orgCode;

        @XmlAttribute(name = "OperType")
        public String operType = "J012";

        // 必填。个人档案号
        @XmlTag(name = "ResidentID")
        public String residentID = "";

        // 必填。评估序号 若输入0 则默认查询最后一条数据
        @XmlTag(name = "EvalSn")
        public String evalSn = "";
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

        // 必填。个人档案号
        @XmlTag(name = "ResidentID")
        public String residentID = "";

        // 必填。姓名-
        @XmlTag(name = "ResidentName")
        public String residentName = "";

        // 评估序号
        @XmlTag(name = "EvalSn")
        public String evalSn = "";

		//<!--评估类型， CD：代码，1.移动体检，2.定点体检，99.其他  -->
		@XmlTag(name = "EvalType")
        public BeanCD evalType = null;

        // 。检查日期
        @XmlTag(name = "CheckDate")
        public String checkDate = "";

        // 必填。评估人员ID或编号
        @XmlTag(name = "EvalEmpId")
        public String evalEmpId = "";

        // -必填。评估人员姓名-
        @XmlTag(name = "EvalEmpName")
        public String evalEmpName = "";

        // -心理_结论
        @XmlTag(name = "Summury")
        public String summury = "";

        // 心理_建议
        @XmlTag(name = "Suggest")
        public String suggest = "";

        // -中医_体质类型1
        @XmlTag(name = "TcmType1")
        public String tcmType1 = "";

        // 中医_体质描述1
        @XmlTag(name = "TcmDescribe1")
        public String tcmDescribe1 = "";

        // -中医_体质指导建议1
        @XmlTag(name = "TcmSuggest1")
        public String tcmSuggest1 = "";

        // -中医_体质分型1
        @XmlTag(name = "TcmKind1")
        public String tcmKind1 = "";

        // -中医_体质类型2
        @XmlTag(name = "TcmType2")
        public String tcmType2 = "";

        // 中医_体质描述2
        @XmlTag(name = "TcmDescribe2")
        public String tcmDescribe2 = "";

        // -中医_体质指导建议2
        @XmlTag(name = "TcmSuggest2")
        public String tcmSuggest2 = "";

        // -中医_体质分型2
        @XmlTag(name = "TcmKind2")
        public String tcmKind2 = "";

		//数据来源, 有多条记录，因此会有多个DeviceUse节点
		@XmlTag(name = "DeviceUse",isListWithoutGroupTag=true)
		public List<DeviceUse> deviceUses = DeviceUseFactory.getDtoDeviceUses(getClass());
    }
}
