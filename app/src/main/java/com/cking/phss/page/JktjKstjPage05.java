/* Cking Inc. (C) 2012. All rights reserved.
 *
 * JbxxPage01.java
 * classes : com.cking.phss.view.JbxxBodyView
 * @author Administrator
 * V 1.0.0
 * Create at 2012-9-16 上午11:25:10
 */
package com.cking.phss.page;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import net.xinhuaxing.util.StringUtil;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cking.phss.R;
import com.cking.phss.bean.BloodOxygen;
import com.cking.phss.bean.IBean;
import com.cking.phss.bean.Jktj_kstj;
import com.cking.phss.bluetooth.BluetoothClient.OnDisconnectListener;
import com.cking.phss.bluetooth.BluetoothClient4Bm2000;
import com.cking.phss.bluetooth.BluetoothClient4Bm2000.OnReceiveListener;
import com.cking.phss.util.AppConfigFactory.AppConfig;
import com.cking.phss.util.DecimalsTextWatcher;
import com.cking.phss.util.JgxxConfigFactory;
import com.cking.application.MyApplication;
import com.cking.phss.util.TestResultUtil;
import com.cking.phss.util.TispToastFactory;
import com.cking.phss.widget.DeviceListDialog.OnDialogResultListener;
import com.cking.phss.xml4jgxx.tags.constants.TagConstants;

/**
 * 血氧
 * 
 * @author Administrator <br/>
 *         create at 2012-9-16 上午11:25:10
 */
public class JktjKstjPage05 extends LinearLayout implements IPage, ITestPage {
    @SuppressWarnings("unused")
    private static final String TAG = "JktjKstjPage02";
    private Context mContext = null;
    private Map<String, IBean> beanMap = null;

    Button hqsjButton;
    EditText bhdEditText;
    EditText mlEditText;
    EditText jlEditText;
    TextView tsxxTextView;

    public ViewGroup mParent;
    private Toast mToast = null;
    private Timer mTimeoutTimer = null;
    private List<BloodOxygen> mBloodOxygenList = new ArrayList<BloodOxygen>();

    private BluetoothClient4Bm2000 mBluetoothClient4Bm2000 = null;

    private ProgressDialog progressDialog = null;
    private int deviceId = 0;

    public String cName="";//健康建议
    /**
     * @param context
     */
    public JktjKstjPage05(Context context, Map<String, IBean> beanMap, ViewGroup parent) {
        super(context);
        this.beanMap = beanMap;
        this.mParent = parent;
        init(context);
    }

    /**
     * @param context
     * @param attrs
     */
    public JktjKstjPage05(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    /**
     * @param context
     */
    private void init(Context context) {
        mContext = context;
        mToast = TispToastFactory.getToast(context, "");
        deviceId = JgxxConfigFactory.findIdByName(mContext, TagConstants.XML_VALUE_NAME_XYY);
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.fragment_health_kstj_xueyang_layout, this);

        loadPage(context, this);
    }

    public void loadPage(Context context, ViewGroup viewGroup) {Button hqsjButton;
        hqsjButton = (Button) findViewById(R.id.hqsjButton);
        bhdEditText = (EditText) findViewById(R.id.bhdEditText);
        mlEditText = (EditText) findViewById(R.id.mlEditText);
        jlEditText = (EditText) findViewById(R.id.jlEditText);
        tsxxTextView = (TextView) findViewById(R.id.tsxxTextView);
        tsxxTextView.setText("1、开启血氧仪，并指夹连接线进行连接；\n" + "2、将手指按照提示方向完全放入指夹套；\n"
                + "3、出现类似“98”的结果时，保持10秒钟；\n" + "4、待结果稳定之后，取下指夹套；\n" + "5、血氧数据自动上传到平板；\n"
                + "6、点击【保存】和【上传】即完成血氧测量。\n");

        if (JgxxConfigFactory.findDeviceInfoTagByName(mContext, TagConstants.XML_VALUE_NAME_XYY) == null) {
            hqsjButton.setEnabled(false);
        }
        hqsjButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if (deviceId == TagConstants.XML_VALUE_ID_BEIRUI_BZ) {
                    getResultFromDeviceBm2000();
                } else {
                    mToast.setText("系统暂不支持此设备");
                    mToast.show();
                }
            }
        });

        bhdEditText.addTextChangedListener(xyTextWatcher);
        AppConfig appConfig = (AppConfig) beanMap.get(AppConfig.class.getName());
        if (appConfig != null) {
            String value = appConfig.getTjsrxz();
            if (value != null) {
                bhdEditText.setEnabled(value.contains("血氧检测") ? false : true);
                mlEditText.setEnabled(value.contains("血氧检测") ? false : true);
            }
        }
    }

    /**
     * 
     */
    protected void getResultFromDeviceBm2000() {
        if (mBluetoothClient4Bm2000 == null) {
            mBluetoothClient4Bm2000 = new BluetoothClient4Bm2000(mContext);
        }
        mBluetoothClient4Bm2000.setOnReceiveListener(new OnReceiveListener() {
            @Override
            public void onReceiveBloodOxygenData(BloodOxygen data) {
                // Log.i(TAG, "signal:" + data.getSignal() +
                // ",searchTimeLong:" + data.getSearchTimeLong() +
                // ",spo2Low:" + data.getSpo2Low() +
                // ",pulseWave:" + data.getPulseWave() +
                // ",pi:" + data.getPi() +
                // ",proberError:" + data.getProberError() +
                // ",searchPulse:" + data.getSearchPulse() +
                // ",spo2:" + data.getSpo2() +
                // ",realPulseRate:" + data.getRealPulseRate());
                mBloodOxygenList.add(data);
            }
        });
        mBluetoothClient4Bm2000.setOnDisconnectListener(new OnDisconnectListener() {

            @Override
            public void onDisconnect(boolean isSuccess) {
            }
        });
        mBluetoothClient4Bm2000.run(new OnDialogResultListener() {
            
            @Override
            public void onConfirm() {// 显示进度条
                if (progressDialog == null) {
                    progressDialog = ProgressDialog.show(mContext, "正在获取", "请稍等...", false, true);
                }
                progressDialog.setOnCancelListener(new OnCancelListener() {

                    @Override
                    public void onCancel(DialogInterface arg0) {

                        if (mBluetoothClient4Bm2000 != null) {
                            mBluetoothClient4Bm2000.stop();
                            mBluetoothClient4Bm2000 = null;
                        }

                        // 取消定时器
                        if (mTimeoutTimer != null) {
                            mTimeoutTimer.cancel();
                            mTimeoutTimer = null;
                        }
                        progressDialog = null;
                    }
                });
                mBloodOxygenList.clear();
                mTimeoutTimer = new Timer();
                mTimeoutTimer.schedule(new TimerTask() {

                    @Override
                    public void run() {
                        showResultHandler.sendEmptyMessage(0);
                    }
                }, 20000);
            }
            
            @Override
            public void onCancel() {
            }
        });
    }

    Handler showResultHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            // 断开连接
            if (mBluetoothClient4Bm2000 != null) {
                mBluetoothClient4Bm2000.stop();
                mBluetoothClient4Bm2000 = null;
            }
            if (!mBloodOxygenList.isEmpty()) {
                // 震动，铃声
                MyApplication.getInstance().shock();
                MyApplication.getInstance().tone();

                int averSpo2 = Math.round(getAverageSpo2(mBloodOxygenList));
                int averRealPulseRate = Math.round(getAverageRealPulseRate(mBloodOxygenList));
                String result = TestResultUtil.getXyResult(averSpo2);

                bhdEditText.setText(averSpo2 + "");
                mlEditText.setText(averRealPulseRate + "");
                jlEditText.setText(result);
                xyTextWatcher.afterTextChanged(bhdEditText.getText());

                getValue();
            } else {
                mToast.setText("获取数据失败，请稍后重试。");
                mToast.show();
            }

            // 隐藏进度条
            if (progressDialog != null && progressDialog.isShowing()) {
                try {
                    progressDialog.dismiss();
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                }
            }
            progressDialog = null;
            super.handleMessage(msg);
        }
        
    };

    private float getAverageRealPulseRate(
            List<BloodOxygen> mBloodOxygenList) {
        if (mBloodOxygenList.size() <= 10) {
            return -1;
        }
        
        float total = 0;
        float average;
        int count = mBloodOxygenList.size() - 10;
        for (int i = 10; i<mBloodOxygenList.size(); i++) {
            total += mBloodOxygenList.get(i).getRealPulseRate();
        }
        average = total / count;
        return average;
    }
    
    private float getAverageSpo2(List<BloodOxygen> mBloodOxygenList) {
        if (mBloodOxygenList.size() <= 10) {
            return -1;
        }
        
        float total = 0;
        float average;
        int count = mBloodOxygenList.size() - 10;
        for (int i = 10; i<mBloodOxygenList.size(); i++) {
            total += mBloodOxygenList.get(i).getSpo2();
        }
        average = total / count;
        return average;
    }
    
    @Override
    public void setValue() {
      Jktj_kstj mJktj_kstj = (Jktj_kstj) beanMap.get(Jktj_kstj.class.getName());
      if (mJktj_kstj == null)
          return;
      
        bhdEditText.setText(mJktj_kstj.getXybhd());
        mlEditText.setText(mJktj_kstj.getXyml());
        try {
            int averSpo2 = Integer.parseInt(mJktj_kstj.getXybhd());
            jlEditText.setText(TestResultUtil.getXyResult(averSpo2));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean getValue() {
        Jktj_kstj mJktj_kstj = (Jktj_kstj) beanMap.get(Jktj_kstj.class.getName());
        if (mJktj_kstj == null)
            return false;

        mJktj_kstj.setXybhd(bhdEditText.getText().toString());
        mJktj_kstj.setXyml(mlEditText.getText().toString());
        mJktj_kstj.setXyjl(jlEditText.getText().toString());
        
        return true;
    }

    /* (non-Javadoc)
     * @see com.cking.phss.page.ITestPage#getValidDataCount()
     */
    @Override
    public int getValidDataCount() {
        int count  = 0;
        String result = bhdEditText.getText().toString().trim();
        if (StringUtil.isDecimal(result)) {
            count++;
        }

        result = mlEditText.getText().toString().trim();
        if (StringUtil.isDecimal(result)) {
            count++;
        }

        return count;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.cking.phss.page.IPage#clear()
     */
    @Override
    public void clear() {
    }

    // 根据饱和度得出结论
    DecimalsTextWatcher xyTextWatcher = new DecimalsTextWatcher(2) {
        @Override
        public void afterTextChanged(Editable s) {
            super.afterTextChanged(s);
            String bhd = bhdEditText.getText().toString();// 高密度脂蛋白
            try {
                if (StringUtil.isEmptyString(bhd)) {
                    jlEditText.setText("");
                    return;
                }
                try {
                    int averSpo2 = Integer.parseInt(bhd);
                    jlEditText.setText(TestResultUtil.getXyResult(averSpo2));
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }

        }
    };
}
