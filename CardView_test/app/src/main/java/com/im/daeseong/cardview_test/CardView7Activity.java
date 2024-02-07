package com.im.daeseong.cardview_test;

import androidx.appcompat.app.AppCompatActivity;//import android.support.v7.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import android.os.Build;
import android.os.Bundle;

public class CardView7Activity extends AppCompatActivity {

    private CardView cv2;
    private CardView cv3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_view7);

        init();
    }

    private void init() {

        cv2 = findViewById(R.id.cv2);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            cv2.setBackground(ContextCompat.getDrawable(this, R.drawable.topround));
        } else {
            cv2.setBackgroundResource(R.drawable.topround);
        }

        cv3 = findViewById(R.id.cv3);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            cv3.setBackground(ContextCompat.getDrawable(this, R.drawable.bottomround));
        } else {
            cv3.setBackgroundResource(R.drawable.bottomround);
        }
    }

}