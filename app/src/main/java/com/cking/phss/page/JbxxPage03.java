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
import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import com.cking.phss.R;
import com.cking.phss.bean.BeanCD;
import com.cking.phss.bean.Dzqy;
import com.cking.phss.bean.IBean;
import com.cking.phss.bean.Jmjkxx;
import com.cking.phss.global.Global;
import com.cking.phss.util.TispToastFactory;
import com.cking.phss.util.ViewGroupUtil;
import com.cking.phss.widget.CalendarText;
import com.cking.phss.widget.SpinnerUtil;

/**
 * 签约情况
 * 
 * @author Administrator <br/>
 *         create at 2012-9-16 上午11:25:10
 */
public class JbxxPage03 extends LinearLayout implements IPage {
    @SuppressWarnings("unused")
    private static final String TAG = "JbxxPage03";
    private Context mContext = null;

    /**
     * 第三页控件
     */
    RadioGroup qyRadioGroup;
    CalendarText qyrqCalendarText;
    EditText qyddEditText;
    EditText qyysEditText;
    RadioGroup qyzqRadioGroup;
    Button xxButton;
    EditText sqdhEditText;
    Button sqButton;
    SpinnerUtil qylxSpinnerUtil;
    TextView sqztTextView;
    RadioButton qyzq01RadioButton;
    RadioButton qyzq02RadioButton;
    RadioButton qyqk01RadioButton;
    RadioButton qyqk02RadioButton;
    
    ViewGroupUtil viewGroupUtil;

    TextView bddltsTextView;
    LinearLayout mainLinearLayout;

    private Toast mToast = null;
    private Map<String, IBean> beanMap = null;


    /**
     * @param context
     */
    public JbxxPage03(Context context, Map<String, IBean> beanMap) {
        super(context);
        this.beanMap = beanMap;
        init(context);
    }

    /**
     * @param context
     * @param attrs
     */
    public JbxxPage03(Context context, Map<String, IBean> beanMap, AttributeSet attrs) {
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
        inflater.inflate(R.layout.fragment_archives_qyqk_layout, this);

        loadPage(context, this);
    }

    public void loadPage(Context context, ViewGroup viewGroup) {
        qyRadioGroup = (RadioGroup) findViewById(R.id.qyRadiogroup);
        qyrqCalendarText = (CalendarText) findViewById(R.id.qyrqCalendarText);
        qyddEditText = (EditText) findViewById(R.id.qyddEditText);
        qyysEditText = (EditText) findViewById(R.id.qyysEditText);
        qyzqRadioGroup = (RadioGroup) findViewById(R.id.qyzqRadioGroup);
        xxButton = (Button) findViewById(R.id.xxButton);
        qylxSpinnerUtil = (SpinnerUtil) findViewById(R.id.qylxSpinnerUtil);
        sqdhEditText = (EditText) findViewById(R.id.sqdhEditText);
        sqButton = (Button) findViewById(R.id.sqButton);
        sqztTextView = (TextView) findViewById(R.id.sqztTextView);
        qyzq01RadioButton = (RadioButton) findViewById(R.id.qyzq01RadioButton);
        qyzq02RadioButton = (RadioButton) findViewById(R.id.qyzq02RadioButton);
        qyzqRadioGroup.setEnabled(false);
        qyzq01RadioButton.setEnabled(false);
        qyzq02RadioButton.setEnabled(false);
        qyqk01RadioButton = (RadioButton) findViewById(R.id.qyqk01RadioButton);
        qyqk02RadioButton = (RadioButton) findViewById(R.id.qyqk02RadioButton);
        viewGroupUtil = new ViewGroupUtil(this, new int[] { R.id.qyrqCalendarText, R.id.qyddEditText,
                R.id.qyysEditText, R.id.qylxSpinnerUtil, R.id.sqdhEditText, R.id.xxButton, R.id.sqButton });

        bddltsTextView = (TextView) findViewById(R.id.bddltsTextView);
        mainLinearLayout = (LinearLayout) findViewById(R.id.mainLinearLayout);

        if (Global.isLocalLogin) {
            bddltsTextView.setVisibility(View.VISIBLE);
            mainLinearLayout.setVisibility(View.GONE);
        } else {
            bddltsTextView.setVisibility(View.GONE);
            mainLinearLayout.setVisibility(View.VISIBLE);
        }

        qyRadioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            
            @Override
            public void onCheckedChanged(RadioGroup arg0, int arg1) {
                switch (arg1) {
                    case R.id.qyqk01RadioButton:
                        viewGroupUtil.setEnabledAll(true);
                        break;
                    case R.id.qyqk02RadioButton:
                        viewGroupUtil.setEnabledAll(false);
                        break;
                }
            }
        });
        qyqk02RadioButton.setChecked(true);
        
        xxButton.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View arg0) {
                int id = qylxSpinnerUtil.getSelectedValueInt();
                showXyDialog(id);
            }
        });
    }

    /**
     * @param id
     */
    protected void showXyDialog(int id) {
        String url = null;
        switch (id) {
            case 1: // 60岁以上人群
                url = "file:///mnt/sdcard/phms/res/daxx/dzqy/" + "60岁以上人群.html";
                break; 
            case 2: // 儿童保健家庭
                url = "file:///mnt/sdcard/phms/res/daxx/dzqy/" + "儿童保健.html";
                break; 
            case 3: // 孕产妇保健家庭
                url = "file:///mnt/sdcard/phms/res/daxx/dzqy/" + "孕产妇保健.html";
                break; 
        }
        Log.i(TAG, "url:" + url);
        if (url != null) {
            final Dialog selectDialog = new Dialog(mContext, R.style.dialog);  
            selectDialog.setCancelable(false);  
            selectDialog.setContentView(R.layout.dialog_qyqk_xy_layout); 
            WebView xyWebView = (WebView) selectDialog.findViewById(R.id.xyWebView);
            xyWebView.loadUrl(Uri.parse(url).toString());
            Button tyButton = (Button) selectDialog.findViewById(R.id.tyButton);
            Button btyButton = (Button) selectDialog.findViewById(R.id.btyButton);
            tyButton.setOnClickListener(new OnClickListener() {
                
                @Override
                public void onClick(View arg0) {
                    qyzq01RadioButton.setChecked(true);
                    selectDialog.dismiss();
                }
            });
            btyButton.setOnClickListener(new OnClickListener() {
                
                @Override
                public void onClick(View arg0) {
                    qyzq02RadioButton.setChecked(true);
                    selectDialog.dismiss();
                }
            });
            selectDialog.show();
        }
    }

    @Override
    public void setValue() {
        Jmjkxx mJmjkxx = (Jmjkxx) beanMap.get(Jmjkxx.class.getName());
        if (mJmjkxx == null) {
            return;
        }
        Dzqy dzqy = (Dzqy) beanMap.get(Dzqy.class.getName());
        if (dzqy == null) {
            return;
        }

        if (dzqy.SignContract == null || dzqy.SignContract.getcD().equals("0")) {
            qyqk02RadioButton.setChecked(true);
        } else {
            qyqk01RadioButton.setChecked(true);
        }
        qyrqCalendarText.setText(dzqy.SignDate);
        qyddEditText.setText(dzqy.SignPlace);
        qyysEditText.setText(dzqy.SignDoctor);
        qylxSpinnerUtil.setCheckedByBeanCD(dzqy.SignType);
        if (dzqy.SignKnow == null || dzqy.SignKnow.getcD().equals("0")) {
            qyzq02RadioButton.setChecked(true);
        } else {
            qyzq01RadioButton.setChecked(true);
        }
        sqdhEditText.setText(dzqy.SignPhone);
        if (dzqy.SignStatus != null) {
            sqztTextView.setText(dzqy.SignStatus.getTagValue());
        } else {
            sqztTextView.setText("未通过");
        }
    }

    @Override
    public boolean getValue() {
        Jmjkxx mJmjkxx = (Jmjkxx) beanMap.get(Jmjkxx.class.getName());
        if (mJmjkxx == null) {
            return false;
        }
        Dzqy dzqy = (Dzqy) beanMap.get(Dzqy.class.getName());
        if (dzqy == null) {
            return false;
        }

        if (qyqk02RadioButton.isChecked()) {
            dzqy.SignContract = new BeanCD("0", "否");
        } else {
            dzqy.SignContract = new BeanCD("1", "是");
        }
        dzqy.SignDate = qyrqCalendarText.getText().toString();
        dzqy.SignPlace = qyddEditText.getText().toString();
        dzqy.SignDoctor = qyysEditText.getText().toString();
        dzqy.SignType = new BeanCD(qylxSpinnerUtil.getSelectedValue(),
                qylxSpinnerUtil.getSelectedData());
        if (qyzq02RadioButton.isChecked()) {
            dzqy.SignKnow = new BeanCD("0", "不同意");
        } else {
            dzqy.SignKnow = new BeanCD("1", "同意");
        }
        dzqy.SignPhone = sqdhEditText.getText().toString();
        String id = ResourcesFactory.findId(mContext, "tl", sqztTextView.getText().toString());
        dzqy.SignStatus = new BeanCD(id, sqztTextView.getText().toString());

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
