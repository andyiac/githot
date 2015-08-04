package com.knight.arch.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.knight.arch.R;
import com.knight.arch.adapter.ListAdapterHolder;
import com.knight.arch.api.ApiClient;
import com.knight.arch.model.AllPersonlInfos;
import com.knight.arch.model.PersonInfo;
import com.knight.arch.utils.L;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * @author andyiac
 * @web http://blog.andyiac.com/
 */
public class RankingFragment extends Fragment {

    private FragmentActivity mActivity;

    private SwipeRefreshLayout swipeRefreshLayout;

    private RecyclerView mRecyclerView;
    private ListAdapterHolder adapter;

    private List<PersonInfo> mPersonInfos = new ArrayList<PersonInfo>();
    private int lastVisibleItem;
    private LinearLayoutManager mLinearLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ranking_fragment, container, false);
        initView(view);
        fetchData();
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
        adapter.SetOnItemClickListener(new ListAdapterHolder.OnItemClickListener() {

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
                mPersonInfos.clear();
                adapter.notifyDataSetChanged();

                fetchData();
            }
        });

        mRecyclerView = (RecyclerView) view.findViewById(R.id.id_recycler_view);
        adapter = new ListAdapterHolder(mActivity, mPersonInfos);

        //下拉刷新
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == adapter.getItemCount()) {
                    setRefreshing(true);
                    L.i("========onScrollStateChanged load more==========");
                    fetchData();
                }

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = mLinearLayoutManager.findLastVisibleItemPosition();
            }


        });
    }


    private void fetchData() {
        setRefreshing(true);
        ApiClient.getTestDemoApiClient().getData2(new Callback<AllPersonlInfos>() {
            @Override
            public void success(AllPersonlInfos personInfos, Response response) {
                mPersonInfos.addAll(personInfos.getData());
                adapter.notifyDataSetChanged();
                setRefreshing(false);
            }

            @Override
            public void failure(RetrofitError error) {
                L.e(error.toString());
                setRefreshing(false);
            }
        });
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
