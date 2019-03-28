package com.bluetools;

import com.bluetools.events.BluetoothStateChangeEvent;
import com.bluetools.events.DefaultEventBus;
import com.bluetools.model.IBluetoothModel;
import com.bluetools.presenters.DeviceBluetoothDetailPresenter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.Silent.class)
public class DeviceBluetoothDetailPresenterTest {

    private static final String SCANMODE = "SCANMODE";
    private static final String ADDRESS = "ADDRESS";

    @InjectMocks
    DeviceBluetoothDetailPresenter presenter;

    @Mock
    DeviceBluetoothDetailPresenter.IDeviceBluetoothDetailView mockView;

    @Mock
    IBluetoothModel mockModel;

    @Mock
    BluetoothStateChangeEvent mockStateChangeEvent;

    @Mock
    DefaultEventBus mockEventBus;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(mockModel.isBluetoothNull()).thenReturn(true);
        when(mockModel.isBluetoothEnabled()).thenReturn(false);
    }


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
    public void should_register_with_bus_when_onStart() {
        setupEnabledModel();
        presenter.onStart();
        verify(mockEventBus, times(1)).registerWithBus(presenter);
    }


    @Test
    public void should_unregister_with_bus_when_onStop() {
        setupEnabledModel();
        presenter.onDestroy();
        verify(mockEventBus, times(1)).unregisterWithBus(presenter);
    }

    private void verifyUpdateView() {
        verify(mockView).displayScanMode(SCANMODE);
        verify(mockView).displayAddress(ADDRESS);
    }

    private void setupEnabledModel() {
        when(mockModel.isBluetoothNull()).thenReturn(false);
        when(mockModel.isBluetoothEnabled()).thenReturn(true);
        when(mockModel.getLocalScanMode()).thenReturn(SCANMODE);
        when(mockModel.getLocalAddress()).thenReturn(ADDRESS);
    }

}