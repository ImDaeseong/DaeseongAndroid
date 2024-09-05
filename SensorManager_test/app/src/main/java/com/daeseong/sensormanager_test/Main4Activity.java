package com.daeseong.sensormanager_test;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class Main4Activity extends AppCompatActivity {

    private SensorInfo sensorInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        sensorInfo.getInstance().initSensor(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        sensorInfo.getInstance().DestorySeonsor();

    }
}
