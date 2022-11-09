package com.daeseong.changedeprecated;

import androidx.appcompat.app.AppCompatActivity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main1Activity_Sub extends AppCompatActivity {

    private static final String TAG = Main1Activity_Sub.class.getSimpleName();

    private Button button1, button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1_sub);

        button1 = findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Main1Activity_Sub.this, Main1Activity.class);
                intent.putExtra("string", "문자열");
                intent.putExtra("int", 1);
                intent.putExtra("boolean", true);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                reStart();
            }
        });
    }

    //앱 완전히 종료후 재시작
    private void reStart(){

        PackageManager packageManager = getPackageManager();
        Intent intent = packageManager.getLaunchIntentForPackage(getPackageName());
        ComponentName componentName = intent.getComponent();
        Intent mainIntent = Intent.makeRestartActivityTask(componentName);
        startActivity(mainIntent);
        System.exit(0);
    }
}