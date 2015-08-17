package com.knight.arch.ui;

import android.os.Bundle;
import android.view.MenuItem;

import com.knight.arch.R;
import com.knight.arch.module.SettingsModule;
import com.knight.arch.ui.base.InjectableActivity;

import java.util.Arrays;
import java.util.List;

/**
 * @author andyiac
 * @date 15-8-15
 * @web http://blog.andyiac.com/
 */
public class SettingsActivity extends InjectableActivity {

    @Override
    public List<Object> getModules() {
        return Arrays.<Object>asList(new SettingsModule());
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_setting;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
