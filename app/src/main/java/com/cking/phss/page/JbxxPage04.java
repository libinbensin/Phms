/* Cking Inc. (C) 2012. All rights reserved.
 *
 * JbxxPage01.java
 * classes : com.cking.phss.view.JbxxBodyView
 * @author Administrator
 * V 1.0.0
 * Create at 2012-9-16 上午11:25:10
 */
package com.cking.phss.page;

import java.util.Map;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.cking.phss.R;
import com.cking.phss.bean.IBean;
import com.cking.phss.bean.Jmjkxx;
import com.cking.phss.util.CheckBoxGroup;
import com.cking.phss.util.TispToastFactory;

/**
 * 基本信息第3页 com.cking.phss.view.JbxxPage03
 * 
 * @author Administrator <br/>
 *         create at 2012-9-16 上午11:25:10
 */
public class JbxxPage04 extends LinearLayout implements IPage {
    @SuppressWarnings("unused")
    private static final String TAG = "JbxxPage04";
    private Context mContext = null;

    /**
     * 第三页控件
     */
   RadioGroup mBlsGroup = null;
    private CheckBoxGroup mCheckBoxGroup2 = null;
    private int[] viewIds2 = new int[] { R.id.bls01CheckBox, R.id.bls02CheckBox, R.id.bls03CheckBox };
    RadioButton bls01RadioButton = null;
    RadioButton bls02RadioButton = null;

    private Toast mToast = null;
    private Map<String, IBean> beanMap = null;

    private Button mYwgmsAddBtn = null;

    /**
     * @param context
     */
    public JbxxPage04(Context context, Map<String, IBean> beanMap) {
        super(context);
        this.beanMap = beanMap;
        init(context);
    }

    /**
     * @param context
     * @param attrs
     */
    public JbxxPage04(Context context, Map<String, IBean> beanMap, AttributeSet attrs) {
        super(context, attrs);
        this.beanMap = beanMap;
        init(context);
    }

    /**
     * @param context
     */
    private void init(Context context) {
        mContext = context;
        mToast = TispToastFactory.getToast(context, "");
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.fragment_archives_bls_layout, this);

        loadPage(context, this);
    }

    public void loadPage(Context context, ViewGroup viewGroup) {
        mCheckBoxGroup2 = new CheckBoxGroup(viewIds2, viewGroup);
        mBlsGroup = (RadioGroup) findViewById(R.id.blsRadioGroup);

        bls01RadioButton = (RadioButton) findViewById(R.id.bls01RadioButton);
        bls02RadioButton = (RadioButton) findViewById(R.id.bls02RadioButton);

        // 默认的情况下的状态
        bls01RadioButton.setChecked(true);
        mCheckBoxGroup2.setEnabledAll(false);

        mBlsGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.bls01RadioButton) {
                    mCheckBoxGroup2.setCheckedAll(false);
                    mCheckBoxGroup2.setEnabledAll(false);
                } else {
                    mCheckBoxGroup2.setEnabledAll(true);
                }
            }
        });
    }

    @Override
    public void setValue() {
        Jmjkxx mJmjkxx = (Jmjkxx) beanMap.get(Jmjkxx.class.getName());
        if (mJmjkxx == null) {
            return;
        }

        // 暴露史
        String[] exposureCDs = mJmjkxx.getExposureCD().split(",");
        for (String e : exposureCDs) {
            try {
                if (e.trim().equals(""))
                    continue;
                int value = Integer.parseInt(e.trim());
                if (value == 1) {
                    bls01RadioButton.setChecked(true);
                    mCheckBoxGroup2.setCheckedAll(false);
                    mCheckBoxGroup2.setEnabledAll(false);
                } else {
                    if (!bls02RadioButton.isChecked()) {
                        bls02RadioButton.setChecked(true);
                        mCheckBoxGroup2.setEnabledAll(true);
                    }
                    if (value - 2 >= 0 && value - 2 < mCheckBoxGroup2.size()) {
                        mCheckBoxGroup2.setChecked(value - 2, true);
                    }
                }

            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    @Override
    public boolean getValue() {
        Jmjkxx mJmjkxx = (Jmjkxx) beanMap.get(Jmjkxx.class.getName());

        // 暴露史：1无 2化学品 3毒物 4射线
        String exposureCDs = "";
        if (!bls01RadioButton.isChecked()) {
            for (int i = 0; i < viewIds2.length; i++) {
                if (mCheckBoxGroup2.getCheckBox(i).isChecked()) {
                    exposureCDs += (i + 2 + ", ");
                }
            }
            mJmjkxx.setExposureName("有");
        } else {
            exposureCDs = "1";
            mJmjkxx.setExposureName("无");
        }
        if (exposureCDs.length() > 2)
            exposureCDs = exposureCDs.substring(0, exposureCDs.length() - 2);
        mJmjkxx.setExposureCD(exposureCDs);
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.cking.phss.page.IPage#clear()
     */
    @Override
    public void clear() {
    }
}
