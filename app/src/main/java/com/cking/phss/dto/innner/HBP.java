/* Xinhuaxing Inc. (C) 2014. All rights reserved.
 *
 * HBP.java
 * classes : com.cking.phss.dto.innner.HBP
 * @author Administrator
 * V 1.0.0
 * Create at 2014-7-24 上午11:31:58
 */
package com.cking.phss.dto.innner;

import java.util.List;

import com.cking.phss.dto.IDto;
import com.cking.phss.util.DeviceUseFactory;
import com.cking.phss.xml.util.XmlTag;

/**
 * com.cking.phss.dto.innner.HBP
 * @author Administrator <br/>
 * create at 2014-7-24 上午11:31:58
 */

public class HBP implements IDto {
    // 必填。 测压日期：yyyy-mm-dd
    @XmlTag(name = "HBPDate")
    public String HBPDate = "";

    // 必填，收缩压，整型
    @XmlTag(name = "SBP")
    public int SBP = 0;

    // 必填。舒张压，整型
    @XmlTag(name = "DBP")
    public int DBP = 0;

    // 数据来源, 有多条记录，因此会有多个DeviceUse节点
    @XmlTag(name = "DeviceUse", isListWithoutGroupTag = true)
    public List<DeviceUse> deviceUses = DeviceUseFactory.getDtoDeviceUses(getClass());
}
