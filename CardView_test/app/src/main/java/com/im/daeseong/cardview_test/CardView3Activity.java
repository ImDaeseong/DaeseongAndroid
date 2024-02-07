package com.im.daeseong.cardview_test;

import androidx.appcompat.app.AppCompatActivity;//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import androidx.cardview.widget.CardView;//import android.support.v7.widget.CardView;
import android.view.View;

public class CardView3Activity extends AppCompatActivity {

    private static final String TAG = CardView3Activity.class.getSimpleName();

    private CardView cardView1, cardView2, cardView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_view3);

        cardView1 = (CardView)findViewById(R.id.cardview1);
        cardView2 = (CardView)findViewById(R.id.cardview2);
        cardView3 = (CardView)findViewById(R.id.cardview3);

        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
