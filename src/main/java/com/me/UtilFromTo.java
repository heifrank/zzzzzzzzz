package com.me;

import avro.shaded.com.google.common.collect.Lists;
import avro.shaded.com.google.common.collect.Maps;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yidian.cpp.common.util.json.JacksonMapper;
import com.yidian.cpp.common.util.json.JsonUtil;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by heifrank on 16/5/19.
 */
public class UtilFromTo {
    private static void doWork(String inName) throws IOException {
        System.out.println("inName: " + inName);
        File inf = new File(inName);
//        ObjectMapper mapper = JacksonMapper.MAPPER.getMapper();
//        mapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
        List<Object> lst = JacksonMapper.MAPPER.getMapper().readValue(inf, new TypeReference<List<Object>>(){});
        List<String> resSame = Lists.newArrayList();
        Map<String, Object> resDiff = Maps.newHashMap();
        for(Object obj : lst){
            Map<String, Object> map = (Map<String, Object>)obj;
            Map<String, String> tmp = Maps.newHashMap();
            tmp.put("rename", (String) map.get("to"));

            resSame.add((String) map.get("from"));

            if(!map.get("from").equals(map.get("to"))){
                resDiff.put((String) map.get("from"), tmp);
            }
        }
        System.out.println(JacksonMapper.MAPPER.getMapper().writeValueAsString(resSame));
        System.out.println(JacksonMapper.MAPPER.getMapper().writeValueAsString(resDiff));
        System.out.println("size is " + lst.size());
        System.out.println("==============================================");
    }

    public static void main(String[] args) throws IOException {
        doWork("/Users/heifrank/codes/output/add_display_serving_mongo");
        doWork("/Users/heifrank/codes/output/add_online_serving_mongo");
        doWork("/Users/heifrank/codes/output/add_online_mongo");
        doWork("/Users/heifrank/codes/output/add_offline_mongo");

    }
}
