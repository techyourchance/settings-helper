package com.techyourchance.settingshelper.sharedpreferences;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;

/**
 * Implementation of {@link SharedPrefsDataEntry} for boolean
 */
@SuppressWarnings("unused")
/* pp */ class SharedPrefsBooleanEntry extends SharedPrefsDataEntry<Boolean> {

    /* pp */ SharedPrefsBooleanEntry(
            SharedPreferences preferences,
            SharedPrefsSettingEntriesHolder sharedPrefsSettingEntriesHolder,
            String key,
            Boolean defaultValue
    ) {
        super(preferences, sharedPrefsSettingEntriesHolder, key, defaultValue);
    }

    @Override
    public Boolean getValue() {
        return preferences.getBoolean(key, defaultValue == null ? false : defaultValue);
    }

    @SuppressLint("CommitPrefEdits")
    @Override
    public void setValue(Boolean value) {
        preferences.edit().putBoolean(key, value == null ? false : value).commit();
    }
}
