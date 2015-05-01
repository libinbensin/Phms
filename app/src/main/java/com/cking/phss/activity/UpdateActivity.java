package com.cking.phss.activity;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cking.phss.R;
import com.cking.phss.update.UpdateInfo;

public class UpdateActivity extends Activity {
    private TextView titleTv;
    private TextView messageTv;
    private Button sureBtn;
    private Button cancelBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_dialog);
        titleTv = (TextView) findViewById(R.id.title_tv);
        messageTv = (TextView) findViewById(R.id.message_tv);
        sureBtn = (Button) findViewById(R.id.sure_btn);
        cancelBtn = (Button) findViewById(R.id.cancel_btn);

        Intent intent = getIntent();
        final int status = intent.getExtras().getInt("status");
        if (status==UpdateInfo.LAST_CHECK_STATUS_SUCCEED) {// update and download succeed
            titleTv.setText("更新成功");
            messageTv.setText("是否进入安装？");
        } else if(status==UpdateInfo.LAST_CHECK_STATUS_FAILED){// failed
            titleTv.setText("更新失败");
            messageTv.setText("可能由于网络原因，更新失败，将在下一次启动的时候再次更新。");
            cancelBtn.setVisibility(View.GONE);
        }else if(status==UpdateInfo.LAST_CHECK_STATUS_NO_INSTALL){// update and download succeed ,but not install in last time
            titleTv.setText("更新信息");
            messageTv.setText("上次更新成功，但未安装，是否进入安装？");
        }

        sureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (status!=UpdateInfo.LAST_CHECK_STATUS_FAILED) {
                    //install automatic
                    Intent intent = new Intent();
                    String fileName=UpdateInfo.getInstance().getLastCheckFile();
                    File file=new File(Environment.getExternalStorageDirectory().getAbsoluteFile()+"/phms/"+fileName);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setAction(android.content.Intent.ACTION_VIEW);
                    intent.setDataAndType(Uri.fromFile(file),
                                                      "application/vnd.android.package-archive");
                    startActivity(intent);
                }
                finish();
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (status!=UpdateInfo.LAST_CHECK_STATUS_FAILED) {
                    UpdateInfo.getInstance().setLastCheckStatus(UpdateInfo.LAST_CHECK_STATUS_NO_INSTALL);
                }
                finish();
            }
        });
    }

}
