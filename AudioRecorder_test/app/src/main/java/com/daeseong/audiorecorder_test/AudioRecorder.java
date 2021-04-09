package com.daeseong.audiorecorder_test;

import android.content.Context;
import android.media.MediaRecorder;
import android.os.Environment;
import android.util.Log;
import java.io.File;
import java.io.IOException;

public class AudioRecorder {

    private static final String TAG = AudioRecorder.class.getName();

    private MediaRecorder recorder = null;
    private File saveFolder = null;
    private Context context = null;
    private boolean isRecording = false;
    private long recordingStart = 0;

    private static AudioRecorder instance = null;
    public static AudioRecorder getInstance(Context context) {
        if(instance == null) {
            instance = new AudioRecorder(context);
        }
        return instance;
    }

    public AudioRecorder(Context context) {
        this.recorder = new MediaRecorder();
        this.context = context;
    }

    public boolean startRecord(String sfilename) {

        if(!isRecording) {

            String sFile = sfilename + ".aac";
            this.saveFolder = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC), sFile);

            if (this.recorder == null) {
                this.recorder = new MediaRecorder();
            }

            try {
                recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                recorder.setOutputFormat(MediaRecorder.OutputFormat.AAC_ADTS);
                recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
                recorder.setOutputFile(this.saveFolder.getAbsolutePath());
                recorder.prepare();
                recorder.start();

                recordingStart = System.currentTimeMillis();
                this.isRecording = true;
                return true;
            } catch (IOException iex) {
                iex.printStackTrace();
                return false;
            }
        } else {
            return true;
        }
    }

    public int stopRecord() {
        int nDuration = 0;

        if(recorder != null) {

            nDuration = (int) ((System.currentTimeMillis() - recordingStart) / 1000);
            isRecording = false;

            if(isRecording) {
                recorder.stop();
            }

            recorder.reset();
            recorder.release();
            recorder = null;
        }
        return nDuration;
    }

    public void release() {
        try {
            recorder.stop();
            recorder.release();
            recorder = null;
        } catch(Exception ex){
            ex.getMessage().toString();
        }
    }

    public boolean isRecording() {
        return isRecording;
    }

    public File getSaveFolder() {
        return saveFolder;
    }
}