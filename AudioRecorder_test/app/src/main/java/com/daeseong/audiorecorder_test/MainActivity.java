package com.daeseong.audiorecorder_test;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import java.io.File;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    public ActivityResultLauncher<String[]> requestPermissions;

    private static final String[] PERMISSIONS = new String[]{Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private static final String[] PERMISSIONS33 = new String[]{Manifest.permission.RECORD_AUDIO, Manifest.permission.READ_MEDIA_AUDIO};

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

        initPermissionsLauncher();

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

        checkPermissions();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(audioRecorder.isRecording()){
            audioRecorder.stopRecord();
        }

        audioPlayer.release();
    }

    private void initPermissionsLauncher() {

        requestPermissions = registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), result -> {

            boolean bRecord = false;
            boolean bAudio = false;

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {

                bRecord = Boolean.TRUE.equals(result.get(Manifest.permission.RECORD_AUDIO));
                bAudio = Boolean.TRUE.equals(result.get(Manifest.permission.READ_MEDIA_AUDIO));

            } else {

                bRecord = Boolean.TRUE.equals(result.get(Manifest.permission.RECORD_AUDIO));
                bAudio = Boolean.TRUE.equals(result.get(Manifest.permission.WRITE_EXTERNAL_STORAGE));
            }

            if (bRecord && bAudio) {
                Log.e(TAG, "PERMISSIONS 권한 소유");
            } else {
                Log.e(TAG, "PERMISSIONS 권한 미소유");
            }
        });
    }

    private void checkPermissions() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            boolean bPermissResult = false;

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {

                for (String permission : PERMISSIONS33) {
                    bPermissResult = checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
                    if(!bPermissResult) {
                        break;
                    }
                }

                if(!bPermissResult) {
                    requestPermissions.launch(PERMISSIONS33);
                } else {
                    Log.e(TAG, "PERMISSIONS33 권한 소요");
                }

            } else {

                for (String permission : PERMISSIONS) {
                    bPermissResult = checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
                    if(!bPermissResult) {
                        break;
                    }
                }

                if(!bPermissResult) {
                    requestPermissions.launch(PERMISSIONS);
                } else {
                    Log.e(TAG, "PERMISSIONS 권한 소요");
                }
            }
        }

    }
}