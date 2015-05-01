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
import com.cking.phss.bluetooth.BluetoothClient4Bp3m;
import com.cking.phss.bluetooth.BluetoothServer4And;
import com.cking.phss.bluetooth.BluetoothServer4And.OnReceiveListener;
import com.cking.phss.bluetooth.IBluetoothServerReceiver.BcaData;
import com.cking.phss.file.FileLog;
import com.cking.phss.util.AppConfigFactory.AppConfig;
import com.cking.phss.util.CommonUtil;
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
public class JktjKstjPage02 extends LinearLayout implements IPage, ITestPage {
    @SuppressWarnings("unused")
    private static final String TAG = "JktjKstjPage02";
    private Context mContext = null;
    private Map<String, IBean> beanMap = null;
    /**
     * 第二页控件
     */

    Button xyclButton;
    EditText ssyEditText;
    EditText szyEditText;
    EditText zqmbEditText;
    EditText jlEditText;
    TextView tsxxTextView;
    LinearLayout xyclLinearLayout;

    public ViewGroup mParent;
    private Toast mToast = null;
    private Timer mTimeoutTimer = null;

    private static BluetoothServer4And mBluethoothServer = null;
    private BluetoothClient4Bp3m mBluetoothClient4Bp3m = null;
    private ProgressDialog progressDialog = null;
    
    private int deviceId = 0;

    /**
     * @param context
     */
    public JktjKstjPage02(Context context, Map<String, IBean> beanMap, ViewGroup parent) {
        super(context);
        this.beanMap = beanMap;
        this.mParent = parent;
        init(context);
    }

    /**
     * @param context
     * @param attrs
     */
    public JktjKstjPage02(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    /**
     * @param context
     */
    private void init(Context context) {
        mContext = context;
        mToast = TispToastFactory.getToast(context, "");
        deviceId = JgxxConfigFactory.findIdByName(mContext, TagConstants.XML_VALUE_NAME_XYJ);
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.fragment_health_kstj_xueya_layout, this);
        
        loadPage(context, this);
    }

    public void loadPage(Context context, ViewGroup viewGroup) {
        xyclLinearLayout = (LinearLayout) findViewById(R.id.xyclLinearLayout);
        xyclButton = (Button) findViewById(R.id.xyclButton);
        ssyEditText = (EditText) findViewById(R.id.ssyEditText);
        szyEditText = (EditText) findViewById(R.id.szyEditText);
        zqmbEditText = (EditText) findViewById(R.id.zqmbEditText);
        jlEditText = (EditText) findViewById(R.id.jlEditText);
        tsxxTextView = (TextView) findViewById(R.id.tsxxTextView);
        tsxxTextView.setText("1、测量前请休息5-10分钟，保持心情平和；\n" + "2、测量手臂需裸露或仅穿较薄的衣物；\n"
                + "3、袖带佩戴位置保持与心脏齐平的高度；\n" + "4、开启血压计按钮，约1分钟后显示血压；\n" + "5、5-10秒钟后，血压数据自动上传到平板；\n"
                + "6、点击【保存】和【上传】即完成血压测量。");

        if (JgxxConfigFactory.findDeviceInfoTagByName(mContext, TagConstants.XML_VALUE_NAME_XYJ) == null) {
            xyclButton.setEnabled(false);
        }
        xyclButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // 获取血脂数据
                // // 血脂
                // mCholEdit.setText(String.valueOf(getData().getDgc()));
                // mTgEdit.setText(String.valueOf(getData().getGysz()));
                // mDgcjlEditText.setText(getData().getDgcjl());
                // mGyszjlEditText.setText(getData().getGyszjl());
                if (deviceId == TagConstants.XML_VALUE_ID_MICROLIFE_BP3M) {
                    getResultFromDeviceBp3m();
                } else {
                    mToast.setText("系统暂不支持此设备");
                    mToast.show();
                }
            }
        });
        ssyEditText.addTextChangedListener(xyTextWatcher);
        szyEditText.addTextChangedListener(xyTextWatcher);
        AppConfig appConfig = (AppConfig) beanMap.get(AppConfig.class.getName());
        if (appConfig != null) {
            String value = appConfig.getTjsrxz();
            if (value != null) {
                ssyEditText.setEnabled(value.contains("血压测量") ? false : true);
                szyEditText.setEnabled(value.contains("血压测量") ? false : true);
                zqmbEditText.setEnabled(value.contains("血压测量") ? false : true);
            }
        }
    }

    /**
     * 
     */
    protected void getResultFromDeviceAnd() {
//        if (mTimeoutTimer == null) {
//            mTimeoutTimer = new Timer();
//        }
//        mTimeoutTimer.schedule(new TimerTask() {
//
//            @Override
//            public void run() {
//                mToast.setText("获取数据失败，请稍后重试。");
//                mToast.show();
//
//                mBluethoothServer.close();
//                finishConnectionUi();
//            }
//
//        }, 40 * 1000);

        /**
         * 创建一个蓝牙设备
         */
        if (mBluethoothServer == null) {
            mBluethoothServer = new BluetoothServer4And(mContext);
        }
        mBluethoothServer
                .setOnReceiveListener(new OnReceiveListener() {

                    @Override
                    public void onReceivePbtcDevicesData(BcaData bcaData) {
                    }

                    @Override
                    public void onReceivePbtcDevicesConfig(int id) {
                    }

                    @Override
                    public void onReceivePbtSeriresData(int systolic,
                            int diastolic, int pulseRate) {
                        
                        // 震动，铃声
                        MyApplication.getInstance().shock();
                        MyApplication.getInstance().tone();
                        
                        Log.d(TAG, "onReceivePbtSeriresData - systolic:"
                                + systolic + ", diastolic:" + diastolic
                                + ", pulseRate:" + pulseRate);
                        ssyEditText.setText(systolic + "");
                        Log.i(TAG, "mSystolicEdit:"
                                + ssyEditText.getText()
                                        .toString());
                        szyEditText.setText(diastolic + "");
                        Log.i(TAG, "mDiastolicEdit:"
                                + szyEditText.getText()
                                        .toString());
                        zqmbEditText.setText(pulseRate + "");
                        Log.i(TAG, "mPulseRateEdit:"
                                + zqmbEditText.getText()
                                        .toString());
                        jlEditText.setText(TestResultUtil.getXyResult(systolic, diastolic));
                        // 显示血压结论
                        getValue();
                        /**
                         * 关闭蓝牙设备
                         */
//                        mBluethoothServer.close();
//                        finishConnectionUi();
                    }
                });
        /**
         * 打开蓝牙设备
         */
        if (mBluethoothServer != null) {
            mBluethoothServer.open();
        }
    }

    /**
     * 
     */
    protected void getResultFromDeviceBp3m() {
        if (mBluetoothClient4Bp3m == null) {
            mBluetoothClient4Bp3m = new BluetoothClient4Bp3m(mContext);
        }
        mBluetoothClient4Bp3m
                .setOnReceiveListener(new com.cking.phss.bluetooth.BluetoothClient4Bp3m.OnReceiveListener() {
                    @Override
                    public void onReceiveXyData(int systolic, int diastolic, int pulseRate) {
                        
                        // 震动，铃声
                        MyApplication.getInstance().shock();
                        MyApplication.getInstance().tone();
                        
                        Log.d(TAG, "onReceivePbtSeriresData - systolic:"
                                + systolic + ", diastolic:" + diastolic
                                + ", pulseRate:" + pulseRate);
                        ssyEditText.setText(systolic + "");
                        Log.i(TAG, "mSystolicEdit:"
                                + ssyEditText.getText()
                                        .toString());
                        szyEditText.setText(diastolic + "");
                        Log.i(TAG, "mDiastolicEdit:"
                                + szyEditText.getText()
                                        .toString());
                        zqmbEditText.setText(pulseRate + "");
                        Log.i(TAG, "mPulseRateEdit:"
                                + zqmbEditText.getText()
                                        .toString());
                        xyTextWatcher.afterTextChanged(ssyEditText.getText());
                        xyTextWatcher.afterTextChanged(szyEditText.getText());
                        // 显示血压结论
                        getValue();
                        if (mBluetoothClient4Bp3m != null) {
                            mBluetoothClient4Bp3m.stop();
                            mBluetoothClient4Bp3m = null;
                        }
                        finishConnectionUi();
                    }
                });
        mBluetoothClient4Bp3m.setOnDisconnectListener(new com.cking.phss.bluetooth.BluetoothClient4Bp3m.OnDisconnectListener() {

            @Override
            public void onDisconnect(boolean isSuccess) {
            }
        });
        mBluetoothClient4Bp3m.run(new OnDialogResultListener() {
            
            @Override
            public void onConfirm() {// 显示进度条
                if (progressDialog == null) {
                    progressDialog = ProgressDialog.show(mContext, "正在获取", "请稍等...", false, true);
                }
                progressDialog.setOnCancelListener(new OnCancelListener() {

                    @Override
                    public void onCancel(DialogInterface arg0) {

                        if (mBluetoothClient4Bp3m != null) {
                            mBluetoothClient4Bp3m.stop();
                            mBluetoothClient4Bp3m = null;
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
                        if (mBluetoothClient4Bp3m != null) {
                            mBluetoothClient4Bp3m.stop();
                            mBluetoothClient4Bp3m = null;
                        }
                        finishConnectionUi();
                    }

                }, 10000);
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

        // 血压
        ssyEditText.setText(mJktj_kstj.getsBP());
        szyEditText.setText(mJktj_kstj.getdBP());
        zqmbEditText.setText(mJktj_kstj.getxL());
        try {
            int nSystolic = Integer.parseInt(mJktj_kstj.getsBP());
            int nDiatolic = Integer.parseInt(mJktj_kstj.getdBP());
            jlEditText.setText(TestResultUtil.getXyResult(nSystolic, nDiatolic));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean getValue() {
        Jktj_kstj mJktj_kstj = (Jktj_kstj) beanMap.get(Jktj_kstj.class.getName());
        if (mJktj_kstj == null)
            return false;

        // 体温
        String result = ssyEditText.getText().toString().trim();
        mJktj_kstj.setsBP(result);
        result = szyEditText.getText().toString().trim();
        mJktj_kstj.setdBP(result);
        result = zqmbEditText.getText().toString().trim();
        mJktj_kstj.setxL(result);
        result = jlEditText.getText().toString().trim();
        mJktj_kstj.setxYJL(result);
        
        return true;
    }

    public void clear(){
        // 体温
        ssyEditText.setText("");
        szyEditText.setText("");
        zqmbEditText.setText("");
        jlEditText.setText("");
   }

    /* (non-Javadoc)
     * @see com.cking.phss.page.ITestPage#getValidDataCount()
     */
    @Override
    public int getValidDataCount() {
        int count  = 0;
        String result = ssyEditText.getText().toString().trim();
        if (StringUtil.isDecimal(result)) {
            count++;
        }
        result = szyEditText.getText().toString().trim();
        if (StringUtil.isDecimal(result)) {
            count++;
        }
        result = zqmbEditText.getText().toString().trim();
        if (StringUtil.isDecimal(result)) {
            count++;
        }
        return count;
    }

    /**
     * @param strReading
     */
    private void updateView(String strReading) {

        Log.i(TAG, "entry updateView...");

        String head = strReading.substring(0, 2);

        // 判断数据是否有效，血压仪
        if (head.equals("80")) {
            byte[] hexBytes = CommonUtil.hexString2Bytes(strReading);
            ssyEditText.setText((hexBytes[1] + hexBytes[2]) + "");
            Log.i(TAG, "mSystolicEdit:"
                    + ssyEditText.getText().toString());
            szyEditText.setText(hexBytes[2] + "");
            Log.i(TAG, "mDiastolicEdit:"
                    + szyEditText.getText().toString());
            zqmbEditText.setText(hexBytes[3] + "");
            Log.i(TAG, "mPulseRateEdit:"
                    + zqmbEditText.getText().toString());
        }
        // mPressResultEdit = (EditText) findViewById(R.id.press_result_edit);
        // mHeightEdit = (EditText) findViewById(R.id.height_edit);
        // mWeightEdit = (EditText) findViewById(R.id.weight_edit);
        // mBmiEdit = (EditText) findViewById(R.id.bmi_edit);
        // mWeightResultEdit = (EditText) findViewById(R.id.weight_result_edit);

        // 判断数据是否有效，体成分分析仪
        if (head.equals("8F")) {
            // byte[] hexBytes = CommonUtil.hexString2Bytes(strReading);
            // mSystolicEdit.setText((hexBytes[1] + hexBytes[2]) + "");
            // Log.i(TAG, "mSystolicEdit:" +
            // mSystolicEdit.getText().toString());
            // mDiastolicEdit.setText(hexBytes[2] + "");
            // Log.i(TAG, "mDiastolicEdit:" +
            // mDiastolicEdit.getText().toString());
            // mPulseRateEdit.setText(hexBytes[3] + "");
            // Log.i(TAG, "mPulseRateEdit:" +
            // mPulseRateEdit.getText().toString());
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Log.i(TAG, "onDetachedFromWindow");
        FileLog.i(TAG, "onDetachedFromWindow");
        if (deviceId == TagConstants.XML_VALUE_ID_AND_767) {
            if (mBluethoothServer != null) {
                mBluethoothServer.close();
                mBluethoothServer = null;
            }
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.i(TAG, "onAttachedToWindow");
        FileLog.i(TAG, "onAttachedToWindow");
        if (deviceId == TagConstants.XML_VALUE_ID_AND_767) {
            xyclLinearLayout.setVisibility(View.GONE);
            getResultFromDeviceAnd();
        }
    }

    // 根据收缩压和舒张压得出结论
    DecimalsTextWatcher xyTextWatcher = new DecimalsTextWatcher(2) {
        @Override
        public void afterTextChanged(Editable s) {
            super.afterTextChanged(s);
            String ssy = ssyEditText.getText().toString();
            String szy = szyEditText.getText().toString();
            try {
                if (StringUtil.isEmptyString(ssy) || StringUtil.isEmptyString(szy)) {
                    jlEditText.setText("");
                    return;
                }
                try {
                    int nSystolic = Integer.parseInt(ssy);
                    int nDiatolic = Integer.parseInt(szy);
                    jlEditText.setText(TestResultUtil.getXyResult(nSystolic, nDiatolic));
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }

        }
    };
}
