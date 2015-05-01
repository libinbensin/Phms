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
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.cking.phss.R;
import com.cking.phss.bean.BeanCD;
import com.cking.phss.bean.BeanID;
import com.cking.phss.bean.IBean;
import com.cking.phss.bean.Sfgljl_gxy;
import com.cking.phss.util.CalendarUtil;
import com.cking.phss.util.CheckBoxGroupUtil;
import com.cking.phss.util.MyApplication;
import com.cking.phss.util.TispToastFactory;
import com.cking.phss.widget.CalendarText;

public class SfglGxyPage05 extends MyPage  {
	@SuppressWarnings("unused")
	private static final String TAG = "SfglGxyPage05";

	private Context mContext = null;
	
	RadioGroup zzRadioGroup;
	RadioButton zz01RadioButton;
	RadioButton zz02RadioButton;
	EditText yyEditText;// 转诊原因
	EditText jgjksEditText;// 机构和可别
	CalendarText rqCalendarText;
	EditText qtjbEditText;
	CalendarText xcsfrqCalendarText;// 下次随访日期
	EditText sfysqmEditText;// 随访医生签名

    private CheckBoxGroupUtil checkBoxGroup1;
    private int[] viewsId1 = new int[] { R.id.bqgsh01CheckBox, R.id.bqgsh02CheckBox,
            R.id.bqgsh03CheckBox, R.id.bqgsh04CheckBox, R.id.bqgsh05CheckBox, R.id.bqgsh06CheckBox };
    private CheckBoxGroupUtil checkBoxGroup2;
    private int[] viewsId2 = new int[] { R.id.hbz01CheckBox, R.id.hbz02CheckBox,
            R.id.hbz03CheckBox, R.id.hbz04CheckBox, R.id.hbz05CheckBox, R.id.hbz06CheckBox,
            R.id.hbz07CheckBox, R.id.hbz08CheckBox, R.id.hbz09CheckBox };
    
	private Toast mToast;
	private Map<String, IBean> beanMap = null;

	public SfglGxyPage05(Context context, Map<String, IBean> beanMap) {
		super(context);
		this.beanMap = beanMap;
		// init(context);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param context
	 * @param attrs
	 */
	public SfglGxyPage05(Context context, AttributeSet attrs) {
		super(context, attrs);
		// init(context);
		// TODO Auto-generated constructor stub
	}

	protected void init(Context context) {
		mContext = context;
		mToast = TispToastFactory.getToast(context, "");
		LayoutInflater.from(context).inflate(R.layout.fragment_sfgl_gxy_05_layout, this);
		
		loadPage(context, this);
	}

    public void loadPage(Context context, ViewGroup viewGroup) {
        zzRadioGroup = (RadioGroup) findViewById(R.id.zzRadioGroup);
        zz01RadioButton = (RadioButton) findViewById(R.id.zz01RadioButton);
        zz02RadioButton = (RadioButton) findViewById(R.id.zz02RadioButton);
		yyEditText = (EditText) findViewById(R.id.yyEditText);
		jgjksEditText = (EditText) findViewById(R.id.jgjksEditText);
	    rqCalendarText = (CalendarText) findViewById(R.id.rqCalendarText);
	    qtjbEditText = (EditText) findViewById(R.id.qtjbEditText);
		xcsfrqCalendarText = (CalendarText) findViewById(R.id.xcsfrqCalendarText);
		sfysqmEditText = (EditText) findViewById(R.id.sfysqmEditText);
        String[] ids = ResourcesFactory.listId(mContext, "bqgss");
        checkBoxGroup1 = new CheckBoxGroupUtil(viewsId1, viewGroup, ids);
        ids = ResourcesFactory.listId(mContext, "gxyhbz");
        checkBoxGroup2 = new CheckBoxGroupUtil(viewsId2, viewGroup, null);

        // 默认的情况下的状态
        zzRadioGroup.check(R.id.zz01RadioButton);
        yyEditText.setEnabled(false);
        jgjksEditText.setEnabled(false);
        yyEditText.setText("");
        jgjksEditText.setText("");
        xcsfrqCalendarText.setDate(CalendarUtil.getNextMonth(new Date()));
        // qtjbEditText.setEnabled(false);
        qtjbEditText.setText("");
        
        zzRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.zz01RadioButton) {
                    yyEditText.setEnabled(false);
                    jgjksEditText.setEnabled(false);
                    yyEditText.setText("");
                    jgjksEditText.setText("");
                } else {
                    yyEditText.setEnabled(true);
                    jgjksEditText.setEnabled(true);
                }
            }
        });
        
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

	@Override
	public void setValue() { if (!hasInit) {return;}
		Sfgljl_gxy mSfgljl = (Sfgljl_gxy) beanMap.get(Sfgljl_gxy.class
				.getName());
		if (mSfgljl == null)
			return;

		//原因
		String yy = mSfgljl.getzZYY();
		
		//机构及科室
		String jgjks = mSfgljl.getzZKB();
		
		if( StringUtil.isEmptyString(yy) && 
			StringUtil.isEmptyString(jgjks) )
		{
			zzRadioGroup.check(R.id.zz01RadioButton);
		}
		else
		{
			zzRadioGroup.check(R.id.zz02RadioButton);
			yyEditText.setText(yy);
			jgjksEditText.setText(jgjks);
		}
		
		// 转诊日期		
		String rq = mSfgljl.getReferralVisitDate(); 
		if (!StringUtil.isEmptyString(rq)) {
            rqCalendarText.setText(rq);
        } 
        
        // 靶器官损害 
        BeanCD criticalOrgan = mSfgljl.getCriticalOrgan();
        if (criticalOrgan != null) {
            checkBoxGroup1.setCheckedByValues(criticalOrgan.getcD());
        }
        
        // 合并症 
        BeanCD comorbidity = mSfgljl.getComorbidity();
        if (comorbidity != null) {
            checkBoxGroup2.setCheckedByValues(comorbidity.getcD());
        }
        
        String qtjb = mSfgljl.getOtherDiseases();
        // if (checkBoxGroup2.getCheckBox(viewsId2.length - 1).isChecked()) {
            qtjbEditText.setText(qtjb);
        // }

		// 下次随访日期
        String xcsf = mSfgljl.getxCSF(); 
        xcsfrqCalendarText.setText(xcsf);
        
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

	@Override
	public boolean getValue() { if (!hasInit) {return false;}
		// TODO Auto-generated method stub

		Sfgljl_gxy mSfgljl = (Sfgljl_gxy) beanMap.get(Sfgljl_gxy.class
				.getName());
		if (mSfgljl == null) {
			Log.e(TAG, "mSfgljl is null");
			return false;
		}
		
		String zzyy = yyEditText.getText().toString().trim();// 转诊原因
		String jgjks = jgjksEditText.getText().toString().trim();// 机构和科别	
		mSfgljl.setzZYY(zzyy);
		mSfgljl.setzZKB(jgjks);
			
        // 转诊日期
		mSfgljl.setReferralVisitDate(rqCalendarText.getText().toString().trim());
        
        // 靶器官损害 
        BeanCD criticalOrgan = new com.cking.phss.bean.BeanCD();
        String values = checkBoxGroup1.getCheckValues("|");
        Log.e(TAG, "靶器官损害："+values);
        if (!StringUtil.isEmptyString(values)) {
            criticalOrgan.setcD(values);
            mSfgljl.setCriticalOrgan(criticalOrgan);
        }
        
        // 合并症 
        BeanCD comorbidity = new com.cking.phss.bean.BeanCD(); 
        		//mSfgljl.getComorbidity();
        values = checkBoxGroup2.getCheckValues("|");
        Log.e(TAG, "合并症："+values);
        if (!StringUtil.isEmptyString(values)) {
            comorbidity.setcD(values);
            mSfgljl.setComorbidity(comorbidity);
        }
        //if (!StringUtil.isEmptyString(qtjbEditText.getText().toString())) 
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
