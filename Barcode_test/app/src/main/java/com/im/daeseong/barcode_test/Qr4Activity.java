package com.im.daeseong.barcode_test;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class Qr4Activity extends AppCompatActivity implements ZXingScannerView.ResultHandler{

    private ImageView imageView1, imageView2, imageView3;
    private TextView textView1;

    private ZXingScannerView zXingScannerView;

    private String sBarcode;

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
    }

    @Override
    protected void onResume() {
        super.onResume();

        //startCode();
    }

    @Override
    protected void onPause() {
        super.onPause();

        try {
            zXingScannerView.stopCamera();
        }catch (Exception e){
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        try {
            zXingScannerView.stopCamera();
        }catch (Exception e){
        }
    }

    //com.journeyapps.barcodescanner.BarcodeEncoder
    private void displayCode1(String sCode){
        MultiFormatWriter writer = new MultiFormatWriter();
        try{
            BitMatrix bitMatrix = writer.encode(sCode, BarcodeFormat.CODE_128, 300, 100);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            imageView1.setImageBitmap(bitmap);
        }catch (WriterException e){
        }
    }

    //encodeBitmap1
    private void displayCode2(String sCode){
        try {
            Bitmap bitmap = encodeBitmap1(sCode, BarcodeFormat.CODE_128, 300, 100);
            imageView2.setImageBitmap(bitmap);
        } catch (WriterException e) {
        }
    }

    private Bitmap encodeBitmap1(String sCode, BarcodeFormat format, int nWidht, int nHeight)  throws WriterException{

        final int WHITE = 0xFFFFFFFF;
        final int BLACK = 0xFF000000;

        String contentsToEncode = sCode;
        if (contentsToEncode == null) {
            return null;
        }

        Map<EncodeHintType, Object> hints = null;
        String encoding = guessAppropriateEncoding(contentsToEncode);
        if (encoding != null) {
            hints = new EnumMap<EncodeHintType, Object>(EncodeHintType.class);
            hints.put(EncodeHintType.CHARACTER_SET, encoding);
        }

        MultiFormatWriter writer = new MultiFormatWriter();
        BitMatrix result;
        try {
            result = writer.encode(contentsToEncode, format, nWidht, nHeight, hints);
        } catch (IllegalArgumentException iae) {
            // Unsupported format
            return null;
        }

        int width = result.getWidth();
        int height = result.getHeight();
        int[] pixels = new int[width * height];
        for (int y = 0; y < height; y++) {
            int offset = y * width;
            for (int x = 0; x < width; x++) {
                pixels[offset + x] = result.get(x, y) ? BLACK : WHITE;
            }
        }

        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return bitmap;
    }
    private String guessAppropriateEncoding(CharSequence contents) {
        for (int i = 0; i < contents.length(); i++) {
            if (contents.charAt(i) > 0xFF) {
                return "UTF-8";
            }
        }
        return null;
    }

    //encodeBitmap2
    private void displayCode3(String sCode){
        try {
            Bitmap bitmap = encodeBitmap2(sCode);
            imageView3.setImageBitmap(bitmap);
        } catch (Exception e) {
        }
    }

    public Bitmap encodeBitmap2(String sCode){
        try {
            BitMatrix result = new MultiFormatWriter().encode(sCode, BarcodeFormat.CODE_128, 300, 100, null);
            int w = result.getWidth();
            int h = result.getHeight();
            int[] pixels =  new int [ w * h];

            for (int y = 0; y < h; y++) {
                int offset = y * w;
                for (int x = 0; x < w; x++) {
                    pixels[offset + x] = result.get(x, y) ? Color.BLACK : Color.WHITE;
                }
            }

            Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
            bitmap.setPixels(pixels, 0, 300,0, 0, w, h);
            return bitmap;
        } catch (Exception e) {
        }
        return null;
    }

    private void startCode() {
        zXingScannerView = new ZXingScannerView(getApplicationContext());
        setContentView(zXingScannerView);
        zXingScannerView.setResultHandler(this);
        zXingScannerView.startCamera();
    }

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
                zXingScannerView.resumeCameraPreview(Qr4Activity.this);
            }
        }, 1000);

    }
}
