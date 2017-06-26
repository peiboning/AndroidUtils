package com.android.utils.System;

/**
 * Created by peiboning on 2017/6/26.
 */

public class App {
    public static long maxMemery = 0L;

    static {
        maxMemery = Runtime.getRuntime().maxMemory();
    }
}
