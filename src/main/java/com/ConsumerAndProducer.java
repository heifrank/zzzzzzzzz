package com;

import avro.shaded.com.google.common.collect.Lists;
import com.mongo.MyComponent;
import com.typesafe.config.Config;

import java.util.List;
import java.util.Properties;
import java.util.Random;

/**
 * Created by heifrank on 16/5/6.
 */
public class ConsumerAndProducer {
    public static void main(String[] args) throws InterruptedException {
        MyQue que = new MyQue(10);
        Thread producer = new Thread(new Producer(que), "producer");
        Thread consumer = new Thread(new Consumer(que), "consumer");
        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
    }
}

class Producer implements Runnable{
    MyQue que;
    Producer(MyQue que){
        this.que = que;
    }
    @Override
    public void run() {
        int num = 0;
        while(true){
            try {
                que.put(++num);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("put %d to que\n", num);
            Random random = new Random(System.currentTimeMillis());
            try {
                Thread.sleep(random.nextInt(500));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Consumer implements Runnable{
    MyQue que;
    Consumer(MyQue que){
        this.que = que;
    }
    @Override
    public void run() {
        while(true){
            int num = 0;
            try {
                num = que.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("get %d from queue\n", num);
            Random random = new Random(System.currentTimeMillis());
            try {
                Thread.sleep(random.nextInt(3000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class MyQue{
    List<Integer> inner;

    int cap = 0;

    int cursor = 0;

    public MyQue(int max){
        this.cap = max;
        this.inner = Lists.newArrayList();
    }

    public int get() throws InterruptedException {
        int ret;
        synchronized (this){
            while(cursor == inner.size()){
                wait();
            }
            ret = inner.get(cursor);
            cursor++;
            notify();
        }
        return ret;
    }

    public synchronized void put(int n) throws InterruptedException {
        int now;
        while((now = inner.size() - cursor) == cap){
            wait();
        }
        this.inner.add(n);
        notify();
    }
}
