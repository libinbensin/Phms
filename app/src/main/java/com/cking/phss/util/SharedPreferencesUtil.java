/* Xinhuaxing Inc. (C) 2014. All rights reserved.
 *
 * SharedPreferencesUtil.java
 * classes : com.cking.phss.util.SharedPreferencesUtil
 * @author Administrator
 * V 1.0.0
 * Create at 2014-12-8 下午2:38:15
 */
package com.cking.phss.util;

import java.io.File;
import java.lang.reflect.Field;

import android.content.Context;
import android.content.ContextWrapper;

/**
 * com.cking.phss.util.SharedPreferencesUtil
 * @author Administrator <br/>
 * create at 2014-12-8 下午2:38:15
 */
public class SharedPreferencesUtil {
    private static final String TAG = "SharedPreferencesUtil";
    public static void changeStorageDir(Context context, String dir) {  
        try {  
            Field field;  
            // 获取ContextWrapper对象中的mBase变量。该变量保存了ContextImpl对象  
            field = ContextWrapper.class.getDeclaredField("mBase");  
            field.setAccessible(true);  
            // 获取mBase变量  
            Object obj = field.get(context);  
            // 获取ContextImpl。mPreferencesDir变量，该变量保存了数据文件的保存路径  
            field = obj.getClass().getDeclaredField("mPreferencesDir");  
            field.setAccessible(true);  
            // 创建自定义路径  
            File file = new File(dir);  
            // 修改mPreferencesDir变量的值  
            field.set(obj, file);  
        } catch (NoSuchFieldException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        } catch (IllegalArgumentException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        } catch (IllegalAccessException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
    } 
}
