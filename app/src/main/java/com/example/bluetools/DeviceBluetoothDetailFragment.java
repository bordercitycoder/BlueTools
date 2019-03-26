package com.example.bluetools;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.BlueTools.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class DeviceBluetoothDetailFragment extends Fragment implements DeviceBluetoothDetailPresenter.IDeviceBluetoothDetailView {


    private TextView scanModeTextView;
    private TextView addressTextView;
    private DeviceBluetoothDetailPresenter presenter;


    public DeviceBluetoothDetailFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_device_bluetooth_detail, container, false);
        findViews(view);

        return view;
    }

    private void findViews(View view) {
        addressTextView = view.findViewById(R.id.addressTextView);
        scanModeTextView = view.findViewById(R.id.scanModeTextView);
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter = new DeviceBluetoothDetailPresenter();
        presenter.setView(this);
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
