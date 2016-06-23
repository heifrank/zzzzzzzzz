package com.jacksonjson;

import avro.shaded.com.google.common.collect.Maps;
import com.fasterxml.jackson.databind.JsonNode;
import com.yidian.cpp.common.util.json.JacksonMapper;
import com.yidian.cpp.common.util.json.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * Created by heifrank on 16/5/26.
 */
public class TestAvroJson {

    private static Logger logger = LoggerFactory.getLogger(TestAvroJson.class);
    public static void main(String[] args) throws IOException {
////        String path = "/Users/heifrank/codes/see.json";
        String path = "/Users/heifrank/codes/see2.json";
        String map = JacksonMapper.MAPPER.getMapper().readValue(new File(path), String.class);
        logger.error(map);

//        Map<String, Object> mp = Maps.newHashMap();
//        mp.put("aa", 123);
//        System.out.println(JacksonMapper.MAPPER.getMapper().writeValueAsString(mp));
    }
}
