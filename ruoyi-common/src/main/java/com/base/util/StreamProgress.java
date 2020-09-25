package com.base.util;

public interface StreamProgress {
    void start();

    void progress(long var1);

    void finish();
}
