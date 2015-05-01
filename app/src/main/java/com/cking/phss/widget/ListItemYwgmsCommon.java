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
 * 药物过敏史的Item
 * 
 * @author AUS
 * 
 */
public class ListItemYwgmsCommon extends RelativeLayout {
//	private TextView mIndexText = null;// 序号
	 
	private TextView gmszlTextView = null;// 过敏史种类
	private TextView gmyTextView = null;// 过敏史名称
	private TextView fbsjTextView = null;// 发病时间
    private TextView fbyyTextView = null;// 发病时间
    private TextView zlmsTextView = null;// 发病时间
    LinearLayout czLinearLayout;
	private String disSn="";//疾病序号
    private String gmsId = "";
	private String gmyId = "";
	private String zlms = "";
	private String fbyy="";
	View[] needResetViews = null;
	public ListItemYwgmsCommon(Context context) {
		super(context);
		init(context);
	}

	public ListItemYwgmsCommon(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	/**
	 * @param context
	 */
	private void init(Context context) {
		LayoutInflater inflater = LayoutInflater.from(context);
		inflater.inflate(R.layout.item_gms_layout, this);

//		mIndexText = (TextView) findViewById(R.id.index_text);
		gmszlTextView = (TextView) findViewById(R.id.gmszlTextView);// 过敏史种类
		gmyTextView = (TextView) findViewById(R.id.gmyTextView);// 过敏史名称
		fbsjTextView = (TextView) findViewById(R.id.fbsjTextView);// 发病时间
		fbyyTextView = (TextView) findViewById(R.id.fbyyTextView);
        zlmsTextView = (TextView) findViewById(R.id.zlmsTextView);
        czLinearLayout = (LinearLayout) findViewById(R.id.czLinearLayout);
        needResetViews = new View[] {
                gmszlTextView, gmyTextView, fbsjTextView, fbyyTextView, zlmsTextView, czLinearLayout
        };
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

	public void setDate(String date) {
		fbsjTextView.setText(date);
	}

	public String getDate() {
		return fbsjTextView.getText().toString();
	}

    public String getGmsName() {
		return gmszlTextView.getText().toString().trim();
	}

    public void setGmsName(String mGmszl) {
		gmszlTextView.setText(mGmszl);
	}

	public String getmGmyName() {
		return gmyTextView.getText().toString().trim();
	}

	public void setmGmyName(String gmyName) {
		gmyTextView.setText(gmyName);
	}

	public String getmDate() {
		return fbsjTextView.getText().toString().trim();
	}

	public void setmDate(String dateString) {
		fbsjTextView.setText(dateString);
	}

    public String getGmsId() {
        return gmsId;
    }

    public void setGmsId(String gmsId) {
        this.gmsId = gmsId;
    }
	
	public String getGmyId() {
		return gmyId;
	}

	public void setGmyId(String gmyId) {
		this.gmyId = gmyId;
	}

	public String getZlms() {
		return zlmsTextView.getText().toString().trim();
	}

	public void setZlms(String zlms) {
		zlmsTextView.setText(zlms);
	}

	public String getFbyy() {
		return fbyyTextView.getText().toString().trim();
	}

	public void setFbyy(String fbyy) {
		fbyyTextView.setText(fbyy);
	}

    public String getDisSn() {
        return disSn;
    }

    public void setDisSn(String disSn) {
        this.disSn = disSn;
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
