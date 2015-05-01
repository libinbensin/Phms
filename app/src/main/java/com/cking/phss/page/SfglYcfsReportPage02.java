package com.cking.phss.page;

import java.util.Map;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import com.cking.phss.R;
import com.cking.phss.bean.IBean;
import com.cking.phss.util.TispToastFactory;

/**
 * 
 * com.cking.phss.page.SfglYcfsReportPage02
 * 
 * @author Wation.Haliyoo <br/>
 *         create at 2014-8-20 下午4:06:17
 */
public class SfglYcfsReportPage02 extends MyPage2 {
    private static final String TAG = "SfglYcfsReportPage02";
    
    private Context mContext;
    private Toast mToast;
    private Map<String, IBean> beanMap = null;

    public SfglYcfsReportPage02(Context context, Map<String, IBean> beanMap) {
        super(context);
        this.beanMap = beanMap;
        // init(context);
    }

    /**
     * @param context
     * @param attrs
     */
    public SfglYcfsReportPage02(Context context, AttributeSet attrs) {
        super(context);
        // init(context);
    }

    /**
     * @param context
     */
    protected void init(Context context) {
        mContext = context;
        mToast = TispToastFactory.getToast(context, "");
        LayoutInflater.from(context).inflate(R.layout.fragment_sfgl_ycfs_bk_02_layout, this);
        loadPage(context, this);
    }

    public void loadPage(Context context, ViewGroup viewGroup) {
    }
}
