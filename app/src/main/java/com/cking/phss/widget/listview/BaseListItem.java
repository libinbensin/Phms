package com.cking.phss.widget.listview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.cking.phss.R;

/**
 * 家庭成员的Item
 * 
 * @author AUS
 * 
 */
public class BaseListItem extends RelativeLayout {
	public BaseListItem(Context context) {
		super(context);
	}

	public BaseListItem(Context context, AttributeSet attrs) {
		super(context, attrs);
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
}
