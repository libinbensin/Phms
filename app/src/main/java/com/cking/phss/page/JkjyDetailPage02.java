/* Cking Inc. (C) 2012. All rights reserved.
 *
 * JkjyMbzhPage01.java
 * classes : com.cking.phss.page.JkjyMbzhPage01
 * @author zhaoyupeng
 * V 1.0.0
 * Create at 2012-9-24 上午7:22:22
 */
package com.cking.phss.page;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.cking.phss.R;
import com.cking.phss.util.TispToastFactory;

/**
 * com.cking.phss.page.JkjyDetailPage02
 * 
 * @author zhaoyupeng <br/>
 *         create at 2012-9-24 上午7:22:22
 */
public class JkjyDetailPage02 extends LinearLayout {

    private Context mContext = null;
    private Toast mToast = null;
    private WebView mJkjyDetailWebView = null;
    private String mCurrentTitle;

    public JkjyDetailPage02(Context context,String currentTitle) {
        super(context);
        mCurrentTitle = currentTitle;
        init(context);
    }

    public JkjyDetailPage02(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private static final String TAG = "JkjyDetailPage02";
    
    private void init(Context context) {
        mContext = context;
        mToast = TispToastFactory.getToast(context, "");
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.data_jkjy_detail, this);
        mJkjyDetailWebView = (WebView)findViewById(R.id.jkjy_detail);
        //以下代码可直接载入T卡html文件，即mJkjyDetailWebView.loadUrl
//        setDetailContnet(readYzzdFile(mCurrentTitle));
    }
    
//    private String readYzzdFile(String name) {
//		File file = new File(Constant.RES_JKJY_PATH+File.separator,name+".html");
//		Log.e(TAG,file.toString());
//		String str =  FileHelper.getInstance().readFileToString(file);
//		if(str!=null && str.length()>0){
//			return str;
//		}else{
//			return name+"内容待添加";
//		}
//	}
    
    public void setCurrentTitle(String title){
    	mCurrentTitle = title;
//    	setDetailContnet(readYzzdFile(mCurrentTitle));
    	mJkjyDetailWebView.loadUrl("file:///mnt/sdcard/phms/res/jkjy/"+title+".html");
    }
    
    public void setDetailContnet(String content){
    	mJkjyDetailWebView.loadData(content,"text/plant" , "utf-8");
    }
}
