package com.knight.arch.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.knight.arch.R;
import com.knight.arch.module.HomeModule;
import com.knight.arch.ui.base.InjectableActivity;
import com.knight.arch.ui.fragment.HotReposMainFragment;
import com.knight.arch.ui.fragment.HotUsersMainFragment;
import com.knight.arch.ui.fragment.TrendingReposMainFragment;
import com.umeng.analytics.MobclickAgent;

import java.util.Arrays;
import java.util.List;

/**
 * @author andyiac
 * @date 15-8-4
 * @web http://blog.andyiac.com/
 */
public class MainActivity extends InjectableActivity {

    private HotUsersMainFragment hotUsersFragment;
    private HotReposMainFragment hotReposFragment;
    private TrendingReposMainFragment trendingReposMainFragment;

    private DrawerLayout mDrawerLayout;
    private ActionBar ab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
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
    protected int provideContentViewId() {
        return R.layout.activity_main;
    }

    private void initView() {

//        setStatusColor(android.R.color.transparent);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.id_main_toolbar);
        setSupportActionBar(mToolbar);


        ab = getSupportActionBar();
        if (ab != null) {
            ab.setHomeAsUpIndicator(R.mipmap.ic_menu);
            ab.setDisplayHomeAsUpEnabled(true);
        }

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }
        selectFragment(R.id.nav_user_china);

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

        if (id == android.R.id.home) {
            mDrawerLayout.openDrawer(GravityCompat.START);
            return true;
        }
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


    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        mDrawerLayout.closeDrawers();
                        selectFragment(menuItem.getItemId());
                        return true;
                    }
                });
    }

    private void selectFragment(int fragmentId) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        hideAllFragment(transaction);
        switch (fragmentId) {
            case R.id.nav_user_china:
                if (hotUsersFragment == null) {
                    hotUsersFragment = new HotUsersMainFragment();
                    // todo diff with transaction.replace() ?
                    transaction.add(R.id.id_main_frame_container, hotUsersFragment, "hotUser");
                } else {
                    transaction.show(hotUsersFragment);
                }
                ab.setTitle("Hot users");
                break;

            case R.id.nav_repositories:
                if (hotReposFragment == null) {
                    hotReposFragment = new HotReposMainFragment();
                    transaction.add(R.id.id_main_frame_container, hotReposFragment, "hotRepos");
                } else {
                    transaction.show(hotReposFragment);
                }
                ab.setTitle("Hot repos");
                break;

            case R.id.nav_trending_repos:
                if (trendingReposMainFragment == null) {
                    trendingReposMainFragment = new TrendingReposMainFragment();
                    transaction.add(R.id.id_main_frame_container, trendingReposMainFragment, "TrendingRepos");
                } else {
                    transaction.show(trendingReposMainFragment);
                }
                ab.setTitle("Trending repos");
                break;
        }
        transaction.commit();
    }

    private void hideAllFragment(FragmentTransaction transaction) {
        if (hotUsersFragment != null) {
            transaction.hide(hotUsersFragment);
        }
        if (hotReposFragment != null) {
            transaction.hide(hotReposFragment);
        }
        if (trendingReposMainFragment != null) {
            transaction.hide(trendingReposMainFragment);
        }
    }

}
