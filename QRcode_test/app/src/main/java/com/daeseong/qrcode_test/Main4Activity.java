package com.daeseong.qrcode_test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;

import com.google.zxing.Result;

import me.dm7.barcodescanner.core.IViewFinder;
import me.dm7.barcodescanner.core.ViewFinderView;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class Main4Activity extends AppCompatActivity {

    private static final String TAG = Main4Activity.class.getSimpleName();

    private ZXingScannerView zXingScannerView;
    private ZXingScannerViewResultHandler zXingScannerViewResultHandler;

    private ConstraintLayout cL1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

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

        zXingScannerViewResultHandler = new ZXingScannerViewResultHandler();

        zXingScannerView = new ZXingScannerView(this){
            @Override
            protected IViewFinder createViewFinderView(Context context) {
                return new CustomViewFinderView(context);
                //return super.createViewFinderView(context);
            }
        };
        zXingScannerView.setResultHandler(zXingScannerViewResultHandler);

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
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    zXingScannerView.resumeCameraPreview(zXingScannerViewResultHandler);
                }
            }, 2000);
        }
    }

    private static class CustomViewFinderView extends ViewFinderView {
        public static final String TRADE_MARK_TEXT = "QR 코드 인식";
        public static final int TRADE_MARK_TEXT_SIZE_SP = 30;
        public final Paint PAINT = new Paint();

        public CustomViewFinderView(Context context) {
            super(context);
            init();
        }

        public CustomViewFinderView(Context context, AttributeSet attrs) {
            super(context, attrs);
            init();
        }

        private void init() {
            PAINT.setColor(Color.WHITE);
            PAINT.setAntiAlias(true);
            float textPixelSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,  TRADE_MARK_TEXT_SIZE_SP, getResources().getDisplayMetrics());
            PAINT.setTextSize(textPixelSize);
        }

        @Override
        public void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            drawTradeMark(canvas);
        }

        private void drawTradeMark(Canvas canvas) {
            Rect framingRect = getFramingRect();
            float tradeMarkTop;
            float tradeMarkLeft;

            if (framingRect != null) {
                tradeMarkTop = framingRect.bottom + PAINT.getTextSize() + 10;
                tradeMarkLeft = framingRect.left;
            } else {
                tradeMarkTop = 10;
                tradeMarkLeft = canvas.getHeight() - PAINT.getTextSize() - 10;
            }
            canvas.drawText(TRADE_MARK_TEXT, tradeMarkLeft, tradeMarkTop, PAINT);
        }
    }

}
