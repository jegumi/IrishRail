package com.jegumi.irishrail.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import android.text.TextUtils;

public class Utils {

    public static Calendar stringToTimeCalendar(String strDate) {
        if (TextUtils.isEmpty(strDate)) {
            return Calendar.getInstance();
        }
        SimpleDateFormat form = new SimpleDateFormat("HH:mm", Locale.getDefault());
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(form.parse(strDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return calendar;
    }

    public static String getCurrentDate() {
        Calendar calendar = Calendar.getInstance(Locale.ENGLISH);

        String strdate = null;
        SimpleDateFormat sdf = (SimpleDateFormat) DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.ENGLISH);

        if (calendar != null) {
            strdate = sdf.format(calendar.getTime());
        }
        return strdate;
    }
}

