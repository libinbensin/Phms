package com.cking.phss.page;

import java.util.Map;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.cking.phss.R;
import com.cking.phss.bean.IBean;
import com.cking.phss.bean.Jmjbxx;
import com.cking.phss.bean.Sfgljl_cjsf;
import com.cking.phss.global.Global;
import com.cking.phss.util.CheckBoxGroupUtil;
import com.cking.phss.util.TispToastFactory;
import com.cking.phss.widget.CalendarText;
import com.cking.phss.widget.SpinnerUtil;
import com.cking.phss.xml.util.XmlSerializerUtil;
import com.cking.phss.xml.util.XmlTag;

public class SfglCjsfPage01 extends MyPage  {
    private static final String TAG = "SfglCjsfPage01";
    @XmlTag(name = "FlupDate")
    CalendarText sfrqCalendarText = null;
    @XmlTag(name = "FlupWay")
	SpinnerUtil sffsSpinnerUtil = null;
    @XmlTag(name = "VulnerableGroup")
	SpinnerUtil knqtSpinnerUtil = null;
    @XmlTag(name = "RecureDemands")
	SpinnerUtil kfxqSpinnerUtil = null;
	EditText zrysEditText = null;
    @XmlTag(name = "DeformityCard")
	EditText cjzbhEditText = null;

    @XmlTag(name = "MainDisability")
	SpinnerUtil zycjSpinnerUtil = null;

    @XmlTag(name = "MultiDisability")
    CheckBoxGroupUtil dccjCheckBoxGroup;

    int[] viewIds = new int[] { R.id.dccj02CheckBox, R.id.dccj03CheckBox,
            R.id.dccj04CheckBox, R.id.dccj05CheckBox, R.id.dccj06CheckBox, R.id.dccj07CheckBox };

	CheckBox dccj01CheckBox = null;
	CheckBox dccj02CheckBox = null;
	CheckBox dccj03CheckBox = null;
	CheckBox dccj04CheckBox = null;
	CheckBox dccj05CheckBox = null;
	CheckBox dccj06CheckBox = null;
	CheckBox dccj07CheckBox = null;

    @XmlTag(name = "DisabilityDate")
	CalendarText zcrqCalendarText = null;
    @XmlTag(name = "DisabilityLevel")
	SpinnerUtil cjcdSpinnerUtil = null;
    @XmlTag(name = "DisabilityReason")
	SpinnerUtil cjyySpinnerUtil = null;

    private Context mContext;
	private Toast mToast;
    private Map<String, IBean> beanMap = null;
	public SfglCjsfPage01(Context context) {
		super(context);
		// init(context);
		// TODO Auto-generated constructor stub
	}

    public SfglCjsfPage01(Context context, Map<String, IBean> beanMap) {
        super(context);
        this.beanMap = beanMap;
        // init(context);
    }

	/**
	 * @param context
	 * @param attrs
	 */
	public SfglCjsfPage01(Context context, AttributeSet attrs) {
		super(context, attrs);
		// init(context);
		// TODO Auto-generated constructor stub
	}
	protected void init(Context context){
        mContext = context;
		mToast =TispToastFactory.getToast(context, "");
		LayoutInflater.from(context).inflate(R.layout.fragment_sfgl_cjsf_01_layout, this);
        loadPage(context, this);
	}
	public void loadPage(Context context, ViewGroup viewGroup) {   	
        sfrqCalendarText = (CalendarText) findViewById(R.id.sfrqCalendarText);
        sffsSpinnerUtil = (SpinnerUtil) findViewById(R.id.sffsSpinnerUtil);
        knqtSpinnerUtil = (SpinnerUtil) findViewById(R.id.knqtSpinnerUtil);
        kfxqSpinnerUtil = (SpinnerUtil) findViewById(R.id.kfxqSpinnerUtil);
        zrysEditText = (EditText) findViewById(R.id.zrysEditText);
        cjzbhEditText = (EditText) findViewById(R.id.cjzbhEditText);

        zycjSpinnerUtil = (SpinnerUtil) findViewById(R.id.zycjSpinnerUtil);

        dccj01CheckBox = (CheckBox) findViewById(R.id.dccj01CheckBox);
        dccj02CheckBox = (CheckBox) findViewById(R.id.dccj02CheckBox);
        dccj03CheckBox = (CheckBox) findViewById(R.id.dccj03CheckBox);
        dccj04CheckBox = (CheckBox) findViewById(R.id.dccj04CheckBox);
        dccj05CheckBox = (CheckBox) findViewById(R.id.dccj05CheckBox);
        dccj06CheckBox = (CheckBox) findViewById(R.id.dccj06CheckBox);
        dccj07CheckBox = (CheckBox) findViewById(R.id.dccj07CheckBox);

        zcrqCalendarText = (CalendarText) findViewById(R.id.zcrqCalendarText);
        cjcdSpinnerUtil = (SpinnerUtil) findViewById(R.id.cjcdSpinnerUtil);
        cjyySpinnerUtil = (SpinnerUtil) findViewById(R.id.cjyySpinnerUtil);

        dccjCheckBoxGroup = new CheckBoxGroupUtil(mContext, viewIds, this, "dccj");
        dccjCheckBoxGroup.setCheckedAll(false);
        dccjCheckBoxGroup.setEnabledAll(false);
        dccj01CheckBox.setChecked(true);
        // 对于否checkBox的监听
        dccj01CheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    dccjCheckBoxGroup.setCheckedAll(false);
                    dccjCheckBoxGroup.setEnabledAll(false);
                } else {
                    dccjCheckBoxGroup.setEnabledAll(true);
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
        zrysEditText.setText(Global.doctorName);
        dccj01CheckBox.setChecked(sfgljl.multiDisabilityFlag == 1);
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

        // 其他自定义
        sfgljl.dutyDoctorID = Global.doctorID;
        sfgljl.dutyDoctorName = Global.doctorName;
        sfgljl.multiDisabilityFlag = dccj01CheckBox.isChecked() ? 1 : 2;

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
