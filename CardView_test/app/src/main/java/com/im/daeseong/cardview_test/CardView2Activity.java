package com.im.daeseong.cardview_test;

import androidx.appcompat.app.AppCompatActivity;//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import androidx.cardview.widget.CardView;//import android.support.v7.widget.CardView;
import android.view.View;

public class CardView2Activity extends AppCompatActivity {

    private static final String TAG = CardView2Activity.class.getSimpleName();

    private CardView cardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_view2);

        cardView =(CardView)findViewById(R.id.cardview2);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
