package com.me;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.concurrent.*;

/**
 * Created by heifrank on 16/6/3.
 */
public class TestThreads {
    public class MyThread implements Runnable{
        private int tid;

        private volatile boolean running = true;

        ScheduledFuture future = null;

        ScheduledExecutorService timeoutService = null;

        public MyThread(int id){
            this.tid = id;
            timeoutService = Executors.newScheduledThreadPool(5);
            future =  timeoutService.schedule(new Runnable() {
                @Override
                public void run() {
                    MyThread.this.stop();
                }
            }, 30, TimeUnit.SECONDS);
        }

        @Override
        public void run() {
            int seconds = 0;
            while(running){
                try {
                    Thread.sleep(1000);
                    System.out.println(String.format("thread %s running for %d seconds", this.tid, ++seconds));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(String.format("Thread %s stopped", this.tid));
        }

        public synchronized void stop(){
            if(future != null)
                future.cancel(false);
            if(timeoutService != null)
                timeoutService.shutdown();
            this.running = false;
            System.out.println(String.format("Set stop for thread %s", this.tid));
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int numThreads = 3;
        ExecutorService service = Executors.newFixedThreadPool(numThreads);
        TestThreads testThreads = new TestThreads();
        List<MyThread> innerThreads = Lists.newArrayList();

        for(int i = 0; i < numThreads; i++){
            innerThreads.add(testThreads.new MyThread(i));
            service.submit(innerThreads.get(i));
        }

        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run() {
                for(int i = 0; i < numThreads; i++){
                    innerThreads.get(i).stop();
                }

                service.shutdown();
                try {
                    service.awaitTermination(Integer.MAX_VALUE, TimeUnit.SECONDS);
                    System.out.println("YEP");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread.sleep(Integer.MAX_VALUE);
    }
}
