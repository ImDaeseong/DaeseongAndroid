package com.daeseong.kakao_test;

import android.app.Application;
import com.kakao.auth.KakaoSDK;

public class GlobalApplication extends Application {

    private static GlobalApplication instance;

    public static synchronized GlobalApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        // Kakao Sdk 초기화
        KakaoSDK.init(new KakaoSDKAdapter());
    }

    @Override
    public void onTerminate() {
        super.onTerminate();

        instance = null;
    }
}
