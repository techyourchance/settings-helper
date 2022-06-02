package com.techyourchance.settingshelper;


import androidx.annotation.NonNull;

public interface SettingDataEntryChangeListener<T> {

    void onValueChanged(@NonNull SettingDataEntry<T> settingDataEntry, @NonNull T value);

}
