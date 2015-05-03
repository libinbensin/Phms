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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.cking.phss.R;
import com.cking.phss.bean.IBean;
import com.cking.phss.bean.Jktj_kstj;
import com.cking.phss.bean.Jmjbxx;
import com.cking.phss.bluetooth.BluetoothServer4Ls501;
import com.cking.phss.util.AppConfigFactory.AppConfig;
import com.cking.phss.util.DecimalsTextWatcher;
import com.cking.phss.util.JgxxConfigFactory;
import com.cking.application.MyApplication;
import com.cking.phss.util.TestResultUtil;
import com.cking.phss.util.TispToastFactory;
import com.cking.phss.widget.DeviceListDialog.OnDialogResultListener;
import com.cking.phss.xml4jgxx.tags.constants.TagConstants;

/**
 * 健康体检快速体检第1页 com.cking.phss.view.JktjKstjPage01
 * 
 * @author Administrator <br/>
 *         create at 2012-9-16 上午11:25:10
 */
public class JktjKstjPage01 extends LinearLayout implements IPage, ITestPage {
	private Context mContext = null;
	private Map<String, IBean> beanMap = null;
	/**
	 * 第一页控件
	 */
	Button twjcButton;
	EditText twEditText;
	EditText twjlEditText;
	
	Button tzzsButton;
	EditText sgEditText;
	EditText tzEditText;
	EditText bmiEditText;
	EditText tzzsjlEditText;
	
	Button swclButton;
	EditText ywEditText;
	EditText tunweiEditText;
    EditText xwEditText;
	EditText ytbEditText;
	EditText swcljlEditText;

	private Toast mToast = null;

    private int deviceId = 0;

    private BluetoothServer4Ls501 mBluetoothServer4Ls501 = null;
    private Timer mTimeoutTimer = null;
    private ProgressDialog progressDialog = null;

	/**
	 * @param context
	 */
	public JktjKstjPage01(Context context, Map<String, IBean> beanMap) {
		super(context);
		this.beanMap = beanMap;
		init(context);
	}

	/**
	 * @param context
	 * @param attrs
	 */
	public JktjKstjPage01(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	/**
	 * @param context
	 */
	private void init(Context context) {
		mContext = context;
		mToast = TispToastFactory.getToast(context, "");
        deviceId = JgxxConfigFactory.findIdByName(mContext, TagConstants.XML_VALUE_NAME_DZYWC);
		LayoutInflater inflater = LayoutInflater.from(context);
		inflater.inflate(R.layout.fragment_health_kstj_cgtj_layout, this);

		loadPage(context, this);
	}

	/**
	 * 设置第一界面的默认显示信息
	 * 
	 * @param context
	 * @param viewGroup
	 */
	public void loadPage(Context context, ViewGroup viewGroup) {

        twjcButton = (Button) findViewById(R.id.twjcButton);
        twEditText = (EditText) findViewById(R.id.twEditText);
        twjlEditText = (EditText) findViewById(R.id.twjlEditText);

        tzzsButton = (Button) findViewById(R.id.tzzsButton);
        sgEditText = (EditText) findViewById(R.id.sgEditText);
        tzEditText = (EditText) findViewById(R.id.tzEditText);
        bmiEditText = (EditText) findViewById(R.id.bmiEditText);
        tzzsjlEditText = (EditText) findViewById(R.id.tzzsjlEditText);

        swclButton = (Button) findViewById(R.id.swclButton);
        ywEditText = (EditText) findViewById(R.id.ywEditText);
        tunweiEditText = (EditText) findViewById(R.id.tunweiEditText);
        xwEditText = (EditText) findViewById(R.id.xwEditText);
        ytbEditText = (EditText) findViewById(R.id.ytbEditText);
        swcljlEditText = (EditText) findViewById(R.id.swcljlEditText);

        twjcButton.setEnabled(false);
        if (JgxxConfigFactory.findDeviceInfoTagByName(mContext, TagConstants.XML_VALUE_NAME_TWJ) == null) {
            tzzsButton.setEnabled(false);
        }

        if (JgxxConfigFactory.findDeviceInfoTagByName(mContext, TagConstants.XML_VALUE_NAME_DZYWC) == null) {
            swclButton.setEnabled(false);
        }
        swclButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // 获取血脂数据
                // // 血脂
                // mCholEdit.setText(String.valueOf(getData().getDgc()));
                // mTgEdit.setText(String.valueOf(getData().getGysz()));
                // mDgcjlEditText.setText(getData().getDgcjl());
                // mGyszjlEditText.setText(getData().getGyszjl());
                // 显示进度条
                if (deviceId == TagConstants.XML_VALUE_ID_LX_LS501) {
                    getResultFromDeviceLs501();
                } else {
                    mToast.setText("系统暂不支持此设备");
                    mToast.show();
                }
            }
        });

        twEditText.addTextChangedListener(wdTextWatcher);

        sgEditText.addTextChangedListener(sgTextWatcher);
        tzEditText.addTextChangedListener(tzTextWatcher);

        sgEditText.addTextChangedListener(tzzsTextWatcher);
        tzEditText.addTextChangedListener(tzzsTextWatcher);
        bmiEditText.addTextChangedListener(DecimalsTextWatcher.getInstance());
        
        ywEditText.addTextChangedListener(swTextWatcher);
        tunweiEditText.addTextChangedListener(swTextWatcher);
        xwEditText.addTextChangedListener(swTextWatcher);
        AppConfig appConfig = (AppConfig) beanMap.get(AppConfig.class.getName());
        if (appConfig != null) {
            String value = appConfig.getTjsrxz();
            if (value != null) {
                twEditText.setEnabled(value.contains("体温") ? false : true);
                sgEditText.setEnabled(value.contains("体质指数") ? false : true);
                tzEditText.setEnabled(value.contains("体质指数") ? false : true);
                bmiEditText.setEnabled(value.contains("体质指数") ? false : true);
                ywEditText.setEnabled(value.contains("三围测量") ? false : true);
                tunweiEditText.setEnabled(value.contains("三围测量") ? false : true);
                xwEditText.setEnabled(value.contains("三围测量") ? false : true);
                ytbEditText.setEnabled(value.contains("三围测量") ? false : true);
            }
        }
	}

    /**
     * 
     */
    protected void getResultFromDeviceLs501() {
        mBluetoothServer4Ls501 = new BluetoothServer4Ls501(mContext);
        mBluetoothServer4Ls501.setOnReceiveListener(new BluetoothServer4Ls501.OnReceiveListener() {

            @Override
            public void onReceiveYwData(float fValue, String unit) {
            }

            @Override
            public void onReceiveXwData(float fValue, String unit) {
            }

            @Override
            public void onReceiveTwData(float fValue, String unit) {
            }

            @Override
            public void onTestError() {
                mToast.setText("获取数据失败，请重启蓝牙设备后再试。");
                mToast.show();
                if (mBluetoothServer4Ls501 != null) {
                    mBluetoothServer4Ls501.stop();
                    mBluetoothServer4Ls501 = null;
                }
                finishConnectionUi();
            }

            @Override
            public void onPaired() {
                // 震动，铃声
                MyApplication.getInstance().shock();
                MyApplication.getInstance().tone();

                mToast.setText("蓝牙设备配对成功");
                mToast.show();
                if (mBluetoothServer4Ls501 != null) {
                    mBluetoothServer4Ls501.stop();
                    mBluetoothServer4Ls501 = null;
                }
                finishConnectionUi();
            }

            @Override
            public void onReceiveAllData(float fywValue, String ywUnit, float fxwValue,
                    String xwUnit, float ftwValue, String twUnit) {
                // 震动，铃声
                MyApplication.getInstance().shock();
                MyApplication.getInstance().tone();

                if ((int) fywValue != 0) {
                    ywEditText.setText(fywValue + "");
                    swTextWatcher.afterTextChanged(ywEditText.getText());
                }
                if ((int) fxwValue != 0) {
                    xwEditText.setText(fxwValue + "");
                    swTextWatcher.afterTextChanged(xwEditText.getText());
                }
                if ((int) ftwValue != 0) {
                    tunweiEditText.setText(ftwValue + "");
                    swTextWatcher.afterTextChanged(tunweiEditText.getText());
                }

                getValue();
                if (mBluetoothServer4Ls501 != null) {
                    mBluetoothServer4Ls501.stop();
                    mBluetoothServer4Ls501 = null;
                }
                finishConnectionUi();
            }
        });
        mBluetoothServer4Ls501
                .setOnDisconnectListener(new BluetoothServer4Ls501.OnDisconnectListener() {

                    @Override
                    public void onDisconnect(boolean isSuccess) {
                        mToast.setText("获取数据失败，请重启蓝牙设备后再试。");
                        mToast.show();

                        finishConnectionUi();
                    }
                });
        mBluetoothServer4Ls501.run(new OnDialogResultListener() {

            @Override
            public void onConfirm() {// 显示进度条
                if (progressDialog == null) {
                    progressDialog = ProgressDialog.show(mContext, "正在获取", "请稍等...", false, true);
                }
                progressDialog.setOnCancelListener(new OnCancelListener() {

                    @Override
                    public void onCancel(DialogInterface arg0) {

                        if (mBluetoothServer4Ls501 != null) {
                            mBluetoothServer4Ls501.stop();
                            mBluetoothServer4Ls501 = null;
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

                        if (mBluetoothServer4Ls501 != null) {
                            mBluetoothServer4Ls501.stop();
                            mBluetoothServer4Ls501 = null;
                        }

                        finishConnectionUi();
                    }

                }, 40000);
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

        // 体温
        twEditText.setText(mJktj_kstj.getTw());
        try {
            float dgc = Float.parseFloat(mJktj_kstj.getTw());
            twjlEditText.setText(TestResultUtil.getTwResult(dgc));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        
        // 体质指数
        sgEditText.setText(mJktj_kstj.getHeight());
        tzEditText.setText(mJktj_kstj.getWeight());
        try {

            double dsg = Double.parseDouble(mJktj_kstj.getHeight()) / 100.0;
            double dtz = Double.parseDouble(mJktj_kstj.getWeight());
            
            double bmi = TestResultUtil.getBmiResult(dsg, dtz);
            
            bmiEditText.setText(StringUtil.formatDecimal(bmi));
            tzzsjlEditText.setText(TestResultUtil.getSgtzResult(bmi));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        // 三围
        ywEditText.setText(mJktj_kstj.getWaist());
        tunweiEditText.setText(mJktj_kstj.gethIP());
        xwEditText.setText(mJktj_kstj.getBust());
        try {
            float yw = Float.parseFloat(mJktj_kstj.getWaist());
            float tunwei = Float.parseFloat(mJktj_kstj.gethIP());
            String ytb = TestResultUtil.getYtbResult(mJmjbxx.getSexCD() == 1, yw, tunwei);
            ytbEditText.setText(StringUtil.formatDecimal(yw / tunwei));
            swcljlEditText.setText(ytb);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
	}
    
	@Override
	public boolean getValue() {
		Jktj_kstj mJktj_kstj = (Jktj_kstj) beanMap.get(Jktj_kstj.class
				.getName());
		if (mJktj_kstj == null)
			return false;

		// 体温
		String result = twEditText.getText().toString().trim();
		mJktj_kstj.setTw(result);
		result = twjlEditText.getText().toString().trim();
		mJktj_kstj.setTwjl(result);

		// 体质指数
		/**
		* 屏蔽代码说明：
		* 由于需求规定常规检测和人体成分中身高体重数据同步
		* 如果在这里获取值的话会造成如下问题：
		* 当在常规检测中输入值由于被人体成分中的覆盖，造成
		* 最后值没变。
		*/
/*		result = sgEditText.getText().toString().trim();
		mJktj_kstj.setHeight(result);
		result = tzEditText.getText().toString().trim();
		mJktj_kstj.setWeight(result);
		result = bmiEditText.getText().toString().trim();
		mJktj_kstj.setbMI(result);
		result = tzzsjlEditText.getText().toString().trim();
		mJktj_kstj.setTzjl(result);*/

		// 三围
		result = ywEditText.getText().toString().trim();
		mJktj_kstj.setWaist(result);
		result = tunweiEditText.getText().toString().trim();
		mJktj_kstj.sethIP(result);
		result = xwEditText.getText().toString().trim();
		mJktj_kstj.setBust(result);
		result = ytbEditText.getText().toString().trim();
		mJktj_kstj.setYtb(result);
		result = swcljlEditText.getText().toString().trim();
		mJktj_kstj.setYtbjl(result);
        
		return true;
	}

    public void clear() {
        // 体温
        twEditText.setText("");
        twjlEditText.setText("");
        
        // 体质指数
        sgEditText.setText("");
        tzEditText.setText("");
        bmiEditText.setText("");
        tzzsjlEditText.setText("");

        // 三围
        ywEditText.setText("");
        tunweiEditText.setText("");
        xwEditText.setText("");
        ytbEditText.setText("");
        swcljlEditText.setText("");
	}

    /* (non-Javadoc)
     * @see com.cking.phss.page.ITestPage#getValidDataCount()
     */
    @Override
    public int getValidDataCount() {
        int count  = 0;
        String result = twEditText.getText().toString().trim();
        if (StringUtil.isDecimal(result)) {
            count++;
        }
        result = sgEditText.getText().toString().trim();
        if (StringUtil.isDecimal(result)) {
            count++;
        }
        result = tzEditText.getText().toString().trim();
        if (StringUtil.isDecimal(result)) {
            count++;
        }
        result = ywEditText.getText().toString().trim();
        if (StringUtil.isDecimal(result)) {
            count++;
        }
        result = tunweiEditText.getText().toString().trim();
        if (StringUtil.isDecimal(result)) {
            count++;
        }
        result = xwEditText.getText().toString().trim();
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

    // 根据温度得出结论
    DecimalsTextWatcher wdTextWatcher = new DecimalsTextWatcher(2) {
        @Override
        public void afterTextChanged(Editable s) {
            super.afterTextChanged(s);
            String dgc = twEditText.getText().toString();
            try {
                if (StringUtil.isEmptyString(dgc)) {
                    twjlEditText.setText("");
                    return;
                }
                try {
                    float ftw = Float.parseFloat(dgc);
                    twjlEditText.setText(TestResultUtil.getTwResult(ftw));
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }

        }
    };
    // 根据身高体重得出BMI和结论
    DecimalsTextWatcher tzzsTextWatcher = new DecimalsTextWatcher(2) {
        @Override
        public void afterTextChanged(Editable s) {
            super.afterTextChanged(s);
            String sg = sgEditText.getText().toString();
            String tz = tzEditText.getText().toString();
            try {
                if (StringUtil.isEmptyString(sg) || StringUtil.isEmptyString(tz)) {
                    bmiEditText.setText("");
                    tzzsjlEditText.setText("");
                    return;
                }
                double dsg = Double.parseDouble(sg) / 100.0;
                double dtz = Double.parseDouble(tz);
                double bmi = TestResultUtil.getBmiResult(dsg, dtz);
                bmiEditText.setText(StringUtil.formatDecimal(bmi));
                tzzsjlEditText.setText(TestResultUtil.getSgtzResult(bmi));

                Jktj_kstj mJktj_kstj = (Jktj_kstj) beanMap.get(Jktj_kstj.class.getName());
                if (mJktj_kstj == null)
                    return;
                mJktj_kstj.setbMI(StringUtil.formatDecimal(bmi));
                mJktj_kstj.setTzjl(TestResultUtil.getSgtzResult(bmi));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }

        }
    };
    // 根据腰围臀围得出腰臀比和结论
    DecimalsTextWatcher swTextWatcher = new DecimalsTextWatcher(2) {
        @Override
        public void afterTextChanged(Editable s) {
            super.afterTextChanged(s);

            String yw = ywEditText.getText().toString();
            String tunwei = tunweiEditText.getText().toString();

            Jktj_kstj mJktj_kstj = (Jktj_kstj) beanMap.get(Jktj_kstj.class.getName());
            if (mJktj_kstj == null)
                return;

            try {
                if (StringUtil.isEmptyString(yw) || StringUtil.isEmptyString(tunwei)) {
                    ytbEditText.setText("");
                    swcljlEditText.setText("");
                    return;
                }
                try {
                    float fyw = Float.parseFloat(yw);
                    float ftunwei = Float.parseFloat(tunwei);

                    Jmjbxx mJmjbxx = (Jmjbxx) beanMap.get(Jmjbxx.class.getName());
                    if (mJmjbxx == null)
                        return;
                    mJktj_kstj.setWaist(fyw + "");
                    mJktj_kstj.setYtb(StringUtil.formatDecimal(fyw / ftunwei));
                    ytbEditText.setText(StringUtil.formatDecimal(fyw / ftunwei));
                    String ytbjl = TestResultUtil.getYtbResult(mJmjbxx.getSexCD() == 1, fyw,
                            ftunwei);
                    swcljlEditText.setText(ytbjl);
                    mJktj_kstj.setYtbjl(ytbjl);

                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }

        }
    };
}
