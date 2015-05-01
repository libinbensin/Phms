/* Cking Inc. (C) 2013. All rights reserved.
 *
 * DatePickerDialog.java
 * classes : com.dialog.activity.DatePickerDialog
 * @author xiasiming
 * V 1.0.0
 * Create at 2013-1-29 涓嬪崍4:52:21
 */
package com.cking.phss.util;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;

import com.cking.phss.R;

/**
 * com.dialog.activity.DatePickerDialog
 * 
 * @author xiasiming <br/>
 *         create at 2013-1-29 涓嬪崍4:52:21
 */

public class DatePickerDialog extends Dialog implements OnDateChangedListener {

    private Context context;
    private Button apply, cancel;
    private DatePicker startDatePicker, endDatePicker;

    private int start_year;
    private int start_monthOfYear;
    private int start_dayOfMonth;
    private int end_year;
    private int end_monthOfYear;
    private int end_dayOfMonth;

    public DatePickerDialog(Context context, int start_year, int start_monthOfYear,
            int start_dayOfMonth, int end_year, int end_monthOfYear, int end_dayOfMonth) {
        super(context);
        this.context = context;
        this.start_year = start_year;
        this.start_monthOfYear = start_monthOfYear;
        this.start_dayOfMonth = start_dayOfMonth;
        this.end_year = end_year;
        this.end_monthOfYear = end_monthOfYear;
        this.end_dayOfMonth = end_dayOfMonth;

        init();
    }

    public DatePickerDialog(Context context, int theme, int start_year, int start_monthOfYear,
            int start_dayOfMonth, int end_year, int end_monthOfYear, int end_dayOfMonth) {
        super(context);
        this.context = context;
        this.start_year = start_year;
        this.start_monthOfYear = start_monthOfYear;
        this.start_dayOfMonth = start_dayOfMonth;
        this.end_year = end_year;
        this.end_monthOfYear = end_monthOfYear;
        this.end_dayOfMonth = end_dayOfMonth;
        init();
    }

    private void init() {
        // this.setCanceledOnTouchOutside(true);
        this.setCancelable(true);
        this.setTitle("日期选择器");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ipo_dialog_date_picker);
        initViews();
        initValues();
    }

    private void initViews() {
        apply = (Button) findViewById(R.id.apply);
        apply.setOnClickListener(clickListener);
        cancel = (Button) findViewById(R.id.cancel);
        cancel.setOnClickListener(clickListener);
        startDatePicker = (DatePicker) findViewById(R.id.startDatePicker);
        endDatePicker = (DatePicker) findViewById(R.id.endDatePicker);
    }

    private void initValues() {
        startDatePicker.init(start_year, start_monthOfYear, start_dayOfMonth, this);
        endDatePicker.init(end_year, end_monthOfYear, end_dayOfMonth, this);
    }

    private Button.OnClickListener clickListener = new Button.OnClickListener() {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            switch (v.getId()) {
                case R.id.apply:
                    if (onDateSetListener != null) {
                        onDateSetListener.onDateSet(start_year, start_monthOfYear,
                                start_dayOfMonth, end_year, end_monthOfYear, end_dayOfMonth);
                    }
                    dismiss();
                    break;
                case R.id.cancel:
                    dismiss();
                    break;

                default:
                    break;
            }
        }
    };

    private OnDateSetListener onDateSetListener;

    public interface OnDateSetListener {
        void onDateSet(int start_year, int start_monthOfYear, int start_dayOfMonth, int end_year,
                int end_monthOfYear, int end_dayOfMonth);
    }

    public void setOnDateSetListener(OnDateSetListener onDateSetListener) {
        this.onDateSetListener = onDateSetListener;
    }

    @Override
    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        // TODO Auto-generated method stub
        if (view == startDatePicker) {
            this.start_year = year;
            this.start_monthOfYear = monthOfYear;
            this.start_dayOfMonth = dayOfMonth;
        } else if (view == endDatePicker) {
            this.end_year = year;
            this.end_monthOfYear = monthOfYear;
            this.end_dayOfMonth = dayOfMonth;
        }
    }

}
