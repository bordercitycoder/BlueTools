package com.bluetools.model;

import android.bluetooth.BluetoothAdapter;

import com.bluetools.BlueTools.R;
import com.bluetools.utils.ResourceUtil;


public class BluetoothState {


    ResourceUtil resourceUtil;


    public BluetoothState(ResourceUtil resourceUtil) {
        this.resourceUtil = resourceUtil;
    }

    public String getStateText(int state) {

        String stateText = resourceUtil.getString(R.string.bluetooth_state_unknown);

        switch (state) {
            case BluetoothAdapter.STATE_OFF:
                stateText = resourceUtil.getString(R.string.bluetooth_state_off);
                break;
            case BluetoothAdapter.STATE_TURNING_OFF:
                stateText = resourceUtil.getString(R.string.bluetooth_state_turn_off);
                break;
            case BluetoothAdapter.STATE_ON:
                stateText = resourceUtil.getString(R.string.bluetooth_state_on);
                break;
            case BluetoothAdapter.STATE_TURNING_ON:
                stateText = resourceUtil.getString(R.string.bluetooth_state_turn_on);
                break;
            case BluetoothAdapter.ERROR:
                stateText = resourceUtil.getString(R.string.bluetooth_state_error);
                break;
        }

        return stateText;
    }

}
