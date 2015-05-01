/* Xinhuaxing Inc. (C) 2014. All rights reserved.
 *
 * ListView.java
 * classes : com.cking.phss.widget.ListView
 * @author Wation.Haliyoo
 * V 1.0.0
 * Create at 2014-8-15 下午5:22:38
 */
package com.cking.phss.widget;

import net.xinhuaxing.dialogs.ListViewDialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;

import com.cking.phss.R;

/**
 * com.cking.phss.widget.ListView
 * @author Wation.Haliyoo <br/>
 * create at 2014-8-15 下午5:22:38
 */
public class ListView extends net.xinhuaxing.widget.ListView {
    private static final String TAG = "ListView";
    private Context mContext;
    int addButtonId = -1;
    int addDialogLayout = -1;
    View addView;

    /**
     * @param context
     * @param attrs
     */
    public ListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ListView);
        addButtonId = a.getResourceId(R.styleable.ListView_addButton, -1);
        addDialogLayout = a.getResourceId(R.styleable.ListView_addDialogLayout, -1);
        a.recycle();

        initView();
    }

    // @Override
    // protected void onFinishInflate() {
    // super.onFinishInflate();
    //
    // initView();
    // }

    public void initView() {
        setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                int position = (Integer) arg0.getTag();
                String tag = (String) arg0.getContentDescription();
                if ("edit".equals(tag)) {
                    showTextListViewDialog(mContext, position);
                } else if ("delete".equals(tag)) {
                    removeItem(position);
                }
            }
        });
    }

    public void showAddDialog() {
        showTextListViewDialog(mContext, -1);
    }

    private void showTextListViewDialog(Context context, final int index) {
        getValue();
        ListViewDialog selectDialog = new ListViewDialog(context, R.style.dialog, this, index,
                addDialogLayout);
        selectDialog.setCancelable(true);
        selectDialog.show();
    }

    /**
     * @param xzImageView
     */
    public void setAddButton(View addView) {
        if (addView != null) {
            addView.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    showTextListViewDialog(mContext, -1);
                }
            });
        }
    }
}
