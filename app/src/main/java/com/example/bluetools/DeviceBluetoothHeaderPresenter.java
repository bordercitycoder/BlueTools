package com.example.bluetools;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class DeviceBluetoothHeaderPresenter {


    private IBluetoothModel bluetoothModel;

    private IDeviceBluetoothPresenterView view;


    public DeviceBluetoothHeaderPresenter() {
        bluetoothModel = new BluetoothModel();
    }

    @Subscribe
    public void onBluetoothStateChange(BluetoothStateChange event) {
        updateView();
    }


    public void onStop() {
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    public void setView(IDeviceBluetoothPresenterView view) {
        this.view = view;
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }

        updateView();
    }


    public void onEnableBluetoothButtonClick() {
        if (!bluetoothModel.isBluetoothNull()) {
            bluetoothModel.toggleBluetoothEnablement();
        }
    }

    private void updateView() {

        if (!bluetoothModel.isBluetoothNull()) {

            String name = bluetoothModel.getLocalBluetoothName();
            String address = bluetoothModel.getLocalAddress();
            String enabled = bluetoothModel.getLocalEnabled();
            String state = bluetoothModel.getLocalBluetoothState();


            view.displayName(name);
            view.displayEnabled(enabled);
            view.displayState(state);
            view.displayAddress(address);


            if (bluetoothModel.isBluetoothEnabled()) {
                view.updateEnableButton("Disable");
                view.displayEnabledImage();
            } else {
                view.updateEnableButton("Enable");
                view.displayDisabledImage();
            }


        } else {
            //   textView.setText("BluetoothAdapter = null");


        }
    }


    public interface IDeviceBluetoothPresenterView {

        void displayName(String name);

        void displayEnabled(String enabled);

        void displayState(String state);

        void displayAddress(String address);

        void updateEnableButton(String text);

        void displayEnabledImage();

        void displayDisabledImage();

    }


}
