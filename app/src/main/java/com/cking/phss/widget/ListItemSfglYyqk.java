/* Cking Inc. (C) 2012. All rights reserved.
 *
 * ListItemJbxxCommon.java
 * classes : com.cking.phss.view.ListItemJbxxCommon
 * @author Wation Haliyoo
 * V 1.0.0
 * Create at 2012-9-18 下午03:02:15
 */
package com.cking.phss.widget;


import java.util.List;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cking.phss.R;
import com.cking.phss.dto.innner.MedicineUse;

/**
 * 列表项，用于随访管理-用药情况
 * com.cking.phss.widget.ListItemSfglYyqk
 * @author Wation Haliyoo <br/>
 * create at 2012-9-18 下午03:02:15
 */
public /*abstract*/ class ListItemSfglYyqk extends RelativeLayout {
    private static final String TAG = "ListItemSfglYyqk";

    private TextView xhTextView = null; // 序号
    private TextView ywmcTextView = null;  // 药物名称
    private TextView ywlxTextView = null;  // 药物类型
    private TextView ylTextView = null;// 用量
    private TextView ywdwTextView=null;//单位
    private TextView yfTextView = null; // 用法
    private TextView syzjlTextView = null; // 总剂量
    private TextView gyfsTextView = null; // 给药方式

    private MedicineUse medicineUse;
    View[] needResetViews = null;
    /**
     * @param context
     */
    public ListItemSfglYyqk(Context context) {
        super(context);
        init(context);
    }

    /**
     * @param context
     * @param attrs
     */
    public ListItemSfglYyqk(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    /**
     * @param context
     */
    private void init(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.item_yyqk_layout, this);

        xhTextView = (TextView) findViewById(R.id.xhTextView);
        ywmcTextView = (TextView) findViewById(R.id.ywmcTextView);
        ywlxTextView = (TextView) findViewById(R.id.ywlxTextView);
        ylTextView = (TextView) findViewById(R.id.ylTextView);
        ywdwTextView = (TextView) findViewById(R.id.ywdwTextView);
        yfTextView = (TextView) findViewById(R.id.yfTextView);
        syzjlTextView = (TextView) findViewById(R.id.syzjlTextView);
        gyfsTextView = (TextView) findViewById(R.id.gyfsTextView);
        LinearLayout czLinearLayout = (LinearLayout) findViewById(R.id.czLinearLayout);
        needResetViews = new View[] { xhTextView, ywmcTextView, ywlxTextView, ylTextView,
                ywdwTextView, yfTextView, syzjlTextView, gyfsTextView, 
                czLinearLayout };
    }

    public void setIndex(int index) {
        xhTextView.setText((index + 1) + "");
        setTag(index);
        if (index % 2 == 1) {
            setBackgroundResource(R.color.list_jsh_background_color);
        } else {
            setBackgroundResource(R.color.list_osh_background_color);
        }
    }
    
    public int getIndex() {
        return Integer.parseInt(xhTextView.getText().toString());
    }

    public void setName(String name) {
        ywmcTextView.setText(name);
    }
    
    public String getName() {
        return ywmcTextView.getText().toString();
    }

    public void setUsage(String usage) {
        yfTextView.setText(usage);
    }

    public String getUsage() {
        return yfTextView.getText().toString();
    }

    public void setDosage(String dosage) {
        ylTextView.setText(dosage);
    }

    public String getDosage() {
        return ylTextView.getText().toString();
    }

    public void setUnit(String unit) {
        ywdwTextView.setText(unit);
    }

    public String getUnit() {
        return ywdwTextView.getText().toString();
    }

    public void setWay(String way) {
        gyfsTextView.setText(way);
    }

    public String getWay() {
        return gyfsTextView.getText().toString();
    }
    
//    public void setTitleItem(boolean titleItem) {
//        RelativeLayout titleLayout = (RelativeLayout) findViewById(R.id.opration_title);
//        RelativeLayout bodyLayout = (RelativeLayout) findViewById(R.id.opration_body);
//        
//        if (titleItem) {
//            titleLayout.setVisibility(View.VISIBLE);
//            bodyLayout.setVisibility(View.GONE);
//        } else {
//            titleLayout.setVisibility(View.GONE);
//            bodyLayout.setVisibility(View.VISIBLE);
//        }
//    }

	public MedicineUse getMedicineUse() {
		return medicineUse;
	}

	public void setMedicineUse(MedicineUse medicineUse) {
		this.medicineUse = medicineUse;
	}

    public String getType() {
        return ywlxTextView.getText().toString();
    }

    public void setType(String mTypeText) {
        this.ywlxTextView.setText(mTypeText);
    }

    public String getTotalDosage() {
        return syzjlTextView.getText().toString();
    }

    public void setTotalDosage(String mTotalDosageText) {
        this.syzjlTextView.setText(mTotalDosageText);
    }

    /**
     * @param widths
     */
    public void setViewByWidths(List<Integer> widths) {
        for (int i = 0; i<needResetViews.length; i++) {
            View childView = needResetViews[i];
            if (childView instanceof TextView) {
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) childView.getLayoutParams(); 
                params.width = widths.get(i);
                childView.setLayoutParams(params);
            }
            
        }
    }
}
