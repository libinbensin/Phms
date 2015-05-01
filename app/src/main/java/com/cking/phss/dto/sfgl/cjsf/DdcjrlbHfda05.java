package com.cking.phss.dto.sfgl.cjsf;

import java.util.List;

import com.cking.phss.bean.BeanCD;
import com.cking.phss.dto.IDto;
import com.cking.phss.dto.innner.CardInfo;
import com.cking.phss.global.Global;
import com.cking.phss.xml.util.XmlAttribute;
import com.cking.phss.xml.util.XmlTag;

/**
 * HFDA05 得到残疾人列表.xml
 * 
 * @author Administrator
 */
public class DdcjrlbHfda05 implements IDto {
	/**
	 * Request
	 */
	@XmlTag(name = "Request")
	public Request request = null;

	static public class Request implements IDto {
		@XmlAttribute(name = "OrgCode")
		public String orgCode = Global.orgCode;

		@XmlAttribute(name = "OperType")
		public String operType = "HFDA05";

		//<!-- 返回记录数，假如为0或空则说明返回所有符合条件的记录-->
        @XmlTag(name = "ReturnRecord")
		public int returnRecord = 0;
		//<!--必填。值：当前登录用户ID -->
        @XmlTag(name = "UserID")
		public String userID = "";
		//<!--社区（村）或者居委会，值：居委名称；ID：居委代码 -->
        @XmlTag(name = "Community")
		public BeanCD community = null;
		//<!--随访医生姓名，查询时用like -->
        @XmlTag(name = "FlupDocotor")
		public String flupDocotor = null;
		//<!--责任医生姓名，查询时用like -->
        @XmlTag(name = "DutyDocotor")
		public String dutyDocotor = null;
		//<!-- 患者姓名，查询时用 like-->
        @XmlTag(name = "ResidentName")
		public String residentName = null;
		//<!--必填。值：随访提醒的条件 0：无；1：至今未访；2：应访日期段-->
        @XmlTag(name = "WarnCondi")
		public String warnCondi = null;
		//<!--假如随访提醒条件为：应访日期段，则值：应访开始日期，格式：yyyy-mm-dd -->
        @XmlTag(name = "WarnSD")
		public String warnSD = null;
		//<!--假如随访提醒条件为：应访日期段，则值：应访结束日期，格式：yyyy-mm-dd -->
        @XmlTag(name = "WarnED")
		public String warnED = null;
		//<!-- 身份证号 -->
        @XmlTag(name = "IDCard")
		public String iDCard = null;
		//<!--个人档案号-->
        @XmlTag(name = "ResidentID")
		public String residentID = null;
		//<!--报卡医生，值：医生姓名，ID：医生ID或代码 。 -->
        @XmlTag(name = "ReportDoctor")
		public BeanCD reportDoctor = null;
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

		//<!--返回记录数 -->
        @XmlTag(name = "ReturnNum")
		public int returnNum = 0;
		//<!-- 可以返回有多个患者的管理卡，每张管理卡一个CardInfo节点,按ResidentName升序-->
		@XmlTag(name = "CardInfo", isListWithoutGroupTag=true)
		public List<CardInfo> cardInfo;
	}

	public void init() {
		// /**
		// * Request
		// */
		// this.request = new Request();
		// this.request.orgCode = "16";
		// this.request.operType = "16";
		// this.request.type = 1;
		// this.request.userID = 16;
		// this.request.residentID = "16";
		// this.request.flupID = "16";
		// this.request.visitDate = "2012-10-03";
		// this.request.doctorID = "1016";
		// this.request.doctorName = "安哥";
		// this.request.sffscd = 1016;
		// this.request.xcsf = "2012-10-12";
		// this.request.ZZCD = "1";
		// this.request.ZZQT = "无异常";
		// this.request.SBP = 0;
		// this.request.DBP = 12;
		// this.request.BCTZ = "61.5";
		// this.request.BCSG = "171";
		// this.request.TZZS = "体质正常";
		// this.request.BCXL = 68;
		// this.request.QTTZ = "其他特征正常";
		// this.request.XCTZ = "60";
		// this.request.XCXL = 75;
		// this.request.BCXYL = 03;
		// this.request.XCXY = 04;
		// this.request.BCYJ = 03;
		// this.request.XCYJ = 04;
		// this.request.YDZC = 11;
		// this.request.XCYDZC = 19;
		// this.request.XCYDCD = 30;
		// this.request.BCSYL = 1;
		// this.request.XCSYL = 2;
		// this.request.XLTZCD = 1;
		// this.request.ZYXWCD = 1;
		// this.request.FZJC = "1";
		// this.request.FYYCXCD = 2;
		// this.request.BLFY = 0;
		// this.request.FYQK = "没有不良情况";
		// this.request.SFFLCD = 1;
		// this.request.ZZYY = "该医院设备不全";
		// this.request.ZZKB = "什么什么设备";
		// this.request.BZ = "无备注";
		// /**
		// * Response
		// */
		// this.response = new Response();
		// this.response.errMsg = "16";
		// this.response.residentID = "1016";
		// this.response.visitID = 1106;
	}
}
