/* Xinhuaxing Inc. (C) 2014. All rights reserved.
 *
 * Calendar.java
 * classes : com.cking.phss.util.Calendar
 * @author Administrator
 * V 1.0.0
 * Create at 2014-6-16 下午12:04:49
 */
package com.cking.phss.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * com.cking.phss.util.CalendarUtil
 * @author Administrator <br/>
 * create at 2014-6-16 下午12:04:49
 */
public class CalendarUtil {
    private static final String TAG = "CalendarUtil";

    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    private static SimpleDateFormat _format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    public static Date parseDate(String dateString) {
        Date date = null;
        SimpleDateFormat[] formats = new SimpleDateFormat[] { format, _format };
        for (SimpleDateFormat fm : formats) {
            try {
                date = fm.parse(dateString);
                break;
            } catch (ParseException e) {
            }
        }
        if (date == null) {
            date = new Date();
        }
        return date;
    }
    
    public static int getAge(int year, int month, int day) {
        Calendar c = Calendar.getInstance();
        int nowYear = c.get(Calendar.YEAR);
        int age = (nowYear - year);
        return age;
    }

    public static int getAge(String strDate) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date date = format.parse(strDate);
            SimpleDateFormat format2 = new SimpleDateFormat("yyyy");
            int birthdayYear = Integer.parseInt(format2.format(date));
            Calendar c = Calendar.getInstance();
            int nowYear = c.get(Calendar.YEAR);
            int age = (nowYear - birthdayYear);
            return age;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        return 0;
    }

    public static Date getMonthStart(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, 1);
        return calendar.getTime();
    }

    public static Date getMonthEnd(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DATE, 1);
        calendar.add(Calendar.DATE, -1);
        return calendar.getTime();
    }

    public static Date getNextDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 1);
        return calendar.getTime();
    }

    public static Date getPreviousDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, -1);
        return calendar.getTime();
    }

    public static Date getNextWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, +7);
        return calendar.getTime();
    }

    public static Date getPreviousWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, -7);
        return calendar.getTime();
    }

    public static Date getNextMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 1);
        return calendar.getTime();
    }

    public static Date getPreviousMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, -1);
        return calendar.getTime();
    }
}
