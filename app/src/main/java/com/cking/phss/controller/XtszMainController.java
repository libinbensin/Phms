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
import com.cking.phss.view.XtszBodyView;
import com.cking.phss.widget.GuidePager.OnPageChangeListener;
import com.cking.phss.widget.MenuRadioButton;
import com.cking.phss.widget.MenuRadioGroup;
import com.cking.phss.widget.MenuRadioGroup.OnCheckedChangeListener;

/**
 * com.cking.phss.view.JktjMainController
 * @author Administrator <br/>
 * create at 2014-6-11 上午11:39:13
 */
public class XtszMainController {
    private static final String TAG = "JktjMainController";
    private Context mContext = null;
    View bodyView;

    private static int sLeftRadioIndex = 0;
    MenuRadioGroup radiogoup_xtsz;
    LinearLayout one_main_fragment_right_layout;

    /**
     * 系统设置
     */
    private XtszBodyView mXtszBodyView = null;
    
    /**
     * @param context
     */
    public XtszMainController(Context context) {
        mContext = context;
        
        init(context);
    }

    /**
     * @param context
     */
    private void init(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        bodyView = inflater.inflate(R.layout.fragment_xtsz, null);
        
        mXtszBodyView = new XtszBodyView(context);
        radiogoup_xtsz = (MenuRadioGroup) bodyView.findViewById(R.id.radiogoup_xtsz);
        one_main_fragment_right_layout = (LinearLayout) bodyView
                .findViewById(R.id.one_main_fragment_right_layout);
        mXtszBodyView.getBeanFromDB();
        one_main_fragment_right_layout.removeAllViews();
        one_main_fragment_right_layout.addView(mXtszBodyView);
        radiogoup_xtsz.setOnCheckedChangeListener(new OnCheckedChangeListener() {

          @Override
          public void onCheckedChanged(MenuRadioGroup group, int checkedId, int index) {
              // TODO Auto-generated method stub
              if(!((MenuRadioButton) group.findViewById(checkedId)).isChecked()){
                  return;
              }
              XtszMainController.sLeftRadioIndex = index;
              mXtszBodyView.showItemByIndex(index);
            }
        });

        mXtszBodyView.setOnPageChangeListener(new OnPageChangeListener() { // 当选中某页的回调
            
            @Override
            public void onPageSelected(int index) {
                if (radiogoup_xtsz.getCheckedIndex() != index) {
                    radiogoup_xtsz.setCheckedByIndex(index);
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
}
