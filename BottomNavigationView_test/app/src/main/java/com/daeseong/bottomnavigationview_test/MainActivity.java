package com.daeseong.bottomnavigationview_test;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    private final int[] buttonIds = {
            R.id.button1,
            R.id.button2,
            R.id.button3,
            R.id.button4,
            R.id.button5,
            R.id.button6,
            R.id.button7
    };

    private final Class<?>[] activities = {
            Main1Activity.class,
            Main2Activity.class,
            Main3Activity.class,
            Main4Activity.class,
            Main5Activity.class,
            Main6Activity.class,
            Main7Activity.class
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for (int buttonId : buttonIds) {
            Button button = findViewById(buttonId);
            if (button != null) {
                button.setOnClickListener(this);
            }
        }
    }

    @Override
    public void onClick(View v) {

        int id = v.getId();
        for (int i = 0; i < buttonIds.length; i++) {
            if (id == buttonIds[i]) {
                startActivity(new Intent(this, activities[i]));
                return;
            }
        }

    }
}
