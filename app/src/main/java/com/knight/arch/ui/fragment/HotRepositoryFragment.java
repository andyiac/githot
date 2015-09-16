package com.knight.arch.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSON;
import com.knight.arch.R;
import com.knight.arch.api.ApiService;
import com.knight.arch.data.Repositories;
import com.knight.arch.model.Repository;
import com.knight.arch.ui.base.InjectableFragment;
import com.knight.arch.utils.L;

import javax.inject.Inject;

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


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fetchData();
    }

    private void fetchData() {

        AppObservable.bindFragment(this, apiService.getRepositories("language:Java", mPage)).map(new Func1<Repositories<Repository>, Repositories<Repository>>() {
            @Override
            public Repositories<Repository> call(Repositories<Repository> repositoryRepositories) {

                L.i(JSON.toJSONString(repositoryRepositories));
                return repositoryRepositories;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe();


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.hot_repository_fragment, container, false);
        return view;
    }
}
