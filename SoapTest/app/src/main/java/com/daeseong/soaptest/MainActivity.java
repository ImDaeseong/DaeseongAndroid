package com.daeseong.soaptest;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.daeseong.util.SoapXml;

public class MainActivity extends AppCompatActivity {

    private SoapXml soapXml;
    TextView textView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView1 = (TextView)findViewById(R.id.textView1);

        try {

            soapXml = new SoapXml(textView1);
            soapXml.execute("검색어");

        }catch (Exception e){

            soapXml.cancel(true);

            textView1.setText("Error");

            e.printStackTrace();
        }
    }
}