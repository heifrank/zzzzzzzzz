package com.metrics;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.JmxReporter;
import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by heifrank on 16/6/23.
 */
public class PracticalUsage{
    private static Logger logger = LoggerFactory.getLogger(PracticalUsage.class);

    private static MetricRegistry registry = new MetricRegistry();

    public static void main(String[] args) throws InterruptedException {
        Service service = new Service(registry.meter("request-stat"));

        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                service.serve();
            }
        }, 1, 2, TimeUnit.SECONDS);

        JmxReporter reporter = JmxReporter.forRegistry(registry).build();
        reporter.start();

        while(true){
            Thread.sleep(1000);
        }
    }

    public static class Service{
        private Meter meter = null;

        public Service(Meter meter){
            this.meter = meter;
        }

        public void serve(){
            logger.info("Serve at " + LocalDateTime.now().format(DateTimeFormatter.ISO_DATE));
            meter.mark();
        }
    }
}
