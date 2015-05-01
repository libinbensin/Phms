package com.cking.phss.dto.innner;

import com.cking.phss.bean.BeanCD;
import com.cking.phss.bean.BeanID;
import com.cking.phss.dto.IDto;
import com.cking.phss.xml.util.XmlTag;

/**
 * 用药情况记录 某些xml接口的内嵌节点
 * taowencong
 */
public class MedicineUse implements IDto {

	//<!-- 序号 -->
	@XmlTag(name = "MedicineSn")
	public String medicineSn = "";

	//<!-- 疾病类型 1.高血压，2.糖尿病，3.高血脂，99.其他所有合并用药 -->
	@XmlTag(name = "DiseaseType")
	public BeanID diseaseType;

	//<!-- 药物种类 CD：ID或代码； 1.未使用、2.中成药、3.中草药、 9.其他中药、10、西药 -->
	@XmlTag(name = "MedicineType")
    public BeanCD medicineType;

	
	@XmlTag(name = "Medicine")
    public BeanID medicine ;

	// 用量。浮点
	@XmlTag(name = "Dosage")
	public String dosage = "";

	// 值：单位名称，ID：代码
	@XmlTag(name = "MedicineUnit")
    public BeanID medicineUnit;


	// 值：用法，ID：代码
	@XmlTag(name = "Usage")
    public BeanID usage;

	//<!--药物使用总剂量-->
	@XmlTag(name = "IntegralDose")
	public String integralDose = "";

	// 值：给药方式。ID：代码
	@XmlTag(name = "Way")
    public BeanID way;

	// <!-- 疾病类型 1.高血压，2.糖尿病，3.高血脂，4.精神病，99.其他所有合并用药 -->
	public BeanCD DiseaseType;
}