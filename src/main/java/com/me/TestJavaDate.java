package com.me;


import com.yidian.cpp.common.utils.DateUtils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * Created by heifrank on 16/5/17.
 */
public class TestJavaDate {
    public static void main(String[] args){

        String dateString = "2016-02-02 12:58:32";
        LocalDateTime date = DateUtils.fromString(dateString);

        long dateLong = date.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();

        System.out.println(DateUtils.fromUTS(dateLong));
        System.out.println(date);


        LocalDateTime now = LocalDateTime.of(1928, 2, 3, 10, 22);
        System.out.println(now.format(DateTimeFormatter.ofPattern("uuuu-MM-dd")));
    }
}
