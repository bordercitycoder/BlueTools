package com.example.bluetools;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;


public class DeviceBluetoothDetailPresenter {

    private final IBluetoothModel bluetoothModel;
    private IDeviceBluetoothDetailView view;


    public DeviceBluetoothDetailPresenter() {
        bluetoothModel = new BluetoothModel();
    }

    public void setView(IDeviceBluetoothDetailView view) {
        this.view = view;

        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }

        updateView();
    }

    @Subscribe
    public void onBluetoothStateChange(BluetoothStateChange event) {
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
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    public interface IDeviceBluetoothDetailView {

        void displayScanMode(String mode);

        void displayAddress(String address);

    }


}
