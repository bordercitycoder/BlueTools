package com.bluetools.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bluetools.BlueTools.R;
import com.bluetools.inject.AppModule;
import com.bluetools.inject.DaggerAppComponent;
import com.bluetools.presenters.DeviceBluetoothDetailPresenter;

import javax.inject.Inject;

public class DeviceBluetoothDetailFragment extends Fragment implements DeviceBluetoothDetailPresenter.IDeviceBluetoothDetailView {


    private TextView scanModeTextView;
    private TextView addressTextView;

    @Inject
    DeviceBluetoothDetailPresenter presenter;


    public DeviceBluetoothDetailFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        DaggerAppComponent.builder().appModule(new AppModule(getContext())).build()
                .inject(this);

        View view = inflater.inflate(R.layout.fragment_device_bluetooth_detail, container, false);
        findViews(view);

        presenter.setView(this);

        return view;
    }

    private void findViews(View view) {
        addressTextView = view.findViewById(R.id.addressTextView);
        scanModeTextView = view.findViewById(R.id.scanModeTextView);
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.onStart();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public void displayScanMode(String mode) {
        scanModeTextView.setText(mode);
    }

    @Override
    public void displayAddress(String address) {
        addressTextView.setText(address);
    }


}
