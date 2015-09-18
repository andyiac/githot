package com.knight.arch.ui;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;

import com.knight.arch.R;
import com.knight.arch.module.UserDetailsModule;
import com.knight.arch.ui.base.InjectableActivity;

import java.util.Arrays;
import java.util.List;

/**
 * Created by summer on 15-9-9.
 */
public class ReposDetailsActivity extends InjectableActivity {
    @Override
    protected int provideContentViewId() {
        return R.layout.activity_user_details;
    }

    @Override
    public List<Object> getModules() {

        return Arrays.<Object>asList(new UserDetailsModule(this));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle("Test!!!");
    }
}
