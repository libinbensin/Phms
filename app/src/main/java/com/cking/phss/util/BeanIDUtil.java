/* Xinhuaxing Inc. (C) 2014. All rights reserved.
 *
 * BeanCDUtil.java
 * classes : com.cking.phss.util.BeanCDUtil
 * @author Administrator
 * V 1.0.0
 * Create at 2014-7-28 下午12:01:16
 */
package com.cking.phss.util;

import com.cking.phss.bean.BeanID;

/**
 * com.cking.phss.util.BeanCDUtil
 * @author Administrator <br/>
 * create at 2014-7-28 下午12:01:16
 */
public class BeanIDUtil {
    private static final String TAG = "BeanIDUtil";

    public static String getTagValue(BeanID beanID) {
        if (beanID == null) {
            return "无";
        } else {
            return beanID.getTagValue();
        }
    }
}
