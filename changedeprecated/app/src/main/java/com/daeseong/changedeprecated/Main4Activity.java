package com.daeseong.changedeprecated;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.daeseong.changedeprecated.Common.GetBitmapTask;
import com.daeseong.changedeprecated.Common.GetStringTask;

public class Main4Activity extends AppCompatActivity {

    private static final String TAG = Main4Activity.class.getSimpleName();

    private ImageView imageView1;
    private TextView textView1;
    private Button button1, button2;

    private String sPngUrl = "https://cdn.pixabay.com/photo/2015/07/14/18/14/school-845196_960_720.png";
    private String sStrUrl = "https://api.bithumb.com/public/ticker/BTC";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        imageView1 = findViewById(R.id.imageView1);
        textView1 = findViewById(R.id.textView1);

        button1 = findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                    Bitmap bitmap = null;
                    bitmap = new GetBitmapTask().execute(sPngUrl);
                    if (bitmap != null) {
                        imageView1.setImageBitmap(bitmap);
                    }

                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });

        button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                    String sResult = "";
                    sResult = new GetStringTask().execute(sStrUrl);
                    textView1.setText(sResult);

                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });
    }
}