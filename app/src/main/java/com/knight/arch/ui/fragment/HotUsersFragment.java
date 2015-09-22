package com.knight.arch.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.knight.arch.R;
import com.knight.arch.module.Injector;
import com.knight.arch.ui.base.BaseFragment;
import com.knight.arch.ui.base.InjectableFragment;
import com.knight.arch.ui.fragment.RankingFragment;

import java.util.ArrayList;
import java.util.List;


//public class HotUsersFragment extends InjectableFragment {
public class HotUsersFragment extends BaseFragment{


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hot_users_main2, container,false);
        initView(view);
        return view;
    }

    private void initView(View view) {
/*
        Toolbar mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        final ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setHomeAsUpIndicator(R.mipmap.ic_menu);
            ab.setDisplayHomeAsUpEnabled(true);
        }

        mDrawerLayout = (DrawerLayout) view.findViewById(R.id.drawer_layout);

        NavigationView navigationView = (NavigationView) view.findViewById(R.id.nav_view);
        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }
*/


        ViewPager viewPager = (ViewPager) view.findViewById(R.id.hot_user_fragment_viewpager);
        if (viewPager != null) {
            setupViewPager(viewPager);
        }

        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.hot_users_tabs);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        if (viewPager != null) {
            tabLayout.setupWithViewPager(viewPager);
        }

    }

    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getActivity().getSupportFragmentManager());
        adapter.addFragment(new RankingFragment(), "China All");
        adapter.addFragment(new RankingFragment("language:Java"), "Java");
        adapter.addFragment(new RankingFragment("language:C"), "C");
        adapter.addFragment(new RankingFragment("language:Objective-C"), "Objective-C");
        adapter.addFragment(new RankingFragment("language:csharp"), "C#");
        adapter.addFragment(new RankingFragment("language:Python"), "Python");
        adapter.addFragment(new RankingFragment("language:PHP"), "PHP");
        adapter.addFragment(new RankingFragment("language:JavaScript"), "JavaScript");
        adapter.addFragment(new RankingFragment("language:Ruby"), "Ruby");
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
