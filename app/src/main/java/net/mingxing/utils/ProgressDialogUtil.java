package net.mingxing.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

/**
 * 创建进度条(创建进度条, 关闭进度条)
 */
public class ProgressDialogUtil {

    private static final String TAG = "ProgressDialogUtil";

    private static ProgressDialog mProgressDialog = null;

    public static  final int CLOSE_PROGRESSDIALOG = 1;

    public static  final int OPEN_PROGRESSDIALOG = 0;

    private static String mTitle;
    private static String mContent;
    private static boolean mCancelable;

    private static int count = 0; //计数器
    
    static Handler dialogHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case OPEN_PROGRESSDIALOG:
 Log.e(TAG, "进度条显示");
                    if (count == 0 && mProgressDialog == null) {
                        mProgressDialog = ProgressDialog.show(sContext, mTitle, mContent, false, mCancelable);
                    }
                    mProgressDialog.setTitle(mTitle);
                    mProgressDialog.setMessage(mContent);
                    mProgressDialog.setCancelable(mCancelable);
                    count++;
                    break;
                case CLOSE_PROGRESSDIALOG:
                    count--;
                    if (count <= 0) {
                        count = 0;
                    }
                    if (count == 0 && mProgressDialog != null) {
Log.e(TAG, "进度条关闭");
                        try {
                            mProgressDialog.dismiss();
                        } catch (IllegalArgumentException e) {
                            e.printStackTrace();
                        }
                        mProgressDialog = null;
                    }
                    break;
            }
        }
    };

    private static Context sContext;

    public static void showProgressDialog(Context context, String title, String text, boolean cancelable) {
        sContext = context;
        mTitle =        title;
        mContent = text;
        mCancelable = cancelable;
        dialogHandler.sendEmptyMessage(OPEN_PROGRESSDIALOG);
    }

    public static void hideProgressDialog() {
        dialogHandler.sendEmptyMessage(CLOSE_PROGRESSDIALOG);
    }
    
}
