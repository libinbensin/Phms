/* Cking Inc. (C) 2012. All rights reserved.
 *
 * DisplayUtil.java
 * classes : com.okis.happyguide.util.DisplayUtil
 * @author 刘军鹏
 * V 1.0.0
 * Create at 2012-9-3 上午11:21:28
 * Reference:
 * http://www.oschina.net/question/234345_40079
 */
package com.cking.phss.util;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * com.okis.happyguide.util.DisplayUtil
 * Android大小单位转换工具类
 * @author 刘军鹏 <br/>
 * create at 2012-9-3 上午11:21:28
 */
public class DisplayUtil {    

    /** 
     * 当前屏幕的density因子 
     *  
     * @param context 
     * @retrun DmDensity Getter 
     * */  
    public static float getDmDensityDpi(Context context) {  
        // 获取当前屏幕  
        DisplayMetrics dm = new DisplayMetrics();  
        dm = context.getApplicationContext().getResources().getDisplayMetrics();  
        return dm.densityDpi;  
    }  

    /** 
     * 当前屏幕缩放比
     *  
     * @param context 
     * @retrun DmDensity Getter 
     * */  
    public static float getScale(Context context) {  
        // 获取当前屏幕  
        DisplayMetrics dm = new DisplayMetrics();  
        dm = context.getApplicationContext().getResources().getDisplayMetrics();  
        return dm.densityDpi / 160;  
    }  
    /**
     * 将px值转换为dip或dp值，保证尺寸大小不变
     * 
     * @param pxValue
     * @param scale
     *            （DisplayMetrics类中属性density）
     * @return
     */
    public static int px2dip(float pxValue, float scale) {
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 将dip或dp值转换为px值，保证尺寸大小不变
     * 
     * @param dipValue
     * @param scale
     *            （DisplayMetrics类中属性density）
     * @return
     */
    public static int dip2px(float dipValue, float scale) {
        return (int) (dipValue * scale + 0.5f);
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     * 
     * @param pxValue
     * @param fontScale
     *            （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    public static int px2sp(float pxValue, float fontScale) {
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     * 
     * @param spValue
     * @param fontScale
     *            （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    public static int sp2px(float spValue, float fontScale) {
        return (int) (spValue * fontScale + 0.5f);
    }
}
