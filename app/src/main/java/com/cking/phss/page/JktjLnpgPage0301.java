package com.cking.phss.page;

import java.util.Map;

import android.app.Dialog;
import android.content.Context;
import android.database.Cursor;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

import com.cking.phss.R;
import com.cking.phss.bean.IBean;
import com.cking.phss.bean.Jmjbxx;
import com.cking.phss.bean.Lnpg;
import com.cking.phss.sqlite.SqliteField.LnpgField;
import com.cking.phss.sqlite.SqliteOperater;
import com.cking.application.MyApplication;
import com.cking.phss.util.TispToastFactory;

public class JktjLnpgPage0301 extends LinearLayout implements IPage {
    private Context mContext = null;
    private Toast mToast = null;
    private Map<String, IBean> beanMap = null;
    private JktjLnpgTestPage03 mLnpgTestPage03 = null;
    JktjLnpgJgxx03 mJktjLnpgJgxx;
    JktjLnpgPage parent;

    Button ksjcButton;
    Button scjgButton;
    
    public static  int[] rateValues;//保存上次的结果的分数数据，这些数据从数据库中查找出来的

    /**
     * @param context
     * @param jktjLnpgPage
     */
    public JktjLnpgPage0301(Context context, JktjLnpgPage jktjLnpgPage, Map<String, IBean> beanMap) {
        super(context);
        parent = jktjLnpgPage;
        this.beanMap = beanMap;
        init(context);
    }

    /**
     * @param context
     * @param attrs
     */
    public JktjLnpgPage0301(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    /**
     * @param context
     */
    private void init(Context context) {
        mContext = context;
        mToast = TispToastFactory.getToast(context, "");
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.fragment_health_lnpg_zhilipg_1_layout, this);

        loadPage(context, this);
    }

    /**
     * 设置第一界面的默认显示信息
     * 
     * @param context
     * @param viewGroup
     */
    public void loadPage(Context context, ViewGroup viewGroup) {
        ksjcButton = (Button) findViewById(R.id.ksjcButton);
        scjgButton = (Button) findViewById(R.id.scjgButton);
        ksjcButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (MyApplication.getInstance().getSession().getCurrentResident() == null) {
                    mToast.setText("当前没有用户，不能进行检测！");
                    mToast.show();
                    return;
                }
                showLnpgTestPagePrepareDialog(); // 显示对话框
            }
        });

        scjgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (choicePageToShow()) {
                    if (rateValues == null || rateValues.length != Lnpg.ZILIPG_QUESTION.length) {
                        mToast.setText("您之前没有做过智力评估，没有数据!");
                        mToast.show();
                        return;
                    }
                    showLnpgZlpgJgxx(rateValues);
                }
            }
        });
    }

    int preAnswer = 0;
    /**
     * 
     */
    protected void showLnpgTestPagePrepareDialog() {
        final Dialog selectDialog = new Dialog(mContext, R.style.dialog);
        selectDialog.setCancelable(true);
        selectDialog.setContentView(R.layout.dialog_lnpg_zhilipg_whcd_layout);
        ImageView closeImageView = (ImageView) selectDialog.findViewById(R.id.closeImageView);
        RadioGroup rg = (RadioGroup) selectDialog.findViewById(R.id.radio_bar_answer);

        rg.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup arg0, int arg1) {
                RadioButton rb = (RadioButton) arg0.findViewById(arg1);
                int tag = Integer.parseInt((String) rb.getTag());
                preAnswer = tag;
                selectDialog.dismiss();// 隐藏对话框
                showLnpgTestPage();
            }
        });

        closeImageView.setOnClickListener(new android.view.View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                selectDialog.dismiss();// 隐藏对话框
            }
        });
        selectDialog.show();
    }

    private void setView(ViewGroup viewGroup) {
        try {
            removeAllViews();
            addView(viewGroup);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public void showStartPage() {
        parent.showLnpgPage0301();
    }

    public void showLnpgTestPage() {
        // 第三个参数是人物的性别
        mLnpgTestPage03 = new JktjLnpgTestPage03(mContext, this, ((Jmjbxx) beanMap.get(Jmjbxx.class
                .getName())).getSexCD());
        mLnpgTestPage03.initTopics();
        setView(mLnpgTestPage03);
    }

    public void showLnpgZlpgJgxx(int[] rate) {
        mJktjLnpgJgxx = new JktjLnpgJgxx03(mContext, this, preAnswer, rate, beanMap);
        setView(mJktjLnpgJgxx);
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
    /*
     * (non-Javadoc)
     * 
     * @see com.cking.phss.page.IPage#clear()
     */
    @Override
    public void clear() {
    }

    // 选择到底显示哪一个界面，根据从数据库读取的数据来判断
    public boolean choicePageToShow() {
        if (MyApplication.getInstance().getSession().getCurrentResident() == null) {
            mToast.setText("当前没有用户，不能进行检测！");
            mToast.show();
            return false;
        }
        String resident_uuid = MyApplication.getInstance().getSession().getCurrentResident()
                .getResidentUUID();
        Cursor cursor = SqliteOperater.getDbInstance().queryBySql(
                "SELECT * FROM " + LnpgField.TB_NAME + " WHERE " + LnpgField.RESIDENT_UUID + "=\'"
                        + resident_uuid + "\'", null);
        while (cursor.moveToNext()) {
            String valueRate = cursor.getString(cursor.getColumnIndex(LnpgField.ZILIPG));
            rateValues = parseStringToInt(valueRate);
            return true;
        }
        return true;
    }

    // 将"1,2,3"类似形式的字符串 转换为int[]数组
    private int[] parseStringToInt(String valueString) {
        String[] values = valueString.split(",");
        int[] rates = new int[values.length];
        for (int i = 0; i < values.length; i++) {
            rates[i] = Integer.parseInt(values[i]);
        }
        return rates;
    }
}
