/* Xinhuaxing Inc. (C) 2014. All rights reserved.
 *
 * ItemMenuLeft.java
 * classes : com.cking.phss.widget.ItemMenuLeft
 * @author Administrator
 * V 1.0.0
 * Create at 2014-6-11 下午12:15:21
 */
package com.cking.phss.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cking.phss.R;

/**
 * com.cking.phss.widget.ItemMenuLeft
 * 
 * @author Administrator <br/>
 *         create at 2014-6-11 下午12:15:21
 */
public class MenuRadioButton extends LinearLayout {
    private static final String TAG = "MenuRadioButton";
    private Context mContext = null;

    LinearLayout layout_ps_press;
    LinearLayout layout_ps_nor;
    ImageView imageview_ps_press;
    ImageView imageview_ps_nor;
    TextView textview_ps_press;
    TextView textview_ps_nor;
    ImageView imageview_bk_press;
    ImageView imageview_bk_nor;

    private boolean mChecked = false;

    /**
     * @param context
     * @param attrs
     */
    public MenuRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        mContext = context;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View textEntryView = inflater.inflate(R.layout.item_menu_left, this);
        layout_ps_press = (LinearLayout) textEntryView.findViewById(R.id.layout_ps_press);
        layout_ps_nor = (LinearLayout) textEntryView.findViewById(R.id.layout_ps_nor);
        imageview_ps_press = (ImageView) textEntryView.findViewById(R.id.imageview_ps_press);
        imageview_ps_nor = (ImageView) textEntryView.findViewById(R.id.imageview_ps_nor);
        textview_ps_press = (TextView) textEntryView.findViewById(R.id.textview_ps_press);
        textview_ps_nor = (TextView) textEntryView.findViewById(R.id.textview_ps_nor);
        imageview_bk_press = (ImageView) textEntryView.findViewById(R.id.imageview_bk_press);
        imageview_bk_nor = (ImageView) textEntryView.findViewById(R.id.imageview_bk_nor);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MenuRadioButton);

        mChecked = a.getBoolean(R.styleable.MenuRadioButton_checked, false);
        changeView(mChecked);
        int iconDrawablePress = a.getResourceId(R.styleable.MenuRadioButton_iconDrawablePress, 0);
        if (iconDrawablePress != 0) {
            imageview_ps_press.setImageResource(iconDrawablePress);
        }
        int iconDrawableNormal = a.getResourceId(R.styleable.MenuRadioButton_iconDrawableNormal, 0);
        if (iconDrawableNormal != 0) {
            imageview_ps_nor.setImageResource(iconDrawableNormal);
        }
        int textPress = a.getResourceId(R.styleable.MenuRadioButton_textPress, 0);
        if (textPress == 0) {
            String tipsStr = a.getString(R.styleable.MenuRadioButton_textPress);
            textview_ps_press.setText(tipsStr);
        } else {
            textview_ps_press.setText(textPress);
        }
        int textNormal = a.getResourceId(R.styleable.MenuRadioButton_textNormal, 0);
        if (textNormal == 0) {
            String tipsStr = a.getString(R.styleable.MenuRadioButton_textNormal);
            textview_ps_nor.setText(tipsStr);
        } else {
            textview_ps_nor.setText(textNormal);
        }
        imageview_bk_nor.setVisibility(View.GONE);
        imageview_bk_nor.setVisibility(View.GONE);

        a.recycle();
    }

    private void changeView(boolean checked) {
        if (checked) {
            layout_ps_press.setVisibility(View.VISIBLE);
            layout_ps_nor.setVisibility(View.GONE);
        } else {
            layout_ps_press.setVisibility(View.GONE);
            layout_ps_nor.setVisibility(View.VISIBLE);
        }
    }

    public boolean isChecked() {
        return mChecked;
    }

    /**
     * @param b
     */
    public void setChecked(boolean checked) {
        mChecked = checked;
        changeView(mChecked);
    }

    /**
     * @param b
     */
    public void setBkVisibility(int visiable) {
        imageview_bk_nor.setVisibility(visiable);
        imageview_bk_press.setVisibility(visiable);
    }
}
