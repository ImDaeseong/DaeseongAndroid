package com.im.daeseong.barcode_test;

import androidx.appcompat.app.AppCompatActivity;//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class Qr2Activity extends AppCompatActivity {

    private ZXingScannerView zXingScannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr2);
    }

    @Override
    protected void onResume() {
        super.onResume();

        startScan();
    }

    @Override
    protected void onPause() {
        super.onPause();

        zXingScannerView.stopCamera();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        zXingScannerView.stopCamera();
    }

    public void startScan() {

        zXingScannerView = new ZXingScannerView(this);
        zXingScannerView.setResultHandler( new ZXingScannerViewResultHandler());

        setContentView(zXingScannerView);
        zXingScannerView.startCamera();
    }

    class ZXingScannerViewResultHandler implements ZXingScannerView.ResultHandler {

        @Override
        public void handleResult(Result result) {

            String sgetText = result.getText();
            String sBarcodeFormat = result.getBarcodeFormat().toString();
            Toast.makeText(Qr2Activity.this, sgetText, Toast.LENGTH_LONG).show();
            Toast.makeText(Qr2Activity.this, sBarcodeFormat, Toast.LENGTH_LONG).show();

            setContentView(R.layout.activity_qr2);
            zXingScannerView.stopCamera();
        }
    }

}
