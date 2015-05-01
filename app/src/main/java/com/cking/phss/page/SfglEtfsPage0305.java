/* Xinhuaxing Inc. (C) 2014. All rights reserved.
 *
 * SfglYcfsPage0105.java
 * classes : com.cking.phss.page.SfglYcfsPage0105
 * @author Wation.Haliyoo
 * V 1.0.0
 * Create at 2014-8-5 上午11:15:56
 */
package com.cking.phss.page;

import java.util.Map;

import net.xinhuaxing.util.GlobalUtil;
import net.xinhuaxing.widget.EditText;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import com.cking.phss.R;
import com.cking.phss.bean.IBean;
import com.cking.phss.global.Global;
import com.cking.phss.util.TispToastFactory;

/**
 * com.cking.phss.page.SfglYcfsPage0105
 * 
 * @author Wation.Haliyoo <br/>
 *         create at 2014-8-5 上午11:15:56
 */
public class SfglEtfsPage0305 extends MyPage2 {
    private static final String TAG = "SfglEtfsPage0305";
    private Toast mToast;
    private Map<String, IBean> beanMap;
    private Context mContext;
    EditText bjhhjEditText;
    EditText pjEditText;

    /**
     * @param context
     * @param beanMap
     */
    public SfglEtfsPage0305(Context context, Map<String, IBean> beanMap) {
        super(context);
        this.beanMap = beanMap;
        // init(context);
    }

    protected void init(Context context) {
        mToast = TispToastFactory.getToast(context, "");
        LayoutInflater.from(context).inflate(R.layout.fragment_sfgl_etfs_0305_layout, this);

        loadPage(context, this);
    }

    public void loadPage(Context context, ViewGroup viewGroup) {
        bjhhjEditText = (EditText) findViewById(R.id.bjhhjEditText);
        pjEditText = (EditText) findViewById(R.id.pjEditText);
    }

    @Override
    public void setValue() { if (!hasInit) {return;}
        super.setValue();
        GlobalUtil.getInstance().setWidgetValue((ViewGroup) getContentView(), "脑卒中-个人信息-责任医生",
                Global.doctorName);
    }

    @Override
    public void getValue() { if (!hasInit) {return;}
        super.getValue();
    }
}
