/* Cking Inc. (C) 2012. All rights reserved.
 *
 * MyHomeListView.java
 * classes : com.cking.basic.view.MyHomeListView
 * @author xiasiming
 * V 1.0.0
 * Create at 2012-12-25 下午6:05:24
 */
package com.cking.phss.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * com.cking.basic.view.MyHomeListView
 * 
 * @author xiasiming <br/>
 *         create at 2012-12-25 下午6:05:24
 */
public class MyHomeListView extends ListView {

    /**
     * @param context
     */
    public MyHomeListView(Context context) {
        super(context);
    }

    /**
     * @param context
     * @param attrs
     */
    public MyHomeListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * @param context
     * @param attrs
     * @param defStyle
     */
    public MyHomeListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    private static final String TAG = "MyHomeListView";
    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int expandSpec = MeasureSpec.makeMeasureSpec(
                Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
