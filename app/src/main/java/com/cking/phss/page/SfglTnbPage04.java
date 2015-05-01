package com.cking.phss.page;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.cking.phss.R;
import com.cking.phss.bean.IBean;
import com.cking.phss.bean.Sfgljl_gxy.FZJC;
import com.cking.phss.bean.Sfgljl_tnb;
import com.cking.phss.util.TispToastFactory;
import com.cking.phss.widget.ListItemSfglFzjc;
import com.cking.phss.widget.SfglFzjcLayout;
import com.cking.phss.widget.SpinnerUtil;

public class SfglTnbPage04 extends MyPage  {
	ImageView xzImageView = null;
	LinearLayout fzjcTitleLinearLayout = null;
	SfglFzjcLayout sfglFzjcLayout = null;
	EditText kfxtzEditText = null;
	EditText thxhdbzEditText = null;
	EditText chlxsxtzEditText = null;
	EditText sjxtzEditText = null;
	SpinnerUtil fyycxSpinnerUtil = null;
//
//	private SfglYdsLayout mYdsLayout = null;
	private Map<String, IBean> beanMap = null;
//	private Button mYdsAddBtn = null;
	private Toast mToast;
//
//	private EditText mZzyyEdit = null;// 转诊原因
//	private EditText mJghkbEdit = null;// 机构和可别
//	private CalendarText mXcsfrqCalendar = null;// 下次随访日期
//	private EditText mSfysqmEdit = null;// 随访医生签名
//
//	private Button mUploadBtn = null;
//	private Button mSaveBtn = null;

//	private SfglTnbPage mParent;

	public SfglTnbPage04(Context context, Map<String, IBean> beanMap,
	        SfglTnbPage parent) {
		super(context);
		this.beanMap = beanMap;
//		mParent = parent;
		// init(context);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param context
	 * @param attrs
	 */
	public SfglTnbPage04(Context context, AttributeSet attrs) {
		super(context, attrs);
		// init(context);
		// TODO Auto-generated constructor stub
	}

	protected void init(Context context) {
		mToast = TispToastFactory.getToast(context, "");
		LayoutInflater.from(context).inflate(R.layout.fragment_sfgl_tnb_04_layout, this);
        
        loadPage(context, this);
    }

    public void loadPage(Context context, ViewGroup viewGroup) {

		xzImageView = (ImageView) findViewById(R.id.xzImageView);
		fzjcTitleLinearLayout = (LinearLayout) findViewById(R.id.fzjcTitleLinearLayout);
		sfglFzjcLayout = (SfglFzjcLayout) findViewById(R.id.sfglFzjcLayout);
		kfxtzEditText		= (EditText) findViewById(R.id.kfxtzEditText		);
		thxhdbzEditText	= (EditText) findViewById(R.id.thxhdbzEditText	);
		chlxsxtzEditText	= (EditText) findViewById(R.id.chlxsxtzEditText	);
		sjxtzEditText		= (EditText) findViewById(R.id.sjxtzEditText		);
		fyycxSpinnerUtil = (SpinnerUtil) findViewById(R.id.fyycxSpinnerUtil);

        xzImageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout titleLinearLayout = (LinearLayout) findViewById(R.id.fzjcTitleLinearLayout);
                sfglFzjcLayout.addItem(titleLinearLayout);
            }
        });

	}

	@Override
	public void setValue() { if (!hasInit) {return;}
		Sfgljl_tnb mSfgljl = (Sfgljl_tnb) beanMap.get(Sfgljl_tnb.class
				.getName());
		if (mSfgljl == null)
			return;
        
        // 辅助体检
        List<FZJC> fzjc = mSfgljl.getfZJC();
        // 清掉所有项
        sfglFzjcLayout.clear();
        if (fzjc != null && fzjc.size() > 0) {
            for (FZJC f : fzjc) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    sfglFzjcLayout.addItem(f.getjCXM(), f.getjCJG(), f.getjCR(), format.parse(f.getjCRQ()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
        
        kfxtzEditText.setText(mSfgljl.getkFXT()); // 空腹血糖
        thxhdbzEditText.setText(mSfgljl.getxHDB()); // 糖化血红蛋白
        chlxsxtzEditText.setText(mSfgljl.getcHXT()); // 餐后两小时血糖
        sjxtzEditText.setText(mSfgljl.getqTXT()); // 随机血糖值

		// 药物服从性
		fyycxSpinnerUtil.setSelection(mSfgljl.getfYYCXCD() - 1);
	}

	@Override
	public boolean getValue() { if (!hasInit) {return false;}
		Sfgljl_tnb mSfgljl = (Sfgljl_tnb) beanMap.get(Sfgljl_tnb.class
				.getName());
		if (mSfgljl == null)
			return false;

        // 辅助体检
        List<FZJC> fzjc = mSfgljl.getfZJC();
        if (fzjc != null) { // 如果有数据则先清空
            fzjc.clear();
        } else { // 如果没有则先创建一个实体
            fzjc = new ArrayList<FZJC>();
            mSfgljl.setfZJC(fzjc);
        }

        if (sfglFzjcLayout.mListView.size() > 0) {
            for (ListItemSfglFzjc ljc : sfglFzjcLayout.mListView) {
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

        mSfgljl.setkFXT(kfxtzEditText.getText().toString()); // 空腹血糖
        mSfgljl.setxHDB(thxhdbzEditText.getText().toString()); // 糖化血红蛋白
        mSfgljl.setcHXT(chlxsxtzEditText.getText().toString()); // 餐后两小时血糖
        mSfgljl.setqTXT(sjxtzEditText.getText().toString()); // 餐后两小时血糖

      // 药物服从性
        mSfgljl.setfYYCXCD(fyycxSpinnerUtil.getSelectedValueInt());

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
