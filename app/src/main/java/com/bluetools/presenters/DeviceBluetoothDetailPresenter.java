package com.bluetools.presenters;

import com.bluetools.events.BluetoothStateChangeEvent;
import com.bluetools.events.DefaultEventBus;
import com.bluetools.events.IDefaultEventBus;
import com.bluetools.model.IBluetoothModel;

import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;


public class DeviceBluetoothDetailPresenter {

    private IBluetoothModel bluetoothModel;
    private IDeviceBluetoothDetailView view;
    private IDefaultEventBus eventBus;


    @Inject
    public DeviceBluetoothDetailPresenter(IBluetoothModel bluetoothModel, DefaultEventBus eventBus) {
        this.eventBus = eventBus;
        this.bluetoothModel = bluetoothModel;
    }

    public void setView(IDeviceBluetoothDetailView view) {
        this.view = view;
    }

    public void onStart() {
        eventBus.registerWithBus(this);
        updateView();
    }

    @Subscribe
    public void onBluetoothStateChange(BluetoothStateChangeEvent event) {
        updateView();
    }

    private void updateView() {

        if (!bluetoothModel.isBluetoothNull()) {
            String addressText = bluetoothModel.getLocalAddress();
            String scanModeText = bluetoothModel.getLocalScanMode();

            view.displayScanMode(scanModeText);
            view.displayAddress(addressText);
        }
    }

    public void onDestroy() {
        eventBus.unregisterWithBus(this);
    }

    public interface IDeviceBluetoothDetailView {

        void displayScanMode(String mode);

        void displayAddress(String address);

    }


}
