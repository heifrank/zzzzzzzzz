import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by heifrank on 16/7/22.
 */
public class TestShutdown {
    @Test
    public void test() throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(2);

        PriorityBlockingQueue<Integer> queue = new PriorityBlockingQueue<Integer>(10);

        for(int i = 0; i < 5; i++) {
            service.submit(new Runnable() {
                @Override
                public void run() {

                    while (true) {
                        try{
                            System.out.println(Thread.currentThread() + " is running");
                            queue.take();
                            System.out.println("YEP");
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            System.out.println("Got exception");
                            break;
//                            e.printStackTrace();
                        }

                    }
                }
            });
        }

        Thread.sleep(3000);
        service.shutdownNow();
        service.awaitTermination(10, TimeUnit.MINUTES);
    }
}
