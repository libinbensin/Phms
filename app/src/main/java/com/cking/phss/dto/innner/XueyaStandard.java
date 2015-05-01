/* Xinhuaxing Inc. (C) 2014. All rights reserved.
 *
 * Standard.java
 * classes : com.cking.phss.dto.innner.Standard
 * @author Administrator
 * V 1.0.0
 * Create at 2014-7-24 上午11:31:26
 */
package com.cking.phss.dto.innner;

import com.cking.phss.dto.IDto;
import com.cking.phss.xml.util.XmlTag;

/**
 * com.cking.phss.dto.innner.Standard
 * @author Administrator <br/>
 * create at 2014-7-24 上午11:31:26
 */

public class XueyaStandard implements IDto {
    // 收缩压标准值，如果不填说明无
    @XmlTag(name = "stSBP")
    public String stSBP = "";

    // 舒张压标准值， 如果不填说明无
    @XmlTag(name = "stDBP")
    public String stDBP = "";

    // 收缩压预警值，如果不填说明无
    @XmlTag(name = "wnSBP")
    public String wnSBP = "";

    // 舒张压预警值，如果不填说明无
    @XmlTag(name = "wnDBP")
    public String wnDBP = "";
}
