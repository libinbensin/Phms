/* Cking Inc. (C) 2012. All rights reserved.
 *
 * JbxxBodyView.java
 * classes : com.cking.phss.view.JbxxBodyView
 * @author Administrator
 * V 1.0.0
 * Create at 2012-9-16 上午11:25:10
 */
package com.cking.phss.page;

import java.io.IOException;
import java.util.HashMap;
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
import com.cking.phss.bean.IBean;
import com.cking.phss.global.Global;
import com.cking.phss.util.AppConfigFactory;
import com.cking.phss.util.AppConfigFactory.AppConfig;
import com.cking.phss.util.TispToastFactory;

/**
 * 高级设置
 * com.cking.phss.view.JbxxBodyView
 * @author Administrator <br/>
 * create at 2012-9-16 上午11:25:10
 */
/**
 * @author Administrator
 * 
 */
public class XtszGjszPage extends LinearLayout implements IPage {
    @SuppressWarnings("unused")
    private static final String TAG = "JktjKstjPage";
    private static final boolean D = true;
    private Context mContext = null;

    private Map<String, IBean> beanMap = new HashMap<String, IBean>();

    private Toast mToast = null;

    public static boolean sHasData = false;

    RadioGroup gjszRadioGroup;
    LinearLayout layout_content;

    /**
     * 高级设置的4个子页
     */
    XtszGjszPage01 mXtszGjszPage01 = null;
    XtszGjszPage02 mXtszGjszPage02 = null;
    XtszGjszPage03 mXtszGjszPage03 = null;
    XtszGjszPage04 mXtszGjszPage04 = null;

    RadioButton gjsz01RadioButton;
    RadioButton gjsz02RadioButton;

    /**
     * @param context
     */
    public XtszGjszPage(Context context) {
        super(context);
        mContext = context;
        beanMap.put(AppConfig.class.getName(), Global.appConfig);
        init(context);
    }

    /**
     * @param context
     * @param attrs
     */
    public XtszGjszPage(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init(context);
    }

    /**
     * @param context
     */
    private void init(final Context context) {
        mToast = TispToastFactory.getToast(context, "");
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.fragment_xtsz_gjsz_layout, this);

        loadPage(context, this);
    }

    /**
     * 设置第一界面的默认显示信息
     * 
     * @param context
     * @param viewGroup
     */
    public void loadPage(Context context, ViewGroup viewGroup) {
        // 添加页面
        gjszRadioGroup = (RadioGroup) findViewById(R.id.gjszRadioGroup);
        gjsz01RadioButton = (RadioButton) findViewById(R.id.gjsz01RadioButton);
        gjsz02RadioButton = (RadioButton) findViewById(R.id.gjsz02RadioButton);
        layout_content = (LinearLayout) findViewById(R.id.layout_content);

        mXtszGjszPage01 = new XtszGjszPage01(mContext, beanMap);
        mXtszGjszPage02 = new XtszGjszPage02(mContext, beanMap);
        mXtszGjszPage03 = new XtszGjszPage03(mContext, beanMap);
        mXtszGjszPage04 = new XtszGjszPage04(mContext, beanMap);
        
        if (Global.isLocalLogin) {
            gjsz01RadioButton.setText("本地用户管理");
            gjsz02RadioButton.setText("模块隐藏设置");
        } else {
            gjsz01RadioButton.setText("用户管理");
            gjsz02RadioButton.setText("模块设置");
        }

        gjszRadioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {

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
                        layout_content.removeAllViewsInLayout();
                        layout_content.addView(mXtszGjszPage01);
                        break;

                    case 1:
                        layout_content.removeAllViewsInLayout();
                        layout_content.addView(mXtszGjszPage02);
                        break;
                    case 2:
                        layout_content.removeAllViewsInLayout();
                        layout_content.addView(mXtszGjszPage03);
                        break;

                    case 3:
                        layout_content.removeAllViewsInLayout();
                        layout_content.addView(mXtszGjszPage04);
                        break;
                }
            }
        });
        gjszRadioGroup.check(R.id.gjsz01RadioButton);
    }

    @Override
    public void setValue() {
        AppConfig appConfig = (AppConfig) beanMap.get(AppConfig.class.getName());
        if (appConfig == null)
            return;
        try {
            AppConfigFactory.readAppConfig(mContext, appConfig);
        } catch (IOException e) {
            e.printStackTrace();
        }

        mXtszGjszPage01.setValue();
        mXtszGjszPage02.setValue();
        mXtszGjszPage03.setValue();
        mXtszGjszPage04.setValue();
    }

    @Override
    public boolean getValue() {
        AppConfig appConfig = (AppConfig) beanMap.get(AppConfig.class.getName());
        if (appConfig == null)
            return false;
        mXtszGjszPage01.getValue();
        mXtszGjszPage02.getValue();
        mXtszGjszPage03.getValue();
        mXtszGjszPage04.getValue();

        try {
            AppConfigFactory.writeAppConfig(mContext, appConfig);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    protected void onAttachedToWindow() {
        setValue();
        super.onAttachedToWindow();
    }

    @Override
    protected void onDetachedFromWindow() {
        getValue();
        super.onDetachedFromWindow();
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
