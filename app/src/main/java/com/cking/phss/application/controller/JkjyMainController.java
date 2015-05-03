package com.cking.phss.application.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.cking.phss.R;
import com.cking.phss.view.JkjyBodyView;
import com.cking.phss.widget.GuidePager.OnPageChangeListener;
import com.cking.phss.widget.MenuRadioButton;
import com.cking.phss.widget.MenuRadioGroup;
import com.cking.phss.widget.MenuRadioGroup.OnCheckedChangeListener;

/**
 * com.cking.phss.view.JkjyMainController
 * @author Administrator <br/>
 * create at 2014-6-11 上午11:39:13
 */
public class JkjyMainController {
    private static final String TAG = "JkjyMainController";
    private Context mContext = null;

    private static int sLeftRadioIndex = 0;
    MenuRadioGroup radiogoup_jkjy;
    LinearLayout one_main_fragment_right_layout;
    View bodyView;

    /**
     * 基本信息
     */
    private JkjyBodyView mJkjyBodyView = null;
    
    /**
     * @param context
     */
    public JkjyMainController(Context context) {
        mContext = context;
        
        init(context);
    }

    /**
     * @param context
     */
    private void init(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        bodyView = inflater.inflate(R.layout.fragment_jkjy, null);
        
        mJkjyBodyView = new JkjyBodyView(context);
        radiogoup_jkjy = (MenuRadioGroup) bodyView.findViewById(R.id.radiogoup_jkjy);
        one_main_fragment_right_layout = (LinearLayout) bodyView
                .findViewById(R.id.one_main_fragment_right_layout);
        mJkjyBodyView.getBeanFromDB();
        one_main_fragment_right_layout.removeAllViews();
        one_main_fragment_right_layout.addView(mJkjyBodyView);
        radiogoup_jkjy.setOnCheckedChangeListener(new OnCheckedChangeListener() {

          @Override
          public void onCheckedChanged(MenuRadioGroup group, int checkedId, int index) {
              // TODO Auto-generated method stub
              if(!((MenuRadioButton) group.findViewById(checkedId)).isChecked()){
                  return;
              }
              JkjyMainController.sLeftRadioIndex = index;
              mJkjyBodyView.showItemByIndex(index);
            }
        });

        mJkjyBodyView.setOnPageChangeListener(new OnPageChangeListener() { // 当选中某页的回调
            
            @Override
            public void onPageSelected(int index) {
                if (radiogoup_jkjy.getCheckedIndex() != index) {
                    radiogoup_jkjy.setCheckedByIndex(index);
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
