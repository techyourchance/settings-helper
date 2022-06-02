package com.techyourchance.settingshelper.sharedpreferences;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;


/**
 * Implementation of {@link SharedPrefsDataEntry} for integer
 */
@SuppressWarnings("unused")
/* pp */ class SharedPrefsIntegerEntry extends SharedPrefsDataEntry<Integer> {

    /* pp */ SharedPrefsIntegerEntry(
            SharedPreferences preferences,
            SharedPrefsSettingEntriesHolder sharedPrefsSettingEntriesHolder,
            String key,
            Integer defaultValue
    ) {
        super(preferences, sharedPrefsSettingEntriesHolder, key, defaultValue);
    }
    @Override
    public Integer getValue() {
        return preferences.getInt(key, defaultValue == null ? 0 : defaultValue);
    }

    @SuppressLint("CommitPrefEdits")
    @Override
    public void setValue(Integer value) {
        preferences.edit().putInt(key, value == null ? 0 : value).commit();
    }
}
