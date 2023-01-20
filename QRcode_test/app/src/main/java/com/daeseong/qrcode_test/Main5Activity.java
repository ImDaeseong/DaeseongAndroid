package com.daeseong.qrcode_test;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import com.google.zxing.Result;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class Main5Activity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    private static final String TAG = Main5Activity.class.getSimpleName();

    private ZXingScannerView zXingScannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        zXingScannerView = findViewById(R.id.zxsv);

        startScanner();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        stopScanner();
    }

    private void startScanner(){

        zXingScannerView.setResultHandler(this);
        zXingScannerView.startCamera();
    }

    private void stopScanner(){

        zXingScannerView.stopCamera();
    }

    @Override
    public void handleResult(Result rawResult) {

        String sText = rawResult.getText();
        String sBarcodeFormatText = rawResult.getBarcodeFormat().toString();
        Log.e(TAG, "handleResult - sText : " + sText + " sBarcodeFormatText: " + sBarcodeFormatText);

        //한번 찍고 나서 멈추는걸 방지하기 위해
        zXingScannerView.resumeCameraPreview(this);
        zXingScannerView.stopCamera();
    }
}