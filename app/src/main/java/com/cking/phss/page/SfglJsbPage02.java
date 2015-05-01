package com.cking.phss.page;

import java.util.Map;

import net.xinhuaxing.util.ResourcesFactory;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.cking.phss.R;
import com.cking.phss.bean.BeanCD;
import com.cking.phss.bean.IBean;
import com.cking.phss.bean.Sfgljl_jsb;
import com.cking.phss.util.RadioGroupUtil;
import com.cking.phss.util.TispToastFactory;
import com.cking.phss.widget.SpinnerUtil;

public class SfglJsbPage02 extends MyPage  {

	RadioButton zzl01RadioButton = null;
	RadioButton zzl02RadioButton = null;
	RadioButton zzl03RadioButton = null;

	SpinnerUtil smqkSpinnerUtil = null;
	SpinnerUtil ysqkSpinnerUtil = null;

	View lineView = null;

	SpinnerUtil grshllSpinnerUtil = null;
	SpinnerUtil jwhdSpinnerUtil = null;
	SpinnerUtil xxnlSpinnerUtil = null;
	SpinnerUtil scldjgzSpinnerUtil = null;
	SpinnerUtil shrjjwSpinnerUtil = null;

	EditText qdzsEditText = null;
	EditText zsEditText = null;
	EditText zhEditText = null;
	EditText zhaoshiEditText = null;
	EditText zswsEditText = null;
	EditText qtEditText = null;
	Map<String, IBean> beanMap;

	private RadioGroupUtil zzlRadioGroup;// 自知力

	private int[] zzlRadioBtnIds = new int[] { R.id.zzl01RadioButton,
			R.id.zzl02RadioButton, R.id.zzl03RadioButton };
	private Toast mToast;
	private Context mContext;
	public SfglJsbPage02(Context context) {
		super(context);
		// init(context);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param context
	 * @param beanMap
	 */
	public SfglJsbPage02(Context context, Map<String, IBean> beanMap) {
		super(context);
		this.beanMap = beanMap;
		// init(context);
		// TODO Auto-generated constructor stub
	}
	protected void init(Context context){
		mContext = context;
		mToast =TispToastFactory.getToast(context, "");
		LayoutInflater.from(context).inflate(R.layout.fragment_sfgl_jsb_02_layout, this);

        loadPage(context, this);
	}
	public void loadPage(Context context, ViewGroup viewGroup) {   
		zzl01RadioButton = (RadioButton) findViewById(R.id.zzl01RadioButton);
		zzl02RadioButton = (RadioButton) findViewById(R.id.zzl02RadioButton);
		zzl03RadioButton = (RadioButton) findViewById(R.id.zzl03RadioButton);

		smqkSpinnerUtil = (SpinnerUtil) findViewById(R.id.smqkSpinnerUtil);
		ysqkSpinnerUtil = (SpinnerUtil) findViewById(R.id.ysqkSpinnerUtil);

		lineView = (View) findViewById(R.id.lineView);

		grshllSpinnerUtil = (SpinnerUtil) findViewById(R.id.grshllSpinnerUtil);
		jwhdSpinnerUtil = (SpinnerUtil) findViewById(R.id.jwhdSpinnerUtil);
		xxnlSpinnerUtil = (SpinnerUtil) findViewById(R.id.xxnlSpinnerUtil);
		scldjgzSpinnerUtil = (SpinnerUtil) findViewById(R.id.scldjgzSpinnerUtil);
		shrjjwSpinnerUtil = (SpinnerUtil) findViewById(R.id.shrjjwSpinnerUtil);

		qdzsEditText = (EditText) findViewById(R.id.qdzsEditText);
		zsEditText = (EditText) findViewById(R.id.zsEditText);
		zhEditText = (EditText) findViewById(R.id.zhEditText);
		zhaoshiEditText = (EditText) findViewById(R.id.zsEditText);
		zswsEditText = (EditText) findViewById(R.id.zswsEditText);
		qtEditText = (EditText) findViewById(R.id.qtEditText);
		// 自知力
		String[] ids = ResourcesFactory.listId(mContext, "zzl");
		zzlRadioGroup = new RadioGroupUtil(zzlRadioBtnIds, viewGroup, ids);
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

		// 自知力
		if (sfgljl.Insight != null) {
			zzlRadioGroup.setCheckedByValue(sfgljl.Insight.getcD());
		}

		// 睡眠情况
		if (sfgljl.Sleeping != null) {
			smqkSpinnerUtil.setSelectedPositionByValue(sfgljl.Sleeping.getcD());
		}

		// 饮食情况
		if (sfgljl.Diet != null) {
			ysqkSpinnerUtil.setSelectedPositionByValue(sfgljl.Diet.getcD());
		}

		// 个人生活料理
		if (sfgljl.LifeCare != null) {
			grshllSpinnerUtil.setSelectedPositionByValue(sfgljl.LifeCare
					.getcD());
		}

		// 家务活动
		if (sfgljl.Housework != null) {
			jwhdSpinnerUtil
					.setSelectedPositionByValue(sfgljl.Housework.getcD());
		}

		// 学习能力
		if (sfgljl.LearningAbility != null) {
			xxnlSpinnerUtil.setSelectedPositionByValue(sfgljl.LearningAbility
					.getcD());
		}

		// 生产劳动及工作
		if (sfgljl.ProductiveLabor != null) {
			scldjgzSpinnerUtil
					.setSelectedPositionByValue(sfgljl.ProductiveLabor.getcD());
		}

		// 社会人际交往
		if (sfgljl.Communication != null) {
			shrjjwSpinnerUtil.setSelectedPositionByValue(sfgljl.Communication
					.getcD());
		}

		// 轻度滋事
		if (sfgljl.MildTrouble != null) {
			qdzsEditText.setText(sfgljl.MildTrouble);
		}

		// 肇事
		if (sfgljl.Accident != null) {
			zhaoshiEditText.setText(sfgljl.Accident);
		}

		// 肇祸
		if (sfgljl.Trouble != null) {
			zhEditText.setText(sfgljl.Trouble);
		}

		// 自伤
		if (sfgljl.SelfWounding != null) {
			zsEditText.setText(sfgljl.SelfWounding);
		}

		// 自杀未遂
		if (sfgljl.AttemptedSuicide != null) {
			zswsEditText.setText(sfgljl.AttemptedSuicide);
		}

		// 其他
		if (sfgljl.InfluenceOther != null) {
			qtEditText.setText(sfgljl.InfluenceOther);
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

		// 自知力
		if (zzlRadioGroup.getCheckValue() != null) {
			sfgljl.Insight = new BeanCD(zzlRadioGroup.getCheckValue(), "");
		}

		// 睡眠情况
		sfgljl.Sleeping = new BeanCD(smqkSpinnerUtil.getSelectedValue(), "");

		// 饮食情况
		sfgljl.Diet = new BeanCD(ysqkSpinnerUtil.getSelectedValue(), "");

		// 个人生活料理
		sfgljl.LifeCare = new BeanCD(grshllSpinnerUtil.getSelectedValue(), "");

		// 家务活动
		sfgljl.Housework = new BeanCD(jwhdSpinnerUtil.getSelectedValue(), "");

		// 学习能力
		sfgljl.LearningAbility = new BeanCD(xxnlSpinnerUtil.getSelectedValue(),
				"");

		// 生产劳动及工作
		sfgljl.ProductiveLabor = new BeanCD(
				scldjgzSpinnerUtil.getSelectedValue(), "");

		// 社会人际交往
		sfgljl.Communication = new BeanCD(shrjjwSpinnerUtil.getSelectedValue(),
				"");

		// 轻度滋事
		sfgljl.MildTrouble = qdzsEditText.getText().toString();

		// 肇事
		sfgljl.Accident = zhaoshiEditText.getText().toString();

		// 肇祸
		sfgljl.Trouble = zhEditText.getText().toString();

		// 自伤
		sfgljl.SelfWounding = zsEditText.getText().toString();

		// 自杀未遂
		sfgljl.AttemptedSuicide = zswsEditText.getText().toString();

		// 其他
		sfgljl.InfluenceOther = qtEditText.getText().toString();

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
