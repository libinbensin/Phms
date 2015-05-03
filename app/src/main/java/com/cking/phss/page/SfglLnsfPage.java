package com.cking.phss.page;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
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
import com.cking.phss.bean.Sfgljl_jsb;
import com.cking.phss.bean.Sfgljl_lnsf;
import com.cking.phss.bean.Sfgljl_tnb;
import com.cking.phss.dto.IDto;
import com.cking.phss.dto.Login1;
import com.cking.phss.dto.sfgl.lnsf.BclnrsfjlHfe03;
import com.cking.phss.dto.sfgl.lnsf.DdlnrsfjlxxxxHfe04;
import com.cking.phss.global.Global;
import com.cking.phss.sqlite.ResidentBll;
import com.cking.phss.sqlite.Sfgl;
import com.cking.phss.sqlite.SfglBll;
import com.cking.application.MyApplication;
import com.cking.phss.util.PrinterUtil;
import com.cking.phss.util.ProgressDialogUtil;
import com.cking.phss.util.TispToastFactory;
import com.cking.phss.view.SfglBodyView.OnGetSfbhListener;
import com.cking.phss.widget.GuidePager;
import com.cking.phss.widget.SfglTjLayout;
import com.cking.phss.xml.util.XmlSerializerUtil;

public class SfglLnsfPage extends MyPage implements ISfglPage {

    public GuidePager mGuidePager = null;
    private Map<String, IBean> beanMap = new HashMap<String, IBean>();
    private Jmjbxx mJmjbxx = null;
    private Sfgljl_gxy sfgljl_gxy = null;
    private Sfgljl_tnb mSfgljl_tnb = null;
    private Context mContext = null;
    public int operateFlag = 1;// 操作的状态，0表示无操作，1表示新增操作，2表示编辑操作
    /**
     * 全局控件
     * 
     * @param context
     */
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
    private static final int MAX_PAGE_COUNT = 3;
    
    /**
     * 老年随访3个子页面
     * 
     * @param context
     */
    SfglLnsfPage01 mLnsfPage01 = null;
    SfglLnsfPage02 mLnsfPage02 = null;
    SfglLnsfPage03 mLnsfPage03 = null;

    public SfglLnsfPage(Context context) {
        super(context);
        beanMap.put(Jmjbxx.class.getName(), Global.jmjbxx);
        beanMap.put(Sfgljl_lnsf.class.getName(), Global.sfgljlLnsf);
        beanMap.put(Jktj_kstj.class.getName(), Global.jktjKstj);
        
        // init(context);
    }

    /**
     * @param context
     * @param attrs
     */
    public SfglLnsfPage(Context context, AttributeSet attrs) {
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
        sfglTjLayout.setDisType("0206");

//        getGxyBeanFromDB();
        
        // 添加页面
        mLnsfPage01 = new SfglLnsfPage01(context, beanMap);
        mPageList.add(mLnsfPage01);
        mLnsfPage02 = new SfglLnsfPage02(context, beanMap);
        mPageList.add(mLnsfPage02);
        mLnsfPage03 = new SfglLnsfPage03(context, beanMap);
        mPageList.add(mLnsfPage03);
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

    // 老年随访Pager数据
    class LnsfAdapter extends PagerAdapter {
        private Context c;
        private List<View> pList = new ArrayList<View>();

        /**
         * @param c
         */
        public LnsfAdapter(Context c) {
            super();
            this.c = c;
            mLnsfPage01 = new SfglLnsfPage01(c);
            pList.add(mLnsfPage01);
            mLnsfPage02 = new SfglLnsfPage02(c);
            pList.add(mLnsfPage02);
            mLnsfPage03 = new SfglLnsfPage03(c);
            pList.add(mLnsfPage03);
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return pList.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            // TODO Auto-generated method stub
            return (arg0 == arg1);
        }

        /*
         * (non-Javadoc)
         * 
         * @see
         * android.support.v4.view.PagerAdapter#destroyItem(android.view.View,
         * int, java.lang.Object)
         */
        @Override
        public void destroyItem(View container, int position, Object object) {
            // TODO Auto-generated method stub
            ((ViewPager) container).removeView(pList.get(position));
        }

        /*
         * (non-Javadoc)
         * 
         * @see
         * android.support.v4.view.PagerAdapter#instantiateItem(android.view
         * .View, int)
         */
        @Override
        public Object instantiateItem(View container, int position) {
            // TODO Auto-generated method stub
            View v = pList.get(position);
            ((ViewPager) container).addView(v);
            return v;
        }
    }

    @Override
    public void setValue() { if (!hasInit) {return;}
        mLnsfPage01.setValue();
        mLnsfPage02.setValue();
        mLnsfPage03.setValue();
    }

    @Override
    public boolean getValue() { if (!hasInit) {return false;}
        return mLnsfPage01.getValue() && mLnsfPage02.getValue() && mLnsfPage03.getValue();
    }

    private boolean isResidentInfoInDb() {
        if (MyApplication.getInstance().getSession().getCurrentResident() != null) {
            if (ResidentBll.query(MyApplication.getInstance().getSession().getCurrentResident()
                    .getPaperNum()) == null)
                return false;
            else {
                return true;
            }
        }
        return false;
    }

    /**
     * @param jbxxBodyGrxx
     */
    public void showItemByIndex(int index) {
        mGuidePager.showPage(index);
    }

    /* (non-Javadoc)
     * @see com.cking.phss.page.ISfglPage#onResultSflb(boolean)
     */
    @Override
    public void onResultSflb(boolean canDoSf) {
        if (canDoSf) {
            mToast.setText("该居民可以进行老年随访");
            mToast.show();
        } else {
            mToast.setText("该居民不可以进行老年随访");
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

    /**
     * 
     */
    public void print() {
        Jmjbxx mJmjbxx = (Jmjbxx) beanMap.get(Jmjbxx.class.getName());
        Sfgljl_lnsf mSfgljl = (Sfgljl_lnsf) beanMap.get(Sfgljl_lnsf.class.getName());
        PrinterUtil.printSfglLnsf(mContext, mJmjbxx, mSfgljl);
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
     * 
     */
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
        listBeans.add(beanMap.get(Sfgljl_lnsf.class.getName()));
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
                    mToast.setText("随访管理老年随访存储成功！");
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
                            "47", dataSource, operType, new Date().getTime(),
                            Sfgljl_lnsf.class.getName());
                } else {
                    mToast.setText("随访管理老年随访存储失败！");
                    mToast.show();
                }
            }
        });
    }

    /**
     * @param onGetSfbhListener
     */
    public void saveValueToWeb(final OnGetSfbhListener onGetSfbhListener) {
        Jmjbxx mJmjbxx = (Jmjbxx) beanMap.get(Jmjbxx.class.getName());
        if (mJmjbxx == null || mJmjbxx.getResidentID().equals("")) {
            mToast.setText(R.string.toast_info_none_resident);
            mToast.show();
            return;
        }
        String stringuserID = MyApplication.getInstance().getSession().getLoginResult().response.userID;
        if (getValue()) {
            final Sfgljl_lnsf sfgljl = (Sfgljl_lnsf) beanMap.get(Sfgljl_lnsf.class.getName());
            if (operateFlag == 0) {// 如果即没有做新增操作，也没有做修改操作，而是使用默认的方式
                // 从数据库中查询最后一次的随访时间和编号
                String residentUUID = MyApplication.getInstance().getSession().getCurrentResident()
                        .getResidentUUID();
                Sfgl sfgl = SfglBll.queryLast(Sfgljl_lnsf.class.getName(), residentUUID);
                if (sfgl != null) {
                    Sfgljl_lnsf sfgljl_gxyQuery = (Sfgljl_lnsf) XmlSerializerUtil.getInstance()
                            .beanFromXML(Sfgljl_lnsf.class, sfgl.getBean());
                    if (sfgljl_gxyQuery != null) {
                        if (sfgljl_gxyQuery.flupDate != null) {
                            if (!new SimpleDateFormat("yyyy-MM-dd").format(new Date()).equals(
                                    sfgljl_gxyQuery.flupDate)) {
                                // 不是同一天，那么默认使用编辑模式
                                sfgljl.flupID = sfgljl_gxyQuery.flupID;
                                // mLnsfPage01.mUserIDText.setText(sfgljl_gxyQuery.getVisitID());
                                onGetSfbhListener.onGetSfbh(sfgljl_gxyQuery.flupID);
                            }
                        }
                    }
                }
            }

            BclnrsfjlHfe03 requireData = new BclnrsfjlHfe03();
            requireData.request = new BclnrsfjlHfe03.Request();
            requireData.request.userID = stringuserID;
            mJmjbxx = (Jmjbxx) beanMap.get(Jmjbxx.class.getName());
            if (mJmjbxx != null) {
                requireData.request.residentID = mJmjbxx.getResidentID();
            }

            // 设置责任医生
            if (Global.doctorID != null && Global.doctorName != null) {
                sfgljl.dutyDoctorID = Global.doctorID;
                sfgljl.dutyDoctorName = Global.doctorName;
            }

            // 从数据库中读数据，拿到随访序列号，如果是在同一天，则使用同一个序列号，如果不是，那么使用""
            requireData.request.flupID = sfgljl.flupID;
            if (requireData.request.flupID.trim().equals("")) {
                requireData.request.type = 1;// 新增存盘
            } else {
                requireData.request.type = 2;// 编辑存盘
            }

            // --个人姓名--
            requireData.request.residentName = sfgljl.residentName;

            // <!-- 新症状, CD：代码
            requireData.request.newSymptom = sfgljl.newSymptom;

            // <!-- 原症状, CD：代码
            requireData.request.oldSymptom = sfgljl.oldSymptom;

            // <!--必填。随访方式 CD:代码（单选），值为代码：1.门诊 2.家庭 3.电话 4.集体 -->
            requireData.request.flupWay = sfgljl.flupWay;

            // <!-- 随访周期建议，CD:包括（无需、每2年、每年、每3个月、半年）的代码，值：文字值 -->
            requireData.request.cycle = sfgljl.cycle;

            // <!-- 随访性质，CD:包括（一般人群、重点管理疾病的高危人群、患者）的代码，值：文字值 -->
            requireData.request.nature = sfgljl.nature;

            // <!-- 体征 - 身高 -->
            requireData.request.height = sfgljl.height;
            // <!-- 体征 - 体重 -->
            requireData.request.weight = sfgljl.weight;
            // <!-- 体征 - 体质指数 -->
            requireData.request.bodyMassIndex = sfgljl.bodyMassIndex;
            // <!-- 体征 - 收缩压 -->
            requireData.request.systolicPressure = sfgljl.diastolicPressure;
            // <!-- 体征 - 舒张压 -->
            requireData.request.diastolicPressure = sfgljl.diastolicPressure;
            // <!-- 体征 - 心率 -->
            requireData.request.heartRate = sfgljl.heartRate;
            // <!-- 体征 - 其他体征 -->
            requireData.request.otherSigns = sfgljl.otherSigns;

            // <!-- 体征-腰围 -->
            requireData.request.waist = sfgljl.waist;

            // <!-- 体征-空腹血糖 -->
            requireData.request.fastingBloodGlucose = sfgljl.fastingBloodGlucose;

            // <!-- 体征-总胆固醇 -->
            requireData.request.cholesterol = sfgljl.cholesterol;

            // <!-- 体征-甘油三酯 -->
            requireData.request.triglyceride = sfgljl.triglyceride;

            // <!-- 体征-血清低密度脂蛋白胆固醇 -->
            requireData.request.lDensityLipoprotein = sfgljl.lDensityLipoprotein;

            // <!-- 体征-血清高密度脂蛋白胆固醇 -->
            requireData.request.hDensityLipoprotein = sfgljl.hDensityLipoprotein;

            // <!-- 生活方式指导 - 是否吸烟, CD：代码 ，1.是，2.否-->
            requireData.request.smoking = sfgljl.smoking;
            // <!-- 生活方式指导 - 日吸烟量（支） -->
            requireData.request.smokingDay = sfgljl.smokingDay;
            // <!-- 生活方式指导 - 是否饮酒, CD：代码 ，1.是，2.否-->
            requireData.request.drinking = sfgljl.drinking;
            // <!-- 生活方式指导 - 日饮酒量（两/次） -->
            requireData.request.drinkingDay = sfgljl.drinkingDay;
            // <!-- 生活方式指导 - 饮酒种类, CD：代码 ； 1.白酒，2.红酒，3.黄酒，4.米酒，99.其他-->
            requireData.request.drinkingType = sfgljl.exercise;
            // <!-- 生活方式指导 - 是否运动;1.是，2.否-->
            requireData.request.exercise = sfgljl.exercise;
            // <!-- 生活方式指导 - 运动项目, CD：代码
            // ；1.散步，2.慢跑，3.太极拳，4.气功，5.球类运动，6.跳舞，99.其他-->
            requireData.request.exerciseEvent = sfgljl.exerciseEvent;
            // <!-- 生活方式指导 - 运动频率 (次/周) -->
            requireData.request.exerciseFrequency = sfgljl.exerciseFrequency;
            // <!-- 生活方式指导 - 运动时长 (分钟/次) -->
            requireData.request.exerciseDuration = sfgljl.exerciseDuration;
            // <!-- 生活方式指导 - 摄盐量 -->
            requireData.request.saltIntake = sfgljl.saltIntake;
            // <!-- 生活方式指导 - 摄盐结论 -->
            requireData.request.saltConclusion = sfgljl.saltConclusion;
            // <!-- 生活方式指导 - 目标摄盐量 -->
            requireData.request.saltTarget = sfgljl.saltTarget;
            // <!-- 生活方式指导 - 心理调整, CD：代码 ；1.良好，2.一般，3.差-->
            requireData.request.psyche = sfgljl.compliance;
            // <!-- 生活方式指导 - 遵医行为；1.良好，2.一般，3.差-->
            requireData.request.compliance = sfgljl.compliance;
            // <!-- 心理状态，CD:包括（正常、紧张、抑郁、焦虑、其他） -->
            requireData.request.mentation = sfgljl.mentation;
            // <!-- 此次随访评估；1.满意，2.一般，3.不满意-->
            requireData.request.evaluation = sfgljl.evaluation;
            // <!-- 康复建议 -->
            requireData.request.suggest = sfgljl.flupDate;
            // <!-- 随访日期 -->
            requireData.request.flupDate = sfgljl.flupDate;
            // <!-- 下次随访日期 -->
            requireData.request.nextFlupDate = sfgljl.nextFlupDate;
            // <!-- 责任医生ID -->
            requireData.request.dutyDoctorID = sfgljl.transferFlag;
            // <!-- 责任医生姓名 -->
            requireData.request.dutyDoctorName = sfgljl.transferFlag;
            // <!-- 转诊标志 -->
            requireData.request.transferFlag = sfgljl.transferFlag;
            // <!-- 转诊原因 -->
            requireData.request.transferReason = sfgljl.transferReason;
            // <!-- 转入医疗机构名称 -->
            requireData.request.transferInstitution = sfgljl.transferInstitution;
            // <!-- 转入机构科室名称 -->
            requireData.request.transferDepartment = sfgljl.transferDepartment;
            // <!-- 数据来源, 有多条记录，因此会有多个DeviceUse节点-->
            // requireData.request.deviceUses = sfgljl.deviceUses;

            /**
             * 发送网络请求
             */
            List<IDto> beanList = new ArrayList<IDto>();
            beanList.add(requireData);
            BeanUtil.getInstance().getBeanFromWeb(beanList, new OnResultFromWeb() {
                @Override
                public void onResult(List<IDto> listBean, boolean isSucc) {
                    if (listBean != null && listBean.size() > 0) {
                        BclnrsfjlHfe03 responseBean = (BclnrsfjlHfe03) listBean.get(0);
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
                                    // mLnsfPage01.mUserIDText.setText(responseBean.response.visitID
                                    // + "");
                                    sfgljl.flupID = responseBean.response.flupID + "";
                                    onGetSfbhListener.onGetSfbh(responseBean.response.flupID + "");
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

    /**
     * @param onGetSfbhListener
     */
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

        DdlnrsfjlxxxxHfe04 requreData = new DdlnrsfjlxxxxHfe04();
        requreData.request = new DdlnrsfjlxxxxHfe04.Request();
        requreData.request.userID = login1Result.response.employeeNo.getiD();
        requreData.request.residentID = jmjbxx.getResidentID();
        requreData.request.flupID = "0";// 0表示请求最后一条数据

        List<IDto> beanList = new ArrayList<IDto>();
        beanList.add(requreData);
        ProgressDialogUtil.showProgressDialog(mContext, "正在查询", "请稍等...");
        BeanUtil.getInstance().getBeanFromWeb(beanList, new BeanUtil.OnResultFromWeb() {
            @Override
            public void onResult(List<IDto> listBean, boolean isSucc) {
                ProgressDialogUtil.hideProgressDialog();
                if (isSucc) {
                    StringBuilder sb = new StringBuilder();
                    DdlnrsfjlxxxxHfe04 result = (DdlnrsfjlxxxxHfe04) listBean.get(0);
                    if (result == null || result.response == null) {
                        mToast.setText("【得到随访记录】服务器接口异常");
                        mToast.show();
                    } else {
                        if (result.response.errMsg.length() > 0) {
                            mToast.setText("【得到随访记录】" + result.response.errMsg);
                            mToast.show();
                        } else {
                            try {
                                setLastLnsfjlxxxx(result.response, onGetSfbhListener);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        });
    }

    public void setLastLnsfjlxxxx(DdlnrsfjlxxxxHfe04.Response response,
            OnGetSfbhListener onGetSfbhListener) throws ParseException {
        Sfgljl_lnsf sfgljl = (Sfgljl_lnsf) beanMap.get(Sfgljl_lnsf.class.getName());

        String visitDate = response.flupDate;
        if (!visitDate.equals("")) {
            if (operateFlag == 1) {// 表示是新增操作
                // 因为每天只能新增一条数据，所以要判断是否是同一天
                if (new SimpleDateFormat("yyyy-MM-dd").parse(visitDate).getDay() == new Date()
                        .getDay()) {
                    // 如果是同一天
                    mToast.setText("每天只能有一次随访记录，新增失败，默认改为编辑模式");
                    mToast.show();
                    sfgljl.flupID = response.flupID;// 使用上一次的id，即为编辑模式
                    sfgljl.flupDate = response.flupDate;
                } else {
                    sfgljl.flupID = "";// ID为空，则为新增模式
                    sfgljl.flupDate = (new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
                }
            } else if (operateFlag == 2) {// 编辑模式
                sfgljl.flupID = response.flupID;// 使用上一次的id，即为编辑模式
                sfgljl.flupDate = response.flupDate;
            }
        } else {
            if (operateFlag == 2) {// 表示是编辑操作，但是没有记录，所以改为编辑操作
                // 因为每天只能新增一条数据，所以要判断是否是同一天
                mToast.setText("该居民没有随访老年随访记录，默认改为新增模式！");
                mToast.show();
                sfgljl.flupID = "";// ID为空，则为新增模式
                sfgljl.flupDate = (new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
            }
        }
        onGetSfbhListener.onGetSfbh(sfgljl.flupID);
        // date不设置，使用新的date
        // // 操作用户ID
        //
        // sfgljl.userID = Integer.parseInt(response.userID);

        // 个人档案号
        sfgljl.residentID = response.residentID;

        // --个人姓名--
        sfgljl.residentName = response.residentName;

        // <!-- 随访编号 -->
        sfgljl.flupID = response.flupID;

        // <!-- 新症状, CD：代码
        sfgljl.newSymptom = response.newSymptom;

        // <!-- 原症状, CD：代码
        sfgljl.oldSymptom = response.oldSymptom;

        // <!--必填。随访方式 CD:代码（单选），值为代码：1.门诊 2.家庭 3.电话 4.集体 -->
        sfgljl.flupWay = response.flupWay;

        // <!-- 随访周期建议，CD:包括（无需、每2年、每年、每3个月、半年）的代码，值：文字值 -->
        sfgljl.cycle = response.cycle;

        // <!-- 随访性质，CD:包括（一般人群、重点管理疾病的高危人群、患者）的代码，值：文字值 -->
        sfgljl.nature = response.nature;

        // <!-- 体征 - 身高 -->
        sfgljl.height = response.height;
        // <!-- 体征 - 体重 -->
        sfgljl.weight = response.weight;
        // <!-- 体征 - 体质指数 -->
        sfgljl.bodyMassIndex = response.bodyMassIndex;
        // <!-- 体征 - 收缩压 -->
        sfgljl.systolicPressure = response.diastolicPressure;
        // <!-- 体征 - 舒张压 -->
        sfgljl.diastolicPressure = response.diastolicPressure;
        // <!-- 体征 - 心率 -->
        sfgljl.heartRate = response.heartRate;
        // <!-- 体征 - 其他体征 -->
        sfgljl.otherSigns = response.otherSigns;

        // <!-- 体征-腰围 -->
        sfgljl.waist = response.waist;

        // <!-- 体征-空腹血糖 -->
        sfgljl.fastingBloodGlucose = response.fastingBloodGlucose;

        // <!-- 体征-总胆固醇 -->
        sfgljl.cholesterol = response.cholesterol;

        // <!-- 体征-甘油三酯 -->
        sfgljl.triglyceride = response.triglyceride;

        // <!-- 体征-血清低密度脂蛋白胆固醇 -->
        sfgljl.lDensityLipoprotein = response.lDensityLipoprotein;

        // <!-- 体征-血清高密度脂蛋白胆固醇 -->
        sfgljl.hDensityLipoprotein = response.hDensityLipoprotein;

        // <!-- 生活方式指导 - 是否吸烟, CD：代码 ，1.是，2.否-->
        sfgljl.smoking = response.smoking;
        // <!-- 生活方式指导 - 日吸烟量（支） -->
        sfgljl.smokingDay = response.smokingDay;
        // <!-- 生活方式指导 - 是否饮酒, CD：代码 ，1.是，2.否-->
        sfgljl.drinking = response.drinking;
        // <!-- 生活方式指导 - 日饮酒量（两/次） -->
        sfgljl.drinkingDay = response.drinkingDay;
        // <!-- 生活方式指导 - 饮酒种类, CD：代码 ； 1.白酒，2.红酒，3.黄酒，4.米酒，99.其他-->
        sfgljl.drinkingType = response.exercise;
        // <!-- 生活方式指导 - 是否运动;1.是，2.否-->
        sfgljl.exercise = response.exercise;
        // <!-- 生活方式指导 - 运动项目, CD：代码 ；1.散步，2.慢跑，3.太极拳，4.气功，5.球类运动，6.跳舞，99.其他-->
        sfgljl.exerciseEvent = response.exerciseEvent;
        // <!-- 生活方式指导 - 运动频率 (次/周) -->
        sfgljl.exerciseFrequency = response.exerciseFrequency;
        // <!-- 生活方式指导 - 运动时长 (分钟/次) -->
        sfgljl.exerciseDuration = response.exerciseDuration;
        // <!-- 生活方式指导 - 摄盐量 -->
        sfgljl.saltIntake = response.saltIntake;
        // <!-- 生活方式指导 - 摄盐结论 -->
        sfgljl.saltConclusion = response.saltConclusion;
        // <!-- 生活方式指导 - 目标摄盐量 -->
        sfgljl.saltTarget = response.saltTarget;
        // <!-- 生活方式指导 - 心理调整, CD：代码 ；1.良好，2.一般，3.差-->
        sfgljl.psyche = response.compliance;
        // <!-- 生活方式指导 - 遵医行为；1.良好，2.一般，3.差-->
        sfgljl.compliance = response.compliance;
        // <!-- 心理状态，CD:包括（正常、紧张、抑郁、焦虑、其他） -->
        sfgljl.mentation = response.mentation;
        // <!-- 此次随访评估；1.满意，2.一般，3.不满意-->
        sfgljl.evaluation = response.evaluation;
        // <!-- 康复建议 -->
        sfgljl.suggest = response.flupDate;
        // <!-- 随访日期 -->
        sfgljl.flupDate = response.flupDate;
        // <!-- 下次随访日期 -->
        sfgljl.nextFlupDate = response.nextFlupDate;
        // <!-- 责任医生ID -->
        sfgljl.dutyDoctorID = response.transferFlag;
        // <!-- 责任医生姓名 -->
        sfgljl.dutyDoctorName = response.transferFlag;
        // <!-- 转诊标志 -->
        sfgljl.transferFlag = response.transferFlag;
        // <!-- 转诊原因 -->
        sfgljl.transferReason = response.transferReason;
        // <!-- 转入医疗机构名称 -->
        sfgljl.transferInstitution = response.transferInstitution;
        // <!-- 转入机构科室名称 -->
        sfgljl.transferDepartment = response.transferDepartment;
        // <!-- 数据来源, 有多条记录，因此会有多个DeviceUse节点-->
        // sfgljl.deviceUses = response.deviceUses;

        setValue(); // 显示到界面
    }

    /**
     * @param onGetSfbhListener
     */

    // 获取最后一条老年随访随访信息
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
        // 在取最近一次的老年随访和老年随访随访记录
        List<Class<? extends IBean>> sfListBean = new ArrayList<Class<? extends IBean>>();
        sfListBean.add(Sfgljl_jsb.class);
        BeanUtil.getInstance().getLastSfglFromDb(sfListBean, new BeanUtil.OnResultFromDb() {

            @Override
            public void onResult(List<IBean> listBean, boolean isSucc) {
                if (isSucc) {
                    Sfgljl_jsb mSfgljl = null;
                    if (listBean.get(0) != null) {
                        Sfgljl_jsb sfgljl = (Sfgljl_jsb) listBean.get(0);
                        if (operateFlag == 1) {// 表示是新增操作
                            try {
                                Date visitDate = new SimpleDateFormat("yyyy-MM-dd")
                                        .parse(sfgljl.FlupDate);
                                // 因为每天只能新增一条数据，所以要判断是否是同一天
                                if (visitDate.getDay() == new Date().getDay()) {
                                    // 如果是同一天
                                    mToast.setText("每天只能有一次随访记录，新增失败，默认改为编辑模式");
                                    mToast.show();
                                } else {
                                    sfgljl.flupID = "";// ID为空，则为新增模式
                                    sfgljl.FlupDate = new SimpleDateFormat("yyyy-MM-dd")
                                            .format(new Date());
                                }
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        } else if (operateFlag == 2) {// 编辑模式
                        }
                        mSfgljl = sfgljl;
                    } else {
                        Sfgljl_jsb sfgljl = new Sfgljl_jsb();
                        if (operateFlag == 2) {// 表示是编辑操作，但是没有记录，所以改为编辑操作
                            // 因为每天只能新增一条数据，所以要判断是否是同一天
                            mToast.setText("该居民没有随访老年随访记录，默认改为新增模式！");
                            mToast.show();
                            sfgljl.flupID = "";// ID为空，则为新增模式
                            sfgljl.FlupDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
                        }
                    }
                    beanMap.put(Sfgljl_jsb.class.getName(), mSfgljl);
                    onGetSfbhListener.onGetSfbh(mSfgljl.flupID);
                    setValue();
                }
            }
        });
    }


}
