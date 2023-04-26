package com.daeseong.kakao_v2_test;

import android.app.Application;
import com.kakao.sdk.common.KakaoSdk;

public class GlobalApplication extends Application {

    private static GlobalApplication instance;

    public static GlobalApplication getGlobalApplicationContext() {

        if (instance == null) {
            throw new IllegalStateException("This Application does not inherit com.kakao.GlobalApplication");
        }
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        // Kakao Sdk 초기화
        KakaoSdk.init(this, getResources().getString(R.string.kakao_id));
    }

    @Override
    public void onTerminate() {
        super.onTerminate();

        instance = null;
    }
}
