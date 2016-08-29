package com.avro;

import avro.shaded.com.google.common.collect.Lists;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by heifrank on 16/7/16.
 */
public class TestAvro {
    public static void main(String[] args) throws IOException {
        Schema writerSchema = new Schema.Parser().parse(new File(TestAvro.class.getResource("/test.avsc").getPath()));

        Schema readersSchema = new Schema.Parser().parse(new File(TestAvro.class.getResource("/test2.avsc").getPath()));

        // Writing a record using the initial schema with the
        // test field defined as an int
        GenericData.Record record = new GenericData.Record(writerSchema);
        record.put("userName", "Martin");
        record.put("favouriteNumber", 1337L);
        List<String> lst = Lists.newArrayList();
        lst.add("daydreaming");
        lst.add("hacking");
        GenericData.Array array = new GenericData.Array(writerSchema.getField("interests").schema(), lst);
        record.put("interests", array);
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        BinaryEncoder jsonEncoder = EncoderFactory.get().blockingBinaryEncoder(output, null);
        GenericDatumWriter<GenericData.Record> writer = new
                GenericDatumWriter<GenericData.Record>(writerSchema);
        writer.write(record, jsonEncoder);
        jsonEncoder.flush();
        output.flush();
        byte[] bytes = output.toByteArray();


        BinaryDecoder binaryDecoder = DecoderFactory.get().binaryDecoder(bytes, null);
        GenericDatumReader<GenericData.Record> reader = new GenericDatumReader<>(writerSchema, readersSchema);
        GenericRecord read = reader.read(null, binaryDecoder);
        System.out.println(read.get("uName"));
        System.out.println(read.get("favouriteNumber"));
        System.out.println(read.get("interests"));

    }
}
