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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.xinhuaxing.eshow.constants.Constants;
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
import com.cking.phss.bean.BeanUtil;
import com.cking.phss.bean.IBean;
import com.cking.phss.bean.Jktj_kstj;
import com.cking.phss.bean.Jmjbxx;
import com.cking.phss.global.Global;
import com.cking.phss.net.ISoapRecv;
import com.cking.phss.net.WebService;
import com.cking.phss.page.IPage;
import com.cking.phss.page.JktjKstjPage;
import com.cking.phss.page.JktjLnpgPage;
import com.cking.phss.page.JktjPttjPage;
import com.cking.phss.page.JktjTzbsPage;
import com.cking.phss.page.JktjXlpgPage;
import com.cking.phss.util.AppConfigFactory.AppConfig;
import com.cking.phss.util.BluetoothUtil;
import com.cking.phss.util.CalendarUtil;
import com.cking.phss.util.Constant;
import com.cking.phss.util.DeviceUseFactory;
import com.cking.phss.util.JgxxConfigFactory;
import com.cking.phss.util.TispToastFactory;
import com.cking.phss.widget.GuidePager;
import com.cking.phss.widget.GuidePager.OnPageChangeListener;
import com.cking.phss.xml4jgxx.tags.constants.TagConstants;

/**
 * 健康体检
 * com.cking.phss.view.JbxxBodyView
 * @author Administrator <br/>
 * create at 2012-9-16 上午11:25:10
 */
/**
 * @author Administrator
 * 
 */
public class JktjBodyView extends LinearLayout implements IPage {
	@SuppressWarnings("unused")
	private static final String TAG = "JktjBodyView";
	private static final boolean D = true;

	private GuidePager mGuidePager = null;
	private Map<String, IBean> beanMap = new HashMap<String, IBean>();
	/**
	 * 全局控件
	 */
    ImageView sexImageView;
    TextView sexTextView;
    TextView ageTextView;
    TextView sfzhTextView;
    TextView tjbhTextView;
    TextView khTextView;
    TextView residentNameTextView;
    ImageView profileImageView;

	private Toast mToast = null;
	private Context mContext;
    private int deviceId = 0;

    public static boolean sHasData = false;
    
	/**
	 * 选择的TAB页编号
	 */
	private static int sTabRadioId = 0;

	/**
	 * 快速体检的2个子页
	 */
	JktjKstjPage mJktjKstjPage = null;

	/**
	 * 普通体检的15个子页
	 */
	JktjPttjPage mJktjPttjPage = null;

    /**
     * 体质辨识
     */
	JktjTzbsPage mJktjTzpsPage = null;
    /**
     * 心理评估
     */
    JktjXlpgPage mJktjXlpgPage = null;
    /**
     * 老年评估
     */
    JktjLnpgPage mJktjLnpgPage = null;

    Button tjdjButton; // 体检登记
    Button scButton; // 上传
    Button bcButton; // 保存
    Button jlButton; // 结论
    Button dyButton; // 打印
    
    private OnPageChangeListener mOnPageChangeListener = null;
    
    public void setOnPageChangeListener(OnPageChangeListener listener) {
        mOnPageChangeListener = listener;
    }
    
	/**
	 * @param context
	 */
	public JktjBodyView(Context context) {
		super(context);
		beanMap.put(Jmjbxx.class.getName(), Global.jmjbxx);
        beanMap.put(Jktj_kstj.class.getName(), Global.jktjKstj);
        beanMap.put(AppConfig.class.getName(), Global.appConfig);
		init(context);
	}

	/**
	 * @param context
	 * @param attrs
	 */
	public JktjBodyView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	/**
	 * @param context
	 */
	private void init(final Context context) {
	    mContext = context;
		mToast = TispToastFactory.getToast(context, "");
        deviceId = JgxxConfigFactory.findIdByName(mContext, TagConstants.XML_VALUE_NAME_RMDYJ);
		LayoutInflater inflater = LayoutInflater.from(context);
		inflater.inflate(R.layout.fragment_health_body_layout, this);

        loadPage(context, this);
    }

    public void loadPage(Context context, ViewGroup viewGroup) {

//		mTabRadios = (RadioGroup) findViewById(R.id.tab_radio);
        profileImageView = (ImageView) findViewById(R.id.profileImageView);
        residentNameTextView = (TextView) findViewById(R.id.residentNameTextView);
        sexImageView = (ImageView) findViewById(R.id.sexImageView);
        sexTextView = (TextView) findViewById(R.id.sexTextView);
        ageTextView = (TextView) findViewById(R.id.ageTextView);
        sfzhTextView = (TextView) findViewById(R.id.sfzhTextView);
        tjbhTextView = (TextView) findViewById(R.id.tjbhTextView);
        khTextView = (TextView) findViewById(R.id.khTextView);
        
//		mSaveButton = (Button) findViewById(R.id.save_button);
//		mUploadButton = (Button) findViewById(R.id.upload_button);
		mGuidePager = (GuidePager) findViewById(R.id.guide_pager);
//		mResultButton = (Button) findViewById(R.id.result_button);
//		mRegisterButton = (Button) findViewById(R.id.register_button);
//        mPrintButton = (Button) findViewById(R.id.print_button);

        tjdjButton = (Button) findViewById(R.id.tjdjButton);
        scButton = (Button) findViewById(R.id.scButton);
        bcButton = (Button) findViewById(R.id.bcButton);
        jlButton = (Button) findViewById(R.id.jlButton);
        dyButton = (Button) findViewById(R.id.dyButton);

        if (deviceId == TagConstants.XML_VALUE_ID_SPRT_SPRMTIIIBTA) {
            if (!BluetoothUtil.isPared(Constants.BT_NAME_SPRT_SPRMTIIIBTA)) {
                dyButton.setEnabled(false);
            } else {
                dyButton.setEnabled(true);
            }
        } else if (deviceId == TagConstants.XML_VALUE_ID_QS_BZ) {
        	if(BluetoothUtil.isPared(Constants.BT_NAME_QS_BZ) || BluetoothUtil.isPared(Constants.BT_NAME_BMV2_BZ)) {
        		dyButton.setEnabled(true);
        	} else {
        		dyButton.setEnabled(false);
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

        dyButton.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View arg0) {
                String strCHECKINID = tjbhTextView.getText().toString().trim();// 获取编号
                if (strCHECKINID == null || strCHECKINID.equals("")) {
                    strCHECKINID = "";
                }
                
                switch (mGuidePager.getSelectIndex()) {
                    case 0:
                        mJktjKstjPage.print(strCHECKINID);
                        break;
                }
            }
        });
        mGuidePager.setAdapter(new MyPagerAdapter(context));
        mGuidePager.setOnPageChangeListener(new OnPageChangeListener() { // 当选中某页的回调
            
            @Override
            public void onPageSelected(int index) {
                if (mOnPageChangeListener != null) {
                    mOnPageChangeListener.onPageSelected(index);
                }
            }
        });

        if (Global.isLocalLogin) {
            scButton.setEnabled(false);
        } else {
            scButton.setEnabled(true);
        }
		scButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (Global.isLocalLogin) {
                    mToast.setText("当前是本地登录，不支持上传操作。");
                    mToast.show();
                    return;
                }
                String strCHECKINID = tjbhTextView.getText().toString().trim();// 获取编号
                switch (mGuidePager.getSelectIndex()) {
                    case 0:
                        mJktjKstjPage.upload(strCHECKINID);
                        break;
                    case 2: // 体质辨识
                        mJktjTzpsPage.upload(strCHECKINID);
                        break;
                    case 3:
                        mJktjXlpgPage.upload(strCHECKINID);
                        break;
                }
            }
        });
        
        if (Global.isLocalLogin) {
            tjdjButton.setEnabled(false);
        } else {
            tjdjButton.setEnabled(true);
        }
		tjdjButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
                if (Global.isLocalLogin) {
                    mToast.setText("当前是本地登录，不支持体检登记操作。");
                    mToast.show();
                    return;
                }
				jktjRegister();
			}
		});

		bcButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
                switch (mGuidePager.getSelectIndex()) {
                    case 0:
                        mJktjKstjPage.save();
                        break;
                }
			}
		});

		jlButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
                switch (mGuidePager.getSelectIndex()) {
                    case 0:
//                      // 对所有快速体检中有数据的项显示结论
                        mJktjKstjPage.updateResult();
                        break;
                }
			}
		});
	}

    /**
     * @return
     */
    protected boolean checkHasValidData() {
        return false;
    }

    // viewpager数据绑定
    class MyPagerAdapter extends PagerAdapter {
        List<View> mPageList = new ArrayList<View>();

        public MyPagerAdapter(Context c) {
            super();
            // 添加页面
            mJktjKstjPage = new JktjKstjPage(c, beanMap);
            mPageList.add(mJktjKstjPage);
            mJktjPttjPage = new JktjPttjPage(c);
            mPageList.add(mJktjPttjPage);
            mJktjTzpsPage = new JktjTzbsPage(c);
            mPageList.add(mJktjTzpsPage);
            mJktjXlpgPage = new JktjXlpgPage(c);
            mPageList.add(mJktjXlpgPage);
            mJktjLnpgPage = new JktjLnpgPage(c);
            mPageList.add(mJktjLnpgPage);
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

    private void jktjRegister() {
        // 体检登记
        String strCheckin = assemblePacket_Checkin();
        if(strCheckin==null)
        	return;
        WebService.getInstance().excute("Checkin", "data", strCheckin, new ISoapRecv() {
            @Override
            public void onRecvData(String xmlStr, boolean success) {
                Log.v(TAG, "Result >>>>>" + xmlStr + "code: " + success);
                if (success) {
                    mToast.setText("体检登记成功！");
                    mToast.show();
                    if(xmlStr.contains("<CHECKINID>")){//包含这个字符传，表示登记成功返回登记体检号
                        int start = xmlStr.indexOf("<CHECKINID>") + "<CHECKINID>".length();
                        int end = xmlStr.indexOf("</CHECKINID>");
                        Global.tjbh = xmlStr.substring(start, end);
                        tjbhTextView.setText(Global.tjbh);
                        return;
                    }else {//没有这个节点，表示返回了错误信息
                        mToast.setText(xmlStr);
                        mToast.show();
                    }
                } else {
                    mToast.setText("体检登记失败！");
                    mToast.show();
                }
            }
            
        });

	}

	/**
     * @return
     */
    private String assemblePacket_Checkin() {
        getValue();
        Jmjbxx mJmjbxx = (Jmjbxx) beanMap.get(Jmjbxx.class.getName());
        if(mJmjbxx==null||mJmjbxx.getResidentName().equals("")||mJmjbxx.getPaperNum().equals("")){
        	mToast.setText("请先登记居民信息，在进行体检登记！");
        	mToast.show();
        	return null;
        }
        Jktj_kstj mJktj_kstj = (Jktj_kstj) beanMap.get(Jktj_kstj.class.getName());
        // 获取身份证号码
        String paperNum = mJmjbxx.getPaperNum();
        String sex = null;
        switch (mJmjbxx.getSexCD()) {
            case 1:
                sex = "M";
                break;
            case 2:
                sex = "F";
                break;
            default:
                sex = "N";
                break;
        }
        String birthDay = mJmjbxx.getBirthDay();
        //10,未婚；20,已婚；22,再婚；23,复婚；30,丧偶；40,离婚；90,未说明的婚姻状况
        String marriage = null;
        switch (mJmjbxx.getMarriageCD()) {
            //(1/2/3/4/5/6 已婚/未婚/离异/再婚/丧偶/不详)
            case 10:
                marriage = "2";
                break;
            case 20:
                marriage = "1";
                break;
            case 22:
                marriage = "4";
                break;
            case 23:
                marriage = "6";
                break;
            case 30:
                marriage = "5";
                break;
            case 40:
                marriage = "3";
                break;
            case 90:
                marriage = "6";
                break;
            default:
                marriage = "6";
                break;
        }
        String docId = null;
        if (mJmjbxx.getDutyDoctor() != null) {
            docId =  mJmjbxx.getDutyDoctor().getiD();
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        int age = 0;
        // 计算年龄
        try {
            age = new Date(System.currentTimeMillis()).getYear() - dateFormat.parse(birthDay).getYear() + 1;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String strRequest = "<Staff>" + "<NAME>" + mJmjbxx.getResidentName() + "</NAME>" + "<SEX>"
                + sex + "</SEX>" + "<BIRTHDAY>" + birthDay + "</BIRTHDAY>" + "<IDCARD>" + paperNum
                + "</IDCARD>" + "<MARRIAGE>" + marriage + "</MARRIAGE>" + "<AGE>" + age + "</AGE>"
                + "<TEL></TEL>" + "<ADDRESS></ADDRESS>" + "<DOC_ID>" + docId + "</DOC_ID>"
                + DeviceUseFactory.getKstjDeviceUses("01") + "</Staff>";
        return strRequest;
    }

	@Override
	public void setValue() {
        Jmjbxx mJmjbxx = (Jmjbxx) beanMap.get(Jmjbxx.class.getName());
        if (mJmjbxx != null) {
            residentNameTextView.setText(mJmjbxx.getResidentName());
            if (Global.tjbh != null) {
                tjbhTextView.setText(Global.tjbh);
            }
//            tjbhTextView.setText(mJmjbxx.getResidentID());
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

        mJktjKstjPage.setValue();
	}

	@Override
	public boolean getValue() {
		// TODO Auto-generated method stub
		if(mJktjKstjPage.getValue()){
			
		}
		return true;
	}

	public void getBeanFromDB() {
		if (beanMap == null)
			return;
        if (Global.updateJmjbxx) {
            setValue();
        } else {
            List<Class<? extends IBean>> listBean = new ArrayList<Class<? extends IBean>>();
            listBean.add(Jmjbxx.class);
            BeanUtil.getInstance().getJbxxFromDb(listBean, new BeanUtil.OnResultFromDb() {
                @Override
                public void onResult(List<IBean> listBean, boolean isSucc) {
                    if (listBean == null || listBean.size() < 0)
                        return;
                    // if (isSucc) {
                    beanMap.put(Jmjbxx.class.getName(), listBean.get(0));
                    setValue();
                    // }
                }
            });
        }

        // // 在取出快速体检的数据放进去
        // List<Class<? extends IBean>> kstjListBean = new ArrayList<Class<?
        // extends IBean>>();
        // kstjListBean.add(Jktj_kstj.class);
        // BeanUtil.getInstance().getLastJktjFromDb(kstjListBean, new
        // BeanUtil.OnResultFromDb() {
        // @Override
        // public void onResult(List<IBean> listBean, boolean isSucc) {
        // if (isSucc) {
        // if (listBean.get(0) != null) {
        // beanMap.put(Jktj_kstj.class.getName(), listBean.get(0));
        // mJktjKstjPage.setValue();
        // return;
        // }
        // }
        // beanMap.put(Jktj_kstj.class.getName(), new Jktj_kstj());
        // }
        // });
    }

	public void clear(){
//	    if(mKstjPage01==null||mKstjPage02==null){
//	        return;
//	    }
//	    mKstjPage01.clear();
//	    mKstjPage02.clear();
	}

    /**
     * @param index
     */
    public void showItemByIndex(int index) {
        mGuidePager.showPage(index);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        // 把按钮加入与网络相关的视图列表
        Global.globalViewList.add(scButton);
        Global.globalViewList.add(tjdjButton);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();

        // 把按钮移除与网络相关的视图列表
        Global.globalViewList.remove(scButton);
        Global.globalViewList.remove(tjdjButton);

        // 由于快速体检数据无需保存即可到处使用，故添加getValue
        getValue();
    }
}
