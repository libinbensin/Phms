package com.cking.phss.dto;

import java.util.List;

import com.cking.phss.bean.BeanCD;
import com.cking.phss.bean.HistoryDisease;
import com.cking.phss.bean.HistoryHyper;
import com.cking.phss.dto.innner.DeviceUse;
import com.cking.phss.global.Global;
import com.cking.phss.util.DeviceUseFactory;
import com.cking.phss.xml.util.XmlAttribute;
import com.cking.phss.xml.util.XmlTag;

/**
 * 8.2 保存居民健康信息.xml
 * 
 * @author Administrator
 */
public class Bcjmjkxx8_2 implements IDto {
	/**
	 * Request
	 */
	@XmlTag(name = "Request")
	public Request request = null;

	static public class Request implements IDto {
		@XmlAttribute(name = "OrgCode")
		public String orgCode = Global.orgCode;

		@XmlAttribute(name = "OperType")
		public String operType = "8.2";

		// 操作用户ID
		@XmlTag(name = "UserID")
		public String userID = "";

		// 家庭档案号
		@XmlTag(name = "FamilyID")
		public String familyID ="";

		// 个人档案号
		@XmlTag(name = "ResidentID")
		public String residentID = "";

		//家族史：家族史：祖父母（多选），多个代码之间用英文逗号分隔，值为代码：1、无；2、高血压；3、糖尿病；4、冠心病；5、慢性阻塞性肺疾病；6、恶性肿瘤；7、脑卒中；8、重性精神疾病；9、结核病；10、肝炎；11、先天畸形；12、其他
		@XmlTag(name = "Grandparents")
        public BeanCD grandparents = null;

		// 家族史：父亲代码（多选），多个代码之间用英文|分隔，值为代码：1、无；2、高血压；3、糖尿病；4、冠心病；5、慢性阻塞性肺疾病；6、恶性肿瘤；7、脑卒中；8、重性精神疾病；9、结核病；10、肝炎；11、先天畸形；12、其他
		@XmlTag(name = "FatherCD")
		public String fatherCD = "";

        //家族史：父亲, 文字值，多个值之间用英文逗号分隔,选其他时可为其他的具体内容
        @XmlTag(name = "FatherName")
        public String fatherName = "";

		// 家族史：母亲代码（多选），多个代码之间用英文|分隔，值为代码：1、无；2、高血压；3、糖尿病；4、冠心病；5、慢性阻塞性肺疾病；6、恶性肿瘤；7、脑卒中；8、重性精神疾病；9、结核病；10、肝炎；11、先天畸形；12、其他
		@XmlTag(name = "MotherCD")
		public String motherCD = "";

        //家族史：母亲,文字值，多个值之间用英文逗号分隔,选其他时可为其他的具体内容
        @XmlTag(name = "MotherName")
        public String motherName = "";

		// 家族史：兄弟代码（多选），多个代码之间用英文|分隔，值为代码：1、无；2、高血压；3、糖尿病；4、冠心病；5、慢性阻塞性肺疾病；6、恶性肿瘤；7、脑卒中；8、重性精神疾病；9、结核病；10、肝炎；11、先天畸形；12、其他
		@XmlTag(name = "BrotherCD")
		public String brotherCD = "";

        //家族史：兄弟,文字值，多个值之间用英文逗号分隔,选其他时可为其他的具体内容
        @XmlTag(name = "BrotherName")
        public String brotherName = "";

		// 家族史：子女代码（多选），多个代码之间用英文|分隔，值为代码：1、无；2、高血压；3、糖尿病；4、冠心病；5、慢性阻塞性肺疾病；6、恶性肿瘤；7、脑卒中；8、重性精神疾病；9、结核病；10、肝炎；11、先天畸形；12、其他
		@XmlTag(name = "ChildCD")
		public String childCD = "";

        //家族史：子女, 文字值，多个值之间用英文逗号分隔,选其他时可为其他的具体内容
        @XmlTag(name = "ChildName")
        public String childName = "";
		
		// 家族史：其他成员代码（多选），多个代码之间用英文|分隔，值为代码：1、无；2、高血压；3、糖尿病；4、冠心病；5、慢性阻塞性肺疾病；6、恶性肿瘤；7、脑卒中；8、重性精神疾病；9、结核病；10、肝炎；11、先天畸形；12、其他
		@XmlTag(name = "OtherMemberCD")
		public String otherMemberCD = "";

        //家族史：其他,文字值，多个值之间用英文逗号分隔,选其他时可为其他的具体内容
        @XmlTag(name = "OtherMemberName")
        public String otherMemberName = "";
		
		// 残疾情况代码（多选），多个代码之间用英文|分隔，值为代码：1无残疾；2 视力残疾；3听力残疾；4言语残疾；5
		// 肢体残疾；6智力残疾；7精神残疾；8其他残疾
		@XmlTag(name = "DeformityCD")
		public String deformityCD = "";

        //残疾情况,文字值，多个值之间用英文逗号分隔,选其他时可为其他的具体内容
        @XmlTag(name = "DeformityName")
        public String deformityName = "";

        //残疾证号, 至少20位
        @XmlTag(name = "DeformityCardNo")
        public String deformityCardNo = "";

        //伤残等级，CD包括（一级、二级、三级、四级、未评定） 
		@XmlTag(name = "DeformityLevel")
		public BeanCD deformityLevel = null;

		// 遗传史代码（单选），值为代码：1:无；2：有
		@XmlTag(name = "HeredityCD")
		public int heredityCD = 0;

        //遗传史, 文字值
        @XmlTag(name = "HeredityCDName")
        public String heredityCDName = "";
		// 假如有遗传史则是具体疾病名称
		@XmlTag(name = "HeredityName")
		public String heredityName = "";
		
		// 暴露史代码（多选），多个代码之间用英文逗号分隔，值为代码：1无 2化学品 3毒物 4射线
		@XmlTag(name = "ExposureCD")
		public String exposureCD = "";

        //暴露史， 文字值
        @XmlTag(name = "ExposureName")
        public String exposureName = "";
	
		/**
		 * 居民既往疾病史。可以有多个HistoryDisease节点
		 */
		@XmlTag(name = "HistoryDisease", isListWithoutGroupTag = true)
        public List<HistoryDisease> historyDisease = null;

		/**
		 * 居民过敏史。可以有多个HistoryHyper节点
		 */
        @XmlTag(name = "HistoryHyper", isListWithoutGroupTag = true)
        public List<HistoryHyper> historyHyper = null;

		//数据来源, 有多条记录，因此会有多个DeviceUse节点
		@XmlTag(name = "DeviceUse",isListWithoutGroupTag=true)
		public List<DeviceUse> deviceUses = DeviceUseFactory.getDtoDeviceUses(getClass());
	}

	/**
	 * Response
	 */
	@XmlTag(name = "Response")
	public Response response =null;

	static public class Response implements IDto {
		@XmlAttribute(name = "ErrMsg")
		public String errMsg = "";

		@XmlTag(name = "FamilyID")
		public String familyID = "";

		@XmlTag(name = "ResidentID")
		public String residentID = "";
	}
	public void init(){
//		/**
//		 * Request
//		 */
//		this.request = new Request();
//		this.request.orgCode = "8.2";
//		this.request.operType = "8.2";
//		this.request.userID = "10082";
//		this.request.familyID = "10082";
//		this.request.residentID = "11180"; 
//		this.request.fatherCD ="1";
//		this.request.motherCD = "1";
//		this.request.brotherCD = "1";
//		this.request.hildCD = "1"; 
//		this.request.otherMemberCD = "1";
//		this.request.heredityCD = 1;
//		this.request.heredityName="无";
//		this.request.exposureCD = "1";
//		this.request.historyDisease.historyDiseaseID = 1082;
//		this.request.historyDisease.hDType=1;
//		this.request.historyDisease.iCD10.iCD10ID = 1008;
//		this.request.historyDisease.disease = "白癜风";
//		this.request.historyDisease.diagnoseDate = "1008-11-23";
//		this.request.historyDisease.HappenDate = "2009-4-13";
//		this.request.historyDisease.resultCD = 1;
//		this.request.historyDisease.cureDes = "正常";
//		this.request.historyDisease.cureHos = "天方医院";
//		this.request.historyHyper.historyHyperID = 10082;
//		this.request.historyHyper.hyperTypeCD = 1;
//		this.request.historyHyper.hyperSource.hyperSourceID = 2082;
//		this.request.historyHyper.happenDate = "2005-14-02";
//		this.request.historyHyper.hyperReason = "无知";
//		this.request.historyHyper.cureDes = "突然就晕倒了";
//		/**
//		 * Response
//		 */
//		this.response = new Response();
//		this.response.errMsg = "8.2";
//		this.response.familyID = 82;
//		this.response.residentID = 82;
	}
}
