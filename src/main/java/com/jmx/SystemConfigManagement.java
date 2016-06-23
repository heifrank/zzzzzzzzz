package com.jmx;

import javax.management.*;
import java.lang.management.ManagementFactory;

/**
 * Created by heifrank on 16/6/22.
 */

public class SystemConfigManagement {
    private static final int DEFAULT_NO_THREADS=10;
    private static final String DEFAULT_SCHEMA="default_schema";

    public static void main(String[] args) throws MalformedObjectNameException, InterruptedException, InstanceAlreadyExistsException, MBeanRegistrationException, NotCompliantMBeanException {
        //Get the MBean server
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        //register the MBean
        SystemConfigMBean mBean = new SystemConfig(DEFAULT_NO_THREADS, DEFAULT_SCHEMA);
        ObjectName name = new ObjectName("com.journaldev.jmx:name=tyystemConfig");
        mbs.registerMBean(mBean, name);
        do{
            Thread.sleep(3000);
            System.out.println("Thread Count="+mBean.getThreadCount()+":::Schema Name="+mBean.getSchemaName());
        }while(mBean.getThreadCount() !=0);
    }
}

