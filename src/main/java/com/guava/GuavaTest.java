package com.guava;

import com.google.common.util.concurrent.Monitor;

/**
 * Created by heifrank on 16/4/26.
 */
public class GuavaTest {
    Monitor monitor = new Monitor();

    Monitor.Guard condition = new Monitor.Guard(monitor) {
        @Override
        public boolean isSatisfied() {
             return false;
        }
    };

    public static void main(String[] args) throws InterruptedException {
        GuavaTest guavaTest = new GuavaTest();
        if(guavaTest.monitor.enterIf(guavaTest.condition)) {
            try {
                System.out.println("YEP");
            } catch (Exception e) {
                guavaTest.monitor.leave();
            }
        }
    }
}
