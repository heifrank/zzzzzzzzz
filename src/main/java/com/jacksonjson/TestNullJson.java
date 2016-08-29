package com.jacksonjson;

import com.yidian.cpp.common.util.json.JsonUtil;

import java.util.Map;

/**
 * Created by heifrank on 16/8/8.
 */
public class TestNullJson {
    public static void main(String[] args){
        String s = "";
        Map<String, Object> map = JsonUtil.readAsMap(s);
        System.out.println(map.get("data"));
        return ;
    }
}
