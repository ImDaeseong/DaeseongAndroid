package com.daeseong.sensormanager_test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity implements SensorEventListener {

    private static final String TAG = Main2Activity.class.getSimpleName();

    private TextView tv1, tv2;
    private SensorManager sensorManager = null;
    private Sensor Accelsensor = null;
    private Sensor Lightsensor = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);

        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        if(sensorManager != null){
            Accelsensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            Lightsensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
            sensorManager.registerListener(this, Accelsensor, SensorManager.SENSOR_DELAY_NORMAL);
            sensorManager.registerListener(this, Lightsensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(sensorManager != null) {
            sensorManager.unregisterListener(this);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {

            double dX = event.values[0];
            double dY = event.values[1];
            double dZ = event.values[2];
            double angleX = Math.atan2(dX,  dZ) * 180/Math.PI;
            double angleY = Math.atan2(dY,  dZ) * 180/Math.PI;

            final String sValue = String.format("X:%.4f Y:%.4f", angleX, angleY);

            Log.e(TAG, sValue);

            //final String sValue = String.format("X:%s Y:%s Z:%s", Float.toString(event.values[0]), Float.toString(event.values[1]), Float.toString(event.values[2]));
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    tv1.setText(sValue);
                }
            });

        } else if( event.sensor.getType() == Sensor.TYPE_LIGHT){

            Log.e(TAG, String.valueOf(event.values[0]));

            final String sValue = String.format("TYPE_LIGHT:%.4f", event.values[0]);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    tv2.setText(sValue);
                }
            });

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        Log.e(TAG, "onAccuracyChanged");
    }
}
