package com.techyourchance.settingshelper.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.techyourchance.settingshelper.SettingEntry;
import com.techyourchance.settingshelper.SettingEntryListener;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@RunWith(RobolectricTestRunner.class)
public class SharedPrefsSettingsTest {

    // region constants ----------------------------------------------------------------------------
    private static final String KEY_BOOLEAN_SETTING_ENTRY = "KEY_BOOLEAN_SETTING_ENTRY";
    private static final Boolean DEFAULT_BOOLEAN_SETTING_ENTRY = true;
    private static final String KEY_STRING_SETTING_ENTRY = "KEY_STRING_SETTING_ENTRY";
    private static final String DEFAULT_STRING_SETTING_ENTRY = "default";
    private static final String KEY_INT_SETTING_ENTRY = "KEY_INT_SETTING_ENTRY";
    private static final int DEFAULT_INT_SETTING_ENTRY = 3;
    private static final String KEY_LONG_SETTING_ENTRY = "KEY_LONG_SETTING_ENTRY";
    private static final long DEFAULT_LONG_SETTING_ENTRY = 3;
    private static final String KEY_DOUBLE_SETTING_ENTRY = "KEY_DOUBLE_SETTING_ENTRY";
    private static final double DEFAULT_DOUBLE_SETTING_ENTRY = 3.0;
    private static final String KEY_FLOAT_SETTING_ENTRY = "KEY_FLOAT_SETTING_ENTRY";
    private static final float DEFAULT_FLOAT_SETTING_ENTRY = 3.0f;
    // endregion constants -------------------------------------------------------------------------

    // region helper fields ------------------------------------------------------------------------
    // endregion helper fields ---------------------------------------------------------------------

    SharedPrefsSettingEntriesFactory SUT;

    private SettingEntry<Boolean> booleanSettingEntry;
    private SettingEntry<String> stringSettingEntry;
    private SettingEntry<Integer> intSettingEntry;
    private SettingEntry<Long> longSettingEntry;
    private SettingEntry<Double> doubleSettingEntry;
    private SettingEntry<Float> floatSettingEntry;

    @Mock SettingEntryListener<Integer> intListener1;
    @Mock SettingEntryListener<Integer> intListener2;

    @Before
    public void setup() throws Exception {
        SharedPreferences sharedPreferences =
                RuntimeEnvironment.getApplication().getSharedPreferences("prefs", Context.MODE_PRIVATE);
        SUT = new SharedPrefsSettingEntriesFactory(sharedPreferences);

        booleanSettingEntry = SUT.getSettingEntry(
                Boolean.class, KEY_BOOLEAN_SETTING_ENTRY, DEFAULT_BOOLEAN_SETTING_ENTRY
        );
        stringSettingEntry = SUT.getSettingEntry(
                String.class, KEY_STRING_SETTING_ENTRY, DEFAULT_STRING_SETTING_ENTRY
        );
        intSettingEntry = SUT.getSettingEntry(
                Integer.class, KEY_INT_SETTING_ENTRY, DEFAULT_INT_SETTING_ENTRY
        );
        longSettingEntry = SUT.getSettingEntry(
                Long.class, KEY_LONG_SETTING_ENTRY, DEFAULT_LONG_SETTING_ENTRY
        );
        doubleSettingEntry = SUT.getSettingEntry(
                Double.class, KEY_DOUBLE_SETTING_ENTRY, DEFAULT_DOUBLE_SETTING_ENTRY
        );
        floatSettingEntry = SUT.getSettingEntry(
                Float.class, KEY_FLOAT_SETTING_ENTRY, DEFAULT_FLOAT_SETTING_ENTRY
        );

        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void booleanSettingEntry_defaultReturned() throws Exception {
        // Arrange
        // Act
        Boolean result = booleanSettingEntry.getValue();
        // Assert
        assertThat(result, is(DEFAULT_BOOLEAN_SETTING_ENTRY));
    }

    @Test
    public void booleanSettingEntry_writeThenRead_writtenValueReturned() throws Exception {
        // Arrange
        // Act
        booleanSettingEntry.setValue(false);
        Boolean result = booleanSettingEntry.getValue();
        // Assert
        assertThat(result, is(false));
    }
    
    @Test
    public void stringSettingEntry_defaultReturned() throws Exception {
        // Arrange
        // Act
        String result = stringSettingEntry.getValue();
        // Assert
        assertThat(result, is(DEFAULT_STRING_SETTING_ENTRY));
    }

    @Test
    public void stringSettingEntry_writeThenRead_writtenValueReturned() throws Exception {
        // Arrange
        // Act
        stringSettingEntry.setValue("test");
        String result = stringSettingEntry.getValue();
        // Assert
        assertThat(result, is("test"));
    }

    @Test
    public void integerSettingEntry_defaultReturned() throws Exception {
        // Arrange
        // Act
        int result = intSettingEntry.getValue();
        // Assert
        assertThat(result, is(DEFAULT_INT_SETTING_ENTRY));
    }

    @Test
    public void integerSettingEntry_writeThenRead_writtenValueReturned() throws Exception {
        // Arrange
        // Act
        intSettingEntry.setValue(5);
        int result = intSettingEntry.getValue();
        // Assert
        assertThat(result, is(5));
    }

    @Test
    public void longSettingEntry_defaultReturned() throws Exception {
        // Arrange
        // Act
        long result = longSettingEntry.getValue();
        // Assert
        assertThat(result, is(DEFAULT_LONG_SETTING_ENTRY));
    }

    @Test
    public void longSettingEntry_writeThenRead_writtenValueReturned() throws Exception {
        // Arrange
        // Act
        longSettingEntry.setValue(5L);
        long result = longSettingEntry.getValue();
        // Assert
        assertThat(result, is(5L));
    }

    @Test
    public void doubleSettingEntry_defaultReturned() throws Exception {
        // Arrange
        // Act
        double result = doubleSettingEntry.getValue();
        // Assert
        assertThat(result, is(DEFAULT_DOUBLE_SETTING_ENTRY));
    }

    @Test
    public void doubleSettingEntry_writeThenRead_writtenValueReturned() throws Exception {
        // Arrange
        // Act
        doubleSettingEntry.setValue(5.0);
        double result = doubleSettingEntry.getValue();
        // Assert
        assertThat(result, is(5.0));
    }

    @Test
    public void floatSettingEntry_defaultReturned() throws Exception {
        // Arrange
        // Act
        float result = floatSettingEntry.getValue();
        // Assert
        assertThat(result, is(DEFAULT_FLOAT_SETTING_ENTRY));
    }

    @Test
    public void floatSettingEntry_writeThenRead_writtenValueReturned() throws Exception {
        // Arrange
        // Act
        floatSettingEntry.setValue(5.0f);
        float result = floatSettingEntry.getValue();
        // Assert
        assertThat(result, is(5.0f));
    }

    @Test
    public void setValue_listenersNotified() throws Exception {
        // Arrange
        intSettingEntry.registerListener(intListener1);
        intSettingEntry.registerListener(intListener2);
        // Act
        intSettingEntry.setValue(10);
        // Assert
        verify(intListener1).onValueChanged(intSettingEntry, 10);
        verify(intListener2).onValueChanged(intSettingEntry, 10);
    }

    @Test
    public void setValue_listenerUnregistered_listenersNotNotified() throws Exception {
        // Arrange
        intSettingEntry.registerListener(intListener1);
        intSettingEntry.registerListener(intListener2);
        // Act
        intSettingEntry.unregisterListener(intListener1);
        intSettingEntry.unregisterListener(intListener2);
        intSettingEntry.setValue(10);
        // Assert
        verifyNoMoreInteractions(intListener1);
        verifyNoMoreInteractions(intListener2);
    }

    @Test
    public void sharedPrefsSettingEntriesFactory_cachesSettingEntriesInternally() throws Exception {
        // Arrange
        // Act
        SettingEntry<Integer> intSettingEntryNew = SUT.getSettingEntry(
                Integer.class, KEY_INT_SETTING_ENTRY, DEFAULT_INT_SETTING_ENTRY
        );
        // Assert
        assertEquals(intSettingEntryNew, intSettingEntry);
    }

    // region helper methods -----------------------------------------------------------------------
    // endregion helper methods --------------------------------------------------------------------

    // region helper classes -----------------------------------------------------------------------
    // endregion helper classes --------------------------------------------------------------------

}