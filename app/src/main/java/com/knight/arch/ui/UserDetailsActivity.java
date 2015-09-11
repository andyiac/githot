package com.knight.arch.ui;

import com.knight.arch.R;
import com.knight.arch.module.UserDetailsModule;
import com.knight.arch.ui.base.InjectableActivity;

import java.util.Arrays;
import java.util.List;

/**
 * Created by summer on 15-9-9.
 */
public class UserDetailsActivity extends InjectableActivity {
    @Override
    protected int provideContentViewId() {
        return R.layout.activity_user_details;
    }

    @Override
    public List<Object> getModules() {
        return Arrays.<Object>asList(new UserDetailsModule(this));
    }
}
