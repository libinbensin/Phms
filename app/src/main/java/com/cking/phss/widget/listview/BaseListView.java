/* Cking Inc. (C) 2012. All rights reserved.
 *
 * JbxxJwsjbListView.java
 * classes : com.cking.phss.view.JbxxJwsjbListView
 * @author Wation Haliyoo
 * V 1.0.0
 * Create at 2012-9-18 下午04:24:14
 */
package com.cking.phss.widget.listview;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

/**
 * 基本信息->既往史疾病->ListView
 * com.cking.phss.view.JbxxJwsjbListView
 * @author Wation Haliyoo <br/>
 * create at 2012-9-18 下午04:24:14
 */
public class BaseListView extends ListView {
    private static final String TAG = "BaseListView";
    protected List<BaseListItem> mListView = new ArrayList<BaseListItem>();

    private ListAdapter mListAdapter = new ListAdapter();
    class ListAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return mListView.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return (View)mListView.get(position);
        }
    }

    /**
     * @param context
     */
    public BaseListView(Context context) {
        super(context);
        init(context);
    }

    /**
     * @param context
     * @param attrs
     */
    public BaseListView(Context context, AttributeSet attrs) {
        super(context, attrs);
//        TypedArray a = context.obtainStyledAttributes(attrs,  
//                R.styleable.Jwslb);  
//        int jwslbid = a.getInteger(R.styleable.Jwslb_jwslbid, 0);
        init(context);
    }

    /**
     * @param context
     */
    private void init(Context context) {
        // removeAllViews();
        setAdapter(mListAdapter);
    }
    
    public void clear() {
        mListView.clear();
        mListAdapter.notifyDataSetChanged();
        // removeAllViews();
        // invalidate();
    }

    /**
     * 添加子项
     * 
     * @param string
     */ 
    public void addItem(BaseListItem listItem) {
        try {
            listItem.setIndex(mListView.size() - 1);
            mListView.add(listItem);
            // addView(listItem);
            mListAdapter.notifyDataSetChanged();
            // invalidate();
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 移除子项
     * @param position
     */
    public void removeItem(int position) {
        Log.e(TAG, "position:" + position);
        
        mListView.remove(position);
        // 更新索引
        for (int i = position; i < mListView.size(); i++) {
            BaseListItem listItem = mListView.get(i);
            listItem.setIndex(i + 1);
            listItem.setTag(i);
        }
        mListAdapter.notifyDataSetChanged();
    }
    
    /**
     * 更新子项
     */
    public void updateItem(BaseListItem listItem, int position) {
        mListView.set(position, listItem);
        mListAdapter.notifyDataSetChanged();
    }

    public int getCount() {
        //Log.i(TAG, "getView, mViews.length:" + mViews.length);
        return mListView.size();
    }
}
