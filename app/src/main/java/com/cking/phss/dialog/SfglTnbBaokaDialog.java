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
import com.cking.phss.bean.BeanID;
import com.cking.phss.bean.BeanUtil;
import com.cking.phss.bean.BeanUtil.OnResultFromWeb;
import com.cking.phss.bean.IBean;
import com.cking.phss.bean.Jktj_kstj;
import com.cking.phss.bean.Jmjbxx;
import com.cking.phss.bean.Sfgljl_tnb;
import com.cking.phss.dto.IDto;
import com.cking.phss.dto.Login1;
import com.cking.phss.dto.sfgl.tnb.Bctnbglk26;
import com.cking.phss.dto.sfgl.tnb.Ddtnbglkxxxx25;
import com.cking.phss.global.Global;
import com.cking.phss.page.IPage;
import com.cking.phss.page.SfglTnbReportPage01;
import com.cking.phss.page.SfglTnbReportPage02;
import com.cking.phss.page.SfglTnbReportPage03;
import com.cking.phss.page.SfglTnbReportPage04;
import com.cking.application.MyApplication;
import com.cking.phss.util.TispToastFactory;

/**
 * com.cking.phss.widget.AddressText
 * 
 * @author Administrator <br/>
 *         create at 2012-9-16 下午12:56:59
 */
public class SfglTnbBaokaDialog extends Dialog implements IPage {
    @SuppressWarnings("unused")
    private static final String TAG = "SfglTnbBaokaDialog";
    
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
    public int checkReport = 1;// 检查是否进入报卡状态，1表示没有 ，2表示进入高血压报卡，3表示进入糖尿病报卡

    private Map<String, IBean> tnbBeanMap = new HashMap<String, IBean>();
    
    /**
     * 高血压报卡的三个页面
     * 
     * @param context
     */
    SfglTnbReportPage01 mSfglTnbReportPage01 = null;
    SfglTnbReportPage02 mSfglTnbReportPage02 = null;
    SfglTnbReportPage03 mSfglTnbReportPage03 = null;
    SfglTnbReportPage04 mSfglTnbReportPage04 = null;

    public interface OnTnbBaokaResultListener {
        public void onConfirm();
        public void onCancel();
    }
    
    public OnTnbBaokaResultListener onTnbBaokaResultListener = null;
    
    public void setOnTnbBaokaResultListener(OnTnbBaokaResultListener listener) {
        onTnbBaokaResultListener = listener;
    }
    
    /**
     * @param context
     */
    public SfglTnbBaokaDialog(Context context) {
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
        tnbBeanMap.put(Jmjbxx.class.getName(), Global.jmjbxx);
        tnbBeanMap.put(Sfgljl_tnb.class.getName(), new Sfgljl_tnb());
        tnbBeanMap.put(Jktj_kstj.class.getName(), new Jktj_kstj());
        tnbBeanMap.put(Ddtnbglkxxxx25.class.getName(), new Ddtnbglkxxxx25());

        Bctnbglk26 bctnbglk26 = new Bctnbglk26();
        bctnbglk26.request = new Bctnbglk26.Request();
        tnbBeanMap.put(Bctnbglk26.class.getName(), bctnbglk26);
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
        mSfglTnbReportPage01 = new SfglTnbReportPage01(context, tnbBeanMap, SfglTnbBaokaDialog.this);
        mPageList.add(mSfglTnbReportPage01);
        mSfglTnbReportPage02 = new SfglTnbReportPage02(context, tnbBeanMap);
        mPageList.add(mSfglTnbReportPage02);
        mSfglTnbReportPage03 = new SfglTnbReportPage03(context, tnbBeanMap);
        mPageList.add(mSfglTnbReportPage03);
        mSfglTnbReportPage04 = new SfglTnbReportPage04(context, tnbBeanMap,SfglTnbBaokaDialog.this);
        mPageList.add(mSfglTnbReportPage04);
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

        Jmjbxx jmjbxx = (Jmjbxx) tnbBeanMap.get(Jmjbxx.class.getName());
        if (jmjbxx == null || jmjbxx.getResidentID().equals("")) {
            mToast.setText(R.string.toast_info_none_resident);
            mToast.show();
            return;
        }

        if (!getValue()) {
            return;
        }

        Bctnbglk26 bctnbglk26 = (Bctnbglk26) tnbBeanMap.get(Bctnbglk26.class.getName());
        if (bctnbglk26 == null || bctnbglk26.request == null) {
            mToast.setText("上传出错，请重试！");
            mToast.show();
            return;
        }
        bctnbglk26.request.dutyDoctor = new BeanID(Global.doctorID, Global.doctorName);
        List<IDto> beanList = new ArrayList<IDto>();
        beanList.add(bctnbglk26);
        BeanUtil.getInstance().getBeanFromWeb(beanList, new OnResultFromWeb() {
            @Override
            public void onResult(List<IDto> listBean, boolean isSucc) {
                Bctnbglk26 responseBean = (Bctnbglk26) listBean.get(0);
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
                            mToast.setText("【糖尿病报卡】上传成功");
                            mToast.show();
                        }
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
        Jmjbxx mJmjbxx = (Jmjbxx) tnbBeanMap.get(Jmjbxx.class.getName());
        if (mJmjbxx == null || mJmjbxx.getResidentID() == null
                || mJmjbxx.getResidentID().equals("")) {
            mToast.setText(R.string.toast_info_none_resident);
            mToast.show();
            return;
        }
        Ddtnbglkxxxx25 ddtnbglkxxxx25 = new Ddtnbglkxxxx25();
        ddtnbglkxxxx25.request = new Ddtnbglkxxxx25.Request();
        ddtnbglkxxxx25.request.employeeNo = login1.response.employeeNo;
        ddtnbglkxxxx25.request.residentID = mJmjbxx.getResidentID();

        List<IDto> beanList = new ArrayList<IDto>();
        beanList.add(ddtnbglkxxxx25);
        BeanUtil.getInstance().getBeanFromWeb(beanList, new BeanUtil.OnResultFromWeb() {
            @Override
            public void onResult(List<IDto> listBean, boolean isSucc) {
                if (isSucc) {
                    Ddtnbglkxxxx25 responseDdtnbglkxxxx25 = (Ddtnbglkxxxx25) listBean.get(0);
                    if (responseDdtnbglkxxxx25 == null || responseDdtnbglkxxxx25.response == null
                            || responseDdtnbglkxxxx25.response.errMsg.length() > 0) {
                        mToast.setText("获取糖尿病报卡出错，请返回重试");
                        mToast.show();
                        return;
                    } else {
                        if (StringUtil.isEmptyString(responseDdtnbglkxxxx25.response.paperNum)) {// 返回的符合的记录不存在
                            // 没有数据
                        } else {
                            tnbBeanMap.put(Ddtnbglkxxxx25.class.getName(), responseDdtnbglkxxxx25);
                        }
                        setValue();
                    }
                }
            }
        });

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.cking.phss.page.IPage#setValue()
     */
    @Override
    public void setValue() {
        mSfglTnbReportPage01.setValue();
        mSfglTnbReportPage02.setValue();
        mSfglTnbReportPage03.setValue();
        mSfglTnbReportPage04.setValue();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.cking.phss.page.IPage#getValue()
     */
    @Override
    public boolean getValue() {
        return mSfglTnbReportPage01.getValue() && mSfglTnbReportPage02.getValue()
                && mSfglTnbReportPage03.getValue() && mSfglTnbReportPage04.getValue();
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
