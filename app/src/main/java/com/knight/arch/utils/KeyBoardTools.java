package com.knight.arch.utils;

import android.app.Instrumentation;

/**
 * @author andyiac
 * @date 15-8-17
 * @web http://blog.andyiac.com/
 */
public class KeyBoardTools {
    /**
     * 模拟键盘事件方法
     */
    public static void actionKey(final int keyCode) {
        new Thread() {
            public void run() {
                try {
                    Instrumentation inst = new Instrumentation();
                    inst.sendKeyDownUpSync(keyCode);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
