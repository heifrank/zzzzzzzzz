package com.me;

import avro.shaded.com.google.common.collect.Maps;
import com.google.common.collect.Lists;
import junit.framework.TestCase;

import java.util.List;
import java.util.Map;

/**
 * Created by heifrank on 16/4/13.
 */

class Tmp{
    private String name;

    public Tmp(String name){
        this.name = name;
    }

    public void say(){
        System.out.println("This is " + this.name);
    }
}

public class TestCollections extends TestCase{
    public void test_map(){
        Map<String, Object> map = Maps.newHashMap();
        map.put("sd", new Tmp("songyang"));
        Object mm = map.get("sd");
        mm = new Tmp("liuzi");
        ((Tmp)map.get("sd")).say();
    }

//    public void test_stream(){
//        List<String> lst = Lists.newArrayList("1", "2", "3");
//        lst.stream().filter(s -> s.contains("1") || s.contains("3"))
//                    .map(Tmp::toMe)
//                    .forEach(s -> System.out.println(s));
//
//    }

    public void testSize(){
        List<String> lst = Lists.newArrayListWithCapacity(10);
        System.out.println(lst.size());
    }

    public void testSplit(){
        String s = "10.111.0.170:27017";
        String[] res = s.split(",");
        for(String ss : res){
            System.out.println("songyang: " + ss);
        }
    }
}
