package com.cking.phss.page;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.xinhuaxing.util.StringUtil;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.cking.phss.R;
import com.cking.phss.bean.IBean;
import com.cking.phss.bean.Sfgljl_tnb;
import com.cking.phss.dto.innner.MedicineUse;
import com.cking.phss.util.TispToastFactory;
import com.cking.phss.widget.ListItemSfglYds;
import com.cking.phss.widget.ListItemSfglYyqk;
import com.cking.phss.widget.SfglYdsLayout;
import com.cking.phss.widget.SfglYyqkLayout;

public class SfglTnbPage03 extends MyPage  {

	ImageView xz01ImageView = null;
	LinearLayout yyqkTitleLinearLayout = null;
	SfglYyqkLayout sfglYyqkLayout = null;
	ImageView xz02ImageView = null;
	SfglYdsLayout sfglYdsLayout;
	LinearLayout ydsTitleLinearLayout;


	private Map<String, IBean> beanMap = null;

	private Toast mToast;
    //List<Integer> widths = new ArrayList<Integer>();

	public SfglTnbPage03(Context context, Map<String, IBean> beanMap) {
		super(context);
		this.beanMap = beanMap;
		// init(context);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param context
	 * @param attrs
	 */
	public SfglTnbPage03(Context context, AttributeSet attrs) {
		super(context, attrs);
		// init(context);
		// TODO Auto-generated constructor stub
	}

	protected void init(Context context) {
		mToast = TispToastFactory.getToast(context, "");
		LayoutInflater.from(context).inflate(R.layout.fragment_sfgl_tnb_03_layout, this);

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

		xz01ImageView = (ImageView)findViewById(R.id.xz01ImageView);
		yyqkTitleLinearLayout = (LinearLayout)findViewById(R.id.yyqkTitleLinearLayout);
		sfglYyqkLayout = (SfglYyqkLayout)findViewById(R.id.sfglYyqkLayout);
		xz02ImageView = (ImageView)findViewById(R.id.xz02ImageView);
		sfglYdsLayout = (SfglYdsLayout) findViewById(R.id.sfglYdsLayout);
		ydsTitleLinearLayout = (LinearLayout) findViewById(R.id.ydsTitleLinearLayout);

		xz01ImageView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
                LinearLayout titleLinearLayout = (LinearLayout) findViewById(R.id.yyqkTitleLinearLayout);
                sfglYyqkLayout.addItem(null, titleLinearLayout);
			}
		});
		xz02ImageView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				LinearLayout titleLinearLayout = (LinearLayout) findViewById(R.id.ydsTitleLinearLayout);
				sfglYdsLayout.addItem(titleLinearLayout);
			}
		});
	}

	@Override
	public void setValue() { if (!hasInit) {return;}
		Sfgljl_tnb mSfgljl = (Sfgljl_tnb) beanMap.get(Sfgljl_tnb.class
				.getName());
		if (mSfgljl == null)
			return;

		// 用药情况
		List<MedicineUse> medicineUse = mSfgljl.getMedicineUse();
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

		// 胰島素
		String insulinUse = mSfgljl.getInsulinUse();
		// 清掉所有项
		sfglYdsLayout.clear();
		if (!StringUtil.isEmptyString(insulinUse)) {
            String[] items = insulinUse.split("\\|");
			if (items != null) {
				for (String item : items) {
					String[] cells = item.split(",");
					if( cells.length >=3 )
					{
						sfglYdsLayout.addItem(cells[0], cells[1], cells[2]);
					}
				}
			}
		}
	}

	@Override
	public boolean getValue() { if (!hasInit) {return false;}
		Sfgljl_tnb mSfgljl = (Sfgljl_tnb) beanMap.get(Sfgljl_tnb.class
				.getName());
		if (mSfgljl == null)
			return false;

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

		// 胰島素
		String insulinUse = "";
		if (sfglYdsLayout.mListView.size() > 0) {
			int i = 0;
			for (ListItemSfglYds liy : sfglYdsLayout.mListView) {
				String cells = liy.getType() + "," + liy.getFrequency() + ","
						+ liy.getUsage();
				if (i > 0) {
					cells = "|" + cells;
				}
				insulinUse += cells;
				i++;
			}
		}
		mSfgljl.setInsulinUse(insulinUse);

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
