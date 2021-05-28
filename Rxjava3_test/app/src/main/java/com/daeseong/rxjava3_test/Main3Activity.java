package com.daeseong.rxjava3_test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class Main3Activity extends AppCompatActivity {

    private static final String TAG = Main3Activity.class.getSimpleName();

    private static final String DATABASE_NAME = "IndonesiaDB.db";

    private Disposable disposable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        try{

            boolean bFile = isFile();
            if(!bFile){

                //Log.e(TAG, "isFile Not Found");

                CopyDBfile(this);

            } else {

                //Log.e(TAG, "isFile Found");
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void CopyDBfile(Context context){

        final String PATH_DATABASE = "/data/data/" + context.getApplicationContext().getPackageName() + "/databases/";

        disposable = Observable.fromCallable(() -> {

            //Log.e(TAG, "doInBackground");

            try {

                InputStream myinput = context.getAssets().open(DATABASE_NAME);
                File file = new File(PATH_DATABASE + DATABASE_NAME);

                if(!file.exists()) {
                    File dir  = new File(PATH_DATABASE);
                    dir.mkdirs();

                    OutputStream myoutput =  new FileOutputStream(PATH_DATABASE + DATABASE_NAME);
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = myinput.read(buffer)) > 0) {
                        myoutput.write(buffer, 0, length);
                    }

                    myoutput.flush();
                    myoutput.close();
                    myinput.close();
                }

            } catch(IOException e) {
                e.printStackTrace();
            }

            return false;
        })
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe((result) -> {

            //Log.e(TAG, "onPostExecute");

            disposable.dispose();
        });
    }

    private boolean isFile(){

        String sFile = "/data/data/" + getPackageName() + "/databases/" + DATABASE_NAME;
        File file = new File(sFile);
        if(file.exists()) {
            return true;
        }
        return false;
    }

}