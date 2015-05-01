/* Cking Inc. (C) 2012. All rights reserved.
 *
 * ListItemJbxxCommon.java
 * classes : com.cking.phss.view.ListItemJbxxCommon
 * @author Wation Haliyoo
 * V 1.0.0
 * Create at 2012-9-18 下午03:02:15
 */
package com.cking.phss.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cking.phss.R;

/**
 * 列表项，用于系统设置-用户管理
 * com.cking.phss.widget.ListItemXtszYhgl
 * @author Wation Haliyoo <br/>
 * create at 2012-9-18 下午03:02:15
 */
public /*abstract*/ class ListItemXtszYhgl extends RelativeLayout {
    private static final String TAG = "ListItemXtszYhgl";

    private TextView mUserText = null;  // 用户名
    private TextView mNameText = null; // 真实姓名
    private TextView mPasswordText = null;// 密码

    /**
     * @param context
     */
    public ListItemXtszYhgl(Context context) {
        super(context);
        
        init(context);
    }

    /**
     * @param context
     * @param attrs
     */
    public ListItemXtszYhgl(Context context, AttributeSet attrs) {
        super(context, attrs);
        
        init(context);
    }

    /**
     * @param context
     */
    private void init(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.list_item_yhgl, this);

        mUserText = (TextView) findViewById(R.id.user_text);
        mNameText = (TextView) findViewById(R.id.name_text);
        mPasswordText = (TextView) findViewById(R.id.password_text);
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

    public void setUser(String user) {
        mUserText.setText(user);
    }
    
    public String getUser() {
        return mUserText.getText().toString();
    }

    public void setName(String name) {
        mNameText.setText(name);
    }

    public String getName() {
        return mNameText.getText().toString();
    }

    public void setPassword(String password) {
        mPasswordText.setText(password);
    }

    public String getPassword() {
        return mPasswordText.getText().toString();
    }

    public void setTitleItem(boolean titleItem) {
        RelativeLayout titleLayout = (RelativeLayout) findViewById(R.id.opration_title);
        RelativeLayout bodyLayout = (RelativeLayout) findViewById(R.id.opration_body);
        
        if (titleItem) {
            titleLayout.setVisibility(View.VISIBLE);
            bodyLayout.setVisibility(View.GONE);
        } else {
            titleLayout.setVisibility(View.GONE);
            bodyLayout.setVisibility(View.VISIBLE);
        }
    }
}
