package com.me;

import avro.shaded.com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Map;

/**
 * Created by heifrank on 16/4/22.
 */
public class TestPath {
    private static final Logger logger = LoggerFactory.getLogger(TestPath.class);

    public static void main(String[] args){
        System.out.printf("ssd {}\n", "123");
        logger.error("ssd {}", 123);
//        String userdir = System.getProperty("user.dir");
//
//        String file = new File(userdir + "/" + args[0]).getAbsolutePath();
//        System.out.println(file);
        Map<String, Object> map = Maps.newHashMap();
        System.out.println((long)map.get("334"));
    }
}
