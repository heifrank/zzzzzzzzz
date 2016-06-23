package com.me;

import avro.shaded.com.google.common.collect.Lists;
import org.junit.Assert;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by heifrank on 16/5/17.
 */
public class TestJavaSort {
    public static void main(String[] args){
        List<Human> humans = Lists.newArrayList(new Human("Sarah", 10), new Human("Jack", 12));
        Collections.sort(humans, new Comparator<Human>(){
            @Override
            public int compare(Human o1, Human o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });

        Collections.sort(humans, (h1, h2) -> h1.getName().compareTo(h2.getName()));
        System.out.println(humans.get(0).getName());
    }
}

class Human{
    private String name;
    private int age;

    public Human(final String name, final int age){
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
