/* Xinhuaxing Inc. (C) 2014. All rights reserved.
 *
 * Family.java
 * classes : com.cking.phss.dto.innner.Family
 * @author Administrator
 * V 1.0.0
 * Create at 2014-7-24 上午11:22:49
 */
package com.cking.phss.dto.innner;

import java.util.List;

import com.cking.phss.dto.IDto;
import com.cking.phss.util.DeviceUseFactory;
import com.cking.phss.xml.util.XmlAttribute;
import com.cking.phss.xml.util.XmlTag;

/**
 * com.cking.phss.dto.innner.Family
 * @author Administrator <br/>
 * create at 2014-7-24 上午11:22:49
 */

public class Family implements IDto {
    // <!-- 必填。用户ID，输入人员-->
    @XmlAttribute(name = "UserID")
    public String userID = "";

    // <!-- 必填。用户名称-->
    @XmlAttribute(name = "User")
    public String user = "";

    // <!--必填。家庭档案号 -->
    @XmlAttribute(name = "FamilyID")
    public String familyID = "";

    // <!--值：街道名称 -->
    @XmlAttribute(name = "Street")
    public String street = "";

    // <!--户主姓名 -->
    @XmlAttribute(name = "Householder")
    public String householder = "";

    // <!-- 家庭地址-->
    @XmlAttribute(name = "Address")
    public String address = "";

    // <!--必填，团队-->
    @XmlAttribute(name = "Team")
    public String team = "";

    // <!--必填，家庭电话-->
    @XmlAttribute(name = "HomePhone")
    public String homePhone = "";

    // 数据来源, 有多条记录，因此会有多个DeviceUse节点
    @XmlTag(name = "DeviceUse", isListWithoutGroupTag = true)
    public List<DeviceUse> deviceUses = DeviceUseFactory.getDtoDeviceUses(getClass());
}
