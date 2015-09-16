package com.knight.arch.ui;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;

import com.knight.arch.R;
import com.knight.arch.module.HotReposModule;
import com.knight.arch.ui.base.InjectableActivity;
import com.knight.arch.ui.fragment.HotRepositoryFragment;

import java.util.Arrays;
import java.util.List;

/**
 * @author andyiac
 * @date 15-9-9
 * @web http://blog.andyiac.com
 * @github https://github.com/andyiac
 */
public class HotReposActivity extends InjectableActivity {
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
//        initView();

        initFragment();
    }

    private void initFragment() {
        getSupportFragmentManager().beginTransaction().add(R.id.id_hot_repos_container, new HotRepositoryFragment(), "hotfragment").commit();
    }

    private void initView() {
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle("Test!!!");
    }
}
