package com.annotation;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by heifrank on 16/4/7.
 */

@TesterInfo(
        priority = TesterInfo.Priority.HIGH,
        createBy = "heifrank",
        tags = {"family", "we"}
)
public class TestExample {
    @Test
    void testA(){
        if(true){
            throw new RuntimeException("This test always failed");
        }
    }

    @Test(enabled = false)
    void testB(){
        if(false){
            System.out.println("This never happend");
        }
    }

    @Test
    void testC(){
        if(true){
            System.out.println("YEP in C");
        }
    }

    public static void main(String[] args) throws IOException {
        File f = new File("aaa");
        System.out.println(f.getAbsolutePath());
        System.out.println("YY");
    }
}
