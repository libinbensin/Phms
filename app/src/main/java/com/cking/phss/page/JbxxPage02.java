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
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.cking.phss.R;
import com.cking.phss.bean.IBean;
import com.cking.phss.bean.Jmjbxx;
import com.cking.phss.util.CheckBoxGroupUtil;
import com.cking.phss.util.TispToastFactory;

/**
 * 基本信息第2页 com.cking.phss.view.JbxxPage02
 * 
 * @author Administrator <br/>
 *         create at 2012-9-16 上午11:25:10
 */
public class JbxxPage02 extends LinearLayout implements IPage {
    @SuppressWarnings("unused")
    private static final String TAG = "JbxxPage02";
    private Context mContext = null;
    private Toast mToast = null;
    private Map<String, IBean> beanMap = null;
    EditText ybkhEditText;
    /**
     * 第二页控件
     */
    private CheckBoxGroupUtil checkboxGroup3;//保险类别的group
    private int[] checkBoxIds3 = new int[] { R.id.bxlb01CheckBox, R.id.bxlb02CheckBox,
            R.id.bxlb03CheckBox, R.id.bxlb04CheckBox, R.id.bxlb05CheckBox, R.id.bxlb06CheckBox,
            R.id.bxlb07CheckBox, R.id.bxlb08CheckBox, R.id.bxlb09CheckBox, R.id.bxlb10CheckBox,
            R.id.bxlb11CheckBox, R.id.bxlb12CheckBox, R.id.bxlb13CheckBox };

    /**
     * @param context
     */
    public JbxxPage02(Context context, Map<String, IBean> beanMap) {
        super(context);
        this.beanMap = beanMap;
        init(context);
    }

    /**
     * @param context
     * @param attrs
     */
    public JbxxPage02(Context context, Map<String, IBean> beanMap, AttributeSet attrs) {
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
        inflater.inflate(R.layout.fragment_archives_bxlb_layout, this);
        loadPage(context, this);
    }

    public void loadPage(Context context, ViewGroup viewGroup) {
        //保险类别
        checkboxGroup3 = new CheckBoxGroupUtil(mContext, checkBoxIds3, viewGroup, "bxlb");
        ybkhEditText = (EditText) findViewById(R.id.ybkhEditText);
        ybkhEditText.setEnabled(false);

        checkboxGroup3.getCheckBox(0)// 第一一个checkBox的监听
                .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            ybkhEditText.setEnabled(true);
                        } else {
                            ybkhEditText.setText("");
                            ybkhEditText.setEnabled(false);
                        }
                    }
                });
    }

    @Override
    public void setValue() {
        Jmjbxx mJmjbxx = (Jmjbxx) beanMap.get(Jmjbxx.class.getName());
        if (mJmjbxx == null) {
            mToast.setText(R.string.toast_info_none_resident);
            mToast.show();
            return;
        }
        checkboxGroup3.setCheckedByValues(mJmjbxx.getInsuranceCD());
        if (checkboxGroup3.getCheckBox(0).isChecked()) {
            ybkhEditText.setText(mJmjbxx.getInsuranceNum());
        }
    }

    @Override
    public boolean getValue() {
        Jmjbxx mJmjbxx = (Jmjbxx) beanMap.get(Jmjbxx.class.getName());
        // 01,医保；02,商业医疗保险；03,大病统筹；04,新型农村合作医疗；05,城镇居民基本医疗保险；06,公费医疗；99,其他
        mJmjbxx.setInsuranceCD(checkboxGroup3.getCheckValues(","));
        
        if (checkboxGroup3.getCheckBox(0).isChecked()) {
        	mJmjbxx.setInsuranceNum( ybkhEditText.getText().toString() );        	
        } else {
        	mJmjbxx.setInsuranceNum("");
        }
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
