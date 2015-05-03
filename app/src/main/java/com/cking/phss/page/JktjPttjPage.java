/* Cking Inc. (C) 2012. All rights reserved.
 *
 * JbxxBodyView.java
 * classes : com.cking.phss.view.JbxxBodyView
 * @author Administrator
 * V 1.0.0
 * Create at 2012-9-16 上午11:25:10
 */
package com.cking.phss.page;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.adinnet.xd.medical.widget.MyButton;
import com.cking.phss.R;
import com.cking.phss.bean.BeanUtil;
import com.cking.phss.bean.BeanUtil.SaveToDbResult;
import com.cking.phss.bean.IBean;
import com.cking.phss.bean.Jktj_kstj;
import com.cking.phss.bean.Jmjbxx;
import com.cking.phss.bluetooth.BluetoothClient4Bu34;
import com.cking.phss.bluetooth.BluetoothServer4And;
import com.cking.phss.dto.Login1;
import com.cking.phss.global.Global;
import com.cking.application.MyApplication;
import com.cking.phss.util.TispToastFactory;
import com.cking.phss.widget.GuidePager.OnPageChangeListener;

/**
 * 普通体检
 * com.cking.phss.view.JbxxBodyView
 * @author Administrator <br/>
 * create at 2012-9-16 上午11:25:10
 */
/**
 * @author Administrator
 * 
 */
public class JktjPttjPage extends LinearLayout implements IPage {
	@SuppressWarnings("unused")
	private static final String TAG = "JktjBodyView";
	private static final boolean D = true;

//	private GuidePager mGuidePager = null;
	private Map<String, IBean> beanMap = new HashMap<String, IBean>();
	/**
	 * 全局控件
	 */
//	private RadioGroup mTabRadios = null;
//	private Button mSaveButton = null;
//	private Button mResultButton = null;
//	private Button mUploadButton = null;
//	private Button mRegisterButton = null;
//	public static TextView mRegisterIdText = null;
	LinearLayout layout_content;
	MyButton imageview_left_page;
	MyButton imageview_right_page;
	TextView textview_page;

    private static final int MAX_PAGE_COUNT = 17;
	private Toast mToast = null;

	private static BluetoothServer4And mBluethoothServer = null;
	private static BluetoothClient4Bu34 mBluethoothClient = null;

    public static boolean sHasData = false;
    
	/**
	 * 选择的TAB页编号
	 */
	private static int sTabRadioId = 0;

	/**
	 * 普通体检的17个子页
	 */
	JktjPttjPage01 mPttjPage01 = null;
	JktjPttjPage02 mPttjPage02 = null;
	JktjPttjPage03 mPttjPage03 = null;
	JktjPttjPage04 mPttjPage04 = null;
	JktjPttjPage05 mPttjPage05 = null;
	JktjPttjPage06 mPttjPage06 = null;
	JktjPttjPage07 mPttjPage07 = null;
	JktjPttjPage08 mPttjPage08 = null;
	JktjPttjPage09 mPttjPage09 = null;
	JktjPttjPage10 mPttjPage10 = null;
	JktjPttjPage11 mPttjPage11 = null;
	JktjPttjPage12 mPttjPage12 = null;
	JktjPttjPage13 mPttjPage13 = null;
	JktjPttjPage14 mPttjPage14 = null;
	JktjPttjPage15 mPttjPage15 = null;
    JktjPttjPage16 mPttjPage16 = null;
    JktjPttjPage17 mPttjPage17 = null;

    List<View> mPageList = new ArrayList<View>();
	int pageIndexFrom1 = 1;

    private OnPageChangeListener mOnPageChangeListener = null;
    
    public void setOnPageChangeListener(OnPageChangeListener listener) {
        mOnPageChangeListener = listener;
    }
    
	/**
	 * @param context
	 */
	public JktjPttjPage(Context context) {
		super(context);
		beanMap.put(Jmjbxx.class.getName(), Global.jmjbxx);
		beanMap.put(Jktj_kstj.class.getName(), new Jktj_kstj());
		init(context);
	}

	/**
	 * @param context
	 * @param attrs
	 */
	public JktjPttjPage(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	/**
	 * @param context
	 */
	private void init(final Context context) {
		mToast = TispToastFactory.getToast(context, "");
		LayoutInflater inflater = LayoutInflater.from(context);
		inflater.inflate(R.layout.fragment_health_pttj_layout, this);

        loadPage(context, this);
	}

    /**
     * 设置第一界面的默认显示信息
     * 
     * @param context
     * @param viewGroup
     */
    public void loadPage(Context context, ViewGroup viewGroup) {

        // 添加页面
        mPttjPage01 = new JktjPttjPage01(context);
        mPageList.add(mPttjPage01);
        mPttjPage02 = new JktjPttjPage02(context);
        mPageList.add(mPttjPage02);
        mPttjPage03 = new JktjPttjPage03(context);
        mPageList.add(mPttjPage03);
        mPttjPage04 = new JktjPttjPage04(context);
        mPageList.add(mPttjPage04);
        mPttjPage05 = new JktjPttjPage05(context);
        mPageList.add(mPttjPage05);
        mPttjPage06 = new JktjPttjPage06(context);
        mPageList.add(mPttjPage06);
        mPttjPage07 = new JktjPttjPage07(context);
        mPageList.add(mPttjPage07);
        mPttjPage08 = new JktjPttjPage08(context);
        mPageList.add(mPttjPage08);
        mPttjPage09 = new JktjPttjPage09(context);
        mPageList.add(mPttjPage09);
        mPttjPage10 = new JktjPttjPage10(context);
        mPageList.add(mPttjPage10);
        mPttjPage11 = new JktjPttjPage11(context);
        mPageList.add(mPttjPage11);
        mPttjPage12 = new JktjPttjPage12(context);
        mPageList.add(mPttjPage12);
        mPttjPage13 = new JktjPttjPage13(context);
        mPageList.add(mPttjPage13);
        mPttjPage14 = new JktjPttjPage14(context);
        mPageList.add(mPttjPage14);
        mPttjPage15 = new JktjPttjPage15(context);
        mPageList.add(mPttjPage15);
        mPttjPage16 = new JktjPttjPage16(context);
        mPageList.add(mPttjPage16); // TODO:待添加新页面
        mPttjPage17 = new JktjPttjPage17(context);
        mPageList.add(mPttjPage17);
        layout_content = (LinearLayout) findViewById(R.id.layout_content);
        imageview_left_page = (MyButton) findViewById(R.id.imageview_left_page);
        imageview_right_page = (MyButton) findViewById(R.id.imageview_right_page);
        textview_page = (TextView) findViewById(R.id.textview_page);
        layout_content.removeAllViews();
        pageIndexFrom1 = 1;
        textview_page.setText(pageIndexFrom1 + "");
        layout_content.addView(mPageList.get(0));

        View.OnClickListener vocLeftListener = new View.OnClickListener() {
            
            @Override
            public void onClick(View arg0) {
                if (pageIndexFrom1 <= 1) {
                    pageIndexFrom1 = 1;
                    return;
                }
                pageIndexFrom1--;
                textview_page.setText(pageIndexFrom1 + "");
                layout_content.removeAllViews();
                layout_content.addView(mPageList.get(pageIndexFrom1 - 1));
            }
        };
        View.OnClickListener vocRightListener = new View.OnClickListener() {
            
            @Override
            public void onClick(View arg0) {
                if (pageIndexFrom1 >= MAX_PAGE_COUNT) {
                    pageIndexFrom1 = MAX_PAGE_COUNT;
                    return;
                }
                pageIndexFrom1++;
                textview_page.setText(pageIndexFrom1 + "");
                layout_content.removeAllViews();
                layout_content.addView(mPageList.get(pageIndexFrom1 - 1));
            }
        };
        RelativeLayout leftPageRelativeLayout = (RelativeLayout) findViewById(R.id.leftPageRelativeLayout);
        leftPageRelativeLayout.setOnClickListener(vocLeftListener);
        imageview_left_page.setOnClickListener(vocLeftListener);
        RelativeLayout rightPageRelativeLayout = (RelativeLayout) findViewById(R.id.rightPageRelativeLayout);
        rightPageRelativeLayout.setOnClickListener(vocRightListener);
        imageview_right_page.setOnClickListener(vocRightListener);

//      mTabRadios = (RadioGroup) findViewById(R.id.tab_radio);
//      mSaveButton = (Button) findViewById(R.id.save_button);
//      mUploadButton = (Button) findViewById(R.id.upload_button);
//      mGuidePager = (GuidePager) findViewById(R.id.guide_pager);
//      mResultButton = (Button) findViewById(R.id.result_button);
//      mRegisterButton = (Button) findViewById(R.id.register_button);
//      mRegisterIdText = (TextView) findViewById(R.id.dabh_text);

//        mGuidePager.setAdapter(new MyPttjPagerAdapter(context));
//        mGuidePager.showPage(0);
//        mGuidePager.setOnPageChangeListener(new OnPageChangeListener() { // 当选中某页的回调
//            
//            @Override
//            public void onPageSelected(int index) {
//                if (mOnPageChangeListener != null) {
//                    mOnPageChangeListener.onPageSelected(index);
//                }
//            }
//        });


//      mUploadButton.setOnClickListener(new OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                
//                sHasData = false;
//                // 显示血压结论
//                showResult4BloodPressure();
//                // 显示血糖结论
//                showResult4BloodSugar();
//                // 显示血脂结论
//                showResult4Lipids();
//                // 显示身体成分结论
//                showResult4Bca();
//                // 显示三围结论
//                showResult4Measurements();
//                
//                if (!sHasData) {
//                    mToast.setText("请填写至少一组数据！");
//                    mToast.show();
//                    return;
//                }
//                
//                getValue();
//
//                Jmjbxx mJmjbxx = (Jmjbxx) beanMap.get(Jmjbxx.class.getName());
//                Jktj_kstj mJktj_kstj = (Jktj_kstj) beanMap.get(Jktj_kstj.class.getName());
//                String strCHECKINID = mRegisterIdText.getText().toString().trim();// 获取编号
//                // 保存结果
//                if (strCHECKINID.equals("")) {
//                    mToast.setText("无法获取体检编号！");
//                    mToast.show();
//                    return;
//                } else {
//                    String strSaveResults = assemblePacket_SaveResults(strCHECKINID);
//                    if(strSaveResults==null)
//                        return;
//                    WebService.getInstance().excute("SaveResults", "data", strSaveResults, new ISoapRecv() {
//
//                        @Override
//                        public void onRecvData(String xmlStr, boolean success) {
//                            Log.v(TAG, "Result >>>>>" + xmlStr + "code: " + success);
//                            if (success) {
//                                mToast.setText("上传快速体检数据成功！");
//                                mToast.show();
//                            } else {
//                                mToast.setText("上传快速体检数据失败！");
//                                mToast.show();
//                            }
//                        }
//                        
//                    });
//                }
//            }
//        });
//        
//      mRegisterButton.setOnClickListener(new OnClickListener() {
//
//          @Override
//          public void onClick(View v) {
//              jktjRegister();
//          }
//      });

//      mTabRadios.setOnCheckedChangeListener(new OnCheckedChangeListener() {
//          private MyKstjPagerAdapter kstjPageAdapter = null;
//          private MyPttjPagerAdapter pttjPageAdapter = null;
//
//          @Override
//          public void onCheckedChanged(RadioGroup group, int checkedId) {
//              JktjBodyView.sTabRadioId = checkedId;
//              switch (checkedId) {
//              case R.id.radio_kstj:
//                  if (kstjPageAdapter == null) {
//                      kstjPageAdapter = new MyKstjPagerAdapter(context);
//                  }
//
//                  mGuidePager.setAdapter(kstjPageAdapter);
//                  break;
//
//              case R.id.radio_pttj:
//                  if (pttjPageAdapter == null) {
//                      pttjPageAdapter = new MyPttjPagerAdapter(context);
//                  }
//                  mGuidePager.setAdapter(pttjPageAdapter);
//                  break;
//              }
//          }
//      });
//      /**
//       * 显示默认项，在布局文件中不能设置此项选中，否则这句话不生效
//       */
//      ((RadioButton) findViewById(R.id.radio_kstj)).setChecked(true);

//      /**
//       * 创建一个蓝牙设备
//       */
//      if (mBluethoothServer == null) {
//          mBluethoothServer = new BluetoothServer(context);
//      }
//      mBluethoothServer
//              .setOnReceiveListener(new com.cking.phss.bluetooth.BluetoothServer.OnReceiveListener() {
//
//                  @Override
//                  public void onReceivePbtcDevicesData(BcaData bcaData) {
//                        
//                        // 震动，铃声
//                        MyApplication.getInstance().shock();
//                        MyApplication.getInstance().tone();
//                        
//                      int height = Integer.parseInt(bcaData.height.substring(
//                              0, bcaData.height.length() - 2));
//                      String heightUnit = bcaData.height
//                              .substring(bcaData.height.length() - 2);
//                      if (heightUnit.equals("mm")) {
//                          mKstjPage02.mHeightEdit.setText((height / 10) + "");
//                          mKstjPage02.mHeithUnitText.setText("cm");
//                      } else {
//                          mKstjPage02.mHeightEdit.setText(height + "");
//                          mKstjPage02.mHeithUnitText.setText(heightUnit);
//                      }
//
//                      float weight = Float.parseFloat(bcaData.weight
//                              .substring(0, bcaData.weight.length() - 2));
//                      String weightUnit = bcaData.weight
//                              .substring(bcaData.weight.length() - 2);
//                      DecimalFormat format = new DecimalFormat("0.0");
//                      mKstjPage02.mWeightEdit.setText(format.format(weight));
//                      mKstjPage02.mWeightUnitText.setText(weightUnit);
//
//                      float bmi = height / (weight * weight);
//                      mKstjPage02.mBmiEdit.setText(format.format(bmi));
//
//                      float impedance = Float.parseFloat(bcaData.impedance
//                              .substring(0, bcaData.impedance.length() - 1));
//                      String impedanceUnit = bcaData.impedance
//                              .substring(bcaData.impedance.length() - 1);
//                      mKstjPage02.mImpedanceEdit.setText(format
//                              .format(impedance));
//                      mKstjPage02.mImpedanceUnitText.setText(impedanceUnit);
//
//                      float fatDegree = bcaData.fatDegree;
//                      mKstjPage02.mFatDegreeEdit.setText(format
//                              .format(fatDegree));
//
//                      int basalMetabolism = Integer
//                              .parseInt(bcaData.basalMetabolism.substring(0,
//                                      bcaData.basalMetabolism.length() - 2));
//                      String basalMetabolismUnit = bcaData.basalMetabolism
//                              .substring(bcaData.basalMetabolism.length() - 2);
//                      mKstjPage02.mBmrEdit
//                              .setText(basalMetabolism + "");
//                      mKstjPage02.mBmrUnitText
//                              .setText(basalMetabolismUnit);
//
//                      float muscleMass = Float.parseFloat(bcaData.muscleMass
//                              .substring(0, bcaData.muscleMass.length() - 2));
//                      String muscleMassUnit = bcaData.muscleMass
//                              .substring(bcaData.muscleMass.length() - 2);
//                      mKstjPage02.mMuscleMassEdit.setText(format
//                              .format(muscleMass));
//                      mKstjPage02.mMuscleMassUnitText.setText(muscleMassUnit);
//
//                      float bodyWaterMass = Float
//                              .parseFloat(bcaData.bodyWaterMass.substring(0,
//                                      bcaData.bodyWaterMass.length() - 2));
//                      String bodyWaterMassUnit = bcaData.bodyWaterMass
//                              .substring(bcaData.bodyWaterMass.length() - 2);
//                      mKstjPage02.mBodyWaterMassEdit.setText(format
//                              .format(bodyWaterMass));
//                      mKstjPage02.mBodyWaterMassUnitText
//                              .setText(bodyWaterMassUnit);
//                  }
//
//                  @Override
//                  public void onReceivePbtcDevicesConfig(int id) {
//                      Log.d(TAG, "onReceivePbtcDevicesConfig - id:" + id);
//                      String strHeight = mKstjPage02.mHeightEdit.getText()
//                              .toString();
//                      if (strHeight == null || strHeight.length() <= 0) {
//                          return;
//                      }
//                      int height = Integer.parseInt(mKstjPage02.mHeightEdit
//                              .getText().toString());
//                      String gender = mKstjPage01.mGenderText.getText()
//                              .toString();
//                      gender = gender.substring("性别：".length());
//                      boolean isMale = gender.equals("男") ? true : false;
//                      SimpleDateFormat format = new SimpleDateFormat(
//                              "yyyy-MM-dd");
//                      Date birthday = null;
//                      try {
//                          String strBirthday = mKstjPage01.mBirthdayText
//                                  .getText().toString();
//                          strBirthday = strBirthday.substring("出生年月："
//                                  .length());
//                          birthday = format.parse(strBirthday);
//                      } catch (ParseException e) {
//                          e.printStackTrace();
//                      }
//                      Date now = new Date(System.currentTimeMillis());
//                      int age = now.getYear() - birthday.getYear();
//                      mBluethoothServer.setPbtcConfig(height * 10, isMale,
//                              age);
//                  }
//
//                  @Override
//                  public void onReceivePbtSeriresData(int systolic,
//                          int diastolic, int pulseRate) {
//                        
//                        // 震动，铃声
//                        MyApplication.getInstance().shock();
//                        MyApplication.getInstance().tone();
//                      
//                      Log.d(TAG, "onReceivePbtSeriresData - systolic:"
//                              + systolic + ", diastolic:" + diastolic
//                              + ", pulseRate:" + pulseRate);
//                      mKstjPage01.mSystolicEdit.setText(systolic + "");
//                      Log.i(TAG, "mSystolicEdit:"
//                              + mKstjPage01.mSystolicEdit.getText()
//                                      .toString());
//                      mKstjPage01.mDiastolicEdit.setText(diastolic + "");
//                      Log.i(TAG, "mDiastolicEdit:"
//                              + mKstjPage01.mDiastolicEdit.getText()
//                                      .toString());
//                      mKstjPage01.mPulseRateEdit.setText(pulseRate + "");
//                      Log.i(TAG, "mPulseRateEdit:"
//                              + mKstjPage01.mPulseRateEdit.getText()
//                                      .toString());
//                      // 显示血压结论
//                      showResult4BloodPressure();
//                  }
//              });
//
//      /*if (mBluethoothClient == null) {
//          mBluethoothClient = new BluetoothClient(context);
//      }
//      mBluethoothClient
//              .setOnReceiveListener(new com.cking.phss.bluetooth.BluetoothClient.OnReceiveListener() {
//
//                  @Override
//                  public void onReceiveTgData(float fValue, String unit) {
//                        
//                        // 震动，铃声
//                        MyApplication.getInstance().shock();
//                        MyApplication.getInstance().tone();
//                        
//                      Log.i(TAG, "test Result,TG:" + fValue + unit);
//                        mKstjPage02.mTgEdit.setText(fValue + "");
//                        mKstjPage02.mTgUnitText.setText(unit);
//                        // 显示血脂结论
//                        showResult4Lipids();
//                  }
//
//                  @Override
//                  public void onReceiveCholData(float fValue, String unit) {
//                        
//                        // 震动，铃声
//                        MyApplication.getInstance().shock();
//                        MyApplication.getInstance().tone();
//                        
//                      Log.i(TAG, "test Result,CHOL:" + fValue + unit);
//                        mKstjPage02.mCholEdit.setText(fValue + "");
//                        mKstjPage02.mCholUnitText.setText(unit);
//                        // 显示血脂结论
//                        showResult4Lipids();
//                  }
//              });*/
//      mKstjPage02.mXzReadBtn.setOnClickListener(new OnClickListener() {
//
//          @Override
//          public void onClick(View v) {
//              // 获取血脂数据
//              mBluethoothClient.run();
//          }
//      });
//
//      mSaveButton.setOnClickListener(new View.OnClickListener() {
//          @Override
//          public void onClick(View v) {
//              sHasData = false;
//                // 显示血压结论
//                showResult4BloodPressure();
//                // 显示血糖结论
//                showResult4BloodSugar();
//                // 显示血脂结论
//                showResult4Lipids();
//                // 显示身体成分结论
//                showResult4Bca();
//                // 显示三围结论
//                showResult4Measurements();
//                
//              // 当点击保存数据的时候从两个页面中获取页面上的数据
//              saveJktjToDb();
//          }
//      });
//
//      mResultButton.setOnClickListener(new View.OnClickListener() {
//          @Override
//          public void onClick(View v) {
//              // 对所有快速体检中有数据的项显示结论
//
//              // 显示血压结论
//              showResult4BloodPressure();
//              // 显示血糖结论
//              showResult4BloodSugar();
//              // 显示血脂结论
//              showResult4Lipids();
//              // 显示身体成分结论
//              showResult4Bca();
//              // 显示三围结论
//              showResult4Measurements();
//          }
//      });
    }
	/**
     * @param strCHECKINID 
	 * @return
     */
    protected String assemblePacket_SaveResults(String strCHECKINID) {
        Jktj_kstj mJktj_kstj = (Jktj_kstj) beanMap.get(Jktj_kstj.class.getName());
        String result = "";
        String CHECKINID = strCHECKINID;
        String DRIVERID = "";
        String PROJECTCODE = "";
        String PROJECTNAME = "";
        String RESULT = "";
        String UNIT = "";
        String REFERENCE = "";
        String EXCEPTIONTIPS = "";
        String TRUN = "";
        String CHECKDATE = "";
        String DOC_ID="";
        Login1 login1=MyApplication.getInstance().getSession().getLoginResult();
        if(login1==null||login1.response==null){
            mToast.setText("当前没有医生登录，请先登录！");
            mToast.show();
            return null;
        }
        DOC_ID=login1.response.doctorID;
        
        String head = "<Results>";
        result += head;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        CHECKINID = strCHECKINID;
        // 血压
        {
            DRIVERID = "02";
            // 收缩压
            if (!mJktj_kstj.getsBP().trim().equals("")&&Integer.parseInt(mJktj_kstj.getsBP().trim()) > 0)
            {
                PROJECTCODE = "020001";
                PROJECTNAME = "收缩压";
                RESULT = String.valueOf(mJktj_kstj.getsBP());
                UNIT = "mmHg";
                REFERENCE = "";
                EXCEPTIONTIPS = "";
                TRUN = "";
                CHECKDATE = dateFormat.format(new Date(System.currentTimeMillis()));
                String item = 
                    "<Item>" +
                        "<CHECKINID>" + CHECKINID + "</CHECKINID>" +
                        "<DRIVERID>" + DRIVERID + "</DRIVERID>" +
                        "<PROJECTCODE>" + PROJECTCODE + "</PROJECTCODE>" +
                        "<PROJECTNAME>" + PROJECTNAME + "</PROJECTNAME>" +
                        "<RESULT>" + RESULT + "</RESULT>" +
                        "<UNIT>" + UNIT + "</UNIT>" +
                        "<REFERENCE>" + REFERENCE + "</REFERENCE>" +
                        "<EXCEPTIONTIPS>" + EXCEPTIONTIPS + "</EXCEPTIONTIPS>" +
                        "<TRUN>" + TRUN + "</TRUN>" +
                        "<CHECKDATE>" + CHECKDATE + "</CHECKDATE>" +
                        "<DOC_ID>"+DOC_ID+"</DOC_ID>"+
                      "</Item>";
                result += item;
            }
            // 舒张压
            if (!mJktj_kstj.getdBP().trim().equals("")&&Integer.parseInt(mJktj_kstj.getdBP().trim()) > 0)
            {
                PROJECTCODE = "020002";
                PROJECTNAME = "舒张压";
                RESULT = String.valueOf(mJktj_kstj.getdBP());
                UNIT = "mmHg";
                REFERENCE = "";
                EXCEPTIONTIPS = "";
                TRUN = "";
                CHECKDATE = dateFormat.format(new Date(System.currentTimeMillis()));
                String item = 
                    "<Item>" +
                        "<CHECKINID>" + CHECKINID + "</CHECKINID>" +
                        "<DRIVERID>" + DRIVERID + "</DRIVERID>" +
                        "<PROJECTCODE>" + PROJECTCODE + "</PROJECTCODE>" +
                        "<PROJECTNAME>" + PROJECTNAME + "</PROJECTNAME>" +
                        "<RESULT>" + RESULT + "</RESULT>" +
                        "<UNIT>" + UNIT + "</UNIT>" +
                        "<REFERENCE>" + REFERENCE + "</REFERENCE>" +
                        "<EXCEPTIONTIPS>" + EXCEPTIONTIPS + "</EXCEPTIONTIPS>" +
                        "<TRUN>" + TRUN + "</TRUN>" +
                        "<CHECKDATE>" + CHECKDATE + "</CHECKDATE>" +
                        "<DOC_ID>"+DOC_ID+"</DOC_ID>"+
                      "</Item>";
                result += item;
            }
            // 脉搏
            if (!mJktj_kstj.getxL().trim().equals("")&&Integer.parseInt(mJktj_kstj.getxL().trim()) > 0)
            {
                PROJECTCODE = "020003";
                PROJECTNAME = "脉搏";
                RESULT = String.valueOf(mJktj_kstj.getxL());
                UNIT = "次/min";
                REFERENCE = "";
                EXCEPTIONTIPS = "";
                TRUN = "";
                CHECKDATE = dateFormat.format(new Date(System.currentTimeMillis()));
                String item = 
                    "<Item>" +
                        "<CHECKINID>" + CHECKINID + "</CHECKINID>" +
                        "<DRIVERID>" + DRIVERID + "</DRIVERID>" +
                        "<PROJECTCODE>" + PROJECTCODE + "</PROJECTCODE>" +
                        "<PROJECTNAME>" + PROJECTNAME + "</PROJECTNAME>" +
                        "<RESULT>" + RESULT + "</RESULT>" +
                        "<UNIT>" + UNIT + "</UNIT>" +
                        "<REFERENCE>" + REFERENCE + "</REFERENCE>" +
                        "<EXCEPTIONTIPS>" + EXCEPTIONTIPS + "</EXCEPTIONTIPS>" +
                        "<TRUN>" + TRUN + "</TRUN>" +
                        "<CHECKDATE>" + CHECKDATE + "</CHECKDATE>" +
                        "<DOC_ID>"+DOC_ID+"</DOC_ID>"+
                      "</Item>";
                result += item;
            }
            // 血压结论
            if (mJktj_kstj.getxYJL() != null&&!mJktj_kstj.getxYJL().trim().equals("") )
            {
                PROJECTCODE = "020005";
                PROJECTNAME = "血压结论";
                RESULT = mJktj_kstj.getxYJL();
                UNIT = "";
                REFERENCE = "";
                EXCEPTIONTIPS = "";
                TRUN = "";
                CHECKDATE = dateFormat.format(new Date(System.currentTimeMillis()));
                String item = 
                    "<Item>" +
                        "<CHECKINID>" + CHECKINID + "</CHECKINID>" +
                        "<DRIVERID>" + DRIVERID + "</DRIVERID>" +
                        "<PROJECTCODE>" + PROJECTCODE + "</PROJECTCODE>" +
                        "<PROJECTNAME>" + PROJECTNAME + "</PROJECTNAME>" +
                        "<RESULT>" + RESULT + "</RESULT>" +
                        "<UNIT>" + UNIT + "</UNIT>" +
                        "<REFERENCE>" + REFERENCE + "</REFERENCE>" +
                        "<EXCEPTIONTIPS>" + EXCEPTIONTIPS + "</EXCEPTIONTIPS>" +
                        "<TRUN>" + TRUN + "</TRUN>" +
                        "<CHECKDATE>" + CHECKDATE + "</CHECKDATE>" +
                        "<DOC_ID>"+DOC_ID+"</DOC_ID>"+
                      "</Item>";
                result += item;
            }
        }
        // 胆固醇
        {
            DRIVERID = "17";
            // 胆固醇值
            if (!mJktj_kstj.getdGC().equals("")&&Float.parseFloat(mJktj_kstj.getdGC())>0 )
            {
                PROJECTCODE = "170001";
                PROJECTNAME = "胆固醇值";
                RESULT = String.valueOf(mJktj_kstj.getdGC());
                UNIT = "mmol/L";
                REFERENCE = "";
                EXCEPTIONTIPS = "";
                TRUN = "";
                CHECKDATE = dateFormat.format(new Date(System.currentTimeMillis()));
                String item = 
                    "<Item>" +
                        "<CHECKINID>" + CHECKINID + "</CHECKINID>" +
                        "<DRIVERID>" + DRIVERID + "</DRIVERID>" +
                        "<PROJECTCODE>" + PROJECTCODE + "</PROJECTCODE>" +
                        "<PROJECTNAME>" + PROJECTNAME + "</PROJECTNAME>" +
                        "<RESULT>" + RESULT + "</RESULT>" +
                        "<UNIT>" + UNIT + "</UNIT>" +
                        "<REFERENCE>" + REFERENCE + "</REFERENCE>" +
                        "<EXCEPTIONTIPS>" + EXCEPTIONTIPS + "</EXCEPTIONTIPS>" +
                        "<TRUN>" + TRUN + "</TRUN>" +
                        "<CHECKDATE>" + CHECKDATE + "</CHECKDATE>" +
                        "<DOC_ID>"+DOC_ID+"</DOC_ID>"+
                      "</Item>";
                result += item;
            }
            // 胆固醇结论
            if (mJktj_kstj.getdGCJL() != null&&!mJktj_kstj.getdGCJL().equals(""))
            {
                PROJECTCODE = "170002";
                PROJECTNAME = "胆固醇结论";
                RESULT = mJktj_kstj.getdGCJL();
                UNIT = "";
                REFERENCE = "";
                EXCEPTIONTIPS = "";
                TRUN = "";
                CHECKDATE = dateFormat.format(new Date(System.currentTimeMillis()));
                String item = 
                    "<Item>" +
                        "<CHECKINID>" + CHECKINID + "</CHECKINID>" +
                        "<DRIVERID>" + DRIVERID + "</DRIVERID>" +
                        "<PROJECTCODE>" + PROJECTCODE + "</PROJECTCODE>" +
                        "<PROJECTNAME>" + PROJECTNAME + "</PROJECTNAME>" +
                        "<RESULT>" + RESULT + "</RESULT>" +
                        "<UNIT>" + UNIT + "</UNIT>" +
                        "<REFERENCE>" + REFERENCE + "</REFERENCE>" +
                        "<EXCEPTIONTIPS>" + EXCEPTIONTIPS + "</EXCEPTIONTIPS>" +
                        "<TRUN>" + TRUN + "</TRUN>" +
                        "<CHECKDATE>" + CHECKDATE + "</CHECKDATE>" +
                        "<DOC_ID>"+DOC_ID+"</DOC_ID>"+
                      "</Item>";
                result += item;
            }
        }
        // 甘油三酯
        {
            DRIVERID = "16";
            // 甘油三酯值
            if (!mJktj_kstj.getgYSZ().equals("")&&Float.parseFloat(mJktj_kstj.getgYSZ())>0)
            {
                PROJECTCODE = "160001";
                PROJECTNAME = "甘油三酯值";
                RESULT = String.valueOf(mJktj_kstj.getgYSZ());
                UNIT = "mmol/L";
                REFERENCE = "";
                EXCEPTIONTIPS = "";
                TRUN = "";
                CHECKDATE = dateFormat.format(new Date(System.currentTimeMillis()));
                String item = 
                    "<Item>" +
                        "<CHECKINID>" + CHECKINID + "</CHECKINID>" +
                        "<DRIVERID>" + DRIVERID + "</DRIVERID>" +
                        "<PROJECTCODE>" + PROJECTCODE + "</PROJECTCODE>" +
                        "<PROJECTNAME>" + PROJECTNAME + "</PROJECTNAME>" +
                        "<RESULT>" + RESULT + "</RESULT>" +
                        "<UNIT>" + UNIT + "</UNIT>" +
                        "<REFERENCE>" + REFERENCE + "</REFERENCE>" +
                        "<EXCEPTIONTIPS>" + EXCEPTIONTIPS + "</EXCEPTIONTIPS>" +
                        "<TRUN>" + TRUN + "</TRUN>" +
                        "<CHECKDATE>" + CHECKDATE + "</CHECKDATE>" +
                        "<DOC_ID>"+DOC_ID+"</DOC_ID>"+
                      "</Item>";
                result += item;
            }
            // 甘油三酯结论
            if (mJktj_kstj.getgYSZJL() != null&&!mJktj_kstj.getgYSZJL().equals(""))
            {
                PROJECTCODE = "160002";
                PROJECTNAME = "甘油三酯结论";
                RESULT = mJktj_kstj.getgYSZJL();
                UNIT = "";
                REFERENCE = "";
                EXCEPTIONTIPS = "";
                TRUN = "";
                CHECKDATE = dateFormat.format(new Date(System.currentTimeMillis()));
                String item = 
                    "<Item>" +
                        "<CHECKINID>" + CHECKINID + "</CHECKINID>" +
                        "<DRIVERID>" + DRIVERID + "</DRIVERID>" +
                        "<PROJECTCODE>" + PROJECTCODE + "</PROJECTCODE>" +
                        "<PROJECTNAME>" + PROJECTNAME + "</PROJECTNAME>" +
                        "<RESULT>" + RESULT + "</RESULT>" +
                        "<UNIT>" + UNIT + "</UNIT>" +
                        "<REFERENCE>" + REFERENCE + "</REFERENCE>" +
                        "<EXCEPTIONTIPS>" + EXCEPTIONTIPS + "</EXCEPTIONTIPS>" +
                        "<TRUN>" + TRUN + "</TRUN>" +
                        "<CHECKDATE>" + CHECKDATE + "</CHECKDATE>" +
                        "<DOC_ID>"+DOC_ID+"</DOC_ID>"+
                      "</Item>";
                result += item;
            }
        }
        
        // 高密度脂蛋白
        {
            DRIVERID = "20";
            // 高密度脂蛋白值
            if (!mJktj_kstj.getHDL().equals("")&&Float.parseFloat(mJktj_kstj.getHDL())>0)
            {
                PROJECTCODE = "200001";
                PROJECTNAME = "高密度脂蛋白值";
                RESULT = String.valueOf(mJktj_kstj.getHDL());
                UNIT = "mmol/L";
                REFERENCE = "";
                EXCEPTIONTIPS = "";
                TRUN = "";
                CHECKDATE = dateFormat.format(new Date(System.currentTimeMillis()));
                String item = 
                    "<Item>" +
                        "<CHECKINID>" + CHECKINID + "</CHECKINID>" +
                        "<DRIVERID>" + DRIVERID + "</DRIVERID>" +
                        "<PROJECTCODE>" + PROJECTCODE + "</PROJECTCODE>" +
                        "<PROJECTNAME>" + PROJECTNAME + "</PROJECTNAME>" +
                        "<RESULT>" + RESULT + "</RESULT>" +
                        "<UNIT>" + UNIT + "</UNIT>" +
                        "<REFERENCE>" + REFERENCE + "</REFERENCE>" +
                        "<EXCEPTIONTIPS>" + EXCEPTIONTIPS + "</EXCEPTIONTIPS>" +
                        "<TRUN>" + TRUN + "</TRUN>" +
                        "<CHECKDATE>" + CHECKDATE + "</CHECKDATE>" +
                        "<DOC_ID>"+DOC_ID+"</DOC_ID>"+
                      "</Item>";
                result += item;
            }
            // 高密度脂蛋白结论
            if (mJktj_kstj.getHDLR() != null&&!mJktj_kstj.getHDLR().equals(""))
            {
                PROJECTCODE = "200002";
                PROJECTNAME = "高密度脂蛋白结论";
                RESULT = mJktj_kstj.getHDLR();
                UNIT = "";
                REFERENCE = "";
                EXCEPTIONTIPS = "";
                TRUN = "";
                CHECKDATE = dateFormat.format(new Date(System.currentTimeMillis()));
                String item = 
                    "<Item>" +
                        "<CHECKINID>" + CHECKINID + "</CHECKINID>" +
                        "<DRIVERID>" + DRIVERID + "</DRIVERID>" +
                        "<PROJECTCODE>" + PROJECTCODE + "</PROJECTCODE>" +
                        "<PROJECTNAME>" + PROJECTNAME + "</PROJECTNAME>" +
                        "<RESULT>" + RESULT + "</RESULT>" +
                        "<UNIT>" + UNIT + "</UNIT>" +
                        "<REFERENCE>" + REFERENCE + "</REFERENCE>" +
                        "<EXCEPTIONTIPS>" + EXCEPTIONTIPS + "</EXCEPTIONTIPS>" +
                        "<TRUN>" + TRUN + "</TRUN>" +
                        "<CHECKDATE>" + CHECKDATE + "</CHECKDATE>" +
                        "<DOC_ID>"+DOC_ID+"</DOC_ID>"+
                      "</Item>";
                result += item;
            }
        }
        
        
     // 低密度脂蛋白
        {
            DRIVERID = "21";
            // 低密度脂蛋白值
            if (!mJktj_kstj.getLDL().equals("")&&Float.parseFloat(mJktj_kstj.getLDL())>0)
            {
                PROJECTCODE = "210001";
                PROJECTNAME = "低密度脂蛋白值";
                RESULT = String.valueOf(mJktj_kstj.getLDL());
                UNIT = "mmol/L";
                REFERENCE = "";
                EXCEPTIONTIPS = "";
                TRUN = "";
                CHECKDATE = dateFormat.format(new Date(System.currentTimeMillis()));
                String item = 
                    "<Item>" +
                        "<CHECKINID>" + CHECKINID + "</CHECKINID>" +
                        "<DRIVERID>" + DRIVERID + "</DRIVERID>" +
                        "<PROJECTCODE>" + PROJECTCODE + "</PROJECTCODE>" +
                        "<PROJECTNAME>" + PROJECTNAME + "</PROJECTNAME>" +
                        "<RESULT>" + RESULT + "</RESULT>" +
                        "<UNIT>" + UNIT + "</UNIT>" +
                        "<REFERENCE>" + REFERENCE + "</REFERENCE>" +
                        "<EXCEPTIONTIPS>" + EXCEPTIONTIPS + "</EXCEPTIONTIPS>" +
                        "<TRUN>" + TRUN + "</TRUN>" +
                        "<CHECKDATE>" + CHECKDATE + "</CHECKDATE>" +
                        "<DOC_ID>"+DOC_ID+"</DOC_ID>"+
                      "</Item>";
                result += item;
            }
            // 低密度脂蛋白结论
            if (mJktj_kstj.getLDLR() != null&&!mJktj_kstj.getLDLR().equals(""))
            {
                PROJECTCODE = "210002";
                PROJECTNAME = "低密度脂蛋白结论";
                RESULT = mJktj_kstj.getLDLR();
                UNIT = "";
                REFERENCE = "";
                EXCEPTIONTIPS = "";
                TRUN = "";
                CHECKDATE = dateFormat.format(new Date(System.currentTimeMillis()));
                String item = 
                    "<Item>" +
                        "<CHECKINID>" + CHECKINID + "</CHECKINID>" +
                        "<DRIVERID>" + DRIVERID + "</DRIVERID>" +
                        "<PROJECTCODE>" + PROJECTCODE + "</PROJECTCODE>" +
                        "<PROJECTNAME>" + PROJECTNAME + "</PROJECTNAME>" +
                        "<RESULT>" + RESULT + "</RESULT>" +
                        "<UNIT>" + UNIT + "</UNIT>" +
                        "<REFERENCE>" + REFERENCE + "</REFERENCE>" +
                        "<EXCEPTIONTIPS>" + EXCEPTIONTIPS + "</EXCEPTIONTIPS>" +
                        "<TRUN>" + TRUN + "</TRUN>" +
                        "<CHECKDATE>" + CHECKDATE + "</CHECKDATE>" +
                        "<DOC_ID>"+DOC_ID+"</DOC_ID>"+
                      "</Item>";
                result += item;
            }
        }
        
        // 血糖
        {
            DRIVERID = "03";
            // 血糖值
            if (!mJktj_kstj.getxTValue().equals("")&&Float.parseFloat(mJktj_kstj.getxTValue())>0 )
            {
                PROJECTCODE = "030001";
                PROJECTNAME = "血糖值";
                RESULT = String.valueOf(mJktj_kstj.getxTValue());
                UNIT = "mmol/L";
                REFERENCE = "";
                EXCEPTIONTIPS = "";
                TRUN = "";
                CHECKDATE = dateFormat.format(new Date(System.currentTimeMillis()));
                String item = 
                    "<Item>" +
                        "<CHECKINID>" + CHECKINID + "</CHECKINID>" +
                        "<DRIVERID>" + DRIVERID + "</DRIVERID>" +
                        "<PROJECTCODE>" + PROJECTCODE + "</PROJECTCODE>" +
                        "<PROJECTNAME>" + PROJECTNAME + "</PROJECTNAME>" +
                        "<RESULT>" + RESULT + "</RESULT>" +
                        "<UNIT>" + UNIT + "</UNIT>" +
                        "<REFERENCE>" + REFERENCE + "</REFERENCE>" +
                        "<EXCEPTIONTIPS>" + EXCEPTIONTIPS + "</EXCEPTIONTIPS>" +
                        "<TRUN>" + TRUN + "</TRUN>" +
                        "<CHECKDATE>" + CHECKDATE + "</CHECKDATE>" +
                        "<DOC_ID>"+DOC_ID+"</DOC_ID>"+
                      "</Item>";
                result += item;
            }
            // 血糖结论
            if (mJktj_kstj.getxTJL() != null&&!mJktj_kstj.getxTJL().equals(""))
            {
                PROJECTCODE = "030002";
                PROJECTNAME = "血糖结论";
                RESULT = mJktj_kstj.getxTJL();
                UNIT = "";
                REFERENCE = "";
                EXCEPTIONTIPS = "";
                TRUN = "";
                CHECKDATE = dateFormat.format(new Date(System.currentTimeMillis()));
                String item = 
                    "<Item>" +
                        "<CHECKINID>" + CHECKINID + "</CHECKINID>" +
                        "<DRIVERID>" + DRIVERID + "</DRIVERID>" +
                        "<PROJECTCODE>" + PROJECTCODE + "</PROJECTCODE>" +
                        "<PROJECTNAME>" + PROJECTNAME + "</PROJECTNAME>" +
                        "<RESULT>" + RESULT + "</RESULT>" +
                        "<UNIT>" + UNIT + "</UNIT>" +
                        "<REFERENCE>" + REFERENCE + "</REFERENCE>" +
                        "<EXCEPTIONTIPS>" + EXCEPTIONTIPS + "</EXCEPTIONTIPS>" +
                        "<TRUN>" + TRUN + "</TRUN>" +
                        "<CHECKDATE>" + CHECKDATE + "</CHECKDATE>" +
                        "<DOC_ID>"+DOC_ID+"</DOC_ID>"+
                      "</Item>";
                result += item;
            }
        }
        // 腰围
        {
            DRIVERID = "18";
            // 胸围
            if (!mJktj_kstj.getBust().trim().equals("")&&Integer.parseInt(mJktj_kstj.getBust().trim()) > 0)
            {
                PROJECTCODE = "180001";
                PROJECTNAME = "胸围";
                RESULT = String.valueOf(mJktj_kstj.getBust());
                UNIT = "cm";
                REFERENCE = "";
                EXCEPTIONTIPS = "";
                TRUN = "";
                CHECKDATE = dateFormat.format(new Date(System.currentTimeMillis()));
                String item = 
                    "<Item>" +
                        "<CHECKINID>" + CHECKINID + "</CHECKINID>" +
                        "<DRIVERID>" + DRIVERID + "</DRIVERID>" +
                        "<PROJECTCODE>" + PROJECTCODE + "</PROJECTCODE>" +
                        "<PROJECTNAME>" + PROJECTNAME + "</PROJECTNAME>" +
                        "<RESULT>" + RESULT + "</RESULT>" +
                        "<UNIT>" + UNIT + "</UNIT>" +
                        "<REFERENCE>" + REFERENCE + "</REFERENCE>" +
                        "<EXCEPTIONTIPS>" + EXCEPTIONTIPS + "</EXCEPTIONTIPS>" +
                        "<TRUN>" + TRUN + "</TRUN>" +
                        "<CHECKDATE>" + CHECKDATE + "</CHECKDATE>" +
                        "<DOC_ID>"+DOC_ID+"</DOC_ID>"+
                      "</Item>";
                result += item;
            }
            // 腰围
            if (!mJktj_kstj.getWaist().trim().equals("")&&Integer.parseInt(mJktj_kstj.getWaist().trim()) > 0)
            {
                PROJECTCODE = "180002";
                PROJECTNAME = "腰围";
                RESULT = String.valueOf(mJktj_kstj.getWaist());
                UNIT = "cm";
                REFERENCE = "";
                EXCEPTIONTIPS = "";
                TRUN = "";
                CHECKDATE = dateFormat.format(new Date(System.currentTimeMillis()));
                String item = 
                    "<Item>" +
                        "<CHECKINID>" + CHECKINID + "</CHECKINID>" +
                        "<DRIVERID>" + DRIVERID + "</DRIVERID>" +
                        "<PROJECTCODE>" + PROJECTCODE + "</PROJECTCODE>" +
                        "<PROJECTNAME>" + PROJECTNAME + "</PROJECTNAME>" +
                        "<RESULT>" + RESULT + "</RESULT>" +
                        "<UNIT>" + UNIT + "</UNIT>" +
                        "<REFERENCE>" + REFERENCE + "</REFERENCE>" +
                        "<EXCEPTIONTIPS>" + EXCEPTIONTIPS + "</EXCEPTIONTIPS>" +
                        "<TRUN>" + TRUN + "</TRUN>" +
                        "<CHECKDATE>" + CHECKDATE + "</CHECKDATE>" +
                        "<DOC_ID>"+DOC_ID+"</DOC_ID>"+
                      "</Item>";
                result += item;
            }
            // 臀围
            if (!mJktj_kstj.gethIP().trim().equals("")&&Integer.parseInt(mJktj_kstj.gethIP().trim()) > 0)
            {
                PROJECTCODE = "180003";
                PROJECTNAME = "臀围";
                RESULT = String.valueOf(mJktj_kstj.gethIP());
                UNIT = "cm";
                REFERENCE = "";
                EXCEPTIONTIPS = "";
                TRUN = "";
                CHECKDATE = dateFormat.format(new Date(System.currentTimeMillis()));
                String item = 
                    "<Item>" +
                        "<CHECKINID>" + CHECKINID + "</CHECKINID>" +
                        "<DRIVERID>" + DRIVERID + "</DRIVERID>" +
                        "<PROJECTCODE>" + PROJECTCODE + "</PROJECTCODE>" +
                        "<PROJECTNAME>" + PROJECTNAME + "</PROJECTNAME>" +
                        "<RESULT>" + RESULT + "</RESULT>" +
                        "<UNIT>" + UNIT + "</UNIT>" +
                        "<REFERENCE>" + REFERENCE + "</REFERENCE>" +
                        "<EXCEPTIONTIPS>" + EXCEPTIONTIPS + "</EXCEPTIONTIPS>" +
                        "<TRUN>" + TRUN + "</TRUN>" +
                        "<CHECKDATE>" + CHECKDATE + "</CHECKDATE>" +
                        "<DOC_ID>"+DOC_ID+"</DOC_ID>"+
                      "</Item>";
                result += item;
            }
            // 腰臀比结论
            if (mJktj_kstj.getYtbjl() != null&&!mJktj_kstj.getYtbjl().trim().equals(""))
            {
                PROJECTCODE = "180004";
                PROJECTNAME = "结论";
                RESULT = mJktj_kstj.getYtbjl();
                UNIT = "";
                REFERENCE = "";
                EXCEPTIONTIPS = "";
                TRUN = "";
                CHECKDATE = dateFormat.format(new Date(System.currentTimeMillis()));
                String item = 
                    "<Item>" +
                        "<CHECKINID>" + CHECKINID + "</CHECKINID>" +
                        "<DRIVERID>" + DRIVERID + "</DRIVERID>" +
                        "<PROJECTCODE>" + PROJECTCODE + "</PROJECTCODE>" +
                        "<PROJECTNAME>" + PROJECTNAME + "</PROJECTNAME>" +
                        "<RESULT>" + RESULT + "</RESULT>" +
                        "<UNIT>" + UNIT + "</UNIT>" +
                        "<REFERENCE>" + REFERENCE + "</REFERENCE>" +
                        "<EXCEPTIONTIPS>" + EXCEPTIONTIPS + "</EXCEPTIONTIPS>" +
                        "<TRUN>" + TRUN + "</TRUN>" +
                        "<CHECKDATE>" + CHECKDATE + "</CHECKDATE>" +
                        "<DOC_ID>"+DOC_ID+"</DOC_ID>"+
                      "</Item>";
                result += item;
            }
        }
        //人体成分
        {
            DRIVERID = "08";
            //体脂肪率
            if (!mJktj_kstj.getFat().trim().equals("")&&Float.parseFloat(mJktj_kstj.getFat().trim())> 0)
            {
                PROJECTCODE = "080004";
                PROJECTNAME = "体脂肪率";
                RESULT = String.valueOf(mJktj_kstj.getFat());
                UNIT = "";
                REFERENCE = "";
                EXCEPTIONTIPS = "";
                TRUN = "";
                CHECKDATE = dateFormat.format(new Date(System.currentTimeMillis()));
                String item = 
                    "<Item>" +
                        "<CHECKINID>" + CHECKINID + "</CHECKINID>" +
                        "<DRIVERID>" + DRIVERID + "</DRIVERID>" +
                        "<PROJECTCODE>" + PROJECTCODE + "</PROJECTCODE>" +
                        "<PROJECTNAME>" + PROJECTNAME + "</PROJECTNAME>" +
                        "<RESULT>" + RESULT + "</RESULT>" +
                        "<UNIT>" + UNIT + "</UNIT>" +
                        "<REFERENCE>" + REFERENCE + "</REFERENCE>" +
                        "<EXCEPTIONTIPS>" + EXCEPTIONTIPS + "</EXCEPTIONTIPS>" +
                        "<TRUN>" + TRUN + "</TRUN>" +
                        "<CHECKDATE>" + CHECKDATE + "</CHECKDATE>" +
                        "<DOC_ID>"+DOC_ID+"</DOC_ID>"+
                      "</Item>";
                result += item;
            }
            
            
            //基础代谢
            if (!mJktj_kstj.getbMR().trim().equals("")&&Float.parseFloat(mJktj_kstj.getbMR().trim())> 0)
            {
                PROJECTCODE = "080006";
                PROJECTNAME = "基础代谢";
                RESULT = String.valueOf(mJktj_kstj.getbMR());
                UNIT = "Kcal";
                REFERENCE = "";
                EXCEPTIONTIPS = "";
                TRUN = "";
                CHECKDATE = dateFormat.format(new Date(System.currentTimeMillis()));
                String item = 
                    "<Item>" +
                        "<CHECKINID>" + CHECKINID + "</CHECKINID>" +
                        "<DRIVERID>" + DRIVERID + "</DRIVERID>" +
                        "<PROJECTCODE>" + PROJECTCODE + "</PROJECTCODE>" +
                        "<PROJECTNAME>" + PROJECTNAME + "</PROJECTNAME>" +
                        "<RESULT>" + RESULT + "</RESULT>" +
                        "<UNIT>" + UNIT + "</UNIT>" +
                        "<REFERENCE>" + REFERENCE + "</REFERENCE>" +
                        "<EXCEPTIONTIPS>" + EXCEPTIONTIPS + "</EXCEPTIONTIPS>" +
                        "<TRUN>" + TRUN + "</TRUN>" +
                        "<CHECKDATE>" + CHECKDATE + "</CHECKDATE>" +
                        "<DOC_ID>"+DOC_ID+"</DOC_ID>"+
                      "</Item>";
                result += item;
            }
            
            
          //相对基础代谢
            if (!mJktj_kstj.getrBMR().trim().equals("")&&Float.parseFloat(mJktj_kstj.getrBMR().trim())> 0)
            {
                PROJECTCODE = "080007";
                PROJECTNAME = "相对基础代谢";
                RESULT = String.valueOf(mJktj_kstj.getrBMR());
                UNIT = "Kcal";
                REFERENCE = "";
                EXCEPTIONTIPS = "";
                TRUN = "";
                CHECKDATE = dateFormat.format(new Date(System.currentTimeMillis()));
                String item = 
                    "<Item>" +
                        "<CHECKINID>" + CHECKINID + "</CHECKINID>" +
                        "<DRIVERID>" + DRIVERID + "</DRIVERID>" +
                        "<PROJECTCODE>" + PROJECTCODE + "</PROJECTCODE>" +
                        "<PROJECTNAME>" + PROJECTNAME + "</PROJECTNAME>" +
                        "<RESULT>" + RESULT + "</RESULT>" +
                        "<UNIT>" + UNIT + "</UNIT>" +
                        "<REFERENCE>" + REFERENCE + "</REFERENCE>" +
                        "<EXCEPTIONTIPS>" + EXCEPTIONTIPS + "</EXCEPTIONTIPS>" +
                        "<TRUN>" + TRUN + "</TRUN>" +
                        "<CHECKDATE>" + CHECKDATE + "</CHECKDATE>" +
                        "<DOC_ID>"+DOC_ID+"</DOC_ID>"+
                      "</Item>";
                result += item;
            }
            
            
          //肌肉含量
            if (!mJktj_kstj.getMus().trim().equals("")&&Float.parseFloat(mJktj_kstj.getMus().trim())> 0)
            {
                PROJECTCODE = "080011";
                PROJECTNAME = "肌肉含量";
                RESULT = String.valueOf(mJktj_kstj.getMus());
                UNIT = "Kg";
                REFERENCE = "";
                EXCEPTIONTIPS = "";
                TRUN = "";
                CHECKDATE = dateFormat.format(new Date(System.currentTimeMillis()));
                String item = 
                    "<Item>" +
                        "<CHECKINID>" + CHECKINID + "</CHECKINID>" +
                        "<DRIVERID>" + DRIVERID + "</DRIVERID>" +
                        "<PROJECTCODE>" + PROJECTCODE + "</PROJECTCODE>" +
                        "<PROJECTNAME>" + PROJECTNAME + "</PROJECTNAME>" +
                        "<RESULT>" + RESULT + "</RESULT>" +
                        "<UNIT>" + UNIT + "</UNIT>" +
                        "<REFERENCE>" + REFERENCE + "</REFERENCE>" +
                        "<EXCEPTIONTIPS>" + EXCEPTIONTIPS + "</EXCEPTIONTIPS>" +
                        "<TRUN>" + TRUN + "</TRUN>" +
                        "<CHECKDATE>" + CHECKDATE + "</CHECKDATE>" +
                        "<DOC_ID>"+DOC_ID+"</DOC_ID>"+
                      "</Item>";
                result += item;
            }
            
          //水分含量
            if (!mJktj_kstj.gettBW().trim().equals("")&&Float.parseFloat(mJktj_kstj.gettBW().trim())> 0)
            {
                PROJECTCODE = "080012";
                PROJECTNAME = "水分含量";
                RESULT = String.valueOf(mJktj_kstj.gettBW());
                UNIT = "Kg";
                REFERENCE = "";
                EXCEPTIONTIPS = "";
                TRUN = "";
                CHECKDATE = dateFormat.format(new Date(System.currentTimeMillis()));
                String item = 
                    "<Item>" +
                        "<CHECKINID>" + CHECKINID + "</CHECKINID>" +
                        "<DRIVERID>" + DRIVERID + "</DRIVERID>" +
                        "<PROJECTCODE>" + PROJECTCODE + "</PROJECTCODE>" +
                        "<PROJECTNAME>" + PROJECTNAME + "</PROJECTNAME>" +
                        "<RESULT>" + RESULT + "</RESULT>" +
                        "<UNIT>" + UNIT + "</UNIT>" +
                        "<REFERENCE>" + REFERENCE + "</REFERENCE>" +
                        "<EXCEPTIONTIPS>" + EXCEPTIONTIPS + "</EXCEPTIONTIPS>" +
                        "<TRUN>" + TRUN + "</TRUN>" +
                        "<CHECKDATE>" + CHECKDATE + "</CHECKDATE>" +
                        "<DOC_ID>"+DOC_ID+"</DOC_ID>"+
                      "</Item>";
                result += item;
            }
            
          //阻抗
            if (!mJktj_kstj.getiMP().trim().equals("")&&Float.parseFloat(mJktj_kstj.getiMP().trim())> 0)
            {
                PROJECTCODE = "080013";
                PROJECTNAME = "阻抗";
                RESULT = String.valueOf(mJktj_kstj.getiMP());
                UNIT = "";
                REFERENCE = "";
                EXCEPTIONTIPS = "";
                TRUN = "";
                CHECKDATE = dateFormat.format(new Date(System.currentTimeMillis()));
                String item = 
                    "<Item>" +
                        "<CHECKINID>" + CHECKINID + "</CHECKINID>" +
                        "<DRIVERID>" + DRIVERID + "</DRIVERID>" +
                        "<PROJECTCODE>" + PROJECTCODE + "</PROJECTCODE>" +
                        "<PROJECTNAME>" + PROJECTNAME + "</PROJECTNAME>" +
                        "<RESULT>" + RESULT + "</RESULT>" +
                        "<UNIT>" + UNIT + "</UNIT>" +
                        "<REFERENCE>" + REFERENCE + "</REFERENCE>" +
                        "<EXCEPTIONTIPS>" + EXCEPTIONTIPS + "</EXCEPTIONTIPS>" +
                        "<TRUN>" + TRUN + "</TRUN>" +
                        "<CHECKDATE>" + CHECKDATE + "</CHECKDATE>" +
                        "<DOC_ID>"+DOC_ID+"</DOC_ID>"+
                      "</Item>";
                result += item;
            }
            
          //bmi
            if (!mJktj_kstj.getbMI().trim().equals("")&&Float.parseFloat(mJktj_kstj.getbMI().trim())> 0)
            {
                PROJECTCODE = "080016";
                PROJECTNAME = "BMI";
                RESULT = String.valueOf(mJktj_kstj.getbMI());
                UNIT = "";
                REFERENCE = "";
                EXCEPTIONTIPS = "";
                TRUN = "";
                CHECKDATE = dateFormat.format(new Date(System.currentTimeMillis()));
                String item = 
                    "<Item>" +
                        "<CHECKINID>" + CHECKINID + "</CHECKINID>" +
                        "<DRIVERID>" + DRIVERID + "</DRIVERID>" +
                        "<PROJECTCODE>" + PROJECTCODE + "</PROJECTCODE>" +
                        "<PROJECTNAME>" + PROJECTNAME + "</PROJECTNAME>" +
                        "<RESULT>" + RESULT + "</RESULT>" +
                        "<UNIT>" + UNIT + "</UNIT>" +
                        "<REFERENCE>" + REFERENCE + "</REFERENCE>" +
                        "<EXCEPTIONTIPS>" + EXCEPTIONTIPS + "</EXCEPTIONTIPS>" +
                        "<TRUN>" + TRUN + "</TRUN>" +
                        "<CHECKDATE>" + CHECKDATE + "</CHECKDATE>" +
                        "<DOC_ID>"+DOC_ID+"</DOC_ID>"+
                      "</Item>";
                result += item;
            }
            
           //结论
            if (!mJktj_kstj.getCname().trim().equals(""))
            {
                PROJECTCODE = "080017";
                PROJECTNAME = "结论建议";
                RESULT = String.valueOf(mJktj_kstj.getCname());
                UNIT = "";
                REFERENCE = "";
                EXCEPTIONTIPS = "";
                TRUN = "";
                CHECKDATE = dateFormat.format(new Date(System.currentTimeMillis()));
                String item = 
                    "<Item>" +
                        "<CHECKINID>" + CHECKINID + "</CHECKINID>" +
                        "<DRIVERID>" + DRIVERID + "</DRIVERID>" +
                        "<PROJECTCODE>" + PROJECTCODE + "</PROJECTCODE>" +
                        "<PROJECTNAME>" + PROJECTNAME + "</PROJECTNAME>" +
                        "<RESULT>" + RESULT + "</RESULT>" +
                        "<UNIT>" + UNIT + "</UNIT>" +
                        "<REFERENCE>" + REFERENCE + "</REFERENCE>" +
                        "<EXCEPTIONTIPS>" + EXCEPTIONTIPS + "</EXCEPTIONTIPS>" +
                        "<TRUN>" + TRUN + "</TRUN>" +
                        "<CHECKDATE>" + CHECKDATE + "</CHECKDATE>" +
                        "<DOC_ID>"+DOC_ID+"</DOC_ID>"+
                      "</Item>";
                result += item;
            }
            
          //内脏脂肪
            if (!mJktj_kstj.getVisceralFat().trim().equals("")&&Float.parseFloat(mJktj_kstj.getVisceralFat().trim())> 0)
            {
                PROJECTCODE = "080018";
                PROJECTNAME = "BMI";
                RESULT = String.valueOf(mJktj_kstj.getVisceralFat());
                UNIT = "";
                REFERENCE = "";
                EXCEPTIONTIPS = "";
                TRUN = "";
                CHECKDATE = dateFormat.format(new Date(System.currentTimeMillis()));
                String item = 
                    "<Item>" +
                        "<CHECKINID>" + CHECKINID + "</CHECKINID>" +
                        "<DRIVERID>" + DRIVERID + "</DRIVERID>" +
                        "<PROJECTCODE>" + PROJECTCODE + "</PROJECTCODE>" +
                        "<PROJECTNAME>" + PROJECTNAME + "</PROJECTNAME>" +
                        "<RESULT>" + RESULT + "</RESULT>" +
                        "<UNIT>" + UNIT + "</UNIT>" +
                        "<REFERENCE>" + REFERENCE + "</REFERENCE>" +
                        "<EXCEPTIONTIPS>" + EXCEPTIONTIPS + "</EXCEPTIONTIPS>" +
                        "<TRUN>" + TRUN + "</TRUN>" +
                        "<CHECKDATE>" + CHECKDATE + "</CHECKDATE>" +
                        "<DOC_ID>"+DOC_ID+"</DOC_ID>"+
                      "</Item>";
                result += item;
            }
            
            //骨含量
            if (!mJktj_kstj.getBone().trim().equals("")&&Float.parseFloat(mJktj_kstj.getBone().trim())> 0)
            {
                PROJECTCODE = "080019";
                PROJECTNAME = "Kg";
                RESULT = String.valueOf(mJktj_kstj.getBone());
                UNIT = "";
                REFERENCE = "";
                EXCEPTIONTIPS = "";
                TRUN = "";
                CHECKDATE = dateFormat.format(new Date(System.currentTimeMillis()));
                String item = 
                    "<Item>" +
                        "<CHECKINID>" + CHECKINID + "</CHECKINID>" +
                        "<DRIVERID>" + DRIVERID + "</DRIVERID>" +
                        "<PROJECTCODE>" + PROJECTCODE + "</PROJECTCODE>" +
                        "<PROJECTNAME>" + PROJECTNAME + "</PROJECTNAME>" +
                        "<RESULT>" + RESULT + "</RESULT>" +
                        "<UNIT>" + UNIT + "</UNIT>" +
                        "<REFERENCE>" + REFERENCE + "</REFERENCE>" +
                        "<EXCEPTIONTIPS>" + EXCEPTIONTIPS + "</EXCEPTIONTIPS>" +
                        "<TRUN>" + TRUN + "</TRUN>" +
                        "<CHECKDATE>" + CHECKDATE + "</CHECKDATE>" +
                        "<DOC_ID>"+DOC_ID+"</DOC_ID>"+
                      "</Item>";
                result += item;
            }
            
          //身体类型
            if (!mJktj_kstj.getCtype().trim().equals(""))
            {
                PROJECTCODE = "080020";
                PROJECTNAME = "";
                RESULT = String.valueOf(mJktj_kstj.getCtype());
                UNIT = "";
                REFERENCE = "";
                EXCEPTIONTIPS = "";
                TRUN = "";
                CHECKDATE = dateFormat.format(new Date(System.currentTimeMillis()));
                String item = 
                    "<Item>" +
                        "<CHECKINID>" + CHECKINID + "</CHECKINID>" +
                        "<DRIVERID>" + DRIVERID + "</DRIVERID>" +
                        "<PROJECTCODE>" + PROJECTCODE + "</PROJECTCODE>" +
                        "<PROJECTNAME>" + PROJECTNAME + "</PROJECTNAME>" +
                        "<RESULT>" + RESULT + "</RESULT>" +
                        "<UNIT>" + UNIT + "</UNIT>" +
                        "<REFERENCE>" + REFERENCE + "</REFERENCE>" +
                        "<EXCEPTIONTIPS>" + EXCEPTIONTIPS + "</EXCEPTIONTIPS>" +
                        "<TRUN>" + TRUN + "</TRUN>" +
                        "<CHECKDATE>" + CHECKDATE + "</CHECKDATE>" +
                        "<DOC_ID>"+DOC_ID+"</DOC_ID>"+
                      "</Item>";
                result += item;
            }
        }
        
        String tail = "</Results>";
        result += tail;
        Log.i(TAG, result);
        return result;
    }

    

//    private void jktjRegister() {
//        // 体检登记
//        String strCheckin = assemblePacket_Checkin();
//        if(strCheckin==null)
//        	return;
//        WebService.getInstance().excute("Checkin", "data", strCheckin, new ISoapRecv() {
//            @Override
//            public void onRecvData(String xmlStr, boolean success) {
//                Log.v(TAG, "Result >>>>>" + xmlStr + "code: " + success);
//                if (success) {
//                    mToast.setText("体检登记成功！");
//                    mToast.show();
//                    if(xmlStr.contains("<CHECKINID>")){//包含这个字符传，表示登记成功返回登记体检号
//                        int start = xmlStr.indexOf("<CHECKINID>") + "<CHECKINID>".length();
//                        int end = xmlStr.indexOf("</CHECKINID>");
//                        mRegisterIdText.setText(xmlStr.substring(start, end));
//                        return;
//                    }else {//没有这个节点，表示返回了错误信息
//                        mToast.setText(xmlStr);
//                        mToast.show();
//                    }
//                } else {
//                    mToast.setText("体检登记失败！");
//                    mToast.show();
//                }
//            }
//            
//        });
//
//	}

	/**
     * @return
     */
//    private String assemblePacket_Checkin() {
//        getValue();
//        Jmjbxx mJmjbxx = (Jmjbxx) beanMap.get(Jmjbxx.class.getName());
//        if(mJmjbxx==null||mJmjbxx.getResidentName().equals("")||mJmjbxx.getPaperNum().equals("")){
//        	mToast.setText("请先登记居民信息，在进行体检登记！");
//        	mToast.show();
//        	return null;
//        }
//        Jktj_kstj mJktj_kstj = (Jktj_kstj) beanMap.get(Jktj_kstj.class.getName());
//        // 获取身份证号码
//        String paperNum = mJmjbxx.getPaperNum();
//        String sex = null;
//        switch (mJmjbxx.getSexCD()) {
//            case 1:
//                sex = "M";
//                break;
//            case 2:
//                sex = "F";
//                break;
//            default:
//                sex = "N";
//                break;
//        }
//        String birthDay = mJmjbxx.getBirthDay();
//        //10,未婚；20,已婚；22,再婚；23,复婚；30,丧偶；40,离婚；90,未说明的婚姻状况
//        String marriage = null;
//        switch (mJmjbxx.getMarriageCD()) {
//            //(1/2/3/4/5/6 已婚/未婚/离异/再婚/丧偶/不详)
//            case 10:
//                marriage = "2";
//                break;
//            case 20:
//                marriage = "1";
//                break;
//            case 22:
//                marriage = "4";
//                break;
//            case 23:
//                marriage = "6";
//                break;
//            case 30:
//                marriage = "5";
//                break;
//            case 40:
//                marriage = "3";
//                break;
//            case 90:
//                marriage = "6";
//                break;
//            default:
//                marriage = "6";
//                break;
//        }
//        String docId = null;
//        if (mJmjbxx.getDutyDoctor() != null) {
//            docId =  mJmjbxx.getDutyDoctor().getiD();
//        }
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        int age = 0;
//        // 计算年龄
//        try {
//            age = new Date(System.currentTimeMillis()).getYear() - dateFormat.parse(birthDay).getYear() + 1;
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        String strRequest = 
//          "<Staff>" +
//            "<NAME>"+mJmjbxx.getResidentName()+"</NAME>" +
//            "<SEX>" + sex + "</SEX>" +
//            "<BIRTHDAY>" + birthDay + "</BIRTHDAY>" +
//            "<IDCARD>" + paperNum + "</IDCARD>" +
//            "<MARRIAGE>" + marriage + "</MARRIAGE>" +
//            "<AGE>" + age + "</AGE>" +
//            "<TEL></TEL>" +
//            "<ADDRESS></ADDRESS>" +
//            "<DOC_ID>" + docId + "</DOC_ID>" +
//          "</Staff>";
//        return strRequest;
//    }

    /**
	 * @param isMale
	 * @param fyw
	 * @param ftw
	 * @return
	 */
//	private String getYtbResult(boolean isMale, float fyw, float ftw) {
//	    float result=fyw/ftw;
//		if (isMale) {
//			if (result > 0 && result < 0.85) {
//				return "正常";
//			}else if (result < 0.90) {
//				return "标准";
//			}else if ( result < 10) {
//				return "中心性肥胖";
//			}
//		} else {
//			if (result > 0 && result < 0.75) {
//				return "正常";
//			}else if (result < 0.80) {
//			    return "标准";
//            }else if (result < 10) {
//				return "中心性肥胖";
//			}
//		}
//
//		return "数据错误";
//	}
//
//	/**
//	 * @param ftg
//	 * @return
//	 */
//	private String getTgResult(float ftg) {
//		if (ftg > 0 && ftg < 0.56) {
//			return "甘油三酯偏少";
//		}
//		if (ftg >= 0.56 && ftg <= 1.7) {
//			return "正常";
//		}
//		if (ftg > 1.7) {
//			return "甘油三酯偏多";
//		}
//		return "数据错误";
//	}
//
//	/**
//	 * @param fchol
//	 * @return
//	 */
//	private String getCholResult(float fchol) {
//		if (fchol > 0 && fchol < 2.82) {
//			return "胆固醇偏少";
//		}
//		if (fchol >= 2.82 && fchol <= 5.95) {
//			return "正常";
//		}
//		if (fchol > 5.95) {
//			return "胆固醇偏多";
//		}
//		return "数据错误";
//	}
//	
//	
//	/**
//     * @param highZdbValue 高密度脂蛋白的值
//     * @return 结论
//     */
//    private String getHighZdbResult(float highZdbValue) {
//        if (highZdbValue<=0) {
//            return "数据测量错误";
//        }else if (highZdbValue<1.04) {
//            return "高密度脂蛋白偏低";
//        }else{
//            return "高密度脂蛋白正常";
//        }
//    }
//
//
//    /**
//     * @param lowZdbValue 低密度脂蛋白的值
//     * @return 结论
//     */
//    private String getLowZdbResult(float lowZdbValue) {
//        if (lowZdbValue<=0) {
//            return "数据测量错误";
//        }else if (lowZdbValue<=3.14) {
//            return "低密度脂蛋白正常";
//        }else{
//            return "低密度脂蛋白偏高";
//        }
//    }
//    
//	/**
//	 * @param condition
//	 * @param fxt
//	 * @return
//	 */
//	private String getXtResult(long condition, float fxt) {
//		switch ((int) condition) {
//		    case 0:
//	            if (fxt <= 0) {
//	                return "数据错误";
//	            } else if (fxt > 0 && fxt <= 11.1) {
//	                return "正常";
//	            }else if (fxt >11.1) {
//	                return "血糖偏高";
//	            }
//	            break;
//		case 1:
//			if (fxt <= 0) {
//				return "数据错误";
//			} else if (fxt > 0 && fxt <= 3.89) {
//				return "低血糖";
//			} else if (fxt > 3.89 && fxt <= 6.11) {
//				return "正常";
//			} else if (fxt > 6.11 && fxt <= 7) {
//				return "血糖偏高";
//			} else if (fxt > 7) {
//				return "高血糖";
//			}
//			break;
//		case 2:
//			if (fxt <= 0) {
//				return "数据错误";
//			} else if (fxt > 0 && fxt <= 3.89) {
//				return "低血糖";
//			} else if (fxt > 3.89 && fxt <= 7.8) {
//				return "正常";
//			} else if (fxt > 7.8&& fxt <= 11.1) {
//				return "血糖偏高";
//			} else if (fxt > 11.1) {
//				return "高血糖";
//			}
//			break;
//
//		default:
//			break;
//		}
//		return "数据错误";
//	}
//
//	/**
//	 * @param nSystolic
//	 * @param nDiatolic
//	 * @return
//	 */
//	private String getXyResult(int nSystolic, int nDiatolic) {
//		if (nSystolic > 0 && nSystolic <= 89) {
//			// 0-89 0-59 1 低血压
//			// 0-89 60-79 1 低血压
//			// 0-89 80-89 1 低血压
//			// 0-89 90-99 1 低血压
//			// 0-89 100-109 1 低血压
//			// 0-89 110-200 1 低血压
//			if (nDiatolic > 0 && nDiatolic <= 59) {
//				return "低血压";
//			} else if (nDiatolic > 59 && nDiatolic <= 79) {
//				return "低血压";
//			} else if (nDiatolic > 79 && nDiatolic <= 89) {
//				return "低血压";
//			} else if (nDiatolic > 89 && nDiatolic <= 99) {
//				return "低血压";
//			} else if (nDiatolic > 99 && nDiatolic <= 109) {
//				return "低血压";
//			} else if (nDiatolic > 109 && nDiatolic <= 200) {
//				return "低血压";
//			}
//		} else if (nSystolic > 89 && nSystolic <= 119) {
//			// 90-119 0-59 1 低血压
//			// 90-119 60-79 2 正常血压
//			// 90-119 80-89 3 正常高值
//			// 90-119 90-99 4 1级高血压【轻度】
//			// 90-119 100-109 5 2级高血压【中度】
//			// 90-119 110-200 6 3级高血压【高度】
//			if (nDiatolic > 0 && nDiatolic <= 59) {
//				return "低血压";
//			} else if (nDiatolic > 59 && nDiatolic <= 79) {
//				return "正常血压";
//			} else if (nDiatolic > 79 && nDiatolic <= 89) {
//				return "正常高值";
//			} else if (nDiatolic > 89 && nDiatolic <= 99) {
//				return "1级高血压【轻度】";
//			} else if (nDiatolic > 99 && nDiatolic <= 109) {
//				return "2级高血压【中度】";
//			} else if (nDiatolic > 109 && nDiatolic <= 200) {
//				return "3级高血压【高度】";
//			}
//		} else if (nSystolic > 119 && nSystolic <= 139) {
//			// 120-139 0-59 1 低血压
//			// 120-139 60-79 3 正常高值
//			// 120-139 80-89 3 正常高值
//			// 120-139 90-99 4 1级高血压【轻度】
//			// 120-139 100-109 5 2级高血压【中度】
//			// 120-139 110-200 6 3级高血压【高度】
//			if (nDiatolic > 0 && nDiatolic <= 59) {
//				return "低血压";
//			} else if (nDiatolic > 59 && nDiatolic <= 79) {
//				return "正常高值";
//			} else if (nDiatolic > 79 && nDiatolic <= 89) {
//				return "正常高值";
//			} else if (nDiatolic > 89 && nDiatolic <= 99) {
//				return "1级高血压【轻度】";
//			} else if (nDiatolic > 99 && nDiatolic <= 109) {
//				return "2级高血压【中度】";
//			} else if (nDiatolic > 109 && nDiatolic <= 200) {
//				return "3级高血压【高度】";
//			}
//		} else if (nSystolic > 139 && nSystolic <= 159) {
//			// 140-159 0-59 7 单纯收缩期高血压
//			// 140-159 60-79 7 单纯收缩期高血压
//			// 140-159 80-89 7 单纯收缩期高血压
//			// 140-159 90-99 4 1级高血压【轻度】
//			// 140-159 100-109 5 2级高血压【中度】
//			// 140-159 110-200 6 3级高血压【高度】
//			if (nDiatolic > 0 && nDiatolic <= 59) {
//				return "单纯收缩期高血压";
//			} else if (nDiatolic > 59 && nDiatolic <= 79) {
//				return "单纯收缩期高血压";
//			} else if (nDiatolic > 79 && nDiatolic <= 89) {
//				return "单纯收缩期高血压";
//			} else if (nDiatolic > 89 && nDiatolic <= 99) {
//				return "1级高血压【轻度】";
//			} else if (nDiatolic > 99 && nDiatolic <= 109) {
//				return "2级高血压【中度】";
//			} else if (nDiatolic > 109 && nDiatolic <= 200) {
//				return "3级高血压【高度】";
//			}
//		} else if (nSystolic > 159 && nSystolic <= 179) {
//			// 160-179 0-59 7 单纯收缩期高血压
//			// 160-179 60-79 7 单纯收缩期高血压
//			// 160-179 80-89 7 单纯收缩期高血压
//			// 160-179 90-99 5 2级高血压【中度】
//			// 160-179 100-109 5 2级高血压【中度】
//			// 160-179 110-200 6 3级高血压【高度】
//			if (nDiatolic > 0 && nDiatolic <= 59) {
//				return "单纯收缩期高血压";
//			} else if (nDiatolic > 59 && nDiatolic <= 79) {
//				return "单纯收缩期高血压";
//			} else if (nDiatolic > 79 && nDiatolic <= 89) {
//				return "单纯收缩期高血压";
//			} else if (nDiatolic > 89 && nDiatolic <= 99) {
//				return "2级高血压【中度】";
//			} else if (nDiatolic > 99 && nDiatolic <= 109) {
//				return "2级高血压【中度】";
//			} else if (nDiatolic > 109 && nDiatolic <= 200) {
//				return "3级高血压【高度】";
//			}
//		} else if (nSystolic > 179 && nSystolic <= 300) {
//			// 180-300 0-59 7 单纯收缩期高血压
//			// 180-300 60-79 7 单纯收缩期高血压
//			// 180-300 80-89 7 单纯收缩期高血压
//			// 180-300 90-99 6 3级高血压【高度】
//			// 180-300 100-109 6 3级高血压【高度】
//			// 180-300 110-200 6 3级高血压【高度】
//			if (nDiatolic > 0 && nDiatolic <= 59) {
//				return "单纯收缩期高血压";
//			} else if (nDiatolic > 59 && nDiatolic <= 79) {
//				return "单纯收缩期高血压";
//			} else if (nDiatolic > 79 && nDiatolic <= 89) {
//				return "单纯收缩期高血压";
//			} else if (nDiatolic > 89 && nDiatolic <= 99) {
//				return "3级高血压【高度】";
//			} else if (nDiatolic > 99 && nDiatolic <= 109) {
//				return "3级高血压【高度】";
//			} else if (nDiatolic > 109 && nDiatolic <= 200) {
//				return "3级高血压【高度】";
//			}
//		}
//		return "数据错误";
//	}

//	/**
//	 * 显示三围结论
//	 */
//	public void showResult4Measurements() {
//		String xw = mKstjPage02.mXWEdit.getText().toString();
//		String yw = mKstjPage02.mYWEdit.getText().toString();
//		String tw = mKstjPage02.mTWEdit.getText().toString();
//
//		if (yw != null && tw != null && yw.length() > 0 && tw.length() > 0) {
//			// 显示结论
//			String gender = mKstjPage01.mGenderText.getText().toString();
//			if (gender == null) {
//				return;
//			}
//			float fyw = Float.parseFloat(yw);
//			float ftw = Float.parseFloat(tw);
//			boolean isMale = gender.equals("性别：男") ? true : false;
//
//			DecimalFormat format = new DecimalFormat("0.00");
//			mKstjPage02.mYTBEdit.setText(format.format(fyw / ftw));
//			String ytbResult = getYtbResult(isMale, fyw, ftw);
//			mKstjPage02.mYTBJLEdit.setText(ytbResult);
//
//            JktjPttjPage.sHasData = true;
//		}
//	}
//
//	/**
//	 * 显示身体成分结论
//	 */
//	public void showResult4Bca() {
//	    String height = mKstjPage02.mHeightEdit.getText().toString();
//        String weight = mKstjPage02.mWeightEdit.getText().toString();
//	    String bmi=mKstjPage02.mBmiEdit.getText().toString();
//	    if(!height.equals("")&&!weight.equals("")&&!bmi.equals("")){
//	        JktjPttjPage.sHasData = true;
//	    }
//	}
//
//	/**
//	 * 显示血脂结论
//	 */
//	public void showResult4Lipids() {
//		String chol = mKstjPage02.mCholEdit.getText().toString();
//		String tg = mKstjPage02.mTgEdit.getText().toString();
//
//		if (chol != null && chol.length() > 0) {
//			// 显示结论
//			float fchol = Float.parseFloat(chol);
//			String cholResult = getCholResult(fchol);
//			mKstjPage02.mCholResultEdit.setText(cholResult);
//			
//			JktjPttjPage.sHasData = true;
//		}
//		if (tg != null && tg.length() > 0) {
//			// 显示结论
//			float ftg = Float.parseFloat(tg);
//			String tgResult = getTgResult(ftg);
//			mKstjPage02.mTgResultEdit.setText(tgResult);
//
//            JktjPttjPage.sHasData = true;
//		}
//		
//		
//		String higtZdb = mKstjPage02.mHighDensityEdit.getText().toString();
//        String lowZdb = mKstjPage02.mLowDensityEdit.getText().toString();
//		//高密度脂蛋白
//		if (higtZdb != null && higtZdb.length() > 0) {
//            // 显示结论
//            float higtZdbValue = Float.parseFloat(higtZdb);
//            String higtZdbResult = getHighZdbResult(higtZdbValue);
//            mKstjPage02.mHDResultEdit.setText(higtZdbResult);
//
//            JktjPttjPage.sHasData = true;
//        }
//		
//		//低密度脂蛋白
//        if (lowZdb != null && lowZdb.length() > 0) {
//            // 显示结论
//            float lowZdbValue = Float.parseFloat(lowZdb);
//            String lowZdbResult = getLowZdbResult(lowZdbValue);
//            mKstjPage02.mLDResultEdit.setText(lowZdbResult);
//
//            JktjPttjPage.sHasData = true;
//        }
//	}
//
//	/**
//	 * 显示血糖结论
//	 */
//	protected void showResult4BloodSugar() {
//		long condition = mKstjPage01.mCltjSpinner.getSelectedItemPosition();
//		String value = mKstjPage01.mXtzEdit.getText().toString();
//
//		if (value != null && value.length() > 0) {
//			// 显示结论
//			float fxt = Float.parseFloat(value);
//			String xtResult = getXtResult(condition, fxt);
//			mKstjPage01.mXtjlEdit.setText(xtResult);
//
//            JktjPttjPage.sHasData = true;
//		}
//	}
//
//	/**
//	 * 显示血压结论
//	 */
//	protected void showResult4BloodPressure() {
//		String systolic = mKstjPage01.mSystolicEdit.getText().toString();//收缩压
//		String diastolic = mKstjPage01.mDiastolicEdit.getText().toString();
//		String pulseRate = mKstjPage01.mPulseRateEdit.getText().toString();
//
//		if (systolic != null && diastolic != null && pulseRate != null
//				&& systolic.length() > 0 && diastolic.length() > 0
//				&& pulseRate.length() > 0) {
//			// 显示结论
//			int nSystolic = Integer.parseInt(systolic);
//			int nDiatolic = Integer.parseInt(diastolic);
//			String xyResult = getXyResult(nSystolic, nDiatolic);
//			mKstjPage01.mPressResultEdit.setText(xyResult);
//			JktjPttjPage.sHasData = true;
//		}
//	}
//
//	// 快速体检数据绑定
//	class MyKstjPagerAdapter extends PagerAdapter {
//		private Context c;
//		List<View> mPageList = new ArrayList<View>();
//
//		public MyKstjPagerAdapter(Context c) {
//			super();
//			this.c = c;
//			// 添加页面
//			mKstjPage01 = new JktjKstjPage01(c, beanMap);
//			mPageList.add(mKstjPage01);
//			mKstjPage02 = new JktjKstjPage02(c, beanMap,JktjPttjPage.this);
//			mPageList.add(mKstjPage02);
//		}
//
//		public void destroyItem(View arg0, int arg1, Object arg2) {
//			((ViewPager) arg0).removeView(mPageList.get(arg1));
//		}
//
//		@Override
//		public void finishUpdate(View arg0) {
//		}
//
//		public int getCount() {
//			return mPageList.size();
//		}
//
//		public Object instantiateItem(View arg0, int arg1) {
//			View v = mPageList.get(arg1);
//			((ViewPager) arg0).addView(v);
//			return v;
//		}
//
//		public boolean isViewFromObject(View arg0, Object arg1) {
//			return (arg0 == arg1);
//		}
//
//		@Override
//		public void restoreState(Parcelable arg0, ClassLoader arg1) {
//		}
//
//		@Override
//		public Parcelable saveState() {
//			return null;
//		}
//
//		@Override
//		public void startUpdate(View arg0) {
//		}
//	}

	// 普通体检数据绑定
	class MyPttjPagerAdapter extends PagerAdapter {
		private Context c;
		List<View> mPageList = new ArrayList<View>();

		public MyPttjPagerAdapter(Context c) {
			super();
			this.c = c;

			// 添加页面
			mPttjPage01 = new JktjPttjPage01(c);
			mPageList.add(mPttjPage01);
			mPttjPage02 = new JktjPttjPage02(c);
			mPageList.add(mPttjPage02);
			mPttjPage03 = new JktjPttjPage03(c);
			mPageList.add(mPttjPage03);
			mPttjPage04 = new JktjPttjPage04(c);
			mPageList.add(mPttjPage04);
			mPttjPage05 = new JktjPttjPage05(c);
			mPageList.add(mPttjPage05);
			mPttjPage06 = new JktjPttjPage06(c);
			mPageList.add(mPttjPage06);
			mPttjPage07 = new JktjPttjPage07(c);
			mPageList.add(mPttjPage07);
			mPttjPage08 = new JktjPttjPage08(c);
			mPageList.add(mPttjPage08);
			mPttjPage09 = new JktjPttjPage09(c);
			mPageList.add(mPttjPage09);
			mPttjPage10 = new JktjPttjPage10(c);
			mPageList.add(mPttjPage10);
			mPttjPage11 = new JktjPttjPage11(c);
			mPageList.add(mPttjPage11);
			mPttjPage12 = new JktjPttjPage12(c);
			mPageList.add(mPttjPage12);
			mPttjPage13 = new JktjPttjPage13(c);
			mPageList.add(mPttjPage13);
			mPttjPage14 = new JktjPttjPage14(c);
			mPageList.add(mPttjPage14);
			mPttjPage15 = new JktjPttjPage15(c);
			mPageList.add(mPttjPage15);
		}

		public void destroyItem(View arg0, int arg1, Object arg2) {
			((ViewPager) arg0).removeView(mPageList.get(arg1));
		}

		@Override
		public void finishUpdate(View arg0) {
			// TODO Auto-generated method stub

		}

		public int getCount() {
			// return mViews.length;
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
			// TODO Auto-generated method stub

		}

		@Override
		public Parcelable saveState() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void startUpdate(View arg0) {
			// TODO Auto-generated method stub

		}
	}

//	@Override
//	protected void onDetachedFromWindow() {
//		super.onDetachedFromWindow();
//		Log.i(TAG, "onDetachedFromWindow");
//		FileLog.i(TAG, "onDetachedFromWindow");
//		/**
//		 * 关闭蓝牙设备
//		 */
//		if (mBluethoothServer != null)
//			mBluethoothServer.close();
//	}
//
//	@Override
//	protected void onAttachedToWindow() {
//		super.onAttachedToWindow();
//		Log.i(TAG, "onAttachedToWindow");
//		FileLog.i(TAG, "onAttachedToWindow");
//
//		/**
//		 * 打开蓝牙设备
//		 */
//		if (mBluethoothServer != null) {
//			mBluethoothServer.open();
//		}
//	}

	/**
	 * @param strReading
	 */
//	private void updateView(String strReading) {
//
//		Log.i(TAG, "entry updateView...");
//
//		String head = strReading.substring(0, 2);
//
//		// 判断数据是否有效，血压仪
//		if (head.equals("80")) {
//			byte[] hexBytes = CommonUtil.hexString2Bytes(strReading);
//			mKstjPage01.mSystolicEdit.setText((hexBytes[1] + hexBytes[2]) + "");
//			Log.i(TAG, "mSystolicEdit:"
//					+ mKstjPage01.mSystolicEdit.getText().toString());
//			mKstjPage01.mDiastolicEdit.setText(hexBytes[2] + "");
//			Log.i(TAG, "mDiastolicEdit:"
//					+ mKstjPage01.mDiastolicEdit.getText().toString());
//			mKstjPage01.mPulseRateEdit.setText(hexBytes[3] + "");
//			Log.i(TAG, "mPulseRateEdit:"
//					+ mKstjPage01.mPulseRateEdit.getText().toString());
//		}
//		// mPressResultEdit = (EditText) findViewById(R.id.press_result_edit);
//		// mHeightEdit = (EditText) findViewById(R.id.height_edit);
//		// mWeightEdit = (EditText) findViewById(R.id.weight_edit);
//		// mBmiEdit = (EditText) findViewById(R.id.bmi_edit);
//		// mWeightResultEdit = (EditText) findViewById(R.id.weight_result_edit);
//
//		// 判断数据是否有效，体成分分析仪
//		if (head.equals("8F")) {
//			// byte[] hexBytes = CommonUtil.hexString2Bytes(strReading);
//			// mSystolicEdit.setText((hexBytes[1] + hexBytes[2]) + "");
//			// Log.i(TAG, "mSystolicEdit:" +
//			// mSystolicEdit.getText().toString());
//			// mDiastolicEdit.setText(hexBytes[2] + "");
//			// Log.i(TAG, "mDiastolicEdit:" +
//			// mDiastolicEdit.getText().toString());
//			// mPulseRateEdit.setText(hexBytes[3] + "");
//			// Log.i(TAG, "mPulseRateEdit:" +
//			// mPulseRateEdit.getText().toString());
//		}
//	}

	@Override
	public void setValue() {
		// TODO Auto-generated method stub
//		mKstjPage01.setValue();
//		mKstjPage02.setValue();
	}

	@Override
	public boolean getValue() {
		// TODO Auto-generated method stub
//		if(mKstjPage01.getValue()&&mKstjPage02.getValue()){
//			
//		}
		return true;
	}

	public void getBeanFromDB() {
		if (beanMap == null)
			return;
		List<Class<? extends IBean>> listBean = new ArrayList<Class<? extends IBean>>();
		listBean.add(Jmjbxx.class);
		BeanUtil.getInstance().getJbxxFromDb(listBean,
				new BeanUtil.OnResultFromDb() {
					@Override
					public void onResult(List<IBean> listBean, boolean isSucc) {
					    if(listBean==null||listBean.size()<0)
                            return;
//						if (isSucc) {
							beanMap.put(Jmjbxx.class.getName(), listBean.get(0));
							setValue();
//						}
					}
				});

	}
	
	public void saveJktjToDb() {
	    getValue();
	    
		if (!sHasData) {
            mToast.setText("请填写至少一组数据！");
            mToast.show();
            return;
		}
		List<IBean> listBeans = new ArrayList<IBean>();
		listBeans.add(beanMap.get(Jktj_kstj.class.getName()));
        BeanUtil.getInstance().saveJktjToDb(listBeans, new Date().getTime(),
                new BeanUtil.OnResultSaveToDb() {
                    @Override
                    public void onResult(List<SaveToDbResult> listBean, boolean isSucc) {
                        if (isSucc) {
                            mToast.setText("快速体检数据保存成功！");
                            mToast.show();
                        } else {
                            mToast.setText("快速体检数据保存失败！");
                            mToast.show();
                        }
                    }
		});
		
	}

//	public void clear(){
//	    if(mKstjPage01==null||mKstjPage02==null){
//	        return;
//	    }
//	    mKstjPage01.clear();
//	    mKstjPage02.clear();
//	}

    /**
     * @param index
     */
    public void showItemByIndex(int index) {
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.cking.phss.page.IPage#clear()
     */
    @Override
    public void clear() {
    }
}
