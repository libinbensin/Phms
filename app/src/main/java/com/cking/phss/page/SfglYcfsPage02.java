package com.cking.phss.page;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.xinhuaxing.beans.BeanCD;
import net.xinhuaxing.beans.XmlTag;
import net.xinhuaxing.type.HashMap2;
import net.xinhuaxing.util.GlobalUtil;
import net.xinhuaxing.util.GlobalUtil.Supplement;
import net.xinhuaxing.util.ResourcesFactory;
import net.xinhuaxing.util.StringUtil;
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
import com.cking.phss.bean.BeanUtil.OnResultFromWeb2;
import com.cking.phss.bean.BeanUtil.OnResultSaveToDb;
import com.cking.phss.bean.BeanUtil.SaveToDbResult;
import com.cking.phss.bean.IBean;
import com.cking.phss.bean.Jktj_kstj;
import com.cking.phss.bean.Jmjbxx;
import com.cking.phss.bean.Sfgljl_gxy;
import com.cking.phss.dto.Login1;
import com.cking.phss.global.Global;
import com.cking.phss.sqlite.Sfgl;
import com.cking.phss.sqlite.SfglBll;
import com.cking.application.MyApplication;
import com.cking.phss.util.PrinterUtil;
import com.cking.phss.util.TispToastFactory;
import com.cking.phss.view.SfglBodyView.OnGetSfbhListener;
import com.cking.phss.widget.SfglTjLayout;

public class SfglYcfsPage02 extends MyPage implements ISfglPage {

//    public GuidePager mGuidePager = null;
    private Map<String, IBean> beanMap = new HashMap<String, IBean>();
    private Context mContext = null;
    public int checkReport = 1;// 检查是否进入报卡状态，1表示没有 ，2表示进入脑卒中报卡，3表示进入糖尿病报卡
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
    private static final int MAX_PAGE_COUNT = 4;
    
    public int operateFlag = 1;// 操作的状态，0表示无操作，1表示新增操作，2表示编辑操作
    
    /**
     * 脑卒中三个子页面
     * 
     * @param context
     */
    SfglYcfsPage0201 page01 = null;
    SfglYcfsPage0202 page02 = null;
    SfglYcfsPage0203 page03 = null;
    SfglYcfsPage0204 page04 = null;

    public SfglYcfsPage02(Context context) {
        super(context);
        beanMap.put(Jmjbxx.class.getName(), Global.jmjbxx);
        beanMap.put(Sfgljl_gxy.class.getName(), Global.sfgljlGxy);
        beanMap.put(Jktj_kstj.class.getName(), Global.jktjKstj);

        // init(context);
    }

    /**
     * @param context
     * @param attrs
     */
    public SfglYcfsPage02(Context context, AttributeSet attrs) {
        super(context, attrs);
        // init(context);
    }

    protected void init(final Context context) {
        mContext = context;
        mToast = TispToastFactory.getToast(context, "");
        LayoutInflater.from(context).inflate(R.layout.fragment_sfgl_common_layout2, this);
        
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
        sfglTjLayout.setDisType("0203");

        // getGxyBeanFromDB();
        
        // 添加页面
        page01 = new SfglYcfsPage0201(context, beanMap);
        mPageList.add(page01);
        page02 = new SfglYcfsPage0202(context, beanMap);
        mPageList.add(page02);
        page03 = new SfglYcfsPage0203(context, beanMap);
        mPageList.add(page03);
        page04 = new SfglYcfsPage0204(context, beanMap);
        mPageList.add(page04);
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

    // 从数据库取出脑卒中的信息
    public void getNzzBeanFromDB() {
        if (beanMap == null) {// 如果为null，表示出错了，返回
            return;
        }

        // 首先取到Jmjbxx，放入map中
        List<Class<? extends IBean>> jbxxListBean = new ArrayList<Class<? extends IBean>>();
        jbxxListBean.add(Jmjbxx.class);
        com.cking.phss.bean.BeanUtil.getInstance().getJbxxFromDb(jbxxListBean,
                new com.cking.phss.bean.BeanUtil.OnResultFromDb() {
                    @Override
                    public void onResult(List<IBean> listBean, boolean isSucc) {
                        if (listBean == null || listBean.size() < 0)
                            return;
                        beanMap.put(Jmjbxx.class.getName(), listBean.get(0));
                    }
                });

        // 在取最近一次的脑卒中随访记录
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
            page01.setValue();
            page02.setValue();
            page03.setValue();
            page04.setValue();
        }
    }

    @Override
    public boolean getValue() { if (!hasInit) {return false;}
        page01.getValue();
        page02.getValue();
        page03.getValue();
        page04.getValue();

        return true;
    }

    /**
     * 随访管理脑卒中
     */
    private void saveNzzValueToWeb(final OnGetSfbhListener onGetSfbhListener) {
        Jmjbxx mJmjbxx = (Jmjbxx) beanMap.get(Jmjbxx.class.getName());
        if (mJmjbxx == null || mJmjbxx.getResidentID().equals("")) {
            mToast.setText(R.string.toast_info_none_resident);
            mToast.show();
            return;
        }

        if (getValue()) {
            XmlTag visitIdTag = (XmlTag) GlobalUtil.globalValueMap.get("FlupID");
            if (operateFlag == 0) {// 如果即没有做新增操作，也没有做修改操作，而是使用默认的方式
                // 从数据库中查询最后一次的随访时间和编号
                String residentUUID = MyApplication.getInstance().getSession().getCurrentResident()
                        .getResidentUUID();
                Sfgl sfgl = SfglBll.queryLast("DB脑卒中随访记录", residentUUID);
                if (sfgl != null) {
                    try {
                        GlobalUtil.getInstance().convertProtocolBodyToValue(sfgl.getBean());
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    }
                    XmlTag flupDate = (XmlTag) GlobalUtil.globalValueMap.get("FlupDate");
                    String visitDate = (String) flupDate.getValue();
                    if (!new SimpleDateFormat("yyyy-MM-dd").format(new Date()).equals(visitDate)) {
                        // 不是同一天，那么默认使用编辑模式
                        visitIdTag = (XmlTag) GlobalUtil.globalValueMap.get("FlupID");
                        onGetSfbhListener.onGetSfbh((String) visitIdTag.getValue());
                    }
                }
            }

            List<String> beanClassList = new ArrayList<String>();
            beanClassList.add("HFS03 保存脑卒中随访记录");
            getValue();
            GlobalUtil.getInstance().setSupplement(new Supplement() {
                @Override
                public void protocolToValueSupplement(XmlTag arg0, String arg1) {
                    Jmjbxx mJmjbxx = (Jmjbxx) beanMap.get(Jmjbxx.class.getName());

                    // OrgCode
                    XmlTag xmlTag = GlobalUtil.getInstance().findFirstXmlTag(arg0, "Request");
                    xmlTag.setAttr("OrgCode", Global.orgCode);
                    // UserID
                    XmlTag reqSubTag = GlobalUtil.getInstance().findFirstXmlTag(xmlTag, "UserID");
                    String userID = MyApplication.getInstance().getSession().getLoginResult().response.userID;
                    reqSubTag.setValue(userID);

                    // ResidentID
                    if (mJmjbxx != null) {
                        reqSubTag = GlobalUtil.getInstance().findFirstXmlTag(xmlTag, "ResidentID");
                        reqSubTag.setValue(mJmjbxx.getResidentID());
                    }

                    // // 设置随访医生
                    // if (Global.doctorID != null && Global.doctorName != null)
                    // {
                    // reqSubTag =
                    // GlobalUtil.getInstance().findFirstXmlTag(xmlTag,
                    // "DoctorID");
                    // reqSubTag.setValue(Global.doctorID);
                    // reqSubTag =
                    // GlobalUtil.getInstance().findFirstXmlTag(xmlTag,
                    // "DoctorName");
                    // reqSubTag.setValue(Global.doctorName);
                    // }

                    // 从数据库中读数据，拿到随访序列号，如果是在同一天，则使用同一个序列号，如果不是，那么使用""
                    reqSubTag = GlobalUtil.getInstance().findFirstXmlTag(xmlTag, "FlupID");
                    int type = 1;
                    if (StringUtil.isEmptyString((String) reqSubTag.getValue())) {
                        type = 1;// 新增存盘
                    } else {
                        type = 2;// 编辑存盘
                    }
                    reqSubTag = GlobalUtil.getInstance().findFirstXmlTag(xmlTag, "Type");
                    reqSubTag.setValue(type + "");
                    reqSubTag = GlobalUtil.getInstance().findFirstXmlTag(xmlTag, "DutyDoctorID");
                    reqSubTag.setValue(Global.doctorID);
                    reqSubTag = GlobalUtil.getInstance().findFirstXmlTag(xmlTag, "DutyDoctorName");
                    reqSubTag.setValue(Global.doctorName);

                    // 白酒或黄酒 啤酒
                    String bjhhj = (String) GlobalUtil.globalValueMap.get("TmpTagBjhhj");
                    String pj = (String) GlobalUtil.globalValueMap.get("TmpTagPj");
                    String id = "";
                    String value = "";
                    String yjl = ""; // 饮酒量
                    if (!StringUtil.isEmptyString(bjhhj)) {
                        String tmpid = ResourcesFactory.findId(mContext, "jdzl", "白酒");
                        id = tmpid;
                        value = "白酒";
                        yjl = bjhhj;
                    }
                    if (!StringUtil.isEmptyString(pj)) {
                        if (!StringUtil.isEmptyString(id)) {
                            id += "|";
                        }
                        if (!StringUtil.isEmptyString(value)) {
                            value += "|";
                        }
                        if (!StringUtil.isEmptyString(yjl)) {
                            yjl += "|";
                        }
                        String tmpid = ResourcesFactory.findId(mContext, "jdzl", "啤酒");
                        id += tmpid;
                        value += "啤酒";
                        yjl += pj;
                    }
                    reqSubTag = GlobalUtil.getInstance().findFirstXmlTag(xmlTag, "DrinkingType");
                    reqSubTag.setValue(new BeanCD(id, value));
                    reqSubTag = GlobalUtil.getInstance().findFirstXmlTag(xmlTag, "DrinkingDay");
                    reqSubTag.setValue(yjl);
                }
            });
            BeanUtil.getInstance().saveBeanToWeb2(beanClassList, new OnResultFromWeb2() {

                @Override
                public void onResult(List<String> listBean, boolean isSucc) {
                    if (isSucc) {
                        String errorMsg = listBean.get(0);
                        if (errorMsg.length() > 0) {
                            mToast.setText(errorMsg);
                            mToast.show();
                            return;
                        } else {
                            // mGxyPage01.mUserIDText.setText(responseBean.response.visitID
                            // + "");
                            XmlTag visitIdTag = (XmlTag) GlobalUtil.globalValueMap.get("FlupID");
                            onGetSfbhListener.onGetSfbh((String) visitIdTag.getValue());
                            mToast.setText("数据上传成功");
                            mToast.show();
                            // 数据上传成功，得到随访序列号，保存到数据库中
                            saveValueToDb();
                        }
                    } else {
                        mToast.setText("网络请求失败");
                        mToast.show();
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
        List<String> beanClassList = new ArrayList<String>();
        beanClassList.add("DB脑卒中随访记录");
        BeanUtil.getInstance().saveSfglToDb2(beanClassList, operTime,
                new OnResultSaveToDb() {

                    @Override
                    public void onResult(List<SaveToDbResult> listBean, boolean isSucc) {
                        if (isSucc) {
                    mToast.setText("随访管理脑卒中存储成功！");
                            mToast.show();
                            SaveToDbResult result = listBean.get(0);
                            // 操作标志 1-原始 2-新建 3-修改
                            int operType = result.isAdd ? 2 : 3;
                            int dataSource = 0; // 0 代表不改变原来的状态 ，1
                                                // 代表档案平台，2代表非档案平台
                            // 保存本地记录
                            if (operateFlag == 1) { // 新建
                                dataSource = 2;
                            } else if (operType == 2) { // 第一次插入数据库
                                dataSource = 1;
                            }
                            Jmjbxx jmjbxx = (Jmjbxx) beanMap.get(Jmjbxx.class.getName());
                            com.cking.phss.bean.BeanUtil.getInstance().saveLocalRecord(mContext,
                                    jmjbxx, result.projectUUID, "43", dataSource, operType,
                                    new Date().getTime(), "DB脑卒中随访记录");
                        } else {
                    mToast.setText("随访管理脑卒中存储失败！");
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
    public void saveNzzToWeb(OnGetSfbhListener onGetSfbhListener) {
        saveNzzValueToWeb(onGetSfbhListener);
    }

    // 获取最后一条脑卒中随访信息
    public void getLastNzzSfxx(final OnGetSfbhListener onGetSfbhListener) {
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
        List<String> beanClassList = new ArrayList<String>();
        beanClassList.add("HFS04 得到脑卒中随访记录详细信息");
        GlobalUtil.getInstance().setSupplement(new Supplement() {
            @Override
            public void protocolToValueSupplement(XmlTag arg0, String arg1) {
                Jmjbxx mJmjbxx = (Jmjbxx) beanMap.get(Jmjbxx.class.getName());

                // OrgCode
                XmlTag xmlTag = GlobalUtil.getInstance().findFirstXmlTag(arg0, "Request");
                xmlTag.setAttr("OrgCode", Global.orgCode);

                HashMap2 tagMap = new HashMap2();
                String userID = MyApplication.getInstance().getSession().getLoginResult().response.userID;
                tagMap.put("UserID", userID);
                String residentID = mJmjbxx.getResidentID();
                tagMap.put("ResidentID", residentID);
                String visitID = "0";// 0表示请求最后一条数据
                tagMap.put("FlupID", visitID);

                GlobalUtil.getInstance().setXmlTagValue(xmlTag, tagMap);
            }
        });
        BeanUtil.getInstance().saveBeanToWeb2(beanClassList, new OnResultFromWeb2() {

            @Override
            public void onResult(List<String> listBean, boolean isSucc) {
                if (isSucc) {
                    String errorMsg = listBean.get(0);
                    if (errorMsg.length() > 0) {
                        mToast.setText("【得到随访记录】" + errorMsg);
                        mToast.show();
                        return;
                    } else {
                        // mGxyPage01.mUserIDText.setText(responseBean.response.visitID
                        // + "");
                        XmlTag visitIdTag = (XmlTag) GlobalUtil.globalValueMap.get("FlupID");
                        XmlTag visitDateTag = (XmlTag) GlobalUtil.globalValueMap.get("FlupDate");
                        String visitDate = (String) visitDateTag.getValue();
                        if (!StringUtil.isEmptyString(visitDate)) {
                            if (operateFlag == 1) {// 表示是新增操作
                                // 因为每天只能新增一条数据，所以要判断是否是同一天
                                try {
                                    if (new SimpleDateFormat("yyyy-MM-dd").parse(visitDate)
                                            .getDay() == new Date().getDay()) {
                                        // 如果是同一天
                                        mToast.setText("每天只能有一次随访记录，新增失败，默认改为编辑模式");
                                        mToast.show();
                                    } else {
                                        visitIdTag.setValue("");// ID为空，则为新增模式
                                        visitDateTag.setValue(new SimpleDateFormat("yyyy-MM-dd")
                                                .format(new Date()));
                                    }
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                            } else if (operateFlag == 2) {// 编辑模式
                            }
                        } else {
                            if (operateFlag == 2) {// 表示是编辑操作，但是没有记录，所以改为编辑操作
                                // 因为每天只能新增一条数据，所以要判断是否是同一天
                                mToast.setText("该居民没有随访脑卒中记录，默认改为新增模式！");
                                mToast.show();
                                visitIdTag.setValue("");// ID为空，则为新增模式
                                visitDateTag.setValue(new SimpleDateFormat("yyyy-MM-dd")
                                        .format(new Date()));
                            }
                        }
                        onGetSfbhListener.onGetSfbh((String) visitIdTag.getValue());
                        mToast.setText("【得到随访记录】成功");
                        mToast.show();

                        // 白酒或黄酒 啤酒
                        XmlTag tag0 = (XmlTag) GlobalUtil.globalValueMap.get("DrinkingType");
                        String tag0Value = (String) tag0.getValue();
                        String[] items = tag0Value.split("\\|");
                        XmlTag tag1 = (XmlTag) GlobalUtil.globalValueMap.get("DrinkingDay");
                        String tag1Value = (String) tag1.getValue();
                        String[] item1s = tag1Value.split("\\|");
                        int i = 0;
                        for (String item : items) {
                            if (item.equals("白酒")) {
                                GlobalUtil.globalValueMap.put("TmpTagBjhhj", item1s[i]);
                            } else if (item.equals("啤酒")) {
                                GlobalUtil.globalValueMap.put("TmpTagPj", item1s[i]);
                            }
                            i++;
                        }

                        setValue();
                    }
                } else {
                    mToast.setText("网络请求失败");
                    mToast.show();
                }
            }
        });
    }

    // 获取最后一条脑卒中随访信息
    public void getLastNzzSfxxFromDb(final OnGetSfbhListener onGetSfbhListener) {
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
        // 在取最近一次的脑卒中和糖尿病随访记录
        List<String> beanClassList = new ArrayList<String>();
        beanClassList.add("DB脑卒中随访记录");
        BeanUtil.getInstance().getLastSfglFromDb2(beanClassList, new BeanUtil.OnResultFromDb2() {

            @Override
            public void onResult(List<String> listBean, boolean isSucc) {
                if (isSucc) {
                    if (operateFlag == 1) {// 表示是新增操作

                        XmlTag flupDate = (XmlTag) GlobalUtil.globalValueMap.get("FlupDate");
                        String visitDate = (String) flupDate.getValue();
                        if (new SimpleDateFormat("yyyy-MM-dd").format(new Date()).equals(visitDate)) {
                            // 如果是同一天
                            mToast.setText("每天只能有一次随访记录，新增失败，默认改为编辑模式");
                            mToast.show();
                        } else {
                            // 不是同一天，那么默认使用编辑模式
                            XmlTag visitIdTag = (XmlTag) GlobalUtil.globalValueMap.get("FlupID");
                            visitIdTag.setValue("");// ID为空，则为新增模式
                            flupDate.setValue(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
                        }
                    } else if (operateFlag == 2) {// 编辑模式
                    }
                } else {
                    if (operateFlag == 2) {// 表示是编辑操作，但是没有记录，所以改为编辑操作
                        // 因为每天只能新增一条数据，所以要判断是否是同一天
                        mToast.setText("该居民没有随访脑卒中记录，默认改为新增模式！");
                        mToast.show();
                        XmlTag visitIdTag = (XmlTag) GlobalUtil.globalValueMap.get("FlupID");
                        XmlTag flupDate = (XmlTag) GlobalUtil.globalValueMap.get("FlupDate");
                        visitIdTag.setValue("");// ID为空，则为新增模式
                        flupDate.setValue(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
                    }
                }
                XmlTag visitIdTag = (XmlTag) GlobalUtil.globalValueMap.get("FlupID");
                onGetSfbhListener.onGetSfbh((String) visitIdTag.getValue());
                setValue();
            }
        });
    }

    /* (non-Javadoc)
     * @see com.cking.phss.page.ISfglPage#onResultSflb(boolean)
     */
    @Override
    public void onResultSflb(boolean canDoSf) {if (canDoSf) {
            mToast.setText("该居民可以进行脑卒中随访");
        mToast.show();
    } else {
            mToast.setText("该居民不可以进行脑卒中随访");
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
