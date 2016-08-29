package com.me;

import com.yidian.commons.base.DaemonThreadFactory;
import sun.jvm.hotspot.debugger.ThreadAccess;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by heifrank on 16/7/23.
 */
public class TestThreads2 {
    private static ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);

    public static void test_schedule(){
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                    System.out.println("5 seconds passed! " + System.currentTimeMillis() / 1000 + ", " + Thread.currentThread());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, 0, 10, TimeUnit.SECONDS);
    }

    public static void test_readwrite() throws InterruptedException {
        ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        ExecutorService service = Executors.newFixedThreadPool(3);

        Runnable runnable = () -> {
            readWriteLock.readLock().lock();
            try {
                System.out.println("Get lock finished!");
                while(true) {
                    Thread.sleep(1000);
                    System.out.println("Running in reading..." + Thread.currentThread());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                readWriteLock.readLock().unlock();
            }
        };

        service.submit(runnable);
        service.submit(runnable);

        Thread.sleep(5000);
        readWriteLock.writeLock().lock();

        try{
            System.out.println("YEP");
        }finally {
            readWriteLock.writeLock().unlock();
        }

    }

    public static void main(String[] args) throws InterruptedException {
//        test_schedule();
        test_readwrite();
        if(true)return ;

//        ExecutorService service = Executors.newSingleThreadExecutor(new DaemonThreadFactory());
        ExecutorService service = Executors.newFixedThreadPool(3);
        Future<Integer>[] futures = new Future[5];
        for(int i = 0; i < 2; i++) {
            futures[i] = service.submit(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    boolean flag = true;
                    while(flag) {
                        Thread.sleep(1000);
                        System.out.println("Running " + Thread.currentThread());
                    }
                    return 123;
                }

            });
        }

        new Thread(() -> {
            try {
                Thread.sleep(10000);
                service.shutdownNow();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        for (int i = 0; i < 2; i++) {
            try {
                Integer res = futures[i].get();
            } catch (ExecutionException e) {
                e.printStackTrace();
                System.out.println("songyang");
            }
        }
        System.out.println("Finished");
    }
}
