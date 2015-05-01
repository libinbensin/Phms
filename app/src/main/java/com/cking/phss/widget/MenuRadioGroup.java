/* Xinhuaxing Inc. (C) 2014. All rights reserved.
 *
 * MenuRadioGroup.java
 * classes : com.cking.phss.widget.MenuRadioGroup
 * @author Administrator
 * V 1.0.0
 * Create at 2014-6-11 下午1:23:56
 */
package com.cking.phss.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.InflateException;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

/**
 * com.cking.phss.widget.MenuRadioGroup
 * @author Administrator <br/>
 * create at 2014-6-11 下午1:23:56
 */
public class MenuRadioGroup extends LinearLayout {
    private static final String TAG = "MenuRadioGroup";
    
    public interface OnCheckedChangeListener {
        public void onCheckedChanged(MenuRadioGroup group, int checkedId, int index);
    }
    
    private OnCheckedChangeListener mOnCheckedChangeListener = null;
    
    public void setOnCheckedChangeListener(OnCheckedChangeListener listener) {
        mOnCheckedChangeListener = listener;
    }
    
    float x, y;
    int lastCheckedIndex = -1;
    
    public int getCheckedIndex() {
        return lastCheckedIndex;
    }
    /**
     * @param context
     * @param attrs
     */
    public MenuRadioGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        
        setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View arg0) {
                int count = getChildCount();
                for (int i = 0; i< count; i++) {
                    View v = getChildAt(i);
                    // 获取子视图在屏幕上的区域
                    final int[] location = new int[2]; 
                    v.getLocationOnScreen(location);  
                    Rect r = new Rect(location[0], location[1], location[0] + v.getWidth(),
                            location[1] + v.getHeight());
                    // 判断点击是否在区域内
                    if (r.contains((int)x, (int)y)) {
                        setCheckedByIndex(i);
                    }
                }
            }
        });
        
        setOnTouchListener(new OnTouchListener() {
            
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                x = arg1.getRawX();
                y = arg1.getRawY();
                return false;
            }
        });
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        int count = getChildCount();
        for (int i = 0; i< count; i++) {
            View v = getChildAt(i);
            if (!(v instanceof MenuRadioButton)) {
                throw new InflateException();
            }
            if (((MenuRadioButton) v).isChecked()) {
                lastCheckedIndex = i;
            }
        }
    }
    /**
     * @param index
     */
    public void setCheckedByIndex(int index) {
        if (lastCheckedIndex != index) {
            View v = getChildAt(index);
            ((MenuRadioButton) v).setChecked(true);
            if (lastCheckedIndex >= 0) {
                View lastv = getChildAt(lastCheckedIndex);
                ((MenuRadioButton) lastv).setChecked(false);
            }
            lastCheckedIndex = index;
            if (mOnCheckedChangeListener != null) {
                mOnCheckedChangeListener.onCheckedChanged(MenuRadioGroup.this, v.getId(), index);
            }
        }
    }
}
