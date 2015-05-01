package com.cking.phss.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.ViewFlipper;

import com.cking.phss.R;
import com.cking.phss.util.DisplayUtil;

public class GuideFlipper extends LinearLayout {
    @SuppressWarnings("unused")
    private static final String TAG = "GuideFlipper";

    private ImageButton mPreviousBtn = null;
    private ImageButton mNextBtn = null;
    private RadioGroup mBottomRadios = null;
    
    private Context mContext = null;
    private int mPageCount = 0;
    int select = 0;
    private ViewFlipper mFlipper;
    private static final int BOTTOM_RADIO_ID_OFFSET = 200;
    
	public GuideFlipper(Context context) {

		super(context);

        init(context);

	}

	public GuideFlipper(Context context, AttributeSet attrs) {

		super(context, attrs);

        init(context);
	}

    /**
     * @param context
     */
    private void init(Context context) {
        mContext = context;
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.guide_flipper, this);
        
        mPreviousBtn = (ImageButton) findViewById(R.id.previous_img);
        mNextBtn = (ImageButton) findViewById(R.id.next_img);
        mBottomRadios = (RadioGroup) findViewById(R.id.bottom_radio);
        mFlipper = (ViewFlipper) findViewById(R.id.flipper_pannel);

        mPreviousBtn.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                showPrevious();
            }
        });
        mNextBtn.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                showNext();
            }
        });
        
        mBottomRadios.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
            }
        });
        
        // 更新方向键
        updateDirectKey();
    }

    public void addView(View child) {
        mFlipper.addView(child);

        /// 添加按钮的数量
        RadioButton r = new RadioButton(mContext, null, R.style.bottom_radio_style);
        r.setBackgroundResource(R.drawable.bottom_bar_bg);
        r.setId(BOTTOM_RADIO_ID_OFFSET + mPageCount); 
        /// 高亮第一个
        if (mPageCount == 0) {
            r.setChecked(true);
        } else {
            RadioGroup.LayoutParams params = new RadioGroup.LayoutParams(
                    LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            params.leftMargin = DisplayUtil.dip2px(24, DisplayUtil.getScale(mContext));
            r.setLayoutParams(params);
        }
        mBottomRadios.addView(r);
        mPageCount++;
    }
    
    public void showNext() {
        mFlipper.showNext();
        select++;
        RadioButton r = (RadioButton) findViewById(BOTTOM_RADIO_ID_OFFSET + select);
        r.setChecked(true);
        
        updateDirectKey();
    }
    
    public void showPrevious() {
        mFlipper.showPrevious();
        select--;
        RadioButton r = (RadioButton) findViewById(BOTTOM_RADIO_ID_OFFSET + select);
        r.setChecked(true);
        
        updateDirectKey();
    }
    
    public void updateDirectKey() {

        // 如果第一个则去掉左侧的方向键
        // 如果最后一个则去掉右侧的方向键
        if (select == 0) {
            mPreviousBtn.setVisibility(View.GONE);
            mNextBtn.setVisibility(View.VISIBLE);
        } else if (select == mPageCount - 1) {
            mPreviousBtn.setVisibility(View.VISIBLE);
            mNextBtn.setVisibility(View.GONE);
        } else {
            mPreviousBtn.setVisibility(View.VISIBLE);
            mNextBtn.setVisibility(View.VISIBLE);
        }
    }
}
