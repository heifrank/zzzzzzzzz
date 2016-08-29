package com.zk;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

/**
 * Created by heifrank on 16/5/3.
 */
public class ZooKeeperTest {
    final CountDownLatch connectedSignal = new CountDownLatch(1);
    public ZooKeeper connect(String hosts, int sessionTimeout) throws IOException, InterruptedException {
        ZooKeeper zk = new ZooKeeper(hosts, sessionTimeout, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                System.out.println("songyang: watched Event is " + watchedEvent);

                if(watchedEvent.getState() == Event.KeeperState.SyncConnected){
                    connectedSignal.countDown();
                }
            }
        });
        connectedSignal.await();
        System.out.println("YEP");
        return zk;
    }

    public static void test_semaphore() throws IOException, InterruptedException, KeeperException {
        ZooKeeper zk = new ZooKeeperTest().connect("192.168.253.243:2181", 3000);
        Semaphore semaphore = new Semaphore(1);
        semaphore.acquire();
        String path = "/sample-group";
        while(true){
            List<String> children = zk.getChildren(path, new Watcher(){
                @Override
                public void process(WatchedEvent event){
                    if(event.getType() == Event.EventType.NodeChildrenChanged){
                        semaphore.release();
                        System.out.println("CK release finished");
                    }
                }
            });
            System.out.println("CK getChildren finished");
            children.stream().forEach(System.out::println);
            System.out.println("CK before acquire");
            semaphore.acquire();
        }
    }

    public static void main(String args[]) throws IOException, InterruptedException, KeeperException {
        ZooKeeperTest zooKeeperTest = new ZooKeeperTest();
//        zooKeeperTest.connect("192.168.254.80:2181", 3000);
        test_semaphore();
    }
}
