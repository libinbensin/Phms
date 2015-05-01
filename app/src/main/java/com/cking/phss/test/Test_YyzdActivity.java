/* Cking Inc. (C) 2012. All rights reserved.
 *
 * Test_YyzdActivity.java
 * classes : com.cking.phss.test.Test_YyzdActivity
 * @author Wation Haliyoo
 * V 1.0.0
 * Create at 2012-9-22 下午06:15:51
 */
package com.cking.phss.test;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import com.cking.phss.R;
import com.cking.phss.util.YyzdReader;
import com.cking.pinyin.SearchAdapter;

/**
 * com.cking.phss.test.Test_YyzdActivity
 * @author Wation Haliyoo <br/>
 * create at 2012-9-22 下午06:15:51
 */
public class Test_YyzdActivity extends Activity implements OnClickListener {
    private static final String TAG = "Test_YyzdActivity";
    private SearchAdapter adapter=null;
    private AutoCompleteTextView search = null;
    
    /* (non-Javadoc)
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_yyzd);
        

        search = (AutoCompleteTextView) findViewById(R.id.yyzd_search);
        search.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                    long id) {
                // TODO Auto-generated method stub
                Log.d(TAG, "onItemClick:"+position);
            }
            
        });
        
        search.setThreshold(1);
        
        String[] folder = YyzdReader.getFolderList(Test_YyzdActivity.this);
        
        adapter = new SearchAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, folder, SearchAdapter.ALL);//速度优先
        search.setAdapter(adapter);//
        
        Button bt1 = (Button) findViewById(R.id.button1);
        Button bt2 = (Button) findViewById(R.id.button2);
        Button bt3 = (Button) findViewById(R.id.button3);
        Button bt4 = (Button) findViewById(R.id.button4);
        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
        bt3.setOnClickListener(this);
        bt4.setOnClickListener(this);
        
    }

    /* (non-Javadoc)
     * @see android.view.View.OnClickListener#onClick(android.view.View)
     */
    @Override
    public void onClick(View v) {
        String text = YyzdReader.getFileContent(Test_YyzdActivity.this, search.getText().toString(), ((Button) v).getText().toString());
        
        TextView tv = (TextView) findViewById(R.id.textView1);
        tv.setText(text);
    }
}
