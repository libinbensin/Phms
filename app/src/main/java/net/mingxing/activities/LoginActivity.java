package net.mingxing.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.*;
import android.os.Process;
import android.support.v4.app.FragmentActivity;

import com.cking.phss.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;

import net.mingxing.fragments.LoginActivityFrament;
import net.mingxing.service.ListenNetStateService;

@ContentView(R.layout.activity_login)
public class LoginActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        startService(new Intent(this, ListenNetStateService.class));  // 开启网络监听服务
        ViewUtils.inject(this);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, LoginActivityFrament.getInstace()).commit();
    }

    /**
     * 跳转到HomeAcvitity
     */
    public void toNextActivity() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.dync_in_from_right, R.anim.dync_out_to_left);
        finish();
    }

    @Override
    public void onBackPressed() {
        exit();
    }

    /**
     * 退出
     * 将应用程序的进程杀死
     */
    public void exit() {
        new AlertDialog.Builder(this).setMessage("您确定要退出应用程序吗?")
                .setCancelable(false)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        android.os.Process.killProcess(Process.myPid());  // application process kill
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                }).create().show();
    }
}
