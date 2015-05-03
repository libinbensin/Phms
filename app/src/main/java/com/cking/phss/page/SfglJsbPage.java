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
import com.cking.phss.bean.Sfgljl_jsb;
import com.cking.phss.dto.IDto;
import com.cking.phss.dto.Login1;
import com.cking.phss.dto.sfgl.jsb.BcjsbsfjlHfm03;
import com.cking.phss.dto.sfgl.jsb.DdjsbsfjlxxxxHfm04;
import com.cking.phss.global.Global;
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

public class SfglJsbPage extends MyPage implements ISfglPage {

    public GuidePager mGuidePager = null;
    private Map<String, IBean> beanMap = new HashMap<String, IBean>();
    private Jmjbxx mJmjbxx = null;
    private Context mContext = null;
    public int checkReport = 1;// 检查是否进入报卡状态，1表示没有 ，2表示进入精神病报卡，3表示进入精神病报卡

    private Toast mToast = null;
    
    SfglTjLayout sfglTjLayout;
    LinearLayout layout_content;
    MyButton imageview_left_page;
    MyButton imageview_right_page;
    TextView textview_page;
    TextView textview_total_page;
    List<View> mPageList = new ArrayList<View>();
    int pageIndexFrom1 = 1;
    private static final int MAX_PAGE_COUNT = 4;
    
    private static int mTabRadioID = 0;

    /**
     * 精神病三个子页面
     * 
     * @param context
     */
    SfglJsbPage01 mJsbPage01 = null;
    SfglJsbPage02 mJsbPage02 = null;
    SfglJsbPage03 mJsbPage03 = null;
    SfglJsbPage04 mJsbPage04 = null;

    SfglJsbReportPage01 mJsbReportPage01 = null;
    SfglJsbReportPage02 mJsbReportPage02 = null;
    SfglJsbReportPage03 mJsbReportPage03 = null;
    SfglJsbReportPage04 mJsbReportPage04 = null;
    public int operateFlag = 1;// 操作的状态，0表示无操作，1表示新增操作，2表示编辑操作

    public SfglJsbPage(Context context) {
        super(context);
        beanMap.put(Jmjbxx.class.getName(), Global.jmjbxx);
        beanMap.put(Sfgljl_jsb.class.getName(), Global.sfgljlJsb);
        beanMap.put(Jktj_kstj.class.getName(), Global.jktjKstj);
        // init(context);
    }

    /**
     * @param context
     * @param attrs
     */
    public SfglJsbPage(Context context, AttributeSet attrs) {
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
        sfglTjLayout.setDisType("0105");

        // getJsbBeanFromDB();

        // 添加页面
		mJsbPage01 = new SfglJsbPage01(context, beanMap);
        mPageList.add(mJsbPage01);
		mJsbPage02 = new SfglJsbPage02(context, beanMap);
        mPageList.add(mJsbPage02);
		mJsbPage03 = new SfglJsbPage03(context, beanMap);
        mPageList.add(mJsbPage03);
		mJsbPage04 = new SfglJsbPage04(context, beanMap);
        mPageList.add(mJsbPage04);
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

    // 从数据库取出精神病的信息
    public void getJsbBeanFromDB() {
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

        // 在取最近一次的精神病随访记录
        /*
         * List<Class<? extends IBean>> gxyListBean = new ArrayList<Class<?
         * extends IBean>>(); gxyListBean.add(Sfgljl_jsb.class);
         * BeanUtil.getInstance().getLastSfglFromDb(gxyListBean, new
         * BeanUtil.OnResultFromDb() {
         * 
         * @Override public void onResult(List<IBean> listBean, boolean isSucc)
         * { if (isSucc) if (listBean.get(0) != null) {
         * beanMap.put(Sfgljl_jsb.class.getName(), listBean.get(0)); setValue();
         * }
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
    
    @Override
    public void setValue() { if (!hasInit) {return;}
		if (checkReport == 1) {
			mJsbPage01.setValue();
			mJsbPage02.setValue();
			mJsbPage03.setValue();
			mJsbPage04.setValue();
        } else if (checkReport == 2) {// 精神病报卡，进行精神病报卡的设置
            mJsbReportPage01.setValue();
            mJsbReportPage02.setValue();
            mJsbReportPage03.setValue();
            mJsbReportPage04.setValue();
		}

    }

    @Override
    public boolean getValue() { if (!hasInit) {return false;}
        return (mJsbPage01.getValue() && mJsbPage02.getValue() && mJsbPage03.getValue() && mJsbPage04
                .getValue());
    }

    /**
     * 随访管理精神病
     */
    private void saveJsbValueToWeb(final OnGetSfbhListener onGetSfbhListener) {
        Jmjbxx mJmjbxx = (Jmjbxx) beanMap.get(Jmjbxx.class.getName());
        if (mJmjbxx == null || mJmjbxx.getResidentID().equals("")) {
            mToast.setText(R.string.toast_info_none_resident);
            mToast.show();
            return;
        }
        String stringuserID = MyApplication.getInstance().getSession().getLoginResult().response.userID;
        if (getValue()) {
            final Sfgljl_jsb sfgljl = (Sfgljl_jsb) beanMap.get(Sfgljl_jsb.class.getName());
            if (operateFlag == 0) {// 如果即没有做新增操作，也没有做修改操作，而是使用默认的方式
                // 从数据库中查询最后一次的随访时间和编号
                String residentUUID = MyApplication.getInstance().getSession().getCurrentResident()
                        .getResidentUUID();
                Sfgl sfgl = SfglBll.queryLast(Sfgljl_jsb.class.getName(), residentUUID);
                if (sfgl != null) {
                    Sfgljl_jsb sfgljl_gxyQuery = (Sfgljl_jsb) XmlSerializerUtil.getInstance()
                            .beanFromXML(Sfgljl_jsb.class, sfgl.getBean());
                    if (sfgljl_gxyQuery != null) {
                        if (sfgljl_gxyQuery.FlupDate != null) {
                            if (!new SimpleDateFormat("yyyy-MM-dd").format(new Date()).equals(
                                    sfgljl_gxyQuery.FlupDate)) {
                                // 不是同一天，那么默认使用编辑模式
                                sfgljl.flupID = sfgljl_gxyQuery.flupID;
                                // mJsbPage01.mUserIDText.setText(sfgljl_gxyQuery.getVisitID());
                                onGetSfbhListener.onGetSfbh(sfgljl_gxyQuery.flupID);
                            }
                        }
                    }
                }
            }

            BcjsbsfjlHfm03 bcjsbsfjlHfm03 = new BcjsbsfjlHfm03();
            bcjsbsfjlHfm03.request = new BcjsbsfjlHfm03.Request();
            bcjsbsfjlHfm03.request.userID = stringuserID;
            mJmjbxx = (Jmjbxx) beanMap.get(Jmjbxx.class.getName());
            if (mJmjbxx != null) {
                bcjsbsfjlHfm03.request.residentID = mJmjbxx.getResidentID();
            }

            // 设置责任医生
            if (Global.doctorID != null && Global.doctorName != null) {
                sfgljl.DutyDoctorID = Global.doctorID;
                sfgljl.DutyDoctorName = Global.doctorName;
            }

            // 从数据库中读数据，拿到随访序列号，如果是在同一天，则使用同一个序列号，如果不是，那么使用""
            bcjsbsfjlHfm03.request.flupID = sfgljl.flupID;
            if (bcjsbsfjlHfm03.request.flupID.trim().equals("")) {
                bcjsbsfjlHfm03.request.type = 1;// 新增存盘
            } else {
                bcjsbsfjlHfm03.request.type = 2;// 编辑存盘
            }

            // // 操作用户ID
            // bcjsbsfjlHfm03.request.userID = sfgljl.userID;

            // // 个人档案号
            // bcjsbsfjlHfm03.request.residentID = sfgljl.residentID;

            // // --个人姓名--
            // bcjsbsfjlHfm03.request.residentName = sfgljl.residentName;

            // <!-- 随访编号 -->
            bcjsbsfjlHfm03.request.flupID = sfgljl.flupID;

            // <!-- 证件类型ID-->
            bcjsbsfjlHfm03.request.Credentials = sfgljl.Credentials;

            // <!-- 证件号 -->
            bcjsbsfjlHfm03.request.CredentialsNo = sfgljl.CredentialsNo;

            // <!-- 危险性级别, CD：代码 ，1.0级、2.1级、3.2级、4.3级、5.4级、6.5级-->
            bcjsbsfjlHfm03.request.Dangerous = sfgljl.Dangerous;

            // <!-- 目前症状, CD：代码
            // ；1.幻觉、2.交流困难、3.猜疑、4.喜怒无常、5.行为怪异、6.兴奋话多、7.伤人毁物、8.悲观厌世、9.无故外走、10.自语自笑、11.孤僻懒散、99.其他-->
            bcjsbsfjlHfm03.request.Symptoms = sfgljl.Symptoms;
            // <!-- 自知力, CD：代码 ；1.自知力完全、2.自知力不全、3.自知力缺失-->
            bcjsbsfjlHfm03.request.Insight = sfgljl.Insight;
            // <!-- 睡眠情况, CD：代码 ；1.良好、2.一般、3.较差-->
            bcjsbsfjlHfm03.request.Sleeping = sfgljl.Sleeping;
            // <!-- 饮食情况, CD：代码 ；1.良好、2.一般、3.较差-->
            bcjsbsfjlHfm03.request.Diet = sfgljl.Diet;
            // <!-- 个人生活料理, CD：代码 ；1.良好、2.一般、3.较差-->
            bcjsbsfjlHfm03.request.LifeCare = sfgljl.LifeCare;
            // <!-- 家务劳动, CD：代码 ；1.良好、2.一般、3.较差-->
            bcjsbsfjlHfm03.request.Housework = sfgljl.Housework;
            // <!-- 生产劳动及工作, CD：代码 ；1.良好、2.一般、3.较差、9.此项不适用-->
            bcjsbsfjlHfm03.request.ProductiveLabor = sfgljl.ProductiveLabor;
            // <!-- 学习能力, CD：代码 ；1.良好、2.一般、3.较差-->
            bcjsbsfjlHfm03.request.LearningAbility = sfgljl.LearningAbility;
            // <!-- 社会人际交往, CD：代码 ；1.良好、2.一般、3.较差-->
            bcjsbsfjlHfm03.request.Communication = sfgljl.Communication;
            // <!-- 有无对家庭社会的影响, CD：代码 ；1.无，2.有-->
            bcjsbsfjlHfm03.request.Influence = sfgljl.Influence;
            // <!-- 对家庭社会的影响 - 轻度滋事次数 -->
            bcjsbsfjlHfm03.request.MildTrouble = sfgljl.MildTrouble;
            // <!-- 对家庭社会的影响 - 肇事次数 -->
            bcjsbsfjlHfm03.request.Accident = sfgljl.Accident;
            // <!-- 对家庭社会的影响 - 肇祸次数 -->
            bcjsbsfjlHfm03.request.Trouble = sfgljl.Trouble;
            // <!-- 对家庭社会的影响 - 自伤次数 -->
            bcjsbsfjlHfm03.request.SelfWounding = sfgljl.SelfWounding;
            // <!-- 对家庭社会的影响 - 自杀未遂次数 -->
            bcjsbsfjlHfm03.request.AttemptedSuicide = sfgljl.AttemptedSuicide;
            // <!-- 对家庭社会的影响 - 其他 -->
            bcjsbsfjlHfm03.request.InfluenceOther = sfgljl.InfluenceOther;
            // <!-- 关锁情况, CD：代码 ；1.无关锁、2.关锁、3.关锁已解除-->
            bcjsbsfjlHfm03.request.LockUp = sfgljl.LockUp;
            // <!-- 住院情况, CD：代码 ；1.从未住院；2.目前正在住院；3.既往住院，4.现未住院-->
            bcjsbsfjlHfm03.request.Hospitalizations = sfgljl.Hospitalizations;
            // <!-- 末次出院时间 -->
            bcjsbsfjlHfm03.request.LastDischargeDate = sfgljl.LastDischargeDate;
            // <!-- 实验室检查有无, CD：代码 ；1.无，2.有-->
            bcjsbsfjlHfm03.request.LaboratoryTest = sfgljl.LaboratoryTest;
            // <!-- 实验室（辅助）检查，有多条数据，因此会有多个InspectAuxiliary节点 -->
            bcjsbsfjlHfm03.request.inspectAuxiliaries = sfgljl.inspectAuxiliaries;
            // <!-- 服药依从性, CD：代码 ；1.规律、2.间断、3.不服药-->
            bcjsbsfjlHfm03.request.DrugCompliance = sfgljl.DrugCompliance;
            // <!-- 药物不良反应有无, CD：代码 ；1.无，2.有-->
            bcjsbsfjlHfm03.request.AdverseReactions = sfgljl.AdverseReactions;
            // <!--有多条用药情况记录，因此会有多个MedicineUse节点 -->
            bcjsbsfjlHfm03.request.medicineUses = sfgljl.medicineUses;// 药物名称
            // <!-- 治疗效果, CD：代码 ；1.痊愈、2.好转、3.无变化、4.加重-->
            bcjsbsfjlHfm03.request.TreatmentEffect = sfgljl.TreatmentEffect;
            // <!-- 康复措施, CD：代码 ；1.生活劳动能力、2.职业训练、3.学习能力、4.社会交往、99.其他-->
            bcjsbsfjlHfm03.request.RehabilitationMeasure = sfgljl.RehabilitationMeasure;
            // <!-- 本次随访分类, CD：代码 ；1.不稳定、2.基本稳定、3.稳定、0.未访到-->
            bcjsbsfjlHfm03.request.Conclusion = sfgljl.Conclusion;
            // <!-- 随访日期 -->
            bcjsbsfjlHfm03.request.FlupDate = sfgljl.FlupDate;
            // <!-- 下次随访日期 -->
            bcjsbsfjlHfm03.request.NextFlupDate = sfgljl.NextFlupDate;
            // <!-- 转诊标志 -->
            bcjsbsfjlHfm03.request.TransferFlag = sfgljl.TransferFlag;
            // <!-- 转诊原因 -->
            bcjsbsfjlHfm03.request.TransferReason = sfgljl.TransferReason;
            // <!-- 转入医疗机构名称 -->
            bcjsbsfjlHfm03.request.TransferInstitution = sfgljl.TransferInstitution;
            // <!-- 转入机构科室名称 -->
            bcjsbsfjlHfm03.request.TransferDepartment = sfgljl.TransferDepartment;
            // <!-- 数据来源, 有多条记录，因此会有多个DeviceUse节点-->
            // bcjsbsfjlHfm03.request.deviceUses = sfgljl.DeviceUses;

            /**
             * 发送网络请求
             */
            List<IDto> beanList = new ArrayList<IDto>();
            beanList.add(bcjsbsfjlHfm03);
            BeanUtil.getInstance().getBeanFromWeb(beanList, new OnResultFromWeb() {
                @Override
                public void onResult(List<IDto> listBean, boolean isSucc) {
                    if (listBean != null && listBean.size() > 0) {
                        BcjsbsfjlHfm03 responseBean = (BcjsbsfjlHfm03) listBean.get(0);
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
                                    // mJsbPage01.mUserIDText.setText(responseBean.response.visitID
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
        listBeans.add(beanMap.get(Sfgljl_jsb.class.getName()));
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
                    mToast.setText("随访管理精神病存储成功！");
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
                            "44", dataSource, operType, new Date().getTime(),
                            Sfgljl_jsb.class.getName());
                } else {
                    mToast.setText("随访管理精神病存储失败！");
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
        saveJsbValueToWeb(onGetSfbhListener);
    }

    // 获取最后一条精神病随访信息
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

        DdjsbsfjlxxxxHfm04 ddjsbsfjlxxxxHfm04 = new DdjsbsfjlxxxxHfm04();
        ddjsbsfjlxxxxHfm04.request = new DdjsbsfjlxxxxHfm04.Request();
        ddjsbsfjlxxxxHfm04.request.userID = login1Result.response.employeeNo.getiD();
        ddjsbsfjlxxxxHfm04.request.residentID = jmjbxx.getResidentID();
        ddjsbsfjlxxxxHfm04.request.flupID = "0";// 0表示请求最后一条数据

        List<IDto> beanList = new ArrayList<IDto>();
        beanList.add(ddjsbsfjlxxxxHfm04);
        ProgressDialogUtil.showProgressDialog(mContext, "正在查询", "请稍等...");
        BeanUtil.getInstance().getBeanFromWeb(beanList, new BeanUtil.OnResultFromWeb() {
            @Override
            public void onResult(List<IDto> listBean, boolean isSucc) {
                ProgressDialogUtil.hideProgressDialog();
                if (isSucc) {
                    StringBuilder sb = new StringBuilder();
                    DdjsbsfjlxxxxHfm04 dgxysfjlxxxx15Result = (DdjsbsfjlxxxxHfm04) listBean.get(0);
                    if (dgxysfjlxxxx15Result == null || dgxysfjlxxxx15Result.response == null) {
                        mToast.setText("【得到随访记录】服务器接口异常");
                        mToast.show();
                    } else {
                        if (dgxysfjlxxxx15Result.response.errMsg.length() > 0) {
                            mToast.setText("【得到随访记录】" + dgxysfjlxxxx15Result.response.errMsg);
                            mToast.show();
                        } else {
                            try {
                                setLastJsbjlxxxx(dgxysfjlxxxx15Result.response, onGetSfbhListener);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        });
    }

    public void setLastJsbjlxxxx(DdjsbsfjlxxxxHfm04.Response response,
            OnGetSfbhListener onGetSfbhListener) throws ParseException {
        Sfgljl_jsb sfgljl = (Sfgljl_jsb) beanMap.get(Sfgljl_jsb.class.getName());

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
                    sfgljl.FlupDate = response.flupDate;
                } else {
                    sfgljl.flupID = "";// ID为空，则为新增模式
                    sfgljl.FlupDate = (new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
                }
            } else if (operateFlag == 2) {// 编辑模式
                sfgljl.flupID = response.flupID;// 使用上一次的id，即为编辑模式
                sfgljl.FlupDate = response.flupDate;
            }
        } else {
            if (operateFlag == 2) {// 表示是编辑操作，但是没有记录，所以改为编辑操作
                // 因为每天只能新增一条数据，所以要判断是否是同一天
                mToast.setText("该居民没有随访精神病记录，默认改为新增模式！");
                mToast.show();
                sfgljl.flupID = "";// ID为空，则为新增模式
                sfgljl.FlupDate = (new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
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

        // <!-- 证件类型ID-->
        sfgljl.Credentials = response.credentials;

        // <!-- 证件号 -->
        sfgljl.CredentialsNo = response.credentialsNo;

        // <!-- 危险性级别, CD：代码 ，1.0级、2.1级、3.2级、4.3级、5.4级、6.5级-->
        sfgljl.Dangerous = response.dangerous;

        // <!-- 目前症状, CD：代码
        // ；1.幻觉、2.交流困难、3.猜疑、4.喜怒无常、5.行为怪异、6.兴奋话多、7.伤人毁物、8.悲观厌世、9.无故外走、10.自语自笑、11.孤僻懒散、99.其他-->
        sfgljl.Symptoms = response.symptoms;
        // <!-- 自知力, CD：代码 ；1.自知力完全、2.自知力不全、3.自知力缺失-->
        sfgljl.Insight = response.insight;
        // <!-- 睡眠情况, CD：代码 ；1.良好、2.一般、3.较差-->
        sfgljl.Sleeping = response.sleeping;
        // <!-- 饮食情况, CD：代码 ；1.良好、2.一般、3.较差-->
        sfgljl.Diet = response.diet;
        // <!-- 个人生活料理, CD：代码 ；1.良好、2.一般、3.较差-->
        sfgljl.LifeCare = response.lifeCare;
        // <!-- 家务劳动, CD：代码 ；1.良好、2.一般、3.较差-->
        sfgljl.Housework = response.housework;
        // <!-- 生产劳动及工作, CD：代码 ；1.良好、2.一般、3.较差、9.此项不适用-->
        sfgljl.ProductiveLabor = response.productiveLabor;
        // <!-- 学习能力, CD：代码 ；1.良好、2.一般、3.较差-->
        sfgljl.LearningAbility = response.LearningAbility;
        // <!-- 社会人际交往, CD：代码 ；1.良好、2.一般、3.较差-->
        sfgljl.Communication = response.communication;
        // <!-- 有无对家庭社会的影响, CD：代码 ；1.无，2.有-->
        sfgljl.Influence = response.influence;
        // <!-- 对家庭社会的影响 - 轻度滋事次数 -->
        sfgljl.MildTrouble = response.mildTrouble;
        // <!-- 对家庭社会的影响 - 肇事次数 -->
        sfgljl.Accident = response.accident;
        // <!-- 对家庭社会的影响 - 肇祸次数 -->
        sfgljl.Trouble = response.trouble;
        // <!-- 对家庭社会的影响 - 自伤次数 -->
        sfgljl.SelfWounding = response.selfWounding;
        // <!-- 对家庭社会的影响 - 自杀未遂次数 -->
        sfgljl.AttemptedSuicide = response.attemptedSuicide;
        // <!-- 对家庭社会的影响 - 其他 -->
        sfgljl.InfluenceOther = response.influenceOther;
        // <!-- 关锁情况, CD：代码 ；1.无关锁、2.关锁、3.关锁已解除-->
        sfgljl.LockUp = response.lockUp;
        // <!-- 住院情况, CD：代码 ；1.从未住院；2.目前正在住院；3.既往住院，4.现未住院-->
        sfgljl.Hospitalizations = response.hospitalizations;
        // <!-- 末次出院时间 -->
        sfgljl.LastDischargeDate = response.lastDischargeDate;
        // <!-- 实验室检查有无, CD：代码 ；1.无，2.有-->
        sfgljl.LaboratoryTest = response.laboratoryTest;
        // <!-- 实验室（辅助）检查，有多条数据，因此会有多个InspectAuxiliary节点 -->
        sfgljl.inspectAuxiliaries = response.inspectAuxiliaries;
        // <!-- 服药依从性, CD：代码 ；1.规律、2.间断、3.不服药-->
        sfgljl.DrugCompliance = response.drugCompliance;
        // <!-- 药物不良反应有无, CD：代码 ；1.无，2.有-->
        sfgljl.AdverseReactions = response.adverseReactions;
        // <!--有多条用药情况记录，因此会有多个MedicineUse节点 -->
        sfgljl.medicineUses = response.medicineUses;// 药物名称
        // <!-- 治疗效果, CD：代码 ；1.痊愈、2.好转、3.无变化、4.加重-->
        sfgljl.TreatmentEffect = response.treatmentEffect;
        // <!-- 康复措施, CD：代码 ；1.生活劳动能力、2.职业训练、3.学习能力、4.社会交往、99.其他-->
        sfgljl.RehabilitationMeasure = response.rehabilitationMeasure;
        // <!-- 本次随访分类, CD：代码 ；1.不稳定、2.基本稳定、3.稳定、0.未访到-->
        sfgljl.Conclusion = response.conclusion;
        // <!-- 随访日期 -->
        sfgljl.FlupDate = response.flupDate;
        // <!-- 下次随访日期 -->
        sfgljl.NextFlupDate = response.nextFlupDate;
        // <!-- 责任医生ID -->
        sfgljl.DutyDoctorID = response.dutyDoctorID;
        // <!-- 责任医生姓名 -->
        sfgljl.DutyDoctorName = response.dutyDoctorName;
        // <!-- 转诊标志 -->
        sfgljl.TransferFlag = response.transferFlag;
        // <!-- 转诊原因 -->
        sfgljl.TransferReason = response.transferReason;
        // <!-- 转入医疗机构名称 -->
        sfgljl.TransferInstitution = response.transferInstitution;
        // <!-- 转入机构科室名称 -->
        sfgljl.TransferDepartment = response.transferDepartment;
        // <!-- 数据来源, 有多条记录，因此会有多个DeviceUse节点-->
        // sfgljl.DeviceUses = response.deviceUses;

        setValue(); // 显示到界面
    }

    /* (non-Javadoc)
     * @see com.cking.phss.page.ISfglPage#onResultSflb(boolean)
     */
    @Override
    public void onResultSflb(boolean canDoSf) {
        if (canDoSf) {
            mToast.setText("该居民可以进行精神病随访");
            mToast.show();
        } else {
            mToast.setText("该居民不可以进行精神病随访");
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
        Sfgljl_jsb mSfgljl = (Sfgljl_jsb) beanMap.get(Sfgljl_jsb.class.getName());
        PrinterUtil.printSfglJsb(mContext, mJmjbxx, mSfgljl);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.cking.phss.page.IPage#clear()
     */
    @Override
    public void clear() {
    }

    // 获取最后一条精神病随访信息
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
        // 在取最近一次的精神病和精神病随访记录
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
                            mToast.setText("该居民没有随访精神病记录，默认改为新增模式！");
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
