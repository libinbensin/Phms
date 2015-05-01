package com.cking.phss.page;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.util.AttributeSet;
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
import com.cking.phss.bean.BeanUtil.OnResultFromWeb;
import com.cking.phss.bean.BeanUtil.SaveToDbResult;
import com.cking.phss.bean.IBean;
import com.cking.phss.bean.Jktj_kstj;
import com.cking.phss.bean.Jmjbxx;
import com.cking.phss.bean.Sfgljl_gxy;
import com.cking.phss.bean.Sfgljl_gxy.FZJC;
import com.cking.phss.bean.Sfgljl_tnb;
import com.cking.phss.dto.IDto;
import com.cking.phss.dto.Login1;
import com.cking.phss.dto.innner.MedicineUse;
import com.cking.phss.dto.sfgl.tnb.Bctnbglk26;
import com.cking.phss.dto.sfgl.tnb.Bctnbsfjl23;
import com.cking.phss.dto.sfgl.tnb.Ddtnbglkxxxx25;
import com.cking.phss.dto.sfgl.tnb.Dtnbsfjlxxxx22;
import com.cking.phss.global.Global;
import com.cking.phss.sqlite.Sfgl;
import com.cking.phss.sqlite.SfglBll;
import com.cking.phss.util.MyApplication;
import com.cking.phss.util.PrinterUtil;
import com.cking.phss.util.TispToastFactory;
import com.cking.phss.view.SfglBodyView.OnGetSfbhListener;
import com.cking.phss.widget.SfglTjLayout;
import com.cking.phss.xml.util.XmlSerializerUtil;

public class SfglTnbPage extends MyPage implements ISfglPage {

//    public GuidePager mGuidePager = null;
    private Map<String, IBean> beanMap = new HashMap<String, IBean>();
    private Jmjbxx mJmjbxx = null;
    private Sfgljl_tnb mSfgljl_tnb = null;
    private Context mContext = null;
    public int checkReport = 1;// 检查是否进入报卡状态，1表示没有 ，2表示进入高血压报卡，3表示进入糖尿病报卡
    /**
     * 全局控件
     * 
     * @param context
     */
//    public RadioGroup mTabRadios = null;
    private Toast mToast = null;

    SfglTjLayout sfglTjLayout; // 底部统计信息
    LinearLayout layout_content;
    MyButton imageview_left_page;
    MyButton imageview_right_page;
    TextView textview_page;
    TextView textview_total_page;
    List<View> mPageList = new ArrayList<View>();
    int pageIndexFrom1 = 1;
    private static final int MAX_PAGE_COUNT = 6;
    
    private static int mTabRadioID = 0;

    /**
     * 糖尿病四个子页面
     * 
     * @param context
     */
    SfglTnbPage01 mTnbPage01 = null;
    SfglTnbPage02 mTnbPage02 = null;
    SfglTnbPage03 mTnbPage03 = null;
    SfglTnbPage04 mTnbPage04 = null;
    SfglTnbPage05 mTnbPage05 = null;
    SfglTnbPage06 mTnbPage06 = null;

    public int operateFlag = 1;// 操作的状态，0表示无操作，1表示新增操作，2表示编辑操作

    public SfglTnbPage(Context context) {
        super(context);

        beanMap.put(Jmjbxx.class.getName(), Global.jmjbxx);
        beanMap.put(Sfgljl_tnb.class.getName(), Global.sfgljlTnb);
        beanMap.put(Jktj_kstj.class.getName(), Global.jktjKstj);
        beanMap.put(Ddtnbglkxxxx25.class.getName(), new Ddtnbglkxxxx25());

        Bctnbglk26 bctnbglk26 = new Bctnbglk26();
        bctnbglk26.request = new Bctnbglk26.Request();
        beanMap.put(Bctnbglk26.class.getName(), bctnbglk26);
        // init(context);
    }

    /**
     * @param context
     * @param attrs
     */
    public SfglTnbPage(Context context, AttributeSet attrs) {
        super(context, attrs);
        // init(context);
    }

    protected void init(final Context context) {
        mContext = context;
        mToast = TispToastFactory.getToast(context, "");
        LayoutInflater.from(context).inflate(R.layout.fragment_sfgl_common_layout, this);
        
        loadPage(context, this);
    }

    /**
     * 设置第一界面的默认显示信息
     * 
     * @param context
     * @param viewGroup
     */
    public void loadPage(Context context, ViewGroup viewGroup) {
        sfglTjLayout = (SfglTjLayout) findViewById(R.id.sfglTjLayout);
        sfglTjLayout.setDisType("0102");

        // getTnbBeanFromDB();

        // 添加页面
        mTnbPage01 = new SfglTnbPage01(context, beanMap);
        mPageList.add(mTnbPage01);
        mTnbPage02 = new SfglTnbPage02(context, beanMap);
        mPageList.add(mTnbPage02);
        mTnbPage03 = new SfglTnbPage03(context, beanMap);
        mPageList.add(mTnbPage03);
        mTnbPage04 = new SfglTnbPage04(context, beanMap, SfglTnbPage.this);
        mPageList.add(mTnbPage04);
        mTnbPage05 = new SfglTnbPage05(context, beanMap);
        mPageList.add(mTnbPage05);
        mTnbPage06 = new SfglTnbPage06(context, beanMap);
        mPageList.add(mTnbPage06);
        layout_content = (LinearLayout) findViewById(R.id.layout_content);
        imageview_left_page = (MyButton) findViewById(R.id.imageview_left_page);
        imageview_right_page = (MyButton) findViewById(R.id.imageview_right_page);
        textview_page = (TextView) findViewById(R.id.textview_page);
        textview_total_page = (TextView) findViewById(R.id.textview_total_page);
        textview_total_page.setText(MAX_PAGE_COUNT + "");
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
    }

    /**
     * 随访管理糖尿病
     */
    private void saveTnbValueToWeb(final OnGetSfbhListener onGetSfbhListener) {
        Jmjbxx mJmjbxx = (Jmjbxx) beanMap.get(Jmjbxx.class.getName());
        if (mJmjbxx == null || mJmjbxx.getResidentID().equals("")) {
            mToast.setText(R.string.toast_info_none_resident);
            mToast.show();
            return;
        }
        String stringuserID = MyApplication.getInstance().getSession().getLoginResult().response.userID;
        if (getValue()) {

            mSfgljl_tnb = (Sfgljl_tnb) beanMap.get(Sfgljl_tnb.class.getName());

            if (operateFlag == 0) {// 如果即没有做新增操作，也没有做修改操作，而是使用默认的方式
                // 从数据库中查询最后一次的随访时间和编号
                String residentUUID = MyApplication.getInstance().getSession().getCurrentResident()
                        .getResidentUUID();
                Sfgl sfgl = SfglBll.queryLast(Sfgljl_tnb.class.getName(), residentUUID);
                if (sfgl != null) {
                    Sfgljl_tnb sfgljl_tnbQuery = (Sfgljl_tnb) XmlSerializerUtil.getInstance()
                            .beanFromXML(Sfgljl_tnb.class, sfgl.getBean());
                    if (sfgljl_tnbQuery != null) {
                        if (sfgljl_tnbQuery.getVisitDate() != null) {
                            if (!new SimpleDateFormat("yyyy-MM-dd").format(new Date()).equals(
                                    sfgljl_tnbQuery.getVisitDate())) {
                                // 不是同一天，那么默认使用编辑模式
                                mSfgljl_tnb.setVisitID(sfgljl_tnbQuery.getVisitID());
//                                mTnbPage01.mUserIdTextView.setText(sfgljl_tnbQuery.getVisitID());
                                onGetSfbhListener.onGetSfbh(sfgljl_tnbQuery.getVisitID());
                            }
                        }
                    }
                }
            }

            Bctnbsfjl23 bctnbsfjl23 = new Bctnbsfjl23();

            bctnbsfjl23.request = new Bctnbsfjl23.Request();
            bctnbsfjl23.request.userID = stringuserID;
            mJmjbxx = (Jmjbxx) beanMap.get(Jmjbxx.class.getName());
            if (mJmjbxx != null) {
                bctnbsfjl23.request.residentID = mJmjbxx.getResidentID();
            }
            // 设置责任医生
            if (Global.doctorID != null && Global.doctorName != null) {
                mSfgljl_tnb.setDoctorID(Global.doctorID);
                mSfgljl_tnb.setDoctorName(Global.doctorName);
            }

            bctnbsfjl23.request.visitID = mSfgljl_tnb.getVisitID();
            // 从数据库中读数据，拿到随访序列号，如果是在同一天，则使用同一个序列号，如果不是，那么使用""
            if (bctnbsfjl23.request.visitID.trim().equals("")) {
                bctnbsfjl23.request.type = 1;// 新增存盘
            } else {
                bctnbsfjl23.request.type = 2;// 编辑存盘
            }
            bctnbsfjl23.request.visitDate = mSfgljl_tnb.getVisitDate();

            bctnbsfjl23.request.doctorID = mSfgljl_tnb.getDoctorID();
            bctnbsfjl23.request.doctorName = mSfgljl_tnb.getDoctorName();
            bctnbsfjl23.request.dutyDoctorID = Global.doctorID;
            bctnbsfjl23.request.dutyDoctorName = Global.doctorName;
            bctnbsfjl23.request.SFFSCD = mSfgljl_tnb.getsFFSCD();
            bctnbsfjl23.request.XCSF = mSfgljl_tnb.getxCSF();
            bctnbsfjl23.request.ZZCD = mSfgljl_tnb.getzZCD();
            bctnbsfjl23.request.ZZQT = mSfgljl_tnb.getZZQT();
            bctnbsfjl23.request.SBP = mSfgljl_tnb.getsBP();
            bctnbsfjl23.request.DBP = mSfgljl_tnb.getdBP();
            bctnbsfjl23.request.HeartRate = mSfgljl_tnb.getbCXL();
            bctnbsfjl23.request.BCTZ = mSfgljl_tnb.getbCTZ();
            bctnbsfjl23.request.BCSG = mSfgljl_tnb.getbCSG();
            bctnbsfjl23.request.XCTZ = mSfgljl_tnb.getxCTZ();
            bctnbsfjl23.request.TZZS = mSfgljl_tnb.gettZZS();
            bctnbsfjl23.request.xczs = mSfgljl_tnb.getxCZS();
            bctnbsfjl23.request.DMBDCD = mSfgljl_tnb.getdMBDCD();
            bctnbsfjl23.request.WaistNow = mSfgljl_tnb.getWaistNow();
            bctnbsfjl23.request.WaistTarget = mSfgljl_tnb.getWaistTarget();
            bctnbsfjl23.request.QTTZ = mSfgljl_tnb.getqTTZ();
            bctnbsfjl23.request.BCXYL = mSfgljl_tnb.getbCXYL();
            bctnbsfjl23.request.XCXY = mSfgljl_tnb.getxCXY();
            bctnbsfjl23.request.BCYJ = mSfgljl_tnb.getbCYJ();
            bctnbsfjl23.request.XCYJ = mSfgljl_tnb.getxCYJ();
            bctnbsfjl23.request.YDZC = mSfgljl_tnb.getyDZC();
            bctnbsfjl23.request.XLTZCD = mSfgljl_tnb.getxLTZCD();
            bctnbsfjl23.request.YDCF = mSfgljl_tnb.getyDCF();
            bctnbsfjl23.request.XCYDZC = mSfgljl_tnb.getxCYDZC();
            bctnbsfjl23.request.XCYDCD = mSfgljl_tnb.getxCYDCD();
            bctnbsfjl23.request.BCZSL = mSfgljl_tnb.getbCZSL();
            bctnbsfjl23.request.XCZSL = mSfgljl_tnb.getxCZSL();
            bctnbsfjl23.request.ZYXWCD = mSfgljl_tnb.getzYXWCD();
            bctnbsfjl23.request.KFXT = mSfgljl_tnb.getkFXT();
            bctnbsfjl23.request.XHDB = mSfgljl_tnb.getxHDB();
            bctnbsfjl23.request.QTJC = mSfgljl_tnb.getqTJC();
            bctnbsfjl23.request.FYYCXCD = mSfgljl_tnb.getfYYCXCD();
            bctnbsfjl23.request.BLFY = mSfgljl_tnb.getbLFY();
            bctnbsfjl23.request.DXTFYCD = mSfgljl_tnb.getdXTFYCD();
            bctnbsfjl23.request.SFFLCD = mSfgljl_tnb.getsFFLCD();
            bctnbsfjl23.request.ZZYY = mSfgljl_tnb.getzZYY();
            bctnbsfjl23.request.ZZKB = mSfgljl_tnb.getzZKB();
            bctnbsfjl23.request.BZ = mSfgljl_tnb.getBz();
            bctnbsfjl23.request.cHXT = mSfgljl_tnb.getcHXT();
            bctnbsfjl23.request.qTXT = mSfgljl_tnb.getqTXT();
            bctnbsfjl23.request.FYQK = mSfgljl_tnb.getfYQK();
            
            bctnbsfjl23.request.referralVisitDate = mSfgljl_tnb.getReferralVisitDate();
            if (mSfgljl_tnb.getComplication() != null) {
                bctnbsfjl23.request.complication = mSfgljl_tnb.getComplication();
            }
            if (mSfgljl_tnb.getComorbidity() != null) {
                bctnbsfjl23.request.comorbidity = mSfgljl_tnb.getComorbidity();
            }
            bctnbsfjl23.request.otherDiseases = mSfgljl_tnb.getOtherDiseases();

            String fzjcString = "";
            if (mSfgljl_tnb.getfZJC() != null) {
                List<FZJC> fromBean = mSfgljl_tnb.getfZJC();
                for (FZJC fzjc : fromBean) {
                    fzjcString += (fzjc.getjCXM() + ",");
                    fzjcString += (fzjc.getjCJG() + ",");
                    fzjcString += (fzjc.getjCR() + ",");
                    fzjcString += (fzjc.getjCRQ() + "|");
                }
            }
            bctnbsfjl23.request.QTJC = fzjcString;

            bctnbsfjl23.request.medicineUse = (ArrayList<MedicineUse>) mSfgljl_tnb.getMedicineUse();

            // 胰岛素
            bctnbsfjl23.request.insulinUse = mSfgljl_tnb.getInsulinUse();
            /**
             * 发送网络请求
             */
            List<IDto> beanList = new ArrayList<IDto>();
            beanList.add(bctnbsfjl23);
            BeanUtil.getInstance().getBeanFromWeb(beanList, new OnResultFromWeb() {
                @Override
                public void onResult(List<IDto> listBean, boolean isSucc) {
                    if (listBean != null && listBean.size() > 0) {
                        Bctnbsfjl23 responseBean = (Bctnbsfjl23) listBean.get(0);
                        if (isSucc) {
                            if (responseBean.response == null) {
                                mToast.setText("网络异常");
                                mToast.show();
                            } else {
                                if (responseBean.response.errMsg != null
                                        && responseBean.response.errMsg.length() > 0) {
                                    mToast.setText(responseBean.response.errMsg);
                                    mToast.show();
                                    return;
                                } else {
//                                    mTnbPage01.mUserIdTextView
//                                            .setText(responseBean.response.VisitID);
                                    mSfgljl_tnb.setVisitID(responseBean.response.VisitID);
                                    onGetSfbhListener.onGetSfbh(responseBean.response.VisitID);
                                    mToast.setText("数据上传成功");
                                    mToast.show();
                                    saveValueToDb();
                                }
                            }
                        } else {
                            mToast.setText("网络请求失败");
                            mToast.show();
                        }
                    }
                }
            });
        }

    }

    public void saveValueToDb() {
        if (!getValue()) {
            return;
        }
        Jmjbxx mJmjbxx = (Jmjbxx) beanMap.get(Jmjbxx.class.getName());
        if (mJmjbxx == null || mJmjbxx.getResidentID().equals("")) {
            mToast.setText(R.string.toast_info_none_resident);
            mToast.show();
            return;
        }
        List<IBean> listBeans = new ArrayList<IBean>();
        listBeans.add(beanMap.get(Sfgljl_tnb.class.getName()));
        long operTime = -1;
        if (operateFlag == 1) { // 新增
            operTime = new Date().getTime();
        } else if (operateFlag == 2) { // 修改
            operTime = -1;
        }
        BeanUtil.getInstance().saveSfglToDb(listBeans, operTime,
                new BeanUtil.OnResultSaveToDb() {
                    @Override
                    public void onResult(List<SaveToDbResult> listBean, boolean isSucc) {
                        if (isSucc) {
                            mToast.setText("随访管理糖尿病存储成功！");
                            mToast.show();
                    SaveToDbResult result = listBean.get(0);
                    // 操作标志 1-原始 2-新建 3-修改
                    int operType = result.isAdd ? 2 : 3;
                    int dataSource = 0; // 0 代表不改变原来的状态 ，1 代表档案平台，2代表非档案平台
                    // 保存本地记录
                    if (operateFlag == 1) { // 新建
                        dataSource = 2;
                    } else if (operType == 2) { // 第一次插入数据库
                        dataSource = 1;
                    }
                    // 保存本地记录
                    Jmjbxx jmjbxx = (Jmjbxx) beanMap.get(Jmjbxx.class.getName());
                    BeanUtil.getInstance().saveLocalRecord(mContext, jmjbxx, result.projectUUID,
                            "42", dataSource, operType, new Date().getTime(),
                            Sfgljl_tnb.class.getName());
                        } else {
                            mToast.setText("随访管理糖尿病存储失败！");
                            mToast.show();
                        }
                    }
                });
    }

    /**
     * 和idto对接没有写
     * 
     * @param onGetSfbhListener
     */
    public void saveValueToWeb(OnGetSfbhListener onGetSfbhListener) {
        saveTnbValueToWeb(onGetSfbhListener);
        // getValue();
    }

    // 从数据库取出糖尿病的信息
    public void getTnbBeanFromDB() {
        if (beanMap == null) {// 如果为null，表示出错了，返回
            return;
        }

        // 首先取到Jmjbxx，放入map中
        List<Class<? extends IBean>> jbxxListBean = new ArrayList<Class<? extends IBean>>();
        jbxxListBean.add(Jmjbxx.class);
        BeanUtil.getInstance().getJbxxFromDb(jbxxListBean, new BeanUtil.OnResultFromDb() {
            @Override
            public void onResult(List<IBean> listBean, boolean isSucc) {
                if (listBean == null || listBean.size() < 0)
                    return;
                beanMap.put(Jmjbxx.class.getName(), listBean.get(0));
                //mTnbPage01.setJbxxValue();
            }
        });

        // 在取最近一次的糖尿病随访记录
        /*
         * List<Class<? extends IBean>> tnbListBean = new ArrayList<Class<?
         * extends IBean>>(); tnbListBean.add(Sfgljl_tnb.class);
         * BeanUtil.getInstance().getLastSfglFromDb(tnbListBean, new
         * BeanUtil.OnResultFromDb() {
         * 
         * @Override public void onResult(List<IBean> listBean, boolean isSucc)
         * { if (isSucc) if (listBean.get(0) != null) {
         * tnbBeanMap.put(Sfgljl_tnb.class.getName(), listBean.get(0));
         * setValue(); }
         * 
         * } });
         */

        // // 在取出快速体检的数据放进去
        // List<Class<? extends IBean>> kstjListBean = new ArrayList<Class<?
        // extends IBean>>();
        // kstjListBean.add(Jktj_kstj.class);
        // BeanUtil.getInstance().getLastJktjFromDb(kstjListBean, new
        // BeanUtil.OnResultFromDb() {
        // @Override
        // public void onResult(List<IBean> listBean, boolean isSucc) {
        // if (isSucc)
        // if (listBean.get(0) != null) {
        // beanMap.put(Jktj_kstj.class.getName(), listBean.get(0));
        // }
        //
        // }
        // });
    }

    /* (non-Javadoc)
     * @see com.cking.phss.page.IPage#setValue()
     */
    @Override
    public void setValue() { if (!hasInit) {return;}
      if (checkReport == 1) {
          mTnbPage01.setValue();
          mTnbPage02.setValue();
          mTnbPage03.setValue();
          mTnbPage04.setValue();
          mTnbPage05.setValue();
          mTnbPage06.setValue();
      }
    }

    /* (non-Javadoc)
     * @see com.cking.phss.page.IPage#getValue()
     */
    @Override
    public boolean getValue() { if (!hasInit) {return false;}
        return (mTnbPage01.getValue() && mTnbPage02.getValue() && mTnbPage03.getValue()
                && mTnbPage04.getValue() && mTnbPage05.getValue() && mTnbPage06.getValue());
    }
    
    /* (non-Javadoc)
     * @see com.cking.phss.page.ISfglPage#onResultSflb(boolean)
     */
    @Override
    public void onResultSflb(boolean canDoSf) {
        if (canDoSf) {
            mToast.setText("该居民可以进行糖尿病随访");
            mToast.show();
        } else {
            mToast.setText("该居民不可以进行糖尿病随访");
            mToast.show();
        }
    }

    /* (non-Javadoc)
     * @see com.cking.phss.page.ISfglPage#onResultSftj(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public void onResultSftj(String should, String done, String notdo) {
        sfglTjLayout.setFootTipsText(should, done, notdo);
    }

    // 获取最后一条糖尿病随访信息
    public void getLastValue(final OnGetSfbhListener onGetSfbhListener) {
        if (MyApplication.getInstance().getSession().getCurrentResident() == null) {
            mToast.setText("当前没有居民信息，请先登录！");
            mToast.show();
            return;
        }
        Login1 login1Result = MyApplication.getInstance().getSession().getLoginResult();
        if (login1Result == null || login1Result.response == null) {
            mToast.setText("当前没有医生登录，请先登录！");
            mToast.show();
            return;
        }
        Jmjbxx jmjbxx = (Jmjbxx) beanMap.get(Jmjbxx.class.getName());

        Dtnbsfjlxxxx22 dtnbsfjlxxxx22 = new Dtnbsfjlxxxx22();
        dtnbsfjlxxxx22.request = new Dtnbsfjlxxxx22.Request();
        dtnbsfjlxxxx22.request.employeeNo = login1Result.response.employeeNo;
        dtnbsfjlxxxx22.request.residentID = jmjbxx.getResidentID();
        dtnbsfjlxxxx22.request.visitID = "0";// 0表示请求最后一条数据

        List<IDto> beanList = new ArrayList<IDto>();
        beanList.add(dtnbsfjlxxxx22);
        BeanUtil.getInstance().getBeanFromWeb(beanList, new BeanUtil.OnResultFromWeb() {
            @Override
            public void onResult(List<IDto> listBean, boolean isSucc) {
                if (isSucc) {
                    StringBuilder sb = new StringBuilder();
                    Dtnbsfjlxxxx22 dtnbsfjlxxxx22Result = (Dtnbsfjlxxxx22) listBean.get(0);
                    if (dtnbsfjlxxxx22Result == null || dtnbsfjlxxxx22Result.response == null) {
                    } else {
                        if (dtnbsfjlxxxx22Result.response.errMsg.length() > 0) {
                            mToast.setText("错误信息：" + dtnbsfjlxxxx22Result.response.errMsg);
                            mToast.show();
                        } else {
                            try {
                                setLastTnbjlxxxx(dtnbsfjlxxxx22Result.response, onGetSfbhListener);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        });
    }

    public void setLastTnbjlxxxx(Dtnbsfjlxxxx22.Response response,
            OnGetSfbhListener onGetSfbhListener) throws ParseException {
        Sfgljl_tnb mSfgljl_tnb = (Sfgljl_tnb) beanMap.get(Sfgljl_tnb.class.getName());

        String visitDate = response.visitDate;
        if(!visitDate.equals("")){
            if (operateFlag == 1) {// 表示是新增操作
                // 因为每天只能新增一条数据，所以要判断是否是同一天
                if (new SimpleDateFormat("yyyy-MM-dd").parse(visitDate).getDay() == new Date().getDay()) {
                    // 如果是同一天
                    mToast.setText("每天只能有一次随访记录，所以新增失败，默认改为编辑模式");
                    mToast.show();
                    mSfgljl_tnb.setVisitID(response.visitID);// 使用上一次的id，即为编辑模式
                    mSfgljl_tnb.setVisitDate(response.visitDate);
                } else {
                    mSfgljl_tnb.setVisitID("");// ID为空，则为新增模式
                    mSfgljl_tnb.setVisitDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
                }
            } else if (operateFlag == 2) {// 编辑模式
                mSfgljl_tnb.setVisitID(response.visitID);// 使用上一次的id，即为编辑模式
                mSfgljl_tnb.setVisitDate(response.visitDate);
            }
        }else {
            if (operateFlag == 2) {// 表示是编辑操作，但是没有记录，所以改为编辑操作
                // 因为每天只能新增一条数据，所以要判断是否是同一天
                mToast.setText("该居民没有随访糖尿病记录，默认改为新增模式！");
                mToast.show();
                mSfgljl_tnb.setVisitID("");// ID为空，则为新增模式
                mSfgljl_tnb.setVisitDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
            }
        }
        onGetSfbhListener.onGetSfbh(mSfgljl_tnb.getVisitID());
        // date不设置，使用新的date
        mSfgljl_tnb.setDutyDoctor(response.dutyDoctorName);
        mSfgljl_tnb.setsFFSCD(response.SFFSCD);
        mSfgljl_tnb.setxCSF(response.XCSF);
        mSfgljl_tnb.setzZCD(response.ZZCD);
        mSfgljl_tnb.setzZQT(response.ZZQT);
        mSfgljl_tnb.setsBP(response.SBP);
        mSfgljl_tnb.setdBP(response.DBP);
        mSfgljl_tnb.setbCXL(response.HeartRate);
        mSfgljl_tnb.setbCTZ(response.BCTZ);
        mSfgljl_tnb.setbCSG(response.BCSG);
        mSfgljl_tnb.setxCTZ(response.XCTZ);
        mSfgljl_tnb.settZZS(response.TZZS);
        mSfgljl_tnb.setxCZS(response.xczs);
        mSfgljl_tnb.setdMBDCD(response.DMBDCD);
        mSfgljl_tnb.setWaistNow(response.WaistNow);
        mSfgljl_tnb.setWaistTarget(response.WaistTarget);
        mSfgljl_tnb.setqTTZ(response.QTTZ);
        mSfgljl_tnb.setbCXYL(response.BCXYL);
        mSfgljl_tnb.setxCXY(response.XCXY);
        mSfgljl_tnb.setbCYJ(response.BCYJ);
        mSfgljl_tnb.setxCYJ(response.XCYJ);
        mSfgljl_tnb.setyDZC(response.YDZC);
        mSfgljl_tnb.setyDCF(response.YDCF);
        mSfgljl_tnb.setxCYDZC(response.XCYDZC);
        mSfgljl_tnb.setxCYDCD(response.XCYDCD);
        mSfgljl_tnb.setbCZSL(response.BCZSL);
        mSfgljl_tnb.setxCZSL(response.XCZSL);
        mSfgljl_tnb.setxLTZCD(response.XLTZCD);
        mSfgljl_tnb.setzYXWCD(response.ZYXWCD);
        mSfgljl_tnb.setkFXT(response.KFXT);
        mSfgljl_tnb.setjCSJ(response.JCSJ);
        mSfgljl_tnb.setxHDB(response.XHDB);
        mSfgljl_tnb.setqTJC(response.QTJC);
        mSfgljl_tnb.setcHXT(response.cHXT);
        mSfgljl_tnb.setqTXT(response.qTXT);

        List<FZJC> fzjcList = new ArrayList<Sfgljl_gxy.FZJC>();
        String fzjcString = response.QTJC;// 其他检查
        if (fzjcString != null) {
            String[] fzjcs = fzjcString.split("\\|");
            if (fzjcs != null && fzjcs.length > 0) {
                for (String f : fzjcs) {
                    String[] fItem = f.split(",");
                    if(fItem.length>=4){
                        FZJC fzjc = new FZJC();
                        fzjc.setjCXM(fItem[0]);
                        fzjc.setjCJG(fItem[1]);
                        fzjc.setjCR(fItem[2]);
                        fzjc.setjCRQ(fItem[3]);
                        fzjcList.add(fzjc);
                    }
                }
            }
        }

        mSfgljl_tnb.setfZJC(fzjcList);

        mSfgljl_tnb.setfYYCXCD(response.FYYCXCD);
        mSfgljl_tnb.setbLFY(response.BLFY);
        mSfgljl_tnb.setfYQK(response.FYQK);
        mSfgljl_tnb.setdXTFYCD(response.DXTFYCD);
        mSfgljl_tnb.setsFFLCD(response.SFFLCD);
        mSfgljl_tnb.setzZYY(response.ZZYY);
        mSfgljl_tnb.setzZKB(response.ZZKB);
        mSfgljl_tnb.setBz(response.BZ);

        mSfgljl_tnb.setReferralVisitDate(response.ReferralVisitDate);
        mSfgljl_tnb.setComplication(response.Complication);
        mSfgljl_tnb.setComorbidity(response.Comorbidity);
        mSfgljl_tnb.setOtherDiseases(response.OtherDiseases);

        mSfgljl_tnb.setMedicineUse(response.medicineUse);
        // 胰岛素
        mSfgljl_tnb.setInsulinUse(response.insulinUse);

        setValue(); // 显示到界面
    }

    // 获取最后一条糖尿病随访信息
    public void getLastValueFromDb(final OnGetSfbhListener onGetSfbhListener) {
        if (MyApplication.getInstance().getSession().getCurrentResident() == null) {
            mToast.setText("当前没有居民信息，请先登录！");
            mToast.show();
            return;
        }
        Login1 login1Result = MyApplication.getInstance().getSession().getLoginResult();
        if (login1Result == null || login1Result.response == null) {
            mToast.setText("当前没有医生登录，请先登录！");
            mToast.show();
            return;
        }
        // 在取最近一次的糖尿病和糖尿病随访记录
        List<Class<? extends IBean>> sfListBean = new ArrayList<Class<? extends IBean>>();
        sfListBean.add(Sfgljl_tnb.class);
        BeanUtil.getInstance().getLastSfglFromDb(sfListBean, new BeanUtil.OnResultFromDb() {

            @Override
            public void onResult(List<IBean> listBean, boolean isSucc) {
                if (isSucc) {
                    Sfgljl_tnb mSfgljl = null;
                    if (listBean.get(0) != null) {
                        Sfgljl_tnb sfgljl = (Sfgljl_tnb) listBean.get(0);
                        if (operateFlag == 1) {// 表示是新增操作
                            try {
                                Date visitDate = new SimpleDateFormat("yyyy-MM-dd").parse(sfgljl
                                        .getVisitDate());
                                // 因为每天只能新增一条数据，所以要判断是否是同一天
                                if (visitDate.getDay() == new Date().getDay()) {
                                    // 如果是同一天
                                    mToast.setText("每天只能有一次随访记录，新增失败，默认改为编辑模式");
                                    mToast.show();
                                    sfgljl.setVisitID(sfgljl.getVisitID());// 使用上一次的id，即为编辑模式
                                    sfgljl.setVisitDate(sfgljl.getVisitDate());
                                } else {
                                    sfgljl.setVisitID("");// ID为空，则为新增模式
                                    sfgljl.setVisitDate(new SimpleDateFormat("yyyy-MM-dd")
                                            .format(new Date()));
                                }
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        } else if (operateFlag == 2) {// 编辑模式
                            sfgljl.setVisitID(sfgljl.getVisitID());// 使用上一次的id，即为编辑模式
                            sfgljl.setVisitDate(sfgljl.getVisitDate());
                        }
                        mSfgljl = sfgljl;
                    } else {
                        Sfgljl_tnb sfgljl = new Sfgljl_tnb();
                        if (operateFlag == 2) {// 表示是编辑操作，但是没有记录，所以改为编辑操作
                            // 因为每天只能新增一条数据，所以要判断是否是同一天
                            mToast.setText("该居民没有随访糖尿病记录，默认改为新增模式！");
                            mToast.show();
                            sfgljl.setVisitID("");// ID为空，则为新增模式
                            sfgljl.setVisitDate(new SimpleDateFormat("yyyy-MM-dd")
                                    .format(new Date()));
                        }
                    }
                    beanMap.put(Sfgljl_tnb.class.getName(), mSfgljl);
                    onGetSfbhListener.onGetSfbh(mSfgljl.getVisitID());
                    setValue();
                }
            }
        });
    }

    /**
     * 
     */
    public void print() {
        Jmjbxx mJmjbxx = (Jmjbxx) beanMap.get(Jmjbxx.class.getName());
        Sfgljl_tnb mSfgljl = (Sfgljl_tnb) beanMap.get(Sfgljl_tnb.class.getName());
        PrinterUtil.printSfglTnb(mContext, mJmjbxx, mSfgljl);
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