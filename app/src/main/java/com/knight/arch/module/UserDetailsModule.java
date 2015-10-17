package com.knight.arch.module;

import com.knight.arch.ui.UserDetailsActivity;
import com.knight.arch.ui.base.InjectableActivity;

import dagger.Module;


/**
 * @author andyiac
 * @date 15-9-16
 * @web http://blog.andyiac.com
 * @github https://github.com/andyiac
 */

@Module(
        complete = false,
        injects = {
                UserDetailsActivity.class
//                RankingUsersFragment.class
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
