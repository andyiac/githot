package com.knight.arch.utils;

import com.knight.arch.BuildConfig;
import com.orhanobut.logger.Logger;

import java.util.Objects;

/**
 * Created by summer on 15-7-30.
 *
 * @web http://blog.andyiac.com/
 */
public class L {

    public static boolean isDebuggable() {
        return BuildConfig.DEBUG;
    }

    public static void i(String msg, Objects... args) {
        if (isDebuggable())
            Logger.i(msg, args);
    }

    public static void d(String msg, Objects... args) {
        if (isDebuggable())
            Logger.d(msg, args);
    }

    public static void e(String msg, Objects... args) {
        if (isDebuggable())
            Logger.e(msg, args);
    }


    public static void json(String json) {
        if (isDebuggable())
            Logger.json(json);
    }

}
