package com.daeseong.kakao_test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;

public class SuccessActivity extends AppCompatActivity {

    private static final String TAG = SuccessActivity.class.getSimpleName();

    private Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);

        button1 = findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UserManagement.getInstance().requestLogout(new LogoutResponseCallback() {
                    @Override
                    public void onCompleteLogout() {

                        Log.e(TAG,"onCompleteLogout:" + "정상적으로 로그아웃되었습니다.");

                        Intent intent = new Intent(SuccessActivity.this, MainActivity.class);
                        startActivity(intent);

                    }
                });
            }
        });

    }
}
