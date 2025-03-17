package com.im.daeseong.completablefuture_test;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class CompletableFuture4Activity extends AppCompatActivity {

    private final String sUrl1 = "https://cdn.pixabay.com/photo/2015/07/14/18/14/school-845196_960_720.png";
    private final String sUrl2 = "https://api.bithumb.com/public/ticker/BTC";

    private ImageView iv1;
    private TextView tv1;

    private Executor executor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_completable_future4);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        iv1 = findViewById(R.id.iv1);
        tv1 = findViewById(R.id.tv1);

        //고정된 크기의 스레드 풀 2개 생성
        executor = Executors.newFixedThreadPool(2);

        loadImage();
        loadText();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //고정된 크기의 스레드 풀 종료
        if (executor instanceof java.util.concurrent.ExecutorService) {
            ((java.util.concurrent.ExecutorService) executor).shutdown();
        }
    }

    private void loadImage() {

        CompletableFuture.supplyAsync(() -> {
            return HttpUtil.getDataBitmap(sUrl1);
        }, executor).thenAcceptAsync(bitmap -> {
            if (bitmap != null) {
                runOnUiThread(() -> iv1.setImageBitmap(bitmap));
            }
        });
    }

    private void loadText() {

        CompletableFuture.supplyAsync(() -> {
            return HttpUtil.getDataString(sUrl2);
        }, executor).thenAcceptAsync(strResult -> {
            if (strResult != null && !strResult.isEmpty()) {
                runOnUiThread(() -> tv1.setText(strResult));
            }
        });

    }
}