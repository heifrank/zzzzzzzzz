package com.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Created by heifrank on 16/5/30.
 */
public class TestRandomAccess {
    public static void main(String[] args) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(new File("/Users/heifrank/codes/a.py"), "rw");
        randomAccessFile.seek(12);
        randomAccessFile.write("宋洋".getBytes());
        randomAccessFile.close();
    }
}
