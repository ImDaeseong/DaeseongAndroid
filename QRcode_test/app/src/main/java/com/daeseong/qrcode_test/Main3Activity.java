package com.daeseong.qrcode_test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.os.Bundle;
import android.util.Log;
import com.google.zxing.Result;
//import me.dm7.barcodescanner.zxing.ZXingScannerView;
import com.google.zxing.Result;
import com.google.zxing.ResultPoint;
import com.journeyapps.barcodescanner.BarcodeCallback;
import com.journeyapps.barcodescanner.BarcodeResult;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;
import java.util.List;

public class Main3Activity extends AppCompatActivity {

    private static final String TAG = Main3Activity.class.getSimpleName();

    //private ZXingScannerView zXingScannerView;
    //private ConstraintLayout cL1;

    private DecoratedBarcodeView barcodeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        barcodeView = findViewById(R.id.dv);
        barcodeView.decodeContinuous(callback);

        /*
        cL1 = findViewById(R.id.cL1);
        initScanner();
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
            if (result.getText() != null) {
                Log.e(TAG, "Scanned result: " + result.getText());
                barcodeView.pause(); // 스캔 후 멈춤
            }
        }

        @Override
        public void possibleResultPoints(List<ResultPoint> resultPoints) {

        }
    };


    /*
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
    */
}
