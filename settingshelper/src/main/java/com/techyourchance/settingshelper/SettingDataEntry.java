package com.techyourchance.settingshelper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This class represents a single application setting entry. The specifics about how exactly this
 * setting entry is implemented and persisted should be implemented by sub-classes.
 * @param <T> data type of this setting entry
 */
public abstract class SettingDataEntry<T> {

    private final Set<SettingDataEntryChangeListener<T>> listeners = new HashSet<>();

    protected final String key;
    protected final T defaultValue;

    public SettingDataEntry(String key, T defaultValue) {
        this.key = key;
        this.defaultValue = defaultValue;
    }

    public abstract T getValue();
    public abstract void setValue(T value);
    public abstract void remove();

    public final void registerListener(SettingDataEntryChangeListener<T> listener){
        synchronized (listeners) {
            boolean modified = listeners.add(listener);
            if (modified && listeners.size() == 1) {
                onFirstListenerRegistered();
            }
        }
    }

    public final void unregisterListener(SettingDataEntryChangeListener<T> listener){
        synchronized (listeners) {
            boolean modified = listeners.remove(listener);
            if (modified && listeners.isEmpty()) {
                onLastListenerUnregistered();
            }
        }
    }

    /**
     * This "hook" method can be overridden if any actions need to be performed when first listener
     * being registered
     */
    protected void onFirstListenerRegistered() {}

    /**
     * This "hook" method can be overridden if any actions need to be performed when last listener
     * being unregistered
     */
    protected void onLastListenerUnregistered() {}

    protected void notifyListeners(String key, T value){
        final List<SettingDataEntryChangeListener<T>> listenersCopy;
        synchronized (listeners) {
            listenersCopy = new ArrayList<>(listeners);
        }

        for (SettingDataEntryChangeListener<T> listener : listenersCopy){
            listener.onValueChanged(this, value);
        }
    }
}
