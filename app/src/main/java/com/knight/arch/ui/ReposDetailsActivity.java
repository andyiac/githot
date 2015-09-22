package com.knight.arch.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.knight.arch.R;
import com.knight.arch.adapter.HotReposDetailsListAdapterHolder;
import com.knight.arch.data.ReposKV;
import com.knight.arch.model.Repository;
import com.knight.arch.module.ReposDetailsModule;
import com.knight.arch.ui.base.InjectableActivity;
import com.knight.arch.ui.misc.DividerItemDecoration;
import com.knight.arch.utils.L;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author andyiac
 * @date 15-8-4
 * @web http://blog.andyiac.com/
 */
public class ReposDetailsActivity extends InjectableActivity {
    private Repository mRepository;
    private RecyclerView mRecyclerView;
    private HotReposDetailsListAdapterHolder adapter;
    private LinearLayoutManager linearLayoutManager;
    private List<ReposKV> mReposData = new ArrayList<>();

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_repos_details;
    }

    @Override
    public List<Object> getModules() {

        return Arrays.<Object>asList(new ReposDetailsModule(this));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        mRepository = intent.getParcelableExtra("repos_data");
        L.json(JSON.toJSONString(mRepository));

        if (mRepository != null) {
            initData();
        }

        initView();
    }

    private void initData() {
        mReposData.add(new ReposKV("repository name", mRepository.getName()));
        mReposData.add(new ReposKV("description", mRepository.getDescription()));
        mReposData.add(new ReposKV("stars", String.valueOf(mRepository.getStargazers_count())));
        mReposData.add(new ReposKV("language", String.valueOf(mRepository.getLanguage())));
        mReposData.add(new ReposKV("clone url", String.valueOf(mRepository.getClone_url())));
        mReposData.add(new ReposKV("owner", mRepository.getOwner().getLogin()));
        mReposData.add(new ReposKV("owner type", mRepository.getOwner().getType()));
        mReposData.add(new ReposKV("create at", mRepository.getCreated_at()));
        mReposData.add(new ReposKV("update at", mRepository.getUpdated_at()));
    }

    private void initView() {

        adapter = new HotReposDetailsListAdapterHolder(this, mReposData);
        mRecyclerView = (RecyclerView) findViewById(R.id.id_repos_details_recycler_view);
        mRecyclerView.setAdapter(adapter);
        linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        float paddingStart = getResources().getDimension(R.dimen.repos_hot_divider_padding_start);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST, paddingStart, false));
    }

}
