package com.cking.phss.page;

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
import android.widget.Toast;

import com.cking.phss.R;
import com.cking.phss.bean.IBean;
import com.cking.phss.bean.Sfgljl_gxy;
import com.cking.phss.dto.innner.MedicineUse;
import com.cking.phss.util.TispToastFactory;
import com.cking.phss.widget.ListItemSfglYyqk;
import com.cking.phss.widget.SfglYyqkLayout;

public class SfglGxyPage04 extends MyPage  {
	@SuppressWarnings("unused")
	private static final String TAG = "SfglGxyPage04";
    private Context mContext = null;

	SfglYyqkLayout sfglYyqkLayout;
	ImageView xzImageView;
	EditText zljyEditText;

	private Toast mToast;
	private Map<String, IBean> beanMap = null;
    //List<Integer> widths = new ArrayList<Integer>();

	public SfglGxyPage04(Context context, Map<String, IBean> beanMap) {
		super(context);
		this.beanMap = beanMap;
		// init(context);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param context
	 * @param attrs
	 */
	public SfglGxyPage04(Context context, AttributeSet attrs) {
		super(context, attrs);
		// init(context);
		// TODO Auto-generated constructor stub
	}

	protected void init(Context context) {
		mContext = context;
		mToast = TispToastFactory.getToast(context, "");
		LayoutInflater.from(context).inflate(R.layout.fragment_sfgl_gxy_04_layout, this);

		//63, 103, 103, 86, 126, 87, 147, 127, 87
        //widths.add(63);
        //widths.add(103);
        //widths.add(103);
        //widths.add(86);
        //widths.add(126);
        //widths.add(87);
        //widths.add(147);
       // widths.add(127);
        //widths.add(87);
        
		loadPage(context, this);
	}

    public void loadPage(Context context, ViewGroup viewGroup) {
		xzImageView = (ImageView) findViewById(R.id.xzImageView);
		sfglYyqkLayout = (SfglYyqkLayout) findViewById(R.id.sfglYyqkLayout);
		zljyEditText = (EditText) findViewById(R.id.zljyEditText);

		xzImageView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
		//[64, 105, 105, 90, 130, 65, 150, 130, 90]
		//[59, 99, 99, 78, 118, 59, 138, 118, 78]

                LinearLayout titleLinearLayout = (LinearLayout) findViewById(R.id.yyqkTitleLinearLayout);
                sfglYyqkLayout.addItem(null, titleLinearLayout);
			}
		});

//		mCcfwflSpinner = (Spinner) findViewById(R.id.spinner_ccfwfl);
	}

	@Override
	public void setValue() { if (!hasInit) {return;}
		Sfgljl_gxy mSfgljl = (Sfgljl_gxy) beanMap.get(Sfgljl_gxy.class
				.getName());
		if (mSfgljl == null)
			return;

		// 用药情况
		List<MedicineUse> medicineUse = mSfgljl.getMedicineUse();
		// 清掉所有项
		sfglYyqkLayout.clear();
		if (medicineUse != null && medicineUse.size() > 0) {
			for (MedicineUse mu : medicineUse) {
				if (mu.medicine == null||mu.medicineUnit==null||mu.dosage.equals("")||
						mu.usage==null||mu.way==null) {
					break;
				}
				sfglYyqkLayout.addItem(null, mu);
			}
		}
		
        zljyEditText.setText(mSfgljl.recommendation);
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

		// 用药情况
		List<MedicineUse> medicineUses = mSfgljl.getMedicineUse();
		if (medicineUses != null) { // 如果有数据则先清空
			medicineUses.clear();
		} else { // 如果没有则先创建一个实体
			medicineUses = new ArrayList<MedicineUse>();
			mSfgljl.setMedicineUse(medicineUses);
		}

		if (sfglYyqkLayout.mListView.size() > 0) {
			for (ListItemSfglYyqk liy : sfglYyqkLayout.mListView) {
				MedicineUse mu = liy.getMedicineUse();
				medicineUses.add(mu);
			}
		}
		
        mSfgljl.recommendation = zljyEditText.getText().toString();

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
