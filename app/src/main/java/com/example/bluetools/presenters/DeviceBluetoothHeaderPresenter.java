package com.example.bluetools.presenters;


import com.example.bluetools.events.BluetoothStateChangeEvent;
import com.example.bluetools.events.DefaultEventBus;
import com.example.bluetools.events.IDefaultEventBus;
import com.example.bluetools.model.BluetoothModel;
import com.example.bluetools.model.IBluetoothModel;

import org.greenrobot.eventbus.Subscribe;

public class DeviceBluetoothHeaderPresenter {


    private IBluetoothModel bluetoothModel;
    private IDefaultEventBus eventBus;
    private IDeviceBluetoothHeaderView view;


    public DeviceBluetoothHeaderPresenter() {
        this.bluetoothModel = new BluetoothModel();
        this.eventBus = new DefaultEventBus();
    }

    @Subscribe
    public void onBluetoothStateChange(BluetoothStateChangeEvent event) {
        updateView();
    }

    public void onStart() {
        eventBus.registerWithBus(this);
        updateView();
    }

    public void onStop() {
        eventBus.unregisterWithBus(this);
    }

    public void setView(IDeviceBluetoothHeaderView view){
        this.view = view;
    }

    public void onEnableBluetoothButtonClick() {
        if (!bluetoothModel.isBluetoothNull()) {
            bluetoothModel.toggleBluetoothEnablement();
        }
    }

    private void updateView() {

        if (!bluetoothModel.isBluetoothNull()) {

            String name = bluetoothModel.getLocalBluetoothName();
            String enabled = bluetoothModel.getLocalEnabled();
            String state = bluetoothModel.getLocalBluetoothState();


            view.displayName(name);
            view.displayEnabled(enabled);
            view.displayState(state);


            if (bluetoothModel.isBluetoothEnabled()) {
                view.updateEnableButton("Disable");
                view.displayEnabledImage();
            } else {
                view.updateEnableButton("Enable");
                view.displayDisabledImage();
            }


        }
    }


    public interface IDeviceBluetoothHeaderView {

        void displayName(String name);

        void displayEnabled(String enabled);

        void displayState(String state);

        void updateEnableButton(String text);

        void displayEnabledImage();

        void displayDisabledImage();

    }


}
