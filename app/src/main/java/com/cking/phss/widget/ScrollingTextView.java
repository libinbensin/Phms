/* Cking Inc. (C) 2012. All rights reserved.
 *
 * ScrollingTextView.java
 * classes : com.okis.happyguide.expandableview.ScrollingTextView
 * @author zhaowanlin
 * V 1.0.0
 * Create at 2012-11-19 下午05:30:18
 */
package com.cking.phss.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * com.okis.happyguide.expandableview.ScrollingTextView
 * @author zhaowanlin <br/>
 * create at 2012-11-19 下午05:30:18
 */
public class ScrollingTextView extends TextView{

    private static final String TAG = "ScrollingTextView";

    public ScrollingTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    /**
     * @param context
     */
    public ScrollingTextView(Context context) {
        super(context);
    }
    /**
     * @param context
     * @param attrs
     */
    public ScrollingTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
       if(focused)
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
    }
    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        if(hasWindowFocus)
        super.onWindowFocusChanged(hasWindowFocus);
    }
    @Override
    public boolean isFocused() {
        return true;
    }
    
   }
