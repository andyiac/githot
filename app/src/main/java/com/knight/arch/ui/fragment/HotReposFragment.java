package com.knight.arch.ui.fragment;

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
import com.knight.arch.ui.base.InjectableFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author andyiac
 * @date 15-9-9
 * @web http://blog.andyiac.com
 * @github https://github.com/andyiac
 */
//public class HotReposFragment extends InjectableFragment {
public class HotReposFragment extends BaseFragment{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_hot_pepos_main, container, false);
        View view = inflater.inflate(R.layout.fragment_hot_repos_main2, container, false);
        initView(view);
        return view;
    }


    private void initView(View view) {

//        ViewPager viewPager = (ViewPager) view.findViewById(R.id.main_activity_viewpager);
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.hot_repos_fragment_viewpager);
        if (viewPager != null) {
            setupViewPager(viewPager);
        }

//        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.main_activity_tabs);
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.hot_repos_tabs);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        if (viewPager != null) {
            tabLayout.setupWithViewPager(viewPager);
        }
    }

    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getActivity().getSupportFragmentManager());
        adapter.addFragment(new RankReposFragment("language:Java"), "Java");
        adapter.addFragment(new RankReposFragment("language:C"), "C");
        adapter.addFragment(new RankReposFragment("language:Objective-C"), "Objective-C");
        adapter.addFragment(new RankReposFragment("language:csharp"), "C#");
        adapter.addFragment(new RankReposFragment("language:Python"), "Python");
        adapter.addFragment(new RankReposFragment("language:PHP"), "PHP");
        adapter.addFragment(new RankReposFragment("language:JavaScript"), "JavaScript");
        adapter.addFragment(new RankReposFragment("language:Ruby"), "Ruby");
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
