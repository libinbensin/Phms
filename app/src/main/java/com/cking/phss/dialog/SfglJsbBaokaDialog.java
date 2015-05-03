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
import com.cking.phss.bean.BeanUtil;
import com.cking.phss.bean.BeanUtil.OnResultFromWeb;
import com.cking.phss.bean.IBean;
import com.cking.phss.bean.Jktj_kstj;
import com.cking.phss.bean.Jmjbxx;
import com.cking.phss.bean.Sfgljl_jsb;
import com.cking.phss.dto.IDto;
import com.cking.phss.dto.Login1;
import com.cking.phss.dto.sfgl.jsb.BcjsbglkHfm01;
import com.cking.phss.dto.sfgl.jsb.DdjsbglkxxxxHfm02;
import com.cking.phss.global.Global;
import com.cking.phss.page.IPage;
import com.cking.phss.page.SfglJsbReportPage01;
import com.cking.phss.page.SfglJsbReportPage02;
import com.cking.phss.page.SfglJsbReportPage03;
import com.cking.phss.page.SfglJsbReportPage04;
import com.cking.application.MyApplication;
import com.cking.phss.util.TispToastFactory;

/**
 * com.cking.phss.widget.AddressText
 * 
 * @author Administrator <br/>
 *         create at 2012-9-16 下午12:56:59
 */
public class SfglJsbBaokaDialog extends Dialog implements IPage {
    @SuppressWarnings("unused")
    private static final String TAG = "SfglJsbBaokaDialog";
    
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
    public int checkReport = 1;// 检查是否进入报卡状态，1表示没有 ，2表示进入精神病报卡，3表示进入精神病报卡

    private Map<String, IBean> jsbBeanMap = new HashMap<String, IBean>();
    
    /**
     * 精神病报卡的三个页面
     * 
     * @param context
     */
    SfglJsbReportPage01 mJsbReportPage01 = null;
    SfglJsbReportPage02 mJsbReportPage02 = null;
    SfglJsbReportPage03 mJsbReportPage03 = null;
    SfglJsbReportPage04 mJsbReportPage04 = null;

    public interface OnJsbBaokaResultListener {
        public void onConfirm();
        public void onCancel();
    }
    
    public OnJsbBaokaResultListener onJsbBaokaResultListener = null;
    
    public void setOnJsbBaokaResultListener(OnJsbBaokaResultListener listener) {
        onJsbBaokaResultListener = listener;
    }
    
    /**
     * @param context
     */
    public SfglJsbBaokaDialog(Context context) {
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
        jsbBeanMap.put(Jmjbxx.class.getName(), Global.jmjbxx);
        jsbBeanMap.put(Sfgljl_jsb.class.getName(), new Sfgljl_jsb());
        jsbBeanMap.put(Jktj_kstj.class.getName(), new Jktj_kstj());
        jsbBeanMap.put(DdjsbglkxxxxHfm02.class.getName(), new DdjsbglkxxxxHfm02());

        BcjsbglkHfm01 bcjsbglkHfm01 = new BcjsbglkHfm01();
        bcjsbglkHfm01.request = new BcjsbglkHfm01.Request();
        jsbBeanMap.put(BcjsbglkHfm01.class.getName(), bcjsbglkHfm01);
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
        mJsbReportPage01 = new SfglJsbReportPage01(context, jsbBeanMap, SfglJsbBaokaDialog.this);
        mPageList.add(mJsbReportPage01);
        mJsbReportPage02 = new SfglJsbReportPage02(context, jsbBeanMap);
        mPageList.add(mJsbReportPage02);
        mJsbReportPage03 = new SfglJsbReportPage03(context, jsbBeanMap);
        mPageList.add(mJsbReportPage03);
        mJsbReportPage04 = new SfglJsbReportPage04(context, jsbBeanMap, SfglJsbBaokaDialog.this);
        mPageList.add(mJsbReportPage04);
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

        Jmjbxx jmjbxx = (Jmjbxx) jsbBeanMap.get(Jmjbxx.class.getName());
        if (jmjbxx == null || jmjbxx.getResidentID().equals("")) {
            mToast.setText(R.string.toast_info_none_resident);
            mToast.show();
            return;
        }

        if (!getValue()) {
            return;
        }

        BcjsbglkHfm01 bcjsbglkHfm01 = (BcjsbglkHfm01) jsbBeanMap.get(BcjsbglkHfm01.class
                .getName());
        if (bcjsbglkHfm01 == null || bcjsbglkHfm01.request == null) {
            mToast.setText("上传出错，请重试！");
            mToast.show();
            return;
        }
        List<IDto> beanList = new ArrayList<IDto>();
        beanList.add(bcjsbglkHfm01);
        BeanUtil.getInstance().getBeanFromWeb(beanList, new OnResultFromWeb() {
            @Override
            public void onResult(List<IDto> listBean, boolean isSucc) {
                BcjsbglkHfm01 responseBean = (BcjsbglkHfm01) listBean.get(0);
                if (isSucc) {
                    if (responseBean == null || responseBean.response == null) {
                        mToast.setText("网络异常");
                        mToast.show();
                    } else {
                        if (responseBean.response.errMsg != null
                                && responseBean.response.errMsg.length() > 0) {
                            mToast.setText(responseBean.response.errMsg);
                            mToast.show();
                            return;
                        } else {
                            mToast.setText("【精神病报卡】上传成功");
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
        Jmjbxx mJmjbxx = (Jmjbxx) jsbBeanMap.get(Jmjbxx.class.getName());
        if (mJmjbxx == null || mJmjbxx.getResidentID() == null
                || mJmjbxx.getResidentID().equals("")) {
            mToast.setText(R.string.toast_info_none_resident);
            mToast.show();
            return;
        }
        DdjsbglkxxxxHfm02 ddjsbglkxxxxHfm02 = new DdjsbglkxxxxHfm02();
        ddjsbglkxxxxHfm02.request = new DdjsbglkxxxxHfm02.Request();
        ddjsbglkxxxxHfm02.request.userID = login1.response.userID;
        ddjsbglkxxxxHfm02.request.residentID = mJmjbxx.getResidentID();

        List<IDto> beanList = new ArrayList<IDto>();
        beanList.add(ddjsbglkxxxxHfm02);
        BeanUtil.getInstance().getBeanFromWeb(beanList, new BeanUtil.OnResultFromWeb() {
            @Override
            public void onResult(List<IDto> listBean, boolean isSucc) {
                if (isSucc) {
                    DdjsbglkxxxxHfm02 ddjsbglkxxxxHfm02 = (DdjsbglkxxxxHfm02) listBean.get(0);
                    if (ddjsbglkxxxxHfm02 == null || ddjsbglkxxxxHfm02.response == null
                            || ddjsbglkxxxxHfm02.response.errMsg.length() > 0) {
                        mToast.setText("获取精神病报卡出错，请返回重试");
                        mToast.show();
                        return;
                    } else {
                        if (StringUtil.isEmptyString(ddjsbglkxxxxHfm02.response.credentialsNo)) {// 返回的符合的记录不存在
                            // 没有数据
                        } else {
                            jsbBeanMap.put(DdjsbglkxxxxHfm02.class.getName(), ddjsbglkxxxxHfm02);
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
        mJsbReportPage01.setValue();
        mJsbReportPage02.setValue();
        mJsbReportPage03.setValue();
        mJsbReportPage04.setValue();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.cking.phss.page.IPage#getValue()
     */
    @Override
    public boolean getValue() {
        return mJsbReportPage01.getValue() && mJsbReportPage02.getValue()
                && mJsbReportPage03.getValue() && mJsbReportPage04.getValue();
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
