/* Xinhuaxing Inc. (C) 2014. All rights reserved.
 *
 * JbxxMainView.java
 * classes : com.cking.phss.view.JbxxMainView
 * @author Administrator
 * V 1.0.0
 * Create at 2014-6-11 上午11:39:13
 */
package com.cking.phss.application.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.cking.phss.R;
import com.cking.phss.view.JbxxBodyView;
import com.cking.phss.widget.GuidePager.OnPageChangeListener;
import com.cking.phss.widget.MenuRadioButton;
import com.cking.phss.widget.MenuRadioGroup;
import com.cking.phss.widget.MenuRadioGroup.OnCheckedChangeListener;

/**
 * com.cking.phss.view.JbxxMainController
 * @author Administrator <br/>
 * create at 2014-6-11 上午11:39:13
 */
public class JbxxMainController {
    private static final String TAG = "JbxxMainController";
    private Context mContext = null;

    private static int sLeftRadioIndex = 0;
    MenuRadioGroup radiogoup_jbxx;
    LinearLayout one_main_fragment_right_layout;
    View bodyView;

    /**
     * 基本信息
     */
    private JbxxBodyView mJbxxBodyView = null;
    
    /**
     * @param context
     */
    public JbxxMainController(Context context) {
        mContext = context;
        
        init(context);
    }

    /**
     * @param context
     */
    private void init(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        bodyView = inflater.inflate(R.layout.fragment_archives, null);
        
        mJbxxBodyView = new JbxxBodyView(context);
        radiogoup_jbxx = (MenuRadioGroup) bodyView.findViewById(R.id.radiogoup_jbxx);
        one_main_fragment_right_layout = (LinearLayout) bodyView
                .findViewById(R.id.one_main_fragment_right_layout);
        mJbxxBodyView.getBeanFromDB();
        one_main_fragment_right_layout.removeAllViews();
        one_main_fragment_right_layout.addView(mJbxxBodyView);
        radiogoup_jbxx.setOnCheckedChangeListener(new OnCheckedChangeListener() {

          @Override
          public void onCheckedChanged(MenuRadioGroup group, int checkedId, int index) {
              // TODO Auto-generated method stub
              if(!((MenuRadioButton) group.findViewById(checkedId)).isChecked()){
                  return;
              }
              JbxxMainController.sLeftRadioIndex = index;
              mJbxxBodyView.showItemByIndex(index);
            }
        });

        mJbxxBodyView.setOnPageChangeListener(new OnPageChangeListener() { // 当选中某页的回调
            
            @Override
            public void onPageSelected(int index) {
                if (radiogoup_jbxx.getCheckedIndex() != index) {
                    radiogoup_jbxx.setCheckedByIndex(index);
                }
            }
        });
    }

    /**
     * @return
     */
    public View getView() {
        return bodyView;
    }

    /**
     * @param paperNum
     */
    public void autoSearchPaperNum(String paperNum) {
        mJbxxBodyView.autoSearchPaperNum(paperNum);
    }
}
