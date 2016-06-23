package com.regex;

import org.junit.Test;

/**
 * Created by heifrank on 16/4/21.
 */
public class TestRegex {
    public static void main(String[] args){

    }

    @Test
    public void test(){
        String pattern = "(?i)\\d+\\s\\w+songyang\\w";
        String text = "234      8usongyangk";
        System.out.println(text.matches(text));
        System.out.println(text.matches(text + "o"));
    }

    @Test
    public void test_title(){
        String content = "[12/23]";
        System.out.println(content.matches("[\\[(]\\d{1,2}/\\d{1,2}[)\\]]"));
    }
}
