package com.cking.phss.page;

import java.util.Map;

import net.xinhuaxing.util.ResourcesFactory;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.cking.phss.R;
import com.cking.phss.bean.BeanCD;
import com.cking.phss.bean.IBean;
import com.cking.phss.bean.Sfgljl_jsb;
import com.cking.phss.util.CheckBoxGroupUtil;
import com.cking.phss.util.TispToastFactory;
import com.cking.phss.widget.CalendarText;
import com.cking.phss.widget.SpinnerUtil;

public class SfglJsbPage01 extends MyPage  {

	private Toast mToast;
	private Context mContext;

	EditText zrysEditText = null;
	CalendarText sfrqCalendarText = null;
	SpinnerUtil wxxSpinnerUtil = null;
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
	CheckBox zz11CheckBox = null;
	CheckBox zz12CheckBox = null;

	EditText qtEditText = null;
	Map<String, IBean> beanMap;

	private CheckBoxGroupUtil zzCheckBoxGroup;
	int[] viewIds = new int[] { R.id.zz02CheckBox, R.id.zz03CheckBox,
			R.id.zz04CheckBox, R.id.zz05CheckBox, R.id.zz06CheckBox,
			R.id.zz07CheckBox, R.id.zz08CheckBox, R.id.zz09CheckBox,
			R.id.zz10CheckBox, R.id.zz11CheckBox, R.id.zz12CheckBox };
	public SfglJsbPage01(Context context) {
		super(context);
		// init(context);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param context
	 * @param beanMap
	 */
	public SfglJsbPage01(Context context, Map<String, IBean> beanMap) {
		super(context);
		mContext = context;
		this.beanMap = beanMap;
		// init(context);
		// TODO Auto-generated constructor stub
	}
	protected void init(Context context){
		mToast =TispToastFactory.getToast(context, "");
		LayoutInflater.from(context).inflate(R.layout.fragment_sfgl_jsb_01_layout,this);

        loadPage(context, this);
	}

	public void loadPage(Context context, ViewGroup viewGroup) {   	

		zrysEditText = (EditText) findViewById(R.id.zrysEditText);
		sfrqCalendarText = (CalendarText) findViewById(R.id.sfrqCalendarText);
		wxxSpinnerUtil = (SpinnerUtil) findViewById(R.id.wxxSpinnerUtil);
		zz01CheckBox = (CheckBox) findViewById(R.id.zz01CheckBox);
		zz02CheckBox = (CheckBox) findViewById(R.id.zz02CheckBox);
		zz03CheckBox = (CheckBox) findViewById(R.id.zz03CheckBox);
		zz04CheckBox = (CheckBox) findViewById(R.id.zz04CheckBox);
		zz05CheckBox = (CheckBox) findViewById(R.id.zz05CheckBox);
		zz06CheckBox = (CheckBox) findViewById(R.id.zz06CheckBox);
		zz07CheckBox = (CheckBox) findViewById(R.id.zz07CheckBox);
		zz08CheckBox = (CheckBox) findViewById(R.id.zz09CheckBox);
		zz10CheckBox = (CheckBox) findViewById(R.id.zz10CheckBox);
		zz11CheckBox = (CheckBox) findViewById(R.id.zz11CheckBox);
		zz12CheckBox = (CheckBox) findViewById(R.id.zz12CheckBox);

		qtEditText = (EditText) findViewById(R.id.qtEditText);
		String[] ids = ResourcesFactory.listId(mContext, "gxyzz");
		zzCheckBoxGroup = new CheckBoxGroupUtil(viewIds, this, ids);

		/**
		 * 该页面加载进来的初始状态,症状默认选为无，其余的checkbox不可编辑等等
		 */
		qtEditText.setEnabled(false);
		zzCheckBoxGroup.getCheckBox(zzCheckBoxGroup.size() - 1)// 对于无症状checkBox的监听
				.setOnCheckedChangeListener(
						new CompoundButton.OnCheckedChangeListener() {
							@Override
							public void onCheckedChanged(
									CompoundButton buttonView, boolean isChecked) {
								if (isChecked) {
									qtEditText.setEnabled(true);
								} else {
									qtEditText.setText("");
									qtEditText.setEnabled(false);
								}
							}
						});
	}

	/**
	 * 
	 */
	@Override
	public void setValue() { if (!hasInit) {return;}
		Sfgljl_jsb sfgljl = (Sfgljl_jsb) beanMap
				.get(Sfgljl_jsb.class.getName());
		if (sfgljl == null) {
			return;
		}

		if (sfgljl.DutyDoctorName != null) {
			zrysEditText.setText(sfgljl.DutyDoctorName);
		}
		if (sfgljl.FlupDate != null) {
			sfrqCalendarText.setText(sfgljl.FlupDate);
		}
		if (sfgljl.Dangerous != null) {
			wxxSpinnerUtil.setSelectedPositionByValue(sfgljl.Dangerous.getcD());
		}

		// 症状
		if (sfgljl.Symptoms != null) {
			String deformityCDs = sfgljl.Symptoms.getcD();
            zzCheckBoxGroup.setCheckedByValues(deformityCDs);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cking.phss.page.IPage#getValue()
	 */
	@Override
	public boolean getValue() { if (!hasInit) {return false;}
		Sfgljl_jsb sfgljl = (Sfgljl_jsb) beanMap
				.get(Sfgljl_jsb.class.getName());
		if (sfgljl == null) {
			return false;
		}

		sfgljl.DutyDoctorName = zrysEditText.getText().toString();
		sfgljl.FlupDate = sfrqCalendarText.getText().toString();
		sfgljl.Dangerous = new BeanCD(wxxSpinnerUtil.getSelectedValue(),
				wxxSpinnerUtil.getSelectedData());

		// 症状
		String checkes = zzCheckBoxGroup.getCheckValues(",");
		sfgljl.Symptoms = new BeanCD(checkes, "");

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
