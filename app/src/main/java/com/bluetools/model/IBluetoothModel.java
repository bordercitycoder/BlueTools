package com.bluetools.model;

public interface IBluetoothModel {

    String getLocalBluetoothName();

    String getLocalAddress();

    String getLocalEnabled();

    String getLocalBluetoothState();

    String getLocalScanMode();

    boolean isBluetoothNull();

    void toggleBluetoothEnablement();

    boolean isBluetoothEnabled();
}
