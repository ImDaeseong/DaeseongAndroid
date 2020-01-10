package com.daeseong.sensormanager_test;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

public class SensorInfo {

    private static final String TAG = SensorInfo.class.getName();

    private SensorManager sensorManager = null;
    private Sensor Accelsensor = null;
    private Sensor Lightsensor = null;
    private Context mContext;

    private static SensorInfo instance;
    public static SensorInfo getInstance(){
        if( instance == null){
            instance = new SensorInfo();
        }
        return instance;
    }

    public void initSensor(Context context){

        Log.e(TAG, "initSensor" );

        mContext = context;
        sensorManager = (SensorManager)context.getSystemService(Context.SENSOR_SERVICE);
        if(sensorManager != null){
            Accelsensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            Lightsensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
            sensorManager.registerListener(sensorEventListener, Accelsensor, SensorManager.SENSOR_DELAY_NORMAL);
            sensorManager.registerListener(sensorEventListener, Lightsensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    public void DestorySeonsor(){

        Log.e(TAG, "DestorySeonsor" );

        if(sensorManager != null) {
            sensorManager.unregisterListener(sensorEventListener);
        }
    }

    SensorEventListener sensorEventListener = new SensorEventListener() {

        public void onSensorChanged(SensorEvent event) {

            Log.e(TAG, "onSensorChanged" );

            if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {

                double dX = event.values[0];
                double dY = event.values[1];
                double dZ = event.values[2];
                double angleX = Math.atan2(dX,  dZ) * 180/Math.PI;
                double angleY = Math.atan2(dY,  dZ) * 180/Math.PI;
                final double fFrentX = Math.abs(angleX);
                final double fFrentY = Math.abs(angleY);

                if(fFrentX > 170 && fFrentY > 170){

                    Intent item = new Intent("com.daeseong.sensormanager_test.Front");
                    item.putExtra("front", "뒤면");
                    mContext.sendBroadcast(item);

                }else {

                    Intent item = new Intent("com.daeseong.sensormanager_test.Front");
                    item.putExtra("front", "앞면");
                    mContext.sendBroadcast(item);
                }

            } else if( event.sensor.getType() == Sensor.TYPE_LIGHT){

                final float fFrent = Math.abs(event.values[0]);
                if(fFrent == 0){

                    Intent item = new Intent("com.daeseong.sensormanager_test.Front");
                    item.putExtra("front", "뒤면");
                    mContext.sendBroadcast(item);

                }else {

                    Intent item = new Intent("com.daeseong.sensormanager_test.Front");
                    item.putExtra("front", "앞면");
                    mContext.sendBroadcast(item);
                }
            }
        }

        public void onAccuracyChanged(Sensor sensor, int accuracy) {

            Log.e(TAG, "onAccuracyChanged" );
        }
    };

}


