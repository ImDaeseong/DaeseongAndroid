package com.daeseong.sensormanager_test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class Main1Activity extends AppCompatActivity {

    private static final String TAG = Main1Activity.class.getSimpleName();

    private TextView tv1;
    private StringBuffer stringBuffer = new StringBuffer();
    private SensorManager sensorManager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        if(sensorManager != null){

            if(sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null) {
                stringBuffer.append("TYPE_ACCELEROMETER 가속도 감지,외부충격량과 방향 감지 센서\n");
            }

            if(sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE) != null) {
                stringBuffer.append("TYPE_GYROSCOPE 자이로스코프\n");
            }

            if(sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT) != null) {
                stringBuffer.append("TYPE_LIGHT 빛의 세기 감지 센서\n");
            }

            if(sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER) != null) {
                stringBuffer.append("TYPE_STEP_COUNTER 발걸음 횟수 센서\n");
            }

            if(sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR) != null) {
                stringBuffer.append("TYPE_STEP_DETECTOR 발걸음 감지 센서\n");
            }
        }

        tv1 = findViewById(R.id.tv1);
        tv1.setText(stringBuffer.toString());
    }
}
