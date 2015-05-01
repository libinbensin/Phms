package com.cking.phss.widget;

import java.util.List;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cking.phss.R;

/**
 * 家庭成员的Item
 * 
 * @author AUS
 * 
 */
public class ListItemJbxxJtcy extends RelativeLayout {
//	private TextView mIndexText = null;// 序号
	 
	private TextView yhzgxTextView = null;// 与户主关系
	private TextView xmTextView = null;// 姓名
	private TextView xbTextView = null;// 性别
    private TextView csrqTextView = null;// 出生日期
    private TextView whcdTextView = null;// 文化程度
    private TextView zyTextView = null;// 职业
    private TextView hyzkTextView = null;// 婚姻状况
    private TextView grzkTextView = null;// 个人状况
	private String disSn="";//序号
	private String myId = "";
    private String yhzgx = "";
    private String xm = "";
    private String xb = "";
    private String csrq = "";
    private String whcd = "";
    private String zy = "";
    private String hyzk = "";
	private String grzk = "";
	private String sfzhm="";
    private String bz="";
    private String jzdz="";
    View[] needResetViews = null;
	public ListItemJbxxJtcy(Context context) {
		super(context);
		init(context);
	}

	public ListItemJbxxJtcy(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	/**
	 * @param context
	 */
	private void init(Context context) {
		LayoutInflater inflater = LayoutInflater.from(context);
		inflater.inflate(R.layout.item_jtcy_layout, this);

//		mIndexText = (TextView) findViewById(R.id.index_text);
		yhzgxTextView = (TextView) findViewById(R.id.yhzgxTextView);
		xmTextView = (TextView) findViewById(R.id.xmTextView);
		xbTextView = (TextView) findViewById(R.id.xbTextView);
		csrqTextView = (TextView) findViewById(R.id.csrqTextView);
		whcdTextView = (TextView) findViewById(R.id.whcdTextView);
        zyTextView = (TextView) findViewById(R.id.zyTextView);
        hyzkTextView = (TextView) findViewById(R.id.hyzkTextView);
        grzkTextView = (TextView) findViewById(R.id.grzkTextView);
        LinearLayout czLinearLayout = (LinearLayout) findViewById(R.id.czLinearLayout);
        needResetViews = new View[] { yhzgxTextView, xmTextView, xbTextView, csrqTextView,
                whcdTextView, zyTextView, hyzkTextView, grzkTextView, czLinearLayout };
	}

	public void setIndex(int index) {
//		mIndexText.setText(index + ".");
		setTag(index);
        if (index % 2 == 1) {
            setBackgroundResource(R.color.list_jsh_background_color);
        } else {
            setBackgroundResource(R.color.list_osh_background_color);
        }
	}

	public int getIndex() {
		return (Integer) getTag();
	}

    public String getDisSn() {
        return disSn;
    }

    public void setDisSn(String disSn) {
        this.disSn = disSn;
    }

    public String getMyId() {
        return myId;
    }

    public void setMyId(String myId) {
        this.myId = myId;
    }

    public String getYhzgx() {
        return yhzgx;
    }

    public void setYhzgx(String yhzgx) {
        this.yhzgx = yhzgx;
        yhzgxTextView.setText(yhzgx);
    }

    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
        xmTextView.setText(xm);
    }

    public String getXb() {
        return xb;
    }

    public void setXb(String xb) {
        this.xb = xb;
        xbTextView.setText(xb);
    }

    public String getCsrq() {
        return csrq;
    }

    public void setCsrq(String csrq) {
        this.csrq = csrq;
        csrqTextView.setText(csrq);
    }

    public String getWhcd() {
        return whcd;
    }

    public void setWhcd(String whcd) {
        this.whcd = whcd;
        whcdTextView.setText(whcd);
    }

    public String getZy() {
        return zy;
    }

    public void setZy(String zy) {
        this.zy = zy;
        zyTextView.setText(zy);
    }

    public String getHyzk() {
        return hyzk;
    }

    public void setHyzk(String hyzk) {
        this.hyzk = hyzk;
        hyzkTextView.setText(hyzk);
    }

    public String getGrzk() {
        return grzk;
    }

    public void setGrzk(String grzk) {
        this.grzk = grzk;
        grzkTextView.setText(grzk);
    }

    public String getSfzhm() {
        return sfzhm;
    }

    public void setSfzhm(String sfzhm) {
        this.sfzhm = sfzhm;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public String getJzdz() {
        return jzdz;
    }

    public void setJzdz(String jzdz) {
        this.jzdz = jzdz;
    }

    /**
     * @param widths
     */
    public void setViewByWidths(List<Integer> widths) {
        for (int i = 0; i<needResetViews.length; i++) {
            View childView = needResetViews[i];
            if (childView instanceof TextView) {
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) childView.getLayoutParams(); 
                params.width = widths.get(i);
                childView.setLayoutParams(params);
            }
            
        }
    }

}
