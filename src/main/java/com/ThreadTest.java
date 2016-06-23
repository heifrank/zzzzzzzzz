package com;

/**
 * Created by heifrank on 16/5/6.
 */
public class ThreadTest {

    public static void main(String[] args) throws InterruptedException {
        ThreadB b = new ThreadB();
        b.start();

        Thread.sleep(1000);
        synchronized(b){
            try{
                System.out.println("Waiting for b to complete...");
                b.wait();
            }catch(InterruptedException e){
                e.printStackTrace();
            }

            System.out.println("Total is: " + b.total);
        }
    }
}

class ThreadB extends Thread{
    int total;
    @Override
    public void run(){
        synchronized(this){
            for(int i=0; i<100 ; i++){
                total += i;
            }

            notify();
            System.out.println("B is finished now and send notify...");
        }
    }
}
