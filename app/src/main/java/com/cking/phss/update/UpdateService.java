package com.cking.phss.update;

import java.io.IOException;
import java.net.SocketException;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;

import com.cking.phss.activity.UpdateActivity;

public class UpdateService extends Service {

    private static final String TAG = "UpdateService";
    private FtpControl ftpControl;
    private UpdateStatusHandler updateStatusHandler;

    @Override
    public void onCreate() {

        super.onCreate();
    }

    @Override
    public void onStart(Intent intent, int startId) {
        Bundle bundle=intent.getExtras();
        Log.i(TAG, "asdasdasdasdasd="+bundle.getString("url"));
        if(bundle!=null){
            ftpControl = new FtpControl(bundle.getString("url"));
            updateStatusHandler = new UpdateStatusHandler();
            new Thread(new DownLoadThread()).start();
        }
        super.onStart(intent, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return null;
    }

    public class DownLoadThread implements Runnable {
        public void run() {
            try {
                if (ftpControl.openConnect()) {// connect succeed
                    if (ftpControl.startDownload()) {// download succeed
                        updateStatusHandler.sendEmptyMessage(0x1);
                    } else {
                        updateStatusHandler.sendEmptyMessage(0x2);
                    }
                    ftpControl.closeConnect();
                }else{
                    updateStatusHandler.sendEmptyMessage(0x2);
                }
                    
            } catch (SocketException e) {
                updateStatusHandler.sendEmptyMessage(0x2);
            } catch (IOException e) {
                updateStatusHandler.sendEmptyMessage(0x2);
            } catch (Exception e) {
                updateStatusHandler.sendEmptyMessage(0x2);
            }
        }
    }

    private class UpdateStatusHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            Intent intent=new Intent();
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setClass(UpdateService.this, UpdateActivity.class);
            if (msg.what == 0x1) {// update succeed
                UpdateInfo.getInstance().setLastCheckStatus(UpdateInfo.LAST_CHECK_STATUS_SUCCEED);
                intent.putExtra("status", UpdateInfo.LAST_CHECK_STATUS_SUCCEED);
            } else {
                UpdateInfo.getInstance().setLastCheckStatus(UpdateInfo.LAST_CHECK_STATUS_FAILED);
                intent.putExtra("status", UpdateInfo.LAST_CHECK_STATUS_FAILED);
            }
            startActivity(intent);
        }
    }
}
