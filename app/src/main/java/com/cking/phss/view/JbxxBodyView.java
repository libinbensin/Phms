/* Cking Inc. (C) 2012. All rights reserved.
 *
 * JbxxBodyView.java
 * classes : com.cking.phss.view.JbxxBodyView
 * @author Administrator
 * V 1.0.0
 * Create at 2012-9-16 上午11:25:10
 */
package com.cking.phss.view;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.xinhuaxing.util.StringUtil;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cking.phss.R;
import com.cking.phss.bean.BeanCD;
import com.cking.phss.bean.BeanID;
import com.cking.phss.bean.BeanUtil;
import com.cking.phss.bean.BeanUtil.OnResultFromDb;
import com.cking.phss.bean.BeanUtil.OnResultFromWeb;
import com.cking.phss.bean.BeanUtil.OnResultSaveToDb;
import com.cking.phss.bean.BeanUtil.SaveToDbResult;
import com.cking.phss.bean.Dzqy;
import com.cking.phss.bean.HistoryDisease;
import com.cking.phss.bean.HistoryHyper;
import com.cking.phss.bean.IBean;
import com.cking.phss.bean.Jktj_kstj;
import com.cking.phss.bean.Jmjbxx;
import com.cking.phss.bean.Jmjkxx;
import com.cking.phss.bean.Jmjtxx;
import com.cking.phss.bean.Jmxwxg;
import com.cking.phss.bean.Sfgljl_cjsf;
import com.cking.phss.bean.Sfgljl_gxy;
import com.cking.phss.bean.Sfgljl_jsb;
import com.cking.phss.bean.Sfgljl_lnsf;
import com.cking.phss.bean.Sfgljl_tnb;
import com.cking.phss.bluetooth.BluetoothClient.OnDisconnectListener;
import com.cking.phss.bluetooth.BluetoothClient4Hande;
import com.cking.phss.bluetooth.BluetoothClient4Hande.OnConnectedListener;
import com.cking.phss.bluetooth.BluetoothClient4Hande.OnReceiveListener;
import com.cking.phss.dto.BcjmjbxxhjtxxJ003;
import com.cking.phss.dto.Bcjmxwxg8_1;
import com.cking.phss.dto.Bcjtxxxx6;
import com.cking.phss.dto.BcsfzzpJ002;
import com.cking.phss.dto.Ddjmjbxx7;
import com.cking.phss.dto.Ddjmjkxx7_2;
import com.cking.phss.dto.Ddjmxwxg7_1;
import com.cking.phss.dto.Ddjtxxxx5;
import com.cking.phss.dto.DdsfzzpJ001;
import com.cking.phss.dto.IDto;
import com.cking.phss.dto.JmjkxxbcJ007;
import com.cking.phss.dto.Login1;
import com.cking.phss.dto.Zjddjmjbxx7_9;
import com.cking.phss.dto.daxx.BcqyqkHrs01;
import com.cking.phss.dto.daxx.DdqyqkHrs02;
import com.cking.phss.file.FileHelper;
import com.cking.phss.global.Global;
import com.cking.phss.page.IPage;
import com.cking.phss.page.JbxxPage01;
import com.cking.phss.page.JbxxPage02;
import com.cking.phss.page.JbxxPage03;
import com.cking.phss.page.JbxxPage04;
import com.cking.phss.page.JbxxPage05;
import com.cking.phss.page.JbxxPage06;
import com.cking.phss.page.JbxxPage07;
import com.cking.phss.page.JbxxPage08;
import com.cking.phss.page.JbxxPage09;
import com.cking.phss.page.JbxxPage10;
import com.cking.phss.page.JbxxPage11;
import com.cking.phss.page.JbxxPage12;
import com.cking.phss.page.JbxxPage13;
import com.cking.phss.page.JktjTzbsPage01;
import com.cking.phss.sqlite.Resident;
import com.cking.phss.sqlite.ResidentBll;
import com.cking.phss.sqlite.SqliteField.ResidentField;
import com.cking.phss.util.AppConfigFactory.AppConfig;
import com.cking.phss.util.BluetoothUtil;
import com.cking.phss.util.CalendarUtil;
import com.cking.phss.util.CommonUtil;
import com.cking.phss.util.Constant;
import com.cking.phss.util.IdcReader;
import com.cking.phss.util.IdcReader.OnErrorListener;
import com.cking.phss.util.IdcReader.OnReadMessageListener;
import com.cking.phss.util.JgxxConfigFactory;
import com.cking.phss.util.MyApplication;
import com.cking.phss.util.ProgressDialogUtil;
import com.cking.phss.util.Session;
import com.cking.phss.util.TispToastFactory;
import com.cking.phss.widget.DeviceListDialog.OnDialogResultListener;
import com.cking.phss.widget.GuidePager;
import com.cking.phss.widget.GuidePager.OnPageChangeListener;
import com.cking.phss.xml4jgxx.tags.constants.TagConstants;
import com.ivsign.android.IDCReader.IDCReaderSDK;

/**
 * 基本信息 com.cking.phss.view.JbxxBodyView
 * 
 * @author Administrator <br/>
 *         create at 2012-9-16 上午11:25:10
 */
public class JbxxBodyView extends LinearLayout implements IPage {

    private static final String TAG = "JbxxBodyView";
    private Context mContext = null;
    public Map<String, IBean> beanMap = new HashMap<String, IBean>();
    public Bitmap mProfileBitmap = null;
    public String mPaperNum = null;
    private GuidePager mGuidePager = null;
    private ProgressDialog progressDialog = null;

    /**
     * 全局控件
     */
//    private ImageView mProfileImg = null;
//    private TextView mResidentNameText = null;
//    private TextView mResidentIdText = null;
    private Button mReadButton = null;
    private Button mSaveButton = null;
    private Button mUploadButton = null;
    private Button mPrintButton = null;
    private ImageView mIdcSearchImg = null;// 根据身份证号码搜索患者信息的搜索按钮
    private ImageView mNameSearchImg = null;// 根据姓名搜索居民信息
    private ImageView khSearchImg = null;// 根据卡号搜索居民信息
    ImageView sexImageView;
    TextView sexTextView;
    TextView ageTextView;
    EditText sfzhEditText;
    TextView dabhTextView;
    EditText khEditText;
    EditText xmEditText;
    ImageView profileImageView;

    private Toast mToast = null;

    private JbxxPage01 page01 = null;
    private JbxxPage02 page02 = null;
    private JbxxPage03 page03 = null;
    private JbxxPage04 page04 = null;
    private JbxxPage05 page05 = null;
    private JbxxPage06 page06 = null;
    private JbxxPage07 page07 = null;
    private JbxxPage08 page08 = null;
    private JbxxPage09 page09 = null;
    private JbxxPage10 page10 = null;
    private JbxxPage11 page11 = null;
    private JbxxPage12 page12 = null;
    private JbxxPage13 page13 = null;
    private int showMessageStatus = 0;// 具体进行的是什么操作，
    private BluetoothClient4Hande mBluethoothClient4Hande = null;

    // 保存身份证
    private String mPeopleAddress = null;

    private int deviceId = 0;
    private int spdkqDeviceId = 0;

    private OnPageChangeListener mOnPageChangeListener = null;

    public void setOnPageChangeListener(OnPageChangeListener listener) {
        mOnPageChangeListener = listener;
    }

    /**
     * @param context
     */
    public JbxxBodyView(Context context) {
        super(context);

        init(context);
    }

    /**
     * @param context
     * @param attrs
     */
    public JbxxBodyView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    /**
     * @param context
     */
    private void init(Context context) {
        mContext = context;
        mToast = TispToastFactory.getToast(context, "");
        deviceId = JgxxConfigFactory.findIdByName(mContext, TagConstants.XML_VALUE_NAME_SFZYDQ);
        spdkqDeviceId = JgxxConfigFactory.findIdByName(mContext, TagConstants.XML_VALUE_NAME_SPDKQ);

        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.fragment_archives_body_layout, this);

        // 加载bean的信息
        beanMap.put(Jmjbxx.class.getName(), Global.jmjbxx);
        beanMap.put(Jmjtxx.class.getName(), Global.jmjtxx);
        beanMap.put(Jmjkxx.class.getName(), Global.jmjkxx);
        beanMap.put(Jmxwxg.class.getName(), Global.jmxwxg);
        beanMap.put(Dzqy.class.getName(), Global.dzqy);
        beanMap.put(AppConfig.class.getName(), Global.appConfig);

        loadPage(context, this);
    }

    public void loadPage(Context context, ViewGroup viewGroup) {

        mGuidePager = (GuidePager) findViewById(R.id.guide_pager);
        mGuidePager.setAdapter(new MyPagerAdapter(context));
        mGuidePager.showPage(0);
        mGuidePager.setOnPageChangeListener(new OnPageChangeListener() { // 当选中某页的回调

                    @Override
                    public void onPageSelected(int index) {
                        if (mOnPageChangeListener != null) {
                            mOnPageChangeListener.onPageSelected(index);
                        }
                    }
                });

        profileImageView = (ImageView) findViewById(R.id.profileImageView);
        xmEditText = (EditText) findViewById(R.id.xmEditText);
        sexImageView = (ImageView) findViewById(R.id.sexImageView);
        sexTextView = (TextView) findViewById(R.id.sexTextView);
        ageTextView = (TextView) findViewById(R.id.ageTextView);
        sfzhEditText = (EditText) findViewById(R.id.sfzhEditText);
        dabhTextView = (TextView) findViewById(R.id.dabhTextView);
        khEditText = (EditText) findViewById(R.id.khEditText);
        mReadButton = (Button) findViewById(R.id.read_button);
        mSaveButton = (Button) findViewById(R.id.save_button);
        mUploadButton = (Button) findViewById(R.id.upload_button);
        mPrintButton = (Button) findViewById(R.id.print_button);
        mIdcSearchImg = (ImageView) findViewById(R.id.searchSfzhImageView);
        mNameSearchImg = (ImageView) findViewById(R.id.searchNameImageView);
        khSearchImg = (ImageView) findViewById(R.id.searchKhImageView);
        // 卜冷菱 450601197905035588
        // 滑夏烟 45060119790503290X
        // 葛念梦 450601197905036505
        // 毕雁荷 450601197905034227
        // 明盼夏 450601197905032627
        // 龚雪卉 450601197905036169
        // 卫语梦 450601197905036740
        // 郝新筠 450601197905036660
        // 金幻梅 450601197905031907
        // 江语梦 450601197905037321
        // 计初珍 450601197905039300
        // 季妙芙 450601197905031501
        // 康笑卉 45060119790503194X
        // 梅幻梅 450601197905037049
        // 舒飞烟 450601197905037882
        // 邵千柔 450601197905033005
        // 屈孤晴 450601197905038586
        // 经尔风 450601197905038041
        // 滑盼晴 450601197905033267
        // 祁幻巧 450601197905034286
        // 许芷荷 45060119790503880X
        // 莫新筠 450601197905034200
        // 邵青易 450601197905036361
        // 严宛菡 450601197905035027
        // // 测试
        // mResidentNameText.setText("季妙芙");
        // mPaperNumEdit.setText("450601197905031501");
        
//        if (!BluetoothUtil.isPared("CVR-100B")) {
//            mReadButton.setEnabled(false);
//        } else {
//            mReadButton.setEnabled(true);
//        }

        mPrintButton.setEnabled(false);
        if (Global.isLocalLogin) {
            mUploadButton.setEnabled(false);
        } else {
            mUploadButton.setEnabled(true);
        }
        /**
         * 实现
         */
        mPrintButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mToast.setText("正在准备打印，请稍候...");
                mToast.show();
                print();
            }
        });
        mSaveButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showMessageStatus = 0;// 保存到本地数据库操作
                saveValueToDb();
            }
        });

        mReadButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                showMessageStatus = 1;// 表示这是读卡的操作
                // 向handler发消息
                handler.sendEmptyMessage(0);
            }
        });

        mUploadButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Global.isLocalLogin) {
                    mToast.setText("当前是本地登录，不支持上传操作。");
                    mToast.show();
                    return;
                }
                // 表示是不是读卡取数据
                showMessageStatus = 2;// 上传数据的操作
                // 如果上传的时候，没有居民ID，那么先从数据库取数据和居民iD
                // 否侧直接上传到Web
                if (dabhTextView.getText().toString().equals("")) {
                    getJbxxFromDB(sfzhEditText.getText().toString().trim());
                    saveValueToWeb();
                } else {
                    saveValueToWeb();
                }
            }
        });

        mIdcSearchImg.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showMessageStatus = 3;// 根据身份证直接查找操作
                removePreviousResidentData();// 移除上一个居民的信息
                String paperNum = sfzhEditText.getText().toString();
                if (paperNum.equals("")) {
                    mToast.setText("根据身份证查找时，身份证号不能为空");
                    mToast.show();
                } else {
                    searchResident(paperNum);
                }
            }
        });

        mNameSearchImg.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showMessageStatus = 4;// 根据姓名查找操作
                removePreviousResidentData();// 移除上一个居民的信息
                String name = xmEditText.getText().toString();
                if (name.equals("")) {
                    mToast.setText("根据姓名查找时，姓名不能为空");
                    mToast.show();
                } else {
                    searchResident(name);
                }
            }
        });

        khSearchImg.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showMessageStatus = 5;// 根据卡号查找操作
                removePreviousResidentData();// 移除上一个居民的信息
                String kh = khEditText.getText().toString();
                if (kh.equals("")) {
                    mToast.setText("根据卡号查找时，卡号不能为空");
                    mToast.show();
                } else {
                    searchResident(kh);
                }
            }
        });

        if (page01 != null) {
            page01.mSexSpinner.setOnItemSelectedListener(new OnItemSelectedListener() { // 修改性别时通用栏目相应改变

                        @Override
                        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
                                long arg3) {
                            String text = page01.mSexSpinner.getSelectedData();
                            Log.i(TAG, "sex2: " + text);
                            if (text.contains("男")) {
                                sexImageView.setImageResource(R.drawable.male);
                                sexTextView.setText("男");
                            } else if (text.contains("女")) {
                                sexImageView.setImageResource(R.drawable.female);
                                sexTextView.setText("女");
                            } else {
                                sexImageView.setImageResource(R.drawable.unknown);
                                sexTextView.setText("未知");
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> arg0) {
                        }
                    });
            page01.mBirthdayText.addTextChangedListener(new TextWatcher() {

                @Override
                public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                }

                @Override
                public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                }
                
                @Override
                public void afterTextChanged(Editable arg0) {
                    ageTextView.setText(CalendarUtil.getAge(arg0.toString()) + "岁");
                }
            });
        }
        AppConfig appConfig = (AppConfig) beanMap.get(AppConfig.class.getName());
        if (appConfig != null) {
            String dalrsz = appConfig.getDalusz();
            if (dalrsz != null) {
                sfzhEditText.setEnabled(dalrsz.contains("身份证号可编辑") ? true : false);
                mIdcSearchImg.setEnabled(dalrsz.contains("按身份证号搜索档案") ? true : false);
                xmEditText.setEnabled(dalrsz.contains("姓名可编辑") ? true : false);
                mNameSearchImg.setEnabled(dalrsz.contains("按姓名搜索档案") ? true : false);
                khEditText.setEnabled(dalrsz.contains("卡号可编辑") ? true : false);
                khSearchImg.setEnabled(dalrsz.contains("按卡号搜索档案") ? true : false);
            }
        }
    }

    /**
     * 
     */
    protected void print() {
        mToast.setText("暂不支持打印功能。");
        mToast.show();
    }

    
    private void atuoReadKhService() { // 读射频卡

        if (mBluethoothClient4Hande == null) {
            mBluethoothClient4Hande = new BluetoothClient4Hande(mContext);
        }
        mBluethoothClient4Hande.setOnConnectedListener(new OnConnectedListener() {
            @Override
            public void onConnected(boolean isSuccess) {
                mToast.setText("平板与射频读卡器已建立连接。");
                mToast.show();
            }
        });
        mBluethoothClient4Hande
                .setOnReceiveListener(new OnReceiveListener() {
                    @Override
                    public void onReceiveIdcard(int type, String snr) {
                        String text = "type:" + type + ", snr:" + snr;
                        Log.i(TAG, text);
                khEditText.requestFocus();
                khEditText.setText(snr);
                    }
                });
        mBluethoothClient4Hande.setOnDisconnectListener(new OnDisconnectListener() {

            @Override
            public void onDisconnect(boolean isSuccess) {
                Log.i(TAG, "onDisconnect: " + isSuccess);
                mToast.setText("平板与射频读卡器已断开连接。");
                mToast.show();
            }
        });
        mBluethoothClient4Hande.run(new OnDialogResultListener() {
            
            @Override
            public void onConfirm() {// 显示进度条
                mToast.setText("射频读卡器功能将失效。");
                mToast.show();
            }
            
            @Override
            public void onCancel() {
            }
        });
    }
    
    private void searchResident(final String searchString) {
        if (Global.isLocalLogin) {
            getJbxxFromDB(searchString);
        } else {
            getJbxxFromWeb(searchString);
        }
    }

    private void saveValueToWeb() {
        String stringuserID = MyApplication.getInstance().getSession().getLoginResult().response.userID;
        //int userID = Integer.parseInt(stringuserID);
        if (getValue()) {
            // 先判断这个人是不是第一建档
            Jmjtxx mJmjtxx = (Jmjtxx) beanMap.get(Jmjtxx.class.getName());
            Jmjbxx mJmjbxx = (Jmjbxx) beanMap.get(Jmjbxx.class.getName());
            if (mJmjbxx.getResidentID().trim() == null || mJmjbxx.getResidentID().trim().equals("")) {// 说明是第一次建档
                firstAddToWeb();// 新增
            } else {
                editToWeb();// 编辑
            }
        }
    }

    public void firstAddToWeb() {
        uploadToWeb(1);
    }
    public void uploadToWeb(int operType) {
        Jmjbxx mJmjbxx = (Jmjbxx) beanMap.get(Jmjbxx.class.getName());
        Jmjtxx mJmjtxx = (Jmjtxx) beanMap.get(Jmjtxx.class.getName());

        String stringuserID = MyApplication.getInstance().getSession().getLoginResult().response.userID;

        BcjmjbxxhjtxxJ003 bcjmjbxxhjtxxJ003 = new BcjmjbxxhjtxxJ003();
        bcjmjbxxhjtxxJ003.request = new BcjmjbxxhjtxxJ003.Request();

        bcjmjbxxhjtxxJ003.request.type = operType;// 新增存盘
        bcjmjbxxhjtxxJ003.request.userID = stringuserID;
        bcjmjbxxhjtxxJ003.request.familyID = mJmjtxx.getFamilyID();// 新增的时候是空的
        bcjmjbxxhjtxxJ003.request.residentID = mJmjbxx.getResidentID();// 新增的时候是空的
        bcjmjbxxhjtxxJ003.request.residentName = mJmjbxx.getResidentName();
        bcjmjbxxhjtxxJ003.request.sexCD = mJmjbxx.getSexCD();
        bcjmjbxxhjtxxJ003.request.birthDay = mJmjbxx.getBirthDay();
        bcjmjbxxhjtxxJ003.request.paperNum = mJmjbxx.getPaperNum();
        bcjmjbxxhjtxxJ003.request.cardID = mJmjbxx.getCardID();
        bcjmjbxxhjtxxJ003.request.addressType = mJmjbxx.getAddressTypeCD();
        bcjmjbxxhjtxxJ003.request.bloodRh = new BeanCD(mJmjbxx.getRh(), "");

        if (mJmjbxx.getNowProvince() != null) {
            bcjmjbxxhjtxxJ003.request.nowProvince = mJmjbxx.getNowProvince();
        }
        if (mJmjbxx.getNowCity() != null) {
            bcjmjbxxhjtxxJ003.request.nowCity = mJmjbxx.getNowCity();
        }
        if (mJmjbxx.getNowDistrict() != null) {
            bcjmjbxxhjtxxJ003.request.nowDistrict = mJmjbxx.getNowDistrict();
        }
        if (mJmjbxx.getNowStreet() != null) {
            bcjmjbxxhjtxxJ003.request.nowStreet = mJmjbxx.getNowStreet();
        }
        if (mJmjbxx.getNowZone() != null) {
            bcjmjbxxhjtxxJ003.request.nowZone = mJmjbxx.getNowZone();
        }

        if (mJmjbxx.getNowRoad() != null) {
            bcjmjbxxhjtxxJ003.request.nowRoadCD = mJmjbxx.getNowRoad().getiD();
            bcjmjbxxhjtxxJ003.request.nowRoad = mJmjbxx.getNowRoad().getTagValue();
        }

        bcjmjbxxhjtxxJ003.request.nowN = mJmjbxx.getNowN();
        bcjmjbxxhjtxxJ003.request.nowH = mJmjbxx.getNowH();
        bcjmjbxxhjtxxJ003.request.nowS = mJmjbxx.getNowS();

        if (mJmjbxx.getNowProvince() != null) {
            // TODO:户籍地址
        }
        if (mJmjbxx.getNowCity() != null) {
            // TODO:户籍地址
        }
        if (mJmjbxx.getNowDistrict() != null) {
            // TODO:户籍地址
        }
        if (mJmjbxx.getNowStreet() != null) {
            // TODO:户籍地址
        }
        if (mJmjbxx.getNowZone() != null) {
            // TODO:户籍地址
        }
        bcjmjbxxhjtxxJ003.request.regDetail = mJmjbxx.getRegDetail();
        bcjmjbxxhjtxxJ003.request.resideStatusCD = mJmjbxx.getResideStatusCD();
        bcjmjbxxhjtxxJ003.request.regTypeCD = mJmjbxx.getRegTypeCD();
        bcjmjbxxhjtxxJ003.request.regAddress = mJmjbxx.getRegAddress();// 户籍地址是不是身份证上面的地址
        bcjmjbxxhjtxxJ003.request.workUnit = mJmjbxx.getWorkUnit();
        bcjmjbxxhjtxxJ003.request.selfPhone = mJmjbxx.getSelfPhone();
        bcjmjbxxhjtxxJ003.request.relaName = mJmjbxx.getRelaName();
        bcjmjbxxhjtxxJ003.request.relaPhone = mJmjbxx.getRelaPhone();
        bcjmjbxxhjtxxJ003.request.resideCD = mJmjbxx.getResideCD();
        if (mJmjbxx.getFlokCD() != null) {
            bcjmjbxxhjtxxJ003.request.folkCD = mJmjbxx.getFlokCD();
        }
        bcjmjbxxhjtxxJ003.request.bloodCD = mJmjbxx.getBloodCD();
        bcjmjbxxhjtxxJ003.request.educationCD = mJmjbxx.getEducationCD();
        if (mJmjbxx.getVocationCD() != null) {
            bcjmjbxxhjtxxJ003.request.vocationCD = mJmjbxx.getVocationCD();
        }
        bcjmjbxxhjtxxJ003.request.marriageCD = mJmjbxx.getMarriageCD();
        bcjmjbxxhjtxxJ003.request.insuranceCD = mJmjbxx.getInsuranceCD();
        bcjmjbxxhjtxxJ003.request.insuranceNum = mJmjbxx.getInsuranceNum();
        bcjmjbxxhjtxxJ003.request.aidCD = mJmjbxx.getAidCD();
        if (mJmjbxx.getNationalityCD() != null) {
            bcjmjbxxhjtxxJ003.request.nationalityCD = mJmjbxx.getNationalityCD();
        }
        if (mJmjbxx.getRelation() != null) {
            bcjmjbxxhjtxxJ003.request.relation = mJmjbxx.getRelation();
        }
        bcjmjbxxhjtxxJ003.request.zip = mJmjbxx.getZip();
        bcjmjbxxhjtxxJ003.request.email = mJmjbxx.getEmail();
        bcjmjbxxhjtxxJ003.request.manuaINm = mJmjbxx.getManuaINm();

        // 责任医生的设置
        BeanID doctor = null;
        if (mJmjbxx.getDutyDoctor() != null) {
            doctor = mJmjbxx.getDutyDoctor();
        } else {
            Login1.Response userLogin1Response = MyApplication.getInstance().getSession()
                    .getLoginResult().response;
            if (userLogin1Response != null) {
                doctor = new BeanID(userLogin1Response.doctorID, userLogin1Response.doctorName);
            }
        }
        bcjmjbxxhjtxxJ003.request.dutyDoctor = doctor;

        // 管理机构的设置
        BeanID manageOrg = null;
        if (mJmjbxx.getManageOrg() != null && !StringUtil.isEmptyString(mJmjbxx.getManageOrg().getiD())) {
            manageOrg = mJmjbxx.getManageOrg();
        } else {
            BeanID session_manageOrg = MyApplication.getInstance().getSession().getManageOrg();
            if (session_manageOrg != null) {
                manageOrg = session_manageOrg;
            } else {// 默认的设置
                manageOrg = new BeanID(Global.orgCode, Global.orgName);
            }
        }
        bcjmjbxxhjtxxJ003.request.manageOrg = manageOrg;

        // 设置服务站点
        BeanID station = null;
        if (mJmjbxx.getStation() != null && !StringUtil.isEmptyString(mJmjbxx.getStation().getiD())) {
            station = mJmjbxx.getStation();
        } else {
            BeanID session_station = MyApplication.getInstance().getSession().getStation();
            if (session_station != null && !StringUtil.isEmptyString(session_station.getiD())) {
                station = session_station;
            } else {// 默认设置
                station = manageOrg;
            }
        }
        bcjmjbxxhjtxxJ003.request.station = station;

        // 建档日期
        if (mJmjbxx.getBuildDate() != null && !mJmjbxx.getBuildDate().equals("")) {
            bcjmjbxxhjtxxJ003.request.buildDate = mJmjbxx.getBuildDate();
        } else {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            bcjmjbxxhjtxxJ003.request.buildDate = format.format(new Date());
        }

        // 建档人员
        BeanID builder = null;
        if (mJmjbxx.getBuilder() != null) {
            builder = mJmjbxx.getBuilder();
        } else {
            Login1.Response userLogin1Response = MyApplication.getInstance().getSession()
                    .getLoginResult().response;
            if (userLogin1Response != null) {
                // 用当前医生ID和名字 作为建档人员
                builder = new BeanID(userLogin1Response.doctorID, userLogin1Response.doctorName);
            }
        }
        bcjmjbxxhjtxxJ003.request.builder = builder;

        // 建档机构
        BeanID buildOrg = null;
        if (mJmjbxx.getBuildOrg() != null) {
            buildOrg = mJmjbxx.getBuildOrg();
        } else {
            BeanID session_buildOrg = MyApplication.getInstance().getSession().getBuildOrg();
            if (session_buildOrg != null) {
                buildOrg = session_buildOrg;
            } else {// 默认的设置
                buildOrg = new BeanID(Global.orgCode, Global.orgName);
            }
        }
        bcjmjbxxhjtxxJ003.request.buildOrg = buildOrg;

        // if (bcjmjbxxhjtxxJ003.request.type == 1) {// 如果是新建档案的情况,传体检的数据
        // Jktj_kstj mJktj_kstj = (Jktj_kstj)
        // beanMap.get(Jktj_kstj.class.getName());
        // if (mJktj_kstj != null) {
        // bcjmjbxxhjtxxJ003.request.height = mJktj_kstj.getHeight();
        // bcjmjbxxhjtxxJ003.request.weight = mJktj_kstj.getWeight();
        // bcjmjbxxhjtxxJ003.request.bMI = mJktj_kstj.getbMI();
        // bcjmjbxxhjtxxJ003.request.bust = mJktj_kstj.getBust();
        // bcjmjbxxhjtxxJ003.request.hIP = mJktj_kstj.gethIP();
        // bcjmjbxxhjtxxJ003.request.waist = mJktj_kstj.getWaist();
        // }
        // }
        bcjmjbxxhjtxxJ003.request.height = mJmjbxx.getHeight();
        bcjmjbxxhjtxxJ003.request.weight = mJmjbxx.getWeight();
        bcjmjbxxhjtxxJ003.request.bMI = mJmjbxx.getBmi();
        bcjmjbxxhjtxxJ003.request.bust = mJmjbxx.getBust();
        bcjmjbxxhjtxxJ003.request.hIP = mJmjbxx.gethIP();
        bcjmjbxxhjtxxJ003.request.waist = mJmjbxx.getWaist();

        bcjmjbxxhjtxxJ003.request.fileStatusCD = mJmjbxx.getFileStatusCD();

        /**
         * 家庭信息部分
         */
        bcjmjbxxhjtxxJ003.request.familyTypeCD = mJmjtxx.getFamilyTypeCD();
        bcjmjbxxhjtxxJ003.request.incomeCD = mJmjtxx.getIncomeCD();
        bcjmjbxxhjtxxJ003.request.houseHoldCD = mJmjtxx.getHouseHoldCD();
        bcjmjbxxhjtxxJ003.request.population = mJmjtxx.getPopulation() + "";
        bcjmjbxxhjtxxJ003.request.economics = mJmjtxx.getEconomics();
        bcjmjbxxhjtxxJ003.request.housingLighting = mJmjtxx.getHousingLighting();
        bcjmjbxxhjtxxJ003.request.housingRooms = mJmjtxx.getHousingRooms() + "";
        bcjmjbxxhjtxxJ003.request.housingVentilation = mJmjtxx.getHousingVentilation();
        bcjmjbxxhjtxxJ003.request.housingWarm = mJmjtxx.getHousingWarm();
        bcjmjbxxhjtxxJ003.request.airHumidity = mJmjtxx.getAirHumidity();
        bcjmjbxxhjtxxJ003.request.healthStatus = mJmjtxx.getHealthStatus();
        bcjmjbxxhjtxxJ003.request.waterStatus = mJmjtxx.getWaterStatus();
        bcjmjbxxhjtxxJ003.request.sewageTreatment = mJmjtxx.getSewageTreatment();
        bcjmjbxxhjtxxJ003.request.stylisticDevices = mJmjtxx.getStylisticDevices();
        bcjmjbxxhjtxxJ003.request.smokeRemoval = mJmjtxx.getSmokeRemoval();
        bcjmjbxxhjtxxJ003.request.familyMember = mJmjtxx.getFamilyMember();
        bcjmjbxxhjtxxJ003.request.familyMainProblems = mJmjtxx.getFamilyMainProblems();
        bcjmjbxxhjtxxJ003.request.area = mJmjtxx.getArea();
        bcjmjbxxhjtxxJ003.request.avgArea = mJmjtxx.getAvgArea();
        bcjmjbxxhjtxxJ003.request.floorTypeCD = mJmjtxx.getFloorTypeCD();
        bcjmjbxxhjtxxJ003.request.kitchenUseCD = mJmjtxx.getKitchenUseCD();
        bcjmjbxxhjtxxJ003.request.kitchenFanCD = mJmjtxx.getKitchenFanCD();
        bcjmjbxxhjtxxJ003.request.waterCD = mJmjtxx.getWaterCD();
        bcjmjbxxhjtxxJ003.request.fuelCD = mJmjtxx.getFuelCD();
        bcjmjbxxhjtxxJ003.request.sanToiletCD = mJmjtxx.getSanToiletCD();
        bcjmjbxxhjtxxJ003.request.notSanToiletCD = mJmjtxx.getNotSanToiletCD();
        bcjmjbxxhjtxxJ003.request.animalPlaceCD = mJmjtxx.getAnimalPlaceCD();
        bcjmjbxxhjtxxJ003.request.garbageDealCD = mJmjtxx.getGarbageDealCD();
        bcjmjbxxhjtxxJ003.request.applianceCD = mJmjtxx.getApplianceCD();
        bcjmjbxxhjtxxJ003.request.transport = mJmjtxx.getTransport();
        bcjmjbxxhjtxxJ003.request.housingProperty = mJmjtxx.getHousingProperty();

        List<IDto> beanList = new ArrayList<IDto>();
        // 注意，以下顺序不能改变 --徐卓为
        beanList.add(bcjmjbxxhjtxxJ003); // 添加保存居民基本信息idto
        BeanUtil.getInstance().saveBeanToWeb(beanList, new OnResultFromWeb() {
            @Override
            public void onResult(List<IDto> listBean, boolean isSucc) {
                if (isSucc) {
                    StringBuilder sb = new StringBuilder();
                    BcjmjbxxhjtxxJ003 responseBcjmjbxxhjtxxJ003 = (BcjmjbxxhjtxxJ003) listBean
                            .get(0);
                    if (responseBcjmjbxxhjtxxJ003 == null
                            || responseBcjmjbxxhjtxxJ003.response == null) {
                        sb.append("【居民基本信息和家庭信息】服务器接口异常");
                    } else {
                        if (responseBcjmjbxxhjtxxJ003.response.errMsg.length() > 0) {
                            sb.append("【居民基本信息和家庭信息】" + responseBcjmjbxxhjtxxJ003.response.errMsg);
                        } else {
                            // 保存成功的话，更新居民基本信息的id和famliyID
                            saveResidentID_FamilyID(responseBcjmjbxxhjtxxJ003.response.residentID,
                                    responseBcjmjbxxhjtxxJ003.response.familyID);
                            sb.append("【居民基本信息】修改成功");
                            if (responseBcjmjbxxhjtxxJ003.response.returnType == 1) {
                                sb.append("\n【居民家庭信息】修改成功");
                            } else if (responseBcjmjbxxhjtxxJ003.response.returnType == 2) {
                                sb.append("\n【居民家庭信息】有重复家庭");
                            }
                            saveProfileToWeb(responseBcjmjbxxhjtxxJ003.response.residentID);// 保存身份证头像到Web
                            saveJmjkxx_Jmxwxg_DzqyToWeb(false);// 在取保存健康信息和行为习惯
                        }
                    }
                    mToast.setDuration(Toast.LENGTH_LONG);
                    mToast.setText(sb.toString());
                    mToast.show();

                } else {
                    mToast.setDuration(Toast.LENGTH_SHORT);
                    mToast.setText("网络请求异常,数据暂存本地");
                    mToast.show();
                    // saveValueToDb();
                }

            }
        });

    }

    public void editToWeb() {
        uploadToWeb(2);
    }

    public void saveProfileToWeb(String residentID) {
        Jmjbxx mjJmjbxx = (Jmjbxx) beanMap.get(Jmjbxx.class.getName());
        // userId
        String stringuserID = MyApplication.getInstance().getSession().getLoginResult().response.userID;
        // 获取图片的字符串数据
        String photoString = FileHelper.getInstance().getBitmapString(mjJmjbxx.getPaperNum());
        if (photoString == null || photoString.equals(""))
            return;
        BcsfzzpJ002 bcsfzzpJ002 = new BcsfzzpJ002();
        bcsfzzpJ002.request = new BcsfzzpJ002.Request();
        bcsfzzpJ002.request.residentID = residentID;
        bcsfzzpJ002.request.userId = stringuserID;
        bcsfzzpJ002.request.photo = photoString;

        List<IDto> beanList = new ArrayList<IDto>();
        // 注意，以下顺序不能改变 --徐卓为
        beanList.add(bcsfzzpJ002); // 添加保存居民基本信息idto
        BeanUtil.getInstance().saveBeanToWeb(beanList, new OnResultFromWeb() {
            @Override
            public void onResult(List<IDto> listBean, boolean isSucc) {
                if (isSucc) {
                    StringBuilder sb = new StringBuilder();
                    BcsfzzpJ002 responseBcsfzzpJ002 = (BcsfzzpJ002) listBean.get(0);
                    if (responseBcsfzzpJ002 == null || responseBcsfzzpJ002.response == null) {
                        sb.append("【保存身份证照片】服务器接口异常");
                    } else {
                        if (responseBcsfzzpJ002.response.errMsg.length() > 0) {
                            sb.append("【保存身份证照片】" + responseBcsfzzpJ002.response.errMsg);
                        } else {
                            sb.append("【保存身份证照片】新增成功");
                        }
                    }
                }
            }
        });
    }

    /**
     * 
     * @param saveJmjtxx
     *            true表示是从编辑状态到这一步 false表示从新增状态到这一步
     */
    public void saveJmjkxx_Jmxwxg_DzqyToWeb(final boolean saveJmjtxx) {
        String stringuserID = MyApplication.getInstance().getSession().getLoginResult().response.userID;
        Jmjbxx mJmjbxx = (Jmjbxx) beanMap.get(Jmjbxx.class.getName());
        Jmjtxx mJmjtxx = (Jmjtxx) beanMap.get(Jmjtxx.class.getName());

        // 保存居民健康信息8.2
        Jmjkxx mJmjkxx = (Jmjkxx) beanMap.get(Jmjkxx.class.getName());
        JmjkxxbcJ007 jmjkxxbcJ007 = new JmjkxxbcJ007();
        jmjkxxbcJ007.request = new JmjkxxbcJ007.Request();
        jmjkxxbcJ007.request.userID = stringuserID;
        jmjkxxbcJ007.request.familyID = mJmjtxx.getFamilyID();
        jmjkxxbcJ007.request.residentID = mJmjbxx.getResidentID();
        jmjkxxbcJ007.request.fatherCD = mJmjkxx.getFatherCD();
        jmjkxxbcJ007.request.fatherName = mJmjkxx.getFatherName();        
        jmjkxxbcJ007.request.motherCD = mJmjkxx.getMotherCD();
        jmjkxxbcJ007.request.motherName = mJmjkxx.getMotherName();        
        jmjkxxbcJ007.request.brotherCD = mJmjkxx.getBrotherCD();
        jmjkxxbcJ007.request.brotherName = mJmjkxx.getBrotherName();
        jmjkxxbcJ007.request.childCD = mJmjkxx.getChildCD();
        jmjkxxbcJ007.request.childName = mJmjkxx.getChildName();
        jmjkxxbcJ007.request.otherMemberCD = mJmjkxx.getOtherMemberCD();
        jmjkxxbcJ007.request.otherMemberCD = "1";// 默认没有
        jmjkxxbcJ007.request.deformityCD = mJmjkxx.getDeformityCD();
        jmjkxxbcJ007.request.deformityCardNo = mJmjkxx.getDeformityCardNo();
        jmjkxxbcJ007.request.deformityLevel = mJmjkxx.getDeformityLevel();
        jmjkxxbcJ007.request.deformityName = mJmjkxx.getDeformityName();
        jmjkxxbcJ007.request.heredityCD = mJmjkxx.getHeredityCD();
        jmjkxxbcJ007.request.heredityName = mJmjkxx.getHeredityName();
        jmjkxxbcJ007.request.exposureCD = mJmjkxx.getExposureCD();
        jmjkxxbcJ007.request.exposureName = mJmjkxx.getExposureName();

        if (mJmjkxx.getHistoryDisease() != null) {
            ArrayList<HistoryDisease> historyDiseases = new ArrayList<HistoryDisease>();

            List<HistoryDisease> from = mJmjkxx.getHistoryDisease();
            for (int i = 0; i < from.size(); i++) {
                HistoryDisease hd = from.get(i);
                HistoryDisease h = new HistoryDisease();
                h.hDType = hd.gethDType();
                h.disSn = hd.getDisSn();
                h.iCD10 = hd.getiCD10();
                h.disease = hd.getDisease();
                h.diagnoseDate = hd.getDiagnoseDate();
                h.happenDate = hd.getHappenDate();
                h.resultCD = hd.getResultCD();
                h.hDReason = hd.gethDReason();
                h.cureHos = hd.getCureHos();

                historyDiseases.add(h);
            }
            jmjkxxbcJ007.request.historyDisease = historyDiseases;
        }

        if (mJmjkxx.getHistoryHyper() != null) {
            ArrayList<HistoryHyper> historyHypers = new ArrayList<HistoryHyper>();
            List<HistoryHyper> from = mJmjkxx.getHistoryHyper();
            for (HistoryHyper historyHyper : from) {
                HistoryHyper hh = new HistoryHyper();
                hh.hyperTypeCD = historyHyper.getHyperTypeCD();
                hh.hyperSn = historyHyper.getDisSn();
                hh.hyperSource = historyHyper.getHyperSource();
                hh.happenDate = historyHyper.getHappenDate();
                hh.hyperReason = historyHyper.getHyperReason();
                hh.cureDes = historyHyper.getCureDes();
                historyHypers.add(hh);
            }
            jmjkxxbcJ007.request.historyHyper = historyHypers;
        }

        /**
         * 居民行为习惯
         */
        Jmxwxg mJmxwxg = (Jmxwxg) beanMap.get(Jmxwxg.class.getName());
        Bcjmxwxg8_1 bcjmxwxg8_1 = new Bcjmxwxg8_1();
        bcjmxwxg8_1.request = new Bcjmxwxg8_1.Request();
        bcjmxwxg8_1.request.userID = stringuserID;
        bcjmxwxg8_1.request.familyID = mJmjtxx.getFamilyID();
        bcjmxwxg8_1.request.residentID = mJmjbxx.getResidentID();
        bcjmxwxg8_1.request.smokeCD = mJmxwxg.getSmokeCD();
        bcjmxwxg8_1.request.smokeAge = mJmxwxg.getSmokeAge();
        bcjmxwxg8_1.request.noSmokeAge = mJmxwxg.getNoSmokeAge();
        bcjmxwxg8_1.request.smokeDay = mJmxwxg.getSmokeDay();
        bcjmxwxg8_1.request.smokeDayPast = mJmxwxg.getSmokeDayPast();
        bcjmxwxg8_1.request.drinkTypeCD = mJmxwxg.getDrinkTypeCD();
        bcjmxwxg8_1.request.drinkAmount = mJmxwxg.getDrinkAmount();
        bcjmxwxg8_1.request.drinkCD = mJmxwxg.getDrinkCD();
        bcjmxwxg8_1.request.noDrinkCD = mJmxwxg.getNoDrinkCD();
        bcjmxwxg8_1.request.noDrinkAge = mJmxwxg.getNoDrinkAge();
        bcjmxwxg8_1.request.pastDrinkNum = mJmxwxg.getPastDrinkNum();
        bcjmxwxg8_1.request.pastDrinkAmount = mJmxwxg.getPastDrinkAmount();
        bcjmxwxg8_1.request.pastDrinkTypeCD = mJmxwxg.getPastDrinkTypeCD();
        bcjmxwxg8_1.request.foodCD = mJmxwxg.getFoodCD();
        bcjmxwxg8_1.request.brushTeethCD = mJmxwxg.getBrushTeethCD();
        bcjmxwxg8_1.request.sportRateCD = mJmxwxg.getSportRateCD();
        bcjmxwxg8_1.request.sportTypeCD = mJmxwxg.getSportTypeCD();
        bcjmxwxg8_1.request.sportTypeElse = mJmxwxg.getSportTypeElse();
        bcjmxwxg8_1.request.sportTime = mJmxwxg.getSportTime();
        bcjmxwxg8_1.request.primaryEventCD = mJmxwxg.getPrimaryEvent();
        bcjmxwxg8_1.request.primaryEventName = mJmxwxg.getPrimaryEventName();

        /**
         * 保存居民家庭信息 居民家庭信息可能没有，要判断到底保不保存
         */
        Bcjtxxxx6 bcjtxxxx6 = new Bcjtxxxx6();
        bcjtxxxx6.request = new Bcjtxxxx6.Request();
        bcjtxxxx6.request.userID = stringuserID;
        bcjtxxxx6.request.familyID = mJmjtxx.getFamilyID();
        if (mJmjbxx.getNowStreet() != null) {
            bcjtxxxx6.request.street = mJmjbxx.getNowStreet();
        }
        if (mJmjbxx.getNowZone() != null) {
            bcjtxxxx6.request.zone = mJmjbxx.getNowZone();
        }
        if (mJmjbxx.getNowRoad() != null) {
            bcjtxxxx6.request.road = mJmjbxx.getNowRoad();
        }
        bcjtxxxx6.request.n = mJmjbxx.getNowN();
        bcjtxxxx6.request.h = mJmjbxx.getNowH();
        bcjtxxxx6.request.s = mJmjbxx.getNowS();
        bcjtxxxx6.request.familyTypeCD = mJmjtxx.getFamilyTypeCD();
        bcjtxxxx6.request.incomeCD = mJmjtxx.getIncomeCD();
        bcjtxxxx6.request.houseHoldCD = mJmjtxx.getHouseHoldCD();
        bcjtxxxx6.request.area = mJmjtxx.getArea();
        bcjtxxxx6.request.avgArea = mJmjtxx.getAvgArea();
        bcjtxxxx6.request.floorTypeCD = mJmjtxx.getFloorTypeCD();
        bcjtxxxx6.request.kitchenUseCD = mJmjtxx.getKitchenUseCD();
        bcjtxxxx6.request.kitchenFanCD = mJmjtxx.getKitchenFanCD();
        bcjtxxxx6.request.waterCD = mJmjtxx.getWaterCD();
        bcjtxxxx6.request.fuelCD = mJmjtxx.getFuelCD();
        bcjtxxxx6.request.sanToiletCD = mJmjtxx.getSanToiletCD();
        bcjtxxxx6.request.notSanToiletCD = mJmjtxx.getNotSanToiletCD();
        bcjtxxxx6.request.animalPlaceCD = mJmjtxx.getAnimalPlaceCD();
        bcjtxxxx6.request.garbageDealCD = mJmjtxx.getGarbageDealCD();
        bcjtxxxx6.request.applianceCD = mJmjtxx.getApplianceCD();
        bcjtxxxx6.request.transport = mJmjtxx.getTransport();

        /**
         * 电子签约
         */
        Dzqy dzqy = (Dzqy) beanMap.get(Dzqy.class.getName());
        BcqyqkHrs01 bcqyqkHrs01 = new BcqyqkHrs01();
        bcqyqkHrs01.request = new BcqyqkHrs01.Request();
        bcqyqkHrs01.request.ResidentID = mJmjbxx.getResidentID();
        bcqyqkHrs01.request.UserID = stringuserID;
        bcqyqkHrs01.request.SignContract = dzqy.SignContract;
        bcqyqkHrs01.request.SignDate = dzqy.SignDate;
        bcqyqkHrs01.request.SignPlace = dzqy.SignPlace;
        bcqyqkHrs01.request.SignDoctor = dzqy.SignDoctor;
        bcqyqkHrs01.request.SignUnit = dzqy.SignUnit;
        bcqyqkHrs01.request.SignType = dzqy.SignType;
        bcqyqkHrs01.request.SignKnow = dzqy.SignKnow;
        bcqyqkHrs01.request.SignPhone = dzqy.SignPhone;
        bcqyqkHrs01.request.SignStatus = dzqy.SignStatus;
        bcqyqkHrs01.request.OperDoctor = dzqy.OperDoctor;
        // bcqyqkHrs01.request.deviceUses = dzqy.deviceUses;

        List<IDto> beanList = new ArrayList<IDto>();
        // 注意，以下顺序不能改变 --徐卓为

        beanList.add(bcjmxwxg8_1); // 保存居民行为习惯8.1idto
        beanList.add(jmjkxxbcJ007); // 保存居民健康信息8.2idto
        beanList.add(bcqyqkHrs01); // 保存签约情况
        if (mJmjtxx.getFamilyID() != null && !mJmjtxx.getFamilyID().equals("") && saveJmjtxx) {
            beanList.add(bcjtxxxx6); // 添加保存居民家庭信息信息idto
            bcjtxxxx6.request.type = 2;// 目前只能是2 编辑
        }

        BeanUtil.getInstance().saveBeanToWeb(beanList, new OnResultFromWeb() {
            @Override
            public void onResult(List<IDto> listBean, boolean isSucc) {
                if (isSucc) {
                    StringBuilder sb = new StringBuilder();
                    Bcjmxwxg8_1 responseBcjmjkxx8_1 = (Bcjmxwxg8_1) listBean.get(0);
                    if (responseBcjmjkxx8_1 == null || responseBcjmjkxx8_1.response == null) {
                        sb.append("【居民行为习惯】服务器接口异常");
                    } else {
                        if (responseBcjmjkxx8_1.response.errMsg.length() > 0) {
                            sb.append("【居民行为习惯】" + responseBcjmjkxx8_1.response.errMsg);
                        } else {
                            sb.append("【居民行为习惯】上传成功");
                        }
                    }

                    JmjkxxbcJ007 responseJmjkxxbcJ007 = (JmjkxxbcJ007) listBean.get(1);
                    if (responseJmjkxxbcJ007 == null || responseJmjkxxbcJ007.response == null) {
                        sb.append("\n");
                        sb.append("【居民健康信息】服务器接口异常");
                    } else {
                        if (responseJmjkxxbcJ007.response.errMsg.length() > 0) {
                            sb.append("\n");
                            sb.append("【居民健康信息】" + responseJmjkxxbcJ007.response.errMsg);
                        } else {
                            sb.append("\n");
                            sb.append("【居民健康信息】上传成功");
                        }
                    }

                    BcqyqkHrs01 bcqyqkHrs01 = (BcqyqkHrs01) listBean.get(2);
                    if (bcqyqkHrs01 == null || bcqyqkHrs01.response == null) {
                        sb.append("\n");
                        sb.append("【保存签约情况】服务器接口异常");
                    } else {
                        if (bcqyqkHrs01.response.errMsg.length() > 0) {
                            sb.append("\n");
                            sb.append("【保存签约情况】" + bcqyqkHrs01.response.errMsg);
                        } else {
                            sb.append("\n");
                            sb.append("【保存签约情况】上传成功");
                        }
                    }

                    Jmjtxx jmjtxx = (Jmjtxx) beanMap.get(Jmjtxx.class.getName());
                    if (jmjtxx.getFamilyID() != null && !jmjtxx.getFamilyID().equals("")) {
                        if (saveJmjtxx) {
                            Bcjtxxxx6 responseBcjtxxxx6 = (Bcjtxxxx6) listBean.get(3);
                            if (responseBcjtxxxx6 == null || responseBcjtxxxx6.response == null) {
                                sb.append("\n");
                                sb.append("【家庭详细信息】服务器接口异常");
                            } else {
                                if (responseBcjtxxxx6 != null
                                        && responseBcjtxxxx6.response.errMsg.length() > 0) {
                                    sb.append("\n");
                                    sb.append("【家庭详细信息】" + responseBcjtxxxx6.response.errMsg);
                                } else {
                                    sb.append("\n");
                                    sb.append("【家庭详细信息】上传成功");
                                }
                            }
                        }
                    } else {
                        sb.append("\n");
                        sb.append("【家庭详细信息】因服务器问题，暂时不能上传");
                    }
                } else {
                    mToast.setDuration(Toast.LENGTH_SHORT);
                    mToast.setText("网络请求异常,数据暂存本地");
                    mToast.show();
                }

                // saveValueToDb();
            }
        });
    }

    // }

    protected void saveResidentID_FamilyID(String residentID, String familyID) {
        // getValue();
        Jmjbxx mJmjbxx = (Jmjbxx) beanMap.get(Jmjbxx.class.getName());
        Jmjtxx mJmjtxx = (Jmjtxx) beanMap.get(Jmjtxx.class.getName());
        mJmjbxx.setResidentID(residentID);
        mJmjtxx.setFamilyID(familyID);
        // setValue();
        // saveValueToDb();
    }

    private void saveValueToDb() {
        // 从ui提取数据到bean
        if (getValue()) {
            // 保存数据到数据库
            final Jmjbxx mJmjbxx = (Jmjbxx) beanMap.get(Jmjbxx.class.getName());
            // 将省市等保存到NowDetail节点
            if (mJmjbxx != null) {
                mJmjbxx.setNowDetail(getNowDetail(mJmjbxx));
            }
            Jmjtxx mJmjtxx = (Jmjtxx) beanMap.get(Jmjtxx.class.getName());
            Jmjkxx mJmjkxx = (Jmjkxx) beanMap.get(Jmjkxx.class.getName());
            Jmxwxg mJmxwxg = (Jmxwxg) beanMap.get(Jmxwxg.class.getName());
            if (mJmjbxx == null) {
                mToast.setText("用户基本信息填写不完整");
                mToast.show();
                return;
            } else {
                // 1.写居民登记信息表
                Resident bean = new Resident();
                bean.setPaperNum(mJmjbxx.getPaperNum());
                bean.setResidentID(mJmjbxx.getResidentID());
                bean.setResidentName(mJmjbxx.getResidentName());
                bean.setCardId(mJmjbxx.getCardID());
                ResidentBll.saveBean(bean);

                // 2.写session全局信息
                loadResidentInfoIntoSession(mJmjbxx.getPaperNum());
            }

            // 3.写居民基本信息表
            List<IBean> listBean = new ArrayList<IBean>();
            listBean.add(mJmjbxx);
            listBean.add(mJmjtxx);
            listBean.add(mJmjkxx);
            listBean.add(mJmxwxg);

            BeanUtil.getInstance().saveJbxxToDb(listBean, new OnResultSaveToDb() {
                @Override
                public void onResult(List<SaveToDbResult> listBean, boolean isSucc) {
                    if (isSucc) {
                        if (showMessageStatus == 0) {// 只有是保存到本地数据库，才提示这个信息
                            mToast.setText("数据更新到数据库");
                            mToast.show();
                        }

                        SaveToDbResult result = listBean.get(0);
                        // 操作标志 1-原始 2-新建 3-修改
                        int operType = result.isAdd ? 2 : 3;
                        int dataSource = 0; // 0 代表不改变原来的状态 ，1 代表档案平台，2代表非档案平台
                        // 保存本地记录
                        if (Global.isLocalLogin && showMessageStatus == 1) { // 本地登陆且读卡模式则非档案平台
                            dataSource = 2;
                        } else if (operType == 2) { // 第一次插入数据库
                            dataSource = 1;
                        }

                        // 数据来源 1-档案平台 2非档案平台
                        // 保存本地记录
                        BeanUtil.getInstance().saveLocalRecord(mContext, mJmjbxx,
                                result.projectUUID, "21", dataSource, operType,
                                new Date().getTime(),
                                mJmjbxx.getClass().getName());
                    }
                }
            });
        }
    }

    // 在Myapplication中维持一个该居民信息的全局对象
    private void loadResidentInfoIntoSession(String paperNum) {
        Resident bean = ResidentBll.query(paperNum);// 从数据库拿出这个居民的信息
        // 存入到Session中
        Session session = MyApplication.getInstance().getSession();
        session.setCurrentResident(bean);
    }

    /**
     * 用Handler来更新UI
     */
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case 0:
                    // 显示ProgressDialog
                    if (progressDialog == null) {
                        progressDialog = ProgressDialog.show(mContext, "正在读卡", "请稍等...", false,
                                true);
                    }
                    progressDialog.setOnCancelListener(new OnCancelListener() {

                        @Override
                        public void onCancel(DialogInterface arg0) {

                            mLeftTimes4ReadIdc = 0; // 设置剩余次数为0
                            progressDialog = null;
                        }
                    });
                    handler.sendEmptyMessage(1);
                    break;
                case 1:
                    removePreviousResidentData();
                    new ReadIdcThread(10).start();
                    break;
                default:
                    // 隐藏进度条
                    if (progressDialog != null && progressDialog.isShowing()) {
                        try {
                            progressDialog.dismiss();
                        } catch (IllegalArgumentException e) {
                            e.printStackTrace();
                        }
                    }
                    progressDialog = null;
                    break;
            }
        }
    };

    private int mLeftTimes4ReadIdc = 0;
    public class ReadIdcThread extends Thread {

        public ReadIdcThread(int leftTimes) {
            mLeftTimes4ReadIdc = leftTimes;
        }

        @Override
        public void run() {
            Looper.prepare();
            if (deviceId == TagConstants.XML_VALUE_ID_HSDZ_CVR100B) {
                readIdc(mLeftTimes4ReadIdc);
            }
            super.run();
        }

    }

    public void readIdc(final int leftTimes) {
        final IdcReader idcr = new IdcReader();
        idcr.setOnErrorListener(new OnErrorListener() {

            @Override
            public void onError(final int code, final String errorDesc) {
                ((Activity) mContext).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.e(TAG, "Read IDC Error, code:" + code + ", desc:" + errorDesc
                                + ", lefttimes:" + leftTimes);

                        if (leftTimes <= 0) {
                            mToast.setText(errorDesc);
                            mToast.show();
                            handler.sendEmptyMessage(2);
                        } else {
                            Log.e(TAG, "try again.");
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            // 重试
                            new ReadIdcThread(leftTimes - 1).start();
                        }
                    }
                });
            }
        });
        idcr.setOnReadMessageListener(new OnReadMessageListener() {

            @Override
            public void onReadStopDate(Date stopDate) {

            }

            @Override
            public void onReadStartDate(Date startDate) {

            }

            @Override
            public void onReadProfile(final Bitmap profile) {
                ((Activity) mContext).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mProfileBitmap = profile;
                        profileImageView.setImageBitmap(profile);
                        saveProfile();
                    }
                });
            }

            @Override
            public void onReadPeopleSex(final String peopleSex) {
                ((Activity) mContext).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        CommonUtil
                                .setSpinner(page01.mSexSpinner,
                                        getResources().getStringArray(R.array.gender_conditions),
                                        peopleSex);
                        Jmjbxx mJmjbxx = (Jmjbxx) beanMap.get(Jmjbxx.class.getName());
                        if (mJmjbxx != null) {
                            mJmjbxx.setSexCD(page01.mSexSpinner.getSelectedValueInt());
                        }
                    }
                });
            }

            @Override
            public void onReadPeopleNation(final String peopleNation) {
                ((Activity) mContext).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        CommonUtil.setSpinner(page01.mFolkSpinner,
                                getResources().getStringArray(R.array.folk_conditions),
                                peopleNation + "族");
                        Jmjbxx mJmjbxx = (Jmjbxx) beanMap.get(Jmjbxx.class.getName());
                        if (mJmjbxx != null) {
                            BeanID flokCD = new BeanID(page01.mFolkSpinner.getSelectedValue(),
                                    page01.mFolkSpinner.getSelectedData());
                            mJmjbxx.setFlokCD(flokCD);
                        }
                    }
                });
            }

            @Override
            public void onReadPeopleName(final String peopleName) {
                ((Activity) mContext).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        xmEditText.setText(peopleName);
                        Jmjbxx mJmjbxx = (Jmjbxx) beanMap.get(Jmjbxx.class.getName());
                        if (mJmjbxx != null) {
                            mJmjbxx.setResidentName(peopleName);
                        }
                    }
                });
            }

            @Override
            public void onReadPeopleIdCode(final String peopleIdCode) {
                ((Activity) mContext).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mPaperNum = peopleIdCode;
                        saveProfile();
                        sfzhEditText.setText(peopleIdCode);
                        Jmjbxx mJmjbxx = (Jmjbxx) beanMap.get(Jmjbxx.class.getName());
                        if (mJmjbxx != null) {
                            mJmjbxx.setPaperNum(peopleIdCode);
                        }
                        searchResident(peopleIdCode);
                    }
                });
            }

            @Override
            public void onReadPeopleBirthday(final Date peopleBirthday) {
                ((Activity) mContext).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                        page01.mBirthdayText.setText(format.format(peopleBirthday));
                        Jmjbxx mJmjbxx = (Jmjbxx) beanMap.get(Jmjbxx.class.getName());
                        if (mJmjbxx != null) {
                            mJmjbxx.setBirthDay(format.format(peopleBirthday));
                        }
                    }
                });
            }

            @Override
            public void onReadPeopleAddress(final String peopleAddress) {
                ((Activity) mContext).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mPeopleAddress = peopleAddress;
                        page01.mRegAddressEdit.setText(peopleAddress);
                        Jmjbxx mJmjbxx = (Jmjbxx) beanMap.get(Jmjbxx.class.getName());
                        if (mJmjbxx != null) {
                            mJmjbxx.setRegAddress(peopleAddress);
                        }
                    }
                });
            }

            @Override
            public void onReadDepartment(String department) {
            }
        });

        if (idcr.run() == IDCReaderSDK.CVR_RETCODE_SUCCESS) {
            handler.sendEmptyMessage(2);
        }
    }

    /**
     * 保存用户头像
     */
    protected void saveProfile() {
        if (mPaperNum != null && mProfileBitmap != null) {

            final String paperNum = mPaperNum;
            final Bitmap mBitmap = mProfileBitmap;
            mPaperNum = null;
            mProfileBitmap = null;
            new Thread() {
                public void run() {
                    FileHelper.getInstance().saveBitmap(mBitmap, paperNum);
                };
            }.start();
        }
    }

    // viewpager数据绑定
    class MyPagerAdapter extends PagerAdapter {
        List<View> mPageList = new ArrayList<View>();

        public MyPagerAdapter(Context c) {
            super();
            // 添加页面
            page01 = new JbxxPage01(c, beanMap);
            mPageList.add(page01);
            page02 = new JbxxPage02(c, beanMap);
            mPageList.add(page02);
            page03 = new JbxxPage03(c, beanMap);
            mPageList.add(page03);
            page04 = new JbxxPage04(c, beanMap);
            mPageList.add(page04);
            page05 = new JbxxPage05(c, beanMap);
            mPageList.add(page05);
            page06 = new JbxxPage06(c, beanMap);
            mPageList.add(page06);
            page07 = new JbxxPage07(c, beanMap);
            mPageList.add(page07);
            page08 = new JbxxPage08(c, beanMap);
            mPageList.add(page08);
            page09 = new JbxxPage09(c, beanMap);
            mPageList.add(page09);
            page10 = new JbxxPage10(c, beanMap);
            mPageList.add(page10);
            page11 = new JbxxPage11(c, beanMap);
            mPageList.add(page11);
            page12 = new JbxxPage12(c, beanMap);
            mPageList.add(page12);
            page13 = new JbxxPage13(c, beanMap);
            mPageList.add(page13);
        }

        public void destroyItem(View arg0, int arg1, Object arg2) {
            ((ViewPager) arg0).removeView(mPageList.get(arg1));
        }

        @Override
        public void finishUpdate(View arg0) {
        }

        public int getCount() {
            return mPageList.size();
        }

        public Object instantiateItem(View arg0, int arg1) {
            View v = mPageList.get(arg1);
            ((ViewPager) arg0).addView(v);
            return v;
        }

        public boolean isViewFromObject(View arg0, Object arg1) {
            return (arg0 == arg1);
        }

        @Override
        public void restoreState(Parcelable arg0, ClassLoader arg1) {

        }

        @Override
        public Parcelable saveState() {
            return null;
        }

        @Override
        public void startUpdate(View arg0) {
        }
    }

    @Override
    public void setValue() {
        Jmjbxx mJmjbxx = (Jmjbxx) beanMap.get(Jmjbxx.class.getName());
        if (mJmjbxx != null) {
            xmEditText.setText(mJmjbxx.getResidentName());
            sfzhEditText.setText(mJmjbxx.getPaperNum());
            dabhTextView.setText(mJmjbxx.getResidentID());
            khEditText.setText(mJmjbxx.getCardID());
            if (mJmjbxx.getPaperNum() != null && !mJmjbxx.getPaperNum().trim().equals(""))
                profileImageView.setImageURI(Uri.fromFile(new File(Constant.PROFILE_PATH, mJmjbxx
                        .getPaperNum() + ".png")));
            Log.i(TAG, "age: " + CalendarUtil.getAge(mJmjbxx.getBirthDay()));
            ageTextView.setText(CalendarUtil.getAge(mJmjbxx.getBirthDay()) + "岁");

            Log.i(TAG, "sex: " + mJmjbxx.getSexCD());
            if ( mJmjbxx.getSexCD() == 1 ) {
                sexImageView.setImageResource(R.drawable.male);
                sexTextView.setText("男");
            } else if ( mJmjbxx.getSexCD() == 2  ) {
                sexImageView.setImageResource(R.drawable.female);
                sexTextView.setText("女");
            } else {
                sexImageView.setImageResource(R.drawable.unknown);
                sexTextView.setText("未知");
            }
            

            // // 设置责任医生
            // if (Global.doctorID != null && Global.doctorName !=
            // null) {
            // Doctor doctor = new
            // Doctor(Integer.parseInt(Global.doctorID),
            // Global.doctorName);
            // mJmjbxx.setDutyDoctor(doctor);
            // }
            //
            // // 设置机构
            // ManageOrg manageOrg = new ManageOrg(41954126, "江厦街道社区卫生服务中心");
            // mJmjbxx.setManageOrg(manageOrg);
            // Station station = new Station(41954126, "江厦街道社区卫生服务中心");
            // mJmjbxx.setStation(station);
            // Builder builder = new Builder(41954126, "江厦街道社区卫生服务中心");
            // mJmjbxx.setBuilder(builder);
        }
        page01.setValue();
        page02.setValue();
        page03.setValue();
        page04.setValue();
        page05.setValue();
        page06.setValue();
        page07.setValue();
        page08.setValue();
        page09.setValue();
        page10.setValue();
        page11.setValue();
        page12.setValue();
        page13.setValue();
    }

    @Override
    public boolean getValue() {

        Jmjbxx mJmjbxx = (Jmjbxx) beanMap.get(Jmjbxx.class.getName());

        // 姓名
        String residentName = xmEditText.getText().toString().trim();
        if (residentName.equals("")) {
            mToast.setText("居民姓名不能为空！");
            mToast.show();
            return false;
        }
        mJmjbxx.setResidentName(residentName);

        // 身份证
        String paperNum = sfzhEditText.getText().toString().trim();
        if (paperNum.equals("")) {
            mToast.setText("身份证号不能为空！");
            mToast.show();
            return false;
        }
        mJmjbxx.setPaperNum(paperNum);
        mJmjbxx.setCardID(khEditText.getText().toString().trim());

        // // 设置责任医生
        // if (Global.doctorID != null &&
        // !Global.doctorID.equals("")
        // && Global.doctorName != null) {
        // Doctor doctor = new Doctor(Global.doctorID,
        // Global.doctorName);
        // mJmjbxx.setDutyDoctor(doctor);
        // }
        //
        // // 设置机构
        // ManageOrg manageOrg = new ManageOrg(Global.orgCode,
        // "江厦街道社区卫生服务中心");
        // mJmjbxx.setManageOrg(manageOrg);
        // Station station = new Station(Global.orgCode, "江厦街道社区卫生服务中心");
        // mJmjbxx.setStation(station);
        // Builder builder = new Builder("3", "包丽霞");
        // mJmjbxx.setBuilder(builder);
        // BuildOrg buildOrg = new BuildOrg(Global.orgCode,
        // "江厦街道社区卫生服务中心");
        // mJmjbxx.setBuildOrg(buildOrg);

        // 在取出快速体检的数据放进去
        List<Class<? extends IBean>> kstjListBean = new ArrayList<Class<? extends IBean>>();
        kstjListBean.add(Jktj_kstj.class);
        BeanUtil.getInstance().getLastJktjFromDb(kstjListBean, new BeanUtil.OnResultFromDb() {
            @Override
            public void onResult(List<IBean> listBean, boolean isSucc) {
                if (isSucc)
                    if (listBean.get(0) != null) {
                        beanMap.put(Jktj_kstj.class.getName(), listBean.get(0));
                    }

            }
        });

        return (page01.getValue() && page02.getValue() && page03.getValue()
                && page04.getValue() && page05.getValue() && page06.getValue() && page07.getValue()
                && page08.getValue() && page09.getValue() && page10.getValue() && page11.getValue()
                && page12.getValue() && page13.getValue());
    }

    // 从数据库中
    private boolean getJbxxFromDB(String searchString) {
        // 先存入到数据库
        // 1.写居民登记信息表
        Resident bean = new Resident();
        String keyType = "";
        if (showMessageStatus == 3 || showMessageStatus == 1) {// 当根据身份证查找时，身份证不能为空
            if (searchString.equals("")) {
                return false;
            } else {
                bean.setPaperNum(searchString);
                keyType = ResidentField.PAPER_NUM;
            }
        }
        if (showMessageStatus == 4) {// 当根据姓名证查找时，姓名不能为空
            if (searchString.equals("")) {
                return false;
            } else {
                bean.setResidentName(searchString);
                keyType = ResidentField.RESIDENT_NAME;
            }
        }
        if (showMessageStatus == 5) {// 当根据卡号证查找时，卡号不能为空
            if (searchString.equals("")) {
                return false;
            } else {
                bean.setCardId(searchString);
                keyType = ResidentField.CARD_ID;
            }
        }

        final List<Resident> residentBeanList = ResidentBll.query(searchString, keyType);
        if (residentBeanList == null || residentBeanList.size() == 0) {
            Log.e(TAG, "该用户信息尚未采集");
            return false;
        } else {
            if (residentBeanList.size() > 1) {// 有多个同名的居民，弹出对话框选择
                CharSequence[] charSequence = new CharSequence[residentBeanList.size()];
                for (int i = 0; i < residentBeanList.size(); i++) {
                    Resident resident = residentBeanList.get(i);
                    charSequence[i] = resident.getResidentName() + "   " + resident.getPaperNum()
                            + "   ";
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle("由于有多个同名的居民，请选择");
                builder.setSingleChoiceItems(charSequence, 0,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                getJbxxFromDB(residentBeanList.get(which));
                                dialog.cancel();
                            }
                        });
                builder.show();
            } else {
                getJbxxFromDB(residentBeanList.get(0));
            }

            return true;
        }
    }

    private boolean getJbxxFromDB(Resident bean) {
        // 2.写session
        loadResidentInfoIntoSession(bean.getPaperNum());

        List<Class<? extends IBean>> beanList = new ArrayList<Class<? extends IBean>>();

        beanList.add(Jmjbxx.class);
        beanList.add(Jmjtxx.class);
        beanList.add(Jmjkxx.class);
        beanList.add(Jmxwxg.class);

        BeanUtil.getInstance().getJbxxFromDb(beanList, new OnResultFromDb() {
            @Override
            public void onResult(List<IBean> listBean, boolean isSucc) {
                if (isSucc) {// 如果成功

                    Jmjbxx jmjbxx = (Jmjbxx) listBean.get(0);
                    if (listBean.get(0) == null) {
                        Global.jmjbxx = new Jmjbxx();
                        jmjbxx = Global.jmjbxx;
                    } else {
                        Global.jmjbxx = jmjbxx;
                    }
                    Jmjtxx jmjtxx = (Jmjtxx) listBean.get(1);
                    if (listBean.get(1) == null) {
                        Global.jmjtxx = new Jmjtxx();
                        jmjtxx = Global.jmjtxx;
                    } else {
                        Global.jmjtxx = jmjtxx;
                    }
                    Jmjkxx jmjkxx = (Jmjkxx) listBean.get(2);
                    if (listBean.get(2) == null) {
                        Global.jmjkxx = new Jmjkxx();
                        jmjkxx = Global.jmjkxx;
                    } else {
                        Global.jmjkxx = jmjkxx;
                    }
                    Jmxwxg jmxwxg = (Jmxwxg) listBean.get(3);
                    if (listBean.get(3) == null) {
                        Global.jmxwxg = new Jmxwxg();
                        jmxwxg = Global.jmxwxg;
                    } else {
                        Global.jmxwxg = jmxwxg;
                    }
                    beanMap.put(Jmjbxx.class.getName(), jmjbxx);
                    beanMap.put(Jmjtxx.class.getName(), jmjtxx);
                    beanMap.put(Jmjkxx.class.getName(), jmjkxx);
                    beanMap.put(Jmxwxg.class.getName(), jmxwxg);

                    // 向Session写入该居民的基本信息
                    setValue();
                } else {

                }
            }
        });
        return true;
    }

    // 从服务器获取数据 首先直接得到居民信息，在用得到的信息去拿 居民四种信息
    public void getJbxxFromWeb(String searchString) {
        Jmjbxx mJmjbxx = (Jmjbxx) beanMap.get(Jmjbxx.class.getName());
        /**
         * 直接得到居民基本信息
         */
        Zjddjmjbxx7_9 zjddjmjbxx7_9 = new Zjddjmjbxx7_9();
        zjddjmjbxx7_9.request = new Zjddjmjbxx7_9.Request();
        zjddjmjbxx7_9.request.returnRecord = 0;// 0 表示返回所有符合的记录

        ProgressDialogUtil.showProgressDialog(mContext, "正在查询", "请稍等...");

        // 设置责任医生和员工号，当前是否有用户登录并且在全局变量注册
        Login1 loginUser = MyApplication.getInstance().getSession().getLoginResult();
        if (loginUser != null && loginUser.response != null) {
            // Doctor doctor = new
            // Doctor(Integer.parseInt(loginUser.response.doctorID),
            // loginUser.response.doctorName);
            // mJmjbxx.setDutyDoctor(doctor);//因为责任医生并不一定是登陆的用户，所有不设置
            BeanID employeeNo = loginUser.response.employeeNo;
            zjddjmjbxx7_9.request.employeeNo = employeeNo;
        } else {
            ProgressDialogUtil.hideProgressDialog();
            mToast.setText("用户未登录成功，不能进行操作！");
            mToast.show();
            return;
        }

        if (showMessageStatus == 1) {// 读卡操作
            getReadCardData();
            zjddjmjbxx7_9.request.PaperNum = mJmjbxx.getPaperNum();
            zjddjmjbxx7_9.request.residentName = mJmjbxx.getResidentName();
            // zjddjmjbxx7_9.request.residentName =
            // mJmjbxx.getResidentName().trim();
        } else if (showMessageStatus == 3 || showMessageStatus == 4 || showMessageStatus == 5) {// 根据身份证查找
            //String tmp = sfzhEditText.getText().toString();
            zjddjmjbxx7_9.request.PaperNum = mJmjbxx.getPaperNum();
            //tmp = xmEditText.getText().toString();
            zjddjmjbxx7_9.request.residentName = mJmjbxx.getResidentName();
            //tmp = khEditText.getText().toString();
            zjddjmjbxx7_9.request.CardID = mJmjbxx.getCardID();
        }

        List<IDto> beanList = new ArrayList<IDto>();
        // 注意，以下顺序不能改变
        beanList.add(zjddjmjbxx7_9); // 添加 79 直接得到居民基本信息.xml idto

        BeanUtil.getInstance().saveBeanToWeb(beanList, new OnResultFromWeb() {
            @Override
            public void onResult(List<IDto> listBean, boolean isSucc) {
                if (isSucc) {
                    Zjddjmjbxx7_9 responseZjddjmjbxx7_9 = (Zjddjmjbxx7_9) listBean.get(0);

                    if (responseZjddjmjbxx7_9 == null || responseZjddjmjbxx7_9.response == null) {
                        mToast.setText("【直接得到居民基本信息】服务器接口异常");
                        mToast.show();
                        ProgressDialogUtil.hideProgressDialog();
                    } else {
                        if (responseZjddjmjbxx7_9.response.errMsg.length() > 0) {
                            mToast.setText("【直接得到居民基本信息】" + responseZjddjmjbxx7_9.response.errMsg);
                            mToast.show();
                            ProgressDialogUtil.hideProgressDialog();
                        } else {
                            // responseZjddjmjbxx7_9.response.resident=null;//测试建档时候读取身份证
                            if (responseZjddjmjbxx7_9.response.resident == null
                                    || responseZjddjmjbxx7_9.response.resident.size() <= 0) {// 返回的符合的记录不存在
                                // 没有数据后，那么在取数据库找
                                if (showMessageStatus == 3) {// 如果是根据身份证查找
                                    if (!getJbxxFromDB(sfzhEditText.getText().toString().trim())) {
                                        mToast.setText("【直接得到居民基本信息】沒有数据");
                                        mToast.show();
                                    } else {
                                        mToast.setText("【直接得到居民基本信息】服务器沒有数据，本地获得数据成功");
                                        mToast.show();
                                        Global.updateJmjbxx = true;
                                    }
                                } else if (showMessageStatus == 4) {// 根据姓名到数据库查找
                                    // TODO 暂时不做根据姓名到数据库查找，以后根据需求在新增
                                    mToast.setText("【直接得到居民基本信息】沒有数据");
                                    mToast.show();
                                } else if (showMessageStatus == 5) {// 根据姓名到数据库查找
                                    // TODO 暂时不做根据姓名到数据库查找，以后根据需求在新增
                                    mToast.setText("【直接得到居民基本信息】沒有数据");
                                    mToast.show();
                                } else if (showMessageStatus == 1) {// 是读卡操作
                                    parsePeopleAddress();
                                    setValue();
                                    Global.updateJmjbxx = true;
                                } else {
                                    String keyStr = null;
                                    String keyType = null;
                                    if (showMessageStatus == 3) {// 如果是根据身份证查找
                                        keyStr = sfzhEditText.getText().toString().trim();
                                        keyType = ResidentField.PAPER_NUM;
                                    } else if (showMessageStatus == 4) {// 根据姓名到数据库查找
                                        keyStr = xmEditText.getText().toString().trim();
                                        keyType = ResidentField.RESIDENT_NAME;
                                    } else if (showMessageStatus == 5) {// 根据姓名到数据库查找
                                        keyStr = khEditText.getText().toString().trim();
                                        keyType = ResidentField.CARD_ID;
                                    }

                                    if (keyStr != null && keyType != null) {
                                        if (!getJbxxFromDB(sfzhEditText.getText().toString().trim())) {
                                            mToast.setText("【直接得到居民基本信息】沒有数据");
                                            mToast.show();
                                        } else {
                                            mToast.setText("【直接得到居民基本信息】服务器沒有数据，本地获得数据成功");
                                            mToast.show();
                                            Global.updateJmjbxx = true;
                                        }
                                    }
                                }

                                ProgressDialogUtil.hideProgressDialog();
                            } else {
                                // 成功拿到数据，不做提示，继续更新界面和本地数据，
                                checkResidentSize(responseZjddjmjbxx7_9.response);
                            }
                        }
                    }

                } else {
                    mToast.setDuration(Toast.LENGTH_SHORT);
                    mToast.setText("网络请求异常");
                    mToast.show();
                    ProgressDialogUtil.hideProgressDialog();
                }

            }
        });
    }

    public void getProfileFromWeb(String residentID) {
        DdsfzzpJ001 ddsfzzpJ001 = new DdsfzzpJ001();
        ddsfzzpJ001.request = new DdsfzzpJ001.Request();
        ddsfzzpJ001.request.residentID = residentID;
        List<IDto> beanList = new ArrayList<IDto>();
        beanList.add(ddsfzzpJ001);

        BeanUtil.getInstance().getBeanFromWeb(beanList, new BeanUtil.OnResultFromWeb() {
            @Override
            public void onResult(List<IDto> listBean, boolean isSucc) {
                if (isSucc) {
                    StringBuilder sb = new StringBuilder();
                    DdsfzzpJ001 responseDdsfzzpJ001 = (DdsfzzpJ001) listBean.get(0);
                    if (responseDdsfzzpJ001 == null || responseDdsfzzpJ001.response == null) {
                        sb.append("【得到身份证照片】服务器接口异常");
                    } else {
                        if (responseDdsfzzpJ001.response.errMsg.length() > 0) {
                            sb.append("【得到身份证照片】" + responseDdsfzzpJ001.response.errMsg);
                        } else {
                            if (responseDdsfzzpJ001.response.photo.trim().equals("")) {
                                sb.append("【得到身份证照片】失败，服务器没有数据");
                            } else {
                                showProfile(responseDdsfzzpJ001.response.residentID,
                                        responseDdsfzzpJ001.response.photo);
                                sb.append("【得到身份证照片】成功");
                            }
                        }
                    }
                    mToast.setDuration(Toast.LENGTH_LONG);
                    mToast.setText(sb.toString());
                    mToast.show();
                } else {
                    mToast.setDuration(Toast.LENGTH_SHORT);
                    mToast.setText("网络请求异常");
                    mToast.show();
                }

            }
        });

    }

    // 从服务器拿下来的图片字符串变为图片显示
    public void showProfile(String residentID, String photo) {
        Jmjbxx jmjbxx = (Jmjbxx) beanMap.get(Jmjbxx.class.getName());
        FileHelper.getInstance().setBitmapString(jmjbxx.getPaperNum(), photo);
        profileImageView.setImageURI(Uri.fromFile(new File(Constant.PROFILE_PATH, jmjbxx.getPaperNum()
                + ".png")));
    }

    public void checkResidentSize(final com.cking.phss.dto.Zjddjmjbxx7_9.Response response) {
        com.cking.phss.dto.innner.Resident resident = null;
        if (response.resident.size() > 1) {// 有多个同名的居民，弹出对话框选择
            CharSequence[] charSequence = new CharSequence[response.resident.size()];
            for (int i = 0; i < response.resident.size(); i++) {
                resident = response.resident.get(i);
                charSequence[i] = resident.residentName + "   " + resident.paperNum + "   ";
                if (resident.sexCD == 0) {
                    charSequence[i] = charSequence[i] + "不详";
                } else if (resident.sexCD == 1) {
                    charSequence[i] = charSequence[i] + "男";
                } else if (resident.sexCD == 2) {
                    charSequence[i] = charSequence[i] + "女";
                } else {
                    charSequence[i] = charSequence[i] + "未说明性别";
                }
            }
            ProgressDialogUtil.hideProgressDialog();
            AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
            builder.setTitle("由于有多个同名的居民，请选择");
            builder.setSingleChoiceItems(charSequence, 0, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    updateParams(response.resident.get(which));
                    dialog.cancel();
                }
            });
            builder.show();
        } else {
            updateParams(response.resident.get(0));
        }
    }

    /**
     * 从网上得到居民基本信息，并存入beanmap
     */
    protected void updateParams(final com.cking.phss.dto.innner.Resident resident) {
        if (resident == null) {
            ProgressDialogUtil.hideProgressDialog();
            return;
        }
        // 首先设置一部分的基本信息
        Jmjbxx mJmjbxx = (Jmjbxx) beanMap.get(Jmjbxx.class.getName());
        Jmjtxx mJmjtxx = (Jmjtxx) beanMap.get(Jmjtxx.class.getName());

        // 从服务器拿身份证照片
        getProfileFromWeb(resident.residentID);

        mJmjtxx.setFamilyID(resident.familyID);
        mJmjbxx.setResidentID(resident.residentID);
        mJmjbxx.setResidentName(resident.residentName);
        mJmjbxx.setSexCD(resident.sexCD);
        mJmjbxx.setBirthDay(resident.birthDay);
        mJmjbxx.setPaperNum(resident.paperNum);
        mJmjbxx.setCardID(resident.cardID);
        mJmjbxx.setSelfPhone(resident.selfPhone);
        mJmjbxx.setWorkUnit(resident.workUnit);
        if (resident.folkCD != null) {
            mJmjbxx.setFlokCD(resident.folkCD);
        }
        mJmjbxx.setEducationCD(resident.EducationCD);
        if (resident.vocationCD != null) {
            mJmjbxx.setVocationCD(resident.vocationCD);
        }
        mJmjbxx.setMarriageCD(resident.marriageCD);
        mJmjbxx.setAddressTypeCD(resident.AddressTypeCD);

        if (resident.nowProvince != null) {
            mJmjbxx.setNowProvince(resident.nowProvince);
        }
        if (resident.nowCity != null) {
            mJmjbxx.setNowCity(resident.nowCity);
        }
        if (resident.nowDistrict != null) {
            mJmjbxx.setNowDistrict(resident.nowDistrict);
        }
        if (resident.nowStreet != null) {
            mJmjbxx.setNowStreet(resident.nowStreet);
        }
        if (resident.nowZone != null) {
            mJmjbxx.setNowZone(resident.nowZone);
        }
        if (resident.nowRoad != null) {
            mJmjbxx.setNowRoad(resident.nowRoad);
        }

        mJmjbxx.setNowH(resident.nowH);
        mJmjbxx.setNowN(resident.nowN);
        mJmjbxx.setNowS(resident.nowS);
        mJmjbxx.setNowOther(resident.nowOther);

        // TODO regDetail等户籍住址，暂时不保存设置
        if (showMessageStatus != 1 && StringUtil.isEmptyString(mPeopleAddress)) {// 不是读卡操作
            mJmjbxx.setRegDetail(resident.regDetail);
            mJmjbxx.setRegAddress(resident.regDetail);
        } else {
            mJmjbxx.setRegDetail(mPeopleAddress);
            mJmjbxx.setRegAddress(mPeopleAddress);
        }

        mJmjbxx.setHeight(resident.height);
        mJmjbxx.setWeight(resident.weight);
        // 责任医生
        if (resident.dutyDoctor != null) {
            mJmjbxx.setDutyDoctor(resident.dutyDoctor);
        }
        mJmjbxx.setFileStatusCD(resident.fileStatusCD);

        /**
         * 在调用 得到居民基本信息
         */
        Ddjmjbxx7 ddjmjbxx7 = new Ddjmjbxx7();
        ddjmjbxx7.request = new Ddjmjbxx7.Request();
        ddjmjbxx7.request.familyID = mJmjtxx.getFamilyID();
        ddjmjbxx7.request.residentID = mJmjbxx.getResidentID();
        ddjmjbxx7.request.status = Global.status;
        Login1 loginUser = MyApplication.getInstance().getSession().getLoginResult();
        if (loginUser != null && loginUser.response != null) {
            ddjmjbxx7.request.employeeNo = loginUser.response.employeeNo;
        } else {
            mToast.setText("用户未登录注册成功，不能进行操作！");
            mToast.show();
            ProgressDialogUtil.hideProgressDialog();
            return;
        }

        /**
         * 得到居民家庭信息
         */
        Ddjtxxxx5 ddjtxxxx5 = new Ddjtxxxx5();
        ddjtxxxx5.request = new Ddjtxxxx5.Request();
        ddjtxxxx5.request.familyID = resident.familyID;
        ddjtxxxx5.request.employeeNo = ddjmjbxx7.request.employeeNo;

        /**
         * 得到居民行为习惯
         */
        Ddjmxwxg7_1 ddjmxwxg7_1 = new Ddjmxwxg7_1();
        ddjmxwxg7_1.request = new Ddjmxwxg7_1.Request();
        ddjmxwxg7_1.request.familyID = mJmjtxx.getFamilyID();
        ddjmxwxg7_1.request.residentID = mJmjbxx.getResidentID();
        ddjmxwxg7_1.request.employeeNo = ddjmjbxx7.request.employeeNo;
        /**
         * 得到居民健康信息
         */
        Ddjmjkxx7_2 ddjmjkxx7_2 = new Ddjmjkxx7_2();
        ddjmjkxx7_2.request = new Ddjmjkxx7_2.Request();
        ddjmjkxx7_2.request.familyID = mJmjtxx.getFamilyID();
        ddjmjkxx7_2.request.residentID = mJmjbxx.getResidentID();
        ddjmjkxx7_2.request.employeeNo = ddjmjbxx7.request.employeeNo;
        /**
         * 得到电子签约
         */
        String stringuserID = MyApplication.getInstance().getSession().getLoginResult().response.userID;
        DdqyqkHrs02 ddqyqkHrs02 = new DdqyqkHrs02();
        ddqyqkHrs02.request = new DdqyqkHrs02.Request();
        ddqyqkHrs02.request.ResidentID = mJmjbxx.getResidentID();
        ddqyqkHrs02.request.UserID = stringuserID;

        List<IDto> beanList = new ArrayList<IDto>();
        // 注意，以下顺序不能改变
        beanList.add(ddjmjbxx7); // 添加 7 得到居民基本信息.xml idto
        beanList.add(ddjmxwxg7_1);
        beanList.add(ddjmjkxx7_2);
        beanList.add(ddqyqkHrs02);

        if (resident.familyID != null && !resident.familyID.trim().equals("")) {
            beanList.add(ddjtxxxx5); // 家庭信息防最后，因为家庭信息不一定有，防最后容易判断操作
        }

        BeanUtil.getInstance().saveBeanToWeb(beanList, new OnResultFromWeb() {
            @Override
            public void onResult(List<IDto> listBean, boolean isSucc) {
                ProgressDialogUtil.hideProgressDialog();
                if (isSucc) {
                    StringBuilder sb = new StringBuilder();
                    // 得到居民基本信息的反馈信息
                    Ddjmjbxx7 ddjmjbxx7 = (Ddjmjbxx7) listBean.get(0);
                    if (ddjmjbxx7 == null || ddjmjbxx7.response == null) {
                        sb.append("【得到居民基本信息】服务器接口异常");
                    } else {
                        if (ddjmjbxx7.response.errMsg.length() > 0) {
                            sb.append("【得到居民基本信息】" + ddjmjbxx7.response.errMsg);
                        } else {
                            updateJmjbxx(ddjmjbxx7.response);
                            sb.append("【得到居民基本信息】获取成功");
                        }
                    }

                    // 得到居民行为习惯
                    Ddjmxwxg7_1 responseDdjmxwxg7_1 = (Ddjmxwxg7_1) listBean.get(1);
                    if (responseDdjmxwxg7_1 == null || responseDdjmxwxg7_1.response == null) {
                        sb.append("\n");
                        sb.append("【居民行为习惯】服务器接口异常");
                    } else {
                        if (responseDdjmxwxg7_1.response.errMsg.length() > 0) {
                            sb.append("\n");
                            sb.append("【居民行为习惯】" + responseDdjmxwxg7_1.response.errMsg);
                        } else {
                            updateJmxwxg(responseDdjmxwxg7_1.response);
                            sb.append("\n");
                            sb.append("【居民行为习惯】获取成功");
                        }
                    }

                    // 测试代码
                    // String responseXmlStr =
                    // "<?xml version=\"1.0\" encoding=\"utf-8\" standalone=\"yes\"?><Body><Response ErrMsg=\"\"><ReadOnly>0</ReadOnly><UserID>831</UserID><User>庄恩大</User><FamilyID>41961185.20140815.0016</FamilyID><ResidentID>41961185.20140815.00019</ResidentID><Grandparents CD=\"\"></Grandparents><FatherCD>2, 3, 12</FatherCD><FatherName>失眠</FatherName><MotherCD>2, 3, 6, 10, 12</MotherCD><MotherName>梦话</MotherName><BrotherCD>2, 10, 12</BrotherCD><BrotherName>梦游</BrotherName><ChildCD>2, 5, 12</ChildCD><ChildName>磨牙</ChildName><OtherMemberCD>1</OtherMemberCD><OtherMemberName></OtherMemberName><DeformityCD></DeformityCD><DeformityName></DeformityName><DeformityCardNo></DeformityCardNo><DeformityLevel CD=\"4\">四级</DeformityLevel><HeredityCD>2</HeredityCD><HeredityCDName>2, 3</HeredityCDName><HeredityName>2, 3</HeredityName><ExposureCD></ExposureCD><ExposureName></ExposureName><HistoryDisease><DisSn>001</DisSn><HDType>2</HDType><HDTypeName></HDTypeName><ICD10 ID=\"95.241\">眼震电流描记图（ ENG）</ICD10><Disease>眼震电流描记图（ ENG）</Disease><DiagnoseDate>2014-08-18</DiagnoseDate><HappenDate>2014-08-18</HappenDate><HDReason></HDReason><ResultCD>1</ResultCD><CureDes></CureDes><CureHos></CureHos></HistoryDisease><HistoryDisease><DisSn>002</DisSn><HDType>0</HDType><HDTypeName></HDTypeName><ICD10 ID=\"A18.032+\">骶髂关节结核</ICD10><Disease>骶髂关节结核</Disease><DiagnoseDate>2014-08-18</DiagnoseDate><HappenDate>2014-08-18</HappenDate><HDReason></HDReason><ResultCD>1</ResultCD><CureDes></CureDes><CureHos></CureHos></HistoryDisease><HistoryDisease><DisSn>003</DisSn><HDType>0</HDType><HDTypeName></HDTypeName><ICD10 ID=\"A18.032+\">骶髂关节结核</ICD10><Disease>骶髂关节结核</Disease><DiagnoseDate>2014-08-18</DiagnoseDate><HappenDate>2014-08-18</HappenDate><HDReason></HDReason><ResultCD>1</ResultCD><CureDes></CureDes><CureHos></CureHos></HistoryDisease><HistoryDisease><DisSn>004</DisSn><HDType>1</HDType><HDTypeName></HDTypeName><ICD10 ID=\"37.311\">心包部分切除术</ICD10><Disease>心包部分切除术</Disease><DiagnoseDate>2014-08-19</DiagnoseDate><HappenDate>2014-08-19</HappenDate><HDReason></HDReason><ResultCD>1</ResultCD><CureDes></CureDes><CureHos></CureHos></HistoryDisease><HistoryDisease><DisSn>005</DisSn><HDType>1</HDType><HDTypeName></HDTypeName><ICD10 ID=\"37.311\">心包部分切除术</ICD10><Disease>心包部分切除术</Disease><DiagnoseDate>2014-08-19</DiagnoseDate><HappenDate>2014-08-19</HappenDate><HDReason></HDReason><ResultCD>1</ResultCD><CureDes></CureDes><CureHos></CureHos></HistoryDisease><HistoryHyper><HyperSn>001</HyperSn><HyperTypeCD>2</HyperTypeCD><HyperSource ID=\"1\"></HyperSource><HappenDate>2014-08-22</HappenDate><HyperReason>不详</HyperReason><CureDes>好转</CureDes></HistoryHyper><HistoryHyper><HyperSn>002</HyperSn><HyperTypeCD>1</HyperTypeCD><HyperSource ID=\"1\"></HyperSource><HappenDate>2014-08-22</HappenDate><HyperReason>不详</HyperReason><CureDes></CureDes></HistoryHyper></Response></Body>";
                    // String responseXmlStr =
                    // "<?xml version=\"1.0\" encoding=\"utf-8\" standalone=\"yes\"?><Body><Response ErrMsg=\"\"><ReadOnly>0</ReadOnly><UserID>831</UserID><User>庄恩大</User><FamilyID>41961185.20140815.0016</FamilyID><ResidentID>41961185.20140815.00019</ResidentID><Grandparents CD=\"\"></Grandparents><FatherCD>2, 3, 12</FatherCD><FatherName>失眠</FatherName><MotherCD>2, 3, 6, 10, 12</MotherCD><MotherName>梦话</MotherName><BrotherCD>2, 10, 12</BrotherCD><BrotherName>梦游</BrotherName><ChildCD>2, 5, 12</ChildCD><ChildName>磨牙</ChildName><OtherMemberCD>1</OtherMemberCD><OtherMemberName></OtherMemberName><DeformityCD></DeformityCD><DeformityName>sb</DeformityName><DeformityCardNo>233557</DeformityCardNo><DeformityLevel CD=\"3\">三级</DeformityLevel><HeredityCD>2</HeredityCD><HeredityCDName>2, 3</HeredityCDName><HeredityName>2, 3</HeredityName><ExposureCD>3</ExposureCD><ExposureName></ExposureName><HistoryDisease><DisSn>001</DisSn><HDType>2</HDType><HDTypeName></HDTypeName><ICD10 ID=\"95.241\">眼震电流描记图（ ENG）</ICD10><Disease>眼震电流描记图（ ENG）</Disease><DiagnoseDate>2014-08-18</DiagnoseDate><HappenDate>2014-08-18</HappenDate><HDReason></HDReason><ResultCD>1</ResultCD><CureDes></CureDes><CureHos></CureHos></HistoryDisease><HistoryDisease><DisSn>002</DisSn><HDType>0</HDType><HDTypeName></HDTypeName><ICD10 ID=\"A18.032+\">骶髂关节结核</ICD10><Disease>骶髂关节结核</Disease><DiagnoseDate>2014-08-18</DiagnoseDate><HappenDate>2014-08-18</HappenDate><HDReason></HDReason><ResultCD>1</ResultCD><CureDes></CureDes><CureHos></CureHos></HistoryDisease><HistoryDisease><DisSn>003</DisSn><HDType>0</HDType><HDTypeName></HDTypeName><ICD10 ID=\"A18.032+\">骶髂关节结核</ICD10><Disease>骶髂关节结核</Disease><DiagnoseDate>2014-08-18</DiagnoseDate><HappenDate>2014-08-18</HappenDate><HDReason></HDReason><ResultCD>1</ResultCD><CureDes></CureDes><CureHos></CureHos></HistoryDisease><HistoryDisease><DisSn>004</DisSn><HDType>1</HDType><HDTypeName></HDTypeName><ICD10 ID=\"37.311\">心包部分切除术</ICD10><Disease>心包部分切除术</Disease><DiagnoseDate>2014-08-19</DiagnoseDate><HappenDate>2014-08-19</HappenDate><HDReason></HDReason><ResultCD>1</ResultCD><CureDes></CureDes><CureHos></CureHos></HistoryDisease><HistoryDisease><DisSn>005</DisSn><HDType>1</HDType><HDTypeName></HDTypeName><ICD10 ID=\"37.311\">心包部分切除术</ICD10><Disease>心包部分切除术</Disease><DiagnoseDate>2014-08-19</DiagnoseDate><HappenDate>2014-08-19</HappenDate><HDReason></HDReason><ResultCD>1</ResultCD><CureDes></CureDes><CureHos></CureHos></HistoryDisease><HistoryHyper><HyperSn>001</HyperSn><HyperTypeCD>2</HyperTypeCD><HyperSource ID=\"1\"></HyperSource><HappenDate>2014-08-22</HappenDate><HyperReason>不详</HyperReason><CureDes>好转</CureDes></HistoryHyper><HistoryHyper><HyperSn>002</HyperSn><HyperTypeCD>1</HyperTypeCD><HyperSource ID=\"1\"></HyperSource><HappenDate>2014-08-22</HappenDate><HyperReason>不详</HyperReason><CureDes></CureDes></HistoryHyper></Response></Body>";
                    // Ddjmjkxx7_2 responseDdjmjkxx7_2 = (Ddjmjkxx7_2)
                    // XmlSerializerUtil.getInstance()
                    // .beanFromXML(Ddjmjkxx7_2.class, responseXmlStr);
                    // 居民健康信息
                    Ddjmjkxx7_2 responseDdjmjkxx7_2 = (Ddjmjkxx7_2) listBean.get(2);
                    if (responseDdjmjkxx7_2 == null || responseDdjmjkxx7_2.response == null) {
                        sb.append("\n");
                        sb.append("【居民健康信息】服务器接口异常");
                    } else {
                        if (responseDdjmjkxx7_2.response.errMsg.length() > 0) {
                            sb.append("\n");
                            sb.append("【居民健康信息】" + responseDdjmjkxx7_2.response.errMsg);
                        } else {
                            updateJmjkxx(responseDdjmjkxx7_2.response);
                            sb.append("\n");
                            sb.append("【居民健康信息】获取成功");
                        }
                    }

                    // 得到签约情况
                    DdqyqkHrs02 ddqyqkHrs02 = (DdqyqkHrs02) listBean.get(3);
                    if (ddqyqkHrs02 == null || ddqyqkHrs02.response == null) {
                        sb.append("\n");
                        sb.append("【得到签约情况】服务器接口异常");
                    } else {
                        if (ddqyqkHrs02.response.errMsg.length() > 0) {
                            sb.append("\n");
                            sb.append("【得到签约情况】" + ddqyqkHrs02.response.errMsg);
                        } else {
                            updateDzqy(ddqyqkHrs02.response);
                            sb.append("\n");
                            sb.append("【得到签约情况】获取成功");
                        }
                    }

                    // 得到居民家庭信息的反馈信息，从本地数据库拿,先判断到底差没差居民基本信息
                    if (resident.familyID != null && !resident.familyID.trim().equals("")) {
                        Ddjtxxxx5 ddjtxxxx5 = (Ddjtxxxx5) listBean.get(4);
                        if (ddjtxxxx5 == null || ddjtxxxx5.response == null) {
                            sb.append("\n");
                            sb.append("【得到居民家庭信息】服务器接口异常");
                        } else {
                            if (ddjtxxxx5.response.errMsg.length() > 0) {
                                sb.append("\n");
                                sb.append("【得到居民家庭信息】" + ddjtxxxx5.response.errMsg);
                            } else {
                                updateJmjtxx(ddjtxxxx5.response);
                                sb.append("\n");
                                sb.append("【得到居民家庭信息】获取成功");
                            }
                        }
                    } else {
                        sb.append("\n");
                        sb.append("【居民家庭信息】没有数据");
                    }

                    mToast.setDuration(Toast.LENGTH_LONG);
                    mToast.setText(sb.toString());
                    mToast.show();
                    setValue();
                    // saveValueToDb();
                    // 清空居民健康体检里面的数据
                    //((MainActivity) mContext).clearJktj();
                } else {
                    mToast.setDuration(Toast.LENGTH_SHORT);
                    mToast.setText("网络请求异常");
                    mToast.show();
                }
            }
        });
    }

    /**
     * @param response
     */
    protected void updateDzqy(DdqyqkHrs02.Response response) {
        Dzqy dzqy = (Dzqy) beanMap.get(Dzqy.class.getName());
        dzqy.SignContract = response.SignContract;
        dzqy.SignDate = response.SignDate;
        dzqy.SignPlace = response.SignPlace;
        dzqy.SignDoctor = response.SignDoctor;
        dzqy.SignUnit = response.SignUnit;
        dzqy.SignType = response.SignType;
        dzqy.SignKnow = response.SignKnow;
        dzqy.SignPhone = response.SignPhone;
        dzqy.SignStatus = response.SignStatus;
        dzqy.OperDoctor = response.OperDoctor;
        // dzqy.deviceUses = response.deviceUses;
        Global.updateDzqy = true;
    }

    /**
     * 根据网上得到的居民id，更新界面的居民基本信息
     */
    public void updateJmjbxx(com.cking.phss.dto.Ddjmjbxx7.Response response) {
        Jmjbxx mJmjbxx = (Jmjbxx) beanMap.get(Jmjbxx.class.getName());
        mJmjbxx.setResideStatusCD(response.resideStatusCD);
        mJmjbxx.setRegTypeCD(response.regTypeCD);
        mJmjbxx.setRegAddress(response.regAddress);
        mJmjbxx.setRelaName(response.relaName);
        mJmjbxx.setRelaPhone(response.RelaPhone);
        mJmjbxx.setResideCD(response.resideCD);
        mJmjbxx.setBloodCD(response.bloodCD);
        mJmjbxx.setInsuranceCD(response.insuranceCD + "");
        mJmjbxx.setInsuranceNum(response.insuranceNum);
        mJmjbxx.setAidCD(response.aidCD);
        if (showMessageStatus != 1 && StringUtil.isEmptyString(mPeopleAddress)) {// 不是读卡操作
            mJmjbxx.setRegDetail(response.regAddress);
            mJmjbxx.setRegAddress(response.regAddress);
        } else {
            mJmjbxx.setRegDetail(mPeopleAddress);
            mJmjbxx.setRegAddress(mPeopleAddress);
        }
        if (response.nationalityCD != null) {
            mJmjbxx.setNationalityCD(response.nationalityCD);
        }

        if (response.relation != null) {
            mJmjbxx.setRelation(response.relation);
        }
        mJmjbxx.setZip(response.ZIP);
        mJmjbxx.setEmail(response.email);
        mJmjbxx.setManuaINm(response.manualNm + "");// 应该为字符串
        if (response.bloodRh != null) {
            try {
                mJmjbxx.setRh(Integer.parseInt(response.bloodRh.getcD()));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        if (response.dutyDoctor != null) {
            mJmjbxx.setDutyDoctor(response.dutyDoctor);
        }

        if (response.manageOrg != null) {
            mJmjbxx.setManageOrg(response.manageOrg);
        }

        if (response.station != null) {
            mJmjbxx.setStation(response.station);
        }

        if (response.buildDate != null) {
            mJmjbxx.setBuildDate(response.buildDate);
        }

        if (response.builder != null) {
            mJmjbxx.setBuilder(response.builder);
        }

        if (response.buildOrg != null) {
            mJmjbxx.setBuildOrg(response.buildOrg);
        }

        mJmjbxx.setHeight(response.height);
        mJmjbxx.setWeight(response.weight);
        mJmjbxx.setBmi(response.BMI);
        mJmjbxx.setBust(response.Bust + "");
        mJmjbxx.sethIP(response.HIP);
        mJmjbxx.setWaist(response.waist);
        mJmjbxx.setFileStatusCD(response.fileStatusCD);

        // 先存入到数据库
        // 1.写居民登记信息表
        Resident bean = new Resident();
        bean.setPaperNum(mJmjbxx.getPaperNum());
        bean.setResidentName(mJmjbxx.getResidentName());
        bean.setCardId(mJmjbxx.getCardID());
        ResidentBll.saveBean(bean);

        // 2.写session
        loadResidentInfoIntoSession(mJmjbxx.getPaperNum());
        Global.updateJmjbxx = true;
    }

    /**
     * 得到家庭信息的设置
     */
    public void updateJmjtxx(Ddjtxxxx5.Response response) {
        Jmjtxx mJmjtxx = (Jmjtxx) beanMap.get(Jmjtxx.class.getName());
        mJmjtxx.setFamilyID(response.familyID);
        mJmjtxx.setFamilyTypeCD(response.familyTypeCD);
        mJmjtxx.setIncomeCD(response.incomeCD);
        mJmjtxx.setHouseHoldCD(response.houseHoldCD);
        try {
            int population = Integer.parseInt(response.population);
            mJmjtxx.setPopulation(population);
        } catch (NumberFormatException e) {
        }
        mJmjtxx.setEconomics(response.economics);
        mJmjtxx.setHousingLighting(response.housingLighting);
        
        if (!StringUtil.isEmptyString(response.housingRooms)) {
        	mJmjtxx.setHousingRooms(Integer.parseInt(response.housingRooms));
        }
        
        mJmjtxx.setHousingVentilation(response.housingVentilation);
        mJmjtxx.setHousingWarm(response.housingWarm);
        mJmjtxx.setAirHumidity(response.airHumidity);
        mJmjtxx.setHealthStatus(response.healthStatus);
        mJmjtxx.setWaterStatus(response.waterStatus);
        mJmjtxx.setSewageTreatment(response.sewageTreatment);
        
        
        if( null != response.stylisticDevices )
        {   	
        	mJmjtxx.setStylisticDevices(response.stylisticDevices);
        }
        
        mJmjtxx.setSmokeRemoval(response.smokeRemoval);
        mJmjtxx.setFamilyMember(response.familyMember);
        mJmjtxx.setFamilyMainProblems(response.familyMainProblems);
        mJmjtxx.setArea(response.area);
        mJmjtxx.setAvgArea(response.avgArea);
        mJmjtxx.setFloorTypeCD(response.floorTypeCD);
        mJmjtxx.setKitchenUseCD(response.kitchenUseCD);
        mJmjtxx.setKitchenFanCD(response.kitchenFanCD);
        mJmjtxx.setWaterCD(response.waterCD);
        mJmjtxx.setFuelCD(response.fuelCD);
        mJmjtxx.setSanToiletCD(response.sanToiletCD);
        mJmjtxx.setNotSanToiletCD(response.NotSanToiletCD);
        mJmjtxx.setAnimalPlaceCD(response.animalPlaceCD);
        mJmjtxx.setGarbageDealCD(response.garbageDealCD);
        mJmjtxx.setApplianceCD(response.applianceCD);
        mJmjtxx.setTransport(response.transport);
        mJmjtxx.setHousingProperty(response.housingProperty);
        Global.updateJmjtxx = true;
    }

    /**
     * 根据网上得到的居民id，更新界面的居民健康信息
     */
    public void updateJmjkxx(Ddjmjkxx7_2.Response response) {
        Jmjkxx mJmjkxx = (Jmjkxx) beanMap.get(Jmjkxx.class.getName());
        mJmjkxx.setFatherCD(response.fatherCD);
        mJmjkxx.setFatherName(response.fatherName);
        mJmjkxx.setMotherCD(response.motherCD);
        mJmjkxx.setMotherName(response.motherName);
        mJmjkxx.setBrotherCD(response.brotherCD);
        mJmjkxx.setBrotherName(response.brotherName);
        mJmjkxx.setChildCD(response.childCD);
        mJmjkxx.setChildName(response.childName);
        mJmjkxx.setOtherMemberCD(response.otherMemberCD);
        mJmjkxx.setOtherMemberName(response.otherMemberName);
        mJmjkxx.setDeformityCD(response.deformityCD);
        mJmjkxx.setDeformityCardNo(response.deformityCardNo);
        mJmjkxx.setDeformityLevel(response.deformityLevel);
        mJmjkxx.setDeformityName(response.deformityName);
        mJmjkxx.setHeredityCD(response.heredityCD);
        mJmjkxx.setHeredityName(response.heredityName);
        mJmjkxx.setExposureCD(response.exposureCD);

        List<HistoryDisease> historyDis = mJmjkxx.getHistoryDisease();
        if (historyDis != null) { // 如果有数据则先清空
            historyDis.clear();
        } else { // 如果没有则先创建一个实体
            historyDis = new ArrayList<HistoryDisease>();
            mJmjkxx.setHistoryDisease(historyDis);
        }
        if (response.historyDisease != null) {
            for (int i = 0; i < response.historyDisease.size(); i++) {
                HistoryDisease historyDisease = new HistoryDisease();
                historyDisease.sethDType(response.historyDisease.get(i).hDType);
                historyDisease.setDisSn(response.historyDisease.get(i).disSn);
                historyDisease.setDisease(response.historyDisease.get(i).disease);
                historyDisease.setDiagnoseDate(response.historyDisease.get(i).diagnoseDate);
                historyDisease.sethDReason(response.historyDisease.get(i).hDReason);
                historyDisease.setCureHos(response.historyDisease.get(i).cureHos);
                historyDisease.setHappenDate(response.historyDisease.get(i).happenDate);
                historyDisease.setResultCD(response.historyDisease.get(i).resultCD);
                historyDisease.setiCD10(response.historyDisease.get(i).iCD10);
                historyDis.add(historyDisease);
            }
        }

        List<HistoryHyper> historyHypers = mJmjkxx.getHistoryHyper();
        if (historyHypers != null) { // 如果有数据则先清空
            historyHypers.clear();
        } else { // 如果没有则先创建一个实体
            historyHypers = new ArrayList<HistoryHyper>();
            mJmjkxx.setHistoryHyper(historyHypers);
        }
        if (response.historyHyper != null) {
            for (int i = 0; i < response.historyHyper.size(); i++) {
                // ido信息不全
                HistoryHyper historyHyper = new HistoryHyper();
                // historyHyper.setCureDes(response.historyHyper.get(i).)
                historyHyper.setHyperTypeCD(response.historyHyper.get(i).hyperTypeCD);
                historyHyper.setDisSn(response.historyHyper.get(i).hyperSn);
                historyHyper.setHyperSource(response.historyHyper.get(i).hyperSource);
                historyHyper.setCureDes(response.historyHyper.get(i).cureDes);
                historyHyper.setHappenDate(response.historyHyper.get(i).happenDate);
                historyHyper.setHyperReason(response.historyHyper.get(i).hyperReason);
                historyHypers.add(historyHyper);
            }
        }
        Global.updateJmjkxx = true;
    }

    /**
     * 根据网上得到的居民id，更新界面的居民行为习惯
     */
    public void updateJmxwxg(Ddjmxwxg7_1.Response response) {
        Jmxwxg mJmxwxg = (Jmxwxg) beanMap.get(Jmxwxg.class.getName());
        mJmxwxg.setSmokeCD(response.smokeCD);
        mJmxwxg.setSmokeAge(response.smokeAge);
        mJmxwxg.setNoSmokeAge(response.noSmokeAge);
        mJmxwxg.setSmokeDay(response.smokeDay);
        mJmxwxg.setSmokeDayPast(response.smokeDayPast);
        mJmxwxg.setDrinkCD(response.drinkCD);
        mJmxwxg.setDrinkTypeCD(response.drinkTypeCD);
        mJmxwxg.setDrinkAmount(response.drinkAmount);
        mJmxwxg.setNoDrinkCD(response.noDrinkCD);
        mJmxwxg.setNoDrinkAge(response.noDrinkAge);
        mJmxwxg.setPastDrinkNum(response.pastDrinkNum);
        mJmxwxg.setPastDrinkAmount(response.pastDrinkAmount);
        mJmxwxg.setPastDrinkTypeCD(response.pastDrinkTypeCD);
        mJmxwxg.setFoodCD(String.valueOf(response.foodCD));
        mJmxwxg.setBrushTeethCD(response.brushTeethCD);
        mJmxwxg.setSportRateCD(response.sportRateCD);
        mJmxwxg.setSportTypeCD(response.sportTypeCD);
        mJmxwxg.setSportTypeElse(response.sportTypeElse);
        mJmxwxg.setSportTime(response.sportTime);
        mJmxwxg.setPrimaryEvent(response.primaryEventCD);
        mJmxwxg.setPrimaryEventName(response.primaryEventName);
        Global.updateJmxwxg = true;
    }

    // 移除掉上一个居民信息的数据
    private void removePreviousResidentData() {
        // 对全局bean重新赋值，从而实现清空
        Global.jmjbxx = new Jmjbxx();
        Global.updateJmjbxx = false; // 是否已更新
        Global.jmjtxx = new Jmjtxx();
        Global.updateJmjtxx = false; // 是否已更新
        Global.jmjkxx = new Jmjkxx();
        Global.updateJmjkxx = false; // 是否已更新
        Global.jmxwxg = new Jmxwxg();
        Global.updateJmxwxg = false; // 是否已更新
        Global.dzqy = new Dzqy();
        Global.updateDzqy = false; // 是否已更新
        Global.jktjKstj = new Jktj_kstj();
        Global.sfgljlGxy = new Sfgljl_gxy();
        Global.sfgljlTnb = new Sfgljl_tnb();
        Global.sfgljlJsb = new Sfgljl_jsb();
        Global.sfgljlLnsf = new Sfgljl_lnsf();
        Global.sfgljlCjsf = new Sfgljl_cjsf();
        // 清空体检编号
        Global.tjbh = null;

        Jmjbxx jmjbxx = Global.jmjbxx;
        // 只有清空原来的身份证地址才会避免有上次的读卡动作影响本次结果
        mPeopleAddress = null;

        if (showMessageStatus == 1) {// 进行的是读卡的操作，要保存读卡得到的数据，在清除界面数据
            // jmjbxx.setResidentName(mResidentNameText.getText().toString());
            // jmjbxx.setPaperNum(mPaperNumEdit.getText().toString());
            // // 设置性别
            // int sexPosition = page01.mSexSpinner.getSelectedItemPosition();
            // if (sexPosition != 3) {
            // jmjbxx.setSexCD(sexPosition);
            // } else {
            // jmjbxx.setSexCD(9);
            // }
            //
            // // 设置生日
            // jmjbxx.setBirthDay(page01.mBirthdayText.getText().toString());
            //
            // // 设置民族
            // jmjbxx.setFlokCD(new
            // Jmjbxx.FolkCD(page01.mFolkSpinner.getSelectedItemPosition() + 1,
            // (String) page01.mFolkSpinner.getAdapter().getItem(
            // page01.mFolkSpinner.getSelectedItemPosition())));
        } else if (showMessageStatus == 3 || showMessageStatus == 4 || showMessageStatus == 5) {// 根据身份证搜索
            jmjbxx.setPaperNum(sfzhEditText.getText().toString());
            jmjbxx.setResidentName(xmEditText.getText().toString());
            jmjbxx.setCardID(khEditText.getText().toString());
        }
        // 先存入到数据库
        // 1.写居民登记信息表
        // Resident bean = new Resident();
        // bean.setPaperNum(jmjbxx.getPaperNum());
        // bean.setResidentName(jmjbxx.getResidentName());
        // ResidentBll.saveBean(bean);
        // 2.写session
        loadResidentInfoIntoSession(jmjbxx.getPaperNum());
        beanMap.put(Jmjbxx.class.getName(), jmjbxx);
        beanMap.put(Jmjtxx.class.getName(), Global.jmjtxx);
        beanMap.put(Jmjkxx.class.getName(), Global.jmjkxx);
        beanMap.put(Jmxwxg.class.getName(), Global.jmxwxg);
        beanMap.put(Dzqy.class.getName(), Global.dzqy);
        setValue();

        // 并且，把其他几个页面的有关信息清除
        JktjTzbsPage01.rateValues = null;// 清除体质辨识的结果信息
//        if (JktjBodyView.mRegisterIdText != null)// 清除体检登记
//            JktjBodyView.mRegisterIdText.setText("");
        profileImageView.setImageBitmap(null);

        clear();
    }

    private void getReadCardData() {// 如果是读卡的话，要先拿到读卡得到的数据，如姓名、身份证、性别、名族、生日
        Jmjbxx jmjbxx = (Jmjbxx) beanMap.get(Jmjbxx.class.getName());
        jmjbxx.setResidentName(xmEditText.getText().toString().trim());
        jmjbxx.setPaperNum(sfzhEditText.getText().toString().trim());
        int sexPosition = page01.mSexSpinner.getSelectedItemPosition();
        if (sexPosition == 3) {
            jmjbxx.setSexCD(9);
        } else {
            jmjbxx.setSexCD(sexPosition);
        }
        // 设置民族
        int flokcd = page01.mFolkSpinner.getSelectedItemPosition() + 1;
        String flokStr;
        if (flokcd < 10)
            flokStr = "0" + flokcd;
        else {
            flokStr = "" + flokcd;
        }
        jmjbxx.setFlokCD(new BeanID(flokStr, (String) page01.mFolkSpinner.getAdapter()
                .getItem(page01.mFolkSpinner.getSelectedItemPosition())));
        // 生日
        jmjbxx.setBirthDay(page01.mBirthdayText.getText().toString().trim());

    }

    public void parsePeopleAddress() {
        Jmjbxx jmjbxx = (Jmjbxx) beanMap.get(Jmjbxx.class.getName());
        // 设置户籍地址
        jmjbxx.setRegDetail(mPeopleAddress);
        jmjbxx.setRegAddress(mPeopleAddress);
        /*
         * // 湖北省武汉市洪山区关山大道463号 if (mPeopleAddress == null ||
         * mPeopleAddress.trim().equals("")) return; // 解析省 int tempIndex =
         * mPeopleAddress.indexOf("省");// 省的位置 if (tempIndex != -1) { String
         * proName = mPeopleAddress.substring(0, tempIndex + 1);
         * jmjbxx.setNowProvince(new Province(0, proName));// 因为暂时不知道id，默认为0 }
         * 
         * mPeopleAddress = mPeopleAddress.substring(tempIndex + 1,
         * mPeopleAddress.length()); // 解析市 tempIndex =
         * mPeopleAddress.indexOf("市");// 市的位置 if (tempIndex != -1) { String
         * cityName = mPeopleAddress.substring(0, tempIndex + 1);
         * jmjbxx.setNowCity(new City(0, cityName));// 因为暂时不知道id，默认为0 }
         * 
         * mPeopleAddress = mPeopleAddress.substring(tempIndex + 1,
         * mPeopleAddress.length()); // 解析区 tempIndex =
         * mPeopleAddress.indexOf("区");// 区的位置 if (tempIndex != -1) { String
         * disName = mPeopleAddress.substring(0, tempIndex + 1);
         * jmjbxx.setNowDistrict(new District(0, disName));// 因为暂时不知道id，默认为0 }
         * 
         * mPeopleAddress = mPeopleAddress.substring(tempIndex + 1,
         * mPeopleAddress.length()); // 解析道 tempIndex =
         * mPeopleAddress.indexOf("道");// 道的位置 if (tempIndex != -1) { String
         * streetName = mPeopleAddress.substring(0, tempIndex + 1);
         * jmjbxx.setNowStreet(new Street(0, streetName));// 因为暂时不知道id，默认为0 }
         * 
         * mPeopleAddress = mPeopleAddress.substring(tempIndex + 1,
         * mPeopleAddress.length()); // 解析社区 tempIndex =
         * mPeopleAddress.indexOf("区");// 社区的位置 if (tempIndex != -1) { String
         * zoneName = mPeopleAddress.substring(0, tempIndex + 1);
         * jmjbxx.setNowZone(new Zone("0", zoneName));// 因为暂时不知道id，默认为0 }
         * 
         * mPeopleAddress = mPeopleAddress.substring(tempIndex + 1,
         * mPeopleAddress.length()); // 解析路 tempIndex =
         * mPeopleAddress.indexOf("路");// 路的位置 if (tempIndex != -1) { String
         * roadName = mPeopleAddress.substring(0, tempIndex + 1);
         * jmjbxx.setNowRoad(new Road("0", roadName));// 因为暂时不知道id，默认为0 }
         * 
         * mPeopleAddress = mPeopleAddress.substring(tempIndex + 1,
         * mPeopleAddress.length()); // 解析弄 tempIndex =
         * mPeopleAddress.indexOf("弄");// 弄的位置 if (tempIndex != -1) { String
         * nName = mPeopleAddress.substring(0, tempIndex + 1);
         * jmjbxx.setNowN(nName); }
         * 
         * mPeopleAddress = mPeopleAddress.substring(tempIndex + 1,
         * mPeopleAddress.length()); // 解析号 tempIndex =
         * mPeopleAddress.indexOf("号");// 号的位置 if (tempIndex != -1) { String
         * hName = mPeopleAddress.substring(0, tempIndex + 1);
         * jmjbxx.setNowH(hName); }
         * 
         * mPeopleAddress = mPeopleAddress.substring(tempIndex + 1,
         * mPeopleAddress.length()); // 解析室 tempIndex =
         * mPeopleAddress.indexOf("室");// 室的位置 if (tempIndex != -1) { String
         * sName = mPeopleAddress.substring(0, tempIndex + 1);
         * jmjbxx.setNowS(sName); }
         */
    }

    private String getNowDetail(Jmjbxx jmjbxx) {
        StringBuilder sb = new StringBuilder();
        // 将省市等保存到NowDetail节点
        if (jmjbxx != null) {
            BeanID province = jmjbxx.getNowProvince();
            if (province != null && province.getTagValue() != null) {
                sb.append(province.getTagValue());
            }

            BeanID city = jmjbxx.getNowCity();
            if (city != null && city.getTagValue() != null) {
                sb.append(city.getTagValue());
            }

            BeanID district = jmjbxx.getNowDistrict();
            if (district != null && district.getTagValue() != null) {
                sb.append(district.getTagValue());
            }

            BeanID street = jmjbxx.getNowStreet();
            if (street != null && street.getTagValue() != null) {
                sb.append(street.getTagValue());
            }

            BeanID zone = jmjbxx.getNowZone();
            if (zone != null && zone.getTagValue() != null) {
                sb.append(zone.getTagValue());
            }

            BeanID road = jmjbxx.getNowRoad();
            if (road != null && road.getTagValue() != null) {
                sb.append(road.getTagValue());
            }

            if (jmjbxx.getNowN() != null && !jmjbxx.getNowN().equals("")) {
                sb.append(jmjbxx.getNowN() + "弄（幢）");
            }

            if (jmjbxx.getNowH() != null && !jmjbxx.getNowH().equals("")) {
                sb.append(jmjbxx.getNowH() + "号");
            }

            if (jmjbxx.getNowS() != null && !jmjbxx.getNowS().equals("")) {
                sb.append(jmjbxx.getNowS() + "室");
            }
        }
        return sb.toString();
    }

    /**
     * @param jbxxBodyGrxx
     */
    public void showItemByIndex(int index) {
        mGuidePager.showPage(index);
    }

    public void getBeanFromDB() {
        if (beanMap == null)
            return;
        if (Global.updateJmjbxx) {
            setValue();
            return;
        }
        List<Class<? extends IBean>> listBean = new ArrayList<Class<? extends IBean>>();
        listBean.add(Jmjbxx.class);
        BeanUtil.getInstance().getJbxxFromDb(listBean, new BeanUtil.OnResultFromDb() {
            @Override
            public void onResult(List<IBean> listBean, boolean isSucc) {
                if (listBean == null || listBean.size() < 0)
                    return;
                if (listBean.get(0) != null) {
                    beanMap.put(Jmjbxx.class.getName(), listBean.get(0));
                    setValue();
                }
            }
        });

    }
    
    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        // 设置读射频卡
        if (spdkqDeviceId == TagConstants.XML_VALUE_ID_HANDE_FKQ) {
            atuoReadKhService();
        }

        // 把按钮加入与网络相关的视图列表
        Global.globalViewList.add(mUploadButton);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (spdkqDeviceId == TagConstants.XML_VALUE_ID_HANDE_FKQ) {
            mBluethoothClient4Hande.stop();
        }

        // 把按钮移除与网络相关的视图列表
        Global.globalViewList.remove(mUploadButton);
        getValue();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.cking.phss.page.IPage#clear()
     */
    @Override
    public void clear() {
    }

    /**
     * @param paperNum
     */
    public void autoSearchPaperNum(String paperNum) {
        showMessageStatus = 3;// 根据身份证直接查找操作
        removePreviousResidentData();// 移除上一个居民的信息
        sfzhEditText.setText(paperNum);
        if (paperNum.equals("")) {
            mToast.setText("根据身份证查找时，身份证号不能为空");
            mToast.show();
        } else {
            searchResident(paperNum);
        }
    }
}
