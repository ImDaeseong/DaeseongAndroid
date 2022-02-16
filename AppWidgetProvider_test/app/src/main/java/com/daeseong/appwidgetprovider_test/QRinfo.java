package com.daeseong.appwidgetprovider_test;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.Log;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class QRinfo {

    private static final String TAG = QRinfo.class.getSimpleName();

    public static Bitmap CreateQRrcode(String sMessgae, int nwidth, int nheight){

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
                    bitmap.setPixel(x, y, bitMatrix.get(x, y) ? Color.BLACK : Color.rgb(219, 250, 170));//bitmap.setPixel(x, y, bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE);
                }
            }

        }catch (Exception ex){
            Log.e(TAG, ex.getMessage().toString());
        }

        return bitmap;
    }
}
