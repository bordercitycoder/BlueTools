package com.bluetools.model;

import android.bluetooth.BluetoothAdapter;

import com.bluetools.BlueTools.R;
import com.bluetools.utils.ResourceUtil;


public class BluetoothScanMode {

    ResourceUtil resourceUtil;

    public BluetoothScanMode(ResourceUtil resourceUtil) {
        this.resourceUtil = resourceUtil;
    }

    public String getScanModeText(int mode) {

        String scanModeText = resourceUtil.getString(R.string.bluetooth_scan_unknown);

        switch (mode) {
            case BluetoothAdapter.SCAN_MODE_CONNECTABLE:
                scanModeText = resourceUtil.getString(R.string.bluetooth_scan_connect_no_discover);
                break;
            case BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE:
                scanModeText = resourceUtil.getString(R.string.bluetooth_scan_connect_and_discover);
                break;
            case BluetoothAdapter.SCAN_MODE_NONE:
                scanModeText = resourceUtil.getString(R.string.bluetooth_scan_unknown);
                break;
            case BluetoothAdapter.ERROR:
                scanModeText = resourceUtil.getString(R.string.bluetooth_scan_error);
                break;
        }

        return scanModeText;
    }


}
