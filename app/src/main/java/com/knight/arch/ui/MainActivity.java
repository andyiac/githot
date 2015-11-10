package com.knight.arch.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.knight.arch.R;
import com.knight.arch.api.GitHubApiConstants;
import com.knight.arch.api.OAuthGitHubWebFlow;
import com.knight.arch.events.LoginUriMsg;
import com.knight.arch.model.AccessTokenResponse;
import com.knight.arch.ui.adapter.TrendingReposTimeSpanAdapter;
import com.knight.arch.events.TrendingReposTimeSpanTextMsg;
import com.knight.arch.module.HomeModule;
import com.knight.arch.ui.base.InjectableActivity;
import com.knight.arch.ui.fragment.HotReposMainFragment;
import com.knight.arch.ui.fragment.HotUsersMainFragment;
import com.knight.arch.ui.fragment.LoginDialogFragment;
import com.knight.arch.ui.fragment.TrendingReposMainFragment;
import com.knight.arch.utils.L;
import com.umeng.analytics.MobclickAgent;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;
import de.hdodenhof.circleimageview.CircleImageView;
import rx.Observer;
import rx.android.app.AppObservable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;

/**
 * @author andyiac
 * @date 15-8-4
 * @web http://blog.andyiac.com/
 */
public class MainActivity extends InjectableActivity {

    private HotUsersMainFragment hotUsersFragment;
    private HotReposMainFragment hotReposFragment;
    private TrendingReposMainFragment trendingReposMainFragment;

    private DrawerLayout mDrawerLayout;
    private ActionBar ab;
    private Spinner mTrendingSpinner;


    @Inject
    OAuthGitHubWebFlow oAuthGitHubWebFlow;


    Observer<AccessTokenResponse> tokenObservable = new Observer<AccessTokenResponse>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(AccessTokenResponse accessTokenResponse) {
            L.json(JSON.toJSONString(accessTokenResponse));

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        initView();
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
    protected int provideContentViewId() {
        return R.layout.activity_main;
    }

    private void initView() {

//        setStatusColor(android.R.color.transparent);

        Toolbar toolbar = (Toolbar) findViewById(R.id.id_main_toolbar);
        setSupportActionBar(toolbar);

        ab = getSupportActionBar();

        if (ab != null) {
            ab.setHomeAsUpIndicator(R.mipmap.ic_menu);
            ab.setDisplayHomeAsUpEnabled(true);
        }

        View spinnerContainer = LayoutInflater.from(this).inflate(R.layout.trending_repos_time_span, toolbar, false);
        ActionBar.LayoutParams lp = new ActionBar.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        toolbar.addView(spinnerContainer, lp);

        //the spinner use below link solution
        //https://blog.danielbetts.net/2015/01/02/material-design-spinner-toolbar-style-fix/
        mTrendingSpinner = (Spinner) spinnerContainer.findViewById(R.id.trending_time_spinner);
        mTrendingSpinner.setAdapter(new TrendingReposTimeSpanAdapter(this));
        mTrendingSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String ts = "daily";
                switch (position) {
                    case 0:
                        ts = "daily";
                        break;
                    case 1:
                        ts = "weekly";
                        break;
                    case 2:
                        ts = "monthly";
                        break;
                }

                TrendingReposTimeSpanTextMsg msg = new TrendingReposTimeSpanTextMsg();
                msg.setTimeSpan(ts);

                EventBus.getDefault().post(msg);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        if (navigationView != null) {
            setupDrawerContent(navigationView);
            navigationView.setCheckedItem(R.id.nav_user_china);

            initLoginView(navigationView);
        }

        selectFragment(R.id.nav_user_china);
    }

    private void initLoginView(NavigationView navigationView) {

        View headerView = navigationView.inflateHeaderView(R.layout.nav_header);

        TextView tvName = (TextView) headerView.findViewById(R.id.id_nav_header_uname);
        tvName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                L.d("=======tv click=====");
            }
        });

        CircleImageView imageAvatar = (CircleImageView) headerView.findViewById(R.id.id_nav_header_avatar);

        imageAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLoginInBrowser();
            }
        });
    }

    private void openLoginInBrowser() {
        // todo  to use HttpUrl.Builder
        String initialScope = "user,public_repo,repo";
        String url = "https://www.github.com/login/oauth/authorize?client_id=" +
                GitHubApiConstants.GITHUB_APP_CLIENT_ID + "&" + "scope=" + initialScope;
        LoginDialogFragment loginDialogFragment = new LoginDialogFragment(url);
        loginDialogFragment.show(getSupportFragmentManager(), "loginDialog");
    }

    //EventBus
    public void onEvent(LoginUriMsg msg) {

        // GitHub OAuth
        // https://developer.github.com/v3/oauth/#web-application-flow
        Uri uri = msg.getUrl();
        if (uri.getQueryParameter("code") != null) {

            // first step
            String code = uri.getQueryParameter("code");
            // second step
            getAccessToken(code);
        }
    }

    private void getAccessToken(String code) {
        String client_id = GitHubApiConstants.GITHUB_APP_CLIENT_ID;
        String client_secret = GitHubApiConstants.GITHUB_APP_CLIENT_SECRET;

        AppObservable.bindActivity(this, oAuthGitHubWebFlow.getOAuthToken(client_id, client_secret, code))
                .map(new Func1<AccessTokenResponse, AccessTokenResponse>() {
                    @Override
                    public AccessTokenResponse call(AccessTokenResponse accessTokenResponse) {
                        return accessTokenResponse;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(tokenObservable);
    }

    //=============================================================================
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == android.R.id.home) {
            mDrawerLayout.openDrawer(GravityCompat.START);
            return true;
        }
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public List<Object> getModules() {
        return Arrays.<Object>asList(new HomeModule(this));
    }


    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        mDrawerLayout.closeDrawers();
                        selectFragment(menuItem.getItemId());
                        return true;
                    }
                });
    }

    private void selectFragment(int fragmentId) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        hideAllFragment(transaction);
        switch (fragmentId) {
            case R.id.nav_user_china:
                if (hotUsersFragment == null) {
                    hotUsersFragment = new HotUsersMainFragment();
                    // todo diff with transaction.replace() ?
                    transaction.add(R.id.id_main_frame_container, hotUsersFragment, "hotUser");
                } else {
                    transaction.show(hotUsersFragment);
                }
                ab.setTitle("Hot users");
                break;

            case R.id.nav_repositories:
                if (hotReposFragment == null) {
                    hotReposFragment = new HotReposMainFragment();
                    transaction.add(R.id.id_main_frame_container, hotReposFragment, "hotRepos");
                } else {
                    transaction.show(hotReposFragment);
                }
                ab.setTitle("Hot repos");
                break;

            case R.id.nav_trending_repos:
                mTrendingSpinner.setVisibility(View.VISIBLE);
                if (trendingReposMainFragment == null) {
                    trendingReposMainFragment = new TrendingReposMainFragment();
                    transaction.add(R.id.id_main_frame_container, trendingReposMainFragment, "TrendingRepos");
                } else {
                    transaction.show(trendingReposMainFragment);
                }
                ab.setTitle("");
                break;
        }
        transaction.commit();
    }


    private void hideAllFragment(FragmentTransaction transaction) {

        if (mTrendingSpinner != null) {
            mTrendingSpinner.setVisibility(View.INVISIBLE);
        }

        if (hotUsersFragment != null) {
            transaction.hide(hotUsersFragment);
        }
        if (hotReposFragment != null) {
            transaction.hide(hotReposFragment);
        }
        if (trendingReposMainFragment != null) {
            transaction.hide(trendingReposMainFragment);
        }
    }

}
