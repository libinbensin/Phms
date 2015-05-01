package com.cking.phss.bean;

import java.util.List;

import android.util.Log;

import com.cking.phss.xml.util.XmlTag;

/**
 * 居民健康信息
 * 
 * @author AUS
 * 
 */
public class Jmjkxx implements IBean {
	
	private static final String TAG = "Jmjkxx";
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

	@XmlTag(name = "DeformityCD")
	private String deformityCD = "1";// 残疾情况代码（多选），多个代码之间用英文|分隔，值为代码：1无残疾；2
									// 视力残疾；3听力残疾；4言语残疾；5 肢体残疾；6智力残疾；7精神残疾；8其他残疾
									// -->
	
    //残疾情况,文字值，多个值之间用英文逗号分隔,选其他时可为其他的具体内容
    @XmlTag(name = "DeformityName")
    public String deformityName = "";

    //残疾证号, 至少20位
    @XmlTag(name = "DeformityCardNo")
    public String deformityCardNo = "";

    //伤残等级，CD包括（一级、二级、三级、四级、未评定） 
	@XmlTag(name = "DeformityLevel")
	public BeanCD deformityLevel = null;

	@XmlTag(name = "HeredityCD")
	private int heredityCD = 1;// 遗传史代码（单选），值为代码：1:无；2：有 -->
	@XmlTag(name = "HeredityName")
	private String heredityName = "";// 假如有遗传史则是具体疾病名称

	@XmlTag(name = "ExposureCD")
	private String exposureCD = "1";// 暴露史代码（多选），多个代码之间用英文逗号分隔，值为代码：1无 2化学品 3毒物
									// 4射线
									// -->
    //暴露史， 文字值
    @XmlTag(name = "ExposureName")
    private String exposureName = "";

	@XmlTag(name = "HistoryDisease", isListWithoutGroupTag = true)
	private List<HistoryDisease> historyDisease;// 居民既往疾病史。可以有多个HistoryDisease节点

	@XmlTag(name = "HistoryHyper",isListWithoutGroupTag = true)
	private List<HistoryHyper> historyHyper;

	public String getFatherCD() {
		return fatherCD;
	}

	public void setFatherCD(String fatherCD) {
		this.fatherCD = fatherCD;
	}
	
	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getMotherCD() {
		return motherCD;
	}

	public void setMotherCD(String motherCD) {
		this.motherCD = motherCD;
	}
	
	public String getMotherName() {
		return motherName;
	}

	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}


	public String getBrotherCD() {
		return brotherCD;
	}

	public void setBrotherCD(String brotherCD) {
		this.brotherCD = brotherCD;
	}

	public String getBrotherName() {
		return brotherName;
	}

	public void setBrotherName(String brotherName) {
		this.brotherName = brotherName;
	}
	
	public String getChildCD() {
		return childCD;
	}

	public void setChildCD(String childCD) {
		this.childCD = childCD;
	}
	
	public String getChildName() {
		return childName;
	}

	public void setChildName(String childName) {
		this.childName = childName;
	}
	
	public String getOtherMemberCD() {
		return otherMemberCD;
	}

	public void setOtherMemberCD(String otherMemberCD) {
		this.otherMemberCD = otherMemberCD;
	}

	public String getOtherMemberName() {
		return otherMemberName;
	}

	public void setOtherMemberName(String otherMemberName) {
		this.otherMemberName = otherMemberName;
	}

	public String getDeformityCD() {
		return deformityCD;
	}

	public void setDeformityCD(String deformityCD) {
		this.deformityCD = deformityCD;
	}
	
	public String getDeformityName() {
		return deformityName;
	}

	public void setDeformityName(String deformityName) {
		this.deformityName = deformityName;
	}
	
	public String getDeformityCardNo() {
		return deformityCardNo;
	}

	public void setDeformityCardNo(String deformityCardNo) {
		this.deformityCardNo = deformityCardNo;
	}
	
	public BeanCD getDeformityLevel() {
		return deformityLevel;
	}

	/**
     * @return the exposureName
     */
    public String getExposureName() {
        return exposureName;
    }

    /**
     * @param exposureName the exposureName to set
     */
    public void setExposureName(String exposureName) {
        this.exposureName = exposureName;
    }

    public void setDeformityLevel(BeanCD deformityLevel) {
		this.deformityLevel = deformityLevel;
	}

	public int getHeredityCD() {
		return heredityCD;
	}

	public void setHeredityCD(int heredityCD) {
		this.heredityCD = heredityCD;
	}

	public String getHeredityName() {
		return heredityName;
	}

	public void setHeredityName(String heredityName) {
		this.heredityName = heredityName;
	}

	public String getExposureCD() {
		return exposureCD;
	}

	public void setExposureCD(String exposureCD) {
		this.exposureCD = exposureCD;
	}

	public List<HistoryDisease> getHistoryDisease() {
		return historyDisease;
	}

	public void setHistoryDisease(List<HistoryDisease> historyDisease) {
	    Log.i(TAG, "historyDisease.size: " + historyDisease.size());
		this.historyDisease = historyDisease;
	}

	public List<HistoryHyper> getHistoryHyper() {
		return historyHyper;
	}

	public void setHistoryHyper(List<HistoryHyper> historyHyper) {
		this.historyHyper = historyHyper;
	}

}
