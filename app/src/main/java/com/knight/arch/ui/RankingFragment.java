package com.knight.arch.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.knight.arch.R;
import com.knight.arch.api.ApiClient;
import com.knight.arch.model.AllPersonlInfos;
import com.knight.arch.utils.L;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * @author andyiac
 * @web http://blog.andyiac.com/
 */
public class RankingFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        L.i("====================");
        View view = inflater.inflate(R.layout.ranking_fragment, container, false);
        return view;
    }


    public void onTestClick(View view) {
        ApiClient.getTestDemoApiClient().getData2(new Callback<AllPersonlInfos>() {
            @Override
            public void success(AllPersonlInfos personInfos, Response response) {
                Log.e("TAG_success", personInfos.getData().get(1).getUsername());
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("TAG_failure", error.toString());
            }
        });
    }

    @Override
    public void onRefresh() {
        L.i("====================");
    }
}
