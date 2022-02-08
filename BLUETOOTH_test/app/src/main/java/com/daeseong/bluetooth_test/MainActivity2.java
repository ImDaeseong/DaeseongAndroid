package com.daeseong.bluetooth_test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.bluetooth.BluetoothAdapter;
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

public class MainActivity2 extends AppCompatActivity {

    private static final String TAG = MainActivity2.class.getSimpleName();

    private Button button1, button2;

    private BroadcastReceiver broadcastReceiver = null;
    private IntentFilter intentFilter = null;

    private BluetoothAdapter bluetoothAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //bluetooth on
        button1 = (Button)findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(checkPermission()){
                    requestAdapter(true);
                }
            }
        });

        //bluetooth off
        button2 = (Button)findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(checkPermission()){
                    requestAdapter(false);
                }
            }
        });

        //블루투스 상태 변경 확인
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

                if(intent.getAction().equals(BluetoothAdapter.ACTION_STATE_CHANGED)) {

                    final int nState = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, BluetoothAdapter.ERROR);
                    //Log.e(TAG, "ACTION_STATE_CHANGED: " + nState);

                    switch (nState) {
                        case BluetoothAdapter.STATE_OFF:
                            Log.e(TAG, "ACTION_STATE_CHANGED: 블루투스 비활성화");
                            break;
                        case BluetoothAdapter.STATE_TURNING_OFF:
                            Log.e(TAG, "ACTION_STATE_CHANGED: 블루투스 비활성화중");
                            break;
                        case BluetoothAdapter.STATE_ON:
                            Log.e(TAG, "ACTION_STATE_CHANGED: 블루투스 활성화");
                            break;
                        case BluetoothAdapter.STATE_TURNING_ON:
                            Log.e(TAG, "ACTION_STATE_CHANGED: 블루투스 활성화중");
                            break;
                    }
                }
            }
        };

        //블루투스 상태 변환 감지
        intentFilter = new IntentFilter();
        intentFilter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
        registerReceiver(broadcastReceiver, intentFilter);
    }

    private void DestoryFilter(){

        try {
            if (broadcastReceiver != null) {
                unregisterReceiver(broadcastReceiver);
            }

            if (bluetoothAdapter.isDiscovering()) {
                bluetoothAdapter.cancelDiscovery();
            }

            if (bluetoothAdapter.getState() == BluetoothAdapter.STATE_ON) {
                bluetoothAdapter.disable();
            }
        }catch (Exception ex){
            Log.e(TAG, ex.getMessage().toString());
        }
    }

    private void requestAdapter(boolean bEnable) {

        try {

            if (bEnable) {

                if(!bluetoothAdapter.isEnabled()){
                    Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(intent, 1);
                }

            } else {

                if (bluetoothAdapter.isEnabled())
                    bluetoothAdapter.disable();
            }

        }catch (Exception ex){
            Log.e(TAG, ex.getMessage().toString());
        }
    }

    private boolean checkPermission() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {

            //sdk 31
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.BLUETOOTH_CONNECT}, 1);
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
                        if(shouldShowRequestPermissionRationale(Manifest.permission.BLUETOOTH_CONNECT)) {
                            requestPermissions(new String[] {Manifest.permission.BLUETOOTH_CONNECT}, 1);
                        }
                    }
                }
            }
        }
    }
}