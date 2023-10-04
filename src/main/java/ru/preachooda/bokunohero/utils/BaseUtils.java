package ru.preachooda.bokunohero.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class BaseUtils {

    public static String DATE_DEFAULT_FORMAT = "yyyy-MM-dd";
    public static String DATE_TIME_DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static Long convertToLong(String s) {
        if (s != null && !s.isEmpty()) {
            try {
                return Long.valueOf(s);
            } catch (NumberFormatException e) {
                return null;
            }
        }

        return null;
    }

    public static String formatDate(Date date, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return date != null ? simpleDateFormat.format(date) : null;
    }

    public static String formatDate(Date date) {
        return formatDate(date, DATE_DEFAULT_FORMAT);
    }

    public static String formatDateTime(Date date) {
        return formatDate(date, DATE_TIME_DEFAULT_FORMAT);
    }

    public static Date parseDate(String s, String format) {
        Date date = null;

        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
            simpleDateFormat.setTimeZone(TimeZone.getDefault());
            date = simpleDateFormat.parse(s);
        } catch (ParseException ignored) {

        }

        return date;
    }

    public static Date parseDate(String s) {
        return parseDate(s, DATE_DEFAULT_FORMAT);
    }

    public static Date parseDateTime(String s) {
        return parseDate(s, DATE_TIME_DEFAULT_FORMAT);
    }

}
