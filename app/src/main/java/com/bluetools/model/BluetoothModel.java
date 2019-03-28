package com.bluetools.model;

import android.bluetooth.BluetoothAdapter;

import javax.inject.Inject;

public class BluetoothModel implements IBluetoothModel {


    BluetoothState bluetoothState;

    BluetoothScanMode bluetoothScanMode;

    @Inject
    public BluetoothModel(BluetoothState bluetoothState, BluetoothScanMode bluetoothScanMode) {
        this.bluetoothState = bluetoothState;
        this.bluetoothScanMode = bluetoothScanMode;
    }

    public String getLocalBluetoothName() {
        return getBluetoothAdapter().getName();
    }

    public String getLocalAddress() {
        return getBluetoothAdapter().getAddress();
    }

    public String getLocalEnabled() {
        return Boolean.toString(getBluetoothAdapter().isEnabled());
    }

    public String getLocalBluetoothState() {
        return bluetoothState.getStateText(getBluetoothAdapter().getState());
    }

    public String getLocalScanMode() {
        return bluetoothScanMode.getScanModeText(getBluetoothAdapter().getScanMode());
    }

    private BluetoothAdapter getBluetoothAdapter() {
        return BluetoothAdapter.getDefaultAdapter();
    }

    public boolean isBluetoothNull() {
        return getBluetoothAdapter() == null;
    }

    public void toggleBluetoothEnablement() {
        if (getBluetoothAdapter().isEnabled()) {
            getBluetoothAdapter().disable();
        } else {
            getBluetoothAdapter().enable();
        }
    }

    public boolean isBluetoothEnabled() {
        return getBluetoothAdapter().isEnabled();
    }


}
