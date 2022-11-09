package com.daeseong.changedeprecated.Common;

import android.graphics.Bitmap;
import java.util.concurrent.Callable;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class rxUtil {

    private static final String TAG = rxUtil.class.getSimpleName();

    //convert AsyncTask<String, Void, String>
    public static Observable<String> GetDataString(String sUrl){

        return Observable.fromCallable(new Callable<String>() {
            @Override
            public String call() throws Exception {

                String sResult = "";
                try {
                    sResult = HttpUtil.GetDataString(sUrl);
                }catch (Exception e){
                    e.printStackTrace();
                }
                return sResult;
            }
        })
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread());
    }

    //convert AsyncTask<String, Void, Bitmap>
    public static Observable<Bitmap> GetDataBitmap(String sUrl){

        return Observable.fromCallable(new Callable<Bitmap>() {
            @Override
            public Bitmap call() throws Exception {

                Bitmap bm = null;
                try {
                    bm = HttpUtil.GetDataBitmap(sUrl);
                }catch (Exception e){
                    e.printStackTrace();
                }
                return bm;
            }
        })
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread());
    }
}
