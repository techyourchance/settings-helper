package com.techyourchance.settingshelper;

/**
 * Implementations of this interface can be used to provide different types of
 * {@link SettingEntry} objects.
 * Please note that implementations CAN cache the returned values and reuse them on future
 * requests, but DON'T HAVE to do so. Therefore, it's not advisable to rely on the identity of the
 * returned object.
 */
public interface SettingEntriesFactory {

     /**
      *
      * @param clazz class of the stored value
      * @param key key that uniquely identifies this setting entry
      * @param defaultValue value returned by this setting entry by default
      * @param <T> type of the stored value
      * @return new or cached setting entry
      * @throws IllegalArgumentException if the type of the given type of the stored value
      *                                  isn't supported by the factory
      */
     <T> SettingEntry<T> getSettingEntry(final Class<T> clazz, final String key, final T defaultValue);

}
