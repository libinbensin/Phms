package com.cking.phss.page;

import java.util.Map;

import net.xinhuaxing.util.ResourcesFactory;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.cking.phss.R;
import com.cking.phss.bean.IBean;
import com.cking.phss.bean.Jmjbxx;
import com.cking.phss.bean.Sfgljl_lnsf;
import com.cking.phss.global.Global;
import com.cking.phss.util.CheckBoxGroupUtil;
import com.cking.phss.util.TispToastFactory;
import com.cking.phss.widget.CalendarText;
import com.cking.phss.widget.SpinnerUtil;
import com.cking.phss.xml.util.XmlSerializerUtil;
import com.cking.phss.xml.util.XmlTag;

public class SfglLnsfPage01 extends MyPage  {

    private static final String TAG = "SfglLnsfPage01";
    @XmlTag(name = "DutyDoctorName")
    EditText zrysEditText = null;
    @XmlTag(name = "FlupDate")
	CalendarText sfrqCalendarText = null;
    @XmlTag(name = "Cycle")
	SpinnerUtil sfzqjySpinnerUtil = null;
    @XmlTag(name = "FlupWay")
	SpinnerUtil sffsSpinnerUtil = null;
    @XmlTag(name = "Nature")
	SpinnerUtil sfxzSpinnerUtil = null;
    @XmlTag(name = "Mentation")
	SpinnerUtil xlztSpinnerUtil = null;
    @XmlTag(name = "NewSymptom")
    CheckBoxGroupUtil zzCheckBoxGroup;

    int[] viewIds = new int[] { R.id.zz01CheckBox, R.id.zz02CheckBox, R.id.zz03CheckBox,
            R.id.zz04CheckBox, R.id.zz05CheckBox, R.id.zz06CheckBox, R.id.zz07CheckBox,
            R.id.zz08CheckBox, R.id.zz09CheckBox, R.id.zz10CheckBox };

	CheckBox zz01CheckBox = null;
	CheckBox zz02CheckBox = null;
	CheckBox zz03CheckBox = null;
	CheckBox zz04CheckBox = null;
	CheckBox zz05CheckBox = null;
	CheckBox zz06CheckBox = null;
	CheckBox zz07CheckBox = null;
	CheckBox zz08CheckBox = null;
	CheckBox zz09CheckBox = null;
	CheckBox zz10CheckBox = null;

	View lineView = null;
    @XmlTag(name = "SystolicPressure")
	EditText xy01EditText = null;
    @XmlTag(name = "DiastolicPressure")
	EditText xy02EditText = null;
    @XmlTag(name = "HeartRate")
	EditText xlEditText = null;
    @XmlTag(name = "Waist")
	EditText ywEditText = null;

    private Context mContext;
	private Toast mToast;
    private Map<String, IBean> beanMap = null;
	public SfglLnsfPage01(Context context) {
		super(context);
		// init(context);
		// TODO Auto-generated constructor stub
	}

    public SfglLnsfPage01(Context context, Map<String, IBean> beanMap) {
        super(context);
        this.beanMap = beanMap;
        // init(context);
    }

	/**
	 * @param context
	 * @param attrs
	 */
	public SfglLnsfPage01(Context context, AttributeSet attrs) {
		super(context, attrs);
		// init(context);
		// TODO Auto-generated constructor stub
	}
	protected void init(Context context){
        mContext = context;
		mToast =TispToastFactory.getToast(context, "");
		LayoutInflater.from(context).inflate(R.layout.fragment_sfgl_lnsf_01_layout, this);
        loadPage(context, this);
	}
	public void loadPage(Context context, ViewGroup viewGroup) {   	
        zrysEditText = (EditText) findViewById(R.id.zrysEditText);
        sfrqCalendarText = (CalendarText) findViewById(R.id.sfrqCalendarText);
        sfzqjySpinnerUtil = (SpinnerUtil) findViewById(R.id.sfzqjySpinnerUtil);
        sffsSpinnerUtil = (SpinnerUtil) findViewById(R.id.sffsSpinnerUtil);
        sfxzSpinnerUtil = (SpinnerUtil) findViewById(R.id.sfxzSpinnerUtil);
        xlztSpinnerUtil = (SpinnerUtil) findViewById(R.id.xlztSpinnerUtil);

        String[] ids = ResourcesFactory.listId(mContext, "lnsfzz");
        zzCheckBoxGroup = new CheckBoxGroupUtil(viewIds, this, ids);

        zz01CheckBox = (CheckBox) findViewById(R.id.zz01CheckBox);
        zz02CheckBox = (CheckBox) findViewById(R.id.zz02CheckBox);
        zz03CheckBox = (CheckBox) findViewById(R.id.zz03CheckBox);
        zz04CheckBox = (CheckBox) findViewById(R.id.zz04CheckBox);
        zz05CheckBox = (CheckBox) findViewById(R.id.zz05CheckBox);
        zz06CheckBox = (CheckBox) findViewById(R.id.zz06CheckBox);
        zz07CheckBox = (CheckBox) findViewById(R.id.zz07CheckBox);
        zz08CheckBox = (CheckBox) findViewById(R.id.zz08CheckBox);
        zz09CheckBox = (CheckBox) findViewById(R.id.zz09CheckBox);
        zz10CheckBox = (CheckBox) findViewById(R.id.zz10CheckBox);

        xy01EditText = (EditText) findViewById(R.id.xy01EditText);
        xy02EditText = (EditText) findViewById(R.id.xy02EditText);

        xlEditText = (EditText) findViewById(R.id.xlEditText);
        ywEditText = (EditText) findViewById(R.id.ywEditText);

        zzCheckBoxGroup.setCheckedAllExcept(false, zz01CheckBox);
        zzCheckBoxGroup.setEnabledAllExcept(false, zz01CheckBox);
        // 对于无症状checkBox的监听
        zz01CheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    zzCheckBoxGroup.setCheckedAllExcept(false, zz01CheckBox);
                    zzCheckBoxGroup.setEnabledAllExcept(false, zz01CheckBox);
                } else {
                    zzCheckBoxGroup.setEnabledAllExcept(true, zz01CheckBox);
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
            XmlSerializerUtil.getInstance().setValue(SfglLnsfPage01.this, sfgljl);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        // 其他自定义
        zrysEditText.setText(Global.doctorName);

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
            XmlSerializerUtil.getInstance().getValue(SfglLnsfPage01.this, sfgljl);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        // 其他自定义
        sfgljl.dutyDoctorID = Global.doctorID;
        sfgljl.dutyDoctorName = Global.doctorName;

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
