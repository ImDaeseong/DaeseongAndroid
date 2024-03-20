package com.im.daeseong.checkbox_test;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Check4Activity extends AppCompatActivity {

    private static final String TAG = Check4Activity.class.getSimpleName();

    private CheckImage ci1;
    private Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check4);

        ci1 = findViewById(R.id.ci1);
        ci1.setCheck(false);

        btn1 = findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, String.valueOf(ci1.isChecked()));
            }
        });
    }

}
