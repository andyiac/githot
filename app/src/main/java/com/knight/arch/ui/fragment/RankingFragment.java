package com.knight.arch.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
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
import com.knight.arch.adapter.GitHubUserRankListAdapterHolder;
import com.knight.arch.api.ApiService;
import com.knight.arch.data.Users;
import com.knight.arch.model.User;
import com.knight.arch.ui.base.InjectableFragment;
import com.knight.arch.utils.L;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Observer;
import rx.android.app.AppObservable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;

/**
 * @author andyiac
 * @web http://blog.andyiac.com/
 */
public class RankingFragment extends InjectableFragment {

    @Inject
    ApiService apiService;

    @Inject
    Picasso picasso;

    private FragmentActivity mActivity;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private GitHubUserRankListAdapterHolder adapter;
    private List<User> mUsers = new ArrayList<>();
    Observer<Users<User>> userObserver = new Observer<Users<User>>() {
        @Override
        public void onCompleted() {
            setRefreshing(false);
        }

        @Override
        public void onError(Throwable e) {
            L.e("" + e);
            setRefreshing(false);
            Toast.makeText(getActivity(), "服务器开了小差稍后重试", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onNext(Users<User> userUsers) {
            setRefreshing(false);
            mUsers.addAll(userUsers.getItems());
            adapter.notifyDataSetChanged();
        }
    };
    private int lastVisibleItem;
    private LinearLayoutManager mLinearLayoutManager;


    public RankingFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //fetchData();
        fetchUsersInfo("china", 1);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ranking_fragment, container, false);
        initView(view);
        return view;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setHasFixedSize(true);
        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter.SetOnItemClickListener(new GitHubUserRankListAdapterHolder.OnItemClickListener() {

            @Override
            public void onItemClick(View v, int position) {
                // do something with position
            }
        });
    }

    @Override
    public void onAttach(Activity activity) {
        mActivity = (FragmentActivity) activity;
        super.onAttach(activity);
    }

    private void initView(View view) {
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);

        //设置卷内的颜色
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        //下拉刷新
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mUsers.clear();
                adapter.notifyDataSetChanged();

                fetchUsersInfo("china", 1);
            }
        });

        mRecyclerView = (RecyclerView) view.findViewById(R.id.id_recycler_view);
        adapter = new GitHubUserRankListAdapterHolder(mActivity, mUsers, picasso);

        //下拉刷新
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == adapter.getItemCount()) {
                    setRefreshing(true);
                    L.i("========onScrollStateChanged load more==========");
                    fetchUsersInfo("china", 1);

                }

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = mLinearLayoutManager.findLastVisibleItemPosition();
            }


        });
    }


    // fetch data from github api
    private void fetchUsersInfo(String location, int page_id) {
        setRefreshing(true);
        String query = "location:" + location;

        AppObservable.bindFragment(this, apiService.getUsersRxJava(query, page_id))
                .map(new Func1<Users<User>, Users<User>>() {
                    @Override
                    public Users<User> call(Users<User> userUsers) {
                        L.json(JSON.toJSONString(userUsers));
                        return userUsers;
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(userObserver);
    }

    public void setRefreshing(boolean refreshing) {
        if (swipeRefreshLayout == null) {
            return;
        }
        if (!refreshing) {
            swipeRefreshLayout.setRefreshing(false);
        } else {
            swipeRefreshLayout.setRefreshing(true);
        }
    }
}
