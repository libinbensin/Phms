package com.adinnet.xd.medical.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RelativeLayout;

public class RelativeGroup extends RelativeLayout {
    private int mCheckedId = -1;
    private CompoundButton.OnCheckedChangeListener mChildOnCheckedChangeListener;
    private OnCheckedChangeListener mOnCheckedChangeListener;
    private PassThroughHierarchyChangeListener mPassThroughListener;
    private boolean mProtectFromCheckedChange = false;

    public RelativeGroup(Context paramContext) {
        super(paramContext);
        init();
    }

    public RelativeGroup(Context paramContext, AttributeSet paramAttributeSet) {
        super(paramContext, paramAttributeSet);
        init();
    }

    private void init() {
        this.mChildOnCheckedChangeListener = new CheckedStateTracker();
        this.mPassThroughListener = new PassThroughHierarchyChangeListener();
        super.setOnHierarchyChangeListener(this.mPassThroughListener);
    }

    private void setCheckedId(int paramInt) {
        this.mCheckedId = paramInt;
        if (this.mOnCheckedChangeListener != null)
            this.mOnCheckedChangeListener.onCheckedChanged(this, this.mCheckedId);
    }

    private void setCheckedStateForView(int paramInt, boolean paramBoolean) {
        View localView = findViewById(paramInt);
        if ((localView != null) && ((localView instanceof RadioButton)))
            ((RadioButton) localView).setChecked(paramBoolean);
    }

    public void addView(View paramView, int paramInt, ViewGroup.LayoutParams paramLayoutParams) {
        if ((paramView instanceof RadioButton)) {
            RadioButton localRadioButton = (RadioButton) paramView;
            if (localRadioButton.isChecked()) {
                this.mProtectFromCheckedChange = true;
                if (this.mCheckedId != -1)
                    setCheckedStateForView(this.mCheckedId, false);
                this.mProtectFromCheckedChange = false;
                setCheckedId(localRadioButton.getId());
            }
        }
        super.addView(paramView, paramInt, paramLayoutParams);
    }

    public void check(int paramInt) {
        // if ((paramInt != -1) && (paramInt == this.mCheckedId));
        // while (true)
        // {
        // return;
        if (this.mCheckedId != -1)
            setCheckedStateForView(this.mCheckedId, false);
        if (paramInt != -1) {
            setCheckedStateForView(paramInt, true);
            setCheckedId(paramInt);
        }
        // }
    }

    protected boolean checkLayoutParams(ViewGroup.LayoutParams paramLayoutParams) {
        return paramLayoutParams instanceof LayoutParams;
    }

    public void clearCheck() {
        check(-1);
    }

    protected RelativeLayout.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-2, -2);
    }

    public LayoutParams generateLayoutParams(AttributeSet paramAttributeSet) {
        return new LayoutParams(getContext(), paramAttributeSet);
    }

    public int getCheckedRadioButtonId() {
        return this.mCheckedId;
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        if (this.mCheckedId != -1) {
            this.mProtectFromCheckedChange = true;
            setCheckedStateForView(this.mCheckedId, true);
            this.mProtectFromCheckedChange = false;
            setCheckedId(this.mCheckedId);
        }
    }

    public void setOnCheckedChangeListener(OnCheckedChangeListener paramOnCheckedChangeListener) {
        this.mOnCheckedChangeListener = paramOnCheckedChangeListener;
    }

    public void setOnHierarchyChangeListener(
            ViewGroup.OnHierarchyChangeListener paramOnHierarchyChangeListener) {
        this.mPassThroughListener.mOnHierarchyChangeListener = paramOnHierarchyChangeListener;
    }

    private class CheckedStateTracker implements CompoundButton.OnCheckedChangeListener {
        private CheckedStateTracker() {
        }

        public void onCheckedChanged(CompoundButton paramCompoundButton, boolean paramBoolean) {
            if (RelativeGroup.this.mProtectFromCheckedChange) {
                if (RelativeGroup.this.mCheckedId != -1) {
                    RelativeGroup.this.setCheckedStateForView(RelativeGroup.this.mCheckedId, false);
                    RelativeGroup.this.mProtectFromCheckedChange = true;
                } else {
                    RelativeGroup.this.mProtectFromCheckedChange = false;
                    int i = paramCompoundButton.getId();
                    RelativeGroup.this.setCheckedId(i);
                }
            }
        }
    }

    // public static class LayoutParams extends RelativeLayout.LayoutParams
    // {
    // public LayoutParams(int paramInt1, android.view.ViewGroup.LayoutParams
    // paramInt2)
    // {
    // super(paramInt2);
    // }
    //
    // /**
    // * @param paramInt1
    // * @param i
    // */
    // public LayoutParams(int paramInt1, int i) {
    // }
    //
    // /**
    // * @param context
    // * @param paramAttributeSet
    // */
    // public LayoutParams(Context context, AttributeSet paramAttributeSet) {
    // }
    //
    // protected void setBaseAttributes(TypedArray paramTypedArray, int
    // paramInt1, int paramInt2)
    // {
    // if (paramTypedArray.hasValue(paramInt1))
    // {
    // this.width = paramTypedArray.getLayoutDimension(paramInt1,
    // "layout_width");
    // if (!paramTypedArray.hasValue(paramInt2)) {
    // this.width = -2;
    // return;
    // }
    // this.height = paramTypedArray.getLayoutDimension(paramInt2,
    // "layout_height");
    // }
    // }
    // }

    public static abstract interface OnCheckedChangeListener {
        public abstract void onCheckedChanged(RelativeGroup paramRelativeGroup, int paramInt);
    }

    private class PassThroughHierarchyChangeListener implements ViewGroup.OnHierarchyChangeListener {
        private ViewGroup.OnHierarchyChangeListener mOnHierarchyChangeListener;

        private PassThroughHierarchyChangeListener() {
        }

        public void onChildViewAdded(View paramView1, View paramView2) {
            if ((paramView1 == RelativeGroup.this) && ((paramView2 instanceof RadioButton))) {
                if (paramView2.getId() == -1)
                    paramView2.setId(paramView2.hashCode());
                ((RadioButton) paramView2)
                        .setOnCheckedChangeListener(RelativeGroup.this.mChildOnCheckedChangeListener);
            }
            if (this.mOnHierarchyChangeListener != null)
                this.mOnHierarchyChangeListener.onChildViewAdded(paramView1, paramView2);
        }

        public void onChildViewRemoved(View paramView1, View paramView2) {
            if ((paramView1 == RelativeGroup.this) && ((paramView2 instanceof RadioButton)))
                ((RadioButton) paramView2).setOnCheckedChangeListener(null);
            if (this.mOnHierarchyChangeListener != null)
                this.mOnHierarchyChangeListener.onChildViewRemoved(paramView1, paramView2);
        }
    }
}