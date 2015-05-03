package com.cking.phss.page;

import java.util.Date;
import java.util.Map;

import net.xinhuaxing.util.ResourcesFactory;
import net.xinhuaxing.util.StringUtil;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cking.phss.R;
import com.cking.phss.bean.BeanCD;
import com.cking.phss.bean.BeanID;
import com.cking.phss.bean.IBean;
import com.cking.phss.bean.Sfgljl_tnb;
import com.cking.phss.util.CalendarUtil;
import com.cking.phss.util.CheckBoxGroupUtil;
import com.cking.application.MyApplication;
import com.cking.phss.util.TispToastFactory;
import com.cking.phss.widget.CalendarText;

public class SfglTnbPage06 extends MyPage {
	private static final String TAG = "SfglTnbPage06";
    CalendarText rqCalendarText = null;
	TextView bfzTextView = null;
	CheckBox bfz01CheckBox = null;
	CheckBox bfz02CheckBox = null;
	CheckBox bfz03CheckBox = null;
	CheckBox bfz04CheckBox = null;
	CheckBox bfz05CheckBox = null;
	CheckBox bfz06CheckBox = null;
	CheckBox bfz07CheckBox = null;
	CheckBox bfz08CheckBox = null;
	CheckBox bfz09CheckBox = null;
	CheckBox bfz10CheckBox = null;
	CheckBox bfz11CheckBox = null;
	CheckBox bfz12CheckBox = null;
	CheckBox bfz13CheckBox = null;
	CheckBox bfz14CheckBox = null;
	CheckBox bfz15CheckBox = null;
	CheckBox bfz16CheckBox = null;
	CheckBox bfz17CheckBox = null;
	CheckBox bfz18CheckBox = null;
	CheckBox bfz19CheckBox = null;
	CheckBox bfz20CheckBox = null;
	CheckBox bfz21CheckBox = null;
	CheckBox bfz22CheckBox = null;
	CheckBox bfz23CheckBox = null;

	TextView hbzTextView = null;
	CheckBox hbz01CheckBox = null;
	CheckBox hbz02CheckBox = null;
	CheckBox hbz03CheckBox = null;
	CheckBox hbz04CheckBox = null;
	CheckBox hbz05CheckBox = null;
	CheckBox hbz06CheckBox = null;
	CheckBox hbz07CheckBox = null;
	CheckBox hbz08CheckBox = null;
	EditText qtjbEditText = null;

	CalendarText xcsfrqCalendarText = null;
	EditText  sfysqmEditText = null;
	Context mContext;

    private CheckBoxGroupUtil checkBoxGroup1;
    private int[] viewsId1 = new int[] { R.id.bfz01CheckBox,
            R.id.bfz02CheckBox,
            R.id.bfz03CheckBox,
            R.id.bfz04CheckBox,
            R.id.bfz05CheckBox,
            R.id.bfz06CheckBox,
            R.id.bfz07CheckBox,
            R.id.bfz08CheckBox,
            R.id.bfz09CheckBox,
            R.id.bfz10CheckBox,
            R.id.bfz11CheckBox,
            R.id.bfz12CheckBox,
            R.id.bfz13CheckBox,
            R.id.bfz14CheckBox,
            R.id.bfz15CheckBox,
            R.id.bfz16CheckBox,
            R.id.bfz17CheckBox,
            R.id.bfz18CheckBox,
            R.id.bfz19CheckBox,
            R.id.bfz20CheckBox,
            R.id.bfz21CheckBox,
            R.id.bfz22CheckBox,
            R.id.bfz23CheckBox };
    private CheckBoxGroupUtil checkBoxGroup2;
    private int[] viewsId2 = new int[] { R.id.hbz01CheckBox, R.id.hbz02CheckBox,
            R.id.hbz03CheckBox, R.id.hbz04CheckBox, R.id.hbz05CheckBox, R.id.hbz06CheckBox,
            R.id.hbz07CheckBox, R.id.hbz08CheckBox };

	private Map<String, IBean> beanMap = null;
	private Toast mToast;

	public SfglTnbPage06(Context context, Map<String, IBean> beanMap) {
		super(context);
		mContext = context;
		this.beanMap = beanMap;
		// init(context);
	}

	/**
	 * @param context
	 * @param attrs
	 */
	public SfglTnbPage06(Context context, AttributeSet attrs) {
		super(context, attrs);
		// init(context);
	}

	protected void init(Context context) {
		mToast = TispToastFactory.getToast(context, "");
		LayoutInflater.from(context).inflate(R.layout.fragment_sfgl_tnb_06_layout, this);
        
        loadPage(context, this);
    }

    public void loadPage(Context context, ViewGroup viewGroup) {
		rqCalendarText = (CalendarText) findViewById(R.id.rqCalendarText);
		bfzTextView = (TextView) findViewById(R.id.bfzTextView);
		bfz01CheckBox = (CheckBox) findViewById(R.id.bfz01CheckBox);
		bfz02CheckBox = (CheckBox) findViewById(R.id.bfz02CheckBox);
		bfz03CheckBox = (CheckBox) findViewById(R.id.bfz03CheckBox);
		bfz04CheckBox = (CheckBox) findViewById(R.id.bfz04CheckBox);
		bfz05CheckBox = (CheckBox) findViewById(R.id.bfz05CheckBox);
		bfz06CheckBox = (CheckBox) findViewById(R.id.bfz06CheckBox);
		bfz07CheckBox = (CheckBox) findViewById(R.id.bfz07CheckBox);
		bfz08CheckBox = (CheckBox) findViewById(R.id.bfz08CheckBox);
		bfz09CheckBox = (CheckBox) findViewById(R.id.bfz09CheckBox);
		bfz10CheckBox = (CheckBox) findViewById(R.id.bfz10CheckBox);
		bfz11CheckBox = (CheckBox) findViewById(R.id.bfz11CheckBox);
		bfz12CheckBox = (CheckBox) findViewById(R.id.bfz12CheckBox);
		bfz13CheckBox = (CheckBox) findViewById(R.id.bfz13CheckBox);
		bfz14CheckBox = (CheckBox) findViewById(R.id.bfz14CheckBox);
		bfz15CheckBox = (CheckBox) findViewById(R.id.bfz15CheckBox);
		bfz16CheckBox = (CheckBox) findViewById(R.id.bfz16CheckBox);
		bfz17CheckBox = (CheckBox) findViewById(R.id.bfz17CheckBox);
		bfz18CheckBox = (CheckBox) findViewById(R.id.bfz18CheckBox);
		bfz19CheckBox = (CheckBox) findViewById(R.id.bfz19CheckBox);
		bfz20CheckBox = (CheckBox) findViewById(R.id.bfz20CheckBox);
		bfz21CheckBox = (CheckBox) findViewById(R.id.bfz21CheckBox);
		bfz22CheckBox = (CheckBox) findViewById(R.id.bfz22CheckBox);
		bfz23CheckBox = (CheckBox) findViewById(R.id.bfz23CheckBox);

		hbzTextView = (TextView) findViewById(R.id.hbzTextView);
		hbz01CheckBox = (CheckBox) findViewById(R.id.hbz01CheckBox);
		hbz02CheckBox = (CheckBox) findViewById(R.id.hbz02CheckBox);
		hbz03CheckBox = (CheckBox) findViewById(R.id.hbz03CheckBox);
		hbz04CheckBox = (CheckBox) findViewById(R.id.hbz04CheckBox);
		hbz05CheckBox = (CheckBox) findViewById(R.id.hbz05CheckBox);
		hbz06CheckBox = (CheckBox) findViewById(R.id.hbz06CheckBox);
		hbz07CheckBox = (CheckBox) findViewById(R.id.hbz07CheckBox);
		hbz08CheckBox = (CheckBox) findViewById(R.id.hbz08CheckBox);
		qtjbEditText = (EditText) findViewById(R.id.qtjbEditText);

		xcsfrqCalendarText = (CalendarText) findViewById(R.id.xcsfrqCalendarText);
		sfysqmEditText = (EditText) findViewById(R.id.sfysqmEditText);
        xcsfrqCalendarText.setDate(CalendarUtil.getNextMonth(new Date()));
        qtjbEditText.setText("");
        // qtjbEditText.setEnabled(false);
		
        String[] ids = ResourcesFactory.listId(mContext, "bqgss");
        checkBoxGroup1 = new CheckBoxGroupUtil(viewsId1, viewGroup, ids);
        ids = ResourcesFactory.listId(mContext, "gxyhbz");
        checkBoxGroup2 = new CheckBoxGroupUtil(viewsId2, viewGroup, null);
        
        // checkBoxGroup2.getCheckBox(checkBoxGroup2.size() - 1)//
        // 最后一个checkBox的监听
        // .setOnCheckedChangeListener(new
        // CompoundButton.OnCheckedChangeListener() {
        // @Override
        // public void onCheckedChanged(CompoundButton buttonView, boolean
        // isChecked) {
        // if (isChecked) {
        // qtjbEditText.setEnabled(true);
        // } else {
        // qtjbEditText.setText("");
        // qtjbEditText.setEnabled(false);
        // }
        // }
        // });
	}

    /* (non-Javadoc)
     * @see com.cking.phss.page.IPage#setValue()
     */
    @Override
    public void setValue() { if (!hasInit) {return;}
        Sfgljl_tnb mSfgljl = (Sfgljl_tnb) beanMap.get(Sfgljl_tnb.class.getName());
        if (mSfgljl == null)
            return;

        // 转诊日期
        if (mSfgljl.getReferralVisitDate() != null) {
            rqCalendarText.setText(mSfgljl.getReferralVisitDate());
        } 

        // 并发症
        BeanCD complication = mSfgljl.getComplication();
        if (complication != null) {
            checkBoxGroup1.setCheckedByValues(complication.getcD());
        }
        
        // 合并症 
        BeanCD comorbidity = mSfgljl.getComorbidity();
        if (comorbidity != null) {
            checkBoxGroup2.setCheckedByValues(comorbidity.getcD());
        }
        // if (checkBoxGroup2.getCheckBox(viewsId2.length - 1).isChecked()
        // && (!StringUtil.isEmptyString(mSfgljl.getOtherDiseases()))) {
            qtjbEditText.setText(mSfgljl.getOtherDiseases());
        // }

        // 下次随访日期
        xcsfrqCalendarText.setText(mSfgljl.getxCSF());
        
        // 随访医生
        BeanID doctor = mSfgljl.getVisitDoctor();
        if(doctor!=null&&!doctor.getTagValue().equals("")){
            sfysqmEditText.setText(doctor.getTagValue());
        }else{
            try {
                String doctorName=MyApplication.getInstance().getSession().getLoginResult().response.doctorName;
                sfysqmEditText.setText(doctorName);
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
    }

    /* (non-Javadoc)
     * @see com.cking.phss.page.IPage#getValue()
     */
    @Override
    public boolean getValue() { if (!hasInit) {return false;}
        Sfgljl_tnb mSfgljl = (Sfgljl_tnb) beanMap.get(Sfgljl_tnb.class.getName());
        if (mSfgljl == null)
            return false;

        // 转诊日期
        mSfgljl.setReferralVisitDate(rqCalendarText.getText().toString().trim());

        // 并发症
        BeanCD complication = new BeanCD();
        String values = checkBoxGroup1.getCheckValues("|");
        if (!StringUtil.isEmptyString(values)) {
            complication.setcD(values);
            mSfgljl.setComplication(complication);
        }
        
        // 合并症 
        BeanCD comorbidity = new BeanCD();
        values = checkBoxGroup2.getCheckValues("|");
        if (!StringUtil.isEmptyString(values)) {
            comorbidity.setcD(values);
            mSfgljl.setComorbidity(comorbidity);
        }
        
        //if (StringUtil.isEmptyString(qtjbEditText.getText().toString())) 
        {
            mSfgljl.setOtherDiseases(qtjbEditText.getText().toString());
        }

        // 下次随访的日期，不能为空
        String xcsfrq = xcsfrqCalendarText.getText().toString().trim();
        // Pattern pattern = Pattern.compile("\\d{4}\\-\\-\\d{2}\\-\\-\\d{2}");
        // Matcher m = pattern.matcher(xcsfrq);
        if (xcsfrq == null) {
            mToast.setText("下次随访日期不能为空！");
            mToast.show();
            Log.e(TAG, "下次随访日期不能为空！");
            return false;
        } else {
            mSfgljl.setxCSF(xcsfrq);
        }

        /**
         * 随访医生的签名要不要，
         */
        BeanID doctor = new BeanID();
        doctor.setTagValue(sfysqmEditText.getText().toString());
        mSfgljl.setVisitDoctor(doctor);

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
