/* Cking Inc. (C) 2012. All rights reserved.
 *
 * MainActivity.java
 * classes : com.cking.phss.activity.MainActivity
 * @author Wation Haliyoo
 * V 1.0.0
 * Create at 2012-9-15 上午10:57:55
 */
package com.cking.phss.test;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cking.phss.R;
import com.cking.phss.activity.BaseActivity;
import com.cking.phss.view.JbxxBodyView;
import com.cking.phss.view.JktjBodyView;
import com.cking.phss.view.SfglBodyView;

/**
 * com.cking.phss.activity.MainActivity
 * @author Wation Haliyoo <br/>
 * create at 2012-9-15 上午10:57:55
 */
public class Test_MainActivity extends BaseActivity {
    private static final String TAG = "MainActivity";
    
    private TextView mTitleText = null;
    private RadioGroup mLeftRadios = null;
    private ImageButton mSettingBtn = null;
    private RelativeLayout mBodyLayout = null;
    private static ProgressBar sGlobalProgBar = null;
    
    private static int sLeftRadioId = 0; 
    
    /**
     * 基本信息
     */
    private JbxxBodyView mJbxxBodyView = null;
    /**
     * 健康体检
     */
    private JktjBodyView mJktjBodyView = null;
    /**
     * 随访管理
     */
    private SfglBodyView mSfglBodyView =null;
    /* (non-Javadoc)
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        //sLeftRadioId = R.id.radio_jbxx;
        
        mTitleText = (TextView) findViewById(R.id.title_text);
        mLeftRadios = (RadioGroup) findViewById(R.id.main_radio);
        mSettingBtn = (ImageButton) findViewById(R.id.setting_btn);
        mBodyLayout = (RelativeLayout) findViewById(R.id.body_layout);
        sGlobalProgBar = (ProgressBar) findViewById(R.id.gloab_progressbar);
        
        mJbxxBodyView = new JbxxBodyView(this);
        mJktjBodyView = new JktjBodyView(this);
        mSfglBodyView = new SfglBodyView(this);
        
        mLeftRadios.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
                Test_MainActivity.sLeftRadioId = checkedId;
                
                switch (checkedId) {
                case R.id.radio_jbxx:
                    mTitleText.setText(R.string.title_jbxx);
                    mBodyLayout.removeAllViews();
                    mBodyLayout.addView(mJbxxBodyView);
                    break;

                case R.id.radio_jktj:
                    mTitleText.setText(R.string.title_jktj);
                    mBodyLayout.removeAllViews();
                    mBodyLayout.addView(mJktjBodyView);
                    break;

                case R.id.radio_sfgl:
                    mTitleText.setText(R.string.title_sfgl);
                    mBodyLayout.removeAllViews();
                    mBodyLayout.addView(mSfglBodyView);
                    break;

                case R.id.radio_tzps:
                    mTitleText.setText(R.string.title_tzps);
                    mBodyLayout.removeAllViews();
                    break;

                case R.id.radio_xlcs:
                    mTitleText.setText(R.string.title_jbxx);
                    mBodyLayout.removeAllViews();
                    break;

                case R.id.radio_jkjy:
                    mTitleText.setText(R.string.title_jkjy);
                    mBodyLayout.removeAllViews();
                    break;

                case R.id.radio_yyzd:
                    mTitleText.setText(R.string.title_yyzd);
                    mBodyLayout.removeAllViews();
                    break;

                case R.id.radio_sjcx:
                    mTitleText.setText(R.string.title_sjcx);
                    mBodyLayout.removeAllViews();
                    break;
                }
            }
        });
        /**
         * 显示默认项，在布局文件中不能设置此项选中，否则这句话不生效
         */
        ((RadioButton) findViewById(R.id.radio_jbxx)).setChecked(true);
    }
    
    /**
     * 显示/隐藏进度条
     * @param v
     */
    public static void setProgressBarVisibility(int v) {
        sGlobalProgBar.setVisibility(v);
    }
}
