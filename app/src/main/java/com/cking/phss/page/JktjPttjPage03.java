/* Cking Inc. (C) 2012. All rights reserved.
 *
 * JbxxPage01.java
 * classes : com.cking.phss.view.JbxxBodyView
 * @author Administrator
 * V 1.0.0
 * Create at 2012-9-16 上午11:25:10
 */
package com.cking.phss.page;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

import com.cking.phss.R;
import com.cking.phss.util.TispToastFactory;
import com.cking.phss.widget.SpinnerUtil;


/**
 * 健康体检普通体检体检第3页
 * com.cking.phss.view.JktjPttjPage03
 * @author Administrator <br/>
 * create at 2012-9-16 上午11:25:10
 */
public class JktjPttjPage03 extends LinearLayout {
    @SuppressWarnings("unused")
    private static final String TAG = "JktjPttjPage03";
    private Context mContext = null;
    
    /**
     * 第三页控件
     */   
	EditText dlfsEditText = null;
    
	CheckBox ysxg01CheckBox = null;
	CheckBox ysxg02CheckBox = null;
	CheckBox ysxg03CheckBox = null;
	CheckBox ysxg04CheckBox = null;
	CheckBox ysxg05CheckBox = null;
	CheckBox ysxg06CheckBox = null;
    
	RadioGroup xyzkRadioGroup = null;
	RadioButton xyzk01RadioButton = null;
	RadioButton xyzk02RadioButton = null;
	RadioButton xyzk03RadioButton = null;
    
	EditText rxylEditText = null;
	EditText ksxynlEditText = null;
	EditText jynlEditText = null;
    
	SpinnerUtil yjplSpinnerUtil = null; 
	EditText ryjlEditText = null;
    
	RadioButton sfjj01RadioButton = null;
	RadioButton sfjj02RadioButton = null;
    
	EditText jjnlEditText = null;
	EditText ksyjnlEditText = null;
    
	RadioButton jynnsfzxj01RadioButton = null;
	RadioButton jynnsfzxj02RadioButton = null;
    
	SpinnerUtil yjzlSpinnerUtil = null;
	
    
    private Toast mToast = null;
    
    /**
     * @param context
     */
    public JktjPttjPage03(Context context) {
        super(context);
        init(context);
    }

    /**
     * @param context
     * @param attrs
     */
    public JktjPttjPage03(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    /**
     * @param context
     */
    private void init(Context context) {
        mContext = context;
        mToast = TispToastFactory.getToast(context, "");
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.fragment_health_pttj_3_layout, this);
        
        loadPage(context, this);
    }

    public void loadPage(Context context, ViewGroup viewGroup) {
		dlfsEditText = (EditText) findViewById(R.id.dlfsEditText);
        
		ysxg01CheckBox = (CheckBox ) findViewById(R.id.ysxg01CheckBox);
		ysxg02CheckBox = (CheckBox ) findViewById(R.id.ysxg02CheckBox);
		ysxg03CheckBox = (CheckBox ) findViewById(R.id.ysxg03CheckBox);
		ysxg04CheckBox = (CheckBox ) findViewById(R.id.ysxg04CheckBox);
		ysxg05CheckBox = (CheckBox ) findViewById(R.id.ysxg05CheckBox);
		ysxg06CheckBox = (CheckBox ) findViewById(R.id.ysxg06CheckBox);
        		
		xyzkRadioGroup = (RadioGroup) findViewById(R.id.xyzkRadioGroup);
		xyzk01RadioButton = (RadioButton ) findViewById(R.id.xyzk01RadioButton);
		xyzk02RadioButton = (RadioButton ) findViewById(R.id.xyzk02RadioButton);
		xyzk03RadioButton = (RadioButton ) findViewById(R.id.xyzk03RadioButton);
        
		rxylEditText		= ( EditText ) findViewById(R.id.rxylEditText		);
		ksxynlEditText	= ( EditText ) findViewById(R.id.ksxynlEditText	);
		jynlEditText		= ( EditText ) findViewById(R.id.jynlEditText		);
    
		yjplSpinnerUtil = (SpinnerUtil) findViewById(R.id.yjplSpinnerUtil);
		ryjlEditText = (EditText) findViewById(R.id.ryjlEditText);
        
		sfjj01RadioButton = (RadioButton ) findViewById(R.id.sfjj01RadioButton);
		sfjj02RadioButton = (RadioButton ) findViewById(R.id.sfjj02RadioButton);
        
		jjnlEditText = (EditText) findViewById(R.id.jjnlEditText);
		ksyjnlEditText = (EditText) findViewById(R.id.ksyjnlEditText);
        
		jynnsfzxj01RadioButton = (RadioButton ) findViewById(R.id.jynnsfzxj01RadioButton);
		jynnsfzxj02RadioButton = (RadioButton ) findViewById(R.id.jynnsfzxj02RadioButton);
        
		yjzlSpinnerUtil = (SpinnerUtil ) findViewById(R.id.yjzlSpinnerUtil);
		
		rxylEditText.setEnabled(false);
        ksxynlEditText.setEnabled(false);
        jynlEditText.setEnabled(false);
		rxylEditText.setText("");
		ksxynlEditText.setText("");
		jynlEditText.setText("");

		xyzkRadioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                Log.i(TAG,"xyzkRadioGroup checkedId = "+checkedId);

				//从不吸烟
                if (checkedId == xyzk01RadioButton.getId()) {
					rxylEditText.setEnabled(false);
					ksxynlEditText.setEnabled(false);
					jynlEditText.setEnabled(false);
					rxylEditText.setText("");
					ksxynlEditText.setText("");
					jynlEditText.setText("");
                } 
				//过去吸烟，已戒烟
				else if (checkedId == xyzk02RadioButton.getId()) {
					rxylEditText.setEnabled(true);
					ksxynlEditText.setEnabled(true);
					jynlEditText.setEnabled(true);
                }
				//吸烟
				else if (checkedId == xyzk03RadioButton.getId()) {
					rxylEditText.setEnabled(true);
					ksxynlEditText.setEnabled(true);
					jynlEditText.setEnabled(false);
				}
            }
        });

		yjplSpinnerUtil.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                switch (arg2) {
                    case 0:
						ryjlEditText.setEnabled(false);                         
						sfjj01RadioButton.setEnabled(false);
						sfjj02RadioButton.setEnabled(false);                         
						jjnlEditText.setEnabled(false);
						ksyjnlEditText.setEnabled(false);                         
						jynnsfzxj01RadioButton.setEnabled(false);
						jynnsfzxj02RadioButton.setEnabled(false);                        
						yjzlSpinnerUtil.setEnabled(false);
                        break;

                    default:
						ryjlEditText.setEnabled(true);                         
						sfjj01RadioButton.setEnabled(true);
						sfjj02RadioButton.setEnabled(true);                         
						jjnlEditText.setEnabled(true);
						ksyjnlEditText.setEnabled(true);                         
						jynnsfzxj01RadioButton.setEnabled(true);
						jynnsfzxj02RadioButton.setEnabled(true);                        
						yjzlSpinnerUtil.setEnabled(true);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });
    }
}
