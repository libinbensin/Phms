/* Cking Inc. (C) 2012. All rights reserved.
 *
 * JzsItemWidget.java
 * classes : com.cking.phss.widget.JzsItemWidget
 * @author Wation Haliyoo
 * V 1.0.0
 * Create at 2012-9-19 下午02:04:52
 */
package com.cking.phss.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cking.phss.R;
import com.cking.phss.util.CheckBoxGroup;

/**
 * com.cking.phss.widget.JzsItemWidget
 * 
 * @author Wation Haliyoo <br/>
 *         create at 2012-9-19 下午02:04:52
 */
public class JzsItemWidget extends LinearLayout {
	private static final String TAG = "JzsItemWidget";

	private TextView mTitleText = null;
	private EditText mOtherEdit = null;
	private CheckBox mWuCheckBox = null;
	private CheckBoxGroup mCheckBoxGroup = null;
	private EditText qt_edit = null;
	private CheckBox check_qt = null;

	private int[] checkBoxViewIds = { R.id.fq02checkbox,
			R.id.fq03checkbox, R.id.fq04checkbox, R.id.fq05checkbox, R.id.fq06checkbox,
			R.id.fq07checkbox, R.id.fq08checkbox, R.id.fq09checkbox, R.id.fq10checkbox,
			R.id.fq11checkbox, R.id.fq12checkbox };

	/**
	 * @param context
	 */
	public JzsItemWidget(Context context) {
		super(context);

		init(context);
	}

	/**
	 * @param context
	 * @param attrs
	 */
	public JzsItemWidget(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater inflater = LayoutInflater.from(context);
		inflater.inflate(R.layout.jbxx_jzs_item, this);

		TypedArray a = context
				.obtainStyledAttributes(attrs, R.styleable.Widget);

		mTitleText = (TextView) findViewById(R.id.fqTextView);
		int titleResId = a.getResourceId(R.styleable.Widget_titleText, 0);
		if (titleResId == 0) {
			String titleStr = a.getString(R.styleable.Widget_titleText);
			mTitleText.setText(titleStr);
		} else {
			mTitleText.setText(titleResId);
		}

		mOtherEdit = (EditText) findViewById(R.id.fqqtEditText);
		int otherResId = a.getResourceId(R.styleable.Widget_otherText, 0);
		if (otherResId == 0) {
			String otherStr = a.getString(R.styleable.Widget_otherText);
			mOtherEdit.setText(otherStr);
		} else {
			mOtherEdit.setText(otherResId);
		}
		otherResId = a.getResourceId(R.styleable.Widget_otherHint, 0);
		if (otherResId == 0) {
			String otherStr = a.getString(R.styleable.Widget_otherHint);
			mOtherEdit.setHint(otherStr);
		} else {
			mOtherEdit.setHint(otherResId);
		}

		int checkedValue = a.getInt(R.styleable.Widget_checkedValue, 0);
		mWuCheckBox = (CheckBox) findViewById(R.id.fq01checkbox);
		if (mCheckBoxGroup == null) {
			mCheckBoxGroup = new CheckBoxGroup(checkBoxViewIds, this);
		}
		mCheckBoxGroup.setCheckedValue(checkedValue);

		a.recycle();

		mCheckBoxGroup.setEnabledAll(false);
        mCheckBoxGroup.setCheckedAll(false);
        
		mWuCheckBox.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (((CheckBox) v).isChecked()) {
					mCheckBoxGroup.setEnabledAll(false);
					mCheckBoxGroup.setCheckedAll(false);
				} else {
					mCheckBoxGroup.setEnabledAll(true);
				}
			}
		});

		init(context);
		
		mWuCheckBox.setChecked(true);
		mCheckBoxGroup.setCheckedAll(false);
		mOtherEdit.setEnabled(false);
		
	}

	/**
	 * @param context
	 */
	private void init(Context context) {
	    qt_edit = (EditText) findViewById(R.id.fqqtEditText);
	    check_qt = (CheckBox) findViewById(R.id.fq12checkbox);
	    check_qt.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
	                    @Override
	                    public void onCheckedChanged(CompoundButton buttonView,
	                            boolean isChecked) {
	                        if (isChecked) {
	                            qt_edit.setEnabled(true);
	                        } else {
	                            qt_edit.setText("");
	                            qt_edit.setEnabled(false);
	                        }
	                    }
	                });
	}

//	public void setCheckedValue(int checkedValue) {
//		mCheckBoxGroup.setCheckedValue(checkedValue);
//	}
//
//	public long getCheckedValue() {
//		return mCheckBoxGroup.getCheckedValue();
//	}

	public void setValue(String[] jzsStrings) {
        mWuCheckBox.setChecked(true);
        mCheckBoxGroup.setEnabledAll(false);
        mCheckBoxGroup.setCheckedAll(false);
		for (String s : jzsStrings) {
		    try {

	            if(s.trim().equals(""))
	                continue;
	            int value = new Integer(s.trim());
	            if (value == 1) {
	                // ：1、无；2、高血压；3、糖尿病；4、冠心病；5、慢性阻塞性肺疾病；6、恶性肿瘤；7、脑卒中；8、重性精神疾病；9、结核病；10、肝炎；11、先天畸形；12、其他
	                mWuCheckBox.setChecked(true);
	                mCheckBoxGroup.setEnabledAll(false);
	                mCheckBoxGroup.setCheckedAll(false);
	            } else {
	                if(!mCheckBoxGroup.getCheckBox(2).isEnabled()){
	                    mWuCheckBox.setChecked(false);
	                    mCheckBoxGroup.setEnabledAll(true);
	                    mCheckBoxGroup.setCheckedAll(false);
	                }
	                if (value - 2 >=0 && value - 2 < mCheckBoxGroup.size()) {
	                    mCheckBoxGroup.setChecked(value-2, true);
	                }
	                if(value==12){
	                    mOtherEdit.setEnabled(true);
	                }
	            }
	        
            } catch (Exception e) {
                e.printStackTrace();
            }
		}
	}
	
	public void setName(String name) {
		qt_edit = (EditText) findViewById(R.id.fqqtEditText);
	    check_qt = (CheckBox) findViewById(R.id.fq12checkbox);
	    
	    if( check_qt.isChecked() )
	    {
	    	qt_edit.setText(name);	    	
	    }
	}

	public String getValue() {
		String checkString=mCheckBoxGroup.getChecked();
		if(checkString.equals("")){
			checkString+="1";
		}
		return checkString;
	}
	
	public String getName() {
		String ret = "";
		
		qt_edit = (EditText) findViewById(R.id.fqqtEditText);
	    check_qt = (CheckBox) findViewById(R.id.fq12checkbox);
	    
	    if( check_qt.isChecked() )
	    {
	    	ret = qt_edit.getText().toString().trim();
	    }
	    
		return ret;
	}
}
