package com.im.daeseong.barcode_test;

import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import java.util.ArrayList;
import java.util.List;
//import me.dm7.barcodescanner.zxing.ZXingScannerView;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import com.google.zxing.ResultPoint;
import com.journeyapps.barcodescanner.BarcodeCallback;
import com.journeyapps.barcodescanner.BarcodeResult;
import com.journeyapps.barcodescanner.CompoundBarcodeView;
import com.journeyapps.barcodescanner.DefaultDecoderFactory;
import java.util.Arrays;
import java.util.List;

public class Qr3Activity extends AppCompatActivity { //implements ZXingScannerView.ResultHandler{

    private FrameLayout framelayout1;
    private TextView textView1;

    private CompoundBarcodeView barcodeView;

    //private ZXingScannerView zXingScannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr3);

        framelayout1 = (FrameLayout) findViewById(R.id.framelayout1);
        textView1 = (TextView) findViewById(R.id.textView1);

        barcodeView = new CompoundBarcodeView(this);
        framelayout1.addView(barcodeView);

        //바코드 포맷 설정
        List<BarcodeFormat> formats = Arrays.asList(BarcodeFormat.QR_CODE, BarcodeFormat.CODE_128);
        barcodeView.setDecoderFactory(new DefaultDecoderFactory(formats));

        /*
        zXingScannerView = new ZXingScannerView(this);
        framelayout1.addView(zXingScannerView);
        */
    }

    @Override
    protected void onResume() {
        super.onResume();

        barcodeView.decodeContinuous(new BarcodeCallback() {
            @Override
            public void barcodeResult(BarcodeResult result) {
                handleResult(result.getResult());
            }

            @Override
            public void possibleResultPoints(List<ResultPoint> resultPoints) {

            }
        });
        // 카메라 시작
        barcodeView.resume();


        /*
        zXingScannerView.setResultHandler(this);

        List<BarcodeFormat> formats = new ArrayList<>();
        formats.add(BarcodeFormat.CODE_128);
        formats.add(BarcodeFormat.QR_CODE);
        zXingScannerView.setFormats(formats);

        zXingScannerView.startCamera();
        zXingScannerView.setAutoFocus(true);
        */
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

    private void handleResult(Result result) {

        String sgetText = result.getText();
        String sBarcodeFormat = result.getBarcodeFormat().toString();

        String sMsg = String.format("%s %s", sgetText, sBarcodeFormat);
        textView1.setText(sMsg);

        // 카메라를 일정 시간 후 재개
        new Handler().postDelayed(() -> barcodeView.resume(), 1000);
    }


    /*
    @Override
    public void handleResult(Result result) {

        String sgetText = result.getText();
        String sBarcodeFormat = result.getBarcodeFormat().toString();

        String sMsg = String.format("%s %s", sgetText, sBarcodeFormat);
        textView1.setText(sMsg);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                zXingScannerView.resumeCameraPreview(Qr3Activity.this);
            }
        }, 1000);
    }
    */
}
