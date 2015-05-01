package com.cking.phss.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Gallery;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.SpinnerAdapter;

import com.cking.phss.R;
import com.cking.phss.util.DisplayUtil;

public class GuideGallery extends LinearLayout {
    @SuppressWarnings("unused")
    private static final String TAG = "GuideGallery";

    private ImageButton mPreviousBtn = null;
    private ImageButton mNextBtn = null;
    private RadioGroup mBottomRadios = null;
    
    private Context mContext = null;
    private int mPageCount = 0;
    int select = 0;
    private Gallery mGallery;
    private static final int BOTTOM_RADIO_ID_OFFSET = 200;
    
	public GuideGallery(Context context) {

		super(context);

        init(context);

	}

	public GuideGallery(Context context, AttributeSet attrs) {

		super(context, attrs);

        init(context);
	}

    /**
     * @param context
     */
    private void init(Context context) {
        mContext = context;
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.guide_gallery, this);
        
        mPreviousBtn = (ImageButton) findViewById(R.id.previous_img);
        mNextBtn = (ImageButton) findViewById(R.id.next_img);
        mBottomRadios = (RadioGroup) findViewById(R.id.bottom_radio);
        mGallery = (Gallery) findViewById(R.id.gallery_pannel);

        mPreviousBtn.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                mGallery.setSelection(mGallery.getSelectedItemPosition() - 1);
            }
        });
        mNextBtn.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                mGallery.setSelection(mGallery.getSelectedItemPosition() + 1);
            }
        });
        
        mBottomRadios.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                mGallery.setSelection(checkedId - BOTTOM_RADIO_ID_OFFSET);
            }
        });
        
        mGallery.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
             // 如果第一个则去掉左侧的方向键
                // 如果最后一个则去掉右侧的方向键
                if (arg2 == 0) {
                    mPreviousBtn.setVisibility(View.GONE);
                    mNextBtn.setVisibility(View.VISIBLE);
                } else if (arg2 == mPageCount - 1) {
                    mPreviousBtn.setVisibility(View.VISIBLE);
                    mNextBtn.setVisibility(View.GONE);
                } else {
                    mPreviousBtn.setVisibility(View.VISIBLE);
                    mNextBtn.setVisibility(View.VISIBLE);
                }
                
                // 设置RadioButton
                RadioButton r = (RadioButton) findViewById(BOTTOM_RADIO_ID_OFFSET
                        + arg2);
                r.setChecked(true);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
        
        mGallery.setOnFocusChangeListener(new OnFocusChangeListener() {
            
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                // 如果第一个则去掉左侧的方向键
                // 如果最后一个则去掉右侧的方向键
                if (mGallery.getSelectedItemPosition() == 0) {
                    mPreviousBtn.setVisibility(View.GONE);
                    mNextBtn.setVisibility(View.VISIBLE);
                } else if (mGallery.getSelectedItemPosition() == mPageCount - 1) {
                    mPreviousBtn.setVisibility(View.VISIBLE);
                    mNextBtn.setVisibility(View.GONE);
                } else {
                    mPreviousBtn.setVisibility(View.VISIBLE);
                    mNextBtn.setVisibility(View.VISIBLE);
                }
                
                // 设置RadioButton
                RadioButton r = (RadioButton) findViewById(BOTTOM_RADIO_ID_OFFSET
                        + mGallery.getSelectedItemPosition());
                r.setChecked(true);
            }
        });
    }

    public void setAdapter(SpinnerAdapter adapter) {
        mGallery.setAdapter(adapter);
        
        //mGallery.setSelection(0);

        /// 添加按钮的数量
        mPageCount = adapter.getCount();
        for (int i=0; i<mPageCount;i++) {
            RadioButton r = new RadioButton(mContext, null, R.style.bottom_radio_style);
            r.setBackgroundResource(R.drawable.bottom_bar_bg);
            r.setId(BOTTOM_RADIO_ID_OFFSET + i); 
            r.setClickable(true);
            /// 高亮第一个
            if (i == 0) {
                r.setChecked(true);
            } else {
                RadioGroup.LayoutParams params = new RadioGroup.LayoutParams(
                        LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                params.leftMargin = DisplayUtil.dip2px(24, DisplayUtil.getScale(mContext));
                r.setLayoutParams(params);
            }
            mBottomRadios.addView(r);
        }
    }
}
