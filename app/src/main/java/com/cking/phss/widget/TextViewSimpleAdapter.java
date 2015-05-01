/* Xinhuaxing Inc. (C) 2014. All rights reserved.
 *
 * TextViewSimpleAdapter.java
 * classes : com.cking.phss.widget.TextViewSimpleAdapter
 * @author Administrator
 * V 1.0.0
 * Create at 2014-7-19 下午5:12:16
 */
package com.cking.phss.widget;

import java.util.List;
import java.util.Map;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.cking.phss.R;

/**
 * com.cking.phss.widget.TextViewSimpleAdapter
 * @author Administrator <br/>
 * create at 2014-7-19 下午5:12:16
 */
public class TextViewSimpleAdapter extends SimpleAdapter {
    private static final String TAG = "TextViewSimpleAdapter";

    /**
     * @param context
     * @param data
     * @param resource
     * @param from
     * @param to
     */
    public TextViewSimpleAdapter(Context context, List<? extends Map<String, ?>> data,
            int resource, String[] from, int[] to) {
        super(context, data, resource, from, to);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View retView = super.getView(position, convertView, parent);
        if (position % 2 == 1) {
            retView.setBackgroundResource(R.color.list_jsh_background_color);
        } else {
            retView.setBackgroundResource(R.color.list_osh_background_color);
        }
        for (int i = 0; i < ((ViewGroup) retView).getChildCount(); i++) {
            View childView = ((ViewGroup) retView).getChildAt(i);
            if (childView instanceof TextView) {
                ((TextView) childView).setTextColor(R.color.black);
            }

        }
        return retView;
    }
}
