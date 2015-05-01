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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cking.phss.R;
import com.cking.phss.bean.BeanCD;
import com.cking.phss.bean.IBean;
import com.cking.phss.bean.Jmjbxx;
import com.cking.phss.bean.Jmjtxx;
import com.cking.phss.util.RadioGroupUtil;
import com.cking.phss.util.TispToastFactory;
import com.cking.phss.widget.SpinnerUtil;

/**
 * 基本信息第2页 com.cking.phss.view.JbxxPage10
 * 
 * @author Administrator <br/>
 *         create at 2012-9-16 上午11:25:10
 */
public class JbxxPage10 extends LinearLayout implements IPage {
    @SuppressWarnings("unused")
    private static final String TAG = "JbxxPage10";
    private Context mContext = null;
    private Toast mToast = null;
    private Map<String, IBean> beanMap = null;
    /**
     * 第二页控件
     */
    private TextView jtdabhTextView = null;// 家庭id
    private SpinnerUtil jtlxSpinnerUtil = null;// 家庭类型
    private RadioGroupUtil radioGroup1;//家庭年收入的group
    private RadioGroupUtil radioGroup2;//户属性的group
    private int[] radioBtnIds1 = new int[] { R.id.nrjsr01Radiobutton, R.id.nrjsr02Radiobutton,
            R.id.nrjsr03Radiobutton, R.id.nrjsr04Radiobutton, R.id.nrjsr05Radiobutton};
    private int[] radioBtnIds2 = new int[] { R.id.hsx01Radiobutton, R.id.hsx02Radiobutton,
            R.id.hsx03Radiobutton, R.id.hsx04Radiobutton, R.id.hsx05Radiobutton,
            R.id.hsx06Radiobutton, R.id.hsx07Radiobutton, R.id.hsx08Radiobutton};
    EditText hzxmEditText;
    EditText rksEditText;
    SpinnerUtil jjzkSpinnerUtil;

    /**
     * @param context
     */
    public JbxxPage10(Context context, Map<String, IBean> beanMap) {
        super(context);
        this.beanMap = beanMap;
        init(context);
    }

    /**
     * @param context
     * @param attrs
     */
    public JbxxPage10(Context context, Map<String, IBean> beanMap, AttributeSet attrs) {
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
        inflater.inflate(R.layout.fragment_archives_jtxx_layout, this);
        loadPage(context, this);
    }

    public void loadPage(Context context, ViewGroup viewGroup) {
        jtdabhTextView = (TextView) findViewById(R.id.jtdabhTextView);
        jtlxSpinnerUtil = (SpinnerUtil) findViewById(R.id.jtlxSpinnerUtil);
        //年收入
        radioGroup1 = new RadioGroupUtil(radioBtnIds1, viewGroup,null);
        //户属性
        String[] houseHoldValues=context.getResources().getStringArray(R.array.houseHold_values);
        radioGroup2 = new RadioGroupUtil(radioBtnIds2, viewGroup,houseHoldValues);

        hzxmEditText = (EditText) findViewById(R.id.hzxmEditText);
        rksEditText = (EditText) findViewById(R.id.rksEditText);
        jjzkSpinnerUtil = (SpinnerUtil) findViewById(R.id.jjzkSpinnerUtil);

    }

    @Override
    public void setValue() {
        Jmjtxx mJmjtxx = (Jmjtxx) beanMap.get(Jmjtxx.class.getName());
        if (mJmjtxx == null) {
            return;
        }
        Jmjbxx mJmjbxx = (Jmjbxx) beanMap.get(Jmjbxx.class.getName());
        if (mJmjbxx == null) {
            return;
        }
        if (mJmjtxx.getFamilyID() != null)
            jtdabhTextView.setText(mJmjtxx.getFamilyID());
        jtlxSpinnerUtil.setSelectedPositionByValue(mJmjtxx.getFamilyTypeCD());
        radioGroup1.setCheckedByValue(mJmjtxx.getIncomeCD());
        radioGroup2.setCheckedByValue(mJmjtxx.getHouseHoldCD());

        // 如果当前与户主关系是户主，则第10页的户主是当前姓名
        if (mJmjbxx.getRelation() != null && "户主".equals(mJmjbxx.getRelation().getTagValue())) {
            hzxmEditText.setEnabled(false);
            mJmjtxx.setHouseholder(mJmjbxx.getResidentName());
        } else {
            hzxmEditText.setEnabled(true);
        }
        hzxmEditText.setText(mJmjtxx.getHouseholder());
        rksEditText.setText(mJmjtxx.getPopulation() + "");
        if (mJmjtxx.getEconomics() != null) {
            jjzkSpinnerUtil.setSelectedPositionByValue(mJmjtxx.getEconomics().getcD());
        }
    }

    @Override
    public boolean getValue() {
        Jmjtxx mJmjtxx = (Jmjtxx) beanMap.get(Jmjtxx.class.getName());
        // 家庭档案编号
        mJmjtxx.setFamilyID(jtdabhTextView.getText().toString());
        // 家庭类型
        mJmjtxx.setFamilyTypeCD(jtlxSpinnerUtil.getSelectedValue());
        // 收入
        mJmjtxx.setIncomeCD(radioGroup1.getCheckValueInt());
        // 户属性
        mJmjtxx.setHouseHoldCD(radioGroup2.getCheckValue());  
        mJmjtxx.setHouseholder(hzxmEditText.getText().toString());
        try {
            int population = Integer.parseInt(rksEditText.getText().toString());
            mJmjtxx.setPopulation(population);
        } catch (NumberFormatException e) {
            // do nothing, date will be uninitialized
        }
        mJmjtxx.setEconomics(new BeanCD(jjzkSpinnerUtil.getSelectedValueInt() + "", jjzkSpinnerUtil
                .getSelectedData()));
        return true;
    }

    @Override
    protected void onAttachedToWindow() {
        setValue(); // 主要解决户主信息无法实时更新的问题。
        super.onAttachedToWindow();
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
