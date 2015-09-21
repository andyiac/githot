package com.knight.arch.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.knight.arch.R;
import com.knight.arch.adapter.UserDetailsListAdapterHolder;
import com.knight.arch.api.ApiService;
import com.knight.arch.model.Repository;
import com.knight.arch.module.UserDetailsModule;
import com.knight.arch.ui.base.InjectableActivity;
import com.knight.arch.ui.misc.DividerItemDecoration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import rx.Observer;
import rx.android.app.AppObservable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;

public class UserDetailsActivity extends InjectableActivity {

    private RecyclerView mRecyclerView;
    private UserDetailsListAdapterHolder mAdapter;
    private List<Repository> mData = new ArrayList<>();
    private LinearLayoutManager mLinearLayoutManager;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private String mCurrentUser = "";
    private int mPage = 1;

    @Inject
    ApiService apiService;
    private Observer<List<Repository>> repositoryObserver = new Observer<List<Repository>>() {
        @Override
        public void onCompleted() {
            setRefreshing(false);
        }

        @Override
        public void onError(Throwable e) {
            setRefreshing(false);
            Toast.makeText(UserDetailsActivity.this, "server unreachable try again later", Toast.LENGTH_SHORT).show();
        }


        @Override
        public void onNext(List<Repository> repositories) {
            mPage = mPage + 1;
            setRefreshing(false);
            mData.addAll(repositories);
            mAdapter.notifyDataSetChanged();
        }
    };

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
        Intent intent = getIntent();
        mCurrentUser = intent.getStringExtra("user_name");
        initView();
        fetchData(mCurrentUser);
    }


    private void initView() {
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.id_hot_repos_swipe_refresh_layout);
        //设置卷内的颜色
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mData.clear();
                mAdapter.notifyDataSetChanged();
                fetchData(mCurrentUser);
            }
        });

        mAdapter = new UserDetailsListAdapterHolder(this, mData);

        mRecyclerView = (RecyclerView) findViewById(R.id.id_activity_user_details);
        mRecyclerView.setAdapter(mAdapter);
        mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        float paddingStart = getResources().getDimension(R.dimen.repos_hot_divider_padding_start);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST, paddingStart, false));
    }

    private void fetchData(String userName) {
        AppObservable.bindActivity(this, apiService.getUserRepositories(userName))
                .map(new Func1<List<Repository>, List<Repository>>() {
                    @Override
                    public List<Repository> call(List<Repository> repositoryRepositories) {
                        return repositoryRepositories;
                    }
                }).subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(repositoryObserver);
    }


    public void setRefreshing(boolean refreshing) {
        if (mSwipeRefreshLayout == null) {
            return;
        }
        if (!refreshing) {
            mSwipeRefreshLayout.setRefreshing(false);
        } else {
            mSwipeRefreshLayout.setRefreshing(true);
        }
    }

}

