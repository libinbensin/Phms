/* Xinhuaxing Inc. (C) 2014. All rights reserved.
 *
 * BasePage.java
 * classes : com.cking.phss.page.BasePage
 * @author Wation.Haliyoo
 * V 1.0.0
 * Create at 2014-8-19 下午5:58:40
 */
package com.cking.phss.page;

import net.xinhuaxing.pages.BasePage;
import android.content.Context;
import android.util.AttributeSet;

/**
 * com.cking.phss.page.BasePage
 * @author Wation.Haliyoo <br/>
 * create at 2014-8-19 下午5:58:40
 */
public abstract class MyPage2 extends BasePage {
    private static final String TAG = "MyPage2";
    protected boolean hasInit = false;
    private Context mContext = null;

    /**
     * @param context
     */
    public MyPage2(Context context) {
        super(context);
        mContext = context;
    }

    public MyPage2(Context context, AttributeSet attrs) {
        super(context);
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

    /**
     * @param context
     */
    protected abstract void init(Context context);
}
