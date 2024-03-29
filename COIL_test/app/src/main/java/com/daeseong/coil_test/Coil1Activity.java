package com.daeseong.coil_test;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import coil.Coil;
import coil.request.ImageRequest;

public class Coil1Activity extends AppCompatActivity {

    private String sPngUrl = "https://cdn.pixabay.com/photo/2015/07/14/18/14/school-845196_960_720.png";

    private ImageView iv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coil1);

        iv1 = findViewById(R.id.iv1);

        loadImage();
    }


    private void loadImage() {

        ImageRequest request = new ImageRequest.Builder(this)
                .data(sPngUrl)
                .target(iv1)
                .build();

        Coil.imageLoader(this).enqueue(request);
    }
}