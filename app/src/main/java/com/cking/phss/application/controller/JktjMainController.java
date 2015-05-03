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
import com.cking.phss.bean.IBean;
import com.cking.phss.global.Global;
import com.cking.phss.util.AppConfigFactory.AppConfig;
import com.cking.application.MyApplication;
import com.cking.phss.view.JktjBodyView;
import com.cking.phss.widget.GuidePager.OnPageChangeListener;
import com.cking.phss.widget.MenuRadioButton;
import com.cking.phss.widget.MenuRadioGroup;
import com.cking.phss.widget.MenuRadioGroup.OnCheckedChangeListener;

import java.util.HashMap;
import java.util.Map;

/**
 * com.cking.phss.view.JktjMainController
 * @author Administrator <br/>
 * create at 2014-6-11 上午11:39:13
 */
public class JktjMainController {
    private static final String TAG = "JktjMainController";
    private Context mContext = null;

    private static int sLeftRadioIndex = 0;
    MenuRadioGroup radiogoup_jktj;
    LinearLayout one_main_fragment_right_layout;
    private Map<String, IBean> beanMap = new HashMap<String, IBean>();
    View bodyView;
    MenuRadioButton[] radios = new MenuRadioButton[5];

    /**
     * 健康体检
     */
    private JktjBodyView mJktjBodyView = null;
    /**
     * @param context
     */
    public JktjMainController(Context context) {
        mContext = context;
        beanMap.put(AppConfig.class.getName(), Global.appConfig);
        
        init(context);
    }

    /**
     * @param context
     */
    private void init(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        bodyView = inflater.inflate(R.layout.fragment_health, null);
        
        mJktjBodyView = new JktjBodyView(context);
        radiogoup_jktj = (MenuRadioGroup) bodyView.findViewById(R.id.radiogoup_jktj);
        one_main_fragment_right_layout = (LinearLayout) bodyView
                .findViewById(R.id.one_main_fragment_right_layout);
        radios[0] = (MenuRadioButton) bodyView.findViewById(R.id.radio_kstj);
        radios[1] = (MenuRadioButton) bodyView.findViewById(R.id.radio_pttj);
        radios[2] = (MenuRadioButton) bodyView.findViewById(R.id.radio_tzbs);
        radios[3] = (MenuRadioButton) bodyView.findViewById(R.id.radio_xlpg);
        radios[4] = (MenuRadioButton) bodyView.findViewById(R.id.radio_lnpg);
        AppConfig appConfig = (AppConfig) beanMap.get(AppConfig.class.getName());
        if (appConfig != null) {
            String mksz = appConfig.getMksz();
            if (mksz != null) {
                radios[0].setVisibility(mksz.contains("快速体检") ? View.GONE : View.VISIBLE);
                radios[1].setVisibility(mksz.contains("普通体检") ? View.GONE : View.VISIBLE);
                radios[2].setVisibility(mksz.contains("体质辨识") ? View.GONE : View.VISIBLE);
                radios[3].setVisibility(mksz.contains("心理评估") ? View.GONE : View.VISIBLE);
                radios[4].setVisibility(mksz.contains("老年评估") ? View.GONE : View.VISIBLE);
            }
        }
        mJktjBodyView.getBeanFromDB();
        one_main_fragment_right_layout.removeAllViews();
        one_main_fragment_right_layout.addView(mJktjBodyView);
        int firstVisibleIndex = MyApplication.getInstance().getJktjFirstVisibleIndex();
        if (firstVisibleIndex >= 0) {
            mJktjBodyView.showItemByIndex(firstVisibleIndex);
        }
        radiogoup_jktj.setOnCheckedChangeListener(new OnCheckedChangeListener() {

          @Override
          public void onCheckedChanged(MenuRadioGroup group, int checkedId, int index) {
              // TODO Auto-generated method stub
              if(!((MenuRadioButton) group.findViewById(checkedId)).isChecked()){
                  return;
              }
              JktjMainController.sLeftRadioIndex = index;
              mJktjBodyView.showItemByIndex(index);
            }
        });

        mJktjBodyView.setOnPageChangeListener(new OnPageChangeListener() { // 当选中某页的回调
            
            @Override
            public void onPageSelected(int index) {
                if (radiogoup_jktj.getCheckedIndex() != index) {
                    radiogoup_jktj.setCheckedByIndex(index);
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
     * 
     */
    public void setValue() {
        mJktjBodyView.setValue();
    }
}
