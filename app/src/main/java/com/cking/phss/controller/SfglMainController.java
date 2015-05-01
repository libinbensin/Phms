/* Xinhuaxing Inc. (C) 2014. All rights reserved.
 *
 * JbxxMainView.java
 * classes : com.cking.phss.view.JbxxMainView
 * @author Administrator
 * V 1.0.0
 * Create at 2014-6-11 上午11:39:13
 */
package com.cking.phss.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.xinhuaxing.util.StringUtil;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.cking.phss.R;
import com.cking.phss.bean.BeanUtil;
import com.cking.phss.bean.IBean;
import com.cking.phss.bean.Jmjbxx;
import com.cking.phss.dto.IDto;
import com.cking.phss.dto.innner.Card;
import com.cking.phss.dto.sfgl.DdgrbklbHvc01;
import com.cking.phss.global.Global;
import com.cking.phss.util.AppConfigFactory.AppConfig;
import com.cking.phss.util.MyApplication;
import com.cking.phss.view.SfglBodyView;
import com.cking.phss.widget.GuidePager.OnPageChangeListener;
import com.cking.phss.widget.MenuRadioButton;
import com.cking.phss.widget.MenuRadioGroup;
import com.cking.phss.widget.MenuRadioGroup.OnCheckedChangeListener;

/**
 * com.cking.phss.view.JbxxMainController
 * @author Administrator <br/>
 * create at 2014-6-11 上午11:39:13
 */
public class SfglMainController {
    private static final String TAG = "JbxxMainController";
    private Context mContext = null;

    private static int sLeftRadioIndex = 0;
    MenuRadioGroup radiogoup_jbxx;
    LinearLayout one_main_fragment_right_layout;
    private Map<String, IBean> beanMap = new HashMap<String, IBean>();
    View bodyView;
    MenuRadioButton radio_gxy;
    MenuRadioButton radio_tnb;
    MenuRadioButton radio_nzz;
    MenuRadioButton radio_jsb;
    MenuRadioButton radio_ycfs;
    MenuRadioButton radio_etfs;
    MenuRadioButton radio_lnsf;
    MenuRadioButton radio_cjsf;

    /**
     * 随访管理
     */
    private SfglBodyView mSfglBodyView = null;
    
    /**
     * @param context
     */
    public SfglMainController(Context context) {
        mContext = context;
        beanMap.put(Jmjbxx.class.getName(), Global.jmjbxx);
        beanMap.put(AppConfig.class.getName(), Global.appConfig);
        
        init(context);
    }

    /**
     * @param context
     */
    private void init(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        bodyView = inflater.inflate(R.layout.fragment_sfgl, null);
        
        mSfglBodyView = new SfglBodyView(context);
        radiogoup_jbxx = (MenuRadioGroup) bodyView.findViewById(R.id.radiogoup_sfgl);
        one_main_fragment_right_layout = (LinearLayout) bodyView
                .findViewById(R.id.one_main_fragment_right_layout);
        radio_gxy = (MenuRadioButton) bodyView.findViewById(R.id.radio_gxy);
        radio_tnb = (MenuRadioButton) bodyView.findViewById(R.id.radio_tnb);
        radio_nzz = (MenuRadioButton) bodyView.findViewById(R.id.radio_nzz);
        radio_jsb = (MenuRadioButton) bodyView.findViewById(R.id.radio_jsb);
        radio_ycfs = (MenuRadioButton) bodyView.findViewById(R.id.radio_ycfs);
        radio_etfs = (MenuRadioButton) bodyView.findViewById(R.id.radio_etfs);
        radio_lnsf = (MenuRadioButton) bodyView.findViewById(R.id.radio_lnsf);
        radio_cjsf = (MenuRadioButton) bodyView.findViewById(R.id.radio_cjsf);
        AppConfig appConfig = (AppConfig) beanMap.get(AppConfig.class.getName());
        if (appConfig != null) {
            String mksz = appConfig.getMksz();
            if (mksz != null) {
                radio_gxy.setVisibility(mksz.contains("高血压") ? View.GONE : View.VISIBLE);
                radio_tnb.setVisibility(mksz.contains("糖尿病") ? View.GONE : View.VISIBLE);
                radio_nzz.setVisibility(mksz.contains("脑卒中") ? View.GONE : View.VISIBLE);
                radio_jsb.setVisibility(mksz.contains("精神病") ? View.GONE : View.VISIBLE);
                radio_ycfs.setVisibility(mksz.contains("孕产访视") ? View.GONE : View.VISIBLE);
                radio_etfs.setVisibility(mksz.contains("儿童访视") ? View.GONE : View.VISIBLE);
                radio_lnsf.setVisibility(mksz.contains("老年随访") ? View.GONE : View.VISIBLE);
                radio_cjsf.setVisibility(mksz.contains("残疾随访") ? View.GONE : View.VISIBLE);
            }
        }
        mSfglBodyView.getBeanFromDB();
        one_main_fragment_right_layout.removeAllViews();
        one_main_fragment_right_layout.addView(mSfglBodyView);
        int firstVisibleIndex = MyApplication.getInstance().getSfglFirstVisibleIndex();
        if (firstVisibleIndex >= 0) {
            mSfglBodyView.showItemByIndex(firstVisibleIndex);
        }
        radiogoup_jbxx.setOnCheckedChangeListener(new OnCheckedChangeListener() {

          @Override
          public void onCheckedChanged(MenuRadioGroup group, int checkedId, int index) {
              // TODO Auto-generated method stub
              if(!((MenuRadioButton) group.findViewById(checkedId)).isChecked()){
                  return;
              }
              SfglMainController.sLeftRadioIndex = index;
              mSfglBodyView.showItemByIndex(index);
            }
        });

        mSfglBodyView.setOnPageChangeListener(new OnPageChangeListener() { // 当选中某页的回调
            
            @Override
            public void onPageSelected(int index) {
                if (radiogoup_jbxx.getCheckedIndex() != index) {
                    radiogoup_jbxx.setCheckedByIndex(index);
                }
            }
        });

        Jmjbxx mJmjbxx = (Jmjbxx) beanMap.get(Jmjbxx.class.getName());
        if (!Global.isLocalLogin && !StringUtil.isEmptyString(mJmjbxx.getResidentID())) {
            getGrbklbFromWeb(mJmjbxx.getResidentID());
        }
    }

    /**
     * @param stringuserId 
     * 
     */
    private void getGrbklbFromWeb(String ResidentID) {
        String stringuserID = MyApplication.getInstance().getSession().getLoginResult().response.userID;
        DdgrbklbHvc01 ddgrbklbHvc01 = new DdgrbklbHvc01();
        ddgrbklbHvc01.request = new DdgrbklbHvc01.Request();
        ddgrbklbHvc01.request.ResidentID = ResidentID;
        ddgrbklbHvc01.request.UserID = stringuserID;
        List<IDto> beanList = new ArrayList<IDto>();
        beanList.add(ddgrbklbHvc01);

        BeanUtil.getInstance().getBeanFromWeb(beanList, new BeanUtil.OnResultFromWeb() {
            @Override
            public void onResult(List<IDto> listBean, boolean isSucc) {
                if (isSucc) {
                    StringBuilder sb = new StringBuilder();
                    DdgrbklbHvc01 ddgrbklbHvc01 = (DdgrbklbHvc01) listBean.get(0);
                    if (ddgrbklbHvc01 == null || ddgrbklbHvc01.response == null) {
                        sb.append("【得到个人报卡列表】服务器接口异常");
                    } else {
                        if (ddgrbklbHvc01.response.errMsg.length() > 0) {
                            sb.append("【得到个人报卡列表】" + ddgrbklbHvc01.response.errMsg);
                        } else {
                            updateCardList(ddgrbklbHvc01.response.cards);
                            sb.append("【得到个人报卡列表】成功");
                        }
                    }
                }

            }
        });
    }

    /**
     * @param cards
     */
    protected void updateCardList(List<Card> cards) {
        Global.cards = cards;
        if (cards != null && cards.size() > 0) {
            for (Card card : cards) {
                if (card.CardType != null) {
                    String id = card.CardType.getcD();
                    // CD：类型代码 1.高血压，2.糖尿病，3.脑卒中，4.精神病，5.老年人，6.残疾人，7.围产，8.儿童
                    if (id.equals("1")) {
                        radio_gxy.setBkVisibility(View.VISIBLE);
                    } else if (id.equals("2")) {
                        radio_tnb.setBkVisibility(View.VISIBLE);
                    } else if (id.equals("3")) {
                        radio_nzz.setBkVisibility(View.VISIBLE);
                    } else if (id.equals("4")) {
                        radio_jsb.setBkVisibility(View.VISIBLE);
                    } else if (id.equals("5")) {
                        radio_lnsf.setBkVisibility(View.VISIBLE);
                    } else if (id.equals("6")) {
                        radio_cjsf.setBkVisibility(View.VISIBLE);
                    } else if (id.equals("7")) {
                        radio_ycfs.setBkVisibility(View.VISIBLE);
                    } else if (id.equals("8")) {
                        radio_etfs.setBkVisibility(View.VISIBLE);
                    }
                }
            }
        }
    }

    /**
     * @return
     */
    public View getView() {
        return bodyView;
    }
}
