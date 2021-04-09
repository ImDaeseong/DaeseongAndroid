package com.daeseong.audiorecorder_test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import java.io.File;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private String[] permissions = {Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE};

    private Button button1, button2, button3, button4;

    private AudioRecorder audioRecorder = null;
    private AudioPlayer audioPlayer = null;
    private int length = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        audioRecorder = new AudioRecorder(this);
        audioPlayer = AudioPlayer.getInstance();

        checkPermissions();

        button1 = (Button)findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(audioRecorder.isRecording()) {

                    length = audioRecorder.stopRecord();
                    audioRecorder.release();

                    Log.e(TAG, "녹음이 완료되었습니다. length:" + length);

                } else {

                    audioRecorder.startRecord("katakata");

                    Log.e(TAG, "녹음을 시작합니다.");
                }
            }
        });

        button2 = (Button)findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(length > 0) {

                    String sFilename = audioRecorder.getSaveFolder().getAbsoluteFile().toString();
                    Log.e(TAG, "sFilename:" + sFilename + " length:" + length);

                    if(audioRecorder.getSaveFolder() != null) {
                        File file = audioRecorder.getSaveFolder().getAbsoluteFile();
                        if(file.delete()) {
                            Log.e(TAG, "파일이 삭제 되었습니다.");
                        }
                    }
                }
            }
        });

        button3 = (Button)findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String sRedordPath = audioRecorder.getSaveFolder().getAbsoluteFile().getPath();
                if(sRedordPath.isEmpty()){

                    Log.e(TAG, "녹음된 파일을 찾을 수 없습니다.");
                    return;
                }

                audioPlayer.play(sRedordPath, new AudioPlayer.OnMediaPlayerListener() {
                    @Override
                    public void onCompletion(boolean bComplete) {

                        Log.e(TAG, "녹음된 내용을 전부 들었습니다.");
                    }

                    @Override
                    public void onPrepared(int mDuration) {

                        Log.e(TAG, "녹음된 파일이 준비가 되었습니다.");
                    }
                });

            }
        });

        button4 = (Button)findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(audioPlayer.isPlaying()){

                    Log.e(TAG, "녹음된 내용 플레이 중지");

                    audioPlayer.pause();

                } else {

                    Log.e(TAG, "녹음된 내용 플레이 시작");

                    audioPlayer.start();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(audioRecorder.isRecording()){
            audioRecorder.stopRecord();
        }

        audioPlayer.release();
    }

    private static boolean hasPermissions(Context context, String... permissions) {

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {

            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    private void checkPermissions() {

        if (!hasPermissions(this, permissions)) {
            if ( ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.RECORD_AUDIO) ||
                 ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) ) {
                 ActivityCompat.requestPermissions(this, permissions, 1);
            } else {
                 ActivityCompat.requestPermissions(this, permissions, 1);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == 1) {

            for (int i = 0; i < permissions.length; i++) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    Log.e(TAG, "권한이 승인됨 상태:" + permissions[i]);
                } else {
                    Log.e(TAG, "권한이 승인되지 않음 상태:" + permissions[i]);
                }
            }
        }
    }
}