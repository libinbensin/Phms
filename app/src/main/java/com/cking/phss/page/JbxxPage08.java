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
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

import com.cking.phss.R;
import com.cking.phss.bean.IBean;
import com.cking.phss.bean.Jmjkxx;
import com.cking.phss.util.CheckBoxGroupUtil;
import com.cking.phss.util.RadioGroupUtil;
import com.cking.phss.util.TispToastFactory;
import com.cking.phss.widget.MultipleChoiceText;

/**
 * 遗传病史
 * 
 * @author Administrator <br/>
 *         create at 2012-9-16 上午11:25:10
 */
public class JbxxPage08 extends LinearLayout implements IPage {
	@SuppressWarnings("unused")
	private static final String TAG = "JbxxPage08";
	private Context mContext = null;
	private RadioGroup mYcbsRadioGroup = null;
	private RadioButton mYcbsRadio_no = null;
	private RadioButton mYcbsRadio_yes = null;
	private MultipleChoiceText mYcbs_OtherEdit = null;
	
	private CheckBoxGroupUtil checkBoxGroup;
    private RadioGroupUtil ycbsRadioGroup;// 自知力

    private int[] ycbsRadioBtnIds = new int[] { R.id.ycbs01RadioButton,
            R.id.ycbs02RadioButton };
	
	private Toast mToast = null;
	private Map<String, IBean> beanMap = null;

	/**
	 * @param context
	 */
	public JbxxPage08(Context context, Map<String, IBean> beanMap) {
		super(context);
		this.beanMap = beanMap;
		init(context);
	}

	/**
	 * @param context
	 * @param attrs
	 */
	public JbxxPage08(Context context, Map<String, IBean> beanMap,
			AttributeSet attrs) {
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
		inflater.inflate(R.layout.fragment_archives_ycbs_layout, this);
		loadPage(context, this);
	}

	public void loadPage(Context context, ViewGroup viewGroup) {

		mYcbsRadioGroup = (RadioGroup) findViewById(R.id.ycbsRadioGroup);
		mYcbsRadio_no = (RadioButton) findViewById(R.id.ycbs01RadioButton);
		mYcbsRadio_yes = (RadioButton) findViewById(R.id.ycbs02RadioButton);
		mYcbs_OtherEdit = (MultipleChoiceText) findViewById(R.id.ycbsMultipleChoiceText);
        String[] ids = ResourcesFactory.listId(mContext, "ycbkwyy");
        ycbsRadioGroup = new RadioGroupUtil(ycbsRadioBtnIds, mYcbsRadioGroup, ids);
	    
		/**
         * 所有下拉框赋值
         */
				
		mYcbsRadio_no.setChecked(true);
		mYcbs_OtherEdit.setEnabled(false);
		mYcbsRadioGroup
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(RadioGroup group, int checkedId) {
						if (checkedId == mYcbsRadio_no.getId()) {
							mYcbs_OtherEdit.setEnabled(false);
						    mYcbs_OtherEdit.setText("");
						    mYcbs_OtherEdit.clearChecked();
						} else {
							mYcbs_OtherEdit.setEnabled(true);
						}
					}
				});
	}

	@Override
	public void setValue() {
		// 子女家族史的赋值
		Jmjkxx mJmjkxx = (Jmjkxx) beanMap.get(Jmjkxx.class.getName());
        if (mJmjkxx == null) {
            return;
        }

		// 遗传病史的赋值
		int heredityCD = mJmjkxx.getHeredityCD();
        ycbsRadioGroup.setCheckedByValue(heredityCD);
        if (mYcbsRadio_yes.isChecked()) {
            mYcbs_OtherEdit.setCheckedByDatas(mJmjkxx.getHeredityName());
        }
//		if (heredityCD == 1) {
//			mYcbsRadio_no.setChecked(true);
//			mYcbs_OtherEdit.setEnabled(false);
//		} else {
//		    mYcbs_OtherEdit.clearChecked();
//			mYcbsRadio_yes.setChecked(true);
//			mYcbs_OtherEdit.setEnabled(true);
//			mYcbs_OtherEdit.setCheckedByDatas(mJmjkxx.getHeredityName());
//		}
	}

	@Override
	public boolean getValue() {
		// 子女家族史的取值
		Jmjkxx mJmjkxx = (Jmjkxx) beanMap.get(Jmjkxx.class.getName());

		// 遗传病史的取值

		int heredityCD = ycbsRadioGroup.getCheckValueInt();
        mJmjkxx.setHeredityCD(heredityCD);
		if (mYcbsRadio_no.isChecked()){
		    mJmjkxx.setHeredityName("");
		}
		else {
			mJmjkxx.setHeredityName(mYcbs_OtherEdit.getCheckedDatas(","));
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
