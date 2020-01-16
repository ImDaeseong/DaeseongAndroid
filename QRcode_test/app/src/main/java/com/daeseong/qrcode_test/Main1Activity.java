package com.daeseong.qrcode_test;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class Main1Activity extends AppCompatActivity {

    private static final String TAG = Main1Activity.class.getSimpleName();

    private ImageView iv1;
    private Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

        iv1 = findViewById(R.id.iv1);

        button1 = findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bitmap bitmap = CreateQRrcode("0123456789-qr:코드읽기", 512, 512);
                if(bitmap != null) {
                    iv1.setImageBitmap(bitmap);
                }
            }
        });
    }

    private Bitmap CreateQRrcode(String sMessgae, int nwidth, int nheight){

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = null;
        Bitmap bitmap = null;

        try {

            bitMatrix = qrCodeWriter.encode(sMessgae, BarcodeFormat.QR_CODE, nwidth, nheight);

            int width = bitMatrix.getWidth();
            int height = bitMatrix.getHeight();

            bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    bitmap.setPixel(x, y, bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE);
                }
            }

        }catch (Exception ex){
            Log.e(TAG, ex.getMessage().toString());
        }

        return bitmap;
    }

}
