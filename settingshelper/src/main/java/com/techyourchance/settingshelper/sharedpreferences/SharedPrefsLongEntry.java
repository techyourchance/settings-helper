package com.techyourchance.settingshelper.sharedpreferences;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;

/**
 * Implementation of {@link SharedPrefsSettingEntry} for long
 */
@SuppressWarnings("unused")
/* pp */ class SharedPrefsLongEntry extends SharedPrefsSettingEntry<Long> {

    /* pp */ SharedPrefsLongEntry(
            SharedPreferences preferences,
            String key,
            Long defaultValue
    ) {
        super(preferences, key, defaultValue);
    }

    @Override
    public Long getValue() {
        return preferences.getLong(key, defaultValue == null ? 0 : defaultValue);
    }


    @SuppressLint("ApplySharedPref")
    @Override
    public void setValue(Long value) {
        preferences.edit().putLong(key, value == null ? 0 : value).commit();
    }
}
