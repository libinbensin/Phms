package com.cking.phss.page;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.cking.phss.R;
import com.cking.phss.bean.IBean;
import com.cking.phss.bean.Sfgljl_gxy;
import com.cking.phss.bean.Sfgljl_gxy.FZJC;
import com.cking.phss.util.TispToastFactory;
import com.cking.phss.widget.ListItemSfglFzjc;
import com.cking.phss.widget.SfglFzjcLayout;
import com.cking.phss.widget.SpinnerUtil;

public class SfglGxyPage03 extends MyPage  {
    @SuppressWarnings("unused")
    private static final String TAG = "SfglGxyPage02";

	SfglFzjcLayout mFzjcLayout;
	ImageView xzImageView;

	SpinnerUtil fyycxSpinnerUtil;// 服药依从性

	RadioGroup ywblfyRadioGroup;
	RadioButton ywblfy02RadioButton;
	RadioButton ywblfy01RadioButton;
	EditText ywblfyEditText;
    SpinnerUtil ccsfflSpinnerUtil;// 此次访问分类
	private Context mContext;
	private Toast mToast;
	private Map<String, IBean> beanMap = null;

	public SfglGxyPage03(Context context, Map<String, IBean> beanMap) {
		super(context);
		this.beanMap = beanMap;
		// init(context);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param context
	 * @param attrs
	 */
	public SfglGxyPage03(Context context, AttributeSet attrs) {
		super(context, attrs);
		// init(context);
		// TODO Auto-generated constructor stub
	}

	protected void init(Context context) {
		mContext = context;
		mToast = TispToastFactory.getToast(context, "");
		LayoutInflater.from(context).inflate(R.layout.fragment_sfgl_gxy_03_layout, this);
		
		loadPage(context, this);
	}

    public void loadPage(Context context, ViewGroup viewGroup) {
		xzImageView = (ImageView) findViewById(R.id.xzImageView);
		mFzjcLayout = (SfglFzjcLayout) findViewById(R.id.sfglFzjcLayout);

		xzImageView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
                LinearLayout titleLinearLayout = (LinearLayout) findViewById(R.id.fzjcTitleLinearLayout);
                mFzjcLayout.addItem(titleLinearLayout);
			}
		});
		fyycxSpinnerUtil = (SpinnerUtil) findViewById(R.id.fyycxSpinnerUtil);
		ywblfyRadioGroup = (RadioGroup) findViewById(R.id.ywblfyRadioGroup);
		ywblfy02RadioButton = (RadioButton) findViewById(R.id.ywblfy02RadioButton);
		ywblfy01RadioButton = (RadioButton) findViewById(R.id.ywblfy01RadioButton);
		ywblfyEditText = (EditText) findViewById(R.id.ywblfyEditText);
		ccsfflSpinnerUtil = (SpinnerUtil) findViewById(R.id.ccsfflSpinnerUtil);

		// radio的初始状态和 相应控件的状态
		ywblfyRadioGroup.check(R.id.ywblfy01RadioButton);// 默认没有不良的反应
		ywblfyEditText.setEnabled(false);
		ywblfyRadioGroup
				.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(RadioGroup group, int checkedId) {
						if (checkedId == ywblfy01RadioButton.getId()) {// 如果点击了无
							ywblfyEditText.setText("");
							ywblfyEditText.setEnabled(false);
						} else {// 如果点击了有
							ywblfyEditText.setEnabled(true);
						}
					}
				});

	}

	@Override
	public void setValue() { if (!hasInit) {return;}
		Sfgljl_gxy mSfgljl = (Sfgljl_gxy) beanMap.get(Sfgljl_gxy.class
				.getName());
		if (mSfgljl == null)
			return;
		
		// 辅助体检
		List<FZJC> fzjc = mSfgljl.getfZJC();
		// 清掉所有项
		mFzjcLayout.clear();
		if (fzjc != null && fzjc.size() > 0) {
			for (FZJC f : fzjc) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    mFzjcLayout.addItem(f.getjCXM(), f.getjCJG(), f.getjCR(), format.parse(f.getjCRQ()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
			}
		}
		
		fyycxSpinnerUtil.setSelection(mSfgljl.getfYYCXCD()-1);
        //不良反应的设置
		if(mSfgljl.getbLFY()==0){
			ywblfy01RadioButton.setChecked(true);
			ywblfyEditText.setEnabled(false);
		}else {
			ywblfy02RadioButton.setChecked(true);
			ywblfyEditText.setEnabled(true);
			ywblfyEditText.setText(mSfgljl.getfYQK());
		}

        ccsfflSpinnerUtil.setSelection(mSfgljl.getsFFLCD() - 1);
	}

	@Override
	public boolean getValue() { if (!hasInit) {return false;}
		Sfgljl_gxy mSfgljl = (Sfgljl_gxy) beanMap.get(Sfgljl_gxy.class
				.getName());
		if (mSfgljl == null) {
            Log.e(TAG, "mSfgljl is null");
			return false;
		}

		mSfgljl.setfYYCXCD(fyycxSpinnerUtil.getSelectedItemPosition() + 1);// 服药依从性

		// 获取药物不良反应
		if (ywblfy01RadioButton.isChecked())
			mSfgljl.setbLFY(0);
		else {
			mSfgljl.setbLFY(1);
			String blfy = ywblfyEditText.getText().toString().trim();
			if (!blfy.equals(""))
				mSfgljl.setfYQK(blfy);
		}

		// 辅助体检
		List<FZJC> fzjc = mSfgljl.getfZJC();
		if (fzjc != null) { // 如果有数据则先清空
			fzjc.clear();
		} else { // 如果没有则先创建一个实体
			fzjc = new ArrayList<FZJC>();
			mSfgljl.setfZJC(fzjc);
		}

		if (mFzjcLayout.mListView.size() > 0) {
			for (ListItemSfglFzjc ljc : mFzjcLayout.mListView) {
				FZJC f = new FZJC();
				f.setjCJG(ljc.getResult());
				f.setjCXM(ljc.getProject());
				f.setjCR(ljc.getOperator());
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                f.setjCRQ(format.format(ljc.getDate()));
				fzjc.add(f);
			}
		}
		mSfgljl.setfZJC(fzjc);
		
        // 此次访问分类
        mSfgljl.setsFFLCD(ccsfflSpinnerUtil.getSelectedItemPosition() + 1);
		
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
