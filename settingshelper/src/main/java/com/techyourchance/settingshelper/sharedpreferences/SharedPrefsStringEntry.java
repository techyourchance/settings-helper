package com.techyourchance.settingshelper.sharedpreferences;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;


/**
 * Implementation of {@link SharedPrefsSettingEntry} for strings
 */
@SuppressWarnings("unused")
/* pp */ class SharedPrefsStringEntry extends SharedPrefsSettingEntry<String> {

    /* pp */ SharedPrefsStringEntry(
            SharedPreferences preferences,
            String key,
            String defaultValue
    ) {
        super(preferences, key, defaultValue);
    }

    @Override
    public String getValue() {
        return preferences.getString(key, defaultValue);
    }

    @SuppressLint("CommitPrefEdits")
    @Override
    public void setValue(String value) {
        preferences.edit().putString(key, value).commit();
    }
}
