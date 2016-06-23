package com.mongo;

import avro.shaded.com.google.common.collect.Maps;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Collections2;
import com.google.common.collect.Sets;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.yidian.commons.morphia.utils.MongoInstance;
import com.yidian.commons.morphia.utils.MongoUtils;
import com.yidian.cpp.common.mongo.GenericMongoDAO;
import org.bson.Document;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by heifrank on 16/5/31.
 */
public class GetTenThousand {

    private MongoClient mongoClient;

    private MongoDatabase database;

    private static ObjectMapper objectMapper = new ObjectMapper();

    public GetTenThousand() throws UnknownHostException {
        mongoClient = MongoUtils.getMongo("rs-crawler-1.yidian.com,rs-crawler-2.yidian.com,rs-crawler-3.yidian.com");
        database = mongoClient.getDatabase("subpages");

    }

    public void work() throws IOException {
        FindIterable<org.bson.Document> documents = database.getCollection("subpages").find();
        int cnt = 100;
        documents.limit(cnt);
        documents.batchSize(100);
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("/Users/heifrank/codes/zhaokai_full.txt"));
        int num = 0;
        for(org.bson.Document document : documents){
            System.out.println("current doc " + ++num);
            System.out.println(toJson(document));
//            bufferedWriter.write(toJson(document) + "\n");
        }
//        bufferedWriter.close();

    }

    private String toJson(Document document) throws JsonProcessingException {
        return objectMapper.writeValueAsString(document);
    }

    public static void main(String[] args) throws IOException {
        GetTenThousand getTenThousand = new GetTenThousand();
        getTenThousand.work();
    }
}
