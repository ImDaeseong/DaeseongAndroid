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

        //Log.e(TAG, "onBind");

        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        //Log.e(TAG, "onStartCommand");

        serviceIntent = intent;

        createNotificationChannel();

        Intent notificationIntent = new Intent(this, MainActivity.class);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("")
                .setContentText("")
                .build();
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        startForeground(1, notification);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            stopForeground(true);
        } else {
            stopSelf();
        }

        getLocation();

        return START_NOT_STICKY;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        //Log.e(TAG, "onCreate");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        //Log.e(TAG, "onDestroy");

        serviceIntent = null;

        if(locationManager != null){
            try {
                locationManager.removeUpdates(this);
            } catch (Exception ex) {
                Log.e(TAG, ex.getMessage().toString());
            }
        }
    }

    @Override
    public void onLocationChanged(Location location) {

        //Log.e(TAG, "onLocationChanged getLatitude: " + location.getLatitude());
        //Log.e(TAG, "onLocationChanged getLongitude: " + location.getLongitude());

        Intent intent = new Intent("LOCATION_UPDATE");
        intent.putExtra("LATITUDE", location.getLatitude());
        intent.putExtra("LONGITUDE", location.getLongitude());
        sendBroadcast(intent);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //name 에 값이 없으면 않됨
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID,"LocationService NotificationChannel", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }

    private void getLocation() {

        try {

            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            boolean bGPS = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);//GPS 이용 위치,  android.permission.ACCESS_FINE_LOCATION
            boolean bNetwork = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);//기지국, WIFI, android.permission.ACCESS_FINE_LOCATION||android.permission.ACCESS_COARSE_LOCATION

            //Log.e(TAG, "bGPS:" + bGPS);
            //Log.e(TAG, "bNetwork:" + bNetwork);

            if (!bGPS && !bNetwork) {

                //위치 사용 설정
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getApplicationContext().startActivity(intent);
                return;
            }

            if (bNetwork) {

                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 1,  this);
                Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                if (location != null) {
                    Log.e(TAG, "Network getLatitude: " + location.getLatitude());
                    Log.e(TAG, "Network getLongitude: " + location.getLongitude());
                }
            } else {

                //onLocationChanged 에서 받을수 있음, 실내에서는 테스트 불가 , 실외에서 테스트 필요
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 1,  this);
                Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                if (location != null) {
                    Log.e(TAG, "GPS getLatitude: " + location.getLatitude());
                    Log.e(TAG, "GPS getLongitude: " + location.getLongitude());
                }
            }

        }catch (SecurityException ex){
            Log.e(TAG, ex.getMessage().toString());
        }
    }
}