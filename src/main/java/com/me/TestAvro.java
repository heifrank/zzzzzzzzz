package com.me;
import junit.framework.TestCase;
import org.apache.avro.Schema;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;
import org.junit.Test;
import org.junit.runners.model.TestClass;

import java.io.File;
import java.io.IOException;

/**
 * Created by heifrank on 16/3/14.
 */
public class TestAvro{


    public void test() throws IOException {
        DatumReader<User> userDatumReader = new SpecificDatumReader<User>(User.class);
        DataFileReader<User> dataFileReader = new DataFileReader<User>(new File("/Users/heifrank/IdeaProjects/my_test_project/users.avro"), userDatumReader);
        User user = null;
        while (dataFileReader.hasNext()) {
// Reuse user object by passing it to next(). This saves us from
// allocating and garbage collecting many objects for files with
// many items.
            user = dataFileReader.next(user);
            System.out.println(user);
        }
    }


    public void test_without_code_generating() throws IOException {
        Schema schema = new Schema.Parser().parse(new File(this.getClass().getResource("/example.avsc").getPath()));
        GenericRecord user1 = new GenericData.Record(schema);
        user1.put("name", "Alyssa");
        user1.put("favorite_number", 256);

        GenericRecord user2 = new GenericData.Record(schema);
        user2.put("name", "ben");
        user2.put("favorite_number", 7);
        user2.put("favorite_color", "red");

        File file = new File("users.avro");
        DatumWriter<GenericRecord> datumWriter = new GenericDatumWriter<>(schema);
        DataFileWriter<GenericRecord> dataFileWriter = new DataFileWriter<>(datumWriter);
        dataFileWriter.create(schema, file);
        dataFileWriter.append(user1);
        dataFileWriter.append(user2);
        dataFileWriter.close();
    }

    @Test
    public void tttt() throws IOException {
        File file = new File("users.avro");
        Schema schema = new Schema.Parser().parse(new File(this.getClass().getResource("/example.avsc").getPath()));
        DatumReader<GenericRecord> datumReader = new GenericDatumReader<>(schema);
        DataFileReader<GenericRecord> dataFileReader = new DataFileReader<GenericRecord>(file, datumReader);
        GenericRecord user = null;
        while(dataFileReader.hasNext()){
            user = dataFileReader.next(user);
            System.out.println(user);
        }

    }
}
