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
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.cking.phss.R;
import com.cking.phss.util.TispToastFactory;

/**
 * 健康体检普通体检体检第9页
 * com.cking.phss.view.JktjPttjPage09
 * @author Administrator <br/>
 * create at 2012-9-16 上午11:25:10
 */
public class JktjPttjPage09 extends LinearLayout {
    @SuppressWarnings("unused")
    private static final String TAG = "JktjPttjPage09";
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
    
    private Toast mToast = null;
    
    /**
     * @param context
     */
    public JktjPttjPage09(Context context) {
        super(context);
        init(context);
    }

    /**
     * @param context
     * @param attrs
     */
    public JktjPttjPage09(Context context, AttributeSet attrs) {
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
        inflater.inflate(R.layout.fragment_health_pttj_9_layout, this);
        
        //loadPage(context, this);
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
    }
}
