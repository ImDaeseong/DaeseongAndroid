package com.im.daeseong.texttospeech_test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextToSpeechUtil textToSpeechUtil;

    private Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textToSpeechUtil = new TextToSpeechUtil(this);

        btn1 = (Button)findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if( textToSpeechUtil.isLanguageAvailable() ) {
                    textToSpeechUtil.Speak("가나다라마바사아자차타파하");
                } else {
                    Toast.makeText(MainActivity.this, "한글을 사용할수 없습니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
