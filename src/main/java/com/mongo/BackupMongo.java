package com.mongo;

import avro.shaded.com.google.common.collect.Lists;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;
import com.yidian.commons.morphia.utils.MongoUtils;
import com.yidian.cpp.common.mongo.MongoUtil;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.UnknownHostException;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by heifrank on 16/4/14.
 */
public class BackupMongo {
    Logger logger = LoggerFactory.getLogger(BackupMongo.class);

    MongoClient mongoClient = null;

    MongoDatabase mongoDatabase = null;

    String colls = null;

    void init() throws UnknownHostException {
        String servers = "rs-crawler-1.yidian.com,rs-crawler-2.yidian.com,rs-crawler-3.yidian.com";
        mongoClient = MongoUtils.getMongo(servers);
        mongoDatabase = mongoClient.getDatabase("cpp");
        colls = "component";
    }

    void work(){
        FindIterable<Document> documents = mongoDatabase.getCollection(colls).find();
        List<Document> newdocs = Lists.newArrayList();
        int cnt = 0;
        for(Document document : documents){
            cnt++;
            String name = document.getString("name");
            Document newdoc = new Document(document);
            newdoc.replace("_id", name);
            newdocs.add(newdoc);
            System.out.println("songyang " + cnt + ": " + newdoc);
            mongoDatabase.getCollection(colls).deleteOne(document);
        }
//        documents.forEach((Consumer<Document>)  s -> System.out.println(s));

        mongoDatabase.getCollection(colls).insertMany(newdocs);
    }

    public static void main(String[] args) throws UnknownHostException {
        BackupMongo backupMongo = new BackupMongo();
        backupMongo.init();
        backupMongo.work();
    }
}
