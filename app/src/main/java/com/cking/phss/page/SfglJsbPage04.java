package com.cking.phss.page;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.xinhuaxing.util.StringUtil;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.cking.phss.R;
import com.cking.phss.bean.IBean;
import com.cking.phss.bean.Sfgljl_jsb;
import com.cking.phss.dto.innner.MedicineUse;
import com.cking.phss.util.TispToastFactory;
import com.cking.phss.widget.CalendarText;
import com.cking.phss.widget.ListItemSfglYyqk;
import com.cking.phss.widget.SfglYyqkLayout;
import com.cking.phss.widget.SpinnerUtil;

public class SfglJsbPage04 extends MyPage  {

	private static final String TAG = "SfglJsbPage04";
	RadioGroup zzRadioGroup;
	RadioButton zz01RadioButton = null;
	RadioButton zz02RadioButton = null;

	EditText yyEditText = null;
	EditText jgjksEditText = null;
	ImageView xzImageView = null;
	TextView bqgshqkTextView = null;

	CheckBox kfcs01CheckBox = null;
	CheckBox kfcs02CheckBox = null;
	CheckBox kfcs03CheckBox = null;
	CheckBox kfcs04CheckBox = null;
	CheckBox kfcs05CheckBox = null;

	SfglYyqkLayout sfglYyqkLayout;

	SpinnerUtil bcsfflSpinnerUtil = null;
	CalendarText xcsfrqCalendarText = null;
	Map<String, IBean> beanMap;
	//List<Integer> widths = new ArrayList<Integer>();

	private Toast mToast;
	public SfglJsbPage04(Context context) {
		super(context);
		// init(context);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param context
	 * @param beanMap
	 */
	public SfglJsbPage04(Context context, Map<String, IBean> beanMap) {
		super(context);
		this.beanMap = beanMap;
		// init(context);
		// TODO Auto-generated constructor stub
	}
	protected void init(Context context){
		mToast =TispToastFactory.getToast(context, "");
		LayoutInflater.from(context).inflate(R.layout.fragment_sfgl_jsb_04_layout, this);

		// 63, 103, 103, 86, 126, 87, 147, 127, 87
		//widths.add(63);
		//widths.add(103);
		//widths.add(103);
		//widths.add(86);
		//widths.add(126);
		//widths.add(87);
		//widths.add(147);
		//widths.add(127);
		//widths.add(87);

		loadPage(context, this);
	}

	public void loadPage(Context context, ViewGroup viewGroup) {
		zzRadioGroup = (RadioGroup) findViewById(R.id.zzRadioGroup);
		zz01RadioButton = (RadioButton) findViewById(R.id.zz01RadioButton);
		zz02RadioButton = (RadioButton) findViewById(R.id.zz02RadioButton);

		yyEditText = (EditText) findViewById(R.id.yyEditText);
		jgjksEditText = (EditText) findViewById(R.id.jgjksEditText);
		xzImageView = (ImageView) findViewById(R.id.xzImageView);
		bqgshqkTextView = (TextView) findViewById(R.id.bqgshqkTextView);

		kfcs01CheckBox = (CheckBox) findViewById(R.id.kfcs01CheckBox);
		kfcs02CheckBox = (CheckBox) findViewById(R.id.kfcs02CheckBox);
		kfcs03CheckBox = (CheckBox) findViewById(R.id.kfcs03CheckBox);
		kfcs04CheckBox = (CheckBox) findViewById(R.id.kfcs04CheckBox);
		kfcs05CheckBox = (CheckBox) findViewById(R.id.kfcs05CheckBox);

		bcsfflSpinnerUtil = (SpinnerUtil) findViewById(R.id.bcsfflSpinnerUtil);
		xcsfrqCalendarText = (CalendarText) findViewById(R.id.xcsfrqCalendarText);
        sfglYyqkLayout = (SfglYyqkLayout) findViewById(R.id.sfglYyqkLayout);

		xzImageView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// [64, 105, 105, 90, 130, 65, 150, 130, 90]
				// [59, 99, 99, 78, 118, 59, 138, 118, 78]

				LinearLayout titleLinearLayout = (LinearLayout) findViewById(R.id.yyqkTitleLinearLayout);
				sfglYyqkLayout.addItem(null, titleLinearLayout);
			}
		});

		// 默认的情况下的状态
		zz01RadioButton.setChecked(true);
		yyEditText.setEnabled(false);
		jgjksEditText.setEnabled(false);

		zzRadioGroup
				.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(RadioGroup group, int checkedId) {
						if (checkedId == R.id.zz01RadioButton) {
							yyEditText.setEnabled(false);
							jgjksEditText.setEnabled(false);
						} else {
							yyEditText.setEnabled(true);
							jgjksEditText.setEnabled(true);
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
		Sfgljl_jsb sfgljl = (Sfgljl_jsb) beanMap
				.get(Sfgljl_jsb.class.getName());
		if (sfgljl == null)
			return;

		// 用药情况
		List<MedicineUse> medicineUse = sfgljl.medicineUses;
		// 清掉所有项
		sfglYyqkLayout.clear();
		if (medicineUse != null && medicineUse.size() > 0) {
			for (MedicineUse mu : medicineUse) {
				if (mu.medicine == null || mu.medicineUnit == null
						|| mu.dosage.equals("") || mu.usage == null
						|| mu.way == null) {
					break;
				}
				sfglYyqkLayout.addItem(null, mu);
			}
		}

		// 转诊
		yyEditText.setText(sfgljl.TransferReason);
		jgjksEditText.setText(sfgljl.TransferDepartment);
		if (!StringUtil.isEmptyString(sfgljl.TransferReason)
				&& !StringUtil.isEmptyString(sfgljl.TransferDepartment)) {
			zz01RadioButton.setChecked(true);
		} else {
			zz02RadioButton.setChecked(true);
		}

		// 下次随访日期
		if (sfgljl.NextFlupDate != null) {
			xcsfrqCalendarText.setText(sfgljl.NextFlupDate);
		}

		// 随访医生
		// Doctor doctor=sfgljl.getVisitDoctor();
		// if(doctor!=null&&!doctor.getTagValue().equals("")){
		// sfysqmEditText.setText(doctor.getTagValue());
		// }else{
		// try {
		// String
		// doctorName=MyApplication.getInstance().getSession().getLoginResult().response.doctorName;
		// sfysqmEditText.setText(doctorName);
		// } catch (NullPointerException e) {
		// e.printStackTrace();
		// }
		// }
	}

	@Override
	public boolean getValue() { if (!hasInit) {return false;}
		// TODO Auto-generated method stub

		Sfgljl_jsb sfgljl = (Sfgljl_jsb) beanMap
				.get(Sfgljl_jsb.class.getName());
		if (sfgljl == null) {
			Log.e(TAG, "sfgljl is null");
			return false;
		}

		// 用药情况
		List<MedicineUse> medicineUses = sfgljl.medicineUses;
		if (medicineUses != null) { // 如果有数据则先清空
			medicineUses.clear();
		} else { // 如果没有则先创建一个实体
			medicineUses = sfgljl.medicineUses = new ArrayList<MedicineUse>();
		}

		if (sfglYyqkLayout.mListView.size() > 0) {
			for (ListItemSfglYyqk liy : sfglYyqkLayout.mListView) {
				MedicineUse mu = liy.getMedicineUse();
				medicineUses.add(mu);
			}
		}

		// 转诊
		String zzyy = yyEditText.getText().toString().trim();// 转诊原因
		sfgljl.TransferReason = zzyy;

		String jghkb = jgjksEditText.getText().toString().trim();// 机构和科别
		sfgljl.TransferDepartment = jghkb;

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
			sfgljl.NextFlupDate = xcsfrq;
		}

		/**
		 * 随访医生的签名要不要，
		 */
		// Doctor doctor = new Jmjbxx.Doctor();
		// doctor.setTagValue(sfysqmEditText.getText().toString());
		// sfgljl.setVisitDoctor(doctor);

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
