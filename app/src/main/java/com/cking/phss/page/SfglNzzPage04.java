/* Xinhuaxing Inc. (C) 2014. All rights reserved.
 *
 * SfglNzzPage01.java
 * classes : com.cking.phss.page.SfglNzzPage01
 * @author Wation.Haliyoo
 * V 1.0.0
 * Create at 2014-8-5 上午11:15:56
 */
package com.cking.phss.page;

import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.cking.phss.R;
import com.cking.phss.bean.IBean;
import com.cking.phss.util.TispToastFactory;
import com.cking.phss.widget.ListView;

/**
 * com.cking.phss.page.SfglNzzPage01
 * @author Wation.Haliyoo <br/>
 * create at 2014-8-5 上午11:15:56
 */
public class SfglNzzPage04 extends MyPage2 {
    private static final String TAG = "SfglNzzPage04";
    private Toast mToast;
    private Map<String, IBean> beanMap;

    ImageView xzImageView;
    ListView yyqkListView;

    /**
     * @param context
     * @param beanMap
     */
    public SfglNzzPage04(Context context, Map<String, IBean> beanMap) {
        super(context);
        this.beanMap = beanMap;
        // init(context);
    }

    protected void init(Context context) {
        mToast = TispToastFactory.getToast(context, "");
        LayoutInflater.from(context).inflate(R.layout.fragment_sfgl_nzz_04_layout, this);

        loadPage(context, this);
    }

    public void loadPage(Context context, ViewGroup viewGroup) {
        xzImageView = (ImageView) findViewById(R.id.xzImageView);

        yyqkListView = (ListView) findViewById(R.id.yyqkListView);
        yyqkListView.setAddButton(xzImageView);
    }
}
