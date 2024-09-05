package com.daeseong.timepicker_test;

import androidx.appcompat.app.AppCompatActivity;
import android.app.TimePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import java.util.Calendar;

public class Main3Activity extends AppCompatActivity {

    private static final String TAG = Main3Activity.class.getSimpleName();

    private Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        button1 = findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callTimePicker();
            }
        });
    }

    private void callTimePicker(){

        Calendar calendar = Calendar.getInstance();
        int nhour = calendar.get(Calendar.HOUR_OF_DAY);
        int nMinute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog;
        timePickerDialog = new TimePickerDialog(Main3Activity.this, new TimePickerDialog.OnTimeSetListener() {

            private int nhour, nMinute;

            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    nhour = view.getHour();
                    nMinute = view.getMinute();
                } else {
                    nhour = view.getCurrentHour();
                    nMinute = view.getCurrentMinute();
                }

                Log.e(TAG, "nhour:" + nhour + " nMinute:" + nMinute);
                Log.e(TAG, "hourOfDay:" + hourOfDay + " minute:" + minute);
            }
        }, nhour, nMinute, true);
        timePickerDialog.setTitle("날짜 선택");
        timePickerDialog.show();
    }

}
