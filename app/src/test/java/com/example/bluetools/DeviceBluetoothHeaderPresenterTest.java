package com.example.bluetools;

import com.example.bluetools.events.BluetoothStateChangeEvent;
import com.example.bluetools.events.DefaultEventBus;
import com.example.bluetools.model.IBluetoothModel;
import com.example.bluetools.presenters.DeviceBluetoothHeaderPresenter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.Silent.class)
public class DeviceBluetoothHeaderPresenterTest {


    private static final String NAME = "NAME";
    private static final String ADDRESS = "ADDRESS";
    private static final String TRUE = "True";
    private static final String FALSE = "False";
    private static final String STATE = "STATE";



    @InjectMocks
    DeviceBluetoothHeaderPresenter presenter;

    @Mock
    DeviceBluetoothHeaderPresenter.IDeviceBluetoothHeaderView mockView;

    @Mock
    IBluetoothModel mockModel;

    @Mock
    BluetoothStateChangeEvent mockStateChangeEvent;

    @Mock
    DefaultEventBus mockEventBus;


    @Before
    public void setUp() throws Exception {
        when(mockModel.isBluetoothNull()).thenReturn(true);
        when(mockModel.isBluetoothEnabled()).thenReturn(false);
    }

    @Test
    public void should_display_view_data_when_blue_enabled_onBluetoothStateChange() {
        setupEnabledModel();
        presenter.onBluetoothStateChange(mockStateChangeEvent);
        verifyUpdateView();
    }

    @Test
    public void should_updateView_when_onStart() {
        setupEnabledModel();
        presenter.onStart();
        verifyUpdateView();
    }

    @Test
    public void should_setup_disable_button_when_blue_enabled_onBluetoothStateChange() {
        setupEnabledModel();
        presenter.onBluetoothStateChange(mockStateChangeEvent);
        verify(mockView).displayDisableButton();
        verify(mockView).displayEnabledImage();
    }

    @Test
    public void should_setup_enable_button_when_blue_disabled_onBluetoothStateChange() {
        setupDisabledModel();
        presenter.onBluetoothStateChange(mockStateChangeEvent);
        verify(mockView, times(1)).displayEnableButton();
        verify(mockView, times(1)).displayDisabledImage();
    }

    @Test
    public void should_register_with_bus_when_onStart() {
        setupEnabledModel();
        presenter.onStart();
        verify(mockEventBus, times(1)).registerWithBus(presenter);
    }

    @Test
    public void should_unregister_with_bus_when_onStop() {
        setupEnabledModel();
        presenter.onStop();
        verify(mockEventBus, times(1)).unregisterWithBus(presenter);
    }

    @Test
    public void should_toggleBluetoothEnablement_when_onEnableBluetoothButtonClick_bluetooth_valid() {
        when(mockModel.isBluetoothNull()).thenReturn(false);
        presenter.onEnableBluetoothButtonClick();
        verify(mockModel).toggleBluetoothEnablement();
    }

    @Test
    public void should_not_toggleBluetoothEnablement_when_onEnableBluetoothButtonClick_bluetooth_null() {
        when(mockModel.isBluetoothNull()).thenReturn(true);
        presenter.onEnableBluetoothButtonClick();
        verify(mockModel, never()).toggleBluetoothEnablement();
    }

    private void verifyUpdateView() {
        verify(mockView).displayName(NAME);
        verify(mockView).displayEnabled(TRUE);
        verify(mockView).displayState(STATE);
    }


    private void setupEnabledModel() {
        when(mockModel.isBluetoothNull()).thenReturn(false);
        when(mockModel.isBluetoothEnabled()).thenReturn(true);
        when(mockModel.getLocalBluetoothName()).thenReturn(NAME);
        when(mockModel.getLocalAddress()).thenReturn(ADDRESS);
        when(mockModel.getLocalEnabled()).thenReturn(TRUE);
        when(mockModel.getLocalBluetoothState()).thenReturn(STATE);
    }

    private void setupDisabledModel() {
        when(mockModel.isBluetoothNull()).thenReturn(false);
        when(mockModel.isBluetoothEnabled()).thenReturn(false);
        when(mockModel.getLocalBluetoothName()).thenReturn(NAME);
        when(mockModel.getLocalAddress()).thenReturn(ADDRESS);
        when(mockModel.getLocalEnabled()).thenReturn(FALSE);
        when(mockModel.getLocalBluetoothState()).thenReturn(STATE);
    }
}