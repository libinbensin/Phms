package com.cking.phss.dto.sjgl;

import java.util.List;

import com.cking.phss.bean.BeanCD;
import com.cking.phss.dto.IDto;
import com.cking.phss.dto.innner.Record;
import com.cking.phss.global.Global;
import com.cking.phss.xml.util.XmlAttribute;
import com.cking.phss.xml.util.XmlTag;

/**
 * 保存居民基本信息接口
 * 
 * @author Administrator
 */
public class GzglHdw01 implements IDto {
    @XmlTag(name = "Request")
    public Request request = null;

	@XmlTag(name = "Response")
    public Response response = null;

	public static class Request implements IDto {
		@XmlAttribute(name = "OrgCode")
		public String orgCode = Global.orgCode;

        @XmlAttribute(name = "OperType")
        public String operType = "HDW01";

        // <!--必填。值：当前登录用户工号，ID：相应代码或ID -->
        @XmlTag(name = "UserID")
        public int UserID = 1001;
        // <!--必填。登陆的医生姓名 -->
        @XmlTag(name = "DoctorName")
        public String DoctorName = "";
        // <!--必填。一级项目，多个项目用英文“|”隔开，CD：一级项目代码， 0.所有项目、1.档案信息、2.健康体检、3.随访管理 -->
        @XmlTag(name = "FirstProject")
        public BeanCD FirstProject = null;
        // <!--必填。二级项目，多个项目用英文“|”隔开，CD：二级项目代码，
        // 1.档案信息、2.快速体检、3.体质辨识、4.心理评估、5.老年评估、6.普通体检、7.高血压随访、8.糖尿病随访、9.脑卒中随访、10.精神病随访、11.孕产访视、12儿童访视、13.老年随访、14.残疾随访
        // -->
        @XmlTag(name = "SecondProject")
        public BeanCD SecondProject = null;
        // <!--必填。查询开始时间段，格式：yyyy-mm-dd -->
        @XmlTag(name = "SDate")
        public String SDate = "";
        // <!--必填。查询结束时间段，格式：yyyy-mm-dd -->
        @XmlTag(name = "EDate")
        public String EDate = "";
	}

	public static class Response implements IDto {
		@XmlAttribute(name = "ErrMsg")
        public String errMsg = "";

        // <!-- 返回的记录。返回多条记录，可能有多个此Record节点 -->
        @XmlTag(name = "Record", isListWithoutGroupTag = true)
        public List<Record> Records = null;
	}
}
