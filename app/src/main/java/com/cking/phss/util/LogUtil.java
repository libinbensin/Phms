/* Cking Inc. (C) 2012. All rights reserved.
 *
 * LogUtil.java
 * classes : com.cking.phss.util.LogUtil
 * @author Wation Haliyoo
 * V 1.0.0
 * Create at 2012-9-23 下午04:59:15
 */
package com.cking.phss.util;

import android.util.Log;

/**
 * com.cking.phss.util.LogUtil
 * @author Wation Haliyoo <br/>
 * create at 2012-9-23 下午04:59:15
 */
public class LogUtil {
    private static final String TAG = "LogUtil";
    
    public static void e(String tag, String title, Exception e) {
        Log.e(tag, e.toString());
    }
}
