package com.me;

import avro.shaded.com.google.common.base.CharMatcher;
import avro.shaded.com.google.common.base.Preconditions;
import avro.shaded.com.google.common.collect.Iterables;
import avro.shaded.com.google.common.collect.Maps;
import com.google.common.base.Joiner;
import com.google.common.base.Objects;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Lists;
import com.google.common.collect.MapMaker;
import junit.framework.TestCase;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkState;

/**
 * Created by heifrank on 16/3/14.
 */

class TT{
    @Override
    public String toString(){
        return "This is tt";
    }
}

public class TestGuava extends TestCase {
    public void testJoiner(){
        List<String> stringList = new ArrayList<String>();
        stringList.add("songyang");
        stringList.add("linliuzi");
        stringList.add(null);
        stringList.add("family");
        String del = ":";
        System.out.println(Joiner.on(del).skipNulls().join(stringList));
        System.out.println(Joiner.on(del).skipNulls().join(123, 222, 334, null, new TT()));

        Joiner ori = Joiner.on(del).useForNull("missing");
//        ori.useForNull("missing");
        System.out.println(ori.join(stringList));
    }


    public void testSplitter(){
        String content = "  ,   a   ,   ,  b ,   ";
        System.out.println(Splitter.on(",").trimResults().omitEmptyStrings().split(content));
    }

    public void testStrings(){
        System.out.println(Strings.padEnd("songyang", 10, 'x'));
        String s = Preconditions.checkNotNull(null, "12345");
    }

    public void test_check(){
        check_args(33);
    }

    private void check_args(int a){
        checkState(a>3, "YES");
    }

    public String toString(){
        return Objects.toStringHelper(this).add("title", 123).add("name", Objects.firstNonNull("default", "songyang")).toString();
    }

    class Freq implements Callable<String> {
        private int gap;
        private int id;

        public Freq(int gap, int id){
            this.gap = gap;
            this.id = id;
        }

        @Override
        public String call() throws Exception {
            for(int i = 0; i < 10; i++){
                Thread.sleep(this.gap);
                System.out.println(this.id + " is working at rate " + this.gap);
            }
            return this.id + " is finished.";
        }
    }

    public void test_Objects() throws IOException, ExecutionException, InterruptedException {
//        Object o = new TestGuava();
//        System.out.println(o.toString());
        ExecutorService exs = Executors.newCachedThreadPool();
        List<Future<String>> rets = new ArrayList<>();

        int cnt = 0;
        int gap = 500;
        for(int i = 0; i < 5; i++){
            Freq f = new Freq(gap, cnt);
            rets.add(exs.submit(f));
            cnt++;
            gap += 200;
        }

        Thread.sleep(20000);
        for(Future<String> fs : rets){
            if(fs.isDone()){
                System.out.println(fs.get());
            }else{
                System.out.println("not ready yet.");
            }
        }
        exs.shutdown();
    }

    public void test_lists(){
        String s = "/abc/def//okc/ffd";
        List<String> lst = Lists.newArrayList(Splitter.on("/").omitEmptyStrings().split(s));
        for(String ss : lst){
            System.out.println(ss);
        }
    }

    public void test_fluent(){

    }

    public void test_map_null(){
        Map<String, String> mp = Maps.newHashMap();
        mp.put("name", null);
        if(mp.containsKey("name"))
            System.out.println("YES");
        if(mp.get("name") == null)
            System.out.println("That is null");
    }

    public void test_arrays(){
        List lst = Arrays.asList("songyang", "linliuzi");
        for(Object s : lst){
            System.out.println(s);
        }
    }
}
