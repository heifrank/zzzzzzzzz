package com.regex;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by heifrank on 16/4/21.
 */
public class TestRegex implements Runnable{
    private static final Logger logger = LoggerFactory.getLogger(TestRegex.class);

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

    @Override
    public void run() {
        int cnt = 0;
        while(true){
            try {
                Thread.sleep(3000);
                logger.debug("running in TestRegex." + ++cnt);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
