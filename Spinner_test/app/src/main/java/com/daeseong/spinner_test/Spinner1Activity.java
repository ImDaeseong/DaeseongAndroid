package com.daeseong.spinner_test;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class Spinner1Activity extends AppCompatActivity {

    private static final String TAG = Spinner1Activity.class.getSimpleName();

    private Spinner sp1, sp2, sp3;

    private static final String[] items = {"항목 1", "항목 2", "항목 3"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner1);

        sp1 = findViewById(R.id.sp1);
        sp2 = findViewById(R.id.sp2);
        sp3 = findViewById(R.id.sp3);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sp1.setAdapter(adapter);
        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String sItem = parent.getItemAtPosition(position).toString();
                Log.e(TAG, "sp1 선택 항목:" + sItem);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        sp2.setAdapter(adapter);
        sp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String sItem = parent.getItemAtPosition(position).toString();
                Log.e(TAG, "sp2 선택 항목:" + sItem);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        sp3.setAdapter(adapter);
        sp3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String sItem = parent.getItemAtPosition(position).toString();
                Log.e(TAG, "sp3 선택 항목:" + sItem);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }
}