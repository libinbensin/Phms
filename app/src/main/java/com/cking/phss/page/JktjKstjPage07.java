/* Cking Inc. (C) 2012. All rights reserved.
 *
 * JbxxPage01.java
 * classes : com.cking.phss.view.JbxxBodyView
 * @author Administrator
 * V 1.0.0
 * Create at 2012-9-16 上午11:25:10
 */
package com.cking.phss.page;

import java.util.List;
import java.util.Map;

import lifesense.ble.bean.WeightData;
import net.xinhuaxing.eshow.constants.Constants;
import net.xinhuaxing.util.StringUtil;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cking.phss.R;
import com.cking.phss.bean.IBean;
import com.cking.phss.bean.Jktj_kstj;
import com.cking.phss.bean.Jmjbxx;
import com.cking.phss.bluetooth.lifesense.ble.ui.AddDeviceActivity;
import com.cking.phss.global.Global;
import com.cking.phss.util.AppConfigFactory.AppConfig;
import com.cking.phss.util.CalendarUtil;
import com.cking.phss.util.DecimalsTextWatcher;
import com.cking.phss.util.JgxxConfigFactory;
import com.cking.phss.util.TestResultUtil;
import com.cking.phss.util.TispToastFactory;
import com.cking.phss.xml4jgxx.tags.constants.TagConstants;


/**
 * 健康体检快速体检第2页 com.cking.phss.view.JktjKstjPage02
 * 
 * @author Administrator <br/>
 *         create at 2012-9-16 上午11:25:10
 */
public class JktjKstjPage07 extends LinearLayout implements IPage, ITestPage {
    @SuppressWarnings("unused")
    private static final String TAG = "JktjKstjPage02";
    private Context mContext = null;
    private Map<String, IBean> beanMap = null;
    /**
     * 第二页控件
     */

    // 人体成分
    public EditText sgEditText = null;
    public EditText tzEditText = null;
    public EditText bmiEditText = null;
    public EditText zkEditText = null;// 阻抗
    public EditText tzflEditText = null;// 体脂肪率
    public EditText nzzfEditText = null;// 内脏脂肪
    public EditText jcdxEditText = null;// 基础代谢
    public EditText xdjcdxEditText = null;// 相对基础代谢
    public EditText jrhlEditText = null;// 肌肉含量
    public EditText sfhlEditText = null;// 水分含量
    public EditText ghlEditText = null;// 骨含量
    public EditText stlxEditText = null;// 身体类型
    TextView tsxxTextView;
    
    public Button hqsjButton = null;// 人体成分获取数据的按钮

    public ViewGroup mParent;
    private Toast mToast = null;
    
    private int deviceId = 0;

    public String cName="";//健康建议
    /**
     * @param context
     */
    public JktjKstjPage07(Context context, Map<String, IBean> beanMap, ViewGroup parent) {
        super(context);
        this.beanMap = beanMap;
        this.mParent = parent;
        init(context);
    }

    /**
     * @param context
     * @param attrs
     */
    public JktjKstjPage07(Context context, AttributeSet attrs) {
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
        inflater.inflate(R.layout.fragment_health_kstj_rtcf_layout, this);
        deviceId = JgxxConfigFactory.findIdByName(mContext, TagConstants.XML_VALUE_NAME_RTCFY);

        loadPage(context, this);
    }

    public void loadPage(Context context, ViewGroup viewGroup) {
        sgEditText = (EditText) findViewById(R.id.sgEditText);
        tzEditText = (EditText) findViewById(R.id.tzEditText);
        bmiEditText = (EditText) findViewById(R.id.bmiEditText);
        zkEditText = (EditText) findViewById(R.id.zkEditText);
        tzflEditText = (EditText) findViewById(R.id.tzflEditText);
        nzzfEditText = (EditText) findViewById(R.id.nzzfEditText);
        jcdxEditText = (EditText) findViewById(R.id.jcdxEditText);
        xdjcdxEditText = (EditText) findViewById(R.id.xdjcdxEditText);
        sfhlEditText = (EditText) findViewById(R.id.sfhlEditText);
        jrhlEditText = (EditText) findViewById(R.id.jrhlEditText);
        ghlEditText = (EditText) findViewById(R.id.ghlEditText);
        stlxEditText = (EditText) findViewById(R.id.stlxEditText);
        tsxxTextView = (TextView) findViewById(R.id.tsxxTextView);
        tsxxTextView.setText("1、人体成分分析前，确保已做完身高、体重、腰围等项目；\n" + "2、正式测量时请先脱去袜子，并保持脚底的清洁；\n"
                + "3、确认仪器已处于成分分析模式；\n" + "4、测试时，双脚分别站立在两侧电极位置，并保持上身稳定；\n"
                + "5、25秒左右出现数据传输的标志，表示本次测量完成；\n" + "6、随即，在平板电脑【人体成分】页面上点击【获取数据】按钮；\n"
                + "7、获取数据后，点击【保存】和【上传】按钮即完成人体成分分析。");
        
        hqsjButton = (Button) findViewById(R.id.hqsjButton);

        ghlEditText.addTextChangedListener(DecimalsTextWatcher.getInstance());
        stlxEditText.addTextChangedListener(DecimalsTextWatcher.getInstance());
        jrhlEditText.addTextChangedListener(DecimalsTextWatcher.getInstance());
        sfhlEditText.addTextChangedListener(DecimalsTextWatcher.getInstance());
        zkEditText.addTextChangedListener(DecimalsTextWatcher.getInstance());
        tzflEditText.addTextChangedListener(DecimalsTextWatcher.getInstance());
        nzzfEditText.addTextChangedListener(DecimalsTextWatcher.getInstance());
        xdjcdxEditText.addTextChangedListener(DecimalsTextWatcher.getInstance());
        jcdxEditText.addTextChangedListener(DecimalsTextWatcher.getInstance());
        bmiEditText.addTextChangedListener(DecimalsTextWatcher.getInstance());

        if (JgxxConfigFactory.findDeviceInfoTagByName(mContext, TagConstants.XML_VALUE_NAME_RTCFY) == null) {
            hqsjButton.setEnabled(false);
        }
        hqsjButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (deviceId == TagConstants.XML_VALUE_ID_LX_BZ) {
                    getResultFromDeviceLxBz();
                } else {
                    mToast.setText("系统暂不支持此设备");
                    mToast.show();
                }
            }
        });

        sgEditText.addTextChangedListener(sgTextWatcher);
        tzEditText.addTextChangedListener(tzTextWatcher);
        bmiEditText.addTextChangedListener(rtcfTextWatcher);
        zkEditText.addTextChangedListener(rtcfTextWatcher);
        sgEditText.addTextChangedListener(rtcfTextWatcher);
        tzEditText.addTextChangedListener(rtcfTextWatcher);
        sgEditText.addTextChangedListener(bmiTextWatcher);
        tzEditText.addTextChangedListener(bmiTextWatcher);
        AppConfig appConfig = (AppConfig) beanMap.get(AppConfig.class.getName());
        if (appConfig != null) {
            String value = appConfig.getTjsrxz();
            if (value != null) {
                sgEditText.setEnabled(value.contains("人体成分") ? false : true);
                tzEditText.setEnabled(value.contains("人体成分") ? false : true);
                bmiEditText.setEnabled(value.contains("人体成分") ? false : true);
                zkEditText.setEnabled(value.contains("人体成分") ? false : true);
                jcdxEditText.setEnabled(value.contains("人体成分") ? false : true);
                xdjcdxEditText.setEnabled(value.contains("人体成分") ? false : true);
                tzflEditText.setEnabled(value.contains("人体成分") ? false : true);
                nzzfEditText.setEnabled(value.contains("人体成分") ? false : true);
                jrhlEditText.setEnabled(value.contains("人体成分") ? false : true);
                sfhlEditText.setEnabled(value.contains("人体成分") ? false : true);
                ghlEditText.setEnabled(value.contains("人体成分") ? false : true);
                stlxEditText.setEnabled(value.contains("人体成分") ? false : true);
            }
        }
    }

    /**
     * 
     */
    protected void getResultFromDeviceLxBz() {
        Intent showAddDeviceIntent = new Intent(mContext, AddDeviceActivity.class);
        ((Activity) mContext).startActivityForResult(showAddDeviceIntent, Constants.PAGE_JKTJ);
    }

    @Override
    public void setValue() {
        Jktj_kstj mJktj_kstj = (Jktj_kstj) beanMap.get(Jktj_kstj.class.getName());
        if (mJktj_kstj == null)
            return;
        Jmjbxx mJmjbxx = (Jmjbxx) beanMap.get(Jmjbxx.class.getName());
        if (mJmjbxx == null)
            return;

        // 对于乐心体脂称的处理
        if (deviceId == TagConstants.XML_VALUE_ID_LX_BZ) {
            getValueByWeightData();
        }

        // 人体成分
        sgEditText.setText(mJktj_kstj.getHeight());
        tzEditText.setText(mJktj_kstj.getWeight());
        bmiEditText .setText(mJktj_kstj.getbMI());
        zkEditText .setText(mJktj_kstj.getiMP());
        tzflEditText .setText(mJktj_kstj.getFat());
        nzzfEditText .setText(mJktj_kstj.getVisceralFat());
        jcdxEditText.setText(mJktj_kstj.getbMR());
        xdjcdxEditText.setText(mJktj_kstj.getrBMR());
        jrhlEditText.setText(mJktj_kstj.gettBW());
        sfhlEditText.setText(mJktj_kstj.getMus());
        ghlEditText.setText(mJktj_kstj.getBone());
        stlxEditText .setText(mJktj_kstj.getCtype());
    }

    /**
     * 
     */
    private boolean getValueByWeightData() {
        Jktj_kstj mJktj_kstj = (Jktj_kstj) beanMap.get(Jktj_kstj.class.getName());
        if (mJktj_kstj == null)
            return false;

        Jmjbxx mJmjbxx = (Jmjbxx) beanMap.get(Jmjbxx.class.getName());
        if (mJmjbxx == null)
            return false;
        
        @SuppressWarnings("unchecked")
        List<WeightData> wDataList = (List<WeightData>) Global.lxReturnHashMap
                .get("WeightDataList");


        if (wDataList != null && wDataList.size() > 0) {
            getValue();

            WeightData wData = wDataList.get(wDataList.size() - 1);

            Log.i(TAG, "flag: " + wData.getFlag());

            String ywStr = mJktj_kstj.getWaist();
            Log.i(TAG, "ywStr = " + ywStr);
            if (StringUtil.isEmptyString(ywStr)) {
                ywStr = "80";
            }

            int sex = mJmjbxx.getSexCD();
            Log.i(TAG, "sex = " + sex);
            int age = CalendarUtil.getAge(mJmjbxx.getBirthDay());
            Log.i(TAG, "age = " + age);

            String heightStr = mJktj_kstj.getHeight();

            try {
                if (StringUtil.isEmptyString(ywStr)) {
                    return true;
                }

                double height = Double.parseDouble(heightStr.trim()) / 100.0;
                Log.i(TAG, "height = " + height);
                double weight = wData.getWeight();
                Log.i(TAG, "weight = " + weight);
                double BMI = TestResultUtil.getBmiResult(height, weight);
                Log.i(TAG, "BMI = " + BMI);
                double waistline = Double.parseDouble(ywStr);
                Log.i(TAG, "waistline = " + waistline);
                double IMP = wData.getResistance_2();
                Log.i(TAG, "IMP = " + IMP);

                double tzfl = TestResultUtil.getFat(sex, age, height, weight, waistline, IMP);
                Log.i(TAG, "tzfl = " + tzfl);
                double nzzf = TestResultUtil.getVisceralFat(sex, age, BMI, IMP);
                Log.i(TAG, "nzzf = " + nzzf);
                double jcdx = TestResultUtil.getBMR(sex, age, height, weight);
                Log.i(TAG, "jcdx = " + jcdx);
                double xdjcdx = TestResultUtil.getRBMR(jcdx);
                Log.i(TAG, "xdjcdx = " + xdjcdx);
                double jrhl = TestResultUtil.getMus(sex, age, height, weight, IMP);
                Log.i(TAG, "jrhl = " + jrhl);
                double sfhl = TestResultUtil.getTBW(sex, age, height, weight, waistline, IMP);
                Log.i(TAG, "sfhl = " + sfhl);
                double ghl = TestResultUtil.getBone(sex, age, height, weight, IMP);
                Log.i(TAG, "ghl = " + ghl);
                String stlx = TestResultUtil.getBodyType(sex, age, tzfl);
                Log.i(TAG, "stlx = " + stlx);

                // 体重
                mJktj_kstj.setWeight(StringUtil.formatDecimal(wData.getWeight()));
                // BMI
                mJktj_kstj.setbMI(StringUtil.formatDecimal(BMI));
                // 阻抗
                mJktj_kstj.setiMP(StringUtil.formatDecimal(wData.getResistance_2()));
                // 体脂肪率
                mJktj_kstj.setFat(StringUtil.formatDecimal1(tzfl));

                // 内脏脂肪
                mJktj_kstj.setVisceralFat(StringUtil.formatDecimal0(nzzf));

                // 基础代谢
                mJktj_kstj.setbMR(StringUtil.formatDecimal1(jcdx));

                // 相对基础代谢
                mJktj_kstj.setrBMR(StringUtil.formatDecimal1(xdjcdx));//

                // 水分含量
                mJktj_kstj.settBW(StringUtil.formatDecimal1(sfhl));

                // 肌肉含量
                mJktj_kstj.setMus(StringUtil.formatDecimal1(jrhl));

                // 骨含量
                mJktj_kstj.setBone(StringUtil.formatDecimal1(ghl));

                // 身体类型
                mJktj_kstj.setCtype(stlx + "");//

            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean getValue() {
        Jktj_kstj mJktj_kstj = (Jktj_kstj) beanMap.get(Jktj_kstj.class.getName());
        if (mJktj_kstj == null)
            return false;
        
        // 人体成分数据的获取
        /**
         * 屏蔽代码说明： 由于需求规定常规检测和人体成分中身高体重数据同步 如果在这里获取值的话会造成如下问题：
         * 当在常规检测中输入值由于被人体成分中的覆盖，造成 最后值没变。
         */
        // String sg = sgEditText.getText().toString().trim();// 身高
        // mJktj_kstj.setHeight(sg);
        //
        // String tz = tzEditText.getText().toString().trim();// 体重
        // mJktj_kstj.setWeight(tz);
        //
        // // bmi
        // String bmi = bmiEditText.getText().toString().trim();
        // mJktj_kstj.setbMI(bmi);

        // 阻抗
        String zk = zkEditText.getText().toString().trim();
        mJktj_kstj.setiMP(zk);

        // 体脂肪率
        String tzfl = tzflEditText.getText().toString().trim();
        mJktj_kstj.setFat(tzfl);

        // 内脏脂肪
        String nzzf = nzzfEditText.getText().toString().trim();
        mJktj_kstj.setVisceralFat(nzzf);

        // 基础代谢
        String bmr = jcdxEditText.getText().toString().trim();
        mJktj_kstj.setbMR(bmr);

        // 相对基础代谢
        String rbmr = xdjcdxEditText.getText().toString().trim();
        mJktj_kstj.setrBMR(rbmr);

        // 水分含量
        String sfhl = sfhlEditText.getText().toString().trim();
        mJktj_kstj.settBW(sfhl);

        // 肌肉含量
        String jrhl = jrhlEditText.getText().toString().trim();
        mJktj_kstj.setMus(jrhl);

        // 骨含量
        String ghl = ghlEditText.getText().toString().trim();
        mJktj_kstj.setBone(ghl);

        // 身体类型
        String stlx = stlxEditText.getText().toString().trim();
        mJktj_kstj.setCtype(stlx);
        
        //健康建议
        mJktj_kstj.setCname(cName);
        
        return true;
    }

    public void clear(){
        // 人体成分
          sgEditText .setText("");
          tzEditText.setText("");
          bmiEditText .setText("");
          zkEditText .setText("");
          tzflEditText .setText("");
          nzzfEditText .setText("");
          jcdxEditText.setText("");
          xdjcdxEditText.setText("");
          jrhlEditText.setText("");
          sfhlEditText.setText("");
          ghlEditText.setText("");
          stlxEditText .setText("");
   }

    /* (non-Javadoc)
     * @see com.cking.phss.page.ITestPage#getValidDataCount()
     */
    @Override
    public int getValidDataCount() {
        int count  = 0;
        String result = sgEditText.getText().toString().trim();
        if (StringUtil.isDecimal(result)) {
            count++;
        }
        result = tzEditText.getText().toString().trim();
        if (StringUtil.isDecimal(result)) {
            count++;
        }
        result = bmiEditText.getText().toString().trim();
        if (StringUtil.isDecimal(result)) {
            count++;
        }
        result = zkEditText.getText().toString().trim();
        if (StringUtil.isDecimal(result)) {
            count++;
        }
        result = tzflEditText.getText().toString().trim();
        if (StringUtil.isDecimal(result)) {
            count++;
        }
        result = nzzfEditText.getText().toString().trim();
        if (StringUtil.isDecimal(result)) {
            count++;
        }
        result = jcdxEditText.getText().toString().trim();
        if (StringUtil.isDecimal(result)) {
            count++;
        }
        result = xdjcdxEditText.getText().toString().trim();
        if (StringUtil.isDecimal(result)) {
            count++;
        }
        result = jrhlEditText.getText().toString().trim();
        if (StringUtil.isDecimal(result)) {
            count++;
        }
        result = sfhlEditText.getText().toString().trim();
        if (StringUtil.isDecimal(result)) {
            count++;
        }
        result = ghlEditText.getText().toString().trim();
        if (StringUtil.isDecimal(result)) {
            count++;
        }
        result = stlxEditText.getText().toString().trim();
        if (StringUtil.isDecimal(result)) {
            count++;
        }
        return count;
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        /**
         * 由于需求规定常规检测和人体成分中身高体重数据同步 添加此代码后切换tab页显示数据也会同步
         */
        Jktj_kstj mJktj_kstj = (Jktj_kstj) beanMap.get(Jktj_kstj.class.getName());
        if (mJktj_kstj == null)
            return;

        sgEditText.setText(mJktj_kstj.getHeight());
        tzEditText.setText(mJktj_kstj.getWeight());
    }

    // 身高的监听
    DecimalsTextWatcher sgTextWatcher = new DecimalsTextWatcher(3) {
        @Override
        public void afterTextChanged(Editable s) {
            super.afterTextChanged(s);
            Jktj_kstj mJktj_kstj = (Jktj_kstj) beanMap.get(Jktj_kstj.class.getName());
            if (mJktj_kstj == null)
                return;

            // 身高体重实时更新到全局变量，为切换到常规体检而修改
            mJktj_kstj.setHeight(s.toString());
        }
    };

    // 体重的监听
    DecimalsTextWatcher tzTextWatcher = new DecimalsTextWatcher(3) {
        @Override
        public void afterTextChanged(Editable s) {
            super.afterTextChanged(s);
            Jktj_kstj mJktj_kstj = (Jktj_kstj) beanMap.get(Jktj_kstj.class.getName());
            if (mJktj_kstj == null)
                return;

            // 身高体重实时更新到全局变量，为切换到常规体检而修改
            mJktj_kstj.setWeight(s.toString());
        }
    };

    // 根据身高体重得出BMI和结论
    DecimalsTextWatcher bmiTextWatcher = new DecimalsTextWatcher(2) {
        @Override
        public void afterTextChanged(Editable s) {
            super.afterTextChanged(s);
            String sg = sgEditText.getText().toString();
            String tz = tzEditText.getText().toString();
            try {
                if (StringUtil.isEmptyString(sg) || StringUtil.isEmptyString(tz)) {
                    bmiEditText.setText("");
                    return;
                }
                double dsg = Double.parseDouble(sg) / 100.0;
                double dtz = Double.parseDouble(tz);
                double bmi = TestResultUtil.getBmiResult(dsg, dtz);
                bmiEditText.setText(StringUtil.formatDecimal(bmi));
                Jktj_kstj mJktj_kstj = (Jktj_kstj) beanMap.get(Jktj_kstj.class.getName());
                if (mJktj_kstj != null) {
                    mJktj_kstj.setbMI(StringUtil.formatDecimal(bmi));
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }

        }
    };

    // 其他的监听
    DecimalsTextWatcher rtcfTextWatcher = new DecimalsTextWatcher(3) {
        @Override
        public void afterTextChanged(Editable s) {
            super.afterTextChanged(s);
            Jktj_kstj mJktj_kstj = (Jktj_kstj) beanMap.get(Jktj_kstj.class.getName());
            if (mJktj_kstj == null)
                return;

            String ywStr = mJktj_kstj.getWaist();
            Log.i(TAG, "ywStr = " + ywStr);

            Jmjbxx mJmjbxx = (Jmjbxx) beanMap.get(Jmjbxx.class.getName());
            if (mJmjbxx == null)
                return;

            int sex = mJmjbxx.getSexCD();
            Log.i(TAG, "sex = " + sex);
            int age = CalendarUtil.getAge(mJmjbxx.getBirthDay());
            Log.i(TAG, "age = " + age);

            String heightStr = sgEditText.getText().toString();
            String weigthStr = tzEditText.getText().toString();
            String bmiStr = bmiEditText.getText().toString();
            String impStr = zkEditText.getText().toString();

            try {

                if (StringUtil.isEmptyString(bmiStr)) {
                    tzflEditText.setText(""); // 体脂肪率
                    nzzfEditText.setText(""); // 内脏脂肪
                    jcdxEditText.setText(""); // 基础代谢
                    xdjcdxEditText.setText(""); // 相对基础代谢
                    jrhlEditText.setText(""); // 肌肉含量
                    sfhlEditText.setText(""); // 水分含量
                    ghlEditText.setText(""); // 骨含量
                    stlxEditText.setText(""); // 身体类型
                    return;
                }

                if (StringUtil.isEmptyString(impStr)) {
                    tzflEditText.setText(""); // 体脂肪率
                    nzzfEditText.setText(""); // 内脏脂肪
                    jcdxEditText.setText(""); // 基础代谢
                    xdjcdxEditText.setText(""); // 相对基础代谢
                    jrhlEditText.setText(""); // 肌肉含量
                    sfhlEditText.setText(""); // 水分含量
                    ghlEditText.setText(""); // 骨含量
                    stlxEditText.setText(""); // 身体类型
                    return;
                }

                if (StringUtil.isEmptyString(ywStr)) {
                    tzflEditText.setText(""); // 体脂肪率
                    nzzfEditText.setText(""); // 内脏脂肪
                    jcdxEditText.setText(""); // 基础代谢
                    xdjcdxEditText.setText(""); // 相对基础代谢
                    jrhlEditText.setText(""); // 肌肉含量
                    sfhlEditText.setText(""); // 水分含量
                    ghlEditText.setText(""); // 骨含量
                    stlxEditText.setText(""); // 身体类型
                    return;
                }

                double height = Double.parseDouble(heightStr.trim()) / 100.0;
                Log.i(TAG, "height = " + height);
                double weight = Double.parseDouble(weigthStr.trim());
                Log.i(TAG, "weight = " + weight);
                double waistline = Double.parseDouble(ywStr);
                Log.i(TAG, "waistline = " + waistline);
                double BMI = Double.parseDouble(bmiStr.trim());
                Log.i(TAG, "BMI = " + BMI);
                double IMP = Double.parseDouble(impStr.trim());
                Log.i(TAG, "IMP = " + IMP);

                double tzfl = TestResultUtil.getFat(sex, age, height, weight, waistline, IMP);
                Log.i(TAG, "tzfl = " + tzfl);
                double nzzf = TestResultUtil.getVisceralFat(sex, age, BMI, IMP);
                Log.i(TAG, "nzzf = " + nzzf);
                double jcdx = TestResultUtil.getBMR(sex, age, height, weight);
                Log.i(TAG, "jcdx = " + jcdx);
                double xdjcdx = TestResultUtil.getRBMR(jcdx);
                Log.i(TAG, "xdjcdx = " + xdjcdx);
                double jrhl = TestResultUtil.getMus(sex, age, height, weight, IMP);
                Log.i(TAG, "jrhl = " + jrhl);
                double sfhl = TestResultUtil.getTBW(sex, age, height, weight, waistline, IMP);
                Log.i(TAG, "sfhl = " + sfhl);
                double ghl = TestResultUtil.getBone(sex, age, height, weight, IMP);
                Log.i(TAG, "ghl = " + ghl);
                String stlx = TestResultUtil.getBodyType(sex, age, tzfl);
                Log.i(TAG, "stlx = " + stlx);

                tzflEditText.setText(StringUtil.formatDecimal1(tzfl)); // 体脂肪率
                nzzfEditText.setText(StringUtil.formatDecimal0(nzzf)); // 内脏脂肪
                jcdxEditText.setText(StringUtil.formatDecimal1(jcdx)); // 基础代谢
                xdjcdxEditText.setText(StringUtil.formatDecimal1(xdjcdx)); // 相对基础代谢
                jrhlEditText.setText(StringUtil.formatDecimal1(jrhl)); // 肌肉含量
                sfhlEditText.setText(StringUtil.formatDecimal1(sfhl)); // 水分含量
                ghlEditText.setText(StringUtil.formatDecimal1(ghl)); // 骨含量
                stlxEditText.setText(stlx + ""); // 身体类型
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }

        }
    };
}
