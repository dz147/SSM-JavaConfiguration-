package com.dznfit.decorator;

import java.io.IOException;
import java.io.OutputStream;

public class LogOutputStreams extends OutputStream{
    private OutputStream os;

    public LogOutputStreams(OutputStream os) {
        this.os = os;
    }

    @Override
    public void write(int b) throws IOException {
        this.os.write(b);
    }

    @Override
    public void write(byte[] b) throws IOException {
        System.out.println("开始哈");
        super.write(b);
        System.out.println("结束了哈");
    }
}
