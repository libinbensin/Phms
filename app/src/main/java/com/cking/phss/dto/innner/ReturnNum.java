/* Xinhuaxing Inc. (C) 2014. All rights reserved.
 *
 * ReturnNum.java
 * classes : com.cking.phss.dto.innner.ReturnNum
 * @author Administrator
 * V 1.0.0
 * Create at 2014-7-24 上午11:21:29
 */
package com.cking.phss.dto.innner;

import com.cking.phss.dto.IDto;
import com.cking.phss.xml.util.XmlAttribute;

/**
 * com.cking.phss.dto.innner.ReturnNum
 * @author Administrator <br/>
 * create at 2014-7-24 上午11:21:29
 */

public class ReturnNum implements IDto {
    // <!--返回符合条件的有多少家庭 -->
    @XmlAttribute(name = "FamilyNum")
    public int familyNum = 0;

    // <!--返回符合条件的有多少居民 -->
    @XmlAttribute(name = "ResidentNum")
    public int residentNum = 0;
}
