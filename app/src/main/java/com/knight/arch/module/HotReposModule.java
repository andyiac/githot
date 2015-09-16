package com.knight.arch.module;

import com.knight.arch.ui.HotReposActivity;
import com.knight.arch.ui.UserDetailsActivity;
import com.knight.arch.ui.base.InjectableActivity;
import com.knight.arch.ui.fragment.HotRepositoryFragment;

import dagger.Module;

/**
 * @author andyiac
 * @date 15-8-4
 * @web http://blog.andyiac.com/
 */

@Module(
        complete = false,
        injects = {
                HotReposActivity.class,
                HotRepositoryFragment.class
        }
)
public class HotReposModule {
    InjectableActivity activity;

    public HotReposModule() {
    }

    public HotReposModule(InjectableActivity activity) {
        this.activity = activity;
    }
}
