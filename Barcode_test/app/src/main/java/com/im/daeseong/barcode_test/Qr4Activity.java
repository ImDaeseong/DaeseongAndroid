package com.im.daeseong.barcode_test;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import java.util.EnumMap;
import java.util.Map;
//import me.dm7.barcodescanner.zxing.ZXingScannerView;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import java.util.EnumMap;
import java.util.Map;
import com.journeyapps.barcodescanner.CompoundBarcodeView;

public class Qr4Activity extends AppCompatActivity { //implements ZXingScannerView.ResultHandler{

    private static final String TAG = Qr4Activity.class.getSimpleName();

    private ImageView imageView1, imageView2, imageView3;
    private TextView textView1;

    private String sBarcode;

    private CompoundBarcodeView barcodeView;

    //private ZXingScannerView zXingScannerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr4);

        imageView1 = (ImageView)findViewById(R.id.imageView1);
        imageView2 = (ImageView)findViewById(R.id.imageView2);
        imageView3 = (ImageView)findViewById(R.id.imageView3);
        textView1 = (TextView) findViewById(R.id.textView1);

        sBarcode = "ddsfasf123213441312349780";//"adQAd123DBV6";//"aAbcede";//"1234567890";
        displayCode1(sBarcode);
        displayCode2(sBarcode);
        displayCode3(sBarcode);

        barcodeView = findViewById(R.id.cv);
    }

    @Override
    protected void onResume() {
        super.onResume();

        startCode();
    }

    @Override
    protected void onPause() {
        super.onPause();
        barcodeView.pause();

        /*
        try {
            zXingScannerView.stopCamera();
        } catch (Exception ex) {
            ex.getMessage().toString();
        }
        */
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        barcodeView.pause();

        /*
        try {
            zXingScannerView.stopCamera();
        } catch (Exception ex) {
            ex.getMessage().toString();
        }
        */
    }

    private void displayCode1(String sCode) {
        MultiFormatWriter writer = new MultiFormatWriter();
        try {
            BitMatrix bitMatrix = writer.encode(sCode, BarcodeFormat.CODE_128, 300, 100);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            imageView1.setImageBitmap(bitmap);
        } catch (WriterException ex) {
            Log.e(TAG,  "displayCode1:" + ex.getMessage().toString());
        }
    }

    private void displayCode2(String sCode) {
        try {
            Bitmap bitmap = encodeBitmap1(sCode, BarcodeFormat.CODE_128, 300, 100);
            imageView2.setImageBitmap(bitmap);
        } catch (WriterException ex) {
            Log.e(TAG,  "displayCode2:" + ex.getMessage().toString());
        }
    }

    private void displayCode3(String sCode) {
        try {
            Bitmap bitmap = encodeBitmap2(sCode);
            imageView3.setImageBitmap(bitmap);
        } catch (Exception ex) {
            Log.e(TAG,  "displayCode3:" + ex.getMessage().toString());
        }
    }

    private String guessAppropriateEncoding(CharSequence contents) {
        for (int i = 0; i < contents.length(); i++) {
            if (contents.charAt(i) > 0xFF) {
                return "UTF-8";
            }
        }
        return null;
    }

    private Bitmap encodeBitmap1(String sCode, BarcodeFormat format, int nWidth, int nHeight) throws WriterException {
        final int WHITE = 0xFFFFFFFF;
        final int BLACK = 0xFF000000;

        Map<EncodeHintType, Object> hints = null;
        String encoding = guessAppropriateEncoding(sCode);
        if (encoding != null) {
            hints = new EnumMap<>(EncodeHintType.class);
            hints.put(EncodeHintType.CHARACTER_SET, encoding);
        }

        BitMatrix result = new MultiFormatWriter().encode(sCode, format, nWidth, nHeight, hints);
        int width = result.getWidth();
        int height = result.getHeight();
        int[] pixels = new int[width * height];
        for (int y = 0; y < height; y++) {
            int offset = y * width;
            for (int x = 0; x < width; x++) {
                pixels[offset + x] = result.get(x, y) ? BLACK : WHITE;
            }
        }

        return Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
    }

    public Bitmap encodeBitmap2(String sCode) {
        try {
            BitMatrix result = new MultiFormatWriter().encode(sCode, BarcodeFormat.CODE_128, 300, 100, null);
            int w = result.getWidth();
            int h = result.getHeight();
            int[] pixels = new int[w * h];

            for (int y = 0; y < h; y++) {
                int offset = y * w;
                for (int x = 0; x < w; x++) {
                    pixels[offset + x] = result.get(x, y) ? Color.BLACK : Color.WHITE;
                }
            }

            return Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private void startCode() {
        barcodeView.setVisibility(View.VISIBLE);
        barcodeView.decodeContinuous(result -> handleResult(result.getText()));
        barcodeView.resume();
    }

    private void handleResult(String result) {
        textView1.setText(result);

        new Handler().postDelayed(() -> barcodeView.resume(), 1000); // 1초 후 카메라 재개
    }
}
