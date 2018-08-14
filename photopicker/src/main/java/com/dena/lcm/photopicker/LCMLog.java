package com.dena.lcm.photopicker;

import android.util.Log;

/**
 * Created by hui.yang on 5/6/15.
 */
public class LCMLog {

    private static boolean debug = true;

    public static void setDebug(boolean debug) {
        LCMLog.debug = debug;
    }

    public static void d(String tag, String msg) {
        if (debug) {
            Log.d(tag, msg);
        }
    }

    public static void d(String tag, String msg, Throwable cause) {
        if (debug) {
            Log.d(tag, msg, cause);
        }
    }

    public static void w(String tag, String msg) {
        if (debug) {
            Log.w(tag, msg);
        }
    }

    public static void w(String tag, String msg, Throwable cause) {
        if (debug) {
            Log.w(tag, msg, cause);
        }
    }

    public static void e(String tag, String msg) {
        if (debug) {
            Log.e(tag, msg);
        }
    }

    public static void e(String tag, String msg, Throwable cause) {
        if (debug) {
            Log.e(tag, msg, cause);
        }
    }

    public static void i(String tag, String msg) {
        if (debug) {
            Log.i(tag, msg);
        }
    }

    public static void i(String tag, String msg, Throwable cause) {
        if (debug) {
            Log.i(tag, msg, cause);
        }
    }
}
