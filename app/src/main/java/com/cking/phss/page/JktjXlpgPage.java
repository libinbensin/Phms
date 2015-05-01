package com.cking.phss.page;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.cking.phss.R;
import com.cking.phss.bean.Xlcs;
import com.cking.phss.global.Global;
import com.cking.phss.util.TispToastFactory;

public class JktjXlpgPage extends LinearLayout {
	
	private Toast mToast = null;
	private ViewGroup mPage = null;
	private Context mContext = null;
	private XlcsPage01 mXlcsPage01 = null;
	private JktjXlpgTestPage mXlcsTestPage = null;
	private JktjXlpgLasePage mXlcslasePage = null;
	private JktjXlpgScl90LasePage mXlcsScl90LasePage=null;
	
	public JktjXlpgPage(Context context) {
		super(context);
		init(context);
	}

	/**
	 * @param context
	 * @param attrs
	 */
	public JktjXlpgPage(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	private void init(Context context) {
		mContext = context;
		mToast = TispToastFactory.getToast(context, "");
		LayoutInflater inflater = LayoutInflater.from(context);
		inflater.inflate(R.layout.fragment_health_xlpg_layout, this);
		
		mPage = (ViewGroup)findViewById(R.id.layout_content);
		showXlcsPage01();
	}

	public void setView(ViewGroup viewGroup) {
        try {
            mPage.removeAllViews();
            mPage.addView(viewGroup);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }	
	}

	public void showXlcsPage01() {
		if(mXlcsPage01 == null){
			mXlcsPage01 = new XlcsPage01(mContext,this);
		}
        for (int i = 0; i < Global.xlpgResults.length; i++) {
            if (Global.xlpgResults != null) {
                mXlcsPage01.resultTextViews[i].setText(Global.xlpgResults[i]);
            } else {
                mXlcsPage01.resultTextViews[i].setText("");
            }
        }
		this.setView(mXlcsPage01);
	}
	
	public void showXlcsTestPage(int csid){
		if(mXlcsTestPage == null){
			mXlcsTestPage = new JktjXlpgTestPage(mContext,this,csid);
		}
		mXlcsTestPage.changeTestTopics(csid);
		this.setView(mXlcsTestPage);
	}
	
	public void showXlcslasePage(int rank,int csid){
		if(mXlcslasePage == null){
			mXlcslasePage = new JktjXlpgLasePage(mContext,this,0,Xlcs.YY);
		}
		mXlcslasePage.getJbxxFromDB();//得到并设置居民信息
		mXlcslasePage.changeTopics(rank,csid);
		this.setView(mXlcslasePage);
	}
	
	public void showSCL90XlcslasePage(int[] r,int csid){
        if(mXlcsScl90LasePage == null){
            mXlcsScl90LasePage = new JktjXlpgScl90LasePage(mContext,this,r);
        }
        mXlcsScl90LasePage.getJbxxFromDB();//得到并设置居民信息
        this.setView(mXlcsScl90LasePage);
    }

    /**
     * @param strCHECKINID
     */
    public void upload(String strCHECKINID) {
        mToast.setText("开始上传心理评估数据...");
        mToast.show();
        mXlcsPage01.getJmjbxxFromDb(1);
    }
	
}
