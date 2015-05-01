/* Cking Inc. (C) 2012. All rights reserved.
 *
 * SjglPage01.java
 * classes : com.cking.phss.page.SjglPage01
 * @author zhaoyupeng
 * V 1.0.0
 * Create at 2012-9-24 上午8:36:56
 */
package com.cking.phss.page;

import java.util.Map;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.cking.phss.R;
import com.cking.phss.bean.IBean;
import com.cking.phss.util.TispToastFactory;

/**
 * com.cking.phss.page.SjglPage01 显示数据查询列表的页面
 */
public class SjglJmcxPage extends LinearLayout {
    private Context mContext = null;
    private Toast mToast = null;
    private Map<String, IBean> beanMap = null;

    private LinearLayout[] linearStyles = new LinearLayout[3];
    private ListView mListView = null;

    RadioGroup sjglRadioGroup;
    LinearLayout jmcxTitleLinearLayout;
    LinearLayout jmcxBodyLinearLayout;
    
    SjglJmcxPage01 mSjglJmcxPage01;
    SjglJmcxPage02 mSjglJmcxPage02;
    
    private SimpleAdapter adapter=null;
    public SjglJmcxPage(Context context, Map<String, IBean> beanMap) {
        super(context);
        this.beanMap = beanMap;
        init(context);
    }

    public SjglJmcxPage(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        mToast = TispToastFactory.getToast(context, "");
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.fragment_sjgl_jmcx_layout, this);

        loadPage(context, this);
    }

    public void loadPage(Context context, ViewGroup viewGroup) {
        sjglRadioGroup = (RadioGroup) findViewById(R.id.sjglRadioGroup);
        jmcxTitleLinearLayout = (LinearLayout) findViewById(R.id.jmcxTitleLinearLayout);
        jmcxBodyLinearLayout = (LinearLayout) findViewById(R.id.jmcxBodyLinearLayout);

        sjglRadioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
                RadioButton rb = (RadioButton) findViewById(checkedId);
                if (!rb.isChecked()) {
                    return;
                }
                int tag = Integer.parseInt((String) rb.getTag());
                switch (tag) {
                    case 0:
                        jmcxTitleLinearLayout.removeAllViews();
                        jmcxBodyLinearLayout.removeAllViews();
                        mSjglJmcxPage01 = new SjglJmcxPage01(mContext, beanMap, jmcxTitleLinearLayout, jmcxBodyLinearLayout);
                        break;

                    case 1:
                        jmcxTitleLinearLayout.removeAllViews();
                        jmcxBodyLinearLayout.removeAllViews();
                        mSjglJmcxPage02 = new SjglJmcxPage02(mContext, beanMap, jmcxTitleLinearLayout, jmcxBodyLinearLayout);
                        break;
                }
            }
        });
        sjglRadioGroup.check(R.id.sjgl01RadioButton);
    }
}
