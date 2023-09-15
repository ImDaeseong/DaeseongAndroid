package com.im.daeseong.texttospeech_test;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.util.Log;
import java.util.Locale;

public class TextToSpeechUtil {

    private static final String TAG = TextToSpeechUtil.class.getSimpleName();

    private static  TextToSpeech textToSpeech;

    public TextToSpeechUtil(Context context) {

        try {

            if (textToSpeech == null) {
                textToSpeech = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int i) {

                        if (i == TextToSpeech.SUCCESS) {
                            if (textToSpeech.isLanguageAvailable(Locale.KOREAN) == TextToSpeech.LANG_AVAILABLE) {
                                textToSpeech.setLanguage(Locale.KOREAN);
                            }
                        }
                        //textToSpeech.setLanguage(Locale.KOREAN);
                    }
                });
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /*
    //사용않함
    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1)
    public TextToSpeechUtil(Context context, final String sText) {

        try {

            if (textToSpeech == null) {
                textToSpeech = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int i) {

                        //textToSpeech.setLanguage(Locale.KOREAN);

                        if(i != TextToSpeech.ERROR){
                            textToSpeech.setLanguage(Locale.KOREAN);
                        }

                        if (i == TextToSpeech.SUCCESS) {
                            Speak(sText);
                        }


                        textToSpeech.setOnUtteranceProgressListener(new UtteranceProgressListener() {

                            @Override
                            public void onStart(String s) {

                            }

                            @Override
                            public void onDone(String s) {

                            }

                            @Override
                            public void onError(String s) {

                            }
                        });

                    }
                });
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    */

    public static void Speak(final String sText) {
        try {

            if (textToSpeech != null) {
                if (textToSpeech.isSpeaking()) {
                    textToSpeech.stop();
                }

                //textToSpeech.setSpeechRate(1.0f);//속도

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    textToSpeech.speak(sText, TextToSpeech.QUEUE_ADD, null, null);//UtteranceProgressListener 이벤트 받지 않기 위해 null
                } else {
                    textToSpeech.speak(sText, TextToSpeech.QUEUE_ADD, null);//UtteranceProgressListener 이벤트 받지 않기 위해 null
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void Stop() {
        try {

            if (textToSpeech != null) {
                textToSpeech.stop();
                textToSpeech.shutdown();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static boolean isLanguageAvailable() {

        boolean bAvailable = false;
        try {

            if (textToSpeech.isLanguageAvailable(Locale.KOREAN) == TextToSpeech.LANG_AVAILABLE) {
                bAvailable = true;
            }

        }catch (Exception e){
        }
        return bAvailable;
    }

}
