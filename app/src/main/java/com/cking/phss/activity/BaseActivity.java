
package com.cking.phss.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;

import com.cking.phss.global.Global;
import com.cking.phss.util.ActivityStack;
import com.cking.phss.util.MyApplication;

/**
 * com.iaxure.remotecontrol.activity.BaseActivity
 * @author Administrator <br/>
 * create at 2012-9-6 下午10:41:59
 */
public class BaseActivity extends Activity {
    
    protected void onCreate(Bundle savedInstanceState) {
        if (Global.isApplicationStoped) {
            super.onCreate(savedInstanceState);
            super.finish();
            return;
        }
        // 全屏
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        ActivityStack.getInstance().push(this);
        
        super.onCreate(savedInstanceState);
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            this.finalize();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    /**
     * 退出
     */
    public void exit() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this); 
        builder.setMessage("您确定要退出应用程序吗?") 
               .setCancelable(false) 
               .setPositiveButton("确定", new DialogInterface.OnClickListener() { 
                   public void onClick(DialogInterface dialog, int id) {
                      //在退出程序的时候，清空MyApplication全局变量保存的信息
                       MyApplication.getInstance().getSession().setLoginResult(null);
                       MyApplication.getInstance().getSession().setCurrentResident(null);
                       //BaseActivity.super.finish(); 
                        ActivityStack.getInstance().pop();
                       // 关闭所有页面
                       while(!ActivityStack.getInstance().isEmpty()) {
                           Activity activity = ActivityStack.getInstance().pop();
                           activity.finish();
                       }
                       System.exit(0);
                       Global.isApplicationStoped = true;
                       //android.os.Process.killProcess(android.os.Process.myPid());
                   } 
               }) 
               .setNegativeButton("取消", new DialogInterface.OnClickListener() { 
                   public void onClick(DialogInterface dialog, int id) { 
                        dialog.cancel(); 
                   } 
               }); 
        AlertDialog alert = builder.create(); 
        alert.show();
    }

    public void onLowMemory() { 
        super.onLowMemory();     
        System.gc(); 
    } 
    
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            /// 获取Activity的数量，当最后一个时退出
//            ActivityStack.getInstance().pop();
//            /// 退出提示
//            if (ActivityStack.getInstance().isEmpty()) {
//                exit();
//            }
            finish();
            
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void finish() {
        /// 退出提示
        if (ActivityStack.getInstance().getCount() <= 1) {
            exit();
        } else {
            ActivityStack.getInstance().pop();
            super.finish();
        }
    }
}
