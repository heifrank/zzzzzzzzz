package com.me;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by heifrank on 16/7/25.
 */
public class TestURL {
    public static void main(String[] args) throws MalformedURLException {
        URL u = new URL("http://changchun.auto.qq.com/xzzx/xczxList_test.htm");
        System.out.println(u.getHost());
        System.out.println(u.getFile());
    }
}
