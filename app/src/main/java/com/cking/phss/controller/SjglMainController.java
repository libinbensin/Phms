/* Xinhuaxing Inc. (C) 2014. All rights reserved.
 *
 * JbxxMainView.java
 * classes : com.cking.phss.view.JbxxMainView
 * @author Administrator
 * V 1.0.0
 * Create at 2014-6-11 上午11:39:13
 */
package com.cking.phss.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.cking.phss.R;
import com.cking.phss.view.SjglBodyView;

/**
 * com.cking.phss.view.YyzdMainController
 * @author Administrator <br/>
 * create at 2014-6-11 上午11:39:13
 */
public class SjglMainController {
    private static final String TAG = "SjglMainController";
    private Context mContext = null;

    View bodyView;
    LinearLayout one_main_fragment_right_layout;

    /**
     * 数据管理
     */
    private SjglBodyView mSjglBodyView = null;
    
    /**
     * @param context
     */
    public SjglMainController(Context context) {
        mContext = context;
        
        init(context);
    }

    /**
     * @param context
     */
    private void init(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        bodyView = inflater.inflate(R.layout.fragment_sjgl, null);
        
        mSjglBodyView = new SjglBodyView(context);
        one_main_fragment_right_layout = (LinearLayout) bodyView
                .findViewById(R.id.one_main_fragment_right_layout);
        mSjglBodyView.getBeanFromDB();
        one_main_fragment_right_layout.removeAllViews();
        one_main_fragment_right_layout.addView(mSjglBodyView);
    }

    /**
     * @return
     */
    public View getView() {
        return bodyView;
    }
}
