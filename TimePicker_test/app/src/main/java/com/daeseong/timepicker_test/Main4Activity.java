package com.daeseong.timepicker_test;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Main4Activity extends AppCompatActivity {

    private static final String TAG = Main4Activity.class.getSimpleName();

    private Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        button1 = findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callTimePicker();
            }
        });
    }

    private void callTimePicker(){

        final TimePickerDialogEx timePickerDialogEx = new TimePickerDialogEx(Main4Activity.this);
        timePickerDialogEx.show();
        timePickerDialogEx.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if (timePickerDialogEx.BUTTON_TYPE == Dialog.BUTTON_POSITIVE) {

                    Log.e(TAG, "nhour:" +  timePickerDialogEx.getHour() + " nMinute:" + timePickerDialogEx.getMinute());

                } else if(timePickerDialogEx.BUTTON_TYPE == Dialog.BUTTON_NEGATIVE) {

                    Log.e(TAG, "BUTTON_NEGATIVE:" );
                }
            }
        });

    }
}
