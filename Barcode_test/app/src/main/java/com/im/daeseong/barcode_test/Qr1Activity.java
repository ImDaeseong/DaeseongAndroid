package com.im.daeseong.barcode_test;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class Qr1Activity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    private ZXingScannerView zXingScannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr1);

        zXingScannerView = new ZXingScannerView(this);
        setContentView(zXingScannerView);

        checkPermission();
    }

    @Override
    public void handleResult(Result result) {

        //AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        //alertDialog.setMessage(result.getText());
        //alertDialog.show();
        //Log.e("Resultado",result.getText());
        //Log.e("ResultadoBar",result.getBarcodeFormat().toString());
        //onResume();

        Log.e("Resultado",result.getText());
        Log.e("ResultadoBar",result.getBarcodeFormat().toString());

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

    private boolean checkPermission() {

        //마시멜로 이상일 경우만 권한 체크
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            //권한이 없는 경우 요청
            if(ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED) {
                ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CAMERA}, 1);
                return false;
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == 1) {

            if(grantResults.length > 0) {

                boolean result = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                if(!result) {
                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if(shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)) {
                            requestPermissions(new String[] {Manifest.permission.CAMERA}, 1);
                        }
                    }
                }
            }
        }

    }
}
