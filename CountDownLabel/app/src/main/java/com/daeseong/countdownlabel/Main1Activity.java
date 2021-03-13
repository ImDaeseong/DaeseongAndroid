package com.daeseong.countdownlabel;

import androidx.appcompat.app.AppCompatActivity;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main1Activity extends AppCompatActivity {

    private static final String TAG = Main1Activity.class.getSimpleName();

    private TextView tv1;
    private Button button1, button2;

    private int nCount = 10000;
    private CountDownTimer countDownTimer;
    private AnimatorSet animatorSet = new AnimatorSet();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

        tv1 = findViewById(R.id.tv1);

        button1 = findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                stop();
                start();
            }
        });

        button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                stop();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stop();
    }

    private void start(){

        countDownTimer = new CountDownTimer(nCount, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                try {
                    tv1.setText("현재 페이지 " + (millisUntilFinished / 1000) + "초");
                    viewblink(tv1);
                }catch (Exception ex){
                    ex.getMessage().toString();
                }
            }

            @Override
            public void onFinish() {

                try {
                    tv1.setAlpha(1f);
                }catch (Exception ex){
                    ex.getMessage().toString();
                }
            }
        };
        countDownTimer.start();
    }

    private void stop(){

        try {

            if (animatorSet != null) {
                animatorSet.removeAllListeners();
                animatorSet.end();
                animatorSet.cancel();
            }

            if (countDownTimer != null)
                countDownTimer.cancel();

            tv1.setAlpha(1f);

        }catch (Exception ex){
            ex.getMessage().toString();
        }
    }

    private void viewblink(View view){

        ObjectAnimator fadeOut = ObjectAnimator.ofFloat(view, "alpha", 1f, 0f);
        ObjectAnimator fadeIn = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f);
        animatorSet.playTogether(fadeIn, fadeOut );
        animatorSet.setDuration(1000);
        animatorSet.start();
    }
}
