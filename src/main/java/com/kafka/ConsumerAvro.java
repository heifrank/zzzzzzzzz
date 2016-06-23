package com.kafka;

import com.yidian.cpp.common.util.json.JacksonMapper;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.*;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.io.*;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by heifrank on 16/5/28.
 */
public class ConsumerAvro {
    private final KafkaConsumer<String, String> consumer;
    private List<String> topics;
    private Schema schema;


    public ConsumerAvro(String groupId, List<String> topics) throws IOException {
        this.topics = topics;
        Properties props = new Properties();
        props.put("bootstrap.servers", "10.103.17.106:9092,10.103.17.107:9092");
        props.put("group.id", groupId);
        props.put("key.deserializer", StringDeserializer.class.getName());
        props.put("value.deserializer", StringDeserializer.class.getName());
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        props.put("enable.auto.commit", "true");
        this.consumer = new KafkaConsumer<String, String>(props);
        String jsonStr = JacksonMapper.MAPPER.getMapper().readValue(new File("/Users/heifrank/codes/see.json"), String.class);
        this.schema = new Schema.Parser().parse(jsonStr);
//        this.schema = new Schema.Parser().parse(new File("/Users/heifrank/yidian/cpp-rewrite-test/src/test/resources/tt.avsc"));
        System.out.println(this.schema);
    }

    public void run() {
        try{
            consumer.subscribe(topics);
            while(true){
                ConsumerRecords<String, String> records = consumer.poll(5000);
                System.out.println("timeout , retry...");
                for(ConsumerRecord<String, String> record : records){
                    Map<String, Object> data = new HashMap<>();
                    data.put("partition", record.partition());
                    data.put("offset", record.offset());
                    data.put("value", record.value());
//                    GenericRecord datum = new GenericData.Record(schema);
//                    datum.put("name", "yidian.com");
//                    datum.put("favorite_number", 123456);

//                    ByteArrayOutputStream out = new ByteArrayOutputStream();
//                    DatumWriter<GenericRecord> writer = new GenericDatumWriter<>(schema);
//                    Encoder encoder = EncoderFactory.get().binaryEncoder(out, null);
//                    writer.write(datum, encoder);
//                    encoder.flush();
//                    out.close();
//
//                    System.out.println("Serialization: " + out);

                    System.out.println("value is " + data.get("value"));
                    DatumReader<GenericRecord> reader = new GenericDatumReader<>(schema);
                    byte[] tmp = ((String)data.get("value")).getBytes();
                    byte[] tmp2 = new byte[tmp.length - 7];
                    for(int i = 0; i < tmp2.length; i++)
                        tmp2[i] = tmp[i + 7];
                    System.out.println("length is  " + tmp2.length);
                    BinaryDecoder decoder = DecoderFactory.get().binaryDecoder(tmp2, null);
//                    BinaryDecoder decoder = DecoderFactory.get().binaryDecoder(out.toByteArray(), null);
                    GenericRecord result = reader.read(null, decoder);
                    System.out.println(result);
                    Thread.sleep(1000000);
                }
            }
        }catch (WakeupException e){
            System.out.println("ignore for shutdown");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            consumer.close();
        }
    }

    public static void main(String[] args) throws IOException {
        ConsumerAvro consumerAvro = new ConsumerAvro("test_tmp1", Arrays.asList("document_nlp"));
        consumerAvro.run();
        PipedOutputStream pipedOutputStream = new PipedOutputStream();
        PipedInputStream pipedInputStream = new PipedInputStream();
    }
}
