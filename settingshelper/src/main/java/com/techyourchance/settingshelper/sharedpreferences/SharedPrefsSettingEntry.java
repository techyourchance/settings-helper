package com.techyourchance.settingshelper.sharedpreferences;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;

import com.techyourchance.settingshelper.SettingEntry;

/**
 * Implementation of {@link SettingEntry} backed by {@link SharedPreferences}
 */
/* pp */ abstract class SharedPrefsSettingEntry<T> extends SettingEntry<T> implements
        SharedPreferences.OnSharedPreferenceChangeListener {

    private final Object LOCK = new Object();

    /* pp */ final SharedPreferences preferences;

    /* pp */ SharedPrefsSettingEntry(
            SharedPreferences preferences,
            String key,
            T defaultValue
    ) {
        super(key, defaultValue);
        this.preferences = preferences;
    }

    @SuppressLint("ApplySharedPref")
    @Override
    public void remove() {
        preferences.edit().remove(key).commit();
    }


    @Override
    protected void onFirstListenerRegistered() {
        synchronized (LOCK) {
            preferences.registerOnSharedPreferenceChangeListener(this);
        }
    }

    @Override
    protected void onLastListenerUnregistered() {
        synchronized (LOCK) {
            preferences.unregisterOnSharedPreferenceChangeListener(this);
        }
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        notifyListeners(key, getValue());
    }

}
