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
import net.xinhuaxing.util.GlobalUtil;
import net.xinhuaxing.util.GlobalUtil.Supplement;
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
import com.cking.phss.bean.BeanID;
import com.cking.phss.bean.BeanUtil;
import com.cking.phss.bean.BeanUtil.OnResultFromWeb2;
import com.cking.phss.bean.IBean;
import com.cking.phss.bean.Jktj_kstj;
import com.cking.phss.bean.Jmjbxx;
import com.cking.phss.bean.Sfgljl_tnb;
import com.cking.phss.dto.Login1;
import com.cking.phss.dto.sfgl.tnb.Bctnbglk26;
import com.cking.phss.dto.sfgl.tnb.Ddtnbglkxxxx25;
import com.cking.phss.global.Global;
import com.cking.phss.page.IPage;
import com.cking.phss.page.SfglEtfsReportPage01;
import com.cking.phss.page.SfglEtfsReportPage02;
import com.cking.phss.page.SfglEtfsReportPage03;
import com.cking.phss.page.SfglEtfsReportPage04;
import com.cking.phss.util.MyApplication;
import com.cking.phss.util.TispToastFactory;

/**
 * com.cking.phss.widget.AddressText
 * 
 * @author Administrator <br/>
 *         create at 2012-9-16 下午12:56:59
 */
public class SfglEtfsBaokaDialog extends Dialog implements IPage {
    @SuppressWarnings("unused")
    private static final String TAG = "SfglEtfsBaokaDialog";
    
    private Context mContext = null;
    private Toast mToast = null;
    
    LinearLayout layout_content;
    MyButton imageview_left_page;
    MyButton imageview_right_page;
    TextView textview_page;
    TextView textview_total_page;
    List<View> mPageList = new ArrayList<View>();
    int pageIndexFrom1 = 1;
    private static final int MAX_PAGE_COUNT = 4;
    ImageView closeImageView;
    public int checkReport = 1;// 检查是否进入报卡状态，1表示没有 ，2表示进入高血压报卡，3表示进入脑卒中报卡

    private Map<String, IBean> nzzBeanMap = new HashMap<String, IBean>();
    
    /**
     * 高血压报卡的三个页面
     * 
     * @param context
     */
    SfglEtfsReportPage01 mSfglEtfsReportPage01 = null;
    SfglEtfsReportPage02 mSfglEtfsReportPage02 = null;
    SfglEtfsReportPage03 mSfglEtfsReportPage03 = null;
    SfglEtfsReportPage04 mSfglEtfsReportPage04 = null;

    public interface OnEtfsBaokaResultListener {
        public void onConfirm();
        public void onCancel();
    }
    
    public OnEtfsBaokaResultListener onEtfsBaokaResultListener = null;
    
    public void setOnEtfsBaokaResultListener(OnEtfsBaokaResultListener listener) {
        onEtfsBaokaResultListener = listener;
    }
    
    /**
     * @param context
     */
    public SfglEtfsBaokaDialog(Context context) {
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
        nzzBeanMap.put(Ddtnbglkxxxx25.class.getName(), new Ddtnbglkxxxx25());

        Bctnbglk26 bctnbglk26 = new Bctnbglk26();
        bctnbglk26.request = new Bctnbglk26.Request();
        nzzBeanMap.put(Bctnbglk26.class.getName(), bctnbglk26);
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
        mSfglEtfsReportPage01 = new SfglEtfsReportPage01(context, nzzBeanMap);
        mPageList.add(mSfglEtfsReportPage01);
        mSfglEtfsReportPage02 = new SfglEtfsReportPage02(context, nzzBeanMap);
        mPageList.add(mSfglEtfsReportPage02);
        mSfglEtfsReportPage03 = new SfglEtfsReportPage03(context, nzzBeanMap);
        mPageList.add(mSfglEtfsReportPage03);
        mSfglEtfsReportPage04 = new SfglEtfsReportPage04(context, nzzBeanMap,
                SfglEtfsBaokaDialog.this);
        mPageList.add(mSfglEtfsReportPage04);
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

                reqSubTag = GlobalUtil.getInstance().findFirstXmlTag(xmlTag, "DutyDoctor");
                reqSubTag.setValue(new BeanID(Global.doctorID, Global.doctorName));
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
        BeanUtil.getInstance().saveBeanToWeb2(beanClassList, new OnResultFromWeb2() {

            @Override
            public void onResult(List<String> listBean, boolean isSucc) {
                if (isSucc) {
                    String errorMsg = listBean.get(0);
                    if (errorMsg.length() > 0) {
                        mToast.setText(errorMsg);
                        mToast.show();
                        mSfglEtfsReportPage01.setValueByJmjbxx(Global.jmjbxx);
                        return;
                    } else {
                        mToast.setText("【脑卒中报卡】获取成功");
                        mToast.show();
                        setValue();
                    }
                } else {
                    mToast.setText("网络请求失败");
                    mToast.show();
                    mSfglEtfsReportPage01.setValueByJmjbxx(Global.jmjbxx);
                }
            }
        });
    }

    public void setLocalValue() {
        mSfglEtfsReportPage01.setValueByJmjbxx(Global.jmjbxx);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.cking.phss.page.IPage#setValue()
     */
    @Override
    public void setValue() {
        mSfglEtfsReportPage01.setValue();
        mSfglEtfsReportPage02.setValue();
        mSfglEtfsReportPage03.setValue();
        mSfglEtfsReportPage04.setValue();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.cking.phss.page.IPage#getValue()
     */
    @Override
    public boolean getValue() {
        mSfglEtfsReportPage01.getValue();
        mSfglEtfsReportPage02.getValue();
        mSfglEtfsReportPage03.getValue();
        mSfglEtfsReportPage04.getValue();
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
