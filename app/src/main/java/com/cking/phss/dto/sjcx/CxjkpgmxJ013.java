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
 * 查询健康评估明细J013
 * 
 * 
 */
public class CxjkpgmxJ013 implements IDto {
    /**
     * Request
     */
    @XmlTag(name = "Request")
    public Request request = null;

    static public class Request implements IDto {
        @XmlAttribute(name = "OrgCode")
        public String orgCode = Global.orgCode;

        @XmlAttribute(name = "OperType")
        public String operType = "J013";

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

        // 营养评估
        @XmlTag(name = "Eval")
        public String eval = "";

        // --风险因素 -
        @XmlTag(name = "Risk")
        public String risk = "";

        // 膳食指导
        @XmlTag(name = "Suggest")
        public String suggest = "";

       // 运动处方 
        @XmlTag(name = "Prescribe")
        public String prescribe = "";

        // 健康提示 
        @XmlTag(name = "Tips")
        public String tips = "";

        // 慢病知识 
        @XmlTag(name = "Knowledge")
        public String knowledge = "";

		//数据来源, 有多条记录，因此会有多个DeviceUse节点
		@XmlTag(name = "DeviceUse",isListWithoutGroupTag=true)
		public List<DeviceUse> deviceUses = DeviceUseFactory.getDtoDeviceUses(getClass());
    }
}
