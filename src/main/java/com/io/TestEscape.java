package com.io;

import avro.shaded.com.google.common.collect.Maps;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

/**
 * Created by heifrank on 16/7/28.
 */
public class TestEscape {
    public static void main(String[] args) throws IOException {
        String path = "/Users/heifrank/codes/tmp/songyang_test6/version-1.avdl";
        BufferedReader reader = new BufferedReader(new FileReader(path));
        StringBuilder builder = new StringBuilder();
        String tmp;
        while((tmp = reader.readLine()) != null){
            builder.append(tmp + "\n");
        }

        ObjectMapper mapper = new ObjectMapper();

        String out = builder.toString();

        Map<String, Object> map = Maps.newHashMap();
        map.put("id", out);

        System.out.println(mapper.writeValueAsString(out));

        System.out.println("****************");

        System.out.println(out.replace("\"", "\\\""));
        return ;
    }
}
