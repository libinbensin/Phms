package com.cking.phss.dto;

import com.cking.phss.bean.BeanCD;
import com.cking.phss.dto.innner.DeviceUse;
import com.cking.phss.global.Global;
import com.cking.phss.util.DeviceUseFactory;
import com.cking.phss.xml.util.XmlAttribute;
import com.cking.phss.xml.util.XmlTag;

import java.util.List;
/**
 * 保存健康评估
 * @author taowencong
 *  当前分支
 */
public class BcjkpgJ005 implements IDto{
    /**
     * Request
     */
    @XmlTag(name = "Request")
    public Request request = null;
    
    static public class Request implements IDto {
        @XmlAttribute(name = "OrgCode")
        public String orgCode = Global.orgCode;

        @XmlAttribute(name = "OperType")
        public String operType = "J005";
        
        // type
        @XmlTag(name = "Type")
        public String type = "";
        
         // 必填。用户ID
        @XmlTag(name = "UserID")
        public String userID = "";
        // 个人档案号
        @XmlTag(name = "ResidentID")
        public String residentID = "";
        
        
        // 评估序号，如果是新增则无评估序号（新增时此ID由程序后台生成）
        @XmlTag(name = "EvalSn")
        public String evalSn = "";

		//<!--评估类型， CD：代码，1.移动体检，2.定点体检，99.其他  -->
		@XmlTag(name = "EvalType")
        public BeanCD evalType = null;

        // -必填。检查日期，格式：yyyy-mm-dd 
        @XmlTag(name = "CheckDate")
        public String checkDate = "";
        
       // -必填。评估人员ID或编号
        @XmlTag(name = "EvalEmpId")
        public String evalEmpId = "";
        
        // 营养评估
        @XmlTag(name = "Eval")
        public String eval = "";     
        
        //风险因素 
        @XmlTag(name = "Risk")
        public String risk = "";
        
        // 膳食指导 
        @XmlTag(name = "Suggest")
        public String suggest = "";
        
        // 运动处方 
        @XmlTag(name = "Prescribe")
        public String prescribe = "";
        
       //健康提示
        @XmlTag(name = "Tips")
        public String tips = "";
        
        // -慢病知识 
        @XmlTag(name = "Knowledge")
        public String knowledge = "";

		//数据来源, 有多条记录，因此会有多个DeviceUse节点
		@XmlTag(name = "DeviceUse",isListWithoutGroupTag=true)
		public List<DeviceUse> deviceUses = DeviceUseFactory.getDtoDeviceUses(getClass());
    }
    
    /**
     * Response
     */
    @XmlTag(name = "Response")
    public Response response = null;
    
    static public class Response implements IDto {
        @XmlAttribute(name = "ErrMsg")
        public String errMsg = "";
        // 必填。个人档案号
        @XmlTag(name = "ResidentID")
        public String residentID = "";
        
        //必填。随访序号 
        @XmlTag(name = "EvalSn")
        public String evalSn="";
    }
}
