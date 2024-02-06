package com.daeseong.materialcardview_test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import android.os.Bundle;
import com.google.android.material.card.MaterialCardView;

public class MaterialCardView3Activity extends AppCompatActivity {

    private MaterialCardView cv2;
    private MaterialCardView cv3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_card_view3);

        init();
    }

    private void init() {

        cv2 = findViewById(R.id.cv2);
        cv2.setBackground(ContextCompat.getDrawable(this, R.drawable.topround));

        cv3 = findViewById(R.id.cv3);
        cv3.setBackground(ContextCompat.getDrawable(this, R.drawable.bottomround));
    }
}