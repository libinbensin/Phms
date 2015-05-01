/* Xinhuaxing Inc. (C) 2014. All rights reserved.
 *
 * MedicineRecord.java
 * classes : com.cking.phss.dto.innner.MedicineRecord
 * @author Administrator
 * V 1.0.0
 * Create at 2014-7-24 上午11:27:59
 */
package com.cking.phss.dto.innner;

import java.util.List;

import com.cking.phss.bean.BeanCD;
import com.cking.phss.dto.IDto;
import com.cking.phss.xml.util.XmlTag;

/**
 * com.cking.phss.dto.innner.MedicineRecord
 * @author Administrator <br/>
 * create at 2014-7-24 上午11:27:59
 */

public class MedicineRecord implements IDto {
    // <!-- 用药记录，有多条记录，因此会有多个Record节点 -->
    @XmlTag(name = "Record", isListWithoutGroupTag = true)
    public List<Record> records = null;

    static public class Record implements IDto {
        // <!-- 序号 -->
        @XmlTag(name = "RecordSn")
        public String recordSn = "";

        // <!--必填。 就诊时间 -->
        @XmlTag(name = "SeeDoctorDate")
        public String seeDoctorDate = "";

        // <!-- 药物种类 CD：ID或代码； 1.未使用、2.中成药、3.中草药、 9.其他中药、10、西药 -->
        @XmlTag(name = "MedicineType")
        public BeanCD medicineType = null;

        // <!--必填。值：药物名称，CD：代码 -->
        @XmlTag(name = "Medicine")
        public BeanCD medicine = null;

        // <!--必填。剂型 -->
        @XmlTag(name = "DosageForms")
        public BeanCD dosageForms = null;

        // <!--必填。规格 -->
        @XmlTag(name = "Specification")
        public String specification = "";

        // <!--必填。产地 -->
        @XmlTag(name = "ProducingArea")
        public String producingArea = "";

        // <!--必填。 每次用量 -->
        @XmlTag(name = "Dosage")
        public String dosage = "";

        // <!--必填。 配药数量 -->
        @XmlTag(name = "Quantity")
        public String quantity = "";

    }
}