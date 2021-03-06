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
    private static double  currentLongitude;

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

        if (broadcastReceiver == null) {
            broadcastReceiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {

                    currentLatitude = (intent.getDoubleExtra("LATITUDE", 0.0));
                    currentLongitude = (intent.getDoubleExtra("LONGITUDE", 0.0));
                    //Log.e(TAG, "onReceive currentLatitude: " + currentLatitude);
                    //Log.e(TAG, "onReceive currentLongitude: " + currentLongitude);

                    String sMsg = String.format("currentLatitude %f currentLongitude %f", currentLatitude, currentLongitude);
                    Toast.makeText(MainActivity.this,sMsg, Toast.LENGTH_SHORT).show();
                }
            };
            registerReceiver(broadcastReceiver, new IntentFilter("LOCATION_UPDATE"));
        }

        checkPermission();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //Log.e(TAG, "onDestroy");

        stopService();

        if (broadcastReceiver != null) {
            unregisterReceiver(broadcastReceiver);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        //Log.e(TAG, "onRequestPermissionsResult");

        if(requestCode == 1){

            // 네트워크 권한
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                //Log.e(TAG, "네트워크 권한 없음");

            } else {

                //Log.e(TAG, "네트워크 권한 있음");
                runService();
            }
        }
    }

    private void runService(){

        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

                //Log.e(TAG, "startForegroundService");

                if (LocationService.serviceIntent == null) {

                    Intent intent = new Intent(this, LocationService.class);
                    startForegroundService(intent);
                } else {
                    Log.e(TAG, "startForegroundService 이미 실행중");
                }

            } else {

                //Log.e(TAG, "startService");

                if (LocationService.serviceIntent == null) {

                    Intent intent = new Intent(this, LocationService.class);
                    startService(intent);
                } else {
                    Log.e(TAG, "startService 이미 실행중");
                }
            }
        }catch (Exception ex){
            Log.e(TAG, ex.getMessage().toString());
        }
    }

    private void stopService(){
        try{
            Intent intent = new Intent(this, LocationService.class);
            stopService(intent);
        }catch (Exception ex){
            Log.e(TAG, ex.getMessage().toString());
        }
    }

    private void checkPermission(){

        // 네트워크 권한
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            //Log.e(TAG, "네트워크 권한 없음");

            if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.ACCESS_FINE_LOCATION)){

                //Log.e(TAG, "사용자가 네트워크 권한 취소시 권한 재요청");
                ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }
            else {

                //Log.e(TAG, "최초로 네트워크 권한 요청 첫실행");
                ActivityCompat.requestPermissions(this, new String[]  {Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }
        } else {

            //Log.e(TAG, "네트워크 권한 있음");
            runService();
        }
    }

}