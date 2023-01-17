package com.daeseong.bottomsheetdialog_test;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Main6Activity extends AppCompatActivity {

    private static final String TAG = Main6Activity.class.getSimpleName();

    private Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        button1 = (Button)findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showCustomDialog1();
            }
        });
    }

    private void showCustomDialog1() {

        CustomDialog1 customDialog1 = new CustomDialog1(this);
        customDialog1.show();
        customDialog1.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        customDialog1.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        customDialog1.getWindow().getAttributes().windowAnimations = R.style.DialogTheme;
        customDialog1.getWindow().setGravity(Gravity.BOTTOM);
    }
}