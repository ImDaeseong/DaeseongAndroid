package com.im.daeseong.fingerprintmanager_test;

import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity  {

    private static final String TAG = MainActivity.class.getSimpleName();

    private TextView tv1;

    private FingerprintManagerHandler fingerprintManagerHandler;

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            try {
                fingerprintManagerHandler.FingerAuthClear();
            } catch (Exception e) {
                Log.e(TAG, "Exception:" + e.getMessage());
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv1 = (TextView)findViewById(R.id.tv1);

        /*
        //지문 인식전 권한 체크
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        */

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            fingerprintManagerHandler = new FingerprintManagerHandler(this);

            if(fingerprintManagerHandler.isHardwareDetected()){

                if(fingerprintManagerHandler.hasEnrolledFingerprints()){

                    fingerprintManagerHandler.FingerAuthinit(new FingerprintManager.AuthenticationCallback() {

                        @Override
                        public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
                            super.onAuthenticationSucceeded(result);

                            tv1.setText("Succeeded");

                            //Log.e(TAG, "Succeeded");
                        }

                        @Override
                        public void onAuthenticationError(int errorCode, CharSequence errString) {
                            super.onAuthenticationError(errorCode, errString);

                            tv1.setText("failed");

                            //Log.e(TAG, "errorCode:" + String.valueOf(errorCode));
                            //Log.e(TAG, "errString:" + errString);
                        }

                        @Override
                        public void onAuthenticationHelp(int helpCode, CharSequence helpString) {
                            super.onAuthenticationHelp(helpCode, helpString);

                            //Log.e(TAG, "helpCode:" + String.valueOf(helpCode));
                            //Log.e(TAG, "helpString:" + helpString);
                        }

                        @Override
                        public void onAuthenticationFailed() {
                            super.onAuthenticationFailed();

                            tv1.setText("failed");
                        }
                    });

                } else {
                    //Log.e(TAG, "등록된 지문이 없음");

                    tv1.setText("등록된 지문이 없음");
                }
            } else {
                //Log.e(TAG, "하드웨어가 지원되지 않음");

                tv1.setText("하드웨어가 지원되지 않음");
            }
        } else {
            //Log.e(TAG, "지원되지 않는 안드로이드 하위 버전");

            tv1.setText("지원되지 않는 안드로이드 하위 버전");
        }

    }
}
