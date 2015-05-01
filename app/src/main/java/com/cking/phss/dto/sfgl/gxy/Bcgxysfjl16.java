package com.cking.phss.dto.sfgl.gxy;

import java.util.ArrayList;
import java.util.List;

import com.cking.phss.bean.BeanCD;
import com.cking.phss.dto.IDto;
import com.cking.phss.dto.innner.DeviceUse;
import com.cking.phss.dto.innner.MedicineRecord;
import com.cking.phss.dto.innner.MedicineUse;
import com.cking.phss.global.Global;
import com.cking.phss.util.DeviceUseFactory;
import com.cking.phss.xml.util.XmlAttribute;
import com.cking.phss.xml.util.XmlTag;

/**
 * 16 保存高血压随访记录.xml
 * 
 * @author Administrator
 */
public class Bcgxysfjl16 implements IDto {
	/**
	 * Request
	 */
	@XmlTag(name = "Request")
	public Request request = null;

	static public class Request implements IDto {
		@XmlAttribute(name = "OrgCode")
		public String orgCode = Global.orgCode;

		@XmlAttribute(name = "OperType")
		public String operType = "16";

		// 存盘类型，1：新增存盘 2：编辑存盘
		@XmlTag(name = "Type")
		public int type = 0;

		// 操作用户ID
		@XmlTag(name = "UserID")
		public String userID = "";

		// 个人档案号
		@XmlTag(name = "ResidentID")
		public String residentID = "";

		//--个人姓名--
		@XmlTag(name = "ResidentName")
		public String residentName = "";

		// 随访序号，如果是新增则无随访序号（新增时此ID由程序后台生成）
		@XmlTag(name = "VisitID")
		public String visitID = "";

		// 必填。随访日期，格式：yyyy-mm-dd
		@XmlTag(name = "VisitDate")
		public String visitDate = "";

		// 必填。随访医生ID或代码
		@XmlTag(name = "DoctorID")
		public String doctorID = "";

		// 必填。随访医生姓名
		@XmlTag(name = "DoctorName")
		public String doctorName = "";

		//<!-- 责任医生ID -->
		@XmlTag(name = "DutyDoctorID")
		public String dutyDoctorID = "";

		//<!-- 责任医生姓名 -->
		@XmlTag(name = "DutyDoctorName")
		public String dutyDoctorName = "";

		// 必填。随访方式代码（单选），值为代码：1.门诊 2.家庭 3.电话 4.集体
		@XmlTag(name = "SFFSCD")
		public int sffscd = 0;

		//<!--必填。随访方式, 文字值-->
		@XmlTag(name = "SFFSName")
		public String sffsName = "";

		// 下次随访日期，不管是新增还是编辑，此字段值由后台根据规定生成，因此这节点不传递
		@XmlTag(name = "XCSF")
		public String xcsf = "";

        // 高血压类型代码（单选）。值为代码：1：原发性；2：继发性；3：不详
        @XmlTag(name = "HBPTypeCD")
        public String hBPTypeCD = "";

        // <!--高血压类型,文字值-->
        @XmlTag(name = "HBPTypeName")
        public String hBPTypeName = "";

		// 症状代码（多选），多个代码之间用英文|分隔，值为代码：1无症状；2头痛头晕；3恶心呕吐；4眼花耳鸣；5呼吸困难；6心悸胸闷；7鼻衄出血不止；8四肢发麻；9下肢水肿；10其它
		@XmlTag(name = "ZZCD")
		public String ZZCD = "";

		//<!--症状, 文字值, 选其他时可填其他的具体内容-->
		@XmlTag(name = "ZZName")
		public String zzName = "";

		// 此处填其它症状
		@XmlTag(name = "ZZQT")
		public String ZZQT = "";

		// 必填。收缩压。单位：mmHg。整型
		@XmlTag(name = "SBP")
		public int SBP = 0;

		// 必填。舒张压。单位：mmHg。整型
		@XmlTag(name = "DBP")
		public int DBP = 0;

		// 必填。本次体重。单位：kg。浮点
		@XmlTag(name = "BCTZ")
		public String BCTZ = "";

		// 必填。本次身高。单位：cm。整型
		@XmlTag(name = "BCSG")
		public String BCSG = "";

		// 本次体质指数，界面中自动计算。公式=体重（kg）/身高的平方（m2）。浮点
		@XmlTag(name = "TZZS")
		public String TZZS = "";

		// 必填。本次心率。单位：次/分。整型
		@XmlTag(name = "BCXL")
		public int BCXL = 0;

		// 其它体征
		@XmlTag(name = "QTTZ")
		public String QTTZ = "";

		// 下次希望体重。单位：kg。浮点
		@XmlTag(name = "XCTZ")
		public String XCTZ = "";

		//<!-- 下次体质指数，界面中自动计算。公式=体重（kg）/身高的平方（m2） -->
		@XmlTag(name = "XCZS")
		public String xczs = "";

		// 下次心率。单位：次/分。整型
		@XmlTag(name = "XCXL")
		public int XCXL = 0;

		// 本次日吸烟量。单位：支。整型
		@XmlTag(name = "BCXYL")
		public int BCXYL = 0;

		// 下次希望日吸烟量。单位：支。整型
		@XmlTag(name = "XCXY")
		public int XCXY = 0;

		// 本次日饮酒量。单位：两。整型
		@XmlTag(name = "BCYJ")
		public int BCYJ = 0;

		// 下次希望日饮酒量。单位：两。整型
		@XmlTag(name = "XCYJ")
		public int XCYJ = 0;

		// 本次运动每周几次。整型
		@XmlTag(name = "YDZC")
		public int YDZC = 0;

		// 本次运动每次几分钟。整型
		@XmlTag(name = "YDCF")
		public int YDCF = 0;

		// 下次随访目标每周几次。整型
		@XmlTag(name = "XCYDZC")
		public int XCYDZC = 0;

		// 下次随访目标每次几分钟。整型
		@XmlTag(name = "XCYDCD")
		public int XCYDCD = 0;

		// 本次日摄盐量，单位：克，整型
		@XmlTag(name = "BCSYL")
		public int BCSYL = 0;

		// 下次目标摄盐量，单位：克，整型
		@XmlTag(name = "XCSYL")
		public int XCSYL = 0;

		// 心理调整代码（单选），值为代码：1良好 2一般 3差
		@XmlTag(name = "XLTZCD")
		public int XLTZCD = 0;

		//<!-- 心理调整, 文字值-->
		@XmlTag(name = "XLTZName")
		public String xltzName = "";

		// 遵医行为代码（单选），值为代码：1.良好 2.一般 3.差
		@XmlTag(name = "ZYXWCD")
		public int ZYXWCD = 0;

		//<!-- 遵医行为, 文字值-->
		@XmlTag(name = "ZYXWName")
		public String zyxwName = "";

		// 辅助检查
		
//		public FZJC fzjc = new FZJC();
//
//		public static class FZJC implements IDto {
//			@XmlTag(name = "JCXM")
//			public String jcxm = "";
//			@XmlTag(name = "JCJG")
//			public String jcjg = "";
//			@XmlTag(name = "JCR")
//			public String jcr = "";
//			@XmlTag(name = "JCRQ")
//			public String jcrq = "";
//		}

		@XmlTag(name = "FZJC")
		public String FZJC = "";

		// 服药依从性代码（单选），值为代码：1.规律服药 2.间断服药 3.不服药
		@XmlTag(name = "FYYCXCD")
		public int FYYCXCD = 0;

		//<!--服药依从性, 文字值-->
		@XmlTag(name = "FYYCXName")
		public String fyycxName = "";

		// 不良反应（单选），值为代码：0：无；1：有
		@XmlTag(name = "BLFY")
		public int BLFY = 0;

		// 假如有不良反应，则具体情况
		@XmlTag(name = "FYQK")
		public String FYQK = "";

		//<!-- 治疗建议 -->
		@XmlTag(name = "Recommendation")
		public String recommendation = "";

		// 此次随访分类代码（单选），值为代码：1控制满意，2控制不满意，3不良反应，4并发症
		@XmlTag(name = "SFFLCD")
		public int SFFLCD = 0;

		//<!--此次随访分类, 文字值-->
		@XmlTag(name = "SFFLName")
		public String sfflName = "";

		// 转诊原因
		@XmlTag(name = "ZZYY")
		public String ZZYY = "";

		// 转诊机构及科别
		@XmlTag(name = "ZZKB")
		public String ZZKB = "";

		//<!-- 转诊回访--日期 -->
		@XmlTag(name = "ReferralVisitDate")
		public String referralVisitDate = "";

		//<!-- 转诊回访--靶器官损害 CD:包括（心脏、脑、肾脏、周围血管、眼底、其他）的代码；值：文字 -->
		@XmlTag(name = "CriticalOrgan")
        public BeanCD criticalOrgan = null;

		//<!-- 转诊回访--合并症 CD包括（左室肥厚、心力衰竭、冠心病、心律失常、脑卒中、主动脉夹层动脉瘤、动脉粥样硬化、视网膜病变、其他）的代码；值：文字-->
		@XmlTag(name = "Comorbidity")
        public BeanCD comorbidity = null;

		//<!-- 转诊回访--其他疾病 -->
		@XmlTag(name = "OtherDiseases")
		public String otherDiseases = "";

		// 备注
		@XmlTag(name = "BZ")
		public String BZ = "";

		/**
		 * 有多条用药情况记录，因此会有多个MedicineUse节点
		 */
		@XmlTag(name = "MedicineUse", isListWithoutGroupTag = true)
		public ArrayList<MedicineUse> medicineUse = null;

		//<!-- 用药记录 -->
        @XmlTag(name = "MedicineRecord")
        public MedicineRecord medicineRecord = null;

		//数据来源, 有多条记录，因此会有多个DeviceUse节点
		@XmlTag(name = "DeviceUse",isListWithoutGroupTag=true)
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

		@XmlTag(name = "ResidentID")
		public String residentID = "";

		@XmlTag(name = "VisitID")
		public String visitID = "";
	}

	public void init() {
		/**
		 * Request
		 */
		this.request = new Request();
		this.request.orgCode = "16";
		this.request.operType = "16";
		this.request.type = 1;
        this.request.userID = "16";
		this.request.residentID = "16";
		this.request.visitID = "16";
		this.request.visitDate = "2012-10-03";
		this.request.doctorID = "1016";
		this.request.doctorName = "安哥";
		this.request.sffscd = 1016;
		this.request.xcsf = "2012-10-12";
		this.request.ZZCD = "1";
		this.request.ZZQT = "无异常";
		this.request.SBP = 0;
		this.request.DBP = 12;
		this.request.BCTZ = "61.5";
		this.request.BCSG = "171";
		this.request.TZZS = "体质正常";
		this.request.BCXL = 68;
		this.request.QTTZ = "其他特征正常";
		this.request.XCTZ = "60";
		this.request.XCXL = 75;
		this.request.BCXYL = 03;
		this.request.XCXY = 04;
		this.request.BCYJ = 03;
		this.request.XCYJ = 04;
		this.request.YDZC = 11;
		this.request.XCYDZC = 19;
		this.request.XCYDCD = 30;
		this.request.BCSYL = 1;
		this.request.XCSYL = 2;
		this.request.XLTZCD = 1;
		this.request.ZYXWCD = 1;
		this.request.FZJC = "1";
		this.request.FYYCXCD = 2;
		this.request.BLFY = 0;
		this.request.FYQK = "没有不良情况";
		this.request.SFFLCD = 1;
		this.request.ZZYY = "该医院设备不全";
		this.request.ZZKB = "什么什么设备";
		this.request.BZ = "无备注";
		/**
		 * Response
		 */
		this.response = new Response();
		this.response.errMsg = "16";
		this.response.residentID = "1016";
		this.response.visitID = "1106";
	}
}
