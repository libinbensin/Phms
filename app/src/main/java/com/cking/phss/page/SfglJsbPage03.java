package com.cking.phss.page;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;

import com.cking.phss.R;
import com.cking.phss.bean.BeanCD;
import com.cking.phss.bean.IBean;
import com.cking.phss.bean.InspectAuxiliary;
import com.cking.phss.bean.Sfgljl_jsb;
import com.cking.phss.util.TispToastFactory;
import com.cking.phss.widget.CalendarText;
import com.cking.phss.widget.ListItemSfglFzjc;
import com.cking.phss.widget.SfglFzjcLayout;
import com.cking.phss.widget.SpinnerUtil;

public class SfglJsbPage03 extends MyPage  {
	SpinnerUtil gsqkSpinnerUtil = null;
	CalendarText mccysjCalendarText = null;
	SpinnerUtil zyqkSpinnerUtil = null;
	ImageView xzImageView = null;
	SpinnerUtil fyycxSpinnerUtil = null;
	RadioButton ywblfy01RadioButton = null;
	RadioButton ywblfy02RadioButton = null;
	EditText ywblfyEditText = null;
	SpinnerUtil zlxgSpinnerUtil = null;
	Map<String, IBean> beanMap;

	SfglFzjcLayout mFzjcLayout;
	private Context mContext;
	private Toast mToast;
	public SfglJsbPage03(Context context) {
		super(context);
		// init(context);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param context
	 * @param beanMap
	 */
	public SfglJsbPage03(Context context, Map<String, IBean> beanMap) {
		super(context);
		this.beanMap = beanMap;
		// init(context);
		// TODO Auto-generated constructor stub
	}
	protected void init(Context context){
		mContext = context;
		mToast =TispToastFactory.getToast(context, "");
		LayoutInflater.from(context).inflate(R.layout.fragment_sfgl_jsb_03_layout, this);

        loadPage(context, this);
	}

	public void loadPage(Context context, ViewGroup viewGroup) {   
		gsqkSpinnerUtil = (SpinnerUtil) findViewById(R.id.gsqkSpinnerUtil);
		mccysjCalendarText = (CalendarText) findViewById(R.id.mccysjCalendarText);
		zyqkSpinnerUtil = (SpinnerUtil) findViewById(R.id.zyqkSpinnerUtil);
		xzImageView = (ImageView) findViewById(R.id.xzImageView);
		fyycxSpinnerUtil = (SpinnerUtil) findViewById(R.id.fyycxSpinnerUtil);
		ywblfy01RadioButton = (RadioButton) findViewById(R.id.ywblfy01RadioButton);
		ywblfy02RadioButton = (RadioButton) findViewById(R.id.ywblfy02RadioButton);
		ywblfyEditText = (EditText) findViewById(R.id.ywblfyEditText);
		zlxgSpinnerUtil = (SpinnerUtil) findViewById(R.id.zlxgSpinnerUtil);
		mFzjcLayout = (SfglFzjcLayout) findViewById(R.id.sfglFzjcLayout);
		xzImageView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				LinearLayout titleLinearLayout = (LinearLayout) findViewById(R.id.fzjcTitleLinearLayout);
				mFzjcLayout.addItem(titleLinearLayout);
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
		if (sfgljl == null) {
			return;
		}

		// 关锁情况
		if (sfgljl.LockUp != null) {
			gsqkSpinnerUtil.setSelectedPositionByValue(sfgljl.LockUp.getcD());
		}

		// 住院情况
		if (sfgljl.Hospitalizations != null) {
			zyqkSpinnerUtil.setSelectedPositionByValue(sfgljl.Hospitalizations
					.getcD());
		}

		// 末次出院时间
		if (sfgljl.LastDischargeDate != null) {
			mccysjCalendarText.setText(sfgljl.LastDischargeDate);
		}

		// 辅助体检
		List<InspectAuxiliary> fzjc = sfgljl.inspectAuxiliaries;
		// 清掉所有项
		mFzjcLayout.clear();
		if (fzjc != null && fzjc.size() > 0) {
			for (InspectAuxiliary f : fzjc) {
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				try {
					mFzjcLayout.addItem(f.CheckProject, f.CheckResult,
							f.CheckDoctor, format.parse(f.CheckDate));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}

		// 服药依从性
		if (sfgljl.DrugCompliance != null) {
			fyycxSpinnerUtil.setSelectedPositionByValue(sfgljl.DrugCompliance
					.getcD());
		}

		// 设置药物不良反应
		if (sfgljl.AdverseReactions != null)
			if (sfgljl.AdverseReactions.getcD().equals("0")) {
				ywblfy01RadioButton.setChecked(true);
			} else {
				ywblfy02RadioButton.setChecked(true);
				ywblfyEditText.setText(sfgljl.AdverseReactions.getTagValue());
			}
		else {
			String blfy = ywblfyEditText.getText().toString().trim();
			sfgljl.AdverseReactions = new BeanCD(1, blfy);
		}

		// 治疗效果
		if (sfgljl.TreatmentEffect != null) {
			zlxgSpinnerUtil.setSelectedPositionByValue(sfgljl.TreatmentEffect
					.getcD());
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

		// 关锁情况
		sfgljl.LockUp = new BeanCD(gsqkSpinnerUtil.getSelectedValue(), "");

		// 住院情况
		sfgljl.Hospitalizations = new BeanCD(
				zyqkSpinnerUtil.getSelectedValue(), "");

		// 末次出院时间
		sfgljl.LastDischargeDate = mccysjCalendarText.getText().toString();

		// 辅助体检
		List<InspectAuxiliary> fzjc = sfgljl.inspectAuxiliaries;
		if (fzjc != null) { // 如果有数据则先清空
			fzjc.clear();
		} else { // 如果没有则先创建一个实体
			fzjc = sfgljl.inspectAuxiliaries = new ArrayList<InspectAuxiliary>();
		}

		if (mFzjcLayout.mListView.size() > 0) {
			for (ListItemSfglFzjc ljc : mFzjcLayout.mListView) {
				InspectAuxiliary f = new InspectAuxiliary();
				f.CheckResult = ljc.getResult();
				f.CheckProject = ljc.getProject();
				f.CheckDoctor = ljc.getOperator();
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				f.CheckDate = format.format(ljc.getDate());
				fzjc.add(f);
			}
		}
		sfgljl.inspectAuxiliaries = fzjc;

		// 服药依从性
		sfgljl.DrugCompliance = new BeanCD(fyycxSpinnerUtil.getSelectedValue(),
				"");

		// 获取药物不良反应
		if (ywblfy01RadioButton.isChecked())
			sfgljl.AdverseReactions = new BeanCD(0, "");
		else {
			String blfy = ywblfyEditText.getText().toString().trim();
			sfgljl.AdverseReactions = new BeanCD(1, blfy);
		}

		// 治疗效果
		sfgljl.TreatmentEffect = new BeanCD(zlxgSpinnerUtil.getSelectedValue(),
				"");

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
