/* Cking Inc. (C) 2012. All rights reserved.
 *
 * JbxxPage01.java
 * classes : com.cking.phss.view.JbxxBodyView
 * @author Administrator
 * V 1.0.0
 * Create at 2012-9-16 上午11:25:10
 */
package com.cking.phss.page;

import net.xinhuaxing.util.StringUtil;
import android.content.Context;
import android.text.Editable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;

import com.cking.phss.R;
import com.cking.phss.util.DecimalsTextWatcher;
import com.cking.phss.util.TestResultUtil;
import com.cking.phss.util.TispToastFactory;
import com.cking.phss.widget.SpinnerUtil;

/**
 * 健康体检普通体检体检第2页
 * com.cking.phss.view.JktjPttjPage02
 * @author Administrator <br/>
 * create at 2012-9-16 上午11:25:10
 */
public class JktjPttjPage02 extends LinearLayout {
    @SuppressWarnings("unused")
    private static final String TAG = "JktjPttjPage02";
    private Context mContext = null;
    
    /**
     * 第一页控件
     */
//    private Spinner mGenderSpinner = null;
//    private CalendarText mBirthdayText = null;
//    private EditText mCompanyEdit = null;
//    private EditText mTelEdit = null;
//    private EditText mContactNameEdit = null;
//    private EditText mContactTelEdit = null;
//    private Spinner mLiveSpinner = null;
//    private Spinner mEthnicSpinner = null;
//    private Spinner mMarriageSpinner = null;
//    private Spinner mEducbgSpinner = null;
//    private Spinner mRhSpinner = null;
//    private EditText mJobEdit = null;

	EditText twEditText = null;
	EditText mlEditText = null;
	EditText hxplEditText = null;
	EditText zcxy01EditText = null;
	EditText zcxy02EditText = null;

	EditText sgEditText = null;
	EditText bmiEditText = null;

	EditText ycxy01EditText = null;
	EditText ycxy02EditText = null;

	EditText tzEditText = null;
	EditText ywEditText = null;

	SpinnerUtil jkzkSpinnerUtil = null;
	SpinnerUtil shzlSpinnerUtil = null;

	RadioButton rzgn01RadioButton = null;
	RadioButton rzgn02RadioButton = null;

	EditText jyzlztjcEditText = null;

	RadioButton qgzt01RadioButton = null;
	RadioButton qgzt02RadioButton = null;
	EditText jyyyztjcEditText = null;

	SpinnerUtil dlplSpinnerUtil = null;
	EditText mcdlsjEditText = null;
	EditText jcdlsjEditText = null;

    
    private Toast mToast = null;
    
    /**
     * @param context
     */
    public JktjPttjPage02(Context context) {
        super(context);
        init(context);
    }

    /**
     * @param context
     * @param attrs
     */
    public JktjPttjPage02(Context context, AttributeSet attrs) {
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
        inflater.inflate(R.layout.fragment_health_pttj_2_layout, this);
        
        loadPage(context, this);
    }

    public void loadPage(Context context, ViewGroup viewGroup) {
//        mGenderSpinner = (Spinner) viewGroup.findViewById(R.id.gender_spinner);
//        mBirthdayText = (CalendarText) viewGroup.findViewById(R.id.birthday_text);
//        mCompanyEdit = (EditText) viewGroup.findViewById(R.id.company_edit);
//        mTelEdit = (EditText) viewGroup.findViewById(R.id.tel_edit);
//        mContactNameEdit = (EditText) viewGroup.findViewById(R.id.contact_name_edit);
//        mContactTelEdit = (EditText) viewGroup.findViewById(R.id.contact_tel_edit);
//        mLiveSpinner = (Spinner) viewGroup.findViewById(R.id.live_spinner);
//        mEthnicSpinner = (Spinner) viewGroup.findViewById(R.id.ethnic_spinner);
//        mMarriageSpinner = (Spinner) viewGroup.findViewById(R.id.marriage_spinner);
//        mEducbgSpinner = (Spinner) viewGroup.findViewById(R.id.educbg_spinner);
//        mRhSpinner = (Spinner) viewGroup.findViewById(R.id.rh_spinner);
//        mJobEdit = (EditText) viewGroup.findViewById(R.id.job_edit);
//        
//        /**
//         * 所有下拉框赋值
//         */
//        ArrayAdapter<String> adapter = null;
//        adapter = new ArrayAdapter<String>(context,
//                R.layout.simple_spinner_item, context.getResources().getStringArray(
//                        R.array.gender_conditions));
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        mGenderSpinner.setAdapter(adapter);
//        adapter = new ArrayAdapter<String>(context,
//                R.layout.simple_spinner_item, context.getResources().getStringArray(
//                        R.array.live_conditions));
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        mLiveSpinner.setAdapter(adapter);
//        adapter = new ArrayAdapter<String>(context,
//                R.layout.simple_spinner_item, context.getResources().getStringArray(
//                        R.array.ethnic_conditions));
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        mEthnicSpinner.setAdapter(adapter);
//        adapter = new ArrayAdapter<String>(context,
//                R.layout.simple_spinner_item, context.getResources().getStringArray(
//                        R.array.marriage_conditions));
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        mMarriageSpinner.setAdapter(adapter);
//        adapter = new ArrayAdapter<String>(context,
//                R.layout.simple_spinner_item, context.getResources().getStringArray(
//                        R.array.educbg_conditions));
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        mEducbgSpinner.setAdapter(adapter);
//        adapter = new ArrayAdapter<String>(context,
//                R.layout.simple_spinner_item, context.getResources().getStringArray(
//                        R.array.rh_conditions));
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        mRhSpinner.setAdapter(adapter);

		twEditText		= (EditText) findViewById(R.id.twEditText		);
		mlEditText		= (EditText) findViewById(R.id.mlEditText		);
		hxplEditText	= (EditText) findViewById(R.id.hxplEditText	);
		zcxy01EditText	= (EditText) findViewById(R.id.zcxy01EditText);
		zcxy02EditText	= (EditText) findViewById(R.id.zcxy02EditText);

		sgEditText		= (EditText) findViewById(R.id.sgEditText	);
		bmiEditText		= (EditText) findViewById(R.id.bmiEditText);

		ycxy01EditText = (EditText) findViewById(R.id.ycxy01EditText );
		ycxy02EditText = (EditText) findViewById(R.id.ycxy02EditText );

		tzEditText = (EditText) findViewById(R.id.tzEditText);
		ywEditText = (EditText) findViewById(R.id.ywEditText);

		jkzkSpinnerUtil = (SpinnerUtil) findViewById(R.id.jkzkSpinnerUtil);
		shzlSpinnerUtil = (SpinnerUtil) findViewById(R.id.shzlSpinnerUtil);

		rzgn01RadioButton = (RadioButton) findViewById(R.id.rzgn01RadioButton);
		rzgn02RadioButton = (RadioButton) findViewById(R.id.rzgn02RadioButton);

		jyzlztjcEditText = (EditText) findViewById(R.id.jyzlztjcEditText);

		qgzt01RadioButton = (RadioButton) findViewById(R.id.qgzt01RadioButton);
		qgzt02RadioButton = (RadioButton) findViewById(R.id.qgzt02RadioButton);
		jyyyztjcEditText = (EditText) findViewById(R.id.jyyyztjcEditText);

		dlplSpinnerUtil = (SpinnerUtil) findViewById(R.id.dlplSpinnerUtil);
		mcdlsjEditText = (EditText) findViewById(R.id.mcdlsjEditText);
		jcdlsjEditText = (EditText) findViewById(R.id.jcdlsjEditText);

		// 根据身高体重得出BMI和结论
        DecimalsTextWatcher tzzsTextWatcher = new DecimalsTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                super.afterTextChanged(s);
                
                if(s.length()>2)
                {
                	int pos = s.length() -1 ;
                	
                	char c = s.charAt(pos-2);
                	
                	if(c=='.')
                	{
                		s.delete(pos, pos+1);
                	}
                }
                
                String sg = sgEditText.getText().toString();
                String tz = tzEditText.getText().toString();
                try {
                    if (StringUtil.isEmptyString(sg) || StringUtil.isEmptyString(tz)) {
                        bmiEditText.setText("");
                        return;
                    }
                    try {
                        double dsg = Double.parseDouble(sg)/100.0;
                        double dtz = Double.parseDouble(tz);
                        
                        double bmi = TestResultUtil.getBmiResult(dsg, dtz);
                        bmiEditText.setText(StringUtil.formatDecimal(bmi));        
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }

            }
        };

        sgEditText.addTextChangedListener(tzzsTextWatcher);
        tzEditText.addTextChangedListener(tzzsTextWatcher);
    }
}
