package com.cking.phss.dto.sfgl.cjsf;

import java.util.List;

import com.cking.phss.bean.BeanCD;
import com.cking.phss.dto.IDto;
import com.cking.phss.dto.innner.DeviceUse;
import com.cking.phss.global.Global;
import com.cking.phss.util.DeviceUseFactory;
import com.cking.phss.xml.util.XmlAttribute;
import com.cking.phss.xml.util.XmlTag;


/**
 * HFDA03 保存残疾人随访记录.xml
 * 
 * @author Administrator
 */
public class BccjrsfjlHfda03 implements IDto {
	/**
	 * Request
	 */
	@XmlTag(name = "Request")
	public Request request = null;

	static public class Request implements IDto {
		@XmlAttribute(name = "OrgCode")
		public String orgCode = Global.orgCode;

		@XmlAttribute(name = "OperType")
		public String operType = "HFDA03";

		//<!--必填。存盘类型，1：新增存盘 2：编辑存盘-->
		@XmlTag(name = "Type")
		public int type = 0;

		// 操作用户ID
		@XmlTag(name = "UserID")
		public String userID = "";
		//<!-- 档案编号 -->
		@XmlTag(name = "ResidentID")
		public String residentID = "";
		//<!-- 姓名 -->
		@XmlTag(name = "ResidentName")
		public String residentName = "";
		//<!-- 随访ID -->
		@XmlTag(name = "FlupID")
		public String flupID = "";
		//<!-- 困难群体, CD：代码 ；0.非困难群体，1.低保对象，2.五保对象，2.特困残疾人 -->
        @XmlTag(name = "VulnerableGroup")
		public BeanCD vulnerableGroup = null;
		//<!-- 康复需求, CD：代码 ；1.有，2.无-->
        @XmlTag(name = "RecureDemands")
		public BeanCD recureDemands = null;
		//<!-- 随访方式, CD：代码 ；1.门诊 2.家庭 3.电话 4.集体-->
        @XmlTag(name = "FlupWay")
		public BeanCD flupWay = null;
		//<!-- 残疾情况 - 残疾证号 -->
        @XmlTag(name = "DeformityCard")
		public String deformityCard = "";
		//<!-- 残疾情况 - 主要残疾, CD：代码 ；1.视力残疾，2.听力残疾，3.言语残疾，4.肢体残疾，5.智力残疾，6.精神残疾-->
        @XmlTag(name = "MainDisability")
		public BeanCD mainDisability = null;
		//<!-- 残疾情况 - 多重残疾标志，1.是，2.否 -->
        @XmlTag(name = "MultiDisabilityFlag")
        public int multiDisabilityFlag = 2;
		//<!-- 残疾情况 - 多重残疾, CD：代码 （多选）；多条用英文逗号隔开；1.视力残疾，2.听力残疾，3.言语残疾，4.肢体残疾，5.智力残疾，6.精神残疾-->
        @XmlTag(name = "MultiDisability")
		public BeanCD multiDisability = null;
		//<!-- 残疾情况 - 残疾程度, CD：代码 ；1.一级，2.二级，3.三级，4.四级，99.未评定-->
        @XmlTag(name = "DisabilityLevel")
		public BeanCD disabilityLevel = null;
		//<!-- 残疾情况 - 致残原因, CD：代码 -->
        @XmlTag(name = "DisabilityReason")
		public BeanCD disabilityReason = null;
		//<!-- 残疾情况 - 致残时间 -->
        @XmlTag(name = "DisabilityDate")
		public String disabilityDate = "";
		//<!-- 症状 -->
        @XmlTag(name = "Symptom")
		public String symptom = "";
		//<!-- 体征 - 身高 -->
        @XmlTag(name = "Height")
		public String height = "";
		//<!-- 体征 - 体重 -->
        @XmlTag(name = "Weight")
		public String weight = "";
		//<!-- 体征 - 体质指数 -->
        @XmlTag(name = "BodyMassIndex")
		public String bodyMassIndex = "";
		//<!-- 体征 - 收缩压 -->
        @XmlTag(name = "SystolicPressure")
		public String systolicPressure = "";
		//<!-- 体征 - 舒张压 -->
        @XmlTag(name = "DiastolicPressure")
		public String diastolicPressure = "";
		//<!-- 体征 - 心率 -->
        @XmlTag(name = "HeartRate")
		public String heartRate = "";
		//<!-- 体征 - 其他体征 -->
        @XmlTag(name = "OtherSigns")
		public String otherSigns = "";
		//<!-- 康复服务 - 康复项目, CD：代码 ；1.功能训练，2.辅助器具，3.心理服务，4.知识普及，5.用药指导，6.转介服务，99.其它-->
        @XmlTag(name = "RecureProject")
		public BeanCD recureProject = null;
		//<!-- 康复服务 - 训练频率 (次/月) -->
        @XmlTag(name = "TrainingFrequency")
		public String trainingFrequency = "";
		//<!-- 康复服务 - 训练时长 (分钟/次) -->
        @XmlTag(name = "TrainingDuration")
		public String trainingDuration = "";
		//<!-- 康复服务 - 训练地点, CD：代码 ；1.专业机构，2.家庭，3.社区-->
        @XmlTag(name = "TrainingPlace")
		public BeanCD trainingPlace = null;
		//<!-- 康复服务 - 训练目标, CD：代码 ；1.运动能力改善，2.感知能力提高，3.认知能力提高，4.交往能力提高，5.自理能力提高，6.适应能力提高，99.其它-->
        @XmlTag(name = "TrainingTarget")
		public BeanCD trainingTarget = null;
		//<!-- 康复服务 - 训练评估, CD：代码 ；1.满意，2.一般，3.不满意-->
        @XmlTag(name = "TrainingEvaluation")
		public BeanCD trainingEvaluation = null;
		//<!-- 康复服务 - 转介去向 -->
        @XmlTag(name = "ReferralDirection")
		public String referralDirection = "";
		//<!-- 康复服务 - 转介原因 -->
        @XmlTag(name = "ReferralReason")
		public String referralReason = "";
		//<!-- 生活方式指导 - 是否吸烟, CD：代码 ，1.是，2.否-->
        @XmlTag(name = "Smoking")
		public BeanCD smoking = null;
		//<!-- 生活方式指导 - 日吸烟量（支） -->
        @XmlTag(name = "SmokingDay")
		public String smokingDay = "";
		//<!-- 生活方式指导 - 是否饮酒, CD：代码 ，1.是，2.否-->
        @XmlTag(name = "Drinking")
		public BeanCD drinking = null;
		//<!-- 生活方式指导 - 日饮酒量（两/次） -->
        @XmlTag(name = "DrinkingDay")
		public String drinkingDay = "";
		//<!-- 生活方式指导 - 饮酒种类, CD：代码 ； 1.白酒，2.红酒，3.黄酒，4.米酒，99.其他-->
        @XmlTag(name = "DrinkingType")
		public BeanCD drinkingType = null;
		//<!-- 生活方式指导 - 是否运动;1.是，2.否-->
        @XmlTag(name = "Exercise")
		public BeanCD exercise = null;
		//<!-- 生活方式指导 - 运动项目, CD：代码 ；1.散步，2.慢跑，3.太极拳，4.气功，5.球类运动，6.跳舞，99.其他-->
        @XmlTag(name = "ExerciseEvent")
		public BeanCD exerciseEvent = null;
		//<!-- 生活方式指导 - 运动频率 (次/周) -->
        @XmlTag(name = "ExerciseFrequency")
		public String exerciseFrequency = "";
		//<!-- 生活方式指导 - 运动时长 (分钟/次) -->
        @XmlTag(name = "ExerciseDuration")
		public String exerciseDuration = "";
		//<!-- 生活方式指导 - 摄盐量 -->
        @XmlTag(name = "SaltIntake")
		public String saltIntake = "";
		//<!-- 生活方式指导 - 摄盐结论 -->
        @XmlTag(name = "SaltConclusion")
		public String saltConclusion = "";
		//<!-- 生活方式指导 - 目标摄盐量 -->
        @XmlTag(name = "SaltTarget")
		public String saltTarget = "";
		//<!-- 生活方式指导 - 心理调整, CD：代码 ；1.良好，2.一般，3.差-->
        @XmlTag(name = "Psyche")
		public BeanCD psyche = null;
		//<!-- 生活方式指导 - 遵医行为；1.良好，2.一般，3.差-->
        @XmlTag(name = "Compliance")
		public BeanCD compliance = null;
		//<!-- 此次随访评估；1.满意，2.一般，3.不满意-->
        @XmlTag(name = "Evaluation")
		public BeanCD evaluation = null;
		//<!-- 康复建议 -->
        @XmlTag(name = "Suggest")
		public String suggest = "";
		//<!-- 随访日期 -->
        @XmlTag(name = "FlupDate")
		public String flupDate = "";
		//<!-- 下次随访日期 -->
        @XmlTag(name = "NextFlupDate")
		public String nextFlupDate = "";
		//<!-- 责任医生ID -->
        @XmlTag(name = "DutyDoctorID")
		public String dutyDoctorID = "";
		//<!-- 责任医生姓名 -->
        @XmlTag(name = "DutyDoctorName")
		public String dutyDoctorName = "";
		//<!-- 转诊标志 -->
        @XmlTag(name = "TransferFlag")
		public String transferFlag = "";
		//<!-- 转诊原因 -->
        @XmlTag(name = "TransferReason")
		public String transferReason = "";
		//<!-- 转入医疗机构名称 -->
        @XmlTag(name = "TransferInstitution")
		public String transferInstitution = "";
		//<!-- 转入机构科室名称 -->
        @XmlTag(name = "TransferDepartment")
		public String transferDepartment = "";
		//<!-- 数据来源, 有多条记录，因此会有多个DeviceUse节点-->
		@XmlTag(name = "DeviceUse", isListWithoutGroupTag=true)
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

		//    <!--如果保存成功则返回个人档案号、随访序号 -->
		//    <!--必填。个人档案号-->
		@XmlTag(name = "ResidentID")
		public String residentID = "";
		
		//    <!--必填。随访序号 -->
		@XmlTag(name = "FlupID")
		public int flupID = 0;
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
