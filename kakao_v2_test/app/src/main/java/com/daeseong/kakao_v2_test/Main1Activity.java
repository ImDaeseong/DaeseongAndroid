package com.daeseong.kakao_v2_test;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.kakao.sdk.share.ShareClient;
import com.kakao.sdk.user.UserApiClient;

public class Main1Activity extends AppCompatActivity {

    private static final String TAG = Main1Activity.class.getSimpleName();

    private Button button1, button2, button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

        button1 = (Button)findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (UserApiClient.getInstance().isKakaoTalkLoginAvailable(Main1Activity.this)) {
                    KakaoTalklogin();
                } else {
                    KakaoAccountlogin();
                }
            }
        });

        button2 = (Button)findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                kakaologout();
            }
        });

        button3 = (Button)findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                kakaoTokenInfo();
            }
        });
    }

    private void KakaoTalklogin() {

        UserApiClient.getInstance().loginWithKakaoTalk(this, (oAuthToken, error) -> {

            if (error != null) {
                Log.e(TAG, "로그인 실패", error);
            } else if (oAuthToken != null) {
                Log.e(TAG, "로그인 성공(토큰) : " + oAuthToken.getAccessToken());
                LoginInfo();
            }
            return null;
        });
    }

    private void KakaoAccountlogin() {

        UserApiClient.getInstance().loginWithKakaoAccount(this, (oAuthToken, error) -> {

            if (error != null) {
                Log.e(TAG, "로그인 실패", error);
            } else if (oAuthToken != null) {
                Log.i(TAG, "로그인 성공(토큰) : " + oAuthToken.getAccessToken());
                LoginInfo();
            }
            return null;
        });
    }

    private void LoginInfo() {

        UserApiClient.getInstance().me((user, throwable) -> {

            if (user != null) {
                try {
                    Log.e(TAG, "getId : " + user.getId());
                    //Log.e(TAG, "getNickname : " + user.getKakaoAccount().getProfile().getNickname());
                    //Log.e(TAG, "getEmail : " + user.getKakaoAccount().getEmail());
                    //Log.e(TAG, "getGender : " + user.getKakaoAccount().getGender());
                    //Log.e(TAG, "getAgeRange : " + user.getKakaoAccount().getAgeRange());
                    //Log.e(TAG, "getProfileImageUrl : " + user.getKakaoAccount().getProfile().getProfileImageUrl());
                    Log.e(TAG, "카카오 로그인 성공");
                } catch (Exception ex) {
                    Log.e(TAG, ex.getMessage().toString());
                }
            } else {
                Log.e(TAG, "카카오 로그인 실패: ", throwable);
            }
            return null;
        });
    }

    //카카오 로그아웃
    private void kakaologout() {

        UserApiClient.getInstance().logout(error -> {
            if (error != null) {
                Log.e(TAG, "kakao 로그아웃 실패");
            }else{
                Log.e(TAG, "kakao 로그아웃 성공");
            }
            return null;
        });
    }

    //카카오 토큰 정보
    private void kakaoTokenInfo() {

        UserApiClient.getInstance().accessTokenInfo( (accessTokenInfo, throwable) -> {
            if (throwable != null) {
                Log.e(TAG, "kakao 토큰 정보 호출 실패");
            } else if(accessTokenInfo != null) {
                Log.e(TAG, "getId:" + accessTokenInfo.getId());
                Log.e(TAG, "만료시간:" + accessTokenInfo.getExpiresIn());
            }
            return null;
        });
    }
}