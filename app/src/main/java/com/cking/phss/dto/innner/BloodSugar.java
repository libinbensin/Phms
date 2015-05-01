/* Xinhuaxing Inc. (C) 2014. All rights reserved.
 *
 * BloodSugar.java
 * classes : com.cking.phss.dto.innner.BloodSugar
 * @author Administrator
 * V 1.0.0
 * Create at 2014-7-24 上午11:44:05
 */
package com.cking.phss.dto.innner;

import com.cking.phss.dto.IDto;
import com.cking.phss.xml.util.XmlTag;

/**
 * com.cking.phss.dto.innner.BloodSugar
 * @author Administrator <br/>
 * create at 2014-7-24 上午11:44:05
 */

public class BloodSugar implements IDto {
    // 必填。 测血糖日期：yyyy-mm-dd
    @XmlTag(name = "BSDate")
    public String BSDate = "";

    // 必填，血糖值(空腹血糖)，浮点
    @XmlTag(name = "GLU")
    public String GLU = "";
}