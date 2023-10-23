package com.daeseong.kakao_test;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.LoginButton;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeV2ResponseCallback;
import com.kakao.usermgmt.response.MeV2Response;
import com.kakao.util.exception.KakaoException;
import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {

    private static final String TAG = Main2Activity.class.getSimpleName();

    private SessionCallback sessionCallback;

    private LoginButton loginButton;
    private Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        initkakao();

        //로그인버튼 숨기기
        loginButton = findViewById(R.id.kakao_loginbtn);
        loginButton.setVisibility(View.GONE);

        button1 = findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //숨긴 로그인 버튼 클릭
                loginButton.performClick();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Session.getCurrentSession().removeCallback(sessionCallback);
    }

    private void  initkakao(){
        sessionCallback = new SessionCallback();
        Session.getCurrentSession().addCallback(sessionCallback);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)){
            return;
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void SuccessCallActivity() {
        Intent intent = new Intent(this, SuccessActivity.class);
        startActivity(intent);
        finish();
    }

    private void FailedCallActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private class SessionCallback implements ISessionCallback {

        //로그인 성공한 상태
        @Override
        public void onSessionOpened() {

            Log.e(TAG,"onSessionOpened");

            if(Session.getCurrentSession().isOpened()){
                requestMe();
            }
        }

        //로그인 실패한 상태
        @Override
        public void onSessionOpenFailed(KakaoException ex) {

            Log.e(TAG,"onSessionOpenFailed:" + ex.getMessage().toString());
        }

        private void requestMe(){

            List<String> keys = new ArrayList<>();
            keys.add("properties.nickname");
            keys.add("properties.profile_image");
            keys.add("kakao_account.email");

            UserManagement.getInstance().me(keys, new MeV2ResponseCallback() {
                @Override
                public void onFailure(ErrorResult errorResult) {
                    Log.e(TAG,"onSessionOpenFailed:" + errorResult.getException().toString());

                    //실패시 이동할 페이지
                    FailedCallActivity();
                }

                @Override
                public void onSessionClosed(ErrorResult errorResult) {
                }

                @Override
                public void onSuccess(MeV2Response response) {

                    try {
                        Log.e(TAG,"user id : " + response.getId());
                        Log.e(TAG,"nickname : " + response.getNickname());

                        if(response.getKakaoAccount() != null) {
                            Log.e(TAG, "email: " + response.getKakaoAccount().getEmail());
                        }

                    }catch (Exception ex){
                        Log.e(TAG, ex.getMessage().toString());
                    }

                    //성공시 이동할 페이지
                    SuccessCallActivity();
                }
            });
        }
    }

}
