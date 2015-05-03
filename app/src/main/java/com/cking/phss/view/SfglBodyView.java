package com.cking.phss.view;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.xinhuaxing.eshow.constants.Constants;
import net.xinhuaxing.util.StringUtil;
import android.content.Context;
import android.net.Uri;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cking.phss.R;
import com.cking.phss.bean.BeanID;
import com.cking.phss.bean.BeanUtil;
import com.cking.phss.bean.BeanUtil.OnResultFromWeb;
import com.cking.phss.bean.IBean;
import com.cking.phss.bean.Jktj_kstj;
import com.cking.phss.bean.Jmjbxx;
import com.cking.phss.bean.Jmjkxx;
import com.cking.phss.bean.Jmjtxx;
import com.cking.phss.bean.Jmxwxg;
import com.cking.phss.dialog.SfglEtfsBaokaDialog;
import com.cking.phss.dialog.SfglEtfsBaokaDialog.OnEtfsBaokaResultListener;
import com.cking.phss.dialog.SfglGxyBaokaDialog;
import com.cking.phss.dialog.SfglGxyBaokaDialog.OnGxyBaokaResultListener;
import com.cking.phss.dialog.SfglJsbBaokaDialog;
import com.cking.phss.dialog.SfglJsbBaokaDialog.OnJsbBaokaResultListener;
import com.cking.phss.dialog.SfglNzzBaokaDialog;
import com.cking.phss.dialog.SfglNzzBaokaDialog.OnNzzBaokaResultListener;
import com.cking.phss.dialog.SfglTnbBaokaDialog;
import com.cking.phss.dialog.SfglTnbBaokaDialog.OnTnbBaokaResultListener;
import com.cking.phss.dialog.SfglYcfsBaokaDialog;
import com.cking.phss.dialog.SfglYcfsBaokaDialog.OnYcfsBaokaResultListener;
import com.cking.phss.dto.Ddgywsftxlb81;
import com.cking.phss.dto.IDto;
import com.cking.phss.dto.MbsftjJ010;
import com.cking.phss.dto.innner.WarnInfo;
import com.cking.phss.global.Global;
import com.cking.phss.page.IPage;
import com.cking.phss.page.ISfglPage;
import com.cking.phss.page.SfglCjsfPage;
import com.cking.phss.page.SfglEtfsPage;
import com.cking.phss.page.SfglGxyPage;
import com.cking.phss.page.SfglJsbPage;
import com.cking.phss.page.SfglLnsfPage;
import com.cking.phss.page.SfglNzzPage;
import com.cking.phss.page.SfglTnbPage;
import com.cking.phss.page.SfglYcfsPage;
import com.cking.phss.util.BluetoothUtil;
import com.cking.phss.util.CalendarUtil;
import com.cking.phss.util.Constant;
import com.cking.phss.util.JgxxConfigFactory;
import com.cking.application.MyApplication;
import com.cking.phss.util.TispToastFactory;
import com.cking.phss.widget.GuidePager;
import com.cking.phss.widget.GuidePager.OnPageChangeListener;
import com.cking.phss.xml4jgxx.tags.constants.TagConstants;

public class SfglBodyView extends LinearLayout implements IPage {

    protected static final String TAG = "SfglBodyView";
    public GuidePager mGuidePager = null;
    private Map<String, IBean> beanMap = new HashMap<String, IBean>();
    private Jmjbxx mJmjbxx = null;
    private Context mContext = null;
    public int checkReport = 1;// 检查是否进入报卡状态，1表示没有 ，2表示进入高血压报卡，3表示进入糖尿病报卡

    String sfbh = null; // 随访编号
    /**
     * 全局控件
     * 
     * @param context
     */
//    public RadioGroup mTabRadios = null;
    private Toast mToast = null;

    SfglGxyPage mSfglGxyPage = null;
    SfglTnbPage mSfglTnbPage = null;
    SfglNzzPage mSfglNzzPage = null;
    SfglJsbPage mSfglJsbPage = null;
    SfglYcfsPage mSfglYcfsPage = null;
    SfglEtfsPage mSfglEtfsPage = null;
    SfglLnsfPage mSfglLnsfPage = null;
    SfglCjsfPage mSfglCjsfPage = null;
    
    ImageView sexImageView;
    TextView sexTextView;
    TextView ageTextView;
    TextView sfzhTextView;
    TextView dabhTextView;
    TextView khTextView;
    TextView residentNameTextView;
    ImageView profileImageView;
    
    MyPagerAdapter myPagerAdapter;
    private int deviceId = 0;
    
    Button xgButton; // 修改
    Button xzButton; // 新增
    Button scButton; // 上传
    Button bcButton; // 保存
    Button bkButton; // 报卡
    Button dyButton; // 打印
    
    private class SfglParams {
        public int sflbParams;
        public String sftjParams;
        public SfglParams(int sflbParams, String sftjParams) {
            this.sflbParams = sflbParams;
            this.sftjParams = sftjParams;
        }
    }

    private final SfglParams[] SFGL_PARAMS = new SfglParams[] { new SfglParams(1, "0101"),
            new SfglParams(2, "0102"), new SfglParams(3, "0105"), new SfglParams(4, "0203"),
            new SfglParams(5, "0206"), new SfglParams(6, "0205") };

    public interface OnGetSfbhListener {
        public void onGetSfbh(String sfbh);
    }

    private OnPageChangeListener mOnPageChangeListener = null;
    
    public void setOnPageChangeListener(OnPageChangeListener listener) {
        mOnPageChangeListener = listener;
    }
    
    public SfglBodyView(Context context) {
        super(context);

        // 加载bean的信息
        beanMap.put(Jmjbxx.class.getName(), Global.jmjbxx);
        beanMap.put(Jmjtxx.class.getName(), Global.jmjtxx);
        beanMap.put(Jmjkxx.class.getName(), Global.jmjkxx);
        beanMap.put(Jmxwxg.class.getName(), Global.jmxwxg);
        beanMap.put(Jktj_kstj.class.getName(), Global.jktjKstj);
        
        init(context);
    }

    /**
     * @param context
     * @param attrs
     */
    public SfglBodyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(final Context context) {
        mContext = context;
        mToast = TispToastFactory.getToast(context, "");
        deviceId = JgxxConfigFactory.findIdByName(mContext, TagConstants.XML_VALUE_NAME_RMDYJ);
        LayoutInflater.from(context).inflate(R.layout.fragment_sfgl_body_layout, this);

        loadPage(context, this);
    }

    public void loadPage(Context context, ViewGroup viewGroup) {

//        mTabRadios = (RadioGroup) findViewById(R.id.tab_radio);
        // mGuidePager.setFootTipsText("20","4","16");
        profileImageView = (ImageView) findViewById(R.id.profileImageView);
        residentNameTextView = (TextView) findViewById(R.id.residentNameTextView);
        sexImageView = (ImageView) findViewById(R.id.sexImageView);
        sexTextView = (TextView) findViewById(R.id.sexTextView);
        ageTextView = (TextView) findViewById(R.id.ageTextView);
        sfzhTextView = (TextView) findViewById(R.id.sfzhTextView);
        dabhTextView = (TextView) findViewById(R.id.dabhTextView);
        khTextView = (TextView) findViewById(R.id.khTextView);
        
        xgButton = (Button) findViewById(R.id.xgButton);
        xzButton = (Button) findViewById(R.id.xzButton);
        scButton = (Button) findViewById(R.id.scButton);
        bcButton = (Button) findViewById(R.id.bcButton);
        bkButton = (Button) findViewById(R.id.bkButton);
        dyButton = (Button) findViewById(R.id.dyButton);

        if (deviceId == TagConstants.XML_VALUE_ID_SPRT_SPRMTIIIBTA) {
            if (!BluetoothUtil.isPared(Constants.BT_NAME_SPRT_SPRMTIIIBTA)) {
                dyButton.setEnabled(false);
            } else {
                dyButton.setEnabled(true);
            }
        } else if (deviceId == TagConstants.XML_VALUE_ID_QS_BZ) {
            if (!BluetoothUtil.isPared(Constants.BT_NAME_QS_BZ)) {
                dyButton.setEnabled(false);
            } else {
                dyButton.setEnabled(true);
            }
        } else if (deviceId == TagConstants.XML_VALUE_ID_QT_BZ3) {
            if (!BluetoothUtil.isPared(Constants.BT_NAME_SPRT_SPRMTIIIBTA)) {
                dyButton.setEnabled(false);
            } else {
                dyButton.setEnabled(true);
            }
        } else {
            dyButton.setEnabled(false);
        }

        if (Global.isLocalLogin) {
            scButton.setEnabled(false);
        } else {
            scButton.setEnabled(true);
        }
        xgButton.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View arg0) {
                edit();
            }
        });
        xzButton.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View arg0) {
                add();
            }
        });
        scButton.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View arg0) {
                upload();
            }
        });
        bcButton.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View arg0) {
                save();
            }
        });
        bkButton.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View arg0) {
                baoka();
            }
        });
        dyButton.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View arg0) {
                print();
            }
        });
        mGuidePager = (GuidePager) findViewById(R.id.guide_pager);
        myPagerAdapter = new MyPagerAdapter(context);
        mGuidePager.setAdapter(myPagerAdapter);
        mGuidePager.showPage(0);
        getSflb(0); // 获取随访列表
        getSftj(0); // 获取随访统计
        mGuidePager.setOnPageChangeListener(new OnPageChangeListener() { // 当选中某页的回调
            
            @Override
            public void onPageSelected(int index) {
                if (mOnPageChangeListener != null) {
                    mOnPageChangeListener.onPageSelected(index);
                }
                if (index < SFGL_PARAMS.length) {
                    getSflb(index); // 获取随访列表
                    getSftj(index); // 获取随访统计
                }
            }
        });
    }

    /**
     * 
     */
    protected void print() {
        Jmjbxx mJmjbxx = (Jmjbxx) beanMap.get(Jmjbxx.class.getName());
        if (mJmjbxx == null || mJmjbxx.getResidentName().equals("")
                || mJmjbxx.getPaperNum().equals("")) {
            mToast.setText(R.string.toast_info_none_resident);
            mToast.show();
        }
        // 保存结果
        if (StringUtil.isEmptyString(sfbh)) {
            mToast.setText("无法获取随访编号！");
            mToast.show();
        } else {
            getValue();
            mToast.setText("正在准备打印，请稍候...");
            mToast.show();
            switch (mGuidePager.getSelectIndex()) {
                case 0: // 高血压
                    mSfglGxyPage.print();
                    break;
                case 1: // 糖尿病
                    mSfglTnbPage.print();
                    break;
                case 2: // 脑卒中
                    mSfglNzzPage.print();
                    break;
                case 3: // 精神病
                    mSfglJsbPage.print();
                    break;
                case 4: // 孕产访视
                    mSfglYcfsPage.print();
                    break;
                case 5: // 儿童访视
                    mSfglEtfsPage.print();
                    break;
                case 6: // 老年随访
                    mSfglLnsfPage.print();
                    break;
                case 7: // 残疾随访
                    mSfglCjsfPage.print();
                    break;
                default:
                    mToast.setText("此项目无法完成此操作。");
                    mToast.show();
                    break;
            }
        }
    }

    /**
     * 
     */
    protected void baoka() {
        Jmjbxx mJmjbxx = (Jmjbxx) beanMap.get(Jmjbxx.class.getName());
        if (mJmjbxx == null) {
            mToast.setText(R.string.toast_info_none_resident);
            mToast.show();
            return;
        }

        if (mJmjbxx.getPaperNum().isEmpty() || mJmjbxx.getResidentName().isEmpty()
                || mJmjbxx.getResidentID().isEmpty()) {
            mToast.setText(R.string.toast_info_none_resident);
            mToast.show();
            return;
        }

        switch (mGuidePager.getSelectIndex()) {
            case 0: // 高血压
                final SfglGxyBaokaDialog sfglGxyBaokaDialog = new SfglGxyBaokaDialog(mContext);
                sfglGxyBaokaDialog.setOnGxyBaokaResultListener(new OnGxyBaokaResultListener() {

                    @Override
                    public void onConfirm() {
                        checkReport = 1;
                        sfglGxyBaokaDialog.saveReportToWeb();
                    }

                    @Override
                    public void onCancel() {
                    }
                });

                if (!Global.isLocalLogin) {
                    sfglGxyBaokaDialog.getReportFromWeb();
                } else {
                    sfglGxyBaokaDialog.setValue();
                }
                sfglGxyBaokaDialog.show();
                break;
            case 1: // 糖尿病
                final SfglTnbBaokaDialog sfglTnbBaokaDialog = new SfglTnbBaokaDialog(mContext);
                sfglTnbBaokaDialog.setOnTnbBaokaResultListener(new OnTnbBaokaResultListener() {

                    @Override
                    public void onConfirm() {
                        checkReport = 1;
                        sfglTnbBaokaDialog.saveReportToWeb();
                    }

                    @Override
                    public void onCancel() {
                    }
                });

                if (!Global.isLocalLogin) {
                    sfglTnbBaokaDialog.getReportFromWeb();
                } else {
                    sfglTnbBaokaDialog.setValue();
                }
                sfglTnbBaokaDialog.show();
                break;
            case 2: // 脑卒中
                final SfglNzzBaokaDialog sfglNzzBaokaDialog = new SfglNzzBaokaDialog(mContext);
                sfglNzzBaokaDialog.setOnNzzBaokaResultListener(new OnNzzBaokaResultListener() {

                    @Override
                    public void onConfirm() {
                        checkReport = 1;
                        sfglNzzBaokaDialog.saveReportToWeb();
                    }

                    @Override
                    public void onCancel() {
                    }
                });

                if (!Global.isLocalLogin) {
                    sfglNzzBaokaDialog.getReportFromWeb();
                } else {
                    sfglNzzBaokaDialog.setLocalValue();
                }
                sfglNzzBaokaDialog.show();
                break;
            case 3: // 精神病
                final SfglJsbBaokaDialog sfglJsbBaokaDialog = new SfglJsbBaokaDialog(mContext);
                sfglJsbBaokaDialog.setOnJsbBaokaResultListener(new OnJsbBaokaResultListener() {

                    @Override
                    public void onConfirm() {
                        checkReport = 1;
                        sfglJsbBaokaDialog.saveReportToWeb();
                    }

                    @Override
                    public void onCancel() {
                    }
                });
                if (!Global.isLocalLogin) {
                    sfglJsbBaokaDialog.getReportFromWeb();
                } else {
                    sfglJsbBaokaDialog.setValue();
                }
                sfglJsbBaokaDialog.show();
                break;
            case 4: // 孕产访视
                final SfglYcfsBaokaDialog sfglYcfsBaokaDialog = new SfglYcfsBaokaDialog(mContext);
                sfglYcfsBaokaDialog.setOnYcfsBaokaResultListener(new OnYcfsBaokaResultListener() {

                    @Override
                    public void onConfirm() {
                        checkReport = 1;
                        sfglYcfsBaokaDialog.saveReportToWeb();
                    }

                    @Override
                    public void onCancel() {
                    }
                });

                if (!Global.isLocalLogin) {
                    sfglYcfsBaokaDialog.getReportFromWeb();
                } else {
                    sfglYcfsBaokaDialog.setLocalValue();
                }
                sfglYcfsBaokaDialog.show();
                break;
            case 5: // 儿童访视
                final SfglEtfsBaokaDialog sfglEtfsBaokaDialog = new SfglEtfsBaokaDialog(mContext);
                sfglEtfsBaokaDialog.setOnEtfsBaokaResultListener(new OnEtfsBaokaResultListener() {

                    @Override
                    public void onConfirm() {
                        checkReport = 1;
                        sfglEtfsBaokaDialog.saveReportToWeb();
                    }

                    @Override
                    public void onCancel() {
                    }
                });

                if (!Global.isLocalLogin) {
                    sfglEtfsBaokaDialog.getReportFromWeb();
                } else {
                    sfglEtfsBaokaDialog.setLocalValue();
                }
                sfglEtfsBaokaDialog.show();
                break;
            default:
                mToast.setText("此项目不需要报卡。");
                mToast.show();
                break;
        }
    }

    /**
     * 
     */
    protected void save() {
        switch (mGuidePager.getSelectIndex()) {
            case 0: // 高血压
                mSfglGxyPage.saveValueToDb();
                break;
            case 1: // 糖尿病
                mSfglTnbPage.saveValueToDb();
                break;
            case 2: // 脑卒中
                mSfglNzzPage.saveValueToDb();
                break;
            case 3: // 精神病
                mSfglJsbPage.saveValueToDb();
                break;
            case 4: // 孕产访视
                mSfglYcfsPage.saveValueToDb();
                break;
            case 5: // 儿童访视
                mSfglEtfsPage.saveValueToDb();
                break;
            case 6: // 老年随访
                mSfglLnsfPage.saveValueToDb();
                break;
            case 7: // 残疾随访
                mSfglCjsfPage.saveValueToDb();
                break;
            default:
                mToast.setText("此项目无法完成此操作。");
                mToast.show();
                break;
        }
    }

    /**
     * 
     */
    protected void upload() {
        if (Global.isLocalLogin) {
            mToast.setText("当前是本地登录，不支持上传操作。");
            mToast.show();
            return;
        }
        switch (mGuidePager.getSelectIndex()) {
            case 0: // 高血压
                mSfglGxyPage.saveValueToWeb(onGetSfbhListener);
                break;
            case 1: // 糖尿病
                mSfglTnbPage.saveValueToWeb(onGetSfbhListener);
                break;
            case 2: // 脑卒中
                mSfglNzzPage.saveValueToWeb(onGetSfbhListener);
                break;
            case 3: // 精神病
                mSfglJsbPage.saveValueToWeb(onGetSfbhListener);
                break;
            case 4: // 孕产访视
                mSfglYcfsPage.saveValueToWeb(onGetSfbhListener);
                break;
            case 5: // 儿童访视
                mSfglEtfsPage.saveValueToWeb(onGetSfbhListener);
                break;
            case 6: // 老年随访
                mSfglLnsfPage.saveValueToWeb(onGetSfbhListener);
                break;
            case 7: // 残疾随访
                mSfglCjsfPage.saveValueToWeb(onGetSfbhListener);
                break;
            default:
                mToast.setText("此项目无法完成此操作。");
                mToast.show();
                break;
        }
    }

    OnGetSfbhListener onGetSfbhListener = new OnGetSfbhListener() {

        @Override
        public void onGetSfbh(String sfbh) {
            SfglBodyView.this.sfbh = sfbh;
            dabhTextView.setText(sfbh);
        }

    };

    /**
     * 
     */
    protected void add() {
        switch (mGuidePager.getSelectIndex()) {
            case 0: // 高血压
                mSfglGxyPage.operateFlag = 1;
                if (Global.isLocalLogin) {
                    mSfglGxyPage.getLastValueFromDb(onGetSfbhListener);
                } else {
                    mSfglGxyPage.getLastValue(onGetSfbhListener);
                }
                break;
            case 1: // 糖尿病
                mSfglTnbPage.operateFlag = 1;
                if (Global.isLocalLogin) {
                    mSfglTnbPage.getLastValueFromDb(onGetSfbhListener);
                } else {
                    mSfglTnbPage.getLastValue(onGetSfbhListener);
                }
                break;
            case 2: // 脑卒中
                mSfglNzzPage.operateFlag = 1;
                if (Global.isLocalLogin) {
                    mSfglNzzPage.getLastValueFromDb(onGetSfbhListener);
                } else {
                    mSfglNzzPage.getLastValue(onGetSfbhListener);
                }
                break;
            case 3: // 精神病
                mSfglJsbPage.operateFlag = 1;
                if (Global.isLocalLogin) {
                    mSfglJsbPage.getLastValueFromDb(onGetSfbhListener);
                } else {
                    mSfglJsbPage.getLastValue(onGetSfbhListener);
                }
                break;
            case 4: // 孕产访视
                mSfglYcfsPage.operateFlag = 1;
                if (Global.isLocalLogin) {
                    mSfglYcfsPage.getLastValueFromDb(onGetSfbhListener);
                } else {
                    mSfglYcfsPage.getLastValue(onGetSfbhListener);
                }
                break;
            case 5: // 儿童访视
                mSfglEtfsPage.operateFlag = 1;
                if (Global.isLocalLogin) {
                    mSfglEtfsPage.getLastValueFromDb(onGetSfbhListener);
                } else {
                    mSfglEtfsPage.getLastValue(onGetSfbhListener);
                }
                break;
            case 6: // 老年随访
                mSfglLnsfPage.operateFlag = 1;
                if (Global.isLocalLogin) {
                    mSfglLnsfPage.getLastValueFromDb(onGetSfbhListener);
                } else {
                    mSfglLnsfPage.getLastValue(onGetSfbhListener);
                }
                break;
            case 7: // 残疾随访
                mSfglCjsfPage.operateFlag = 1;
                if (Global.isLocalLogin) {
                    mSfglCjsfPage.getLastValueFromDb(onGetSfbhListener);
                } else {
                    mSfglCjsfPage.getLastValue(onGetSfbhListener);
                }
                break;
            default:
                mToast.setText("此项目无法完成此操作。");
                mToast.show();
                break;
        }
    }

    /**
     * 
     */
    protected void edit() {
        switch (mGuidePager.getSelectIndex()) {
            case 0: // 高血压
                mSfglGxyPage.operateFlag = 2;
                if (Global.isLocalLogin) {
                    mSfglGxyPage.getLastValueFromDb(onGetSfbhListener);
                } else {
                    mSfglGxyPage.getLastValue(onGetSfbhListener);
                }
                break;
            case 1: // 糖尿病
                mSfglTnbPage.operateFlag = 2;
                if (Global.isLocalLogin) {
                    mSfglTnbPage.getLastValueFromDb(onGetSfbhListener);
                } else {
                    mSfglTnbPage.getLastValue(onGetSfbhListener);
                }
                break;
            case 2: // 脑卒中
                mSfglNzzPage.operateFlag = 2;
                if (Global.isLocalLogin) {
                    mSfglNzzPage.getLastValueFromDb(onGetSfbhListener);
                } else {
                    mSfglNzzPage.getLastValue(onGetSfbhListener);
                }
                break;
            case 3: // 精神病
                mSfglJsbPage.operateFlag = 2;
                if (Global.isLocalLogin) {
                    mSfglJsbPage.getLastValueFromDb(onGetSfbhListener);
                } else {
                    mSfglJsbPage.getLastValue(onGetSfbhListener);
                }
                break;
            case 4: // 孕产访视
                mSfglYcfsPage.operateFlag = 2;
                if (Global.isLocalLogin) {
                    mSfglYcfsPage.getLastValueFromDb(onGetSfbhListener);
                } else {
                    mSfglYcfsPage.getLastValue(onGetSfbhListener);
                }
                break;
            case 5: // 儿童访视
                mSfglEtfsPage.operateFlag = 2;
                if (Global.isLocalLogin) {
                    mSfglEtfsPage.getLastValueFromDb(onGetSfbhListener);
                } else {
                    mSfglEtfsPage.getLastValue(onGetSfbhListener);
                }
                break;
            case 6: // 老年随访
                mSfglLnsfPage.operateFlag = 2;
                if (Global.isLocalLogin) {
                    mSfglLnsfPage.getLastValueFromDb(onGetSfbhListener);
                } else {
                    mSfglLnsfPage.getLastValue(onGetSfbhListener);
                }
                break;
            case 7: // 残疾随访
                mSfglCjsfPage.operateFlag = 2;
                if (Global.isLocalLogin) {
                    mSfglCjsfPage.getLastValueFromDb(onGetSfbhListener);
                } else {
                    mSfglCjsfPage.getLastValue(onGetSfbhListener);
                }
                break;
            default:
                mToast.setText("此项目无法完成此操作。");
                mToast.show();
                break;
        }
    }

    // viewpager数据绑定
    class MyPagerAdapter extends PagerAdapter {
        List<View> mPageList = new ArrayList<View>();

        public MyPagerAdapter(Context c) {
            super();
            // 添加页面
            mSfglGxyPage = new SfglGxyPage(c);
            mPageList.add(mSfglGxyPage);
            mSfglTnbPage = new SfglTnbPage(c);
            mPageList.add(mSfglTnbPage);
            mSfglNzzPage = new SfglNzzPage(c);
            mPageList.add(mSfglNzzPage);
            mSfglJsbPage = new SfglJsbPage(c);
            mPageList.add(mSfglJsbPage);
            mSfglYcfsPage = new SfglYcfsPage(c);
            mPageList.add(mSfglYcfsPage);
            mSfglEtfsPage = new SfglEtfsPage(c);
            mPageList.add(mSfglEtfsPage);
            mSfglLnsfPage = new SfglLnsfPage(c);
            mPageList.add(mSfglLnsfPage);
            mSfglCjsfPage = new SfglCjsfPage(c);
            mPageList.add(mSfglCjsfPage);
        }
        
        public View getItem(int position) {
            return mPageList.get(position);
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
            residentNameTextView.setText(mJmjbxx.getResidentName());
            sfzhTextView.setText(mJmjbxx.getPaperNum());
            khTextView.setText(mJmjbxx.getCardID());
            if (mJmjbxx.getPaperNum() != null && !mJmjbxx.getPaperNum().trim().equals(""))
                profileImageView.setImageURI(Uri.fromFile(new File(Constant.PROFILE_PATH, mJmjbxx
                        .getPaperNum() + ".png")));

            // 性别
            if (mJmjbxx.getSexCD() == 1) {
                sexImageView.setImageResource(R.drawable.male);
                sexTextView.setText("男");
            } else if (mJmjbxx.getSexCD() == 2) {
                sexImageView.setImageResource(R.drawable.female);
                sexTextView.setText("女");
            } else {
                sexImageView.setImageResource(R.drawable.unknown);
                sexTextView.setText("未知");
            }

            // 年龄
            ageTextView.setText(CalendarUtil.getAge(mJmjbxx.getBirthDay()) + "岁");
        }
        mSfglGxyPage.setValue();
        mSfglTnbPage.setValue();
        mSfglNzzPage.setValue();
        mSfglJsbPage.setValue();
        mSfglLnsfPage.setValue();
        mSfglCjsfPage.setValue();

    }

    @Override
    public boolean getValue() {
        return mSfglGxyPage.getValue() && mSfglTnbPage.getValue() && mSfglNzzPage.getValue()
                && mSfglJsbPage.getValue()
                && mSfglLnsfPage.getValue() && mSfglCjsfPage.getValue();
    }

    // 获取高血压随访列表
    public void getSflb(final int pageIndex) {
        Jmjbxx jmjbxx_temp = (Jmjbxx) beanMap.get(Jmjbxx.class.getName());
        if (jmjbxx_temp == null || jmjbxx_temp.getResidentID().equals(""))
            return;
        Ddgywsftxlb81 ddgywsftxlb81 = new Ddgywsftxlb81();
        ddgywsftxlb81.request = new Ddgywsftxlb81.Request();
        ddgywsftxlb81.request.returnRecord = 0;// 返回记录数，假如为0或空则说明返回所有符合条件的记录
        // 设置责任医生
        if (Global.doctorID != null && !Global.doctorID.equals("")
                && Global.doctorName != null) {
            ddgywsftxlb81.request.employeeNo = new BeanID(Global.doctorID, Global.doctorName);
        } else {
            mToast.setText("当前员工号不能为空！");
            mToast.show();
            return;
        }

        // 社区和居委会没有设置
        // 业务模块的设置
        ddgywsftxlb81.request.warnBusi = new BeanID(SFGL_PARAMS[pageIndex].sflbParams, "");

        // 随访提醒条件
        ddgywsftxlb81.request.warnCondi = new BeanID(3, "");

        // 应访日期段的设置
        Date date = new Date();
        GregorianCalendar calendar = new GregorianCalendar();
        int maxDate = calendar.getActualMaximum(Calendar.DATE);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        ddgywsftxlb81.request.warnSD = format.format(date) + "-01";
        ddgywsftxlb81.request.warnED = format.format(date) + "-" + maxDate;

        // 报卡医生没有设置
        // 排序条件
        ddgywsftxlb81.request.sortCondi = new BeanID(1, "");

        List<IDto> beanList = new ArrayList<IDto>();
        beanList.add(ddgywsftxlb81);
        BeanUtil.getInstance().getBeanFromWeb(beanList, new OnResultFromWeb() {
            @Override
            public void onResult(List<IDto> listBean, boolean isSucc) {

                if (isSucc) {
                    if (listBean != null && listBean.size() > 0) {
                        Ddgywsftxlb81 ddgywsftxlb81 = (Ddgywsftxlb81) listBean.get(0);
                        if (ddgywsftxlb81 == null || ddgywsftxlb81.response == null
                                || ddgywsftxlb81.response.returnNum == 0
                                || ddgywsftxlb81.response.warnInfo == null
                                || ddgywsftxlb81.response.warnInfo.size() <= 0) {
                            mToast.setText("随访记录获取失败！");
                            mToast.show();
                            Log.i(TAG, "getSflb - 随访记录获取失败！");
                            return;
                        }
                        /**
                         * 暂时把可不可以随访注释掉随访
                         */
                        if (canDoSf(ddgywsftxlb81.response.warnInfo)) {
                            ((ISfglPage) myPagerAdapter.getItem(pageIndex)).onResultSflb(true);
                        } else {
                            ((ISfglPage) myPagerAdapter.getItem(pageIndex)).onResultSflb(false);
                        }
                    }
                } else {
                    mToast.setText("随访列表获取失败，请重新获取！");
                    mToast.show();
                }

            }
        });
    }

    // 判断该居民是否可以进行随访
    public boolean canDoSf(List<WarnInfo> warnInfos) {
        Jmjbxx jmjbxx_temp = (Jmjbxx) beanMap.get(Jmjbxx.class.getName());
        for (int i = 0; i < warnInfos.size(); i++) {
            WarnInfo warnInfo = warnInfos.get(i);
            if (warnInfo.residentID == jmjbxx_temp.getResidentID())
                return true;
        }
        return false;
    }

    // 获得随访统计的
    public void getSftj(final int pageIndex) {
        MbsftjJ010 mbsftjJ010 = new MbsftjJ010();
        mbsftjJ010.request = new MbsftjJ010.Request();
        if (MyApplication.getInstance().getSession().getLoginResult() == null
                || MyApplication.getInstance().getSession().getLoginResult().response == null) {
            mToast.setText("当前没有医生登录，请先登录！");
            mToast.show();
            return;
        }
        mbsftjJ010.request.userID = MyApplication.getInstance().getSession().getLoginResult().response.userID;
        String sftjParams = SFGL_PARAMS[pageIndex].sftjParams;
        mbsftjJ010.request.disType = sftjParams;
        List<IDto> beanList = new ArrayList<IDto>();
        beanList.add(mbsftjJ010);
        BeanUtil.getInstance().getBeanFromWeb(beanList, new OnResultFromWeb() {
            @Override
            public void onResult(List<IDto> listBean, boolean isSucc) {
                if (isSucc) {
                    MbsftjJ010 responseMbsftjJ010 = (MbsftjJ010) listBean.get(0);
                    if (responseMbsftjJ010 == null || responseMbsftjJ010.response == null
                            || responseMbsftjJ010.response.errMsg.length() > 0) {
                        return;
                    } else {
                        ((ISfglPage) myPagerAdapter.getItem(pageIndex))
                                .onResultSftj(responseMbsftjJ010.response.should,
                                        responseMbsftjJ010.response.done,
                                        responseMbsftjJ010.response.notdo);
                    }
                }
            }
        });
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
        BeanUtil.getInstance().getJbxxFromDb(listBean,
                new BeanUtil.OnResultFromDb() {
                    @Override
                    public void onResult(List<IBean> listBean, boolean isSucc) {
                        if(listBean==null||listBean.size()<0)
                            return;
//                      if (isSucc) {
                            beanMap.put(Jmjbxx.class.getName(), listBean.get(0));
                            setValue();
//                      }
                    }
                });

    }
    
    /**
     * @param jbxxBodyGrxx
     */
    public void showItemByIndex(int index) {
        mGuidePager.showPage(index);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.cking.phss.page.IPage#clear()
     */
    @Override
    public void clear() {
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        // 把按钮加入与网络相关的视图列表
        Global.globalViewList.add(scButton);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();

        // 把按钮移除与网络相关的视图列表
        Global.globalViewList.remove(scButton);
        getValue();
    }
}
