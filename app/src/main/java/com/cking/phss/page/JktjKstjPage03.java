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
import com.cking.phss.bean.IBean;
import com.cking.phss.bean.Jktj_kstj;
import com.cking.phss.bean.Jmjbxx;
import com.cking.phss.bluetooth.BluetoothClient.OnDisconnectListener;
import com.cking.phss.bluetooth.BluetoothClient4BeneCheck;
import com.cking.phss.bluetooth.BluetoothClient4BeneCheck.OnReceiveListener;
import com.cking.phss.bluetooth.BluetoothClient4CardioChek;
import com.cking.phss.dto.Dyxt;
import com.cking.phss.dto.IDto;
import com.cking.phss.dto.innner.Glucose;
import com.cking.phss.net.ISoapRecv;
import com.cking.phss.net.WebService;
import com.cking.phss.util.AppConfigFactory.AppConfig;
import com.cking.phss.util.DecimalsTextWatcher;
import com.cking.phss.util.JgxxConfigFactory;
import com.cking.phss.util.MyApplication;
import com.cking.phss.util.TestResultUtil;
import com.cking.phss.util.TispToastFactory;
import com.cking.phss.widget.DeviceListDialog.OnDialogResultListener;
import com.cking.phss.widget.SpinnerUtil;
import com.cking.phss.xml.util.XmlSerializerUtil;
import com.cking.phss.xml4jgxx.tags.ConfigTag;
import com.cking.phss.xml4jgxx.tags.constants.TagConstants;

/**
 * 健康体检快速体检血糖
 * 
 * @author Administrator <br/>
 *         create at 2012-9-16 上午11:25:10
 */
public class JktjKstjPage03 extends LinearLayout implements IPage, ITestPage {
    @SuppressWarnings("unused")
    private static final String TAG = "JktjKstjPage03";
    private Context mContext = null;
    private Map<String, IBean> beanMap = null;
    /**
     * 第三页控件
     */
    private BluetoothClient4CardioChek mBluetoothClient4CardioChek = null;
    private BluetoothClient4BeneCheck mBluetoothClient4BeneCheck = null;
    
    Button xtjcButton;
    SpinnerUtil jclxSpinnerUtil;
    EditText xtzEditText;
    EditText jlEditText;

    TextView tsxxTextView;
    TextView xtzdwTextView;
    public ViewGroup mParent;
    private Toast mToast = null;
    private Timer mTimeoutTimer = null;

    private ProgressDialog progressDialog = null;
    private int deviceId = 0;
    
    public String cName="";//健康建议
    /**
     * @param context
     */
    public JktjKstjPage03(Context context, Map<String, IBean> beanMap, ViewGroup parent) {
        super(context);
        this.beanMap = beanMap;
        this.mParent = parent;
        init(context);
    }

    /**
     * @param context
     * @param attrs
     */
    public JktjKstjPage03(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    /**
     * @param context
     */
    private void init(Context context) {
        mContext = context;
        mToast = TispToastFactory.getToast(context, "");
        deviceId = JgxxConfigFactory.findIdByName(mContext, TagConstants.XML_VALUE_NAME_XTY);
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.fragment_health_kstj_xt_layout, this);

        loadPage(context, this);
    }

    public void loadPage(Context context, ViewGroup viewGroup) {
        xtjcButton = (Button) findViewById(R.id.xtjcButton);
        jclxSpinnerUtil = (SpinnerUtil) findViewById(R.id.jclxSpinnerUtil);
        xtzEditText = (EditText) findViewById(R.id.xtzEditText);
        xtzdwTextView = (TextView) findViewById(R.id.xtzdwTextView);
        jlEditText = (EditText) findViewById(R.id.jlEditText);
        tsxxTextView = (TextView) findViewById(R.id.tsxxTextView);
        tsxxTextView.setText("1、准备好试纸、消毒物品、采血针等物料；\n" + "2、开启血糖仪，将最新试纸条插入插槽；\n" + "3、清洗并擦拭双手，并对手指进行消毒；\n"
                + "4、使用全新的采血针，对准采血部位采血；\n" + "5、顺着手指血流方向挤压出足够的血液量；\n" + "6、将连接血糖仪的试纸另一头吸取血液；\n"
                + "7、约5秒钟后，显示血糖检测结果；\n" + "6、点击【保存】和【上传】即完成血糖检测。\n");

        if (JgxxConfigFactory.findDeviceInfoTagByName(mContext, TagConstants.XML_VALUE_NAME_XTY) == null) {
            xtjcButton.setEnabled(false);
        }
        xtjcButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // 获取血脂数据
                // // 血脂
                // mCholEdit.setText(String.valueOf(getData().getDgc()));
                // mTgEdit.setText(String.valueOf(getData().getGysz()));
                // mDgcjlEditText.setText(getData().getDgcjl());
                // mGyszjlEditText.setText(getData().getGyszjl());
                // 显示进度条
                if (deviceId == TagConstants.XML_VALUE_ID_KDK_CARDIOCHEK) {
                    getResultFromDeviceCardioChek();
                } else if (deviceId == TagConstants.XML_VALUE_ID_JKZX_BU34) {
                    getResultFromDeviceBu34();
                } else if (deviceId == TagConstants.XML_VALUE_ID_BJ_PGD1) {
                    getResultFromDeviceBeneCheck();
                } else if (deviceId == TagConstants.XML_VALUE_ID_QT_BZ) {
                    getResultFromWebservice();
                } else {
                    mToast.setText("系统暂不支持此设备");
                    mToast.show();
                }
            }
        });
        xtzEditText.addTextChangedListener(xtTextWatcher);
        AppConfig appConfig = (AppConfig) beanMap.get(AppConfig.class.getName());
        if (appConfig != null) {
            String value = appConfig.getTjsrxz();
            if (value != null) {
                xtzEditText.setEnabled(value.contains("血糖测量") ? false : true);
            }
        }
    }

    /**
     * 从服务端获取血糖数据
     */
    protected void getResultFromWebservice() {
        Dyxt dyxt = new Dyxt();
        dyxt.request = new Dyxt.Request();
        ConfigTag configTag = JgxxConfigFactory.getConfigTag(mContext);
        dyxt.request.bridgeId = configTag.attrBean.getBridgeId();
        dyxt.request.deviceSn = configTag.attrBean.getSerialNo();
        dyxt.request.pfId = configTag.attrBean.getId();
        dyxt.request.glucoseType = "1";
        List<IDto> beanList = new ArrayList<IDto>();
        // 注意，以下顺序不能改变 --徐卓为
        beanList.add(dyxt); // 添加保存居民基本信息idto
        if (progressDialog == null) {
            progressDialog = ProgressDialog.show(mContext, "正在获取", "请稍等...", false, true);
        }
        progressDialog.setOnCancelListener(new OnCancelListener() {

            @Override
            public void onCancel(DialogInterface arg0) {
                finishConnectionUi();
            }
        });
        WebService.getInstance().excuteGetKstj("GetWaistData", "xml", dyxt, new ISoapRecv() {

            @Override
            public void onRecvData(String xmlStr, boolean success) {
                if (success) {
                    StringBuilder sb = new StringBuilder();
                    // xmlStr = "<Body><Response>" + xmlStr +
                    // "</Response></Body>";
                    Dyxt response = (Dyxt) XmlSerializerUtil.getInstance().beanFromXML(Dyxt.class,
                            xmlStr);
                    if (response == null || response.response == null) {
                        sb.append("【调用血糖】服务器接口异常");
                    } else {
                        if (response.response.errMsg.length() > 0) {
                            sb.append("【调用血糖】" + response.response.errMsg);
                        } else {
                            // 保存成功的话，更新居民基本信息的id和famliyID
                            Glucose glucose = response.response.glucose;

                            try {
                                float fValue = Float.parseFloat(glucose.glucoseValue);
                                // 震动，铃声
                                MyApplication.getInstance().shock();
                                MyApplication.getInstance().tone();
                                StringUtil.formatDecimal2(fValue);

                                xtzEditText.setText(StringUtil.formatDecimal2(fValue));
                                xtzdwTextView.setText("mmol/L");
                                jlEditText.setText(TestResultUtil.getXtResult(
                                        jclxSpinnerUtil.getSelectedValueInt(), fValue));
                                xtTextWatcher.afterTextChanged(xtzEditText.getText());
                                getValue();
                                sb.append("【调用血糖】成功");
                            } catch (NumberFormatException e) {
                                sb.append("【调用血糖】没有血糖数据");
                            }
                        }
                    }
                    mToast.setDuration(Toast.LENGTH_LONG);
                    mToast.setText(sb.toString());
                    mToast.show();

                } else {
                    mToast.setDuration(Toast.LENGTH_SHORT);
                    mToast.setText("网络请求异常");
                    mToast.show();
                }

                finishConnectionUi();
            }
        });
    }

    /**
     * 
     */
    protected void getResultFromDeviceBu34() {
    }

    /**
     * 
     */
    protected void getResultFromDeviceBeneCheck() {
        if (mBluetoothClient4BeneCheck == null) {
            mBluetoothClient4BeneCheck = new BluetoothClient4BeneCheck(mContext);
        }
        mBluetoothClient4BeneCheck.setOnReceiveListener(new OnReceiveListener() {
            @Override
            public void onReceiveCholData(float fValue, String unit) {
            }

            @Override
            public void onReceiveTgData(float fValue, String unit) {
            }

            @Override
            public void onReceiveHdlData(float fValue, String unit) {
            }

            @Override
            public void onReceiveLdlData(float fValue, String unit) {
            }

            @Override
            public void onReceiveGlucoseData(float fValue, String unit) {
                // 震动，铃声
                MyApplication.getInstance().shock();
                MyApplication.getInstance().tone();

                xtzEditText.setText(StringUtil.formatDecimal2(fValue));
                xtzdwTextView.setText(unit);
                jlEditText.setText(TestResultUtil.getXtResult(
                        jclxSpinnerUtil.getSelectedValueInt(), fValue));
                getValue();
                if (mBluetoothClient4BeneCheck != null) {
                    mBluetoothClient4BeneCheck.stop();
                    mBluetoothClient4BeneCheck = null;
                }
                finishConnectionUi();
            }

            @Override
            public void onTestError() {
                mToast.setText("获取数据失败，请稍后重试。");
                mToast.show();
                if (mBluetoothClient4BeneCheck != null) {
                    mBluetoothClient4BeneCheck.stop();
                    mBluetoothClient4BeneCheck = null;
                }

                finishConnectionUi();
            }
        });
        mBluetoothClient4BeneCheck.setOnDisconnectListener(new OnDisconnectListener() {

            @Override
            public void onDisconnect(boolean isSuccess) {
            }
        });
        mBluetoothClient4BeneCheck.run(new OnDialogResultListener() {

            @Override
            public void onConfirm() {// 显示进度条
                if (progressDialog == null) {
                    progressDialog = ProgressDialog.show(mContext, "正在获取", "请稍等...", false, true);
                }
                progressDialog.setOnCancelListener(new OnCancelListener() {

                    @Override
                    public void onCancel(DialogInterface arg0) {

                        if (mBluetoothClient4BeneCheck != null) {
                            mBluetoothClient4BeneCheck.stop();
                            mBluetoothClient4BeneCheck = null;
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

                        if (mBluetoothClient4BeneCheck != null) {
                            mBluetoothClient4BeneCheck.stop();
                            mBluetoothClient4BeneCheck = null;
                        }

                        finishConnectionUi();
                    }

                }, 30000);
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
                        if (mBluetoothClient4CardioChek != null) {
                            mBluetoothClient4CardioChek.stop();
                            mBluetoothClient4CardioChek = null;
                        }
                    }

                    @Override
                    public void onReceiveLdlData(float fValue, String unit) {
                        if (mBluetoothClient4CardioChek != null) {
                            mBluetoothClient4CardioChek.stop();
                            mBluetoothClient4CardioChek = null;
                        }
                    }

                    @Override
                    public void onReceiveHdlData(float fValue, String unit) {
                        if (mBluetoothClient4CardioChek != null) {
                            mBluetoothClient4CardioChek.stop();
                            mBluetoothClient4CardioChek = null;
                        }
                    }

                    @Override
                    public void onReceiveCholData(float fValue, String unit) {
                        if (mBluetoothClient4CardioChek != null) {
                            mBluetoothClient4CardioChek.stop();
                            mBluetoothClient4CardioChek = null;
                        }
                    }

                    @Override
                    public void onReceiveGlucoseData(float fValue, String unit) {
                        // 震动，铃声
                        MyApplication.getInstance().shock();
                        MyApplication.getInstance().tone();
                        StringUtil.formatDecimal2(fValue);
                        
                        xtzEditText.setText(StringUtil.formatDecimal2(fValue));
                        xtzdwTextView.setText(unit);
                        jlEditText.setText(TestResultUtil.getXtResult(jclxSpinnerUtil.getSelectedValueInt(), fValue));
                        xtTextWatcher.afterTextChanged(xtzEditText.getText());
                        getValue();
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

                        if (mBluetoothClient4CardioChek != null) {
                            mBluetoothClient4CardioChek.stop();
                            mBluetoothClient4CardioChek = null;
                        }

                        finishConnectionUi();
                    }

                }, 3000);
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

        // 血糖
        int condition = mJktj_kstj.getxTCSTJ();
        jclxSpinnerUtil.setSelectedPositionByValue(condition);
        xtzEditText.setText(mJktj_kstj.getxTValue());
        try {
            float fxt = Float.parseFloat(mJktj_kstj.getxTValue());
            jlEditText.setText(TestResultUtil.getXtResult(condition, fxt));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean getValue() {
        Jktj_kstj mJktj_kstj = (Jktj_kstj) beanMap.get(Jktj_kstj.class.getName());
        if (mJktj_kstj == null)
            return false;

        // 血糖
        try {
            int condition = Integer.parseInt(jclxSpinnerUtil.getSelectedValue());
            mJktj_kstj.setxTCSTJ(condition);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        String result = xtzEditText.getText().toString().trim();
        mJktj_kstj.setxTValue(result);
        result = jlEditText.getText().toString().trim();
        mJktj_kstj.setxTJL(result);
        
        return true;
    }

    public void clear(){
        // 血糖
        jclxSpinnerUtil.setSelection(0);
        xtzEditText.setText("");
        jlEditText.setText("");
   }

    /* (non-Javadoc)
     * @see com.cking.phss.page.ITestPage#getValidDataCount()
     */
    @Override
    public int getValidDataCount() {
        int count  = 0;
        String result = xtzEditText.getText().toString().trim();
        if (StringUtil.isDecimal(result)) {
            count++;
        }
        return count;
    }

    // 根据血糖值得出结论
    DecimalsTextWatcher xtTextWatcher = new DecimalsTextWatcher(2) {
        @Override
        public void afterTextChanged(Editable s) {
            super.afterTextChanged(s);
            String xtz = xtzEditText.getText().toString();
            try {
                if (StringUtil.isEmptyString(xtz)) {
                    jlEditText.setText("");
                    return;
                }
                try {
                    float fxt = Float.parseFloat(xtz);
                    int condition = Integer.parseInt(jclxSpinnerUtil.getSelectedValue());
                    jlEditText.setText(TestResultUtil.getXtResult(condition, fxt));
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }

        }
    };
}
