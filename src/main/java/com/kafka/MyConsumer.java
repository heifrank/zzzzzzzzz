package com.kafka;

import avro.shaded.com.google.common.collect.Lists;
import avro.shaded.com.google.common.collect.Maps;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by heifrank on 16/6/3.
 */
public class MyConsumer implements Runnable{
    KafkaConsumer<String, String> consumer;
    List<String> topics;
    int id;

    MyConsumer(int id, String group, List<String> topics){
        Properties prop = new Properties();
        prop.put("group.id", group);
        prop.put("bootstrap.servers", "localhost:9094");
        prop.put("key.deserializer", StringDeserializer.class.getName());
        prop.put("value.deserializer", StringDeserializer.class.getName());
        prop.put("auto.offset.reset", "earliest");
        this.consumer = new KafkaConsumer<String, String>(prop);
        this.topics = topics;
        this.id = id;
    }

    @Override
    public void run() {
        try {
            this.consumer.subscribe(topics);
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Integer.MAX_VALUE);
                for (ConsumerRecord<String, String> record : records) {
                    Map<String, Object> data = Maps.newHashMap();
                    data.put("offset", record.offset());
                    data.put("value", record.value());
                    data.put("partition", record.partition());
                    System.out.println(String.format("consumer id %d got %s", this.id, data));
                }
            }
        }catch (WakeupException e){
            System.out.println(String.format("Thread %d wakeup", id));
            // nothing to do here
        }finally {
            this.consumer.close();
        }
    }

    public void stop(){
        this.consumer.wakeup();
    }

    public static void main(String[] args){
        List<String> topics = Arrays.asList("tmp-test");
        int num = 2;
        ExecutorService executorService = Executors.newFixedThreadPool(num);
        List<MyConsumer> consumers = Lists.newArrayList();

        for(int i = 0; i < num; i++){
            consumers.add(new MyConsumer(i, "group3", topics));
            executorService.submit(consumers.get(i));
        }

        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run(){
                executorService.shutdown();
                for(int i = 0; i < num; i++){
                    consumers.get(i).stop();
                }
                try {
                    executorService.awaitTermination(5, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
