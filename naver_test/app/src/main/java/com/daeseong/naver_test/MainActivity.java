package com.daeseong.naver_test;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.nhn.android.naverlogin.OAuthLogin;
import com.nhn.android.naverlogin.OAuthLoginHandler;
import com.nhn.android.naverlogin.ui.view.OAuthLoginButton;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private static OAuthLogin oAuthLogin;
    private static Context mContext;

    //네이버 아이디로 로그인
    //https://developers.naver.com/apps/#/list
    private static String OAUTH_CLIENT_ID = "Client ID";
    private static String OAUTH_CLIENT_SECRET = "Client Secret";
    private static String OAUTH_CLIENT_NAME = "네이버 아이디로 로그인";


    private OAuthLoginButton oAuthLoginButton;
    private Button buttonOAuth, buttonVerifier, buttonRefresh, buttonOAuthLogout, buttonOAuthDeleteToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //네이버 로그인 초기화
        mContext = this;
        oAuthLogin = OAuthLogin.getInstance();
        //oAuthLogin.showDevelopersLog(true);
        oAuthLogin.init(mContext, OAUTH_CLIENT_ID, OAUTH_CLIENT_SECRET, OAUTH_CLIENT_NAME);

        //로그인 정보 업데이트
        updateView();


        //로그인 버튼 설정
        oAuthLoginButton = (OAuthLoginButton) findViewById(R.id.oAuthLoginButton1);
        oAuthLoginButton.setOAuthLoginHandler(mOAuthLoginHandler);

        //인증하기
        buttonOAuth = findViewById(R.id.buttonOAuth);
        buttonOAuth.setOnClickListener(new View.OnClickListener() {

            @SuppressLint("HandlerLeak")
            @Override
            public void onClick(View v) {

                //oAuthLogin.startOauthLoginActivity(MainActivity.this, mOAuthLoginHandler);
                OAuthLogin.getInstance().startOauthLoginActivity(MainActivity.this, new OAuthLoginHandler(){

                    @Override
                    public void run(boolean success) {

                        //사용자 정보 가져오기
                        String accessToken = oAuthLogin.getAccessToken(mContext);
                        String refreshToken = oAuthLogin.getRefreshToken(mContext);
                        long expiresAt = oAuthLogin.getExpiresAt(mContext);
                        String tokenType = oAuthLogin.getTokenType(mContext);

                        Log.e(TAG,"accessToken : " + accessToken);
                        Log.e(TAG,"refreshToken : " + refreshToken);
                        Log.e(TAG,"expiresAt : " + String.valueOf(expiresAt));
                        Log.e(TAG,"tokenType : " + tokenType);
                        Log.e(TAG,"oAuthLogin.getState : " + oAuthLogin.getState(mContext).toString());
                    }
                });
            }
        });

        //API호출
        buttonVerifier = findViewById(R.id.buttonVerifier);
        buttonVerifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new RequestApiTask().execute();
            }
        });

        //토큰다시받기
        buttonRefresh = findViewById(R.id.buttonRefresh);
        buttonRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new RefreshTokenTask().execute();
            }
        });

        //로그아웃
        buttonOAuthLogout = findViewById(R.id.buttonOAuthLogout);
        buttonOAuthLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!oAuthLogin.getState(mContext).toString().equals("NEED_LOGIN")){

                    oAuthLogin.logout(mContext);
                    updateView();
                }

            }
        });

        //연동끊기
        buttonOAuthDeleteToken = findViewById(R.id.buttonOAuthDeleteToken);
        buttonOAuthDeleteToken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new DeleteTokenTask().execute();
            }
        });
    }

    private void updateView() {

        //사용자 정보 가져오기
        String accessToken = oAuthLogin.getAccessToken(mContext);
        String refreshToken = oAuthLogin.getRefreshToken(mContext);
        long expiresAt = oAuthLogin.getExpiresAt(mContext);
        String tokenType = oAuthLogin.getTokenType(mContext);

        Log.e(TAG,"accessToken : " + accessToken);
        Log.e(TAG,"refreshToken : " + refreshToken);
        Log.e(TAG,"expiresAt : " + String.valueOf(expiresAt));
        Log.e(TAG,"tokenType : " + tokenType);
        Log.e(TAG,"oAuthLogin.getState : " + oAuthLogin.getState(mContext).toString());
    }

    static private OAuthLoginHandler mOAuthLoginHandler = new OAuthLoginHandler() {
        @Override
        public void run(boolean success) {

            //로그인 인증 성공
            if (success) {

                //사용자 정보 가져오기
                String accessToken = oAuthLogin.getAccessToken(mContext);
                String refreshToken = oAuthLogin.getRefreshToken(mContext);
                long expiresAt = oAuthLogin.getExpiresAt(mContext);
                String tokenType = oAuthLogin.getTokenType(mContext);

                Log.e(TAG,"accessToken : " + accessToken);
                Log.e(TAG,"refreshToken : " + refreshToken);
                Log.e(TAG,"expiresAt : " + String.valueOf(expiresAt));
                Log.e(TAG,"tokenType : " + tokenType);
                Log.e(TAG,"oAuthLogin.getState : " + oAuthLogin.getState(mContext).toString());

            } else {

                //로그인 인증 실패
                String errorCode = oAuthLogin.getLastErrorCode(mContext).getCode();
                String errorDesc = oAuthLogin.getLastErrorDesc(mContext);
                Log.e(TAG,"errorCode : " + errorCode + ", errorDesc:" + errorDesc);
            }
        }
    };

    private class DeleteTokenTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            boolean isSuccessDeleteToken = oAuthLogin.logoutAndDeleteToken(mContext);

            if (!isSuccessDeleteToken) {
                // 서버에서 token 삭제에 실패했어도 클라이언트에 있는 token 은 삭제되어 로그아웃된 상태이다
                // 실패했어도 클라이언트 상에 token 정보가 없기 때문에 추가적으로 해줄 수 있는 것은 없음
                Log.d(TAG, "errorCode:" + oAuthLogin.getLastErrorCode(mContext));
                Log.d(TAG, "errorDesc:" + oAuthLogin.getLastErrorDesc(mContext));
            }
            return null;
        }

        protected void onPostExecute(Void v) {
            updateView();
        }
    }

    private class RequestApiTask extends AsyncTask<Void, Void, String> {
        @Override
        protected void onPreExecute() {

            Log.d(TAG, "onPreExecute");
        }

        @Override
        protected String doInBackground(Void... params) {
            String url = "https://openapi.naver.com/v1/nid/me";
            String at = oAuthLogin.getAccessToken(mContext);
            return oAuthLogin.requestApi(mContext, at, url);
        }

        protected void onPostExecute(String content) {

            Log.d(TAG, "onPostExecute: " + content);

            try{

                JSONObject jsonObject = new JSONObject(content);

                if(jsonObject.getString("resultcode").equals("00")){

                    JSONObject subjsonObject = new JSONObject(jsonObject.getString("response"));
                    String id = subjsonObject.getString("id");
                    String nickname = subjsonObject.getString("nickname");
                    String email = subjsonObject.getString("email");
                    String name = subjsonObject.getString("name");
                    String age = subjsonObject.getString("age");
                    String gender = subjsonObject.getString("gender");
                    String birthday = subjsonObject.getString("birthday");
                    String profile_image = subjsonObject.getString("profile_image");

                    Log.d(TAG, "id: " + id);
                    Log.d(TAG, "nickname: " + nickname);
                    Log.d(TAG, "email: " + email);
                    Log.d(TAG, "name: " + name);
                    Log.d(TAG, "age: " + age);
                    Log.d(TAG, "gender: " + gender);
                    Log.d(TAG, "birthday: " + birthday);
                    Log.d(TAG, "profile_image: " + profile_image);
                }

            }catch (Exception ex){
                Log.d(TAG, "Exception: " + ex.getMessage().toString());
            }
        }
    }

    private class RefreshTokenTask extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... params) {
            return oAuthLogin.refreshAccessToken(mContext);
        }

        protected void onPostExecute(String res) {
            updateView();
        }
    }

}
