package com.knight.arch.module;

import com.knight.arch.ui.ReposDetailsActivity;
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
                ReposDetailsActivity.class
                //RankingUsersFragment.class
        }
)
public class ReposDetailsModule {
    InjectableActivity activity;

    public ReposDetailsModule() {
    }

    public ReposDetailsModule(InjectableActivity activity) {
        this.activity = activity;
    }
}
