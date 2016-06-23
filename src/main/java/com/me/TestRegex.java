package com.me;

import org.apache.commons.lang3.CharSet;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by heifrank on 16/4/22.
 */
public class TestRegex {
    public static void main(String[] args) throws IOException {
        System.out.println("宋洋".getBytes().length);
        byte[] ss = "宋洋".getBytes(Charset.forName("gbk"));
        System.out.println(ss.length);
        System.out.println(new String(ss, Charset.forName("UTF-8")));

        String file = "/Users/heifrank/codes/untitled.html";
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String s;
        StringBuilder builder = new StringBuilder();
        while((s = reader.readLine()) != null){
            builder.append(s);

        }
        Pattern pattern = Pattern.compile("<a\\s+href=\"([^<>]*)\"\\s*?>");
        System.out.println(builder.toString());
        Matcher matcher = pattern.matcher(builder.toString());
        while(matcher.find()){
            System.out.println(matcher.group(0));
        }
    }
}
