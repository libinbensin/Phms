/* Xinhuaxing Inc. (C) 2014. All rights reserved.
 *
 * FamilyMainProblems.java
 * classes : com.cking.phss.bean.FamilyMainProblems
 * @author Administrator
 * V 1.0.0
 * Create at 2014-6-27 下午3:31:36
 */
package com.cking.phss.bean;

import java.util.List;

import com.cking.phss.xml.util.XmlTag;

/**
 * com.cking.phss.bean.FamilyMainProblems
 * @author Administrator <br/>
 * create at 2014-6-27 下午3:31:36
 */
public class FamilyMainProblems implements IBean {

    //<!-- 主要问题，有多条数据，因此会有多个Problem节点 -->
    @XmlTag(name = "Problem",isListWithoutGroupTag=true)
    public List<Problem> problems = null;

    static public class Problem implements IBean {
        //<!-- 问题ID,新建时系统自动生成 -->
        @XmlTag(name = "ProblemID")
        public String problemID = "";

        //<!-- 阶段，CD包括（1.新婚、2.第一个孩子出生、3.有学龄前儿童、4.有青少年、5.孩子离家创业、6.父母独处、7.退休、8.二代同生、9.三代同生、99.其他） -->
        @XmlTag(name = "Stage")
        public BeanCD stage = null;

        //<!-- 发生日期 -->
        @XmlTag(name = "HappenDate")
        public String happenDate = "";

        //<!-- 记录日期 -->
        @XmlTag(name = "MarkDate")
        public String markDate = "";

        //<!-- 记录医生ID -->
        @XmlTag(name = "MarkDoctorID")
        public String markDoctorID = "";

        //<!-- 记录医生姓名 -->
        @XmlTag(name = "MarkDoctorName")
        public String markDoctorName = "";

        //<!-- 备注 -->
        @XmlTag(name = "ProblemRemark")
        public String problemRemark = "";

        //<!-- 主观资料 -->
        @XmlTag(name = "SubjectData")
        public String SubjectData = "";

        //<!-- 客观资料 -->
        @XmlTag(name = "ObjectiveData")
        public String objectiveData = "";

        //<!-- 主要问题 CD包括（1药物过敏；2遗传问题；3酗酒；4吸烟；5缺血性卒中；6离婚；7丧偶；8脑出血；9传染病；10蛛网膜下腔出血；11短暂性脑缺血发作；12慢性病〔高血压、糖尿病、肿瘤、心脑血管疾病、慢性呼吸系统疾病等〕；13其他脑血管疾病；14持续性健康指标异常；15残疾；16糖尿病肾病；17肾功能衰竭；18急性肾炎；19慢性肾炎；20其他肾脏疾病；21心肌梗死；22心绞痛；23冠状动脉血运重建；24充血性心力衰竭；25心前区疼痛；26其他心脏疾病；27夹层动脉瘤；28动脉闭塞性疾病；29其他血管疾病；30神经系统疾病；31其他系统疾病 -->
        @XmlTag(name = "MainProblem")
        public BeanCD mainProblem = null;

        //<!-- 问题评估 -->
        @XmlTag(name = "Appraise")
        public String appraise = "";

        //<!-- 处理及结果 -->
        @XmlTag(name = "HandleAndResult")
        public String handleAndResult = "";

        //<!-- 其他 -->
        @XmlTag(name = "Other")
        public String other = "";

        //<!-- 发生人 -->
        @XmlTag(name = "HappenedMember")
        public String happenedMember = "";

        //<!-- 管理计划 -->
        @XmlTag(name = "ManagementPlan")
        public String managementPlan = "";

        //<!-- 备注 -->
        @XmlTag(name = "Remark")
        public String remark = "";
    }
}      

