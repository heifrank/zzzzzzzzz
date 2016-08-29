package com.me;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by heifrank on 16/8/26.
 */
public class TestWontStop {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(2);
        service.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("YEP");
            }
        });
        System.out.println("finish in main");
    }
}
