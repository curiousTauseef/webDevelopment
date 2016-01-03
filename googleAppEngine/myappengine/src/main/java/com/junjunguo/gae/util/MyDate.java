package com.junjunguo.gae.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * This file is part of spring_hibernate_relation.
 * <p/>
 * Created by <a href="http://junjunguo.com">GuoJunjun</a> on 25/10/15.
 */
public class MyDate {
    /**
     * @param date Date
     * @return date string yyyy-MM-dd HH:mm:ss
     */
    public String getDateString(Date date) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss zzz");
        return sdf.format(date);
    }

    /**
     * @param dateInString format: yyyy-MM-dd
     * @param timeInString format: HH:mm:ss
     * @return Date object
     */
    public Date getDate(String dateInString, String timeInString) {
        if (timeInString.length() <= 2) {
            return getDate(dateInString + " 00:00:00");
        }
        return getDate(dateInString + " " + timeInString);
    }

    /**
     * @param dateInString format: yyyy-MM-dd HH:mm:ss
     * @return Date object
     */
    public Date getDate(String dateInString) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        try {
            Date date = sdf.parse(dateInString);
            System.out.println(date);
            System.out.println(sdf.format(date));
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
