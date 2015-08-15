package com.knight.arch.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.knight.arch.R;
import com.knight.arch.module.Injector;

/**
 * @author andyiac
 * @date 15-8-15
 * @web http://blog.andyiac.com/
 */
public class SettingsFragment extends PreferenceFragment {

    private boolean injected = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity();
        addPreferencesFromResource(R.xml.preference);
        findPreference(getString(R.string.pref_build_time)).setSummary("2015年08月15日");
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (!injected) {
            injected = true;
            Injector injector = (Injector) getActivity();
            injector.inject(this);
        }
    }
}


