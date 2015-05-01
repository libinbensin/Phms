package com.cking.phss.dto;

import java.util.List;

import com.cking.phss.bean.BeanCD;
import com.cking.phss.bean.BeanID;
import com.cking.phss.bean.HistoryDisease;
import com.cking.phss.bean.HistoryHyper;
import com.cking.phss.dto.innner.DeviceUse;
import com.cking.phss.global.Global;
import com.cking.phss.util.DeviceUseFactory;
import com.cking.phss.xml.util.XmlAttribute;
import com.cking.phss.xml.util.XmlTag;

/**
 * 7.2 得到居民健康信息.xml
 * 
 * @author Administrator
 */
public class Ddjmjkxx7_2 implements IDto {
    @XmlTag(name = "Request")
    public Request request = null;

    static public class Request implements IDto {
        @XmlAttribute(name = "OrgCode")
        public String OrgCode = Global.orgCode;
        @XmlAttribute(name = "OperType")
        public String operType = "7.2";
        // 必填。值：当前登录用户工号，ID：相应代码或ID
        @XmlTag(name = "EmployeeNo")
        public BeanID employeeNo = null;

        // 必填。个人档案号
        @XmlTag(name = "ResidentID")
        public String residentID = "";

        // 必填，家庭档案号
        @XmlTag(name = "FamilyID")
        public String familyID = "";
    }

    @XmlTag(name = "Response")
    public Response response = null;

    static public class Response implements IDto {
        @XmlAttribute(name = "ErrMsg")
        public String errMsg = "";

        // 必填。此条记录是否只读。1：是；0：否
        @XmlTag(name = "ReadOnly")
        public int ReadOnly = 0;

        // 必填。用户ID
        @XmlTag(name = "UserID")
        public String userID = "";

        // 必填。用户名称
        @XmlTag(name = "User")
        public String userName = "";

        // 必填。家庭档案号
        @XmlTag(name = "FamilyID")
        public String familyID = "";

        // 必填。个人档案号
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

        // 家族史：其他成员（多选），多个代码之间用英文|分隔，值为代码：1、无；2、高血压；3、糖尿病；4、冠心病；5、慢性阻塞性肺疾病；6、恶性肿瘤；7、脑卒中；8、重性精神疾病；9、结核病；10、肝炎；11、先天畸形；12、其他
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

        // 居民既往疾病史。可以有多个HistoryDisease节点
        @XmlTag(name = "HistoryDisease", isListWithoutGroupTag = true)
        public List<HistoryDisease> historyDisease = null;

        // 居民过敏史。可以有多个HistoryHyper节点
        @XmlTag(name = "HistoryHyper", isListWithoutGroupTag = true)
        public List<HistoryHyper> historyHyper = null;

		//数据来源, 有多条记录，因此会有多个DeviceUse节点
		@XmlTag(name = "DeviceUse",isListWithoutGroupTag=true)
		public List<DeviceUse> deviceUses = DeviceUseFactory.getDtoDeviceUses(getClass());
    }
}
