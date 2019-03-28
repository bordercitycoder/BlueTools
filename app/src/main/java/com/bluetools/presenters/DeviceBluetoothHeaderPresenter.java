package com.bluetools.presenters;


import com.bluetools.events.BluetoothStateChangeEvent;
import com.bluetools.events.DefaultEventBus;
import com.bluetools.events.IDefaultEventBus;
import com.bluetools.model.BluetoothModel;
import com.bluetools.model.IBluetoothModel;

import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

public class DeviceBluetoothHeaderPresenter {


    private IBluetoothModel bluetoothModel;
    private IDefaultEventBus eventBus;
    private IDeviceBluetoothHeaderView view;


    @Inject
    public DeviceBluetoothHeaderPresenter(BluetoothModel bluetoothModel, DefaultEventBus eventBus) {
        this.bluetoothModel = bluetoothModel;
        this.eventBus = eventBus;
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

    public void setView(IDeviceBluetoothHeaderView view) {
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
                view.displayDisableButton();
                view.displayEnabledImage();
            } else {
                view.displayEnableButton();
                view.displayDisabledImage();
            }


        }
    }


    public interface IDeviceBluetoothHeaderView {

        void displayName(String name);

        void displayEnabled(String enabled);

        void displayState(String state);

        void displayEnabledImage();

        void displayDisabledImage();

        void displayEnableButton();

        void displayDisableButton();

    }


}
