package com.daeseong.bottomsheetdialog_test;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main5Activity extends AppCompatActivity {

    private static final String TAG = Main5Activity.class.getSimpleName();

    private Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        button1 = (Button)findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                BottomFragmentDialog bottomFragmentDialog = new BottomFragmentDialog();
                bottomFragmentDialog.show(getSupportFragmentManager(), "");
            }
        });
    }
}