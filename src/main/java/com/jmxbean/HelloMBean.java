package com.jmxbean;

/**
 * Created by heifrank on 16/5/10.
 */
public interface HelloMBean {
    public void sayHello();

    public int add(int x, int y);

    public String getName();

    public int getCacheSize();

    public void setCacheSize(int size);
}
