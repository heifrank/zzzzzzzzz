package com.me;

import avro.shaded.com.google.common.collect.Lists;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by heifrank on 16/4/26.
 */
public class TestJackson2 {

    public static void main(String[] args) throws IOException {
        test();
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> ret = mapper.readValue(new File(TestJackson2.class.getResource("/StudentActivity.json").getPath()), new TypeReference< Map<String, Object> >() {});
        Object tmp = ret.get("married");
        if(tmp != null && ((boolean)tmp == true)){

            System.out.println(ret.get("married"));
        }else{
            System.out.println("no");
        }
    }

    public static void test(){
        List<String> list = Lists.newArrayList();
        list.add("123");
        list.add("456");
        System.out.println(String.format("%s", list));
        System.out.println(list);
    }
}
