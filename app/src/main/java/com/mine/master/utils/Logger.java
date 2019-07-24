package com.mine.master.utils;

import android.util.Log;

public class Logger {
    public static boolean DEBUG = true;
    private static final String LOG_TAG = "APP//:";

    public static void d(String tag, String message) {
        if (DEBUG) {
            Log.d(tag, message);
        }
    }

    public static void i(String tag, String message) {
        if (DEBUG) {
            Log.i(tag, message);
        }
    }

    public static void w(String tag, String message) {
        if (DEBUG) {
            Log.w(tag, message);
        }
    }

    public static void e(String tag, String message) {
        if (DEBUG) {
            Log.e(tag, message);
        }
    }

    public static void i(String message) {
        if (DEBUG) {
            i(LOG_TAG, message);
        }
    }

    public static void w(String message) {
        if (DEBUG) {
            w(LOG_TAG, message);
        }
    }

    public static void d(String message) {
        if (DEBUG) {
            d(LOG_TAG, message);
        }
    }

    public static void e(String message) {
        if (DEBUG) {
            e(LOG_TAG, message);
        }
    }

}
