package com.kafka;


import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.Cluster;

import java.util.Map;
import java.util.Properties;

/**
 * Created by heifrank on 16/4/19.
 */



public class SimpleProducer {
    public static void main(String[] args){
        Properties prop = new Properties();
        prop.put("bootstrap.servers", "localhost:9092,localhost:9093");
        prop.put("key.serializer",   "org.apache.kafka.common.serialization.IntegerSerializer");
        prop.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        prop.put("partitioner.class", "com.kafka.MyPartitioner");

        KafkaProducer<Integer, String> producer = new KafkaProducer<Integer, String>(prop);


        ProducerRecord<Integer, String> record = new ProducerRecord<Integer, String>("my-learning-topic", 123457, "33宋洋");
        producer.send(record);
        producer.close();
    }
}
