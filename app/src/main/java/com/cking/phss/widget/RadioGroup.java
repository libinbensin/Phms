/* Xinhuaxing Inc. (C) 2014. All rights reserved.
 *
 * RadioGroup.java
 * classes : com.cking.phss.widget.RadioGroup
 * @author Administrator
 * V 1.0.0
 * Create at 2014-7-29 上午11:15:26
 */
package com.cking.phss.widget;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RadioButton;

import com.cking.phss.R;
import com.cking.phss.bean.BeanCD;
import com.cking.phss.util.RadioGroupUtil;

/**
 * com.cking.phss.widget.RadioGroup
 * @author Administrator <br/>
 * create at 2014-7-29 上午11:15:26
 */
public class RadioGroup extends android.widget.RadioGroup {
    private static final String TAG = "RadioGroup";
    private Context mContext;
    private String mName = null;
    RadioGroupUtil util = null;

    /**
     * @param context
     * @param attrs
     */
    public RadioGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MultiValue);
        String name = a.getString(R.styleable.MultiValue_name);
        a.recycle();
        init(name);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        List<Integer> viewIds = new ArrayList<Integer>();
        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            if (view instanceof RadioButton) {
                viewIds.add(view.getId());
            }
        }
        int[] ids = new int[viewIds.size()];
        for (int i = 0; i < viewIds.size(); i++) {
            ids[i] = viewIds.get(i);
        }

        util = new RadioGroupUtil(mContext, ids, this, mName);
    }

    private void init(String name) {
        mName = name;
    }

    /**
     * @param value
     */
    public void setCheckedByBeanCD(BeanCD bean) {
        util.setCheckedByBeanCD(bean);
    }

    /**
     * @return
     */
    public BeanCD getCheckedBeanCD() {
        return util.getCheckedBeanCD();
    }

}
