package com.daeseong.spinner_test;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class Spinner4Activity extends AppCompatActivity {

    private static final String TAG = Spinner4Activity.class.getSimpleName();

    private Spinner sp1;

    private static final String[] items = {"항목 1", "항목 2", "항목 3"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner4);

        sp1 = findViewById(R.id.sp1);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp1.setAdapter(adapter);

        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String sItem = parent.getItemAtPosition(position).toString();
                Log.e(TAG, "선택 항목:" + sItem);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
}