package com.daeseong.spinner_test;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import java.util.ArrayList;
import java.util.List;

public class Spinner2Activity extends AppCompatActivity {

    private static final String TAG = Spinner2Activity.class.getSimpleName();

    private Spinner sp1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner2);

        sp1 = findViewById(R.id.sp1);

        List<String> list = new ArrayList<>();
        list.add("항목 1");
        list.add("항목 2");
        list.add("항목 3");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);
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