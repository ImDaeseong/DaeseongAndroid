package com.daeseong.kakao_test;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.util.exception.KakaoException;

public class Main1Activity extends AppCompatActivity {

    private static final String TAG = Main1Activity.class.getSimpleName();

    private SessionCallback sessionCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

        initkakao_login();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Session.getCurrentSession().removeCallback(sessionCallback);
    }

    private void  initkakao_login(){
        sessionCallback = new SessionCallback();
        Session.getCurrentSession().addCallback(sessionCallback);

        //실행 시 로그인 토큰이 있으면 자동으로 로그인 수행
        Session.getCurrentSession().checkAndImplicitOpen();
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

    private class SessionCallback implements ISessionCallback {

        //로그인 성공한 상태
        @Override
        public void onSessionOpened() {

            Log.e(TAG,"onSessionOpened");

            if(Session.getCurrentSession().isOpened()){
                SuccessCallActivity();
            }
        }

        //로그인 실패한 상태
        @Override
        public void onSessionOpenFailed(KakaoException ex) {

            Log.e(TAG,"onSessionOpenFailed:" + ex.getMessage().toString());
        }
    }

}
