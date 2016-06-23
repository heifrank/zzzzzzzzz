package com.me;

import avro.shaded.com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Created by heifrank on 16/6/3.
 */
public class TestAggregate {
    public static void main(String[] args){
        List<Integer> lst = Lists.newArrayList(1, 5, 2, 8, 3, 9, 4);
        lst.stream().filter(p -> p >= 4).sorted();
    }
}
