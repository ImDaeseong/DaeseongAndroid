package com.daeseong.kakao_test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.kakao.auth.ApiResponseCallback;
import com.kakao.auth.AuthService;
import com.kakao.auth.Session;
import com.kakao.auth.network.response.AccessTokenInfoResponse;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.LoginButton;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeV2ResponseCallback;
import com.kakao.usermgmt.callback.UnLinkResponseCallback;
import com.kakao.usermgmt.response.MeV2Response;

import java.util.ArrayList;
import java.util.List;

public class Main3Activity extends AppCompatActivity {

    private static final String TAG = Main3Activity.class.getSimpleName();

    private LoginButton loginButton;
    private Button button1, button2, button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        //카카오 로그인
        loginButton = findViewById(R.id.kakao_loginbtn);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //실행 시 로그인 토큰이 있으면 자동으로 로그인 수행
                Session.getCurrentSession().checkAndImplicitOpen();
            }
        });

        //getNickname
        button1 = findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!(Session.getCurrentSession().checkAndImplicitOpen())) {
                    Log.e(TAG,"연결해제된 아이디");
                }

                List<String> keys = new ArrayList<>();
                keys.add("properties.nickname");

                UserManagement.getInstance().me(keys, new MeV2ResponseCallback() {
                    @Override
                    public void onSessionClosed(ErrorResult errorResult) {
                    }

                    @Override
                    public void onSuccess(MeV2Response result) {
                        Log.e(TAG,"getNickname : " + result.getNickname());
                    }
                });

            }
        });

        //getUserId
        button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AuthService.getInstance().requestAccessTokenInfo(new ApiResponseCallback<AccessTokenInfoResponse>() {
                    @Override
                    public void onSessionClosed(ErrorResult errorResult) {
                    }

                    @Override
                    public void onNotSignedUp() {
                    }

                    @Override
                    public void onSuccess(AccessTokenInfoResponse result) {
                        Log.e(TAG,"getUserId : " + result.getUserId());
                    }
                });
            }

        });

        //로그아웃
        button3 = findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UserManagement.getInstance().requestUnlink(new UnLinkResponseCallback() {
                    @Override
                    public void onSessionClosed(ErrorResult errorResult) {
                    }

                    @Override
                    public void onNotSignedUp() {
                    }

                    @Override
                    public void onSuccess(Long result) {
                        Log.e(TAG,"정상적으로 로그아웃되었습니다.");
                    }
                });

            }
        });

    }
}
