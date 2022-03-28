package com.daeseong.realm_test;

import android.app.Application;
import android.content.res.Configuration;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MyApplicaton extends Application {

    private static final String TAG = MyApplicaton.class.getSimpleName();

    private static MyApplicaton mInstance;
    public static synchronized MyApplicaton getInstance() {
        return mInstance;
    }

    private Realm realm;
    public  Realm getRealm() { return realm; }

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;
        initRealm();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    private void initRealm() {
        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration
                .Builder()
                .allowWritesOnUiThread(true)// UI Thread realm 접근 가능
                .deleteRealmIfMigrationNeeded() // db 변경사항 있을시 저장 데이터 모두 삭제
                .name("Lotto")
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);

        realm = Realm.getInstance(realmConfiguration);
    }
}
