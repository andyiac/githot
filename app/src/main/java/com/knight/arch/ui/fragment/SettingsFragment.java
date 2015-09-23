package com.knight.arch.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;

import com.knight.arch.R;
import com.knight.arch.module.Injector;

import de.psdev.licensesdialog.LicensesDialog;


/**
 * @author andyiac
 * @date 15-9-16
 * @web http://blog.andyiac.com
 * @github https://github.com/andyiac
 */
public class SettingsFragment extends PreferenceFragment {

    private boolean injected = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity();
        addPreferencesFromResource(R.xml.preference);
        findPreference(getString(R.string.pref_build_time)).setSummary("2015年08月15日");
        findPreference(getString(R.string.open_source_licence)).setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                new LicensesDialog.Builder(getActivity())
                        .setNotices(R.raw.notices)
                        .setIncludeOwnLicense(true)
                        .setThemeResourceId(R.style.custom_theme)
//                        .setDividerColorId(R.color.dialog_custom_divider_color)
                        .build()
                        .show();
                return false;
            }
        });

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


