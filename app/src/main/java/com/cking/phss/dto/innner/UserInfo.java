/* Xinhuaxing Inc. (C) 2014. All rights reserved.
 *
 * UserInfo.java
 * classes : com.cking.phss.dto.innner.UserInfo
 * @author Administrator
 * V 1.0.0
 * Create at 2014-7-24 上午11:11:55
 */
package com.cking.phss.dto.innner;

import com.cking.phss.dto.IDto;
import com.cking.phss.xml.util.XmlAttribute;

/**
 * com.cking.phss.dto.innner.UserInfo
 * @author Administrator <br/>
 * create at 2014-7-24 上午11:11:55
 */

public class UserInfo implements IDto {
    @XmlAttribute(name = "UserID")
    public int userInfoID = 0;

    @XmlAttribute(name = "UserName")
    public String userInfoName = "";
}
