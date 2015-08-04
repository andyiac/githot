package com.knight.arch.ui.base;

import android.os.Bundle;

import com.knight.arch.ClientApplication;
import com.knight.arch.module.Injector;

import dagger.ObjectGraph;

/**
 * @author andyiac
 * @date 15-8-4
 * @web http://blog.andyiac.com/
 */
public abstract class InjectableActivity extends BaseActivity implements Injector {
    protected ObjectGraph objectGraph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ClientApplication app = (ClientApplication) getApplication();
        objectGraph = app.plus(this);
        objectGraph.inject(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        objectGraph = null;
    }

    public void inject(Object target) {
        objectGraph.inject(target);
    }

    public ObjectGraph plus(Object[] modules) {
        return objectGraph.plus(modules);
    }

}
