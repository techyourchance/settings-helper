package com.techyourchance.settingshelper.sharedpreferences;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;

/**
 * Implementation of {@link SharedPrefsSettingEntry} for double
 */
@SuppressWarnings("unused")
/* pp */ class SharedPrefsDoubleEntry extends SharedPrefsSettingEntry<Double> {

    /* pp */ SharedPrefsDoubleEntry(
            SharedPreferences preferences,
            SharedPrefsSettingEntriesHolder sharedPrefsSettingEntriesHolder,
            String key,
            Double defaultValue
    ) {
        super(preferences, sharedPrefsSettingEntriesHolder, key, defaultValue);
    }

    @Override
    public Double getValue() {
        final double defValD = defaultValue == null ? 0 : defaultValue;
        return Double.longBitsToDouble(preferences.getLong(key, Double.doubleToLongBits(defValD)));
    }

    @SuppressLint("CommitPrefEdits")
    @Override
    public void setValue(Double value) {
        final double valD = value == null ? 0 : value;
        preferences.edit().putLong(key, Double.doubleToLongBits(valD)).commit();
    }
}
