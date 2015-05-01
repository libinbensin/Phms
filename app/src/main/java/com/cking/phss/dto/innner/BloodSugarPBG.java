/* Xinhuaxing Inc. (C) 2014. All rights reserved.
 *
 * BloodSugarPBG.java
 * classes : com.cking.phss.dto.innner.BloodSugarPBG
 * @author Administrator
 * V 1.0.0
 * Create at 2014-7-24 上午11:42:41
 */
package com.cking.phss.dto.innner;

import java.util.List;

import com.cking.phss.dto.IDto;
import com.cking.phss.util.DeviceUseFactory;
import com.cking.phss.xml.util.XmlTag;

/**
 * com.cking.phss.dto.innner.BloodSugarPBG
 * @author Administrator <br/>
 * create at 2014-7-24 上午11:42:41
 */

public class BloodSugarPBG implements IDto {
    // 必填。 测血糖日期：yyyy-mm-dd-
    @XmlTag(name = "PBGDate")
    public String PBGDate = "";

    // 必填，血糖值(餐后2小时血糖)，浮点
    @XmlTag(name = "PBG")
    public String PBG = "";

    // 数据来源, 有多条记录，因此会有多个DeviceUse节点
    @XmlTag(name = "DeviceUse", isListWithoutGroupTag = true)
    public List<DeviceUse> deviceUses = DeviceUseFactory.getDtoDeviceUses(getClass());
}
