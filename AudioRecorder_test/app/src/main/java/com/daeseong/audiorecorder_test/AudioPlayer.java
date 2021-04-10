package com.daeseong.audiorecorder_test;

import android.media.MediaPlayer;
import android.util.Log;

public class AudioPlayer {

    private static final String TAG = AudioPlayer.class.getName();

    private static MediaPlayer mediaPlayer = null;
    private OnMediaPlayerListener onMediaPlayerListener;

    private static AudioPlayer instance;
    public static AudioPlayer getInstance(){

        if( instance == null){
            instance = new AudioPlayer();
            mediaPlayer = new MediaPlayer();
        }
        return instance;
    }

    public void release() {

        try {

            removeListener();

            if (mediaPlayer != null) {
                mediaPlayer.release();
                mediaPlayer = null;
            }
        }catch (Exception ex){
            Log.d(TAG, ex.getMessage().toString());
        }
    }

    public void play(String sPath, final OnMediaPlayerListener onMediaPlayerListener){

        try {

            if (mediaPlayer != null) {

                mediaPlayer.reset();

                this.onMediaPlayerListener = onMediaPlayerListener;
                mediaPlayer.setDataSource(sPath);
                mediaPlayer.prepare();

                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {

                        if(onMediaPlayerListener != null) {
                            onMediaPlayerListener.onCompletion(true);
                        }
                    }
                });

                mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {

                        if(onMediaPlayerListener != null){
                            onMediaPlayerListener.onPrepared(mp.getDuration());
                        }
                        //mp.start();
                    }
                });

                mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                    @Override
                    public boolean onError(MediaPlayer mp, int what, int extra) {

                        if(onMediaPlayerListener != null) {
                            onMediaPlayerListener.onCompletion(false);
                        }
                        return false;
                    }
                });
            }

        }catch (Exception ex){

            if(onMediaPlayerListener != null) {
                onMediaPlayerListener.onCompletion(false);
            }

            Log.d(TAG, ex.getMessage().toString());
        }
    }

    public void start() {

        if(mediaPlayer != null){
            mediaPlayer.start();
        }
    }

    public void stop() {

        if(mediaPlayer != null && mediaPlayer.isPlaying()){
            mediaPlayer.stop();
            mediaPlayer.reset();
        }
    }

    public void pause(){

        if(mediaPlayer != null && mediaPlayer.isPlaying()){
            mediaPlayer.pause();
        }
    }

    public int getCurrentPosition(){

        if(mediaPlayer != null) return mediaPlayer.getCurrentPosition();
        else return 0;
    }

    public int getDuration(){

        if(mediaPlayer != null) return mediaPlayer.getDuration();
        else return 0;
    }

    public boolean isPlaying() {

        if (mediaPlayer != null) return mediaPlayer.isPlaying();
        else return false;
    }

    public void seekTo(int position) {

        if (mediaPlayer != null) {
            mediaPlayer.seekTo(position);
        }
    }

    private void removeListener(){

        try{
            onMediaPlayerListener = null;
        }catch (Exception ex){
            Log.d(TAG, ex.getMessage().toString());
        }
    }

    public interface OnMediaPlayerListener {
        void onCompletion(boolean bComplete);
        void onPrepared(int mDuration);
    }
}
