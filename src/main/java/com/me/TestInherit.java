package com.me;

import avro.shaded.com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Created by heifrank on 16/6/2.
 */
public class TestInherit {
    public static void main(String[] args){
        BaseProcessor derivedProcessor = new DerivedProcessor();
        derivedProcessor.process();
    }
}

interface Icom{
    public void process();
}

abstract class AbstractProcessor implements Icom{
    protected Logger logger = LoggerFactory.getLogger(AbstractProcessor.class);

    @Override
    public void process() {
        System.out.println("process method in AbstractProcessor...");
        work();
    }

    public abstract void work();
}

class BaseProcessor extends AbstractProcessor{

    @Override
    public void work() {
        System.out.println("work method in BaseProcessor...");
    }
}

class DerivedProcessor extends BaseProcessor{
    private static Logger logger = LoggerFactory.getLogger(DerivedProcessor.class);

    @Override
    public void work(){
        System.out.println("work method in DerivedProcessor...");
        super.work();
    }

    @Override
    public void process(){
        System.out.println("process method in DerivedProcessor...");
        super.process();
    }
}
