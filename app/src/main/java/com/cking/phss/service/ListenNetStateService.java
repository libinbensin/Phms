/* Xinhuaxing Inc. (C) 2014. All rights reserved.
 *
 * ListenNetStateService.java
 * classes : com.cking.phss.service.ListenNetStateService
 * @author Administrator
 * V 1.0.0
 * Create at 2014-7-8 下午12:37:45
 */
package com.cking.phss.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.IBinder;
import android.util.Log;
import android.view.View;

import com.cking.phss.global.Global;

/**
 * com.cking.phss.service.ListenNetStateService
 * @author Administrator <br/>
 * create at 2014-7-8 下午12:37:45
 */
public class ListenNetStateService extends Service {
    private static final String TAG = "ListenNetStateService";

    private ConnectivityManager connectivityManager;
    private NetworkInfo info;

    private BroadcastReceiver mReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
                Log.d(TAG, "网络状态已经改变");
                connectivityManager = (ConnectivityManager)

                getSystemService(Context.CONNECTIVITY_SERVICE);
                info = connectivityManager.getActiveNetworkInfo();
                if (info != null && info.isAvailable()) {
                    String name = info.getTypeName();
                    Log.d(TAG, "当前网络名称：" + name);
                    Global.isNetStateValid = true;
                } else {
                    Log.d(TAG, "没有可用网络");
                    Global.isNetStateValid = false;
                }

                // 设置视图有效性
                for (View v : Global.globalViewList) {
                    if (v != null) {
                        // 网络好且非本地登录
                        if (Global.isNetStateValid && (!Global.isLocalLogin)) {
                            v.setEnabled(true);
                        } else {
                            v.setEnabled(false);
                        }
                    }
                }
            }
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    /**
     * @param b
     */
    protected void setUploadButtonEnabled(boolean b) {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        IntentFilter mFilter = new IntentFilter();
        mFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(mReceiver, mFilter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mReceiver);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }
}
