/* Cking Inc. (C) 2012. All rights reserved.
 *
 * YyzdYlxPage01.java
 * classes : com.cking.phss.page.YyzdYlxPage01
 * @author zhaoyupeng
 * V 1.0.0
 * Create at 2012-9-24 上午8:09:14
 */
package com.cking.phss.page;

import java.io.File;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cking.phss.R;
import com.cking.phss.bean.Yyzd;
import com.cking.phss.file.FileHelper;
import com.cking.phss.util.Constant;
import com.cking.phss.util.TispToastFactory;

/**
 * com.cking.phss.page.YyzdDetailPage
 * 
 * @author zhaoyupeng <br/>
 *         create at 2012-9-24 上午8:09:14
 */
public class YyzdDetailPage extends LinearLayout {

    private Context mContext = null;
    private Toast mToast = null;
    private TextView mYyzdDetailTextView = null;
    private String mCurrentYpmc = null;
    private String mCurrentYpsm = null;
    
    public YyzdDetailPage(Context context) {
        super(context);
        init(context);
    }

    public YyzdDetailPage(Context context, String currentYpmc, int ypsmId) {
        super(context);
        mCurrentYpmc = currentYpmc;
        mCurrentYpsm = Yyzd.YPSMS[ypsmId-1];
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        mToast = TispToastFactory.getToast(context, "");
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.fragment_yyzd_detail_layout, this);
        mYyzdDetailTextView = (TextView)this.findViewById(R.id.yyzd_detail);
        setDetailContent(readYzzdFile(mCurrentYpmc,mCurrentYpsm));
    }
    
    private String readYzzdFile(String dir, String name) {
		File file = new File(Constant.RES_YYZD_PATH+File.separator + dir,name+".txt");
		String str =  FileHelper.getInstance().readFileToString(file);
		if(str!=null && str.length()>0){
			return str;
		}else{
			return name+"内容待添加";
		}
	}

	public void setDetailContent(String content){
    	mYyzdDetailTextView.setText(content);
    }

    private static final String TAG = "YyzdDetailPage";
}
