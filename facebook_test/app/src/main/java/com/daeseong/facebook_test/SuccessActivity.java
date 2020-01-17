package com.daeseong.facebook_test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.facebook.login.LoginManager;

public class SuccessActivity extends AppCompatActivity {

    private static final String TAG = SuccessActivity.class.getSimpleName();

    private Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String email = intent.getStringExtra("email");
        String name = intent.getStringExtra("name");
        Log.e(TAG, "id:" + id);
        Log.e(TAG, "email:" + email);
        Log.e(TAG, "name:" + name);


        button1 = findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //페이스북 로그아웃
                LoginManager.getInstance().logOut();

                Intent intent = new Intent(SuccessActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
