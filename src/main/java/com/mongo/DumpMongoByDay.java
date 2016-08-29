package com.mongo;

import avro.shaded.com.google.common.collect.Lists;
import avro.shaded.com.google.common.collect.Maps;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;
import com.yidian.commons.base.ObjectMapperInstance;
import com.yidian.commons.morphia.utils.MongoUtils;
import org.bson.Document;
import org.joda.time.format.ISODateTimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

/**
 * Created by heifrank on 16/7/14.
 */
public class DumpMongoByDay {
    Logger logger = LoggerFactory.getLogger(DumpMongoByDay.class);

    MongoClient mongoClient = null;

    MongoDatabase mongoDatabase = null;

    String colls = null;

    String outputDir = null;

    ObjectMapper mapper = new ObjectMapper();

    void init() throws UnknownHostException {
        String servers = "10.103.17.106,10.103.17.107";
        mongoClient = MongoUtils.getMongo(servers);
        mongoDatabase = mongoClient.getDatabase("documents");
        colls = "documents";
        outputDir = "/Users/heifrank/codes/all_feature_dump/";
    }

    void work() throws IOException {
        FindIterable<Document> documents = mongoDatabase.getCollection(colls).find();
        documents.batchSize(1000);

        Map<String, BufferedWriter> openFile = Maps.newHashMap();

        int cnt = 0;
        for(Document document : documents){
            String dateStr = document.getString("insert_time");
            LocalDateTime date = LocalDateTime.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            String dt = date.format(formatter);
            File fileDir = new File(outputDir + dt);
            if(!fileDir.exists()){

                fileDir.mkdirs();
                openFile.put(dt, new BufferedWriter(new FileWriter(fileDir + "/1.txt")));
            }

            System.out.println(String.format("Processing %d document", ++cnt));
            BufferedWriter writer = openFile.get(dt);
            writer.write(mapper.writeValueAsString(document) + "\n");
        }

        for(BufferedWriter writer : openFile.values()){
            writer.close();
        }
    }

    public static void main(String[] args) throws IOException {
        DumpMongoByDay dumpMongoByDay = new DumpMongoByDay();
        dumpMongoByDay.init();
        dumpMongoByDay.work();
    }
}
