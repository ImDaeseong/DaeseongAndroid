package com.daeseong.facebook_test;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONObject;

public class Main1Activity extends AppCompatActivity {

    private static final String TAG = Main1Activity.class.getSimpleName();

    private CallbackManager callbackManager;
    private LoginButton loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

        callbackManager = CallbackManager.Factory.create();

        loginButton = findViewById(R.id.loginButton);
        loginButton.setReadPermissions("public_profile");
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {

            // 로그인 성공 시 호출 됩니다. Access Token 발급 성공
            @Override
            public void onSuccess(LoginResult loginResult) {

                GraphRequest graphRequest = GraphRequest.newMeRequest(loginResult.getAccessToken(),new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {

                        //Log.e(TAG, object.toString());
                        //Log.e(TAG, "id:" + object.optString("id"));
                        //Log.e(TAG, "email:" + object.optString("email"));
                        //Log.e(TAG, "name:" + object.optString("name"));

                        Intent intent = new Intent(Main1Activity.this, SuccessActivity.class);
                        intent.putExtra("id",object.optString("id"));
                        intent.putExtra("email",object.optString("email"));
                        intent.putExtra("name",object.optString("name"));
                        startActivity(intent);
                    }
                });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email,gender,birthday");
                graphRequest.setParameters(parameters);
                graphRequest.executeAsync();
            }

            //로그인 창을 닫을 경우
            @Override
            public void onCancel() {

                Log.e(TAG, "onCancel");
            }

            //로그인 실패 시
            @Override
            public void onError(FacebookException error) {

                Log.e(TAG, error.getMessage().toString());
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(callbackManager.onActivityResult(requestCode, resultCode, data)){
            return;
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void SuccessCallActivity() {
        Intent intent = new Intent(this, SuccessActivity.class);
        startActivity(intent);
        finish();
    }
}
