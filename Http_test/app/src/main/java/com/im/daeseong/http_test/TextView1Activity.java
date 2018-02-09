package com.im.daeseong.http_test;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.im.daeseong.http_test.HttpUtil.DownloadText;

public class TextView1Activity extends AppCompatActivity {

    private DownloadText downloadText;
    TextView textView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_view1);

        textView1 = (TextView)findViewById(R.id.textView1);

        try {
            if (IsConnection()) {

                String url1 = "https://";
                downloadText = new DownloadText(textView1);
                downloadText.execute(url1);
            }else {
                textView1.setText("not connect");
            }
        }catch (Exception e){

            downloadText.cancel(true);

            textView1.setText("Error");

            e.printStackTrace();
        }
    }

    public boolean IsConnection(){
        ConnectivityManager connectivityManager = (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if(networkInfo == null){
            return false;
        }

        if(!networkInfo.isConnected()){
            return  false;
        }

        if(!networkInfo.isAvailable()){
            return false;
        }
        return true;
    }

}
