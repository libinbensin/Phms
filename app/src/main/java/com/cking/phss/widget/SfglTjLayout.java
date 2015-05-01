package com.cking.phss.widget;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.xinhuaxing.eshow.constants.Constants;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.cking.phss.R;
import com.cking.phss.activity.BaseActivity;
import com.cking.phss.activity.MainActivity;
import com.cking.phss.bean.BeanUtil;
import com.cking.phss.bean.BeanUtil.OnResultFromWeb;
import com.cking.phss.dto.IDto;
import com.cking.phss.dto.MbsfmxlbJ011;
import com.cking.phss.dto.MbsfmxlbJ011.Person;
import com.cking.phss.global.Global;
import com.cking.phss.util.CalendarUtil;
import com.cking.phss.util.DisplayUtil;
import com.cking.phss.util.MyApplication;
import com.cking.phss.util.TispToastFactory;

public class SfglTjLayout extends LinearLayout {
    @SuppressWarnings("unused")
    private static final String TAG = "GuidePager";

    private TextView mFooterTipsShould = null;
    private TextView mFooterTipsDone = null;
    private TextView mFooterTipsNotDone = null;
    private TextView mFooterNextTime = null;
    private String disType="";
    private Context mContext = null;
    private View currentView;
    private PopupWindow mPopupWindow;
    private View mPopupView;
    private LinearLayout mPopupList;
    private Toast mToast;
    private TextView mPopupTitle;
    private ImageView[] mPopupPointerImg;
    
    public interface OnPageChangeListener {
        public void onPageSelected(int index);
    }
    
    private OnPageChangeListener mOnPageChangeListener = null;
    
    public void setOnPageChangeListener(OnPageChangeListener listener) {
        mOnPageChangeListener = listener;
    }
    
	public SfglTjLayout(Context context) {

		super(context);

        init(context);

	}

	public SfglTjLayout(Context context, AttributeSet attrs) {

		super(context, attrs);

        init(context);
	}

    /**
     * @param context
     */
    private void init(Context context) {
        mContext = context;
        mToast = TispToastFactory.getToast(context, "");
        LayoutInflater inflater = LayoutInflater.from(context);
        currentView=inflater.inflate(R.layout.sfgl_tj_layout, this);
        
        mFooterTipsShould = (TextView) findViewById(R.id.footer_tips);
        mFooterTipsDone = (TextView) findViewById(R.id.footer_tips_done);
        mFooterTipsNotDone = (TextView) findViewById(R.id.footer_tips_notDone);
        mFooterNextTime = (TextView) findViewById(R.id.footer_tips_nexttime);
        // 自动定位下月今天
        Date nextMonth = CalendarUtil.getNextMonth(new Date());
        mFooterNextTime.setText(new SimpleDateFormat("yyyy-MM-dd").format(nextMonth)); // 默认值
        
        mFooterTipsShould.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSfmxlb(1);//显示随访明细列表
            }
        });
        
        mFooterTipsDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSfmxlb(2);//显示随访明细列表
            }
        });
        
        mFooterTipsNotDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSfmxlb(3);//显示随访明细列表
            }
        });
    }
    
    public void setFootTipsText(String should,String done,String notDone) {
        mFooterTipsShould.setText(should + "人");
        mFooterTipsDone.setText(done + "人");
        mFooterTipsNotDone.setText(notDone + "人");
    }
    
    public void setDisType(String disType) {
        this.disType=disType;
    }
    
    public void setNextTime(String nextTime) {
        if (mFooterNextTime != null) {
            mFooterNextTime.setText(nextTime);
        }
    }

    public void setNextTime(Date nextTime) {
        if (mFooterNextTime != null) {
            mFooterNextTime.setText(new SimpleDateFormat("yyyy-MM-dd").format(nextTime));
        }
    }

    /**
     * 显示随访明细列表
     * @param index 1表示点击的是应随访  ，2表示已随访  ，3表示未随访
     */
    public void showSfmxlb(int index){
        if (Global.isLocalLogin) {
            mToast.setText("本地登录，此功能无效");
            mToast.show();
            return;
        }
        if(mPopupWindow==null){
            initPopWindow();
        }
        mPopupList.removeAllViews();
        mPopupList.addView(new ListItemSfglmxlb(mContext));
        mPopupList.invalidate();
        if(index==1){
            mPopupTitle.setText("随访管理明细列表（应随访）");
        }else if (index==2) {
            mPopupTitle.setText("随访管理明细列表（已随访）");
        }else {
            mPopupTitle.setText("随访管理明细列表（未随访）");
        }
        setPopWindowPoter(index);
        //弹出对话框显示随访列表的详细信息
        mPopupWindow.showAtLocation(currentView, Gravity.CENTER, 0, 0);// 设置在屏幕中的显示位置
        getSfmxlb(index);
    }
    
    private void initPopWindow(){
        LayoutInflater inflater = LayoutInflater.from(mContext);
        mPopupView = inflater.inflate(R.layout.sfgl_mxlb_alertdialog,null);
        mPopupWindow = new PopupWindow(mPopupView, DisplayUtil.dip2px(1100,
                DisplayUtil.getScale(mContext)), DisplayUtil.dip2px(630,
                DisplayUtil.getScale(mContext)));
        mPopupWindow.setFocusable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setAnimationStyle(android.R.style.Animation_Dialog);
        mPopupList=(LinearLayout)mPopupView.findViewById(R.id.list_layout);
        mPopupTitle=(TextView)mPopupView.findViewById(R.id.list_title);
        mPopupPointerImg=new ImageView[3];
        mPopupPointerImg[0]=(ImageView)mPopupView.findViewById(R.id.left_point);
        mPopupPointerImg[1]=(ImageView)mPopupView.findViewById(R.id.middle_point);
        mPopupPointerImg[2]=(ImageView)mPopupView.findViewById(R.id.right_point);
        Button cancelBtn=(Button)mPopupView.findViewById(R.id.cancel_btn);
        cancelBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mPopupWindow!=null||mPopupWindow.isShowing()){
                    mPopupWindow.dismiss();
                }
            }
        });
        mPopupView.invalidate();
    }
    
    //从服务器上获取随访明细列表
    private void getSfmxlb(int index){
        MbsfmxlbJ011 mbsfmxlbJ011=new MbsfmxlbJ011();
        mbsfmxlbJ011.request=new MbsfmxlbJ011.Request();
        if(MyApplication.getInstance().getSession().getLoginResult()==null||MyApplication.getInstance().getSession().getLoginResult().response==null){
            mToast.setText("当前没有医生登录，请先登录！");
            mToast.show();
            return;
        }
        mbsfmxlbJ011.request.userID= MyApplication.getInstance().getSession().getLoginResult().response.userID;
        mbsfmxlbJ011.request.disType=disType;
        mbsfmxlbJ011.request.searchType=index;
        
        List<IDto> beanList=new ArrayList<IDto>();
        beanList.add(mbsfmxlbJ011);
        BeanUtil.getInstance().getBeanFromWeb(beanList, new OnResultFromWeb() {
            @Override
            public void onResult(List<IDto> listBean, boolean isSucc) {
                if (isSucc) {
                    MbsfmxlbJ011 responseMbsfmxlbJ011= (MbsfmxlbJ011) listBean
                            .get(0);
                    if (responseMbsfmxlbJ011 == null || responseMbsfmxlbJ011.response == null) {
                        mToast.setText("网络异常");
                        mToast.show();
                    } else {
                        if (responseMbsfmxlbJ011.response.errMsg != null
                                && responseMbsfmxlbJ011.response.errMsg.length() > 0) {
                            mToast.setText(responseMbsfmxlbJ011.response.errMsg);
                            mToast.show();
                            return;
                        } else {
                            setSfmxlb(responseMbsfmxlbJ011.response.persons);
                        }
                    }
                } else {
                    mToast.setText("网络请求失败");
                    mToast.show();
                }
            }
        });
    }
    
    private void setSfmxlb(List<Person> persons){
        if(persons==null||persons.size()<=0)
            return;
        for (final Person p : persons) {
            ListItemSfglmxlb listItem=new ListItemSfglmxlb(mContext);
            listItem.setName(p.name);
            listItem.setSex(p.sex);
            listItem.setAge(p.age);
            listItem.setLinkPhone(p.linkPhone);
            listItem.setIdCard(p.idcard);
            listItem.setAddress(p.address);
            listItem.setDisType(p.disType);
            listItem.setRptDate(p.rptDate);
            listItem.setRptSratus(p.rptStatus);
            listItem.setApprovedDate(p.approvedDate);
            listItem.setApprovedFlag(p.approvedFlag);
            mPopupList.addView(listItem);
            mPopupView.invalidate();
            listItem.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) { // 跳转到档案信息页面并搜索
                    Intent intent = new Intent();
                    ((BaseActivity) mContext).finish();
                    intent.putExtra("tag", Constants.PAGE_JBXX);
                    intent.putExtra("doctorID", Global.doctorID);
                    intent.putExtra("doctorName", Global.doctorName);
                    intent.putExtra("paperNum", p.idcard);
                    intent.setClass(mContext, MainActivity.class);
                    mContext.startActivity(intent);
                    // 设置切换动画，从右边进入，左边退出,带动态效果
                    ((Activity) mContext).overridePendingTransition(R.anim.dync_in_from_right,
                            R.anim.dync_out_to_left);
                }
            });
        }
    }
    
    //设置底部的图标指向
    private void setPopWindowPoter(int index){
        for(ImageView imageView:mPopupPointerImg){
            imageView.setVisibility(View.INVISIBLE);
        }
        mPopupPointerImg[index-1].setVisibility(View.VISIBLE);
    }
}
