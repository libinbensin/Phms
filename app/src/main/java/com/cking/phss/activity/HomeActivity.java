/* Xinhuaxing Inc. (C) 2014. All rights reserved.
 *
 * HomeActivity.java
 * classes : com.cking.phss.activity.HomeActivity
 * @author Administrator
 * V 1.0.0
 * Create at 2014-6-11 上午10:17:01
 */
package com.cking.phss.activity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import net.xinhuaxing.eshow.constants.Constants;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.cking.phss.R;
import com.cking.phss.util.ContextUtil;
import com.cking.phss.util.MyApplication;

/**
 * com.cking.phss.activity.HomeActivity
 * @author Administrator <br/>
 * create at 2014-6-11 上午10:17:01
 */
public class HomeActivity extends BaseActivity implements OnClickListener {
    private static final String TAG = "HomeActivity";
    String doctorID;
    String doctorName;
    ImageView logoImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        logoImageView = (ImageView) findViewById(R.id.logoImageView);

        if (ContextUtil.sdCardCanRead()) {
            String externalPath = Environment.getExternalStorageDirectory() + "/phms/image/";
            String logoPath = externalPath + "logo.png";// logo
            Bitmap bitmap;
            try {
                bitmap = BitmapFactory.decodeStream(new FileInputStream(new File(logoPath)));
                logoImageView.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                Log.e(TAG, e.toString());
            }
        }

        Intent intent = getIntent();
        doctorID = intent.getStringExtra("doctorID");
        doctorName = intent.getStringExtra("doctorName");
        Log.i(TAG, "doctorID:" + doctorID + ", doctorName:" + doctorName);
    }

    /* (non-Javadoc)
     * @see android.view.View.OnClickListener#onClick(android.view.View)
     */
    @Override
    public void onClick(View paramView) {
        Intent intent = new Intent();
        
        switch (paramView.getId())
        {
        case R.id.button_jbxx: 
            intent.putExtra("tag", Constants.PAGE_JBXX);
            break;
            
        case R.id.button_jktj:
                if (MyApplication.getInstance().getJktjFirstVisibleIndex() < 0) {
                    return;
                }
            intent.putExtra("tag", Constants.PAGE_JKTJ);
            break;
            
        case R.id.button_sfgl:
                if (MyApplication.getInstance().getSfglFirstVisibleIndex() < 0) {
                    return;
                }
            intent.putExtra("tag", Constants.PAGE_SFGL);
            break;
            
        case R.id.button_ycjh:
            intent.putExtra("tag", Constants.PAGE_YCJH);
            break;
            
        case R.id.button_jkjy:
            intent.putExtra("tag", Constants.PAGE_JKJY);
            break;
            
        case R.id.button_yyzd:
            intent.putExtra("tag", Constants.PAGE_YYZD);
            break;
            
        case R.id.button_sjgl:
            intent.putExtra("tag", Constants.PAGE_SJGL);
            break;
            
        case R.id.button_xtsz:
            intent.putExtra("tag", Constants.PAGE_XTSZ);
            break;
            
        default:
            return;
        }

        intent.putExtra("doctorID", doctorID);
        intent.putExtra("doctorName", doctorName);
        intent.setClass(HomeActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
