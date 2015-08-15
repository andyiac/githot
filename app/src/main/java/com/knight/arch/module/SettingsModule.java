package com.knight.arch.module;

import com.knight.arch.ui.SettingsActivity;
import com.knight.arch.ui.fragment.SettingsFragment;

import dagger.Module;

/**
 * @author andyiac
 * @date 15-8-15
 * @web http://blog.andyiac.com/
 */
@Module(
        complete = false,
        overrides = true,
        injects = {
                SettingsActivity.class,
                SettingsFragment.class
        }
)
public class SettingsModule {
}
