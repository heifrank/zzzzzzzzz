package com.jmxbean;

/**
 * Created by heifrank on 16/5/10.
 */
public class Hello implements HelloMBean{

    @Override
    public void sayHello() {
        System.out.println("Hello world, songyang");
    }

    @Override
    public int add(int x, int y) {
        return x + y;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getCacheSize() {
        return this.cacheSize;
    }

    @Override
    public synchronized void setCacheSize(int size) {
        this.cacheSize = size;
        System.out.println("Cache size now is :" + this.cacheSize);
    }

    private final String name = "songyang";

    private int cacheSize = DEFAULT_CACHE_SIZE;

    private static final int DEFAULT_CACHE_SIZE = 200;
}
