package com.example.bluetools.model;

import android.bluetooth.BluetoothAdapter;

public class BluetoothScanMode {

    public String getScanModeText(int mode) {

        String scanModeText = "Unknown Bluetooth scan mode";

        switch (mode) {
            case BluetoothAdapter.SCAN_MODE_CONNECTABLE:
                scanModeText = "Connectable but not discoverable";
                break;
            case BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE:
                scanModeText = "Connectable and discoverable";
                break;
            case BluetoothAdapter.SCAN_MODE_NONE:
                scanModeText = "Unknown";
                break;
            case BluetoothAdapter.ERROR:
                scanModeText = "Error finding Bluetooth scan mode...";
                break;
        }

        return scanModeText;
    }


}
