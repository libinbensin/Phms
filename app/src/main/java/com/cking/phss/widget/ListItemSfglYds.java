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
 * 列表项，用于随访管理-胰岛素
 * com.cking.phss.widget.ListItemSfglYds
 * @author Wation Haliyoo <br/>
 * create at 2012-9-18 下午03:02:15
 */
public /*abstract*/ class ListItemSfglYds extends RelativeLayout {
    private static final String TAG = "ListItemSfglYds";

    private TextView mTypeText = null;  // 药物类型
    private TextView mUsageText = null; // 用法
    private TextView mFrequencyText = null;// 频率

	View[] needResetViews = null;
    /**
     * @param context
     */
    public ListItemSfglYds(Context context) {
        super(context);
        
        init(context);
    }

    /**
     * @param context
     * @param attrs
     */
    public ListItemSfglYds(Context context, AttributeSet attrs) {
        super(context, attrs);
        
        init(context);
    }

    /**
     * @param context
     */
    private void init(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
		inflater.inflate(R.layout.item_yds_layout, this);

		mUsageText = (TextView) findViewById(R.id.syjlTextView);
		mFrequencyText = (TextView) findViewById(R.id.syplTextView);
		mTypeText = (TextView) findViewById(R.id.ywzlTextView);
		LinearLayout czLinearLayout = (LinearLayout) findViewById(R.id.czLinearLayout);
		needResetViews = new View[] { mTypeText, mFrequencyText, mUsageText,
				czLinearLayout };
    }

    public void setIndex(int index) {
//      mIndexText.setText(index + ".");
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

    public void setUsage(String usage) {
        mUsageText.setText(usage);
    }

    public String getUsage() {
        return mUsageText.getText().toString();
    }

    public void setFrequency(String frequency) {
        mFrequencyText.setText(frequency);
    }

    public String getFrequency() {
        return mFrequencyText.getText().toString();
    }

    public void setType(String type) {
        mTypeText.setText(type);
    }

    public String getType() {
        return mTypeText.getText().toString();
    }

	/**
	 * @param widths
	 */
	public void setViewByWidths(List<Integer> widths) {
		for (int i = 0; i < needResetViews.length; i++) {
			View childView = needResetViews[i];
			if (childView instanceof TextView) {
				LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) childView
						.getLayoutParams();
				params.width = widths.get(i);
				childView.setLayoutParams(params);
			}

		}
	}
}
