package com.example.bluetools.ui;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.BlueTools.R;
import com.example.bluetools.events.BluetoothStateChangeEvent;
import com.example.bluetools.presenters.DeviceBluetoothHeaderPresenter;

import org.greenrobot.eventbus.EventBus;

public class MainActivity extends AppCompatActivity implements
        DeviceBluetoothHeaderPresenter.IDeviceBluetoothHeaderView {

    private DeviceBluetoothDetailFragment deviceBluetoothDetailFragment;
    private Fragment activeFragment;

    private ImageView disabledImageView;
    private ImageView enabledImageView;

    private TextView enabledTextView;
    private TextView addressTextView;
    private TextView stateTextView;
    private TextView nameTextView;
    private Button enableButton;


    private DeviceBluetoothHeaderPresenter presenter;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {

                case R.id.navigation_home:
                    getSupportFragmentManager()
                            .beginTransaction()
                            .hide(activeFragment)
                            .show(deviceBluetoothDetailFragment)
                            .commit();
                    activeFragment = deviceBluetoothDetailFragment;
                    return true;

                case R.id.navigation_dashboard:
                    return true;

                case R.id.navigation_notifications:
                    // mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        deviceBluetoothDetailFragment = new DeviceBluetoothDetailFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.main_container, deviceBluetoothDetailFragment, "1").commit();
        activeFragment = deviceBluetoothDetailFragment;

        findViews();

        presenter = new DeviceBluetoothHeaderPresenter();
        presenter.setView(this);
    }

    private void findViews() {
        CollapsingToolbarLayout layout = findViewById(R.id.toolbar_layout);
        nameTextView = layout.findViewById(R.id.nameTextView);
        enabledTextView = layout.findViewById(R.id.enabledTextView);
        stateTextView = layout.findViewById(R.id.stateTextView);
        addressTextView = layout.findViewById(R.id.addressTextView);
        enableButton = layout.findViewById(R.id.enableBluetoothButton);
        enabledImageView = layout.findViewById(R.id.enabledImageView);
        disabledImageView = layout.findViewById(R.id.disabledImageView);
        disabledImageView.setVisibility(View.INVISIBLE);
        enabledImageView.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onStart() {
        super.onStart();

        registerReceiver(mReceiver, new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED));
        registerReceiver(mReceiver, new IntentFilter(BluetoothAdapter.ACTION_SCAN_MODE_CHANGED));
        registerReceiver(mReceiver, new IntentFilter(BluetoothAdapter.ACTION_CONNECTION_STATE_CHANGED));
        registerReceiver(mReceiver, new IntentFilter(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE));
        registerReceiver(mReceiver, new IntentFilter(BluetoothAdapter.ACTION_REQUEST_ENABLE));

        presenter.onStart();

        enableButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onEnableBluetoothButtonClick();
            }
        });
    }

    @Override
    public void displayName(String name) {
        nameTextView.setText(name);
    }

    @Override
    public void displayEnabled(String enabled) {
        enabledTextView.setText(enabled);
    }

    @Override
    public void displayState(String state) {
        stateTextView.setText(state);
    }

    @Override
    public void displayAddress(String address) {
        addressTextView.setText(address);
    }

    @Override
    public void updateEnableButton(String text) {
        enableButton.setText(text);
    }

    public void displayEnabledImage() {
        disabledImageView.setVisibility(View.INVISIBLE);
        enabledImageView.setVisibility(View.VISIBLE);
    }


    public void displayDisabledImage() {
        enabledImageView.setVisibility(View.INVISIBLE);
        disabledImageView.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.onStop();
    }

    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();

            if (action != null &&
                    (action.equals(BluetoothAdapter.ACTION_STATE_CHANGED) ||
                            action.equals(BluetoothAdapter.ACTION_SCAN_MODE_CHANGED))) {

                EventBus.getDefault().post(new BluetoothStateChangeEvent());

            }
        }
    };
}
