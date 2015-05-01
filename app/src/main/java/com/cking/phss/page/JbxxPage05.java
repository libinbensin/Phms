/* Cking Inc. (C) 2012. All rights reserved.
 *
 * JbxxPage01.java
 * classes : com.cking.phss.view.JbxxBodyView
 * @author Administrator
 * V 1.0.0
 * Create at 2012-9-16 上午11:25:10
 */
package com.cking.phss.page;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.cking.phss.R;
import com.cking.phss.bean.BeanID;
import com.cking.phss.bean.HistoryHyper;
import com.cking.phss.bean.IBean;
import com.cking.phss.bean.Jmjkxx;
import com.cking.phss.util.TispToastFactory;
import com.cking.phss.widget.JbxxYwgmsLayout;
import com.cking.phss.widget.ListItemYwgmsCommon;

/**
 * 基本信息第5页 com.cking.phss.view.JbxxPage05
 * 
 * @author Administrator <br/>
 *         create at 2012-9-16 上午11:25:10
 */
public class JbxxPage05 extends LinearLayout implements IPage {
    @SuppressWarnings("unused")
    private static final String TAG = "JbxxPage05";
    private Context mContext = null;

    /**
     * 第5页控件
     */
    private Toast mToast = null;
    private Map<String, IBean> beanMap = null;

    private Button xzButton = null;
    private JbxxYwgmsLayout gmsJbxxYwgmsLayout = null;

    /**
     * @param context
     */
    public JbxxPage05(Context context, Map<String, IBean> beanMap) {
        super(context);
        this.beanMap = beanMap;
        init(context);
    }

    /**
     * @param context
     * @param attrs
     */
    public JbxxPage05(Context context, Map<String, IBean> beanMap, AttributeSet attrs) {
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
        inflater.inflate(R.layout.fragment_archives_gms_layout, this);

        loadPage(context, this);
    }

    public void loadPage(Context context, ViewGroup viewGroup) {
        xzButton = (Button) findViewById(R.id.xzButton);
        gmsJbxxYwgmsLayout = (JbxxYwgmsLayout) findViewById(R.id.gmsJbxxYwgmsLayout);

        xzButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout titleLinearLayout = (LinearLayout) findViewById(R.id.gmsTitleLinearLayout);
                gmsJbxxYwgmsLayout.addItem(titleLinearLayout);
            }
        });
    }

    @Override
    public void setValue() {
        Jmjkxx mJmjkxx = (Jmjkxx) beanMap.get(Jmjkxx.class.getName());
        if (mJmjkxx == null) {
            return;
        }

        List<HistoryHyper> historyHypers = mJmjkxx.getHistoryHyper();
        // 要显示在当前页面的过敏史
        List<HistoryHyper> currentHypers = new ArrayList<HistoryHyper>();
        gmsJbxxYwgmsLayout.clear();// 清掉所有项
        if (historyHypers != null && historyHypers.size() > 0) {
            for (int i = 0; i < historyHypers.size(); i++) {
                HistoryHyper hd = historyHypers.get(i);
                // BeanID hyperSource = hd.getHyperSource();
                // if ((hd.getHyperTypeCD() != 1 && hd.getHyperTypeCD() != 2) ||
                // hyperSource == null
                // || hyperSource.getiD().equals("") ||
                // hyperSource.getTagValue().equals("")
                // ||hd.disSn.equals(""))
                // continue;
                currentHypers.add(hd);
            }
        }
        currentHypers = gmsJbxxYwgmsLayout.dateSort(currentHypers);
        for (HistoryHyper hd : currentHypers) {
            BeanID hyperSource = hd.getHyperSource();
            gmsJbxxYwgmsLayout.addItem(hd.getDisSn(), hd.getHyperTypeCD() + "",
                    hyperSource.getiD(), hyperSource.getTagValue(), hd.getHappenDate(),
            		hd.getCureDes(),hd.getHyperReason());
        }
    }

    @Override
    public boolean getValue() {
        Jmjkxx mJmjkxx = (Jmjkxx) beanMap.get(Jmjkxx.class.getName());

        List<HistoryHyper> historyHypers = mJmjkxx.getHistoryHyper();
        if (historyHypers != null) { // 如果有数据则先清空
            for (int i = 0; i < historyHypers.size(); i++) {
                historyHypers.remove(i);
                i--;
            }
        } else { // 如果没有则先创建一个实体
            historyHypers = new ArrayList<HistoryHyper>();
            mJmjkxx.setHistoryHyper(historyHypers);
        }

        if (gmsJbxxYwgmsLayout.mListView.size() > 0) {
            for (ListItemYwgmsCommon ljc : gmsJbxxYwgmsLayout.mListView) {
                HistoryHyper hd = new HistoryHyper();
                hd.setHyperTypeCD(ljc.getGmsId());
                hd.setDisSn(ljc.getDisSn());
                BeanID hyperSource = new BeanID();
                hyperSource.setiD(ljc.getGmyId());
                hyperSource.setTagValue(ljc.getmGmyName());
                hd.setHyperSource(hyperSource);

                hd.setCureDes(ljc.getZlms());
                hd.setHappenDate(ljc.getDate());
                hd.setHyperReason(ljc.getFbyy());

                historyHypers.add(hd);
            }
        }
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
