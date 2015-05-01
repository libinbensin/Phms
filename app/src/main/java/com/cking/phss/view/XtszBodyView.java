/* Cking Inc. (C) 2012. All rights reserved.
 *
 * JbxxBodyView.java
 * classes : com.cking.phss.view.JbxxBodyView
 * @author Administrator
 * V 1.0.0
 * Create at 2012-9-16 上午11:25:10
 */
package com.cking.phss.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.cking.phss.R;
import com.cking.phss.activity.BaseActivity;
import com.cking.phss.bean.BeanUtil;
import com.cking.phss.bean.IBean;
import com.cking.phss.bean.Jktj_kstj;
import com.cking.phss.bean.Jmjbxx;
import com.cking.phss.global.Global;
import com.cking.phss.page.IPage;
import com.cking.phss.page.XtszBbgxPage;
import com.cking.phss.page.XtszBzPage;
import com.cking.phss.page.XtszGjszPage;
import com.cking.phss.page.XtszGnjsPage;
import com.cking.phss.page.XtszGyPage;
import com.cking.phss.page.XtszJgxxPage;
import com.cking.phss.page.XtszJmspPage;
import com.cking.phss.util.TispToastFactory;
import com.cking.phss.widget.GuidePager;
import com.cking.phss.widget.GuidePager.OnPageChangeListener;

/**
 * 健康体检
 * com.cking.phss.view.JbxxBodyView
 * @author Administrator <br/>
 * create at 2012-9-16 上午11:25:10
 */
/**
 * @author Administrator
 * 
 */
public class XtszBodyView extends LinearLayout implements IPage {
	@SuppressWarnings("unused")
	private static final String TAG = "XtszBodyView";
	private static final boolean D = true;

	private GuidePager mGuidePager = null;
	private Map<String, IBean> beanMap = new HashMap<String, IBean>();
	/**
	 * 全局控件
	 */
	CheckBox etmsCheckBox;
	Button tcButton;

	private Toast mToast = null;
	private Context mContext;

	/**
	 * 机构信息
	 */
	XtszJgxxPage mXtszJgxxPage = null;

	/**
	 * 高级设置
	 */
	XtszGjszPage mXtszGjszPage = null;

    /**
     * 功能介绍
     */
	XtszGnjsPage mXtszGnjsPage = null;
    /**
     * 版本更新
     */
	XtszBbgxPage mXtszBbgxPage = null;
    /**
     * 界面适配
     */
	XtszJmspPage mXtszJmspPage = null;
    /**
     * 帮助
     */
    XtszBzPage mXtszBzPage = null;
    /**
     * 关于
     */
    XtszGyPage mXtszGyPage = null;
    
    private OnPageChangeListener mOnPageChangeListener = null;
    
    public void setOnPageChangeListener(OnPageChangeListener listener) {
        mOnPageChangeListener = listener;
    }
    
	/**
	 * @param context
	 */
	public XtszBodyView(Context context) {
		super(context);
		beanMap.put(Jmjbxx.class.getName(), Global.jmjbxx);
		beanMap.put(Jktj_kstj.class.getName(), new Jktj_kstj());
		init(context);
	}

	/**
	 * @param context
	 * @param attrs
	 */
	public XtszBodyView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	/**
	 * @param context
	 */
	private void init(final Context context) {
	    mContext = context;
		mToast = TispToastFactory.getToast(context, "");
		LayoutInflater inflater = LayoutInflater.from(context);
		inflater.inflate(R.layout.fragment_xtsz_body_layout, this);

		etmsCheckBox = (CheckBox) findViewById(R.id.etmsCheckBox);
		tcButton = (Button) findViewById(R.id.tcButton);
        mGuidePager = (GuidePager) findViewById(R.id.guide_pager);

        mGuidePager.setAdapter(new MyPagerAdapter(context));
        mGuidePager.showPage(0);
        mGuidePager.setOnPageChangeListener(new OnPageChangeListener() { // 当选中某页的回调
            
            @Override
            public void onPageSelected(int index) {
                if (mOnPageChangeListener != null) {
                    mOnPageChangeListener.onPageSelected(index);
                }
            }
        });
        
        tcButton.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View arg0) {
                ((BaseActivity) mContext).exit();
            }
        });
	}

    /**
     * @return
     */
    protected boolean checkHasValidData() {
        return false;
    }

    // viewpager数据绑定
    class MyPagerAdapter extends PagerAdapter {
        List<View> mPageList = new ArrayList<View>();

        public MyPagerAdapter(Context c) {
            super();
            // 添加页面
            mXtszJgxxPage = new XtszJgxxPage(c);
            mPageList.add(mXtszJgxxPage);
            mXtszGjszPage = new XtszGjszPage(c);
            mPageList.add(mXtszGjszPage);
            mXtszGnjsPage = new XtszGnjsPage(c);
            mPageList.add(mXtszGnjsPage);
            mXtszBbgxPage = new XtszBbgxPage(c);
            mPageList.add(mXtszBbgxPage);
            mXtszJmspPage = new XtszJmspPage(c);
            mPageList.add(mXtszJmspPage);
            mXtszBzPage = new XtszBzPage(c);
            mPageList.add(mXtszBzPage);
            mXtszGyPage = new XtszGyPage(c);
            mPageList.add(mXtszGyPage);
        }

        public void destroyItem(View arg0, int arg1, Object arg2) {
            ((ViewPager) arg0).removeView(mPageList.get(arg1));
        }

        @Override
        public void finishUpdate(View arg0) {
        }

        public int getCount() {
            return mPageList.size();
        }

        public Object instantiateItem(View arg0, int arg1) {
            View v = mPageList.get(arg1);
            ((ViewPager) arg0).addView(v);
            return v;
        }

        public boolean isViewFromObject(View arg0, Object arg1) {
            return (arg0 == arg1);
        }

        @Override
        public void restoreState(Parcelable arg0, ClassLoader arg1) {

        }

        @Override
        public Parcelable saveState() {
            return null;
        }

        @Override
        public void startUpdate(View arg0) {
        }
    }
    
	@Override
	public void setValue() {
        Jmjbxx mJmjbxx = (Jmjbxx) beanMap.get(Jmjbxx.class.getName());
        if (mJmjbxx != null) {
        }
        //mJktjKstjPage.setValue();
	}

	@Override
	public boolean getValue() {
		return true;
	}

	public void getBeanFromDB() {
		if (beanMap == null)
			return;
        if (Global.updateJmjbxx) {
            setValue();
            return;
        }
		List<Class<? extends IBean>> listBean = new ArrayList<Class<? extends IBean>>();
		listBean.add(Jmjbxx.class);
		BeanUtil.getInstance().getJbxxFromDb(listBean,
				new BeanUtil.OnResultFromDb() {
					@Override
					public void onResult(List<IBean> listBean, boolean isSucc) {
					    if(listBean==null||listBean.size()<0)
                            return;
//						if (isSucc) {
							beanMap.put(Jmjbxx.class.getName(), listBean.get(0));
							setValue();
//						}
					}
				});

	}

    /**
     * @param index
     */
    public void showItemByIndex(int index) {
        mGuidePager.showPage(index);
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
