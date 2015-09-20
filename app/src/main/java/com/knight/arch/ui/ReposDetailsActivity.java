package com.knight.arch.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;

import com.alibaba.fastjson.JSON;
import com.knight.arch.R;
import com.knight.arch.module.ReposDetailsModule;
import com.knight.arch.ui.base.InjectableActivity;
import com.knight.arch.utils.L;

import java.util.Arrays;
import java.util.List;
public class ReposDetailsActivity extends InjectableActivity {
    private Parcelable mRepository;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_user_details;
    }

    @Override
    public List<Object> getModules() {

        return Arrays.<Object>asList(new ReposDetailsModule(this));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
        Intent intent = getIntent();
        mRepository = intent.getParcelableExtra("repos_data");
        L.json(JSON.toJSONString(mRepository));
    }

    private void initView() {
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle("Test!!!");
    }
}
