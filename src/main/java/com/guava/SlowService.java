package com.guava;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

/**
 * Created by heifrank on 16/4/26.
 */
class SlowService {

    private Cache<Integer, String> cache = CacheBuilder.newBuilder().build();
    private int calls;

    public String cachedMethod(final Integer param)
            throws ExecutionException {
        return cache.get(param, new Callable<String>() {
            @Override
            public String call() {
                return internalSlowMethod(param);
            }
        });
    }

    private String internalSlowMethod(Integer param) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        calls++;
        return "Result for " + param;
    }

    public int getCalls() {
        return calls;
    }

    public static void main(String[] args) throws ExecutionException {
        SlowService slowService = new SlowService();

        System.out.println(slowService.cachedMethod(1)); // -> Result for 1
        System.out.println("YES");
        System.out.println(slowService.cachedMethod(2)); // -> Result for 2
        System.out.println("YES");
        System.out.println(slowService.cachedMethod(1)); // -> Result for 1
        System.out.println("YES");
        System.out.println(slowService.cachedMethod(2)); // -> Result for 2
        System.out.println("YES");
        System.out.println(slowService.getCalls());      // -> 2
    }
}
