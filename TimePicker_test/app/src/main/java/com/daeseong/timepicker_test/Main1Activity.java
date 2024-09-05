package com.daeseong.timepicker_test;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.TimePicker;

public class Main1Activity extends AppCompatActivity {

    private static final String TAG = Main1Activity.class.getSimpleName();

    private TimePicker timepicker1;
    private int nhour, nMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

        timepicker1 = (TimePicker)findViewById(R.id.timepicker1);
        timepicker1.setIs24HourView(false);
        timepicker1.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {

                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    nhour = timepicker1.getHour();
                    nMinute = timepicker1.getMinute();
                } else {
                    nhour = timepicker1.getCurrentHour();
                    nMinute = timepicker1.getCurrentMinute();
                }

                Log.e(TAG, "nhour:" + nhour + " nMinute:" + nMinute);
                Log.e(TAG, "hourOfDay:" + hourOfDay + " minute:" + minute);
            }
        });

    }
}
