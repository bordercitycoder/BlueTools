package com.example.bluetools;

import android.bluetooth.BluetoothAdapter;

public class BluetoothState {


    public BluetoothState() {
    }

    public String getStateText(int state) {

        String stateText = "Unknown Bluetooth state";

        switch (state) {
            case BluetoothAdapter.STATE_OFF:
                stateText = "Bluetooth off";
                break;
            case BluetoothAdapter.STATE_TURNING_OFF:
                stateText = "Turning Bluetooth off...";
                break;
            case BluetoothAdapter.STATE_ON:
                stateText = "Bluetooth on";
                break;
            case BluetoothAdapter.STATE_TURNING_ON:
                stateText = "Turning Bluetooth on...";
                break;
            case BluetoothAdapter.ERROR:
                stateText = "Error finding Bluetooth state...";
                break;
        }

        return stateText;
    }

}
