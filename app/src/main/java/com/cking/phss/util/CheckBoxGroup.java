/* Cking Inc. (C) 2012. All rights reserved.
 *
 * CheckBoxGroup.java
 * classes : com.cking.phss.util.CheckBoxGroup
 * @author Wation Haliyoo
 * V 1.0.0
 * Create at 2012-9-19 下午12:06:16
 */
package com.cking.phss.util;

import java.util.ArrayList;
import java.util.List;

import android.view.ViewGroup;
import android.widget.CheckBox;

/**
 * com.cking.phss.util.CheckBoxGroup
 * @author Wation Haliyoo <br/>
 * create at 2012-9-19 下午12:06:16
 */
public class CheckBoxGroup {
    private static final String TAG = "CheckBoxGroup";
    
    private List<CheckBox> mCheckBoxList = null;
    
    public CheckBoxGroup() {
        mCheckBoxList = new ArrayList<CheckBox>();
    }

    public CheckBoxGroup(int[] viewIds, ViewGroup viewGroup) {
        mCheckBoxList = new ArrayList<CheckBox>();
        
        for (int viewId : viewIds) {
            CheckBox checkBox = (CheckBox) viewGroup.findViewById(viewId);
            
            add(checkBox);
        }
    }
    
    
    public void add(CheckBox checkBox) {
        mCheckBoxList.add(checkBox);
    }
    
    public void clear() {
        mCheckBoxList.clear();
    }
    
    public void setCheckedAll(boolean checked) {
        for (CheckBox checkBox : mCheckBoxList) {
            checkBox.setChecked(checked);
        }
    }
    
    public void setEnabledAll(boolean enabled) {
        for (CheckBox checkBox : mCheckBoxList) {
            checkBox.setEnabled(enabled);
        }
    }

    public void setCheckedValue(int checkedValue) {
        int i = 0;

        for (CheckBox checkBox : mCheckBoxList) {
            
            // 如果选中，则累加
            if ((checkedValue & (long)Math.pow(2, i)) > 0) {
                checkBox.setChecked(true);
            }
            
            i++;
        }
    }
    
    public long getCheckedValue() {
        long value = 0;
        int i = 0;

        for (CheckBox checkBox : mCheckBoxList) {
            
            // 如果选中，则累加
            if (checkBox.isChecked()) {
                value |= (long)Math.pow(2, i);
            }
            
            i++;
        }
        
        return value;
    }
    
    
    //设置选中的按钮
    public void setChecked(int id,boolean checked){
    	mCheckBoxList.get(id).setChecked(checked);
    }
    
    //返回选择的按钮 代表的字符串
    public String getChecked(){
    	String checkString="";
    	for(int i=0;i<mCheckBoxList.size();i++){
    		if(mCheckBoxList.get(i).isChecked()){
    			checkString+=(i+2+", ");
    		}
    	}
    	if(!checkString.equals("")&&checkString.length()>2){
    		checkString=checkString.substring(0, checkString.length()-2);
    	}
    	return checkString;
    }
    
    public boolean isChecked(int id){
    	boolean checked=false;
    	if(id>=0&&id<mCheckBoxList.size())
    		checked=mCheckBoxList.get(id).isChecked();
    	return checked;
    }
    
    public boolean isEnable(int id){
    	boolean enable=false;
    	if(id>=0&&id<mCheckBoxList.size())
    		enable=mCheckBoxList.get(id).isEnabled();
    	return enable;
    }
    
    public CheckBox getCheckBox(int i){
    	return mCheckBoxList.get(i);
    }
    
    public int size() {
        return mCheckBoxList.size();
    }
}
