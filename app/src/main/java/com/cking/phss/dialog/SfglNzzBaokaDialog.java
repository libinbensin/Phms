/* Cking Inc. (C) 2012. All rights reserved.
 *
 * AddressText.java
 * classes : com.cking.phss.widget.AddressText
 * @author Administrator
 * V 1.0.0
 * Create at 2012-9-16 下午12:56:59
 */
package com.cking.phss.dialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.xinhuaxing.beans.XmlTag;
import net.xinhuaxing.type.HashMap2;
import net.xinhuaxing.util.GlobalUtil;
import net.xinhuaxing.util.GlobalUtil.Supplement;
import net.xinhuaxing.util.ResourcesFactory;
import net.xinhuaxing.util.StringUtil;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.adinnet.xd.medical.widget.MyButton;
import com.cking.phss.R;
import com.cking.phss.bean.BeanUtil;
import com.cking.phss.bean.BeanUtil.OnResultFromWeb2;
import com.cking.phss.bean.IBean;
import com.cking.phss.bean.Jktj_kstj;
import com.cking.phss.bean.Jmjbxx;
import com.cking.phss.bean.Sfgljl_tnb;
import com.cking.phss.dto.Login1;
import com.cking.phss.global.Global;
import com.cking.phss.page.IPage;
import com.cking.phss.page.SfglNzzReportPage01;
import com.cking.phss.page.SfglNzzReportPage02;
import com.cking.phss.page.SfglNzzReportPage03;
import com.cking.phss.page.SfglNzzReportPage04;
import com.cking.phss.page.SfglNzzReportPage05;
import com.cking.phss.page.SfglNzzReportPage06;
import com.cking.phss.page.SfglNzzReportPage07;
import com.cking.phss.page.SfglNzzReportPage08;
import com.cking.phss.util.MyApplication;
import com.cking.phss.util.TispToastFactory;

/**
 * com.cking.phss.widget.AddressText
 * 
 * @author Administrator <br/>
 *         create at 2012-9-16 下午12:56:59
 */
public class SfglNzzBaokaDialog extends Dialog implements IPage {
    @SuppressWarnings("unused")
    private static final String TAG = "SfglNzzBaokaDialog";
    
    private Context mContext = null;
    private Toast mToast = null;
    
    LinearLayout layout_content;
    MyButton imageview_left_page;
    MyButton imageview_right_page;
    TextView textview_page;
    TextView textview_total_page;
    List<View> mPageList = new ArrayList<View>();
    int pageIndexFrom1 = 1;
    private static final int MAX_PAGE_COUNT = 8;
    ImageView closeImageView;
    public int checkReport = 1;// 检查是否进入报卡状态，1表示没有 ，2表示进入高血压报卡，3表示进入脑卒中报卡
    int type = 1;

    private Map<String, IBean> nzzBeanMap = new HashMap<String, IBean>();
    // 保存所有的全局数据
    public static HashMap2 globalValueMap = new HashMap2();
    
    /**
     * 高血压报卡的三个页面
     * 
     * @param context
     */
    SfglNzzReportPage01 mSfglNzzReportPage01 = null;
    SfglNzzReportPage02 mSfglNzzReportPage02 = null;
    SfglNzzReportPage03 mSfglNzzReportPage03 = null;
    SfglNzzReportPage04 mSfglNzzReportPage04 = null;
    SfglNzzReportPage05 mSfglNzzReportPage05 = null;
    SfglNzzReportPage06 mSfglNzzReportPage06 = null;
    SfglNzzReportPage07 mSfglNzzReportPage07 = null;
    SfglNzzReportPage08 mSfglNzzReportPage08 = null;

    public interface OnNzzBaokaResultListener {
        public void onConfirm();
        public void onCancel();
    }
    
    public OnNzzBaokaResultListener onNzzBaokaResultListener = null;
    
    public void setOnNzzBaokaResultListener(OnNzzBaokaResultListener listener) {
        onNzzBaokaResultListener = listener;
    }
    
    /**
     * @param context
     */
    public SfglNzzBaokaDialog(Context context) {
        super(context, R.style.dialog);

        init(context);
    }

    /**
     * @param context
     */
    private void init(Context context) {
        mContext = context;
        mToast = TispToastFactory.getToast(context, "");
        setCancelable(true);
        setContentView(R.layout.dialog_bk_common_layout);
        nzzBeanMap.put(Jmjbxx.class.getName(), Global.jmjbxx);
        nzzBeanMap.put(Sfgljl_tnb.class.getName(), new Sfgljl_tnb());
        nzzBeanMap.put(Jktj_kstj.class.getName(), new Jktj_kstj());
        loadPage(context);
    }

    public void loadPage(Context context) {
        layout_content = (LinearLayout) findViewById(R.id.layout_content);
        closeImageView = (ImageView) findViewById(R.id.closeImageView);
        closeImageView.setOnClickListener(new android.view.View.OnClickListener() {
            
            @Override
            public void onClick(View arg0) {
                dismiss();//隐藏对话框 
            }
        });

        // 添加页面
        mSfglNzzReportPage01 = new SfglNzzReportPage01(context, nzzBeanMap, SfglNzzBaokaDialog.this);
        mSfglNzzReportPage01.setValueMap(globalValueMap);
        mPageList.add(mSfglNzzReportPage01);
        mSfglNzzReportPage02 = new SfglNzzReportPage02(context, nzzBeanMap);
        mSfglNzzReportPage02.setValueMap(globalValueMap);
        mPageList.add(mSfglNzzReportPage02);
        mSfglNzzReportPage03 = new SfglNzzReportPage03(context, nzzBeanMap);
        mSfglNzzReportPage03.setValueMap(globalValueMap);
        mPageList.add(mSfglNzzReportPage03);
        mSfglNzzReportPage04 = new SfglNzzReportPage04(context, nzzBeanMap);
        mSfglNzzReportPage04.setValueMap(globalValueMap);
        mPageList.add(mSfglNzzReportPage04);
        mSfglNzzReportPage05 = new SfglNzzReportPage05(context, nzzBeanMap);
        mSfglNzzReportPage05.setValueMap(globalValueMap);
        mPageList.add(mSfglNzzReportPage05);
        mSfglNzzReportPage06 = new SfglNzzReportPage06(context, nzzBeanMap);
        mSfglNzzReportPage06.setValueMap(globalValueMap);
        mPageList.add(mSfglNzzReportPage06);
        mSfglNzzReportPage07 = new SfglNzzReportPage07(context, nzzBeanMap);
        mSfglNzzReportPage07.setValueMap(globalValueMap);
        mPageList.add(mSfglNzzReportPage07);
        mSfglNzzReportPage08 = new SfglNzzReportPage08(context, nzzBeanMap, SfglNzzBaokaDialog.this);
        mSfglNzzReportPage08.setValueMap(globalValueMap);
        mPageList.add(mSfglNzzReportPage08);
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

    public void saveReportToWeb() {
        Login1 login1 = MyApplication.getInstance().getSession().getLoginResult();
        if (login1 == null || login1.response == null) {
            mToast.setText("当前没有医生登录，请先登录！");
            mToast.show();
            return;
        }

        Jmjbxx jmjbxx = (Jmjbxx) nzzBeanMap.get(Jmjbxx.class.getName());
        if (jmjbxx == null || jmjbxx.getResidentID().equals("")) {
            mToast.setText(R.string.toast_info_none_resident);
            mToast.show();
            return;
        }

        List<String> beanClassList = new ArrayList<String>();
        beanClassList.add("HFS01 保存脑卒中管理卡");
        getValue();

        GlobalUtil.getInstance().setSupplement(new Supplement() {
            @Override
            public void protocolToValueSupplement(XmlTag arg0, String arg1) {
                Jmjbxx mJmjbxx = (Jmjbxx) nzzBeanMap.get(Jmjbxx.class.getName());

                // Type
                XmlTag xmlTag = GlobalUtil.getInstance().findFirstXmlTag(arg0, "Type");
                xmlTag.setValue(type + "");
                // OrgCode
                xmlTag = GlobalUtil.getInstance().findFirstXmlTag(arg0, "Request");
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

                reqSubTag = GlobalUtil.getInstance().findFirstXmlTag(xmlTag, "DutyDoctor");
                reqSubTag.setAttr("ID", Global.doctorID);
                reqSubTag.setValue(Global.doctorName);

                // 白酒或黄酒 啤酒
                XmlTag bjhhj = (XmlTag) globalValueMap.get("脑卒中报卡-生活习惯-白酒或黄酒");
                XmlTag pj = (XmlTag) globalValueMap.get("脑卒中报卡-生活习惯-啤酒");
                String id = "";
                String value = "";
                String yjl = ""; // 饮酒量
                if (!StringUtil.isEmptyString((String) bjhhj.getValue())) {
                    String tmpid = ResourcesFactory.findId(mContext, "jdzl", "白酒");
                    id = tmpid;
                    value = "白酒";
                    yjl = (String) bjhhj.getValue();
                }
                if (!StringUtil.isEmptyString((String) pj.getValue())) {
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
                    yjl += pj.getValue();
                }
                reqSubTag = GlobalUtil.getInstance().findFirstXmlTag(xmlTag, "DrinkingType");
                reqSubTag.setAttr("CD", id);
                reqSubTag.setValue(value);
                reqSubTag = GlobalUtil.getInstance().findFirstXmlTag(xmlTag, "DrinkingDay");
                reqSubTag.setValue(yjl);

                // 个人系统疾病史 - 脑卒中（类型，诊断时间）
                reqSubTag = GlobalUtil.getInstance().findFirstXmlTag(xmlTag, "PHStroke");
                XmlTag lx = (XmlTag) globalValueMap.get("脑卒中情况-类型");
                XmlTag zdsj = (XmlTag) globalValueMap.get("脑卒中情况-诊断时间");

            }
        });
        BeanUtil.getInstance().saveBeanToWeb2(globalValueMap, beanClassList,
                new OnResultFromWeb2() {

            @Override
            public void onResult(List<String> listBean, boolean isSucc) {
                if (isSucc) {
                    String errorMsg = listBean.get(0);
                    if (errorMsg.length() > 0) {
                        mToast.setText(errorMsg);
                        mToast.show();
                        return;
                    } else {
                        mToast.setText("【脑卒中报卡】上传成功");
                        mToast.show();
                    }
                } else {
                    mToast.setText("网络请求失败");
                    mToast.show();
                }
            }
        });
    }

    public void getReportFromWeb() {
        Login1 login1 = MyApplication.getInstance().getSession().getLoginResult();
        if (login1 == null || login1.response == null) {
            mToast.setText("当前没有医生登录，请先登录！");
            mToast.show();
            return;
        }
        Jmjbxx mJmjbxx = (Jmjbxx) nzzBeanMap.get(Jmjbxx.class.getName());
        if (mJmjbxx == null || mJmjbxx.getResidentID() == null
                || mJmjbxx.getResidentID().equals("")) {
            mToast.setText(R.string.toast_info_none_resident);
            mToast.show();
            return;
        }
        List<String> beanClassList = new ArrayList<String>();
        beanClassList.add("HFS02 得到脑卒中管理卡详细信息");

        GlobalUtil.getInstance().setSupplement(new Supplement() {
            @Override
            public void protocolToValueSupplement(XmlTag arg0, String arg1) {
                Jmjbxx mJmjbxx = (Jmjbxx) nzzBeanMap.get(Jmjbxx.class.getName());

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
            }
        });
        BeanUtil.getInstance().saveBeanToWeb2(globalValueMap, beanClassList,
                new OnResultFromWeb2() {

                    @Override
                    public void onResult(List<String> listBean, boolean isSucc) {
                        mSfglNzzReportPage01.setValueByJmjbxx(Global.jmjbxx);
                        if (isSucc) {
                            String errorMsg = listBean.get(0);
                            if (errorMsg.length() > 0) {
                                mToast.setText(errorMsg);
                                mToast.show();
                                return;
                            } else {

                                XmlTag residentIdTag = (XmlTag) globalValueMap.get("ResidentID");
                                if (residentIdTag == null
                                        || StringUtil.isEmptyString((String) residentIdTag
                                                .getValue())) {
                                    type = 1;
                                } else {
                                    type = 2;
                                    mToast.setText("【脑卒中报卡】获取成功");
                                    mToast.show();
                                }
                                XmlTag tag22 = (XmlTag) globalValueMap.get("Income");
                                // 白酒或黄酒 啤酒
                                XmlTag tag0 = (XmlTag) globalValueMap.get("DrinkingType");
                                if (tag0 != null) {
                                    String tag0Value = (String) tag0.getValue();
                                    if (tag0Value != null) {
                                        String[] items = tag0Value.split("\\|");
                                        XmlTag tag1 = (XmlTag) globalValueMap.get("DrinkingDay");
                                        String tag1Value = (String) tag1.getValue();
                                        if (tag1Value != null) {
                                            String[] item1s = tag1Value.split("\\|");
                                            int i = 0;
                                            for (String item : items) {
                                                if (item.equals("白酒")) {
                                                    globalValueMap.put("脑卒中报卡-生活习惯-白酒或黄酒",
                                                            new XmlTag("脑卒中报卡-生活习惯-白酒或黄酒",
                                                                    item1s[i]));
                                                } else if (item.equals("啤酒")) {
                                                    globalValueMap.put("脑卒中报卡-生活习惯-啤酒", new XmlTag(
                                                            "脑卒中报卡-生活习惯-啤酒", item1s[i]));
                                                }
                                                i++;
                                            }
                                        }
                                    }
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

    public void setLocalValue() {
        mSfglNzzReportPage01.setValueByJmjbxx(Global.jmjbxx);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.cking.phss.page.IPage#setValue()
     */
    @Override
    public void setValue() {
        mSfglNzzReportPage01.setValue();
        mSfglNzzReportPage02.setValue();
        mSfglNzzReportPage03.setValue();
        mSfglNzzReportPage04.setValue();
        mSfglNzzReportPage05.setValue();
        mSfglNzzReportPage06.setValue();
        mSfglNzzReportPage07.setValue();
        mSfglNzzReportPage08.setValue();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.cking.phss.page.IPage#getValue()
     */
    @Override
    public boolean getValue() {
        mSfglNzzReportPage01.getValue();
        mSfglNzzReportPage02.getValue();
        mSfglNzzReportPage03.getValue();
        mSfglNzzReportPage04.getValue();
        mSfglNzzReportPage05.getValue();
        mSfglNzzReportPage06.getValue();
        mSfglNzzReportPage07.getValue();
        mSfglNzzReportPage08.getValue();
        return true;
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
