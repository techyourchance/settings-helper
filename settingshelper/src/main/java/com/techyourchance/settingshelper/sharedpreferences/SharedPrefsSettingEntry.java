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
    /* pp */ final SharedPrefsSettingEntriesHolder sharedPrefsSettingEntriesHolder;

    /* pp */ SharedPrefsSettingEntry(
            SharedPreferences preferences,
            SharedPrefsSettingEntriesHolder sharedPrefsSettingEntriesHolder,
            String key,
            T defaultValue
    ) {
        super(key, defaultValue);
        this.preferences = preferences;
        this.sharedPrefsSettingEntriesHolder = sharedPrefsSettingEntriesHolder;
    }

    @SuppressLint("ApplySharedPref")
    @Override
    public void remove() {
        preferences.edit().remove(key).commit();
    }


    @Override
    protected void onFirstListenerRegistered() {
        synchronized (LOCK) {
            sharedPrefsSettingEntriesHolder.holdReference(this);
            preferences.registerOnSharedPreferenceChangeListener(this);
        }
    }

    @Override
    protected void onLastListenerUnregistered() {
        synchronized (LOCK) {
            preferences.unregisterOnSharedPreferenceChangeListener(this);
            sharedPrefsSettingEntriesHolder.releaseReference(this);
        }
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        notifyListeners(key, getValue());
    }

}
