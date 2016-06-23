package com.me;

import avro.shaded.com.google.common.collect.Sets;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yidian.cpp.common.util.json.JacksonMapper;

import java.io.*;
import java.util.Map;
import java.util.Set;

/**
 * Created by heifrank on 16/6/21.
 */
public class FindFields {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(FindFields.class.getResource("/ad.dump").getPath()));
        String line;
        ObjectMapper mapper = new ObjectMapper();

        Set<String> fields = Sets.newHashSet();
        int cnt = 0;
        while((line = reader.readLine()) != null){
            Map<String, Object> map = mapper.readValue(line, new TypeReference<Map<String, Object>>(){});
            map.keySet().forEach(key -> fields.add(key));
            cnt++;
        }

        System.out.println("total lines: " + cnt);
        for(String key : fields){
            System.out.println(key);
        }
    }
}
