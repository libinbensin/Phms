/* Cking Inc. (C) 2012. All rights reserved.
 *
 * Test_PinyinActivity.java
 * classes : com.cking.phss.test.Test_PinyinActivity
 * @author Administrator
 * V 1.0.0
 * Create at 2012-9-20 下午10:28:58
 */
package com.cking.phss.test;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.SlidingDrawer;

import com.cking.phss.R;
import com.cking.pinyin.SearchAdapter;

/**
 * com.cking.phss.test.Test_PinyinActivity
 * @author Administrator <br/>
 * create at 2012-9-20 下午10:28:58
 */
public class Test_PinyinActivity extends Activity {
    private static final String TAG = "Test_PinyinActivity";
    private AutoCompleteTextView search ;
    private SlidingDrawer mDrawer;
    
    public SearchAdapter adapter=null;//
    //需要读取
    public String[] hanzi = new String[] {
            "长江证券100002","长江证券100001", "农业银行200001","工商银行300001" , 
            "招商银行100001", "建设银行100001", "中国银行100002", "华夏银行500002", 
            "上海银行100010", "浦发银行200009", "张华胜", "阿司匹林", "井冈霉素", "苏打", "连帽衫"
            };
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_pinyin);
        
        initViews();
    }
    
    private void initViews(){
        search = (AutoCompleteTextView) findViewById(R.id.search);
        search.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                    long id) {
                // TODO Auto-generated method stub
                Log.d(TAG, "onItemClick:"+position);
            }
            
        });
        
        search.setThreshold(1);
        
        adapter = new SearchAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, hanzi,SearchAdapter.ALL);//速度优先
        search.setAdapter(adapter);//
        
        mDrawer = (SlidingDrawer) findViewById(R.id.slidingdrawer);
        
    }
}
