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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.cking.phss.R;
import com.cking.phss.bean.BeanID;
import com.cking.phss.bean.HistoryDisease;
import com.cking.phss.bean.IBean;
import com.cking.phss.bean.Jmjkxx;
import com.cking.phss.util.TispToastFactory;
import com.cking.phss.widget.JbxxJwsLayout;
import com.cking.phss.widget.ListItemJbxxJwsCommon;

/**
 * 既往史
 * 
 * @author Administrator <br/>
 *         create at 2012-9-16 上午11:25:10
 */
public class JbxxPage07 extends LinearLayout implements IPage {
	@SuppressWarnings("unused")
	private static final String TAG = "JbxxPage07";
	private Context mContext = null;
	private Jmjkxx mJmjkxx=null;
	
    private Button mJwssxAddBtn = null;// 既往史输血添加按钮
    private JbxxJwsLayout mJwssxLayout = null;

    private Button mJwsssAddBtn = null;// 既往史手术添加按钮
    private JbxxJwsLayout mJwsssLayout = null;
    
    private Button mJwswsAddBtn = null;// 既往史外伤添加按钮
    private JbxxJwsLayout mJwswsLayout = null;

    private Button mJwsjbAddBtn = null;// 既往史疾病添加按钮
    private JbxxJwsLayout mJwsjbLayout = null;
    
	private Toast mToast = null;
	private Map<String, IBean> beanMap=null;
//	private List<Jmjkxx.HistoryDisease> hisDisLists=null;

	/**
	 * @param context
	 */
	public JbxxPage07(Context context,Map<String, IBean> beanMap) {
		super(context);
		this.beanMap=beanMap;
		init(context);
	}

	/**
	 * @param context
	 * @param attrs
	 */
	public JbxxPage07(Context context,Map<String, IBean> beanMap, AttributeSet attrs) {
		super(context, attrs);
		this.beanMap=beanMap;
		init(context);
	}

	/**
	 * @param context
	 */
	private void init(Context context) {
		mContext = context;
		mToast = TispToastFactory.getToast(context, "");
		LayoutInflater inflater = LayoutInflater.from(context);
		inflater.inflate(R.layout.fragment_archives_jws_layout, this);

		loadPage(context, this);
	}

	@Override
	public void setValue() {
		// TODO Auto-generated method stub
        Jmjkxx mJmjkxx = (Jmjkxx) beanMap.get(Jmjkxx.class.getName());
        if (mJmjkxx == null) {
            return;
        }

        List<HistoryDisease> historyDis = mJmjkxx.getHistoryDisease();

        setHistoryDiseaseToView(mJwsjbLayout, historyDis, 0);
        setHistoryDiseaseToView(mJwsssLayout, historyDis, 1);
        setHistoryDiseaseToView(mJwswsLayout, historyDis, 2);
        setHistoryDiseaseToView(mJwssxLayout, historyDis, 3);
    }

    @Override
    public boolean getValue() {
        Jmjkxx mJmjkxx = (Jmjkxx) beanMap.get(Jmjkxx.class.getName());
        List<HistoryDisease> historyDis = mJmjkxx.getHistoryDisease();
        if (historyDis != null) { // 如果有数据则先清空
            historyDis.clear();
        } else { // 如果没有则先创建一个实体
            historyDis = new ArrayList<HistoryDisease>();
            mJmjkxx.setHistoryDisease(historyDis);
        }

        setHistoryDiseaseToBean(historyDis, mJwsjbLayout.mListView, 0);
        setHistoryDiseaseToBean(historyDis, mJwsssLayout.mListView, 1);
        setHistoryDiseaseToBean(historyDis, mJwswsLayout.mListView, 2);
        setHistoryDiseaseToBean(historyDis, mJwssxLayout.mListView, 3);

        return true;
    }

    /**
     * @param mJwssxLayout2
     * @param currentDis
     * @param i
     */
    private void setHistoryDiseaseToView(JbxxJwsLayout layout, List<HistoryDisease> historyDis,
            int id) {
        if (layout == null) {
            return;
        }
        if (historyDis == null) {
            // 清掉所有项
            layout.clear();
            return;
        }
        List<HistoryDisease> currentDis = new ArrayList<HistoryDisease>();
        // 清掉所有项
        currentDis.clear();
        if (historyDis != null && historyDis.size() > 0) {
            for (int i = 0; i < historyDis.size(); i++) {
                HistoryDisease hd = historyDis.get(i);
                if (hd.gethDType() == id) {
                    BeanID icd10 = hd.getiCD10();
                    if (icd10 == null)
                        hd.setiCD10(new BeanID());
                    // if (!hd.disSn.equals("")) {
                        currentDis.add(hd);
                    // }
                }
            }
        }
        Log.i(TAG, "setValue currentDis.size: " + currentDis.size());
        currentDis = layout.dateSort(currentDis);
        for (HistoryDisease hd : currentDis) {
            layout.addItem(hd.getDisease(), hd.getDisSn(), hd.getiCD10().getiD(), hd.getiCD10()
                    .getTagValue(), hd.getDiagnoseDate(), hd.getHappenDate(), hd.gethDReason(), hd
                    .getResultCD());
        }
    }

    /**
     * @param historyDis
     * @param mListView
     * @param i
     */
    private void setHistoryDiseaseToBean(List<HistoryDisease> historyDis,
            List<ListItemJbxxJwsCommon> listView, int id) {
        if (listView != null && listView.size() > 0) {
            for (ListItemJbxxJwsCommon ljc : listView) {
                HistoryDisease hd = new HistoryDisease();
                BeanID icd10 = new BeanID();
                icd10.setiD(ljc.getIcd10_id());
                icd10.setTagValue(ljc.getIcd10_name());
                hd.setiCD10(icd10);
                hd.sethDType(id);
                hd.setDisSn(ljc.getDisSn());
                hd.setDisease(ljc.getName());
                hd.setDiagnoseDate(ljc.getDate());
                hd.setHappenDate(ljc.getHappenDate());
                hd.setResultCD(ljc.getZljgId());
                hd.sethDReason(ljc.getMs());
                historyDis.add(hd);
            }
        }
    }

    public void loadPage(Context context, ViewGroup viewGroup) {
        mJwssxAddBtn = (Button) findViewById(R.id.jwssx_add_button);
        mJwssxLayout = (JbxxJwsLayout) findViewById(R.id.jwssx_layout);
        mJwssxLayout.setHdType(3);//这是输血既往史
        mJwssxLayout.setConfig("输血", View.VISIBLE);
        mJwssxAddBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout titleLinearLayout = (LinearLayout) findViewById(R.id.jwssxTitleLinearLayout);
                mJwssxLayout.addItem(titleLinearLayout);
            }
        });

        mJwsssAddBtn = (Button) findViewById(R.id.jwsss_add_button);
        mJwsssLayout = (JbxxJwsLayout) findViewById(R.id.jwsss_layout);
        mJwsssLayout.setHdType(1);//这是手术既往史
        mJwsssLayout.setConfig("手术", View.VISIBLE);
        mJwsssAddBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout titleLinearLayout = (LinearLayout) findViewById(R.id.jwsssTitleLinearLayout);
                mJwsssLayout.addItem(titleLinearLayout);
            }
        });
        
        mJwswsAddBtn = (Button) findViewById(R.id.jwsws_add_button);
        mJwswsLayout = (JbxxJwsLayout) findViewById(R.id.jwsws_layout);
        mJwswsLayout.setHdType(2);//这是外伤既往史
        mJwswsLayout.setConfig("外伤", View.INVISIBLE);
        mJwswsAddBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout titleLinearLayout = (LinearLayout) findViewById(R.id.jwswsTitleLinearLayout);
                mJwswsLayout.addItem(titleLinearLayout);
            }
        });

        mJwsjbAddBtn = (Button) findViewById(R.id.jwsjb_add_button);
        mJwsjbLayout = (JbxxJwsLayout) findViewById(R.id.jwsjb_layout);
        mJwsjbLayout.setHdType(0);//这是疾病既往史
        mJwsjbLayout.setConfig("疾病", View.INVISIBLE);
        mJwsjbAddBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout titleLinearLayout = (LinearLayout) findViewById(R.id.jwsjbTitleLinearLayout);
                mJwsjbLayout.addItem(titleLinearLayout);
            }
        });
	}

    /*
     * (non-Javadoc)
     * 
     * @see com.cking.phss.page.IPage#clear()
     */
    @Override
    public void clear() {
    }

//	// 向所属的主页面请求bean
//	public void requestBean() {
//		mJmjkxx = new Jmjkxx();
////		Jmjkxx.HistoryDisease historyDisease=new Jmjkxx.HistoryDisease();
//	}
}
