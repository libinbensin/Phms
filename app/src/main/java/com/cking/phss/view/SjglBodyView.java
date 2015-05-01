/* Cking Inc. (C) 2012. All rights reserved.
 *
 * SjcxBodyView.java
 * classes : com.cking.phss.view.SjcxBodyView
 * @author zhaoyupeng
 * V 1.0.0
 * Create at 2012-9-24 上午8:23:00
 */
package com.cking.phss.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.cking.phss.bean.BeanUtil;
import com.cking.phss.bean.IBean;
import com.cking.phss.bean.Jmjbxx;
import com.cking.phss.global.Global;
import com.cking.phss.page.IPage;
import com.cking.phss.page.SjglJmcxPage;
import com.cking.phss.page.SjglgzglPage;
import com.cking.phss.page.SjglscglPage;
import com.cking.phss.util.TispToastFactory;

/**
 * com.cking.phss.view.SjcxBodyView
 * 
 */
public class SjglBodyView extends LinearLayout implements IPage {
    private Toast mToast = null;
    private Context mContext = null;
    private int mNowPageIndex = -1;// 当前的页面的序号，下面的几个子页面序号是从0-5；
    /**
     * 7个子页
     */
    SjglJmcxPage mSjglJmcxPage = null;
    SjglgzglPage mSjglgzglPage = null;
    SjglscglPage mSjglscglPage = null;

    RadioGroup sjglRadioGroup;
    LinearLayout layout_content;

    // beanMap
    private Map<String, IBean> beanMap = new HashMap<String, IBean>();
    //
    private LinearLayout mBodyPager;

    public SjglBodyView(Context context) {
        super(context);
        beanMap.put(Jmjbxx.class.getName(), Global.jmjbxx);
        init(context);
    }

    public SjglBodyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(final Context context) {
        mContext = context;
        mToast = TispToastFactory.getToast(context, "");
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.fragment_sjgl_body_layout, this);

        loadPage(context, this);
    }

    public void loadPage(Context context, ViewGroup viewGroup) {
        sjglRadioGroup = (RadioGroup) findViewById(R.id.sjglRadioGroup);
        layout_content = (LinearLayout) findViewById(R.id.layout_content);
        RadioButton sjgl02RadioButton = (RadioButton) findViewById(R.id.sjgl02RadioButton);
        // if (Global.isLocalLogin) {
        // sjgl02RadioButton.setVisibility(View.GONE);
        // } else {
        // sjgl02RadioButton.setVisibility(View.VISIBLE);
        // }
        
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
                        mSjglJmcxPage = new SjglJmcxPage(mContext, beanMap);
                        layout_content.removeAllViewsInLayout();
                        layout_content.addView(mSjglJmcxPage);
                        break;

                    case 1:
                        mSjglgzglPage = new SjglgzglPage(mContext, beanMap);
                        layout_content.removeAllViewsInLayout();
                        layout_content.addView(mSjglgzglPage);
                        break;
                    case 2:
                        mSjglscglPage = new SjglscglPage(mContext, beanMap);
                        layout_content.removeAllViewsInLayout();
                        layout_content.addView(mSjglscglPage);
                        break;
                }
            }
        });
        sjglRadioGroup.check(R.id.sjgl01RadioButton);
    }

    /* (non-Javadoc)
     * @see com.cking.phss.page.IPage#setValue()
     */
    @Override
    public void setValue() {
    }

    /* (non-Javadoc)
     * @see com.cking.phss.page.IPage#getValue()
     */
    @Override
    public boolean getValue() {
        return false;
    }
    
    public void getBeanFromDB() {
        if (beanMap == null)
            return;
        if (Global.updateJmjbxx) {
            setValue();
            return;
        }
        List<Class<? extends IBean>> listBean = new ArrayList<Class<? extends IBean>>();
        listBean.add(Jmjbxx.class);
        BeanUtil.getInstance().getJbxxFromDb(listBean, new BeanUtil.OnResultFromDb() {
            @Override
            public void onResult(List<IBean> listBean, boolean isSucc) {
                if (listBean == null || listBean.size() < 0)
                    return;
                // if (isSucc) {
                beanMap.put(Jmjbxx.class.getName(), listBean.get(0));
                setValue();
                // }
            }
        });

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
