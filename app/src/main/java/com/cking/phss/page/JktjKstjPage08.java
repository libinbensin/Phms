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

import net.xinhuaxing.util.StringUtil;
import android.content.Context;
import android.text.Editable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.cking.phss.R;
import com.cking.phss.bean.IBean;
import com.cking.phss.bean.Jktj_kstj;
import com.cking.phss.bean.Jmjbxx;
import com.cking.phss.util.AppConfigFactory.AppConfig;
import com.cking.phss.util.DecimalsTextWatcher;
import com.cking.phss.util.TestResultUtil;
import com.cking.phss.util.TispToastFactory;
import com.cking.phss.widget.SpinnerUtil;

/**
 * 健康体检快速体检第2页 com.cking.phss.view.JktjKstjPage02
 * 
 * @author Administrator <br/>
 *         create at 2012-9-16 上午11:25:10
 */
public class JktjKstjPage08 extends LinearLayout implements IPage, ITestPage {
    @SuppressWarnings("unused")
    private static final String TAG = "JktjKstjPage08";
    private Context mContext = null;
    private Map<String, IBean> beanMap = null;
    /**
     * 第8页控件
     */

	EditText	yxsyEditText = null;
	SpinnerUtil yxsySpinnerUtil = null;
	EditText	yxsyjlEditText = null;

	EditText	bxbEditText = null;
	SpinnerUtil bxbSpinnerUtil = null;
	EditText	bxbjlEditText = null;

	EditText	ndyEditText = null;
	SpinnerUtil ndySpinnerUtil = null;
	EditText	ndyjlEditText = null;

	EditText	ndbEditText = null;
	SpinnerUtil ndbSpinnerUtil = null;
	EditText	ndbjlEditText = null;

	EditText	qxEditText = null;
	SpinnerUtil qxSpinnerUtil = null;
	EditText	qxjlEditText = null;

	EditText	nttEditText = null;
	SpinnerUtil nttSpinnerUtil = null;
	EditText	nttjlEditText = null;

	EditText	dhsEditText = null;
	SpinnerUtil dhsSpinnerUtil = null;
	EditText	dhsjlEditText = null;

	EditText	ntEditText = null;
	SpinnerUtil ntSpinnerUtil = null;
	EditText	ntjlEditText = null;

	EditText	wsscEditText = null;
	SpinnerUtil wsscSpinnerUtil = null;
	EditText	wsscjlEditText = null;

	EditText	phEditText = null;
	SpinnerUtil phSpinnerUtil = null;
	EditText	phjlEditText = null;

    public ViewGroup mParent;
    private Toast mToast = null;
    /**
     * @param context
     */
    public JktjKstjPage08(Context context, Map<String, IBean> beanMap, ViewGroup parent) {
        super(context);
        this.beanMap = beanMap;
        this.mParent = parent;
        init(context);
    }

    /**
     * @param context
     * @param attrs
     */
    public JktjKstjPage08(Context context, AttributeSet attrs) {
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
        inflater.inflate(R.layout.fragment_health_kstj_ty_layout, this);

        loadPage(context, this);
    }

    public void loadPage(Context context, ViewGroup viewGroup) {
		yxsyEditText		= (EditText) findViewById(R.id.yxsyEditText);
		yxsySpinnerUtil	 = (SpinnerUtil) findViewById(R.id.yxsySpinnerUtil);
		yxsyjlEditText		= (EditText) findViewById(R.id.yxsyjlEditText);
										  
		bxbEditText			= (EditText) findViewById(R.id.bxbEditText);
		bxbSpinnerUtil	 = (SpinnerUtil) findViewById(R.id.bxbSpinnerUtil);
		bxbjlEditText		= (EditText) findViewById(R.id.bxbjlEditText);
															  
		ndyEditText			= (EditText) findViewById(R.id.ndyEditText);
		ndySpinnerUtil	 = (SpinnerUtil) findViewById(R.id.ndySpinnerUtil);
		ndyjlEditText		= (EditText) findViewById(R.id.ndyjlEditText);
															  
		ndbEditText			= (EditText) findViewById(R.id.ndbEditText);
		ndbSpinnerUtil	 = (SpinnerUtil) findViewById(R.id.ndbSpinnerUtil);
		ndbjlEditText		= (EditText) findViewById(R.id.ndbjlEditText);
										  
		qxEditText			= (EditText) findViewById(R.id.qxEditText); 
		qxSpinnerUtil	 = (SpinnerUtil) findViewById(R.id.qxSpinnerUtil); 
		qxjlEditText		= (EditText) findViewById(R.id.qxjlEditText); 
										  
		nttEditText			= (EditText) findViewById(R.id.nttEditText);
		nttSpinnerUtil	 = (SpinnerUtil) findViewById(R.id.nttSpinnerUtil);
		nttjlEditText		= (EditText) findViewById(R.id.nttjlEditText);
															  
		dhsEditText			= (EditText) findViewById(R.id.dhsEditText);
		dhsSpinnerUtil	 = (SpinnerUtil) findViewById(R.id.dhsSpinnerUtil);
		dhsjlEditText		= (EditText) findViewById(R.id.dhsjlEditText);
										  
		ntEditText			= (EditText) findViewById(R.id.ntEditText); 
		ntSpinnerUtil	 = (SpinnerUtil) findViewById(R.id.ntSpinnerUtil); 
		ntjlEditText		= (EditText) findViewById(R.id.ntjlEditText); 
										  
		wsscEditText		= (EditText) findViewById(R.id.wsscEditText); 
		wsscSpinnerUtil	 = (SpinnerUtil) findViewById(R.id.wsscSpinnerUtil);
		wsscjlEditText		= (EditText) findViewById(R.id.wsscjlEditText);
										  
		phEditText			= (EditText) findViewById(R.id.phEditText); 
		phSpinnerUtil	 = (SpinnerUtil) findViewById(R.id.phSpinnerUtil); 
		phjlEditText		= (EditText) findViewById(R.id.phjlEditText);
		
        yxsySpinnerUtil.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                String[] retStr = { "尿亚硝酸盐结果正常", "尿亚硝酸盐结果呈阳性" };
                String text = yxsyEditText.getText().toString();
                if (!StringUtil.isEmptyString(text)) {
                    yxsyjlEditText.setText(retStr[arg2]);
                }
                yxsyEditText.setText(retStr[arg2]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        dhsSpinnerUtil.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                String[] retStr = { "尿胆红素正常", "少量尿胆红素", "中量尿胆红素", "大量尿胆红素" };
                String text = dhsEditText.getText().toString();
                if (!StringUtil.isEmptyString(text)) {
                    dhsjlEditText.setText(retStr[arg2]);
                }
                dhsEditText.setText(retStr[arg2]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

		bxbEditText.addTextChangedListener(bxbTextWatcher);

        ndyEditText.addTextChangedListener(ndyTextWatcher);

        ndbEditText.addTextChangedListener(ndbTextWatcher);

        qxEditText.addTextChangedListener(qxTextWatcher);

        nttEditText.addTextChangedListener(nttTextWatcher);

        ntEditText.addTextChangedListener(ntTextWatcher);

        wsscEditText.addTextChangedListener(wsscTextWatcher);

        phEditText.addTextChangedListener(phTextWatcher);

        AppConfig appConfig = (AppConfig) beanMap.get(AppConfig.class.getName());
        if (appConfig != null) {
            String value = appConfig.getTjsrxz();
            if (value != null) {
                yxsySpinnerUtil.setEnabled(value.contains("体液检测") ? false : true);
                bxbEditText.setEnabled(value.contains("体液检测") ? false : true);
                bxbSpinnerUtil.setEnabled(value.contains("体液检测") ? false : true);
                ndyEditText.setEnabled(value.contains("体液检测") ? false : true);
                ndySpinnerUtil.setEnabled(value.contains("体液检测") ? false : true);
                ndbEditText.setEnabled(value.contains("体液检测") ? false : true);
                ndbSpinnerUtil.setEnabled(value.contains("体液检测") ? false : true);
                qxEditText.setEnabled(value.contains("体液检测") ? false : true);
                qxSpinnerUtil.setEnabled(value.contains("体液检测") ? false : true);
                nttEditText.setEnabled(value.contains("体液检测") ? false : true);
                nttSpinnerUtil.setEnabled(value.contains("体液检测") ? false : true);
                dhsSpinnerUtil.setEnabled(value.contains("体液检测") ? false : true);
                ntEditText.setEnabled(value.contains("体液检测") ? false : true);
                ntSpinnerUtil.setEnabled(value.contains("体液检测") ? false : true);
                wsscEditText.setEnabled(value.contains("体液检测") ? false : true);
                wsscSpinnerUtil.setEnabled(value.contains("体液检测") ? false : true);
                phEditText.setEnabled(value.contains("体液检测") ? false : true);
            }
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
        
        // yxsyEditText.setText("");
        yxsySpinnerUtil.setSelectedPositionByData(mJktj_kstj.yxsybdljg);
        yxsyjlEditText.setText(mJktj_kstj.yxsyjl);

        bxbEditText.setText(mJktj_kstj.bxbz);
        bxbSpinnerUtil.setSelectedPositionByData(mJktj_kstj.bxbbdljg);
        bxbjlEditText.setText(mJktj_kstj.bxbjl);

        ndyEditText.setText(mJktj_kstj.ndyz);
        // ndySpinnerUtil.setSelectedPositionByData(mJktj_kstj.nd);
        ndyjlEditText.setText(mJktj_kstj.ndyjl);

        ndbEditText.setText(mJktj_kstj.ndbz);
        ndbSpinnerUtil.setSelectedPositionByData(mJktj_kstj.ndbbdljg);
        ndbjlEditText.setText(mJktj_kstj.ndbjl);

        qxEditText.setText(mJktj_kstj.qxz);
        qxSpinnerUtil.setSelectedPositionByData(mJktj_kstj.qxbdljg);
        qxjlEditText.setText(mJktj_kstj.qxjl);

        nttEditText.setText(mJktj_kstj.nttz);
        nttSpinnerUtil.setSelectedPositionByData(mJktj_kstj.nttbdljg);
        nttjlEditText.setText(mJktj_kstj.nttjl);

        dhsEditText.setText(mJktj_kstj.dhsz);
        dhsSpinnerUtil.setSelectedPositionByData(mJktj_kstj.dhsbdljg);
        dhsjlEditText.setText(mJktj_kstj.dhsjl);

        ntEditText.setText(mJktj_kstj.ntz);
        ntSpinnerUtil.setSelectedPositionByData(mJktj_kstj.ntbdljg);
        ntjlEditText.setText(mJktj_kstj.ntjl);

        wsscEditText.setText(mJktj_kstj.wsscz);
        wsscSpinnerUtil.setSelectedPositionByData(mJktj_kstj.wssbdljg);
        wsscjlEditText.setText(mJktj_kstj.wsscjl);

        phEditText.setText(mJktj_kstj.phz);
        // phSpinnerUtil.setSelectedPositionByData(mJktj_kstj.);
        phjlEditText.setText(mJktj_kstj.phjl);
    }


    @Override
    public boolean getValue() {
        Jktj_kstj mJktj_kstj = (Jktj_kstj) beanMap.get(Jktj_kstj.class.getName());
        if (mJktj_kstj == null)
            return false;

        // yxsyEditText.setText("");
        mJktj_kstj.yxsybdljg = yxsySpinnerUtil.getSelectedData();
        mJktj_kstj.yxsyjl = yxsyjlEditText.getText().toString();

        mJktj_kstj.bxbz = bxbEditText.getText().toString();
        mJktj_kstj.bxbbdljg = bxbSpinnerUtil.getSelectedData();
        mJktj_kstj.bxbjl = bxbjlEditText.getText().toString();

        mJktj_kstj.ndyz = ndyEditText.getText().toString();
        // ndySpinnerUtil.getSelectedData(mJktj_kstj.nd);
        mJktj_kstj.ndyjl = ndyjlEditText.getText().toString();

        mJktj_kstj.ndbz = ndbEditText.getText().toString();
        mJktj_kstj.ndbbdljg = ndbSpinnerUtil.getSelectedData();
        mJktj_kstj.ndbjl = ndbjlEditText.getText().toString();

        mJktj_kstj.qxz = qxEditText.getText().toString();
        mJktj_kstj.qxbdljg = qxSpinnerUtil.getSelectedData();
        mJktj_kstj.qxjl = qxjlEditText.getText().toString();

        mJktj_kstj.nttz = nttEditText.getText().toString();
        mJktj_kstj.nttbdljg = nttSpinnerUtil.getSelectedData();
        mJktj_kstj.nttjl = nttjlEditText.getText().toString();

        mJktj_kstj.dhsz = dhsEditText.getText().toString();
        mJktj_kstj.dhsbdljg = dhsSpinnerUtil.getSelectedData();
        mJktj_kstj.dhsjl = dhsjlEditText.getText().toString();

        mJktj_kstj.ntz = ntEditText.getText().toString();
        mJktj_kstj.ntbdljg = ntSpinnerUtil.getSelectedData();
        mJktj_kstj.ntjl = ntjlEditText.getText().toString();

        mJktj_kstj.wsscz = wsscEditText.getText().toString();
        mJktj_kstj.wssbdljg = wsscSpinnerUtil.getSelectedData();
        mJktj_kstj.wsscjl = wsscjlEditText.getText().toString();

        mJktj_kstj.phz = phEditText.getText().toString();
        // phSpinnerUtil.getSelectedData(mJktj_kstj.);
        mJktj_kstj.phjl = phjlEditText.getText().toString();
        return true;
    }

    public void clear(){

        yxsyEditText.setText("");
        yxsySpinnerUtil.setSelection(0);
        yxsyjlEditText.setText("");

        bxbEditText.setText("");
        bxbSpinnerUtil.setSelection(0);
        bxbjlEditText.setText("");

        ndyEditText.setText("");
        ndySpinnerUtil.setSelection(0);
        ndyjlEditText.setText("");

        ndbEditText.setText("");
        ndbSpinnerUtil.setSelection(0);
        ndbjlEditText.setText("");

        qxEditText.setText("");
        qxSpinnerUtil.setSelection(0);
        qxjlEditText.setText("");

        nttEditText.setText("");
        nttSpinnerUtil.setSelection(0);
        nttjlEditText.setText("");

        dhsEditText.setText("");
        dhsSpinnerUtil.setSelection(0);
        dhsjlEditText.setText("");

        ntEditText.setText("");
        ntSpinnerUtil.setSelection(0);
        ntjlEditText.setText("");

        wsscEditText.setText("");
        wsscSpinnerUtil.setSelection(0);
        wsscjlEditText.setText("");

        phEditText.setText("");
        phSpinnerUtil.setSelection(0);
        phjlEditText.setText("");
   }

    /* (non-Javadoc)
     * @see com.cking.phss.page.ITestPage#getValidDataCount()
     */
    @Override
    public int getValidDataCount() {
        EditText[] list = new EditText[] { yxsyjlEditText, bxbjlEditText, ndyjlEditText,
                ndbjlEditText, qxjlEditText, nttjlEditText, dhsjlEditText, ntjlEditText,
                wsscjlEditText, phjlEditText, };
        int count = 0;
        for (EditText item : list) {
            String result = item.getText().toString().trim();
            if (!StringUtil.isEmptyString(result)) {
                count++;
            }
        }
        return count;
    }


    // 白细胞的监听
    DecimalsTextWatcher bxbTextWatcher = new DecimalsTextWatcher(2) {
        @Override
        public void afterTextChanged(Editable s) {
            super.afterTextChanged(s);
            String mText = bxbEditText.getText().toString();

            try {
                if (StringUtil.isEmptyString(mText)) {
                    bxbjlEditText.setText("");
                    return;
                }
                try {
                    double mData = Double.parseDouble(mText);

                    int mResult = TestResultUtil.get_BaiXiBao_Data(mData);

                    if (mResult < 0)
                        return;

                    bxbSpinnerUtil.setSelectedPositionByValue(mResult);
                    bxbjlEditText.setText(TestResultUtil.get_BaiXiBao_Result(mResult));

                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
    };

    // 尿胆原的监听
    DecimalsTextWatcher ndyTextWatcher = new DecimalsTextWatcher(2) {
        @Override
        public void afterTextChanged(Editable s) {
            super.afterTextChanged(s);
            String mText = ndyEditText.getText().toString();

            try {
                if (StringUtil.isEmptyString(mText)) {
                    ndyjlEditText.setText("");
                    return;
                }
                try {
                    double mData = Double.parseDouble(mText);

                    int mResult = TestResultUtil.get_NiaoDanYuan_Data(mData);

                    if (mResult < 0)
                        return;

                    // ndySpinnerUtil.setSelectedPositionByValue(mResult);
                    ndyjlEditText.setText(TestResultUtil.get_NiaoDanYuan_Result(mResult));

                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
    };

    // 尿蛋白的监听
    DecimalsTextWatcher ndbTextWatcher = new DecimalsTextWatcher(3) {
        @Override
        public void afterTextChanged(Editable s) {
            super.afterTextChanged(s);
            String mText = ndbEditText.getText().toString();

            try {
                if (StringUtil.isEmptyString(mText)) {
                    ndyjlEditText.setText("");
                    return;
                }
                try {
                    double mData = Double.parseDouble(mText);

                    int mResult = TestResultUtil.get_NiaoDanBai_Data(mData);

                    if (mResult < 0)
                        return;

                    // ndySpinnerUtil.setSelectedPositionByValue(mResult);
                    ndbjlEditText.setText(TestResultUtil.get_NiaoDanBai_Result(mResult));

                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
    };

    // 潜血的监听
    DecimalsTextWatcher qxTextWatcher = new DecimalsTextWatcher(2) {
        @Override
        public void afterTextChanged(Editable s) {
            super.afterTextChanged(s);
            String mText = qxEditText.getText().toString();

            try {
                if (StringUtil.isEmptyString(mText)) {
                    qxjlEditText.setText("");
                    return;
                }
                try {
                    double mData = Double.parseDouble(mText);

                    int mResult = TestResultUtil.get_QianXue_Data(mData);

                    if (mResult < 0)
                        return;

                    qxSpinnerUtil.setSelectedPositionByValue(mResult);
                    qxjlEditText.setText(TestResultUtil.get_QianXue_Result(mResult));

                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
    };

    // 尿酮体的监听
    DecimalsTextWatcher nttTextWatcher = new DecimalsTextWatcher(2) {
        @Override
        public void afterTextChanged(Editable s) {
            super.afterTextChanged(s);
            String mText = nttEditText.getText().toString();

            try {
                if (StringUtil.isEmptyString(mText)) {
                    nttjlEditText.setText("");
                    return;
                }
                try {
                    double mData = Double.parseDouble(mText);

                    int mResult = TestResultUtil.get_NiaoTongTi_Data(mData);

                    if (mResult < 0)
                        return;

                    nttSpinnerUtil.setSelectedPositionByValue(mResult);
                    nttjlEditText.setText(TestResultUtil.get_NiaoTongTi_Result(mResult));

                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
    };

    // 尿糖的监听
    DecimalsTextWatcher ntTextWatcher = new DecimalsTextWatcher(2) {
        @Override
        public void afterTextChanged(Editable s) {
            super.afterTextChanged(s);
            String mText = ntEditText.getText().toString();

            try {
                if (StringUtil.isEmptyString(mText)) {
                    ntjlEditText.setText("");
                    return;
                }
                try {
                    double mData = Double.parseDouble(mText);

                    int mResult = TestResultUtil.get_NiaoTang_Data(mData);

                    if (mResult < 0)
                        return;

                    ntSpinnerUtil.setSelectedPositionByValue(mResult);
                    ntjlEditText.setText(TestResultUtil.get_NiaoTang_Result(mResult));

                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
    };

    // 维生素C的监听
    DecimalsTextWatcher wsscTextWatcher = new DecimalsTextWatcher(2) {
        @Override
        public void afterTextChanged(Editable s) {
            super.afterTextChanged(s);
            String mText = wsscEditText.getText().toString();

            try {
                if (StringUtil.isEmptyString(mText)) {
                    wsscjlEditText.setText("");
                    return;
                }
                try {
                    double mData = Double.parseDouble(mText);

                    int mResult = TestResultUtil.get_WeiShengSuC_Data(mData);

                    if (mResult < 0)
                        return;

                    wsscSpinnerUtil.setSelectedPositionByValue(mResult);
                    wsscjlEditText.setText(TestResultUtil.get_WeiShengSuC_Result(mResult));

                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
    };

    // PH的监听
    DecimalsTextWatcher phTextWatcher = new DecimalsTextWatcher(2) {
        @Override
        public void afterTextChanged(Editable s) {
            super.afterTextChanged(s);
            String mText = phEditText.getText().toString();

            try {
                if (StringUtil.isEmptyString(mText)) {
                    phjlEditText.setText("");
                    return;
                }
                try {
                    double mData = Double.parseDouble(mText);

                    int mResult = TestResultUtil.get_PH_Data(mData);

                    if (mResult < 0)
                        return;

                    // phSpinnerUtil.setSelectedPositionByValue(mResult);
                    phjlEditText.setText(TestResultUtil.get_PH_Result(mResult));

                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
    };
}
