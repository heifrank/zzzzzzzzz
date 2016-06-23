package com.jacksonjson;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Map;

/**
 * Created by heifrank on 16/5/11.
 */
public class TestJackson {
    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        MyBean myBean = new MyBean(123);
//        myBean.setName(null);
        String ss = objectMapper.writeValueAsString(myBean);
        System.out.println(ss);

        MyBean mb = objectMapper.readValue(ss, MyBean.class);

        System.out.println(mb.getTheValue());

//        map.keySet().stream().forEach(key -> System.out.println(String.format("key: %s, value: %s", key, map.get(key))));


//        String myBeanStr = "{\"theValue\":334}";
//        MyBean myBean1 = objectMapper.readValue(myBeanStr, MyBean.class);
//        System.out.println(myBean1);

    }
}


@JsonInclude(JsonInclude.Include.NON_EMPTY)
class MyBean{
    @JsonProperty("theValue")
    private int value;

    @JsonGetter("man")
    public String getTheName() {
        return myName;
    }

    @JsonSetter("man")
    public void setTTTTN(String name){
        this.myName = name;
    }

//    public void setMyName(String myName) {
//        this.myName = myName;
//    }

    public int getTheValue(){
        return this.value;
    }

    private String myName = "songyang";

    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("value:" + value);
        builder.append("myName:" + myName);
        return builder.toString();
    }

    public MyBean(){

    }

    public MyBean(int v)
    {
        value = v;
    }
}
