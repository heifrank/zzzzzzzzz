package com.io;

import java.io.*;
import java.nio.ByteBuffer;

/**
 * Created by heifrank on 16/5/30.
 */
public class TestBytes {
    public static void main(String[] args) throws IOException {

        String y = "宋";


        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        outputStream.write(x);
        outputStream.write(y.getBytes("UTF-8"));
//        outputStream.write(z);
        byte[] bytes = outputStream.toByteArray();
        System.out.println(bytes.length);
        outputStream.close();

        InputStreamReader dataInputStream = new InputStreamReader(new ByteArrayInputStream(bytes), "UTF-8");
//        System.out.println(dataInputStream.readChar());

        System.out.println((char)dataInputStream.read());

//        System.out.println(dataInputStream.readChar());

    }
}
