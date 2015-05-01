package com.cking.phss.util;

import android.view.View;
import android.view.ViewGroup;

/**
 * 应用场景：当每个CheckBox对应了一个值的时候，并且根据值来设置选中状态，或者根据选中状态来获取以"," 或 "|"等方式分隔的字符串
 * 
 * checkBox组的操作类，简化 本工程需要传很多值的需要
 * 
 * @author taowencong
 * 
 */
public class ViewGroupUtil {
    private View[] mViews;//checkBox组
    private ViewGroup mParent;//
    private String[] valueSource;//每个checkBox对应的值，长度必须和mCheckBoxs一致

    /**
     * 
     * @param viewIds 所操作的所有checkBox
     * @param viewGroup 父视图
     * @param valueSource 每个checkBox对应的value，长度必须和viewIds一致
     */
    public ViewGroupUtil(ViewGroup parent, int[] viewIds) {
        mViews = new View[viewIds.length];
        mParent = parent;
        for (int i = 0; i < viewIds.length; i++) {
            mViews[i] = (View) mParent.findViewById(viewIds[i]);
        }
    }

    /**
     * 设置所有checkBox的状态  
     * @param checked 是否选中
     */
    public void setEnabledAll(boolean enabled) {
        for (View view : mViews) {
            view.setEnabled(enabled);
        }
    }
    
    public void setVisibilityAll(int visibility) {
        for (View view : mViews) {
            view.setVisibility(visibility);
        }
    }

    /*
     * public boolean isChecked(int id){ return checked; }
     */

    /*
     * public boolean isEnable(int id){
     * 
     * }
     */

    /**
     * 所有checkBox
     * @return 有多少checkBox
     */
    public int size() {
        return mViews.length;
    }
}
