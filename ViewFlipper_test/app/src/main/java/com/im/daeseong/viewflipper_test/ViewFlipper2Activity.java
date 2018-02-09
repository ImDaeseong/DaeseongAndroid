package com.im.daeseong.viewflipper_test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class ViewFlipper2Activity extends AppCompatActivity {

    private static final String TAG = ViewFlipper2Activity.class.getSimpleName();

    private Button button1;
    private TextView textview1, textview2, textview3;
    private ViewFlipper viewFlipper1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_flipper2);

        setTitle(TAG);

        button1 = (Button)findViewById(R.id.button1);
        textview1 = (TextView)findViewById(R.id.textview1);
        textview2 = (TextView)findViewById(R.id.textview2);
        textview3 = (TextView)findViewById(R.id.textview3);
        viewFlipper1 = (ViewFlipper)findViewById(R.id.viewFlipper1);

        textview1.setText("textview1textview1textview1textview1textview1textview1textview1textview1textview1");
        textview2.setText("textview2textview2textview2textview2textview2textview2textview2textview2textview2");
        textview3.setText("textview3textview3textview3textview3textview3textview3textview3textview3textview3");

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewFlipper1.showNext();
            }
        });
    }
}
