package com.techyourchance.settingshelper.sharedpreferences;

import android.content.SharedPreferences;

import com.techyourchance.settingshelper.SettingEntry;
import com.techyourchance.settingshelper.SettingEntriesFactory;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import androidx.annotation.Nullable;

public class SharedPrefsSettingEntriesFactory implements SettingEntriesFactory {

    private final Object STATIC_LOCK = new Object();
    private final Map<String, SharedPrefsSettingEntry<?>> settingEntries = new HashMap<>();

    private final SharedPreferences sharedPreferences;


    public SharedPrefsSettingEntriesFactory(SharedPreferences sharedPreferences){
        this.sharedPreferences = sharedPreferences;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> SettingEntry<T> getSettingEntry(Class<T> clazz, String key, @Nullable T defaultValue){
        synchronized (STATIC_LOCK) {
            SharedPrefsSettingEntry<T> settingEntry;
            if (settingEntries.containsKey(key)) {
                settingEntry = (SharedPrefsSettingEntry<T>) settingEntries.get(key);
            } else {
                settingEntry = newSettingEntry(clazz, key, defaultValue);
                settingEntries.put(key, settingEntry);
            }
            return settingEntry;
        }
    }

    @SuppressWarnings("unchecked")
    private <T> SharedPrefsSettingEntry<T> newSettingEntry(Class<T> clazz, String key, @Nullable T defaultValue){
        if (clazz == Boolean.class) return (SharedPrefsSettingEntry<T>) new SharedPrefsBooleanEntry(sharedPreferences, key, (Boolean) defaultValue);
        if (clazz == String.class) return (SharedPrefsSettingEntry<T>) new SharedPrefsStringEntry(sharedPreferences, key, (String) defaultValue);
        if (clazz == Integer.class) return (SharedPrefsSettingEntry<T>) new SharedPrefsIntegerEntry(sharedPreferences, key, (Integer) defaultValue);
        if (clazz == Long.class) return (SharedPrefsSettingEntry<T>) new SharedPrefsLongEntry(sharedPreferences, key, (Long) defaultValue);
        if (clazz == Double.class) return (SharedPrefsSettingEntry<T>) new SharedPrefsDoubleEntry(sharedPreferences, key, (Double) defaultValue);
        if (clazz == Float.class) return (SharedPrefsSettingEntry<T>) new SharedPrefsFloatEntry(sharedPreferences, key, (Float) defaultValue);

        throw new IllegalArgumentException("Class " + clazz.getSimpleName() + " is not supported");
    }


}
