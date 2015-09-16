package com.knight.arch.ui.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.knight.arch.R;
import com.knight.arch.adapter.HotReposListAdapterHolder;
import com.knight.arch.api.ApiService;
import com.knight.arch.data.Repositories;
import com.knight.arch.model.Repository;
import com.knight.arch.ui.base.InjectableFragment;
import com.knight.arch.utils.L;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Observer;
import rx.android.app.AppObservable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;

/**
 * @author andyiac
 * @date 15-9-16
 * @web http://blog.andyiac.com
 * @github https://github.com/andyiac
 */
public class HotRepositoryFragment extends InjectableFragment {

    @Inject
    ApiService apiService;
    private int mPage = 1;

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private List<Repository> mRepos = new ArrayList<Repository>();
    private HotReposListAdapterHolder mAdapter;

    Observer<Repositories<Repository>> repositoryObserver = new Observer<Repositories<Repository>>() {
        @Override
        public void onCompleted() {
            setRefreshing(false);

        }

        @Override
        public void onError(Throwable e) {
            setRefreshing(false);
            Toast.makeText(getActivity(), "server unreachable try again later", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onNext(Repositories<Repository> repositoryRepositories) {
            setRefreshing(false);
            mRepos.addAll(repositoryRepositories.getItems());
            mAdapter.notifyDataSetChanged();

        }
    };
    private LinearLayoutManager mLinearLayoutManager;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fetchData("language:Java", 1);
    }

    private void fetchData(String query, int page) {

        AppObservable.bindFragment(this, apiService.getRepositories(query, page))
                .map(new Func1<Repositories<Repository>, Repositories<Repository>>() {
                    @Override
                    public Repositories<Repository> call(Repositories<Repository> repositoryRepositories) {
                        L.i(JSON.toJSONString(repositoryRepositories));

                        return repositoryRepositories;
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(repositoryObserver);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.hot_repository_fragment, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.id_hot_repos_swipe_refresh_layout);
        //设置卷内的颜色
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fetchData("language:java", 1);
            }
        });

        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView = (RecyclerView) view.findViewById(R.id.id_hot_repos_recycler_view);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new HotReposListAdapterHolder(getActivity(), mRepos);
        mRecyclerView.setAdapter(mAdapter);

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
