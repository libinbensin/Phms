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

import net.xinhuaxing.util.ResourcesFactory;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

import com.cking.phss.R;
import com.cking.phss.bean.BeanCD;
import com.cking.phss.bean.IBean;
import com.cking.phss.bean.Jmjkxx;
import com.cking.phss.util.CheckBoxGroupUtil;
import com.cking.phss.util.TispToastFactory;
import com.cking.phss.widget.SpinnerUtil;

/**
 * 残疾情况
 * 
 * @author Administrator <br/>
 *         create at 2012-9-16 上午11:25:10
 */
public class JbxxPage09 extends LinearLayout implements IPage {
    @SuppressWarnings("unused")
    private static final String TAG = "JbxxPage09";
    private Context mContext = null;

    RadioGroup cjqkRadioGroup;
    RadioButton cjqk01RadioButton;
    RadioButton cjqk02RadioButton;
    EditText cjzhEditText;
    SpinnerUtil cjdjSpinnerUtil;

    private CheckBoxGroupUtil checkBoxGroup;
    private int[] viewsId = new int[] { R.id.cjqk01CheckBox, R.id.cjqk02CheckBox,
            R.id.cjqk03CheckBox, R.id.cjqk04CheckBox, R.id.cjqk05CheckBox, R.id.cjqk06CheckBox,
            R.id.cjqk07CheckBox };

    EditText cjqkqtEditText;
    private Toast mToast = null;
    private Map<String, IBean> beanMap = null;

    /**
     * @param context
     */
    public JbxxPage09(Context context, Map<String, IBean> beanMap) {
        super(context);
        this.beanMap = beanMap;
        init(context);
    }

    /**
     * @param context
     * @param attrs
     */
    public JbxxPage09(Context context, Map<String, IBean> beanMap, AttributeSet attrs) {
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
        inflater.inflate(R.layout.fragment_archives_cjqk_layout, this);
        loadPage(context, this);
    }

    public void loadPage(Context context, ViewGroup viewGroup) {

        // 找到控件，并初始化状态 null表示使用默认值
        String[] ids = ResourcesFactory.listId(mContext, "zycj");
        checkBoxGroup = new CheckBoxGroupUtil(viewsId, viewGroup, ids);
        cjqkqtEditText = (EditText) findViewById(R.id.cjqkqtEditText);
        cjqkRadioGroup = (RadioGroup) findViewById(R.id.cjqkRadioGroup);
        cjqk01RadioButton = (RadioButton) findViewById(R.id.cjqk01RadioButton);
        cjqk02RadioButton = (RadioButton) findViewById(R.id.cjqk02RadioButton);
        cjzhEditText = (EditText) findViewById(R.id.cjzhEditText);
        cjdjSpinnerUtil = (SpinnerUtil) findViewById(R.id.cjdjSpinnerUtil);

        /**
         * 所有下拉框赋值
         */

        cjqkRadioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                boolean enabled = true;
                if (checkedId == cjqk01RadioButton.getId()) {
                    enabled = false;
                } else {
                    enabled = true;
                }
                setControlsEnabled(enabled);
            }
        });
        cjqk01RadioButton.setChecked(true);

        checkBoxGroup.getCheckBox(checkBoxGroup.size() - 1)// 最后一个checkBox的监听
                .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            cjqkqtEditText.setEnabled(true);
                        } else {
                            cjqkqtEditText.setText("");
                            cjqkqtEditText.setEnabled(false);
                        }
                    }
                });
        
        setValue();
    }

    @Override
    public void setValue() {
        // 子女家族史的赋值
        Jmjkxx mJmjkxx = (Jmjkxx) beanMap.get(Jmjkxx.class.getName());
        if (mJmjkxx == null) {
            return;
        }
        
        // 残疾情况的赋值
        // 残疾情况代码（多选），多个代码之间用英文|分隔，值为代码：1无残疾；2
        // 视力残疾；3听力残疾；4言语残疾；5 肢体残疾；6智力残疾；7精神残疾；8其他残疾 -->
        if (mJmjkxx.getDeformityCD() != null && !mJmjkxx.getDeformityCD().equals("")) {          
            String deformityCardNo = mJmjkxx.getDeformityCardNo();
            BeanCD deformityLevel = mJmjkxx.getDeformityLevel();
            String deformityCDs = mJmjkxx.getDeformityCD();
            String deformityName   = mJmjkxx.getDeformityName();
                        
            if(deformityCDs.equals("1"))
            {
            	cjqk01RadioButton.setChecked(true);
            	cjqk02RadioButton.setChecked(false);
            	setControlsEnabled(false);
                cjdjSpinnerUtil.setSelection(0);
            }
            else
            {
            	cjqk01RadioButton.setChecked(false);
            	cjqk02RadioButton.setChecked(true);
            	cjzhEditText.setText(deformityCardNo);
            	cjdjSpinnerUtil.setSelectedPositionByData(deformityLevel.getTagValue());
                checkBoxGroup.setCheckedByValues(deformityCDs);
            	cjqkqtEditText.setText(deformityName);
            	setControlsEnabled(true);
            }
        }
    }

    @Override
    public boolean getValue() {
        // 子女家族史的取值
        Jmjkxx mJmjkxx = (Jmjkxx) beanMap.get(Jmjkxx.class.getName());

        // 残疾情况的取值
        // 残疾情况代码（多选），多个代码之间用英文|分隔，值为代码：1无残疾；2
        // 视力残疾；3听力残疾；4言语残疾；5 肢体残疾；6智力残疾；7精神残疾；8其他残疾 -->
        if (cjqk01RadioButton.isChecked()) {
            mJmjkxx.setDeformityCD("1");
            mJmjkxx.setDeformityCardNo("");
        } else {
        	mJmjkxx.setDeformityCardNo(cjzhEditText.getText().toString());
        	BeanCD deformityLevel = new BeanCD();
        	deformityLevel.setcD(cjdjSpinnerUtil.getSelectedValue());
        	deformityLevel.setTagValue(cjdjSpinnerUtil.getSelectedData());        	
        	mJmjkxx.setDeformityLevel(deformityLevel);
            mJmjkxx.setDeformityCD(checkBoxGroup.getCheckValues(","));
            mJmjkxx.setDeformityName(cjqkqtEditText.getText().toString());
        }
        return true;
    }

    public void setControlsEnabled(boolean enabled) {

    	if(!enabled)
    	{   
    		cjzhEditText.setText("");
            cjdjSpinnerUtil.setSelection(0);
            cjqkqtEditText.setText("");
            checkBoxGroup.setCheckedAll(enabled);
    	}
    	
    	cjzhEditText.setEnabled(enabled);
        cjdjSpinnerUtil.setEnabled(enabled);
        cjqkqtEditText.setEnabled(false);
        checkBoxGroup.setEnabledAll(enabled);
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
