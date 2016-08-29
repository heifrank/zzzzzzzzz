package com.io;

import java.io.IOException;
import java.io.StringReader;

/**
 * Created by heifrank on 16/7/23.
 */
public class TestReader {


    public static void main(String[] args) throws IOException {
        String s = "宋洋123llz";
        StringReader reader = new StringReader(s);
        int data;
        while( (data = reader.read()) != -1){
            System.out.println((char)data);
        }
    }
}
