package com.knight.arch.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

import com.knight.arch.R;
import com.knight.arch.module.HotReposModule;
import com.knight.arch.ui.base.InjectableActivity;
import com.knight.arch.ui.fragment.HotRepositoryFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author andyiac
 * @date 15-9-9
 * @web http://blog.andyiac.com
 * @github https://github.com/andyiac
 */
public class HotReposActivity extends InjectableActivity {

    private DrawerLayout mDrawerLayout;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_hot_pepos;
    }

    @Override
    public List<Object> getModules() {
        return Arrays.<Object>asList(new HotReposModule(this));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        final ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setHomeAsUpIndicator(R.mipmap.ic_menu);
            ab.setDisplayHomeAsUpEnabled(true);
        }

        ViewPager viewPager = (ViewPager) findViewById(R.id.main_activity_viewpager);
        if (viewPager != null) {
            setupViewPager(viewPager);
        }

        TabLayout tabLayout = (TabLayout) findViewById(R.id.main_activity_tabs);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        if (viewPager != null) {
            tabLayout.setupWithViewPager(viewPager);
        }
    }

    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getSupportFragmentManager());
        adapter.addFragment(new HotRepositoryFragment("language:Java"), "Java");
        adapter.addFragment(new HotRepositoryFragment("language:C"), "C");
        adapter.addFragment(new HotRepositoryFragment("language:Objective-C"), "Objective-C");
        adapter.addFragment(new HotRepositoryFragment("language:C#"), "C#");
        adapter.addFragment(new HotRepositoryFragment("language:Python"), "Python");
        adapter.addFragment(new HotRepositoryFragment("language:PHP"), "PHP");
        adapter.addFragment(new HotRepositoryFragment("language:JavaScript"), "JavaScript");
        adapter.addFragment(new HotRepositoryFragment("language:Ruby"), "Ruby");
        viewPager.setAdapter(adapter);
    }


    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public Adapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {

            return mFragmentTitles.get(position);
        }
    }
}
