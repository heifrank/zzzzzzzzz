package com.metrics;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;
import com.regex.TestRegex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * Created by heifrank on 16/6/23.
 */
public class GetStarted {
    public static final Logger logger = LoggerFactory.getLogger(GetStarted.class);

    static final MetricRegistry metrics = new MetricRegistry();

    public static void main(String args[]) throws InterruptedException {
        startReport();
        Meter requests = metrics.meter("requests");
        requests.mark();
        new Thread(new TestRegex()).start();
        wait5Seconds();
    }

    static void startReport() {
        ConsoleReporter reporter = ConsoleReporter.forRegistry(metrics)
                .convertRatesTo(TimeUnit.SECONDS)
                .convertDurationsTo(TimeUnit.DAYS)
                .build();
        reporter.start(10, TimeUnit.SECONDS);
    }

    static void wait5Seconds() throws InterruptedException {
        while(true) {
            Thread.sleep(5 * 1000);
            logger.info("In GetStarted.");
        }
    }
}
