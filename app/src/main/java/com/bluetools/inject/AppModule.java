package com.bluetools.inject;


import android.content.Context;

import com.bluetools.events.DefaultEventBus;
import com.bluetools.model.BluetoothModel;
import com.bluetools.model.BluetoothScanMode;
import com.bluetools.model.BluetoothState;
import com.bluetools.presenters.DeviceBluetoothDetailPresenter;
import com.bluetools.presenters.DeviceBluetoothHeaderPresenter;
import com.bluetools.utils.ResourceUtil;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private Context context;


    public AppModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    public ResourceUtil provideResourceUtil() {
        return new ResourceUtil(context);
    }


    @Provides
    @Singleton
    public DefaultEventBus provideDefaultEventBus() {
        return new DefaultEventBus();
    }

    @Provides
    @Singleton
    public BluetoothState provideBluetoothState(ResourceUtil resourceUtil) {
        return new BluetoothState(resourceUtil);
    }

    @Provides
    @Singleton
    public BluetoothScanMode provideBluetoothScanMode(ResourceUtil resourceUtil) {
        return new BluetoothScanMode(resourceUtil);
    }

    @Provides
    @Singleton
    public BluetoothModel provideBluetoothModel(BluetoothState state, BluetoothScanMode scanMode) {
        return new BluetoothModel(state, scanMode);
    }

    @Provides
    @Singleton
    public DeviceBluetoothHeaderPresenter provideDeviceBluetoothHeaderPresenter(BluetoothModel model, DefaultEventBus bus) {
        return new DeviceBluetoothHeaderPresenter(model, bus);
    }


    @Provides
    @Singleton
    public DeviceBluetoothDetailPresenter provideDeviceBluetoothDetailPresenter(BluetoothModel model, DefaultEventBus bus) {
        return new DeviceBluetoothDetailPresenter(model, bus);
    }
}
