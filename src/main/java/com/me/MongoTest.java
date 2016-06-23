package com.me;

import avro.shaded.com.google.common.collect.Maps;
import com.mongodb.Block;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.model.Filters;
import com.yidian.commons.base.Strings;
import org.apache.log4j.net.SyslogAppender;
import org.bson.Document;
import org.junit.Test;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

import java.util.*;
import java.util.concurrent.Executors;
import java.util.function.*;

/**
 * Created by heifrank on 16/4/6.
 */
public class MongoTest {
    @Test
    public void test_document(){
        Map<String, Object> map = Maps.newHashMap();
        map.put("ssd", 123);
        map.put("ssd2", new Document("ff", 123));

        Document document = new Document(map);
        document.remove("ssdss2");

        map.keySet().stream().forEach(key -> System.out.println("key: " + key + ", value: " + map.get(key)));
        System.out.println("songyang");
        document.keySet().forEach(key -> System.out.println("key: " + key + ", value: " + document.get(key)));
    }

    @Test
    public void test_connect(){
        MongoClient mongoClient = new MongoClient(Arrays.asList(new ServerAddress("rs-crawler-1.yidian.com", 27017),
                                                                new ServerAddress("rs-crawler-2.yidian.com", 27017),
                                                                new ServerAddress("rs-crawler-3.yidian.com", 27017)));
        MongoDatabase db = mongoClient.getDatabase("cpp");
        FindIterable<Document> iterable = db.getCollection("component_test").find();
        iterable.forEach((Block<Document>) (doc -> System.out.println("songyang: " + ((Document)doc.get("ctype_mapping")).get("audio"))));


        List<String> lst = new ArrayList<>();
    }

    @Test
    public void testMap(){
        Map<String, String> mp = new HashMap<>();
        System.out.println(mp.get("Songyang"));
    }

    private int ss;

    public boolean isSsd() {
        return ssd;
    }

    public void setSsd(boolean ssd) {
        this.ssd = ssd;
    }

    private boolean ssd;

    public int getSs() {
        return ss;
    }

    public void setSs(int ss) {
        this.ss = ss;
    }
}
