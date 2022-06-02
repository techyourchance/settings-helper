package com.techyourchance.settingshelper.sharedpreferences;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;


/**
 * Implementation of {@link SharedPrefsDataEntry} for float
 */
@SuppressWarnings("unused")
/* pp */ class SharedPrefsFloatEntry extends SharedPrefsDataEntry<Float> {

    /* pp */ SharedPrefsFloatEntry(
            SharedPreferences preferences,
            SharedPrefsSettingEntriesHolder sharedPrefsSettingEntriesHolder,
            String key,
            Float defaultValue
    ) {
        super(preferences, sharedPrefsSettingEntriesHolder, key, defaultValue);
    }

    @Override
    public Float getValue() {
        return preferences.getFloat(key, defaultValue == null ? 0f : defaultValue);
    }

    @SuppressLint("CommitPrefEdits")
    @Override
    public void setValue(Float value) {
        preferences.edit().putFloat(key, value == null ? 0 : value).commit();
    }
}
