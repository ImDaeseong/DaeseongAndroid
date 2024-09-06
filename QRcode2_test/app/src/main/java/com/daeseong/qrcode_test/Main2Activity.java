package com.daeseong.qrcode_test;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;
import com.journeyapps.barcodescanner.BarcodeCallback;
import com.journeyapps.barcodescanner.BarcodeResult;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;
import com.google.zxing.ResultPoint;

public class Main2Activity extends AppCompatActivity {

    private static final String TAG = Main2Activity.class.getSimpleName();

    private DecoratedBarcodeView barcodeView;
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

    private void initScanner() {
        barcodeView = new DecoratedBarcodeView(this);
        barcodeView.decodeContinuous(new BarcodeCallback() {
            @Override
            public void barcodeResult(BarcodeResult result) {
                String sText = result.getText();
                String sBarcodeFormatText = result.getBarcodeFormat().toString();
                Log.e(TAG, "sText : " + sText + " sBarcodeFormatText: " + sBarcodeFormatText);

                // QR 코드 스캔 후 멈추려면 이 부분을 활성화
                //stopScanner();
            }

            @Override
            public void possibleResultPoints(java.util.List<com.google.zxing.ResultPoint> resultPoints) {

                if (resultPoints != null && !resultPoints.isEmpty()) {
                    for (ResultPoint point : resultPoints) {
                        Log.d(TAG, "Possible Point: " + point.toString());
                    }
                }

            }
        });

        fL1.addView(barcodeView);
    }

    private void startScanner() {
        barcodeView.resume();
    }

    private void stopScanner() {
        barcodeView.pause();
    }
}
