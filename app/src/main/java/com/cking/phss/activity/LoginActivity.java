/* Cking Inc. (C) 2012. All rights reserved.
 *
 * LoginActivity.java
 * classes : com.cking.phss.activity.LoginActivity
 * @author Wation Haliyoo
 * V 1.0.0
 * Create at 2012-9-14 上午11:17:16
 */
package com.cking.phss.activity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.xinhuaxing.util.StringUtil;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cking.phss.R;
import com.cking.phss.bean.BeanUtil;
import com.cking.phss.bean.BeanUtil.OnResultFromWeb;
import com.cking.phss.dto.IDto;
import com.cking.phss.dto.Login1;
import com.cking.phss.global.Global;
import com.cking.phss.sqlite.Account;
import com.cking.phss.sqlite.AccountBll;
import com.cking.phss.util.ContextUtil;
import com.cking.phss.util.JgxxConfigFactory;
import com.cking.phss.util.MyApplication;
import com.cking.phss.util.ProgressDialogUtil;
import com.cking.phss.util.TispToastFactory;
import com.cking.phss.widget.JbxxJwsLayout;
import com.cking.phss.xml.util.XmlSerializerUtil;
import com.cking.phss.xml4jgxx.tags.HospitalTag;

/**
 * com.cking.phss.activity.LoginActivity
 * 
 * @author Wation Haliyoo <br/>
 *         create at 2012-9-14 上午11:17:16
 */
public class LoginActivity extends Activity {
    private static final String TAG = "LoginActivity";

    private EditText mUsernameEdit = null;
    private EditText mPasswordEdit = null;
    private Button mNetworkLoginBtn = null;
    private Button mLocalLoginBtn = null;
    private CheckBox mRememberCkeckBox = null;

    private ImageView logoImageView, banquanImageView;
    private TextView jigouTextView; // 机构
    private Toast mToast = null;
    private ProgressDialog progressDialog = null;
    private Context mContext = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        mContext = this;
        Global.isApplicationStoped = false;

        mToast = TispToastFactory.getToast(getApplicationContext(), "");

        mUsernameEdit = (EditText) findViewById(R.id.username_text);
        mPasswordEdit = (EditText) findViewById(R.id.password_text);

        logoImageView = (ImageView) findViewById(R.id.logoImageView);
        banquanImageView = (ImageView) findViewById(R.id.banquanImageView);
        jigouTextView = (TextView) findViewById(R.id.jigouTextView);

        if (ContextUtil.sdCardCanRead()) {
            String externalPath = Environment.getExternalStorageDirectory() + "/phms/image/";
            String logoPath = externalPath + "logo.png";// logo
            String banquanPath = externalPath + "banquan.png";// 版权
            Bitmap bitmap;
            try {
                bitmap = BitmapFactory.decodeStream(new FileInputStream(new File(logoPath)));
                logoImageView.setImageBitmap(bitmap);
                bitmap = BitmapFactory.decodeStream(new FileInputStream(new File(banquanPath)));
                banquanImageView.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                Log.e(TAG, e.toString());
            }
        }

        mRememberCkeckBox = (CheckBox) findViewById(R.id.remember_pwd);
        mNetworkLoginBtn = (Button) findViewById(R.id.network_login_button);
        mLocalLoginBtn = (Button) findViewById(R.id.local_login_button);
        mNetworkLoginBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (checkValid(mUsernameEdit.getText().toString(), mPasswordEdit.getText()
                        .toString())) {
                    networkLogin();
                }

            }
        });
        mLocalLoginBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (checkValid(mUsernameEdit.getText().toString(), mPasswordEdit.getText()
                        .toString())) {
                    localLogin();
                }

            }
        });

        // 遍历数据库，获取被标记为记住密码的账户
        Account account = AccountBll.queryLastRemember();
        if (account != null) {// 如果有这样的一个账户
            mUsernameEdit.setText(account.getUsername());
            if (account.getRemember() == 1){// 表示是要记住密码的
                mPasswordEdit.setText(account.getPassword());
                mRememberCkeckBox.setChecked(true);
            }else {
                mPasswordEdit.setText("");
                mRememberCkeckBox.setChecked(false);
            }
        }
//        File file=new File("/data/data/com.cking.phss/shared_prefs/versionUpdateInfo.xml");
//        Log.i(TAG, "文件是否存在"+file.exists());

        checkUrlFileNumber();
        
        // 初始化既往史数据加载
        ProgressDialogUtil.showProgressDialog(mContext, "正在加载数据", "请稍等...");
        new Thread(new Runnable() {

            @Override
            public void run() {
                JbxxJwsLayout.initData(mContext);
                ProgressDialogUtil.hideProgressDialog();
            }
            
        }).start();
    }

    @Override
    protected void onResume() {
        if (!StringUtil.isEmptyString(Global.orgName)) {
            jigouTextView.setText(Global.orgName);
        }
        super.onResume();
    }

    /**
     * 
     */
    protected void localLogin() {
        Login1.Request request = new Login1.Request();
        request.userN = mUsernameEdit.getText().toString();
        request.password = mPasswordEdit.getText().toString();
        
        // 根据账户密码找到对应xml文件
        // 该文件在网络登录时保存
        Account account = AccountBll.query(request.userN);
        if (account != null && account.getPassword().equals(request.password)) {
            if (account.getStatus() == 1) {
                Login1 login1 = (Login1) XmlSerializerUtil.getInstance().beanFromXML(Login1.class,
                        account.getBean());
                if (login1 == null || login1.response == null) {
                    mToast.setText("无法进行本地登录，本地不存在该账号。");
                    mToast.show();
                } else {
                    boolean remeber = mRememberCkeckBox.isChecked() ? true : false;
                    saveAccountToDb(request.userN, request.password, remeber, login1);
                    // 注册登陆用户对应的医生信息到Session
                    MyApplication myApplication = (MyApplication) LoginActivity.this
                            .getApplication();
                    myApplication.getSession().setLoginResult(login1);
                    jumpToMainActivity(login1.response.doctorID, login1.response.doctorName);
                    Global.isLocalLogin = true;
                }
            } else {
                mToast.setText("无法进行本地登录，该账号已被禁用。");
                mToast.show();
            }
        } else {
            mToast.setText("无法进行本地登录，本地不存在该账号。");
            mToast.show();
        }
    }

    /**
     * 网络登录
     */
    protected void networkLogin() {
        final Login1.Request request = new Login1.Request();
        request.userN = mUsernameEdit.getText().toString();
        request.password = mPasswordEdit.getText().toString();
        
        showProgressDialog();
        
        Login1 loginBean = new Login1();
        loginBean.request = request;

        List<IDto> beanList = new ArrayList<IDto>();
        beanList.add(loginBean);
        BeanUtil.getInstance().getBeanFromWeb(beanList, new OnResultFromWeb() {
            @Override
            public void onResult(List<IDto> listBean, boolean isSucc) {
                if (listBean != null && listBean.size() > 0) {
                    Login1 login1ResultLogin1 = (Login1) listBean.get(0);
                    if (login1ResultLogin1 == null
                            || login1ResultLogin1.response == null) {
                        mToast.setText("网络异常，请稍后重试。");
                        mToast.show();
                        hideProgressDialog();
                        return;
                    }
                    if (login1ResultLogin1.response.errMsg != null
                            && login1ResultLogin1.response.errMsg.equals("")) {
                        if (login1ResultLogin1.response.userID != null // 根据协议修改判断条件
                                && login1ResultLogin1.response.userID.length() > 0) {
                            // 注册登陆用户对应的医生信息到Session
                            MyApplication myApplication = (MyApplication) LoginActivity.this
                                    .getApplication();
                            login1ResultLogin1.request = request;
                            myApplication.getSession().setLoginResult(login1ResultLogin1);
                            // 将账户信息存储到数据库中
                            boolean remeber = mRememberCkeckBox.isChecked() ? true : false;
                            saveAccountToDb(request.userN, request.password, remeber,
                                    login1ResultLogin1);

                            // 隐藏进度条
                            hideProgressDialog();
                            jumpToMainActivity(login1ResultLogin1.response.doctorID,
                                    login1ResultLogin1.response.doctorName);
                            Global.isLocalLogin = false;
                            return;

                        } else {
                            mToast.setText("登录失败-用户名或密码错误。");
                            mToast.show();
                        }
                    } else {
                        mToast.setText(login1ResultLogin1.response.errMsg);
                        mToast.show();
                    }
                }
                hideProgressDialog();

            }
        });
    }

    protected void showProgressDialog() {
        progressDialog = ProgressDialog.show(LoginActivity.this, "正在登录", "请稍等...", true, true);
    }

    protected void hideProgressDialog() {
        dialogHandler.sendEmptyMessage(0);
        
    }

    private void jumpToMainActivity(String doctorID, String doctorName) {
        Intent intent = new Intent();
        intent.setClass(LoginActivity.this, HomeActivity.class);
        intent.putExtra("doctorID", doctorID);
        intent.putExtra("doctorName", doctorName);
        startActivity(intent);

        // 结束登陆页面
        finish();
    }

    /**
     * 检查用户名密码是否有效
     * 
     * @param username
     * @param password
     * @return
     */
    protected boolean checkValid(String username, String password) {
        if (username == null || username.equals("") || username.length() > 32) {
            mToast.setText("用户名格式不正确。");
            mToast.show();
            return false;
        }
        if (password == null || username.equals("") || password.length() > 32) {
            mToast.setText("密码格式不正确。");
            mToast.show();
            return false;
        }

        return true;
    }

    /**
     * 用Handler来闭关progressDialog
     */
    private Handler dialogHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            if (progressDialog != null) {
                try {
                    progressDialog.dismiss();
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                }
                progressDialog = null;
            }
        }
    };
    
    //将用户信息保存到数据库
    private void saveAccountToDb(String userName, String password, boolean remeber, Login1 login1) {
        Account account = AccountBll.query(userName);
        int userGroup = Account.CUSTOMER; //
        boolean status = true;
        Date lastLogin = new Date();
        if (account != null) {
            userGroup = account.getUserGroup();
            status = account.getStatus() == 0 ? false : true;
        }
        AccountBll.saveBean(userName, password, userGroup, remeber, status, lastLogin, login1);
    }
    
    private void checkUrlFileNumber() {
        final List<HospitalTag> hospitaoList = JgxxConfigFactory.listHosptalTag(mContext);
        if (hospitaoList == null || hospitaoList.size() == 0) {
            mToast.setText("配置文件出错，请检查配置文件格式");
            mToast.show();
            System.exit(0);
        } else if (hospitaoList.size() > 1) {// 选择街道
            CharSequence[] charSequence = new CharSequence[hospitaoList.size()];
            for (int i = 0; i < hospitaoList.size(); i++) {
                charSequence[i] = hospitaoList.get(i).attrBean.getName();
            }

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("请先选择街道再进行登录");
            builder.setSingleChoiceItems(charSequence, 0, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    setGlobalHospitalInfo(hospitaoList.get(which));
                    dialog.cancel();
                    if (!StringUtil.isEmptyString(Global.orgName)) {
                        jigouTextView.setText(Global.orgName);
                    }
                }
            });
            builder.show();
        } else {
            setGlobalHospitalInfo(hospitaoList.get(0));
        }
    }

    /**
     * @param hospitalTag
     */
    protected void setGlobalHospitalInfo(HospitalTag hospitalTag) {
        Global.status = hospitalTag.attrBean.getStatus();
        Global.orgCode = hospitalTag.attrBean.getSerialNo(); // 机构代码
        Global.orgName = hospitalTag.attrBean.getName(); // 机构名称
        Global.webserviceUrl = hospitalTag.attrBean.getWebserviceUrl();
        Global.uploadKstjUrl = hospitalTag.attrBean.getUploadKstjUrl();
        Global.downloadKstjUrl = hospitalTag.attrBean.getDownloadKstjUrl();
        Global.versionServiceUrl = hospitalTag.attrBean.getVersionServiceUrl();
        Global.dataVersionUrl = hospitalTag.attrBean.getDataVersionUrl();
        Global.printHeader = hospitalTag.attrBean.getPrintHeader();
        Global.printFooter = hospitalTag.attrBean.getPrintFooter();
    }
}
