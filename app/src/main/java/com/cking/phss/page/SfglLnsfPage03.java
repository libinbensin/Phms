package com.cking.phss.page;

import java.util.Map;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.cking.phss.R;
import com.cking.phss.bean.IBean;
import com.cking.phss.bean.Jmjbxx;
import com.cking.phss.bean.Sfgljl_lnsf;
import com.cking.phss.global.Global;
import com.cking.phss.util.TispToastFactory;
import com.cking.phss.widget.CalendarText;
import com.cking.phss.widget.SpinnerUtil;
import com.cking.phss.xml.util.XmlSerializerUtil;
import com.cking.phss.xml.util.XmlTag;

public class SfglLnsfPage03 extends MyPage  {

    private static final String TAG = "SfglLnsfPage03";
    EditText ydxmEditText = null;
    @XmlTag(name = "SaltIntake")
	EditText syqk01EditText = null;
    @XmlTag(name = "SaltTarget")
	EditText syqk02EditText = null;

	SpinnerUtil syqk01SpinnerUtil = null;
	SpinnerUtil syqk02SpinnerUtil = null;

    @XmlTag(name = "Psyche")
	SpinnerUtil xltzSpinnerUtil = null;
    @XmlTag(name = "Compliance")
	SpinnerUtil zyxwSpinnerUtil = null;

	EditText mqqkEditText = null;
	EditText sffwnrEditText = null;

    @XmlTag(name = "Evaluation")
	SpinnerUtil sfpgSpinnerUtil = null;
    @XmlTag(name = "NextFlupDate")
	CalendarText xcsfrqCalendarText = null;
	EditText sfysqmEditText = null;

    private Context mContext;
	private Toast mToast;
    private Map<String, IBean> beanMap = null;
	public SfglLnsfPage03(Context context) {
		super(context);
		// init(context);
		// TODO Auto-generated constructor stub
	}

    public SfglLnsfPage03(Context context, Map<String, IBean> beanMap) {
        super(context);
        this.beanMap = beanMap;
        // init(context);
    }
	/**
	 * @param context
	 * @param attrs
	 */
	public SfglLnsfPage03(Context context, AttributeSet attrs) {
		super(context, attrs);
		// init(context);
		// TODO Auto-generated constructor stub
	}
	protected void init(Context context){
        mContext = context;
		mToast =TispToastFactory.getToast(context, "");
		LayoutInflater.from(context).inflate(R.layout.fragment_sfgl_lnsf_03_layout,this);
        loadPage(context, this);
	}

	public void loadPage(Context context, ViewGroup viewGroup) {   
        ydxmEditText = (EditText) findViewById(R.id.ydxmEditText);
        syqk01EditText = (EditText) findViewById(R.id.syqk01EditText);
        syqk02EditText = (EditText) findViewById(R.id.syqk02EditText);

        syqk01SpinnerUtil = (SpinnerUtil) findViewById(R.id.syqk01SpinnerUtil);
        syqk02SpinnerUtil = (SpinnerUtil) findViewById(R.id.syqk02SpinnerUtil);

        xltzSpinnerUtil = (SpinnerUtil) findViewById(R.id.xltzSpinnerUtil);
        zyxwSpinnerUtil = (SpinnerUtil) findViewById(R.id.zyxwSpinnerUtil);

        mqqkEditText = (EditText) findViewById(R.id.mqqkEditText);
        sffwnrEditText = (EditText) findViewById(R.id.sffwnrEditText);

        sfpgSpinnerUtil = (SpinnerUtil) findViewById(R.id.sfpgSpinnerUtil);
        xcsfrqCalendarText = (CalendarText) findViewById(R.id.xcsfrqCalendarText);
        sfysqmEditText = (EditText) findViewById(R.id.sfysqmEditText);
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
            XmlSerializerUtil.getInstance().setValue(SfglLnsfPage03.this, sfgljl);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        sfysqmEditText.setText(Global.doctorName);
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
            XmlSerializerUtil.getInstance().getValue(SfglLnsfPage03.this, sfgljl);
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
