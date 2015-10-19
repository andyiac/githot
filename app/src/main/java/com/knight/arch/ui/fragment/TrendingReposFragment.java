package com.knight.arch.ui.fragment;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.knight.arch.R;
import com.knight.arch.ui.adapter.HotReposListAdapterHolder;
import com.knight.arch.api.ReposTrendingApiService;
import com.knight.arch.events.TrendingReposTimeSpanTextMsg;
import com.knight.arch.model.Repository;
import com.knight.arch.ui.ReposDetailsActivity;
import com.knight.arch.ui.base.InjectableFragment;
import com.knight.arch.ui.misc.DividerItemDecoration;
import com.knight.arch.utils.L;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;
import rx.Observer;
import rx.android.app.AppObservable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;

/**
 * @author andyiac
 * @date 15-10-17
 * @web http://blog.andyiac.com
 * @github https://github.com/andyiac
 */
@SuppressLint("ValidFragment")
public class TrendingReposFragment extends InjectableFragment {

    @Inject
    ReposTrendingApiService apiService;

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private List<Repository> mRepos = new ArrayList<>();
    private HotReposListAdapterHolder mAdapter;
    private String mTimeSpan = "daily";
    private String mLanguage;

    private LinearLayoutManager mLinearLayoutManager;
    private int lastVisibleItem;

    public TrendingReposFragment(String language) {
        this.mLanguage = language;
        EventBus.getDefault().register(this);
    }


    Observer<List<Repository>> repositoryObserver = new Observer<List<Repository>>() {
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
        public void onNext(List<Repository> repositoryRepositories) {
            setRefreshing(false);
            mRepos.addAll(repositoryRepositories);
            mAdapter.notifyDataSetChanged();

        }
    };


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fetchData(mLanguage, mTimeSpan);
    }

    private void fetchData(String language, String since) {
        setRefreshing(true);
        AppObservable.bindFragment(this, apiService.getTrendingRepositories(language, since))
                .map(new Func1<List<Repository>, List<Repository>>() {
                    @Override
                    public List<Repository> call(List<Repository> repositories) {
//                        L.json(JSON.toJSONString(repositories));
                        L.i("=====loaddata=="+mTimeSpan);
                        return repositories;
                    }
                }).subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(repositoryObserver);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.hot_repository_fragment, container, false);
        initView(view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        float paddingStart = getActivity().getResources().getDimension(R.dimen.repos_hot_divider_padding_start);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST, paddingStart, safeIsRtl()));

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
                mRepos.clear();
                mAdapter.notifyDataSetChanged();
                fetchData(mLanguage, mTimeSpan);
            }
        });

        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView = (RecyclerView) view.findViewById(R.id.id_hot_repos_recycler_view);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new HotReposListAdapterHolder(getActivity(), mRepos);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == mAdapter.getItemCount()) {

                    Toast.makeText(getActivity(), "There is no more data !", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = mLinearLayoutManager.findLastCompletelyVisibleItemPosition();
            }
        });


        mAdapter.setOnItemClickListener(new HotReposListAdapterHolder.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), ReposDetailsActivity.class);
                intent.putExtra("repos_data", mRepos.get(position));
                startActivity(intent);
            }
        });

    }

    private boolean safeIsRtl() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1 && isRtl();
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private boolean isRtl() {
        return getView().getLayoutDirection() == View.LAYOUT_DIRECTION_RTL;
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

    //EventBus
    public void onEvent(TrendingReposTimeSpanTextMsg msg) {

        mRepos.clear();
        mAdapter.notifyDataSetChanged();

        mTimeSpan = msg.getTimeSpan();
        L.d("timeSpan======>>"+ msg.getTimeSpan());

        if (this.isVisible()) {
            fetchData(mLanguage, mTimeSpan);
        }

    }
}
