package com.me;

/**
 * Created by heifrank on 16/4/15.
 */
public class TestSynchronized extends Thread{
    public static void main(String[] args) throws InterruptedException {
        TestSynchronized t1 = new TestSynchronized(1);
        TestSynchronized t2 = new TestSynchronized(2);
        t1.start();
        t2.start();
        Thread.sleep(10000000);
        System.out.println("YES");

    }

    int num;

    public TestSynchronized(int num){
        this.num = num;
    }

    @Override
    public void run() {
        for(int i = 0; i < 1000; i++) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (this){
                if (this.num == 1) {
                    cnt++;
                    System.out.println("Thread " + num + " says: cnt = " + cnt);
                } else {
                    cnt--;
                    System.out.println("Thread " + num + " says: cnt = " + cnt);
                }
            }
        }
        System.out.println("Thread " + num + " finished!");
    }

    static volatile int cnt;
}
