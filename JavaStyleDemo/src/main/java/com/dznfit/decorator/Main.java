package com.dznfit.decorator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        /*FileOutputStream stream = new FileOutputStream(new File("e:/demo.text"));
        ProgressOutputStream pss = new ProgressOutputStream(stream);
        LogOutputStreams los = new LogOutputStreams(pss);

        los.write("cccc".getBytes());
        los.close();*/

        String str = "class com.dznfit.entity.String";
        System.out.println(str.lastIndexOf("."));
        System.out.println(str.substring(str.lastIndexOf(".") + 1, str.length()));
    }
}
