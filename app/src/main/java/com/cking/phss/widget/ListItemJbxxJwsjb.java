/* Cking Inc. (C) 2012. All rights reserved.
 *
 * ListItemJbxxJwsjb.java
 * classes : com.cking.phss.view.ListItemJbxx02
 * @author Wation Haliyoo
 * V 1.0.0
 * Create at 2012-9-18 下午03:02:15
 */
package com.cking.phss.widget;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cking.phss.R;

/**
 * com.cking.phss.view.ListItemJbxxJwsjb
 * @author Wation Haliyoo <br/>
 * create at 2012-9-18 下午03:02:15
 */
public /*abstract*/ class ListItemJbxxJwsjb extends RelativeLayout {
    private static final String TAG = "ListItemJbxxJwsjb";

    private TextView mIndexText = null;
    private TextView mNameText = null;
    private TextView mDiagnoseTimeText = null;
    private TextView mTimeText = null;

    /**
     * @param context
     */
    public ListItemJbxxJwsjb(Context context) {
        super(context);
        
        init(context);
    }

    /**
     * @param context
     * @param attrs
     */
    public ListItemJbxxJwsjb(Context context, AttributeSet attrs) {
        super(context, attrs);
        
        init(context);
    }

    /**
     * @param context
     */
    private void init(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.list_item_jbxx_common, this);

        mIndexText = (TextView) findViewById(R.id.index_text);
        mNameText = (TextView) findViewById(R.id.name_text);
        mDiagnoseTimeText = (TextView) findViewById(R.id.diagnose_time_text);
        mTimeText = (TextView) findViewById(R.id.time_text);
    }

    public void setIndex(int index) {
        mIndexText.setText(index + ".");
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

    public void setDate(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        mTimeText.setText(format.format(date));
    }
    
    public Date getDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return format.parse(mTimeText.getText().toString());
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
