package com.knight.arch;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;

/**
 * Created by summer on 15-7-29.
 *
 * @web http://blog.andyiac.com/
 */
public class ClientApplication extends Application {
    private static final String LOGGER_TAG = "<<=TAG=>>";

    public void onCreate() {
        super.onCreate();
        initStetho();
        initLogger();
    }

    private void initLogger() {
        Logger.init(LOGGER_TAG)               // default PRETTYLOGGER or use just init()
                .setMethodCount(3)            // default 2
                .setLogLevel(LogLevel.FULL)   // default LogLevel.FULL
                .setMethodOffset(2);          // default 0
//              .hideThreadInfo()             // default shown
    }

    private void initStetho() {
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build());
    }
}
