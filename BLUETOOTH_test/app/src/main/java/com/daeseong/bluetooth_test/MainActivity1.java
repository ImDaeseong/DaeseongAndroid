package com.daeseong.bluetooth_test;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity1 extends AppCompatActivity {

    private static final String TAG = MainActivity1.class.getSimpleName();

    private TextView tv1;

    private BluetoothAdapter bluetoothAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

        tv1 = findViewById(R.id.tv1);

        if(checkPermission()) {

            bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            if (bluetoothAdapter == null) {

                tv1.setText("장치가 블루투스를 지원되지 않습니다.");

            } else {

                //Log.e(TAG, "bluetoothAdapter getState: " + bluetoothAdapter.getState());

                if (bluetoothAdapter.getState() == BluetoothAdapter.STATE_ON) {

                    tv1.setText("블루투스 활성화");

                } else if (bluetoothAdapter.getState() == BluetoothAdapter.STATE_TURNING_ON) {

                    tv1.setText("블루투스 활성화중");

                } else if (bluetoothAdapter.getState() == BluetoothAdapter.STATE_TURNING_OFF) {

                    tv1.setText("블루투스 비활성화중");

                } else if (bluetoothAdapter.getState() == BluetoothAdapter.STATE_OFF) {

                    tv1.setText("블루투스 비활성화");
                }
            }
        } else {
            requestAdapter();
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //Log.e(TAG, "resultCode:" + resultCode);

        if(resultCode == RESULT_OK){

            Log.e(TAG, resultCode + " : 블루투스 On");
        } else if(resultCode == RESULT_CANCELED){

            Log.e(TAG, resultCode + " : 블루투스 Off");
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