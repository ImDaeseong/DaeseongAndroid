package com.daeseong.control_test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;

public class Main1Activity extends AppCompatActivity {

    private static final String TAG = Main1Activity.class.getSimpleName();

    private ConstraintLayout cL1;
    private ConstraintLayout cLview2;
    private boolean bview = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

        cLview2 = (ConstraintLayout)findViewById(R.id.cLview2);

        cL1 = (ConstraintLayout)findViewById(R.id.cL1);
        cL1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(bview){
                    cLview2.setVisibility(View.GONE);
                    bview = false;
                }else {
                    cLview2.setVisibility(View.VISIBLE);
                    bview = true;
                }

            }
        });
    }
}
