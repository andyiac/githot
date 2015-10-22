package com.knight.arch.module;

import com.knight.arch.ui.MainActivity;
import com.knight.arch.ui.base.InjectableActivity;
import com.knight.arch.ui.fragment.LoginDialogFragment;
import com.knight.arch.ui.fragment.RankingReposFragment;
import com.knight.arch.ui.fragment.RankingUsersFragment;
import com.knight.arch.ui.fragment.TrendingReposFragment;

import dagger.Module;

/**
 * @author andyiac
 * @date 15-8-4
 * @web http://blog.andyiac.com/
 */

@Module(
        complete = false,
        injects = {
                MainActivity.class,
                RankingReposFragment.class,
                RankingUsersFragment.class,
                TrendingReposFragment.class,
                LoginDialogFragment.class
                //HotUsersMainFragment.class,
                //HotReposMainFragment.class,
        }
)
public class HomeModule {
    InjectableActivity activity;

    public HomeModule() {
    }

    public HomeModule(InjectableActivity activity) {
        this.activity = activity;
    }
}
