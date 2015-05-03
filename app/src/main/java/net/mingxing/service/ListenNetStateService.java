package net.mingxing.service;


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

import net.mingxing.global.Global;


/**
 * Created by MingXing on 2015/5/3.
 * 监听网络状态service
 */
public class ListenNetStateService extends Service {
    private static final String TAG = ListenNetStateService.class.getName();

    private ConnectivityManager connectivityManager;
    private NetworkInfo info;

    private BroadcastReceiver mReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
Log.e(TAG, "网络状态已经改变");
                connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                info = connectivityManager.getActiveNetworkInfo();
                if (info != null && info.isAvailable()) {
                    String name = info.getTypeName();
Log.e(TAG, "当前网络名称：" + name);
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
