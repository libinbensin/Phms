/* Cking Inc. (C) 2012. All rights reserved.
 *
 * JkjyMbzhPage01.java
 * classes : com.cking.phss.page.JkjyMbzhPage01
 * @author zhaoyupeng
 * V 1.0.0
 * Create at 2012-9-24 上午7:22:22
 */
package com.cking.phss.page;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.cking.phss.R;
import com.cking.phss.bean.Jkjy_Mbmc;
import com.cking.phss.util.TispToastFactory;
import com.cking.pinyin.SearchAdapter;

/**
 * com.cking.phss.page.JkjyMbzhPage01
 * 
 * @author zhaoyupeng <br/>
 *         create at 2012-9-24 上午7:22:22
 */
public class JkjyMbzhPage01 extends LinearLayout {
    private Context mContext = null;
    private Toast mToast = null;
    private WebView mJkjyMbzsDetailWebView = null;
    private String mCurrentMbmc;
    private AutoCompleteTextView mMdmcEdit;

    public JkjyMbzhPage01(Context context) {
        super(context);
        init(context);
    }

    public JkjyMbzhPage01(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private static final String TAG = "JkjyMbzhPage01";

    private void init(Context context) {
        mContext = context;
        mToast = TispToastFactory.getToast(context, "");
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.fragment_jkjy_06_layout, this);
        mJkjyMbzsDetailWebView = (WebView) findViewById(R.id.jkjy_mbzs_detail);

        mCurrentMbmc = Jkjy_Mbmc.MDMC[0];
        mMdmcEdit = (AutoCompleteTextView) findViewById(R.id.jkjy_ypmc_edit);
        mMdmcEdit.setText(mCurrentMbmc);
        setCurrentMbmc(mCurrentMbmc);
        SearchAdapter<String> adapter = new SearchAdapter<String>(context,
                android.R.layout.simple_dropdown_item_1line, Jkjy_Mbmc.MDMC,SearchAdapter.ALL);// 配置Adaptor
        mMdmcEdit.setAdapter(adapter);
        mMdmcEdit.setOnFocusChangeListener(new OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    mMdmcEdit.setText("");
                }
            }
        });
        mMdmcEdit.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mCurrentMbmc = (String) parent.getAdapter().getItem(position);
                setCurrentMbmc(mCurrentMbmc);
            }
        });
    }

    private void setCurrentMbmc(String currentMbmc) {
        mCurrentMbmc = currentMbmc;
        mJkjyMbzsDetailWebView.loadUrl("file:///mnt/sdcard/phms/res/jkjy/"+currentMbmc+".html");
        
    }

}
