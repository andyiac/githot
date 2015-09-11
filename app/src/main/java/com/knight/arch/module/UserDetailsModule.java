package com.knight.arch.module;

import com.knight.arch.ui.UserDetailsActivity;
import com.knight.arch.ui.base.InjectableActivity;

import dagger.Module;

/**
 * @author andyiac
 * @date 15-8-4
 * @web http://blog.andyiac.com/
 */

@Module(
        complete = false,
        injects = {
                UserDetailsActivity.class
//                RankingFragment.class
        }
)
public class UserDetailsModule {
    InjectableActivity activity;

    public UserDetailsModule() {
    }

    public UserDetailsModule(InjectableActivity activity) {
        this.activity = activity;
    }
}
