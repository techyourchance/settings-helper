package com.techyourchance.settingshelper.sharedpreferences;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;


/**
 * Implementation of {@link SharedPrefsSettingEntry} for float
 */
@SuppressWarnings("unused")
/* pp */ class SharedPrefsFloatEntry extends SharedPrefsSettingEntry<Float> {

    /* pp */ SharedPrefsFloatEntry(
            SharedPreferences preferences,
            String key,
            Float defaultValue
    ) {
        super(preferences, key, defaultValue);
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
