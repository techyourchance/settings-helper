package com.techyourchance.settingshelper.sharedpreferences;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;


/**
 * Implementation of {@link SharedPrefsSettingEntry} for integer
 */
@SuppressWarnings("unused")
/* pp */ class SharedPrefsIntegerEntry extends SharedPrefsSettingEntry<Integer> {

    /* pp */ SharedPrefsIntegerEntry(
            SharedPreferences preferences,
            String key,
            Integer defaultValue
    ) {
        super(preferences, key, defaultValue);
    }
    @Override
    public Integer getValue() {
        return preferences.getInt(key, defaultValue == null ? 0 : defaultValue);
    }

    @SuppressLint("ApplySharedPref")
    @Override
    public void setValue(Integer value) {
        preferences.edit().putInt(key, value == null ? 0 : value).commit();
    }
}
