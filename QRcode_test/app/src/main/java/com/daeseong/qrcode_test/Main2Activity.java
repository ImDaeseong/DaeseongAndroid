package com.daeseong.qrcode_test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;
import com.google.zxing.Result;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class Main2Activity extends AppCompatActivity {

    private static final String TAG = Main2Activity.class.getSimpleName();

    private ZXingScannerView zXingScannerView;

    private FrameLayout fL1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        fL1 = findViewById(R.id.fL1);

        initScanner();

        startScanner();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        stopScanner();
    }

    private void initScanner(){

        zXingScannerView = new ZXingScannerView(this);
        zXingScannerView.setResultHandler( new ZXingScannerViewResultHandler());

        fL1.addView(zXingScannerView);
    }

    private void startScanner(){

        zXingScannerView.startCamera();
    }

    private void stopScanner(){

        zXingScannerView.stopCamera();
    }

    private class ZXingScannerViewResultHandler implements ZXingScannerView.ResultHandler{

        @Override
        public void handleResult(Result result) {

            String sText = result.getText();
            String sBarcodeFormatText = result.getBarcodeFormat().toString();
            Log.e(TAG, "handleResult - sText : " + sText + " sBarcodeFormatText: " + sBarcodeFormatText);

            stopScanner();
        }
    }

}
