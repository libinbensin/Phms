/* Xinhuaxing Inc. (C) 2014. All rights reserved.
 *
 * DialogHandler.java
 * classes : com.cking.phss.util.DialogHandler
 * @author Administrator
 * V 1.0.0
 * Create at 2014-6-26 下午9:40:42
 */
package com.cking.phss.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

/**
 * com.cking.phss.util.DialogHandler
 * @author Administrator <br/>
 * create at 2014-6-26 下午9:40:42
 */
public class ProgressDialogUtil {
    private static final String TAG = "ProgressDialogUtil";

    private static ProgressDialog progressDialog = null;
    private static int count = 0; //计数器
    static Handler dialogHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    Bundle bundle = (Bundle) msg.obj;
                    String title = bundle.getString("title");
                    String text = bundle.getString("text");
                    Log.i(TAG, "handleMessage - showProgressDialog");
                    if (count == 0 && progressDialog == null) {
                        Log.i(TAG, "handleMessage - create ProgressDialog");
                        progressDialog = ProgressDialog.show(sContext, title, text, false, false);
                    }
                    progressDialog.setTitle(title);
                    progressDialog.setMessage(text);
                    count++;
                    break;
                case 1:
                    count--;
                    if (count <= 0) {
                        count = 0;
                    }
                    Log.i(TAG, "handleMessage - hideProgressDialog");
                    if (count == 0 && progressDialog != null) {
                        Log.i(TAG, "handleMessage - dismiss ProgressDialog");
                        try {
                            progressDialog.dismiss();
                        } catch (IllegalArgumentException e) {
                            e.printStackTrace();
                        }
                        progressDialog = null;
                    }
                    break;
            }
        }
    };

    private static Context sContext;
    public static void showProgressDialog(Context context, String title, String text) {
        Bundle bundle = new Bundle();
        sContext = context;
        bundle.putString("title", title);
        bundle.putString("text", text);
        Message msg = new Message();
        msg.what = 0;
        msg.obj = bundle;
        Log.i(TAG, "showProgressDialog");
        dialogHandler.sendMessage(msg);
    }

    public static void hideProgressDialog() {
        Log.i(TAG, "hideProgressDialog");
        dialogHandler.sendEmptyMessage(1);
    }
    
}
