/* Cking Inc. (C) 2012. All rights reserved.
 *
 * CalendarText.java
 * classes : com.cking.phss.widget.CalendarText
 * @author Administrator
 * V 1.0.0
 * Create at 2012-9-16 下午12:56:59
 */
package com.cking.phss.widget;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.cking.phss.R;

/**
 * com.cking.phss.widget.CalendarText
 * @author Administrator <br/>
 * create at 2012-9-16 下午12:56:59
 */
public class CalendarText extends TextView {
    @SuppressWarnings("unused")
    private static final String TAG = "CalendarText";
    private Context mContext = null;
    // date and time
    private int mYear;
    private int mMonth;
    private int mDay;
    private boolean allowEmpty = false;
    
    public interface OnCalendarChangedListener {
        public void onCalendarChanged(int year, int month, int day);
    }
    
    private OnCalendarChangedListener mOnCalendarChangedListener;
    
    public void setOnCalendarChangedListener(OnCalendarChangedListener listener) {
        mOnCalendarChangedListener = listener;
    }
    
    /**
     * @param context
     */
    public CalendarText(Context context) {
        super(context);
        
        init(context);
    }
    
    public CalendarText(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.Widget);
        allowEmpty = a.getBoolean(R.styleable.Widget_allowEmpty, false);
        a.recycle();
        
        init(context);
    }

    /**
     * @param context
     */
    private void init(Context context) {
        mContext = context;
        setDate();
        updateDisplay();
        
        setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                showCalenderDialog();
            }
        });
    }
    
    private void setDate() {

        Calendar c = Calendar.getInstance();
        if (getText() == null || getText().length() < 5) {
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);
        } else {
            if (!allowEmpty) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    String strDate = getText().toString();
                    Log.e(TAG, "setDate, getText: " + getText().toString());
                    Date date = format.parse(strDate);

                    mYear = date.getYear() + 1900;
                    mMonth = date.getMonth();
                    mDay = date.getDate();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void updateDisplay() {
        DecimalFormat format = new DecimalFormat("00");
        setText(
            new StringBuilder()
                    // Month is 0 based so add 1
                    .append(mYear).append("-")
                    .append(format.format(mMonth + 1)).append("-")
                    .append(format.format(mDay)));
        if (mOnCalendarChangedListener != null) {
            mOnCalendarChangedListener.onCalendarChanged(mYear, mMonth, mDay);
        }
    }
    
    private void showCalenderDialog() {
        setDate();
        DatePickerDialog datePicker = new DatePickerDialog(mContext, mDateSetListener, mYear, mMonth, mDay);
        datePicker.show();
    }

    private DatePickerDialog.OnDateSetListener mDateSetListener =
            new DatePickerDialog.OnDateSetListener() {

                public void onDateSet(DatePicker view, int year, int monthOfYear,
                        int dayOfMonth) {
                    mYear = year;
                    mMonth = monthOfYear;
                    mDay = dayOfMonth;
                    updateDisplay();
                }
            };

    private static String pad(int c) {
        if (c >= 10)
            return String.valueOf(c);
        else
            return "0" + String.valueOf(c);
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        if (text == null || text.equals("")) {
            if (!allowEmpty) {
                Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);
                DecimalFormat format = new DecimalFormat("00");
                text = new StringBuilder()
                        // Month is 0 based so add 1
                        .append(mYear).append("-").append(format.format(mMonth + 1)).append("-")
                        .append(format.format(mDay));
            }
        }
        super.setText(text, type);
    }

    /**
     * @param nextWeek
     */
    public void setDate(Date date) {
        setText(new SimpleDateFormat("yyyy-MM-dd").format(date));
    }
}
