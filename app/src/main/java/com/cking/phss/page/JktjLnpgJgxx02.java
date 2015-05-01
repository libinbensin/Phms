package com.cking.phss.page;

import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cking.phss.R;
import com.cking.phss.bean.IBean;
import com.cking.phss.util.TispToastFactory;

public class JktjLnpgJgxx02 extends LinearLayout {

    private JktjLnpgPage0201 mParent;
    private Context mContext = null;
    private TextView pgjgTextView = null;
    private TextView jktyTextView = null;
    private Button cxcsButton = null;
    private int[] mRate;
    private Map<String, IBean> beanMap = null;
    // 下面表示要存入数据库的两种体质
    private int tz1 = -1;
    private int tz2 = -1;

    private String tzTitle = "";// 体质结果的标题
    private String tzContent = "";// 体质结果的内容
    private Toast mToast=null;

    public JktjLnpgJgxx02(Context context, JktjLnpgPage0201 jktjLnpgPage0201, int[] rate,
            Map<String, IBean> beanMap) {
        super(context);
        this.beanMap = beanMap;
        mParent = jktjLnpgPage0201;
        mRate = rate;
        init(context);
    }

    public void init(Context context) {
        mContext = context;
        mToast=TispToastFactory.getToast(mContext, "");
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.fragment_health_lnpg_zilipg_3_layout, this);
        pgjgTextView = (TextView) findViewById(R.id.pgjgTextView);
        cxcsButton = (Button) findViewById(R.id.cxcsButton);
        jktyTextView = (TextView) findViewById(R.id.jktyTextView);
        cxcsButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mParent.showStartPage();
            }
        });
        // 根据传进来的mRate分数数组，计算体质的结论
        setRate();

    }

    public void setRate() {
        // 0～3分者为可自理； 4～8分者为轻度依赖；9～18分者为中度依赖；19分者为不能自理
        int rate = 0;
        for (int r : mRate) {
            rate += r;
        }
        String pgjgStr = "无老年抑郁";
        if (rate < 15) {
            pgjgStr = "无老年抑郁";
        } else {
            pgjgStr = "老年抑郁可能，转上级医院神经科处理";
        }
        pgjgTextView.setText(pgjgStr);
    }
}