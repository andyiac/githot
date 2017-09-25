package com.knight.arch.ui.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.knight.arch.R;
import com.knight.arch.ui.base.BaseFragment;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.List;

/**
 * @author andyiac
 * @date 15-10-16
 * @web http://blog.andyiac.com
 * @github https://github.com/andyiac
 */
@SuppressLint("ValidFragment")
public class TrendingReposMainFragment extends BaseFragment {

    private TabLayout mTabLayout;

    public TrendingReposMainFragment() {
    }


    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("TrendingReposMainFragment"); //统计页面
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("TrendingReposMainFragment");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // setStatusColor(android.R.color.transparent);
        View view = inflater.inflate(R.layout.fragment_trending_repos_main, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {

        ViewPager viewPager = (ViewPager) view.findViewById(R.id.trending_repos_fragment_viewpager);
        if (viewPager != null) {
            setupViewPager(viewPager);
        }

        mTabLayout = (TabLayout) view.findViewById(R.id.trending_repos_tabs);
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        if (viewPager != null) {
            mTabLayout.setupWithViewPager(viewPager);
        }
    }

    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getActivity().getSupportFragmentManager());
        adapter.addFragment(new TrendingReposFragment("java"), "Java");
        adapter.addFragment(new TrendingReposFragment("c"), "C");
        adapter.addFragment(new TrendingReposFragment("objective-c"), "Objective-C");
        adapter.addFragment(new TrendingReposFragment("swift"), "Swift");
        adapter.addFragment(new TrendingReposFragment("csharp"), "C#");
        adapter.addFragment(new TrendingReposFragment("python"), "Python");
        adapter.addFragment(new TrendingReposFragment("php"), "PHP");
        adapter.addFragment(new TrendingReposFragment("javascript"), "JavaScript");
        adapter.addFragment(new TrendingReposFragment("ruby"), "Ruby");
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
