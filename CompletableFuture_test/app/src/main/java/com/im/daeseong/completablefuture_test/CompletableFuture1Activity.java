package com.im.daeseong.completablefuture_test;

import android.os.Bundle;
import android.widget.ImageView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.util.concurrent.CompletableFuture;
import coil.Coil;
import coil.request.ImageRequest;
import coil.transform.CircleCropTransformation;

public class CompletableFuture1Activity extends AppCompatActivity {

    private final String sUrl = "https://cdn.pixabay.com/photo/2015/07/14/18/14/school-845196_960_720.png";

    private ImageView iv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_completable_future1);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        iv1 = findViewById(R.id.iv1);

        loadImage();
    }

    // CompletableFuture를 사용하여 이미지 로딩을 비동기적으로 처리
    private void loadImage() {

        CompletableFuture.runAsync(() -> {
            ImageRequest request = new ImageRequest.Builder(this)
                    .data(sUrl)
                    .target(iv1)
                    .transformations(new CircleCropTransformation())
                    .build();

            // 이미지를 로드하고 결과가 끝나면 UI 스레드에서 처리
            Coil.imageLoader(this).enqueue(request);
        });
    }

}