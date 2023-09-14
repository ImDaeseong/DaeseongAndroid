package com.im.daeseong.screenbrightness_test;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import androidx.appcompat.app.AppCompatActivity;//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private WindowManager.LayoutParams layoutParams;
    private float brightness;

    private SeekBar seekBar1;
    private TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestPermission();

        seekBar1 = (SeekBar)findViewById(R.id.seekBar1);
        tv1 = (TextView)findViewById(R.id.tv1);

        layoutParams = getWindow().getAttributes();
        layoutParams.screenBrightness = 0 / 100.0f;
        seekBar1.setProgress(0);
        setPercentText();

        seekBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                layoutParams.screenBrightness = i / 100.0f;
                getWindow().setAttributes(layoutParams);
                setPercentText();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.System.canWrite(getApplicationContext())) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS, Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent, 1);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1) {
            Log.e(TAG, "resultCode:" + resultCode);
        }
    }

    private void setPercentText() {
        String sPercent = "밝기:" + String.valueOf(layoutParams.screenBrightness) + "%";
        tv1.setText(sPercent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        /*
        //현재 밝기 정보 저장
        brightness = layoutParams.screenBrightness;

        //밝기를 최대로 설정
        layoutParams.screenBrightness = 1f;
        getWindow().setAttributes(layoutParams);
        */
    }

    @Override
    protected void onPause() {
        super.onPause();
        /*
        // 밝기 원래대로 설정
        layoutParams.screenBrightness = brightness;
        getWindow().setAttributes(layoutParams);
        */
    }
}
