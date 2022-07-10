package com.techyourchance.settingshelper.sharedpreferences;

import android.content.SharedPreferences;

import com.techyourchance.settingshelper.SettingEntry;
import com.techyourchance.settingshelper.SettingEntriesFactory;

import androidx.annotation.Nullable;

public class SharedPrefsSettingEntriesFactory implements SettingEntriesFactory {

    private final SharedPreferences sharedPreferences;
    private final SharedPrefsSettingEntriesHolder sharedPrefsSettingEntriesHolder;

    public SharedPrefsSettingEntriesFactory(SharedPreferences sharedPreferences, SharedPrefsSettingEntriesHolder sharedPrefsSettingEntriesHolder){
        this.sharedPreferences = sharedPreferences;
        this.sharedPrefsSettingEntriesHolder = sharedPrefsSettingEntriesHolder;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> SettingEntry<T> getSettingEntry(Class<T> clazz, String key, @Nullable T defaultValue){

        if (clazz == Boolean.class) return (SettingEntry<T>) new SharedPrefsBooleanEntry(sharedPreferences, sharedPrefsSettingEntriesHolder, key, (Boolean) defaultValue);
        if (clazz == String.class) return (SettingEntry<T>) new SharedPrefsStringEntry(sharedPreferences, sharedPrefsSettingEntriesHolder, key, (String) defaultValue);
        if (clazz == Integer.class) return (SettingEntry<T>) new SharedPrefsIntegerEntry(sharedPreferences, sharedPrefsSettingEntriesHolder, key, (Integer) defaultValue);
        if (clazz == Long.class) return (SettingEntry<T>) new SharedPrefsLongEntry(sharedPreferences, sharedPrefsSettingEntriesHolder, key, (Long) defaultValue);
        if (clazz == Double.class) return (SettingEntry<T>) new SharedPrefsDoubleEntry(sharedPreferences, sharedPrefsSettingEntriesHolder, key, (Double) defaultValue);

        throw new IllegalArgumentException("Class " + clazz.getSimpleName() + " is not supported yet");

    }

}
