package com.cking.phss.dto.sfgl.tnb;

import java.util.ArrayList;
import java.util.List;

import com.cking.phss.bean.BeanCD;
import com.cking.phss.dto.IDto;
import com.cking.phss.dto.innner.DeviceUse;
import com.cking.phss.dto.innner.MedicineUse;
import com.cking.phss.global.Global;
import com.cking.phss.util.DeviceUseFactory;
import com.cking.phss.xml.util.XmlAttribute;
import com.cking.phss.xml.util.XmlTag;

/**
 * 23 保存糖尿病随访记录.xml
 * 
 * @author Administrator
 */
public class Bctnbsfjl23 implements IDto {
	/**
	 * Request
	 */
	@XmlTag(name = "Request")
	public Request request = null;

	static public class Request implements IDto {
		@XmlAttribute(name = "OrgCode")
		public String orgCode = Global.orgCode;

		@XmlAttribute(name = "OperType")
		public String operType = "23";

		// 必填。存盘类型，1：新增存盘 2：编辑存盘
		@XmlTag(name = "Type")
		public int type = 0;

		// 必填。 操作用户ID或代码
		@XmlTag(name = "UserID")
		public String userID = "";

		// 必填。个人档案号
		@XmlTag(name = "ResidentID")
		public String residentID = "";

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
		public int SFFSCD = 0;

		//<!--必填。随访方式, 文字值-->
		@XmlTag(name = "SFFSName")
		public String sffsName = "";

		// 必填。下次随访日期，格式：yyyy-mm-dd（在PAD界面中此字段只读）
		@XmlTag(name = "XCSF")
		public String XCSF = "";

		// 症状代码（多选），多个代码之间用英文|分隔，值为代码：1无症状；2多饮；3多食；4多尿；5视力模糊；6感染 ；7手脚麻木；8下肢浮肿；9
		// 体重明显下降；10其他
		@XmlTag(name = "ZZCD")
		public String ZZCD = "";

		//<!--症状, 文字值, 选其他时可填其他的具体内容-->
		@XmlTag(name = "ZZName")
		public String zzName = "";

		// 症状其它
		@XmlTag(name = "ZZQT")
		public String ZZQT = "";

		// 必填。收缩压。单位：mmHg，整型
		@XmlTag(name = "SBP")
		public int SBP = 0;

		// 必填。舒张压。单位：mmHg，整型
		@XmlTag(name = "DBP")
		public int DBP = 0;

        // <!-- 必填。心率。单位：次/分钟 -->
        @XmlTag(name = "HeartRate")
        public int HeartRate = 0;

		// 必填。本次体重，单位：kg，浮点
		@XmlTag(name = "BCTZ")
		public String BCTZ = "";

		// 必填。本次身高。单位：cm。整型
		@XmlTag(name = "BCSG")
		public String BCSG = "";

		// 下次希望体重，单位：kg，浮点
		@XmlTag(name = "XCTZ")
		public String XCTZ = "";

		// 本次体质指数，界面中自动计算。公式=体重（kg）/身高的平方（m2）。浮点
		@XmlTag(name = "TZZS")
		public String TZZS = "";

		//<!-- 下次体质指数，界面中自动计算。公式=体重（kg）/身高的平方（m2） -->
		@XmlTag(name = "XCZS")
		public String xczs = "";

		// 足背动脉搏动（单选），值为代码：1 未触及2 触及
		@XmlTag(name = "DMBDCD")
		public int DMBDCD = 0;

		//<!-- 足背动脉搏动, 文字值-->
		@XmlTag(name = "DMBDName")
		public String dMBDName = "";

        // <!-- 当前腰围 -->
        @XmlTag(name = "WaistNow")
        public String WaistNow = "";

        // <!-- 目标腰围 -->
        @XmlTag(name = "WaistTarget")
        public String WaistTarget = "";

		// 其它体征
		@XmlTag(name = "QTTZ")
		public String QTTZ = "";

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

		// 日主食量，单位：克/天，整型
		@XmlTag(name = "BCZSL")
		public int BCZSL = 0;

		// 下次希望主食量，单位：克/天，整型
		@XmlTag(name = "XCZSL")
		public int XCZSL = 0;

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

		//<!-- 必填。血糖类型， CD：ID或代码，1.空腹血糖，2.餐后两小时血糖，3.随机血糖, 4.其他-->
		@XmlTag(name = "XTLX")
        public BeanCD xTLX = null;

		// 必填。空腹血糖，单位：mmol/L，浮点
		@XmlTag(name = "KFXT")
		public String KFXT = "";

		//<!-- 必填。餐后两小时血糖，单位：mmol/L，浮点-->
		@XmlTag(name = "CHXT")
		public String cHXT = "";

		//<!-- 必填。其他血糖类型的血糖值，单位：mmol/L，浮点-->
		@XmlTag(name = "QTXT")
		public String qTXT = "";

		// 检查时间，格式：yyyy-mm-dd
		@XmlTag(name = "JCSJ")
		public String JCSJ = "";

		// 糖化血红蛋白，单位：%，浮点
		@XmlTag(name = "XHDB")
		public String XHDB = "";

		// 其他检查
		@XmlTag(name = "QTJC")
		public String QTJC = "";

		// 服药依从性代码（单选），值为代码：1.规律服药 2.间断服药 3.不服药
		@XmlTag(name = "FYYCXCD")
		public int FYYCXCD = 0;

		//<!--服药依从性, 文字值-->
		@XmlTag(name = "FYYCXName")
		public String fyycxName = "";

		// 药物不良反应（单选），值为代码：0：无；1：有
		@XmlTag(name = "BLFY")
		public int BLFY = 0;

		// 不良反应情况
		@XmlTag(name = "FYQK")
		public String FYQK = "";

		// 低血糖反应代码（单选），值为代码：1无 2 偶尔 3频繁
		@XmlTag(name = "DXTFYCD")
		public int DXTFYCD = 0;

		//<!-- 低血糖反应, 文字值 -->
		@XmlTag(name = "DXTFYName")
		public String dXTFYName = "";

		//<!--胰岛素使用情况, 包括：胰岛素用药种类，胰岛素用药使用频率(次/d)，胰岛素用药次剂量(U)；有多条记录时，用英文|隔开 -->
		@XmlTag(name = "InsulinUse")
		public String insulinUse = "";

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

        //<!-- 转诊回访--并发症 CD包括（足病、肾病、眼病、脑病、心脏病、皮肤病、性病、其他酮症酸中毒、非酮症性高渗性昏迷、乳酸性酸中毒、低血糖昏迷、泌尿道感染、呼吸道感染、皮肤感染、酮症酸中毒、肾病、心脏病变、神经病变、眼部病变、眼部病变、糖尿病足、心脏病、脑血管病变、肢端坏疽、其他）的代码；值：文字-->
        @XmlTag(name = "Complication")
        public BeanCD complication = null;

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
		
        @XmlAttribute(name = "TipMsg")
        public String tipMsg = "";

		//必填。个人档案号
		@XmlTag(name = "ResidentID")
		public String ResidentID= "";
		
		//必填。随访序号
		@XmlTag(name = "VisitID")
		public String VisitID= "";  
	}
	public void init(){
		/**
		 * Request
		 */
		this.request = new Request();
		this.request.orgCode = "23";
		this.request.operType = "23";
		this.request.type = 1;
        this.request.userID = "1023";
		this.request.residentID = "1023";
		this.request.visitID = "1023";
		this.request.visitDate = "2012-10-04";
		this.request.doctorID = "18823";
		this.request.doctorName = "安哥";
		this.request.SFFSCD = 1;
		this.request.XCSF = "2012-11-11";
		this.request.ZZCD = "1";
		this.request.ZZQT = "其他症状都很正常";
		this.request.SBP = 13;
		this.request.DBP = 23;
		this.request.BCTZ = "61";
		this.request.BCSG ="171";
		this.request.XCTZ = "61";
		this.request.TZZS = "正常体质";
		this.request.DMBDCD = 1;
		this.request.QTTZ = "无";
		this.request.BCXYL = 1;
		this.request.XCXY = 2;
		this.request.BCYJ = 2;
		this.request.XCYJ = 4;
		this.request.YDZC = 2;
		this.request.YDCF = 30;
		this.request.XCYDZC = 2;
		this.request.XCYDCD = 22;
		this.request.BCZSL = 22;
		this.request.XCZSL = 44;
		this.request.XLTZCD = 1;
		this.request.ZYXWCD = 1;
		this.request.KFXT = "22.0";
		this.request.JCSJ = "2017";
		this.request.XHDB = "22";
		this.request.QTJC = "无";
		this.request.FYYCXCD = 1;
		this.request.BLFY = 1;
		this.request.DXTFYCD = 1;
		this.request.SFFLCD = 1;
		this.request.ZZYY = "wu";
		this.request.ZZKB = "11";
		this.request.BZ = "wu";
				
				
		/**
		 * Response
		 */
		this.response = new Response();
		this.response.errMsg = "23";
		this.response.ResidentID = "1023";
		this.response.VisitID = "1023";
	}
	
}
