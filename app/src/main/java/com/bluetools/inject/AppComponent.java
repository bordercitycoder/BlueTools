package com.bluetools.inject;

import com.bluetools.model.BluetoothModel;
import com.bluetools.model.BluetoothScanMode;
import com.bluetools.model.BluetoothState;
import com.bluetools.presenters.DeviceBluetoothDetailPresenter;
import com.bluetools.presenters.DeviceBluetoothHeaderPresenter;
import com.bluetools.ui.DeviceBluetoothDetailFragment;
import com.bluetools.ui.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {


    void inject(MainActivity mainActivity);

    void inject(BluetoothState bluetoothState);

    void inject(BluetoothScanMode bluetoothScanMode);

    void inject(BluetoothModel bluetoothModel);

    void inject(DeviceBluetoothHeaderPresenter presenter);

    void inject(DeviceBluetoothDetailFragment fragment);

    void inject(DeviceBluetoothDetailPresenter presenter);

}


