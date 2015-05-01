/* Cking Inc. (C) 2012. All rights reserved.
 *
 * ListItemJbxxCommon.java
 * classes : com.cking.phss.view.ListItemJbxxCommon
 * @author Wation Haliyoo
 * V 1.0.0
 * Create at 2012-9-18 下午03:02:15
 */
package com.cking.phss.widget;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
 * 列表项，用于随访管理-辅助检查
 * com.cking.phss.widget.ListItemSfglFzjc
 * @author Wation Haliyoo <br/>
 * create at 2012-9-18 下午03:02:15
 */
public /*abstract*/ class ListItemSfglFzjc extends RelativeLayout {
    private static final String TAG = "ListItemSfglFzjc";

    private TextView mProjectText = null;  // 辅助检查项目
    private TextView mResultText = null; // 辅助检查结果
    private TextView mOperatorText = null;// 检查人
    private TextView mDateText = null;  // 检查日期

    View[] needResetViews = null;
    /**
     * @param context
     */
    public ListItemSfglFzjc(Context context) {
        super(context);
        
        init(context);
    }

    /**
     * @param context
     * @param attrs
     */
    public ListItemSfglFzjc(Context context, AttributeSet attrs) {
        super(context, attrs);
        
        init(context);
    }

    /**
     * @param context
     */
    private void init(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.item_fzjc_layout, this);

        mProjectText = (TextView) findViewById(R.id.fzjcxmTextView);
        mResultText = (TextView) findViewById(R.id.fzjcjgTextView);
        mOperatorText = (TextView) findViewById(R.id.jcrTextView);
        mDateText = (TextView) findViewById(R.id.jcrqTextView);
        LinearLayout czLinearLayout = (LinearLayout) findViewById(R.id.czLinearLayout);
        needResetViews = new View[] { mProjectText, mResultText, mOperatorText, mDateText,
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

    public void setProject(String project) {
        mProjectText.setText(project);
    }
    
    public String getProject() {
        return mProjectText.getText().toString();
    }

    public void setResult(String result) {
        mResultText.setText(result);
    }

    public String getResult() {
        return mResultText.getText().toString();
    }

    public void setOperator(String operator) {
        mOperatorText.setText(operator);
    }

    public String getOperator() {
        return mOperatorText.getText().toString();
    }

    public void setDate(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        mDateText.setText(format.format(date));
    }

    public Date getDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return format.parse(mDateText.getText().toString());
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
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
