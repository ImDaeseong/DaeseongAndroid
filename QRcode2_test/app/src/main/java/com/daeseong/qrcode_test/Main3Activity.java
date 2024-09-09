package com.daeseong.qrcode_test;

import android.os.Bundle;
import android.util.Log;
import android.util.Size;
import androidx.annotation.OptIn;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ExperimentalGetImage;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.content.ContextCompat;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.barcode.Barcode;
import com.google.mlkit.vision.barcode.BarcodeScanning;
import com.google.mlkit.vision.barcode.BarcodeScanner;
import com.google.common.util.concurrent.ListenableFuture;

public class Main3Activity extends AppCompatActivity {

    private static final String TAG = Main3Activity.class.getSimpleName();

    private PreviewView previewView;
    private BarcodeScanner barcodeScanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        previewView = findViewById(R.id.previewView);

        //BarcodeScanner 초기화
        barcodeScanner = BarcodeScanning.getClient();

        // 카메라 시작
        startCamera();
    }

    private void startCamera() {

        // 카메라 프로바이더 인스턴스 얻기
        ListenableFuture<ProcessCameraProvider> cameraProviderFuture = ProcessCameraProvider.getInstance(this);
        cameraProviderFuture.addListener(() -> {
            try {

                // 카메라 프로바이더 인스턴스를 가져온 후 바인딩
                ProcessCameraProvider cameraProvider = cameraProviderFuture.get();
                bindPreview(cameraProvider);

            } catch (Exception e) {
                Log.e(TAG, e.getMessage().toString());
            }
        }, ContextCompat.getMainExecutor(this));
    }

    @OptIn(markerClass = ExperimentalGetImage.class)
    private void bindPreview(ProcessCameraProvider cameraProvider) {

        // 미리보기 설정
        Preview preview = new Preview.Builder().build();
        CameraSelector cameraSelector = new CameraSelector.Builder()
                .requireLensFacing(CameraSelector.LENS_FACING_BACK) // 후면 카메라 선택
                .build();

        // 이미지 분석 설정
        ImageAnalysis imageAnalysis = new ImageAnalysis.Builder()
                .setTargetResolution(new Size(1280, 720)) // 해상도 설정
                .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST) // 최신 이미지만 유지
                .build();

        // 이미지 분석기 설정
        imageAnalysis.setAnalyzer(ContextCompat.getMainExecutor(this), image -> {

            // 이미지에서 입력 이미지 생성
            InputImage inputImage = InputImage.fromMediaImage(image.getImage(), image.getImageInfo().getRotationDegrees());

            // 바코드 스캔 시작
            barcodeScanner.process(inputImage)
                    .addOnSuccessListener(barcodes -> {

                        if (!barcodes.isEmpty()) {
                            // 바코드 인식 성공
                            for (Barcode barcode : barcodes) {
                                String sText = barcode.getRawValue();
                                Log.e(TAG, "Barcode: " + sText);
                            }

                            // 인식 성공 시 카메라 멈춤
                            cameraProvider.unbindAll();
                        }

                    })
                    .addOnFailureListener(e -> Log.e(TAG, e.getMessage().toString())) // 바코드 인식 실패 시
                    .addOnCompleteListener(task -> image.close()); // 이미지 닫기
        });

        // 기존 바인딩 해제
        cameraProvider.unbindAll();

        // 라이프사이클에 카메라 바인딩
        cameraProvider.bindToLifecycle(this, cameraSelector, preview, imageAnalysis);

        // PreviewView에 SurfaceProvider 설정
        preview.setSurfaceProvider(previewView.getSurfaceProvider());
    }

}
