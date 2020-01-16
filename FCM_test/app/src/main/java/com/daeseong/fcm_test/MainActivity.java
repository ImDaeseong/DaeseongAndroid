package com.daeseong.fcm_test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String Token = FirebaseInstanceId.getInstance().getToken();
        if(isNullOrEmpty(Token)){
            Log.d(TAG, "not token = " + Token);
        }else {
            Log.d(TAG, "token = " + Token);
        }

        FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
            @Override
            public void onComplete(@NonNull Task<InstanceIdResult> task) {

                if(task.isSuccessful()){

                    String Token = task.getResult().getToken();
                    String Id = task.getResult().getId();
                    Log.d(TAG, "onComplete token = " + Token + " Id = " + Id);

                }else {
                    Log.d(TAG, "onComplete token = " + task.getException());
                }
            }
        });

    }

    private static boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }
}
