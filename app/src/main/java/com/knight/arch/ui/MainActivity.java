package com.knight.arch.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;

import com.knight.arch.R;
import com.knight.arch.module.HomeModule;
import com.knight.arch.ui.base.InjectableActivity;
import com.knight.arch.ui.fragment.RankingFragment;

import java.util.Arrays;
import java.util.List;


public class MainActivity extends InjectableActivity {

    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_main;
    }


    private void initView() {
        RankingFragment rankingFragment = new RankingFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.id_frame_container, rankingFragment).commit();

    }


    //=============================================================================
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public List<Object> getModules() {
        return Arrays.<Object>asList(new HomeModule(this));
    }


}
