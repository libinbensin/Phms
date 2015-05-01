/* Cking Inc. (C) 2012. All rights reserved.
 *
 * AddressText.java
 * classes : com.cking.phss.widget.AddressText
 * @author Administrator
 * V 1.0.0
 * Create at 2012-9-16 下午12:56:59
 */
package com.cking.phss.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.cking.phss.R;
import com.cking.phss.util.TispToastFactory;

/**
 * com.cking.phss.widget.AddressText
 * 
 * @author Administrator <br/>
 *         create at 2012-9-16 下午12:56:59
 */
public class SetDatespanDialog extends Dialog {
    @SuppressWarnings("unused")
    private static final String TAG = "SetDatespanDialog";
    
    private Context mContext = null;
    private Toast mToast = null;
    
    ImageView closeImageView;
    CalendarText ksrqCalendarText;
    CalendarText jsrqCalendarText;
    Button okButton;
    Button cancelButton;

    public interface OnResultListener {
        public void onConfirm(String startDate, String stopDate);
        public void onCancel();
    }
    
    public OnResultListener onResultListener = null;
    
    public void setOnResultListener(OnResultListener listener) {
        onResultListener = listener;
    }
    
    /**
     * @param context
     */
    public SetDatespanDialog(Context context) {
        super(context, R.style.dialog);

        init(context);
    }

    /**
     * @param context
     */
    private void init(Context context) {
        mContext = context;
        mToast = TispToastFactory.getToast(context, "");
        setCancelable(true);
        setContentView(R.layout.dialog_set_datespan_layout);

        loadPage(context);
    }

    public void loadPage(Context context) {
        ksrqCalendarText = (CalendarText) findViewById(R.id.ksrqCalendarText);
        jsrqCalendarText = (CalendarText) findViewById(R.id.jsrqCalendarText);
        okButton = (Button) findViewById(R.id.okButton);
        cancelButton = (Button) findViewById(R.id.cancelButton);
        closeImageView = (ImageView) findViewById(R.id.closeImageView);
        closeImageView.setOnClickListener(new android.view.View.OnClickListener() {
            
            @Override
            public void onClick(View arg0) {
                dismiss();//隐藏对话框 
                if (onResultListener != null) {
                    onResultListener.onCancel();
                }
            }
        });
        okButton.setOnClickListener(new android.view.View.OnClickListener() {
            
            @Override
            public void onClick(View arg0) {
                dismiss();//隐藏对话框 
                if (onResultListener != null) {
                    onResultListener.onConfirm(ksrqCalendarText.getText().toString(),
                            jsrqCalendarText.getText().toString());
                }
            }
        });
        cancelButton.setOnClickListener(new android.view.View.OnClickListener() {
            
            @Override
            public void onClick(View arg0) {
                dismiss();//隐藏对话框 
                if (onResultListener != null) {
                    onResultListener.onCancel();
                }
            }
        });
    }
}
