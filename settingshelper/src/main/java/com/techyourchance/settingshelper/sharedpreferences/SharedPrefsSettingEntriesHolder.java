package com.techyourchance.settingshelper.sharedpreferences;


import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * This class is required because SharedPreferences objects don't store strong
 * references to their listeners, thus allowing them to be garbage collected if not referenced.
 * Our workaround: whenever there are listeners registered for a specific {@link SharedPrefsDataEntry},
 * we store a reference to that data entry inside this class.
 * NOTE: this class must be global and live for as long as the app is alive.
 */
public class SharedPrefsSettingEntriesHolder {

    private final Set<SharedPrefsDataEntry<?>> entries = new CopyOnWriteArraySet<>();

    public void holdReference(SharedPrefsDataEntry<?> entry) {
        entries.add(entry);
    }

    public void releaseReference(SharedPrefsDataEntry<?> entry) {
        entries.remove(entry);
    }

}