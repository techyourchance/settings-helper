package com.techyourchance.settingshelper;

/**
 * Implementations of this interface can be used to provide different types of
 * {@link SettingEntry} objects.
 */
public interface SettingEntriesFactory {

     <T> SettingEntry<T> getSettingEntry(final Class<T> clazz, final String key, final T defaultValue);

}
