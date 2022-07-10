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
public abstract class SettingEntry<T> {

    private final Set<SettingEntryListener<T>> listeners = new HashSet<>();

    protected final String key;
    protected final T defaultValue;

    public SettingEntry(String key, T defaultValue) {
        this.key = key;
        this.defaultValue = defaultValue;
    }

    public abstract T getValue();
    public abstract void setValue(T value);
    public abstract void remove();

    public final void registerListener(SettingEntryListener<T> listener){
        synchronized (listeners) {
            boolean modified = listeners.add(listener);
            if (modified && listeners.size() == 1) {
                onFirstListenerRegistered();
            }
        }
    }

    public final void unregisterListener(SettingEntryListener<T> listener){
        synchronized (listeners) {
            boolean modified = listeners.remove(listener);
            if (modified && listeners.isEmpty()) {
                onLastListenerUnregistered();
            }
        }
    }

    /**
     * This method can be overridden if any actions need to be performed after the first
     * listener is registered
     */
    protected void onFirstListenerRegistered() {}

    /**
     * This method can be overridden if any actions need to be performed after the last
     * listener is unregistered
     */
    protected void onLastListenerUnregistered() {}

    protected void notifyListeners(String key, T value){
        final List<SettingEntryListener<T>> listenersCopy;
        synchronized (listeners) {
            listenersCopy = new ArrayList<>(listeners);
        }

        for (SettingEntryListener<T> listener : listenersCopy){
            listener.onValueChanged(this, value);
        }
    }
}
