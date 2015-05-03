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
import com.cking.phss.bean.Sfgljl_cjsf;
import com.cking.phss.bean.Sfgljl_jsb;
import com.cking.phss.bean.Sfgljl_lnsf;
import com.cking.phss.dto.IDto;
import com.cking.phss.dto.Login1;
import com.cking.phss.dto.sfgl.cjsf.BccjrsfjlHfda03;
import com.cking.phss.dto.sfgl.cjsf.DdcjrsfjlxxxxHfda04;
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

public class SfglCjsfPage extends MyPage implements ISfglPage {

    public GuidePager mGuidePager = null;
    private Map<String, IBean> beanMap = new HashMap<String, IBean>();
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
     * 残疾随访2个子页面
     * 
     * @param context
     */
    SfglCjsfPage01 mCjsfPage01 = null;
    SfglCjsfPage02 mCjsfPage02 = null;
    SfglCjsfPage03 mCjsfPage03 = null;

    public SfglCjsfPage(Context context) {
        super(context);
        beanMap.put(Jmjbxx.class.getName(), Global.jmjbxx);
        beanMap.put(Sfgljl_cjsf.class.getName(), Global.sfgljlCjsf);
        beanMap.put(Jktj_kstj.class.getName(), Global.jktjKstj);
        
        // init(context);
    }

    /**
     * @param context
     * @param attrs
     */
    public SfglCjsfPage(Context context, AttributeSet attrs) {
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
        sfglTjLayout.setDisType("0205");

//        getGxyBeanFromDB();

        // 添加页面
        mCjsfPage01 = new SfglCjsfPage01(context, beanMap);
        mPageList.add(mCjsfPage01);
        mCjsfPage02 = new SfglCjsfPage02(context, beanMap);
        mPageList.add(mCjsfPage02);
        mCjsfPage03 = new SfglCjsfPage03(context, beanMap);
        mPageList.add(mCjsfPage03);
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

    @Override
    public void setValue() { if (!hasInit) {return;}
        mCjsfPage01.setValue();
        mCjsfPage02.setValue();
        mCjsfPage03.setValue();
    }

    @Override
    public boolean getValue() { if (!hasInit) {return false;}
        return mCjsfPage01.getValue() && mCjsfPage02.getValue() && mCjsfPage03.getValue();
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
            mToast.setText("该居民可以进行残疾随访");
            mToast.show();
        } else {
            mToast.setText("该居民不可以进行残疾随访");
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
        Sfgljl_cjsf mSfgljl = (Sfgljl_cjsf) beanMap.get(Sfgljl_cjsf.class.getName());
        PrinterUtil.printSfglCjsf(mContext, mJmjbxx, mSfgljl);
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
        listBeans.add(beanMap.get(Sfgljl_cjsf.class.getName()));
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
                    mToast.setText("随访管理残疾随访存储成功！");
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
                            "48", dataSource, operType, new Date().getTime(),
                            Sfgljl_lnsf.class.getName());
                } else {
                    mToast.setText("随访管理残疾随访存储失败！");
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
            Sfgljl_cjsf sfgljl = (Sfgljl_cjsf) beanMap.get(Sfgljl_cjsf.class.getName());
            if (operateFlag == 0) {// 如果即没有做新增操作，也没有做修改操作，而是使用默认的方式
                // 从数据库中查询最后一次的随访时间和编号
                String residentUUID = MyApplication.getInstance().getSession().getCurrentResident()
                        .getResidentUUID();
                Sfgl sfgl = SfglBll.queryLast(Sfgljl_cjsf.class.getName(), residentUUID);
                if (sfgl != null) {
                    Sfgljl_cjsf sfgljl_gxyQuery = (Sfgljl_cjsf) XmlSerializerUtil.getInstance()
                            .beanFromXML(Sfgljl_cjsf.class, sfgl.getBean());
                    if (sfgljl_gxyQuery != null) {
                        if (sfgljl_gxyQuery.flupDate != null) {
                            if (!new SimpleDateFormat("yyyy-MM-dd").format(new Date()).equals(
                                    sfgljl_gxyQuery.flupDate)) {
                                // 不是同一天，那么默认使用编辑模式
                                sfgljl.flupID = sfgljl_gxyQuery.flupID;
                                // mCjsfPage01.mUserIDText.setText(sfgljl_gxyQuery.getVisitID());
                                onGetSfbhListener.onGetSfbh(sfgljl_gxyQuery.flupID);
                            }
                        }
                    }
                }
            }

            BccjrsfjlHfda03 requireData = new BccjrsfjlHfda03();
            requireData.request = new BccjrsfjlHfda03.Request();
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

            requireData.request.vulnerableGroup = sfgljl.vulnerableGroup;
            requireData.request.recureDemands = sfgljl.recureDemands;
            requireData.request.flupWay = sfgljl.flupWay;
            requireData.request.deformityCard = sfgljl.deformityCard;
            requireData.request.mainDisability = sfgljl.mainDisability;
            requireData.request.multiDisabilityFlag = sfgljl.multiDisabilityFlag;
            requireData.request.multiDisability = sfgljl.multiDisability;
            requireData.request.disabilityLevel = sfgljl.disabilityLevel;
            requireData.request.disabilityReason = sfgljl.disabilityReason;
            requireData.request.disabilityDate = sfgljl.disabilityDate;
            requireData.request.symptom = sfgljl.symptom;
            requireData.request.height = sfgljl.height;
            requireData.request.weight = sfgljl.weight;
            requireData.request.bodyMassIndex = sfgljl.bodyMassIndex;
            requireData.request.systolicPressure = sfgljl.systolicPressure;
            requireData.request.diastolicPressure = sfgljl.diastolicPressure;
            requireData.request.heartRate = sfgljl.heartRate;
            requireData.request.otherSigns = sfgljl.otherSigns;
            requireData.request.recureProject = sfgljl.recureProject;
            requireData.request.trainingFrequency = sfgljl.trainingFrequency;
            requireData.request.trainingDuration = sfgljl.trainingDuration;
            requireData.request.trainingPlace = sfgljl.trainingPlace;
            requireData.request.trainingTarget = sfgljl.trainingTarget;
            requireData.request.trainingEvaluation = sfgljl.trainingEvaluation;
            requireData.request.referralDirection = sfgljl.referralDirection;
            requireData.request.referralReason = sfgljl.referralReason;
            requireData.request.smoking = sfgljl.smoking;
            requireData.request.smokingDay = sfgljl.smokingDay;
            requireData.request.drinking = sfgljl.drinking;
            requireData.request.drinkingDay = sfgljl.drinkingDay;
            requireData.request.drinkingType = sfgljl.drinkingType;
            requireData.request.exercise = sfgljl.exercise;
            requireData.request.exerciseEvent = sfgljl.exerciseEvent;
            requireData.request.exerciseFrequency = sfgljl.exerciseFrequency;
            requireData.request.exerciseDuration = sfgljl.exerciseDuration;
            requireData.request.saltIntake = sfgljl.saltIntake;
            requireData.request.saltConclusion = sfgljl.saltConclusion;
            requireData.request.saltTarget = sfgljl.saltTarget;
            requireData.request.psyche = sfgljl.psyche;
            requireData.request.compliance = sfgljl.compliance;
            requireData.request.evaluation = sfgljl.evaluation;
            requireData.request.suggest = sfgljl.suggest;
            requireData.request.flupDate = sfgljl.flupDate;
            requireData.request.nextFlupDate = sfgljl.nextFlupDate;
            requireData.request.dutyDoctorID = sfgljl.dutyDoctorID;
            requireData.request.dutyDoctorName = sfgljl.dutyDoctorName;
            requireData.request.transferFlag = sfgljl.transferFlag;
            requireData.request.transferReason = sfgljl.transferReason;
            requireData.request.transferInstitution = sfgljl.transferInstitution;
            requireData.request.transferDepartment = sfgljl.transferDepartment;
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
                        BccjrsfjlHfda03 responseBean = (BccjrsfjlHfda03) listBean.get(0);
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
                                    // mCjsfPage01.mUserIDText.setText(responseBean.response.visitID
                                    // + "");
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

        DdcjrsfjlxxxxHfda04 requreData = new DdcjrsfjlxxxxHfda04();
        requreData.request = new DdcjrsfjlxxxxHfda04.Request();
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
                    DdcjrsfjlxxxxHfda04 result = (DdcjrsfjlxxxxHfda04) listBean.get(0);
                    if (result == null || result.response == null) {
                        mToast.setText("【得到随访记录】服务器接口异常");
                        mToast.show();
                    } else {
                        if (result.response.errMsg.length() > 0) {
                            mToast.setText("【得到随访记录】" + result.response.errMsg);
                            mToast.show();
                        } else {
                            try {
                                setLastCjsfjlxxxx(result.response, onGetSfbhListener);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        });
    }

    public void setLastCjsfjlxxxx(DdcjrsfjlxxxxHfda04.Response response,
            OnGetSfbhListener onGetSfbhListener) throws ParseException {
        Sfgljl_cjsf sfgljl = (Sfgljl_cjsf) beanMap.get(Sfgljl_cjsf.class.getName());

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
                mToast.setText("该居民没有随访残疾随访记录，默认改为新增模式！");
                mToast.show();
                sfgljl.flupID = "";// ID为空，则为新增模式
                sfgljl.flupDate = (new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
            }
        }
        onGetSfbhListener.onGetSfbh(sfgljl.flupID);
        // date不设置，使用新的date
        // // 操作用户ID
        //

        // 个人档案号
        sfgljl.residentID = response.residentID;
        // --个人姓名--
        sfgljl.residentName = response.residentName;

        sfgljl.vulnerableGroup = response.vulnerableGroup;
        sfgljl.recureDemands = response.recureDemands;
        sfgljl.flupWay = response.flupWay;
        sfgljl.deformityCard = response.deformityCard;
        sfgljl.mainDisability = response.mainDisability;
        sfgljl.multiDisabilityFlag = response.multiDisabilityFlag;
        sfgljl.multiDisability = response.multiDisability;
        sfgljl.disabilityLevel = response.disabilityLevel;
        sfgljl.disabilityReason = response.disabilityReason;
        sfgljl.disabilityDate = response.disabilityDate;
        sfgljl.symptom = response.symptom;
        sfgljl.height = response.height;
        sfgljl.weight = response.weight;
        sfgljl.bodyMassIndex = response.bodyMassIndex;
        sfgljl.systolicPressure = response.systolicPressure;
        sfgljl.diastolicPressure = response.diastolicPressure;
        sfgljl.heartRate = response.heartRate;
        sfgljl.otherSigns = response.otherSigns;
        sfgljl.recureProject = response.recureProject;
        sfgljl.trainingFrequency = response.trainingFrequency;
        sfgljl.trainingDuration = response.trainingDuration;
        sfgljl.trainingPlace = response.trainingPlace;
        sfgljl.trainingTarget = response.trainingTarget;
        sfgljl.trainingEvaluation = response.trainingEvaluation;
        sfgljl.referralDirection = response.referralDirection;
        sfgljl.referralReason = response.referralReason;
        sfgljl.smoking = response.smoking;
        sfgljl.smokingDay = response.smokingDay;
        sfgljl.drinking = response.drinking;
        sfgljl.drinkingDay = response.drinkingDay;
        sfgljl.drinkingType = response.drinkingType;
        sfgljl.exercise = response.exercise;
        sfgljl.exerciseEvent = response.exerciseEvent;
        sfgljl.exerciseFrequency = response.exerciseFrequency;
        sfgljl.exerciseDuration = response.exerciseDuration;
        sfgljl.saltIntake = response.saltIntake;
        sfgljl.saltConclusion = response.saltConclusion;
        sfgljl.saltTarget = response.saltTarget;
        sfgljl.psyche = response.psyche;
        sfgljl.compliance = response.compliance;
        sfgljl.evaluation = response.evaluation;
        sfgljl.suggest = response.suggest;
        sfgljl.flupDate = response.flupDate;
        sfgljl.nextFlupDate = response.nextFlupDate;
        sfgljl.dutyDoctorID = response.dutyDoctorID;
        sfgljl.dutyDoctorName = response.dutyDoctorName;
        sfgljl.transferFlag = response.transferFlag;
        sfgljl.transferReason = response.transferReason;
        sfgljl.transferInstitution = response.transferInstitution;
        sfgljl.transferDepartment = response.transferDepartment;
        // sfgljl.deviceUses = response.deviceUses;

        setValue(); // 显示到界面
    }

    // 获取最后一条残疾随访随访信息
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
        // 在取最近一次的残疾随访和残疾随访随访记录
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
                            mToast.setText("该居民没有随访残疾随访记录，默认改为新增模式！");
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
