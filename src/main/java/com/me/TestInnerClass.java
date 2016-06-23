package com.me;

/**
 * Created by heifrank on 16/6/4.
 */
public class TestInnerClass {
    int x = 5;

    public void work(){

        Thread t = new Thread(){
            @Override
            public void run(){
                try {
                    Thread.sleep(2000);
                    System.out.println("x = " + TestInnerClass.this.x);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
//        t.setDaemon(true);
        t.start();
    }

    public static void main(String[] args){
        TestInnerClass testInnerClass = new TestInnerClass();
        testInnerClass.work();
        System.out.println("YEP");
    }
}
