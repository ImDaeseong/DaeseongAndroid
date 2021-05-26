package com.daeseong.backgroundlocation_test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private Button button1, button2;

    //브로드캐스트
    private BroadcastReceiver broadcastReceiver = null;
    private IntentFilter intentFilter = null;
    private static double currentLatitude;
    private static double  currentLongitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //브로드캐스트 등록
        initFilter();

        button1 = findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                requsetPermission();
            }
        });

        button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Log.e(TAG, "stopService 중지");
                stopService();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //브로드캐스트 해제
        DestoryFilter();
    }

    private void initFilter(){

        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                currentLatitude = (intent.getDoubleExtra("LATITUDE", 0.0));
                currentLongitude = (intent.getDoubleExtra("LONGITUDE", 0.0));
                //Log.e(TAG, "onReceive currentLatitude: " + currentLatitude);
                //Log.e(TAG, "onReceive currentLongitude: " + currentLongitude);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        String sMsg = String.format("currentLatitude %f currentLongitude %f", currentLatitude, currentLongitude);
                        Toast.makeText(MainActivity.this,sMsg, Toast.LENGTH_SHORT).show();
                    }
                }, 100);

            }
        };

        intentFilter = new IntentFilter();
        intentFilter.addAction("com.daeseong.backgroundlocation_test.Location");
        registerReceiver(broadcastReceiver, intentFilter);
    }

    private void DestoryFilter(){
        if(broadcastReceiver != null) {
            unregisterReceiver(broadcastReceiver);
        }
    }

    public void runService(){
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

                if (LocationService.serviceIntent == null) {

                    Intent intent = new Intent(this, LocationService.class);
                    startForegroundService(intent);
                }

            } else {
                if (LocationService.serviceIntent == null) {

                    Intent intent = new Intent(this, LocationService.class);
                    startService(intent);
                }
            }
        }catch (Exception ex){
            ex.getMessage().toString();
        }
    }

    public void stopService(){
        try{

            Intent intent = new Intent(this, LocationService.class);
            stopService(intent);
        }catch (Exception ex){
            ex.getMessage().toString();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1) {

            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                if (checkBackgroundLocation()) {

                    //Log.e(TAG, "ACCESS_BACKGROUND_LOCATION 권한 있음 1");
                    runService();

                } else {

                    //Log.e(TAG, "ACCESS_BACKGROUND_LOCATION 권한 체크");
                    requestBackgrundLocation();
                }
            }
        }
        else if (requestCode == 2) {

            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                //Log.e(TAG, "ACCESS_BACKGROUND_LOCATION 권한 있음 2");
                runService();
            }
        }

    }

    public void requsetPermission() {

        if (checkFindLocation() && checkCoarseLocation() ) {

            if (checkBackgroundLocation()) {

                //Log.e(TAG, "ACCESS_BACKGROUND_LOCATION 권한 있으므로 runService 실행");
                runService();
            } else {

                //Log.e(TAG, "ACCESS_BACKGROUND_LOCATION 권한 요청");
                requestBackgrundLocation();
            }

        } else {

            //Log.e(TAG, "ACCESS_FINE_LOCATION 권한 요청");
            requestFineLocation();
        }
    }

    public boolean checkBackgroundLocation() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            return ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_BACKGROUND_LOCATION) == PackageManager.PERMISSION_GRANTED;
        } else {
            return true;
        }
    }

    public boolean checkFindLocation() {
        return ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }

    public boolean checkCoarseLocation(){

        return ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }

    public void requestFineLocation(){

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
    }

    public void requestBackgrundLocation(){

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_BACKGROUND_LOCATION},2);
    }
}