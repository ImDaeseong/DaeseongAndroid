package com.daeseong.locationservice_test;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;
import androidx.core.app.NotificationCompat;

public class LocationService extends Service implements LocationListener {

    private static final String TAG = LocationService.class.getSimpleName();

    private static final String CHANNEL_ID = "LocationService";

    protected LocationManager locationManager;

    public static Intent serviceIntent = null;

    @Override
    public IBinder onBind(Intent intent) {
        // 서비스는 바인딩되지 않음
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // 서비스 인텐트 저장
        serviceIntent = intent;

        // 알림 채널 생성
        createNotificationChannel();

        // 포그라운드 서비스 알림 설정
        Intent notificationIntent = new Intent(this, MainActivity.class);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Location Service Running")
                .setContentText("Tracking location in the background")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .build();

        // 포그라운드 서비스 시작
        startForeground(1, notification);

        // 위치 정보 가져오기
        getLocation();

        // START_STICKY로 서비스가 비정상적으로 종료된 경우 자동으로 재시작
        return START_STICKY;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, "Service Created");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "Service Destroyed");

        // 서비스 인텐트 null로 설정
        serviceIntent = null;

        // 위치 업데이트 중지
        if (locationManager != null) {
            try {
                locationManager.removeUpdates(this);
            } catch (Exception ex) {
                Log.e(TAG, ex.getMessage());
            }
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.e(TAG, "onLocationChanged getLatitude: " + location.getLatitude());
        Log.e(TAG, "onLocationChanged getLongitude: " + location.getLongitude());

        // 위치 업데이트 브로드캐스트
        Intent intent = new Intent("LOCATION_UPDATE");
        intent.putExtra("LATITUDE", location.getLatitude());
        intent.putExtra("LONGITUDE", location.getLongitude());
        sendBroadcast(intent);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // 상태 변경 처리 (deprecated in API level 29)
    }

    @Override
    public void onProviderEnabled(String provider) {
        // 위치 제공자 활성화 처리
    }

    @Override
    public void onProviderDisabled(String provider) {
        // 위치 제공자 비활성화 처리
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // 알림 채널 생성
            NotificationChannel notificationChannel = new NotificationChannel(
                    CHANNEL_ID,
                    "LocationService NotificationChannel",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(notificationChannel);
            }
        }
    }

    private void getLocation() {
        try {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

            // GPS 및 네트워크 제공자 사용 가능 여부 확인
            boolean bGPS = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            boolean bNetwork = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

            // GPS와 네트워크 둘 다 사용 불가능할 경우 위치 설정 화면으로 이동
            if (!bGPS && !bNetwork) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getApplicationContext().startActivity(intent);
                return;
            }

            // 네트워크 제공자가 활성화된 경우 위치 요청
            if (bNetwork) {
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 1, this);
                Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                if (location != null) {
                    Log.e(TAG, "Network getLatitude: " + location.getLatitude());
                    Log.e(TAG, "Network getLongitude: " + location.getLongitude());
                }
            }

            // GPS 제공자가 활성화된 경우 위치 요청
            if (bGPS) {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 1, this);
                Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                if (location != null) {
                    Log.e(TAG, "GPS getLatitude: " + location.getLatitude());
                    Log.e(TAG, "GPS getLongitude: " + location.getLongitude());
                }
            }

        } catch (SecurityException ex) {
            Log.e(TAG, ex.getMessage());
        }
    }
}
