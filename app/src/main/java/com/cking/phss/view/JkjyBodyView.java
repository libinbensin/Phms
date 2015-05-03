/* Cking Inc. (C) 2012. All rights reserved.
 *
 * JbxxBodyView.java
 * classes : com.cking.phss.view.JbxxBodyView
 * @author Administrator
 * V 1.0.0
 * Create at 2012-9-16 上午11:25:10
 */
package com.cking.phss.view;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.xinhuaxing.eshow.constants.Constants;
import android.content.Context;
import android.net.Uri;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cking.phss.R;
import com.cking.phss.bean.BeanUtil;
import com.cking.phss.bean.IBean;
import com.cking.phss.bean.Jktj_kstj;
import com.cking.phss.bean.Jmjbxx;
import com.cking.phss.bean.Sfgljl_gxy;
import com.cking.phss.bean.Sfgljl_tnb;
import com.cking.phss.dto.BcjkpgJ005;
import com.cking.phss.dto.IDto;
import com.cking.phss.dto.Login1;
import com.cking.phss.global.Global;
import com.cking.phss.page.IPage;
import com.cking.phss.page.JkjyMbzhPage01;
import com.cking.phss.page.JkjyPage01;
import com.cking.phss.page.JkjyPage02;
import com.cking.phss.page.JkjyPage03;
import com.cking.phss.page.JkjyPage04;
import com.cking.phss.page.JkjyPage05;
import com.cking.phss.util.BluetoothUtil;
import com.cking.phss.util.CalendarUtil;
import com.cking.phss.util.Constant;
import com.cking.phss.util.JgxxConfigFactory;
import com.cking.application.MyApplication;
import com.cking.phss.util.TispToastFactory;
import com.cking.phss.widget.GuidePager;
import com.cking.phss.widget.GuidePager.OnPageChangeListener;
import com.cking.phss.xml4jgxx.tags.constants.TagConstants;

/**
 * 健康教育 com.cking.phss.view.JbxxBodyView
 * 
 * @author Administrator <br/>
 *         create at 2012-9-16 上午11:25:10
 */
public class JkjyBodyView extends LinearLayout implements IPage {
    @SuppressWarnings("unused")
    private static final String TAG = "JkjyBodyView";
    private static final boolean D = true;
    private GuidePager mGuidePager = null;

    private Map<String, IBean> beanMap = new HashMap<String, IBean>();
    
    ImageView sexImageView;
    TextView sexTextView;
    TextView ageTextView;
    TextView sfzhTextView;
    TextView dabhTextView;
    TextView khTextView;
    TextView residentNameTextView;
    ImageView profileImageView;
    /**
     * 内容子页
     */
    private ViewGroup mDetailPage = null;

    /**
     * 全局控件
     */
//    public RadioGroup mTabRadios = null;
    private Toast mToast = null;
    private Context mContext;
    private Button mUploadBtn=null;
    Button dyButton; // 打印
    /**
     * 选择的TAB页编号
     */
    private static int sTabRadioId = 0;
    private int deviceId = 0;

    /**
     * 健康教育的页面
     */
    JkjyMbzhPage01 mMbzhPage01 = null;

    JkjyPage01 mJkjyPage01 = null;
    JkjyPage02 mJkjyPage02 = null;
    JkjyPage03 mJkjyPage03 = null;
    JkjyPage04 mJkjyPage04 = null;
    JkjyPage05 mJkjyPage05 = null;

    private OnPageChangeListener mOnPageChangeListener = null;
    
    public void setOnPageChangeListener(OnPageChangeListener listener) {
        mOnPageChangeListener = listener;
    }
    
    /**
     * @param context
     */
    public JkjyBodyView(Context context) {
        super(context);
        init(context);
    }

    /**
     * @param context
     * @param attrs
     */
    public JkjyBodyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    /**
     * @param context
     */
    private void init(final Context context) {
        //初始化beanMap里面的值
        beanMap.put(Jmjbxx.class.getName(), Global.jmjbxx);
        beanMap.put(Sfgljl_gxy.class.getName(), Global.sfgljlGxy);
        beanMap.put(Sfgljl_tnb.class.getName(), Global.sfgljlTnb);
        beanMap.put(Jktj_kstj.class.getName(), Global.jktjKstj);

        mContext = context;
        mToast = TispToastFactory.getToast(context, "");
        deviceId = JgxxConfigFactory.findIdByName(mContext, TagConstants.XML_VALUE_NAME_RMDYJ);
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.fragment_jkjy_body_layout, this);

        loadPage(context, this);
    }

    public void loadPage(Context context, ViewGroup viewGroup) {

        profileImageView = (ImageView) findViewById(R.id.profileImageView);
        residentNameTextView = (TextView) findViewById(R.id.residentNameTextView);
        sexImageView = (ImageView) findViewById(R.id.sexImageView);
        sexTextView = (TextView) findViewById(R.id.sexTextView);
        ageTextView = (TextView) findViewById(R.id.ageTextView);
        sfzhTextView = (TextView) findViewById(R.id.sfzhTextView);
        dabhTextView = (TextView) findViewById(R.id.dabhTextView);
        khTextView = (TextView) findViewById(R.id.khTextView);
        
        mGuidePager = (GuidePager) findViewById(R.id.guide_pager);
        mGuidePager.setAdapter(new MyPagerAdapter(context));
        mGuidePager.showPage(0);
        mGuidePager.setOnPageChangeListener(new OnPageChangeListener() { // 当选中某页的回调
            
            @Override
            public void onPageSelected(int index) {
                if (mOnPageChangeListener != null) {
                    mOnPageChangeListener.onPageSelected(index);
                }
            }
        });
        //mDetailPage = (ViewGroup) findViewById(R.id.data_jkjy_detail);
        mUploadBtn=(Button)findViewById(R.id.upload_button);
        dyButton = (Button) findViewById(R.id.print_button);
        mMbzhPage01 = new JkjyMbzhPage01(context);

//        mTabRadios = (RadioGroup) findViewById(R.id.tab_radio);
//
//        mTabRadios.setOnCheckedChangeListener(new OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                JkjyBodyView.sTabRadioId = checkedId;
//                switch (checkedId) {
//                    case R.id.radio_mbzs:
//                        mDetailPage.removeAllViews();
//                        mDetailPage.addView(mMbzhPage01);
//                        break;
//                    case R.id.radio_yypg:
//                        mDetailPage.removeAllViews();
//                        if(mJkjyPage01==null){
//                            mJkjyPage01=new JkjyPage01(context,beanMap);
//                        }
//                        mDetailPage.addView(mJkjyPage01);
//                        mJkjyPage01.setValue();
//                        break;
//                    case R.id.radio_sszd:
//                        mDetailPage.removeAllViews();
//                        if(mJkjyPage03==null){
//                            mJkjyPage03=new JkjyPage03(context);
//                        }
//                        mDetailPage.addView(mJkjyPage03);
//                        break;
//                    case R.id.radio_ydcf:
//                        mDetailPage.removeAllViews();
//                        if(mJkjyPage04==null){
//                            mJkjyPage04=new JkjyPage04(context,beanMap);
//                        }
//                        mDetailPage.addView(mJkjyPage04);
//                        mJkjyPage04.setValue();
//                        break;
//                    case R.id.radio_fxys:
//                        mDetailPage.removeAllViews();
//                        if(mJkjyPage02==null){
//                            mJkjyPage02=new JkjyPage02(context,beanMap);
//                        }
//                        mDetailPage.addView(mJkjyPage02);
//                        mJkjyPage02.setValue();
//                        break;
//                    case R.id.radio_jkts:
//                        mDetailPage.removeAllViews();
//                        if(mJkjyPage05==null){
//                            mJkjyPage05=new JkjyPage05(context);
//                        }
//                        mDetailPage.addView(mJkjyPage05);
//                        mJkjyPage05.setValue(getJkts());
//                        break;
//
//                }
//            }
//        });
//
//        mTabRadios.check(R.id.radio_yypg);

        if (deviceId == TagConstants.XML_VALUE_ID_SPRT_SPRMTIIIBTA) {
            if (!BluetoothUtil.isPared(Constants.BT_NAME_SPRT_SPRMTIIIBTA)) {
                dyButton.setEnabled(false);
            } else {
                dyButton.setEnabled(true);
            }
        } else if (deviceId == TagConstants.XML_VALUE_ID_QS_BZ) {
            if (!BluetoothUtil.isPared(Constants.BT_NAME_QS_BZ)) {
                dyButton.setEnabled(false);
            } else {
                dyButton.setEnabled(true);
            }
        } else if (deviceId == TagConstants.XML_VALUE_ID_QT_BZ3) {
            if (!BluetoothUtil.isPared(Constants.BT_NAME_SPRT_SPRMTIIIBTA)) {
                dyButton.setEnabled(false);
            } else {
                dyButton.setEnabled(true);
            }
        } else {
            dyButton.setEnabled(false);
        }

        if (Global.isLocalLogin) {
            mUploadBtn.setEnabled(false);
        } else {
            mUploadBtn.setEnabled(true);
        }
        mUploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Global.isLocalLogin) {
                    mToast.setText("当前是本地登录，不支持上传操作。");
                    mToast.show();
                    return;
                }
                uploadJkjyInfo();
            }
        });
    }

    // viewpager数据绑定
    class MyPagerAdapter extends PagerAdapter {
        List<View> mPageList = new ArrayList<View>();

        public MyPagerAdapter(Context c) {
            super();
            // 添加页面
            mJkjyPage01=new JkjyPage01(c,beanMap);
            mPageList.add(mJkjyPage01);
            mJkjyPage02=new JkjyPage02(c,beanMap);
            mPageList.add(mJkjyPage02);
            mJkjyPage03=new JkjyPage03(c);
            mPageList.add(mJkjyPage03);
            mJkjyPage04=new JkjyPage04(c,beanMap);
            mPageList.add(mJkjyPage04);
            mJkjyPage05=new JkjyPage05(c);
            mPageList.add(mJkjyPage05);
            mMbzhPage01 = new JkjyMbzhPage01(c);
            mPageList.add(mMbzhPage01);
        }

        public void destroyItem(View arg0, int arg1, Object arg2) {
            ((ViewPager) arg0).removeView(mPageList.get(arg1));
        }

        @Override
        public void finishUpdate(View arg0) {
        }

        public int getCount() {
            return mPageList.size();
        }

        public Object instantiateItem(View arg0, int arg1) {
            View v = mPageList.get(arg1);
            ((ViewPager) arg0).addView(v);
            return v;
        }

        public boolean isViewFromObject(View arg0, Object arg1) {
            return (arg0 == arg1);
        }

        @Override
        public void restoreState(Parcelable arg0, ClassLoader arg1) {

        }

        @Override
        public Parcelable saveState() {
            return null;
        }

        @Override
        public void startUpdate(View arg0) {
        }
    }

    private Map<String, Object> getJkts(){
        String page01Result="";
        if(mJkjyPage01!=null){
          String  result= mJkjyPage01.mJkjyText.getText().toString().trim();
          if(!result.equals(R.string.toast_info_none_resident)&&!result.equals("您没有做体重相关检测，无法对您评估!")){
              page01Result=result;
          }
        }
        
        List<Integer> page02Result=null;
        if(mJkjyPage02!=null){
            page02Result=mJkjyPage02.valueList;
        }
        
        String page04Result="";
        if(mJkjyPage04!=null){
          String  result= mJkjyPage04.mJkjyText.getText().toString().trim();
          if(!result.equals(R.string.toast_info_none_resident)&&!result.equals("您没有做体重相关检测，无法对您评估!")){
              page04Result=result;
          }
        }
        
        Map<String, Object> result=new HashMap<String, Object>();
        result.put("page01Result", page01Result);
        result.put("page02Result", page02Result);
        result.put("page04Result", page04Result);
        
        return result;
    }
    
    public void getDataFromDb() {

        // 在取最近一次的高血压和糖尿病随访记录
        List<Class<? extends IBean>> sfListBean = new ArrayList<Class<? extends IBean>>();
        sfListBean.add(Sfgljl_gxy.class);
        sfListBean.add(Sfgljl_tnb.class);
        BeanUtil.getInstance().getLastSfglFromDb(sfListBean, new BeanUtil.OnResultFromDb() {

            @Override
            public void onResult(List<IBean> listBean, boolean isSucc) {
                if (isSucc) {
                    if (listBean.get(0) != null) {
                        beanMap.put(Sfgljl_gxy.class.getName(), listBean.get(0));
                    }else{
                        beanMap.put(Sfgljl_gxy.class.getName(), new Sfgljl_gxy());
                    }
                    if (listBean.get(1) != null) {
                        beanMap.put(Sfgljl_tnb.class.getName(), listBean.get(1));
                    }else{
                        beanMap.put(Sfgljl_tnb.class.getName(), new Sfgljl_tnb());
                    }
                }
                mJkjyPage01.setValue();
                mJkjyPage02.setValue();
                mJkjyPage04.setValue();
                mJkjyPage05.setValue(getJkts());
            }
        });

        // // 在取出快速体检的数据放进去
        // List<Class<? extends IBean>> kstjListBean = new ArrayList<Class<?
        // extends IBean>>();
        // kstjListBean.add(Jktj_kstj.class);
        // BeanUtil.getInstance().getLastJktjFromDb(kstjListBean, new
        // BeanUtil.OnResultFromDb() {
        // @Override
        // public void onResult(List<IBean> listBean, boolean isSucc) {
        // if (isSucc){
        // if (listBean.get(0) != null) {
        // beanMap.put(Jktj_kstj.class.getName(), listBean.get(0));
        // // if (mGuidePager.getSelectIndex() == 0) {
        // // mJkjyPage01.setValue();
        // // } else if (mGuidePager.getSelectIndex() == 1) {
        // // mJkjyPage02.setValue();
        // // } else if (mGuidePager.getSelectIndex() == 3) {
        // // mJkjyPage04.setValue();
        // // } else if (mGuidePager.getSelectIndex() == 4) {
        // // mJkjyPage05.setValue(getJkts());
        // // }
        // mJkjyPage01.setValue();
        // mJkjyPage02.setValue();
        // mJkjyPage04.setValue();
        // mJkjyPage05.setValue(getJkts());
        // }else{
        // beanMap.put(Jktj_kstj.class.getName(), new Jktj_kstj());
        // }
        // }else{
        // beanMap.put(Jktj_kstj.class.getName(), new Jktj_kstj());
        // }
        // }
        // });
    }
    
    private void uploadJkjyInfo(){
        Map<String, Object> maps=getJkts();
        String page01Result = "";
        List<Integer> page02Result = null;
        String page04Result = "";
        try {
            page01Result = (String) maps.get("page01Result");
            page02Result = (List<Integer>) maps.get("page02Result");
            page04Result = (String) maps.get("page04Result");
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
        
        BcjkpgJ005 bcjkpgJ005=new BcjkpgJ005();
        bcjkpgJ005.request=new BcjkpgJ005.Request();
        bcjkpgJ005.request.type="1";
        
        Login1 login1=MyApplication.getInstance().getSession().getLoginResult();
        if(login1==null||login1.response==null){
            mToast.setText("当前没有医生登录，请先登录！");
            mToast.show();
            return;
        }
        bcjkpgJ005.request.userID=login1.response.userID;
        bcjkpgJ005.request.evalEmpId = login1.response.employeeNo.getiD();
        
        Jmjbxx jmjbxx = (Jmjbxx) beanMap.get(Jmjbxx.class.getName());
        if (jmjbxx == null || jmjbxx.getResidentID().equals("")) {
            mToast.setText("当前没有用户登记，请先登记！");
            mToast.show();
            return;
        }
        bcjkpgJ005.request.residentID=jmjbxx.getResidentID();
        bcjkpgJ005.request.checkDate=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        
        
        //营养评估的结论
        if(page01Result!=null&&!page01Result.equals("")){
            bcjkpgJ005.request.eval=page01Result;
        }
        
        //风险因素
        if(page02Result!=null&&page02Result.size()>0){
            String lastResultString="";
            for(int i:page02Result){
                lastResultString+=(mJkjyPage02.items[i]+";");
            }
            bcjkpgJ005.request.risk=lastResultString;
        }
        
        //运动处方
        if(page04Result!=null&&!page04Result.equals("")){
            bcjkpgJ005.request.prescribe=page04Result;
        }
        
        List<IDto> beanList=new ArrayList<IDto>();
        beanList.add(bcjkpgJ005);
        BeanUtil.getInstance().getBeanFromWeb(beanList, new BeanUtil.OnResultFromWeb() {
            @Override
            public void onResult(List<IDto> listBean, boolean isSucc) {
                if (isSucc) {
                    BcjkpgJ005 responseBcjkpgJ005 = (BcjkpgJ005) listBean
                            .get(0);
                    if (responseBcjkpgJ005 == null
                            || responseBcjkpgJ005.response == null||responseBcjkpgJ005.response.errMsg.length() > 0) {
                        mToast.setText("保存健康评估失败！");
                        mToast.show();
                    } else {
                        mToast.setText("保存健康评估成功！"+responseBcjkpgJ005.response.evalSn);
                        mToast.show();
                    }
                }}
        });
    }

    public void getBeanFromDB() {
        if (beanMap == null)
            return;
        if (Global.updateJmjbxx) {
            setValue();
            return;
        }
        List<Class<? extends IBean>> listBean = new ArrayList<Class<? extends IBean>>();
        listBean.add(Jmjbxx.class);
        BeanUtil.getInstance().getJbxxFromDb(listBean,
                new BeanUtil.OnResultFromDb() {
                    @Override
                    public void onResult(List<IBean> listBean, boolean isSucc) {
                        if(listBean==null||listBean.size()<0)
                            return;
//                      if (isSucc) {
                            beanMap.put(Jmjbxx.class.getName(), listBean.get(0));
                            setValue();
//                      }
                    }
                });

    }
    
    /**
     * @param index
     */
    public void showItemByIndex(int index) {
        mGuidePager.showPage(index);
    }

    /* (non-Javadoc)
     * @see com.cking.phss.page.IPage#setValue()
     */
    @Override
    public void setValue() {
        Jmjbxx mJmjbxx = (Jmjbxx) beanMap.get(Jmjbxx.class.getName());
        if (mJmjbxx != null) {
            residentNameTextView.setText(mJmjbxx.getResidentName());
            dabhTextView.setText(mJmjbxx.getResidentID());
            sfzhTextView.setText(mJmjbxx.getPaperNum());
            khTextView.setText(mJmjbxx.getCardID());
            if (mJmjbxx.getPaperNum() != null && !mJmjbxx.getPaperNum().trim().equals(""))
                profileImageView.setImageURI(Uri.fromFile(new File(Constant.PROFILE_PATH, mJmjbxx
                        .getPaperNum() + ".png")));

            // 性别
            if (mJmjbxx.getSexCD() == 1) {
                sexImageView.setImageResource(R.drawable.male);
                sexTextView.setText("男");
            } else if (mJmjbxx.getSexCD() == 2) {
                sexImageView.setImageResource(R.drawable.female);
                sexTextView.setText("女");
            } else {
                sexImageView.setImageResource(R.drawable.unknown);
                sexTextView.setText("未知");
            }

            // 年龄
            ageTextView.setText(CalendarUtil.getAge(mJmjbxx.getBirthDay()) + "岁");
            
            getDataFromDb(); // 更新所有页面数据
        }
    }

    /* (non-Javadoc)
     * @see com.cking.phss.page.IPage#getValue()
     */
    @Override
    public boolean getValue() {
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.cking.phss.page.IPage#clear()
     */
    @Override
    public void clear() {
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        // 把按钮加入与网络相关的视图列表
        Global.globalViewList.add(mUploadBtn);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();

        // 把按钮移除与网络相关的视图列表
        Global.globalViewList.remove(mUploadBtn);
        getValue();
    }
}
