package com.knight.arch.ui.fragment;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.widget.Toast;

import com.knight.arch.BuildConfig;
import com.knight.arch.R;
import com.knight.arch.api.FirService;
import com.knight.arch.model.FirVersion;
import com.knight.arch.module.Injector;
import com.umeng.analytics.MobclickAgent;

import javax.inject.Inject;

import de.psdev.licensesdialog.LicensesDialog;
import rx.Subscriber;
import rx.android.app.AppObservable;


/**
 * @author andyiac
 * @date 15-9-16
 * @web http://blog.andyiac.com
 * @github https://github.com/andyiac
 */
public class SettingsFragment extends PreferenceFragment {

    private boolean injected = false;

    @Inject
    FirService firService;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity();
        addPreferencesFromResource(R.xml.preference);

        findPreference(getString(R.string.pref_build_time))
                .setSummary(BuildConfig.BUILD_TIME);

        findPreference(getString(R.string.open_source_licence))
                .setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                    @Override
                    public boolean onPreferenceClick(Preference preference) {
                        new LicensesDialog.Builder(getActivity())
                                .setNotices(R.raw.notices)
                                .setIncludeOwnLicense(true)
                                .setThemeResourceId(R.style.custom_theme)
                                .build()
                                .show();
                        return false;
                    }
                });

        Preference checkVersionPref = findPreference(getString(R.string.pref_check_version));

        checkVersionPref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                checkVersion();
                return true;
            }
        });

        checkVersionPref.setSummary(getString(R.string.s_check_version, BuildConfig.VERSION_NAME));

    }

    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("SettingsFragment"); //统计页面
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("SettingsFragment");
    }

    private void checkVersion() {
        final ProgressDialog dialog = new ProgressDialog(getActivity());
        dialog.setMessage(getString(R.string.msg_checking_version));
        dialog.show();
        AppObservable.bindFragment(this, firService.checkVersion(BuildConfig.FIR_APPLICATION_ID, BuildConfig.FIR_API_TOKEN))
                .subscribe(new Subscriber<FirVersion>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }

                    @Override
                    public void onNext(FirVersion firVersion) {
                        if (firVersion.getVersion() > BuildConfig.VERSION_CODE) {
                            dialog.dismiss();
                            showNewVersionFoundDialog(firVersion);
                        } else {
                            Toast.makeText(getActivity(), R.string.msg_this_is_latest_version, Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                    }
                });
    }

    private void showNewVersionFoundDialog(final FirVersion newFirVersion) {
        new AlertDialog.Builder(getActivity())
                .setTitle(R.string.title_new_version_found)
                .setMessage(getString(R.string.msg_new_version_found, newFirVersion.getVersionShort(), newFirVersion.getVersion(), newFirVersion.getChangeLog()))
                .setPositiveButton(R.string.btn_dialog_update, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent downloadPageIntent = new Intent(Intent.ACTION_VIEW);
                        downloadPageIntent.setData(Uri.parse(newFirVersion.getUpdateUrl()));
                        getActivity().startActivity(downloadPageIntent);
                    }
                })
                .setNegativeButton(android.R.string.cancel, null)
                .create()
                .show();
    }

    @Override
    public void onAttach(Context ctx) {
        super.onAttach(ctx);
        if (!injected) {
            injected = true;
            Injector injector = (Injector) getActivity();
            injector.inject(this);
        }
    }
}
