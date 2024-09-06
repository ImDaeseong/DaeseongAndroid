package com.daeseong.qrcode_test;

import androidx.annotation.NonNull;
import androidx.annotation.OptIn;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ExperimentalGetImage;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.content.ContextCompat;
import android.os.Bundle;
import android.util.Log;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.mlkit.vision.barcode.Barcode;
import com.google.mlkit.vision.barcode.BarcodeScanner;
import com.google.mlkit.vision.barcode.BarcodeScanning;
import com.google.mlkit.vision.common.InputImage;
import java.util.concurrent.ExecutionException;

public class Main5Activity extends AppCompatActivity {

    private static final String TAG = Main5Activity.class.getSimpleName();

    private PreviewView previewView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        previewView = findViewById(R.id.preview_view);

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

            } catch (ExecutionException | InterruptedException e) {
                Log.e(TAG, e.getMessage().toString());
            }
        }, ContextCompat.getMainExecutor(this)); // 메인 스레드에서 실행
    }

    @OptIn(markerClass = ExperimentalGetImage.class)
    private void bindPreview(@NonNull ProcessCameraProvider cameraProvider) {

        // 미리보기 설정
        Preview preview = new Preview.Builder().build();

        // 후면 카메라 선택
        CameraSelector cameraSelector = new CameraSelector.Builder()
                .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                .build();

        // PreviewView에 SurfaceProvider 설정
        preview.setSurfaceProvider(previewView.getSurfaceProvider());

        // 이미지 분석 설정
        ImageAnalysis imageAnalysis = new ImageAnalysis.Builder()
                .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST) // 최신 이미지 유지
                .build();

        // 이미지 분석기 설정
        imageAnalysis.setAnalyzer(ContextCompat.getMainExecutor(this), imageProxy -> {

            // 이미지에서 InputImage 생성
            InputImage image = InputImage.fromMediaImage(imageProxy.getImage(), imageProxy.getImageInfo().getRotationDegrees());

            // ML Kit 바코드 스캐너 클라이언트 생성
            BarcodeScanner scanner = BarcodeScanning.getClient();

            // 바코드 스캔 시작
            scanner.process(image)
                    .addOnSuccessListener(barcodes -> {

                        // 바코드 인식 성공
                        for (Barcode barcode : barcodes) {
                            String rawValue = barcode.getRawValue();
                            Log.e(TAG, "Barcode: " + rawValue);
                        }
                    })
                    .addOnFailureListener(e -> Log.e(TAG, e.getMessage().toString())) // 바코드 인식 실패 시
                    .addOnCompleteListener(task -> imageProxy.close()); // 이미지 프로세싱 완료 후 이미지 닫기
        });

        // 카메라와 라이프사이클 바인딩
        cameraProvider.bindToLifecycle(this, cameraSelector, preview, imageAnalysis);
    }
}
