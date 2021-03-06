package com.techyourchance.settingshelper.sharedpreferences;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;

/**
 * Implementation of {@link SharedPrefsSettingEntry} for boolean
 */
@SuppressWarnings("unused")
/* pp */ class SharedPrefsBooleanEntry extends SharedPrefsSettingEntry<Boolean> {

    /* pp */ SharedPrefsBooleanEntry(
            SharedPreferences preferences,
            String key,
            Boolean defaultValue
    ) {
        super(preferences, key, defaultValue);
    }

    @Override
    public Boolean getValue() {
        return preferences.getBoolean(key, defaultValue == null ? false : defaultValue);
    }

    @SuppressLint("ApplySharedPref")
    @Override
    public void setValue(Boolean value) {
        preferences.edit().putBoolean(key, value == null ? false : value).commit();
    }
}
