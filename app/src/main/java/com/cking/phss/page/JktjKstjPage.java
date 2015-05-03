/* Cking Inc. (C) 2012. All rights reserved.
 *
 * JbxxBodyView.java
 * classes : com.cking.phss.view.JbxxBodyView
 * @author Administrator
 * V 1.0.0
 * Create at 2012-9-16 上午11:25:10
 */
package com.cking.phss.page;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.xinhuaxing.util.StringUtil;
import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

import com.cking.phss.R;
import com.cking.phss.bean.BeanUtil;
import com.cking.phss.bean.BeanUtil.SaveToDbResult;
import com.cking.phss.bean.IBean;
import com.cking.phss.bean.Jktj_kstj;
import com.cking.phss.bean.Jmjbxx;
import com.cking.phss.bluetooth.BluetoothClient4Bu34;
import com.cking.phss.bluetooth.BluetoothServer4And;
import com.cking.phss.dto.Login1;
import com.cking.phss.file.FileLog;
import com.cking.phss.net.ISoapRecv;
import com.cking.phss.net.WebService;
import com.cking.phss.util.DeviceUseFactory;
import com.cking.application.MyApplication;
import com.cking.phss.util.PrinterUtil;
import com.cking.phss.util.TispToastFactory;
import com.cking.phss.widget.GuidePager;

/**
 * 快速体检
 * com.cking.phss.view.JbxxBodyView
 * @author Administrator <br/>
 * create at 2012-9-16 上午11:25:10
 */
/**
 * @author Administrator
 * 
 */
public class JktjKstjPage extends LinearLayout implements IPage {
    @SuppressWarnings("unused")
    private static final String TAG = "JktjKstjPage";
    private static final boolean D = true;
    private Context mContext = null;

    private GuidePager mGuidePager = null;
    private Map<String, IBean> beanMap = new HashMap<String, IBean>();
    /**
     * 全局控件
     */
    private RadioGroup mTabRadios = null;
//    private Button mSaveButton = null;
//    private Button mResultButton = null;
//    private Button mUploadButton = null;
//    private Button mRegisterButton = null;
//    public static TextView mRegisterIdText = null;

    private Toast mToast = null;

    private static BluetoothServer4And mBluethoothServer = null;
    private static BluetoothClient4Bu34 mBluethoothClient = null;

    public static boolean sHasData = false;

    /**
     * 选择的TAB页编号
     */
    private static int sTabRadioId = 0;

    RadioGroup radiogroup_kstj;
    LinearLayout layout_content;

    /**
     * 快速体检的8个子页
     */
    JktjKstjPage01 mKstjPage01 = null;
    JktjKstjPage02 mKstjPage02 = null;
    JktjKstjPage03 mKstjPage03 = null;
    JktjKstjPage04 mKstjPage04 = null;
    JktjKstjPage05 mKstjPage05 = null;
    JktjKstjPage06 mKstjPage06 = null;
    JktjKstjPage07 mKstjPage07 = null;
    JktjKstjPage08 mKstjPage08 = null;

    /**
     * @param context
     */
    public JktjKstjPage(Context context, Map<String, IBean> beanMap) {
        super(context);
        mContext = context;
        this.beanMap = beanMap;
        init(context);
    }

    /**
     * @param context
     */
    private void init(final Context context) {
        mToast = TispToastFactory.getToast(context, "");
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.fragment_health_kstj_layout, this);

        loadPage(context, this);
    }

    /**
     * 设置第一界面的默认显示信息
     * 
     * @param context
     * @param viewGroup
     */
    public void loadPage(Context context, ViewGroup viewGroup) {
        // 添加页面
        radiogroup_kstj = (RadioGroup) findViewById(R.id.radiogroup_kstj);
        layout_content = (LinearLayout) findViewById(R.id.layout_content);

        mTabRadios = (RadioGroup) findViewById(R.id.tab_radio);
        mKstjPage01 = new JktjKstjPage01(mContext, beanMap);
        mKstjPage02 = new JktjKstjPage02(mContext, beanMap, JktjKstjPage.this);
        mKstjPage03 = new JktjKstjPage03(mContext, beanMap, JktjKstjPage.this);
        mKstjPage04 = new JktjKstjPage04(mContext, beanMap, JktjKstjPage.this);
        mKstjPage05 = new JktjKstjPage05(mContext, beanMap, JktjKstjPage.this);
        mKstjPage06 = new JktjKstjPage06(mContext, beanMap, JktjKstjPage.this);
        mKstjPage07 = new JktjKstjPage07(mContext, beanMap, JktjKstjPage.this);
        mKstjPage08 = new JktjKstjPage08(mContext, beanMap, JktjKstjPage.this);
        
        radiogroup_kstj.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
                RadioButton rb = (RadioButton) findViewById(checkedId);
                if (!rb.isChecked()) {
                    return;
                }
                JktjKstjPage.sTabRadioId = checkedId;
                int tag = Integer.parseInt((String) rb.getTag());
                switch (tag) {
                    case 0:
                        layout_content.removeAllViewsInLayout();
                        layout_content.addView(mKstjPage01);
                        break;

                    case 1:
                        layout_content.removeAllViewsInLayout();
                        layout_content.addView(mKstjPage02);
                        break;
                    case 2:
                        layout_content.removeAllViewsInLayout();
                        layout_content.addView(mKstjPage03);
                        break;

                    case 3:
                        layout_content.removeAllViewsInLayout();
                        layout_content.addView(mKstjPage04);
                        break;

                    case 4:
                        layout_content.removeAllViewsInLayout();
                        layout_content.addView(mKstjPage05);
                        break;

                    case 5:
                        layout_content.removeAllViewsInLayout();
                        layout_content.addView(mKstjPage06);
                        break;

                    case 6:
                        layout_content.removeAllViewsInLayout();
                        layout_content.addView(mKstjPage07);
                        break;

                    case 7:
                        layout_content.removeAllViewsInLayout();
                        layout_content.addView(mKstjPage08);
                        break;
                }
            }
        });
        radiogroup_kstj.check(R.id.radio_cgtj);
    }

    /**
     * @param strCHECKINID
     * @return
     */
    public String assemblePacket_SaveResults(String strCHECKINID) {
        String[][] kstjResults = getKstjResults();// new String[203][11];
        String result = "";
        String USERID = "";
        String CHECKINID = strCHECKINID;
        String CHECKDATE = "";
        String DOC_ID = "";
        Login1 login1 = MyApplication.getInstance().getSession().getLoginResult();
        if (login1 == null || login1.response == null) {
            mToast.setText("当前没有医生登录，请先登录！");
            mToast.show();
            return null;
        }
        DOC_ID = login1.response.doctorID;
        USERID = login1.response.userID;

        String head = "<Results>";
        result += head;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        CHECKDATE = dateFormat.format(new Date(System.currentTimeMillis()));
        CHECKINID = strCHECKINID;
        for (String[] kstjResult : kstjResults) {
            if (!StringUtil.isEmptyString(kstjResult[3])) { // 过滤空值
                int i = 0;
                String item = "<Item>" + "<USERID>" + USERID + "</USERID>" + "<CHECKINID>"
                        + CHECKINID + "</CHECKINID>" + "<DRIVERID>" + kstjResult[i++]
                        + "</DRIVERID>" + "<PROJECTCODE>" + kstjResult[i++] + "</PROJECTCODE>"
                        + "<PROJECTNAME>" + kstjResult[i++] + "</PROJECTNAME>" + "<RESULT>"
                        + kstjResult[i++] + "</RESULT>" + "<UNIT>" + kstjResult[i++] + "</UNIT>"
                        + "<REFERENCE>" + kstjResult[i++] + "</REFERENCE>" + "<EXCEPTIONTIPS>" + ""
                        + "</EXCEPTIONTIPS>" + "<TRUN>" + "" + "</TRUN>" + "<CHECKDATE>"
                        + CHECKDATE + "</CHECKDATE>" + "<DOC_ID>" + DOC_ID + "</DOC_ID>"
                        + DeviceUseFactory.getKstjDeviceUses(kstjResult[0]) + "</Item>";
                result += item;
            }
        }
        String tail = "</Results>";
        result += tail;
        Log.i(TAG, result);
        return result;
    }

    /**
     * @return
     */
    private String[][] getKstjResults() {
        Jktj_kstj k = (Jktj_kstj) beanMap.get(Jktj_kstj.class.getName());
        String[][] kstjResults = {
                { "01", "010001", "身高", k.getHeight(), "Cm", "" },
                { "01", "010002", "体重", k.getWeight(), "Kg", "" },
                { "01", "010003", "BMI", k.getbMI(), "", "18.5-24" },
                { "01", "010004", "身高体重结论", k.getCname(), "", "" },
                { "02", "020001", "收缩压", k.getsBP(), "mmHg", "" },
                { "02", "020002", "舒张压", k.getdBP(), "mmHg", "" },
                { "02", "020003", "脉搏", k.getxL(), "次/min", "" },
                // { "02", "020004", "平均压", "", "mmHg", "" },
                { "02", "020005", "血压结论", k.getxYJL(), "", "" },
                { "03", "030001", "血糖值", k.getxTValue(), "mmol/L", "" },
                { "03", "030002", "血糖结论", k.getxTJL(), "", "" },
                { "03", "030003", "血糖类型", StringUtil.isEmptyString(k.getxTValue()) ? "" : k.getxTCSTJ() + "", "", "" },
                // { "08", "080001", "健康评估", "", "分", "" },
                // { "08", "080002", "健康类型(结论)", "", "", "" },
                // { "08", "080003", "体脂肪量", k.getVisceralFat(), "Kg", "" },
                { "08", "080004", "体脂肪率", k.getFat(), "%", "" },
                // { "08", "080005", "体水分率", k.gettBW(), "%", "" },
                { "08", "080006", "基础代谢", k.getbMR(), "Kg", "" },
                // { "08", "080007", "脂肪控制", k.getrBMR(), "Kg", "" },
                // { "08", "080008", "肌肉控制", k.getMus(), "Kg", "" },
                // { "08", "080009", "体水分含量", k.gettBW(), "Kg", "" },
                // { "08", "080010", "蛋白质", "", "Kg", "" },
                // { "08", "080011", "无机盐", "", "Kg", "" },
                // { "08", "080012", "腰臀百分比", k.getYtb(), "", "" },
                // { "08", "080013", "体重控制", "", "Kg", "" },
                // { "08", "080014", "建议", "", "", "" },
                // { "08", "080015", "目标体重", "", "Kg", "" },
                { "08", "080016", "身高", k.getHeight(), "cm", "" },
                { "08", "080017", "体重", k.getWeight(), "Kg", "" },
                { "08", "080018", "BMI", k.getbMI(), "", "" },
                { "08", "080019", "阻抗", k.getZk(), "Ω", "" },
                { "08", "080020", "相对基础代谢", k.getrBMR(), "Kcal", "" },
                { "08", "080021", "内脏脂肪", k.getVisceralFat(), "级", "" },
                { "08", "080022", "肌肉含量", k.getMus(), "Kg", "" },
                { "08", "080023", "骨含量", k.getBone(), "Kg", "" },
                { "10", "100001", "心电图结论", k.getXdjl(), "", "" },
                { "10", "100002", "心率", k.getXdxl(), "次/分", "" },
                { "10", "100003", "P时限", k.getXdpsx(), "ms", "" },
                { "10", "100004", "PR间期", k.getXdprjq(), "ms", "" },
                { "10", "100005", "QRS时限", k.getXdqrssx(), "ms", "" },
                { "10", "100006", "QR间期", k.getXdqrjq(), "ms", "" },
                { "10", "100007", "Qtc间期", k.getXdqtcjq(), "ms", "" },
                { "10", "100008", "RV5振幅", k.getXdrv5zf(), "μV", "" },
                { "10", "100009", "RV6振幅", k.getXdrv6zf(), "μV", "" },
                { "10", "100010", "SV1振幅", k.getXdsv1zf(), "μV", "" },
                { "10", "100011", "SV2振幅", k.getXdsv2zf(), "μV", "" },
                { "10", "100012", "P电轴", k.getXdpdz(), "°", "" },
                { "10", "100013", "QRS电轴", k.getXdqrsdz(), "°", "" },
                { "10", "100014", "T电轴", k.getXdtdz(), "°", "" },
                { "12", "120001", "体质类型", "", "", "" },
                { "12", "120002", "体质偏向", "", "", "" },
                // { "14", "140001", "得分", "", "", "" },
                // { "14", "140002", "测评结果", "", "", "" },
                // { "14", "140003", "建议", "", "", "" },
                { "16", "160001", "甘油三酯值", k.getgYSZ(), "mmol/L", "" },
                { "16", "160002", "甘油三酯结论", k.getgYSZJL(), "", "" },
                { "17", "170001", "胆固醇值", k.getdGC(), "mmol/L", "" },
                { "17", "170002", "胆固醇结论", k.getdGCJL(), "", "" },
                { "18", "180001", "胸围", k.getBust(), "cm", "" },
                { "18", "180002", "腰围", k.getWaist(), "cm", "" },
                { "18", "180003", "臀围", k.gethIP(), "cm", "" },
                { "18", "180004", "腰臀比", k.getYtb(), "", "" },
                { "18", "180005", "腰围结论", k.getYtbjl(), "", "" },
                // { "18", "180006", "头围", "", "cm", "" },
                { "18", "180007", "腹围", "", "cm", "" },
                // { "18", "180008", "颈围", "", "cm", "" },
                { "19", "190001", "体温", k.getTw(), "℃", "" },
                // { "19", "190002", "呼吸频率", "", "", "" },
                { "19", "190003", "体温结论", k.getTwjl(), "", "" },
                { "20", "200001", "高密度值", k.getHDL(), "mmol/L", "" },
                { "20", "200002", "高密度结论", k.getHDLR(), "", "" },
                { "21", "210001", "低密度值", k.getLDL(), "mmol/L", "" },
                { "21", "210002", "低密度结论", k.getLDLR(), "", "" },
                { "24", "240001", "尿酸值", k.getNS(), "mmol/L", "" },
                { "24", "240098", "尿酸结论", k.getNSR(), "", "" },
                // { "24", "240099", "尿酸建议", "", "", "" },
                { "25", "250001", "饱和度", k.getXybhd(), "%", "" },
                { "25", "250002", "脉率", k.getXyml(), "次/分", "" },
                { "25", "250003", "血氧结论", k.getXyjl(), "", "" },
                { "26", "260001", "亚硝酸盐半定量结果", StringUtil.isEmptyString(k.yxsyjl) ? "" : k.yxsybdljg, "", "" },
                { "26", "260002", "亚硝酸盐结论", k.yxsyjl, "", "" },
                { "26", "260003", "白细胞值", k.bxbz, "cells/ul", "" },
                { "26", "260004", "白细胞半定量结果", StringUtil.isEmptyString(k.bxbjl) ? "" : k.bxbbdljg, "", "" },
                { "26", "260005", "白细胞结论", k.bxbjl, "", "" },
                { "26", "260006", "尿胆原值", k.ndyz, "umol/L", "" },
                { "26", "260007", "尿胆原结论", k.ndyjl, "", "" },
                { "26", "260008", "尿蛋白值", k.ndbz, "g/L", "" },
                { "26", "260009", "尿蛋白半定量结果", StringUtil.isEmptyString(k.ndbjl) ? "" : k.ndbbdljg, "", "" },
                { "26", "260010", "尿蛋白结论", k.ndbjl, "", "" },
                { "26", "260011", "潜血值", k.qxz, "cells/ul", "" },
                { "26", "260012", "潜血半定量结果", StringUtil.isEmptyString(k.qxjl) ? "" : k.qxbdljg, "", "" },
                { "26", "260013", "潜血结论", k.qxjl, "", "" },
                { "26", "260014", "尿酮体值", k.nttz, "mmol/L", "" },
                { "26", "260015", "尿酮体半定量结果", StringUtil.isEmptyString(k.nttjl) ? "" : k.nttbdljg, "", "" },
                { "26", "260016", "尿酮体结论", k.nttjl, "", "" },
                //{ "26", "260017", "胆红素值", k.dhsz, "mg/L", "" },
                { "26", "260018", "胆红素半定量结果", StringUtil.isEmptyString(k.dhsjl) ? "" : k.dhsbdljg, "mg/L", "" },
                { "26", "260019", "胆红素结论", k.dhsjl, "", "" },
                { "26", "260020", "尿糖值", k.ntz, "mmol/L", "" },
                { "26", "260021", "尿糖半定量结果", StringUtil.isEmptyString(k.ntjl) ? "" : k.ntbdljg, "", "" },
                { "26", "260022", "尿糖结论", k.ntjl, "", "" },
                { "26", "260023", "维生素C值", k.wsscz, "mmol/L", "" },
                { "26", "260024", "维生素半定量结果", StringUtil.isEmptyString(k.wsscjl) ? "" : k.wssbdljg, "", "" },
                { "26", "260025", "维生素C结论", k.wsscjl, "", "" },
                { "26", "260026", "PH值", k.phz, "", "" },
                { "26", "260027", "PH结论", k.phjl, "", "" },
                { "26", "260026", "尿比重", k.nbz, "", "" },
                { "26", "260027", "尿比重结论", k.nbzjl, "", "" },
        };
        return kstjResults;
    }

    // 快速体检数据绑定
    class MyKstjPagerAdapter extends PagerAdapter {
        private Context c;
        List<View> mPageList = new ArrayList<View>();

        public MyKstjPagerAdapter(Context c) {
            super();
            this.c = c;
            // 添加页面
            mKstjPage01 = new JktjKstjPage01(c, beanMap);
            mPageList.add(mKstjPage01);
            mKstjPage02 = new JktjKstjPage02(c, beanMap, JktjKstjPage.this);
            mPageList.add(mKstjPage02);
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

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Log.i(TAG, "onDetachedFromWindow");
        FileLog.i(TAG, "onDetachedFromWindow");
        /**
         * 关闭蓝牙设备
         */
        if (mBluethoothServer != null)
            mBluethoothServer.close();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.i(TAG, "onAttachedToWindow");
        FileLog.i(TAG, "onAttachedToWindow");

        /**
         * 打开蓝牙设备
         */
        if (mBluethoothServer != null) {
            mBluethoothServer.open();
        }
    }

    @Override
    public void setValue() {
        // TODO Auto-generated method stub
        mKstjPage01.setValue();
        mKstjPage02.setValue();
        mKstjPage03.setValue();
        mKstjPage04.setValue();
        mKstjPage05.setValue();
        mKstjPage06.setValue();
        mKstjPage07.setValue();
        mKstjPage08.setValue();
    }

    @Override
    public boolean getValue() {
        // TODO Auto-generated method stub
        if (mKstjPage01.getValue() && mKstjPage02.getValue() && mKstjPage03.getValue()
                & mKstjPage04.getValue() && mKstjPage05.getValue() && mKstjPage06.getValue()
                && mKstjPage07.getValue() && mKstjPage08.getValue()) {

        }
        return true;
    }

    public void getBeanFromDB() {
        if (beanMap == null)
            return;
        List<Class<? extends IBean>> listBean = new ArrayList<Class<? extends IBean>>();
        listBean.add(Jmjbxx.class);
        BeanUtil.getInstance().getJbxxFromDb(listBean, new BeanUtil.OnResultFromDb() {
            @Override
            public void onResult(List<IBean> listBean, boolean isSucc) {
                if (listBean == null || listBean.size() < 0)
                    return;
                // if (isSucc) {
                beanMap.put(Jmjbxx.class.getName(), listBean.get(0));
                setValue();
                // }
            }
        });

    }

    public void save() {
        getValue();
        
        sHasData = checkHasValidData();

        if (!sHasData) {
            mToast.setText("请填写至少一组数据！");
            mToast.show();
            return;
        }
        List<IBean> listBeans = new ArrayList<IBean>();
        listBeans.add(beanMap.get(Jktj_kstj.class.getName()));
        BeanUtil.getInstance().saveJktjToDb(listBeans, new Date().getTime(),
                new BeanUtil.OnResultSaveToDb() {
                    @Override
                    public void onResult(List<SaveToDbResult> listBean, boolean isSucc) {
                        if (isSucc) {
                            mToast.setText("快速体检数据保存成功！");
                            mToast.show();
                        } else {
                            mToast.setText("快速体检数据保存失败！");
                            mToast.show();
                        }

                        SaveToDbResult result = listBean.get(0);
                        // 操作标志 1-原始 2-新建 3-修改
                        int operType = result.isAdd ? 2 : 3;
                        // 保存本地记录，所有体检数据保存都是非档案平台
                        BeanUtil.getInstance().saveLocalRecord(mContext,
                                (Jmjbxx) beanMap.get(Jmjbxx.class.getName()), result.projectUUID,
                                "31", 2, operType,
                                new Date().getTime(), Jktj_kstj.class.getName());
                    }
                });

    }

    /**
     * TODO 待完善
     * @return
     */
    protected boolean checkHasValidData() {
        int count = 0;
        count += mKstjPage01.getValidDataCount();
        count += mKstjPage02.getValidDataCount();
        count += mKstjPage03.getValidDataCount();
        count += mKstjPage04.getValidDataCount();
        count += mKstjPage05.getValidDataCount();
        count += mKstjPage06.getValidDataCount();
        count += mKstjPage07.getValidDataCount();
        count += mKstjPage08.getValidDataCount();
        
        if (count <= 0) {
            return false;
        } else {
            return true;
        }
    }

    public void clear() {
        if (mKstjPage01 == null || mKstjPage02 == null) {
            return;
        }
        mKstjPage01.clear();
        mKstjPage02.clear();
    }

    /**
     * @param index
     */
    public void showItemByIndex(int index) {
    }

    /**
     * @param strCHECKINID 
     * 
     */
    public void print(String strCHECKINID) {
        getValue();

        sHasData = checkHasValidData();

        if (!sHasData) {
            mToast.setText("请填写至少一组数据！");
            mToast.show();
            return;
        }

        Jmjbxx mJmjbxx = (Jmjbxx) beanMap.get(Jmjbxx.class.getName());
        mToast.setText("正在准备打印，请稍候...");
        mToast.show();
        Jktj_kstj jktjKstj = (Jktj_kstj) beanMap.get(Jktj_kstj.class.getName());
        if (mJmjbxx == null || mJmjbxx.getResidentName().equals("")
                || mJmjbxx.getPaperNum().equals("")) {
            mToast.setText(R.string.toast_info_none_resident);
            mToast.show();
        } else {
            PrinterUtil.printJktj(mContext, mJmjbxx, jktjKstj, strCHECKINID);
        }
    }

    /**
     * 
     */
    public void upload(String strCHECKINID) {
        
        getValue();

        sHasData = checkHasValidData();
        
        if (!sHasData) {
            mToast.setText("请填写至少一组数据！");
            mToast.show();
            return;
        }

        Jmjbxx mJmjbxx = (Jmjbxx) beanMap.get(Jmjbxx.class.getName());
        Jktj_kstj mJktj_kstj = (Jktj_kstj) beanMap.get(Jktj_kstj.class.getName());
        // 保存结果
        if (strCHECKINID.equals("")) {
            mToast.setText("无法获取体检编号！");
            mToast.show();
            return;
        } else {
            String strSaveResults = assemblePacket_SaveResults(strCHECKINID);
            FileLog.i(TAG, "Kstj Result >>>>>\r\n" + strSaveResults);
            if(strSaveResults==null)
                return;
            WebService.getInstance().excute("SaveResults", "data", strSaveResults, new ISoapRecv() {

                @Override
                public void onRecvData(String xmlStr, boolean success) {
                    Log.v(TAG, "Result >>>>>" + xmlStr + "code: " + success);
                    if (success) {
                        mToast.setText("上传快速体检数据成功！");
                        mToast.show();
                    } else {
                        mToast.setText("上传快速体检数据失败！");
                        mToast.show();
                    }
                }
                
            });
        }
    }

    /**
     * 
     */
    public void updateResult() {
        
        getValue();

        sHasData = checkHasValidData();
        
        if (!sHasData) {
            mToast.setText("请填写至少一组数据！");
            mToast.show();
            return;
        }

        setValue();
    }

}
