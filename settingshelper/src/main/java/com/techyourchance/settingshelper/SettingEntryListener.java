package com.techyourchance.settingshelper;


import androidx.annotation.NonNull;

public interface SettingEntryListener<T> {

    /**
     * Called when the value of a {@link SettingEntry} changes
     * @param settingEntry the changed {@link SettingEntry}
     * @param value new value
     */
    void onValueChanged(@NonNull SettingEntry<T> settingEntry, @NonNull T value);

}
