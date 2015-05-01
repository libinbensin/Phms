/* Cking Inc. (C) 2012. All rights reserved.
 *
 * AddressText.java
 * classes : com.cking.phss.widget.AddressText
 * @author Administrator
 * V 1.0.0
 * Create at 2012-9-16 下午12:56:59
 */
package com.cking.phss.dialog;

import java.util.HashMap;
import java.util.Map;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.cking.phss.R;
import com.cking.phss.bean.IBean;
import com.cking.phss.bean.Jmjbxx;
import com.cking.phss.global.Global;
import com.cking.phss.page.IDetailPage;
import com.cking.phss.page.IPage;
import com.cking.phss.page.SjglJmcxDetailPage01;
import com.cking.phss.page.SjglJmcxDetailPage02;
import com.cking.phss.page.SjglJmcxDetailPage03;
import com.cking.phss.page.SjglJmcxDetailPage04;
import com.cking.phss.page.SjglJmcxDetailPage05;
import com.cking.phss.util.TispToastFactory;

/**
 * com.cking.phss.widget.AddressText
 * 
 * @author Administrator <br/>
 *         create at 2012-9-16 下午12:56:59
 */
public class SjglJmcxDetailDialog extends Dialog implements IPage {
    @SuppressWarnings("unused")
    private static final String TAG = "SjglJmcxDetailDialog";
    
    private Context mContext = null;
    private Toast mToast = null;
    
    LinearLayout layout_content;
    ImageView closeImageView;

    private Map<String, IBean> beanMap = new HashMap<String, IBean>();

    /**
     * 居民查询详细
     * 
     * @param context
     */
    SjglJmcxDetailPage01 mJmcxDetailPage01 = null;
    SjglJmcxDetailPage02 mJmcxDetailPage02 = null;
    SjglJmcxDetailPage03 mJmcxDetailPage03 = null;
    SjglJmcxDetailPage04 mJmcxDetailPage04 = null;
    SjglJmcxDetailPage05 mJmcxDetailPage05 = null;

    /**
     * @param context
     */
    public SjglJmcxDetailDialog(Context context) {
        super(context, R.style.dialog);

        init(context);
    }

    /**
     * @param context
     */
    private void init(Context context) {
        mContext = context;
        mToast = TispToastFactory.getToast(context, "");
        setCancelable(true);
        setContentView(R.layout.dialog_big_common_layout);
        beanMap.put(Jmjbxx.class.getName(), Global.jmjbxx);

        loadPage(context);
    }

    public void loadPage(Context context) {
        layout_content = (LinearLayout) findViewById(R.id.layout_content);
        closeImageView = (ImageView) findViewById(R.id.closeImageView);
        closeImageView.setOnClickListener(new android.view.View.OnClickListener() {
            
            @Override
            public void onClick(View arg0) {
                dismiss();//隐藏对话框 
            }
        });

        layout_content = (LinearLayout) findViewById(R.id.layout_content);
        layout_content.removeAllViews();
    }

    public void setPage(int index, String checkSv) {
        layout_content.removeAllViews();
        LinearLayout pageView = null;
        // 添加页面
        if (index == 31) {
            pageView = new SjglJmcxDetailPage01(mContext, beanMap);
        } else if (index == 41) {
            pageView = new SjglJmcxDetailPage02(mContext, beanMap);
        } else if (index == 42) {
            pageView = new SjglJmcxDetailPage03(mContext, beanMap);
        } else if (index == 32) {
            pageView = new SjglJmcxDetailPage04(mContext, beanMap);
        } else if (index == 33) {
            pageView = new SjglJmcxDetailPage05(mContext, beanMap);
        } else {
            return;
        }
        layout_content.addView(pageView);

        IDetailPage page = (IDetailPage) pageView;
        page.require(checkSv);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.cking.phss.page.IPage#setValue()
     */
    @Override
    public void setValue() {
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.cking.phss.page.IPage#getValue()
     */
    @Override
    public boolean getValue() {
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
