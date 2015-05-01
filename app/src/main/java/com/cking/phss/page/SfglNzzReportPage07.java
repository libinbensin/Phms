package com.cking.phss.page;

import java.util.Map;

import net.xinhuaxing.pages.BasePage;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import com.cking.phss.R;
import com.cking.phss.bean.IBean;
import com.cking.phss.util.TispToastFactory;
import com.cking.phss.widget.ListView;

/**
 * 因为高血压报卡和糖尿病报卡一模一样，所以使用同一个页面
 * 
 * @author taowencong
 * 
 */
public class SfglNzzReportPage07 extends BasePage {
    private static final String TAG = "SfglNzzReportPage07";
    
    private Context mContext;
    private Toast mToast;
    private Map<String, IBean> beanMap = null;

    public SfglNzzReportPage07(Context context, Map<String, IBean> beanMap) {
        super(context);
        this.beanMap = beanMap;
        init(context);
    }

    /**
     * @param context
     * @param attrs
     */
    public SfglNzzReportPage07(Context context, AttributeSet attrs) {
        super(context);
        init(context);
    }

    /**
     * @param context
     */
    protected void init(Context context) {
        mContext = context;
        mToast = TispToastFactory.getToast(context, "");
        LayoutInflater.from(context).inflate(R.layout.fragment_sfgl_nzz_bk_07_layout, this);
        loadPage(context, this);
    }

    public void loadPage(Context context, ViewGroup viewGroup) {
        ListView listView1 = (ListView) findViewById(R.id.tnbyyqkListView);
        listView1.setAddButton(findViewById(R.id.xzImageView));
    }
}
