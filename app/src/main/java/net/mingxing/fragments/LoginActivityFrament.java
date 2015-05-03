package net.mingxing.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cking.phss.R;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import net.mingxing.activities.LoginActivity;
import net.mingxing.bean.DoctorInfo;
import net.mingxing.constant.Constant;
import net.mingxing.db.bean.UserInfo;
import net.mingxing.dialog.SingleChoiceDialog;
import net.mingxing.global.Global;
import net.mingxing.net.NetUtils;
import net.mingxing.protocol.IProtocol;
import net.mingxing.protocol.login.Protocol_1;
import net.mingxing.utils.BitmapHelp;
import net.mingxing.utils.DButils;
import net.mingxing.utils.ProgressDialogUtil;
import net.mingxing.utils.SDCardUtil;
import net.mingxing.utils.StringUtil;
import net.mingxing.utils.ToastFactory;
import net.mingxing.utils.XmlFactory;

import java.io.File;
import java.util.List;

/**
 * Created by MingXing on 2015/5/3.
 * 登录activity的fragment
 */
public class LoginActivityFrament extends Fragment {

    private static final String TAG = "LoginActivityFrament";

    /**
     * 是否加载过图片
     */
    private boolean isLoadImage = false;

    /**
     * 程序上下文
     */
    private LoginActivity mContext;

    /**
     * 登录状态 (handler request code)
     */
    public static int LOGIN_STATE;   // 登录状态
    public static final int NET_LOGIN = 0;  // 网络登录
    public static final int LOCAL_LOGIN = 1;  // 本地登录
    public static final int SHOW_HOSPITAL_NAME = 2;  // 显示选择的hospitalname
    public static final int OPEN_PROGRESS_DIAOLOG = 3;  // 打开进度条对话框
    public static final int CLOSE_PROGRESS_DIAOLOG = 4;  // 关闭进度条对话框
    public static final int OPEN_SINGLECHOICE_DIALOG = 5;  // 打开单选对话框
    public static final int CLOSE_SINGLECHOICE_DIALOG = 6;  // 打开单选对话框
    public static final int TO_NEXT_ACTIVITY = 7; // 跳转到homeActivity

    /**
     * 加载appconfig.xml状态
     */
    public static int LOAD_APPCONFIG_STATE;
    public static final int LOAD_APPCONFIG_SUCCESS = 0;
    public static final int LOAD_APPCONFIG_ERROR = 1;


    private static LoginActivityFrament mLoginActivityFrament = new LoginActivityFrament();
    private Toast mToast;
    private SingleChoiceDialog mSingleChoiceDialog;
    private String mHospitalName;
    private String[] mHostitalNames;
    private String username;
    private String password;
    private String progressTitleString;
    private String progressContent;


    @ViewInject(R.id.username_text)
    private EditText mUsernameEdit;

    @ViewInject(R.id.password_text)
    private EditText mPasswordEdit;

    @ViewInject(R.id.remember_pwd)
    private CheckBox mRememberCkeckBox;

    @ViewInject(R.id.logoImageView)
    private ImageView logoImageView;

    @ViewInject(R.id.banquanImageView)
    private ImageView banquanImageView;

    @ViewInject(R.id.jigouTextView)
    private TextView jigouTextView; // 机构

    public static LoginActivityFrament getInstace() {
        return mLoginActivityFrament;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Global.isApplicationStoped = false;  // program running
        mContext = (LoginActivity) getActivity();
        mToast = ToastFactory.getToast(mContext, "");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);

        ViewUtils.inject(this, rootView);  // inject view and event

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (!isLoadImage) {
            if (SDCardUtil.isCan()) {
                // 加载图片(logo, power image)
                BitmapUtils bitmapUtils = BitmapHelp.getBitmapUtils(mContext);
                bitmapUtils.display(logoImageView, Uri.fromFile(new File(Constant.LOGO_PATH)).getPath());
                bitmapUtils.display(banquanImageView, Uri.fromFile(new File(Constant.VERSION_PATH)).getPath());

                // 加载记住密码功能  id, username, password, remember(0:记住密码, 1:不记住密码), mode(0:网络登录 1:特定的admin用户)
                DButils instance = DButils.getInstance(mContext, DButils.PHMS_DB);
                UserInfo rememberUser = instance.getRememberUser();
                if(rememberUser != null) {
                    mRememberCkeckBox.setChecked(true);
                    mUsernameEdit.setText(rememberUser.username);
                    mPasswordEdit.setText(rememberUser.password);
                }
            } else {
                Log.e(TAG, "请检查SDcard");
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        progressTitleString = mContext.getString(R.string.progress_title);
        progressContent = mContext.getString(R.string.progress_content);
        mHandler.obtainMessage(OPEN_PROGRESS_DIAOLOG).sendToTarget();  // 打开进度条
        // 加载选择街道,社区
        mHostitalNames = XmlFactory.getHostitalNames();
        if (mHostitalNames == null) {
            mHandler.obtainMessage(CLOSE_PROGRESS_DIAOLOG).sendToTarget();  // 关闭进度条
            LOAD_APPCONFIG_STATE = LOAD_APPCONFIG_ERROR;
            mToast.setText(mContext.getString(R.string.toast_text));
            mToast.show();
            System.exit(0);  // 退出应用程序
        } else {
            LOAD_APPCONFIG_STATE = LOAD_APPCONFIG_SUCCESS;
            mHandler.obtainMessage(CLOSE_PROGRESS_DIAOLOG).sendToTarget();
            // 配置文件中, 配置一个hospital节点
            if (XmlFactory.getAllHospitals().size() == 1) {
                Global.mHospitalTag = XmlFactory.getHospital(0);
                mHospitalName = Global.mHospitalTag.getName();
                mHandler.obtainMessage(SHOW_HOSPITAL_NAME).sendToTarget();
            } else {
                // 配置文件中配置多个hospital节点
                mHandler.obtainMessage(OPEN_SINGLECHOICE_DIALOG).sendToTarget();
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if(mSingleChoiceDialog != null) {
            mSingleChoiceDialog.dismiss();
        }
    }

    @OnClick({R.id.network_login_button, R.id.local_login_button})
    public void onClick(View view) {
        progressTitleString = "正在登录";
        progressContent = "请稍后...";
        mHandler.obtainMessage(OPEN_PROGRESS_DIAOLOG).sendToTarget();
        int tag = Integer.valueOf((String) view.getTag());
        switch (tag) {
            case LOCAL_LOGIN:
                LOGIN_STATE = LOCAL_LOGIN;
                break;
            case NET_LOGIN:
                LOGIN_STATE = NET_LOGIN;
                break;
        }
        Global.isLocalLogin = LOGIN_STATE == LOCAL_LOGIN ? true : false;
        mHandler.obtainMessage(LOGIN_STATE).sendToTarget();
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case LOCAL_LOGIN:  // 本地登录
                    localLogin();
                    break;
                case NET_LOGIN:   // 网络登陆
                    netLogin();
                    break;
                case SHOW_HOSPITAL_NAME:
                    showHospitalName();
                    break;
                case OPEN_PROGRESS_DIAOLOG:
                    openProgressDialog();
                    break;
                case CLOSE_PROGRESS_DIAOLOG:
                    closeProgressDialog();
                    break;
                case OPEN_SINGLECHOICE_DIALOG:
                    openSingleChoiceDialog();
                    break;
                case CLOSE_SINGLECHOICE_DIALOG:
                    closeSingleChoiceDialog();
                    break;
                case TO_NEXT_ACTIVITY:
                    toNextAcitivity();
                    break;
            }
        }
    };

    /**
     * 跳转到HomeActitivty
     */
    private void toNextAcitivity() {
        mContext.toNextActivity();
    }

    /**
     * 本地登陆
     */
    private void localLogin() {
        if (LOAD_APPCONFIG_STATE == LOAD_APPCONFIG_SUCCESS) {
            // 检查输入用户名, 密码格式
            getUsrnameAndPassword();
            if (checkValid()) {
                // 访问本地数据库
                String username = "admin";
                String password = "hande";
                mHandler.obtainMessage(CLOSE_PROGRESS_DIAOLOG).sendToTarget();
                if(this.username.equals(username) && this.password.equals(password)) {
                    mHandler.obtainMessage(TO_NEXT_ACTIVITY).sendToTarget();
                } else {
                    mToast.setText("请输入正确的用户名或密码");
                    mToast.show();
                }
                // 保存数据

                // 跳转到HomeActivity

            } else {
                mHandler.obtainMessage(CLOSE_PROGRESS_DIAOLOG).sendToTarget();
            }
        } else {
            mHandler.obtainMessage(CLOSE_PROGRESS_DIAOLOG).sendToTarget();
            mToast.setText(mContext.getString(R.string.toast_text));
            mToast.show();
        }


    }

    /**
     * 网络登陆
     */
    private void netLogin() {
        if (LOAD_APPCONFIG_STATE == LOAD_APPCONFIG_SUCCESS) {
            // 检查输入用户名, 密码格式
            getUsrnameAndPassword();
            if (checkValid()) {
                if (Global.isNetStateValid) {
                    // 访问网络, 在有网络的状态下, 进行发送数据
                    Protocol_1 mProtocol_1 = Protocol_1.getInstance();
                    mProtocol_1.request = new Protocol_1.Request();
                    mProtocol_1.request.userN = username;
                    mProtocol_1.request.password = password;
                    NetUtils.netInstance().requestByPost(mProtocol_1, new NetUtils.onRequestResult() {
                        @Override
                        public void onSuccess(IProtocol bean) {
                            mHandler.obtainMessage(CLOSE_PROGRESS_DIAOLOG).sendToTarget();
                            progressData(bean);
                        }

                        @Override
                        public void onSuccessForTimes(List<IProtocol> beans) {

                        }

                        @Override
                        public void onFail(String errMsg) {
                            mHandler.obtainMessage(CLOSE_PROGRESS_DIAOLOG).sendToTarget();
                            mToast.setText(mContext.getString(R.string.toast_net_state_text));
                            mToast.show();
                        }
                    });
                } else {
                    mHandler.obtainMessage(CLOSE_PROGRESS_DIAOLOG).sendToTarget();
                    mToast.setText(mContext.getString(R.string.toast_not_net_text));
                    mToast.show();
                }
            } else {
                mHandler.obtainMessage(CLOSE_PROGRESS_DIAOLOG).sendToTarget();
            }
        } else {
            mHandler.obtainMessage(CLOSE_PROGRESS_DIAOLOG).sendToTarget();
            mToast.setText(mContext.getString(R.string.toast_text));
            mToast.show();
        }
    }

    /**
     * 处理webService返回的数据
     *
     * @param bean
     */
    private void progressData(IProtocol bean) {
        Protocol_1 bean1 = (Protocol_1) bean;
        Protocol_1.Response response = bean1.response;
        if (StringUtil.isEmpty(response.errMsg)) {
            // 成功,发送请求
            setDoctorInfo(response);  // 保存数据
            // 跳转到HomeActivity
            mHandler.obtainMessage(TO_NEXT_ACTIVITY).sendToTarget();
        } else {
            mToast.setText(response.errMsg);
            mToast.show();
        }
    }

    /**
     * 保存doctor信息数据
     *
     * @param response
     */
    private void setDoctorInfo(Protocol_1.Response response) {

        saveUserInfoToDB();  // 将doctor信息保存到数据库

        // 设置doctor信息
        DoctorInfo mDoctorInfo = new DoctorInfo();
        mDoctorInfo.username = response.username;
        mDoctorInfo.userID = response.userID;
        mDoctorInfo.passWord = response.passWord;
        mDoctorInfo.doctorID = response.doctorID;
        mDoctorInfo.doctorName = response.doctorName;
        mDoctorInfo.position = response.position;
        mDoctorInfo.employeeNo = response.employeeNo;
        mDoctorInfo.station = response.station;
        mDoctorInfo.healthService = response.healthService;
        mDoctorInfo.community = response.community;
        mDoctorInfo.province = response.province;
        mDoctorInfo.city = response.city;
        mDoctorInfo.roleCD = response.roleCD;
        mDoctorInfo.roleName = response.roleName;
        mDoctorInfo.district = response.district;
        mDoctorInfo.street = response.street;
        mDoctorInfo.community = response.community;
        mDoctorInfo.road = response.road;
        mDoctorInfo.lane = response.lane;
        mDoctorInfo.group = response.group;
        mDoctorInfo.room = response.room;
        mDoctorInfo.other = response.other;
        mDoctorInfo.communityJurisdiction = response.communityJurisdiction;
        mDoctorInfo.Zones = response.Zones;
        Global.mDoctorInfo = mDoctorInfo;

    }

    /**
     * 检查用户名密码是否有效
     *
     * @return
     */
    protected boolean checkValid() {
        if (StringUtil.isEmpty(username)) {
            mToast.setText("用户名格式不正确。");
            mToast.show();
            return false;
        }

        if (StringUtil.isEmpty(password)) {
            mToast.setText("密码格式不正确。");
            mToast.show();
            return false;
        }

        return true;
    }

    /**
     * 将doctor信息保存到数据库
     */
    public void saveUserInfoToDB() {
        DButils instance = DButils.getInstance(mContext, DButils.PHMS_DB);
        UserInfo mUserInfo = new UserInfo();
        mUserInfo.username = username;
        mUserInfo.password = password;
        if (mRememberCkeckBox.isChecked()) {
            mUserInfo.remember = "0";
        } else {
            // 没有记住密码
            mUserInfo.remember = "1";
        }
        // 将用户名,密码 保存到数据库
        instance.saveUserOrUpdateInfo(mUserInfo);
    }

    /**
     * 得到用户名和密码
     */
    public void getUsrnameAndPassword() {
        username = mUsernameEdit.getText().toString();
        password = mPasswordEdit.getText().toString();
    }

    /**
     * 显示 hospital Name
     */
    private void showHospitalName() {
        jigouTextView.setText(mHospitalName);
    }

    /**
     * 打开单选dialog
     */
    private void openSingleChoiceDialog() {
        mSingleChoiceDialog = SingleChoiceDialog.newInstance(mHostitalNames);
        mSingleChoiceDialog.show(getFragmentManager(), SingleChoiceDialog.TAG);
        mSingleChoiceDialog.setOnSelectListener(new SingleChoiceDialog.OnSelectListener() {
            @Override
            public void onSelectItem(int index) {
                Global.mHospitalTag = XmlFactory.getHospital(index);
                mHospitalName = Global.mHospitalTag.getName();
                mHandler.obtainMessage(SHOW_HOSPITAL_NAME).sendToTarget();
            }
        });
    }

    /**
     * 关闭单选dialog
     */
    private void closeSingleChoiceDialog() {
        if (mSingleChoiceDialog != null) {
            mSingleChoiceDialog.dismiss();
        }
    }

    /**
     * 打开进度条dialog
     */
    private void openProgressDialog() {
        ProgressDialogUtil.showProgressDialog(mContext, progressTitleString, progressContent, false);
    }

    /**
     * 关闭进度条dialog
     */
    private void closeProgressDialog() {
        ProgressDialogUtil.hideProgressDialog();
    }

}
