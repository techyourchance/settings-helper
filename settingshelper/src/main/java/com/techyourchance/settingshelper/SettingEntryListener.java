package com.techyourchance.settingshelper;


import androidx.annotation.NonNull;

public interface SettingEntryListener<T> {

    void onValueChanged(@NonNull SettingEntry<T> settingDataEntry, @NonNull T value);

}
