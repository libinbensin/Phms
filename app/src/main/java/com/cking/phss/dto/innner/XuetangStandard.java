/* Xinhuaxing Inc. (C) 2014. All rights reserved.
 *
 * XuetangStandard.java
 * classes : com.cking.phss.dto.innner.XuetangStandard
 * @author Administrator
 * V 1.0.0
 * Create at 2014-7-24 上午11:42:19
 */
package com.cking.phss.dto.innner;

import com.cking.phss.dto.IDto;
import com.cking.phss.xml.util.XmlTag;

/**
 * com.cking.phss.dto.innner.XuetangStandard
 * @author Administrator <br/>
 * create at 2014-7-24 上午11:42:19
 */

public class XuetangStandard implements IDto {
    // 空腹血糖标准值，如果不填说明无
    @XmlTag(name = "stFBG")
    public String stFBG = "";

    // 餐后2小时血糖标准值，如果不填说明无
    @XmlTag(name = "stPBG")
    public String stPBG = "";
}