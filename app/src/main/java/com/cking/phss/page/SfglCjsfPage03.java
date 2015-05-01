package com.cking.phss.page;

import java.util.Map;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.cking.phss.R;
import com.cking.phss.bean.IBean;
import com.cking.phss.bean.Jmjbxx;
import com.cking.phss.bean.Sfgljl_cjsf;
import com.cking.phss.global.Global;
import com.cking.phss.util.TispToastFactory;
import com.cking.phss.widget.CalendarText;
import com.cking.phss.widget.RadioGroup;
import com.cking.phss.widget.SpinnerUtil;
import com.cking.phss.xml.util.XmlSerializerUtil;
import com.cking.phss.xml.util.XmlTag;

public class SfglCjsfPage03 extends MyPage  {

    private static final String TAG = "SfglCjsfPage03";
    @XmlTag(name = "TrainingEvaluation")
    SpinnerUtil xlpgSpinnerUtil = null;
    @XmlTag(name = "ReferralReason")
	EditText yyEditText = null;
    @XmlTag(name = "ReferralDirection")
	EditText zjqxEditText = null;

    @XmlTag(name = "Smoking")
	RadioGroup  sfxyRadioGroup = null;
	RadioButton sfxy01RadioButton = null;
	RadioButton sfxy02RadioButton = null;
    @XmlTag(name = "SmokingDay")
	EditText rxylEditText = null;

    @XmlTag(name = "Drinking")
	RadioGroup  sfyjRadioGroup = null;
	RadioButton sfyj01RadioButton = null;
	RadioButton sfyj02RadioButton = null;
    @XmlTag(name = "DrinkingDay")
	SpinnerUtil jdzlSpinnerUtil = null;
    @XmlTag(name = "DrinkingType")
	EditText ryjlEditText = null;

    @XmlTag(name = "Exercise")
	RadioGroup  sfydRadioGroup = null;
	RadioButton sfyd01RadioButton = null;
	RadioButton sfyd02RadioButton = null;

    @XmlTag(name = "ExerciseFrequency")
	EditText ydpl01EditText = null;
    @XmlTag(name = "ExerciseDuration")
	EditText ydpl02EditText = null;

    @XmlTag(name = "ExerciseEvent")
	EditText ydxmEditText = null;
    @XmlTag(name = "SaltIntake")
	EditText syl01EditText = null;
    @XmlTag(name = "SaltConclusion")
	EditText syl02EditText = null;

	SpinnerUtil syl01SpinnerUtil = null;
	SpinnerUtil syl02SpinnerUtil = null; 

    @XmlTag(name = "Psyche")
	SpinnerUtil xltzSpinnerUtil = null;
    @XmlTag(name = "Compliance")
	SpinnerUtil zyxwSpinnerUtil = null;

    @XmlTag(name = "Evaluation")
	SpinnerUtil sfpgSpinnerUtil = null;

    @XmlTag(name = "Suggest")
	EditText kfjyEditText = null;

    @XmlTag(name = "FlupDate")
	CalendarText xcsfrqCalendarText = null;

	EditText sfysqmEditText = null;

    private Context mContext;
	private Toast mToast;
    private Map<String, IBean> beanMap = null;
	public SfglCjsfPage03(Context context) {
		super(context);
		// init(context);
		// TODO Auto-generated constructor stub
	}

    public SfglCjsfPage03(Context context, Map<String, IBean> beanMap) {
        super(context);
        this.beanMap = beanMap;
        // init(context);
    }

	/**
	 * @param context
	 * @param attrs
	 */
	public SfglCjsfPage03(Context context, AttributeSet attrs) {
		super(context, attrs);
		// init(context);
		// TODO Auto-generated constructor stub
	}
	protected void init(Context context){
        mContext = context;
		mToast =TispToastFactory.getToast(context, "");
		LayoutInflater.from(context).inflate(R.layout.fragment_sfgl_cjsf_03_layout, this);
        loadPage(context, this);
	}
	public void loadPage(Context context, ViewGroup viewGroup) {   
        xlpgSpinnerUtil = (SpinnerUtil) findViewById(R.id.xlpgSpinnerUtil);
        yyEditText = (EditText) findViewById(R.id.yyEditText);
        zjqxEditText = (EditText) findViewById(R.id.zjqxEditText);

		sfxyRadioGroup    = (RadioGroup)  findViewById(R.id.sfxyRadioGroup   );
        sfxy01RadioButton = (RadioButton) findViewById(R.id.sfxy01RadioButton);
        sfxy02RadioButton = (RadioButton) findViewById(R.id.sfxy02RadioButton);
        rxylEditText = (EditText) findViewById(R.id.rxylEditText);

		sfyjRadioGroup    = (RadioGroup)  findViewById(R.id.sfyjRadioGroup    );
        sfyj01RadioButton = (RadioButton) findViewById(R.id.sfyj01RadioButton);
        sfyj02RadioButton = (RadioButton) findViewById(R.id.sfyj02RadioButton);
        jdzlSpinnerUtil = (SpinnerUtil) findViewById(R.id.jdzlSpinnerUtil);
        ryjlEditText = (EditText) findViewById(R.id.ryjlEditText);

		sfydRadioGroup    = (RadioGroup)  findViewById(R.id.sfydRadioGroup    );
        sfyd01RadioButton = (RadioButton) findViewById(R.id.sfyd01RadioButton);
        sfyd02RadioButton = (RadioButton) findViewById(R.id.sfyd02RadioButton);

        ydpl01EditText = (EditText) findViewById(R.id.ydpl01EditText);
        ydpl02EditText = (EditText) findViewById(R.id.ydpl02EditText);

        ydxmEditText = (EditText) findViewById(R.id.ydxmEditText);
        syl01EditText = (EditText) findViewById(R.id.syl01EditText);
        syl02EditText = (EditText) findViewById(R.id.syl02EditText);

        syl01SpinnerUtil = (SpinnerUtil) findViewById(R.id.syl01SpinnerUtil);
        syl02SpinnerUtil = (SpinnerUtil) findViewById(R.id.syl02SpinnerUtil);

		xltzSpinnerUtil = (SpinnerUtil) findViewById(R.id.xltzSpinnerUtil);
        zyxwSpinnerUtil = (SpinnerUtil) findViewById(R.id.zyxwSpinnerUtil);

        sfpgSpinnerUtil = (SpinnerUtil) findViewById(R.id.sfpgSpinnerUtil);

        kfjyEditText = (EditText) findViewById(R.id.kfjyEditText);

        xcsfrqCalendarText = (CalendarText) findViewById(R.id.xcsfrqCalendarText);

        sfysqmEditText = (EditText) findViewById(R.id.sfysqmEditText);
		
		// 是否吸烟radio的初始状态和 相应控件的状态
		sfxyRadioGroup.check(R.id.sfxy01RadioButton);// 默认没有不良的反应
		rxylEditText.setText("");
		rxylEditText.setEnabled(false);
        sfxyRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(android.widget.RadioGroup group, int checkedId) {
                if (checkedId == sfxy01RadioButton.getId()) {// 如果点击了无
                    rxylEditText.setText("");
                    rxylEditText.setEnabled(false);
                } else {// 如果点击了有
                    rxylEditText.setEnabled(true);
                }
            }
        });

		// 是否饮酒radio的初始状态和 相应控件的状态
		sfyjRadioGroup.check(R.id.sfyj01RadioButton);// 默认没有不良的反应
		jdzlSpinnerUtil.setEnabled(false);
		ryjlEditText.setText("");
		ryjlEditText.setEnabled(false);

        sfyjRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(android.widget.RadioGroup group, int checkedId) {
                if (checkedId == sfyj01RadioButton.getId()) {// 如果点击了无
                    ryjlEditText.setText("");
                    jdzlSpinnerUtil.setEnabled(false);
                    ryjlEditText.setEnabled(false);
                } else {// 如果点击了有
                    jdzlSpinnerUtil.setEnabled(true);
                    ryjlEditText.setEnabled(true);
                }
            }
        });

		// 是否运动radio的初始状态和 相应控件的状态
		sfydRadioGroup.check(R.id.sfyd01RadioButton);// 默认没有不良的反应	
		ydpl01EditText.setText("");
		ydpl02EditText.setText("");
		ydpl01EditText.setEnabled(false);
		ydpl02EditText.setEnabled(false);

        sfydRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(android.widget.RadioGroup group, int checkedId) {
                if (checkedId == sfyd01RadioButton.getId()) {// 如果点击了无
                    ydpl01EditText.setText("");
                    ydpl02EditText.setText("");
                    ydpl01EditText.setEnabled(false);
                    ydpl02EditText.setEnabled(false);
                } else {// 如果点击了有
                    ydpl01EditText.setEnabled(true);
                    ydpl02EditText.setEnabled(true);
                }
            }
        });
             
        syl01EditText.addTextChangedListener(syl01TextWatcher);
        syl02EditText.addTextChangedListener(syl02TextWatcher);
	}
	
	private TextWatcher syl01TextWatcher = new TextWatcher() {		
        int mCurVal = 0;
        @Override
        public void beforeTextChanged(CharSequence s, int arg1, int arg2,int arg3) {
        }
       
        @Override
        public void onTextChanged(CharSequence s, int arg1, int arg2,int arg3) {
        	String mStr = s.toString();
        	if( !mStr.isEmpty() )
        	{
        		mCurVal = Integer.parseInt(mStr);
        	}
        	else
        	{
        		mCurVal = 0;
        	}
        }
       
        @Override
        public void afterTextChanged(Editable s) {
        	
        	if( mCurVal >=0 && 
        	    mCurVal <6  )
        	{
        		syl01SpinnerUtil.setSelectedPositionByValue(1);
        	}
        	else if( mCurVal >=6 && 
            	    mCurVal <=12  )
        	{
        		syl01SpinnerUtil.setSelectedPositionByValue(2);
        	}
        	else
        	{        	
        		syl01SpinnerUtil.setSelectedPositionByValue(3);
        	}
        }
    };
    
    private TextWatcher syl02TextWatcher = new TextWatcher() {		
        int mCurVal = 0;
        @Override
        public void beforeTextChanged(CharSequence s, int arg1, int arg2,int arg3) {
        }
       
        @Override
        public void onTextChanged(CharSequence s, int arg1, int arg2,int arg3) {
        	String mStr = s.toString();
        	if( !mStr.isEmpty() )
        	{
        		mCurVal = Integer.parseInt(mStr);
        	}
        	else
        	{
        		mCurVal = 0;
        	}
        }
       
        @Override
        public void afterTextChanged(Editable s) {
        	
        	if( mCurVal >=0 && 
        	    mCurVal <6  )
        	{
        		syl02SpinnerUtil.setSelectedPositionByValue(1);
        	}
        	else if( mCurVal >=6 && 
            	    mCurVal <=12  )
        	{
        		syl02SpinnerUtil.setSelectedPositionByValue(2);
        	}
        	else
        	{        	
        		syl02SpinnerUtil.setSelectedPositionByValue(3);
        	}
        }
    };

    /*
     * (non-Javadoc)
     * 
     * @see com.cking.phss.page.IPage#setValue()
     */
    @Override
    public void setValue() { if (!hasInit) {return;}
        Jmjbxx mJmjbxx = (Jmjbxx) beanMap.get(Jmjbxx.class.getName());
        Sfgljl_cjsf sfgljl = (Sfgljl_cjsf) beanMap.get(Sfgljl_cjsf.class.getName());
        if (mJmjbxx == null || sfgljl == null || mJmjbxx.getResidentName().equals("")) {
            return;
        }

        try {
            XmlSerializerUtil.getInstance().setValue(this, sfgljl);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        // 其他自定义
        sfysqmEditText.setText(Global.doctorName);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.cking.phss.page.IPage#getValue()
     */
    @Override
    public boolean getValue() { if (!hasInit) {return false;}
        Sfgljl_cjsf sfgljl = (Sfgljl_cjsf) beanMap.get(Sfgljl_cjsf.class.getName());
        if (sfgljl == null) {
            Log.e(TAG, "mSfgljl is null");
            return false;
        }

        try {
            XmlSerializerUtil.getInstance().getValue(this, sfgljl);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.cking.phss.page.IPage#clear()
     */
    @Override
    public void clear() {
    }
}
