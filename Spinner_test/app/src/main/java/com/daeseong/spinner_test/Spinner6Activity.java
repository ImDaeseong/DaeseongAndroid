package com.daeseong.spinner_test;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import com.daeseong.spinner_test.Controls.ComboBox;

public class Spinner6Activity extends AppCompatActivity {

    private static final String TAG = Spinner6Activity.class.getSimpleName();

    private ComboBox cb1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner6);

        cb1 = findViewById(R.id.cb1);


        cb1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String selectedItem = (String) cb1.getItemAtPosition(position);
                Log.e(TAG, selectedItem);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        getComboData();
    }

    private void getComboData() {
        cb1.addItem("항목 1");
        cb1.addItem("항목 2");
        cb1.addItem("항목 3");
    }

}