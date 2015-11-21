package com.knight.arch.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.knight.arch.R;
import com.knight.arch.api.ApiService;
import com.knight.arch.ui.adapter.HotReposDetailsListAdapterHolder;
import com.knight.arch.data.ReposKV;
import com.knight.arch.model.Repository;
import com.knight.arch.module.ReposDetailsModule;
import com.knight.arch.ui.base.InjectableActivity;
import com.knight.arch.ui.misc.DividerItemDecoration;
import com.knight.arch.utils.KeyBoardTools;
import com.knight.arch.utils.L;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import retrofit.client.Response;
import rx.Observer;
import rx.android.app.AppObservable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;

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

    private FloatingActionButton mStarFB;

    @Inject
    ApiService oAuthApiService;

    private Observer<Object> observable = new Observer<Object>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            L.e("ReposDetailsActivity === error ====>>>" + e.toString());
        }

        @Override
        public void onNext(Object o) {
            int status = ((Response) o).getStatus();
            L.e("ReposDetailsActivity====>> star repo");
            L.json((Response) o);
            if (status == 204) {
                setStarFBStatus(true);
            }
        }
    };


    // todo 这里的泛型直接使用 Response 就不行
    private Observer<Object> checkStarObservable = new Observer<Object>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(Object response) {
            int status = ((Response) response).getStatus();
            if (status == 204) {
                setStarFBStatus(true);
            }
        }
    };


    @Override
    protected int provideContentViewId() {
        return R.layout.activity_repos_details;
    }

    @Override
    public List<Object> getModules() {

        return Arrays.<Object>asList(new ReposDetailsModule(this));
    }

    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
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
        initStarFlowButtonStatus();
    }

    private void initStarFlowButtonStatus() {
        // 查看本人是否star过本项目
        String reposOwner = mRepository.getOwner().getLogin();
        String reposName = mRepository.getName();
        String accessToken = this.getSharedPreferences("githot_sp", MODE_PRIVATE).getString("token", "");

        if (TextUtils.isEmpty(accessToken)) {
            Toast.makeText(this, "please login first", Toast.LENGTH_SHORT).show();
            return;
        }


        // 检查状态
        AppObservable.bindActivity(ReposDetailsActivity.this, oAuthApiService.checkIfReposStarred(reposOwner, reposName, accessToken))
                .map(new Func1<Response, Object>() {
                    @Override
                    public Object call(Response response) {
                        L.json(response);
                        return response;
                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(checkStarObservable);
        // FB 实例
//        mStarFB

        //设置状态


    }

    private void initData() {
        mReposData.add(new ReposKV("repository name", mRepository.getName()));
        mReposData.add(new ReposKV("description", mRepository.getDescription()));
        mReposData.add(new ReposKV("is fork", "" + mRepository.isFork()));
        mReposData.add(new ReposKV("stars", String.valueOf(mRepository.getStargazers_count())));
        mReposData.add(new ReposKV("forks", String.valueOf(mRepository.getForks_count())));
        mReposData.add(new ReposKV("open issues", String.valueOf(mRepository.getOpen_issues_count())));
        mReposData.add(new ReposKV("language", String.valueOf(mRepository.getLanguage())));
        mReposData.add(new ReposKV("clone url", String.valueOf(mRepository.getClone_url())));
        mReposData.add(new ReposKV("owner", mRepository.getOwner().getLogin()));
        mReposData.add(new ReposKV("owner type", mRepository.getOwner().getType()));
        mReposData.add(new ReposKV("create at", mRepository.getCreated_at()));
        mReposData.add(new ReposKV("update at", mRepository.getUpdated_at()));
    }

    private void initView() {
        Toolbar mToolbar = (Toolbar) findViewById(R.id.id_repos_details_toobar);
        setSupportActionBar(mToolbar);

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setHomeAsUpIndicator(R.mipmap.ic_back_arrow);
            ab.setDisplayHomeAsUpEnabled(true);
            ab.setTitle(mRepository.getName());
        }

        adapter = new HotReposDetailsListAdapterHolder(this, mReposData);
        mRecyclerView = (RecyclerView) findViewById(R.id.id_repos_details_recycler_view);
        mRecyclerView.setAdapter(adapter);
        linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        float paddingStart = getResources().getDimension(R.dimen.repos_hot_divider_padding_start);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST, paddingStart, false));


        mStarFB = (FloatingActionButton) findViewById(R.id.id_repos_details_fb);
        mStarFB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //判断是否登录
                String token = ReposDetailsActivity.this.getSharedPreferences("githot_sp", MODE_PRIVATE).getString("token", "");
                if (TextUtils.isEmpty(token)) {
                    Toast.makeText(ReposDetailsActivity.this, "please login first", Toast.LENGTH_SHORT).show();
                    return;
                }

                AppObservable.bindActivity(ReposDetailsActivity.this, oAuthApiService.starRepo(mRepository.getOwner().getLogin(), mRepository.getName(), token))
                        .map(new Func1<Object, Object>() {
                            @Override
                            public Object call(Object object) {
                                return object;
                            }
                        })
                        .subscribeOn(AndroidSchedulers.mainThread())
                        .subscribe(observable);
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            KeyBoardTools.actionKey(KeyEvent.KEYCODE_BACK);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //更改FloatingButton 的状态
    private void setStarFBStatus(boolean status) {
        if (mStarFB == null) {
            mStarFB = (FloatingActionButton) findViewById(R.id.id_repos_details_fb);
        }

        if (status) {
            mStarFB.setImageResource(R.mipmap.ic_star);
        } else {
            mStarFB.setImageResource(R.mipmap.ic_unstar);
        }

        mStarFB.postInvalidate();

    }

}
