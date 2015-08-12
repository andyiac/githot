package com.knight.arch.utils;

import com.knight.arch.BuildConfig;
import com.orhanobut.logger.Logger;

/**
 * Created by summer on 15-7-30.
 *
 * @web http://blog.andyiac.com/
 */
public class L {

    public static boolean isDebuggable() {
        return BuildConfig.DEBUG;
    }

    public static void i(String msg) {
        if (isDebuggable())
            Logger.i(msg);
    }

    public static void d(String msg) {
        if (isDebuggable())
            Logger.d(msg);
    }

    public static void e(String msg) {
        if (isDebuggable())
            Logger.e(msg);
    }


    public static void json(String json) {
        if (isDebuggable())
            Logger.json(json);
    }

}
