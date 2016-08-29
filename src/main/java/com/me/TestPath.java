package com.me;

import avro.shaded.com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * Created by heifrank on 16/4/22.
 */
public class TestPath {
    private static final Logger logger = LoggerFactory.getLogger(TestPath.class);

    public static void main(String[] args){

        TempClass t = new TempClass();
        System.out.println(t.get());
        t.set("44");

        System.out.println(t.get());
        if(t.getSS() == null){
            System.out.println("empty!");
        }
    }
}

class TempClass{
    String a;

    List<String> ss;

    public String get(){
        return a;
    }

    public void set(String aa){
        a = aa;
    }

    public List<String> getSS(){
        return ss;
    }
}




