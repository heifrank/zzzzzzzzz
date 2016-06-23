package com.me;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Test;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.function.Predicate;

/**
 * Created by heifrank on 16/3/11.
 */
public class TestJson {

    @Test
    public void test(){
        JSONParser parser = new JSONParser();
        try{
            Object obj = parser.parse(new FileReader("/Users/heifrank/codes/test.json"));
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("name", "songyang");
            map.put("family", new ArrayList<String>(Arrays.asList(new String[]{"ss", "ss2"})));

            JSONObject jj = new JSONObject(map);


            System.out.println(((List)jj.get("family")).iterator().next());

            JSONObject jo = (JSONObject)obj;

//            String name = (String)jo.get("name");
//            long age = (Long)jo.get("age");
//            JSONArray arr = (JSONArray)jo.get("messages");
//            System.out.println("name:" + name);
//            System.out.println("age:" + age);
//            Iterator<Object> it = arr.iterator();
//            while(it.hasNext()){
//                System.out.println(it.next());
//            }
            System.out.println(((JSONArray)jo.get("messages")).iterator().next());

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private static void show(Predicate<String> pred){
        if(pred.test("songyan")){
            System.out.println("Correct");
        }else{
            System.out.println("Not Correct");
        }
    }

    public static void main(String[] args){
        show(s -> s.equals("songyang"));
        if(true)return;

        JSONObject jo = new JSONObject();

        jo.put("name", "songyang");
        jo.put("age", 100);

        JSONArray arr = new JSONArray();
        arr.add("msg 1");
        arr.add("msg 2");
        arr.add(33);

        jo.put("messages", arr);

        try{
            FileWriter wr = new FileWriter("/Users/heifrank/codes/test.json");
            wr.write(jo.toJSONString());
            System.out.println(jo.toString());
            wr.flush();
            wr.close();
        }catch(IOException e){
            e.printStackTrace();
        }


    }
}
