package com.daeseong.sendemail_test;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();


    private String[] sAddresses = {"cs93059@gmail.com"};

    private EditText et1, et2;
    private Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1 = (EditText) findViewById(R.id.et1);
        et2 = (EditText) findViewById(R.id.et2);
        btn1 = (Button) findViewById(R.id.btn1);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String title = et1.getText().toString();
                String content = et2.getText().toString();

                // 이메일 보내기 Intent 생성
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:"));
                intent.putExtra(Intent.EXTRA_EMAIL, sAddresses);
                intent.putExtra(Intent.EXTRA_SUBJECT, title);
                intent.putExtra(Intent.EXTRA_TEXT, content);

                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION );
                intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION );

                // 이메일 보내기
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                } else {
                    Log.e(TAG, "메일 보낼수 있는 앱이 없습니다.");
                }

                et1.setText("");
                et2.setText("");
                et1.requestFocus();
            }
        });
    }

}