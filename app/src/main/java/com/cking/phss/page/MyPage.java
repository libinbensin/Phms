/* Xinhuaxing Inc. (C) 2014. All rights reserved.
 *
 * BasePage.java
 * classes : com.cking.phss.page.BasePage
 * @author Wation.Haliyoo
 * V 1.0.0
 * Create at 2014-8-19 下午5:58:40
 */
package com.cking.phss.page;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * com.cking.phss.page.BasePage
 * @author Wation.Haliyoo <br/>
 * create at 2014-8-19 下午5:58:40
 */
public abstract class MyPage extends LinearLayout implements IPage {
    private static final String TAG = "BasePage";
    protected boolean hasInit = false;
    private Context mContext = null;

    /**
     * @param context
     */
    public MyPage(Context context) {
        super(context);
        mContext = context;
    }

    public MyPage(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    @Override
    protected void onAttachedToWindow() {
        if (!hasInit) {
            init(mContext);
            hasInit = true;
        }
        setValue();
        super.onAttachedToWindow();
    }

    /* (non-Javadoc)
     * @see android.view.View#onDetachedFromWindow()
     */
    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        getValue();
    }

    /* (non-Javadoc)
     * @see com.cking.phss.page.IPage#setValue()
     */
    @Override
    public void setValue() {
    }

    /* (non-Javadoc)
     * @see com.cking.phss.page.IPage#getValue()
     */
    @Override
    public boolean getValue() {
        return false;
    }

    /* (non-Javadoc)
     * @see com.cking.phss.page.IPage#clear()
     */
    @Override
    public void clear() {
    }

    /**
     * @param context
     */
    protected abstract void init(Context context);
}
