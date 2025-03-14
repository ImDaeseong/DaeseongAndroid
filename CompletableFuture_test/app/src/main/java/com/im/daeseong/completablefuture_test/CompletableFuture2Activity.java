package com.im.daeseong.completablefuture_test;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.CompletableFuture;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class CompletableFuture2Activity extends AppCompatActivity {

    private final String sUrl = "https://cdn.pixabay.com/photo/2015/07/14/18/14/school-845196_960_720.png";

    private ImageView iv1;

    private OkHttpClient okHttpClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_completable_future2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //초기화
        okHttpClient = new OkHttpClient();

        iv1 = findViewById(R.id.iv1);

        loadImage();
    }

    // 비동기적으로 이미지를 로드
    private void loadImage() {

        CompletableFuture.supplyAsync(() -> {
            try {

                Request request = new Request.Builder().url(sUrl).build();
                Response response = okHttpClient.newCall(request).execute();
                if (response.isSuccessful() && response.body() != null) {
                    InputStream inputStream = response.body().byteStream();
                    return BitmapFactory.decodeStream(inputStream);
                }

            } catch (IOException ex) {
            }
            return null; // 실패 시 null 반환

        }).thenAcceptAsync(bitmap -> {

            if (bitmap != null) {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        iv1.setImageBitmap(bitmap);
                    }
                });

            } else {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // 이미지를 로드하지 못했을 경우 처리
                        // 기본 이미지 표시
                    }
                });
            }
        });

    }
}