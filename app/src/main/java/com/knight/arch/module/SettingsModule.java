package com.knight.arch.module;

import com.knight.arch.ui.SettingsActivity;
import com.knight.arch.ui.fragment.SettingsFragment;

import dagger.Module;


/**
 * @author andyiac
 * @date 15-9-16
 * @web http://blog.andyiac.com
 * @github https://github.com/andyiac
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
