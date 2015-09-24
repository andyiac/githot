package com.knight.arch;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.knight.arch.module.AppModule;
import com.knight.arch.module.Injector;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;
import com.umeng.analytics.MobclickAgent;

import java.util.Arrays;
import java.util.List;

import dagger.ObjectGraph;

/**
 * @author andyiac
 * @date 15-8-4
 * @web http://blog.andyiac.com/
 */
public class ClientApplication extends Application implements Injector {
    private static final String LOGGER_TAG = "<<=TAG=>>";

    private ObjectGraph objectGraph;

    public void onCreate() {
        super.onCreate();
        initStetho();
        initLogger();
        initDagger();
        initUmeng();
    }

    private void initUmeng() {
        MobclickAgent.openActivityDurationTrack(false);
    }


    private void initDagger() {
        objectGraph = ObjectGraph.create(getModules().toArray());
        objectGraph.inject(this);
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

    public List<Object> getModules() {
        return Arrays.<Object>asList(new AppModule(this));
    }

    @Override
    public void inject(Object target) {
        this.objectGraph.inject(target);
    }

    @Override
    public ObjectGraph plus(Object[] modules) {
        return objectGraph.plus(modules);
    }

    public ObjectGraph plus(Injector injector) {
        return this.objectGraph.plus(injector.getModules().toArray());
    }
}
