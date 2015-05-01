package com.cking.phss.page;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.cking.phss.R;
import com.cking.phss.bean.BeanUtil;
import com.cking.phss.bean.IBean;
import com.cking.phss.bean.Jmjbxx;
import com.cking.phss.global.Global;
import com.cking.phss.util.TispToastFactory;

/**
 * 
 */
public class JktjTzbsPage extends LinearLayout implements IPage {

    private Toast mToast = null;
	private ViewGroup mTzpsPage = null;
	private Context mContext = null;
	private JktjTzbsPage01 mTzpsPage01 = null;
    private JktjTzpsJgxx mTzpsjgxx = null;
	private JktjTzbsTestPage mTzpsTestPage = null;
	private JktjTzbsTzbg mTzpsTzbg = null;
    private Map<String, IBean> beanMap = new HashMap<String, IBean>();

    
	public JktjTzbsPage(Context context) {
		super(context);
		init(context);
	}

	/**
	 * @param context
	 * @param attrs
	 */
	public JktjTzbsPage(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	private void init(Context context) {
		mContext = context;
		mToast = TispToastFactory.getToast(context, "");
		LayoutInflater inflater = LayoutInflater.from(context);
		inflater.inflate(R.layout.fragment_health_tzbs_layout, this);
        beanMap.put(Jmjbxx.class.getName(), Global.jmjbxx);

		mTzpsPage = (ViewGroup) findViewById(R.id.layout_content);
		showTzpsPage01();
	}

    public void getBeanFromDB() {
        if (beanMap == null)
            return;
        List<Class<? extends IBean>> listBean = new ArrayList<Class<? extends IBean>>();
        listBean.add(Jmjbxx.class);
        BeanUtil.getInstance().getJbxxFromDb(listBean,
                new BeanUtil.OnResultFromDb() {
                    @Override
                    public void onResult(List<IBean> listBean, boolean isSucc) {
                        if(listBean==null||listBean.size()<0)
                            return;
//                        if (isSucc) {
                            beanMap.put(Jmjbxx.class.getName(), listBean.get(0));
                            setValue();
//                        }
                    }
                });

    }
	private void setView(ViewGroup viewGroup) {
	    try {
    		mTzpsPage.removeAllViews();
    		mTzpsPage.addView(viewGroup);
	    } catch (NullPointerException e) {
	        e.printStackTrace();
	    }
	}

	public void showTzpsPage01() {
		if (mTzpsPage01 == null) {
			mTzpsPage01 = new JktjTzbsPage01(mContext, JktjTzbsPage.this, beanMap);
		}
		setView(mTzpsPage01);
	}

	public void showTzpsjgxx(int[] rate) {
        mTzpsjgxx = new JktjTzpsJgxx(mContext, this, rate, beanMap);
		setView(mTzpsjgxx);
	}
	
	
	public void showTzpsTzbg(int rate[]) {
		mTzpsTzbg = new JktjTzbsTzbg(mContext, this, rate);
		setView(mTzpsTzbg);
	}
	
    public void showTzpsTestPage() {
        // 第三个参数是人物的性别
        mTzpsTestPage = new JktjTzbsTestPage(mContext, this, ((Jmjbxx) beanMap.get(Jmjbxx.class
                .getName())).getSexCD());
        mTzpsTestPage.initTopics();
        setView(mTzpsTestPage);
    }

    /* (non-Javadoc)
     * @see android.view.View#onAttachedToWindow()
     */
//    @Override
//    protected void onAttachedToWindow() {
//        showTzpsPage01();
//        super.onAttachedToWindow();
//    }

    /* (non-Javadoc)
     * @see com.cking.phss.page.IPage#setValue()
     */
    @Override
    public void setValue() {
        mTzpsPage01.setValue();
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

    /**
     * @param strCHECKINID
     */
    public void upload(String strCHECKINID) {
        if (strCHECKINID.equals("")) {
            mToast.setText("无法获取体检编号！");
            mToast.show();
            return;
        } else {
            try {
                mTzpsjgxx.saveJgxxToWeb(strCHECKINID);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }
}
