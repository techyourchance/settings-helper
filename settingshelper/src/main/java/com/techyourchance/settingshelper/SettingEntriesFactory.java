package com.techyourchance.settingshelper;

import java.util.HashMap;
import java.util.Map;

/**
 * Subclasses of this abstract class can be used to provide different types of
 * {@link SettingEntry} objects.
 * Please note that this class will automatically cache the instances of
 * {@link SettingEntry} objects provided by subclasses and will return the same instance on
 * subsequent calls with the same key.
 */
public abstract class SettingEntriesFactory {

     private final Object LOCK = new Object();
     private final Map<String, SettingEntry<?>> settingEntries = new HashMap<>();

     /**
      *
      * @param clazz class of the stored value
      * @param key key that uniquely identifies this setting entry
      * @param defaultValue value returned by this setting entry by default
      * @param <T> type of the stored value
      * @return new or cached setting entry
      * @throws IllegalArgumentException if the given type of the stored value isn't supported by the factory
      */
     @SuppressWarnings("unchecked")
     public final <T> SettingEntry<T> getSettingEntry(final Class<T> clazz, final String key, final T defaultValue) {
          synchronized (LOCK) {
               SettingEntry<T> settingEntry;
               if (settingEntries.containsKey(key)) {
                    settingEntry = (SettingEntry<T>) settingEntries.get(key);
               } else {
                    settingEntry = newSettingEntry(clazz, key, defaultValue);
                    settingEntries.put(key, settingEntry);
               }
               return settingEntry;
          }
     }

     /**
      * Subclasses must override this method and instantiate the respective implementations of
      * {@link SettingEntry} class
      * @param clazz class of the stored value
      * @param key key that uniquely identifies this setting entry
      * @param defaultValue value returned by this setting entry by default
      * @param <T> type of the stored value
      * @return new setting entry
      * @throws IllegalArgumentException if the given type of the stored value isn't supported by the factory
      */
     protected abstract <T> SettingEntry<T> newSettingEntry(Class<T> clazz, String key, T defaultValue);

}
