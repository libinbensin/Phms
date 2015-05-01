package com.cking.phss.dto.daxx;

import java.util.List;

import com.cking.phss.bean.BeanCD;
import com.cking.phss.dto.IDto;
import com.cking.phss.dto.innner.DeviceUse;
import com.cking.phss.global.Global;
import com.cking.phss.util.DeviceUseFactory;
import com.cking.phss.xml.util.XmlAttribute;
import com.cking.phss.xml.util.XmlTag;

/**
 * HRS01 保存签约情况.xml
 * 
 * @author Administrator
 */
public class BcqyqkHrs01 implements IDto {
	/**
	 * Request
	 */
	@XmlTag(name = "Request")
	public Request request = null;

	static public class Request implements IDto {
		@XmlAttribute(name = "OrgCode")
		public String orgCode = Global.orgCode;

		@XmlAttribute(name = "OperType")
        public String operType = "HRS01";

		// 必填。 操作用户ID或代码
		@XmlTag(name = "UserID")
        public String UserID = "";

		// 个人档案号。如果是新增则无个人档案号（新增时此ID由程序后台生成
		@XmlTag(name = "ResidentID")
        public String ResidentID = "";
        // <!-- 是否签约 0:否；1:是 -->
        @XmlTag(name = "SignContract")
        public BeanCD SignContract = null;
        // <!-- 签约日期 -->
        @XmlTag(name = "SignDate")
        public String SignDate = "";
        // <!-- 签约地点 -->
        @XmlTag(name = "SignPlace")
        public String SignPlace = "";
        // <!-- 签约医生 -->
        @XmlTag(name = "SignDoctor")
        public String SignDoctor = "";
        // <!-- 签约单位 -->
        @XmlTag(name = "SignUnit")
        public BeanCD SignUnit = null;
        // <!-- 签约类型 -->
        @XmlTag(name = "SignType")
        public BeanCD SignType = null;
        // <!-- 签约知情；0：不同意，1：同意 -->
        @XmlTag(name = "SignKnow")
        public BeanCD SignKnow = null;
        // <!-- 授权电话 -->
        @XmlTag(name = "SignPhone")
        public String SignPhone = "";
        // <!-- 授权状态 -->
        @XmlTag(name = "SignStatus")
        public BeanCD SignStatus = null;
        // <!-- 操作医生（登陆医生） -->
        @XmlTag(name = "OperDoctor")
        public String OperDoctor = "";
        // <!-- 数据来源, 有多条记录，因此会有多个DeviceUse节点-->
        @XmlTag(name = "DeviceUse", isListWithoutGroupTag = true)
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

		// 必填。个人档案号
		@XmlTag(name = "ResidentID")
        public String ResidentID = "";

        // <!-- 是否签约 0:否；1:是 -->
        @XmlTag(name = "SignContract")
        public BeanCD SignContract = null;
        // <!-- 签约时间 -->
        @XmlTag(name = "SignDate")
        public String SignDate = "";
	}

}
