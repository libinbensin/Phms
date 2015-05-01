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

public class JktjLnpgJgxx03 extends LinearLayout {

    private JktjLnpgPage0301 mParent;
    private Context mContext = null;
    private TextView sjycdTextView = null;
    private TextView lnzlpgdfTextView = null;
    private TextView lnzlpgjgTextView = null;
    private TextView zzsmTextView = null;
    private TextView pjqjTextView = null;
    private TextView thcdTextView = null;
    private Button cxcsButton = null;
    private int[] mRate;
    private Map<String, IBean> beanMap = null;
    // 下面表示要存入数据库的两种体质
    private int tz1 = -1;
    private int tz2 = -1;

    private String tzTitle = "";// 体质结果的标题
    private String tzContent = "";// 体质结果的内容
    private Toast mToast=null;
    private int mPreAnswer;

    public JktjLnpgJgxx03(Context context, JktjLnpgPage0301 jktjLnpgPage0301, int preAnswer,
            int[] rate,
            Map<String, IBean> beanMap) {
        super(context);
        this.beanMap = beanMap;
        mParent = jktjLnpgPage0301;
        mRate = rate;
        mPreAnswer = preAnswer;
        init(context);
    }

    public void init(Context context) {
        mContext = context;
        mToast=TispToastFactory.getToast(mContext, "");
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.fragment_health_lnpg_zhilipg_3_layout, this);
        sjycdTextView = (TextView) findViewById(R.id.sjycdTextView);
        lnzlpgdfTextView = (TextView) findViewById(R.id.lnzlpgdfTextView);
        lnzlpgjgTextView = (TextView) findViewById(R.id.lnzlpgjgTextView);
        zzsmTextView = (TextView) findViewById(R.id.zzsmTextView);
        pjqjTextView = (TextView) findViewById(R.id.pjqjTextView);
        thcdTextView = (TextView) findViewById(R.id.thcdTextView);
        cxcsButton = (Button) findViewById(R.id.cxcsButton);
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
        int rate = 0;
        for (int r : mRate) {
            rate += r;
        }
        sjycdTextView.setText(getsjycd(mPreAnswer));
        lnzlpgdfTextView.setText(rate + "");
        lnzlpgjgTextView.setText(getLnzlpgjg(rate));
        String[] value = getZlpgValue(rate);
        zzsmTextView.setText(value[0]);
        pjqjTextView.setText(value[1]);
        thcdTextView.setText(value[2]);
    }

    /**
     * @param rate
     * @return
     */
    private String[] getZlpgValue(int rate) {
        final String[][] valueSet = {
                { "正常", "-", "成人" },
                { "正常年龄之健忘，与年龄有关之记忆障碍", "-", "成人" },
                { "降低从事复杂工作之能力及社会功能", "-", "年轻之成人" },
                { "计算能力下降，无法从事复杂活动，记忆障碍", "2年", "8 岁-青少年" },
                { "失去选择适当衣服及日常活动之能力，走路缓慢、容易流泪、妄想、躁动不安", "1.5年", "5-7 岁" },
                { "无法念10-9-8-7……，需他人协助穿衣、洗澡及上厕所，大小便失禁", "2.5年", "5-7 岁" },
                { "除叫喊外无语言能力，行为问题减少，增加褥疮、肺炎及四肢挛缩之可能性",
                        "MMSE从23（轻度）→0 约6年，每年约降3-4分，MMSE 到0后可平均再生活2-3年", "4 周-15 个月" } };
        final int[][] rateSpanSet = { { 29, 30 }, { 27, 28 }, { 25, 26 }, { 20, 24 }, { 14, 19 },
                { 5, 13 }, { 0, 4 } };

        int i = 0;
        for (int[] rateSpan : rateSpanSet) {
            if (rate >= rateSpan[0] && rate < rateSpan[1]) {
                return valueSet[i];
            }
            i++;
        }
        return valueSet[0];
    }

    /**
     * @param mPreAnswer2
     * @return
     */
    private String getsjycd(int preAnswer) {
        final String[] whcds = { "文盲（未受教育）", "小学程度（受教育年限≤6年）", "中学（包括中专）程度", "大学（包括大专）程度" };

        return whcds[mPreAnswer];
    }

    /**
     * @param rate
     * @return
     */
    private String getLnzlpgjg(int rate) {
        int lnzlpgjgIndex = 0;
        switch (mPreAnswer) {
            case 0:
                if (rate <= 17) {
                    lnzlpgjgIndex = 0;
                } else {
                    lnzlpgjgIndex = 1;
                }
                break;
            case 1:
                if (rate <= 20) {
                    lnzlpgjgIndex = 0;
                } else {
                    lnzlpgjgIndex = 1;
                }
                break;
            case 2:
                if (rate <= 22) {
                    lnzlpgjgIndex = 0;
                } else {
                    lnzlpgjgIndex = 1;
                }
                break;
            case 3:
                if (rate <= 23) {
                    lnzlpgjgIndex = 0;
                } else {
                    lnzlpgjgIndex = 1;
                }
                break;
        }
        return lnzlpgjgIndex == 0 ? "有老年痴呆可能" : "正常";
    }
}
