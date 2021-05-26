package com.daeseong.backgroundlocation_test;

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
import android.util.Log;

import androidx.core.app.NotificationCompat;

public class LocationService extends Service implements LocationListener {

    private static final String TAG = LocationService.class.getSimpleName();

    private static final String CHANNEL_ID = "backgroundlocation_testLocationService";

    protected LocationManager locationManager;

    public static Intent serviceIntent = null;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        serviceIntent = intent;

        try {
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
        } catch (Exception ex) {
            Log.e(TAG, ex.getMessage().toString());
        }

        getLocation();

        return START_NOT_STICKY;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        serviceIntent = null;

        if (locationManager != null) {
            try {
                locationManager.removeUpdates(this);
            } catch (Exception ex) {
                Log.e(TAG, ex.getMessage().toString());
            }
        }
    }

    @Override
    public void onLocationChanged(Location lastLocation) {

        try {

            Intent item = new Intent("com.daeseong.backgroundlocation_test.Location");
            item.putExtra("LATITUDE", lastLocation.getLatitude());
            item.putExtra("LONGITUDE", lastLocation.getLongitude());
            getApplicationContext().sendBroadcast(item);
            //onDestroy();
        } catch (Exception ex) {
            ex.getMessage().toString();
        }
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
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, "내위치 정보", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }

    private void getLocation() {

        try {

            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            boolean bGPS = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);//GPS 이용 위치,  android.permission.ACCESS_FINE_LOCATION
            boolean bNetwork = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);//기지국, WIFI, android.permission.ACCESS_FINE_LOCATION||android.permission.ACCESS_COARSE_LOCATION

            if (!bGPS && !bNetwork) {

                //Log.e(TAG, "getLocation GPS Network 둘다 미사용");

                Intent item = new Intent("com.daeseong.backgroundlocation_test.Location");
                item.putExtra("LATITUDE", 0);
                item.putExtra("LONGITUDE", 0);
                getApplicationContext().sendBroadcast(item);
                return;
            }

            if (bNetwork) {

                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 0, this);
                Location lastLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                if (lastLocation != null) {

                    Intent item = new Intent("com.daeseong.backgroundlocation_test.Location");
                    item.putExtra("LATITUDE", lastLocation.getLatitude());
                    item.putExtra("LONGITUDE", lastLocation.getLongitude());
                    getApplicationContext().sendBroadcast(item);
                    //onDestroy();
                }

            } else {

                //onLocationChanged 에서 받을수 있음, 실내에서는 테스트 불가 , 실외에서 테스트 필요
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, this);
                Location lastLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                if (lastLocation != null) {

                    Intent item = new Intent("com.daeseong.backgroundlocation_test.Location");
                    item.putExtra("LATITUDE", lastLocation.getLatitude());
                    item.putExtra("LONGITUDE", lastLocation.getLongitude());
                    getApplicationContext().sendBroadcast(item);
                    //onDestroy();
                }
            }

        } catch (SecurityException ex) {
        }
    }
}