package com.cking.phss.util;

import android.content.Context;
import android.os.Environment;

public class ContextUtil {
    private static final String TAG = "ContextUtil";
    private Context mContext = MyApplication.getInstance().getApplicationContext();

//    /**
//     * 单例的实现
//     */
//    private ContextUtil() {
//    }
//
//    private static class ContextUtilHolder {
//        private static final ContextUtil UNIQUE_INSTANCE = new ContextUtil();
//    }
//
//    public static ContextUtil getInstance() {
//        return ContextUtilHolder.UNIQUE_INSTANCE;
//    }

   
    
    
    
    /**
     *  SDCard是否可读
     * @return 是否可读
     */
    public static boolean sdCardCanRead() {
        // 设备状态
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)
                || Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {// 已经插入了sd卡，并且可以读
            return true;
        } else {
            return false;
        }
    }
    
    /**
     *  SDCard是否可写
     * @return 是否可写
     */
    public static boolean sdCardCanWrite() {
        // 设备状态
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {// 已经插入了sd卡，并且可以读与写
            return true;
        } else {
            return false;
        }
    }
}
