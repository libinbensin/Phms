/* Xinhuaxing Inc. (C) 2014. All rights reserved.
 *
 * Medicine.java
 * classes : com.cking.phss.dto.innner.Medicine
 * @author Administrator
 * V 1.0.0
 * Create at 2014-7-24 上午11:12:32
 */
package com.cking.phss.dto.innner;

import com.cking.phss.dto.IDto;
import com.cking.phss.xml.util.XmlTag;

/**
 * com.cking.phss.dto.innner.Medicine
 * @author Administrator <br/>
 * create at 2014-7-24 上午11:12:32
 */

public class Medicine implements IDto {
    // 值：药物名称，ID：药物ID或代码
    @XmlTag(name = "MedicineID")
    public String medicineID = "";

    @XmlTag(name = "MedicineName")
    public String medicineName = "";

    @XmlTag(name = "Usage")
    public String usage = "";

    @XmlTag(name = "Dosage")
    public String dosage = "";

    // 模糊查询根据InputCode
    @XmlTag(name = "InputCode")
    public String inputCode = "";

    @XmlTag(name = "DW")
    public String DW = "";

    @XmlTag(name = "DrugDelivery")
    public String drugDelivery = "";
}
