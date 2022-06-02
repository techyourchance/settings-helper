package com.techyourchance.settingshelper.sharedpreferences;

import android.content.SharedPreferences;

import com.techyourchance.settingshelper.SettingDataEntry;
import com.techyourchance.settingshelper.SettingsEntryFactory;

import androidx.annotation.Nullable;

public class SharedPrefsSettingsEntryFactory extends SettingsEntryFactory {

    private final SharedPreferences sharedPreferences;
    private final SharedPrefsSettingEntriesHolder sharedPrefsSettingEntriesHolder;

    public SharedPrefsSettingsEntryFactory(SharedPreferences sharedPreferences, SharedPrefsSettingEntriesHolder sharedPrefsSettingEntriesHolder){
        this.sharedPreferences = sharedPreferences;
        this.sharedPrefsSettingEntriesHolder = sharedPrefsSettingEntriesHolder;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> SettingDataEntry<T> getDataEntry(Class<T> clazz, String key, @Nullable T defaultValue){

        if (clazz == Boolean.class) return (SettingDataEntry<T>) new SharedPrefsBooleanEntry(sharedPreferences, sharedPrefsSettingEntriesHolder, key, (Boolean) defaultValue);
        if (clazz == String.class) return (SettingDataEntry<T>) new SharedPrefsStringEntry(sharedPreferences, sharedPrefsSettingEntriesHolder, key, (String) defaultValue);
        if (clazz == Integer.class) return (SettingDataEntry<T>) new SharedPrefsIntegerEntry(sharedPreferences, sharedPrefsSettingEntriesHolder, key, (Integer) defaultValue);
        if (clazz == Long.class) return (SettingDataEntry<T>) new SharedPrefsLongEntry(sharedPreferences, sharedPrefsSettingEntriesHolder, key, (Long) defaultValue);
        if (clazz == Double.class) return (SettingDataEntry<T>) new SharedPrefsDoubleEntry(sharedPreferences, sharedPrefsSettingEntriesHolder, key, (Double) defaultValue);

        throw new IllegalArgumentException("Class " + clazz.getSimpleName() + " is not supported yet");

    }

}
