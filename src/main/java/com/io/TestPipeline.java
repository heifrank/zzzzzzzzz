package com.io;

import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by heifrank on 16/5/28.
 */
public class TestPipeline {
    public static void main(String[] args) throws IOException, InterruptedException {
        test1();
        return ;
//
//        PipedOutputStream pipedOutputStream = new PipedOutputStream();
//        PipedInputStream pipedInputStream = new PipedInputStream(pipedOutputStream);
//        ExecutorService service = Executors.newFixedThreadPool(2);
//
//        service.submit(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    pipedOutputStream.write("This is 宋洋 speaking".getBytes());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//
//        service.submit(new Runnable() {
//            @Override
//            public void run() {
//                int len = 0;
//                try {
//                    while((len = pipedInputStream.read()) != -1){
//                        System.out.println((char)len);
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//
//        service.awaitTermination(100, TimeUnit.MINUTES);
    }

    public static void test1() throws IOException, InterruptedException {
        PipedWriter pipedOutputStream = new PipedWriter();
        PipedReader pipedInputStream = new PipedReader(pipedOutputStream);
        ExecutorService service = Executors.newFixedThreadPool(2);

        service.submit(new Runnable() {
            @Override
            public void run() {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    try {
                        pipedOutputStream.write("This is 宋洋 speaking\n");
                        pipedOutputStream.write(-1);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                System.out.println("Finished in writer...");
            }
        });

        service.submit(new Runnable() {
            @Override
            public void run() {
                int len = 0;
                try {
                    while((len = pipedInputStream.read()) != -1){
                        System.out.println((char)len);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("Finished in read..");
                    try {
                        pipedInputStream.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    return;
                }

            }
        });

        Thread.sleep(5000);
//        pipedOutputStream.close();
        service.shutdown();
        System.out.println("output stream close!!");
        service.awaitTermination(100, TimeUnit.MINUTES);
        System.out.println("Finished...");
    }
}
