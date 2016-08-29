package com;

import morpheus.Nebuchadnezzar.Builder.RowBuilder;
import morpheus.Nebuchadnezzar.Row;
import yidian.data.morpheus.smartclient.writer.MorpheusWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by heifrank on 16/7/19.
 */
public class WriteExample {
    public static void main(String[] args) {
        MorpheusWriter writer = null;
        try {
            writer = MorpheusWriter.Builder.newBuilder("10.111.1.2:2181", "userprofile")
                    .withBorrowTimeout(1000)
                    .withTimeout(1000)
                    .withConnectionTtl(60000)
                    .withRetryTimes(3)
                    .enableParallel()  // write to multiple backend morpheus servers in parallel, disabled by default
                    .enableOpenTSDB()
                    .withMetricNamePrefix("writeExample")
                    .withMetricTag("writeExampleTag")
                    .build();
            writer.start();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("build MorpheusWriter failed");
            return;
        }

        String tableName = "userprofile";
        // single put
        {
            Row row = RowBuilder.newBuilder()
                    .setKey("123".getBytes())
                    .addElem("index_1".getBytes(), "value_1".getBytes())
                    .addElem("index_2".getBytes(), "value_2".getBytes())
                    .build();
            try {
                writer.put(tableName, row);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // multi-put
        {
            for (int k = 0; k < 100; ++k) {
                List<Row> rows = new ArrayList<Row>(50);
                for (int i = 0; i < 50; ++i) {
                    Row row = RowBuilder.newBuilder()
                            .setKey(("123" + Integer.toString(i + k)).getBytes())
                            .addElem("index_1".getBytes(), "value_1".getBytes())
                            .addElem("index_2".getBytes(), "value_2".getBytes())
                            .build();
                    rows.add(row);
                }
                try {
                    writer.multiPut(tableName, rows);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        // single update
        {
            Row row = RowBuilder.newBuilder()
                    .setKey("123".getBytes())
                    .addElem("index_1".getBytes(), "value_1".getBytes())
                    .addElem("index_2".getBytes(), "value_2".getBytes())
                    .build();
            try {
                writer.update(tableName, row);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // multi-update
        {
            for (int k = 0; k < 100; ++k) {
                List<Row> rows = new ArrayList<Row>(50);
                for (int i = 0; i < 50; ++i) {
                    Row row = RowBuilder.newBuilder()
                            .setKey(("123" + Integer.toString(i + k)).getBytes())
                            .addElem("index_1".getBytes(), "value_1".getBytes())
                            .addElem("index_2".getBytes(), "value_2".getBytes())
                            .build();
                    rows.add(row);
                }
                try {
                    writer.multiUpdate(tableName, rows);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        writer.close();
    }
}
