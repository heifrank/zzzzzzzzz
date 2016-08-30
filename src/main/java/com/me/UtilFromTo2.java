package com.me;

import avro.shaded.com.google.common.collect.Lists;
import avro.shaded.com.google.common.collect.Maps;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yidian.cpp.common.mongo.GenericMongoDAO;
import com.yidian.cpp.common.mongo.MapDocConverter;
import com.yidian.cpp.common.util.json.JacksonMapper;
import com.yidian.cpp.common.util.json.JsonUtil;
import org.bson.Document;

import java.io.*;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by heifrank on 16/8/29.
 */
public class UtilFromTo2 {
    List<String> modules = null;
    String filename = null;
    ObjectMapper mapper = null;


    private final static GenericMongoDAO mongoDAO = new GenericMongoDAO("rs-crawler-1.yidian.com,rs-crawler-2.yidian.com,rs-crawler-3.yidian.com", "cpp");

    public UtilFromTo2(String filename, List<String> modules) {
        this.filename = filename;
        this.modules = modules;
        mapper = JacksonMapper.MAPPER.getMapper();
        mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
    }

    private Map<String, Object> funcFromTo(List<Map<String, Object>> lst) {
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

        Map<String, Object> ret = Maps.newHashMap();
        ret.put("opts", resDiff);
        ret.put("inputs", resSame);
        return ret;
    }

    private int calBrackets(String line) {
        int cnt = 0;
        for(int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == '{') {
                cnt++;
            } else if (line.charAt(i) == '}') {
                cnt--;
            }
        }
        return cnt;
    }

    private Map<String, Object> parseConfig() throws IOException {
        Map<String, Object> ret = Maps.newHashMap();
        Pattern pattern = Pattern.compile(".*\\[(.+)\\].*");
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        while(true) {
            String line = reader.readLine();
            if(line == null) {
                break;
            }
            Matcher matcher = pattern.matcher(line);
            if(matcher.find() && modules.contains(matcher.group(1))) {
                StringBuilder builder = new StringBuilder();
                int cnt = 0;
                while(true) {
                    String moduleLine = reader.readLine();
                    int tmpCnt = calBrackets(moduleLine);
                    builder.append(moduleLine);
                    if(cnt + tmpCnt == 0 && cnt != 0) {
                        break;
                    }
                    cnt += tmpCnt;
                }
                ret.put(matcher.group(1), builder.toString());
//                System.out.println(matcher.group(1));
            }
        }
        return ret;
    }

    private Map<String, Object> doWork() throws IOException {
        Map<String, Object> ret = Maps.newHashMap();
        Map<String, Object> parsedRes = parseConfig();
        for(Map.Entry<String, Object> entry : parsedRes.entrySet()) {
            String key = entry.getKey();
            Map<String, Object> value = mapper.readValue((String) entry.getValue(), JsonUtil.MAP_REFERENCE);

            List<Map<String, Object>> inputs = Lists.newArrayList();
            Map<String, Object> params = (Map<String, Object>) value.get("params");

            for(Object obj : params.values()) {
                inputs.addAll((Collection<? extends Map<String, Object>>) obj);
            }

            ret.put(key, funcFromTo(inputs));
        }
        return ret;
    }

    public static void main(String[] args) throws IOException {
        List<String> modules = Lists.newArrayList("add_display_serving_mongo", "add_online_serving_mongo", "add_online_mongo", "add_offline_mongo");
        String fileToParse = "/Users/heifrank/codes/output/post_writer.config";
        UtilFromTo2 utilFromTo2 = new UtilFromTo2(fileToParse, modules);

        Map<String, Object> fileParsedRes = utilFromTo2.doWork();
        for(Map.Entry<String, Object> entry : fileParsedRes.entrySet()) {
            System.out.println(entry.getKey() + "###" + entry.getValue());
        }

        Map<String, Object> mongoMap = Maps.newHashMap();
        for(String module : modules) {
            Map<String, Object> mongoRes = mongoDAO.findOne("component", new Document("_id", module), new MapDocConverter());
            mongoMap.put(module, mongoRes.get("input"));
        }

        System.out.println("####################################################################");
        for(String module : modules) {
            List<String> mongoInputs = (List<String>) mongoMap.get(module);
            List<String> configInputs = (List<String>) ((Map<String, Object>)(fileParsedRes.get(module))).get("inputs");
            System.out.println("Module: " + module);
            for(String field : configInputs) {
                if (!mongoInputs.contains(field)) {
                    System.out.println(field);
                }
            }
            System.out.println("####################################################################");
        }

    }
}
