/* Cking Inc. (C) 2012. All rights reserved.
 *
 * JbxxPage01.java
 * classes : com.cking.phss.view.JbxxBodyView
 * @author Administrator
 * V 1.0.0
 * Create at 2012-9-16 上午11:25:10
 */
package com.cking.phss.page;

import java.util.Map;

import net.xinhuaxing.util.ResourcesFactory;
import net.xinhuaxing.util.StringUtil;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.cking.phss.R;
import com.cking.phss.bean.BeanCD;
import com.cking.phss.bean.IBean;
import com.cking.phss.util.AppConfigFactory.AppConfig;
import com.cking.phss.util.CheckBoxGroupUtil;
import com.cking.phss.util.TispToastFactory;

/**
 * 健康体检快速体检第2页 com.cking.phss.view.JktjKstjPage02
 * 
 * @author Administrator <br/>
 *         create at 2012-9-16 上午11:25:10
 */
public class XtszGjszPage02 extends LinearLayout implements IPage {
    @SuppressWarnings("unused")
    private static final String TAG = "JktjKstjPage02";
    private Context mContext = null;
    private Map<String, IBean> beanMap = null;
    /**
     * 第二页控件
     */

    private CheckBoxGroupUtil checkBoxGroup;
    private int[] viewIds = new int[] { R.id.jktj01CheckBox, R.id.jktj02CheckBox,
            R.id.jktj03CheckBox, R.id.jktj04CheckBox, R.id.jktj05CheckBox, R.id.sfgl01CheckBox,
            R.id.sfgl02CheckBox, R.id.sfgl03CheckBox, R.id.sfgl04CheckBox, R.id.sfgl05CheckBox,
            R.id.sfgl06CheckBox, R.id.sfgl07CheckBox, R.id.sfgl08CheckBox, R.id.ycjh01CheckBox,
            R.id.ycjh02CheckBox, R.id.ycjh03CheckBox };

    public ViewGroup mParent;
    private Toast mToast = null;

    /**
     * @param context
     */
    public XtszGjszPage02(Context context, Map<String, IBean> beanMap) {
        super(context);
        this.beanMap = beanMap;
        init(context);
    }

    /**
     * @param context
     * @param attrs
     */
    public XtszGjszPage02(Context context, AttributeSet attrs) {
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
        inflater.inflate(R.layout.fragment_xtsz_gjsz_02_layout, this);

        loadPage(context, this);
    }

    public void loadPage(Context context, ViewGroup viewGroup) {
        String[] ids = ResourcesFactory.listId(mContext, "xtszmksz");
        checkBoxGroup = new CheckBoxGroupUtil(viewIds, viewGroup, ids);
    }

    @Override
    public void setValue() {
        AppConfig appConfig = (AppConfig) beanMap.get(AppConfig.class.getName());
        if (appConfig == null)
            return;

        String mksz = appConfig.getMksz();
        String[] items = mksz.split(",");
        if (items != null) {
            String ids = null;
            for (String item : items) {
                String id = ResourcesFactory.findId(mContext, "xtszmksz", item);
                if (ids == null) {
                    ids = id;
                } else {
                    ids += "," + id;
                }
            }
            if (!StringUtil.isEmptyString(mksz)) {
                checkBoxGroup.setCheckedByBeanCD(new BeanCD(ids, mksz));
            }
        }
    }

    @Override
    public boolean getValue() {
        AppConfig appConfig = (AppConfig) beanMap.get(AppConfig.class.getName());
        if (appConfig == null)
            return false;
        BeanCD bean = checkBoxGroup.getCheckedBeanCD(",");
        appConfig.setMksz(bean.getTagValue());
        return true;
    }

    public void clear(){
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Log.i(TAG, "onDetachedFromWindow");
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.i(TAG, "onAttachedToWindow");
    }
}
