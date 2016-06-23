package com.config;

import avro.shaded.com.google.common.collect.Maps;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import java.util.Map;

/**
 * Created by heifrank on 16/5/24.
 */
public class TestTypesafeConfig {
    public static void main(String[] args){
        Map<String, Object> map = Maps.newHashMap();
        map.put("name", "songyang");
        map.put("ss", null);

        Config config = ConfigFactory.parseMap(map);
        System.out.println(config.getObject("ss"));
        System.out.println(config.getString("name"));
    }

}
