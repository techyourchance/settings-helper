package com.techyourchance.settingshelper.sharedpreferences;

import android.content.SharedPreferences;

import com.techyourchance.settingshelper.SettingsRemover;


public class SharedPrefsRemover implements SettingsRemover {
    private final SharedPreferences sharedPreferences;

    public SharedPrefsRemover(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    @Override
    public void removeAllSettings() {
        sharedPreferences.edit().clear().commit();
    }

}
