package com.demo.jianjunhuang.mvptools.utils;

import android.util.Log;

/**
 * @author jianjunhuang.me@foxmail.com
 *         create on 2017/9/6.
 */

public class HLog {
    private static boolean isLog = true;

    public static boolean isLog() {
        return isLog;
    }

    public static void setLog(boolean log) {
        isLog = log;
    }

    public static void i(String tag, String msg) {
        if (isLog) {
            Log.i(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (isLog) {
            Log.d(tag, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (isLog) {
            Log.w(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (isLog) {
            Log.e(tag, msg);
        }
    }

}
