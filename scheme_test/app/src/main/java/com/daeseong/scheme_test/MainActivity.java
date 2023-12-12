package com.daeseong.scheme_test;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        Uri uri = intent.getData();
        if (uri != null) {
            String scheme = uri.getScheme();
            String param1 = uri.getQueryParameter("param1");
            String param2 = uri.getQueryParameter("param2");
            Toast.makeText(this, scheme + " param1: " + param1 + ", param2: " + param2, Toast.LENGTH_SHORT).show();
        }
    }
}