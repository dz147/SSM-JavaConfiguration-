package com.dznfit.decorator;

import java.io.IOException;
import java.io.OutputStream;

public class ProgressOutputStream extends OutputStream{
    private OutputStream os;

    public ProgressOutputStream(OutputStream os) {
        this.os = os;
    }

    @Override
    public void write(int b) throws IOException {
        System.out.println("...");
        this.os.write(b);
    }
}
