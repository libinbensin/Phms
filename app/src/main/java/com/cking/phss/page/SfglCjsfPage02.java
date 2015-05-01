package com.cking.phss.page;

import java.util.Map;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.cking.phss.R;
import com.cking.phss.bean.IBean;
import com.cking.phss.bean.Jmjbxx;
import com.cking.phss.bean.Sfgljl_cjsf;
import com.cking.phss.util.CheckBoxGroupUtil;
import com.cking.phss.util.TispToastFactory;
import com.cking.phss.widget.SpinnerUtil;
import com.cking.phss.xml.util.XmlSerializerUtil;
import com.cking.phss.xml.util.XmlTag;

public class SfglCjsfPage02 extends MyPage  {

    private static final String TAG = "SfglCjsfPage02";
    Button tjsjButton = null;
    @XmlTag(name = "Symptom")
	EditText zzEditText = null;

    @XmlTag(name = "Weight")
	EditText tzEditText = null;
    @XmlTag(name = "SystolicPressure")
	EditText xy01EditText = null;
    @XmlTag(name = "DiastolicPressure")
	EditText xy02EditText = null;
    @XmlTag(name = "HeartRate")
	EditText xlEditText = null;
    @XmlTag(name = "OtherSigns")
	EditText qtEditText = null;
	
	CheckBox kfxm01CheckBox = null;
	CheckBox kfxm02CheckBox = null;
	CheckBox kfxm03CheckBox = null;
	CheckBox kfxm04CheckBox = null;
	CheckBox kfxm05CheckBox = null;
	CheckBox kfxm06CheckBox = null;
	CheckBox kfxm07CheckBox = null;
	EditText kfxmEditText = null;

    @XmlTag(name = "RecureProject")
    CheckBoxGroupUtil kfxmCheckBoxGroup;

    int[] kfxmViewIds = new int[] { R.id.kfxm01CheckBox, R.id.kfxm02CheckBox, R.id.kfxm03CheckBox,
            R.id.kfxm04CheckBox, R.id.kfxm05CheckBox, R.id.kfxm06CheckBox, R.id.kfxm07CheckBox };

	CheckBox kfmb01CheckBox = null;
	CheckBox kfmb02CheckBox = null;
	CheckBox kfmb03CheckBox = null;
	CheckBox kfmb04CheckBox = null;
	CheckBox kfmb05CheckBox = null;
	CheckBox kfmb06CheckBox = null;
	CheckBox kfmb07CheckBox = null;
	EditText kfmbEditText = null;

    @XmlTag(name = "TrainingTarget")
    CheckBoxGroupUtil kfmbCheckBoxGroup;

    int[] kfmbViewIds = new int[] { R.id.kfmb01CheckBox, R.id.kfmb02CheckBox, R.id.kfmb03CheckBox,
            R.id.kfmb04CheckBox, R.id.kfmb05CheckBox, R.id.kfmb06CheckBox, R.id.kfmb07CheckBox };

    @XmlTag(name = "TrainingDuration")
	EditText gnxl01EditText = null;
    @XmlTag(name = "TrainingDuration")
	EditText gnxl02EditText = null;

    @XmlTag(name = "TrainingPlace")
	SpinnerUtil xlddSpinnerUtil = null;

    private Context mContext;
	private Toast mToast;
    private Map<String, IBean> beanMap = null;
	public SfglCjsfPage02(Context context) {
		super(context);
		// init(context);
		// TODO Auto-generated constructor stub
	}

    public SfglCjsfPage02(Context context, Map<String, IBean> beanMap) {
        super(context);
        this.beanMap = beanMap;
        // init(context);
    }

	/**
	 * @param context
	 * @param attrs
	 */
	public SfglCjsfPage02(Context context, AttributeSet attrs) {
		super(context, attrs);
		// init(context);
		// TODO Auto-generated constructor stub
	}
	protected void init(Context context){
        mContext = context;
		mToast =TispToastFactory.getToast(context, "");
		LayoutInflater.from(context).inflate(R.layout.fragment_sfgl_cjsf_02_layout, this);
        loadPage(context, this);
	}
	public void loadPage(Context context, ViewGroup viewGroup) { 
        tjsjButton = (Button) findViewById(R.id.tjsjButton);
        zzEditText = (EditText) findViewById(R.id.zzEditText);
															                  
        tzEditText = (EditText) findViewById(R.id.tzEditText);
        xy01EditText = (EditText) findViewById(R.id.xy01EditText);
        xy02EditText = (EditText) findViewById(R.id.xy02EditText);
        xlEditText = (EditText) findViewById(R.id.xlEditText);
        qtEditText = (EditText) findViewById(R.id.qtEditText);

        kfxm01CheckBox = (CheckBox) findViewById(R.id.kfxm01CheckBox);
        kfxm02CheckBox = (CheckBox) findViewById(R.id.kfxm02CheckBox);
        kfxm03CheckBox = (CheckBox) findViewById(R.id.kfxm03CheckBox);
        kfxm04CheckBox = (CheckBox) findViewById(R.id.kfxm04CheckBox);
        kfxm05CheckBox = (CheckBox) findViewById(R.id.kfxm05CheckBox);
        kfxm06CheckBox = (CheckBox) findViewById(R.id.kfxm06CheckBox);
        kfxm07CheckBox = (CheckBox) findViewById(R.id.kfxm07CheckBox);
        kfxmEditText = (EditText) findViewById(R.id.kfxmEditText);

        kfmb01CheckBox = (CheckBox) findViewById(R.id.kfmb01CheckBox);
        kfmb02CheckBox = (CheckBox) findViewById(R.id.kfmb02CheckBox);
        kfmb03CheckBox = (CheckBox) findViewById(R.id.kfmb03CheckBox);
        kfmb04CheckBox = (CheckBox) findViewById(R.id.kfmb04CheckBox);
        kfmb05CheckBox = (CheckBox) findViewById(R.id.kfmb05CheckBox);
        kfmb06CheckBox = (CheckBox) findViewById(R.id.kfmb06CheckBox);
        kfmb07CheckBox = (CheckBox) findViewById(R.id.kfmb07CheckBox);
        kfmbEditText = (EditText) findViewById(R.id.kfmbEditText);

        kfxmCheckBoxGroup = new CheckBoxGroupUtil(mContext, kfxmViewIds, this, "kfxm", kfxmEditText);
        kfmbCheckBoxGroup = new CheckBoxGroupUtil(mContext, kfmbViewIds, this, "kfmb", kfmbEditText);

        gnxl01EditText = (EditText) findViewById(R.id.gnxl01EditText);
        gnxl02EditText = (EditText) findViewById(R.id.gnxl02EditText);

        xlddSpinnerUtil = (SpinnerUtil) findViewById(R.id.xlddSpinnerUtil);
	}

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
