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

    private TextView tv1;
    private Button button1;

    private BroadcastReceiver broadcastReceiver = null;
    private IntentFilter intentFilter = null;

    private BluetoothAdapter bluetoothAdapter;

    private StringBuilder stringBuilder = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        stringBuilder = new StringBuilder();

        tv1 = findViewById(R.id.tv1);

        button1 = (Button)findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(checkPermission()) {

                    requestAdapter();

                    if (bluetoothAdapter != null) {
                        if (bluetoothAdapter.isDiscovering()) {
                            bluetoothAdapter.cancelDiscovery();
                        }
                        bluetoothAdapter.startDiscovery();
                    }
                }
            }
        });

        //블루투스 검색 상태 확인
        initFilter();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        DestoryFilter();
    }

    private void initFilter(){

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                String sAction = intent.getAction();

                if (BluetoothDevice.ACTION_FOUND.equals(sAction)) {

                    //Log.e(TAG, "블루투스 디바이스 찾았을때");

                    BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                    if (device != null && device.getName() != null) {

                        //Log.e(TAG, device.getName() + " : " + device.getAddress());

                        stringBuilder.append(device.getName() + " : " + device.getAddress() + "\n");
                        tv1.setText(stringBuilder.toString());
                    }

                } else if (BluetoothAdapter.ACTION_DISCOVERY_STARTED.equals(sAction)) {

                    //Log.e(TAG, "블루투스 디바이스 검색 시작");

                } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(sAction)) {

                    //Log.e(TAG, "블루투스 디바이스 검색 종료");

                } else if (BluetoothDevice.ACTION_BOND_STATE_CHANGED.equals(sAction)) {

                    //Log.e(TAG, "블루투스 디바이스 페어링 상태 변화");

                    BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                    if( device.getBondState() == BluetoothDevice.BOND_BONDED) {

                        Log.e(TAG, device.getName() + " : " + device.getAddress());
                    }
                }
            }
        };

        //블루투스 장치 검색
        intentFilter = new IntentFilter();
        intentFilter.addAction(BluetoothDevice.ACTION_FOUND);
        intentFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
        intentFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        intentFilter.addAction(BluetoothDevice.ACTION_BOND_STATE_CHANGED);
        registerReceiver(broadcastReceiver, intentFilter);
    }

    private void DestoryFilter(){

        if(broadcastReceiver != null) {
            unregisterReceiver(broadcastReceiver);
        }

        if (bluetoothAdapter.isDiscovering()) {
            bluetoothAdapter.cancelDiscovery();
        }

        if (bluetoothAdapter.getState() == BluetoothAdapter.STATE_ON) {
            bluetoothAdapter.disable();
        }
    }

    private void requestAdapter() {

        try {
            bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            if(bluetoothAdapter != null){

                if(!bluetoothAdapter.isEnabled()){
                    Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(intent, 1);
                }
            }
        }catch (Exception ex){
            Log.e(TAG, ex.getMessage().toString());
        }
    }

    private boolean checkPermission() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {

            //sdk 31
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_SCAN) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.BLUETOOTH_SCAN}, 1);
                return false;
            }

        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {

            //sdk 29
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 2);
                return false;
            }

        } else {

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 3);
                return false;
            }
        }

        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        //Log.e(TAG, "onRequestPermissionsResult: " + requestCode);
        //미승인시 재요청

        if(requestCode == 1) {

            if(grantResults.length > 0) {

                boolean result = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                if(!result) {
                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                        if(shouldShowRequestPermissionRationale(Manifest.permission.BLUETOOTH_SCAN)) {
                            requestPermissions(new String[] {Manifest.permission.BLUETOOTH_SCAN}, 1);
                        }
                    }
                }
            }

        } else if(requestCode == 2) {

            if(grantResults.length > 0) {

                boolean result = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                if(!result) {
                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                        if(shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)) {
                            requestPermissions(new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, 2);
                        }
                    }
                }
            }

        } else if(requestCode == 3) {

            if(grantResults.length > 0) {

                boolean result = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                if(!result) {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 3);
                }
            }
        }
    }
}