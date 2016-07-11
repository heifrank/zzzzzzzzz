package com.mongo;

import avro.shaded.com.google.common.collect.Lists;
import avro.shaded.com.google.common.collect.Maps;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;
import com.yidian.commons.morphia.utils.MongoUtils;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;

/**
 * Created by heifrank on 16/4/14.
 */
public class CopyComponentInputOutput {
    Logger logger = LoggerFactory.getLogger(CopyComponentInputOutput.class);

    MongoClient mongoClient = null;

    MongoDatabase mongoDatabase = null;

    String colls = null;

    String collsTo = null;

    void init() throws UnknownHostException {
        String servers = "rs-crawler-1.yidian.com,rs-crawler-2.yidian.com,rs-crawler-3.yidian.com";
        mongoClient = MongoUtils.getMongo(servers);
        mongoDatabase = mongoClient.getDatabase("cpp");
        colls = "component";
        collsTo = "component_test";
    }

    void work(){
        FindIterable<Document> documents = mongoDatabase.getCollection(colls).find();
        int cnt = 0;
        for(Document document : documents){
            Map<String, Object> map = Maps.newHashMap();
            map.put("input", document.get("input"));
            map.put("output", document.get("output"));
            if(map.get("input") != null && map.get("output") != null){
//                Document updateIO = new Document(map);
//                mongoDatabase.getCollection(collsTo).findOneAndUpdate(new Document("_id", document.get("_id")), new Document("$set", updateIO));
//                System.out.println("processed " +  ++cnt + " documents");
            }else{
                System.out.println("id is " + document.get("_id"));
            }
        }
    }

    public static void main(String[] args) throws UnknownHostException {
        CopyComponentInputOutput backupMongo = new CopyComponentInputOutput();
        backupMongo.init();
        backupMongo.work();
    }
}
