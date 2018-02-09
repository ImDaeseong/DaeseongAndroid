package com.im.daeseong.bannerviewpager_test;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class BannerGsub_Activity extends AppCompatActivity {

    private static final String TAG = BannerGsub_Activity.class.getSimpleName();

    public interface Callback {
        void finish();
        void error(String msg);
    }

    public static void init(final Activity activity, final Callback callback){

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {

                //working
                Log.e(TAG, "working");

                callback.finish();
            }
        });
    }

    public static void show(final Activity activity){
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                init(activity, new Callback(){
                    @Override
                    public void finish() {
                        Intent intent = new Intent(activity, BannerGsub_Activity.class);
                        activity.startActivity(intent);

                        Log.e(TAG, "finish");
                    }

                    @Override
                    public void error(String msg) {
                        Log.e(TAG, msg);
                    }
                });
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner_gsub_);
    }
}
