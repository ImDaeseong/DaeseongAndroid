package com.daeseong.qrcode_test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.os.Bundle;
import android.util.Log;
import com.google.zxing.Result;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class Main3Activity extends AppCompatActivity {

    private static final String TAG = Main3Activity.class.getSimpleName();

    private ZXingScannerView zXingScannerView;

    private ConstraintLayout cL1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        cL1 = findViewById(R.id.cL1);

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

        cL1.addView(zXingScannerView);
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

            //한번 찍고 나서 멈추는걸 방지하기 위해
            zXingScannerView.resumeCameraPreview(this);
        }
    }

}
