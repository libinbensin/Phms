/* Cking Inc. (C) 2012. All rights reserved.
 *
 * ListItemJbxxJwsjb.java
 * classes : com.cking.phss.view.ListItemJbxx02
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
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cking.phss.R;

/**
 * com.cking.phss.view.ListItemJbxxJwsjb
 * @author Wation Haliyoo <br/>
 * create at 2012-9-18 下午03:02:15
 */
public /*abstract*/ class ListItemSjglGzgl extends RelativeLayout {
    private static final String TAG = "ListItemSjglGzgl";

    CheckBox gxCheckBox;
    TextView xhTextView;
    TextView xmTextView;
    TextView xbTextView;
    TextView xmmcTextView;
    TextView jgTextView;
    TextView zrysTextView;
    TextView wcztTextView;
    TextView xzztTextView;
    TextView wcrqTextView;

    View[] needResetViews = null;
    /**
     * @param context
     */
    public ListItemSjglGzgl(Context context) {
        super(context);
        
        init(context);
    }

    /**
     * @param context
     * @param attrs
     */
    public ListItemSjglGzgl(Context context, AttributeSet attrs) {
        super(context, attrs);
        
        init(context);
    }

    /**
     * @param context
     */
    private void init(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.item_gzgl_layout, this);

        gxCheckBox = (CheckBox) findViewById(R.id.gxCheckBox);
        xhTextView = (TextView) findViewById(R.id.xhTextView);
        xmTextView = (TextView) findViewById(R.id.xmTextView);
        xbTextView = (TextView) findViewById(R.id.xbTextView);
        xmmcTextView = (TextView) findViewById(R.id.xmmcTextView);
        jgTextView = (TextView) findViewById(R.id.xmmcTextView);
        zrysTextView = (TextView) findViewById(R.id.zrysTextView);
        wcztTextView = (TextView) findViewById(R.id.wcztTextView);
        xzztTextView = (TextView) findViewById(R.id.xzztTextView);
        wcrqTextView = (TextView) findViewById(R.id.wcrqTextView);
        needResetViews = new View[] { xhTextView, xmTextView, xbTextView, xmmcTextView,
 jgTextView,
                zrysTextView, wcztTextView, xzztTextView, wcrqTextView };
    }

    public void setIndex(int index) {
        xhTextView.setText(index + "");
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

    /**
     * @return the gxCheckBox
     */
    public boolean isChecked() {
        return gxCheckBox.isChecked();
    }

    /**
     * @param gxCheckBox
     *            the gxCheckBox to set
     */
    public void setChecked(boolean checked) {
        this.gxCheckBox.setChecked(checked);
    }

    /**
     * @return the xmTextView
     */
    public String getXm() {
        return xmTextView.getText().toString();
    }

    /**
     * @param xmTextView
     *            the xmTextView to set
     */
    public void setXm(String xm) {
        this.xmTextView.setText(xm);
    }

    /**
     * @return the xbTextView
     */
    public String getXb() {
        return xbTextView.getText().toString();
    }

    /**
     * @param xbTextView
     *            the xbTextView to set
     */
    public void setXb(String xb) {
        this.xbTextView.setText(xb);
    }

    /**
     * @return the xmmcTextView
     */
    public String getXmmc() {
        return xmmcTextView.getText().toString();
    }

    /**
     * @param xmmcTextView
     *            the xmmcTextView to set
     */
    public void setXmmc(String xmmc) {
        this.xmmcTextView.setText(xmmc);
    }

    /**
     * @return the jgTextView
     */
    public String getJg() {
        return jgTextView.getText().toString();
    }

    /**
     * @param jgTextView
     *            the jgTextView to set
     */
    public void setJg(String jg) {
        this.jgTextView.setText(jg);
    }

    /**
     * @return the zrysTextView
     */
    public String getZrys() {
        return zrysTextView.getText().toString();
    }

    /**
     * @param zrysTextView
     *            the zrysTextView to set
     */
    public void setZrys(String zrys) {
        this.zrysTextView.setText(zrys);
    }

    /**
     * @return the wcztTextView
     */
    public String getWczt() {
        return wcztTextView.getText().toString();
    }

    /**
     * @param wcztTextView
     *            the wcztTextView to set
     */
    public void setWczt(String wczt) {
        this.wcztTextView.setText(wczt);
    }

    /**
     * @return the wcztTextView
     */
    public String getXzzt() {
        return xzztTextView.getText().toString();
    }

    /**
     * @param wcztTextView
     *            the wcztTextView to set
     */
    public void setXzzt(String xzzt) {
        this.xzztTextView.setText(xzzt);
    }

    /**
     * @return the wcrqTextView
     */
    public String getWcrq() {
        return wcrqTextView.getText().toString();
    }

    /**
     * @param wcrqTextView
     *            the wcrqTextView to set
     */
    public void setWcrq(String wcrq) {
        this.wcrqTextView.setText(wcrq);
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
