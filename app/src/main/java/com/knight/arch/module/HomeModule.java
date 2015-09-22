package com.knight.arch.module;

import com.knight.arch.ui.MainActivity;
import com.knight.arch.ui.base.InjectableActivity;
import com.knight.arch.ui.fragment.RankReposFragment;
import com.knight.arch.ui.fragment.RankingFragment;

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
                RankReposFragment.class,
                RankingFragment.class
                //HotUsersFragment.class,
                //HotReposFragment.class,
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
