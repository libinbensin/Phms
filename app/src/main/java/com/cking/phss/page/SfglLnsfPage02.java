package com.cking.phss.page;

import java.util.Map;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.cking.phss.R;
import com.cking.phss.bean.IBean;
import com.cking.phss.bean.Jmjbxx;
import com.cking.phss.bean.Sfgljl_lnsf;
import com.cking.phss.util.RadioGroupUtil;
import com.cking.phss.util.TispToastFactory;
import com.cking.phss.widget.SpinnerUtil;
import com.cking.phss.xml.util.XmlSerializerUtil;
import com.cking.phss.xml.util.XmlTag;

public class SfglLnsfPage02 extends MyPage  {

    private static final String TAG = "SfglLnsfPage02";
    @XmlTag(name = "Height")
    EditText sgEditText = null;
    @XmlTag(name = "Weight")
	EditText tz01EditText = null;
	EditText tz02EditText = null;
    @XmlTag(name = "BodyMassIndex")
	EditText tzzs01EditText = null;
	EditText tzzs02EditText = null;

    @XmlTag(name = "Cholesterol")
	EditText xzdgcEditText = null;
    @XmlTag(name = "HDensityLipoprotein")
	EditText gmdzdbEditText = null;
    @XmlTag(name = "Triglyceride")
	EditText xgyszEditText = null;
    @XmlTag(name = "LDensityLipoprotein")
	EditText dmdzdbEditText = null;
    @XmlTag(name = "FastingBloodGlucose")
	EditText kfxtEditText = null;
	EditText qtEditText = null;

	RadioGroup  sfxyRadioGroup = null;
	RadioButton sfxy01RadioButton = null;
	RadioButton sfxy02RadioButton = null;
    @XmlTag(name = "Smoking")
    RadioGroupUtil sfxyRadioGroupUtil;

    private int[] sfxyRadioBtnIds = new int[] { R.id.sfxy01RadioButton, R.id.sfxy02RadioButton };

    @XmlTag(name = "SmokingDay")
	EditText rxylEditText = null;

	RadioGroup  sfyjRadioGroup = null;
	RadioButton sfyj01RadioButton = null;
	RadioButton sfyj02RadioButton = null;
    @XmlTag(name = "Drinking")
    RadioGroupUtil sfyjRadioGroupUtil;

    private int[] sfyjRadioBtnIds = new int[] { R.id.sfyj01RadioButton, R.id.sfyj02RadioButton };

    @XmlTag(name = "DrinkingType")
	SpinnerUtil jdzlSpinnerUtil = null;
    @XmlTag(name = "DrinkingDay")
	EditText ryjlEditText = null;

	RadioGroup  sfydRadioGroup = null;
	RadioButton sfyd01RadioButton = null;
	RadioButton sfyd02RadioButton = null;
    @XmlTag(name = "Exercise")
    RadioGroupUtil sfydRadioGroupUtil;

    private int[] sfydRadioBtnIds = new int[] { R.id.sfyd01RadioButton, R.id.sfyd02RadioButton };

    @XmlTag(name = "ExerciseEvent")
	EditText ydpl01EditText = null;
    @XmlTag(name = "ExerciseFrequency")
	EditText ydpl02EditText = null;

    private Context mContext;
	private Toast mToast;
    private Map<String, IBean> beanMap = null;
	public SfglLnsfPage02(Context context) {
		super(context);
		// init(context);
		// TODO Auto-generated constructor stub
	}

    public SfglLnsfPage02(Context context, Map<String, IBean> beanMap) {
        super(context);
        this.beanMap = beanMap;
        // init(context);
    }
	/**
	 * @param context
	 * @param attrs
	 */
	public SfglLnsfPage02(Context context, AttributeSet attrs) {
		super(context, attrs);
		// init(context);
		// TODO Auto-generated constructor stub
	}
	protected void init(Context context){
        mContext = context;
		mToast =TispToastFactory.getToast(context, "");
		LayoutInflater.from(context).inflate(R.layout.fragment_sfgl_lnsf_02_layout, this);
        loadPage(context, this);
	}
	public void loadPage(Context context, ViewGroup viewGroup) {   

        sgEditText = (EditText) findViewById(R.id.sgEditText);
        tz01EditText = (EditText) findViewById(R.id.tz01EditText);
        tz02EditText = (EditText) findViewById(R.id.tz02EditText);
        tzzs01EditText = (EditText) findViewById(R.id.tzzs01EditText);
        tzzs02EditText = (EditText) findViewById(R.id.tzzs02EditText);
															                  
        xzdgcEditText = (EditText) findViewById(R.id.xzdgcEditText);
        gmdzdbEditText = (EditText) findViewById(R.id.gmdzdbEditText);
        xgyszEditText = (EditText) findViewById(R.id.xgyszEditText);
        dmdzdbEditText = (EditText) findViewById(R.id.dmdzdbEditText);
        kfxtEditText = (EditText) findViewById(R.id.kfxtEditText);
        qtEditText = (EditText) findViewById(R.id.qtEditText);

		sfxyRadioGroup    = (RadioGroup)  findViewById(R.id.sfxyRadioGroup   );
		sfxy01RadioButton = (RadioButton) findViewById(R.id.sfxy01RadioButton);
		sfxy02RadioButton = (RadioButton) findViewById(R.id.sfxy02RadioButton);
															                  
        rxylEditText = (EditText) findViewById(R.id.rxylEditText);

		sfyjRadioGroup    = (RadioGroup)  findViewById(R.id.sfyjRadioGroup    );
		sfyj01RadioButton = (RadioButton) findViewById(R.id.sfyj01RadioButton );
		sfyj02RadioButton = (RadioButton) findViewById(R.id.sfyj02RadioButton );

        jdzlSpinnerUtil = (SpinnerUtil) findViewById(R.id.jdzlSpinnerUtil);
        ryjlEditText = (EditText) findViewById(R.id.ryjlEditText);


		sfydRadioGroup    = (RadioGroup)  findViewById(R.id.sfydRadioGroup    );
		sfyd01RadioButton = (RadioButton) findViewById(R.id.sfyd01RadioButton );
		sfyd02RadioButton = (RadioButton) findViewById(R.id.sfyd02RadioButton );
															                  
        ydpl01EditText = (EditText) findViewById(R.id.ydpl01EditText);
        ydpl02EditText = (EditText) findViewById(R.id.ydpl02EditText);

        sfxyRadioGroupUtil = new RadioGroupUtil(mContext, sfxyRadioBtnIds, viewGroup, "qyqk");
        sfyjRadioGroupUtil = new RadioGroupUtil(mContext, sfyjRadioBtnIds, viewGroup, "qyqk");
        sfydRadioGroupUtil = new RadioGroupUtil(mContext, sfydRadioBtnIds, viewGroup, "qyqk");

		// 是否吸烟radio的初始状态和 相应控件的状态
		sfxyRadioGroup.check(R.id.sfxy01RadioButton);// 默认没有不良的反应
		rxylEditText.setText("");
		rxylEditText.setEnabled(false);
		sfxyRadioGroup
				.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(RadioGroup group, int checkedId) {
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

		sfyjRadioGroup
				.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(RadioGroup group, int checkedId) {
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

		sfydRadioGroup
				.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(RadioGroup group, int checkedId) {
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

	}

    /*
     * (non-Javadoc)
     * 
     * @see com.cking.phss.page.IPage#setValue()
     */
    @Override
    public void setValue() { if (!hasInit) {return;}
        Jmjbxx mJmjbxx = (Jmjbxx) beanMap.get(Jmjbxx.class.getName());
        Sfgljl_lnsf sfgljl = (Sfgljl_lnsf) beanMap.get(Sfgljl_lnsf.class.getName());
        if (mJmjbxx == null || sfgljl == null || mJmjbxx.getResidentName().equals("")) {
            return;
        }

        try {
            XmlSerializerUtil.getInstance().setValue(SfglLnsfPage02.this, sfgljl);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.cking.phss.page.IPage#getValue()
     */
    @Override
    public boolean getValue() { if (!hasInit) {return false;}
        Sfgljl_lnsf sfgljl = (Sfgljl_lnsf) beanMap.get(Sfgljl_lnsf.class.getName());
        if (sfgljl == null) {
            Log.e(TAG, "mSfgljl is null");
            return false;
        }

        try {
            XmlSerializerUtil.getInstance().getValue(SfglLnsfPage02.this, sfgljl);
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
