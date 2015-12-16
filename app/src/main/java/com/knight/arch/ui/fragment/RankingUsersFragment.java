package com.knight.arch.ui.fragment;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
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
import com.knight.arch.ui.adapter.GitHubUserRankListAdapterHolder;
import com.knight.arch.api.ApiService;
import com.knight.arch.data.Users;
import com.knight.arch.model.User;
import com.knight.arch.ui.UserDetailsActivity;
import com.knight.arch.ui.base.InjectableFragment;
import com.knight.arch.ui.misc.DividerItemDecoration;
import com.knight.arch.utils.L;
import com.orhanobut.logger.Logger;
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
 * @date 15-9-16
 * @web http://blog.andyiac.com
 * @github https://github.com/andyiac
 */
@SuppressLint("ValidFragment")
public class RankingUsersFragment extends InjectableFragment {

    @Inject
    ApiService apiService;

    @Inject
    Picasso picasso;

    private FragmentActivity mActivity;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private GitHubUserRankListAdapterHolder adapter;
    private List<User> mUsers = new ArrayList<>();
    private int lastVisibleItem;
    private static int PER_PAGE = 30;
    private String mQuery = null;
    private LinearLayoutManager mLinearLayoutManager;

    // for debug
    private boolean D = false;

    Observer<Users<User>> userObserver = new Observer<Users<User>>() {
        @Override
        public void onCompleted() {
            setRefreshing(false);
            isLoadingMoreFlag = false;
        }

        @Override
        public void onError(Throwable e) {
            L.e("" + e);
            setRefreshing(false);
            isLoadingMoreFlag = false;
            Toast.makeText(getActivity(), "server unreachable try again later", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onNext(Users<User> userUsers) {
            setRefreshing(false);
            mUsers.addAll(userUsers.getItems());
            adapter.notifyDataSetChanged();
            isLoadingMoreFlag = false;
        }
    };
    private boolean isLoadingMoreFlag = false;

    public RankingUsersFragment() {
    }

    public RankingUsersFragment(String query) {
        this.mQuery = query;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        //        new DividerItemDecoration(getContext(), VERTICAL_LIST, dividerPaddingStart, safeIsRtl()));
        float paddingStart = getActivity().getResources().getDimension(R.dimen.user_ranking_divider_padding_start);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST, paddingStart, safeIsRtl()));
        adapter.SetOnItemClickListener(new GitHubUserRankListAdapterHolder.OnItemClickListener() {

            @Override
            public void onItemClick(View v, int position) {
                // do something with position
                Intent intent = new Intent();
                intent.setClass(getActivity(), UserDetailsActivity.class);
                intent.putExtra("user_name", mUsers.get(position).getLogin());
                startActivity(intent);
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

                int firstPage = 1;
                fetchUsersInfo("china", firstPage);
            }
        });

        mRecyclerView = (RecyclerView) view.findViewById(R.id.id_recycler_view);
        adapter = new GitHubUserRankListAdapterHolder(mActivity, mUsers, picasso);

        //上拉刷新
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == adapter.getItemCount()) {
                    setRefreshing(true);

                    int nextp = mUsers.size() / PER_PAGE + 1;
                    Logger.i("===onScrollStateChanged load more====>>" + nextp);
                    Logger.i("===onScrollStateChanged load more mUsers size====>>" + mUsers.size());
                    if (!isLoadingMoreFlag) {
                        fetchUsersInfo("china", nextp);
                        isLoadingMoreFlag = true;
                    }
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

        if (mQuery != null) {
            query = mQuery + "+followers:%3E500";
        }
        AppObservable.bindFragment(this, apiService.getUsersRxJava(query, page_id))
                .map(new Func1<Users<User>, Users<User>>() {
                    @Override
                    public Users<User> call(Users<User> userUsers) {
                        if (D) L.json(JSON.toJSONString(userUsers));
                        return userUsers;
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(userObserver);
    }


    private boolean safeIsRtl() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1 && isRtl();
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private boolean isRtl() {
        return getView().getLayoutDirection() == View.LAYOUT_DIRECTION_RTL;
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
