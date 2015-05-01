/* Cking Inc. (C) 2012. All rights reserved.
 *
 * JbxxPage01.java
 * classes : com.cking.phss.view.JbxxBodyView
 * @author Administrator
 * V 1.0.0
 * Create at 2012-9-16 上午11:25:10
 */
package com.cking.phss.page;

import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import net.xinhuaxing.util.StringUtil;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
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
import com.cking.phss.bluetooth.BluetoothClient.OnDisconnectListener;
import com.cking.phss.bluetooth.BluetoothClient4Bu34;
import com.cking.phss.bluetooth.BluetoothClient4Bu34.OnReceiveListener;
import com.cking.phss.bluetooth.BluetoothClient4CardioChek;
import com.cking.phss.util.AppConfigFactory.AppConfig;
import com.cking.phss.util.DecimalsTextWatcher;
import com.cking.phss.util.JgxxConfigFactory;
import com.cking.phss.util.MyApplication;
import com.cking.phss.util.TestResultUtil;
import com.cking.phss.util.TispToastFactory;
import com.cking.phss.widget.DeviceListDialog.OnDialogResultListener;
import com.cking.phss.xml4jgxx.tags.constants.TagConstants;

/**
 * 健康体检快速体检第2页 com.cking.phss.view.JktjKstjPage02
 * 
 * @author Administrator <br/>
 *         create at 2012-9-16 上午11:25:10
 */
public class JktjKstjPage04 extends LinearLayout implements IPage, ITestPage {
    @SuppressWarnings("unused")
    private static final String TAG = "JktjKstjPage02";
    private Context mContext = null;
    private Map<String, IBean> beanMap = null;
    /**
     * 第二页控件
     */
    private BluetoothClient4CardioChek mBluetoothClient4CardioChek = null;

    private BluetoothClient4Bu34 mBluetoothClient4Bu34 = null;

    // 血脂
    public EditText dgcEditText = null;// 胆固醇数据
    public EditText gyszEditText = null;// 甘油三酯数据
    public TextView dgcdwTextView = null;// 胆固醇单位
    public TextView gyszdwTextView = null;// 甘油三酯单位
    public EditText dgcjlEditText = null;// 胆固醇结论
    public EditText gyszjlEditText = null;// 甘油三酯结论
    public EditText gmdzdbEditText = null;// 高密度脂蛋白数据
    public EditText dmdzdbEditText = null;// 低密度脂蛋白数据
    public TextView gmdzdbdwTextView = null;// 高密度脂蛋白单位
    public TextView dmdzdbdwTextView = null;// 低密度脂蛋白单位
    public EditText gmdzdbjlEditText = null;// 高密度脂蛋白结论
    public EditText dmdzdbjlEditText = null;// 低密度脂蛋白结论
    EditText nsEditText;
    EditText nsjlEditText;
    TextView nsdwTextView;
    TextView tsxxTextView;

    public Button xzjcButton = null;
    
    public ViewGroup mParent;
    private Toast mToast = null;
    private Timer mTimeoutTimer = null;
    private ProgressDialog progressDialog = null;

    private int deviceId = 0;

    /**
     * @param context
     */
    public JktjKstjPage04(Context context, Map<String, IBean> beanMap, ViewGroup parent) {
        super(context);
        this.beanMap = beanMap;
        this.mParent = parent;
        init(context);
    }

    /**
     * @param context
     * @param attrs
     */
    public JktjKstjPage04(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    /**
     * @param context
     */
    private void init(Context context) {
        mContext = context;
        mToast = TispToastFactory.getToast(context, "");
        deviceId = JgxxConfigFactory.findIdByName(mContext, TagConstants.XML_VALUE_NAME_XZY);
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.fragment_health_kstj_xz_layout, this);

        loadPage(context, this);
    }

    public void loadPage(Context context, ViewGroup viewGroup) {

        // 血脂
        xzjcButton = (Button) findViewById(R.id.xzjcButton);
        dgcEditText = (EditText) findViewById(R.id.dgcEditText);
        dgcdwTextView = (TextView) findViewById(R.id.dgcdwTextView);
        dgcjlEditText = (EditText) findViewById(R.id.dgcjlEditText);
        gyszEditText = (EditText) findViewById(R.id.gyszEditText);
        gyszdwTextView = (TextView) findViewById(R.id.gyszdwTextView);
        gyszjlEditText = (EditText) findViewById(R.id.gyszjlEditText);
        gmdzdbEditText = (EditText) findViewById(R.id.gmdzdbEditText);// 高密度脂蛋白数据
        gmdzdbdwTextView = (TextView) findViewById(R.id.gmdzdwTextView);// 高密度脂蛋白单位
        gmdzdbjlEditText = (EditText) findViewById(R.id.gmdzdbjlEditText);// 高密度脂蛋白结论
        dmdzdbEditText = (EditText) findViewById(R.id.dmdzdbEditText);// 低密度脂蛋白数据
        dmdzdbdwTextView = (TextView) findViewById(R.id.dmdzdwTextView);// 低密度脂蛋白单位
        dmdzdbjlEditText = (EditText) findViewById(R.id.dmdzdbjlEditText);// 低密度脂蛋白结论
        nsEditText = (EditText) findViewById(R.id.nsEditText);
        nsdwTextView = (TextView) findViewById(R.id.nsdwTextView);
        nsjlEditText = (EditText) findViewById(R.id.nsjlEditText);
        tsxxTextView = (TextView) findViewById(R.id.tsxxTextView);
        tsxxTextView.setText("1、准备好试纸、消毒物品、采血针、移液管等；\n" + "2、开启血脂仪，将芯片和配套试纸条插入插槽；\n"
                + "3、清洗并擦拭双手，并对手指进行消毒；\n" + "4、使用全新的采血针，对准采血部位采血；\n" + "5、顺着手指血流方向挤压出足够的血液量；\n"
                + "6、使用移液管，将血液收集到移液管中；\n" + "7、将移液管中的血液挤捏到试纸条上；\n" + "8、约1-2分钟后，显示相应的血脂检测结果；\n"
                + "9、点击【保存】和【上传】即完成血脂检测。\n");

        if (JgxxConfigFactory.findDeviceInfoTagByName(mContext, TagConstants.XML_VALUE_NAME_XZY) == null) {
            xzjcButton.setEnabled(false);
        }
        xzjcButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // 获取血脂数据
                // // 血脂
                // mCholEdit.setText(String.valueOf(getData().getDgc()));
                // mTgEdit.setText(String.valueOf(getData().getGysz()));
                // mDgcjlEditText.setText(getData().getDgcjl());
                // mGyszjlEditText.setText(getData().getGyszjl());
                if (deviceId == TagConstants.XML_VALUE_ID_KDK_CARDIOCHEK) {
                    getResultFromDeviceCardioChek();
                } else if (deviceId == TagConstants.XML_VALUE_ID_JKZX_BU34) {
                    getResultFromDeviceBu34();
                } else {
                    mToast.setText("系统暂不支持此设备");
                    mToast.show();
                }
            }
        });

        dgcEditText.addTextChangedListener(lowZdbTextWatcher);
        gyszEditText.addTextChangedListener(lowZdbTextWatcher);
        gmdzdbEditText.addTextChangedListener(lowZdbTextWatcher);
        dmdzdbEditText.addTextChangedListener(DecimalsTextWatcher.getInstance());

        nsEditText.addTextChangedListener(nsTextWatcher);
        AppConfig appConfig = (AppConfig) beanMap.get(AppConfig.class.getName());
        if (appConfig != null) {
            String value = appConfig.getTjsrxz();
            if (value != null) {
                dgcEditText.setEnabled(value.contains("血脂检测") ? false : true);
                gyszEditText.setEnabled(value.contains("血脂检测") ? false : true);
                gmdzdbEditText.setEnabled(value.contains("血脂检测") ? false : true);
                dmdzdbEditText.setEnabled(value.contains("血脂检测") ? false : true);
                nsEditText.setEnabled(value.contains("血脂检测") ? false : true);
            }
        }
    }

    /**
     * 
     */
    protected void getResultFromDeviceBu34() {
        if (mBluetoothClient4Bu34 == null) {
            mBluetoothClient4Bu34 = new BluetoothClient4Bu34(mContext);
        }
        mBluetoothClient4Bu34.setOnReceiveListener(new OnReceiveListener() {

            @Override
            public void onReceiveTgData(float fValue, String unit) {
                // TODO 把数据显示在控件上
                Log.i(TAG, "test Result,TG:" + fValue + unit);

                gyszEditText.setText(fValue + "");
                gyszdwTextView.setText(unit);
                String result = TestResultUtil.getGyszResult(fValue);
                gyszjlEditText.setText(result);
                lowZdbTextWatcher.afterTextChanged(gyszEditText.getText());
                getValue();
                if (mBluetoothClient4Bu34 != null) {
                    mBluetoothClient4Bu34.stop();
                    mBluetoothClient4Bu34 = null;
                }

                finishConnectionUi();
            }

            @Override
            public void onReceiveCholData(float fValue, String unit) {
                // TODO 把数据显示在控件上
                Log.i(TAG, "test Result,CHOL:" + fValue + unit);
                dgcEditText.setText(fValue + "");
                dgcdwTextView.setText(unit);
                String result = TestResultUtil.getDgcResult(fValue);
                dgcjlEditText.setText(result);
                lowZdbTextWatcher.afterTextChanged(dgcEditText.getText());
                getValue();
                if (mBluetoothClient4Bu34 != null) {
                    mBluetoothClient4Bu34.stop();
                    mBluetoothClient4Bu34 = null;
                }

                finishConnectionUi();
            }
        });
        mBluetoothClient4Bu34.setOnDisconnectListener(new OnDisconnectListener() {

            @Override
            public void onDisconnect(boolean isSuccess) {
                mToast.setText("获取数据失败，请稍后重试。");
                mToast.show();

                // 隐藏进度条
                finishConnectionUi();
            }
        });
        mBluetoothClient4Bu34.run(new OnDialogResultListener() {
            
            @Override
            public void onConfirm() {// 显示进度条
                if (progressDialog == null) {
                    progressDialog = ProgressDialog.show(mContext, "正在获取", "请稍等...", false, true);
                }
                progressDialog.setOnCancelListener(new OnCancelListener() {

                    @Override
                    public void onCancel(DialogInterface arg0) {

                        if (mBluetoothClient4Bu34 != null) {
                            mBluetoothClient4Bu34.stop();
                            mBluetoothClient4Bu34 = null;
                        }

                        finishConnectionUi();
                    }
                });
                if (mTimeoutTimer == null) {
                    mTimeoutTimer = new Timer();
                }
                mTimeoutTimer.schedule(new TimerTask() {

                    @Override
                    public void run() {
                        mToast.setText("获取数据失败，请稍后重试。");
                        mToast.show();

                        if (mBluetoothClient4Bu34 != null) {
                            mBluetoothClient4Bu34.stop();
                            mBluetoothClient4Bu34 = null;
                        }

                        finishConnectionUi();
                    }

                }, 40 * 1000);
            }
            
            @Override
            public void onCancel() {
            }
        });
    }

    /**
     * 
     */
    protected void getResultFromDeviceCardioChek() {
        if (mBluetoothClient4CardioChek == null) {
            mBluetoothClient4CardioChek = new BluetoothClient4CardioChek(mContext);
        }
        mBluetoothClient4CardioChek
                .setOnReceiveListener(new com.cking.phss.bluetooth.BluetoothClient4CardioChek.OnReceiveListener() {

                    @Override
                    public void onReceiveTgData(float fValue, String unit) {
                        // 震动，铃声
                        MyApplication.getInstance().shock();
                        MyApplication.getInstance().tone();
                        
                        gyszEditText.setText(fValue + "");
                        gyszdwTextView.setText(unit);
                        gyszjlEditText.setText(TestResultUtil.getGyszResult(fValue));
                        lowZdbTextWatcher.afterTextChanged(gyszEditText.getText());
                        getValue();
                        if (mBluetoothClient4CardioChek != null) {
                            mBluetoothClient4CardioChek.stop();
                            mBluetoothClient4CardioChek = null;
                        }
                        finishConnectionUi();
                    }

                    @Override
                    public void onReceiveLdlData(float fValue, String unit) {
                        // 震动，铃声
                        MyApplication.getInstance().shock();
                        MyApplication.getInstance().tone();
                        
                        dmdzdbEditText.setText(fValue + "");
                        dmdzdbdwTextView.setText(unit);
                        dmdzdbjlEditText.setText(TestResultUtil.getDmdzdbResult(fValue));
                        getValue();
                        if (mBluetoothClient4CardioChek != null) {
                            mBluetoothClient4CardioChek.stop();
                            mBluetoothClient4CardioChek = null;
                        }
                        finishConnectionUi();
                    }

                    @Override
                    public void onReceiveHdlData(float fValue, String unit) {
                        // 震动，铃声
                        MyApplication.getInstance().shock();
                        MyApplication.getInstance().tone();
                        
                        gmdzdbEditText.setText(fValue + "");
                        gmdzdbdwTextView.setText(unit);
                        gmdzdbjlEditText.setText(TestResultUtil.getGmdzdbResult(fValue));
                        lowZdbTextWatcher.afterTextChanged(gmdzdbjlEditText.getText());
                        getValue();
                        if (mBluetoothClient4CardioChek != null) {
                            mBluetoothClient4CardioChek.stop();
                            mBluetoothClient4CardioChek = null;
                        }
                        finishConnectionUi();
                    }

                    @Override
                    public void onReceiveCholData(float fValue, String unit) {
                        // 震动，铃声
                        MyApplication.getInstance().shock();
                        MyApplication.getInstance().tone();
                        
                        dgcEditText.setText(fValue + "");
                        dgcdwTextView.setText(unit);
                        dgcjlEditText.setText(TestResultUtil.getDgcResult(fValue));
                        lowZdbTextWatcher.afterTextChanged(dgcjlEditText.getText());
                        getValue();
                        if (mBluetoothClient4CardioChek != null) {
                            mBluetoothClient4CardioChek.stop();
                            mBluetoothClient4CardioChek = null;
                        }
                        finishConnectionUi();
                    }

                    @Override
                    public void onReceiveGlucoseData(float fValue, String unit) {
                        if (mBluetoothClient4CardioChek != null) {
                            mBluetoothClient4CardioChek.stop();
                            mBluetoothClient4CardioChek = null;
                        }
                        finishConnectionUi();
                    }
                });
        mBluetoothClient4CardioChek
                .setOnDisconnectListener(new com.cking.phss.bluetooth.BluetoothClient4CardioChek.OnDisconnectListener() {

                    @Override
                    public void onDisconnect(boolean isSuccess) {
                    }
                });
        mBluetoothClient4CardioChek.run(new OnDialogResultListener() {
            
            @Override
            public void onConfirm() {// 显示进度条
                if (progressDialog == null) {
                    progressDialog = ProgressDialog.show(mContext, "正在获取", "请稍等...", false, true);
                }
                progressDialog.setOnCancelListener(new OnCancelListener() {

                    @Override
                    public void onCancel(DialogInterface arg0) {

                        if (mBluetoothClient4CardioChek != null) {
                            mBluetoothClient4CardioChek.stop();
                            mBluetoothClient4CardioChek = null;
                        }

                        finishConnectionUi();
                    }
                });
                if (mTimeoutTimer == null) {
                    mTimeoutTimer = new Timer();
                }
                mTimeoutTimer.schedule(new TimerTask() {

                    @Override
                    public void run() {
                        mToast.setText("获取数据失败，请稍后重试。");
                        mToast.show();

                        finishConnectionUi();
                    }

                }, 40 * 1000);
            }
            
            @Override
            public void onCancel() {
            }
        });
    }

    private void finishConnectionUi() {
        // 隐藏进度条
        if (progressDialog != null && progressDialog.isShowing()) {
            try {
                progressDialog.dismiss();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
        progressDialog = null;

        // 取消定时器
        if (mTimeoutTimer != null) {
            mTimeoutTimer.cancel();
            mTimeoutTimer = null;
        }
    }
    
    @Override
    public void setValue() {
        Jktj_kstj mJktj_kstj = (Jktj_kstj) beanMap.get(Jktj_kstj.class.getName());
        if (mJktj_kstj == null)
            return;
        Jmjbxx mJmjbxx = (Jmjbxx) beanMap.get(Jmjbxx.class.getName());
        if (mJmjbxx == null)
            return;

        // 血脂
        dgcEditText.setText(mJktj_kstj.getdGC());
        try {
            float dgc = Float.parseFloat(mJktj_kstj.getdGC());
            dgcjlEditText.setText(TestResultUtil.getDgcResult(dgc));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        gyszEditText.setText(mJktj_kstj.getgYSZ());
        try {
            float gysz = Float.parseFloat(mJktj_kstj.getgYSZ());
            gyszjlEditText.setText(TestResultUtil.getGyszResult(gysz));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        gmdzdbEditText.setText(mJktj_kstj.getHDL());
        try {
            float gmdzdb = Float.parseFloat(mJktj_kstj.getHDL());
            gmdzdbjlEditText.setText(TestResultUtil.getGmdzdbResult(gmdzdb));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        dmdzdbEditText.setText(mJktj_kstj.getLDL());
        try {
            float dmdzdb = Float.parseFloat(mJktj_kstj.getLDL());
            dmdzdbjlEditText.setText(TestResultUtil.getDmdzdbResult(dmdzdb));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        nsEditText.setText(mJktj_kstj.getNS());
        try {
            float ns = Float.parseFloat(mJktj_kstj.getNS());
            nsjlEditText.setText(TestResultUtil.getNsResult(mJmjbxx.getSexCD(), ns));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean getValue() {
        Jktj_kstj mJktj_kstj = (Jktj_kstj) beanMap.get(Jktj_kstj.class.getName());
        if (mJktj_kstj == null)
            return false;

        // 胆固醇
        String dgc = dgcEditText.getText().toString().trim();
        mJktj_kstj.setdGC(dgc);

        // 甘油三酯
        String gysz = gyszEditText.getText().toString().trim();
        mJktj_kstj.setgYSZ(gysz);

        // 胆固醇结论
        String dgcjl = dgcjlEditText.getText().toString().trim();
        mJktj_kstj.setdGCJL(dgcjl);

        // 甘油三酯结论
        String gyszjl = gyszjlEditText.getText().toString().trim();
        mJktj_kstj.setgYSZJL(gyszjl);

        // 高密度脂蛋白
        String hdl = gmdzdbEditText.getText().toString().trim();
        mJktj_kstj.setHDL(hdl);

        // 低密度脂蛋白
        String ldl = dmdzdbEditText.getText().toString().trim();
        mJktj_kstj.setLDL(ldl);

        // 高密度脂蛋白结论
        String hdlr = gmdzdbjlEditText.getText().toString().trim();
        mJktj_kstj.setHDLR(hdlr);

        // 低密度脂蛋白结论
        String ldlr = dmdzdbjlEditText.getText().toString().trim();
        mJktj_kstj.setLDLR(ldlr);

        // 尿酸
        String ns = nsEditText.getText().toString().trim();
        mJktj_kstj.setNS(ns);

        // 尿酸结论
        String nsjl = nsjlEditText.getText().toString().trim();
        mJktj_kstj.setNSR(nsjl);

        return true;
    }

    public void clear(){
        // 血脂
          dgcEditText.setText("");
          gyszEditText.setText("");
          dgcjlEditText.setText("");
          gyszjlEditText.setText("");
          gmdzdbEditText.setText("");
          dmdzdbEditText.setText("");
          gmdzdbjlEditText .setText("");
          dmdzdbjlEditText .setText("");
          nsjlEditText .setText("");
          nsjlEditText .setText("");
   }

    /* (non-Javadoc)
     * @see com.cking.phss.page.ITestPage#getValidDataCount()
     */
    @Override
    public int getValidDataCount() {

        int count  = 0;
        // 胆固醇
        String dgc = dgcEditText.getText().toString().trim();
        if (StringUtil.isDecimal(dgc)) {
            count++;
        }

        // 甘油三酯
        String gysz = gyszEditText.getText().toString().trim();
        if (StringUtil.isDecimal(gysz)) {
            count++;
        }

        // 高密度脂蛋白
        String hdl = gmdzdbEditText.getText().toString().trim();
        if (StringUtil.isDecimal(hdl)) {
            count++;
        }

        // 低密度脂蛋白
        String ldl = dmdzdbEditText.getText().toString().trim();
        if (StringUtil.isDecimal(ldl)) {
            count++;
        }

        // 尿酸
        String ns = nsEditText.getText().toString().trim();
        if (StringUtil.isDecimal(ns)) {
            count++;
        }

        return count;
    }

    // 根据胆固醇、甘油三酯、高密度脂蛋白自动计算低密度脂蛋白
    DecimalsTextWatcher lowZdbTextWatcher = new DecimalsTextWatcher(3) {
        @Override
        public void afterTextChanged(Editable s) {
            super.afterTextChanged(s);
            String dgc = dgcEditText.getText().toString();// 胆固醇
            String gysz = gyszEditText.getText().toString();// 甘油三脂
            String highZdb = gmdzdbEditText.getText().toString();// 高密度脂蛋白
            try {
                if (!StringUtil.isEmptyString(dgc)) {
                    try {
                        float fdgc = Float.parseFloat(dgc);
                        dgcjlEditText.setText(TestResultUtil.getDgcResult(fdgc));
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }

                if (!StringUtil.isEmptyString(gysz)) {
                    try {
                        float fgysz = Float.parseFloat(gysz);
                        gyszjlEditText.setText(TestResultUtil.getGyszResult(fgysz));
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }

                if (!StringUtil.isEmptyString(highZdb)) {
                    try {
                        float fgmdzdb = Float.parseFloat(highZdb);
                        gmdzdbjlEditText.setText(TestResultUtil.getGmdzdbResult(fgmdzdb));
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }

                if (StringUtil.isEmptyString(dgc) || StringUtil.isEmptyString(gysz)
                        || StringUtil.isEmptyString(highZdb)) {
                    dmdzdbEditText.setText("");
                    return;
                }

                float dgcValue = Float.parseFloat(dgc.trim());
                float gyszValue = Float.parseFloat(gysz.trim());
                float heightZdbValue = Float.parseFloat(highZdb.trim());

                double lowZdbValue = DecimalsTextWatcher.getInstance().parsePercision(
                        dgcValue - heightZdbValue - gyszValue / 2.17);
                dmdzdbEditText.setText(lowZdbValue + "");

                try {
                    dmdzdbjlEditText.setText(TestResultUtil.getDmdzdbResult((float) lowZdbValue));
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }

        }
    };

    // 根据尿酸
    DecimalsTextWatcher nsTextWatcher = new DecimalsTextWatcher(3) {
        @Override
        public void afterTextChanged(Editable s) {
            super.afterTextChanged(s);
            String ns = nsEditText.getText().toString();// 高密度脂蛋白
            try {
                if (StringUtil.isEmptyString(ns)) {
                    nsjlEditText.setText("");
                    return;
                }
                try {
                    Jmjbxx mJmjbxx = (Jmjbxx) beanMap.get(Jmjbxx.class.getName());
                    if (mJmjbxx == null)
                        return;
                    float fns = Float.parseFloat(ns);
                    nsjlEditText.setText(TestResultUtil.getNsResult(mJmjbxx.getSexCD(), fns));
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }

        }
    };
}
