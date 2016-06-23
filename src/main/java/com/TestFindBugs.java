package com;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Created by heifrank on 16/5/16.
 */
public class TestFindBugs {
    @CheckForNull String test(String p1){
        System.out.println(p1);
        return null;
    }

//    public static String getString(){
//        return "123";
//    }

    public static void main(String[] args){
        String pp = null;
//        pp = getString();
        TestFindBugs testFindBugs = new TestFindBugs();
        System.out.println("YY");
        String ss = testFindBugs.test(pp);
        System.out.println(ss);
    }
}
