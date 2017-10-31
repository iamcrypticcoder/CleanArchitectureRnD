package com.crypticcoder.cleanarchitecture.util;

/**
 * Created by mahbub on 2/15/2015.
 */

import android.util.Log;

public class LogUtil {
    private static final String LOG_PREFIX = "CleanArch_";
    private static final int LOG_PREFIX_LENGTH = LOG_PREFIX.length();
    private static final int MAX_LOG_TAG_LENGTH = 30;

    private LogUtil() {
    }

    public static String makeLogTag(String str) {
        if (str.length() > MAX_LOG_TAG_LENGTH - LOG_PREFIX_LENGTH) {
            return LOG_PREFIX + str.substring(0, MAX_LOG_TAG_LENGTH - LOG_PREFIX_LENGTH - 1);
        }

        return LOG_PREFIX + str;
    }

    /**
     * Don't use this when obfuscating class names!
     */
    public static String makeLogTag(Class cls) {
        return makeLogTag(cls.getSimpleName());
    }

    public static void LOGV(final String tag, String message) {
        // Add conditions for showing log
        Log.v(tag, message);
    }

    public static void LOGD(final String tag, String message) {
        // Add conditions for showing log
        Log.d(tag, message);
    }

    public static void LOGI(final String tag, String message) {
        // Add conditions for showing log
        Log.i(tag, message);
    }

    public static void LOGW(final String tag, String message) {
        // Add conditions for showing log
        Log.w(tag, message);
    }
    public static void LOGE(final String tag, String message) {
        // Add conditions for showing log
        Log.e(tag, message);
    }
}
