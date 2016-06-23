package com.jodatime;

/**
 * Created by heifrank on 16/6/7.
 */


import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;

/**
 * @author weijian
 *         Date : 2013-04-15 18:03
 */
public final class DateUtils {

    private static final DateTimeFormatter DATE_TIME_FORMATTER
            = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");

    private DateUtils() {
        throw new AssertionError();
    }

    public static long currentTime(){
        return System.currentTimeMillis() / 1000;
    }

    public static String currentDate(){
        return DATE_TIME_FORMATTER.print(DateTime.now());
    }

    public static DateTime parse(String dateStr){
        if ( dateStr == null ) return null;

        return DATE_TIME_FORMATTER.parseDateTime(dateStr);
    }

    public static String format(DateTime date){
        if ( date == null ) return null;
        return DATE_TIME_FORMATTER.print(date);
    }

    public static String format(long date){
        return  DATE_TIME_FORMATTER.print(date);
    }

    public static String format(Date date){
        return date == null
                ? null
                : format(new DateTime(date));
    }

    public static String format(Long date){
        return date == null
                ? null
                : format(date.longValue());
    }

    public static Date parseDate(String dateStr){
        if ( dateStr == null ) return null;

        return parse(dateStr).toDate();
    }

    public static void main(String[] args){
        String dt = "2016-02-24 12:23:33";
        DateTime dateTime = DateUtils.parse(dt);
        System.out.println(dateTime.getMillis());

        System.out.println(com.yidian.commons.base.DateUtils.parseToMilli(dt));
    }
}
