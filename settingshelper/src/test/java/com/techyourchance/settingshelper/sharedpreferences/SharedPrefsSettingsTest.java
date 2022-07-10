package com.techyourchance.settingshelper.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.techyourchance.settingshelper.SettingEntry;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
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


    @Before
    public void setup() throws Exception {
        SharedPreferences sharedPreferences =
                RuntimeEnvironment.getApplication().getSharedPreferences("prefs", Context.MODE_PRIVATE);
        SUT = new SharedPrefsSettingEntriesFactory(sharedPreferences);

        booleanSettingEntry = SUT.newSettingEntry(
                Boolean.class, KEY_BOOLEAN_SETTING_ENTRY, DEFAULT_BOOLEAN_SETTING_ENTRY
        );
        stringSettingEntry = SUT.newSettingEntry(
                String.class, KEY_STRING_SETTING_ENTRY, DEFAULT_STRING_SETTING_ENTRY
        );
        intSettingEntry = SUT.newSettingEntry(
                Integer.class, KEY_INT_SETTING_ENTRY, DEFAULT_INT_SETTING_ENTRY
        );
        longSettingEntry = SUT.newSettingEntry(
                Long.class, KEY_LONG_SETTING_ENTRY, DEFAULT_LONG_SETTING_ENTRY
        );
        doubleSettingEntry = SUT.newSettingEntry(
                Double.class, KEY_DOUBLE_SETTING_ENTRY, DEFAULT_DOUBLE_SETTING_ENTRY
        );
        floatSettingEntry = SUT.newSettingEntry(
                Float.class, KEY_FLOAT_SETTING_ENTRY, DEFAULT_FLOAT_SETTING_ENTRY
        );
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

    // region helper methods -----------------------------------------------------------------------
    // endregion helper methods --------------------------------------------------------------------

    // region helper classes -----------------------------------------------------------------------
    // endregion helper classes --------------------------------------------------------------------

}