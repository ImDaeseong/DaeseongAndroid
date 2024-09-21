package com.daeseong.qrcode_test;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import com.google.zxing.Result;
//import me.dm7.barcodescanner.zxing.ZXingScannerView;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;
import com.journeyapps.barcodescanner.BarcodeCallback;
import com.journeyapps.barcodescanner.BarcodeResult;
import java.util.List;

public class Main5Activity extends AppCompatActivity { //implements ZXingScannerView.ResultHandler {

    private static final String TAG = Main5Activity.class.getSimpleName();

    //private ZXingScannerView zXingScannerView;

    private DecoratedBarcodeView barcodeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        barcodeView = findViewById(R.id.dv);
        barcodeView.decodeContinuous(callback);

        /*
        zXingScannerView = findViewById(R.id.zxsv);
        startScanner();
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

        //stopScanner();
    }

    private BarcodeCallback callback = new BarcodeCallback() {
        @Override
        public void barcodeResult(BarcodeResult result) {
            if(result.getText() != null) {
                String sText = result.getText();
                String sBarcodeFormatText = result.getBarcodeFormat().toString();
                Log.e(TAG, "handleResult - sText : " + sText + " sBarcodeFormatText: " + sBarcodeFormatText);

                // 2초 후 미리보기 재개
                barcodeView.pause();
                barcodeView.postDelayed(() -> barcodeView.resume(), 2000);
            }
        }

        @Override
        public void possibleResultPoints(List resultPoints) {
            // 필요시 구현
        }
    };


    /*
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
    */
}