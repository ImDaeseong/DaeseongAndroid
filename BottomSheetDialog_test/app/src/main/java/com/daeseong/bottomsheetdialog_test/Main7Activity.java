package com.daeseong.bottomsheetdialog_test;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Main7Activity extends AppCompatActivity {

    private static final String TAG = Main7Activity.class.getSimpleName();

    private Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);

        button1 = (Button)findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showCustomDialog3();
            }
        });
    }

    private void showCustomDialog3() {

        CustomDialog3 customDialog3 = new CustomDialog3(this);
        customDialog3.show();
        customDialog3.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        customDialog3.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        customDialog3.getWindow().getAttributes().windowAnimations = R.style.DialogTheme;
        customDialog3.getWindow().setGravity(Gravity.BOTTOM);

        //customDialog3.getWindow().getDecorView().setPadding(0,0,0, 100);
    }
}