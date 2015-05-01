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

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.cking.phss.R;
import com.cking.phss.bean.IBean;
import com.cking.phss.bean.Jmjkxx;
import com.cking.phss.util.TispToastFactory;
import com.cking.phss.widget.JzsItemWidget;

/**
 * 基本信息第6页 com.cking.phss.view.JbxxPage06
 * 
 * @author Administrator <br/>
 *         create at 2012-9-16 上午11:25:10
 */
public class JbxxPage06 extends LinearLayout implements IPage {
	@SuppressWarnings("unused")
	private static final String TAG = "JbxxPage06";
	private Context mContext = null;

	/**
	 * 控件
	 */
	private JzsItemWidget mJzsItemWidget_fq = null;
	private JzsItemWidget mJzsItemWidget_mq = null;
	private JzsItemWidget mJzsItemWidget_xdjm = null;
    private JzsItemWidget mJzsItemWidget_zn = null;

	private Toast mToast = null;
	private Map<String, IBean> beanMap = null;

	/**
	 * @param context
	 */
	public JbxxPage06(Context context, Map<String, IBean> beanMap) {
		super(context);
		this.beanMap = beanMap;
		init(context);
	}

	/**
	 * @param context
	 * @param attrs
	 */
	public JbxxPage06(Context context, Map<String, IBean> beanMap,
			AttributeSet attrs) {
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
		inflater.inflate(R.layout.fragment_archives_jzs_layout, this);

		loadPage(context, this);
	}

	public void loadPage(Context context, ViewGroup viewGroup) {
		mJzsItemWidget_fq = (JzsItemWidget) findViewById(R.id.jzs_fq);
		mJzsItemWidget_mq = (JzsItemWidget) findViewById(R.id.jzs_mq);
		mJzsItemWidget_xdjm = (JzsItemWidget) findViewById(R.id.jzs_xdjm);
        mJzsItemWidget_zn = (JzsItemWidget) findViewById(R.id.jzs_zn);
	}

	@Override
	public void setValue() {
		Jmjkxx mJmjkxx = (Jmjkxx) beanMap.get(Jmjkxx.class.getName());
        if (mJmjkxx == null) {
            return;
        }
		String[] fatherCD = mJmjkxx.getFatherCD().split(",");
		String fatherName = mJmjkxx.getFatherName();
		String[] motherCD = mJmjkxx.getMotherCD().split(",");
		String motherName = mJmjkxx.getMotherName();
		String[] brothersCD = mJmjkxx.getBrotherCD().split(",");
		String brothersName = mJmjkxx.getBrotherName();
        String[] childCD = mJmjkxx.getChildCD().split(",");
        String childName = mJmjkxx.getChildName();

		mJzsItemWidget_fq.setValue(fatherCD);
		mJzsItemWidget_fq.setName(fatherName);
		mJzsItemWidget_mq.setValue(motherCD);
		mJzsItemWidget_mq.setName(motherName);
		mJzsItemWidget_xdjm.setValue(brothersCD);
		mJzsItemWidget_xdjm.setName(brothersName);
        mJzsItemWidget_zn.setValue(childCD);
        mJzsItemWidget_zn.setName(childName);
		
	}

	@Override
	public boolean getValue() {
		Jmjkxx mJmjkxx = (Jmjkxx) beanMap.get(Jmjkxx.class.getName());
		mJmjkxx.setFatherCD(mJzsItemWidget_fq.getValue());
		mJmjkxx.setFatherName(mJzsItemWidget_fq.getName());
		mJmjkxx.setMotherCD(mJzsItemWidget_mq.getValue());
		mJmjkxx.setMotherName(mJzsItemWidget_mq.getName());
		mJmjkxx.setBrotherCD(mJzsItemWidget_xdjm.getValue());
		mJmjkxx.setBrotherName(mJzsItemWidget_xdjm.getName());
        mJmjkxx.setChildCD(mJzsItemWidget_zn.getValue());
        mJmjkxx.setChildName(mJzsItemWidget_zn.getName());
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
