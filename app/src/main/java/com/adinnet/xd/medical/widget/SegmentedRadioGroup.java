package com.adinnet.xd.medical.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RadioGroup;

import com.cking.phss.R;

public class SegmentedRadioGroup extends RadioGroup {
    public SegmentedRadioGroup(Context paramContext) {
        super(paramContext);
    }

    public SegmentedRadioGroup(Context paramContext, AttributeSet paramAttributeSet) {
        super(paramContext, paramAttributeSet);
    }

    private void changeButtonsImages() {
        int count = super.getChildCount();
        if (count <= 1) {
            return;
        }

        super.getChildAt(0).setBackgroundResource(R.drawable.segment_radio_left);
        super.getChildAt(count - 1).setBackgroundResource(R.drawable.segment_radio_right);
        for (int i = 1; i < count - 1; i++) {
            super.getChildAt(i).setBackgroundResource(R.drawable.segment_radio_middle);
        }
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        changeButtonsImages();
    }
}