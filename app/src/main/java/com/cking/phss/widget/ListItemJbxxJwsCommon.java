/* Cking Inc. (C) 2012. All rights reserved.
 *
 * ListItemJbxxCommon.java
 * classes : com.cking.phss.view.ListItemJbxxCommon
 * @author Wation Haliyoo
 * V 1.0.0
 * Create at 2012-9-18 下午03:02:15
 */
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
 * 列表项，在基本信息中通用
 * com.cking.phss.view.ListItemJbxxCommon
 * @author Wation Haliyoo <br/>
 * create at 2012-9-18 下午03:02:15
 */
public /*abstract*/ class ListItemJbxxJwsCommon extends RelativeLayout {
    private static final String TAG = "ListItemJbxxJwsCommon";

    private TextView mIndexText = null;
    private TextView mNameText = null;
    private TextView mDateNameText = null;
    private TextView mDateText = null;//确诊时间
    private String icd10_id="";
    private String icd10_name="";
    private String happenDate="";//发病时间
    private String ms="";//描述
    private String disSn="";
    private int zljgId=1;
    View[] needResetViews = null;
    /**
     * @param context
     */
    public ListItemJbxxJwsCommon(Context context) {
        super(context);
        
        init(context);
    }

    /**
     * @param context
     * @param attrs
     */
    public ListItemJbxxJwsCommon(Context context, AttributeSet attrs) {
        super(context, attrs);
        
        init(context);
    }

    /**
     * @param context
     */
    private void init(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.item_jws_layout, this);

//        mIndexText = (TextView) findViewById(R.id.index_text);
        mNameText = (TextView) findViewById(R.id.mcTextView);
        mDateNameText = (TextView) findViewById(R.id.rqTextView);
        mDateText = (TextView) findViewById(R.id.rqTextView);
        LinearLayout czLinearLayout = (LinearLayout) findViewById(R.id.czLinearLayout);
        needResetViews = new View[] {
                mNameText, mDateText, czLinearLayout
        };
    }

    public void setIndex(int index) {
//        mIndexText.setText(index + ".");
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

    public void setName(String name) {
        mNameText.setText(name);
    }

    public String getName() {
        return mNameText.getText().toString();
    }

    public void setDateName(String dateName) {
        mDateNameText.setText(dateName);
    }

    public String getDateName() {
        return mDateNameText.getText().toString();
    }
    
    public void setDate(String date) {
        mDateText.setText(date);
    }
    
    public String getDate() {
       return mDateText.getText().toString();
    }

	public String getIcd10_id() {
		return icd10_id;
	}

	public void setIcd10_id(String icd10_id) {
		this.icd10_id = icd10_id;
	}

	public String getIcd10_name() {
		return icd10_name;
	}

	public void setIcd10_name(String icd10_name) {
        mNameText.setText(icd10_name);
		this.icd10_name = icd10_name;
	}

	public String getHappenDate() {
		return happenDate;
	}

	public void setHappenDate(String happenDate) {
		this.happenDate = happenDate;
	}

	public int getZljgId() {
		return zljgId;
	}

	public void setZljgId(int zljgId) {
		this.zljgId = zljgId;
	}

    public String getDisSn() {
        return disSn;
    }

    public void setDisSn(String disSn) {
        this.disSn = disSn;
    }

    public String getMs() {
        return ms;
    }

    public void setMs(String ms) {
        this.ms = ms;
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
