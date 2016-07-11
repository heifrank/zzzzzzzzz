package com.me;

import junit.framework.TestCase;
import org.junit.Test;

/**
 * Created by heifrank on 16/7/8.
 */
public class TestAbstractConstructor extends TestCase{
    @Test
    public void test(){
        Son son = new Son(22);
        System.out.println(son.getAge());
    }
}

abstract class Father{
    private int age;

    public Father(int age){
        this.age = age;
    }

    public int getAge(){
        return age;
    }
}

class Son extends Father{
    public Son(int age) {
        super(age);
    }
}


