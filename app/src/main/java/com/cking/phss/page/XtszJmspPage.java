package com.cking.phss.page;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.cking.phss.R;
import com.cking.phss.bean.IBean;
import com.cking.phss.bean.Jktj_kstj;
import com.cking.phss.bean.Jmjbxx;
import com.cking.phss.global.Global;
import com.cking.phss.util.TispToastFactory;

/**
 * 
 * @author tao
 * 
 */
public class XtszJmspPage extends LinearLayout implements IPage {
    private Context mContext = null;
    private Toast mToast = null;
    private Map<String, IBean> beanMap = new HashMap<String, IBean>();

    RadioGroup xzztRadioGroup;
    RadioGroup xzfblRadioGroup;
    private RadioButton xzfbl01RadioButton = null;
    private RadioButton xzfbl02RadioButton = null;

    /**
     * @param context
     */
    public XtszJmspPage(Context context) {
        super(context);
        beanMap.put(Jmjbxx.class.getName(), Global.jmjbxx);
        beanMap.put(Jktj_kstj.class.getName(), new Jktj_kstj());
        init(context);
    }

    /**
     * @param context
     * @param attrs
     */
    public XtszJmspPage(Context context, Map<String, IBean> beanMap, AttributeSet attrs) {
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
        inflater.inflate(R.layout.fragment_xtsz_jmsp_layout, this);

        loadPage(context, this);
    }

    public void loadPage(Context context, ViewGroup viewGroup) {
        xzztRadioGroup = (RadioGroup) findViewById(R.id.xzztRadioGroup);
        xzfblRadioGroup = (RadioGroup) findViewById(R.id.xzfblRadioGroup);
        xzfbl01RadioButton = (RadioButton) findViewById(R.id.xzfbl01RadioButton);
        xzfbl02RadioButton = (RadioButton) findViewById(R.id.xzfbl02RadioButton);

        xzztRadioGroup.setEnabled(false);
        xzfblRadioGroup.setEnabled(false);
        xzfbl01RadioButton.setEnabled(false);
        xzfbl02RadioButton.setEnabled(false);
        DisplayMetrics dm = new DisplayMetrics();
        dm = context.getApplicationContext().getResources().getDisplayMetrics();
        if (dm.widthPixels == 1280) {
            xzfbl01RadioButton.setChecked(true);
        } else {
            xzfbl02RadioButton.setChecked(true);
        }
    }

    @Override
    public void setValue() {
    }

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
