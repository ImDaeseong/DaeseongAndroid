package com.im.daeseong.barcode_test;

import androidx.appcompat.app.AppCompatActivity;//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import com.google.zxing.Result;
//import me.dm7.barcodescanner.zxing.ZXingScannerView;
import com.journeyapps.barcodescanner.BarcodeCallback;
import com.journeyapps.barcodescanner.BarcodeResult;
import com.journeyapps.barcodescanner.CompoundBarcodeView;
import java.util.List;
import com.google.zxing.ResultPoint;

public class Qr2Activity extends AppCompatActivity {

    //private ZXingScannerView zXingScannerView;

    private CompoundBarcodeView barcodeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr2);

        barcodeView = findViewById(R.id.cv);
        barcodeView.decodeContinuous(new BarcodeCallback() {
            @Override
            public void barcodeResult(BarcodeResult result) {
                if (result.getText() != null) {
                    String sgetText = result.getText();
                    String sBarcodeFormat = result.getBarcodeFormat().toString();
                    Toast.makeText(Qr2Activity.this, sgetText, Toast.LENGTH_LONG).show();
                    Toast.makeText(Qr2Activity.this, sBarcodeFormat, Toast.LENGTH_LONG).show();

                    // 한 번 스캔 후 멈춤
                    barcodeView.pause();
                }
            }

            @Override
            public void possibleResultPoints(List<ResultPoint> resultPoints) {

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        barcodeView.resume();

        //startScan();
    }

    @Override
    protected void onPause() {
        super.onPause();
        barcodeView.pause();

        //zXingScannerView.stopCamera();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        barcodeView.pause();

        //zXingScannerView.stopCamera();
    }


    /*
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
    */
}
