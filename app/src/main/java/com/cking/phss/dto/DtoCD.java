/* Xinhuaxing Inc. (C) 2014. All rights reserved.
 *
 * DtoCD.java
 * classes : com.cking.phss.dto.DtoCD
 * @author Administrator
 * V 1.0.0
 * Create at 2014-6-27 下午2:34:09
 */
package com.cking.phss.dto;

import com.cking.phss.xml.util.XmlTag;

/**
 * com.cking.phss.dto.DtoCD
 * @author Administrator <br/>
 * create at 2014-6-27 下午2:34:09
 */
public class DtoCD implements IDto {
    @XmlTag(name = "CD")
    public String nowCountryCD = "";
    @XmlTag(name = "TagValue", hasSubTag = false)
    public String tagValue = "";
}
