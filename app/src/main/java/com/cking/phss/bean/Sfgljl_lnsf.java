/* Xinhuaxing Inc. (C) 2014. All rights reserved.
 *
 * Sfgljl_jsb.java
 * classes : com.cking.phss.bean.Sfgljl_jsb
 * @author Administrator
 * V 1.0.0
 * Create at 2014-6-30 下午4:27:24
 */
package com.cking.phss.bean;

import java.util.List;

import com.cking.phss.dto.innner.DeviceUse;
import com.cking.phss.xml.util.XmlTag;

/**
 * com.cking.phss.bean.Sfgljl_jsb
 * @author Administrator <br/>
 * create at 2014-6-30 下午4:27:24
 */
public class Sfgljl_lnsf implements IBean {
    private static final String TAG = "Sfgljl_lnsf";

    // 操作用户ID
    @XmlTag(name = "UserID")
    public String userID = "";
    // <!-- 档案编号 -->
    @XmlTag(name = "ResidentID")
    public String residentID = "";
    // <!-- 姓名 -->
    @XmlTag(name = "ResidentName")
    public String residentName = "";
    // <!-- 随访ID -->
    @XmlTag(name = "FlupID")
    public String flupID = "";

    // <!-- 新症状, CD：代码
    // （多选），多条用英文逗号隔开；1.无不适，2.头痛头晕，3.恶心呕吐，4.眼花耳鸣，5.呼吸困难，6.心悸胸闷，7.四肢发麻，8.下肢水肿，9.皮肤瘙痒，99.其他-->
    @XmlTag(name = "NewSymptom")
    public BeanCD newSymptom = null;

    // <!-- 原症状, CD：代码
    // （多选），多条用英文逗号隔开；1.无不适，2.头痛头晕，3.恶心呕吐，4.眼花耳鸣，5.呼吸困难，6.心悸胸闷，7.四肢发麻，8.下肢水肿，9.皮肤瘙痒，99.其他-->
    @XmlTag(name = "OldSymptom")
    public BeanCD oldSymptom = null;

    // <!--必填。随访方式 CD:代码（单选），值为代码：1.门诊 2.家庭 3.电话 4.集体 -->
    @XmlTag(name = "FlupWay")
    public BeanCD flupWay = null;

    // <!-- 随访周期建议，CD:包括（无需、每2年、每年、每3个月、半年）的代码，值：文字值 -->
    @XmlTag(name = "Cycle")
    public BeanCD cycle = null;

    // <!-- 随访性质，CD:包括（一般人群、重点管理疾病的高危人群、患者）的代码，值：文字值 -->
    @XmlTag(name = "Nature")
    public BeanCD nature = null;

    // <!-- 体征 - 身高 -->
    @XmlTag(name = "Height")
    public String height = "";
    // <!-- 体征 - 体重 -->
    @XmlTag(name = "Weight")
    public String weight = "";
    // <!-- 体征 - 体质指数 -->
    @XmlTag(name = "BodyMassIndex")
    public String bodyMassIndex = "";
    // <!-- 体征 - 收缩压 -->
    @XmlTag(name = "SystolicPressure")
    public String systolicPressure = "";
    // <!-- 体征 - 舒张压 -->
    @XmlTag(name = "DiastolicPressure")
    public String diastolicPressure = "";
    // <!-- 体征 - 心率 -->
    @XmlTag(name = "HeartRate")
    public String heartRate = "";
    // <!-- 体征 - 其他体征 -->
    @XmlTag(name = "OtherSigns")
    public String otherSigns = "";

    // <!-- 体征-腰围 -->
    @XmlTag(name = "Waist")
    public String waist = "";

    // <!-- 体征-空腹血糖 -->
    @XmlTag(name = "FastingBloodGlucose")
    public String fastingBloodGlucose = "";

    // <!-- 体征-总胆固醇 -->
    @XmlTag(name = "Cholesterol")
    public String cholesterol = "";

    // <!-- 体征-甘油三酯 -->
    @XmlTag(name = "Triglyceride")
    public String triglyceride = "";

    // <!-- 体征-血清低密度脂蛋白胆固醇 -->
    @XmlTag(name = "LDensityLipoprotein")
    public String lDensityLipoprotein = "";

    // <!-- 体征-血清高密度脂蛋白胆固醇 -->
    @XmlTag(name = "HDensityLipoprotein")
    public String hDensityLipoprotein = "";

    // <!-- 生活方式指导 - 是否吸烟, CD：代码 ，1.是，2.否-->
    @XmlTag(name = "Smoking")
    public BeanCD smoking = null;
    // <!-- 生活方式指导 - 日吸烟量（支） -->
    @XmlTag(name = "SmokingDay")
    public String smokingDay = "";
    // <!-- 生活方式指导 - 是否饮酒, CD：代码 ，1.是，2.否-->
    @XmlTag(name = "Drinking")
    public BeanCD drinking = null;
    // <!-- 生活方式指导 - 日饮酒量（两/次） -->
    @XmlTag(name = "DrinkingDay")
    public String drinkingDay = "";
    // <!-- 生活方式指导 - 饮酒种类, CD：代码 ； 1.白酒，2.红酒，3.黄酒，4.米酒，99.其他-->
    @XmlTag(name = "DrinkingType")
    public BeanCD drinkingType = null;
    // <!-- 生活方式指导 - 是否运动;1.是，2.否-->
    @XmlTag(name = "Exercise")
    public BeanCD exercise = null;
    // <!-- 生活方式指导 - 运动项目, CD：代码 ；1.散步，2.慢跑，3.太极拳，4.气功，5.球类运动，6.跳舞，99.其他-->
    @XmlTag(name = "ExerciseEvent")
    public BeanCD exerciseEvent = null;
    // <!-- 生活方式指导 - 运动频率 (次/周) -->
    @XmlTag(name = "ExerciseFrequency")
    public String exerciseFrequency = "";
    // <!-- 生活方式指导 - 运动时长 (分钟/次) -->
    @XmlTag(name = "ExerciseDuration")
    public String exerciseDuration = "";
    // <!-- 生活方式指导 - 摄盐量 -->
    @XmlTag(name = "SaltIntake")
    public String saltIntake = "";
    // <!-- 生活方式指导 - 摄盐结论 -->
    @XmlTag(name = "SaltConclusion")
    public String saltConclusion = "";
    // <!-- 生活方式指导 - 目标摄盐量 -->
    @XmlTag(name = "SaltTarget")
    public String saltTarget = "";
    // <!-- 生活方式指导 - 心理调整, CD：代码 ；1.良好，2.一般，3.差-->
    @XmlTag(name = "Psyche")
    public BeanCD psyche = null;
    // <!-- 生活方式指导 - 遵医行为；1.良好，2.一般，3.差-->
    @XmlTag(name = "Compliance")
    public BeanCD compliance = null;
    // <!-- 心理状态，CD:包括（正常、紧张、抑郁、焦虑、其他） -->
    @XmlTag(name = "Mentation")
    public BeanCD mentation = null;
    // <!-- 此次随访评估；1.满意，2.一般，3.不满意-->
    @XmlTag(name = "Evaluation")
    public BeanCD evaluation = null;
    // <!-- 康复建议 -->
    @XmlTag(name = "Suggest")
    public String suggest = "";
    // <!-- 随访日期 -->
    @XmlTag(name = "FlupDate")
    public String flupDate = "";
    // <!-- 下次随访日期 -->
    @XmlTag(name = "NextFlupDate")
    public String nextFlupDate = "";
    // <!-- 责任医生ID -->
    @XmlTag(name = "DutyDoctorID")
    public String dutyDoctorID = "";
    // <!-- 责任医生姓名 -->
    @XmlTag(name = "DutyDoctorName")
    public String dutyDoctorName = "";
    // <!-- 转诊标志 -->
    @XmlTag(name = "TransferFlag")
    public String transferFlag = "";
    // <!-- 转诊原因 -->
    @XmlTag(name = "TransferReason")
    public String transferReason = "";
    // <!-- 转入医疗机构名称 -->
    @XmlTag(name = "TransferInstitution")
    public String transferInstitution = "";
    // <!-- 转入机构科室名称 -->
    @XmlTag(name = "TransferDepartment")
    public String transferDepartment = "";
    // <!-- 数据来源, 有多条记录，因此会有多个DeviceUse节点-->
    @XmlTag(name = "DeviceUse", isListWithoutGroupTag = true)
    public List<DeviceUse> deviceUses = null;
}
