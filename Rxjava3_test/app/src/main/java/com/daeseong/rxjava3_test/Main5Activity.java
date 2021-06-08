package com.daeseong.rxjava3_test;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import com.daeseong.rxjava3_test.Common.DownloadUtil;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class Main5Activity extends AppCompatActivity {

    private static final String TAG = Main5Activity.class.getSimpleName();

    private TextView textView1;

    private String sUrl = "https://api.bithumb.com/public/ticker/BTC";
    private String sImgUrl = "https://.png";

    private DownloadUtil downloadUtil;

    public Main5Activity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        textView1 = (TextView)findViewById(R.id.textView1);

        downloadUtil = new DownloadUtil();
        downloadUtil.getData(sUrl)
                .subscribeOn(Schedulers.io())
                .onErrorComplete()
                .subscribe(sResult -> {
                    try{
                        textView1.setText(sResult);
                    }catch (Exception ex){
                        ex.printStackTrace();
                    }
                });

        downloadUtil.getBitmap(sUrl)
                .subscribeOn(Schedulers.io())
                .onErrorComplete()
                .subscribe(bBitmap -> {
                    try{
                        Log.e(TAG, String.valueOf(bBitmap));
                    }catch (Exception ex){
                        ex.printStackTrace();
                    }
                });
    }
}