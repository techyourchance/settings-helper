package com.techyourchance.settingshelper.sharedpreferences;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;


/**
 * Implementation of {@link SharedPrefsDataEntry} for string
 */
@SuppressWarnings("unused")
/* pp */ class SharedPrefsStringEntry extends SharedPrefsDataEntry<String> {

    /* pp */ SharedPrefsStringEntry(
            SharedPreferences preferences,
            SharedPrefsSettingEntriesHolder sharedPrefsSettingEntriesHolder,
            String key,
            String defaultValue
    ) {
        super(preferences, sharedPrefsSettingEntriesHolder, key, defaultValue);
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
