package com.jacksonjson;

import com.fasterxml.jackson.dataformat.avro.AvroMapper;
import com.yidian.cpp.common.util.json.JacksonMapper;
import com.yidian.cpp.common.util.json.JsonUtil;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.*;

import java.io.*;
import java.util.Map;

/**
 * Created by heifrank on 16/5/27.
 */
public class TestAvroInt {
    static byte[] fromJasonToAvro(String json, Schema schema) throws Exception {

        InputStream input = new ByteArrayInputStream(json.getBytes());
        DataInputStream din = new DataInputStream(input);

        Decoder decoder = DecoderFactory.get().jsonDecoder(schema, din);

        DatumReader<Object> reader = new GenericDatumReader<>(schema);
        Object datum = reader.read(null, decoder);


        GenericDatumWriter<Object>  w = new GenericDatumWriter<Object>(schema);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        Encoder e = EncoderFactory.get().binaryEncoder(outputStream, null);

        w.write(datum, e);
        e.flush();

        return outputStream.toByteArray();
    }

    public static void main(String[] args) throws Exception {
        String path = "/Users/heifrank/yidian/cpp-rewrite-test/src/test/resources/tt.avsc";
        Schema schema = new Schema.Parser().parse(new File(path));
        Map<String, Object> map = JacksonMapper.MAPPER.getMapper().readValue(new File("/Users/heifrank/yidian/cpp-rewrite-test/src/test/resources/tmp.json"), JsonUtil.MAP_REFERENCE);
//        map.clear();
//        map.put("source", "songyang");
        String jsonStr = JacksonMapper.MAPPER.getMapper().writeValueAsString(map);

        System.out.println(jsonStr);
        fromJasonToAvro(jsonStr, schema);



    }
}
