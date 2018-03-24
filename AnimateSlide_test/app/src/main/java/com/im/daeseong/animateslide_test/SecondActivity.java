package com.im.daeseong.animateslide_test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        setTitle("second");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        overridePendingTransition(R.anim.slide_in_top, R.anim.slide_out_bottom);
    }

    private void onCloseActivity(){

        finish();
        overridePendingTransition(R.anim.slide_in_top, R.anim.slide_out_bottom);
    }

}
