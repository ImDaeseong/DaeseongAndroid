package com.daeseong.receivetest_app;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv1 = (TextView) findViewById(R.id.tv1);

        try {

            //다른 앱에서 전달받은 파라미터
            Intent intent = getIntent();
            if (intent != null) {
                String sParam = intent.getStringExtra("param");
                tv1.setText(sParam);
            }

        } catch (Exception ex) {
            Log.e(TAG, ex.getMessage().toString());
        }
    }
}