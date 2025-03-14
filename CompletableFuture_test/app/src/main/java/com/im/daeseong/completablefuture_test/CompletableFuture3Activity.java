package com.im.daeseong.completablefuture_test;

import android.os.Bundle;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CompletableFuture3Activity extends AppCompatActivity {

    private final String sUrl = "https://api.bithumb.com/public/ticker/BTC";

    private TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_completable_future3);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tv1 = findViewById(R.id.tv1);

        loadText();
    }


    private void loadText() {

        CompletableFuture.supplyAsync(() -> {
            try {
                return getData();
            } catch (IOException ex) {
                return null;
            }
        }).thenAccept(response -> {
            if (response != null) {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tv1.setText(response);
                    }
                });

            } else {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tv1.setText("");
                    }
                });
            }
        });
    }

    private String getData() throws IOException {

        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(sUrl)
                .build();

        Response response = okHttpClient.newCall(request).execute();
        if (response.isSuccessful()) {
            assert response.body() != null;
            return response.body().string();
        } else {
            return null;
        }
    }

}