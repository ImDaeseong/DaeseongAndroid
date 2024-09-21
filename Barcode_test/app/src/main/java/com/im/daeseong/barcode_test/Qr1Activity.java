package com.im.daeseong.barcode_test;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;//import android.support.annotation.NonNull;
import androidx.core.app.ActivityCompat;//import android.support.v4.app.ActivityCompat;
import androidx.appcompat.app.AppCompatActivity;//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import com.google.zxing.Result;
//import me.dm7.barcodescanner.zxing.ZXingScannerView;
import com.google.zxing.ResultPoint;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.BarcodeCallback;
import com.journeyapps.barcodescanner.BarcodeResult;
import com.journeyapps.barcodescanner.CompoundBarcodeView;
import java.util.List;

public class Qr1Activity extends AppCompatActivity { //implements ZXingScannerView.ResultHandler {

    //private ZXingScannerView zXingScannerView;

    private CompoundBarcodeView barcodeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr1);

        barcodeView = findViewById(R.id.cv);
        barcodeView.decodeContinuous(new BarcodeCallback() {
            @Override
            public void barcodeResult(BarcodeResult result) {
                if (result.getText() != null) {
                    Toast.makeText(Qr1Activity.this, "QR 코드: " + result.getText(), Toast.LENGTH_LONG).show();
                    barcodeView.pause();  // 한 번 스캔 후 멈춤
                }
            }

            @Override
            public void possibleResultPoints(List<ResultPoint> resultPoints) {

            }
        });


        /*
        zXingScannerView = new ZXingScannerView(this);
        setContentView(zXingScannerView);
        */
    }

    @Override
    protected void onResume() {
        super.onResume();
        barcodeView.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        barcodeView.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        barcodeView.pause();
    }


    /*
    @Override
    public void handleResult(Result result) {

        Toast.makeText(this,result.getText(),Toast.LENGTH_LONG).show();
        Toast.makeText(this,result.getBarcodeFormat().toString(),Toast.LENGTH_LONG).show();
        
        //한번 찍고 나서 멈추는걸 방지하기 위해
        zXingScannerView.resumeCameraPreview(this);
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();

        if ( checkPermission() ) {

            if( zXingScannerView == null) {
                zXingScannerView = new ZXingScannerView(this);
                setContentView(zXingScannerView);
            }
            zXingScannerView.setResultHandler(this);
            zXingScannerView.startCamera();
        }
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
    */
}
