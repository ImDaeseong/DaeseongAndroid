package com.daeseong.bluetooth_test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity4 extends AppCompatActivity {

    private static final String TAG = MainActivity4.class.getSimpleName();
    private static final int REQUEST_ENABLE_BT = 1;
    private static final int REQUEST_PERMISSION_BT_SCAN = 2;
    private static final int REQUEST_PERMISSION_FINE_LOCATION = 3;
    private static final int REQUEST_PERMISSION_COARSE_LOCATION = 4;

    private TextView tv1;
    private Button button1;

    private BroadcastReceiver broadcastReceiver;
    private IntentFilter intentFilter;
    private BluetoothAdapter bluetoothAdapter;
    private StringBuilder stringBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        stringBuilder = new StringBuilder();
        tv1 = findViewById(R.id.tv1);
        button1 = findViewById(R.id.button1);

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkPermission()) {
                    requestAdapter();
                    if (bluetoothAdapter != null && bluetoothAdapter.isDiscovering()) {
                        bluetoothAdapter.cancelDiscovery();
                    }
                    bluetoothAdapter.startDiscovery();
                }
            }
        });

        initFilter();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        destroyFilter();
    }

    private void initFilter() {
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();

                if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                    BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                    if (device != null && device.getName() != null) {
                        stringBuilder.append(device.getName()).append(" : ").append(device.getAddress()).append("\n");
                        tv1.setText(stringBuilder.toString());
                    }
                } else if (BluetoothAdapter.ACTION_DISCOVERY_STARTED.equals(action)) {
                    Log.d(TAG, "Bluetooth discovery started");
                } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
                    Log.d(TAG, "Bluetooth discovery finished");
                } else if (BluetoothDevice.ACTION_BOND_STATE_CHANGED.equals(action)) {
                    BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                    if (device != null && device.getBondState() == BluetoothDevice.BOND_BONDED) {
                        Log.d(TAG, device.getName() + " : " + device.getAddress());
                    }
                }
            }
        };

        intentFilter = new IntentFilter();
        intentFilter.addAction(BluetoothDevice.ACTION_FOUND);
        intentFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
        intentFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        intentFilter.addAction(BluetoothDevice.ACTION_BOND_STATE_CHANGED);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            registerReceiver(broadcastReceiver, intentFilter, Context.RECEIVER_EXPORTED);
        } else {
            registerReceiver(broadcastReceiver, intentFilter);
        }
    }

    private void destroyFilter() {
        if (broadcastReceiver != null) {
            unregisterReceiver(broadcastReceiver);
        }

        if (bluetoothAdapter != null && bluetoothAdapter.isDiscovering()) {
            bluetoothAdapter.cancelDiscovery();
        }
    }

    private void requestAdapter() {
        if (bluetoothAdapter != null && !bluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }
    }

    private boolean checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_SCAN) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.BLUETOOTH_SCAN}, REQUEST_PERMISSION_BT_SCAN);
                return false;
            }
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_PERMISSION_FINE_LOCATION);
                return false;
            }
        } else {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, REQUEST_PERMISSION_COARSE_LOCATION);
                return false;
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            // Permission granted
            if (requestCode == REQUEST_PERMISSION_BT_SCAN || requestCode == REQUEST_PERMISSION_FINE_LOCATION || requestCode == REQUEST_PERMISSION_COARSE_LOCATION) {
                requestAdapter();
            }
        } else {
            // Permission denied
            Log.w(TAG, "Permission denied: " + requestCode);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S && requestCode == REQUEST_PERMISSION_BT_SCAN) {
                if (shouldShowRequestPermissionRationale(Manifest.permission.BLUETOOTH_SCAN)) {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.BLUETOOTH_SCAN}, REQUEST_PERMISSION_BT_SCAN);
                }
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q && requestCode == REQUEST_PERMISSION_FINE_LOCATION) {
                if (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)) {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_PERMISSION_FINE_LOCATION);
                }
            } else if (requestCode == REQUEST_PERMISSION_COARSE_LOCATION) {
                if (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_COARSE_LOCATION)) {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, REQUEST_PERMISSION_COARSE_LOCATION);
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_ENABLE_BT && resultCode == RESULT_OK) {
            // Bluetooth has been enabled, start discovery if necessary
            if (bluetoothAdapter != null) {
                bluetoothAdapter.startDiscovery();
            }
        } else {
            Log.w(TAG, "Bluetooth enabling failed.");
        }
    }
}
