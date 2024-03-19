package com.daeseong.spinner_test;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import com.google.android.material.textfield.TextInputLayout;
import java.util.Arrays;
import java.util.List;

public class Spinner5Activity extends AppCompatActivity {

    private static final String TAG = Spinner5Activity.class.getSimpleName();

    private AutoCompleteTextView atv1;
    private TextInputLayout tiL1;

    private List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner5);

        tiL1 = findViewById(R.id.tiL1);
        atv1 = findViewById(R.id.atv1);


        atv1.setOnItemClickListener((parent, view, position, id) -> {
            String selected = list.get(position);
            Log.d(TAG, selected);
        });

        atv1.setOnFocusChangeListener((v, hasFocus) -> {
            tiL1.setHint(hasFocus ? "" : "선택하세요");
        });

        atv1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                tiL1.setHint(TextUtils.isEmpty(s) ? "선택하세요" : "");
            }
        });

        getComboData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        list = null;
    }

    private void getComboData() {
        list = Arrays.asList("데이터1", "데이터2", "데이터3");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, list);
        atv1.setAdapter(adapter);
    }
}