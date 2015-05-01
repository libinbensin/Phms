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
import com.cking.phss.dto.IDto;
import com.cking.phss.dto.Login1;
import com.cking.phss.dto.innner.MedicineUse;
import com.cking.phss.dto.sfgl.gxy.Bcgxyglk19;
import com.cking.phss.dto.sfgl.gxy.Bcgxysfjl16;
import com.cking.phss.dto.sfgl.gxy.Ddgxyglkxxxx18;
import com.cking.phss.dto.sfgl.gxy.Dgxysfjlxxxx15;
import com.cking.phss.global.Global;
import com.cking.phss.sqlite.Sfgl;
import com.cking.phss.sqlite.SfglBll;
import com.cking.phss.util.MyApplication;
import com.cking.phss.util.PrinterUtil;
import com.cking.phss.util.ProgressDialogUtil;
import com.cking.phss.util.TispToastFactory;
import com.cking.phss.view.SfglBodyView.OnGetSfbhListener;
import com.cking.phss.widget.SfglTjLayout;
import com.cking.phss.xml.util.XmlSerializerUtil;

public class SfglGxyPage extends MyPage implements ISfglPage {

//    public GuidePager mGuidePager = null;
    private Map<String, IBean> beanMap = new HashMap<String, IBean>();
    private Sfgljl_gxy sfgljl_gxy = null;
    private Context mContext = null;
    public int checkReport = 1;// 检查是否进入报卡状态，1表示没有 ，2表示进入高血压报卡，3表示进入糖尿病报卡
    /**
     * 全局控件
     * 
     * @param context
     */
//    public RadioGroup mTabRadios = null;
    private Toast mToast = null;

    private static int mTabRadioID = 0;
    
    SfglTjLayout sfglTjLayout; // 底部统计信息
    LinearLayout layout_content;
    MyButton imageview_left_page;
    MyButton imageview_right_page;
    TextView textview_page;
    TextView textview_total_page;
    List<View> mPageList = new ArrayList<View>();
    int pageIndexFrom1 = 1;
    private static final int MAX_PAGE_COUNT = 5;
    
    public int operateFlag = 1;// 操作的状态，0表示无操作，1表示新增操作，2表示编辑操作
    
    /**
     * 高血压三个子页面
     * 
     * @param context
     */
    SfglGxyPage01 mGxyPage01 = null;
    SfglGxyPage02 mGxyPage02 = null;
    SfglGxyPage03 mGxyPage03 = null;
    SfglGxyPage04 mGxyPage04 = null;
    SfglGxyPage05 mGxyPage05 = null;

    public SfglGxyPage(Context context) {
        super(context);
        beanMap.put(Jmjbxx.class.getName(), Global.jmjbxx);
        beanMap.put(Sfgljl_gxy.class.getName(), Global.sfgljlGxy);
        beanMap.put(Jktj_kstj.class.getName(), Global.jktjKstj);
        beanMap.put(Ddgxyglkxxxx18.class.getName(), new Ddgxyglkxxxx18());

        Bcgxyglk19 bcgxyglk19 = new Bcgxyglk19();
        bcgxyglk19.request = new Bcgxyglk19.Request();
        beanMap.put(Bcgxyglk19.class.getName(), bcgxyglk19);

        // init(context);
    }

    /**
     * @param context
     * @param attrs
     */
    public SfglGxyPage(Context context, AttributeSet attrs) {
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
        sfglTjLayout.setDisType("0101");

        // getGxyBeanFromDB();
        
        // 添加页面
        mGxyPage01 = new SfglGxyPage01(context, beanMap);
        mPageList.add(mGxyPage01);
        mGxyPage02 = new SfglGxyPage02(context, beanMap);
        mPageList.add(mGxyPage02);
        mGxyPage03 = new SfglGxyPage03(context, beanMap);
        mPageList.add(mGxyPage03);
        mGxyPage04 = new SfglGxyPage04(context, beanMap);
        mPageList.add(mGxyPage04);
        mGxyPage05 = new SfglGxyPage05(context, beanMap);
        mPageList.add(mGxyPage05);
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

    // 从数据库取出高血压的信息
    public void getGxyBeanFromDB() {
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
            }
        });

        // 在取最近一次的高血压随访记录
        /*
         * List<Class<? extends IBean>> gxyListBean = new ArrayList<Class<?
         * extends IBean>>(); gxyListBean.add(Sfgljl_gxy.class);
         * BeanUtil.getInstance().getLastSfglFromDb(gxyListBean, new
         * BeanUtil.OnResultFromDb() {
         * 
         * @Override public void onResult(List<IBean> listBean, boolean isSucc)
         * { if (isSucc) if (listBean.get(0) != null) {
         * gxyBeanMap.put(Sfgljl_gxy.class.getName(), listBean.get(0));
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
        // gxyBeanMap.put(Jktj_kstj.class.getName(), listBean.get(0));
        // }
        //
        // }
        // });
    }

    @Override
    public void setValue() { if (!hasInit) {return;}
        if (checkReport == 1) {
            mGxyPage01.setValue();
            mGxyPage02.setValue();
            mGxyPage03.setValue();
            mGxyPage04.setValue();
            mGxyPage05.setValue();
        }
    }

    @Override
    public boolean getValue() { if (!hasInit) {return false;}
        return (mGxyPage01.getValue() && mGxyPage02.getValue() && mGxyPage03.getValue()
                && mGxyPage04.getValue() && mGxyPage05.getValue());
    }

    /**
     * 随访管理高血压
     */
    private void saveGxyValueToWeb(final OnGetSfbhListener onGetSfbhListener) {
        Jmjbxx mJmjbxx = (Jmjbxx) beanMap.get(Jmjbxx.class.getName());
        if (mJmjbxx == null || mJmjbxx.getResidentID().equals("")) {
            mToast.setText(R.string.toast_info_none_resident);
            mToast.show();
            return;
        }
        String stringuserID = MyApplication.getInstance().getSession().getLoginResult().response.userID;
        if (getValue()) {
            sfgljl_gxy = (Sfgljl_gxy) beanMap.get(Sfgljl_gxy.class.getName());
            if (operateFlag == 0) {// 如果即没有做新增操作，也没有做修改操作，而是使用默认的方式
                // 从数据库中查询最后一次的随访时间和编号
                String residentUUID = MyApplication.getInstance().getSession().getCurrentResident()
                        .getResidentUUID();
                Sfgl sfgl = SfglBll.queryLast(Sfgljl_gxy.class.getName(), residentUUID);
                if (sfgl != null) {
                    Sfgljl_gxy sfgljl_gxyQuery = (Sfgljl_gxy) XmlSerializerUtil.getInstance()
                            .beanFromXML(Sfgljl_gxy.class, sfgl.getBean());
                    if (sfgljl_gxyQuery != null) {
                        if (sfgljl_gxyQuery.getVisitDate() != null) {
                            if (!new SimpleDateFormat("yyyy-MM-dd").format(new Date()).equals(
                                    sfgljl_gxyQuery.getVisitDate())) {
                                // 不是同一天，那么默认使用编辑模式
                                sfgljl_gxy.setVisitID(sfgljl_gxyQuery.getVisitID());
                                //mGxyPage01.mUserIDText.setText(sfgljl_gxyQuery.getVisitID());
                                onGetSfbhListener.onGetSfbh(sfgljl_gxyQuery.getVisitID());
                            }
                        }
                    }
                }
            }

            Bcgxysfjl16 bcgxysfjl16 = new Bcgxysfjl16();
            bcgxysfjl16.request = new Bcgxysfjl16.Request();
            bcgxysfjl16.request.userID = stringuserID;
            mJmjbxx = (Jmjbxx) beanMap.get(Jmjbxx.class.getName());
            if (mJmjbxx != null) {
                bcgxysfjl16.request.residentID = mJmjbxx.getResidentID();
            }

            // 设置随访医生
            if (Global.doctorID != null && Global.doctorName != null) {
                sfgljl_gxy.setDoctorID(Global.doctorID);
                sfgljl_gxy.setDoctorName(Global.doctorName);
            }

            // 从数据库中读数据，拿到随访序列号，如果是在同一天，则使用同一个序列号，如果不是，那么使用""
            bcgxysfjl16.request.visitID = sfgljl_gxy.getVisitID();
            if (bcgxysfjl16.request.visitID.trim().equals("")) {
                bcgxysfjl16.request.type = 1;// 新增存盘
            } else {
                bcgxysfjl16.request.type = 2;// 编辑存盘
            }
            bcgxysfjl16.request.visitDate = sfgljl_gxy.getVisitDate();
            bcgxysfjl16.request.doctorID = sfgljl_gxy.getDoctorID();
            bcgxysfjl16.request.doctorName = sfgljl_gxy.getDoctorName();
            bcgxysfjl16.request.dutyDoctorID = Global.doctorID;
            bcgxysfjl16.request.dutyDoctorName = Global.doctorName;
            bcgxysfjl16.request.sffscd = sfgljl_gxy.getsFFSCD();
            bcgxysfjl16.request.hBPTypeCD = sfgljl_gxy.gethBPTypeCD() + "";

            // bcgxysfjl16.request.sffscd = sfgljl_gxy.getSddsCD();
            bcgxysfjl16.request.xcsf = sfgljl_gxy.getxCSF();
            bcgxysfjl16.request.ZZCD = sfgljl_gxy.getZZCD();
            bcgxysfjl16.request.ZZQT = sfgljl_gxy.getZZQT();
            bcgxysfjl16.request.SBP = sfgljl_gxy.getsBP();
            bcgxysfjl16.request.DBP = sfgljl_gxy.getdBP();
            bcgxysfjl16.request.BCTZ = sfgljl_gxy.getbCTZ();

            bcgxysfjl16.request.BCSG = sfgljl_gxy.getBCSG();
            bcgxysfjl16.request.TZZS = sfgljl_gxy.gettZZS();
            bcgxysfjl16.request.BCXL = sfgljl_gxy.getbCXL();
            bcgxysfjl16.request.QTTZ = sfgljl_gxy.getqTTZ();
            bcgxysfjl16.request.XCTZ = sfgljl_gxy.getxCTZ();
            bcgxysfjl16.request.XCXL = sfgljl_gxy.getxCXL();
            bcgxysfjl16.request.BCXYL = sfgljl_gxy.getbCXYL();
            bcgxysfjl16.request.XCXY = sfgljl_gxy.getxCXY();
            bcgxysfjl16.request.BCYJ = sfgljl_gxy.getbCYJ();
            bcgxysfjl16.request.XCYJ = sfgljl_gxy.getxCYJ();
            bcgxysfjl16.request.YDZC = sfgljl_gxy.getyDZC();
            bcgxysfjl16.request.YDCF = sfgljl_gxy.getyDCF();

            bcgxysfjl16.request.XCYDZC = sfgljl_gxy.getxCYDZC();
            bcgxysfjl16.request.XCYDCD = sfgljl_gxy.getxCYDCD();
            bcgxysfjl16.request.BCSYL = sfgljl_gxy.getbCSYL();
            bcgxysfjl16.request.XCSYL = sfgljl_gxy.getxCSYL();
            bcgxysfjl16.request.XLTZCD = sfgljl_gxy.getxLTZCD();
            bcgxysfjl16.request.ZYXWCD = sfgljl_gxy.getzYXWCD();
            
            bcgxysfjl16.request.referralVisitDate = sfgljl_gxy.getReferralVisitDate();
            if (sfgljl_gxy.getCriticalOrgan() != null) {
                bcgxysfjl16.request.criticalOrgan = sfgljl_gxy.getCriticalOrgan();
            }
            if (sfgljl_gxy.getComorbidity() != null) {
                bcgxysfjl16.request.comorbidity = sfgljl_gxy.getComorbidity();
            }
            bcgxysfjl16.request.otherDiseases = sfgljl_gxy.getOtherDiseases();
            /**
             * 辅助检查部分
             */
            // if (sfgljl_gxy.getfZJC() != null) {
            // ArrayList<Bcgxysfjl16.Request.FZJC> fzjclLists = new
            // ArrayList<Bcgxysfjl16.Request.FZJC>();
            // List<FZJC> fromBean = sfgljl_gxy.getfZJC();
            // for (FZJC fzjc : fromBean) {
            // Bcgxysfjl16.Request.FZJC f = new Bcgxysfjl16.Request.FZJC();
            // f.jcxm = fzjc.getjCXM();
            // f.jcjg = fzjc.getjCJG();
            // f.jcr = fzjc.getjCR();
            // f.jcrq = fzjc.getjCRQ();
            // fzjclLists.add(f);
            // }
            // }
            String fzjcString = "";
            if (sfgljl_gxy.getfZJC() != null) {
                List<FZJC> fromBean = sfgljl_gxy.getfZJC();
                for (FZJC fzjc : fromBean) {
                    fzjcString += (fzjc.getjCXM() + ",");
                    fzjcString += (fzjc.getjCJG() + ",");
                    fzjcString += (fzjc.getjCR() + ",");
                    fzjcString += (fzjc.getjCRQ() + "|");
                }
            }
            bcgxysfjl16.request.FZJC = fzjcString;

            bcgxysfjl16.request.FYYCXCD = sfgljl_gxy.getfYYCXCD();
            bcgxysfjl16.request.BLFY = sfgljl_gxy.getbLFY();
            bcgxysfjl16.request.FYQK = sfgljl_gxy.getfYQK();
            bcgxysfjl16.request.SFFLCD = sfgljl_gxy.getsFFLCD();
            bcgxysfjl16.request.ZZYY = sfgljl_gxy.getzZYY();
            bcgxysfjl16.request.ZZKB = sfgljl_gxy.getzZKB();
            bcgxysfjl16.request.recommendation = sfgljl_gxy.recommendation;
            bcgxysfjl16.request.BZ = sfgljl_gxy.getBz();

            if (sfgljl_gxy.getMedicineUse() != null) {
                bcgxysfjl16.request.medicineUse = (ArrayList<MedicineUse>) sfgljl_gxy.getMedicineUse();
            }
            /**
             * 发送网络请求
             */
            List<IDto> beanList = new ArrayList<IDto>();
            beanList.add(bcgxysfjl16);
            BeanUtil.getInstance().getBeanFromWeb(beanList, new OnResultFromWeb() {
                @Override
                public void onResult(List<IDto> listBean, boolean isSucc) {
                    if (listBean != null && listBean.size() > 0) {
                        Bcgxysfjl16 responseBean = (Bcgxysfjl16) listBean.get(0);
                        if (isSucc) {
                            if (responseBean != null && responseBean.response == null) {
                                mToast.setText("网络异常");
                                mToast.show();
                            } else {
                                if (responseBean.response.errMsg != null
                                        && responseBean.response.errMsg.length() > 0) {
                                    mToast.setText(responseBean.response.errMsg);
                                    mToast.show();
                                    return;
                                } else {
//                                    mGxyPage01.mUserIDText.setText(responseBean.response.visitID
//                                            + "");
                                    sfgljl_gxy.setVisitID(responseBean.response.visitID + "");
                                    onGetSfbhListener.onGetSfbh(responseBean.response.visitID + "");
                                    mToast.setText("数据上传成功");
                                    mToast.show();
                                    // 数据上传成功，得到随访序列号，保存到数据库中
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
            // mToast.setText("从界面取数据出错！");
            // mToast.show();
            return;
        }
        Jmjbxx mJmjbxx = (Jmjbxx) beanMap.get(Jmjbxx.class.getName());
        if (mJmjbxx == null || mJmjbxx.getResidentID().equals("")) {
            mToast.setText(R.string.toast_info_none_resident);
            mToast.show();
            return;
        }
        List<IBean> listBeans = new ArrayList<IBean>();
        listBeans.add(beanMap.get(Sfgljl_gxy.class.getName()));
        long operTime = -1;
        if (operateFlag == 1) { // 新增
            operTime = new Date().getTime();
        } else if (operateFlag == 2) { // 修改
            operTime = -1;
        }
        BeanUtil.getInstance().saveSfglToDb(listBeans, operTime, new BeanUtil.OnResultSaveToDb() {
            @Override
            public void onResult(List<SaveToDbResult> listBean, boolean isSucc) {
                if (isSucc) {
                    mToast.setText("随访管理高血压存储成功！");
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
                    Jmjbxx jmjbxx = (Jmjbxx) beanMap.get(Jmjbxx.class.getName());
                    BeanUtil.getInstance().saveLocalRecord(mContext, jmjbxx, result.projectUUID,
                            "41", dataSource, operType, new Date().getTime(),
                            Sfgljl_gxy.class.getName());
                } else {
                    mToast.setText("随访管理高血压存储失败！");
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
        saveGxyValueToWeb(onGetSfbhListener);
    }

    // 获取最后一条高血压随访信息
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

        Dgxysfjlxxxx15 dgxysfjlxxxx15 = new Dgxysfjlxxxx15();
        dgxysfjlxxxx15.request = new Dgxysfjlxxxx15.Request();
        dgxysfjlxxxx15.request.employeeNo = login1Result.response.employeeNo;
        dgxysfjlxxxx15.request.residentID = jmjbxx.getResidentID();
        dgxysfjlxxxx15.request.visitID = "0";// 0表示请求最后一条数据

        List<IDto> beanList = new ArrayList<IDto>();
        beanList.add(dgxysfjlxxxx15);
        ProgressDialogUtil.showProgressDialog(mContext, "正在查询", "请稍等...");
        BeanUtil.getInstance().getBeanFromWeb(beanList, new BeanUtil.OnResultFromWeb() {
            @Override
            public void onResult(List<IDto> listBean, boolean isSucc) {
                ProgressDialogUtil.hideProgressDialog();
                if (isSucc) {
                    StringBuilder sb = new StringBuilder();
                    Dgxysfjlxxxx15 dgxysfjlxxxx15Result = (Dgxysfjlxxxx15) listBean.get(0);
                    if (dgxysfjlxxxx15Result == null || dgxysfjlxxxx15Result.response == null) {
                       mToast.setText("【得到随访记录】服务器接口异常");
                        mToast.show();
                    } else {
                        if (dgxysfjlxxxx15Result.response.errMsg.length() > 0) {
                            mToast.setText("【得到随访记录】"+dgxysfjlxxxx15Result.response.errMsg);
                            mToast.show();
                        } else {
                            try {
                                setLastGxyjlxxxx(dgxysfjlxxxx15Result.response, onGetSfbhListener);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        });
    }

    public void setLastGxyjlxxxx(Dgxysfjlxxxx15.Response response,
            OnGetSfbhListener onGetSfbhListener) throws ParseException {
        Sfgljl_gxy mSfgljl_gxy = (Sfgljl_gxy) beanMap.get(Sfgljl_gxy.class.getName());

        String visitDate = response.visitDate;
        if(!visitDate.equals("")){
            if (operateFlag == 1) {// 表示是新增操作
                // 因为每天只能新增一条数据，所以要判断是否是同一天
                if (new SimpleDateFormat("yyyy-MM-dd").parse(visitDate).getDay() == new Date().getDay()) {
                    // 如果是同一天
                    mToast.setText("每天只能有一次随访记录，新增失败，默认改为编辑模式");
                    mToast.show();
                    mSfgljl_gxy.setVisitID(response.visitID);// 使用上一次的id，即为编辑模式
                    mSfgljl_gxy.setVisitDate(response.visitDate);
                } else {
                    mSfgljl_gxy.setVisitID("");// ID为空，则为新增模式
                    mSfgljl_gxy.setVisitDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
                }
            } else if (operateFlag == 2) {// 编辑模式
                mSfgljl_gxy.setVisitID(response.visitID);// 使用上一次的id，即为编辑模式
                mSfgljl_gxy.setVisitDate(response.visitDate);
            }
        }else {
            if (operateFlag == 2) {// 表示是编辑操作，但是没有记录，所以改为编辑操作
                // 因为每天只能新增一条数据，所以要判断是否是同一天
                mToast.setText("该居民没有随访高血压记录，默认改为新增模式！");
                mToast.show();
                mSfgljl_gxy.setVisitID("");// ID为空，则为新增模式
                mSfgljl_gxy.setVisitDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
            }
        }
        onGetSfbhListener.onGetSfbh(mSfgljl_gxy.getVisitID());
        // date不设置，使用新的date
        mSfgljl_gxy.setDutyDoctor(response.dutyDoctorName);
        mSfgljl_gxy.setsFFSCD(response.SFFSCD);
        try {
            mSfgljl_gxy.sethBPTypeCD(Integer.parseInt(response.hBPTypeCD));
        } catch (NumberFormatException e) {

        }
        mSfgljl_gxy.setxCSF(response.XCSF);
        mSfgljl_gxy.setZZCD(response.ZZCD);
        mSfgljl_gxy.setZZQT(response.ZZQT);
        mSfgljl_gxy.setsBP(response.SBP);
        mSfgljl_gxy.setdBP(response.DBP);
        mSfgljl_gxy.setbCTZ(response.BCTZ);
        mSfgljl_gxy.setBCSG(response.BCSG);
        mSfgljl_gxy.settZZS(response.TZZS);
        mSfgljl_gxy.setXCZS(response.xczs);
        mSfgljl_gxy.setbCXL(response.BCXL);
        mSfgljl_gxy.setqTTZ(response.QTTZ);
        mSfgljl_gxy.setxCTZ(response.XCTZ);
        mSfgljl_gxy.setxCXL(response.XCXL);
        mSfgljl_gxy.setbCXYL(response.BCXYL);
        mSfgljl_gxy.setxCXY(response.XCXY);
        mSfgljl_gxy.setbCYJ(response.BCYJ);
        mSfgljl_gxy.setxCYJ(response.XCYJ);
        mSfgljl_gxy.setyDZC(response.YDZC);
        mSfgljl_gxy.setyDCF(response.YDCF);
        mSfgljl_gxy.setxCYDZC(response.XCYDZC);
        mSfgljl_gxy.setxCYDCD(response.XCYDCD);
        mSfgljl_gxy.setbCSYL(response.BCSYL);
        mSfgljl_gxy.setxCSYL(response.XCSYL);
        mSfgljl_gxy.setxLTZCD(response.XLTZCD);
        mSfgljl_gxy.setzYXWCD(response.ZYXWCD);
        mSfgljl_gxy.setCriticalOrgan(response.criticalOrgan);
        mSfgljl_gxy.setComorbidity(response.comorbidity);
        mSfgljl_gxy.setOtherDiseases(response.otherDiseases);
        mSfgljl_gxy.recommendation = response.recommendation;

        List<FZJC> fzjcList = new ArrayList<Sfgljl_gxy.FZJC>();
        String fzjcString = response.FZJC;
        if (fzjcString != null&&!fzjcString.equals("")) {
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
        mSfgljl_gxy.setfZJC(fzjcList);

        mSfgljl_gxy.setfYYCXCD(response.FYYCXCD);
        mSfgljl_gxy.setbLFY(response.BLFY);
        mSfgljl_gxy.setfYQK(response.FYQK);
        mSfgljl_gxy.setsFFLCD(response.SFFLCD);
        mSfgljl_gxy.setzZYY(response.ZZYY);
        mSfgljl_gxy.setzZKB(response.ZZKB);
        mSfgljl_gxy.setBz(response.BZ);

        List<MedicineUse> medicineUses = new ArrayList<MedicineUse>();
        if (response.medicineUse != null) {
            for (MedicineUse medicineUse : response.medicineUse) {
                if(medicineUse==null){
                    continue;
                }
                medicineUses.add(medicineUse);
            }
        }
        mSfgljl_gxy.setMedicineUse(medicineUses);
        
        setValue(); // 显示到界面
    }

    // 获取最后一条高血压随访信息
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
        // 在取最近一次的高血压和糖尿病随访记录
        List<Class<? extends IBean>> sfListBean = new ArrayList<Class<? extends IBean>>();
        sfListBean.add(Sfgljl_gxy.class);
        BeanUtil.getInstance().getLastSfglFromDb(sfListBean, new BeanUtil.OnResultFromDb() {

            @Override
            public void onResult(List<IBean> listBean, boolean isSucc) {
                if (isSucc) {
                    Sfgljl_gxy mSfgljl = null;
                    if (listBean.get(0) != null) {
                        Sfgljl_gxy sfgljl = (Sfgljl_gxy) listBean.get(0);
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
                        Sfgljl_gxy sfgljl = new Sfgljl_gxy();
                        if (operateFlag == 2) {// 表示是编辑操作，但是没有记录，所以改为编辑操作
                            // 因为每天只能新增一条数据，所以要判断是否是同一天
                            mToast.setText("该居民没有随访高血压记录，默认改为新增模式！");
                            mToast.show();
                            sfgljl.setVisitID("");// ID为空，则为新增模式
                            sfgljl.setVisitDate(new SimpleDateFormat("yyyy-MM-dd")
                                    .format(new Date()));
                        }
                        mSfgljl = sfgljl;
                    }
                    beanMap.put(Sfgljl_gxy.class.getName(), mSfgljl);
                    onGetSfbhListener.onGetSfbh(mSfgljl.getVisitID());
                    setValue();
                }
            }
        });
    }

    /* (non-Javadoc)
     * @see com.cking.phss.page.ISfglPage#onResultSflb(boolean)
     */
    @Override
    public void onResultSflb(boolean canDoSf) {if (canDoSf) {
        mToast.setText("该居民可以进行高血压随访");
        mToast.show();
    } else {
        mToast.setText("该居民不可以进行高血压随访");
        mToast.show();
    }
    }

    /* (non-Javadoc)
     * @see com.cking.phss.page.ISfglPage#onResultSftj(int, int, int)
     */
    @Override
    public void onResultSftj(String should, String done, String notdo) {
        sfglTjLayout.setFootTipsText(should, done, notdo);
    }

    /**
     * 
     */
    public void print() {
        Jmjbxx mJmjbxx = (Jmjbxx) beanMap.get(Jmjbxx.class.getName());
        Sfgljl_gxy mSfgljl_gxy = (Sfgljl_gxy) beanMap.get(Sfgljl_gxy.class.getName());
        PrinterUtil.printSfglGxy(mContext, mJmjbxx, mSfgljl_gxy);
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
