package com.daeseong.sensormanager_test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class Main3Activity extends AppCompatActivity {

    private static final String TAG = Main3Activity.class.getSimpleName();

    private TextView tv1, tv2;
    private SensorManager sensorManager = null;
    private Sensor Accelsensor = null;
    private Sensor Lightsensor = null;
    private SensorEventListener sensorEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);

        initSensor();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        DestorySeonsor();
    }

    private void initSensor(){

        sensorEventListener = new SensorListener();

        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        if(sensorManager != null){
            Accelsensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            Lightsensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
            sensorManager.registerListener(sensorEventListener, Accelsensor, SensorManager.SENSOR_DELAY_NORMAL);
            sensorManager.registerListener(sensorEventListener, Lightsensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    private void DestorySeonsor(){
        if(sensorManager != null) {
            sensorManager.unregisterListener(sensorEventListener);
        }
    }

    private class SensorListener implements SensorEventListener {

        @Override
        public void onSensorChanged(SensorEvent event) {

            Log.e(TAG, "onSensorChanged");

            if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {

                double dX = event.values[0];
                double dY = event.values[1];
                double dZ = event.values[2];
                double angleX = Math.atan2(dX,  dZ) * 180/Math.PI;
                double angleY = Math.atan2(dY,  dZ) * 180/Math.PI;
                final double fFrentX = Math.abs(angleX);
                final double fFrentY = Math.abs(angleY);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        if(fFrentX > 170 && fFrentY > 170){

                            Intent intent = new Intent(Main3Activity.this, LockActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                            DestorySeonsor();

                            tv1.setText("뒤면");
                        }else {
                            tv1.setText("앞면");
                        }
                    }
                });

            } else if( event.sensor.getType() == Sensor.TYPE_LIGHT){

                final float fFrent = Math.abs(event.values[0]);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(fFrent == 0){
                            tv2.setText("뒤면");
                        }else {
                            tv2.setText("앞면");
                        }
                    }
                });

            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
    }

}
