package com.knight.arch.ui;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;

import com.knight.arch.R;
import com.knight.arch.module.SettingsModule;
import com.knight.arch.ui.base.InjectableActivity;
import com.knight.arch.utils.KeyBoardTools;
import com.knight.arch.utils.L;
import com.umeng.analytics.MobclickAgent;

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

    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        initView();
        setStatusColor(android.R.color.transparent);
    }

    private void initView() {
        Toolbar mToolbar = (Toolbar) findViewById(R.id.hot_repos_toolbar);
        setSupportActionBar(mToolbar);

        final ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setHomeAsUpIndicator(R.mipmap.ic_back_arrow);
            ab.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            KeyBoardTools.actionKey(KeyEvent.KEYCODE_BACK);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
