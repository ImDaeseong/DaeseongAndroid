package com.daeseong.rxjava_test;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class Rxjava2Activity extends AppCompatActivity {

    private static final String TAG = Rxjava2Activity.class.getSimpleName();

    private String sTemp1 = "";
    private String sTemp2 = "";
    EditText edt1, edt2;

    private Observable<Integer> observable;
    private Observable<String> stringObservable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava2);

        edt1 = findViewById(R.id.edt1);
        edt2 = findViewById(R.id.edt2);

        justInteger();

        justString();
    }

    private void justInteger(){

        // subscribeOn 데이터 발행 스레드
        // observeOn 데이터를 소비 스레드
        observable = Observable.just(1,2,3,4,5,6,7,8,9,10);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .distinct() //중복 제거
                .subscribeWith(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                        Log.e(TAG, "onSubscribe");
                    }

                    @Override
                    public void onNext(@NonNull Integer integer) {

                        sTemp1 += String.valueOf(integer) + "\t";

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                edt1.setText(sTemp1);
                            }
                        });
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                        Log.e(TAG, "오류");
                    }

                    @Override
                    public void onComplete() {

                        Log.e(TAG, "작업 완료");
                    }
                });

    }

    private void justString(){

        // subscribeOn 데이터 발행 스레드
        // observeOn 데이터를 소비 스레드
        stringObservable = Observable.just("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
        stringObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .distinct() //중복 제거
                .subscribeWith(new Observer<String>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                        Log.e(TAG, "onSubscribe");
                    }

                    @Override
                    public void onNext(@NonNull String s) {

                        sTemp2 += s + "\t";

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                edt2.setText(sTemp2);
                            }
                        });
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                        Log.e(TAG, "오류");
                    }

                    @Override
                    public void onComplete() {

                        Log.e(TAG, "작업 완료");
                    }
                });

    }
}