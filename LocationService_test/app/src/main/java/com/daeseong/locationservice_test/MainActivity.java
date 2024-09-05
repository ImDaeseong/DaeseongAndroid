package com.daeseong.locationservice_test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;
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
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();


    private Button button1, button2;

    private BroadcastReceiver broadcastReceiver;
    private static double currentLatitude;
    private static double currentLongitude;
    private IntentFilter intentFilter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runService();
            }
        });

        button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService();
            }
        });

        // 브로드캐스트 수신기 설정
        if (broadcastReceiver == null) {
            broadcastReceiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    currentLatitude = intent.getDoubleExtra("LATITUDE", 0.0);
                    currentLongitude = intent.getDoubleExtra("LONGITUDE", 0.0);
                    Log.e(TAG, "onReceive currentLatitude: " + currentLatitude);
                    Log.e(TAG, "onReceive currentLongitude: " + currentLongitude);

                    String sMsg = String.format("Latitude: %f, Longitude: %f", currentLatitude, currentLongitude);
                    Toast.makeText(MainActivity.this, sMsg, Toast.LENGTH_SHORT).show();
                }
            };

            intentFilter = new IntentFilter();
            intentFilter.addAction("LOCATION_UPDATE");

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                registerReceiver(broadcastReceiver, intentFilter, Context.RECEIVER_NOT_EXPORTED);
            } else {
                registerReceiver(broadcastReceiver, intentFilter);
            }
        }

        // 권한 체크
        checkPermission();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // 서비스 중지 및 브로드캐스트 수신기 해제
        stopService();
        if (broadcastReceiver != null) {
            unregisterReceiver(broadcastReceiver);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1) {

            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.e(TAG, "Permission granted, starting service");
                runService();
            } else {
                Log.e(TAG, "Permission denied");
                Toast.makeText(this, "Location permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void runService() {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                if (LocationService.serviceIntent == null) {
                    Intent intent = new Intent(this, LocationService.class);
                    startForegroundService(intent);
                } else {
                    Log.e(TAG, "Service already running");
                }
            } else {
                if (LocationService.serviceIntent == null) {
                    Intent intent = new Intent(this, LocationService.class);
                    startService(intent);
                } else {
                    Log.e(TAG, "Service already running");
                }
            }
        } catch (Exception ex) {
            Log.e(TAG, ex.getMessage());
        }
    }

    private void stopService() {
        try {
            if (LocationService.serviceIntent != null) {
                Intent intent = new Intent(this, LocationService.class);
                stopService(intent);
                LocationService.serviceIntent = null;
            }
        } catch (Exception ex) {
            Log.e(TAG, ex.getMessage());
        }
    }

    private void checkPermission() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.FOREGROUND_SERVICE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.FOREGROUND_SERVICE_LOCATION }, 1);
            } else {
                ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.FOREGROUND_SERVICE_LOCATION}, 1);
            }

        } else {
            runService();
        }
    }

}
