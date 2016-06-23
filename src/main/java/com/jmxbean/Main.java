package com.jmxbean;

import javax.management.*;
import java.lang.management.ManagementFactory;

/**
 * Created by heifrank on 16/5/10.
 */
public class Main {
    public static void main(String[] args) throws MalformedObjectNameException, NotCompliantMBeanException, InstanceAlreadyExistsException, MBeanRegistrationException, InterruptedException {
        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
        ObjectName name = new ObjectName("com.jmxbean:type=Hello");
        Hello mbean = new Hello();
        mBeanServer.registerMBean(mbean, name);
        System.out.println("Waiting forever.");
        Thread.sleep(Long.MAX_VALUE);
    }
}
