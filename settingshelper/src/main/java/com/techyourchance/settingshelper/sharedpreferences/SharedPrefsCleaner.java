package com.techyourchance.settingshelper.sharedpreferences;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;

import com.techyourchance.settingshelper.SettingsCleaner;


public class SharedPrefsCleaner implements SettingsCleaner {
    private final SharedPreferences sharedPreferences;

    public SharedPrefsCleaner(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    @SuppressLint("ApplySharedPref")
    @Override
    public void cleanAllSettings() {
        sharedPreferences.edit().clear().commit();
    }

}
